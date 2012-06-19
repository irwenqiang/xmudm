package cn.edu.xmu.dm.bdbk.cluster;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Copy_4_of_Test {
	public static int index = 0;

	public Map<String, Integer> map = new HashMap<String, Integer>();

	public Map<String, Integer> getArff(String inputPath, String outputPath) {

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
		sb.append("@relation baidubaiketfidf").append("\n");
		try {
			temp = br.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		while (temp != null) {
			StringTokenizer st = new StringTokenizer(temp);
			while (st.hasMoreTokens()) {
				String key = st.nextToken();
				if (map.containsKey(key)) {
					continue;
				} else {
					map.put(key, index);
					System.out.println(index);
					index++;
				}
			}
			try {
				temp = br.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		for (Iterator i = sortByValue(map).iterator(); i.hasNext();) {
			String key = (String) i.next();
			sb.append("@attribute ").append(key).append("	real").append("\n");
			// System.out.printf("key: %s, value: %s\n", key, map.get(key));
		}

		sb.append("@attribute ").append("filename").append(" real")
				.append("\n");

		sb.append("@data").append("\n");
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

	public void createArffFile(String inputPath, String outputPath) {
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

		Map<String, Integer> map = this.map;

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
			String fileName = temp.substring(0, temp.indexOf(".txt"));
			temp = temp.substring(temp.indexOf(".txt") + 4).trim();

			StringBuffer sb = new StringBuffer();
			String[] terms = temp.split("\t");
			sb.append("{");
			Map<Integer, String> tmap = new TreeMap<Integer, String>();
			for (int i = 0; i < terms.length; i++) {
				String[] termAndTfidf = terms[i].split(":");
				if(termAndTfidf.length == 2){
					Integer tmp_index = map.get(termAndTfidf[0]);
					String tfidf = termAndTfidf[1];
					if(tmp_index != null){
						tmap.put(tmp_index, termAndTfidf[1]);
					}
				}
			}
			
			Iterator iter = tmap.entrySet().iterator(); 
			while (iter.hasNext()) { 
			    java.util.Map.Entry entry = (Map.Entry) iter.next(); 
			    Integer key = (Integer)entry.getKey(); 
			    String val = (String)entry.getValue(); 
				
				sb.append(key).append(" ").append(val)
				.append(",");
			}
			
			try {
				temp = br.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			sb.append(map.size()).append(" ").append(fileName);
			sb.append("}\n");

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

	public static List sortByValue(final Map m) {
		List keys = new ArrayList();
		keys.addAll(m.keySet());
		Collections.sort(keys, new Comparator() {
			public int compare(Object o1, Object o2) {
				Object v1 = m.get(o1);
				Object v2 = m.get(o2);
				if (v1 == null) {
					return (v2 == null) ? 0 : 1;
				} else if (v1 instanceof Comparable) {
					return ((Comparable) v1).compareTo(v2);
				} else {
					return 0;
				}
			}
		});
		return keys;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Copy_4_of_Test cot = new Copy_4_of_Test();

//		String inputPath = "C:\\Users\\Administrator\\Desktop\\dmchenwq\\hasharff.txt";
//		cot.getArff(inputPath,
//				"C:\\Users\\Administrator\\Desktop\\dmchenwq\\hasharff0.arff");
//		cot.createArffFile(
//				"C:\\Users\\Administrator\\Desktop\\dmchenwq\\test.txt",
//				"C:\\Users\\Administrator\\Desktop\\dmchenwq\\hasharff0.arff");

		 String inputPath =
		 "C:\\Users\\Administrator\\Desktop\\singlemergefile\\alltfidfFinal.txt";
		 cot.getArff(inputPath,
		 "C:\\Users\\Administrator\\Desktop\\dmchenwq\\hasharff.arff");
		 cot.createArffFile("C:\\Users\\Administrator\\Desktop\\dmchenwq\\part-r-all\\part-r-00000","C:\\Users\\Administrator\\Desktop\\dmchenwq\\hasharff.arff");
	}
}
