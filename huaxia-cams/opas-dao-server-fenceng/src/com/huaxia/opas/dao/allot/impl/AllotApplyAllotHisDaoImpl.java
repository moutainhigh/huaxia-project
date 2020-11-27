package com.huaxia.opas.dao.allot.impl;

import java.util.List;
import java.util.Map;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.allot.AllotApplyAllotHisDao;
import com.huaxia.opas.domain.allot.AllotApplyAllotHis;

public class AllotApplyAllotHisDaoImpl extends AbstractDAO implements AllotApplyAllotHisDao {
	
	private static final long serialVersionUID = 2757582418545513971L;

	private static final String NAMESPACES = "AllotApplyAllotHis.";

	@Override
	public int deleteByPrimaryKey(String autoId) {
		return getSqlMap().delete(NAMESPACES + "deleteByPrimaryKey", autoId);
	}

	@Override
	public int insert(AllotApplyAllotHis record) {
		return getSqlMap().insert(NAMESPACES + "insert", record);
	}

	@Override
	public int insertSelective(AllotApplyAllotHis record) {
		return getSqlMap().insert(NAMESPACES + "insertSelective", record);
	}

	@Override
	public AllotApplyAllotHis selectByPrimaryKey(String autoId) {
		return (AllotApplyAllotHis) (getSqlMap().queryForObject(NAMESPACES + "selectByPrimaryKey", autoId));
	}
	@Override
	public AllotApplyAllotHis selectLastOneByAppId(String appId) {
		return (AllotApplyAllotHis) (getSqlMap().queryForObject(NAMESPACES + "selectLastOneByAppId", appId));
	}
	@Override
	public int updateByPrimaryKeySelective(AllotApplyAllotHis record) {
		return getSqlMap().update(NAMESPACES + "updateByPrimaryKeySelective", record);
	}

	@Override
	public int updateByPrimaryKey(AllotApplyAllotHis record) {
		return getSqlMap().update(NAMESPACES + "updateByPrimaryKey", record);
	}

	@Override
	// zhanglibo
	public int queryBizInpApplHisCount(Map map) throws CoreException {
		return Integer
				.parseInt(String.valueOf(getSqlMap().queryForObject(NAMESPACES + "queryBizInpApplHisCount", map)));
	}

	@Override
	public List<AllotApplyAllotHis> queryDetailBizInpApplHisByCurrOptUser(Map map) throws CoreException {
		return getSqlMap().queryForList(NAMESPACES + "queryDetailBizInpApplHisByCurrOptUser", map);
	}

	@Override
	public int selectByCurrNodeAndUserCode(Map map) {
		return Integer
				.parseInt(String.valueOf(getSqlMap().queryForObject(NAMESPACES + "selectByCurrNodeAndUserCode", map)));
	}

	@Override
	public List<AllotApplyAllotHis> querySingleInfoHis(String appId) throws CoreException {
		return getSqlMap().queryForList(NAMESPACES + "querySingleInfoHis", appId);
	}

	@Override
	public AllotApplyAllotHis queryAllotApplyAllotHisByAppId(Map map) throws CoreException {
		return (AllotApplyAllotHis) (getSqlMap().queryForObject(NAMESPACES + "queryAllotApplyAllotHisByAppId", map));
	}

	@Override
	public AllotApplyAllotHis queryAllotApplyAllotHisNozjByAppId(Map map) throws CoreException {
		return (AllotApplyAllotHis) (getSqlMap().queryForObject(NAMESPACES + "queryAllotApplyAllotHisNozjByAppId",
				map));
	}
	@Override
	public AllotApplyAllotHis queryAllotApplyAllotHisSPByAppId(Map map) throws CoreException {
		return (AllotApplyAllotHis) (getSqlMap().queryForObject(NAMESPACES + "queryAllotApplyAllotHisSPByAppId",
				map));
	}
	@Override
	public List<AllotApplyAllotHis> queryStaffJobDetailByCurrNodeHis(String currNode) throws CoreException {
		return getSqlMap().queryForList(NAMESPACES + "queryStaffJobDetailByCurrNodeHis", currNode);
	}
	
	@Override
	public int insertCopyOpasAllotToAllotHis(Map map) {
		return getSqlMap().insert(NAMESPACES + "insertCopyOpasAllotToAllotHis", map);
	}
	
	@Override
	public int insertHisBatch(List<String> lifeList) {
		return getSqlMap().insert(NAMESPACES + "insertHisBatch", lifeList);
	}
	
	@Override
	public int executeOpasPoAllot(Map map) {
		return getSqlMap().insert(NAMESPACES + "executeOpasPoAllot", map);
	}
	
	@Override
	public Map<String, String> queryHisByMap(Map map) {
		return getSqlMap().queryForObject(NAMESPACES + "queryHisByMap", map);
	}

	@Override
	public int insertAutoApply(Map map) {
		return getSqlMap().insert(NAMESPACES + "insertAutoApply", map);
	}
}