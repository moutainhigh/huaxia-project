package com.huaxia.plaze.ui.nciic.mapper;

import java.util.List;
import java.util.Map;

import com.huaxia.plaze.ui.nciic.domain.SimplifyPoliceBatch;
import com.huaxia.plaze.ui.nciic.domain.SimplifyPoliceBatchDetail;
import com.huaxia.plaze.ui.nciic.domain.SimplifyPoliceBatchFile;
import com.huaxia.plaze.ui.nciic.domain.NciicBatchReview;

public interface SimplifyPoliceBatchMapper {

	void insertDetail(SimplifyPoliceBatchDetail detail);

	int insertFileDetail(SimplifyPoliceBatchFile file);

	void insertBatch(SimplifyPoliceBatch batch);

	int selectBatchListCountByPage(Map<String, Object> args);

	List<NciicBatchReview> selectBatchListByPage(Map<String, Object> args);

	List<SimplifyPoliceBatchFile> selectFileDetail(String batchId);

	List<SimplifyPoliceBatchDetail> selectDetail(String batchFileId);
	
}
