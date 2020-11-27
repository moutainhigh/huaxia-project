package com.huaxia.opas.common.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ShareUtil {
	
	/**
	 * 根据字段名拿到随机的值
	 * 
	 * */
	public static String randomChance(String value){
		String[] eums = value.split(";");
		List<String> list = new ArrayList<String>();
		
		for (String eum : eums) {
			if (eum==null || "".equals(eum)) {
				continue;
			}
			String[] chr = eum.split(",");
			String attr = chr[0];
			int chance = Integer.parseInt(chr[2].replace("%", "").trim());
			for (int i = 0; i < chance*10; i++) {
				list.add(attr);
			}
		}
		int index = random(0, list.size()-1);
		return list.get(index);
	}
	
	/**
	 * 根据字段名拿到随机的值
	 * 
	 * */
	public static String randomChance1(String value){
		String[] eums = value.split(";");
		List<String> list = new ArrayList<String>();
		
		for (String eum : eums) {
			if (eum==null || "".equals(eum)) {
				continue;
			}
			String[] chr = eum.split(",");
			String attr = chr[0];
			System.out.println(chr[1].replace("%", "").trim());
			int chance = Integer.parseInt(chr[1].replace("%", "").trim());
			System.out.println(chance);
			for (int i = 0; i < chance*10; i++) {
				list.add(attr);
				System.out.println(attr);
			}
		}
		int index = random(0, list.size()-1);
		return list.get(index);
	}
	
	public static int random(int min,int max){
		Random r = new Random();
		int nextInt = r.nextInt(max+1-min)+min;
		return nextInt;
	}
}
