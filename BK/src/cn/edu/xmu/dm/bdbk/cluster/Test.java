package cn.edu.xmu.dm.bdbk.cluster;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String inputPath = "C:\\Users\\Administrator\\Desktop\\tfidfresult\\tfidfout\\part-r-00000";
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
			System.out.println(temp);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		int cnt = 0;
		while (temp != null) {
			try {
				temp = br.readLine();
				if(cnt > 10000000){
					System.out.println(temp);
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			cnt++;
			if (cnt == 10000040){
				break;
			}
		}
	}
}
