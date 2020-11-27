package com.huaxia.opas.dao;

import com.huateng.neofp.core.CoreException;

public interface DAO<T> {
	
	int insert(T t) throws CoreException;
	
	int update(T t) throws CoreException;
	
	int delete(T t) throws CoreException;
	
	T selectById(T t);
}
