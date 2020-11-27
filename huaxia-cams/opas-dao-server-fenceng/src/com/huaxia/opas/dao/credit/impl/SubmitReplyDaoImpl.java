package com.huaxia.opas.dao.credit.impl;

import java.util.List;
import java.util.Map;

import com.huateng.neofp.core.CoreException; 
import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.credit.SubmitReplyDao;

public class SubmitReplyDaoImpl extends AbstractDAO  implements SubmitReplyDao {
	private static final long serialVersionUID = -6903339223463254250L;

	private static final String NAMESPACES = "SubmitReply.";

	@Override
	public List<Map<String, Object>> querySubmitFraudInfo(Map<String ,String> argMap) throws CoreException{
		List<Map<String, Object>> queryForList = getSqlMap().queryForList(NAMESPACES + "querySubmitFraudInfo", argMap);
		return queryForList.size()!=0?queryForList:null;
	}

	@Override
	public List<Map<String, Object>> querySubmitAuthoInfo(Map<String ,String> argMap) throws CoreException{
		List<Map<String, Object>> queryForList = getSqlMap().queryForList(NAMESPACES + "querySubmitAuthoInfo", argMap);
		return queryForList.size()!=0?queryForList:null;
	}

	@Override
	public List<Map<String, Object>> querySubmitAccountInfo(Map<String ,String> argMap) throws CoreException{
		List<Map<String, Object>> queryForList = getSqlMap().queryForList(NAMESPACES + "querySubmitAccountInfo", argMap);
		return queryForList.size()!=0?queryForList:null;
	}

	/**
	 * 反洗钱 
	 * @author wenyh
	 * @param argMap
	 * @return
	 * @throws CoreException
	 */
	@Override
	public List<Map<String, Object>> querySubmitAMLInfo(Map<String ,String> argMap) throws CoreException{
		List<Map<String, Object>> queryForList = getSqlMap().queryForList(NAMESPACES + "querySubmitAMLInfo", argMap);
		return queryForList.size()!=0?queryForList:null;
	}
	
	@Override
	public List<Map<String, Object>> querySubmitUrgeInfo(Map<String ,String> argMap) throws CoreException{
		List<Map<String, Object>> queryForList = getSqlMap().queryForList(NAMESPACES + "querySubmitUrgeInfo", argMap);
		return queryForList.size()!=0?queryForList:null;
	}

	@Override
	public List<Map<String, Object>> querySubmitKeyInfo(Map<String ,String> argMap) throws CoreException{
		List<Map<String, Object>> queryForList = getSqlMap().queryForList(NAMESPACES + "querySubmitKeyInfo", argMap);
		return queryForList.size()!=0?queryForList:null;
	}

	@Override
	public List<Map<String, Object>> queryCheckReturnInfo(Map<String, String> argMap) throws CoreException {
		List<Map<String, Object>> queryForList = getSqlMap().queryForList(NAMESPACES + "queryCheckReturnInfo", argMap);
		return queryForList.size()!=0?queryForList:null;
	}

	@Override
	public List<Map<String, Object>> queryYsSubmitFraudInfo(Map<String, String> argMap) throws CoreException {
		return getSqlMap().queryForList(NAMESPACES + "queryYsSubmitFraudInfo", argMap);
	}

	@Override
	public List<Map<String, Object>> queryYsCheckReturnInfo(Map<String, String> argMap) throws CoreException {
		return getSqlMap().queryForList(NAMESPACES + "queryYsCheckReturnInfo", argMap);
	}
	/**
	 * 派发协查 wenyh
	 * @param argMap
	 * @return
	 * @throws CoreException
	 */
	@Override
	public List<Map<String, Object>> queryPfxcReturnInfo(Map<String, String> argMap) throws CoreException {
		List<Map<String, Object>> queryForList = getSqlMap().queryForList(NAMESPACES + "queryPfxcReturnInfo", argMap);
		return queryForList.size()!=0?queryForList:null;
	}
}
