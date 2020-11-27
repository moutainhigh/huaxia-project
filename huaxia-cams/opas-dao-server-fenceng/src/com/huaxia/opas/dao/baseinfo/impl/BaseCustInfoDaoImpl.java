package com.huaxia.opas.dao.baseinfo.impl;

import java.util.List;
import java.util.Map;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.baseinfo.BaseCustInfoDao;
import com.huaxia.opas.domain.baseinfo.BaseCustInfo;

public class BaseCustInfoDaoImpl extends AbstractDAO implements BaseCustInfoDao {

	private static final long serialVersionUID = 898708931767583193L;
	private static final String NAMESPACES = "BaseCustInfo.";
	//执行插入操作方法
	public int insertCustInfo(BaseCustInfo baseCustInfo) throws CoreException {
		return getSqlMap().insert(NAMESPACES+"insertCustInfo",baseCustInfo);
	}
	//查询客户基本信息方法
	public Map<String, Object> queryFromBizInpApplC1(String appId) {
		return getSqlMap().queryForObject(NAMESPACES+"queryCustInfo",appId);
	}
	
/*	@Override
	public int queryFromTeamList(String c4Apbatch) {
		return getSqlMap().queryForObject(NAMESPACES+"queryFromTeamList",c4Apbatch);
	}
	@Override
	public int queryFromTeamDealList(String c4Apbatch) {
		return getSqlMap().queryForObject(NAMESPACES+"queryFromTeamDealList",c4Apbatch);
	}*/
	//查询公安状态方法
	public Map<String, Object> queryPoliceStatus(String idNbr) {
		return getSqlMap().queryForObject(NAMESPACES+"queryPoliceStatus",idNbr);
	}
/*	@Override
	public int queryIsHaveCard(String appId) {
		return getSqlMap().queryForObject(NAMESPACES+"queryIsHaveCard",appId);
	}
*/
	/**
	 * APPId查询 客户信息
	 */
	public String selectCustInfo(String appId) throws CoreException {
		return getSqlMap().queryForObject(NAMESPACES+"selectCustInfo",appId);
	}
	/**
	 * 更新客户信息
	 */
	public int updateCustInfo(BaseCustInfo baseCustInfo) throws CoreException {
		return getSqlMap().update(NAMESPACES+"updateCustInfo", baseCustInfo);
	}
	/**
	 * 更新客户信息根据app_id 前十五位相同更新同时更新套卡信息
	 */
	public void updateBaseCustInfo(BaseCustInfo baseCustInfo){
		getSqlMap().update(NAMESPACES+"updateBaseCustInfo", baseCustInfo);
	}
	/**
	 * 查询客户姓名 和身份证号码
	 */
	@Override
	public Map<String, String> queryBizInpApplC1(String appId) {
		return getSqlMap().queryForObject(NAMESPACES+"queryBizInpApplC1",appId);
	}
	@Override
	public List<Map<String, String>> queryBaseCustByIdNbr(Map<String, String> matchingCard) {
		return getSqlMap().queryForList(NAMESPACES+"queryBaseCustByIdNbr",matchingCard);
	}
}
