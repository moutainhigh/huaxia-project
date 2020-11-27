package com.huaxia.opas.cache.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.huateng.neofp.core.CoreException;
import com.huaxia.cams.cache.util.jedis.JedisBufferUtil;
import com.huaxia.opas.cache.common.CacheConsts;
import com.huaxia.opas.cache.service.IDictService;
import com.huaxia.opas.dao.common.OpasError;
import com.huaxia.opas.domain.dict.ApDictDetailSmall;
import com.huaxia.opas.service.dict.ApDictDetailService;

/**
 * 业务字典
 * 
 * @author zhiguo.li
 *
 */
public class DictServiceImpl implements IDictService {

	protected static Logger logger = LoggerFactory.getLogger(DictServiceImpl.class);

	@Resource(name = "apDictDetailService")
	private ApDictDetailService apDictDetailService;

	@Override
	public List<ApDictDetailSmall> getDictContent(final String dictCode) throws Exception {
		if (logger.isDebugEnabled()) {
			logger.info("@@@@@@@@@@" + this.getClass() + ":getDictContent===dictCode:" + dictCode);
		}

		if (StringUtils.isBlank(dictCode)) {
			return new ArrayList<ApDictDetailSmall>();
		}

		// 组装带前缀的Key
		final String installKey = CacheConsts.DICT + dictCode;

		Jedis jedis = null;
		String dict = null;
		try {
			jedis = JedisBufferUtil.getJedisInstance();
			dict = jedis.get(installKey);

			if (logger.isDebugEnabled()) {
				logger.info("DictServiceImpl getDictContent dict:" + dict);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new CoreException(OpasError.CACHE_GET_ERROR.getErrorCode(), "getDictContent失败！", e);
		} finally {
			if (jedis != null) {
				jedis.disconnect();
			}
		}

		if (logger.isDebugEnabled()) {
			logger.info("DictServiceImpl getDictContent dict:" + dict);
		}

		if (dict == null) {
			return new ArrayList<ApDictDetailSmall>();
		}

		Gson gson = new Gson();

		List<ApDictDetailSmall> listBack = gson.fromJson(dict, new TypeToken<List<ApDictDetailSmall>>() {
		}.getType());

		return listBack;
	}

	@Override
	public void deleteDict(String dictCode) throws Exception {
		if (logger.isDebugEnabled()) {
			logger.info("@@@@@@@@@@" + this.getClass() + ":deleteDict===dictCode:" + dictCode);
		}

		final String installKey = CacheConsts.DICT + dictCode;// 组装带前缀的Key

		if (dictCode == null || "".equals(dictCode.trim())) {
			if (logger.isDebugEnabled()) {
				logger.info(
						"@@@@@@@@@@" + this.getClass() + ":deleteDict===dictCode:" + dictCode + "dictCode is empty!");
			}
			throw new CoreException(OpasError.NULL_PARAM_ERROR.getErrorCode(), "dictCode is empty!");
		}
		Jedis jedis = null;
		try {
			jedis = JedisBufferUtil.getJedisInstance();
			jedis.del(installKey);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);

			throw new CoreException(OpasError.CACHE_REMOVE_ERROR.getErrorCode(), "deleteDict失败！", e);
		} finally {
			if (jedis != null) {
				jedis.disconnect();
			}
		}
	}

	@Override
	public void deleteAllDict(List<String> dictCodes) {
		for (String dictCode : dictCodes) {
			try {
				deleteDict(dictCode);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		}
	}

	@Override
	public Map<String, String> getDictMap(String dictCodes) throws Exception {
		Map<String, String> returnMap = new HashMap<String, String>();
		if (StringUtils.isBlank(dictCodes)) {
			return returnMap;
		}
		Gson gson = new Gson();

		Jedis jedis = null;
		try {
			jedis = JedisBufferUtil.getJedisInstance();

			String[] array = dictCodes.split(",");
			String dictCode = null;
			String dictsJson = null;
			String installKey = null;

			List<ApDictDetailSmall> list = null;
			for (int i = 0; i < array.length; i++) {
				dictCode = array[i]; // 业务字典编号

				if ("undefined".equals(dictCode)) {
					continue;
				}

				installKey = CacheConsts.DICT + dictCode; // 缓存key

				// 读取缓存信息
				dictsJson = jedis.get(installKey);

				if (StringUtils.isBlank(dictsJson)) {
					// 如果缓存中不存在，则需要重新从数据库中获取并加入缓存

					list = getApDictDetailService().queryDictDetailByCode(dictCode);

					dictsJson = gson.toJson(list);

					Pipeline pipe = jedis.pipelined();
					pipe.set(installKey, dictsJson);
					pipe.sync();
				}
				returnMap.put(dictCode, dictsJson);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new CoreException(OpasError.CACHE_GET_ERROR.getErrorCode(), "getDictContent失败！", e);
		} finally {
			if (jedis != null) {
				jedis.disconnect();
			}
		}

		return returnMap;
	}

	@Override
	public boolean addDictContent(String dictCode, List<ApDictDetailSmall> list) throws Exception {
		if (logger.isDebugEnabled()) {
			logger.info("@@@@@@@@@@" + this.getClass() + ":addDictContent===dictCode:" + dictCode + ",===list:"
					+ list.toString());
		}

		// 校验dictCode跟list不为空
		if (dictCode == null || "".equals(dictCode.trim()) || list == null || list.isEmpty()) {

			if (logger.isDebugEnabled()) {
				logger.info("@@@@@@@@@@" + this.getClass() + ":addDictContent===dictCode:" + dictCode + ",===list:"
						+ list.toString() + "dictCode or list is null");
			}

			throw new CoreException(OpasError.NULL_PARAM_ERROR.getErrorCode(), "dictCode or list is null");
		}

		final String installKey = CacheConsts.DICT + dictCode;// 组装带前缀的Key

		Gson gson = new Gson();

		final String jsonStr = gson.toJson(list);
		Jedis jedis = null;
		try {
			jedis = JedisBufferUtil.getJedisInstance();

			Pipeline pipe = jedis.pipelined();
			pipe.set(installKey, jsonStr);
			pipe.sync();

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new CoreException(OpasError.CACHE_PUT_ERROR.getErrorCode(), "addDictContent添加失败！", e);
		} finally {
			if (jedis != null) {
				jedis.disconnect();
			}
		}

		return true;
	}

	public ApDictDetailService getApDictDetailService() {
		return apDictDetailService;
	}

	public void setApDictDetailService(ApDictDetailService apDictDetailService) {
		this.apDictDetailService = apDictDetailService;
	}

}
