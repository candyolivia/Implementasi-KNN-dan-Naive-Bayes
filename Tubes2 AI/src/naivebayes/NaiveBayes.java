package naivebayes;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.util.ArrayList;


public class NaiveBayes {
	
	DataModel dataModel;
	private Set<Integer> index = new HashSet<>();
	private List<String> classList;
    private final Map<String, BigInteger> classDenumerators = new HashMap<>();
    private Map<ConfusionObject, Integer> confusionMat = new HashMap<ConfusionObject, Integer>();

	
	public NaiveBayes (String filename) {
		dataModel = new DataModel();
		dataModel.createModel(filename);
	}
	
	public NaiveBayes (DataModel dm, String filename) {
		dataModel = new DataModel();
		this.dataModel = dm;
		dataModel.createModel(filename);
		classList = new ArrayList<String>(dataModel.getClasses());
		System.out.println(dataModel.getClasses().size());
	}

	public DataModel getDataModel() {
		return dataModel;
	}
	
	private void dataTesIdx(int iterasi) {
		index.clear();
        while (index.size() < (int)(0.1*dataModel.getElmtSize())) {
            int i;        
            for (i=iterasi*(dataModel.getElmtSize()/10); i<(iterasi+1)*(dataModel.getElmtSize()/10); i++) {
            	index.add(i);
            }
        }
    }
	
	public DataModel getDataTes(int iterasi) {
        DataModel dataTes = new DataModel();
        dataTesIdx(iterasi);
        for (Integer i: index) {
            dataTes.add(dataModel.getElmt(i), dataModel.getClass(i));
        }
        index.clear();
        return dataTes;
    }
	
	public DataModel getTrainingData(int iterasi) {
        DataModel trData = new DataModel();
        dataTesIdx(iterasi);
        for (int i = 0; i < dataModel.getElmtSize(); i++) {
            if (!index.contains(i)) {
                trData.add(dataModel.getElmt(i), dataModel.getClass(i));
            }
        }
        index.clear();
        return trData;
    }
	
	public String classPrediction(List<String> attributes) {
		for (Map.Entry<String, Integer> classEntry: dataModel.mappedClass().entrySet()) {
            classDenumerators.put(classEntry.getKey(), BigInteger.valueOf(dataModel.getElmtSize()).multiply(
            BigInteger.valueOf(classEntry.getValue()).pow(dataModel.getAttrSize())));
        }
		
        BigDecimal prob = BigDecimal.ZERO; //probability
        String prediction = null;
        for (Map.Entry<String, Integer> classKey: dataModel.mappedClass().entrySet()) {
            BigInteger numerator = BigInteger.valueOf(classKey.getValue());
            BigInteger denumerator = classDenumerators.get(classKey.getKey());
            if (denumerator.compareTo(BigInteger.ZERO) == 0) {
            	continue;
            }
            for (int i = 0; i < attributes.size(); i++) {
                String attr = attributes.get(i);
                String kelas = classKey.getKey();
                Map<String, Map<String, Integer>> map = dataModel.getKnowledgeBased().get(i);
                Map<String, Integer> key = map.get(attr);
                Integer val = key.get(kelas);
                
                if (val == null) {
                    numerator = BigInteger.ZERO;
                    break;
                }                    
                else {
                    numerator = numerator.multiply(BigInteger.valueOf(val));
                }
            }
            BigDecimal probability = new BigDecimal(numerator).divide(new BigDecimal(denumerator), MathContext.DECIMAL128);
            if (probability.compareTo(prob) >= 0) {
                prob = probability;
                prediction = classKey.getKey();
            }
        }
        return prediction;
    }
	
