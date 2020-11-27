package com.huaxia.opas.dao.retrieve;

import java.util.Map;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.decision.OpasReviewDecisionResult;

public interface FinishCountDao {

	public int memberJobCompletCount(Map<String, Object> map);
	
	public int memberJobCompletCountCheckup(Map map) throws CoreException;
	
	//wangdebin 录入环节批量标识
	
	int countBatchCode(String appId) throws CoreException ;
	
	int insertBatchCode(OpasReviewDecisionResult ordr) throws CoreException ;
	
	int updateBatchCode(OpasReviewDecisionResult ordr) throws CoreException ;

	public int memberJobCompletCountHis(Map<String, Object> mapPar);

	public int memberJobCompletCountApprove(Map<String, Object> paramMap);

	public int memberJobCheckup(Map<String, Object> map);
	
}
