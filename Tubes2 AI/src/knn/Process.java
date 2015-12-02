package knn;

import java.io.IOException;

import model.*;

public class Process {
	private int numData;
	private int numAttr;
	private int [][] matrixData;
	private int numNN = 10;
	private int nfold = 10;
	private int numDistance;
	private int numPartition;
	private int[][] distance;
	private int[][] minDistance;
	private double[][] percentage;
	private int mode = 1; //Mode 1 for KNN 10-Fold Cross Validation (default)
						//Mode 2 for KNN Full Training
	public int[] analyseClass = new int[6]; // numAttr+1 for cardata.arff
	
	
	public int[] getAnalyseClass() {
		return analyseClass;
	}

	public void setAnalyseClass(int[] analyseClass) {
		this.analyseClass = analyseClass;
	}

	public int getNumData() {
		return numData;
	}

	public int getMode() {
		return mode;
	}

	public void setMode(int mode) {
		this.mode = mode;
	}

	public void setNumData(int numData) {
		this.numData = numData;
	}
	
	public Process(String filename) {
		init(filename);
	}
	
	public void init(String filename) {
		//ini data dummy untuk menentukan kelas
		for (int i = 0; i < 6; i++) {
			analyseClass[i] = 1;
		}
		
		fileConverter fc = new fileConverter();
		try {
			fc.dataToMatrix(filename);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		fc.convertDataToInteger();
		
		matrixData = new int[numData][fc.getData().getNumAttr()];
		matrixData = fc.getMatrixDataInteger();
		
		numData = fc.getNumData();
		numAttr = fc.getData().getNumAttr();
		numPartition = numData/10;
		numDistance = numData-numData/10;
		distance = new int[numPartition][numDistance];
		minDistance = new int[numPartition][numNN];
		percentage = new double[numNN][nfold];
		
	}
	
	public int countDifferent(int idxMatrixTraining, int idxMatrixTesting) {
		int countDiff = 0;
		for (int i = 0; i < numAttr-1; i++) {
			if (matrixData[idxMatrixTraining][i] != matrixData[idxMatrixTesting][i]) {
				countDiff++;
			}
		}
		return countDiff;
	}
	
	public double countPercentage(int NN) {
		int f = 0, t = 0;
		for (int i = 0; i < numPartition; i++) {
			if (determineClass(i,NN)) {
				t++;
			} else {
				f++;
			}
		}
		return (double)t/(double)(t+f);	
	}
	
	public void doKNN () {
		if (mode == 1) {
			for (int i = 1; i <= 10; i++) {
				knnFoldCrossValidation(i);
			}
			System.out.println("\tSelected k value\t\t : " + (chooseEffectiveNN()+1));
			
		} else {
			float max = 0; int idxmax = 1; float tmp;
			for (int i = 1; i <= 10; i++) {
				tmp = knnFullTraining(i);
				if (max < tmp) {
					max = tmp;
					idxmax = i;
				}
			}
			int trueInstance = (int) (max * numData);
			System.out.println("	Correctly Classified Instances \t\t : " + trueInstance + "     " + max*100 + "%" );
			System.out.println("	Incorrectly Classified Instances\t  : " + (numData - trueInstance) + "     " + (100 - max*100) + "%");
			System.out.println("	Number of Instances\t\t : " + (numData));
			System.out.println("	Selected k value\t\t : " + idxmax);
		}
	}
	
	public float knnFullTraining (int NN) {
		int t;
		int minValue = 1;
		int numNN;
		float accuracy = 0;
		for (int i = 0; i < numData; i++) {
			t = 0;
			numNN = 0;
			int j = 0;
			minValue = 1;
			while (numNN < NN) {
				for (j = 0; j < i; j++) {
					if (numNN >= NN) {
						break;
					} else if (countDifferent(i, j) == minValue) {
						if (matrixData[i][numAttr-1] == matrixData[j][numAttr-1])  {
							t++;
						}
						numNN++;
					}
				}
				
				for (j = i+1; j < numData; j++) {
					if (numNN >= NN) {
						break;
					} else if (countDifferent(i, j) == minValue) {
						if (matrixData[i][numAttr-1] == matrixData[j][numAttr-1]) {
							t++;
						}
						numNN++;
					} 
				}
				minValue++;
			}
			accuracy += (float)t/(float)NN;
		}
		return ((float)accuracy/(float)numData);
	}
	
	public void knnFoldCrossValidation (int partition) {
		for (int i = ((partition-1)*numPartition); i < (partition*numPartition); i++) {
			int tmp = 0;
			int minValue = 1;
			for (int j = 0; j < ((partition-1)*numPartition); j++) {
				distance[i-((partition-1)*numPartition)][j] = countDifferent(i,j);
				if (tmp < 10) {
					if (countDifferent(i,j) == minValue) {
						minDistance[i-((partition-1)*numPartition)][tmp] = j;
						tmp++;
					}
				}
			}
			
			for (int j = (partition*numPartition); j < numData; j++) {
				distance[i-(partition-1)*numPartition][j-((partition)*numPartition)] = countDifferent(i,j);
				if (tmp < 10) {
					if (countDifferent(i,j) == minValue) {
						minDistance[i-(partition-1)*numPartition][tmp] = j;
						tmp++;
					}
				}
				
			}
			
			minValue++;
			while (tmp<10) {
				for (int j = 0; j < numDistance; j++) {
					if (distance[i-(partition-1)*numPartition][j] == minValue){
						minDistance[i-(partition-1)*numPartition][tmp] = j+numPartition+1;
						tmp++;
					}
					if(tmp == 10) {
						break;
					}
				}
			}			
		}
		for (int i = 1; i < numNN; i++) {
			percentage[partition-1][i-1] = countPercentage(i);
			
		}
	}
	
	public boolean isClassTrue(int class_num, int idx) {
		return (matrixData[idx][numAttr-1] == class_num);
	}
	
	public boolean determineClass(int idx, int NN) {
		int classValue[] = new int[numAttr-1];
		for (int i = 0; i < NN; i++) {
			for (int j = 0; j < numAttr-1; j++) {
				if (matrixData[minDistance[idx][i]][numAttr-1] == j) {
					classValue[j]++;
				}
			}			
		}
		
		int max = 0; int idxmax = 0;
		for (int i = 0; i < numAttr-1; i++) {
			if (max < classValue[i]) {
				max = classValue[i];
				idxmax = i;
			}
		}
		
		return (isClassTrue(idxmax,idx));
		
	}
	
	public double countAverage(int NN) {
		double sum = 0;
		double avg = 0;
		for (int i = 0; i < nfold; i++) {
			sum += (double)percentage[i][NN-1];
		}
		avg = sum/(double)10;
		return avg;
	}
	
	public int chooseEffectiveNN() {
		double max = 0;
		int idxmax = 1;
		for (int i = 1; i < numNN; i++) {
			if (max < countAverage(i)) {
				max = countAverage(i);
				idxmax = i;
			}
		}
		int trueInstance = (int) (max * numData);
		System.out.println("\tCorrectly Classified Instances \t\t : " + trueInstance + "       " + max*100 + "%" );
		System.out.println("\tIncorrectly Classified Instances \t : " + (numData - trueInstance) + "       " + (100 - max*100) + "%");
		System.out.println("\tNumber of Instances\t\t : " + (numData));
		return idxmax;
	}
	
	
	
	public void printMatrixData() {
		for (int i = 0; i < numData; i++) {
			for (int j = 0; j < numAttr; j++) {
				System.out.print(matrixData[i][j]+" ");
			}
			System.out.println();
		}
	}
	
	public void printDistanceMatrix() {
		for (int i = 0; i < numPartition; i++) {
			for (int j = 0; j < numDistance; j++) {
				System.out.print(distance[i][j]+" ");
			}
			System.out.println();
		}
	}
	
	public void printMinDistanceMatrix() {
		for (int i = 0; i < numPartition; i++) {
			for (int j = 0; j < numNN; j++) {
				System.out.print(minDistance[i][j]+" ");
			}
			System.out.println();
		}
	}
	
	public void printPercentage() {
		for (int i = 0; i < numNN; i++) {
			for (int j = 0; j < nfold-1; j++) {
				System.out.print(percentage[i][j]+" ");
			}
			System.out.println();
		}
	}
	
	public int countDifferentArr(int[] dataTraining, int[] dataTesting) {
		int count = 0;
		for (int i = 0; i < 6; i++) {
			//System.out.println(dataTraining[i]+ " " + dataTesting[i]);
			if (dataTraining[i] != dataTesting[i]) {
				count++;
				//System.out.println(dataTraining[i]+ " " + dataTesting[i]);
			}
		}
		return count;
	}
	
	public void printAnalyseClass() {
		for (int i = 0; i < 6; i++)
			System.out.print(analyseClass[i]+" ");
	}
	
	public int determineClassCarData() {
		//numNN default 1
		int[] dataTmp = new int[6];
		
		int distance = 0;
		int determinedClass = 0;
		
		for (int i = 0; i < numData; i++) {
			for (int j = 0; j < 6; j++) {
				dataTmp[j] = matrixData[i][j];
				//System.out.print(matrixData[i][j]+" ");
			}
			//System.out.println();
			if (countDifferentArr(analyseClass,dataTmp) == distance) {
				determinedClass = matrixData[i][numAttr-1];
				System.out.println(i);
				break;
			}
		}
		return determinedClass;
	}
}
