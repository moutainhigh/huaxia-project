package com.huaxia.cams.modules.police.mapper;

import com.huaxia.cams.modules.police.domain.PoliceXpInfo;

public interface PoliceXpServiceMapper {
	/**
	 * 根据appid 判断该条任务是否已经查询过
	 * @param appId
	 * @return
	 */
	int selectCountByAppId(String appId);
	/**
	 * 保存解析后的报文policeXpInfo
	 * @param policeXpInfo
	 */
	void insertPoliceXpInfo(PoliceXpInfo policeXpInfo);
}
