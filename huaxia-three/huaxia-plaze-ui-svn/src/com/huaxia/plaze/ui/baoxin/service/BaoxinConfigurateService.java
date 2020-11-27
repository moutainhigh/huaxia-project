package com.huaxia.plaze.ui.baoxin.service;

import java.util.List;
import java.util.Map;

public interface BaoxinConfigurateService {

	List<Map<String, Integer>> queryCountByParams(Map<String, Object> args);

}
