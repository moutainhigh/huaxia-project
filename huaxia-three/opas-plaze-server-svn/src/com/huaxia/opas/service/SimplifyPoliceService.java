package com.huaxia.opas.service;

import com.huaxia.opas.domain.SimplifyPolice;

/**
 * 简项公安服务接口
 * 
 * @author zhiguo.li
 *
 */
public interface SimplifyPoliceService {

	/**
	 * 保存公安信息
	 * 
	 * @param police
	 *            公安对象
	 * @return
	 */
	int save(SimplifyPolice police);

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
	/**
	 *@Title:saveSimPoliceUpdateDataAdapterAction
	 *@Description:简项公安 报文解析入库
	 *@param simplifyPolice 公安对象
	 *@param appId 流水号
	 *@author: gaohui
	 *@Date:2018年3月29日下午4:06:51
	 */
	void saveSimPoliceUpdateDataAdapterAction(SimplifyPolice simplifyPolice,String appId);
}
