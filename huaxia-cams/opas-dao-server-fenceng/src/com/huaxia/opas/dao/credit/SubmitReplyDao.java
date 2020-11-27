package com.huaxia.opas.dao.credit;

import java.util.List;
import java.util.Map;

import com.huateng.neofp.core.CoreException;

public interface SubmitReplyDao{

	public List<Map<String, Object>> querySubmitFraudInfo(Map<String ,String> argMap) throws CoreException;
	
	public List<Map<String, Object>> querySubmitAuthoInfo(Map<String ,String> argMap) throws CoreException;
	
	public List<Map<String, Object>> querySubmitAccountInfo(Map<String ,String> argMap) throws CoreException;
	
	/**
	 * 反洗钱 
	 * @author wenyh
	 * @param argMap
	 * @return
	 * @throws CoreException
	 */
	public List<Map<String, Object>> querySubmitAMLInfo(Map<String ,String> argMap) throws CoreException;
	
	public List<Map<String, Object>> querySubmitUrgeInfo(Map<String ,String> argMap) throws CoreException;
	
	public List<Map<String, Object>> querySubmitKeyInfo(Map<String ,String> argMap) throws CoreException;
	
	public List<Map<String, Object>> queryCheckReturnInfo(Map<String ,String> argMap) throws CoreException;
	/**
	 * 派发协查 wenyh
	 * @param argMap
	 * @return
	 * @throws CoreException
	 */
	public List<Map<String, Object>> queryPfxcReturnInfo(Map<String ,String> argMap) throws CoreException;
	
	//预审欺诈 wdb
	public List<Map<String, Object>> queryYsSubmitFraudInfo(Map<String ,String> argMap) throws CoreException;
	//预审复核退回反馈 wdb
	public List<Map<String, Object>> queryYsCheckReturnInfo(Map<String ,String> argMap) throws CoreException;
}
