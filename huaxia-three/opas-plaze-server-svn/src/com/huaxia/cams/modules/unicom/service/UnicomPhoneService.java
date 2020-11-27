package com.huaxia.cams.modules.unicom.service;

import com.huaxia.cams.modules.unicom.domain.UnicomPhone;

public interface UnicomPhoneService {
	
	/**
	 * 
	 * 根据appId来查询数据表是否存在对应的数据
	 */
	int countByAppId(String appId);
	
	/**
	 * 将解析封装实体插入数据库
	 */
	void saveUnicomPhone(UnicomPhone unicomPhone);

}
