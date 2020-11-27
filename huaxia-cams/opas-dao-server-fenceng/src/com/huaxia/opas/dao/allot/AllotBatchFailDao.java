package com.huaxia.opas.dao.allot;

import java.util.Map;

import com.huateng.neofp.core.CoreException;

public interface AllotBatchFailDao {
	
	int insertFailApply(String appId) throws CoreException;
	
	int insertFailApply(Map map) throws CoreException;
	
	int updateFailApply(Map map) throws CoreException;
	
	int selectCount(Map map) throws CoreException;
}
