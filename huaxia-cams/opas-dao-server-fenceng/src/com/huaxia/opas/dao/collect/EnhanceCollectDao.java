package com.huaxia.opas.dao.collect;

import java.util.Date;

import com.huaxia.opas.dao.DAO;
import com.huaxia.opas.domain.collect.EnhanceBlazeResult;
import com.huaxia.opas.domain.collect.EnhanceInfo;

/**
 * 增强信息采集接口
 * 
 * @author zhiguo.li
 *
 */
public interface EnhanceCollectDao extends DAO<EnhanceInfo> {

	/**
	 * 查询指定申请件编号的记录
	 * 
	 * @param appId
	 *            申请件编号
	 * @return
	 */
	EnhanceInfo selectByAppId(String appId);

	/**
	 * 更新增强采集BLAZE响应结果
	 * 
	 * @param blaze
	 *            BLAZE结果对象
	 * @return
	 */
	int updateEnhanceInfoForBlaze(EnhanceBlazeResult blaze);

	/**
	 * 根据申请件编号查询入职时间
	 * 
	 * @param appId
	 *            申请件编号
	 * @return
	 */
	Date selectEntryYearByAppId(String appId);
	/**
	 * 更新指定申请件编号对应记录的外键值
	 * 
	 * @param appId
	 *            申请件编号
	 * @return
	 */
	int updateForeignKey(String appId);
	/**
	 * 工作年限
	 * 
	 * @param appId
	 *            
	 * @return
	 */
	int selectC1CoyrByAppId(String appId);
	/**
	 * 查询指定申请件编号的记录
	 * 
	 * @param appId
	 *            申请件编号
	 * @return
	 */
	String  selectRequestTypeByAppId(String appId);


}
