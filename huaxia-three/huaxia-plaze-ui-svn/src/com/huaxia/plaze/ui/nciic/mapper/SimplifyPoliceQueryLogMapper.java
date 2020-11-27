package com.huaxia.plaze.ui.nciic.mapper;

import java.util.List;
import java.util.Map;

import com.huaxia.plaze.ui.nciic.domain.SimplifyPoliceQueryLog;

public interface SimplifyPoliceQueryLogMapper {

	int insertObject(SimplifyPoliceQueryLog log);

	int selectLogListCountByPage(Map<String, Object> args);

	List<SimplifyPoliceQueryLog> selectLogListByPage(Map<String, Object> args);

	int updateQueryResult(Map<String, Object> args);

}
