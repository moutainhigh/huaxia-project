package com.huaxia.opas.dao.allot.impl;

import java.util.Map;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.allot.ReviewDecisionDao;
import com.huaxia.opas.domain.audit.ApprovaInfoSupp;
import com.huaxia.opas.domain.decision.OpasReviewDecisionResult;
/**
 * 标准卡 录入环节批量标识
 * @author  wangdebin 
 *
 */
public  class ReviewDecisionDaoImpl  extends AbstractDAO implements ReviewDecisionDao  {
	

	private static final long serialVersionUID = 409572909548904678L;
	
	private static final String NAMESPACES = "OpasReviewDecisionResult.";
	
	private static final String NAMESPACES2 = "InfoCollect.";
	//wangdebin 录入环节批量
	@Override
	public int countBatchCode(String appId) throws CoreException {
		return getSqlMap().queryForObject(NAMESPACES + "countBatchCode", appId);
	}
	
	@Override
	public int insertBatchCode(OpasReviewDecisionResult ordr) throws CoreException {
		return getSqlMap().insert(NAMESPACES + "insertOpasReviewDecisionResult", ordr);
	}

	@Override
	public int updateBatchCode(OpasReviewDecisionResult ordr) throws CoreException {
		return getSqlMap().update(NAMESPACES + "updateBatchCode", ordr);
	}
	
	//行职业代码查询   插入   更新   
	
	@Override
	public int selectCount(String appId) throws CoreException {
		return getSqlMap().queryForObject(NAMESPACES2 + "selectCount", appId);
	}
	
	@Override
	public int insertApprovaInfoSupp(Map map) throws CoreException {
		return getSqlMap().insert(NAMESPACES2 + "insertApprovaInfoSupp", map);
	}

	@Override
	public int updateByAppId(Map map) throws CoreException {
		return getSqlMap().update(NAMESPACES2 + "updateByAppId", map);
	}
}
