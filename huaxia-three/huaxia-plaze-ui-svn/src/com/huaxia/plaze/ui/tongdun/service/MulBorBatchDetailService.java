package com.huaxia.plaze.ui.tongdun.service;

import java.util.List;
import java.util.Map;

import com.huaxia.plaze.ui.tongdun.domain.MulBorBatchDetail;
import com.huaxia.plaze.ui.tongdun.domain.MulBorQueryReview;

public interface MulBorBatchDetailService {
	
	int save(MulBorBatchDetail mulBorTrnBatchDetail);
	/**
	 * 查询状态为0的任务
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> findFormularyNumTasksByType(Map<String, Object> params);
	/**
	 * 更改状态
	 */
	public int updateSingleTask(String uuId, String status);
	/**
	 * 根据文件ID获取文件中记录的信息
	 * @param batchFileId
	 * @return
	 */
	List<MulBorQueryReview> queryDetailListByPage(String batchFileId);
}
