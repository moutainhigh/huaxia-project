package com.huaxia.opas.dao.allot.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.allot.AllotApplyAllotDao;
import com.huaxia.opas.domain.allot.AllotApplyAllot;
import com.huaxia.opas.domain.allot.AllotApplyAllotHis;

public class AllotApplyAllotDaoImpl extends AbstractDAO implements AllotApplyAllotDao {
	private static final long serialVersionUID = 2757582418545513971L;

	private static final String NAMESPACES = "AllotApplyAllot.";

	@Override
	public int deleteByPrimaryKey(String appId) {
		return getSqlMap().delete(NAMESPACES + "deleteByPrimaryKey", appId);
	}

	@Override
	public int insert(AllotApplyAllot record) {
		return getSqlMap().insert(NAMESPACES + "insert", record);
	}

	@Override
	public int insertSelective(AllotApplyAllot record) {
		return getSqlMap().insert(NAMESPACES + "insertSelective", record);
	}

	@Override
	public AllotApplyAllot selectByPrimaryKey(String appId) {
		return (AllotApplyAllot) (getSqlMap().queryForObject(NAMESPACES + "selectByPrimaryKey", appId));
	}
	@Override
	public List<AllotApplyAllot> selectByAppId_15(String appId) {
		Map<String,String> map = new HashMap<String,String>();
		map.put("appId", appId);
		return getSqlMap().queryForList(NAMESPACES + "selectByAppId_15", map);
	}
	
	@Override
	public AllotApplyAllot selectAllotAndAppProdByAppId(String appId) {
		return (AllotApplyAllot) (getSqlMap().queryForObject(NAMESPACES + "selectAllotAndAppProdByAppId", appId));
	}

	@Override
	public int updateSpFlagByAppId(Map map) {
		return getSqlMap().update(NAMESPACES + "updateSpFlagByAppId", map);
	}
	
	@Override
	public int updateByPrimaryKeySelective(AllotApplyAllot record) {
		return getSqlMap().update(NAMESPACES + "updateByPrimaryKeySelective", record);
	}

	@Override
	public int updateByPrimaryKey(AllotApplyAllot record) {
		return getSqlMap().update(NAMESPACES + "updateByPrimaryKey", record);
	}

	@Override
	public List<AllotApplyAllot> selectByUserId(String currOptUser) {
		return getSqlMap().queryForList(NAMESPACES + "selectByUserId", currOptUser);
	}

	@Override
	public AllotApplyAllot selectOneByUserId(Map<String,String> map) {
		return (AllotApplyAllot) (getSqlMap().queryForObject(NAMESPACES + "selectOneByUserId", map));
	}
	@Override
	public AllotApplyAllot selectZSOneByUserId(Map<String,String> map) {
		return (AllotApplyAllot) (getSqlMap().queryForObject(NAMESPACES + "selectZSOneByUserId", map));
	}
	@Override
	public AllotApplyAllot selectZSNextOneByUserId(Map<String,String> map) {
		return (AllotApplyAllot) (getSqlMap().queryForObject(NAMESPACES + "selectZSNextOneByUserId", map));
	}
	@Override
	public String selectZSCurrentNumByUserId(Map<String,String> map) {
		return (String) (getSqlMap().queryForObject(NAMESPACES + "selectZSCurrentNumByUserId", map));
	}
	@Override
	public List<AllotApplyAllot> selectAllotByCondition(Map map) {
		return getSqlMap().queryForList(NAMESPACES + "selectAllotByCondition", map);
	}
	@Override
	public List<AllotApplyAllot> getSpAllotByCondition(Map map) {
		return getSqlMap().queryForList(NAMESPACES + "getSpAllotByCondition", map);
	}
	@Override
	public List<AllotApplyAllot> querySingleInfo(String appId) throws CoreException {
		return getSqlMap().queryForList(NAMESPACES + "querySingleInfo", appId);
	}

	@Override
	public List<AllotApplyAllot> queryStaffJobDetailByCurrNode(String currNode) throws CoreException {
		return getSqlMap().queryForList(NAMESPACES + "queryStaffJobDetailByCurrNode", currNode);
	}


	@Override
	public int updateAllotForExamine(Map<String, Object> updateAllotMap) {
		return getSqlMap().update(NAMESPACES + "updateAllotForExamine", updateAllotMap);
	}
	
	@Override
	public List<AllotApplyAllot> selectAllotListByAppId(String appId) {
		return getSqlMap().queryForList(NAMESPACES + "selectAllotListByAppId",appId);
	}

	@Override
	public List<AllotApplyAllot> selectAllotForExamineByRob(Map<String, Object> conditionMap) {
		return getSqlMap().queryForList(NAMESPACES + "selectAllotForExamineByRob",conditionMap);
	}

	@Override
	public Map<String,Object> selectDelStatusByAppId(String appId) {
		return getSqlMap().queryForObject(NAMESPACES + "selectDelStatusByAppId",appId);
	}
	
	@Override
	public List<AllotApplyAllot> selectApplyByUserId(Map map) {
		return getSqlMap().queryForList(NAMESPACES + "selectApplyByUserId", map);
	}

	@Override
	public int updateSecDecisionFlag1(String appId) {
		return getSqlMap().update(NAMESPACES + "updateSecDecisionFlag1", appId);
	}
	
	@Override
	public int updateSecDecisionFlag0(String appId) {
		return getSqlMap().update(NAMESPACES + "updateSecDecisionFlag0", appId);
	}

	@Override
	public String selectSecDecisionFlag(String appId) {
		return getSqlMap().queryForObject(NAMESPACES + "selectSecDecisionFlag", appId);
	}

	@Override
	public List<Map<String, String>> queryForeignCheckByAppId(String appId) {
		return getSqlMap().queryForList(NAMESPACES + "queryForeignCheckByAppId",appId);
	}

}