package com.huaxia.opas.service;

import java.util.List;

import com.huaxia.opas.domain.CRMSpIfsCrcdPrycrt;

/**
 * 预授信信息服务接口
 * 
 * @author zhiguo.li
 *
 */
public interface CRMSpIfsCrcdPrycrtService {

	/**
	 * 保存预授信信息
	 * 
	 * @param spIfsCrcdPrycrt
	 *            预授信信息对象
	 * @return
	 */
	int save(CRMSpIfsCrcdPrycrt spIfsCrcdPrycrt);
	
	/**
	 * 批量保存预授信信息
	 * 
	 * @param spIfsCrcdPrycrtList
	 *            预授信信息对象列表
	 * @return
	 */
	int saveBatch(List<CRMSpIfsCrcdPrycrt> spIfsCrcdPrycrtList);

}
