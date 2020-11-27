package com.huaxia.opas.dao.tripartite;

import java.util.Map;

/**
 * 第三方-信审交互
 * 
 * @author gaohui
 *
 */
public interface BizInpApplC1SfDao {
	/**
	 *@Title:updateCardC1SeaMemberId
	 *@Description:根据流水号前十五位模糊修改c1表海航会员号
	 *@param params
	 *@author: gaohui
	 *@Date:2018年1月19日上午10:09:45
	 */
	void updateCardC1SeaMemberId(Map<String,Object> params);
}
