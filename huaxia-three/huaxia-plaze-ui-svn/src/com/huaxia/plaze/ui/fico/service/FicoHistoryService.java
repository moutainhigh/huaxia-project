package com.huaxia.plaze.ui.fico.service;

import java.util.List;

import com.huaxia.plaze.common.PageProperty;
import com.huaxia.plaze.ui.fico.domain.FicoRiskLevel;
public interface FicoHistoryService {
	/**
	 * 
	 * @param page
	 * @return
	 */
	List<FicoRiskLevel> selectListByPage(PageProperty page);
	/**
	 * 查询总量
	 * @param page
	 * @return
	 */
	int queryListCountByPage(PageProperty page);
}
