package com.huaxia.opas.util;


import java.beans.Introspector;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.ConstructorUtils;

/**
 * 类操作使用类
 * @author pccc
 */
public class ClassUtil {
	// java.lang.Boolean -> Boolean.TYPE
	private static Map<String, String> abbreviationMap = new HashMap<String, String>(8);
	
	public static final String ARRAY_SUFFIX = "[]";
	// Boolean.class -> Boolean.TYPE
	private static final Map<String, Class> primitiveTypeNameMap = new HashMap<String, Class>(8);
	// Boolean.class -> Boolean.TYPE
	private static final Map<Class, Class> primitiveWrapperTypeMap = new HashMap<Class, Class>(8);
	
	static {
		/*
		 * 静态处理块
		 */
		
		// 类型缩写
		abbreviationMap.put("int", "I");
		abbreviationMap.put("boolean", "Z");
		abbreviationMap.put("float", "F");
		abbreviationMap.put("long", "J");
		abbreviationMap.put("short", "S");
		abbreviationMap.put("byte", "B");
		abbreviationMap.put("double", "D");
		abbreviationMap.put("char", "C");
		
		// 原包装类型
		primitiveWrapperTypeMap.put(Boolean.class, Boolean.TYPE);
		primitiveWrapperTypeMap.put(Byte.class, Byte.TYPE);
		primitiveWrapperTypeMap.put(Character.class, Character.TYPE);
		primitiveWrapperTypeMap.put(Double.class, Double.TYPE);
		primitiveWrapperTypeMap.put(Float.class, Float.TYPE);
		primitiveWrapperTypeMap.put(Integer.class, Integer.TYPE);
		primitiveWrapperTypeMap.put(Long.class, Long.TYPE);
		primitiveWrapperTypeMap.put(Short.class, Short.TYPE);
		
		// 处理 基础类名 和 类的对应关系
		for (Iterator iterator = primitiveWrapperTypeMap.values().iterator(); iterator.hasNext();) {
			Class primitiveClass = (Class) iterator.next();
			primitiveTypeNameMap.put(primitiveClass.getName(), primitiveClass);
		}
	}
	
	/**
	 * 添加资源路径到包路径中
	 * @param clazz
	 * @param resourceName
	 * @return
	 */
	public static String addResourcePathToPackagePath(Class clazz, String resourceName) {
		if (!resourceName.startsWith("/")) {
			return classPackageAsResourcePath(clazz) + "/" + resourceName;
		}
		return classPackageAsResourcePath(clazz) + resourceName;
	}
	
	/**
	 * 参数To字符串
	 * @param args 参数
	 * @return
	 */
	public static String argumentTypesToString(Object[] args) {
		return "(" + Arrays.toString(args) + ")";
	}
	
	/**
	 * 类包转化为文件夹路径
	 * @param clazz
	 * @return
	 */
	public static String classPackageAsResourcePath(Class clazz) {
		if (clazz == null || clazz.getPackage() == null) {
			return "";
		}
		return clazz.getPackage().getName().replace('.', '/');
	}
	
	/**
	 * 复制属性
	 * @param sourceObject 原对象
	 * @param targetObject 目标对象
	 */
	public static void copyFields(Object sourceObject, Object targetObject) {
		if (
			null == sourceObject 
			|| null == targetObject
		) {
			throw new NullPointerException("both of the objects should not be null.");
		}
		Map<String, Field> fieldMap = new HashMap<String, Field>();
		
		// 获取原对象全部属性
		Field[] sourceFields = getAllFields(sourceObject.getClass(), 24);
		for (int i = 0; i < sourceFields.length; i++) {
			Field field = sourceFields[i];
			fieldMap.put(field.getName(), field);
		}
		
		// 获取目标对象全部属性
		Field[] targetFields = getAllFields(targetObject.getClass(), 24);
		for (int i = 0; i < targetFields.length; i++) {
			Field targetField = targetFields[i];
			
			// 如果原对象中不存在该属性，则过滤
			Field sourceField = (Field) fieldMap.get(targetField.getName());

			if (null == sourceField)
				continue;
			try {
				sourceField.setAccessible(true);
				targetField.setAccessible(true);
				
				Object value = sourceField.get(sourceObject);
				targetField.set(targetObject, value);
				
			} catch (Exception ignore) {
				// 忽略异常
			}
		}
	}
	
