package com.huaxia.plaze.ui.tongdun.service;

import java.util.List;
import java.util.Map;

public interface MulBorConfigurateService {

	List<Map<String, Integer>> queryCountByParams(Map<String, Object> args);
	public int  isAllowSingleQuery(String queryFrom);
}
