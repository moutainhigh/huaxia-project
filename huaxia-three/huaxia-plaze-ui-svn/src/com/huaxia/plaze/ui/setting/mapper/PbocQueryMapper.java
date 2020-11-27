package com.huaxia.plaze.ui.setting.mapper;

import java.util.List;
import java.util.Map;

public interface PbocQueryMapper {
	
	List<Map<String, Integer>> selectCountByParams(Map<String, Object> args);
	
	int insertCountReport();
	
	int selectListCountReportByPage(Map<String, Object> args);
	
	List<Map<String, Object>> selectListReportByPage(Map<String, Object> args);

}
