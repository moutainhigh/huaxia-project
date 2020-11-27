package com.huaxia.opas.dao.checking.impl;

import java.util.List;
import java.util.Map;

import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.checking.QualityCheckingDao;
import com.huaxia.opas.domain.checking.QualityChecking;
import com.huaxia.opas.domain.sysparm.ApproveReasonCode;
import com.huaxia.opas.domain.system.ApUser;

public class QualityCheckingDaoImpl extends AbstractDAO implements QualityCheckingDao {

	private static final long serialVersionUID = -8735601804342534052L;
	private static final String NAMESPACES = "QualityChecking.";

	@Override
	public int queryOperatorCount(Map<String,Object> map) {
		return getSqlMap().queryForObject(NAMESPACES+"queryOperatorCount",map);
	}

	@Override
	public List<ApUser> queryOperatorList(Map<String,Object> map,int curNum,int pageNum) {
		return getSqlMap().queryForList(NAMESPACES+"queryOperatorList",map,curNum,pageNum);
	}

	@Override
	public int queryQualityCheckingCount(Map<String, Object> dataMap) {
		return getSqlMap().queryForObject(NAMESPACES+"queryQualityCheckingCount",dataMap);
	}

	@Override
	public List<QualityChecking> queryQualityCheckingList(Map<String, Object> dataMap, int curNum, int pageNum) {
		return getSqlMap().queryForList(NAMESPACES+"queryQualityCheckingList",dataMap,curNum,pageNum);
	}

	@Override
	public int saveQualityChecking(QualityChecking qualityChecking) {
		return getSqlMap().insert(NAMESPACES+"saveQualityChecking",qualityChecking);
	}

	@Override
	public int queryQualityCheckingCountByAppId(String appId) {
		return getSqlMap().queryForObject(NAMESPACES+"queryQualityCheckingCountByAppId",appId);
	}

//	@Override
//	public int updateQualityChecking(QualityChecking qualityChecking) {
//		return getSqlMap().update(NAMESPACES+"updateQualityChecking",qualityChecking);
//	}

	@Override
	public QualityChecking selectQualityChecking(String appId) {
		return getSqlMap().queryForObject(NAMESPACES+"selectQualityChecking",appId);
	}

	@Override
	public String queryUserOrgNo(String userUuid) {
		return getSqlMap().queryForObject(NAMESPACES+"queryUserOrgNo",userUuid);
	}

	@Override
	public String queryStopFlag(String appId) {
		return getSqlMap().queryForObject(NAMESPACES+"queryStopFlag",appId);
	}

	@Override
	public List<QualityChecking> queryQualityCheckingListByDate(Map<String, Object> paramMap) {
		return getSqlMap().queryForList(NAMESPACES+"queryQualityCheckingListByDate",paramMap);
	}

	@Override
	public Map<String, String> queryPerInspected(String appId) {
		return getSqlMap().queryForObject(NAMESPACES+"queryPerInspected",appId);
	}

//	@Override
//	public int updateQualityCheckingWithoutStopFlag(QualityChecking qualityChecking) {
//		return getSqlMap().update(NAMESPACES+"updateQualityCheckingWithoutStopFlag",qualityChecking);
//	}

	@Override
	public List<ApproveReasonCode> queryRefuseCode() {
		return getSqlMap().queryForList(NAMESPACES+"queryRefuseCode");
	}

	@Override
	public List<ApproveReasonCode> queryBreakCode() {
		return getSqlMap().queryForList(NAMESPACES+"queryBreakCode");
	}

	@Override
	public Map<String, Object> querySomeFlagByAppId(String appId) {
		return getSqlMap().queryForObject(NAMESPACES+"querySomeFlagByAppId",appId);
	}

	@Override
	public String queryUserUuid(String appId) {
		return getSqlMap().queryForObject(NAMESPACES+"queryUserUuid",appId);
	}

	@Override
	public List<String> queryOperatorAllList(Map<String, Object> map) {
		return getSqlMap().queryForList(NAMESPACES+"queryOperatorAllList",map);
	}
	
	@Override
	public List<String> selectStopFlagByAppId(String appId) {
		return getSqlMap().queryForList(NAMESPACES+"selectStopFlagByAppId",appId);
	}

	@Override
	public String queryCurrOptUserByNode(Map<String,String> map) {
		return  getSqlMap().queryForObject(NAMESPACES+"queryCurrOptUserByNode",map);
	}

	@Override
	public List<String> queryListOptUserByNode(Map<String, String> map) {
		return  getSqlMap().queryForList(NAMESPACES+"queryListOptUserByNode",map);
	}

	@Override
	public List<String> queryListRoleCodeByUserCode(String userId) {
		return getSqlMap().queryForList(NAMESPACES+"queryListRoleCodeByUserCode", userId);
	}
}
