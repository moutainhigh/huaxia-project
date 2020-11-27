package com.huaxia.opas.dao.report.impl;

import java.util.List;
import java.util.Map;

import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.report.FicoResultDao;

public class FicoResultDaoImpl extends AbstractDAO implements FicoResultDao  {
	private static final long serialVersionUID = 7656349236564009257L;
	private static final String NAMESPACES = "FicoResult.";
	@Override
	public List<Map<String, Object>> selectFicoSummaryResultDataByDate(Map<String, Object> paramsMap) {
		return getSqlMap().queryForList(NAMESPACES + "selectFicoSummaryDataByDate", paramsMap);
	}
	@Override
	public List<Map<String, Object>> selectFicoDetailDataByDate(Map<String, Object> paramsMap, int pageNum,
			int pageRows) {
		return getSqlMap().queryForList(NAMESPACES + "selectFicoDetailDataByDate", paramsMap, pageNum,pageRows);
	}
	@Override
	public String getCountFicoDetailDataByDate(Map<String, Object> paramsMap) {
		return this.getSqlMap().queryForObject(NAMESPACES + "getCountFicoDetailDataByDate", paramsMap);
	}



}
