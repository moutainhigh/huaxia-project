package com.huaxia.opas.service.archive.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import com.huaxia.opas.dao.archive.ArEditDao;
import com.huaxia.opas.domain.archive.ArDetail;
import com.huaxia.opas.domain.archive.ArFailBack;
import com.huaxia.opas.service.AbstractService;
import com.huaxia.opas.service.archive.ArEditService;

public class ArEditServiceImpl extends AbstractService implements ArEditService, Serializable  {

	private static final long serialVersionUID = 9201096815250465961L;

	@Resource(name = "arEditDao")
	private ArEditDao arEditDao;

	@Override
	public List<ArFailBack> queryFailBackList(ArFailBack arFailBack, int curNum, int pageNum) {
		return getArEditDao().queryFailBackList(arFailBack, curNum, pageNum);
	}

	@Override
	public int queryFailBackCount(ArFailBack arFailBack) {
		return getArEditDao().queryFailBackCount(arFailBack);
	}

	public ArEditDao getArEditDao() {
		return arEditDao;
	}

	public void setArEditDao(ArEditDao arEditDao) {
		this.arEditDao = arEditDao;
	}

	@Override
	public int updateArFailBack(ArFailBack arFailBack) {
		return getArEditDao().updateArFailBack(arFailBack);
	}

	@Override
	public int updateArDetail(ArDetail arDetail) {
		return getArEditDao().updateArDetail(arDetail);
	}

}
