package com.huaxia.opas.service;

import com.huaxia.opas.domain.TaskRequestInfo;

/**
 * 任务查询请求服务接口
 * 
 * @author zhiguo.li
 *
 */
public interface TaskRequestInfoService {

	int save(TaskRequestInfo taskRequestInfo);
	
	int update(TaskRequestInfo taskRequestInfo);

}
