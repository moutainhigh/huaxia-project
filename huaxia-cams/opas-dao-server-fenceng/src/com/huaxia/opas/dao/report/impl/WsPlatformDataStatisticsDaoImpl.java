package com.huaxia.opas.dao.report.impl;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.report.WsPlatformDataStatisticsDao;
import com.huaxia.opas.domain.report.WsPlatformDataStatistics;


/**
 * 进件情况统计表
 * @author wenyh
 */
public class WsPlatformDataStatisticsDaoImpl extends AbstractDAO implements WsPlatformDataStatisticsDao{
	private static final long serialVersionUID = -4977572751674129646L;
	private static final String NAMESPACES = "WsPlatformDataStatistics.";
	@Override
	public List<WsPlatformDataStatistics> selectWsPlatformDataStatistics(Map<String, Object> paramsMap) {
		return getSqlMap().queryForList(NAMESPACES + "selectWsPlatformDataStatistics", paramsMap);
	}
	
	@Override
	public List<Map<String, Object>> queryBusinessNumber() {
		List<Map<String, Object>> list = getSqlMap().queryForList(NAMESPACES + "queryBusinessNumber");
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		for (Map<String, Object> map : list) {
			if(map!=null)
			resultList.add(map);
		}
		return resultList;
	}

	@Override
	public List<Map<String, Object>> queryModuleNumber(String businessNumber) {
		List<Map<String, Object>> list = getSqlMap().queryForList(NAMESPACES + "queryModuleNumber",businessNumber);
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		for (Map<String, Object> map : list) {
			if(map!=null)
			resultList.add(map);
		}
		return resultList;
	}
}
