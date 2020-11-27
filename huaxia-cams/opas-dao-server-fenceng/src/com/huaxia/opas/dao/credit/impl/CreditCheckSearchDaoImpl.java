package com.huaxia.opas.dao.credit.impl;

import java.util.List;
import java.util.Map;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.credit.CreditCheckSearchDao;
import com.huaxia.opas.domain.decision.TelcheckAddnote;

public class CreditCheckSearchDaoImpl extends AbstractDAO  implements CreditCheckSearchDao {
	private static final long serialVersionUID = -6903339237463254250L;

	private static final String NAMESPACES = "CreditCheckSearch.";

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> queryCreditCustInfo(Map<String ,String> argMap) throws CoreException{
		//System.out.println(appId);
		List<Object> queryForList = getSqlMap().queryForList(NAMESPACES + "queryCreditCustInfo", argMap);
		if(!queryForList.isEmpty()){
			return (Map<String, Object>)queryForList.get(0);
		}else{
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> queryCheckNodeInfo(Map<String ,String> argMap) throws CoreException{
		List<Object> queryForList = getSqlMap().queryForList(NAMESPACES + "queryCheckNodeInfo", argMap);
		if(!queryForList.isEmpty()){
			return (Map<String, Object>)queryForList.get(0);
		}else{
			return null;
		}
	}

	@Override
	public List<Map<String, Object>> queryApprovalInfo(Map<String ,String> argMap) throws CoreException{
		List<Map<String, Object>> queryForList = getSqlMap().queryForList(NAMESPACES + "queryApprovalInfo", argMap);
		if(!queryForList.isEmpty()){
			return (List<Map<String, Object>>)queryForList;
		}else{
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> queryCheckResultInfo(Map<String ,String> argMap) throws CoreException{
		List<Object> queryForList = getSqlMap().queryForList(NAMESPACES + "queryCheckResultInfo", argMap);
		if(!queryForList.isEmpty()){
			return (Map<String, Object>)queryForList.get(0);
		}else{
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> queryBatchMarkInfo(Map<String ,String> argMap) throws CoreException{
		List<Object> queryForList = getSqlMap().queryForList(NAMESPACES + "queryBatchMarkInfo", argMap);
		if(!queryForList.isEmpty()){
			return (Map<String, Object>)queryForList.get(0);
		}else{
			return null;
		}
	}

	@Override
	public List<Map<String,Object>> selectApprovalBackMemo(String appId) {
		return getSqlMap().queryForList(NAMESPACES + "selectApprovalBackMap", appId);
	}

	@Override
	public List<TelcheckAddnote> selectTelcheckAddnote(String appId) {
		return getSqlMap().queryForList(NAMESPACES + "selectTelcheckAddnote", appId);
	}

	@Override
	public Map<String,Object> selectUserNameByUserCode(String userCode) {
		return getSqlMap().queryForObject(NAMESPACES + "selectUserNameByUserCode",userCode);
	}

	@Override
	public List<Map<String, Object>> selectCheckingDetail(String appId) {
		return getSqlMap().queryForList(NAMESPACES + "selectCheckingDetail", appId);
	}

	@Override
	public List<Map<String, Object>> selectSVoiceBackMemo(String appId) {
		return getSqlMap().queryForList(NAMESPACES + "selectSVoiceBackMap", appId);
	}

	/*@Override
	public List<String> queryDenyMemo(Map<String, String> argMap) throws CoreException {
		List<String> queryForList = getSqlMap().queryForList(NAMESPACES + "queryDenyMemo", argMap);
		if(!queryForList.isEmpty()){
			return queryForList;
		}else{
			return null;
		}
	}*/
	
}
