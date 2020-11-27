package com.huaxia.opas.dao.sysparm.impl;

import java.util.ArrayList;
import java.util.List;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.sysparm.TargetCompanyDao;
import com.huaxia.opas.domain.sysparm.TargetcompanyList;

public class TargetcompanyDaoImpl extends AbstractDAO implements TargetCompanyDao {

	private static final long serialVersionUID = 5757582918595513970L;
	
	private static final String NAMESPACES = "TargetcompanyList.";

	@Override
	public int insert(TargetcompanyList targetcompanylist) {
		return getSqlMap().insert(NAMESPACES + "insert", targetcompanylist);
	}

	@Override
	public int insertSelective(TargetcompanyList targetcompanylist) {
		return getSqlMap().insert(NAMESPACES + "insertSelective", targetcompanylist);
	}

	@Override
	public int deleteByPrimaryKey(String autoId) {
		TargetcompanyList targetcompanylist = new TargetcompanyList();
		targetcompanylist.setAutoId(autoId);
		return getSqlMap().delete(NAMESPACES + "deleteByPrimaryKey", autoId);
	}

	@Override
	public int updateByPrimaryKey(TargetcompanyList targetcompanylist) {
		return getSqlMap().update(NAMESPACES + "updateByPrimaryKey", targetcompanylist);
	}

	@Override
	public int updateByPrimaryKeySelective(TargetcompanyList targetcompanylist) {
		return getSqlMap().update(NAMESPACES + "updateByPrimaryKeySelective", targetcompanylist);
	}

	@Override
	public TargetcompanyList selectByPrimaryKey(String autoId) {
		TargetcompanyList targetcompanylist = new TargetcompanyList();
		targetcompanylist.setAutoId(autoId);
		return (getSqlMap().queryForObject(NAMESPACES + "selectByPrimaryKey", autoId));
	}

	
	//shihuan 2017-03-21 目标企业列表查询 
	@Override
	public List<TargetcompanyList> queryTargetCompany(TargetcompanyList targetcompanylist, int curNum, int pageNum) {

		List<TargetcompanyList> list = new ArrayList<TargetcompanyList>();

		list = getSqlMap().queryForList(NAMESPACES + "queryTargetCompany", targetcompanylist, curNum, pageNum);

		return list;
	}

	//shihuan 2017-03-21 目标企业列表查询 条数
	@Override
	public int queryTargetCompanyCount(TargetcompanyList targetcompanylist) {
		return getSqlMap().queryForObject(NAMESPACES + "queryTargetCompanyCount", targetcompanylist);
	}
	
	//shihuan 2017-03-21  目标企业批量启动停用修改状态
	@Override
	public int updateStartStatus(String autoId) throws CoreException {
		return getSqlMap().update(NAMESPACES + "updateStartStatus", autoId);
	}
	
	//shihuan 2017-03-21  目标企业批量启动停用修改状态
	@Override
	public int updateStopStatus(String autoId) throws CoreException {
		return getSqlMap().update(NAMESPACES + "updateStopStatus", autoId);
	}
	
	//shihuan 2017-03-21  添加历史修改记录表
	@Override
	public int insertHisSelective(TargetcompanyList targetcompanylist) {
		return getSqlMap().insert(NAMESPACES + "insertHisSelective", targetcompanylist);
	}
	
	//shihuan 2017-03-21  历史目标企业列表查询 
	@Override
	public List<TargetcompanyList> queryTargetCompanyHistory(String autoId, int curNum, int pageNum) {

		List<TargetcompanyList> list = new ArrayList<TargetcompanyList>();

		list = getSqlMap().queryForList(NAMESPACES + "queryTargetCompanyHistory", autoId, curNum, pageNum);

		return list;
	}

	//shihuan 2017-03-21  历史目标企业列表查询 条数
	@Override
	public int queryTargetCompHistoryCount(String autoId) {
		return getSqlMap().queryForObject(NAMESPACES + "queryTargetCompHistoryCount", autoId);
	}
	
	//shihuan 2017-03-21  目标企业名单批导入文件
	@Override
	public int insertTargetComUpload(List<TargetcompanyList> obj) throws CoreException {
		return getSqlMap().insert(NAMESPACES + "insertTargetComUpload", obj);
	}
	
	//shihuan 2017-03-21  历史目标企业列表查询 条数
	@Override
	public int queryCompanyName(String companyName) {
		return getSqlMap().queryForObject(NAMESPACES + "queryCompanyName", companyName);
	}
}
