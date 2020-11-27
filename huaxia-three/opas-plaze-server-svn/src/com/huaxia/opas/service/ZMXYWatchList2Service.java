package com.huaxia.opas.service;

import java.util.List;

import com.huaxia.opas.domain.ZMXYWatchList2;

/**
 * 行业关注名单2.0版服务接口
 * 
 * @author zhiguo.li
 *
 */
public interface ZMXYWatchList2Service {

	/**
	 * 保存行业关注名单2.0版信息
	 * 
	 * @param watchList2
	 *            行业关注名单2.0版对象
	 * @return
	 */
	int save(ZMXYWatchList2 watchList2);

	/**
	 * 批量保存行业关注名单2.0版信息
	 * 
	 * @param watchList2List
	 * @return
	 */
	int saveBatch(List<ZMXYWatchList2> watchList2List);
	int queryCountZMXYWatchList2ByAppId(String appId);

}
