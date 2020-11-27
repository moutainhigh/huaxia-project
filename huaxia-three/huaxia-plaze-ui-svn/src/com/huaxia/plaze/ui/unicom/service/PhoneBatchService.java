package com.huaxia.plaze.ui.unicom.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.huaxia.plaze.common.PageProperty;
import com.huaxia.plaze.exception.ApplicationException;
import com.huaxia.plaze.ui.system.domain.User;
import com.huaxia.plaze.ui.unicom.domain.PhoneBatchDetail;
import com.huaxia.plaze.ui.unicom.domain.PhoneBatchFile;
import com.huaxia.plaze.ui.unicom.domain.PhoneHistory;
import com.huaxia.plaze.ui.unicom.domain.PhoneQueryLog;
import com.huaxia.plaze.ui.unicom.domain.UnicomBatchInfo;
import com.huaxia.plaze.ui.unicom.domain.UnicomPhoneData;

public interface PhoneBatchService {

	int saveQueryLog(PhoneQueryLog phoneQueryLog);

	String saveBatch(String batchNo, MultipartFile[] files, User loginUser) throws ApplicationException;

	public int queryBatchListCountByPage(PageProperty property);

	List<UnicomBatchInfo> queryBatchListByPage(PageProperty property);

	List<PhoneBatchDetail> selectDetail(String batchFileId);

	int queryLogListCountByPage(PageProperty page);

	List<PhoneQueryLog> queryLogListByPage(PageProperty page);

	List<PhoneBatchFile> queryFileDetail(String batchFileId);

	int queryHistoryListCountByPage(String certNo);

	//int queryHarHistoryListCountByPage(String certNo);

	List<PhoneHistory> HistoryListByPage(PageProperty page);

	//List<PhoneHistory> harHistoryListByPage(PageProperty page);

	UnicomPhoneData getResult(String trnId);

}
