package com.huaxia.plaze.ui.baoxin.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.huaxia.plaze.ui.baoxin.mapper.BaoxinConfigurateMapper;
import com.huaxia.plaze.ui.baoxin.service.BaoxinConfigurateService;

@Service("baoxinConfigurateService")
public class BaoxinConfigurateServiceImpl implements BaoxinConfigurateService {
	
	private final static Logger logger = LoggerFactory.getLogger(BaoxinConfigurateServiceImpl.class);

	@Resource
	private BaoxinConfigurateMapper baoxinConfigurateMapper;

	@Override
	public List<Map<String, Integer>> queryCountByParams(Map<String, Object> args) {
		return baoxinConfigurateMapper.selectCountByParams(args);
	}
}
