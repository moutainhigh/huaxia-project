package com.huaxia.opas.dao.thirdparty;

import java.util.List;
import java.util.Map;

public interface GjjDao {

	Map<String, String> queryGjjInfo(String appId);
	
	Map<String, String> queryGjjBriefInfo(String appId);

	List<Map<String, String>> queryGjjDetail(String appId);

	List<Map<String, String>> queryGjjBriefAnalysis(String appId);

	List<Map<String, String>> queryGjjDetailAnalysis(String appId);

	List<Map<String, String>> queryCompanyAnalData(String appId);

	List<Map<String, String>> queryGjjLoadBrief(String appId);

	List<Map<String, String>> queryGjjLoadDetail(String appId);

	

}
