package com.huaxia.opas.dao.sysparm.impl;

import java.util.List;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.sysparm.InnerRiskInfoDao;
import com.huaxia.opas.domain.sysparm.InnerRiskInfo;
/**
 * 系统参数--内部风险信息名单dao层实现类的相关方法
 * 
 * @author luzhen.ou
 * 
 * @since 2017-3-16
 * 
 * @version 1.0
 */
public class InnerRiskInfoDaoImpl extends AbstractDAO implements InnerRiskInfoDao {

	private static final long serialVersionUID = 1062308779223148331L;

	private static final String NAMESPACES = "InnerRiskInfo.";

	@Override
	public int updateInnerRiskInfo(InnerRiskInfo innerRiskInfo) throws CoreException {
		return getSqlMap().update(NAMESPACES + "updateInnerRiskInfo", innerRiskInfo);
	}

	@Override
	public int queryInnerRiskInfoCount(InnerRiskInfo innerRiskInfo) throws CoreException{
		return getSqlMap().queryForObject(NAMESPACES + "queryInnerRiskInfoCount", innerRiskInfo);
	}

	@Override
	public List<InnerRiskInfo> queryInnerRiskInfoList(InnerRiskInfo innerRiskInfo, int curNum, int pageNum) throws CoreException{
		return getSqlMap().queryForList(NAMESPACES + "queryInnerRiskInfoList", innerRiskInfo, curNum, pageNum);
	}

	@Override
	public int deleteInnerRiskInfo(InnerRiskInfo innerRiskInfo) throws CoreException{
		return getSqlMap().delete(NAMESPACES + "deleteInnerRiskInfo", innerRiskInfo);
	}

	@Override
	public InnerRiskInfo queryInnerRiskInfo(String autoId) throws CoreException {
		return (InnerRiskInfo)getSqlMap().queryForObject(NAMESPACES + "queryInnerRiskInfo", autoId);
	}

	@Override
	public int updateInnerRiskInfoActive(InnerRiskInfo innerRiskInfo) throws CoreException {
		return getSqlMap().update(NAMESPACES + "updateInnerRiskInfoActive", innerRiskInfo);
	}

	@Override
	public int queryInnerRiskInfoHistoryCount(InnerRiskInfo innerRiskInfo) throws CoreException {
		return getSqlMap().queryForObject(NAMESPACES + "queryInnerRiskInfoHistoryCount", innerRiskInfo);
	}

	@Override
	public List<InnerRiskInfo> queryInnerRiskInfoHistoryList(InnerRiskInfo innerRiskInfo, int curNum, int pageNum){
		return getSqlMap().queryForList(NAMESPACES + "queryInnerRiskInfoHistoryList", innerRiskInfo, curNum, pageNum);
	}
	
	@Override
	public int insertInnerRiskInfo(InnerRiskInfo innerRiskInfo) throws CoreException {
		return getSqlMap().insert(NAMESPACES + "insertInnerRiskInfo", innerRiskInfo);
	}

	@Override
	public int insertInnerRiskInfoList(List<InnerRiskInfo> list) throws CoreException {
		return getSqlMap().insert(NAMESPACES + "insertInnerRiskInfoList", list);
	}
	
	@Override
	public int insertInnerRiskInfoHistory(InnerRiskInfo innerRiskInfo) throws CoreException {
		return getSqlMap().insert(NAMESPACES + "insertInnerRiskInfoHistory", innerRiskInfo);
	}

	@Override
	public int insertInnerRiskInfoHistoryList(List<InnerRiskInfo> resutIdentitylist)  throws CoreException{
		return getSqlMap().insert(NAMESPACES + "insertInnerRiskInfoHistoryList", resutIdentitylist);
	}
	
}
