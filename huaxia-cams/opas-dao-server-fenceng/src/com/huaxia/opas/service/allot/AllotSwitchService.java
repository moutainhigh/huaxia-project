package com.huaxia.opas.service.allot;

import java.util.Map;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.allot.AllotSwitch;

public interface AllotSwitchService {
	//查询是否存在
	int countAllotSwitch(String switchCode) throws CoreException;
		
	// 插入开关
	 int saveAllotSwitch(AllotSwitch allotSwitch) throws CoreException;

	// 更新开关
	 int updateAllotSwitch(AllotSwitch allotSwitch) throws CoreException;

	// 根据switchCode查询开关
	 AllotSwitch queryAllotSwitchByCode(String switchCode) throws CoreException;
	 
	 int queryHoliday() throws CoreException; 
}
