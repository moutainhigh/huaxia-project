package com.huaxia.plaze.ui.setting.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.huaxia.plaze.ui.setting.domain.QueryConfiguration;
import com.huaxia.plaze.ui.setting.mapper.QueryConfigurateMapper;
import com.huaxia.plaze.ui.setting.service.QueryConfigurateService;

@Service("queryConfigurateService")
public class QueryConfigurateServiceImpl implements QueryConfigurateService {

	@Resource
	private QueryConfigurateMapper queryConfigurateMapper;

	@Override
	public List<QueryConfiguration> querySettingByParams(Map<String, Object> args) {
		return queryConfigurateMapper.selectSettingByArgs(args);
	}

	@Override
	public int mergeSetting(Map<String, Object> args) {
		int result = 0;
		List<QueryConfiguration> queryCountList = queryConfigurateMapper.selectSettingByParams(args);
		if (queryCountList != null && queryCountList.size() > 0) {
			result += queryConfigurateMapper.updateSetting(args);
		} else {
			result += queryConfigurateMapper.insertSetting(args);
		}
		return result;
	}

}
