package com.huaxia.opas.dao.tripartite;

import java.util.Map;

/**
 * 第三方-信审交互
 * 
 * @author gaohui
 *
 */
public interface BizInpApplC2SfDao {

	/**
	 *@Title:updateCardC2SeaMemberId
	 *@Description:根据流水号前十五位模糊修改c2表海航会员号
	 *@param params
	 *@author: gaohui
	 *@Date:2018年1月19日上午10:09:45
	 */
	void updateCardC2SeaMemberId(Map<String,Object> params);
}
