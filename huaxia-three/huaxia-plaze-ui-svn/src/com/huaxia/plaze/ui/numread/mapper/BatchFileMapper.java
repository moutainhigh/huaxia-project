package com.huaxia.plaze.ui.numread.mapper;

import java.util.List;

import com.huaxia.plaze.ui.numread.domain.NumReadBatchFile;

public interface BatchFileMapper {

	int saveNumReadBatchFile(NumReadBatchFile file);

	List<NumReadBatchFile> selectFileDetail(String batchId);

}
