package com.huaxia.opas.service.decision;

import java.util.List;
import java.util.Map;

import com.huaxia.opas.domain.decision.JDCardInfo;
import com.huaxia.opas.domain.decision.OpaBzkSysResultInfo;
import com.huaxia.opas.domain.decision.OpaCheckinerResultInfo;
import com.huaxia.opas.domain.decision.OpaInnerDataCheck;
import com.huaxia.opas.domain.decision.OpaRiskInfoCheck;
import com.huaxia.opas.domain.decision.OpasCustBaseInfo;
import com.huaxia.opas.domain.decision.OpasHaveCardCustInfo;
import com.huaxia.opas.domain.input.Union3118;

public interface SysDecisionService {
	
	OpasCustBaseInfo selectCustInfoByAppId(String appId);

	OpaBzkSysResultInfo selectSysResultInfoByAppId(String appId);

	OpaInnerDataCheck selectInnerDataByAppId(String appId);

	OpaRiskInfoCheck selectRiskInfoByAppId(String appId);

	/*OpaCheckinerResultInfo selectChecKinerInfoByAppId(String appId);*/

	Map<String, String> queryCRMInfo(String appId);

	List<Object> selectJiejikaDetailList(String appId);

	List<Object> selectWangyinDetailList(String appId);
	
	Map<String, String> selectCardInfo(String appId);

	List<OpaCheckinerResultInfo> selectCheckinerResultInfoByAppID(String appId);
	
	JDCardInfo selectJDCardInfoByAppId(String appId);

	Map<String, String> selectHaveCardInfoByAppId(String appId);

	Map<String, String> selectHaveCardInfoByAppIdByMap(Map<String, String> paramMap);

	Map<String, Object> queryFqzRstDespByAppId(Map<String, Object> dataMap);
	
	List<Union3118> select3118ByAppId(Map<String, String> paramMap);
	
	List<Union3118> select3118NumByAppId(String appId);
	
	String selectNewHaveFlag(Map<String, String> paramMap);
	
	Union3118 selectfkByAppId(Map<String, String> paramMap);
	
	List<Union3118> select3070NumByAppId(Map<String, String> paramMap);
}
