package com.huaxia.opas.dao.decision.impl;

import java.util.List;
import java.util.Map;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.decision.BizApprovalOperateexcDao;
import com.huaxia.opas.domain.input.BizApprovalOperateexc;

public class BizApprovalOperateexcDaoImpl extends AbstractDAO implements BizApprovalOperateexcDao {
	
	private static final long serialVersionUID = 2757582418545513971L;

	private static final String NAMESPACES = "BizApprovalOperateexc.";

	@Override
	public int deleteByPrimaryKey(String autoId) {
		return getSqlMap().delete(NAMESPACES + "deleteByPrimaryKey", autoId);
	}

	@Override
	public int insert(BizApprovalOperateexc record) {
		return getSqlMap().insert(NAMESPACES + "insert", record);
	}

	@Override
	public int insertSelective(BizApprovalOperateexc record) {
		return getSqlMap().insert(NAMESPACES + "insertSelective", record);
	}

	@Override
	public BizApprovalOperateexc selectByPrimaryKey(String autoId) {
		return (BizApprovalOperateexc) (getSqlMap().queryForObject(NAMESPACES + "selectByPrimaryKey", autoId));
	}
	@Override
	public BizApprovalOperateexc selectByUserCode(String userCode) {
		return (BizApprovalOperateexc) (getSqlMap().queryForObject(NAMESPACES + "selectByUserCode", userCode));
	}
	@Override
	public int updateByPrimaryKeySelective(BizApprovalOperateexc record) {
		return getSqlMap().update(NAMESPACES + "updateByPrimaryKeySelective", record);
	}

	@Override
	public int updateByPrimaryKey(BizApprovalOperateexc record) {
		return getSqlMap().update(NAMESPACES + "updateByPrimaryKey", record);
	}

	@Override
	public int queryExceptionDateLong(String operateCode) throws CoreException {
		return  getSqlMap().queryForObject(NAMESPACES + "queryExceptionDateLong",operateCode);
	}

	@Override
	public List<BizApprovalOperateexc> memberJobEcxceptionDetail(String operateCode){
		return getSqlMap().queryForList(NAMESPACES + "memberJobEcxceptionDetail",operateCode);
	}

	@Override
	public List<BizApprovalOperateexc> selectJobEcxceptionDetail(
			Map<String, Object> map) {
		return getSqlMap().queryForList(NAMESPACES + "selectJobEcxceptionDetail",map);
	}

	@Override
	public Map<String, Object> selectMemberJobEcxception(Map<String, Object> map) {
		return getSqlMap().queryForObject(NAMESPACES + "selectMemberJobEcxception",map);
	}

	@Override
	public List<Map<String, Object>> selectmemberJobEcxceptionDetailDataMap(
			Map<String, Object> dataMap) {
		return getSqlMap().queryForList(NAMESPACES + "selectmemberJobEcxceptionDetailDataMap",dataMap);
	}

}