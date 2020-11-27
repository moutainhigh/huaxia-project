package com.huaxia.opas.dao.archive.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.archive.SuppArDao;
import com.huaxia.opas.domain.archive.SuppArchive;

public class SuppArDaoImpl extends AbstractDAO implements SuppArDao{

	private static final long serialVersionUID = 2406685604209297143L;

	private static final String NAMESPACES = "SuppAr.";

	@Override
	public int querySuppArCount(SuppArchive suppArchive) {
		return getSqlMap().queryForObject(NAMESPACES + "querySuppArCount", suppArchive);
	}

	@Override
	public List<SuppArchive> querySuppArList(SuppArchive suppArchive, int curNum, int pageNum) {
		List<SuppArchive> list = new ArrayList<SuppArchive>();
		list = getSqlMap().queryForList(NAMESPACES + "querySuppArList", suppArchive, curNum, pageNum);
		return list;
	}

	@Override
	public int updateSuppArFlag(SuppArchive suppArchive) {
		getSqlMap().update(NAMESPACES + "updateSuppArFlag1", suppArchive);
		return getSqlMap().update(NAMESPACES + "updateSuppArFlag", suppArchive);
	}

	@Override
	public int querySuppArAllCount(SuppArchive suppArchive) {
		return getSqlMap().queryForObject(NAMESPACES + "querySuppArAllCount", suppArchive);
	}

	@Override
	public List<SuppArchive> querySuppArAllList(SuppArchive suppArchive, int curNum, int pageNum) {
		List<SuppArchive> list = new ArrayList<SuppArchive>();
		list = getSqlMap().queryForList(NAMESPACES + "querySuppArAllList", suppArchive, curNum, pageNum);
		return list;
	}

	@Override
	public int updateSuppToAr(SuppArchive suppArchive) {
		getSqlMap().update(NAMESPACES + "updateSuppToAr1", suppArchive);
		return getSqlMap().update(NAMESPACES + "updateSuppToAr", suppArchive);
	}

	@Override
	public int updateSuppToArAll(SuppArchive suppArchive) {
		getSqlMap().update(NAMESPACES + "updateSuppToArAll1", suppArchive);
		return getSqlMap().update(NAMESPACES + "updateSuppToArAll", suppArchive);
	}

	@Override
	public int updateSuppToDel(SuppArchive suppArchive) {
		getSqlMap().update(NAMESPACES + "updateSuppToDel1", suppArchive);
		return getSqlMap().update(NAMESPACES + "updateSuppToDel", suppArchive);
	}

	@Override
	public List<SuppArchive> querySuccessList(SuppArchive suppArchive) {
		List<SuppArchive> list = new ArrayList<SuppArchive>();
		list = getSqlMap().queryForList(NAMESPACES + "querySuccessList", suppArchive);
		return list;
	}
	
	//一次补件或二次补件后对补件结果下结论   shihuan 2017-03-27
	@Override
	public int insertFilePatch(SuppArchive suppArchive) {
		return getSqlMap().update(NAMESPACES + "insertFilePatch", suppArchive);
	}

	@Override
	public int updateFilePatch(SuppArchive supparchive) {
		return getSqlMap().update(NAMESPACES + "updateFilePatch", supparchive);
	}

	@Override
	public int queryFilePatchCodeCount(String appId) {
		return getSqlMap().queryForObject(NAMESPACES + "queryFilePatchCodeCount", appId);
	}

	@Override
	public int queryCountByPatchCode(Map<String, Object> dataMap) {
		return getSqlMap().queryForObject(NAMESPACES + "queryCountByPatchCode", dataMap);
	}

	@Override
	public int insertFilePatchHis(SuppArchive supparchive) {
		return getSqlMap().insert(NAMESPACES + "insertFilePatchHis", supparchive);
	}

	

}
