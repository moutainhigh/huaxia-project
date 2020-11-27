package com.huaxia.plaze.ui.tongdun.service;

import java.util.List;

import com.huaxia.plaze.common.PageProperty;
import com.huaxia.plaze.ui.tongdun.domain.MulBorAttentionList;
import com.huaxia.plaze.ui.tongdun.domain.MulBorBase;
import com.huaxia.plaze.ui.tongdun.domain.MulBorDiscreditCountMain;
import com.huaxia.plaze.ui.tongdun.domain.MulBorInfo;
import com.huaxia.plaze.ui.tongdun.domain.MulBorRiskList;
public interface MulBorHistoryService {
	List<MulBorBase> selectListByPage(PageProperty page);
	
	MulBorInfo queryMulbor(String trnId);
	
	String queryName(String trnId);
	
	/**
	 * @Title: queryMulBorRiskList
	 *@Description: 查询风险名单，包括两种类型
	 *@param trnId
	 *@return
	 *@author: LiuWei
	 *@Date: 2019年4月10日下午5:07:34
	 */
	List<MulBorRiskList> queryMulBorRiskList(String trnId);
	
	/**
	 * @Title: queryMulBorDiscreditCountMain
	 *@Description: T查询其中的一种类型
	 *@param trnId
	 *@return
	 *@author: LiuWei
	 *@Date: 2019年4月10日下午5:08:54
	 */
	List<MulBorDiscreditCountMain> queryMulBorDiscreditCountMain(String trnId);
	
	/**
	 * @Title: queryMulBorAttentionList
	 *@Description: 查询关注名单
	 *@param trnId
	 *@return
	 *@author: LiuWei
	 *@Date: 2019年4月10日下午5:09:44
	 */
	List<MulBorAttentionList>  queryMulBorAttentionList(String trnId);
}
