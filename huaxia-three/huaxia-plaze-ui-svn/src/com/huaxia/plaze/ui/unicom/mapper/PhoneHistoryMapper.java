package com.huaxia.plaze.ui.unicom.mapper;

import java.util.List;
import java.util.Map;
import com.huaxia.plaze.ui.unicom.domain.PhoneHistory;

public interface PhoneHistoryMapper {

	int queryHistoryListCountByPage(Map<String, Object> args);

	//int queryHarHistoryListCountByPage(Map<String, Object> args);

	List<PhoneHistory> HistoryListByPage(Map<String, Object> args);

	//List<PhoneHistory> harHistoryListByPage(Map<String, Object> args);
}
