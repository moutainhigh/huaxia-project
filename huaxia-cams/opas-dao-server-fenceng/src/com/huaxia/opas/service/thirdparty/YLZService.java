package com.huaxia.opas.service.thirdparty;

import java.util.Map;
/**
 * 厦门易联众社保
 * @author Wss
 *
 */
public interface YLZService {
	
	/**
	 * 根据appId查询厦门易联众社保信息
	 * @param appId
	 * @return
	 */
	public Map<String, String> queryAllInfo(String appId);
	/**
	 * 根据appId查询厦门易联众社保条数
	 * @param appId
	 * @return
	 */
	public int queryCount(String appId);
	public Map<String, String> queryHzGjjxxInfo(String appId);
	public Map<String, String> queryXMPersonal(String appId);
	public Map<String, String> queryXmGjjInfo(String appId);
	public Map<String, String> queryWzgjjInfo(String appId);
	public Map<String, String> queryYcInfo(String appId);
}
