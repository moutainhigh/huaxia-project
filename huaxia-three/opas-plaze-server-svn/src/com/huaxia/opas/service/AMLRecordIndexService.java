package com.huaxia.opas.service;

import com.huaxia.opas.domain.AMLRecordIndex;

/**
 * 路透主索引信息服务接口
 * 
 * @author zhiguo.li
 *
 */
public interface AMLRecordIndexService {

	/**
	 * 保存路透主索引信息
	 * 
	 * @param recordIndex
	 *            路透主索引信息对象
	 * @return
	 */
	int save(AMLRecordIndex recordIndex);

}
