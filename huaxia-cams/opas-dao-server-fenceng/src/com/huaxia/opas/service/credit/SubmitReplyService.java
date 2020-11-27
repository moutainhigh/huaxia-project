package com.huaxia.opas.service.credit;

import java.util.Map;

import com.huateng.neofp.core.CoreException;

public interface SubmitReplyService {

	public Map<String,Object> querySubmitReplyInfo(Map<String ,String> argMap) throws CoreException;
	
	/**
	 * 历史审批查询
	 * */
	public Map<String, Object> selectByHistoryAuditInfo(String appId) throws CoreException;
	//预审回复查看wdb
	public Map<String,Object> queryYsSubmitReplyInfo(Map<String ,String> argMap) throws CoreException;

}
