package com.huaxia.opas.cache.service;

import com.huateng.neofp.core.CoreException;

/**
 * TOKEN接口
 * 
 * @author zhiguo.li
 *
 */
public interface ITokenService {
	
	/**
	 * 新增token方法
	 * 
	 * @param userId
	 *            设置token的key 为用户id
	 * @param token
	 *            token值
	 * @return
	 */
	public boolean addToken(String userId, String token) throws Exception;

	/**
	 * 获取token方法
	 * 
	 * @param userId
	 *            根据用户id获取对应的token
	 * @return 返回最后修改的token
	 */
	public String getToken(String userId) throws Exception;

	/**
	 * 删除token方法
	 * 
	 * @param userId
	 *            根据用户id删除对应的token
	 */
	public void deleteToken(String userId) throws Exception;

	/**
	 * 保存uuid方法
	 * 
	 * @param uuid
	 * @param userId
	 * @return
	 */
	public boolean addUuid(String uuid, String userId) throws Exception;

	/**
	 * 删除uuid方法
	 * 
	 * @param uuid
	 */
	public void deleteUuid(String uuid) throws Exception;

	/**
	 * 获取uuid方法
	 * 
	 * @param uuid
	 * @return
	 * @throws Exception
	 */
	public String getUuid(String uuid) throws Exception;
	
	/**
	 * 绑定userId和guid 
	 * @param uuid
	 * @param userId
	 * @throws Exception
	 */
	public void addUserId(String userId, String guid) throws Exception;

	/**
	 * 根据userId获取guid
	 * @param userId
	 * @return
	 * @throws CoreException 
	 */
	public String getGuid(String userId) throws CoreException;

}
