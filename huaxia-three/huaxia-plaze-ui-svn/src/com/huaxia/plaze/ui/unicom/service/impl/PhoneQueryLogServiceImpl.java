package com.huaxia.plaze.ui.unicom.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.huaxia.plaze.ui.unicom.domain.PhoneQueryLog;
import com.huaxia.plaze.ui.unicom.mapper.PhoneQueryLogMapper;
import com.huaxia.plaze.ui.unicom.service.PhoneLogService;

@Service("phoneQueryLogService")
public class PhoneQueryLogServiceImpl implements PhoneLogService {

	@Resource
	private PhoneQueryLogMapper phoneQueryLogMapper;

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public int savePhoneQueryLog(PhoneQueryLog phoneQueryLog) {
		return phoneQueryLogMapper.savePhoneQueryLog(phoneQueryLog);
	}

}
