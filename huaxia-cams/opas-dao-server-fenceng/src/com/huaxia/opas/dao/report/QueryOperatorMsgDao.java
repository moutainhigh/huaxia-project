package com.huaxia.opas.dao.report;

import java.util.List;
import java.util.Map;

public interface QueryOperatorMsgDao {

	public List<Map<String, Object>> queryOrgName();

	public List<Map<String, Object>> queryOperatorName(String orgId);

	public List<Map<String, Object>> queryApplyOrgName();

	public List<Map<String, Object>> queryApplyOperatorName(String orgName);
	
}