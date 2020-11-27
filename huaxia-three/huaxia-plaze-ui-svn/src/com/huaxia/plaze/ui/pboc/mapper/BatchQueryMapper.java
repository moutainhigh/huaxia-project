package com.huaxia.plaze.ui.pboc.mapper;

import java.util.List;
import java.util.Map;

import com.huaxia.plaze.ui.pboc.domain.BatchQueryReview;

public interface BatchQueryMapper<T> {

	int insertObject(T t);

	int selectBatchListCountByPage(Map<String, Object> args);

	List<BatchQueryReview> selectBatchListByPage(Map<String, Object> args);
	
	int updateStatus(Map<String, Object> args);

	int selectBatchIdListCountByPage(Map<String, Object> args);

	List<BatchQueryReview> selectBatchIdListByPage(Map<String, Object> args);

	List<BatchQueryReview> selectBatchIdRefuseListByPage(Map<String, Object> args);

	int deleteBatchBybatchId(String oldBatchNo);

}
