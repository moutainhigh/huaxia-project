package com.huaxia.plaze.ui.system.mapper;

import java.util.List;
import java.util.Map;

public interface DictionaryMapper<T> {

	List<T> selectList();

	List<T> selectListByDictCode(String groupCode);

	String selectName(Map<String, String> args);

}
