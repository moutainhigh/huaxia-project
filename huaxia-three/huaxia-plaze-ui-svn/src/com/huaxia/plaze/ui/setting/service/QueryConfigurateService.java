package com.huaxia.plaze.ui.setting.service;

import java.util.List;
import java.util.Map;

import com.huaxia.plaze.ui.setting.domain.QueryConfiguration;

/**
 * 查询配置服务接口
 * 
 * @author zhiguo.li
 * @version 1.0.1
 *
 */
public interface QueryConfigurateService {

	List<QueryConfiguration> querySettingByParams(Map<String, Object> args);

	int mergeSetting(Map<String, Object> args);

}
