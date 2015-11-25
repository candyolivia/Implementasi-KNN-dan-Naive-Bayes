package naivebayes;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		//NaiveBayes nb = new NaiveBayes();
		Scanner sc = new Scanner(System.in);
		String filename = sc.nextLine();
		NaiveBayes nbFull = new NaiveBayes(filename); 
		nbFull.predictFullTraining();
		NaiveBayes nbFCV = new NaiveBayes(filename);
		nbFCV.predictFoldCV(filename);
		
	}	
}