package com.huaxia.plaze.ui.nciic.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.huaxia.plaze.common.PageProperty;
import com.huaxia.plaze.ui.nciic.domain.NciicInfo;
import com.huaxia.plaze.ui.nciic.domain.NciicXpInfo;
import com.huaxia.plaze.ui.nciic.domain.SimplifyPoliceBatchDetail;
import com.huaxia.plaze.ui.nciic.domain.SimplifyPoliceBatchFile;
import com.huaxia.plaze.ui.nciic.domain.SimplifyPoliceQueryLog;
import com.huaxia.plaze.ui.nciic.domain.SimplifyPoliceSingle;
import com.huaxia.plaze.ui.nciic.domain.NciicBatchReview;
import com.huaxia.plaze.ui.system.domain.User;

public interface SimplifyPoliceService {

	int saveObject(SimplifyPoliceSingle simPoliceTrnSingle);

	int saveObject(String batchNo, MultipartFile[] files, User loginUser) throws Exception;

	int queryBatchListCountByPage(PageProperty page);

	List<NciicBatchReview> queryBatchListByPage(PageProperty page);

	List<SimplifyPoliceBatchFile> queryFileDetail(String batchId);

	List<SimplifyPoliceBatchDetail> queryDetail(String batchFileId);

	int queryLogListCountByPage(PageProperty page);

	List<SimplifyPoliceQueryLog> queryLogListByPage(PageProperty page);

	int updateStatusSingle(SimplifyPoliceSingle trn);

	int queryHistoryListCountByPage(PageProperty page);

	List<NciicInfo> queryHistoryListByPage(PageProperty page, User loginUser);

	NciicInfo queryResult(String trnId);
	
	List<Map<String, Integer>> queryCountByParams(Map<String, Object> args);
	
	int queryXpHistoryListCountByPage(PageProperty page);
	
	List<NciicXpInfo> queryXpHistoryListByPage(PageProperty page, User loginUser);
	
}
