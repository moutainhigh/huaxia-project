package com.huaxia.plaze.ui.setting.mapper;

import java.util.Map;

public interface ConfigSettingMapper<T> {
	
	T selectById(String id);
	
	int deleteById(String id);
	
	int insert(Map<String, Object> args);
	
	T selecMaxCountById(String id);
}
