package com.huaxia.plaze.ui.tongdun.service;

import java.util.List;

import com.huaxia.plaze.ui.tongdun.domain.MulBorBatchFile;

public interface MulBorBatchFileService {
	
	int save(MulBorBatchFile mulBorTrnBatchFile);
	
	List<MulBorBatchFile> queryDetailListByPage(String batchId);
}
