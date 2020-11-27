package com.huaxia.opas.dao.sysparm.impl;

import java.util.List;
import java.util.Map;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.domain.sysparm.ApproveZipcode;
import com.huaxia.opas.dao.sysparm.ApproveZipcodeDao;

/**
 * 邮编区号维护DAO层实现类
 * @author liudi
 * @since 2017-03-16
 * @version 1.0
 */
public class ApproveZipcodeDaoImpl extends AbstractDAO implements ApproveZipcodeDao{


	/**
	 * 
	 */
	private static final long serialVersionUID = 4584236540646304793L;
	/**
	 * 
	 */
	private static final String NAMESPACES = "ApproveZipcode.";
	//添加
	@Override
	public int saveApproveZipcode(ApproveZipcode approveZipcode) throws CoreException {
		return getSqlMap().insert(NAMESPACES + "insertApproveZipcode", approveZipcode);
	}
	//更新
	@Override
	public int updateApproveZipcode(ApproveZipcode approveZipcode) throws CoreException {
		return getSqlMap().update(NAMESPACES + "updateApproveZipcode", approveZipcode);
	}
	//删除
	@Override
	public int deleteApproveZipcode(String autoId) throws CoreException {
		return getSqlMap().delete(NAMESPACES + "deleteApproveZipcodeById", autoId);
	}
	//邮编区号件数查询
	@Override
	public int queryApproveZipcodeCount(ApproveZipcode approveZipcode) throws CoreException {
		return getSqlMap().queryForObject(NAMESPACES + "queryApproveZipcodeCount", approveZipcode);
	}
	//邮编区号页面初始化详细查询
	@Override
	public List<ApproveZipcode> queryApproveZipcode(ApproveZipcode approveZipcode,int curNum,int pageNum) throws CoreException {
		return getSqlMap().queryForList(NAMESPACES + "queryApproveZipcode1", approveZipcode, curNum, pageNum);
	}
	//邮编区号页面点击查询后实现查询和排序
	@Override
	public List<ApproveZipcode> queryApproveZipcode(Map<String,Object> params,int curNum,int pageNum) throws CoreException {
		return getSqlMap().queryForList(NAMESPACES + "queryApproveZipcode2", params, curNum, pageNum);
	}
	@Override
	public List<ApproveZipcode> queryApproveZipcodeByTelno(Map map) throws CoreException {
		return getSqlMap().queryForList(NAMESPACES + "queryApproveZipcodeByTelno", map);
	}
	//查看区号时候存在
	@Override
	public int queryTelno(Map<String,Object> map) {
		return getSqlMap().queryForObject(NAMESPACES + "queryTelno", map);
	}
	@Override
	public int updateApproveZipcodeWithoutAllStatus(ApproveZipcode approveZipcode) {
		return getSqlMap().update(NAMESPACES + "updateApproveZipcodeWithoutAllStatus", approveZipcode);
	}
	@Override
	public int updateApproveZipcodeWithoutStatus(ApproveZipcode approveZipcode) {
		return getSqlMap().update(NAMESPACES + "updateApproveZipcodeWithoutStatus", approveZipcode);
	}
	@Override
	public int updateApproveZipcodeWithoutRemoteStatus(ApproveZipcode approveZipcode) {
		return getSqlMap().update(NAMESPACES + "updateApproveZipcodeWithoutRemoteStatus", approveZipcode);
	}
	@Override
	public ApproveZipcode selectStatus(String autoId) {
		return getSqlMap().queryForObject(NAMESPACES + "selectStatus", autoId);
	}
	@Override
	public void deleteApproveZipcodes(List<String> ids) {
		getSqlMap().delete(NAMESPACES + "deleteApproveZipcodes", ids);
	}
	@Override
	public void deleteAllApproveZipcodes() {
		getSqlMap().delete(NAMESPACES + "deleteAllApproveZipcodes");
	}
	@Override
	public int insertApproveZipcodeFromFile(List<ApproveZipcode> list) {
		return getSqlMap().insert(NAMESPACES + "insertApproveZipcodeFromFile", list);
	}

	@Override
	public int queryRepetition(Map<String, String> map) {
		return getSqlMap().queryForObject(NAMESPACES + "queryRepetition",map);
	}
}
