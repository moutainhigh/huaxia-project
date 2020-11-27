package com.huaxia.plaze.ui.setting.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.huaxia.plaze.ui.setting.domain.ConfigSetting;
import com.huaxia.plaze.ui.setting.mapper.ConfigSettingMapper;
import com.huaxia.plaze.ui.setting.service.ConfigSettingService;

@Service("configSettingService")
public class ConfigSettingServiceImpl implements ConfigSettingService {
	
	@Resource
	private ConfigSettingMapper<ConfigSetting> configSettingMapper;

	@Override
	public ConfigSetting querySettingById(String id) {
		return configSettingMapper.selectById(id);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public int mergeSetting(Map<String, Object> args) {
		int result = configSettingMapper.deleteById(String.valueOf(args.get("id")));
		result += configSettingMapper.insert(args);
		return result;
	}

}
