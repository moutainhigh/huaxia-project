package com.huaxia.opas.cache.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huateng.neofp.core.CoreException;
import com.huateng.neofp.core.CoreRuntimeException;
import com.huaxia.cams.cache.util.jedis.JedisBufferUtil;
import com.huaxia.opas.cache.common.CacheConsts;
import com.huaxia.opas.cache.service.ITokenService;
import com.huaxia.opas.dao.common.OpasError;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

/**
 * TOKEN操作
 * 
 * @author zhiguo.li
 *
 */
public class TokenServiceImpl implements ITokenService {

	protected static Logger logger = LoggerFactory.getLogger(TokenServiceImpl.class);

	@Override
	public boolean addToken(String userId, final String token) throws CoreException {
		Jedis jedis = null;
		if (userId == null || "".equals(userId.trim()) || token == null || "".equals(token.trim())) {
			throw new CoreException(OpasError.NULL_PARAM_ERROR.getErrorCode(), "请传入正确userId,token");
		}
		final String installUserId = CacheConsts.TOKEN + userId;// 组装userId前带前缀
		try {
			jedis = JedisBufferUtil.getJedisInstance();

			Pipeline pipe = jedis.pipelined();
			pipe.set(installUserId, token);
			pipe.expire(installUserId, 1800);
			pipe.sync();

		} catch (Exception e) {
			logger.error("getToke***userId:" + userId + e.getMessage() + ":::", e);
			throw new CoreException(OpasError.CACHE_PUT_ERROR.getErrorCode(), "token添加失败！", e);
		} finally {
			if (jedis != null) {
				jedis.disconnect();
			}
		}

		return true;
	}

	@Override
	public void deleteToken(String userId) throws CoreException {
		Jedis jedis = null;
		try {
			jedis = JedisBufferUtil.getJedisInstance();
			jedis.del(CacheConsts.TOKEN + userId);
		} catch (Exception e) {
			logger.error("getToke***userId:" + userId + e.getMessage(), e);
			throw new CoreException(OpasError.CACHE_REMOVE_ERROR.getErrorCode(), "token删除失败！", e);
		} finally {
			if (jedis != null) {
				jedis.disconnect();
			}
		}
	}

	@Override
	public String getToken(String userId) throws CoreException {
		logger.info("TokenServiceImpl getToken userId:" + userId);
		if (userId == null) {
			logger.error("TokenServiceImpl getToken userId 为空");
			throw new CoreRuntimeException(OpasError.NULL_PARAM_ERROR.getErrorCode(),
					"TokenServiceImpl getToken userId 为空，userId=" + userId);
		}
		Jedis jedis = null;
		String token = null;
		try {
			jedis = JedisBufferUtil.getJedisInstance();
			token = jedis.get(CacheConsts.TOKEN + userId);

			if (token != null && !"".equals(token)) {
				jedis.expire(CacheConsts.TOKEN + userId, 1800);
			}
		} catch (Exception e) {
			logger.error("getToke***userId:" + userId + e.getMessage(), e);
			throw new CoreException(OpasError.CACHE_GET_ERROR.getErrorCode(), "token获取失败！", e);
		} finally {
			if (jedis != null) {
				jedis.disconnect();
			}
		}
		return token;
	}

	@Override
	public boolean addUuid(String uuid, String userId) throws CoreException {
		Jedis jedis = null;
		if (uuid == null || "".equals(uuid.trim()) || userId == null || "".equals(userId.trim())) {
			throw new CoreException(OpasError.NULL_PARAM_ERROR.getErrorCode(), "请传入正确userId,uuid");
		}
		final String uuidKey = CacheConsts.UUID + uuid;// 组装userId前带前缀
		try {
			jedis = JedisBufferUtil.getJedisInstance();
			Pipeline pipe = jedis.pipelined();
			pipe.set(uuidKey, userId);
			pipe.expire(uuidKey, 20);
			pipe.sync();
		} catch (Exception e) {
			logger.error("addUuid***userId:" + userId + "..uuid:" + uuid + "..exception" + e.getMessage() + ":::", e);
			throw new CoreException(OpasError.CACHE_PUT_ERROR.getErrorCode(), "uuid添加失败！", e);
		} finally {
			if (jedis != null) {
				jedis.disconnect();
			}
		}

		return true;
	}

	@Override
	public void deleteUuid(String uuid) throws CoreException {
		Jedis jedis = null;
		try {
			jedis = JedisBufferUtil.getJedisInstance();
			jedis.del(CacheConsts.UUID + uuid);
		} catch (Exception e) {
			logger.error("deleteUuid***uuid:" + uuid + e.getMessage(), e);
			throw new CoreException(OpasError.CACHE_REMOVE_ERROR.getErrorCode(), "uuid删除失败！", e);
		} finally {
			if (jedis != null) {
				jedis.disconnect();
			}
		}
	}

	@Override
	public String getUuid(String uuid) throws CoreException {

		if (logger.isInfoEnabled()) {
			logger.info("TokenServiceImpl getToken userId:" + uuid);
		}

		if (uuid == null) {
			logger.error("TokenServiceImpl getUuid uuid 为空");
			throw new CoreRuntimeException("TokenServiceImpl getUuid uuid 为空，uuid=" + uuid);
		}
		Jedis jedis = null;
		String userId = null;
		try {
			jedis = JedisBufferUtil.getJedisInstance();
			userId = jedis.get(CacheConsts.UUID + uuid);
		} catch (Exception e) {
			logger.error("getUuid***userId:" + uuid + e.getMessage(), e);
			throw new CoreException(OpasError.CACHE_GET_ERROR.getErrorCode(), "uuid获取失败！", e);
		} finally {
			if (jedis != null) {
				jedis.disconnect();
			}
		}
		return userId;
	}

	@Override
	public void addUserId(String userId, String guid) throws CoreException {
		Jedis jedis = null;
		if (userId == null || "".equals(userId.trim()) || guid == null || "".equals(guid.trim())) {
			throw new CoreException(OpasError.NULL_PARAM_ERROR.getErrorCode(), "请传入正确userId,uuid");
		}
		try {
			jedis = JedisBufferUtil.getJedisInstance();
			Pipeline pipe = jedis.pipelined();
			pipe.set(userId, guid);
			pipe.sync();
		} catch (Exception e) {
			logger.error("addUuid***userId:" + userId + "..guid:" + guid + "..exception" + e.getMessage() + ":::", e);
			throw new CoreException(OpasError.CACHE_PUT_ERROR.getErrorCode(), "uuid添加失败！", e);
		} finally {
			if (jedis != null) {
				jedis.disconnect();
			}
		}
	}

	@Override
	public String getGuid(String userId) throws CoreException {
		Jedis jedis = null;
		String guid = null;
		try {
			jedis = JedisBufferUtil.getJedisInstance();
		    guid = jedis.get(userId);
		} catch (Exception e) {
			throw new CoreException(OpasError.CACHE_GET_ERROR.getErrorCode(), "uuid获取失败！", e);
		} finally {
			if (jedis != null) {
				jedis.disconnect();
			}
		}
		return guid;
	}
}
