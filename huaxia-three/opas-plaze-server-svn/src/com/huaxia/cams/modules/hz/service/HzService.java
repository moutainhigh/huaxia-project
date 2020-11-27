package com.huaxia.cams.modules.hz.service;

import com.huaxia.cams.modules.hz.domain.HzCollection;

/**
 * 
 * @author dingguofeng
 * 杭州区域数据service
 *
 */
public interface HzService {
	
	
	/**
	 * 将报文封装实体类插入数据表
	 */
	void saveHzCollection(HzCollection hzCollection);

}
