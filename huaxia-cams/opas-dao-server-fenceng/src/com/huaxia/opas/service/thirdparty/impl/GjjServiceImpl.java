package com.huaxia.opas.service.thirdparty.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.huaxia.opas.dao.thirdparty.GjjDao;
import com.huaxia.opas.service.AbstractService;
import com.huaxia.opas.service.thirdparty.GjjService;

public class GjjServiceImpl extends AbstractService implements GjjService , Serializable{

	private static final long serialVersionUID = 8369660705258856425L;

	@Resource(name = "gjjDao")
	private GjjDao gjjDao;
	
	@Override
	public Map<String, String> selectGjjInfo(String appId) {
		return gjjDao.queryGjjInfo(appId);
	}
	
	@Override
	public Map<String, String> selectGjjBriefInfo(String appId) {
		return gjjDao.queryGjjBriefInfo(appId);
	}

	@Override
	public List<Map<String, String>> queryGjjDetail(String appId) {
		return gjjDao.queryGjjDetail(appId);
	}

	@Override
	public List<Map<String, String>> queryGjjBriefAnalysis(String appId) {
		return gjjDao.queryGjjBriefAnalysis(appId);
	}

	@Override
	public List<Map<String, String>> queryGjjDetailAnalysis(String appId) {
		return gjjDao.queryGjjDetailAnalysis(appId);
	}

	@Override
	public List<Map<String, String>> queryCompanyAnalData(String appId) {
		return gjjDao.queryCompanyAnalData(appId);
	}

	@Override
	public List<Map<String, String>> queryGjjLoadBrief(String appId) {
		return gjjDao.queryGjjLoadBrief(appId);
	}

	@Override
	public List<Map<String, String>> queryGjjLoadDetail(String appId) {
		return gjjDao.queryGjjLoadDetail(appId);
	}

	

}
