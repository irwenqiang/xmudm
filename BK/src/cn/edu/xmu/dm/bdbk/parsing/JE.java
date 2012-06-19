package cn.edu.xmu.dm.bdbk.parsing;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import jeasy.analysis.MMAnalyzer;

public class JE {
	public void getItemContent(String inputPath, String outputPath) {
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
			MMAnalyzer mm = new MMAnalyzer(2);
			try {
				temp = mm.segment(temp, "\t");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			sb.append(temp);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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

	public static void main(String[] args) {
		JE bic = new JE();

		String filePath = "C:/Users/Administrator/Desktop/bdbkFinal_item_content/";
		String outPutPath = "C:/Users/Administrator/Desktop/bdbkFinal_item_content_mm/";
		File folder = new File(filePath);
		File[] files = folder.listFiles();
		
		for (int i = 0; i < files.length; i++) {
			String fileName = files[i].getName().substring(0, files[i].getName().indexOf(".txt"));
			System.out.println(i + ": " + fileName);
			bic.getItemContent(files[i].getAbsolutePath(), outPutPath + "/" + fileName
					+ ".txt");
		}
	}
}