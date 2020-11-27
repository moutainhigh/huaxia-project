package com.huaxia.plaze.ui.system.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.huaxia.plaze.ui.system.domain.Sms;
import com.huaxia.plaze.ui.system.mapper.SmsMapper;
import com.huaxia.plaze.ui.system.service.SmsService;
@Service("smsService")
public class SmsServiceImpl implements SmsService{
	@Resource
	private SmsMapper<Sms> smsMapper;
	
	@Override
	public Sms queryById(String sms_id) {
		return smsMapper.selectById(sms_id);
	}

}
