package com.huaxia.opas.dao.thirdparty;

import java.util.List;
import java.util.Map;

/**
 * 第三方-百融接口
 * 
 * @author zhiguo.li
 *
 */
public interface Credit100Dao {

	/**
	 * 查询摘要信息
	 * 
	 * @param appId
	 *            申请件编号
	 */
	Map<String, String> selectSummaryInfo(String appId);

	Map<String, String> selectDetailInfo(String appId);

	Map<String,String> selectDetailAppId(String appId);

	Map<String, String> Query_biz_speciallist_cell_data(String appId);

	Map<String, String> Query_OPAS_BIZ_SPECIALLIST_LM_DATA(String appId);

	Map<String, String> Query_OPAS_BIZ_CONSUMPTION_DATA(String appId);

	List<String> Query_task_request_info(String appId);
	
	// add by qingfeng.xiu 20190218 17:40
	int Query_bairong_query_result(String appId);
}
