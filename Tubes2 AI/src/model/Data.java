package model;

public class Data {
	private int numAttr;
	private String[] AttrData = new String[numAttr];

	public int getNumAttr() {
		return numAttr;
	}
	public void setNumAttr(int numAttr) {
		this.numAttr = numAttr;
	}
	public String[] getAttrData() {
		return AttrData;
	}
	public void setAttrData(String[] attrData) {
		AttrData = attrData;
	}
	public void setAttrDataN(String str, int idx) {
		AttrData[idx] = str;
	}
	
	public String getAttrDataN(int idx) {
		return AttrData[idx];
	}
	
	public void printDataStruct () {
		for (int i = 0; i < numAttr; i++) {
			System.out.print(AttrData[i]+" ");
		}
		System.out.println();
	}
}
