package com.huaxia.opas.dao.compare.impl;

import java.util.Map;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.compare.RevCompInfoDao;
import com.huaxia.opas.domain.compare.RevCompInfo;

/**
 * 审查录入比对信息
 * 添加从反欺诈系统查询人行结果的方法
 * @author wangtao  2017-10-20
 * @version v1.1 初始版本v1.0  
 * @author xiebinxu 2017年2月25日
 */
public class RevCompInfoDaoImpl extends AbstractDAO implements RevCompInfoDao {

	private static final long serialVersionUID = -2786388888357864632L;

	private static final String NAMESPACES = "RevCompInfo.";

	@Override
	public int deleteByPrimaryKey(String appId) throws CoreException {
		return getSqlMap().delete(NAMESPACES + "deleteByPrimaryKey", appId);
	}

	@Override
	public int insert(RevCompInfo record) throws CoreException {
		return getSqlMap().insert(NAMESPACES + "insert", record);
	}

	@Override
	public int insertSelective(RevCompInfo record) throws CoreException {
		return getSqlMap().insert(NAMESPACES + "insertSelective", record);
	}

	@Override
	public RevCompInfo selectByPrimaryKey(String appId) throws CoreException {
		return getSqlMap().queryForObject(NAMESPACES + "selectByPrimaryKey", appId);
	}

	@Override
	public int updateByPrimaryKeySelective(RevCompInfo record) throws CoreException {
		return getSqlMap().update(NAMESPACES + "updateByPrimaryKeySelective", record);
	}

	@Override
	public int updateByPrimaryKey(RevCompInfo record) throws CoreException {
		return getSqlMap().update(NAMESPACES + "updateByPrimaryKey", record);
	}

	@Override
	public String selectMonByAppId(String appId) throws CoreException {
		return getSqlMap().queryForObject(NAMESPACES + "selectMonByAppId", appId);
	}

	@Override
	public Map<String,String> selectGongAnByAppId(Map map) throws CoreException {
		return getSqlMap().queryForObject(NAMESPACES + "selectGongAnByAppId", map);
	}

	@Override
	public int insertRemark(Map<String, Object> remarkMap) {
		return getSqlMap().insert(NAMESPACES + "insertRemark", remarkMap);
	}

	@Override
	public Map<String, String> selectPoliceMapByAppId(String appId) {
		return getSqlMap().queryForObject(NAMESPACES + "selectPoliceMapByAppId", appId);
	}

	@Override
	public Map<String, String> selectIvsMap(String appId) {
		return getSqlMap().queryForObject(NAMESPACES + "selectIvsMap", appId);
	}

	@Override
	public int deleteRemarkByAppId(String appId) {
		return getSqlMap().delete(NAMESPACES + "deleteRemarkByAppId", appId);
	}

//	@Override
//	public Map<String, String> selectFqzRiskDesc(Map<String, String> map2) {
//		return getSqlMap().queryForObject(NAMESPACES + "selectFqzRiskDesc", map2);
//	}

	@Override
	public Map<String,String> selectGjjMonth(String appId) {
		return getSqlMap().queryForObject(NAMESPACES + "selectGjjMonth", appId);
	}

	@Override
	public Map<String, String> selectCurrentStatus(String appId) {
		return getSqlMap().queryForObject(NAMESPACES + "selectCurrentStatus", appId);
	}

	@Override
	public Map<String, String> selectInsuredStatus(String appId) {
		return getSqlMap().queryForObject(NAMESPACES + "selectInsuredStatus", appId);
	}

	@Override
	public Map<String, String> selectPayStatus(String appId) {
		return getSqlMap().queryForObject(NAMESPACES + "selectPayStatus", appId);
	}

	@Override
	public Map<String, String> selectCurrNodeKey(String appId) {
		return getSqlMap().queryForObject(NAMESPACES + "selectCurrNodeKey", appId);
	}

	@Override
	public Map<String, Object> selectMonthsByAppId(String appId) throws CoreException{
		return getSqlMap().queryForObject(NAMESPACES + "selectMonthsByAppId", appId);
	}

}
