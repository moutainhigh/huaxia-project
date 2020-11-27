package com.huaxia.opas.service.apply;

import java.util.List;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.apply.ApplyLifeCicle;

public interface ApplyLifeCicleService {

	public int addApplyLifeCicle(ApplyLifeCicle a) throws CoreException, Exception;

	public int addApplyLifeBatch(List<ApplyLifeCicle> cicleList) throws Exception;
	
	public int addInfoCompareToExamine(String appId,String currNode) throws Exception;
	
}
