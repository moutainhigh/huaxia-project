package com.huaxia.plaze.ui.bairong.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.huaxia.plaze.ui.bairong.mapper.ApplyLoanStrConfigurateMapper;
import com.huaxia.plaze.ui.bairong.service.ApplyLoanStrConfigurateService;

@Service("applyLoanStrConfigurateService")
public class ApplyLoanStrConfigurateServiceImpl implements ApplyLoanStrConfigurateService {

	@Resource 
	private ApplyLoanStrConfigurateMapper applyLoanStrConfigurateMapper;

	@Override
	public List<Map<String, Integer>> queryCountByParams(Map<String, Object> args) {
		return applyLoanStrConfigurateMapper.selectCountByParams(args);
	}
	
}
