package com.huaxia.opas.service.thirdparty.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.huaxia.opas.dao.thirdparty.AntDao;
import com.huaxia.opas.service.AbstractService;
import com.huaxia.opas.service.thirdparty.AntService;

/**
 * 蚂蚁服务实现类
 * 
 * @author zhiguo.li
 *
 */
public class AntServiceImpl extends AbstractService implements AntService , Serializable{

	private static final long serialVersionUID = -3530382807642666221L;

	@Resource(name = "antDao")
	private AntDao antDao;

	@Override
	public Map<String, String> selectSummaryInfo(String appId) {
		return getAntDao().selectSummaryInfo(appId);
	}

	public AntDao getAntDao() {
		return antDao;
	}

	public void setAntDao(AntDao antDao) {
		this.antDao = antDao;
	}

	@Override
	public Map<String, String> selectDetailInfo(String appId) {
		return getAntDao().selectDetailInfo(appId);
	}
	
	@Override
	public Map<String,String> selectDetailAppId(String appId) {
		return getAntDao().selectDetailAppId(appId);
	}

	@Override
	public List<Map<String, Object>> findZMRiskTypesGroup(Map paramMap) {
		return antDao.selectZMRiskTypesGroup(paramMap);
	}

	@Override
	public int findZMriskListMessageCount(Map paramMap) {
		return antDao.selectZMriskListMessageCount(paramMap);
	}

	@Override
	public List<Map<String, String>> findZMriskListMessageData(Map map) {
		return antDao.selectZMriskListMessageData(map);
	}

	@Override
	public Map<String, String> findThreePartiesTaskRequestInfo(Map map) {
		return antDao.selectThreePartiesTaskRequestInfo(map);
	}

	@Override
	public String selectIvsScoreByAppId(String appId) {
		return antDao.selectIvsScoreByAppId(appId);
	}

	@Override
	public List<Map<String, Object>> selectAntRiskOrder(Map map) {
		return antDao.selectAntRiskOrder(map);
	}

	
	@Override
	public List<Map<String, Object>> selectBrRistOrder1(Map map) {
		return antDao.selectBrRistOrder1(map);
	}

	@Override
	public List<Map<String, Object>> selectBrRistOrder2(Map map) {
		return antDao.selectBrRistOrder2(map);
	}

	@Override
	public String selectCustRiskLevel(Map map) {
		return antDao.selectCustRiskLevel(map);
	}

}
