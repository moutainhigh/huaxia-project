package com.huaxia.plaze.ui.pboc.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.huaxia.plaze.common.PageProperty;
import com.huaxia.plaze.ui.pboc.domain.FileStorageInfo;
import com.huaxia.plaze.ui.pboc.domain.BatchQueryReview;
import com.huaxia.plaze.ui.pboc.domain.SingleQueryReview;

// 批量交易复核信息业务处理层
public interface BatchQueryService {

	int queryBatchIdListCountByPage(PageProperty page);

	List<BatchQueryReview> queryBatchIdListByPage(PageProperty page);

	List<BatchQueryReview> queryBatchIdRefuseListByPage(PageProperty page);
	
	/* 导入记录 分页总数量 */
	int queryBatchListCountByPage(PageProperty page);

	/* 导入记录 分页记录 */
	List<BatchQueryReview> queryBatchListByPage(PageProperty page);

	/* 根据文件ID查明细 分页总数量 */
	int queryFileDetailCount(PageProperty page);

	/* 根据文件ID查明细分页记录 */
	List<SingleQueryReview> queryFileDetailListByPage(PageProperty page);
	
	/*批量查询复核页面提交查询*/
	int updateSubmitAgreeBatch(Map<String, Object> args);
	 /*批量查询复核页面退回*/
	int updateSubmitRejectBatch(Map<String, Object> args);

	int batchUpdate(List<FileStorageInfo> infoList, SingleQueryReview queryReview, MultipartFile[] files, String batchNo, String oldBatchNo) throws Exception;



}
