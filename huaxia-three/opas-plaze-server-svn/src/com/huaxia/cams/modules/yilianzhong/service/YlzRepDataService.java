package com.huaxia.cams.modules.yilianzhong.service;

import com.huaxia.cams.modules.yilianzhong.domian.YlzRepData;

public interface YlzRepDataService {

	/**
	 * 保存解析后的返回报文
	 * @param ylzRepData
	 * @return
	 */
	int save(YlzRepData ylzRepData);
}
