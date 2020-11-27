package com.huaxia.opas.dao.retrieve;

import java.util.Map;

import com.huateng.neofp.core.CoreException;

public interface FicoBigResponseDao {
	
	//一次反欺诈拒件数
	int queryYCFQZJJS(Map<String, Object> map)throws CoreException;
	//二次反欺诈拒件数
	int queryECFQZJJS(Map<String, Object> map)throws CoreException; 
	//一次决策拒件数
	int queryYCJCJJS(Map<String, Object> map)throws CoreException; 
	//一次决策过件数
	int queryYCJCGJS(Map<String, Object> map)throws CoreException; 
	//二次决策拒件数
	int queryECJCJJS(Map<String, Object> map)throws CoreException;
	//二次决策过件数
	int queryECJCGJS(Map<String, Object> map)throws CoreException;

}
