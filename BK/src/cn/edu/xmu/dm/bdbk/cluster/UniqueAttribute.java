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
import java.util.HashMap;
import java.util.Map;

import cn.edu.xmu.dm.bdbk.utils.RandString;

public class UniqueAttribute {
	public Map<String, String> map = new HashMap<String, String>();
	
	public void getUniqueAttribute(String inputPath, String outputPath){
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
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		String list[] = { "0", "1", "2", "3", "4", "5","6","7","8","9","a" };
		this.perm(list, 0, list.length-1);
		
		System.out.println("随机数组合完毕...");
		int i = 0;
		System.out.println(this.arrangeList.size());
		while (temp != null) {
			if(temp.contains("@attribute")){
				String[] temps = temp.split(" ");
				sb.append(temps[0])
				  .append(" ")
				  .append(this.arrangeList.get(i))
				  .append(" ")
				  .append("real")
				  .append("\n");
				i++;
			}else{
				sb.append(temp)
				  .append("\n");
			}
			try {
				temp = br.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//			System.out.println(sb.toString());
		}
		System.out.println(map.size());
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

	private int total = 0;
	private ArrayList<String> arrangeList = new ArrayList<String>();

	private void swap(String list[], int k, int i) {
		String c3 = list[k];
		list[k] = list[i];
		list[i] = c3;
	}

	public void perm(String list[], int k, int m) {
		if (k > m) {
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i <= m; i++) {
				sb.append(list[i]).append("");
			}
			if (sb.length()>0) {
				sb.setLength(sb.length()-1);
			}
			arrangeList.add(sb.toString());
			total++;
		} else {
			for (int i = k; i <= m; i++) {
				swap(list, k, i);
				perm(list, k + 1, m);
				swap(list, k, i);
			}
		}
	}
	
	public static void main(String[] args) {
		UniqueAttribute ua = new UniqueAttribute();

		String filePath = "C:\\Users\\Administrator\\Desktop\\dmchenwq\\hasharff.arff";
		String outputPath = "C:\\Users\\Administrator\\Desktop\\dmchenwq\\hasharffdigit2.txt";
		ua.getUniqueAttribute(filePath, outputPath);
	}
}
