package com.huaxia.plaze.ui.setting.service;

import java.util.Map;

import com.huaxia.plaze.ui.setting.domain.ConfigSetting;

/**
 * 配置服务
 * 
 * @author zhiguo.li
 *
 */
public interface ConfigSettingService {

	ConfigSetting querySettingById(String id);

	int mergeSetting(Map<String, Object> args);

}
