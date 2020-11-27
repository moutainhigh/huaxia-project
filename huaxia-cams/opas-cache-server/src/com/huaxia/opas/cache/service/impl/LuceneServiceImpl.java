package com.huaxia.opas.cache.service.impl;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
public class LuceneServiceImpl implements LueneService {
	// private static Logger logger =
	// LoggerFactory.getLogger(LueneServiceImpl.class);
	public static void main(String[] args) throws CoreException {
		LuceneServiceImpl lmp = new LuceneServiceImpl();
		// String str1 = "上海市浦东新区张江镇张江路423号";
		String str1 = "石景山区政达路6号院6号楼华夏信用卡中心6层";
		String str2 = "石景山区政达路6号院6号楼华夏银行6层";// "上海市浦东新区张江镇张江路654号";
		String str3 = "北京市石景山区政达路A座801号楼1层3";
		String str4 = "88906750";
		String str5 = "北京石景山区古城南路47号2单元301";
		String str6 = "03";
		String strtype = "北京市  石景山   区政    达路";
		String str7 = " ";
		String str8 = "北京医科大学";
		String str9 = "17600737965";
		String str10 = "017600737965";
		String str11 = "楚雄技师学院";
		String str12 = "楚雄技师?学校";
		
		String reg = "^[(`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）【】’；：“”‘。，、？)]+$";
		
		// String
		// regEx="[`~!@#$%^&*()+=|{}':;',[-]\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";

		//System.out.println(lmp.getMachResult(str5, str6, ""));
		//System.out.println(lmp.machResult(str5, str6, ""));
		// System.out.println(regex_match(str9,""));
		// long start = System.currentTimeMillis();
		/*
		 * long t1 = System.currentTimeMillis();
		 * System.out.println("COST="+(t1-start)+" ms");
		 */
		// System.out.println("getSimScore="+lueneService.getSimScore(str1,
		// str3));
		//System.out.println("".equals(str7.replaceAll(" +", "")));
		//System.out.println(str12.replaceAll(reg, ""));
		//System.out.println(Pattern.compile(reg).matcher(str12).replaceAll(""));
		/*
		 * long t2 = System.currentTimeMillis();
		 * System.out.println("COST="+(t2-t1)+" ms");
		 */
	}

	@Override
	public boolean getMachResult(String src, String dest, String strtype)
			throws CoreException {
		boolean matchresult = false;
		if(null!=src&&null!=dest){
			dest=StringUtil.deleteWhiteSpace(dest);
			src=StringUtil.deleteWhiteSpace(src);
			if(!"".equals(src)&&!"".equals(dest)) {
				if ("1".equals(strtype)) {
					String str1 = regex_match(toChange(src), "1");
					String str2 = regex_match(toChange(dest), "1");
					if (machResult(src, dest, "1")) {
						matchresult = true;
					}
				} else {
					Map<String, Object> machResultAdd = machResultAdd(src, dest, "");
					int errFlag = (int)machResultAdd.get("errFlag");
					int size = (int)machResultAdd.get("size");
					//System.out.println("err:"+errFlag + "********size:" + size+"*****"+((float)errFlag/size));
					if((float)errFlag/size>=CacheConsts.accuracy && machResult(src, dest, "1")){
						matchresult = true;
					}
				}	
			}
		}
		return matchresult;
		
	}

	// 抽取字符串中的中文和数字进行比对
	private boolean machResult(String src, String dest, String strtype) {
		boolean result = false;
		try {
			// 抽取中文字符串匹配规则
			List<String> lst1 = IKAnalzyerUtil.getSortedString_(regex_match(
					toChange(src), strtype));
			List<String> lst2 = IKAnalzyerUtil.getSortedString_(regex_match(
					toChange(dest), strtype));
			//System.out.println(lst1);
			//System.out.println(lst2);
			List<Double> dob = getSimScoreList(lst1, lst2);
			List<String> str_src = diffresult(lst2, lst1);
			//System.out.println(str_src);
			//System.out.println(dob);
			int errFlag = 0;
			for (int i = 0; i < dob.size(); i++) {
				result = true;
				if (dob.get(i) < 100) {
					result = false;
					if ("1".equals(strtype)) {
						break;
					}
//					System.out.println(lst1.get(i));
					if (match_signal(lst1.get(i), str_src)) {
						dob.set(i, 100.00);
						result = true;
					} else {
						errFlag++;
						//break;
					}
				}
			}	
		} catch (IOException e) {
			e.getMessage();
		}
		return result;
	}
	
