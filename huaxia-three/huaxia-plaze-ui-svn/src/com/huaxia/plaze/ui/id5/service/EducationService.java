package com.huaxia.plaze.ui.id5.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.huaxia.plaze.common.PageProperty;
import com.huaxia.plaze.ui.id5.domain.Education;
import com.huaxia.plaze.ui.id5.domain.EducationBatch;
import com.huaxia.plaze.ui.id5.domain.EducationBatchDetail;
import com.huaxia.plaze.ui.id5.domain.EducationBatchFile;
import com.huaxia.plaze.ui.id5.domain.EducationQueryLog;
import com.huaxia.plaze.ui.id5.domain.EducationSingle;
import com.huaxia.plaze.ui.system.domain.User;

public interface EducationService {

	int save(EducationSingle educationSingle);

	int saveBatch(String batchNo, MultipartFile[] files, User loginUser) throws Exception;

	int queryBatchListCountByPage(PageProperty page);

	List<EducationBatch> queryBatchListByPage(PageProperty page);

	List<EducationBatchFile> queryFileDetail(String batchId);

	List<EducationBatchDetail> queryDetail(String batchFileId);

	int queryLogListCountByPage(PageProperty page);

	List<EducationQueryLog> queryLogListByPage(PageProperty page);

	int updateStatusSingle(EducationSingle trn);

	int queryHistoryListCountByPage(PageProperty page);

	List<Education> queryHistoryListByPage(PageProperty page, User loginUser);

	Education queryResultByTrnId(String trnId);

	int updateStatusById(String trnId, String queryStatus);

	List<Map<String, Integer>> queryCountByParams(Map<String, Object> params);

	int query24HoursCountByParams(Map<String, Object> args);
	
	String query24HoursDataTrnIDByParams(Map<String, Object> args);
}
