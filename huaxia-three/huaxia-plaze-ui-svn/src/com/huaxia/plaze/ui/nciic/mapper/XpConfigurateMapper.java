package com.huaxia.plaze.ui.nciic.mapper;

import java.util.List;
import java.util.Map;

public interface XpConfigurateMapper {
	
	List<Map<String, Integer>> selectCountByParams(Map<String, Object> args);
	
}
