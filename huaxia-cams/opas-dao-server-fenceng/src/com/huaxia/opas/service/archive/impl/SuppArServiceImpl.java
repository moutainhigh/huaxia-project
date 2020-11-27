package com.huaxia.opas.service.archive.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import com.huaxia.opas.dao.archive.SuppArDao;
import com.huaxia.opas.domain.archive.SuppArchive;
import com.huaxia.opas.service.AbstractService;
import com.huaxia.opas.service.archive.SuppArService;

public class SuppArServiceImpl extends AbstractService implements SuppArService, Serializable{

	private static final long serialVersionUID = 7495899965494220508L;

	@Resource(name = "suppArDao")
	private SuppArDao suppArDao;
	
	@Override
	public List<SuppArchive> querySuppArList(SuppArchive suppArchive, int curNum, int pageNum) {
		return getSuppArDao().querySuppArList(suppArchive, curNum, pageNum);
	}

	@Override
	public int querySuppArCount(SuppArchive suppArchive) {
		return getSuppArDao().querySuppArCount(suppArchive);
	}

	@Override
	public int updateSuppArFlag(SuppArchive suppArchive) {
		return getSuppArDao().updateSuppArFlag(suppArchive);
	}
	
	@Override
	public List<SuppArchive> querySuppArAllList(SuppArchive suppArchive, int curNum, int pageNum) {
		return getSuppArDao().querySuppArAllList(suppArchive, curNum, pageNum);
	}
	
	@Override
	public int querySuppArAllCount(SuppArchive suppArchive) {
		return getSuppArDao().querySuppArAllCount(suppArchive);
	}

	public SuppArDao getSuppArDao() {
		return suppArDao;
	}

	public void setSuppArDao(SuppArDao suppArDao) {
		this.suppArDao = suppArDao;
	}

	@Override
	public int updateSuppToAr(SuppArchive suppArchive) {
		return getSuppArDao().updateSuppToAr(suppArchive);
	}

	@Override
	public int updateSuppToArAll(SuppArchive suppArchive) {
		return getSuppArDao().updateSuppToArAll(suppArchive);
	}

	@Override
	public int updateSuppToDel(SuppArchive suppArchive) {
		return getSuppArDao().updateSuppToDel(suppArchive);
	}

	@Override
	public List<SuppArchive> querySuccessList(SuppArchive suppArchive) {
		return getSuppArDao().querySuccessList(suppArchive);
	}

	//一次补件或二次补件后对补件结果下结论   shihuan 2017-03-27
	@Override
	public int insertFilePatch(SuppArchive suppArchive) {
		return getSuppArDao().insertFilePatch(suppArchive);
	}


}
