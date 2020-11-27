package com.huaxia.cams.modules.pengyuan.service;

public interface RecordService {

	int selectPcrCountByAppId(String appId);
	
	int selecPwsltCountByAppId(String appId);
	
	int selectYlzCountByAppId(String appId);
}
