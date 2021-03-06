package com.huaxia.opas.mapper;

import java.util.Map;

public interface PBOCCreditCueMapper {

	int insert(Map<String, Object> entity);
	
	/**
	 *@Title:insertScoreElesMapList
	 *@Description:将 银行  个人信用报告的说明字段  以集合形式入库
	 *@param prarmMap 存放list集合的map
	 *@return
	 *@author: gaohui
	 *@Date:2017年10月18日下午9:30:53
	 */
	int insertScoreElesMapList(Map<String, Object> prarmMap);
}
