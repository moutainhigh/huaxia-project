package com.huaxia.plaze.ui.bairong.mapper;

import java.util.List;
import java.util.Map;

import com.huaxia.plaze.ui.bairong.domain.ApplyLoanStrBatch;

public interface ApplyLoanStrBatchMapper {

	int insertObject(ApplyLoanStrBatch applyLoanStrTrnBatch);

	/**
	 * 查询批次表
	 * 
	 * @param args
	 * @return
	 */
	List<ApplyLoanStrBatch> selectBatchListByPage(Map<String, Object> args);

	/**
	 * 查询总记录数目
	 * 
	 * @param args
	 * @return
	 */
	int selectBatchListCountByPage(Map<String, Object> args);
}
