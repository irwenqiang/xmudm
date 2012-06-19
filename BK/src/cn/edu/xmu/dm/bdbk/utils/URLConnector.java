package cn.edu.xmu.dm.bdbk.utils;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class URLConnector {
	public String getVoteNum(String sUrl) {
		String voteNum = new String();
		HttpURLConnection conn = null;
		try {
			conn = (HttpURLConnection) new URL(sUrl).openConnection();
			int result = conn.getResponseCode();
			String re = conn.getResponseMessage();
			if (result == HttpURLConnection.HTTP_OK) {
				// System.out.println(re);
				InputStream in = conn.getInputStream();
				byte[] b = new byte[10240];
			
				while (in.read(b) > 0){
					voteNum += new String(b);
				}
			} else {
				System.out.println("connet fail!！");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.disconnect();
				conn = null;
			}
		}
//		System.out.println(voteNum);
		voteNum = voteNum.substring(voteNum.indexOf("\"count\":") + 8, voteNum.indexOf(",\""));
		return voteNum;
	}
	public String getViewNum(String sUrl) {
		String viewNum = new String();
		HttpURLConnection conn = null;
		try {
			conn = (HttpURLConnection) new URL(sUrl).openConnection();
			int result = conn.getResponseCode();
			String re = conn.getResponseMessage();
			if (result == HttpURLConnection.HTTP_OK) {
				// System.out.println(re);
				InputStream in = conn.getInputStream();
				byte[] b = new byte[10240];
			
				while (in.read(b) > 0){
					viewNum += new String(b);
				}
			} else {
				System.out.println("connet fail!！");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.disconnect();
				conn = null;
			}
		}
//		System.err.println("viewNum: " + viewNum);
		viewNum = viewNum.substring(viewNum.indexOf("pv:") + 3, viewNum.indexOf(",dynamic"));
		return viewNum;
	}
}
