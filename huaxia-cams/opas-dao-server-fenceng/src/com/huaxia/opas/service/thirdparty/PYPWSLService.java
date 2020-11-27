package com.huaxia.opas.service.thirdparty;

import java.util.Map;

/**
 * 深圳鹏元高信分
 * @author Wss
 *
 */
public interface PYPWSLService {
	/**
	 * 根据appId查询深圳鹏元高信分信息
	 * @param appId
	 * @return
	 */
	public Map<String, Object> queryAllPYPWSLInfo(String appId);

}
