package com.huaxia.plaze.ui.numread.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.huaxia.plaze.common.PageProperty;
import com.huaxia.plaze.ui.numread.domain.NumReadBatchDetail;
import com.huaxia.plaze.ui.numread.domain.NumReadBatchResponse;

public interface BatchDetailMapper {

	int saveNumReadBatchDetail(NumReadBatchDetail detail);

	List<NumReadBatchDetail> selectDetail(Map<String, Object> args);

	int selectCountDetail(String batchFileId);

	NumReadBatchResponse selectDetailResponseByTrnId(String trnId);

	int selectCountDetailSuccess(@Param("certNo") String certNo);

	List<NumReadBatchDetail> selectDetailByCertNoAndSuccess(Map<String, Object> args);

}
