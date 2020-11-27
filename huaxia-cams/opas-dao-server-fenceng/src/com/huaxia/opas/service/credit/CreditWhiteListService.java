package com.huaxia.opas.service.credit;

import java.util.List;
import java.util.Map;
import com.huateng.neofp.core.Context;
import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.sysparm.CreditWhiteList;
import com.huaxia.opas.service.BaseService;
/**
 * 征信白名单复核业务层接口
 * @author susha 2017/03/20
 *
 */
public interface CreditWhiteListService extends BaseService<CreditWhiteList>{
	//查询数量方法
	int queryCount(CreditWhiteList creditWhiteList) throws CoreException;
	//查询列表方法
	//List<CreditWhiteList> queryList(CreditWhiteList creditWhiteList, int curNum, int pageNum) throws CoreException;
	List<Map<Object, Object>> queryList(CreditWhiteList creditWhiteList, int curNum, int pageNum) throws CoreException;
	//批量删除(含单条)
	void deleteList(List list) throws CoreException;
	//批量插入
	void insertList(List list) throws CoreException;
	//工作量统计
	Map<String, Object> queryWorkload(String startTime, String endTime,String crediter) throws CoreException;
	int refuse(CreditWhiteList creditWhiteList) throws CoreException;
	/**
	 *@Title:saveOrUpdateCreditWhiteCenter
	 *@Description:bzk征信库添加 （标准卡征信信息名单表（中间表））
	 *@param creditWhiteList
	 *@author: gaohui
	 *@Date:2017年5月4日下午8:35:36
	 */
	public void saveOrUpdateCreditWhiteCenter(CreditWhiteList creditWhiteList);
	/**
	 * 白名单征信库查询
	 * @param creditWhiteList
	 * @return
	 */
	int queryCreditWhiteCountInfo(CreditWhiteList creditWhiteList);
	List<CreditWhiteList> queryCreditWhiteListInfo(CreditWhiteList creditWhiteList, int curNum, int pageNum);
}
