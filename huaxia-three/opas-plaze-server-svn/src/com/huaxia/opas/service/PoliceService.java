package com.huaxia.opas.service;

import java.util.List;

import com.huaxia.opas.domain.Police;

/**
 * 增强公安服务接口
 * 
 * @author zhiguo.li
 *
 */
public interface PoliceService {

	/**
	 * 保存公安信息
	 * 
	 * @param police
	 *            公安对象
	 * @return
	 */
	int save(Police police);

	/**
	 * 批量保存公安信息
	 * 
	 * @param policeList
	 *            公安对象集合
	 * @return
	 */
	int saveBatch(List<Police> policeList);

	/**
	 * 判断指定编号申请件是否存在
	 * 
	 * @param appId
	 *            申请件编号
	 * @return
	 */
	int getCountByAppId(String appId);

	/**
	 * 判断指定证件号码申请件是否存在
	 * 
	 * @param certNo
	 *            证件号码
	 * @return
	 */
	int getCountByCertNo(String certNo);

}
