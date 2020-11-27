package com.huaxia.opas.util;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;

//import com.spdbccc.service.iface.dao.bizinpappins.BizInpAppIns;


/**
 * 关键字修改工具类，主要是提供：
 * 1）运营策略的“导航栏”
 * @author uatym990190
 *
 */
public class KeyWordUtil {
	
//	public static void main(String[] args) throws NoSuchFieldException {
//		
//		BizInpAppIns oldObj = new BizInpAppIns();
//		
//		oldObj.setHomeAddr1("11");
//		oldObj.setAppCertNo("AAAAb");
//		
//		BizInpAppIns newObj = new BizInpAppIns();
//		newObj.setHomeAddr2("2222");
//		newObj.setHomeAddr1("11");
//		
//		newObj.setAppCertNo("AAAA");
//		
//		System.out.println(getKeyWordModifyNavigate(oldObj, newObj));
//		
//		System.out.println(getKeyWordModifyNavigate(new Object[]{oldObj}, new Object[]{newObj}));
//		
//	}
	
	
	private static Properties KEYWORD_MODIFY_NAVIGATE_PROPERTIES = null;
	
	static {
		// 加载配置文件
		Properties prop = new Properties();
		InputStream in = null;
		try {
			in = KeyWordUtil.class.getResourceAsStream("/config/properties/KeyWordModifyNavigate.properties");
			
			prop.load(in);
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			if (in!=null) {
				try {
					in.close();
				} catch (IOException e) {
				}
			}
		}
		KEYWORD_MODIFY_NAVIGATE_PROPERTIES = prop;
	}
	
	/**
	 * 获得关键字修改的导航栏
	 * @param oldObj 数据库中老对象
	 * @param newObj 更新过数据的新对象
	 * @return
	 * @throws NoSuchFieldException 
	 */
	public static String getKeyWordModifyNavigate(Object oldObj, Object newObj) throws NoSuchFieldException {
		String returnValue = null;
		
		if (oldObj!=null && newObj!=null) {
			Set<String> set = getKeyWordModifyNavigateSet(oldObj, newObj);
			
			returnValue = set2String(set);
		}
		
		return returnValue;
	}
	
	/**
	 * 获得关键字修改的导航栏
	 * 注意同一个对象在新老数组中的下标需要对应的
	 * @param oldObjs 数据库中老对象数组
	 * @param newObjs 更新过数据的新对象数组
	 * @return
	 * @throws NoSuchFieldException 
	 */
	public static String getKeyWordModifyNavigate(Object[] oldObjs, Object[] newObjs) throws NoSuchFieldException {
		String returnValue = null;
		
		if (oldObjs!=null && newObjs!=null) {
			
			Set<String> set = new HashSet<String>();
			
			for (int i = 0; i < oldObjs.length; i++) {
				set.addAll(getKeyWordModifyNavigateSet(oldObjs[i], newObjs[i]));
			}
			
			returnValue = set2String(set);
		}
		
		return returnValue;
	}
	
	/**
	 * Set转字符串
	 * @param set
	 * @return
	 */
	private static String set2String (Set<String> set) {
		StringBuilder sb = new StringBuilder();
		
		Iterator<String> it = set.iterator();
		
		while(it.hasNext()) {
			sb.append(it.next()).append(",");
		}
		
		if (sb.length()>0) {
			sb.deleteCharAt(sb.length()-1);
		}
		
		return sb.toString();
	}
	
	private static Set<String> getKeyWordModifyNavigateSet(Object oldObj, Object newObj) throws NoSuchFieldException {
		Set<String> set = new HashSet<String>();
		
		if (KEYWORD_MODIFY_NAVIGATE_PROPERTIES==null) {
			return set;
		}
		
		if (oldObj==null && newObj==null) {
			return set;
		}
		
		Field[] fields = getFields(oldObj==null?newObj.getClass():oldObj.getClass(), 0);
		
		for (Field field : fields) {
			
			if (KEYWORD_MODIFY_NAVIGATE_PROPERTIES.containsKey(field.getName())) {
				// 属性属于导航栏清单
				
				Object oldValue = getFieldValue(oldObj, field.getName());
				Object newValue = getFieldValue(newObj, field.getName());
				
				if (oldValue==null && newValue==null) {
					continue;
				} else if (
					(oldValue==null && newValue!=null)
					|| (oldValue!=null && newValue==null)
				) {
					// 有一个值为null
					set.add(KEYWORD_MODIFY_NAVIGATE_PROPERTIES.getProperty(field.getName()));
				} else if (
					oldValue instanceof Comparable
					&& 	newValue instanceof Comparable
				) {
					if (
						((Comparable)oldValue).compareTo((Comparable)newValue) != 0
					) {
						set.add(KEYWORD_MODIFY_NAVIGATE_PROPERTIES.getProperty(field.getName()));
					}
					
				} else {
					if (!oldValue.equals(newValue)) {
						set.add(KEYWORD_MODIFY_NAVIGATE_PROPERTIES.getProperty(field.getName()));
					}
				}
			}
		}
		
		return set;
	}
	
	
	/**
	 * 获取属性
	 * @param clazz
	 * @param filter
	 * @return
	 */
	private static Field[] getFields(Class clazz, int filter) {
		List<Field> list = new ArrayList<Field>();

		Field[] fields = clazz.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];

			if ((field.getModifiers() & filter) == 0) {
				list.add(field);
			}
		}

		Field[] resultFields = new Field[list.size()];
		list.toArray(resultFields);

		return resultFields;
	}
	
	
	private static Object getFieldValue(Object object, String fieldName)
	throws NoSuchFieldException {
		try {
			if (null == object) {
				return null;
			}

			Field field = object.getClass().getDeclaredField(fieldName);

			if (null == field) {
				field = object.getClass().getField(fieldName);
			}

			if (null != field) {
				field.setAccessible(true);
				return field.get(object);
			}

			return null;
		} catch (Exception e) {
			if (e instanceof NoSuchFieldException) {
				try {
					return getNotAccessibleFieldValue(object, fieldName);
				} catch (Exception ex1) {
				}
			}
		}
		throw new NoSuchFieldException("The field of " + fieldName + "not founded.");
	}
	
	private static Object getNotAccessibleFieldValue(Object object, String fieldName) 
	throws IllegalArgumentException, IllegalAccessException {
		Field field = null;
		Class superClass = object.getClass().getSuperclass();
		while (true) {
			if (superClass == null)
				return null;
			try {
				field = superClass.getDeclaredField(fieldName);
			} catch (NoSuchFieldException noSuchFieldException1) {
				superClass = superClass.getSuperclass();
			}
			
			if (null != field) {
				field.setAccessible(true);
				return field.get(object);
			}
		}
	}
	

}
