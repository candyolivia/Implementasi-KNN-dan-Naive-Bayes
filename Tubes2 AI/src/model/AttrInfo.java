package model;

public class AttrInfo {
	private int numAttr;
	private String attrName;
	private String[] AttrList;
	
	public String getAttrName() {
		return attrName;
	}
	public void setAttrName(String attrName) {
		this.attrName = attrName;
	}

	public int getNumAttr() {
		return numAttr;
	}
	public void setNumAttr(int numAttr) {
		this.numAttr = numAttr;
	}
	public String[] getAttrList() {
		return AttrList;
	}
	
	public String getAttrListN(int idx) {
		return AttrList[idx];
	}
	
	public void setAttrListN(String str, int idx) {
		AttrList[idx] = str;
	}
	
	public void setAttrList(String[] str) {
		AttrList = str;
	}
	
	public void printAttrList () {
		System.out.println(attrName + ":");
		System.out.print("{");
		for (int i = 0; i < numAttr; i++) {
			if (i < numAttr-1) {
				System.out.print(AttrList[i]+",");
			} else {
				System.out.print(AttrList[i]);
			}
			
		}
		System.out.println("}");
	}
}
