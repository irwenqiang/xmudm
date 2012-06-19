package cn.edu.xmu.dm.lexical;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StopwordClearer {
	private Set<String> stopwords = new HashSet<String>();

	public Set<String> readFile(String fileName) {
		File file = new File(fileName);

		BufferedReader br = null;
		try {
			InputStreamReader read = new InputStreamReader(new FileInputStream(
					file), "utf-8");
			br = new BufferedReader(read);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {

			e.printStackTrace();
		}
		String temp = null;

		try {
			temp = br.readLine();
			stopwords.add(temp);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		while (temp != null) {
			stopwords.add(temp);
			try {
				temp = br.readLine();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}		
		return this.stopwords;
	}
	
	public void removeStopword(String inputPath, String outputPath) {
		File outputFile = new File(outputPath);

		if (!outputFile.exists())
			try {
				outputFile.createNewFile();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(outputFile);
		} catch (FileNotFoundException e2) {
			e2.printStackTrace();
		}

		File file = new File(inputPath);

		BufferedReader br = null;
		try {
			InputStreamReader read = new InputStreamReader(new FileInputStream(
					file), "utf-8");
			br = new BufferedReader(read);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String temp = null;


		StringBuffer sb = new StringBuffer();
		try {
			temp = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}

		while(temp != null){
			Pattern pattern = Pattern.compile("[0-9]");
			Matcher matcher = pattern.matcher(temp);
			
			temp = matcher.replaceAll("");
			
			StringTokenizer   st   =   new   StringTokenizer(temp); 
			while(st.hasMoreTokens()){
				String word = st.nextToken();
				if(this.stopwords.contains(word)){
					continue;
				}else{
					sb.append(word)
					  .append("\t");
				}
			}
			try {
				temp = br.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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

	public void removeStopword(File[] files, String fileOutputDir){
		for (int i = 0; i < files.length; i++) {
			String fileName = files[i].getName().substring(0, files[i].getName().indexOf(".txt"));
			this.removeStopword(files[i].getAbsolutePath(), fileOutputDir + "/" + fileName
					+ ".txt");
		}
	}
	
	public long calcDistinctWords(File[] files){
		
		Set<String> distinctWords = new HashSet<String>();
		
		for (int i = 0; i < files.length; i++) {
			File file = new File(files[i].getAbsolutePath());
			System.out.println(i);
			BufferedReader br = null;
			try {
				InputStreamReader read = new InputStreamReader(new FileInputStream(
						file), "utf-8");
				br = new BufferedReader(read);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			String temp = null;


			StringBuffer sb = new StringBuffer();
			try {
				temp = br.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}

			while(temp != null){
				StringTokenizer   st   =   new   StringTokenizer(temp); 
				while(st.hasMoreTokens()){
					String word = st.nextToken();
					System.out.println(word);
					distinctWords.add(word);
				}
				try {
					temp = br.readLine();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return distinctWords.size();
	}
	
	public static void main(String[] args) {
		
	}
}
