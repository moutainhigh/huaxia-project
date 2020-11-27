package com.huaxia.cams.modules.szjd.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.huaxia.cams.modules.szjd.domain.SzjdResponse;
import com.huaxia.cams.modules.szjd.mapper.SzjdResponseMapper;
import com.huaxia.cams.modules.szjd.service.SzjdResponseService;

@Service("szjdResponseService")
public class SzjdResponseServiceImpl implements SzjdResponseService {
	
	@Resource
	private SzjdResponseMapper szjdResponseMapper;

	@Override
	public void saveSzjdResponse(SzjdResponse szjdResponse) {
		szjdResponseMapper.insertSzjdResponse(szjdResponse);
	}

}
