package com.huaxia.plaze.ui.tongdun.service;

import java.util.List;
import java.util.Map;

import com.huaxia.plaze.ui.tongdun.domain.MulBorSingle;

public interface MulBorSingleService {

	int save(MulBorSingle mulBorTrnSingle);
	/**
	 * 查询状态，任务类型
	 */
	public List<Map<String, Object>> findFormularyNumTasksByType(Map<String, Object> params);
	/**
	 * 更改状态
	 */
	public int updateStatusById(String trnId, String status);
	
}
