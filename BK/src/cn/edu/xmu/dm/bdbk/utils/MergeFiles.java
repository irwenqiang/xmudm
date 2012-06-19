package cn.edu.xmu.dm.bdbk.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class MergeFiles {
	public String mergeFiles(File file) {

		String fileName = file.getAbsolutePath().substring(file.getAbsolutePath().lastIndexOf("\\") + 1);
		System.out.println(fileName);

		BufferedReader br = null;
		try {
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
		
		sb.append(fileName).append("\t");
		try {
			temp = br.readLine();
			sb.append(temp).append("\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return sb.toString();
	}

	public static void main(String[] args) {
		MergeFiles mf = new MergeFiles();

		String filePath = "C:/Users/Administrator/Desktop/bdbkFinal_item_content_mm/";
		
		File folder = new File(filePath);
		File[] files = folder.listFiles();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < files.length; i++) {
			String fileNameAndContent = mf.mergeFiles(files[i]);
			sb.append(fileNameAndContent);
		}
		
		String outputPath = "C:/Users/Administrator/Desktop/singlemergefile/alltfidf.txt" ;
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
