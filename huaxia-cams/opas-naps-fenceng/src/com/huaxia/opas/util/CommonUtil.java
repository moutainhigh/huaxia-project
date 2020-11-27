package com.huaxia.opas.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author xuzhiguo
 */
public class CommonUtil {
	public static void main(String[] args) {
		sortStr("A8,B3,A2,A10,A9,E8,B1,D9,E1,C3,D4",",");
	}
	/**
	 * 对字母与数字进行排序,A1,A2,A3,。。。,A10;B1,B2,B3,。。。,B10;
	 */
	public static String sortStr(String str,String splitChar){
		TreeSet<String> set = new TreeSet<String>();
		if(str!=null && !"".equals(str)){
			str = str.replaceAll("10", "99");
			if(splitChar.equals(str.substring(str.length()-1))){
				str = str.substring(0,str.length()-1);
			}
			String array[] = str.split("\\"+splitChar);
			for (int i = 0; i < array.length; i++) {
				set.add(array[i]);
			}
		}
		String res = "";
		for(String s : set){
			res+=s+",";
		}
		if(!"".equals(res)){
			res = res.substring(0, res.length()-1);
		}
		res = res.replaceAll("99", "10");
		//System.out.println(res);
		return res;
	}
	
	
	/**
	 * 根据当前时间获取其年龄
	 */
	public static int getAge(Date birthDate){
		int age = 0;
		Calendar birth = Calendar.getInstance();
		birth.setTime(birthDate);
		Calendar sysdate = Calendar.getInstance();
		int birth_year = birth.get(Calendar.YEAR);
		int birth_m = birth.get(Calendar.MONTH) + 1;
		int birth_d = birth.get(Calendar.DATE);
		int sys_year = sysdate.get(Calendar.YEAR);
		int sys_m = sysdate.get(Calendar.MONTH) + 1;
		int sys_d = sysdate.get(Calendar.DATE);
		if(birth_year > sys_year){
			return -1;
		}
		age = sys_year - birth_year; 
		if(birth_m > sys_m){
			--age;
		}else if(birth_m == sys_m){
			if(birth_d > sys_d){
				--age;
			}
		}
		return age;
	}
	/**
	 * 获得一个非空结果
	 * isNumber:标志，若为true,则表示要求结果为数字，否则为字符串
	 * @author xuzhiguo
	 */
	public static String getNoNullResult(String obj,boolean isNumber){
		if(isNumber){
			if(obj==null){
				return "0";
			}else if("".equals(obj)){
				return "0";
			}else{
				return obj;
			}
		}else{
			if(obj==null){
				return "";
			}else if("".equals(obj)){
				return "";
			}else{
				return obj;
			}
		}
	}
	/**
	 * 获取min-max的N个不重复的随机数
	 * xuzhiguo
	 */
	public static Set<Integer> getNRandom(int max,int min,int n,Set<Integer> set){
		if( n>(max-min+1) || max<min ){
			return null;
		}
		for (int i = 0; i < n; i++) {
			int num = (int)(Math.random()*(max-min))+min;
			set.add(num);
			if(set.size()==n){
				break;
			}
		}
		if(set.size()<n){
			getNRandom(max,min,n,set);
		}
		return set;
	}
	/**
	 * 判断多个字符串是否为空，返回第一个不为空的字符串
	 * @author xuzhiguo
	 */
	public static String geNotNullString(String... strings){
		String res = null;
		for (String str : strings) {
			if(str != null && !"".equals(str))
				return str;
		}
		return res;
	}
	/**
	 * 判断参数para是否存在于字符数组中，若存在则返回ture
	 * @author xuzhiguo
	 */
	public static boolean getContains(String param,String... strings){
		for (String string : strings) {
			if(string.equals(param)){
				return true;
			}
		}
		return false;
	}
	/**
	 * 判断两个集合中相同的键key的值是否相同，并返回他们【不相同】的键集合
	 * @param map
	 * @param mubiaoMap
	 * @author xuzhiguo
	 */
	public static List<String> mapCompareToMap(Map<String,Object> map,Map<String,Object> mubiaoMap){
		List<String> list = new ArrayList<String>();
		if(map==null){
			return null;
		}else if(mubiaoMap==null){
			list.addAll(map.keySet());
		}else{
			for (String str : map.keySet()) {
				if(map.get(str)!=mubiaoMap.get(str)&&map.get(str)!=null&&!"".equals(map.get(str)))
					list.add(str);
			}
		}
		return list;
	}
	/**
	 * 判断两个集合中相同的键key的值是否相同，并返回他们【不相同】的键集合
	 * @param map
	 * @param mubiaoMap
	 * @author xuzhiguo
	 */
	public static List<String> mapCompareToMapStr(Map<String,String> map,Map<String,String> mubiaoMap){
		List<String> list = new ArrayList<String>();
		if(map==null){
			return null;
		}else if(mubiaoMap==null){
			list.addAll(map.keySet());
		}else{
			for (String str : map.keySet()) {
				if(map.get(str)!=mubiaoMap.get(str)&&map.get(str)!=null&&!"".equals(map.get(str)))
					list.add(str);
			}
		}
		return list;
	}
	public static String getAnothorCardAppId(String appId,String tkFlag){
		char lastChar = appId.charAt(appId.length()-1);
		if(!"0".equals(tkFlag)){
			if("2".equals(lastChar+"")){
				return appId.substring(0, appId.length()-1)+"1";
			}else if("1".equals(lastChar+"")){
				return appId.substring(0, appId.length()-1)+"2";
			}
		}
		return null;
	}
}
