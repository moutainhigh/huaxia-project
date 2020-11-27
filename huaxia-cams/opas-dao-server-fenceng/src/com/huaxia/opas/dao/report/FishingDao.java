package com.huaxia.opas.dao.report;

import java.util.List;
import java.util.Map;

import com.huaxia.opas.domain.report.Fishing;
import com.huaxia.opas.domain.report.OutsourceDetail;

public interface FishingDao {
	/**
	*@Title:getFishingCountByCrtDateOrApplyChannle
	*@Description:获取符合条件（日期时间，渠道）的总条数
	*@param paraMap
	*@return
	*@author: gaohui
	*@Date:2017年3月12日下午7:16:30
	 */
	public String getFishingCountByCrtDateOrApplyChannle(Map<String, Object> paraMap);
	/**
	*@Title:getFishingListByCrtDateOrApplyChannle
	*@Description:获取符合条件（日期时间，渠道）的所有数据
	*@param paraMap
	*@param pageNum 第几页
	*@param pageRows 每页显示的数目
	*@return
	*@author: gaohui
	*@Date:2017年3月12日下午7:38:19
	 */
	public List<Fishing>findFishingListByCrtDateOrApplyChannle(Map<String, Object> paraMap,int pageNum, int pageRows);
	
	public void insertFishing(Fishing addFish);
	public List<OutsourceDetail> selectListOutsource(Map<String, Object> paraMap, int pageNum, int pageRows);
	public int selectTodaySubmitNum(Map<String, Object> paraMaps);
	public int selectTodayDetailNum(Map<String, Object> paraMaps);
	public int selectTodayInventoryNum(Map<String, Object> paraMaps);
	public int selectTodayBackNum(Map<String, Object> paraMaps);
	public OutsourceDetail selectEnterQueueDayNum(Map<String, Object> paraMaps);
	public List<OutsourceDetail> selectReturnNumInfo(Map<String, Object> map);
	public int selectHistoryBackNum(Map<String, Object> paraMaps);
	public List<OutsourceDetail> selectNowdayInfo(Map<String, Object> map);
}