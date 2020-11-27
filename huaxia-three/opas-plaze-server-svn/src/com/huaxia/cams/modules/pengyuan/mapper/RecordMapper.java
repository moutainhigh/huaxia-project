package com.huaxia.cams.modules.pengyuan.mapper;

public interface RecordMapper {

	int selectPcrCountByAppId(String appId);
	
	int selecPwsltCountByAppId(String appId);
	
	int selectYlzCountByAppId(String appId);
}
