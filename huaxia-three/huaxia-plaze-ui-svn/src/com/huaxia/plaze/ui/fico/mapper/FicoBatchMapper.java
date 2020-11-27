package com.huaxia.plaze.ui.fico.mapper;

import java.util.List;
import java.util.Map;

import com.huaxia.plaze.ui.fico.domain.FicoBatch;

public interface FicoBatchMapper {
	int insertObject(FicoBatch ficoBatch);
	/**
	 * 查询批次表
	 * @param args
	 * @return
	 */
	List<FicoBatch> selectBatchListByPage(Map<String, Object> args);

	/**
	 * 查询总记录数目
	 * @param args
	 * @return
	 */
	int selectBatchListCountByPage(Map<String, Object> args);
}
