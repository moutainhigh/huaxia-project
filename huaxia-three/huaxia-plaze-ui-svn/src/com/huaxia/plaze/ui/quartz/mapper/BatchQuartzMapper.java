package com.huaxia.plaze.ui.quartz.mapper;

import java.util.List;
import java.util.Map;

import com.huaxia.plaze.ui.quartz.domain.BatchQuartz;

public interface BatchQuartzMapper {

	int queryTotalCountByPage(Map<String, Object> args);

	List<BatchQuartz> queryListByPage(Map<String, Object> args);

	int saveQuartz(BatchQuartz quartz);

	int deleteQuartzById(String id);

	BatchQuartz selectQuartzById(String id);

	int updateQuartz(BatchQuartz quartz);

}
