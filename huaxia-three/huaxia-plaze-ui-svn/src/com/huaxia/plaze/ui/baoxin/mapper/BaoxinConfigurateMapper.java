package com.huaxia.plaze.ui.baoxin.mapper;

import java.util.List;
import java.util.Map;

import com.huaxia.plaze.ui.setting.domain.QueryConfiguration;

public interface BaoxinConfigurateMapper {

	List<Map<String, Integer>> selectCountByParams(Map<String, Object> args);
	
}
