package cn.edu.xmu.dm.lexical;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class bkRegx {
	public static void main(String[] args) {
		Pattern pattern = Pattern.compile("[0-9]");
		Matcher matcher = pattern.matcher("正则表达125式 Hello World,正则表达式 Hello World");
		//替换第一个符合正则的数据
		System.out.println(matcher.replaceAll(""));
	}
}
