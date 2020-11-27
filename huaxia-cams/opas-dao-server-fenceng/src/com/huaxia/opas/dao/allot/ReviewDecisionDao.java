package com.huaxia.opas.dao.allot;

import java.util.Map;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.audit.ApprovaInfoSupp;
import com.huaxia.opas.domain.decision.OpasReviewDecisionResult;
/**
 * 标准卡 录入环节批量标识
 * @author  wangdebin 
 *
 */
public interface ReviewDecisionDao {
	
	//wangdebin 录入环节批量标识
	
	int countBatchCode(String appId) throws CoreException ;
	
	int insertBatchCode(OpasReviewDecisionResult ordr) throws CoreException ;
	
	int updateBatchCode(OpasReviewDecisionResult ordr) throws CoreException ;
	
	int selectCount(String appId) throws CoreException ;
	
	int insertApprovaInfoSupp(Map map) throws CoreException;
	
	int updateByAppId(Map map) throws CoreException;
	
}
