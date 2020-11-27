package com.huaxia.plaze.ui.quartz.service;

import java.util.List;

import com.huaxia.plaze.common.PageProperty;
import com.huaxia.plaze.ui.quartz.domain.BatchQuartz;

public interface BatchQuartzService {

	int queryTotalCountByPage(PageProperty page);

	List<BatchQuartz> queryListByPage(PageProperty page);

	int saveQuartz(BatchQuartz quartz);

	int deleteQuartzById(String id);

	BatchQuartz selectQuartzById(String id);

	int updateQuartz(BatchQuartz quartz);

}