	public void predictFullTraining() {
		confusionMat.clear();
		int correct = 0;
        for (int i = 0; i < dataModel.getElmtSize(); i++) {
            String prediction = classPrediction(dataModel.getElmt(i));
            //System.out.println(prediction+"	"+dataModel.getClass(i));
            ConfusionObject key = new ConfusionObject(prediction, dataModel.getClass(i));
            confusionMat.put(
              key,
              (!confusionMat.containsKey(key)) ? 1 : (int)confusionMat.get(key) + 1
            );
            if (dataModel.getClasses().get(i).equals(prediction))
                correct++;
        }
        System.out.println("	Correctly Classified Instances		: " + correct);
		System.out.println("	Incorrectly Classified Instances	: " + ((dataModel.getElmtSize())-correct));
		System.out.println("	Number of Instances		: " + dataModel.getElmtSize());
		System.out.println("	Accuracy			: " + ((float)(correct*100)/dataModel.getElmtSize())+"%");
		System.out.print("\n\n     CONFUSION MATRIX :\n");
		List<String> value = new ArrayList<String>(dataModel.getClassValue());
		System.out.print("	\t");
		for (int i=0; i<value.size(); i++) {
			System.out.print(dataModel.getClassValue().get(i) + "	");
		}
		System.out.println();
		//System.out.print("\t");
		for (int i=0; i<value.size(); i++) {
			String val = dataModel.getClassValue().get(i);
			System.out.print("\t"+ val+"	");
			for (int j=0; j<value.size(); j++) {
				int temp = (int) (confusionMat.get(new ConfusionObject(val, value.get(j)))!= null ?confusionMat.get(new ConfusionObject(val, value.get(j))) : 0);
				System.out.print(temp+"	");
			}
			System.out.println();
		}
		System.out.println();
		System.out.println("	Horizontal Class Value	: Actual Class");
		System.out.println("	Vertical Class Value	: Predicted Class");
		
	}
	
	public void predictFoldCV(String filename) {
		confusionMat.clear();
		int res = 0;
		ArrayList<Float> accuracy = new ArrayList<>();
		for (int j=0; j<10; j++) {
			NaiveBayes nb = new NaiveBayes(filename);
			DataModel trData = nb.getTrainingData(j);
	        DataModel testData = nb.getDataTes(j);
	        int correct = 0;
	        for (int i = 0; i < testData.getElmtSize(); ++i) {
	            String prediction = nb.classPrediction(testData.getElmt(i));
	            //System.out.println(prediction+"	"+testData.getClass(i));
	            ConfusionObject key = new ConfusionObject(prediction, testData.getClass(i));
	            confusionMat.put(
	              key,
	              (!confusionMat.containsKey(key)) ? 1 : (int)confusionMat.get(key) + 1
	            );
	            if (testData.getClass(i).equals(prediction))
	                ++correct;
	        }
	        res += correct;
	        accuracy.add(((float)correct/testData.getElmtSize())*100);
	        
		}
		float mean = 0;
		for(float acc : accuracy) {
			mean += acc;
		}
		System.out.println("	Correctly Classified Instances		: " + res);
		System.out.println("	Incorrectly Classified Instances	: " + ((dataModel.getElmtSize())-res));
		System.out.println("	Number of Instances		: " + dataModel.getElmtSize());
		System.out.println("	Accuracy			: " + mean/10 +"%");
		System.out.print("\n\n     CONFUSION MATRIX :\n");
		List<String> value = new ArrayList<String>(dataModel.getClassValue());
		System.out.print("		");
		for (int i=0; i<value.size(); i++) {
			System.out.print(dataModel.getClassValue().get(i) + "	");
		}
		System.out.println();
		for (int i=0; i<value.size(); i++) {
			String val = dataModel.getClassValue().get(i);
			System.out.print("	"+val+"	");
			for (int j=0; j<value.size(); j++) {
				int temp = (int) (confusionMat.get(new ConfusionObject(val, value.get(j)))!= null ?confusionMat.get(new ConfusionObject(val, value.get(j))) : 0);
				System.out.print(temp+"	");
			}
			System.out.println();
		}
		System.out.println();
		System.out.println("	Horizontal Class Value	: Actual Class");
		System.out.println("	Vertical Class Value	: Predicted Class");
	}
	
	public void printModel() {
		//System.out.print(dataModel.getArrf());
		
	}
	
}
