package com.huaxia.plaze.ui.fico.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.huaxia.plaze.common.PageProperty;
import com.huaxia.plaze.ui.fico.domain.FicoBatch;
import com.huaxia.plaze.ui.fico.domain.FicoBatchDetail;
import com.huaxia.plaze.ui.fico.domain.FicoBatchFile;
import com.huaxia.plaze.ui.tongdun.domain.MulBorQueryReview;

/**
 * 编辑一些批量表，进行批量数据的入库
 * 
 * @author Liuwei
 */
public interface FicoBatchService {
	/**
	 *文件解析入库
	 * @param file
	 * @param batchNo
	 * @param account
	 * @return
	 * @throws Exception
	 */
	int saveUploadRecord(MultipartFile file, String batchNo, String account) throws Exception;
		
	public int saveTrnBatch(FicoBatch ficoBatch);

	public int saveTrnBatchFile(FicoBatchFile ficoBatchFile);

	public int saveTrnBatchDetail(FicoBatchDetail ficoBatchDetail);
	/**
	 * 批次查询列表
	 * @param page
	 * @return
	 */
	List<FicoBatch> queryBatchListByPage(PageProperty page);
	/**
	 * 批次查询列表总量
	 * @param page
	 * @return
	 */
	int queryBatchListCountByPage(PageProperty page);
	
	/**
	 * 批次查询列表总量
	 * @param page
	 * @return
	 */
	int queryDetailListCountByPage(PageProperty page);
	
	/**
	 * 查询批次中的每个文件
	 * @param batchId
	 * @return
	 */
	List<FicoBatchFile> queryDetailListByPage(String batchId);
	/**
	 * 根据文件ID获取文件中记录的信息
	 * @param batchFileId
	 * @return
	 */
	List<FicoBatchDetail> queryFileDetailListByPage(String batchFileId);
	/**
	 * 根据文件page获取文件中记录的信息
	 * @param batchFileId
	 * @return
	 */
	List<FicoBatchDetail> queryFileDetailListByPage(PageProperty page);
	
	/**
	 * 统计BatchDetail数量
	 * @param args
	 * @return
	 */
	int countBatchDetailNumber(Map<String, Object> args);
	/**
	 * 统计BatchFile数量
	 * @param args
	 * @return
	 */
	int countBatchFileNumber(Map<String, Object> args);
}
