package com.huaxia.plaze.ui.fico.mapper;

import java.util.List;
import java.util.Map;

import com.huaxia.plaze.ui.fico.domain.FicoRiskLevel;

public interface FicoHistoryMapper {

	List<FicoRiskLevel> selectListByPage(Map<String, Object> args);
	
	int selectListCountByPage(Map<String, Object> args);
}
