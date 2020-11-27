package com.huaxia.cams.modules.pengyuan.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.huaxia.cams.modules.pengyuan.mapper.RecordMapper;
import com.huaxia.cams.modules.pengyuan.service.RecordService;

@Service("recordService")
public class RecordServiceImpl implements RecordService{

	@Resource
	private RecordMapper recordMapper;

	@Override
	public int selectPcrCountByAppId(String appId) {
		return recordMapper.selectPcrCountByAppId(appId);
	}

	@Override
	public int selecPwsltCountByAppId(String appId) {
		return recordMapper.selecPwsltCountByAppId(appId);
	}

	@Override
	public int selectYlzCountByAppId(String appId) {
		return recordMapper.selectYlzCountByAppId(appId);
	}

}
