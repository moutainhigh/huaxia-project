package com.huaxia.opas.dao.decision;

import java.util.List;
import java.util.Map;

import com.huaxia.opas.domain.decision.OpaCheckinerResultInfo;
import com.huaxia.opas.domain.decision.OpaInnerDataCheck;
import com.huaxia.opas.domain.decision.OpaRiskInfoCheck;
import com.huaxia.opas.domain.decision.OpasCustBaseInfo;
import com.huaxia.opas.domain.decision.OpasHaveCardCustInfo;
import com.huaxia.opas.domain.decision.YdjSysresultInfo;
import com.huaxia.opas.domain.rule.Opasbizinpapplc1;

public interface SysDecisionYdjDao {

	OpasCustBaseInfo selectCustInfoByAppId(String appId);

	YdjSysresultInfo selectSysResultfoByAppId(String appId);
	
	OpaInnerDataCheck selectInnerDataByAppId(String appId);

	OpaRiskInfoCheck selectRiskInfoByAppId(String appId);

	OpaCheckinerResultInfo selectChecKinerInfoByAppId(String appId);

	OpasHaveCardCustInfo selectHaveCardInfoByAppId(String appId);
	Map<String,String> selectCustInfoByCustId(Opasbizinpapplc1 on);

	Map selectSystemApprovalResult(String appId);

	OpasCustBaseInfo selectCustAndPreSellimitByAppId(String appId);
	/**
	 * 审批页面-客户信息 wdb 
	 */
	OpasCustBaseInfo selectCustAndPreSellimitByAppNo(Map map);

	OpaCheckinerResultInfo selectChecKinerInfoByAppId_15(String appId);

	List<Map<String, Object>> selectFqzRequestTypeByAppId(String appId);
	
	List<Map<String, Object>> selectFqzRstDespByAppId(Map<String, Object> param);

	String selectFqzFraudScore(Map<String, Object> param);
	
	String selectMaxFqzRequestTypeByAppId(String appId);
	//wdb 查询人像比对结果  2019年8月28日下午16:22:49
	String selectPoliceXPInfo(String appId);
	String selectMisszdResultByAppId(String appId);
	String selectMainCardAppIdByAppId(String appId);

	List<Map<String, Object>> selectFqzRiskDec(Map<String, Object> param);

}
