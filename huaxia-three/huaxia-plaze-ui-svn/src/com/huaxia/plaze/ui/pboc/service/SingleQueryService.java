package com.huaxia.plaze.ui.pboc.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.huaxia.plaze.common.PageProperty;
import com.huaxia.plaze.ui.pboc.domain.FileStorageInfo;
import com.huaxia.plaze.ui.pboc.domain.SingleQueryReview;

//交易复核信息业务处理层
public interface SingleQueryService {

	int saveObject(SingleQueryReview review);

	/** 分页总数量 */
	int queryListCountByPage(PageProperty property);

	/** 分页总记录 */
	List<SingleQueryReview> queryListByPage(PageProperty property);

	/* 批量通过 */
	int updateBatchAgree(Map<String, Object> args);

	/* 批量退回 */
	int updateBatchReject(Map<String, Object> args);

	/* 单个提交查询 */
	int updateSubmitAgree(Map<String, Object> args);

	/* 单个提交拒绝 */
	int updateSubmitReject(Map<String, Object> args);

	/* 批量提交复核 */
	int saveObjectList(SingleQueryReview queryReview, MultipartFile[] files, String batchNo) throws Exception;

	/* 根据ID查明细 */
	SingleQueryReview queryObjectById(String id);

	/* 根据ID更新单条记录 */
	int updateObject(SingleQueryReview queryReview);

	/*查人行询在区间范围时间提交查询的总数*/
	int queryAllowCount(Map<String, Object> args);

	String checkAuth(String userId);

	int saveObject(List<FileStorageInfo> infoList, SingleQueryReview queryReview);

	int saveObjectBatch(List<FileStorageInfo> infoList, SingleQueryReview queryReview, MultipartFile[] files,
			String batchNo) throws Exception;

	int checkUser(String ids);

}
