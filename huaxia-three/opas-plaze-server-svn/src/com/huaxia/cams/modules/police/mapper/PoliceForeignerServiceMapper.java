package com.huaxia.cams.modules.police.mapper;

import java.util.Map;

import com.huaxia.cams.modules.police.domain.NciicForeignerInfo;

public interface PoliceForeignerServiceMapper {
	/**
	 * 根据appid 判断该条任务是否已经查询过
	 * @param appId
	 * @return
	 */
	int selectCountByAppId(String appId);
	/**
	 * 保存解析后的报文nciicForeignerInfo
	 * @param policeXpInfo
	 */
	void insertPoliceForeignerInfo(NciicForeignerInfo nciicForeignerInfo);
	/**
	 * @Title: selectBirthAndIDTE
	 *@Description: TODO 根据appid查询出生日期和证件有效期
	 *@param appId
	 *@return
	 *@author: LiuWei
	 *@Date: 2020年6月10日上午10:02:18
	 */
	Map<String,String> selectBirthAndIDTE(String appId);
	/**
	 * @Title: selectSupKarBirthAndIDTE
	 *@Description: TODO根据appid查询附卡出生日期和证件有效期
	 *@param appId
	 *@return
	 *@author: LiuWei
	 *@Date: 2020年6月10日下午1:50:51
	 */
	Map<String,String> selectSupKarBirthAndIDTE(String appId);
}
