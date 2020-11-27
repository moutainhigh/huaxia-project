package com.huaxia.opas.service.report.impl;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.google.common.util.concurrent.AbstractService;
import com.huaxia.opas.dao.report.WsPlatformDataStatisticsDao;
import com.huaxia.opas.domain.report.WsPlatformDataStatistics;
import com.huaxia.opas.service.report.WsPlatformDataStatisticsService;

/**
 * @author wenyh (进件情况统计表)
 *
 */
public class WsPlatformDataStatisticsServiceImpl extends AbstractService implements WsPlatformDataStatisticsService, Serializable{
	private static final long serialVersionUID = 7871848735562548188L;
	@Resource(name = "wsPlatformDataStatisticsDao")
	private WsPlatformDataStatisticsDao wsPlatformDataStatisticsDao;
	@Override
	protected void doStart() {
	}
	@Override
	protected void doStop() {
	}
	/**
	 *@Title:findWsPlatformDataStatistics
	 *@Description:根据查询条件从进件情况统计表等表中获取所需数据
	 *@param paraMap
	 *@return
	 *@author: wenyh
	 *@Date:2020年7月2日
	 */
	@Override
	public Map<String, Object> findWsPlatformDataStatistics(Map<String, Object> paraMap) {
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("beginDate",paraMap.get("beginDate"));
		paramsMap.put("endDate",paraMap.get("endDate"));
		paramsMap.put("business",paraMap.get("business"));//公司编码
		paramsMap.put("module",paraMap.get("module"));//模块编码
		List<WsPlatformDataStatistics> listMap = wsPlatformDataStatisticsDao.selectWsPlatformDataStatistics(paramsMap);
		WsPlatformDataStatistics wspds = new WsPlatformDataStatistics();
		int wspdsAppcnt = 0;//进件数量  APPCNT
		for (WsPlatformDataStatistics WsPlatformDataStatistics : listMap) {
			wspdsAppcnt += Integer.parseInt(WsPlatformDataStatistics.getAppcnt());
		}
		wspds.setCrtDate("合计");
		wspds.setAppcnt(String.valueOf(wspdsAppcnt));
		
		List<WsPlatformDataStatistics> listMapUse = new ArrayList<WsPlatformDataStatistics>();
		listMapUse.add(wspds);
		listMapUse.addAll(listMap);
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("rows", listMapUse);
		return dataMap;
	}
	/**
	 * 获取公司编码
	 * @return
	 */
	@Override
	public List<Map<String, Object>> queryBusinessNumber() {
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		Map<String, Object> cardMap = new HashMap<String, Object>();
		cardMap.put("businessNumber", "");
		cardMap.put("businessName", "--请选择--");
		listMap.add(cardMap);
		List<Map<String, Object>> resultList = wsPlatformDataStatisticsDao.queryBusinessNumber();
		if(!resultList.isEmpty()){
			listMap.addAll(resultList);
		}
		return listMap;
	}
	/**
	 * 获取模块编码
	 * @param orgName
	 * @return
	 */
	@Override
	public List<Map<String, Object>> queryModuleNumber(String businessNumber) {
		List<Map<String, Object>> resultList = wsPlatformDataStatisticsDao.queryModuleNumber(businessNumber);
		return resultList;
	}
}
