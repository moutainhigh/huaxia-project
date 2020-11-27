package com.huaxia.plaze.ui.nciic.mapper;

import java.util.List;
import java.util.Map;

import com.huaxia.plaze.ui.nciic.domain.NciicXpInfo;

public interface XpInfoMapper {

	int selectHistoryListCountByPage(Map<String, Object> args);

	List<NciicXpInfo> selectHistoryListByPage(Map<String, Object> args);
	
}
