package com.huaxia.opas.service.apply.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.apply.BizInpApplC1Dao;
import com.huaxia.opas.domain.apply.ApplyLifeCicle;
import com.huaxia.opas.domain.input.BizInpApplC1;
import com.huaxia.opas.domain.input.BizInpApplC2;
import com.huaxia.opas.service.AbstractService;
import com.huaxia.opas.service.apply.ApplyService;

/**
 * 申请表信息服务
 * 
 * @author jiangming.yang
 *
 */
public class ApplyServiceImpl extends AbstractService implements ApplyService,Serializable  {

	private static final long serialVersionUID = -4781493556950791586L;
	@Resource(name="bizInpApplC1Dao")
	private BizInpApplC1Dao bizInpApplC1Dao;
	@Override
	public int save(BizInpApplC1 t) throws CoreException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int remove(BizInpApplC1 t) throws CoreException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(BizInpApplC1 t) throws CoreException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public BizInpApplC1 get(BizInpApplC1 t) {
		try {
			return bizInpApplC1Dao.queryBizInpApplC1ByAppId(t.getAppId());
		} catch (CoreException e) {
		}
		return null;
	}

	@Override
	public void updateBizInfo(BizInpApplC1 bizInpApplC1) throws CoreException {
		bizInpApplC1Dao.updateBizInfo(bizInpApplC1);
		
	}

	@Override
	public BizInpApplC2 findBiz2info(String appno) throws CoreException {
		try {
			return bizInpApplC1Dao.findBiz2info(appno);
		} catch (CoreException e) {
		}
		return null;
	}

	@Override
	public int queryCount(Map<String, Object> map) throws CoreException {
		//bizInpApplC1Dao.queryCount(map);
		return bizInpApplC1Dao.queryCount(map);
	}

	@Override
	public List<Map<Object,Object>> queryList(Map<String, Object> map, int curNum, int pageNum) throws CoreException {
		
		return bizInpApplC1Dao.queryList(map,curNum,pageNum);
	}

	@Override
	public Map queryDragAqqlyById(String appId) throws CoreException {
		try {
			return bizInpApplC1Dao.queryDragAqqlyById(appId);
		} catch (CoreException e) {
		}
		return null;
	}

	@Override
	public List<ApplyLifeCicle> queryApplyLifeList(String appId) throws CoreException {
		try {
			return bizInpApplC1Dao.queryApplyLifeList(appId);
		} catch (CoreException e) {
		}
		return null;
	}

	

}
