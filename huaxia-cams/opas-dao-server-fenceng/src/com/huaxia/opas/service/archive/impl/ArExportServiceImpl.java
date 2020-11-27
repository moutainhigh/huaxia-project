package com.huaxia.opas.service.archive.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.huaxia.opas.dao.archive.ArExportDao;
import com.huaxia.opas.domain.archive.ArExport;
import com.huaxia.opas.service.AbstractService;
import com.huaxia.opas.service.archive.ArExportService;

public class ArExportServiceImpl extends AbstractService implements ArExportService, Serializable {

	private static final long serialVersionUID = -3217302168576995341L;
	@Resource(name = "arExportDao")
	private ArExportDao arExportDao;

	@Override
	public List queryAppIdList(ArExport arExport) {
		return getArExportDao().queryAppIdList(arExport);
	}

	public ArExportDao getArExportDao() {
		return arExportDao;
	}

	public void setArExportDao(ArExportDao arExportDao) {
		this.arExportDao = arExportDao;
	}

	@Override
	public List queryNoSendList(String appIds) {
		return getArExportDao().queryNoSendList(appIds);
	}

	//补全导出的文件的字段
	@Override
	public List<Map<String, Object>> querySendCardUpdateColunm(String appId) {
		return  getArExportDao().querySendCardUpdateColunm(appId);
	}

	//查询进件渠道
	@Override
	public String queryinputChannel(String appId) {
		return getArExportDao().queryinputChannel(appId);
	}

	
}
