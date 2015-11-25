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
	private List<String> data = new ArrayList<String> ();
	private Set<Integer> index = new HashSet<>();
    private final Map<String, BigInteger> classDenumerators = new HashMap<>();

	
	public NaiveBayes (String filename) {
		dataModel = new DataModel();
		dataModel.createModel(filename);
	}
	
	public NaiveBayes (DataModel dm, String filename) {
		dataModel = new DataModel();
		this.dataModel = dm;
		dataModel.createModel(filename);
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
            dataTes.add(dataModel.getElmt(i), dataModel.getClasses(i));
        }
        index.clear();
        return dataTes;
    }
	
	public DataModel getTrainingData(int iterasi) {
        DataModel trData = new DataModel();
        dataTesIdx(iterasi);
        for (int i = 0; i < dataModel.getElmtSize(); i++) {
            if (!index.contains(i)) {
                trData.add(dataModel.getElmt(i), dataModel.getClasses(i));
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
		int correct = 0;
        for (int i = 0; i < dataModel.getElmtSize(); i++) {
            String prediction = classPrediction(dataModel.getElmt(i));
            if (dataModel.getClasses(i).equals(prediction))
                correct++;
        }
        System.out.println("Correctly Classified Instances		: " + correct);
		System.out.println("Incorrectly Classified Instances	: " + ((dataModel.getElmtSize())-correct));
		System.out.println("Number of Instances			: " + dataModel.getElmtSize());
		System.out.println("Accuracy				: " + ((float)(correct*100)/dataModel.getElmtSize())+"%");
	}
	
	public void predictFoldCV(String filename) {
		int res = 0;
		ArrayList<Float> accuracy = new ArrayList<>();
		for (int j=0; j<10; j++) {
			NaiveBayes nb = new NaiveBayes(filename);
			DataModel trData = nb.getTrainingData(j);
	        DataModel testData = nb.getDataTes(j);
	        int correct = 0;
	        for (int i = 0; i < testData.getElmtSize(); ++i) {
	            String prediction = nb.classPrediction(testData.getElmt(i));
	            if (testData.getClasses(i).equals(prediction))
	                ++correct;
	        }
	        res += correct;
	        accuracy.add(((float)correct/testData.getElmtSize())*100);
	        
		}
		float mean = 0;
		for(float acc : accuracy) {
			mean += acc;
		}
		System.out.println("Correctly Classified Instances		: " + res);
		System.out.println("Incorrectly Classified Instances	: " + ((dataModel.getElmtSize())-res));
		System.out.println("Number of Instances			: " + dataModel.getElmtSize());
		System.out.println("Accuracy				: " + mean/10 +"%");
		//printModel();
	}
	
	public void printModel() {
		//System.out.print(dataModel.getArrf());
		
	}
	
}
