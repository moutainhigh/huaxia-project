package com.huaxia.plaze.ui.unicom.mapper;

import java.util.List;
import java.util.Map;

import com.huaxia.plaze.ui.unicom.domain.PhoneBatchDetail;
import com.huaxia.plaze.ui.unicom.domain.UnicomPhoneData;

public interface PhoneBatchDetailMapper {

	int savePhoneTrnBatchDetail(PhoneBatchDetail phoneTrnBatchDetail);

	List<PhoneBatchDetail> selectDetail(String batchFileId);

	UnicomPhoneData getResult(String trnId);

	int selectBatchCountByParams(Map<String, Object> args);
}
