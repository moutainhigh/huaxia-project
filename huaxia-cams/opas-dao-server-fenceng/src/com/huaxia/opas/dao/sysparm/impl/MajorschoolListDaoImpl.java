package com.huaxia.opas.dao.sysparm.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.sysparm.MajorschoolListDao;
import com.huaxia.opas.domain.decision.TeamdealList;
import com.huaxia.opas.domain.sysparm.MajorschoolList;
import com.huaxia.opas.domain.sysparm.MajorschoolListHistory;

public class MajorschoolListDaoImpl extends AbstractDAO implements MajorschoolListDao {

	private static final long serialVersionUID = 5757582418545513970L;
	
	private static final String NAMESPACES = "MajorschoolList.";

	@Override
	public int insert(MajorschoolList majorschoollist) {
		return getSqlMap().insert(NAMESPACES + "insert", majorschoollist);
	}

	@Override
	public int insertSelective(MajorschoolList majorschoollist) {
		return getSqlMap().insert(NAMESPACES + "insertSelective", majorschoollist);
	}

	@Override
	public int deleteByPrimaryKey(String autoId) {
		MajorschoolList majorschoollist = new MajorschoolList();
		majorschoollist.setAutoId(autoId);
		return getSqlMap().delete(NAMESPACES + "deleteByPrimaryKey", autoId);
	}

	@Override
	public int updateByPrimaryKey(MajorschoolList majorschoollist) {
		return getSqlMap().update(NAMESPACES + "updateByPrimaryKey", majorschoollist);
	}

	@Override
	public int updateByPrimaryKeySelective(MajorschoolList majorschoollist) {
		return getSqlMap().update(NAMESPACES + "updateByPrimaryKeySelective", majorschoollist);
	}

	@Override
	public MajorschoolList selectByPrimaryKey(String autoId) {
		MajorschoolList majorschoollist = new MajorschoolList();
		majorschoollist.setAutoId(autoId);
		return (getSqlMap().queryForObject(NAMESPACES + "selectByPrimaryKey", autoId));
	}
	@Override
	public List<MajorschoolList> selectByCondtion(Map map,int curNum,int pageNum) {
		return (getSqlMap().queryForList(NAMESPACES + "selectByCondtion", map,curNum,pageNum));
	}
	
	//shihuan 2017-03-14 重点院校列表查询 
	@Override
	public List<MajorschoolList> queryMajorSchooList(MajorschoolList majorschoollist, int curNum, int pageNum) {

		List<MajorschoolList> list = new ArrayList<MajorschoolList>();

		list = getSqlMap().queryForList(NAMESPACES + "queryMajorSchooList", majorschoollist, curNum, pageNum);

		return list;
	}

	//shihuan 2017-03-14 重点院校列表查询 条数
	@Override
	public int queryMajorSchooCount(MajorschoolList majorschoollist) {
		return getSqlMap().queryForObject(NAMESPACES + "queryMajorSchooCount", majorschoollist);
	}
	
	//shihuan 2017-03-14  重点院校批量启动停用修改状态
	@Override
	public int updateStartStatus(String autoId) throws CoreException {
		return getSqlMap().update(NAMESPACES + "updateStartStatus", autoId);
	}
	
	//shihuan 2017-03-14  重点院校批量启动停用修改状态
	@Override
	public int updateStopStatus(String autoId) throws CoreException {
		return getSqlMap().update(NAMESPACES + "updateStopStatus", autoId);
	}
	
	//shihuan 2017-03-14  添加历史修改记录表
	@Override
	public int insertHisSelective(MajorschoolListHistory majorschoollisthistory) {
		return getSqlMap().insert(NAMESPACES + "insertHisSelective", majorschoollisthistory);
	}
	
	//shihuan 2017-03-14  历史重点院校列表查询 
	@Override
	public List<MajorschoolList> queryMajorSchoolHistory(String autoId, int curNum, int pageNum) {

		List<MajorschoolList> list = new ArrayList<MajorschoolList>();

		list = getSqlMap().queryForList(NAMESPACES + "queryMajorSchoolHistory", autoId, curNum, pageNum);

		return list;
	}

	//shihuan 2017-03-14  历史重点院校列表查询 条数
	@Override
	public int queryMajorSchoolHistoryCount(String autoId) {
		return getSqlMap().queryForObject(NAMESPACES + "queryMajorSchoolHistoryCount", autoId);
	}
	
	//shihuan 2017-03-18  重点院校名单批导入文件
	@Override
	public int insertMajorSchUpload(List<MajorschoolList> obj) throws CoreException {
		return getSqlMap().insert(NAMESPACES + "insertMajorSchUpload", obj);
	}
	
	//shihuan 2017-04-24 校验重点院校名称不能重复 
	@Override
	public int queryByMajorschoolName(String majorschoolName) {
		return getSqlMap().queryForObject(NAMESPACES + "queryByMajorschoolName", majorschoolName);
	}

	@Override
	public int queryCountList(Map map) {
		return getSqlMap().queryForObject(NAMESPACES + "queryCountList", map);
	}
}
