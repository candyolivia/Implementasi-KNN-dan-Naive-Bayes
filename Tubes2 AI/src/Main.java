import java.util.Scanner;

import knn.Process;
import naivebayes.*;

public class Main {
	public static void main (String args[]) {
		Scanner sc = new Scanner(System.in);
		System.out.print("File Name : ");
		String filename = sc.nextLine();
		
		System.out.println("1. Algoritma KNN");
		System.out.println("2. Algoritma Naive Bayes");
		System.out.print("Select Algorithm : ");
		int algo = Integer.parseInt(sc.nextLine());
		
		System.out.println("1. 10 Fold Cross Validation");
		System.out.println("2. Full Training");
		System.out.print("Select Schema : ");
		int schema = Integer.parseInt(sc.nextLine());
		
		System.out.println("==================================================================");
		if (algo == 1) {
			Process p = new Process(filename);
			p.setMode(schema);
			if (p.getMode() == 1) {
				System.out.println("             Test Mode : KNN 10-fold Cross Validation             ");
			} else {
				System.out.println("                  Test Mode : KNN Full Training                   ");
			}
			
			System.out.println("==================================================================");
			p.doKNN();
		} else if (algo == 2) {
			
			if (schema == 1) {
				System.out.println("          Test Mode : Naive-Bayes 10-fold Cross Validation          ");
				System.out.println("==================================================================");
				NaiveBayes nbFCV = new NaiveBayes(filename);
				nbFCV.predictFoldCV(filename);
			} else {
				NaiveBayes nbFull = new NaiveBayes(filename);
				System.out.println("               Test Mode : Naive-Bayes Full Training                ");
				System.out.println("==================================================================");
				nbFull.predictFullTraining();
			}
			
			 
			
			
			
		}
		System.out.println("==================================================================");
	}
}
