package naivebayes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JTextArea;

import model.fileConverter;


public class DataModel {
	
	private fileConverter arrf;
	private List<String> data;
	private List<String> classList = new ArrayList<String>();
	private List<List<String>> attrList = new ArrayList<List<String>> ();
	private List<String> attr;
	private Map<String, List<String>> attributes;
	private Map<String, Integer> classes = new HashMap<String, Integer> ();
	private List<Map<String, Map<String, Integer>>> knowledgeBased = new ArrayList<Map<String, Map<String, Integer>>>();
	
	public DataModel() {
	
	}
	
	public void scanData(String filename) {
		arrf = new fileConverter();
		try {
			arrf.readFile(filename);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		data = new ArrayList<String> (arrf.getAttrData());
		attr = new ArrayList<String> (arrf.getAttr());
		attributes = new HashMap<String, List<String>>(arrf.getAttributes());
		classList.clear();
		attrList.clear();
		
		for (int i=0; i<data.size(); i++) {
			List<String> split = Arrays.asList(data.get(i).trim().split("[,]"));
			if (split.isEmpty()) {
				continue;
			}
			List<String> attr = new ArrayList<String>();
			for (int j=0; j<split.size(); j++) {
				String temp = new String(split.get(j).trim());
				if (j==split.size()-1) {
					classList.add(temp);
				} else {
					attr.add(temp);
				}
			}
			attrList.add(attr);
		}
	}
	
	public void add(List<String> attr, String kelas) {
        if (attrList.isEmpty() || attrList.get(0).size() == attr.size()) {
            attrList.add(attr);
            classList.add(kelas);
        }
    }
	
	public void createModel(String filename) {
		classes.clear();
		scanData(filename);
		
		for (int i=0; i<attrList.size(); i++) {
			String tempClass = new String (classList.get(i));
			int value = (classes.containsKey(tempClass)) ? (int) classes.get(tempClass)+1 : 1;
			classes.put(tempClass, value);
			
			if (i==0) { 
				for (int j=0; j<attrList.get(i).size(); j++) {
					Map<String, Map<String, Integer>> Map = new HashMap<String, Map<String, Integer>>();
					Map.put(attrList.get(i).get(j), new HashMap<String, Integer>());
					knowledgeBased.add(Map);
				}
				//counterScan(attrList.get(i), i);
			}
			counterScan(attrList.get(i), i);
		}
		
		/*for(Object M : knowledgeBased.toArray()) {
			System.out.println(M.toString());
		}
		
		List<String> keys = new ArrayList<String>(classes.keySet());
		for (String key: keys) {
		    System.out.print(key + ": " + classes.get(key) + "   ");
		}*/
	}
	
	public void counterScan(List<String> attString, int idx) {
		/* Menghitung atribut */
		int size = attString.size();
		for(int i=0;i<size;i++) {
			Map<String, Map<String, Integer>> map = knowledgeBased.get(i);
			if(map.containsKey(attString.get(i))) {
				Map<String, Integer> map2 = map.get(attString.get(i));
				if(map2.containsKey(classList.get(idx))) {
					map2.put(classList.get(idx), map2.get(classList.get(idx))+1);
				}
				else {
					map2.put(classList.get(idx), 1);
				}
				map.put(attString.get(i), map2);
			}
			else {
				Map<String, Integer> map2 = new HashMap<String, Integer>();
				map2.put(classList.get(idx), 1);
				map.put(attString.get(i), map2);
			}
		}
	}
	
	public StringBuffer stringHypothesis() {
		StringBuffer sb = new StringBuffer();
		List<Integer> count = new ArrayList<Integer>();
		sb.append("\n\n\n");
		sb.append("	   	");
		List<String> classValue = new ArrayList<String>(attributes.get(attr.get(attr.size()-1)));
		for (String c : classValue) {
			sb.append("      "+c + "	");
		}
		sb.append("\n");
		for (int i=0; i<attr.size()-1; i++) {
			count.clear();
			Map<String, Map<String, Integer>> knowledge = new HashMap<String, Map<String, Integer>>(knowledgeBased.get(i));
			List<String> keys = new ArrayList<String>(knowledge.keySet());
			sb.append("	      "+attr.get(i));
			sb.append("\n");
			for (String c : classValue) {
				count.add(0);
			}
			for (String key: keys) {
				sb.append("	         "+key + "  	");
			    //if (key.length() < 4) System.out.print("	");
			    Map<String, Integer> keysVal = new HashMap<String, Integer>(knowledge.get(key));
			    for (int j=0; j<classValue.size(); j++) {
			    	int value = (keysVal.get(classValue.get(j))==null ? 0 : keysVal.get(classValue.get(j)));
			    	count.set(j, count.get(j)+value);
			    	//System.out.println(count.get(j));
			    	sb.append("       "+value + "	");
				}
			    sb.append("\n");
			}
			sb.append("	         "+"Total"+"	");
			for (int k=0; k<count.size(); k++) {
				sb.append("       "+count.get(k)+"	");
			}
			sb.append("\n\n");
			//System.out.println();
		}
		return sb;
	}
	
	public int getElmtSize() {
        return attrList.size();
    }

    public int getAttrSize() {
        return attrList.get(0).size();
    }
    
    public List<String> getElmt(int idx) {
        if (idx >= 0 && idx < attrList.size())
            return attrList.get(idx);
        else 
            return null;
    }
    
    public String getClasses(int idx) {
        if (idx >= 0 && idx < classList.size())
            return classList.get(idx);
        else
            return null;
    }
    
    public String getAttr(int elmtIdx, int attrIdx) {
        if (elmtIdx >= 0 && elmtIdx < attrList.size() && attrIdx >= 0 && attrIdx < attrList.get(elmtIdx).size())
            return attrList.get(elmtIdx).get(attrIdx);
        else
            return null;
    }
    
    public List<Map<String, Map<String, Integer>>> getKnowledgeBased() {
    	return knowledgeBased;
    }

    public Map<String, Integer> mappedClass() {
    	return classes;
    }
    
    public fileConverter getArrf() {
    	return arrf;
    }
}
