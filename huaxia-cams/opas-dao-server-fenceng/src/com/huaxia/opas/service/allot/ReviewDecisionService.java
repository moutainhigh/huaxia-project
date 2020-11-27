package com.huaxia.opas.service.allot;

import java.util.List;
import java.util.Map;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.decision.OpasReviewDecisionResult;
/**
 * 录入批量fico结果入库
 * @author  wangdebin
 *
 */
public interface ReviewDecisionService {
	
	int countBatchCode(String appId) throws CoreException;

	int saveReviewDecisionResult(OpasReviewDecisionResult ordr) throws CoreException;

	int updateReviewDecision(OpasReviewDecisionResult ordr) throws CoreException;
	
	int queryCount(String appId) throws CoreException ;
	
	int saveApprovaInfoSupp(Map map) throws CoreException;
	
	int updateByAppId(Map map) throws CoreException;
}
