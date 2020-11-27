/**
 * 
 */
package com.huaxia.opas.util;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * 把Java对象转化成Map的类
 * @author liuwei
 *
 */
public class MapTools {

	/**
	 * 
	 */
	public MapTools() {
		// TODO Auto-generated constructor stub
	}

	/** 测试Map
	 * @param args
	 */
	public static void main(String[] args) {
		/*// TODO Auto-generated method stub
		LowRiskCustomers lowRiskCustomers = new LowRiskCustomers();
		lowRiskCustomers.setUuid("aslkdfalsdf");
		lowRiskCustomers.setCrtUser("liuwei");
		lowRiskCustomers.setCrtTime(new Timestamp(new Date().getTime()));
		lowRiskCustomers.setCusName("华夏银行");
		lowRiskCustomers.setCredNo("41022199505293452");
		lowRiskCustomers.setMobile("18612995529");
		lowRiskCustomers.setCompanyName("华夏银行");
		lowRiskCustomers.setListType("1");
		lowRiskCustomers.setSTATUS("0");
		Map<String, Object> map;
		try {
			map = MapTools.objectToMap(lowRiskCustomers);
			System.out.println(map);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	
//		try {
//			LowRiskCustomers lowRiskCustomers = null;
//			MapTools.objectToMap(lowRiskCustomers);
//		} catch (IllegalAccessException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	/**
	 * 把Java对象转化成Map的类
	 * @param obj
	 * @return
	 * @throws IllegalAccessException
	 */
	public static Map<String,Object> objectToMap(Object obj) throws IllegalAccessException{
		Map<String,Object> map = new HashMap<String,Object>();
		Class<?> clazz = obj.getClass();
		for(Field field:clazz.getDeclaredFields()){
			field.setAccessible(true);
			String fieldname = field.getName();
			Object value = field.get(obj);
			map.put(fieldname, value);
		}
		return map;
	}
}
