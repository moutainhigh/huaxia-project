package com.huaxia.opas.dao.allot;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.decision.OpaBzkSysResultInfo;
import com.huaxia.opas.domain.decision.YdjSysresultInfo;
/**
 * 易达金卡征信策略结果
 * @author  wangdebin
 *
 */
public interface AllotYdjResultDao {
	// 插入
	public int insertYdjResultInfo(YdjSysresultInfo info) throws CoreException;
	
	//查询
	public int selectCount(String appId) throws CoreException;
		
	//更新
	public int updateYdjResultInfo(YdjSysresultInfo info) throws CoreException;
	
}
