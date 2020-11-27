package com.huaxia.plaze.ui.unicom.mapper;

import java.util.Map;

import com.huaxia.plaze.ui.unicom.domain.PhoneSingle;
import com.huaxia.plaze.ui.unicom.domain.UnicomPhoneData;

public interface PhoneSingleMapper {

	int insert(PhoneSingle phoneTrnSingle);

	int updateStatusById(Map<String, Object> args);
	
	int selectCountByParams(Map<String, Object> args);
	
	UnicomPhoneData selectSingleResultByTrnid(String trnId);
}
