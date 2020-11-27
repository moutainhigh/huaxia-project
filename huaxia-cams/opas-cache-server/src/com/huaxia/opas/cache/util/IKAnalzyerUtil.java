package com.huaxia.opas.cache.util;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;
import org.apache.lucene.analysis.tokenattributes.TypeAttribute;
import org.wltea.analyzer.lucene.IKAnalyzer;


/**
 * @author:
 * @versin:2017年2月27日下午1:45:18
 */
public class IKAnalzyerUtil {
	private static IKAnalyzer analyzer = new IKAnalyzer(true);
	public static void main(String[] args) throws IOException {
/*		String str1 = "上海市宝山区大场镇场南村417号";
		String str2 = "大场镇（场南村）417号、宝山区、上海市";
		String str3 = "宝山区大场镇场南村544号上海市";*/
		
		
		String str1 = "北京市石景山区政达路80-1-2";
		
		String str2 = "上海市浦东新区张江镇张江路654号";
		
		String str3 = "北京市石景山区政达路80号1层";

		String a1 = "我是工程师";
		String a2 = "我是攻城狮";

		long start = System.currentTimeMillis();
		String str10 = "80-1";
		String str11 = "80号1层";

		//System.out.println(getSimilarityValue(str1, str2));
		//System.out.println(getSimilarityValue(str1, str3));

//		System.out.println("cost=" + (System.currentTimeMillis() - start));
		//String str10 = "80-1";
		//List<String> lst1= doSplitWordTest(str1);
		doSplitWordTest1(str10);
		//doSplitWordTest(str11);
	}

	public static List<String> doSplitWordTest(String str) throws IOException {
		Analyzer analyzer = null;
        List<String> lst=null;
 		TokenStream ts = null;
		try {
			//analyzer = new IKAnalyzer(true);

			ts = analyzer.tokenStream(null, new StringReader(str));

			OffsetAttribute offset = (OffsetAttribute) ts.addAttribute(OffsetAttribute.class);

			CharTermAttribute term = (CharTermAttribute) ts.addAttribute(CharTermAttribute.class);

			TypeAttribute type = (TypeAttribute) ts.addAttribute(TypeAttribute.class);

			ts.reset();
			int i=0;
			while (ts.incrementToken()) {
				i++;
				lst.add(i, term.toString());
//				System.out.println(offset.startOffset() + " - " + offset.endOffset() + " : " + term.toString() + " | "
//						+ type.type());
			}

			ts.end();
		} finally {
			if (ts != null)
				try {
					ts.close();
				} catch (IOException localIOException) {
				}
			if (analyzer != null)
				try {
					analyzer.close();
				} catch (Exception localException) {
				}
		}
		return lst;
	}

	
	
	public static void doSplitWordTest1(String str) throws IOException {
		Analyzer analyzer = null;
        List<String> lst=null;
 		TokenStream ts = null;
		try {
			analyzer = new IKAnalyzer(true);


			ts = analyzer.tokenStream(null, new StringReader(str));
			//
			OffsetAttribute offset = (OffsetAttribute) ts.addAttribute(OffsetAttribute.class);
			//KeywordAttribute term = (KeywordAttribute) ts.addAttribute(KeywordAttribute.class);
			CharTermAttribute term = (CharTermAttribute) ts.addAttribute(CharTermAttribute.class);
			TypeAttribute type = (TypeAttribute) ts.addAttribute(TypeAttribute.class);
			ts.reset();
			//int i=0;
			while (ts.incrementToken()) {
				//i++;
				//lst.add(i, term.toString());
//				System.out.println(offset.startOffset() + " - " + offset.endOffset() + " : " + term.toString() + " | "
//						+ type.type());
			}

			ts.end();
		} finally {
			if (ts != null)
				try {
					ts.close();
				} catch (IOException localIOException) {
				}
			if (analyzer != null)
				try {
					analyzer.close();
				} catch (Exception localException) {
				}
		}
	}
	public static float getSimilarityValue(String str1, String str2) throws IOException {
		if (StringUtils.equals(str1, str2)) {
			return 100.0F;
		}
		String sortedStr1 = getSortedString(str1);
//		System.out.println(sortedStr1+"::"+sortedStr1.length());
		String sortedStr2 = getSortedString(str2);
//		System.out.println(sortedStr2+"::"+sortedStr2.length());
		//System.out.println(getSimScoreList());
		
		int distence = StringUtils.getLevenshteinDistance(sortedStr1, sortedStr2);
//		System.out.println(distence);
		float returnF = 100.0F - (distence * 100.0F / Math.max(sortedStr1.length(), sortedStr2.length()));
//		System.out.println("%||" + returnF);
		return returnF;
	}

	private static String getSortedString(String str) throws IOException {
		if (str == null) {
			return null;
		}

		List<String> list1 = new ArrayList();

		Analyzer analyzer = null;
		TokenStream ts = null;
		try {
			analyzer = new IKAnalyzer(true);

			ts = analyzer.tokenStream(null, new StringReader(str));			
			CharTermAttribute term = (CharTermAttribute) ts.addAttribute(CharTermAttribute.class);
			ts.reset();

			while (ts.incrementToken()) {
				list1.add(term.toString());
			}

			ts.end();
		} finally {
			if (ts != null)
				try {
					ts.close();
				} catch (IOException localIOException) {
				}
			if (analyzer != null) {
				try {
					analyzer.close();
				} catch (Exception localException) {
				}
			}
		}
		Collections.sort(list1);

		StringBuilder sb = new StringBuilder(128);
		for (String str1:list1) {
//			System.out.println("遍历："+str1);
			sb.append(str1);
		}

		return sb.toString();
	}
	
	
	public static List<String> getSortedString_(String str) throws IOException {
		if (str == null) {
			return null;
		}

		List<String> list1 = new ArrayList();

		//Analyzer analyzer = null;
		TokenStream ts = null;
		try {
			//analyzer = new IKAnalyzer(true);

			ts = analyzer.tokenStream(null, new StringReader(str));
			CharTermAttribute term = (CharTermAttribute) ts.addAttribute(CharTermAttribute.class);
			ts.reset();

			while (ts.incrementToken()) {
				list1.add(term.toString());
			}

			ts.end();
		} finally {
			if (ts != null)
				try {
					ts.close();
				} catch (IOException localIOException) {
				}
			if (analyzer != null) {
				try {
					//analyzer.close();
				} catch (Exception localException) {
				}
			}
		}
		Collections.sort(list1);

		/*StringBuilder sb = new StringBuilder(128);
		for (String str1:list1) {
			System.out.println("遍历："+str1);
			sb.append(str1);
		}
*/
		return list1;
	}

	
}
