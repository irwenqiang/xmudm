package cn.edu.xmu.dm.bdbk.cluster;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class Copy_3_of_Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String inputPath = "C:\\Users\\Administrator\\Desktop\\singlemergefile\\alltfidf.txt";
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

		int cnt = 0;
		while (temp != null) {
			temp = temp.substring(temp.indexOf(".txt") + 4).trim();
//			System.out.println(temp);
			sb.append(temp).append("\n");
			
			try {
				temp = br.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		File outputFile = new File("C:\\Users\\Administrator\\Desktop\\singlemergefile\\alltfidfFinal.txt");

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
		
		try {
			out.write(sb.toString().getBytes("gbk"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
