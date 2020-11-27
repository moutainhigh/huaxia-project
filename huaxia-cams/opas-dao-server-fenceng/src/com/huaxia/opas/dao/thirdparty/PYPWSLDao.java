package com.huaxia.opas.dao.thirdparty;

import java.util.Map;

/**
 * 深圳鹏元高信分
 * @author Wss
 *
 */
public interface PYPWSLDao {
	/**
	 * 根据appId查询深圳鹏元高信分信息
	 * @param appId
	 * @return
	 */
	public Map<String, Object> selectAllPYPWSLInfo(String appId);
}
