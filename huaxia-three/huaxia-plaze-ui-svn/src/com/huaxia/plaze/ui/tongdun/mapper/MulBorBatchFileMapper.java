package com.huaxia.plaze.ui.tongdun.mapper;

import java.util.List;

import com.huaxia.plaze.ui.tongdun.domain.MulBorBatchFile;

public interface MulBorBatchFileMapper {

	int insertObject(MulBorBatchFile mulBorTrnBatchFile);
	/**
	 *获取批次的每个文件
	 * @param batchId
	 * @return
	 */
	List<MulBorBatchFile> selectDetailListByPage(String batchId);
}
