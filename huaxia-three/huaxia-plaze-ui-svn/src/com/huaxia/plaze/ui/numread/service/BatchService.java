package com.huaxia.plaze.ui.numread.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.huaxia.plaze.common.PageProperty;
import com.huaxia.plaze.ui.numread.domain.NumReadBatch;
import com.huaxia.plaze.ui.numread.domain.NumReadBatchDetail;
import com.huaxia.plaze.ui.numread.domain.NumReadBatchFile;
import com.huaxia.plaze.ui.numread.domain.NumReadBatchResponse;
import com.huaxia.plaze.ui.system.domain.User;

/**
 *  人行数字解读 批量
 * @author liyanjie
 *
 */
public interface BatchService {

	String saveBatch(String batchNo, MultipartFile[] files, User loginUser);

	int queryBatchListCountByPage(PageProperty page);

	List<NumReadBatch> queryBatchListByPage(PageProperty page);

	List<NumReadBatchFile> queryFileDetail(String batchId);

	List<NumReadBatchDetail> selectDetail(PageProperty page);

	int selectCountDetail(String batchFileId);

	NumReadBatchResponse selectDetailResponseByTrnId(String trnId);

	List<NumReadBatchDetail> selectDetailByCertNoAndSuccess(PageProperty page);

	int selectCountDetailSuccess(String certNo);

	int saveLog(String certNo, User loginUser);


}
