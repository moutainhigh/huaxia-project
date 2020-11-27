package com.huaxia.plaze.ui.id5.mapper;

import java.util.List;
import java.util.Map;

public interface EducationConfigurate {

	int selectEduQueryNumByTime();
	
	List<Map<String, Integer>> selectCountByTime(Map<String, Object> args);
}
