package com.huaxia.plaze.ui.system.service;

import com.huaxia.plaze.ui.system.domain.Sms;

public interface SmsService {
	
	/** 获取验证码文本 */
	Sms queryById(String sms_id);
}
