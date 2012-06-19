package cn.edu.xmu.dm.bdbk.test;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import cn.edu.xmu.dm.bdbk.utils.HtmlRegexpUtil;
import cn.edu.xmu.dm.bdbk.utils.URLConnector;

public class TestHtmlRegexUtil extends TestCase {
	@Test
	public void testFilterHtml(){
		HtmlRegexpUtil hr = new HtmlRegexpUtil();
		String str = "<div class=\"card-summary-content\"><p>薄熙来（1949.07-）男，汉族。山西定襄人。1980年10月加入<a target=_blank href=/view/1893.htm>中国共产党</a>，1968年1月参加工作。<a target=_blank href=/view/1609682.htm>中国社会科学院研究生院</a>国际新闻专业毕业，研究生学历，文学硕士。曾任大连市委书记，大连市市长，辽宁省委常委，辽宁省委副书记，辽宁省省长，商务部部长，中央政治局委员，重庆市委书记等职。2012年3月15日，中共中央决定薄熙来同志不再兼任重庆市委书记、常委、委员职务。2012年4月10日，因涉嫌严重违纪，中央决定，停止其担任的中央政治局委员、中央委员职务，由中共中央纪律检查委员会对其立案调查。</p></div>";
		String result = hr.filterHtml(str);
		System.out.println(result);
	}
	
	
	@Test
	public void testURLConnector(){
		URLConnector uc = new URLConnector();
		String sUrl = "http://like.baidu.com/get?key=100037&cb=getVoteData&type=0&like=1&pid=3&random=0";
		uc.getVoteNum(sUrl);
	}
}