	/**
	 * 获取全部属性
	 * @param clazz 类
	 * @param filter 过滤器
	 * @return
	 */
	public static Field[] getAllFields(Class clazz, int filter) {
		List<Field> list = new ArrayList<Field>();

		while (null != clazz) {
			CollectionUtils.addAll(list, getFields(clazz, filter));
			clazz = clazz.getSuperclass();
		}

		Field[] resultFields = new Field[list.size()];
		list.toArray(resultFields);

		return resultFields;
	}
	
	/**
	 * 获取对象全部接口的类型
	 * @param object 对象
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Class[] getAllInterfaces(Object object) {
		Set interfaces = getAllInterfacesAsSet(object);
		return (Class[]) (Class[]) interfaces.toArray(new Class[interfaces.size()]);
	}
	
	/**
	 * 获取对象全部接口的类型
	 * @param object 对象
	 * @return
	 */
	public static Set getAllInterfacesAsSet(Object object) {
		return getAllInterfacesForClassAsSet(object.getClass());
	}
	
	/**
	 * 获取对象全部接口的类型
	 * @param clazz 类
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Class[] getAllInterfacesForClass(Class clazz) {
		Set interfaces = getAllInterfacesForClassAsSet(clazz);
		return (Class[]) (Class[]) interfaces.toArray(new Class[interfaces.size()]);
	}
	
	/**
	 * 获取对象全部接口的类型
	 * @param clazz 类
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Set getAllInterfacesForClassAsSet(Class clazz) {
		return new HashSet(ClassUtils.getAllInterfaces(clazz));
	}

	/**
	 * 获取Class
	 * @param classLoader 类加载器
	 * @param className 类名
	 * @return
	 * @throws ClassNotFoundException
	 */
	public static Class getClass(ClassLoader classLoader, String className)
			throws ClassNotFoundException {
		return getClass(classLoader, className, true);
	}

	/**
	 * 获取Class对象
	 * @param classLoader 类加载器
	 * @param className 类名
	 * @param initialize whether the class must be initialized
	 * @return
	 * @throws ClassNotFoundException
	 */
	private static Class getClass(ClassLoader classLoader, String className,
			boolean initialize) throws ClassNotFoundException {
		Class clazz;
		if (abbreviationMap.containsKey(className)) {
			String clsName = "[" + abbreviationMap.get(className);
			clazz = Class.forName(clsName, initialize, classLoader)
					.getComponentType();
		} else {
			clazz = Class.forName(toProperClassName(className), initialize,
					classLoader);
		}
		return clazz;
	}

	/**
	 * 获取类定义
	 * @param args 需要获取的对象列表
	 * @return
	 */
	public static Class[] getClasses(Object[] args) {
		if (ArrayUtils.isEmpty(args)) {
			return new Class[0];
		}

		Class[] classes = new Class[args.length];
		for (int i = 0; i < args.length; i++) {
			Object object = args[i];
			if (null == object) {
				throw new IllegalArgumentException(
						"the object array should not contains null");
			}

			classes[i] = object.getClass();
			if (primitiveWrapperTypeMap.containsKey(classes[i])) {
				classes[i] = ((Class) primitiveWrapperTypeMap.get(classes[i]));
			}

		}

		return classes;
	}
	
	/**
	 * 获取默认类加载器
	 * @return
	 */
	public static ClassLoader getDefaultClassLoader() {
		ClassLoader classLoader = null;
		try {
			classLoader = Thread.currentThread().getContextClassLoader();
		} catch (Throwable e) {
			System.err.println(String.format("获取当前类加载器异常：%s", e.getMessage()));
		}
		if (classLoader == null) {
			classLoader = ClassUtil.class.getClassLoader();
		}
		return classLoader;
	}

