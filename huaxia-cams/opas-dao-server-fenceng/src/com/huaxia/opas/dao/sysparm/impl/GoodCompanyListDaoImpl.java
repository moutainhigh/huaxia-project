package com.huaxia.opas.dao.sysparm.impl;

import java.util.ArrayList;
import java.util.List;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.sysparm.GoodCompanyListDao;
import com.huaxia.opas.domain.sysparm.GoodcompanyList;
import com.huaxia.opas.domain.sysparm.GoodcompanyListHistory;

public class GoodCompanyListDaoImpl extends AbstractDAO implements GoodCompanyListDao {

	private static final long serialVersionUID = 5757582918595513970L;
	
	private static final String NAMESPACES = "GoodcompanyList.";

	@Override
	public int insert(GoodcompanyList goodcompanylist) {
		return getSqlMap().insert(NAMESPACES + "insert", goodcompanylist);
	}

	@Override
	public int insertSelective(GoodcompanyList goodcompanylist) {
		return getSqlMap().insert(NAMESPACES + "insertSelective", goodcompanylist);
	}

	@Override
	public int deleteByPrimaryKey(String autoId) {
		GoodcompanyList goodcompanylist = new GoodcompanyList();
		goodcompanylist.setAutoId(autoId);
		return getSqlMap().delete(NAMESPACES + "deleteByPrimaryKey", autoId);
	}

	@Override
	public int updateByPrimaryKey(GoodcompanyList goodcompanylist) {
		return getSqlMap().update(NAMESPACES + "updateByPrimaryKey", goodcompanylist);
	}

	@Override
	public int updateByPrimaryKeySelective(GoodcompanyList goodcompanylist) {
		return getSqlMap().update(NAMESPACES + "updateByPrimaryKeySelective", goodcompanylist);
	}

	@Override
	public GoodcompanyList selectByPrimaryKey(String autoId) {
		GoodcompanyList goodcompanylist = new GoodcompanyList();
		goodcompanylist.setAutoId(autoId);
		return (getSqlMap().queryForObject(NAMESPACES + "selectByPrimaryKey", autoId));
	}

	
	//shihuan 2017-03-19 优质企业列表查询 
	@Override
	public List<GoodcompanyList> queryCoodCompanyList(GoodcompanyList goodcompanylist, int curNum, int pageNum) {

		List<GoodcompanyList> list = new ArrayList<GoodcompanyList>();

		list = getSqlMap().queryForList(NAMESPACES + "queryCoodCompanyList", goodcompanylist, curNum, pageNum);

		return list;
	}

	//shihuan 2017-03-19 优质企业列表查询 条数
	@Override
	public int queryCoodCompanyCount(GoodcompanyList goodcompanylist) {
		return getSqlMap().queryForObject(NAMESPACES + "queryCoodCompanyCount", goodcompanylist);
	}
	
	//shihuan 2017-03-19  优质企业批量启动停用修改状态
	@Override
	public int updateStartStatus(String autoId) throws CoreException {
		return getSqlMap().update(NAMESPACES + "updateStartStatus", autoId);
	}
	
	//shihuan 2017-03-19  优质企业批量启动停用修改状态
	@Override
	public int updateStopStatus(String autoId) throws CoreException {
		return getSqlMap().update(NAMESPACES + "updateStopStatus", autoId);
	}
	
	//shihuan 2017-03-19  添加历史修改记录表
	@Override
	public int insertHisSelective(GoodcompanyListHistory goodcompanylisthistory) {
		return getSqlMap().insert(NAMESPACES + "insertHisSelective", goodcompanylisthistory);
	}
	
	//shihuan 2017-03-19  历史优质企业列表查询 
	@Override
	public List<GoodcompanyList> queryCoodCompanyHistory(String autoId, int curNum, int pageNum) {

		List<GoodcompanyList> list = new ArrayList<GoodcompanyList>();

		list = getSqlMap().queryForList(NAMESPACES + "queryCoodCompanyHistory", autoId, curNum, pageNum);

		return list;
	}

	//shihuan 2017-03-19  历史优质企业列表查询 条数
	@Override
	public int queryCoodCompanyHistoryCount(String autoId) {
		return getSqlMap().queryForObject(NAMESPACES + "queryCoodCompanyHistoryCount", autoId);
	}
	
	//shihuan 2017-03-19  优质企业名单批导入文件
	@Override
	public int insertCoodCompUpload(List<GoodcompanyList> obj) throws CoreException {
		return getSqlMap().insert(NAMESPACES + "insertCoodCompUpload", obj);
	}
	
	//shihuan 2017-04-24 校验学校名称不能重复 
	@Override
	public int queryByCompanyName(String companyName) {
		return getSqlMap().queryForObject(NAMESPACES + "queryByCompanyName", companyName);
	}
}
