package com.huaxia.opas.dao.sysparm.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.sysparm.SubReportDao;
import com.huaxia.opas.domain.sysparm.SubReport;

public class SubReportDaoImpl extends AbstractDAO implements SubReportDao {

	private static final long serialVersionUID = 495518709212054335L;
	
	private static final String NAMESPACES = "SubReport.";
	
	//提报未完成数量查询
	@Override
	public int queryCount(SubReport subReport) throws CoreException {
		return getSqlMap().queryForObject(NAMESPACES + "queryCount", subReport);
	}
	//提报已完成数量查询
	@Override
	public int queryCount1(SubReport subReport) throws CoreException {
		return getSqlMap().queryForObject(NAMESPACES + "queryCount1", subReport);
	}
	//提报未完成分页列表
	@Override
	public List<SubReport> queryUnfinishedList(SubReport subReport, int curNum, int pageNum) throws CoreException {
		return getSqlMap().queryForList(NAMESPACES + "queryUnfinishedList", subReport, curNum, pageNum);
	}
	//提报已完成分页列表
	@Override
	public List<SubReport> queryFinishedList(SubReport subReport, int curNum, int pageNum) throws CoreException {
		return getSqlMap().queryForList(NAMESPACES + "queryFinishedList", subReport, curNum, pageNum);
	}
	//提报至已完成队列
	@Override
	public int toFinished(SubReport subReport) throws CoreException {
		return getSqlMap().update(NAMESPACES + "toFinished", subReport);
	}
	//提报到征信系统
	@Override
	public int toCredit(SubReport subReport) throws CoreException {
		return getSqlMap().update(NAMESPACES + "toCredit", subReport);
	}
	//全部提报到征信系统
	@Override
	public int commitAll(SubReport subReport) throws CoreException {
		return getSqlMap().update(NAMESPACES + "commitAll",subReport);
	}
	//回收提报到未完成队列
	@Override
	public int toUnfinished(String appId) throws CoreException {
		return getSqlMap().update(NAMESPACES + "toUnfinished", appId);
	}
	//查询提报原因
	@Override
	public SubReport querySubReason(String appId) throws CoreException {
		return getSqlMap().queryForObject(NAMESPACES + "querySubReason", appId);
	}
	@Override
	public int queryDayCount(Map<String, Object> map) throws CoreException {
		return getSqlMap().queryForObject(NAMESPACES + "queryDayCount", map);
	}
	@Override
	public Date queryDate(Date start) throws CoreException {
		return getSqlMap().queryForObject(NAMESPACES + "queryDate", start);
	}
	@Override
	public int queryMonthCount(Map<String, Object> map) throws CoreException {
		return getSqlMap().queryForObject(NAMESPACES + "queryMonthCount", map);
	}
	@Override
	public void insertOpasSubmittypeInfo(SubReport subReport) {
		getSqlMap().insert(NAMESPACES+"insertOpasSubmittypeInfo",subReport);
	}
	/**
	 * 单个更新申请件分配表可见状态
	 */
	public int updateApplyAllot(SubReport t) {
		return getSqlMap().update(NAMESPACES+"updateApplyAllot",t);
	}
	/**
	 * 查询提交到征信的数据列表
	 */
	public List<SubReport> selectCommitAll(SubReport subReport) throws CoreException {
		return getSqlMap().queryForList(NAMESPACES+"selectCommitAll",subReport);
	}
	/**
	 * 批量更新申请件分配表可见状态
	 */
	public int updateApplyAllotList(List<SubReport> list) throws CoreException {
		return getSqlMap().update(NAMESPACES+"updateApplyAllotList",list);
	}
	@Override
	public void updateSubReportByAppId(SubReport subReport) {
		 getSqlMap().update(NAMESPACES + "updateSubReportByAppId", subReport);
	}
	@Override
	public int querySubReportByAppId(String appId) {
		return getSqlMap().queryForObject(NAMESPACES+"querySubReportByAppId",appId);
	}
	@Override
	public String queryCurrNode(String appId) {
		return getSqlMap().queryForObject(NAMESPACES+"queryCurrNode",appId);
		
	}
	@Override
	public void insertOpasSubmittypeInfoHis(SubReport subReport) {
		getSqlMap().insert(NAMESPACES+"insertOpasSubmittypeInfoHis",subReport);
	}
	@Override
	public String queryDelStatus(String appId) {
		return getSqlMap().queryForObject(NAMESPACES+"queryDelStatus", appId);
	}
	@Override
	public int queryUnSubReportByAppId(String appId) {
		return getSqlMap().queryForObject(NAMESPACES+"queryUnSubReportByAppId",appId);
	}
	@Override
	public void deleteOpasSubmittypeInfoByAppId(String appId) {
		getSqlMap().update(NAMESPACES + "deleteOpasSubmittypeInfoByAppId", appId);
	}
}
