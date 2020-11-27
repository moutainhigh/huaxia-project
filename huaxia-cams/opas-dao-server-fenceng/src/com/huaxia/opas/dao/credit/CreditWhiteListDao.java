package com.huaxia.opas.dao.credit;

import java.util.List;
import java.util.Map;
import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.sysparm.CreditWhiteList;
/**
 * 征信白名单复核dao层接口
 * @author susha 2017/03/20
 *
 */
public interface CreditWhiteListDao {
	//查询数量方法
	int queryCount(CreditWhiteList creditWhiteList) throws CoreException;
	//查询列表方法
	//List<CreditWhiteList> queryList(CreditWhiteList creditWhiteList, int curNum, int pageNum) throws CoreException;
	List<Map<Object, Object>> queryList(CreditWhiteList creditWhiteList, int curNum, int pageNum) throws CoreException;
	//批量删除(含单条)
	int deleteList(List list) throws CoreException;
	//批量插入
	int insertList(List list) throws CoreException;
	//单条插入
	int save(CreditWhiteList t) throws CoreException;
	//完成工作量统计
	int queryFinishedCount(Map<String, Object> map) throws CoreException;
	//未完成工作量统计
	int queryUnfinishedCount(Map<String, Object> map) throws CoreException;
	//提报到其他的工作量统计
	int querySub2othersCount(Map<String, Object> map) throws CoreException;
	//单条插入后更新中间表状态
	int update(CreditWhiteList t) throws CoreException;
	//多条插入后更新中间表状态
	int updateList(List list) throws CoreException;
	//拒绝添加
	int refuse(CreditWhiteList creditWhiteList) throws CoreException;
	/**
	 *@Title:insertCreditWhiteCenter
	 *@Description:bzk征信库添加 （标准卡征信信息名单表（中间表））
	 *@param creditWhiteList
	 *@author: gaohui
	 *@Date:2017年5月4日下午8:37:52
	 */
	public void insertCreditWhiteCenter(CreditWhiteList creditWhiteList);
	/**
	 * 白名单征信库查询
	 * @param creditWhiteList
	 * @return
	 */
	int queryCreditWhiteCountInfo(CreditWhiteList creditWhiteList);
	List<CreditWhiteList> queryCreditWhiteListInfo(CreditWhiteList creditWhiteList, int curNum, int pageNum);
}
