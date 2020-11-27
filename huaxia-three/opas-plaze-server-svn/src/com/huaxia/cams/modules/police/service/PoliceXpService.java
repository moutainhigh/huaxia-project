package com.huaxia.cams.modules.police.service;

import com.huaxia.cams.modules.police.domain.PoliceXpInfo;

public interface PoliceXpService {
	/**
	 * 根据appid查询
	 * @param appId
	 * @return
	 */
	int getCountByAppId(String appId);

	/**
	 * 保存policeXpInfo到数据库
	 * @param policeXpInfo
	 */
	void savePoliceXpInfo(PoliceXpInfo policeXpInfo);

}
