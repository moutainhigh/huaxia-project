package com.huaxia.opas.service.checking.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.huaxia.opas.dao.checking.QualityCheckingDao;
import com.huaxia.opas.domain.checking.QualityChecking;
import com.huaxia.opas.domain.sysparm.ApproveReasonCode;
import com.huaxia.opas.domain.system.ApUser;
import com.huaxia.opas.service.checking.QualityCheckingService;

public class QualityCheckingServiceImpl implements QualityCheckingService {
	@Resource(name="qualityCheckingDao")
	private QualityCheckingDao qualityCheckingDao;

	@Override
	public int queryOperatorCount(Map<String, Object> map) {
		return qualityCheckingDao.queryOperatorCount(map);
	}

	@Override
	public List<ApUser> queryOperatorList(Map<String, Object> map,int curNum,int pageNum) {
		return qualityCheckingDao.queryOperatorList(map,curNum,pageNum);
	}

	@Override
	public int queryQualityCheckingCount(Map<String, Object> dataMap) {
		return qualityCheckingDao.queryQualityCheckingCount(dataMap);
	}

	@Override
	public List<QualityChecking> queryQualityCheckingList(Map<String, Object> dataMap, int curNum, int pageNum) {
		return qualityCheckingDao.queryQualityCheckingList(dataMap,curNum,pageNum);
	}

	@Override
	public int saveQualityChecking(QualityChecking qualityChecking) {
		return qualityCheckingDao.saveQualityChecking(qualityChecking);
	}

	@Override
	public int queryQualityCheckingCountByAppId(String appId) {
		return qualityCheckingDao.queryQualityCheckingCountByAppId(appId);
	}

//	@Override
//	public int updateQualityChecking(QualityChecking qualityChecking) {
//		return qualityCheckingDao.updateQualityChecking(qualityChecking);
//	}

	@Override
	public QualityChecking selectQualityChecking(String appId) {
		return qualityCheckingDao.selectQualityChecking(appId);
	}

	@Override
	public String queryUserOrgNo(String userUuid) {
		return qualityCheckingDao.queryUserOrgNo(userUuid);
	}

	@Override
	public String queryStopFlag(String appId) {
		return qualityCheckingDao.queryStopFlag(appId);
	}

	@Override
	public List<QualityChecking> queryQualityCheckingListByDate(Map<String, Object> paramMap) {
		return qualityCheckingDao.queryQualityCheckingListByDate(paramMap);
	}

	@Override
	public Map<String, String> queryPerInspected(String appId) {
		return qualityCheckingDao.queryPerInspected(appId);
	}

//	@Override
//	public int updateQualityCheckingWithoutStopFlag(QualityChecking qualityChecking) {
//		return qualityCheckingDao.updateQualityCheckingWithoutStopFlag(qualityChecking);
//	}

	@Override
	public List<ApproveReasonCode> queryRefuseCode() {
		return qualityCheckingDao.queryRefuseCode();
	}

	@Override
	public List<ApproveReasonCode> queryBreakCode() {
		return qualityCheckingDao.queryBreakCode();
	}

	@Override
	public Map<String, Object> querySomeFlagByAppId(String appId) {
		return qualityCheckingDao.querySomeFlagByAppId(appId);
	}

	@Override
	public String queryUserUuid(String appId) {
		return qualityCheckingDao.queryUserUuid(appId);
	}

	@Override
	public List<String> queryOperatorAllList(Map<String, Object> map) {
		return qualityCheckingDao.queryOperatorAllList(map);
	}

	@Override
	public List<String> queryStopFlagByAppId(String appId) {
		return qualityCheckingDao.selectStopFlagByAppId(appId);
	}

	@Override
	public String queryCurrOptUserByNode(Map<String,String> map) {
		return qualityCheckingDao.queryCurrOptUserByNode(map);
	}

	@Override
	public List<String> queryListOptUserByNode(Map<String, String> map) {
		return qualityCheckingDao.queryListOptUserByNode(map);
	}

	@Override
	public List<String> queryListRoleCodeByUserCode(String userId) {
		return qualityCheckingDao.queryListRoleCodeByUserCode(userId);
	}
	
}
