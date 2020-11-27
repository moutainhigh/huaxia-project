package com.huaxia.opas.dao.collect.impl;

import java.util.Map;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.collect.InfoCollectDao;
import com.huaxia.opas.domain.audit.ApprovaInfoSupp;
import com.huaxia.opas.domain.audit.FicoSdBlaze;
import com.huaxia.opas.domain.collect.InfoCollect;

/**
 * 录入
 * 
 * @author xiebinxu 2017年3月3日
 */
public class InfoCollectDaoImpl extends AbstractDAO implements InfoCollectDao {

	private static final long serialVersionUID = -8888888888357864632L;

	private static final String NAMESPACES = "InfoCollect.";

	@Override
	public InfoCollect queryInfoCollect(Map map) throws CoreException {
		return getSqlMap().queryForObject(NAMESPACES + "queryInfoCollect", map);
	}

	@Override
	public ApprovaInfoSupp queryApprovaInfoSupp(Map s) throws CoreException {
		return getSqlMap().queryForObject(NAMESPACES + "selectByPrimaryKey", s);
	}

	@Override
	public int insertApprovaInfoSupp(ApprovaInfoSupp s) throws CoreException {
		return getSqlMap().insert(NAMESPACES + "insert", s);
	}

	@Override
	public int updateApprovaInfoSupp(ApprovaInfoSupp s) throws CoreException {
		return getSqlMap().update(NAMESPACES + "updateByPrimaryKey", s);
	}

	@Override
	public int deleteApprovaInfoSupp(String s) throws CoreException {
		return getSqlMap().delete(NAMESPACES + "deleteByPrimaryKey", s);
	}

//	@Override
//	public Map<String, Object> selectStandardCardReturnTableDataByAppid(Map paramMap) {
//		return getSqlMap().queryForObject(NAMESPACES + "selectStandardCardReturnTableDataByAppid", paramMap);
//	}

	@Override
	public Map<String, String> querySdBlaze(Map<String,Object> paramMap) {
		return getSqlMap().queryForObject(NAMESPACES + "querySdBlaze",paramMap);
	}

	@Override
	public FicoSdBlaze querySdBlaze1(Map<String,Object> paramMap) {
		return getSqlMap().queryForObject(NAMESPACES + "querySdBlaze1",paramMap);
	}

	@Override
	public Map<String, String> selectNameAndCardMap(String appId) {
		return getSqlMap().queryForObject(NAMESPACES + "selectNameAndCardMap",appId);
	}

	@Override
	public Map<String, Object> querySdBlazeBatch(Map<String,Object> paramMap) {
		return getSqlMap().queryForObject(NAMESPACES + "querySdBlaze",paramMap);
	}

	@Override
	public int insertInfoCollect(Map<String, String> blazeMap) {
		return getSqlMap().insert(NAMESPACES + "insertInfoCollect",blazeMap);
	}

	@Override
	public int queryCountByIndustryCode(String industryCode) {
		return getSqlMap().queryForObject(NAMESPACES + "queryCountByIndustryCode", industryCode);
	}

	@Override
	public Map<String, String> selectRegionalReserveFundByAppid(String appId) {
		return getSqlMap().queryForObject(NAMESPACES + "selectRegionalReserveFundByAppid",appId);
	}

	@Override
	public int queryInfoCollectByAppid(String appId) {
		return getSqlMap().queryForObject(NAMESPACES + "queryInfoCollectByAppid",appId);
	}

	@Override
	public int updateInfoCollect(Map<String, String> blazeMap) {
		return getSqlMap().update(NAMESPACES+"updateInfoCollect",blazeMap);
	}
}
