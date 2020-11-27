package com.huaxia.opas.dao.sysparm;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.sysparm.SubReport;

public interface SubReportDao {

	int queryCount(SubReport subReport) throws CoreException;
	
	int queryCount1(SubReport subReport) throws CoreException;

	List<SubReport> queryUnfinishedList(SubReport subReport, int curNum, int pageNum) throws CoreException;

	List<SubReport> queryFinishedList(SubReport subReport, int curNum, int pageNum) throws CoreException;
	
	int toFinished(SubReport subReport) throws CoreException;

	int toCredit(SubReport subReportAccount) throws CoreException;

	int commitAll(SubReport subReport) throws CoreException;

	int toUnfinished(String appId) throws CoreException;

	SubReport querySubReason(String appId) throws CoreException;

	int queryDayCount(Map<String, Object> map) throws CoreException;

	Date queryDate(Date start) throws CoreException;

	int queryMonthCount(Map<String, Object> map) throws CoreException;
	/*****************************
	 *@describe:添加征信提报其他信息表
	 *@param subReport
	 *@date：2017-03-29
	 *@author:xiaoJianFeng
	 *****************************/
	void insertOpasSubmittypeInfo(SubReport subReport);
	/*****************************
	 *@describe:添加征信提报其他信息表
	 *@param subReport
	 *@date：2017-03-29
	 *@author:xiaoJianFeng
	 *****************************/
	void deleteOpasSubmittypeInfoByAppId(String appId);
	/*****************************
	 *@describe:添加征信提报其他信息表
	 *@param subReport
	 *@date：2017-03-29
	 *@author:xiaoJianFeng
	 *****************************/
	void insertOpasSubmittypeInfoHis(SubReport subReport);
	/**
	 * 单条更新申请件分配表
	 * @param t
	 * @return
	 */
	int updateApplyAllot(SubReport t);
	/**
	 * 查询提交到征信的数据
	 * @param subReport
	 * @return
	 */
	List<SubReport> selectCommitAll(SubReport subReport) throws CoreException;
	/**
	 * 批量更新申请件分配表
	 * @param list
	 * @return
	 * @throws CoreException
	 */
	int updateApplyAllotList(List<SubReport> list) throws CoreException;
	/**
	 *@Title:updateSubReportByAppId
	 *@Description:根据appId修改 OPAS_SUBMITTYPE_INFO不为null 且不为空的 列 
	 *@param subReport
	 *@author: gaohui
	 *@Date:2017年5月17日上午11:15:36
	 */
	public void updateSubReportByAppId(SubReport subReport);
	/**
	 *@Title:updateSubReportByAppId
	 *@Description:根据appId查询是否存在该申请件
	 *@param appId
	 *@author: susha
	 *@Date:2017年5月17日上午11:15:36
	 */
	int querySubReportByAppId(String appId);
	/**
	 *@Title:updateSubReportByAppId
	 *@Description:根据appId查询是否存在该申请件
	 *@param appId
	 *@author: susha
	 *@Date:2017年5月17日上午11:15:36
	 */
	int queryUnSubReportByAppId(String appId);
	/**
	 * 查询节点
	 * @param appId
	 */
	String queryCurrNode(String appId);
	/**
	 * 查询状态
	 * @param appId
	 */
	String queryDelStatus(String appId);


}
