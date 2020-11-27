package com.huaxia.opas.util;

import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.util.Assert;

import com.huaxia.opas.common.OperaMarkConstant;

import javassist.ClassClassPath;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.NotFoundException;
import javassist.bytecode.CodeAttribute;
import javassist.bytecode.LocalVariableAttribute;
import javassist.bytecode.MethodInfo;

public class OperaMarkUtil {
	
	/**
	 * @author wangyang
	 * @since 2020-10-10
	 * AOP获取请求的参数名和参数值
	 * @throws NotFoundException 
	 */
	public static Map<String, Object> getRequestParams(Class<?> clazz, String clazzName, String methodName, Object[] args) throws NotFoundException {
		Map<String, Object> map = new HashMap<String, Object>();
		ClassPool pool = ClassPool.getDefault();
		ClassClassPath classPath = new ClassClassPath(clazz);
		pool.insertClassPath(classPath);
		
		CtClass cc = pool.get(clazzName);
		CtMethod cm = cc.getDeclaredMethod(methodName);
		MethodInfo methodInfo = cm.getMethodInfo();
		CodeAttribute codeAttribute = methodInfo.getCodeAttribute();
		LocalVariableAttribute attr = (LocalVariableAttribute) codeAttribute.getAttribute(LocalVariableAttribute.tag);
		
		Assert.notNull(attr, "LocalVariableAttribute is null.");
		
		int pos = Modifier.isStatic(cm.getModifiers()) ? 0 : 1;
		for (int i = 0; i< cm.getParameterTypes().length; i++) {
			map.put(attr.variableName(i + pos), args[i]);
		}
		return map;
	}
	
	/**
	 * 获取客户端的真实IP地址
	 * @param request
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		
		return ip.equals(OperaMarkConstant.LOCAL_IPV6) ? OperaMarkConstant.LOCAL_IP : ip;
	}
}
