package com.huaxia.opas.cache.service;

import java.util.List;

import com.huateng.neofp.core.CoreException;

/**
 * 全文检索服务
 * @author Administrator
 */
public interface LueneService {
	/**
	 * 获取匹配结果是否一致
	 * @param strs1
	 * @param strs2
	 * @return boolean
	 * @throws CoreException
	 */	
	public boolean getMachResult(String strs1,String strs2,String strtype) throws CoreException;
	public boolean getMachResultTel(String strs1,String strs2,String strtype) throws CoreException;
}
