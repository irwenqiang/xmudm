package cn.edu.xmu.dm.bdbk.parsing;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class BaikeItemContent {
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
			temp = br.readLine();
			temp = temp.replaceAll("\\pP|\\pS", "");
			sb.append(temp)
			  .append("\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


//		while (temp != null) {
//			if(temp.contains("词条内容:")){
//				temp.substring(temp.indexOf("词条内容:") + 5);
//				sb.append(temp)
//				  .append("\n");
//			}
//			try {
//				temp = br.readLine();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}

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
		BaikeItemContent bic = new BaikeItemContent();

		String filePath = "C:/Users/Administrator/Desktop/bdbkFinal_item_correct/";
		String outPutPath = "C:/Users/Administrator/Desktop/bdbkFinal_item_content/";
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
