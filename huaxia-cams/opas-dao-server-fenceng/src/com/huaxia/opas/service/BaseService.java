package com.huaxia.opas.service;

import com.huateng.neofp.core.CoreException;

/**
 * 基类服务接口
 * 
 * @author zhiguo.li
 *
 * @param <T>
 */
public interface BaseService<T> {

	int save(T t) throws CoreException;

	int remove(T t) throws CoreException;

	int update(T t) throws CoreException;

	T get(T t);

}
