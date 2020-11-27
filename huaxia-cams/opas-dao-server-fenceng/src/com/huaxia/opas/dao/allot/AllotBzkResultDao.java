package com.huaxia.opas.dao.allot;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.decision.OpaBzkSysResultInfo;
/**
 * 标准卡征信策略结果
 * @author  wangdebin
 *
 */
public interface AllotBzkResultDao {
	
	// 插入
	 int insertBzkResultInfo(OpaBzkSysResultInfo info) throws CoreException;
	
	//查询
	int selectCount(String appId) throws CoreException;
	
	String selectResultByAppId(String appId) throws CoreException;
	
	//更新
	 int updateBzkResultInfo(OpaBzkSysResultInfo info) throws CoreException;
}
