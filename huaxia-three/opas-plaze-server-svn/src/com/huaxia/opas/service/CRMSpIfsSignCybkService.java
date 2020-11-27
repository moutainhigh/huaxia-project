package com.huaxia.opas.service;

import java.util.List;

import com.huaxia.opas.domain.CRMSpIfsSignCybk;

/**
 * 网银签约服务接口
 * 
 * @author zhiguo.li
 *
 */
public interface CRMSpIfsSignCybkService {

	/**
	 * 保存网银签约信息
	 * 
	 * @param spIfsSignCybk
	 *            网银签约对象
	 * @return
	 */
	int save(CRMSpIfsSignCybk spIfsSignCybk);

	/**
	 * 批量保存网银签约信息
	 * 
	 * @param spIfsSignCybkList
	 *            网银签约信息对象列表
	 * @return
	 */
	int saveBatch(List<CRMSpIfsSignCybk> spIfsSignCybkList);

}
