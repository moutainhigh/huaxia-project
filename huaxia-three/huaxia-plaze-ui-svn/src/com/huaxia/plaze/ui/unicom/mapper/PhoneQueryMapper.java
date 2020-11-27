package com.huaxia.plaze.ui.unicom.mapper;

import java.util.List;
import java.util.Map;

public interface PhoneQueryMapper {

	List<Map<String, Integer>> selectCountByParams(Map<String, Object> args);

	void selectQueryResultBySource(Map<String, Object> args);
}
