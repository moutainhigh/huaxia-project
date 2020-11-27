package com.huaxia.plaze.ui.system.mapper;

import java.util.Map;

import com.huaxia.plaze.ui.system.domain.PasswordDetail;

public interface PasswordDetailMapper {
	
	int insertObject(PasswordDetail passwordDetail);
	
	int selectCountByPwdDetail(Map<String, Object> args);

}
