package com.huaxia.opas.dao.sysparm.impl;

import java.util.List;
import java.util.Map;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.domain.sysparm.FraudCode;
import com.huaxia.opas.dao.sysparm.FraudCodeDao;

/**
 * 欺诈结果原因码DAO层实体类
 * @author liudi
 * @since 2017-02-26
 * @version 1.0
 */
public class FraudCodeDaoImpl extends AbstractDAO implements FraudCodeDao{

	/**
	 * 
	 */
	private static final long serialVersionUID = 861885855528611675L;

	/**
	 * 
	 */
	private static final String NAMESPACES = "FraudCode.";
	//添加状态为Start
	@Override
	public int saveFraudCodeStart(FraudCode fraudCode) throws CoreException {
		return getSqlMap().insert(NAMESPACES + "insertFraudCode1", fraudCode);
	}
	//添加状态为End
	@Override
	public int saveFraudCodeEnd(FraudCode fraudCode) throws CoreException {
		return getSqlMap().insert(NAMESPACES + "insertFraudCode2", fraudCode);
	}
	//修改
	@Override
	public int updateFraudCode(FraudCode fraudCode) throws CoreException {
		return getSqlMap().update(NAMESPACES + "updateFraudCode", fraudCode);
	}
	//删除
	@Override
	public int deleteFraudCode(String autoID) throws CoreException {
		return getSqlMap().delete(NAMESPACES + "deleteFraudCodeById", autoID);
	}
	//查询
	@Override
	public List<FraudCode> queryFraudCode(FraudCode fraudCode, int curNum, int pageNum) throws CoreException {
		return getSqlMap().queryForList(NAMESPACES + "queryFraudCode", fraudCode, curNum, pageNum);
	}
	//件数查询
	@Override
	public int queryFraudCodeCount(FraudCode fraudCode) throws CoreException {
		return getSqlMap().queryForObject(NAMESPACES + "queryFraudCodeCount", fraudCode);
	}
	//点击查询后，触发查询和排序功能
	@Override
	public List<FraudCode> queryFraudCode(Map<String, Object> params, int curNum, int pageNum) throws CoreException {
		return getSqlMap().queryForList(NAMESPACES + "queryFraudCode2", params, curNum, pageNum);
	}
	//查询是否重复
	@Override
	public FraudCode queryFraudCode(FraudCode fraudCode) throws CoreException {
		return getSqlMap().queryForObject(NAMESPACES + "queryFraudCode3", fraudCode);
	}
	@Override
	public String selectFraudCode(String autoId) {
		return getSqlMap().queryForObject(NAMESPACES + "selectFraudCode", autoId);
	}
	@Override
	public int updateFraudCodeWithoutStatus(FraudCode fraudCode) {
		return getSqlMap().update(NAMESPACES + "updateFraudCodeWithoutStatus",fraudCode);
	}

}
