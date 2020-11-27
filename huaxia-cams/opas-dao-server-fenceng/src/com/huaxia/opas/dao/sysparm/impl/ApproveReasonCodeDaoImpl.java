package com.huaxia.opas.dao.sysparm.impl;

import java.util.List;
import java.util.Map;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.domain.sysparm.ApproveReasonCode;
import com.huaxia.opas.dao.sysparm.ApproveReasonCodeDao;

/**
 * 审批原因码DAO层实现类
 * @author liudi
 * @since 2017-03-06
 * @version 1.0
 */
public class ApproveReasonCodeDaoImpl extends AbstractDAO implements ApproveReasonCodeDao{


	/**
	 * 
	 */
	private static final long serialVersionUID = -4863123787314935168L;
	/**
	 * 
	 */
	private static final String NAMESPACES = "ApproveReasonCode.";
	//添加状态为启用
	@Override
	public int saveApproveReasonCodeStart(ApproveReasonCode approveReasonCode) throws CoreException {
		return getSqlMap().insert(NAMESPACES + "insertApproveReasonCode1", approveReasonCode);
	}
	//添加状态为禁用
	@Override
	public int saveApproveReasonCodeEnd(ApproveReasonCode approveReasonCode) throws CoreException {
		return getSqlMap().insert(NAMESPACES + "insertApproveReasonCode2", approveReasonCode);
	}
	//更新
	@Override
	public int updateApproveReasonCode(ApproveReasonCode approveReasonCode) throws CoreException {
		return getSqlMap().update(NAMESPACES + "updateApproveReasonCode", approveReasonCode);
	}
	//删除
	@Override
	public int deleteApproveReasonCode(String autoId) throws CoreException {
		return getSqlMap().delete(NAMESPACES + "deleteApproveReasonCodeById", autoId);
	}
	//审批原因码查询件数
	@Override
	public int queryApproveReasonCodeCount(ApproveReasonCode approveReasonCode) throws CoreException {
		return getSqlMap().queryForObject(NAMESPACES + "queryApproveReasonCodeCount", approveReasonCode);
	}
	//审批原因码查询详细
	@Override
	public List<ApproveReasonCode> queryApproveReasonCode(ApproveReasonCode approveReasonCode,int curNum,int pageNum) throws CoreException {
		return getSqlMap().queryForList(NAMESPACES + "queryApproveReasonCode1", approveReasonCode, curNum, pageNum);
	}
	//点击查询按钮，实现条件查询详细，排序功能
	@Override
	public List<ApproveReasonCode> queryApproveReasonCode(Map<String,Object> params,int curNum,int pageNum) throws CoreException {
		return getSqlMap().queryForList(NAMESPACES + "queryApproveReasonCode2", params, curNum, pageNum);
	}
	//审批原因编码和银联原因代码组合是否重复
	@Override
	public ApproveReasonCode queryApproveReasonCode(ApproveReasonCode approveReasonCode) throws CoreException {
		return getSqlMap().queryForObject(NAMESPACES + "queryApproveReasonCode3", approveReasonCode);
	}
	@Override
	public List<ApproveReasonCode> queryAllApproveReason(Map<String,String> map) throws CoreException {
		return getSqlMap().queryForList(NAMESPACES + "queryAllApproveReason", map);
	}
	@Override
	public String queryAllApproveReasonCodeStatus(String autoId) {
		return getSqlMap().queryForObject(NAMESPACES + "queryAllApproveReasonCodeStatus", autoId);
	}
	@Override
	public int updateApproveReasonCodeWithoutStatus(ApproveReasonCode approveReasonCode) {
		return getSqlMap().update(NAMESPACES + "updateApproveReasonCodeWithoutStatus", approveReasonCode);
	}
}
