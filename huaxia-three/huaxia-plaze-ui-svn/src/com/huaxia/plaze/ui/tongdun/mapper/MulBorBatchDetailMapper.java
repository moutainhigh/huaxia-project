package com.huaxia.plaze.ui.tongdun.mapper;

import java.util.List;
import java.util.Map;

import com.huaxia.plaze.ui.tongdun.domain.MulBorBatchDetail;
import com.huaxia.plaze.ui.tongdun.domain.MulBorQueryReview;

public interface MulBorBatchDetailMapper {

	int insertObject(MulBorBatchDetail mulBorTrnBatchDetail);
	
	List<Map<String,Object>> selectFormularyNumTasksByType(Map<String,Object> params);
	/**
	 * 更改状态Map对象
	 * @param params
	 * @return
	 */
	int updateSingleTask(Map<String, Object> params);
	/**
	 *获取每个文件中的记录执行情况
	 * @param batchId
	 * @return
	 */
	List<MulBorQueryReview> selectDetailListByPage(String batchFileId);
}
