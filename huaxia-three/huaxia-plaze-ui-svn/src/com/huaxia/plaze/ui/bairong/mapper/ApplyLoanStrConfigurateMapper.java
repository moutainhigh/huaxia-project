package com.huaxia.plaze.ui.bairong.mapper;

import java.util.List;
import java.util.Map;

import com.huaxia.plaze.ui.setting.domain.QueryConfiguration;
import com.huaxia.plaze.ui.setting.domain.QueryTask;

public interface ApplyLoanStrConfigurateMapper {
	
	List<Map<String, Integer>> selectCountByParams(Map<String, Object> args);
	
	QueryConfiguration selectSettingByTaskType(Map<String, Object> args);
	
	int selectBatchCountByReferTime(Map<String, Object> args);
	
	int selectBatchOverCountByReferTime(Map<String, Object> args);
	
	int selectBatchWaitCountByReferTime(Map<String, Object> args);

}
