package com.huaxia.opas.service.crm;

import java.util.List;
import java.util.Map;

public interface CRMCommonService {
	Map<String, Object> queryCRMInfo(String appId);

	List<Object> selectJiejikaDetailList(String appId);

	List<Object> selectWangyinDetailList(String appId);

	List<Object> selectDanbaoDetailList(String appId);

	List<Object> selectDaikuanDetailList(String appId);
}
