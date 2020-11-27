package com.huaxia.plaze.ui.unicom.service;

import java.util.Map;

import com.huaxia.plaze.ui.unicom.domain.PhoneSingle;
import com.huaxia.plaze.ui.unicom.domain.UnicomPhoneData;

public interface PhoneSingleService {

	int save(PhoneSingle phoneSingle);

	int updateStatusById(String trnId, String status);

	int queryCountByParams(Map<String, Object> args);

	UnicomPhoneData querySingleResultByTrnid(String trnId);
}
