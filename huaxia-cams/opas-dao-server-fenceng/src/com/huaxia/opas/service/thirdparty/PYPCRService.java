package com.huaxia.opas.service.thirdparty;

import java.util.Map;

/**
 * 深圳鹏元个人社保
 * @author Wss
 *
 */
public interface PYPCRService {
	
	/**
	 * 根据appId查询深圳鹏元个人社保基本信息
	 * @param appId
	 * @return
	 */
	public Map<String, Object> queryPYPCRBaseInfo(String appId);
	
	/**
	 * 根据appId查询深圳鹏元个人社保信息
	 * @param appId
	 * @return
	 */
	public Map<String, Object> queryPYPCRInfo(String appId);
	
	/**
	 * 根据appId查询深圳鹏元个人社保信息 60个月内参保信息
	 * @param appId
	 * @return
	 */
	public Map<String, Object> queryPYPCR60Info(String appId);
	/**
	 * 根据appId查询深圳鹏元个人社保信息 60个月内参保总汇信息
	 * @param appId
	 * @return
	 */
	public Map<String, Object> queryPYPCR60SyllogeInfo(String appId);
}
