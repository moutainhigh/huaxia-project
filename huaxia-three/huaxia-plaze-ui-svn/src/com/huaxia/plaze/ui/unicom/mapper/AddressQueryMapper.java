package com.huaxia.plaze.ui.unicom.mapper;

import java.util.List;
import java.util.Map;

public interface AddressQueryMapper {

	void query001102ResultByResource(Map<String, Object> args);

	void query001103ResultByResource(Map<String, Object> args);
	/**
	 * 查询当前年份已查询的数量条数
	 * @param args
	 * @return
	 */
	List<Map<String, Integer>> selectCountByTime(Map<String, Object> args);

}
