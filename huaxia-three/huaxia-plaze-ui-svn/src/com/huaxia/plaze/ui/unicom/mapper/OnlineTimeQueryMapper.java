package com.huaxia.plaze.ui.unicom.mapper;

import java.util.List;
import java.util.Map;

public interface OnlineTimeQueryMapper {

	List<Map<String, Integer>> selectCountByParams(Map<String, Object> args);

}
