package com.huaxia.opas.service.sysparm;

import java.util.Map;

import com.huateng.neofp.core.CoreException;

public interface CreditApplyStatisticService{

	public int queryCreditApplyCount(Map<String, Object> map) throws CoreException;

	public int querySeniorApplyCount(Map<String, Object> map) throws CoreException;

	public int queryFinishedCount(Map<String, Object> map) throws CoreException;


}
