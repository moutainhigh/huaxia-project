package com.huaxia.opas.service;

import java.util.List;

import com.huaxia.opas.domain.CRMSpIfsPyrl;

/**
 * 代发工资服务接口
 * 
 * @author zhiguo.li
 *
 */
public interface CRMSpIfsPyrlService {

	/**
	 * 保存代发工资信息
	 * 
	 * @param spIfsPyrl
	 *            代发工资对象
	 * @return
	 */
	int save(CRMSpIfsPyrl spIfsPyrl);
	
	/**
	 * 批量保存代发工资信息
	 * 
	 * @param spIfsPyrlList
	 *            代发工资信息对象列表
	 * @return
	 */
	int saveBatch(List<CRMSpIfsPyrl> spIfsPyrlList);

}
