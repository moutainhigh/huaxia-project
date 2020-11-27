package com.huaxia.opas.dao.archive.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.archive.ArExportDao;
import com.huaxia.opas.dao.archive.ArQueueDao;
import com.huaxia.opas.domain.archive.ArBatch;
import com.huaxia.opas.domain.archive.ArDetail;
import com.huaxia.opas.domain.archive.ArExport;

public class ArExportDaoImpl extends AbstractDAO implements ArExportDao {

	private static final long serialVersionUID = 7281069982219184945L;
	private static final String NAMESPACES = "ArExport.";
	@Override
	public List queryAppIdList(ArExport arExport) {
		List list = new ArrayList();
		list = getSqlMap().queryForList(NAMESPACES + "queryAppIdList", arExport);
		return list;
	}
	
	@Override
	public List queryNoSendList(String appIds) {
		List list = new ArrayList();
		list = getSqlMap().queryForList(NAMESPACES + "selectNoSend", appIds);
		return list;
	}

	//补全导出文件的字段
	@Override
	public List<Map<String, Object>> querySendCardUpdateColunm(String appId) {
		return getSqlMap().queryForList(NAMESPACES + "selSendCardUpdateColunm", appId);
	}

	@Override
	public String queryinputChannel(String appId) {
		return getSqlMap().queryForObject(NAMESPACES + "queryinputChannel",appId);
	}


}
