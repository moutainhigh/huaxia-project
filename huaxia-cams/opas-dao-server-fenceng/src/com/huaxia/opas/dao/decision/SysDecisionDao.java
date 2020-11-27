package com.huaxia.opas.dao.decision;

import java.util.List;
import java.util.Map;

import com.huaxia.opas.domain.decision.JDCardInfo;
import com.huaxia.opas.domain.decision.OpaBzkSysResultInfo;
import com.huaxia.opas.domain.decision.OpaCheckinerResultInfo;
import com.huaxia.opas.domain.decision.OpaInnerDataCheck;
import com.huaxia.opas.domain.decision.OpaRiskInfoCheck;
import com.huaxia.opas.domain.decision.OpasCustBaseInfo;
import com.huaxia.opas.domain.input.Union3118;

public interface SysDecisionDao {

	OpasCustBaseInfo selectCustInfoByAppId(String appId);
	
	JDCardInfo selectJDCardInfoByAppId(String appId);

	OpaBzkSysResultInfo selectSysResultfoByAppId(String appId);

	OpaInnerDataCheck selectInnerDataByAppId(String appId);

	OpaRiskInfoCheck selectRiskInfoByAppId(String appId);

	/*OpaCheckinerResultInfo selectChecKinerInfoByAppId(String appId);*/

	Map<String, String> queryCRMInfo(String appId);

	List<Object> queryJiejikanfo(String appId);

	List<Object> queryWangyinInfo(String appId);

	Map<String, String> selectCardInfoByAppId(String appId);
	
	List<OpaCheckinerResultInfo> selectCheckinerResultInfoByAppID(String appId);

	Map<String, String> selectHaveCardInfoByAppId(String appId);

	Map<String, String> selectHaveCardInfoByAppIdByMap(Map<String, String> paramMap);

	List<Map<String, Object>> selectCreditLimitByAppId(String appId);

	List<Map<String, Object>> selectFqzRstDespByAppId(
			Map<String, Object> dataMap);
	
	List<Union3118> select3118ByAppId(Map<String, String> paramMap);
	
	List<Union3118> select3118NumByAppId(String appId);
	
	String selectNewHaveFlag(Map<String, String> paramMap);
	
	Union3118 selectfkByAppId(Map<String, String> paramMap);
	
	List<Union3118> select3070NumByAppId(Map<String, String> paramMap);
}
