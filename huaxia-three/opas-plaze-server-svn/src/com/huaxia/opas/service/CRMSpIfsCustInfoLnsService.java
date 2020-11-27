package com.huaxia.opas.service;

import java.util.List;

import com.huaxia.opas.domain.CRMSpIfsCustInfoLns;

/**
 * 个贷客户基本信息服务接口
 * 
 * @author zhiguo.li
 *
 */
public interface CRMSpIfsCustInfoLnsService {

	/**
	 * 保存个贷客户基本信息
	 * 
	 * @param spIfsCustInfoLns
	 *            个贷客户基本信息对象
	 * @return
	 */
	int save(CRMSpIfsCustInfoLns spIfsCustInfoLns);

	/**
	 * 批量保存个贷客户基本信息
	 * 
	 * @param spIfsCustindexList
	 *            个贷客户基本信息对象列表
	 * @return
	 */
	int saveBatch(List<CRMSpIfsCustInfoLns> spIfsCustInfoLnsList);

}
