package com.huaxia.plaze.ui.fico.mapper;

import java.util.List;
import java.util.Map;

import com.huaxia.plaze.ui.fico.domain.FicoBatch;
import com.huaxia.plaze.ui.fico.domain.FicoBatchDetail;
import com.huaxia.plaze.ui.fico.domain.FicoBatchFile;
import com.huaxia.plaze.ui.tongdun.domain.MulBorBatchFile;

public interface FicoBatchFileMapper {
	int insertObject(FicoBatchFile ficoBatchFile);
	/**
	 *获取批次的每个文件
	 * @param batchId
	 * @return
	 */
	List<FicoBatchFile> selectDetailListByPage(String batchId);
	/**
	 * 判断文件名是否重复
	 * @param fileName
	 * @return
	 */
	int isTheFileNameDuplicate(String fileName);
	/**
	 * 统计数量
	 * @param args
	 * @return
	 */
	int countNumber(Map<String, Object> args);
}
