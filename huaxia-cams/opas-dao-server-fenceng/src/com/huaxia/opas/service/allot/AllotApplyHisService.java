package com.huaxia.opas.service.allot;

import java.util.List;
import java.util.Map;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.allot.AllotApplyAllotHis;

public interface AllotApplyHisService {
	// 插入历史记录
	int saveAllotApplyHis(AllotApplyAllotHis allotApplyHis) throws CoreException;
	
	int saveAllotApplyHis(Map map) throws CoreException; 
	// 批量插入历史记录
	int saveHisBatch(List<String> lifeList) throws CoreException;
}
