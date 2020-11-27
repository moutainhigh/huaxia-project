package com.huaxia.opas.service.thirdparty.impl;

import java.io.Serializable;
import java.util.Map;

import javax.annotation.Resource;

import com.huaxia.opas.dao.thirdparty.EducationDao;
import com.huaxia.opas.service.AbstractService;
import com.huaxia.opas.service.thirdparty.EducationService;

/**
 * 学历服务实现类
 * 
 * @author zhiguo.li
 *
 */
public class EducationServiceImpl extends AbstractService implements EducationService , Serializable{

	private static final long serialVersionUID = 4709015848043866850L;

	@Resource(name = "educationDao")
	private EducationDao educationDao;

	@Override
	public Map<String, String> selectSummaryInfo(String appId) {
		return getEducationDao().selectSummaryInfo(appId);
	}
	
	@Override
	public String querySummaryInfoQuery(String appId) {
		return getEducationDao().querySummaryInfoQuery(appId);
	}

	public EducationDao getEducationDao() {
		return educationDao;
	}

	public void setEducationDao(EducationDao educationDao) {
		this.educationDao = educationDao;
	}

	@Override
	public Map<String, String> selectDetailInfo(String appId) {
		return  getEducationDao().selectDetailInfo(appId);
	}

}
