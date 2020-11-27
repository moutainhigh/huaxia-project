package com.huaxia.opas.service.decision.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.huaxia.opas.dao.decision.SysDecisionDao;
import com.huaxia.opas.dao.decision.SysDecisionYdjDao;
import com.huaxia.opas.domain.decision.JDCardInfo;
import com.huaxia.opas.domain.decision.OpaBzkSysResultInfo;
import com.huaxia.opas.domain.decision.OpaCheckinerResultInfo;
import com.huaxia.opas.domain.decision.OpaInnerDataCheck;
import com.huaxia.opas.domain.decision.OpaRiskInfoCheck;
import com.huaxia.opas.domain.decision.OpasCustBaseInfo;
import com.huaxia.opas.domain.input.Union3118;
import com.huaxia.opas.service.AbstractService;
import com.huaxia.opas.service.decision.SysDecisionService;

public class SysDecisionServiceImpl extends AbstractService implements SysDecisionService,Serializable  {

	private static final long serialVersionUID = -8008508483239828116L;

	@Resource(name = "sysDecisionDao")
	private SysDecisionDao sysDecisionDao;
	
	@Resource(name = "sysDecisionYdjDao")
	private SysDecisionYdjDao sysDecisionYdjDao;

	public SysDecisionDao getSysDecisionDao() {
		return sysDecisionDao;
	}

	public void setSysDecisionDao(SysDecisionDao sysDecisionDao) {
		this.sysDecisionDao = sysDecisionDao;
	}

	public OpasCustBaseInfo selectCustInfoByAppId(String appId) {
		return getSysDecisionDao().selectCustInfoByAppId(appId);
	}

	public OpaBzkSysResultInfo selectSysResultInfoByAppId(String appId) {
		return getSysDecisionDao().selectSysResultfoByAppId(appId);
	}

	public OpaInnerDataCheck selectInnerDataByAppId(String appId) {
		return getSysDecisionDao().selectInnerDataByAppId(appId);
	}

	public OpaRiskInfoCheck selectRiskInfoByAppId(String appId) {
		return getSysDecisionDao().selectRiskInfoByAppId(appId);
	}

	/*public OpaCheckinerResultInfo selectChecKinerInfoByAppId(String appId) {
		return getSysDecisionDao().selectChecKinerInfoByAppId(appId);
	}*/
	@Override
	public Map<String, String> selectHaveCardInfoByAppIdByMap(Map<String,String> paramMap) {
		return getSysDecisionDao().selectHaveCardInfoByAppIdByMap(paramMap);
	}
	@Override
	public Map<String, String> selectHaveCardInfoByAppId(String appId) {
		return getSysDecisionDao().selectHaveCardInfoByAppId(appId);
	}
	@Override
	public Map<String, String> queryCRMInfo(String appId) {
		return getSysDecisionDao().queryCRMInfo(appId);
	}

	@Override
	public List<Object> selectJiejikaDetailList(String appId) {
		return getSysDecisionDao().queryJiejikanfo(appId);
	}

	@Override
	public List<Object> selectWangyinDetailList(String appId) {
		return getSysDecisionDao().queryWangyinInfo(appId);
	}
	
	@Override
	public Map<String, String> selectCardInfo(String appId) {
		return getSysDecisionDao().selectCardInfoByAppId(appId);
	}
	
	@Override
	public List<OpaCheckinerResultInfo> selectCheckinerResultInfoByAppID(String appId) {
		return getSysDecisionDao().selectCheckinerResultInfoByAppID(appId);
	}

	@Override
	public JDCardInfo selectJDCardInfoByAppId(String appId) {
		return getSysDecisionDao().selectJDCardInfoByAppId(appId);
	}

	@Override
	public Map<String, Object> queryFqzRstDespByAppId(
			Map<String, Object> dataMap) {
		List<Map<String, Object>> FqzRstDeslist =  getSysDecisionDao().selectFqzRstDespByAppId(dataMap);
		dataMap.put("rows", FqzRstDeslist);
		dataMap.put("total", FqzRstDeslist.size());
		
		String appId = (String) dataMap.get("appId");
		String wsStatus = appId.substring(6, 7);
		//网申、非网申评分
		String fraudScore = "";
		//如果是B为网申件
		if(wsStatus.equals("B")){
			dataMap.put("fqzType", "WS_AntiFraud_Score");
			fraudScore = sysDecisionYdjDao.selectFqzFraudScore(dataMap);
		} else {
			dataMap.put("fqzType", "FWS_AntiFraud_Score");
			fraudScore = sysDecisionYdjDao.selectFqzFraudScore(dataMap);
		}
		dataMap.put("fraudScore",fraudScore);
		return dataMap;
	}
	
	@Override
	public List<Union3118> select3118ByAppId(Map<String,String> paramMap) {
		return getSysDecisionDao().select3118ByAppId(paramMap);
	}
	
	@Override
	public List<Union3118> select3118NumByAppId(String appId) {
		return getSysDecisionDao().select3118NumByAppId(appId);
	}
	
	@Override
	public String selectNewHaveFlag(Map<String,String> paramMap) {
		return getSysDecisionDao().selectNewHaveFlag(paramMap);
	}
	
	@Override
	public Union3118 selectfkByAppId(Map<String,String> paramMap) {
		return getSysDecisionDao().selectfkByAppId(paramMap);
	}
	
	@Override
	public List<Union3118> select3070NumByAppId(Map<String,String> paramMap) {
		return getSysDecisionDao().select3070NumByAppId(paramMap);
	}
}
