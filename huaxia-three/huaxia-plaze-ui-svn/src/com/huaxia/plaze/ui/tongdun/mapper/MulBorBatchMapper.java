package com.huaxia.plaze.ui.tongdun.mapper;

import java.util.List;
import java.util.Map;

import com.huaxia.plaze.ui.tongdun.domain.MulBorBatch;
import com.huaxia.plaze.ui.tongdun.domain.MulBorQueryReview;

public interface MulBorBatchMapper {

	int insertObject(MulBorBatch mulBorTrnBatch);
	
	/**
	 * 查询批次表
	 * @param args
	 * @return
	 */
	List<MulBorQueryReview> selectBatchListByPage(Map<String, Object> args);

	/**
	 * 查询总记录数目
	 * @param args
	 * @return
	 */
	int selectBatchListCountByPage(Map<String, Object> args);
}
