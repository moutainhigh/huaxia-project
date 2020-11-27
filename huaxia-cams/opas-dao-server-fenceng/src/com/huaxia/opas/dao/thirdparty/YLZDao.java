package com.huaxia.opas.dao.thirdparty;

import java.util.Map;

public interface YLZDao {

	/**
	 * 根据appId查询厦门易联众社保信息
	 * @param appId
	 * @return
	 */
	public Map<String, String> selectAllYLZInfo(String appId);
	
	/**
	 * 根据appId查询条数
	 * @param appId
	 * @return
	 */
	public int selectCount(String appId);

	public Map<String, String> queryHzGjjxxInfo(String appId);

	public Map<String, String> queryXMPersonal(String appId);

	public Map<String, String> queryXmGjjInfo(String appId);

	public Map<String, String> queryWzgjjInfo(String appId);

	public Map<String, String> queryYcInfo(String appId);

	
}
