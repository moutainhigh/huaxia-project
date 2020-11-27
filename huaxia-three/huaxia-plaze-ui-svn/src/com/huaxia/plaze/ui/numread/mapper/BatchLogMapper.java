package com.huaxia.plaze.ui.numread.mapper;

import java.util.List;
import java.util.Map;

import com.huaxia.plaze.ui.numread.domain.NumReadLog;

public interface BatchLogMapper {

	int selectLogCountByPage(Map<String, Object> args);

	List<NumReadLog> selectLogListByPage(Map<String, Object> args);

	int saveBatchLog(NumReadLog log);

}
