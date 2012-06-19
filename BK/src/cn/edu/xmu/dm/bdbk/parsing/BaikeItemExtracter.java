package cn.edu.xmu.dm.bdbk.parsing;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import cn.edu.xmu.dm.bdbk.utils.BdbkConstants;
import cn.edu.xmu.dm.bdbk.utils.HtmlRegexpUtil;
import cn.edu.xmu.dm.bdbk.utils.URLConnector;

public class BaikeItemExtracter {
	public String ParsingItem(String filePath, String outputPath)
			throws UnsupportedEncodingException {
		File file = new File(filePath);
		File outFile = new File(outputPath); // 结果保存文件

		if (!outFile.exists()) {
			try {
				outFile.createNewFile();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(outFile);
		} catch (FileNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		StringBuffer sbOut = new StringBuffer();

		BufferedReader br = null;
		try {
			// FileReader fr = new FileReader(file);
			InputStreamReader read = new InputStreamReader(new FileInputStream(
					file), "gb2312");
			br = new BufferedReader(read);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		String temp = null;

		try {
			temp = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}

		HtmlRegexpUtil hr = new HtmlRegexpUtil();
		URLConnector uc = new URLConnector();
		while (temp != null) {
			int titleIndexBegin = temp.indexOf(BdbkConstants.TITLE);
			
			int titleIndexEnd = temp.indexOf("</h1>");
//			System.out.println(titleIndexBegin + ":" + titleIndexEnd);
			String title = "";
			if(titleIndexBegin != -1){
				title = temp.substring(
						titleIndexBegin + 18,titleIndexEnd
						);
			}else{
				title = "未命名";
			}
			
			
//			int textContentIndex = temp.indexOf(BdbkConstants.TEXTCONTENT);
//			System.out.println("index: " + textContentIndex);
//			temp = temp.substring(textContentIndex);
//			String text = temp.substring(
//					temp.indexOf(BdbkConstants.TEXTCONTENT) + 37,
//					temp.indexOf("</p>"));
//			text = hr.filterHtml(text);
//
			int tabIndex = temp.indexOf(BdbkConstants.OPENTAB);
			String tab = "";
			if(tabIndex != -1){
			tab = temp.substring(tabIndex);
			
			tab = tab.substring(
					tab.indexOf(BdbkConstants.OPENTAB) + 14,
					tab.indexOf("</dd>"));
			tab = hr.filterHtml(tab);
			}else{
				tab = "";
			}
			
			
//			System.out.println("temp: " + temp);
			String text = temp.substring(temp.indexOf("<body"),temp.indexOf("</body>") + 7);
			text = hr.replaceNotNeed(text);
			
			temp = temp.substring(temp.indexOf("编辑次数：") + 5);
//			System.out.println(temp);
			String editTimes = temp.substring(0,temp.indexOf("次"));
//			System.out.println("editTimes:" + editTimes);
			
			
			temp = temp.substring(temp.indexOf("<span id=\"lastModifyTime\">") + 26);
			String lastUpdate = temp.substring(0,temp.indexOf("</span>"));
//			System.out.println("lastUpdate: " + lastUpdate);
						
			
			String key = filePath
					.substring(
							filePath.indexOf("C:/Users/Administrator/Desktop/baidubaike/baike/") + 49,
							filePath.indexOf(".html"));

			String sUrl = "http://like.baidu.com/get?key=" + key
					+ "&cb=getVoteData&type=0&like=1&pid=3&random=0";
//			System.out.println("sUrl: " + sUrl);
			String voteNum = uc.getVoteNum(sUrl);
			
			sUrl = "http://baike.baidu.com/api/lemmacnt/"+key;
			String viewNum = uc.getViewNum(sUrl);
//			System.out.println("viewNum2: " + viewNum);
			// System.out.println(tab);
			sbOut.append("词条名称:").append(title).append("\n").append("词条内容:").append(text).append("\n").append("帮组次数:")
					.append(voteNum).append("\n").append("浏览次数:").append(viewNum).append("\n").append("编辑次数:").append(editTimes).append("\n").append("最后更新:").append(lastUpdate).append("\n").append("开放类别:").append(tab).append("\n");
			try {
				temp = br.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			out.write(sbOut.toString().getBytes("gb2312"));
		} catch (UnsupportedEncodingException e) {

			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @param args
	 * @throws UnsupportedEncodingException
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws UnsupportedEncodingException, InterruptedException {
		BaikeItemExtracter bie = new BaikeItemExtracter();

		String filePath = "C:/Users/Administrator/Desktop/baidubaike/baike/";
		String outPutPath = "C:/Users/Administrator/Desktop/bdbk2/";
		File folder = new File(filePath);
		File[] files = folder.listFiles();
//		System.out.println(files.length);
		
		for (int i = 0; i < files.length; i++) {
			
			int last = 138634;
			if(i <= last){
				continue;
			}
//			if(i % 100 == 0){
//				Thread.sleep(5000);
//			}
			
			String fileName = files[i].getName().substring(0, files[i].getName().indexOf(".html"));
			System.out.println(i + ": " + fileName);
			bie.ParsingItem(files[i].getAbsolutePath(), outPutPath + "/" + fileName
					+ ".txt");
		}
	}
}
