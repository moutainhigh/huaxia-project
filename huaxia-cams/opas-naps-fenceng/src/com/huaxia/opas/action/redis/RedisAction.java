package com.huaxia.opas.action.redis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.huateng.neofp.core.Action;
import com.huateng.neofp.core.Context;
import com.huaxia.opas.cache.service.IDictService;
import com.huaxia.opas.cache.service.ITokenService;
import com.huaxia.opas.domain.dict.ApDictDetailSmall;

/**
 * 缓存查询
 * 
 * @author zhiguo.li
 *
 */
public class RedisAction implements Action {

	private static Logger logger = LoggerFactory.getLogger(RedisAction.class);

	@Resource(name = "tokenService")
	private ITokenService tokenService;

	@Resource(name = "dictService")
	private IDictService dictService;

	/**
	 * 获取缓存值
	 * 
	 * @param ctx
	 * @throws Exception
	 */
	public void queryRedisValue(Context ctx) throws Exception {
		String redisType = String.valueOf(ctx.getDataMap().get("redisType"));
		String rediskey = String.valueOf(ctx.getDataMap().get("redisKey"));

		String redisValue = null;
		List<ApDictDetailSmall> dictList = new ArrayList<ApDictDetailSmall>();
		Map<String, Object> dataMap = new HashMap<String, Object>();

		if ("TOKEN".equals(redisType)) {
			redisValue = tokenService.getToken(rediskey);
		} else if ("DICT".equals(redisType)) {
			dictList = dictService.getDictContent(rediskey);
			Gson gson = new Gson();
			redisValue = gson.toJson(dictList);
			redisValue = redisValue.replace("[{", "[\r\n    {")
					.replace("},", "},\r\n    ").replace("}]", "}\r\n]");
		}

		dataMap.put("redisValue", redisValue);
		ctx.setDataMap(dataMap);

	}

	@Override
	public String toString() {
		return "RedisAction [tokenService=" + tokenService + ", dictService="
				+ dictService + "]";
	}

}