	/**
	 * 获取属性
	 * @param clazz 类
	 * @param fieldName 属性名称
	 * @return
	 */
	public static Field getField(Class clazz, String fieldName) {
		Field[] fields = clazz.getFields();
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			if (field.getName().equals(fieldName)) {
				return field;
			}
		}
		fields = clazz.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			if (field.getName().equals(fieldName)) {
				return field;
			}
		}
		return null;
	}

	/**
	 * 获取属性
	 * @param clazz
	 * @param filter
	 * @return
	 */
	public static Field[] getFields(Class clazz, int filter) {
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
	
	/**
	 * 获取对象属性值
	 * @param object 对象
	 * @param fieldName 属性名称
	 * @return
	 * @throws NoSuchFieldException
	 */
	public static Object getFieldValue(Object object, String fieldName)
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

	/**
	 * 获取方法
	 * @param clazz 类
	 * @param methodName 方法名称
	 * @param parameterTypes 参数类型
	 * @return
	 */
	public static Method getMethod(Class clazz, String methodName,
			Class[] parameterTypes) {
		return getMethod(clazz, methodName, parameterTypes, true);
	}

	/**
	 * 获取方法
	 * @param clazz 类
	 * @param methodName 方法名称
	 * @param parameterTypes 参数类型
	 * @param allowSuperType 是否检查父类方法
	 * @return
	 */
	public static Method getMethod(Class clazz, String methodName,
			Class[] parameterTypes, boolean allowSuperType) {
		
		// 普通方法
		Method[] methods = clazz.getMethods();
		for (int i = 0; i < methods.length; i++) {
			Method method = methods[i];
			if ((method.getName().equals(methodName))
					&& (isParameterEqual(method, parameterTypes, allowSuperType))) {
				return method;
			}
		}
		
		// 公告使用的方法
		methods = clazz.getDeclaredMethods();
		for (int i = 0; i < methods.length; i++) {
			Method method = methods[i];
			if ((method.getName().equals(methodName))
					&& (isParameterEqual(method, parameterTypes, allowSuperType))) {
				return method;
			}
		}

		return null;
	}

	/**
	 * 获取方法
	 * @param clazz 类
	 * @param methodName 方法名称
	 * @param args 参数类型
	 * @return
	 */
	public static Method getMethod(Class clazz, String methodName, Object[] args) {
		return getMethod(clazz, methodName, args, true);
	}

	/**
	 * 获取方法
	 * @param clazz 类
	 * @param methodName 方法名称
	 * @param args 参数类型
	 * @param allowSuperType
	 * @return
	 */
	public static Method getMethod(Class clazz, String methodName, Object[] args,
			boolean allowSuperType) {
		Class[] classes = getClasses(args);
		return getMethod(clazz, methodName, classes, allowSuperType);
	}

	public static int getMethodCountForName(Class clazz, String methodName) {
		int count = 0;
		for (int i = 0; i < clazz.getDeclaredMethods().length; i++) {
			Method method = clazz.getDeclaredMethods()[i];
			if (methodName.equals(method.getName())) {
				count++;
			}
		}
		Class[] interfaces = clazz.getInterfaces();
		for (int i = 0; i < interfaces.length; i++) {
			count += getMethodCountForName(interfaces[i], methodName);
		}
		if (clazz.getSuperclass() != null) {
			count += getMethodCountForName(clazz.getSuperclass(), methodName);
		}
		return count;
	}

	/**
	 * 
	 * @param method
	 * @return
	 */
	public static String getMethodSingnature(Method method) {
		StringBuffer buffer = new StringBuffer();
		String methodName = method.getName();
		buffer.append(methodName);
		buffer.append("(");
		Class[] argumentTypes = method.getParameterTypes();
		for (int i = 0; i < argumentTypes.length; i++) {
			buffer.append(argumentTypes[i].getSimpleName());
			if (i < argumentTypes.length - 1) {
				buffer.append(", ");
			}
		}
		buffer.append(")");
		return buffer.toString();
	}

	/**
	 * 获取无法到达的属性值
	 * @param object 对象
	 * @param fieldName 属性
	 * @return
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
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

	public static String getQualifiedMethodName(Method method) {
		return method.getDeclaringClass().getName() + "." + method.getName();
	}

	public static String getQualifiedName(Class clazz) {
		if (clazz.isArray()) {
			return getQualifiedNameForArray(clazz);
		}

		return clazz.getName();
	}

	private static String getQualifiedNameForArray(Class clazz) {
		StringBuffer buffer = new StringBuffer();
		while (clazz.isArray()) {
			clazz = clazz.getComponentType();
			buffer.append(ARRAY_SUFFIX);
		}
		buffer.insert(0, clazz.getName());
		return buffer.toString();
	}

	public static Class getRealComponentType(Class clazz) {
		if (clazz.isArray()) {
			Class newClazz = clazz.getComponentType();
			return getRealComponentType(newClazz);
		}

		return clazz;
	}

	/**
	 * 获取属性短名称
	 * @param clazz
	 * @return
	 */
	public static String getShortNameAsProperty(Class clazz) {
		return Introspector.decapitalize(ClassUtils.getShortClassName(clazz));
	}

	/**
	 * 获取静态方法
	 * @param clazz 类
	 * @param methodName 方法名
	 * @param args 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Method getStaticMethod(Class clazz, String methodName, Class[] args) {
		try {
			Method method = clazz.getDeclaredMethod(methodName, args);
			if ((method.getModifiers() & 0x8) != 0)
				return method;
		} catch (NoSuchMethodException ex) {
		}
		return null;
	}
	
	/**
	 * 异常处理方法
	 * @param ex
	 */
	private static void handleInvocationTargetException(
			InvocationTargetException ex) {
		if (ex.getTargetException() instanceof RuntimeException) {
			throw ((RuntimeException) ex.getTargetException());
		}
		if (ex.getTargetException() instanceof Error) {
			throw ((Error) ex.getTargetException());
		}
		throw new IllegalStateException(
				"Unexpected exception thrown by method - "
						+ ex.getTargetException().getClass().getName() + ": "
						+ ex.getTargetException().getMessage());
	}
	
	/**
	 * 异常处理方法
	 * @param ex 异常
	 */
	private static void handleReflectionException(Exception ex) {
		if ((ex instanceof NoSuchMethodException)) {
			throw new IllegalStateException("Method not found: " + ex.getMessage());
		}
		if ((ex instanceof IllegalAccessException)) {
			throw new IllegalStateException("Could not access method: " + ex.getMessage());
		}
		if ((ex instanceof InvocationTargetException)) {
			handleInvocationTargetException((InvocationTargetException) ex);
		}
		throw new IllegalStateException("Unexpected reflection exception - " + ex.getClass().getName() + ": " + ex.getMessage());
	}
	
	/**
	 * 判断类是否存在相应方法
	 * @param clazz 类
	 * @param methodName 
	 * @return
	 */
	public static boolean hasMethod(Class clazz, String methodName) {
		for (int i = 0; i < clazz.getDeclaredMethods().length; i++) {
			Method method = clazz.getDeclaredMethods()[i];
			if (method.getName().equals(methodName)) {
				return true;
			}
		}
		Class[] interfaces = clazz.getInterfaces();
		for (int i = 0; i < interfaces.length; i++) {
			if (hasMethod(interfaces[i], methodName)) {
				return true;
			}
		}
		if (clazz.getSuperclass() != null) {
			return hasMethod(clazz.getSuperclass(), methodName);
		}
		return false;
	}
	
	public static boolean hasMethod(Class clazz, String methodName,
			Class[] argumentTypes) {
		return getMethod(clazz, methodName, argumentTypes) != null;
	}
	
	/**
	 * 调用方法
	 * @param clazz 类
	 * @param methodName 方法名
	 * @param args 方法所需要的参数
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	public static Object invokeMethod(Class clazz, String methodName,
			Object[] args) throws InstantiationException,
			IllegalAccessException, IllegalArgumentException,
			InvocationTargetException {
		Object target = clazz.newInstance();
		return getMethod(clazz, methodName, args).invoke(target, args);
	}
	
	/**
	 * 调用方法
	 * @param clazz 类
	 * @param methodName 方法名
	 * @param args 方法所需要的参数
	 * @param allowSuperType
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	public static Object invokeMethod(Class clazz, String methodName,
			Object[] args, boolean allowSuperType)
			throws InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		Object target = clazz.newInstance();
		return getMethod(clazz, methodName, args).invoke(target, args);
	}
	
	/**
	 * 调用目标对象方法
	 * @param method 方法
	 * @param target 对象
	 * @return
	 */
	public static Object invokeMethod(Method method, Object target) {
		return invokeMethod(method, target, null);
	}
	
	/**
	 * 调用目标对象方法
	 * @param method 方法
	 * @param target 对象
	 * @param args 参数
	 * @return
	 */
	public static Object invokeMethod(Method method, Object target, Object[] args) {
		try {
			
			return method.invoke(target, args);
			
		} catch (IllegalAccessException ex) {
			handleReflectionException(ex);
			throw new IllegalStateException("Unexpected reflection exception - " + ex.getClass().getName() + ": " + ex.getMessage());
		} catch (InvocationTargetException ex) {
			handleReflectionException(ex);
			throw new IllegalStateException("Unexpected reflection exception - " + ex.getClass().getName() + ": " + ex.getMessage());
		}
		
	}
	
	/**
	 * 调用方法
	 * @param object 对象
	 * @param methodName 方法名
	 * @param types 类型
	 * @param args 方法所需要的参数
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	public static Object invokeMethod(Object object, String methodName, Class[] types, Object[] args) 
	throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		return getMethod(object.getClass(), methodName, types).invoke(object, args);
	}
	
	/**
	 * 调用方法
	 * @param object 对象
	 * @param methodName 方法名称
	 * @param args 参数
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	public static Object invokeMethod(Object object, String methodName, Object[] args) 
	throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		return getMethod(object.getClass(), methodName, args).invoke(object, args);
	}
	
	/**
	 * 调用方法
	 * @param clazzName 类名称
	 * @param methodName 方法名
	 * @param args 方法所需要的参数
	 * @return
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws Throwable
	 */
	public static Object invokeMethod(String clazzName, String methodName, Object[] args) 
	throws InstantiationException, IllegalAccessException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException {
		Class clazz = getClass(getDefaultClassLoader(), clazzName);
		Object target = clazz.newInstance();
		return getMethod(clazz, methodName, args).invoke(target, args);
	}
	
	/**
	 * 判定此 Class 对象所表示的类或接口与指定的 Class 参数所表示的类或接口是否相同，或是否是其超类或超接口。
	 * @param targetType 目标类型
	 * @param valueType 值类型
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static boolean isAssignable(Class targetType, Class valueType) {
		return targetType.isAssignableFrom(valueType)
				|| (targetType.equals(primitiveWrapperTypeMap.get(valueType)));
	}
	
	/**
	 * 
	 * @param type 类型
	 * @param value 值
	 * @return
	 */
	public static boolean isAssignableValue(Class type, Object value) {
		return !type.isPrimitive() ? true : value != null ? isAssignable(type, value.getClass()) : false;
	}
	
	/**
	 * 判断两个对象是否具有相同的属性
	 * @param sourceObject 原对象
	 * @param targetObject 目标对象
	 * @return
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	@SuppressWarnings("unchecked")
	public static boolean isEqualOnFields(Object sourceObject, Object targetObject) 
	throws IllegalArgumentException, IllegalAccessException {
		if (null == sourceObject && null == targetObject) {
			return true;
		}

		if (null == sourceObject || null == targetObject) {
			return false;
		}

		if (sourceObject.getClass() != targetObject.getClass()) {
			return false;
		}

		if (sourceObject instanceof Comparable) {
			return 0 == ((Comparable) sourceObject).compareTo(targetObject);
		}

		Field[] fields = getAllFields(sourceObject.getClass(), 24);
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];

			field.setAccessible(true);

			Object sourceValue = field.get(sourceObject);
			Object targetValue = field.get(targetObject);

			if (field.getType().isArray()) {
				if (!ArrayUtils.isEquals(sourceValue, targetValue)) {
					return false;
				}

			} else if (!ObjectUtils.equals(sourceValue, targetValue)) {
				return false;
			}

		}

		return true;
	}
	
	/**
	 * 判断方法是否参数也是一致的
	 * @param method 方法
	 * @param parameterTypes 参数类型
	 * @param allowSuperType 是否检查父类方法
	 * @return
	 */
	public static boolean isParameterEqual(Method method,
			Class[] parameterTypes, boolean allowSuperType) {
		if (null == method) {
			return false;
		}
		Class[] methodParameterTypes = method.getParameterTypes();

		if ((ArrayUtils.isEmpty(parameterTypes))
				&& (ArrayUtils.isEmpty(methodParameterTypes))) {
			return true;
		}

		if (ArrayUtils.getLength(parameterTypes) != ArrayUtils
				.getLength(methodParameterTypes)) {
			return false;
		}

		if (allowSuperType) {
			return ClassUtils
					.isAssignable(parameterTypes, methodParameterTypes);
		}

		return Arrays.equals(parameterTypes, methodParameterTypes);
	}
	
	/**
	 * 判断类是否存在
	 * @param className 类名
	 * @return
	 */
	public static boolean isPresent(String className) {
		try {
			getClass(getDefaultClassLoader(), className);
			return true;
		} catch (Throwable ex) {
		}
		return false;
	}
	
	/**
	 * 判断类是否存在
	 * @param className 类名
	 * @param classLoader 类加载器
	 * @return
	 */
	public static boolean isPresent(String className, ClassLoader classLoader) {
		try {
			getClass(classLoader, className);
			return true;
		} catch (Throwable ex) {
		}
		return false;
	}
	
	public static boolean isPrimitiveArray(Class clazz) {
		return (clazz.isArray()) && (getRealComponentType(clazz).isPrimitive());
	}
	
	public static boolean isPrimitiveOrWrapper(Class clazz) {
		return (clazz.isPrimitive()) || (isPrimitiveWrapper(clazz));
	}
	
	public static boolean isPrimitiveWrapper(Class clazz) {
		return primitiveWrapperTypeMap.containsKey(clazz);
	}
	
	public static boolean isPrimitiveWrapperArray(Class clazz) {
		return (clazz.isArray())
				&& (isPrimitiveWrapper(getRealComponentType(clazz)));
	}
	
	/**
	 * 判断是否静态方法
	 * @param targetClass 类
	 * @param methodName 方法名称
	 * @param argumentTypes 参数类型
	 * @return
	 */
	public static boolean isStaticMethod(Class targetClass, String methodName, Class[] argumentTypes) {
		Method method = getMethod(targetClass, methodName, argumentTypes);
		return Modifier.isStatic(method.getModifiers());
	}
	
	/**
	 * 判断是否静态方法
	 * @param targetClass 类
	 * @param methodName 方法名称
	 * @param args 参数
	 * @return
	 * @throws IllegalArgumentException
	 */
	public static boolean isStaticMethod(Class targetClass, String methodName, Object[] args) 
	throws IllegalArgumentException {
		Class[] classes = getClasses(args);
		return isStaticMethod(targetClass, methodName, classes);
	}
	
	/**
	 * 判断是否静态方法
	 * @param className 类名称
	 * @param methodName 方法名称
	 * @param args 参数
	 * @return
	 * @throws ClassNotFoundException
	 */
	public static boolean isStaticMethod(String className, String methodName, Object[] args) 
	throws ClassNotFoundException {
		return isStaticMethod(Class.forName(className), methodName, args);
	}
	
	/**
	 * 加载类库
	 * @param fileNames 类库文件数组
	 * @return
	 * @throws IOException
	 */
	public static URLClassLoader loadLibrary(String[] fileNames)
			throws IOException {
		File[] files = new File[fileNames.length];

		for (int i = 0; i < files.length; i++) {
			files[i] = new File(fileNames[i]);
		}

		URL[] urls = FileUtils.toURLs(files);
		return URLClassLoader.newInstance(urls);
	}
	
	/**
	 * 创建新的对象实例
	 * @param className 类名称
	 * @param objects 对象
	 * @return
	 * @throws NoSuchMethodException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws InstantiationException
	 * @throws ClassNotFoundException
	 * @throws LinkageError
	 */
	@SuppressWarnings("unchecked")
	public static Object newInstance(String className, Object[] objects)
	throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, ClassNotFoundException, LinkageError {
		if (StringUtils.isBlank(className)) {
			return new IllegalArgumentException("the class name can't be null");
		}
		Class clazz = getClass(getDefaultClassLoader(), className);
		return ConstructorUtils.invokeConstructor(clazz, objects);
	}
	
	/**
	 * 获取原始类
	 * @param name
	 * @return
	 */
	public static Class resolvePrimitiveClassName(String name) {
		Class result = null;

		if ((name != null) && (name.length() <= 8)) {
			result = (Class) primitiveTypeNameMap.get(name);
		}
		return result;
	}
	
	/**
	 * 
	 * @param className
	 * @return
	 */
	private static String toProperClassName(String className) {
		className = StringUtil.deleteWhiteSpace(className);
		if (className == null) {
			throw new IllegalArgumentException("the className can't be null");
		}
		if (className.endsWith(ARRAY_SUFFIX)) {
			StringBuffer classNameBuffer = new StringBuffer();
			for (; className.endsWith(ARRAY_SUFFIX); classNameBuffer.append("[")) {
				className = className.substring(0, className.length() - 2);
			}

			String abbreviation = (String) abbreviationMap.get(className);
			if (abbreviation != null) {
				classNameBuffer.append(abbreviation);
			} else {
				classNameBuffer.append("L").append(className).append(";");
			}
			className = classNameBuffer.toString();
		}
		return className;
	}

}
