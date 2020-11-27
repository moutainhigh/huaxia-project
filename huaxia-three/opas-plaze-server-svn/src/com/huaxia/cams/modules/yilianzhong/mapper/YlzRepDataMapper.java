package com.huaxia.cams.modules.yilianzhong.mapper;

import com.huaxia.cams.modules.yilianzhong.domian.YlzRepData;

public interface YlzRepDataMapper {

	/**
	 * 保存解析后的返回报文
	 * @param ylzRepData
	 * @return
	 */
	int save(YlzRepData ylzRepData);
}
