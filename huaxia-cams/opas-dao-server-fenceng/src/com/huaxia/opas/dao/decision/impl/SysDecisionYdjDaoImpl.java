package com.huaxia.opas.dao.decision.impl;

import java.util.List;
import java.util.Map;

import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.decision.SysDecisionYdjDao;
import com.huaxia.opas.domain.decision.OpaCheckinerResultInfo;
import com.huaxia.opas.domain.decision.OpaInnerDataCheck;
import com.huaxia.opas.domain.decision.OpaRiskInfoCheck;
import com.huaxia.opas.domain.decision.OpasCustBaseInfo;
import com.huaxia.opas.domain.decision.OpasHaveCardCustInfo;
import com.huaxia.opas.domain.decision.YdjSysresultInfo;
import com.huaxia.opas.domain.rule.Opasbizinpapplc1;

public class SysDecisionYdjDaoImpl extends AbstractDAO implements SysDecisionYdjDao {

	private static final long serialVersionUID = -7222923536855463647L;

	private static final String CUSTBASEINFO_NAMESPACES = "opasCustBaseInfoMapper.";// 客户信息
																					// map
	private static final String SYSRESULTINFO_NAMESPACES = "YdjSysresultInfo.";// 主卡决策结果map
	private static final String CHEKINERRESULT_NAMESPACES = "OpaCheckinerResultInfoMapper.";// 质检结果map
	private static final String INNERDATACHECK_NAMESPACES = "OpaInnerDataCheckMapper.";// 内部数据检查map
	private static final String RISKINFO_NAMESPACES = "OpaRiskInfoCheckMapper.";// 风险信息检查map
	private static final String HAVECARDINFO_NAMESPACES = "OpasHaveCardCustInfoMapper.";// 已持卡客户map

	public OpasCustBaseInfo selectCustInfoByAppId(String appId) {
		return (OpasCustBaseInfo)getSqlMap().queryForObject(CUSTBASEINFO_NAMESPACES + "selectByAppId", appId);
	}
	@Override
	public OpasCustBaseInfo selectCustAndPreSellimitByAppId(String appId) {
		return (OpasCustBaseInfo)getSqlMap().queryForObject(CUSTBASEINFO_NAMESPACES + "selectCustAndPreSellimitByAppId", appId);
	}
	@Override
	public String selectMainCardAppIdByAppId(String appId) {
		return getSqlMap().queryForObject(CUSTBASEINFO_NAMESPACES + "selectMainCardAppIdByAppId", appId);
	}
	/**
	 * 审批页面-客户信息 wdb 
	 */
	@Override
	public OpasCustBaseInfo selectCustAndPreSellimitByAppNo(Map map) {
		return (OpasCustBaseInfo)getSqlMap().queryForObject(CUSTBASEINFO_NAMESPACES + "selectCustAndPreSellimitByAppNo", map);
	}
	public YdjSysresultInfo selectSysResultfoByAppId(String appId) {
		return (YdjSysresultInfo)getSqlMap().queryForObject(SYSRESULTINFO_NAMESPACES + "selectByAppId", appId);
	}
	public OpaInnerDataCheck selectInnerDataByAppId(String appId) {
		return (OpaInnerDataCheck)getSqlMap().queryForObject(INNERDATACHECK_NAMESPACES + "selectByAppId", appId);
	}
	public OpaRiskInfoCheck selectRiskInfoByAppId(String appId) {
		return (OpaRiskInfoCheck)getSqlMap().queryForObject(RISKINFO_NAMESPACES + "selectByAppId", appId);
	}
	public OpaCheckinerResultInfo selectChecKinerInfoByAppId(String appId) {
		return (OpaCheckinerResultInfo)getSqlMap().queryForObject(CHEKINERRESULT_NAMESPACES + "selectOneByAppId", appId);
	}
	@Override
	public OpaCheckinerResultInfo selectChecKinerInfoByAppId_15(String appId) {
		return (OpaCheckinerResultInfo)getSqlMap().queryForObject(CHEKINERRESULT_NAMESPACES + "selectOneByAppId_15", appId);
	}
	public OpasHaveCardCustInfo selectHaveCardInfoByAppId(String appId) {
		return (OpasHaveCardCustInfo)getSqlMap().queryForObject(HAVECARDINFO_NAMESPACES + "selectByAppId", appId);
	}
	public Map selectSystemApprovalResult(String appId) {
		return getSqlMap().queryForObject(SYSRESULTINFO_NAMESPACES+"selectSystemApprovalResult", appId);
	}
	@Override
	public Map<String,String> selectCustInfoByCustId(Opasbizinpapplc1 on) {
		return getSqlMap().queryForObject(HAVECARDINFO_NAMESPACES + "selectCustInfoByCustId", on);
	}
	@Override
	public List<Map<String, Object>> selectFqzRequestTypeByAppId(String appId) {
		return getSqlMap().queryForList(SYSRESULTINFO_NAMESPACES + "selectFqzRequestTypeByAppId", appId);
	}
	@Override
	public List<Map<String, Object>>selectFqzRstDespByAppId(Map<String, Object> param) {
		return getSqlMap().queryForList(SYSRESULTINFO_NAMESPACES + "selectFqzRstDespByAppId", param);
	}

	@Override
	public String selectFqzFraudScore(Map<String, Object> param) {				
		return getSqlMap().queryForObject(SYSRESULTINFO_NAMESPACES + "selectFqzFraudScore", param);
	}
	@Override
	public String selectMaxFqzRequestTypeByAppId(String appId) {				
		return getSqlMap().queryForObject(SYSRESULTINFO_NAMESPACES + "selectMaxFqzRequestTypeByAppId", appId);
	}
	@Override
	public String selectMisszdResultByAppId(String appId) {
		return getSqlMap().queryForObject(SYSRESULTINFO_NAMESPACES + "selectMisszdResultByAppId", appId);
	}
	@Override
	public String selectPoliceXPInfo(String appId) {
		return getSqlMap().queryForObject(CUSTBASEINFO_NAMESPACES + "selectPoliceXPInfo", appId);
	}
	@Override
	public List<Map<String, Object>> selectFqzRiskDec(Map<String, Object> param) {
		return getSqlMap().queryForList(SYSRESULTINFO_NAMESPACES + "selectFqzRiskDec", param);
	}

}
