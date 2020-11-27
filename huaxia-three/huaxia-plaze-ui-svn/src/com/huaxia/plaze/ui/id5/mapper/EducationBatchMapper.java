package com.huaxia.plaze.ui.id5.mapper;

import java.util.List;
import java.util.Map;

import com.huaxia.plaze.ui.id5.domain.EducationBatch;
import com.huaxia.plaze.ui.id5.domain.EducationBatchDetail;
import com.huaxia.plaze.ui.id5.domain.EducationBatchFile;

public interface EducationBatchMapper {

	void insertDetail(EducationBatchDetail detail);

	int insertFileDetail(EducationBatchFile file);

	void insertBatch(EducationBatch batch);

	int selectBatchListCountByPage(Map<String, Object> args);

	List<EducationBatch> selectBatchListByPage(Map<String, Object> args);

	List<EducationBatchFile> selectFileDetail(String batchId);

	List<EducationBatchDetail> selectDetail(String batchFileId);
	
	int selectBatchCountByParams(Map<String, Object> args);
	


	
}
