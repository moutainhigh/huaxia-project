package com.huaxia.plaze.ui.system.mapper;

import com.huaxia.plaze.ui.system.domain.Sms;

public interface SmsMapper<T> {
	
	Sms selectById(String name);
	
}
