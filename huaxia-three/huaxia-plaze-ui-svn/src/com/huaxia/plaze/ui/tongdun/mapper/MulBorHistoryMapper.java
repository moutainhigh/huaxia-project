package com.huaxia.plaze.ui.tongdun.mapper;

import java.util.List;
import java.util.Map;

import com.huaxia.plaze.ui.tongdun.domain.MulBorAntifraudIndex;
import com.huaxia.plaze.ui.tongdun.domain.MulBorAttentionList;
import com.huaxia.plaze.ui.tongdun.domain.MulBorBase;
import com.huaxia.plaze.ui.tongdun.domain.MulBorBlackList;
import com.huaxia.plaze.ui.tongdun.domain.MulBorDescreditCount;
import com.huaxia.plaze.ui.tongdun.domain.MulBorDiscreditCountMain;
import com.huaxia.plaze.ui.tongdun.domain.MulBorGreyList;
import com.huaxia.plaze.ui.tongdun.domain.MulBorRiskItem;
import com.huaxia.plaze.ui.tongdun.domain.MulBorRiskList;

public interface MulBorHistoryMapper {

	List<MulBorBase> selectListByPage(Map<String, Object> args);
	
	MulBorBase queryMulBorBase(String trnId);
	
	MulBorAntifraudIndex queryMulBorAntifraudInde(String trnId);
	
	List<MulBorRiskItem> queryMulBorRiskItem(String trnId);
	
	List<MulBorBlackList> queryMulBorBlackList(String trnId);
	
	List<MulBorDescreditCount> queryMulBorDescreditCount(String trnId);
	
	List<MulBorGreyList> queryMulBorGreyList(String trnId);
	
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
