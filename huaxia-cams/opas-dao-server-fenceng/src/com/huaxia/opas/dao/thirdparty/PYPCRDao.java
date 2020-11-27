package com.huaxia.opas.dao.thirdparty;

import java.util.Map;

/**
 * 深圳鹏元个人社保
 * @author Wss
 *
 */
public interface PYPCRDao {
	/**
	 * 根据appId查询深圳鹏元个人社保基本信息
	 * @param appId
	 * @return
	 */
	public Map<String, Object> selectPYPCRBaseInfo(String appId);

	/**
	 * 根据appId查询深圳鹏元个人社保信息
	 * @param appId
	 * @return
	 */
	public Map<String, Object> selectPYPCRInfo(String appId);
	
	/**
	 * 根据appId查询近60个月内的鹏元个人社保信息
	 * @param appId
	 * @return
	 */
	public Map<String, Object> selectPYPCR60Info(String appId);
	/**
	 * 根据appId查询近60个月内的鹏元个人社保总汇信息
	 * @param appId
	 * @return
	 */
	public Map<String, Object> selectPYPCR60SyllogeInfo(String appId);
}
