package com.huaxia.opas.service;

import com.huaxia.opas.domain.BRZX;
import com.huaxia.opas.domain.BRZXConsumption;
import com.huaxia.opas.domain.BRZXCreditPoint;
import com.huaxia.opas.domain.BRZXLocation;
import com.huaxia.opas.domain.BRZXSpecialList;

/**
 * 百融征信服务接口
 * 
 * @author zhiguo.li
 *
 */
public interface BRZXService {

	/**
	 * 保存百融征信信息
	 * 
	 * @param brzx
	 *            百融对象
	 * @return
	 */
	int save(BRZX brzx);

	/**
	 * 保存特殊名单核查信息（身份证号）
	 * 
	 * @param specialList
	 *            特殊名单核查对象
	 * @return
	 */
	int saveSpecialListForId(BRZXSpecialList specialList);

	/**
	 * 保存特殊名单核查信息（手机）
	 * 
	 * @param specialList
	 *            特殊名单核查对象
	 * @return
	 */
	int saveSpecialListForCell(BRZXSpecialList specialList);

	/**
	 * 保存特殊名单核查信息（联系人手机号）
	 * 
	 * @param specialList
	 *            特殊名单核查对象
	 * @return
	 */
	int saveSpecialListForLmCell(BRZXSpecialList specialList);

	/**
	 * 保存地址信息核对信息
	 * 
	 * @param location
	 *            地址信息核对对象
	 * @return
	 */
	int saveLocation(BRZXLocation location);

	/**
	 * 保存商品消费评估信息
	 * 
	 * @param brCreditPoint
	 *            商品消费评估对象
	 * @return
	 */
	int saveConsumption(BRZXConsumption consumption);

	/**
	 * 保存百融通用评分信息
	 * 
	 * @param consumption
	 *            百融通用评分对象
	 * @return
	 */
	int saveCreditPoint(BRZXCreditPoint brCreditPoint);

	/**
	 * 根据申请件编号查询百融征信任务数量
	 * 
	 * @param appId
	 *            申请件编号
	 * @return
	 */
	int selectAllTaskCountByAppId(String appId);
	
	/**
	 * 保存特殊名单核查信息（GID）
	 * 
	 * @param specialList
	 *            特殊名单核查对象
	 * @return
	 */
	int saveSpecialListForGid(BRZXSpecialList specialList);
	/**
	 *@Title:saveBrzxUpdateDataAdapterAction
	 *@Description:百融征信 解析入库
	 *@param brzx
	 *@param appId
	 *@param taskType
	 *@author: gaohui
	 *@Date:2018年3月29日下午4:36:36
	 */
	void saveBrzxUpdateDataAdapterAction(BRZX brzx,String appId,String taskType);
}
