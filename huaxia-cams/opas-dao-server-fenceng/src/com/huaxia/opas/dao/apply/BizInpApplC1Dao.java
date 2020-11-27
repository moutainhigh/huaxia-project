package com.huaxia.opas.dao.apply;

import java.util.List;
import java.util.Map;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.apply.ApplyLifeCicle;
import com.huaxia.opas.domain.input.BizInpApplC1;
import com.huaxia.opas.domain.input.BizInpApplC2;

public interface BizInpApplC1Dao {

	BizInpApplC1 queryBizInpApplC1ByAppId(String appId) throws CoreException;

	String queryWorkCompany(String appId);
	
	String queryRequestTypeByAppId(String appId);
	
	void updateBizInfo(BizInpApplC1 bizInpApplC1) throws CoreException;
	
	BizInpApplC1 selectBizInpApplC1ByAppId(String appId) throws CoreException;
	 
	BizInpApplC2 findBiz2info(String appno) throws CoreException;

	int queryCount(Map<String, Object> map)throws CoreException;

	List<Map<Object,Object>> queryList(Map<String, Object> map, int curNum, int pageNum) throws CoreException;

	Map queryDragAqqlyById(String appId)throws CoreException;

	List<ApplyLifeCicle> queryApplyLifeList(String appId)throws CoreException;
	 String queryRefuseCodeList(Map map) throws CoreException;
	List queryInDataChecK(Map map ,int cur,int page) throws CoreException;
	List queryInDataChecK(Map map) throws CoreException;
	int countInDataChecK(Map map ) throws CoreException;
	Map queryInDataChecK(String appId) throws CoreException;
	//zhanglibo
	BizInpApplC1 queryBizInpApplC1ByAppIdOrder(String appId) throws CoreException;

	void updateBizInpApplInfo(BizInpApplC1 bizInpApplC1) throws CoreException;
	
	void updateBizInpApplC2Info(BizInpApplC2 bizInpApplC2) throws CoreException;

	String findYxqPd(String appId) throws CoreException;

	int findHaveCard(Map<String, Object> map) throws CoreException;

	int getPersonAge(String appId) throws CoreException;
}
