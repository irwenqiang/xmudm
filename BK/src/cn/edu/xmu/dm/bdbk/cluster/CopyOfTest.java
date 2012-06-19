package cn.edu.xmu.dm.bdbk.cluster;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.StringTokenizer;

public class CopyOfTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String filePath = "C:/Users/Administrator/Desktop/bdbkFinal_item_content_mm/";
		File folder = new File(filePath);
		File[] files = folder.listFiles();
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		
		for (int i = 0; i < files.length; i++) {
			String fileName = files[i].getAbsolutePath();
//			System.out.println(i + ": " + fileName);
			System.out.println(i);
			File file = new File(fileName);

			BufferedReader br = null;
			try {
				// FileReader fr = new FileReader(file);
				InputStreamReader read = new InputStreamReader(new FileInputStream(
						file), "utf-8");
				br = new BufferedReader(read);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String temp = null;


			StringBuffer sb = new StringBuffer();
			try {
				temp = br.readLine();
				if(temp == null){
					continue;
				}
				StringTokenizer   st   =   new   StringTokenizer(temp); 
				while(st.hasMoreTokens()){
					map.put(st.nextToken(), 0);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
		}
		
		System.out.println(map.size());
		
//		Iterator iter = map.entrySet().iterator(); 
//		while (iter.hasNext()) { 
//		    java.util.Map.Entry entry = (Map.Entry) iter.next(); 
//		    String key = (String)entry.getKey(); 
//		    int val = (Integer)entry.getValue(); 
//		    System.out.println(key + ":" + val);
//		}
	}
}
