package cn.edu.xmu.dm.bdbk.cluster;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.StringTokenizer;

public class Copy_2_of_Test {
	public Map<String, Double> map = new HashMap<String, Double>();
	
	public Map<String, Double> getArff(String inputPath, String outputPath){
		
		File outputFile = new File(outputPath);

		if (!outputFile.exists())
			try {
				outputFile.createNewFile();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(outputFile);
		} catch (FileNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		File file = new File(inputPath);

		BufferedReader br = null;
		try {
			// FileReader fr = new FileReader(file);
			InputStreamReader read = new InputStreamReader(new FileInputStream(
					file), "gbk");
			br = new BufferedReader(read);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String temp = null;


		StringBuffer sb = new StringBuffer();
		sb.append("@relation baidubaiketfidf")
		  .append("\n");
		try {
			temp = br.readLine();
			StringTokenizer   st   =   new   StringTokenizer(temp); 
			while(st.hasMoreTokens()){
				map.put(st.nextToken(), 0.0);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		while (temp != null) {
//			System.out.println(temp);
			StringTokenizer   st   =   new   StringTokenizer(temp); 
			while(st.hasMoreTokens()){
				map.put(st.nextToken(), 0.0);
			}
			try {
				temp = br.readLine();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

//		System.out.println(map.size());
		Iterator iter = map.entrySet().iterator(); 
		while (iter.hasNext()) { 
		    java.util.Map.Entry entry = (Map.Entry) iter.next(); 
		    String key = (String)entry.getKey(); 
		    double val = (Double)entry.getValue(); 
		    sb.append("@attribute ")
		      .append(key)
		      .append("	real")
		      .append("\n");
//		    System.out.println(key + ":" + val);
		}
		
		sb.append("@data")
		  .append("\n");
		try {
			out.write(sb.toString().getBytes("utf-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("特征个数:" + map.size());
		return map;
		
	}
	
	public void createArffFile (String inputPath, String outputPath){
		File outputFile = new File(outputPath);

		if (!outputFile.exists())
			try {
				outputFile.createNewFile();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(outputFile, true);
		} catch (FileNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		Map<String, Double> map = this.map;
		File file = new File(inputPath);

		BufferedReader br = null;
		try {
			// FileReader fr = new FileReader(file);
			InputStreamReader read = new InputStreamReader(new FileInputStream(
					file), "gbk");
			br = new BufferedReader(read);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String temp = null;

		try {
			temp = br.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		while (temp != null) {
			Iterator iter = map.entrySet().iterator(); 
			while (iter.hasNext()) { 
			    java.util.Map.Entry entry = (Map.Entry) iter.next(); 
			    entry.setValue(0.0); 
			}
			String fileName = temp.substring(0, temp.indexOf(".txt"));
			temp = temp.substring(temp.indexOf(".txt") + 4).trim();

			StringBuffer sb = new StringBuffer();
			String[] terms = temp.split("\t");
			for(int i = 0; i < terms.length; i++){
				String[] termAndTfidf = terms[i].split(":");
				if(termAndTfidf.length == 2){
					map.put(termAndTfidf[0], Double.parseDouble(termAndTfidf[1]));
				}
			}
			try {
				temp = br.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			iter = map.entrySet().iterator(); 
			while (iter.hasNext()) { 
			    java.util.Map.Entry entry = (Map.Entry) iter.next(); 
			    String key = (String)entry.getKey(); 
			    Double val = (Double)entry.getValue(); 
			    sb.append(val)
			      .append(",");
//			    System.out.println(key + ":" + val);
			}
			sb.append(fileName);
			sb.append("\n");
			
			try {
				out.write(sb.toString().getBytes("utf-8"));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		Copy_2_of_Test cot = new Copy_2_of_Test();
//		String inputPath = "C:\\Users\\Administrator\\Desktop\\singlemergefile\\alltfidfFinal.txt";
////		String inputPath = "C:\\Users\\Administrator\\Desktop\\dmchenwq\\hasharff.txt";
//		cot.getArff(inputPath, "C:\\Users\\Administrator\\Desktop\\dmchenwq\\hasharff.arff");
//		cot.createArffFile("C:\\Users\\Administrator\\Desktop\\dmchenwq\\part-r-00000","C:\\Users\\Administrator\\Desktop\\dmchenwq\\hasharff.arff");
	}
}
