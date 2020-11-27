package com.huaxia.opas.cache.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.cache.common.CacheConsts;
import com.huaxia.opas.cache.service.LueneService;
import com.huaxia.opas.cache.service.MatchHander;
import com.huaxia.opas.cache.util.IKAnalzyerUtil;
import com.huaxia.opas.util.StringUtil;

/**
 * 简单版本的全文检索和模糊比配实现类
 * 
 * @author Administrator
 *
 */
public class LuceneServiceImplRepos implements LueneService {
	// private static Logger logger =
	// LoggerFactory.getLogger(LueneServiceImpl.class);
	public static void main(String[] args) throws CoreException {
		LuceneServiceImplRepos lmp = new LuceneServiceImplRepos();
		// String str1 = "上海市浦东新区张江镇张江路423号";
		String str1 = "北京石景山区政达路A座811-1-3";
		String str2 = "88906751";// "上海市浦东新区张江镇张江路654号";
		String str3 = "北京市石景山区政达路A座801号楼1层3";
		String str4 = "88906750";
		String str5 = "长春一汽";
		String str6 = "长春第一汽车";
		String strtype = "北京市石景山区政达路";
		String str7 = "山东省临沂市郯城县泉源乡政府驻地郯城县泉源乡人民政府党政办";
		String str8 = "郯城县泉源乡驻地人民政府院内";
		String str9 = "0537-5118866";
		String str10 = "05375118866";
		String str11 = "楚雄技师学院";
		String str12 = "中央音乐学院";
		
		System.out.println(lmp.getMachResult(str5, str6, ""));

	}

	@Override
	public boolean getMachResult(String src, String dest, String strtype)
			throws CoreException {
		dest=StringUtil.trimToEmpty(dest);
		src=StringUtil.trimToEmpty(src);
		boolean matchresult = false;
		if ("1".equals(strtype)) {
			
		} else {
			matchresult = compare(src,dest);
		}
		return matchresult;
	}

	/**
	 * 正则表达式
	 * 
	 * @param src
	 * @return
	 */
	private static String regex_match(String src, String strType) {
		String strResult = "";
		// 抽取数字规则
		String reg1 = "[^(0-9)]";
		// 抽取中文字母规则
		String reg2 = "[^(a-zA-Z\\u4e00-\\u9fa5)]";
		if ("1".equals(strType)) {
			// 抽取地址中数字
			Pattern p1 = Pattern.compile(reg1);
			Matcher m1 = p1.matcher(src);
			strResult = m1.replaceAll("").trim();
			if (8 - strResult.length() > 0) {
				strResult = String.format(
						"%0" + (8 - strResult.length()) + "d", 0) + strResult;
			}
		} else {
			// 抽取地址中中文字符
			Pattern p = Pattern.compile(reg2);
			Matcher m = p.matcher(src);
			strResult = m.replaceAll("").trim();
		}
		return strResult;
	}
	
	private static boolean  compare(String src,String target){
		String s1 = regex_match(StringUtil.trimToEmpty(src),"");
		String s2 = regex_match(StringUtil.trimToEmpty(target),"");
		boolean ret = false;
		if("".equals(s1) || "".equals(s2)){
			return ret;
		}
		if(s1.length() > s2.length()){
			return ret;
		}
		char[] charArray1 = s1.toCharArray();
		char[] charArray2 = s2.toCharArray();
		
		for (int i = 0; i < charArray1.length; i++) {
			int flag = 0;
			for (int j = 0; j < charArray2.length; j++) {
				if(charArray1[i] == charArray2[j]){
					flag ++;
				}
			}
			if(flag == 0){
				return ret;
			}
		}
		
		return true;
	}

	@Override
	public boolean getMachResultTel(String strs1, String strs2, String strtype) throws CoreException {
		// TODO Auto-generated method stub
		return false;
	}
	
}
