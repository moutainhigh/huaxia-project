package com.huaxia.plaze.ui.bairong.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.huaxia.plaze.common.PageProperty;
import com.huaxia.plaze.ui.bairong.domain.ApplyLoanStrBatch;
import com.huaxia.plaze.ui.bairong.domain.ApplyLoanStrBatchDetail;
import com.huaxia.plaze.ui.bairong.domain.ApplyLoanStrBatchFile;

/**
 * 编辑一些批量表，进行批量数据的入库
 * 
 * @author Liuwei
 */
public interface ApplyLoanStrBatchService {
	
	int saveUploadRecord(MultipartFile file, String batchNo, String account) throws Exception;
	
	int saveTrnBatch(ApplyLoanStrBatch applyLoanStrTrnBatch);

	int saveTrnBatchFile(ApplyLoanStrBatchFile applyLoanStrTrnBatchFile);

	int saveTrnBatchDetail(ApplyLoanStrBatchDetail applyLoanStrTrnBatchDetail);

	/**
	 * @Title: queryBatchListByPage
	 * @Description: 查询显示批量表
	 * @param page
	 * @return
	 * @author: LiuWei
	 * @Date: 2019年6月10日下午4:35:12
	 */
	List<ApplyLoanStrBatch> queryBatchListByPage(PageProperty page);

	/**
	 * @Title: queryBatchListCountByPage
	 * @Description: 获取批量查询到额总的条数
	 * @param page
	 * @return
	 * @author: LiuWei
	 * @Date: 2019年6月10日下午4:35:32
	 */
	int queryBatchListCountByPage(PageProperty page);

	/**
	 * @Title: queryBatchFileListByPage
	 * @Description: 根据batchId获得批次的文件的详情
	 * @param batchId
	 * @return
	 * @author: LiuWei
	 * @Date: 2019年6月10日下午5:52:35
	 */
	List<ApplyLoanStrBatchFile> queryBatchFileListByPage(String batchId);

	/**
	 * @Title: queryBatchDetailListByPage
	 * @Description:根据batchfileid获得文件中每个记录
	 * @param batchFileId
	 * @return
	 * @author: LiuWei
	 * @Date: 2019年6月10日下午5:54:47
	 */
	List<ApplyLoanStrBatchDetail> queryBatchDetailListByPage(String batchFileId);

}
