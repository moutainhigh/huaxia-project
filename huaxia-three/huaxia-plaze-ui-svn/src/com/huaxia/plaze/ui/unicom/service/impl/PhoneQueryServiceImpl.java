package com.huaxia.plaze.ui.unicom.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.huaxia.plaze.ui.unicom.mapper.PhoneQueryMapper;
import com.huaxia.plaze.ui.unicom.service.PhoneQueryService;

@Service("phoneQueryService")
public class PhoneQueryServiceImpl implements PhoneQueryService {

	@Resource
	private PhoneQueryMapper phoneQueryMapper;

	@Override
	public List<Map<String, Integer>> queryCountByParams(Map<String, Object> args) {
		return phoneQueryMapper.selectCountByParams(args);
	}

}
