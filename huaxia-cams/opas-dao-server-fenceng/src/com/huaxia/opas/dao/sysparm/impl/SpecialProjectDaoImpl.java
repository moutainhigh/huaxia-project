package com.huaxia.opas.dao.sysparm.impl;

import java.util.ArrayList;
import java.util.List;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.sysparm.SpecialProjectDao;
import com.huaxia.opas.domain.sysparm.SpecialprojectList;
import com.huaxia.opas.domain.sysparm.SpecialprojectListHis;

public class SpecialProjectDaoImpl extends AbstractDAO implements SpecialProjectDao {
	
	private static final long serialVersionUID = 5470178573528036415L;
	
	private static final String NAMESPACES = "SpecialprojectList.";
	@Override
	public int insert(SpecialprojectList specialprojectlist) {
		return getSqlMap().insert(NAMESPACES + "insert", specialprojectlist);
	}

	@Override
	public int insertSelective(SpecialprojectList specialprojectlist) {
		return getSqlMap().insert(NAMESPACES + "insertSelective", specialprojectlist);
	}

	@Override
	public int deleteByPrimaryKey(String autoId) {
		SpecialprojectList specialprojectlist = new SpecialprojectList();
		specialprojectlist.setAutoId(autoId);
		return getSqlMap().delete(NAMESPACES + "deleteByPrimaryKey", autoId);
	}

	@Override
	public int updateByPrimaryKey(SpecialprojectList specialprojectlist) {
		return getSqlMap().update(NAMESPACES + "updateByPrimaryKey", specialprojectlist);
	}

	@Override
	public int updateByPrimaryKeySelective(SpecialprojectList specialprojectlist) {
		return getSqlMap().update(NAMESPACES + "updateByPrimaryKeySelective", specialprojectlist);
	}

	@Override
	public SpecialprojectList selectByPrimaryKey(String autoId) {
		SpecialprojectList specialprojectlist = new SpecialprojectList();
		specialprojectlist.setAutoId(autoId);
		return (getSqlMap().queryForObject(NAMESPACES + "selectByPrimaryKey", autoId));
	}

	
	//shihuan 2017-03-16   特殊项目列表查询 
	@Override
	public List<SpecialprojectList> querySpecialprojectList(SpecialprojectList specialprojectlist, int curNum, int pageNum) {

		List<SpecialprojectList> list = new ArrayList<SpecialprojectList>();

		list = getSqlMap().queryForList(NAMESPACES + "querySpecialprojectList", specialprojectlist, curNum, pageNum);

		return list;
	}

	//shihuan 2017-03-16 特殊项目列表查询 条数
	@Override
	public int querySpecialprojectCount(SpecialprojectList specialprojectlist) {
		return getSqlMap().queryForObject(NAMESPACES + "querySpecialprojectCount", specialprojectlist);
	}
	
	//shihuan 2017-03-16  特殊项目批量启动停用修改状态
	@Override
	public int updateStartStatus(String autoId) throws CoreException {
		return getSqlMap().update(NAMESPACES + "updateStartStatus", autoId);
	}
	
	//shihuan 2017-03-16  特殊项目批量启动停用修改状态
	@Override
	public int updateStopStatus(String autoId) throws CoreException {
		return getSqlMap().update(NAMESPACES + "updateStopStatus", autoId);
	}
	
	//shihuan 2017-03-16  添加历史修改记录表
	@Override
	public int insertHisSelective(SpecialprojectListHis specialprojectlisthis) {
		return getSqlMap().insert(NAMESPACES + "insertHisSelective", specialprojectlisthis);
	}
	
	//shihuan 2017-03-16  历史特殊项目列表查询 
	@Override
	public List<SpecialprojectList> querySpecialprojectHistory(String autoId, int curNum, int pageNum) {

		List<SpecialprojectList> list = new ArrayList<SpecialprojectList>();

		list = getSqlMap().queryForList(NAMESPACES + "querySpecialprojectHistory", autoId, curNum, pageNum);

		return list;
	}

	//shihuan 2017-03-16  历史特殊项目列表查询 条数
	@Override
	public int queryHistoryCount(String autoId) {
		return getSqlMap().queryForObject(NAMESPACES + "queryHistoryCount", autoId);
	}
	
	@Override
	public int insertSpecProUpload(List<SpecialprojectList> obj) throws CoreException {
		return getSqlMap().insert(NAMESPACES + "insertSpecProUpload", obj);
		
	}
	
	//shihuan 2017-04-24  校验项目代码不能重复 
	@Override
	public int queryProjectCode(String projectCode) {
		return getSqlMap().queryForObject(NAMESPACES + "queryProjectCode", projectCode);
	}
}
