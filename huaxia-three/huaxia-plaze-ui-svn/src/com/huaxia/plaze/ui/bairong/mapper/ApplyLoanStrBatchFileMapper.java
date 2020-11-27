package com.huaxia.plaze.ui.bairong.mapper;

import java.util.List;

import com.huaxia.plaze.ui.bairong.domain.ApplyLoanStrBatchFile;

public interface ApplyLoanStrBatchFileMapper {

	int insertObject(ApplyLoanStrBatchFile applyLoanStrTrnBatchFile);

	/**
	 * 获取每个文件中的记录执行情况
	 * 
	 * @param batchId
	 * @return
	 */
	List<ApplyLoanStrBatchFile> selectBatchFileListByPage(String batchId);
}
