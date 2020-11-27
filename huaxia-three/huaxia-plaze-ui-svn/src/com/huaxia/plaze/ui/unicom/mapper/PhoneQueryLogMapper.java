package com.huaxia.plaze.ui.unicom.mapper;

import java.util.List;
import java.util.Map;
import com.huaxia.plaze.ui.unicom.domain.PhoneQueryLog;

public interface PhoneQueryLogMapper {
	int savePhoneQueryLog(PhoneQueryLog phoneQueryLog);

	int selectLogListCountByPage(Map<String, Object> args);

	List<PhoneQueryLog> selectLogListByPage(Map<String, Object> args);
}