		//20170614
		// 抽取字符串中的中文和数字进行比对
		private Map<String,Object> machResultAdd(String src, String dest, String strtype) {
			Map<String, Object> retMap = new HashMap<>();
			try {
				// 抽取中文字符串匹配规则
				List<String> lst1 = IKAnalzyerUtil.getSortedString_(regex_match(
						toChange(src), strtype));
				List<String> lst2 = IKAnalzyerUtil.getSortedString_(regex_match(
						toChange(dest), strtype));
				//System.out.println(lst1);
				//System.out.println(lst2);
				List<Double> dob = getSimScoreList_(lst1, lst2);
				retMap.put("size", dob.size());
				int errFlag = 0;
				if (lst1 != null && lst2 != null) {
					List<String> str1= lst1.size()>lst2.size()?lst1:lst2;
					List<String> str2= lst1.size()>lst2.size()?lst2:lst1;
					if(str2.size()*2<=str1.size()){
						retMap.put("errFlag", errFlag);
						return retMap;
					}
				}
				List<String> str_src = diffresult(lst2, lst1);
				//System.out.println(str_src);
				//System.out.println(dob);
				for (int i = 0; i < dob.size(); i++) {
					if (dob.get(i) < 100) {
						if ("1".equals(strtype)) {
							break;
						}
//						System.out.println(lst1.get(i));
						if (match_signal(lst1.get(i), str_src)) {
							dob.set(i, 100.00);
							errFlag++;
						} 
					}else{
						errFlag++;
					}
				}
				retMap.put("errFlag", errFlag);
			} catch (IOException e) {
				e.getMessage();
			}
			return retMap;
		}
	// 找出两个字符串中各自不同的分词
	private static List<String> diffresult(List<String> lst1, List<String> lst2) {
		List<String> str_ = new ArrayList<String>();
		List<Double> dob = getSimScoreList_(lst1, lst2);
		for (int i = 0; i < dob.size(); i++) {
			// result = true;
			if (dob.get(i) < 100) {
				str_.add(lst1.get(i));
			}
		}
		return str_;

	}

		// 两个数字中率为100%设置
		private static List<Double> getSimScoreList(List<String> strs1,
				List<String> strs2) {
			List<Double> returnList = new ArrayList<Double>();
			if (strs1 != null && strs2 != null) {
				List<String> str1= strs1.size()>strs2.size()?strs2:strs1;
				List<String> str2= strs1.size()>strs2.size()?strs1:strs2;
				Double str_rate = 0.0;
				MatchHander hander = new MatchHander() {
					
					@Override
					public boolean compare(int a, int b) {
						return a == b;
					}
				};
				out:for (String str : str1) {
					str_rate = 0.0;
					for (String str_ : str2) {
						if (str.contains(str_) || str_.contains(str)) {
							str_rate = 100.00;
						} else {
							continue;
						}
					}
					// 迭代匹配
					returnList.add(str_rate);
					// returnList.add(getSimScore(str1, str2));
				}
			}
			return returnList;
		}
	
	// 两个字符串分词后命中率为100%设置
	private static List<Double> getSimScoreList_(List<String> strs1,
			List<String> strs2) {
		List<Double> returnList = new ArrayList<Double>();
		if (strs1 != null && strs2 != null) {
			List<String> str1= strs1.size()>strs2.size()?strs2:strs1;
			List<String> str2= strs1.size()>strs2.size()?strs1:strs2;
			Double str_rate = 0.0;
			MatchHander hander = new MatchHander() {
				
				@Override
				public boolean compare(int a, int b) {
					return a == b;
				}
			};
			out:for (String str : str1) {
				str_rate = 0.0;
				for (String str_ : str2) {
					if(match(0.75, str, str_, hander) && match(0.75, str_, str, hander)){
						str_rate = 100.00;
						returnList.add(str_rate);
						continue out;
					}else{
						continue;
					}
					/*if (str.contains(str_) || str_.contains(str)) {
						str_rate = 100.00;
					} else {
						continue;
					}*/
				}
				// 迭代匹配
				returnList.add(str_rate);
				// returnList.add(getSimScore(str1, str2));
			}
		}
		return returnList;
	}

	// 两个字符匹配规则
	private static boolean match(String str1, String str2) {
		boolean match = false;
		int i = 0;
		int times = 0;
		int maxlen = 0;
		int minlen = 0;
		String maxshare = "";
		String minshare = "";
		// 获取两个字符的长度
		if (str2.length() >= str1.length()) {
			maxlen = str2.length();
			minlen = str1.length();
			maxshare = str2;
			minshare = str1;
		} else {
			maxlen = str1.length();
			minlen = str2.length();
			maxshare = str1;
			minshare = str2;
		}

		// 短字符落在长字符中的次数
		for (int l = 0; l < maxlen; l++) {
			if (minshare.charAt(i) == maxshare.charAt(l)) {
				times++;
				if (minlen - 1 > i) {
					i++;
				} else {
					break;
				}
			} else {
				if (times > 0) {
					break;
				}
			}
//			System.out.println("test1:" + times);

		}

		// 命中的次数
		if (times != 0 && times >= minlen - 1) {
//			System.out.println("test2:" + times);
			match = true;
		}
		return match;
	}

