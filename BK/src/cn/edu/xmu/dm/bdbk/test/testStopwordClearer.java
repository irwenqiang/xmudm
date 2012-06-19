package cn.edu.xmu.dm.bdbk.test;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import cn.edu.xmu.dm.lexical.StopwordClearer;

public class testStopwordClearer {
	private StopwordClearer sc;
	
	@Before
	public void setUp() throws Exception {
		sc = new StopwordClearer();
	}

	@Test
	public void test() {
		Set<String> stopwords = new HashSet<String>();
		stopwords = sc.readFile("stopwords.txt");
		assert(stopwords.size() == 882);
		
//		Iterator it = stopwords.iterator();
//		while (it.hasNext()) {
//			System.out.println(it.next());
//		}
	}
	
	@Test
	public void testRemoveStopword(){
		String filePath =   "E:\\chenwq\\bdbkFinal_item_content_mm";
		String outPutPath = "E:\\chenwq\\bdbkFinal_item_content_mm_remove_stopwords";
		File folder = new File(filePath);
		File[] files = folder.listFiles();
		sc.readFile("stopwords.txt");
		sc.removeStopword(files, outPutPath);
	}
	
	@Test
	public void testDistinctWords(){
		String filePath =   "E:\\chenwq\\test";
		File folder = new File(filePath);
		File[] files = folder.listFiles();
		System.out.println(files.length);
		System.out.println(sc.calcDistinctWords(files));
	}
}
