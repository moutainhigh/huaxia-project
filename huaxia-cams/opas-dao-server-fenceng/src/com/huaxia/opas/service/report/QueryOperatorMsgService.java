package com.huaxia.opas.service.report;

import java.util.List;
import java.util.Map;

import com.huateng.neofp.core.CoreException;

public interface QueryOperatorMsgService {

	public List<Map<String, Object>> queryOrgName() throws CoreException;

	public List<Map<String, Object>> queryOperatorName(String orgId);

	public List<Map<String, Object>> queryApplyOrgName();

	public List<Map<String, Object>> queryApplyOperatorName(String orgName);
	
}
