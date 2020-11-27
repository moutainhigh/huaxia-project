package com.huaxia.plaze.ui.numread.mapper;

import java.util.List;
import java.util.Map;

import com.huaxia.plaze.common.PageProperty;
import com.huaxia.plaze.ui.numread.domain.NumReadBatch;

public interface BatchMapper {

	int saveNumReadBatch(NumReadBatch batch);

	int selectBatchListCountByPage(Map<String, Object> args);

	List<NumReadBatch> selectBatchListByPage(PageProperty page);

}
