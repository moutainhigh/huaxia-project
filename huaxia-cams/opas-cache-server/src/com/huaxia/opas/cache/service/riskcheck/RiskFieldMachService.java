package com.huaxia.opas.cache.service.riskcheck;

import java.util.List;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.riskcheck.RiskCheckResult;
import com.huaxia.opas.domain.riskcheck.RiskCheck_Apply_C1;
import com.huaxia.opas.domain.riskcheck.RiskCheck_Apply_C2;

/**
 * 风险检查
 * @author qianxiwen
 */
public interface RiskFieldMachService {
	public List<RiskCheckResult> macheField(String appId,RiskCheck_Apply_C1 apply_Info1,
			RiskCheck_Apply_C2 apply_Info2,String appType) throws CoreException;
	public void init(String appId, RiskCheck_Apply_C1 apply_Info1,
			RiskCheck_Apply_C2 apply_Info2,String appType) throws CoreException;
}
