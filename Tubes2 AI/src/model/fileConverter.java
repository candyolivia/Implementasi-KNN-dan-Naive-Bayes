package model;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class fileConverter {
	private Data data;
	private AttrInfo attrInfo;
	private List<String> attrData = new ArrayList<String>();
	private ArrayList<Data> matrixData = new ArrayList<Data>();
	private ArrayList<AttrInfo> matrixAttrInfo = new ArrayList<AttrInfo>();
	Map<String, String> attr = new HashMap<String, String>();
	private int numData;
	private int[][] matrixDataInteger;
	
	public int getNumData() {
		return attrData.size();
	}

	public void setNumData(int numData) {
		this.numData = numData;
	}

	public void setData(Data data) {
		this.data = data;
	}
	
	public Data getData() {
		return data;
	}
	
	public List<String> getAttrData() {
		return attrData;
	}

	public ArrayList<Data> getMatrixData() {
		return matrixData;
	}

	public void setMatrixData(ArrayList<Data> matrixData) {
		this.matrixData = matrixData;
	}

	public ArrayList<AttrInfo> getMatrixAttrInfo() {
		return matrixAttrInfo;
	}

	public void setMatrixAttrInfo(ArrayList<AttrInfo> matrixAttrInfo) {
		this.matrixAttrInfo = matrixAttrInfo;
	}
	
	public void readFile(String filename) throws IOException {
		int lineSkip = 2;
		
		// Open the file
		FileInputStream fstream = new FileInputStream(filename);
		BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
		String strLine;
		
		//Skip first 2 Line
		for (int i = 0; i < lineSkip; i++) {
			br.readLine();
		}
		
		//Read File Line By Line
		while ((strLine = br.readLine()) != null)   {
			int idx = 0;
			strLine = strLine.replace("{", "");
			strLine = strLine.replace(",", "");
			strLine = strLine.replace("}", "");
			
			String[] SplitData = strLine.split(" ");
			
			//Fill Attribute Info
			if (SplitData[0].equals("@attribute")) {
				attrInfo = new AttrInfo();
                attrInfo.setNumAttr(SplitData.length-2);
                attrInfo.setAttrName(SplitData[1]);
                
                String[] strL = new String[attrInfo.getNumAttr()];
                attrInfo.setAttrList(strL);
                
                for (int i = 1; i < SplitData.length-1; i++) {
                	attrInfo.setAttrListN(SplitData[i+1], i-1);
                }
                matrixAttrInfo.add(attrInfo);
                idx++;  
                
            } else if (SplitData[0].equals("@data")) {
            	idx = 0;
            	while ((strLine = br.readLine()) != null)   {
            		attrData.add(strLine);
            		idx++;
            	}
            	numData = idx;
            }
		}
		
		//Close the input stream
		br.close();

	}
	
	public void dataToMatrix(String filename) throws IOException {
		readFile(filename);
		for (int i=0; i < attrData.size(); i++) {
			
			String[] dataTemp = attrData.get(i).split(",");
	
	        data =  new Data();
	        data.setNumAttr(dataTemp.length);
	        String[] dataAttr = new String[data.getNumAttr()];
	        data.setAttrData(dataAttr);
	        for (int j = 0; j < dataTemp.length; j++) {
	        	data.setAttrDataN(dataTemp[j], j);
	        }
	        matrixData.add(data);
	        
        }
        
	}
	
	public int[][] getMatrixDataInteger() {
		return matrixDataInteger;
	}

	public void setMatrixDataInteger(int[][] matrixDataInteger) {
		this.matrixDataInteger = matrixDataInteger;
	}

	public void convertDataToInteger() {
		matrixDataInteger = new int[numData][data.getNumAttr()];
		for (int i = 0; i < data.getNumAttr(); i++) {
			for (int j = 0; j < numData; j++) {
				for (int k = 0; k < matrixAttrInfo.get(i).getNumAttr(); k++) {
					if (matrixData.get(j).getAttrDataN(i).equals(matrixAttrInfo.get(i).getAttrListN(k))) {
						matrixDataInteger[j][i] = k;
					}
				}
			}
		}
	}
	
	public void printMatrixAttrInfo() {
		for (int i = 0; i < data.getNumAttr(); i++) {
			matrixAttrInfo.get(i).printAttrList();	
		}
	}
	
	public void printMatrixData() {
		for (int i = 0; i < numData; i++) {
			System.out.print("Data "+(i+1)+":");
			matrixData.get(i).printDataStruct();	
		}
	}
	
	public void printMatrixDataInteger() {
		for (int i = 0; i < numData; i++) {
			for (int j = 0; j < data.getNumAttr(); j++) {
				System.out.print(matrixDataInteger[i][j]+" ");
			}
			System.out.println();
		}
	}
	
	public AttrInfo getAttrInfo() {
		return attrInfo;
	}

	
}
