package com.huaxia.opas.cache.service;

import java.util.List;
import java.util.Map;

import com.huaxia.opas.domain.dict.ApDictDetailSmall;

/**
 * 业务字典接口
 * 
 * @author zhiguo.li
 *
 */
public interface IDictService {
	/**
	 * 新增业务字段
	 * 
	 * @param map
	 */
	public boolean addDictContent(String dictCode, List<ApDictDetailSmall> list)
			throws Exception;

	/**
	 * 获取业务字典内容
	 * 
	 * @return
	 */
	public List<ApDictDetailSmall> getDictContent(String dictCode)
			throws Exception;

	/***
	 * 删除业务字典
	 */
	public void deleteDict(String dictCode) throws Exception;

	/**
	 * 删除所有业务字典
	 * 
	 */
	public void deleteAllDict(List<String> dictCodes);

	/**
	 * 查询数据字典MAP
	 * 
	 * @param dictCode
	 * @return
	 * @throws Exception
	 */
	public Map<String, String> getDictMap(String dictCodes) throws Exception;

}
