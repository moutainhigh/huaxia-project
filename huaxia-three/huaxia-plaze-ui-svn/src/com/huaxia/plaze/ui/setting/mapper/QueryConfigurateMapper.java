package com.huaxia.plaze.ui.setting.mapper;

import java.util.List;
import java.util.Map;

import com.huaxia.plaze.ui.setting.domain.QueryConfiguration;

public interface QueryConfigurateMapper {

	List<QueryConfiguration> selectSettingByParams(Map<String, Object> args);
	
	int insertSetting(Map<String, Object> args);
	
	int updateSetting(Map<String, Object> args);

	QueryConfiguration selectSettingByTaskType(Map<String, Object> args);
	
	List<QueryConfiguration> selectSettingByArgs(Map<String, Object> args);
	
}
