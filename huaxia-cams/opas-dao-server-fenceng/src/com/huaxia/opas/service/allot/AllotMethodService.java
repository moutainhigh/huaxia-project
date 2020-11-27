package com.huaxia.opas.service.allot;

import java.util.List;
import java.util.Map;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.allot.AllotApply;
import com.huaxia.opas.domain.allot.AllotCommon;
import com.huaxia.opas.domain.apply.ApplyLifeCicle;

public interface AllotMethodService {
	int updateMethod(Map map)  ;
	
	int updateBatchMethod(Map map) ;
	
	int saveRemark(Map map) throws Exception;
	
	void saveAppLifeCicle(Map map) throws Exception;
	
	void selfBatch(List<ApplyLifeCicle> cicleList) throws Exception;
	
	int updateSynFlag(List<String> allotApplyList,String currNode) throws Exception;
}
