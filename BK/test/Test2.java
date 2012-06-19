import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Test2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		   // 只允许字母和数字       
		               // String   regEx  =  "[^a-zA-Z0-9]";                     
	                   // 清除掉所有特殊字符  
		String str = "<div class=\"card-summary-content\"><p>薄$$熙来（1949.07-）男，汉族。山var西定襄人。1980年10月加入<a target=_blank href=/view/1893.htm>中国共产党</a>，1968年1月参加工作。<a target=_blank href=/view/1609682.htm>中国社会科学院研究生院</a>国际新闻专业毕业，研究生学历，文学硕士。曾任大连市委书记，大连市市长，辽宁省委常委，辽宁省委副书记，辽宁省省长，商务部部长，中央政治局委员，重庆市委书记等职。2012年3月15日，中共中央决定薄熙来同志不再兼任重庆市委书记、常委、委员职务。2012年4月10日，因涉嫌严重违纪，中央决定，停止其担任的中央政治局委员、中央委员职务，由中共中央纪律检查委员会对其立案调查。</p></div>";
		       
		 Pattern p3 = Pattern.compile("<([^>]*)>");
	       Matcher m3 = p3.matcher(str);
	       System.out.println(m3.replaceAll("").trim());
	       
		String regEx="[`~!@#$%^&*()+=|{}':;',\\[\\]<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";  
		          Pattern   p   =   Pattern.compile(regEx);     
		       Matcher   m   =   p.matcher(m3.replaceAll("").trim());  
		       System.out.println(m.replaceAll("").trim());
		       
		       Pattern p2 = Pattern.compile("[a-zA-Z]");
		       Matcher m2 = p2.matcher(m.replaceAll("").trim());
		       
		      
		       
		       
		       System.out.println(m2.replaceAll("").trim());
		       

	}

}
