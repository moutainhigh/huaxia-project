package com.huaxia.opas.dao.apply.impl;

import java.util.List;
import java.util.Map;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.apply.BizInpApplC1Dao;
import com.huaxia.opas.domain.apply.ApplyLifeCicle;
import com.huaxia.opas.domain.input.BizInpApplC1;
import com.huaxia.opas.domain.input.BizInpApplC2;

public class BizInpApplC1DaoImpl extends AbstractDAO implements BizInpApplC1Dao{

	private static final long serialVersionUID = -1561600119070421276L;
	
	private static final String NAMESPACES = "BizInpApplC1.";
	private static final String NAMESPACES1 = "ApplyLifeCicle.";

	@Override
	public BizInpApplC1 queryBizInpApplC1ByAppId(String appId) throws CoreException {
		// TODO Auto-generated method stub
		return getSqlMap().queryForObject(NAMESPACES+"queryBizInpApplC1ByAppId",appId);
	}

	@Override
	public void updateBizInfo(BizInpApplC1 bizInpApplC1) throws CoreException {
		// TODO Auto-generated method stub
		
		 getSqlMap().update(NAMESPACES+"updateBizInfo", bizInpApplC1);
	}

	@Override
	public BizInpApplC1 selectBizInpApplC1ByAppId(String appId) throws CoreException {
		return getSqlMap().queryForObject(NAMESPACES+"selectBizInpApplC1ByAppId",appId);
	}
	
	@Override
	public BizInpApplC2 findBiz2info(String appno) throws CoreException {
		// TODO Auto-generated method stub
		 return getSqlMap().queryForObject(NAMESPACES+"findBiz2info", appno);
	}
	@Override
	public int getPersonAge(String appId) throws CoreException {
		 return getSqlMap().queryForObject(NAMESPACES+"getPersonAge", appId);
	}
	@Override
	public String findYxqPd(String appId) throws CoreException {
		// TODO Auto-generated method stub
		 return getSqlMap().queryForObject(NAMESPACES+"findYxqPd", appId);
	}
	@Override
	public int findHaveCard(Map<String, Object> map) throws CoreException {
		 return getSqlMap().queryForObject(NAMESPACES+"findHaveCard", map);
	}
	@Override
	public int queryCount(Map<String, Object> map) throws CoreException {
		// TODO Auto-generated method stub
		return getSqlMap().queryForObject(NAMESPACES+"queryCount",map);
	}

	@Override
	public List<Map<Object,Object>> queryList(Map<String, Object> map, int curNum, int pageNum) throws CoreException {
		
		return  getSqlMap().queryForList(NAMESPACES+"queryList", map, curNum, pageNum);
	}

	@Override
	public Map queryDragAqqlyById(String appId) throws CoreException {
		// TODO Auto-generated method stub
		return  getSqlMap().queryForObject(NAMESPACES+"queryDragAqqlyById", appId);
	}

	@Override
	public List<ApplyLifeCicle> queryApplyLifeList(String appId) throws CoreException {
		// TODO Auto-generated method stub
		return getSqlMap().queryForList(NAMESPACES1+"queryApplyLifeList", appId);
	}
	@Override
	public String queryRefuseCodeList(Map map) throws CoreException {
		return   getSqlMap().queryForObject(NAMESPACES+"queryRefuseCodeList", map);
	}
	@Override
	public List queryInDataChecK(Map map,int cur,int page) throws CoreException {
		return   getSqlMap().queryForList(NAMESPACES+"queryInDataChecKList", map,cur,page);
	}
	
	@Override
	public List queryInDataChecK(Map map) throws CoreException {
		return   getSqlMap().queryForList(NAMESPACES+"queryInDataChecKList", map);
	}
	
	@Override
	public int countInDataChecK(Map map) throws CoreException {
		return   getSqlMap().queryForObject(NAMESPACES+"countInDataChecKList", map);
	}
	@Override
	public Map queryInDataChecK(String appId) throws CoreException {
		return   getSqlMap().queryForObject(NAMESPACES+"queryInDataChecK", appId);
	}
	//张立波
	@Override
	public BizInpApplC1 queryBizInpApplC1ByAppIdOrder(String appId) throws CoreException {
		return getSqlMap().queryForObject(NAMESPACES + "queryBizInpApplC1ByAppIdOrder", appId);
	}
	//安东 通过appid前十五位相同来更新主卡表信息
	@Override
	public void updateBizInpApplInfo(BizInpApplC1 bizInpApplC1) throws CoreException {
		 getSqlMap().update(NAMESPACES+"updateBizInpApplInfo", bizInpApplC1);
	}
	//added by jpc appid前十五位相同来更新C2表
	@Override
	public void updateBizInpApplC2Info(BizInpApplC2 bizInpApplC2) throws CoreException {
		 getSqlMap().update(NAMESPACES+"updateBizInpApplC2Info", bizInpApplC2);
	}

	@Override
	public String queryWorkCompany(String appId) {
		
		return getSqlMap().queryForObject(NAMESPACES+"queryWorkCompany",appId);
	}

	@Override
	public String queryRequestTypeByAppId(String appId) {
		return getSqlMap().queryForObject(NAMESPACES+"queryRequestTypeByAppId",appId);
	}
}
