package cn.edu.xmu.dm.bdbk.parsing;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class BaikeItemCorrecter {
	public void correctItem(String inputPath, String outputPath) {
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
					file), "gb2312");
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

		StringBuffer sb = new StringBuffer();
		String title = "";
		while (temp != null) {
			if(temp.contains("词条名称:")){
				title = temp.substring(temp.indexOf(":") + 1);
			}
			if(!temp.contains("词条内容:")){
				sb.append(temp)
				  .append("\n");
//				System.out.println(temp);
			}else if(temp.contains("词条内容:")){
//				System.out.println(temp);
				String pre = title;
//				System.out.println("pre: " + pre);
//				System.out.println(temp);
				int titleIndex = temp.indexOf(pre);
						
				if(titleIndex != -1){
					temp = temp.substring(titleIndex);
				}else{
					int index = temp.indexOf("拆分词条");
					if(index != -1){
						temp = temp.substring(index);
					}else{
						int index2 = temp.indexOf("百科商城           . . ..-- _..\"...\" \"...\" -");
						if(index2 != -1){
							temp = temp.substring(index2);
						}
						
					}
				}
				
				
				int expandReadingIndex = temp.indexOf("拓展阅读");
				if(expandReadingIndex != -1){
					temp = temp.substring(0, temp.indexOf("拓展阅读"));
				}else{
					int tabIndex = temp.indexOf("开放分类");
					if(tabIndex != -1){
						temp = temp.substring(0, tabIndex);
					}
				}
				
//				System.out.println(temp);
				sb.append(temp)
				  .append("\n");
			}
			try {
				temp = br.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		try {
			out.write(sb.toString().getBytes("utf8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		BaikeItemCorrecter bic = new BaikeItemCorrecter();

		String filePath = "C:/Users/Administrator/Desktop/bdbkFinal/";
		String outPutPath = "C:/Users/Administrator/Desktop/bdbkFinal_item_correct/";
		File folder = new File(filePath);
		File[] files = folder.listFiles();
		
		for (int i = 0; i < files.length; i++) {
			String fileName = files[i].getName().substring(0, files[i].getName().indexOf(".txt"));
			System.out.println(i + ": " + fileName);
			bic.correctItem(files[i].getAbsolutePath(), outPutPath + "/" + fileName
					+ ".txt");
		}
	}
}
