package com.huaxia.opas.dao.decision.impl;

import java.util.List;
import java.util.Map;

import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.decision.SysDecisionDao;
import com.huaxia.opas.domain.decision.JDCardInfo;
import com.huaxia.opas.domain.decision.OpaBzkSysResultInfo;
import com.huaxia.opas.domain.decision.OpaCheckinerResultInfo;
import com.huaxia.opas.domain.decision.OpaInnerDataCheck;
import com.huaxia.opas.domain.decision.OpaRiskInfoCheck;
import com.huaxia.opas.domain.decision.OpasCustBaseInfo;
import com.huaxia.opas.domain.input.Union3118;

public class SysDecisionDaoImpl extends AbstractDAO implements SysDecisionDao {

	private static final long serialVersionUID = -7222923536855463647L;

	private static final String CUSTBASEINFO_NAMESPACES = "opasCustBaseInfoMapper.";// 客户信息
	private static final String JDCARDINFO_NAMESPACES = "JDCardInfo.";//京东卡																				// map
	private static final String SYSRESULTINFO_NAMESPACES = "OpaBzkSysResultInfoMapper.";// 主卡决策结果map
	private static final String CHEKINERRESULT_NAMESPACES = "OpaCheckinerResultInfoMapper.";// 质检结果map
	private static final String INNERDATACHECK_NAMESPACES = "OpaInnerDataCheckMapper.";// 内部数据检查map
	private static final String RISKINFO_NAMESPACES = "OpaRiskInfoCheckMapper.";// 风险信息检查map
	private static final String HAVECARDINFO_NAMESPACES = "OpasHaveCardCustInfoMapper.";// 已持卡客户map
	private static final String CRM_INFO = "CRM.";// 已持卡客户map

	public OpasCustBaseInfo selectCustInfoByAppId(String appId) {
		return (OpasCustBaseInfo) getSqlMap().queryForObject(CUSTBASEINFO_NAMESPACES + "selectByAppId", appId);
	}

	public OpaBzkSysResultInfo selectSysResultfoByAppId(String appId) {
		return (OpaBzkSysResultInfo) getSqlMap().queryForObject(SYSRESULTINFO_NAMESPACES + "selectByAppId", appId);
	}

	public OpaInnerDataCheck selectInnerDataByAppId(String appId) {
		return (OpaInnerDataCheck) getSqlMap().queryForObject(INNERDATACHECK_NAMESPACES + "selectByAppId", appId);
	}

	public OpaRiskInfoCheck selectRiskInfoByAppId(String appId) {
		return (OpaRiskInfoCheck) getSqlMap().queryForObject(RISKINFO_NAMESPACES + "selectByAppId", appId);
	}

	/*public OpaCheckinerResultInfo selectChecKinerInfoByAppId(String appId) {
		return (OpaCheckinerResultInfo) getSqlMap().queryForObject(CHEKINERRESULT_NAMESPACES + "selectByAppId", appId);
	}*/
	@Override
	public Map<String, String> selectHaveCardInfoByAppIdByMap(Map<String,String> paramMap) {
		return getSqlMap().queryForObject(HAVECARDINFO_NAMESPACES + "selectHaveCardInfoByAppIdByMap", paramMap);
	}
	public Map<String, String> selectHaveCardInfoByAppId(String appId) {
		return getSqlMap().queryForObject(HAVECARDINFO_NAMESPACES + "selectCustInfoByAppId", appId);
	}
	public Map<String, String> selectCardInfoByAppId(String appId) {
		return getSqlMap().queryForObject(HAVECARDINFO_NAMESPACES + "selectCardInfoByAppId", appId);
	}
	@Override
	public Map<String, String> queryCRMInfo(String appId) {
		return getSqlMap().queryForObject(CRM_INFO + "queryCustInfo", appId);
	}

	@Override
	public List<Object> queryJiejikanfo(String appId) {
		return null;
	}

	@Override
	public List<Object> queryWangyinInfo(String appId) {
		return null;
	}

	@Override
	public List<OpaCheckinerResultInfo> selectCheckinerResultInfoByAppID(String appId) {
		List<OpaCheckinerResultInfo> arrayList = getSqlMap().queryForList(CHEKINERRESULT_NAMESPACES + "selectByAppId", appId);
		if(arrayList.size()==0){
			OpaCheckinerResultInfo ocri = new OpaCheckinerResultInfo();
			ocri.setCheckResult("此件未进行过质检检查!!!");
			arrayList.add(ocri);
		}
		return arrayList;
	}

	@Override
	public JDCardInfo selectJDCardInfoByAppId(String appId) {
		return getSqlMap().queryForObject(JDCARDINFO_NAMESPACES+"selectJDCardInfoByAppId",appId);
	}

	@Override
	public List<Map<String, Object>> selectCreditLimitByAppId(String appId) {
		return getSqlMap().queryForList(HAVECARDINFO_NAMESPACES + "selectCreditLimitByAppId", appId);
	}

	@Override
	public List<Map<String, Object>> selectFqzRstDespByAppId(
			Map<String, Object> dataMap) {
		return getSqlMap().queryForList(SYSRESULTINFO_NAMESPACES + "selectFqzRstDespByAppId", dataMap);
	}
	
	@Override
	public List<Union3118> select3118ByAppId(Map<String, String> paramMap) {
		return getSqlMap().queryForList(HAVECARDINFO_NAMESPACES + "select3118ByAppId", paramMap);
	}
	
	@Override
	public List<Union3118> select3118NumByAppId(String appId) {
		return getSqlMap().queryForList(HAVECARDINFO_NAMESPACES + "select3118NumByAppId", appId);
	}
	
	@Override
	public String selectNewHaveFlag(Map<String, String> paramMap) {
		return getSqlMap().queryForObject(HAVECARDINFO_NAMESPACES+"selectNewHaveFlag",paramMap);
	}
	
	@Override
	public Union3118 selectfkByAppId(Map<String, String> paramMap) {
		return getSqlMap().queryForObject(HAVECARDINFO_NAMESPACES + "selectfkByAppId", paramMap);
	}
	
	@Override
	public List<Union3118> select3070NumByAppId(Map<String, String> paramMap) {
		return getSqlMap().queryForList(HAVECARDINFO_NAMESPACES + "select3070NumByAppId", paramMap);
	}
}
