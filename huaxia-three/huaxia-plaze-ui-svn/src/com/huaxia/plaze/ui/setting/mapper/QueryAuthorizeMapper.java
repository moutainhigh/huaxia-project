package com.huaxia.plaze.ui.setting.mapper;

import java.util.Map;

public interface QueryAuthorizeMapper {
	
	Map<String, String> selectAuthorizeSettingByParams(Map<String, Object> args);
	
	int insertAuthorizeSetting(Map<String, Object> args);
	
	int updateAuthorizeSetting(Map<String, Object> args);
	
}
