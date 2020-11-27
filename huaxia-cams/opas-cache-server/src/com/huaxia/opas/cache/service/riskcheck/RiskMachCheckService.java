package com.huaxia.opas.cache.service.riskcheck;

import com.huateng.neofp.core.CoreException;

/**
 * 风险检查
 * @author qianxiwen
 */
public interface RiskMachCheckService {
	
	public void exeRiskCheck(String appId,String step,String appType) throws CoreException;
	
}
