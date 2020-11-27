package com.huaxia.opas.service.decision;

import java.util.List;
import java.util.Map;


public interface BizApprovalService {
	
	List<Map<String, String>> selectByHisAppIds(Map hisAppIdArrayParams);

}
