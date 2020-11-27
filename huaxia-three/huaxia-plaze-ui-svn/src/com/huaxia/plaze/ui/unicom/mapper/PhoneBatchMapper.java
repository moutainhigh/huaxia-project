package com.huaxia.plaze.ui.unicom.mapper;

import java.util.List;
import java.util.Map;

import com.huaxia.plaze.ui.unicom.domain.PhoneBatch;
import com.huaxia.plaze.ui.unicom.domain.UnicomBatchInfo;

public interface PhoneBatchMapper {

	int savePhoneTrnBatch(PhoneBatch phoneTrnBatch);

	int selectBatchListCountByPage(Map<String, Object> args);

	List<UnicomBatchInfo> selectBatchListByPage(Map<String, Object> args);
}
