package com.huaxia.opas.dao.fico;

import java.util.Map;

import com.huaxia.opas.domain.fico.Fico;

public interface FicoDao {

	int getCountByAppId(String appId);

	int insert(Fico fico);

	Fico selectConfirmPboc(String appid);
	/**
	 *@Title:selectBizInpApplC1JudgeFlag
	 *@Description:获取申请件的主卡判断标识（YDJ_FLAG, MATCHING_CARD_FLAG ）
	 *@param params
	 *@return
	 *@author: gaohui
	 *@Date:2018年8月30日上午9:07:15
	 */
	Map<String,String> selectBizInpApplC1JudgeFlag(Map<String,String> params);
}
