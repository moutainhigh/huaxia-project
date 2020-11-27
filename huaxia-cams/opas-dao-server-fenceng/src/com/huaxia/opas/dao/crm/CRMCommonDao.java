package com.huaxia.opas.dao.crm;

import java.util.List;
import java.util.Map;

public interface CRMCommonDao {

	Map<String, Object> queryCRMInfo(String appId);


	List<Object> queryWangyinInfo(String appId);

	List<Object> queryJiejikaInfo(String appId);


	List<Object> queryDanbaoInfo(String appId);


	List<Object> queryDaikuanInfo(String appId);
}
