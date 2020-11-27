package com.huaxia.plaze.ui.tongdun.mapper;

import java.util.List;
import java.util.Map;

import com.huaxia.plaze.ui.setting.domain.QueryConfiguration;

public interface MulBorConfigurateMapper {

	QueryConfiguration selectSettingByTaskType(Map<String, Object> args);
	
	void selectSingleCountByReferTime(Map<String, Object> args);
	
	int selectBatchCountByReferTime(Map<String, Object> args);
	
	int selectBatchOverCountByReferTime(Map<String, Object> args);
	
	int selectBatchWaitCountByReferTime(Map<String, Object> args);
	
	List<Map<String, Integer>> selectCountByParams(Map<String, Object> args);
}
