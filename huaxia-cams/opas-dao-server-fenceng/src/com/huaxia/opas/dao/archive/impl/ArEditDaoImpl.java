package com.huaxia.opas.dao.archive.impl;

import java.util.ArrayList;
import java.util.List;

import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.archive.ArEditDao;
import com.huaxia.opas.domain.archive.ArDetail;
import com.huaxia.opas.domain.archive.ArFailBack;

public class ArEditDaoImpl extends AbstractDAO implements ArEditDao {

	private static final long serialVersionUID = -4412964105973601153L;

	private static final String NAMESPACES = "ArEdit.";

	@Override
	public int queryFailBackCount(ArFailBack arFailBack) {
		return getSqlMap().queryForObject(NAMESPACES + "queryFailBackCount", arFailBack);
	}

	@Override
	public List<ArFailBack> queryFailBackList(ArFailBack arFailBack, int curNum, int pageNum) {
		List<ArFailBack> list = new ArrayList<ArFailBack>();
		list = getSqlMap().queryForList(NAMESPACES + "queryFailBackList", arFailBack, curNum, pageNum);
		return list;
	}

	@Override
	public int updateArFailBack(ArFailBack arFailBack) {
		return getSqlMap().update(NAMESPACES + "updateArFailBack", arFailBack);
	}

	@Override
	public int updateArDetail(ArDetail arDetail) {
		return getSqlMap().update(NAMESPACES + "updateArDetail", arDetail);
	}

}
