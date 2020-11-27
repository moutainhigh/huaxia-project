package com.huaxia.cams.modules.police.service;

import java.util.Map;

import com.huaxia.cams.modules.police.domain.NciicForeignerInfo;

public interface PoliceForeignerService {
	/**
	 * 根据appid查询
	 * @param appId
	 * @return
	 */
	int getCountByAppId(String appId);

	/**
	 * 保存nciicForeignerInfo到数据库
	 * @param policeXpInfo
	 */
	void savePoliceForeignerInfo(NciicForeignerInfo nciicForeignerInfo);
	/**
	 * @Title: getBirthAndIDTE
	 *@Description: TODO 根据appid查询出生日期和证件有效期
	 *@param appId
	 *@return
	 *@author: LiuWei
	 *@Date: 2020年6月10日上午10:05:49
	 */
	Map<String,String> getBirthAndIDTE(String appId);
	/**
	 * @Title: getSupKarBirthAndIDTE
	 *@Description: TODO根据appid查询附卡出生日期和证件有效期
	 *@param appId
	 *@return
	 *@author: LiuWei
	 *@Date: 2020年6月10日下午1:49:35
	 */
	Map<String,String> getSupKarBirthAndIDTE(String appId);
}
