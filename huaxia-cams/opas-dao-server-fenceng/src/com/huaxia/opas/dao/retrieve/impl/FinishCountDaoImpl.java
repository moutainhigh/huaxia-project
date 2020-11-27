package com.huaxia.opas.dao.retrieve.impl;

import java.util.Map;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.retrieve.FinishCountDao;
import com.huaxia.opas.domain.decision.OpasReviewDecisionResult;

public class FinishCountDaoImpl  extends AbstractDAO implements FinishCountDao {

	private static final long serialVersionUID = 409572909548904678L;
	
	private static final String NAMESPACES = "OpasReviewDecisionResult.";

	@Override
	public int memberJobCompletCount(Map<String, Object> map){
		return sqlMap.queryForObject(NAMESPACES + "memberJobCompletCount", map);
	}
	
	//wangdebin 录入环节批量
	@Override
	public int countBatchCode(String appId) throws CoreException {
		return getSqlMap().queryForObject(NAMESPACES + "countBatchCode", appId);
	}
	
	@Override
	public int insertBatchCode(OpasReviewDecisionResult ordr) throws CoreException {
		return Integer.parseInt(String.valueOf(getSqlMap().insert(NAMESPACES + "insertOpasReviewDecisionResult", ordr))); 
	}

	@Override
	public int updateBatchCode(OpasReviewDecisionResult ordr) throws CoreException {
		return Integer.parseInt(String.valueOf(getSqlMap().update(NAMESPACES + "updateBatchCode", ordr))); 
	}
	@Override
	public int memberJobCompletCountCheckup(Map map) throws CoreException {
		return Integer.parseInt(String.valueOf(sqlMap.queryForObject(NAMESPACES + "memberJobCompletCountCheckup", map)));
	}

	@Override
	public int memberJobCompletCountHis(Map<String, Object> mapPar) {
		return Integer.parseInt(String.valueOf(sqlMap.queryForObject(NAMESPACES + "memberJobCompletCountHis", mapPar)));
	}

	@Override
	public int memberJobCompletCountApprove(Map<String, Object> paramMap) {
		return Integer.parseInt(String.valueOf(sqlMap.queryForObject(NAMESPACES + "memberJobCompletCountApprove", paramMap)));
	}

	@Override
	public int memberJobCheckup(Map<String, Object> map) {
		return Integer.parseInt(String.valueOf(sqlMap.queryForObject(NAMESPACES + "memberJobCheckup", map)));
	}
}
