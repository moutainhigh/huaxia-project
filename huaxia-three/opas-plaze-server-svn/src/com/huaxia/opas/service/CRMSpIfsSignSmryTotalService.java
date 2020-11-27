package com.huaxia.opas.service;

import java.util.List;

import com.huaxia.opas.domain.CRMSpIfsSignSmryTotal;

/**
 * 签约汇总服务接口
 * 
 * @author zhiguo.li
 *
 */
public interface CRMSpIfsSignSmryTotalService {

	/**
	 * 保存签约汇总信息
	 * 
	 * @param spIfsSignSmryTotal
	 *            签约汇总对象
	 * @return
	 */
	int save(CRMSpIfsSignSmryTotal spIfsSignSmryTotal);

	/**
	 * 批量保存签约汇总信息
	 * 
	 * @param spIfsPyrlList
	 *            签约汇总信息对象列表
	 * @return
	 */
	int saveBatch(List<CRMSpIfsSignSmryTotal> spIfsSignSmryTotalList);

}
