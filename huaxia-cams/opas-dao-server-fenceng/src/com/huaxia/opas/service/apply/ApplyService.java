package com.huaxia.opas.service.apply;

import java.util.List;
import java.util.Map;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.apply.ApplyLifeCicle;
import com.huaxia.opas.domain.input.BizInpApplC1;
import com.huaxia.opas.domain.input.BizInpApplC2;
import com.huaxia.opas.service.BaseService;

public interface ApplyService extends BaseService<BizInpApplC1> {

	void updateBizInfo(BizInpApplC1 bizInpApplC1) throws CoreException;
	
	BizInpApplC2 findBiz2info(String appno) throws CoreException;

	int queryCount(Map<String, Object> map) throws CoreException;

	List<Map<Object,Object>> queryList(Map<String, Object> map, int curNum, int pageNum) throws CoreException;

	Map queryDragAqqlyById(String appId) throws CoreException;

	List<ApplyLifeCicle> queryApplyLifeList(String appId) throws CoreException;

}