	// 两个字符串匹配规则
	private static boolean match_signal(String str1, List<String> str2) {
		boolean match_ = false;
		// System.out.println(IKAnalzyerUtil.doSplitWordTest1(str1));
		String regEx = "[`~!@#$%^&*()+=|{}':;',[-]\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
		String reg1 = "s558zxv85w5581!@2";
		Pattern p = Pattern.compile(reg1);
		Matcher m = p.matcher(str1);
//		System.out.println(m.replaceAll("").trim());
		MatchHander hander = new MatchHander() {
			
			@Override
			public boolean compare(int a, int b) {
				return a == b;
			}
		};
		for (String str : str2) {
			match_ = false;
		/*	if (match(str1, str)) {
//				System.out.println(str1 + "||" + str);
				match_ = true;
				// break;
			}*/
			if(match(0.66, str1, str, hander)){
				System.out.println(str1 + "||" + str);
				match_ = true;
			}
	
		}
		return match_;
	}

	// 全角轉化半角規則
	private static String toChange(String str1) {
		char c[] = str1.toCharArray();
		for (int i = 0; i < c.length; i++) {
			if (c[i] == '\u3000') {
				c[i] = ' ';
			} else if (c[i] > '\uFF00' && c[i] < '\uFF5f') {
				c[i] = (char) (c[i] - 65248);
			}
		}
		return new String(c);
	}

	/**
	 * 百分之多少之内匹配错误可以接受 a与ab匹配为百分之50的错误率。
	 * 
	 * @param percent
	 *            设置匹配百分比
	 * @param src
	 *            字符串1
	 * @param dest
	 *            字符串2
	 * @param hander
	 *            匹配规则
	 * @return
	 */
	private static boolean match(double percent, String src, String dest,
			MatchHander hander) {
		char[] csrc = src.toCharArray();
		char[] cdest = dest.toCharArray();
		double score = 0;
		int max = csrc.length > cdest.length ? csrc.length : cdest.length;
		score = cal(csrc, 0, cdest, 0, hander, 0,
				(int) Math.ceil((1 - percent) * max));
		//System.out.println("最小匹配百分比：" + percent + "，成功匹配百分比：" + score / max);
		return score / max > percent;
	}

	/**
	 * 2个字符串75%匹配成功返回true
	 * 
	 * @param src
	 * @param dest
	 * @return
	 */
	private static boolean match(double percent, String src, String dest) {
		return match(percent, src, dest, new MatchHander() {

			@Override
			public boolean compare(int a, int b) {
				return a == b;
			}
		});
	}

	/**
	 * 使用递归方法匹配字符串
	 * 
	 * @param csrc
	 * @param i
	 * @param cdest
	 * @param j
	 * @param hander
	 * @return
	 */
	private static int cal(char[] csrc, int i, char[] cdest, int j,
			MatchHander hander, int curdeep, int maxdeep) {
		int score = 0;
		if (curdeep > maxdeep || i >= csrc.length || j >= cdest.length)
			return 0;
		boolean ismatch = hander.compare(csrc[i], cdest[j]);
		if (ismatch) {
			score++;
			if (i + 1 < csrc.length && j + 1 < cdest.length)
				score += cal(csrc, i + 1, cdest, j + 1, hander, 0, maxdeep);
		} else {
			int temp1 = 0;
			int temp2 = 0;
			int temp3 = 0;
			temp1 += cal(csrc, i, cdest, j + 1, hander, curdeep + 1, maxdeep);
			temp2 += cal(csrc, i + 1, cdest, j, hander, curdeep + 1, maxdeep);
			temp3 += cal(csrc, i + 1, cdest, j + 1, hander, curdeep + 1,
					maxdeep);
			int temp4 = Math.max(temp1, temp2);
			score += Math.max(temp3, temp4);
		}
		return score;
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
			// System.out.println(String.format("%0"+(8-strResult.length())+"d",0)+strResult);
			if (8 - strResult.length() > 0) {
				strResult = String.format(
						"%0" + (8 - strResult.length()) + "d", 9) + strResult;
			}
		} else {
			// 抽取地址中中文字符
			Pattern p = Pattern.compile(reg2);
			Matcher m = p.matcher(src);
			strResult = m.replaceAll("").trim();
//			System.out.println(m.replaceAll("").trim());
		}
		return strResult;
	}

	@Override
	public boolean getMachResultTel(String strs1, String strs2, String strtype) throws CoreException {
		
		return machResult(strs1,strs2,"");
	}
}
