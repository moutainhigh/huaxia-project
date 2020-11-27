package com.huaxia.opas.cache.service.address;

import com.huateng.neofp.core.CoreException;

/**
 * 单位地址行政级别比较
 * @author qianxiwen
 */
public interface AddresMachLevService {
	/**
	 * 
	 * @param WorkType 是否是事业单位
	 * @param Address  单位地址
	 * @param CompanyName 单位/公司名称
	 * @return
	 * @throws CoreException
	 */
	public String getMachResult(String WorkType,String Address,String CompanyName) throws CoreException;
	
	
}
