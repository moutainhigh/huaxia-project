package com.huaxia.plaze.ui.fico.mapper;

import java.util.List;
import java.util.Map;

import com.huaxia.plaze.ui.fico.domain.FicoBatch;
import com.huaxia.plaze.ui.fico.domain.FicoBatchDetail;

public interface FicoBatchDetailMapper {
	int insertObject(FicoBatchDetail ficoBatchDetial);
	/**
	 *获取每个文件中的记录执行情况
	 * @param batchId
	 * @return
	 */
	List<FicoBatchDetail> selectDetailListByPage(String batchFileId);
	/**
	 * 查询总记录数目
	 * @param args
	 * @return
	 */
	int selectBatchListCountByPage(Map<String, Object> args);
	
	/**
	 * 查询批次表
	 * @param args
	 * @return
	 */
	List<FicoBatchDetail> selectBatchListByPage(Map<String, Object> args);
	/**
	 * 统计数量
	 * @param args
	 * @return
	 */
	int countNumber(Map<String, Object> args);
}
