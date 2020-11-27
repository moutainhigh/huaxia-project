package com.huaxia.opas.util;

import java.lang.reflect.Field;

public class KeyValueUtil {
	
	public static Object getFieldValue(Object object, String fieldName) throws NoSuchFieldException {
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

	public static Object getNotAccessibleFieldValue(Object object, String fieldName)
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
