package com.huaxia.opas.service;

import java.util.List;

import com.huaxia.opas.domain.CRMSpIfsCustInfo;

/**
 * 客户基本信息服务接口
 * 
 * @author zhiguo.li
 *
 */
public interface CRMSpIfsCustInfoService {

	/**
	 * 保存客户基本信息
	 * 
	 * @param spIfsCustInfo
	 *            客户基本信息对象
	 * @return
	 */
	int save(CRMSpIfsCustInfo spIfsCustInfo);
	
	/**
	 * 批量保存客户基本信息
	 * 
	 * @param spIfsCustInfoList
	 *            客户基本信息对象列表
	 * @return
	 */
	int saveBatch(List<CRMSpIfsCustInfo> spIfsCustInfoList);

}
