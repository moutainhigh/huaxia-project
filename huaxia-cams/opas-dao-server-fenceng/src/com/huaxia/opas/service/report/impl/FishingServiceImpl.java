package com.huaxia.opas.service.report.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.google.common.util.concurrent.AbstractService;
import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.report.FishingDao;
import com.huaxia.opas.domain.report.Fishing;
import com.huaxia.opas.domain.report.OutsourceDetail;
import com.huaxia.opas.service.report.FishingService;
/**
 * @author gaohui 捞件表
 *
 */
public class FishingServiceImpl extends AbstractService implements FishingService, Serializable{
	
	private static final long serialVersionUID = 3158215248743229324L;
	@Resource(name = "fishingDao")
	private FishingDao fishingDao;
	public FishingDao getFishingDao() {
		return fishingDao;
	}
	public void setFishingDao(FishingDao fishingDao) {
		this.fishingDao = fishingDao;
	}
	@Override
	protected void doStart() {
	}
	@Override
	protected void doStop() {
	}
	@Override
	public Map<String, Object> findListFishingByCrtDateOrApplyChannle(Map<String, Object> paramsMap,int pageNum, int pageRows) {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		List<Fishing> fish = new ArrayList<Fishing>();
		List<Fishing> fishingList =fishingDao.findFishingListByCrtDateOrApplyChannle(paramsMap, pageNum, pageRows);
		if (!fishingList.isEmpty()){
			for (Fishing fishing :fishingList){
				String approveReuslt = fishing.getApproveReuslt();
				if ("1".equals(approveReuslt)){
					approveReuslt = "批准";
				} else if ("0".equals(approveReuslt)){
					approveReuslt = "拒绝";
				} 
				fishing.setApproveReuslt(approveReuslt);
				fish.add(fishing);
			}
		}
		int total =0;
		String totalNum=fishingDao.getFishingCountByCrtDateOrApplyChannle(paramsMap);
		if(totalNum!=null){
			total=Integer.parseInt(totalNum);
		}
		dataMap.put("rows", fish);
		dataMap.put("total", total);
		return dataMap;
	}
	
	@Override
	public void insertFishing(Fishing addFish) throws CoreException {
		fishingDao.insertFishing(addFish);
		
	}
	@Override
	public Map<String, Object> queryListOutsourceByCrtDate(Map<String, Object> paraMap, int pageNum, int pageRows) {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		Map<String, Object> paraMaps = new HashMap<String, Object>();
		
		
		List<OutsourceDetail> outsourceDetailList = new ArrayList<OutsourceDetail>();
		List<OutsourceDetail> outsourceList = fishingDao.selectListOutsource(paraMap, pageNum, pageRows);
		if (outsourceList.size()>0) {
			for (int i = 0; i< outsourceList.size(); i++) {
				OutsourceDetail outsourceDetail = new OutsourceDetail();
				String userCode = outsourceList.get(i).getUserCode();
				String userName = outsourceList.get(i).getUserName();
				String beginDate = (String) paraMap.get("beginDate");
				String endDate = (String) paraMap.get("endDate");
				paraMaps.put("userCode", userCode);
				paraMaps.put("beginDate", beginDate);
				paraMaps.put("endDate", endDate);
				
				//当日提交量
				int nowSubNum = fishingDao.selectTodaySubmitNum(paraMaps);
				//当日归档量
				int nowDetailNum = fishingDao.selectTodayDetailNum(paraMaps);
				//当日库存量
				int nowInventoryNum = fishingDao.selectTodayInventoryNum(paraMaps);
				//当日退件量
				int backNum = fishingDao.selectTodayBackNum(paraMaps);
				//历史退件量 
				int historyNum = fishingDao.selectHistoryBackNum(paraMaps);
				
				//backNum= backNum +historyNum;
				//进入未完成队列天数
				OutsourceDetail  outsource= fishingDao.selectEnterQueueDayNum(paraMaps);
				int oneDay = 0;
				int twoDay = 0;
				int threeDay = 0;
				int fourDay = 0;
				int fiveDay = 0;
				if (outsource!=null){
					oneDay = outsource.getOneDay();
					twoDay = outsource.getTwoDay();
					threeDay = outsource.getThreeDay();
					fourDay = outsource.getFourDay();
					fiveDay = outsource.getFiveDay();
				}
				outsourceDetail.setNowSubNum(nowSubNum);
				outsourceDetail.setNowDetailNum(nowDetailNum);
				outsourceDetail.setNowInventoryNum(nowInventoryNum);
				outsourceDetail.setBackNum(backNum);
				outsourceDetail.setUserCode(userCode);
				outsourceDetail.setUserName(userName);
				outsourceDetail.setOneDay(oneDay);
				outsourceDetail.setTwoDay(twoDay);
				outsourceDetail.setThreeDay(threeDay);
				outsourceDetail.setFourDay(fourDay);
				outsourceDetail.setFiveDay(fiveDay);
				outsourceDetailList.add(outsourceDetail);
			}
			dataMap.put("total", outsourceDetailList.size());
			dataMap.put("rows", outsourceDetailList);
		} else {
			dataMap.put("total", 0);
			dataMap.put("rows", outsourceDetailList);
			dataMap.put("messager", "没有查到任何外包人员！");
		}
		return dataMap;
	}
	@Override
	public Map<String, Object> queryReturnNumInfo(Map<String, Object> map) {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		
		//List<OutsourceDetail> outsourceDetailList = new ArrayList<OutsourceDetail>();
		//历史退件
		List<OutsourceDetail> outsourceDetailList = fishingDao.selectReturnNumInfo(map);
		//在退件队列的件
		//List<OutsourceDetail> nowdayList = fishingDao.selectNowdayInfo(map);
		//outsourceDetailList.addAll(historyList);
		//outsourceDetailList.addAll(nowdayList);
		dataMap.put("total", outsourceDetailList.size());
		dataMap.put("rows", outsourceDetailList);
		return dataMap;
	}
}
