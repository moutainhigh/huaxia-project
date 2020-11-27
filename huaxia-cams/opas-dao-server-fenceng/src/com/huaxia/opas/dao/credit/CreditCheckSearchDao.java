package com.huaxia.opas.dao.credit;

import java.util.List;
import java.util.Map;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.decision.TelcheckAddnote;

public interface CreditCheckSearchDao{

	public Map<String,Object> queryCreditCustInfo(Map<String ,String> argMap) throws CoreException;
	
	public Map<String,Object> queryCheckNodeInfo(Map<String ,String> argMap) throws CoreException;
	
	public List<Map<String,Object>> queryApprovalInfo(Map<String ,String> argMap) throws CoreException;
	
	public Map<String,Object> queryCheckResultInfo(Map<String ,String> argMap) throws CoreException;
	
	public Map<String,Object> queryBatchMarkInfo(Map<String ,String> argMap) throws CoreException;

	public List<Map<String,Object>> selectApprovalBackMemo(String appId);

	public List<TelcheckAddnote> selectTelcheckAddnote(String appId);

	public Map<String,Object> selectUserNameByUserCode(String userCode);

	public List<Map<String, Object>> selectCheckingDetail(String appId);
	
	public List<Map<String,Object>> selectSVoiceBackMemo(String appId);

	/*public List<String> queryDenyMemo(Map<String, String> argMap) throws CoreException;*/
}
