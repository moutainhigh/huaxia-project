package com.huaxia.cams.modules.jianbing.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.huaxia.cams.modules.jianbing.mapper.JianBingMapper;
import com.huaxia.cams.modules.jianbing.service.JianBingService;
@Service("jianBingService")
public class JianBingServiceImpl implements JianBingService {
	@Resource
	private JianBingMapper jianBingMapper;

	@Override
	public String queryApplyId(String appId) {
		
		return jianBingMapper.selectApplyId(appId);
	}

}
