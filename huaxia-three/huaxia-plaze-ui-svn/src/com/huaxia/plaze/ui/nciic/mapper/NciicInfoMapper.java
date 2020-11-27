package com.huaxia.plaze.ui.nciic.mapper;

import java.util.List;
import java.util.Map;

import com.huaxia.plaze.ui.nciic.domain.NciicInfo;

public interface NciicInfoMapper {

	int selectHistoryListCountByPage(Map<String, Object> args);

	List<NciicInfo> selectHistoryListByPage(Map<String, Object> args);

	NciicInfo selectResult(String trnId);


	
}
