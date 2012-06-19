import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class Test {
	public static void main(String[] args) {
		String sUrl = "http://like.baidu.com/get?key=100037&cb=getVoteData&type=0&like=1&pid=3&random=0"; 
	    HttpURLConnection conn =null; 
	    try{ 
	       conn =(HttpURLConnection)new URL(sUrl).openConnection(); 
	      int result= conn.getResponseCode(); 
	      String re=conn.getResponseMessage();	  
	      if(result==HttpURLConnection.HTTP_OK){ 
//	        System.out.println(re); 
	        InputStream in=conn.getInputStream(); 
	        byte[] b=new byte[1024]; 
	        while(in.read(b)>0) 
	        System.out.write(b); 
	      }else{ 
	         System.out.println("connet fail!！"); 
	      } 
	    }catch(Exception e ){ 
	       e.printStackTrace(); 
	    }finally{ 
	      if(conn!=null){ 
	         conn.disconnect(); 
	         conn = null; 
	      } 
	    }
	}
}
