package com.huaxia.opas.service.thirdparty;

import java.util.List;
import java.util.Map;

public interface GjjService {

	Map<String, String> selectGjjInfo(String appId);
	
	Map<String, String> selectGjjBriefInfo(String appId);

	List<Map<String, String>> queryGjjDetail(String appId);

	List<Map<String, String>> queryGjjBriefAnalysis(String appId);

	List<Map<String, String>> queryGjjDetailAnalysis(String appId);

	List<Map<String, String>> queryCompanyAnalData(String appId);

	List<Map<String, String>> queryGjjLoadBrief(String appId);

	List<Map<String, String>> queryGjjLoadDetail(String appId);


}
