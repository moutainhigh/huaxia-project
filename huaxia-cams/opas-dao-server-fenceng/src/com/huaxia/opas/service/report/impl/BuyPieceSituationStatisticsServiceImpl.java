package com.huaxia.opas.service.report.impl;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.google.common.util.concurrent.AbstractService;
import com.huaxia.opas.dao.report.BuyPieceSituationStatisticsDao;
import com.huaxia.opas.domain.report.EntryStatistics;
import com.huaxia.opas.service.report.BuyPieceSituationStatisticsService;

/**
 * @author gaohui (进件情况统计表)
 *
 */
public class BuyPieceSituationStatisticsServiceImpl extends AbstractService implements BuyPieceSituationStatisticsService, Serializable{
	private static final long serialVersionUID = 7871848735562548188L;
	@Resource(name = "buyPieceSituationStatisticsDao")
	private BuyPieceSituationStatisticsDao buyPieceSituationStatisticsDao;
	public BuyPieceSituationStatisticsDao getBuyPieceSituationStatisticsDao() {
		return buyPieceSituationStatisticsDao;
	}
	public void setBuyPieceSituationStatisticsDao(BuyPieceSituationStatisticsDao buyPieceSituationStatisticsDao) {
		this.buyPieceSituationStatisticsDao = buyPieceSituationStatisticsDao;
	}
	@Override
	protected void doStart() {
	}
	@Override
	protected void doStop() {
	}
	@Override
	public Map<String, Object> findBuyPieceSituationStatisticsDataByDate(Map<String, Object> paraMap) {
		Map<String, Object> paramsMap=  new HashMap<String, Object>();
		paramsMap.put("beginDate",paraMap.get("beginDate"));
		paramsMap.put("endDate",paraMap.get("endDate"));
		paramsMap.put("ydjFlag",paraMap.get("ydjFlag"));//易达金标识
		List<EntryStatistics> listMap =buyPieceSituationStatisticsDao.selectBuyPieceSituationStatisticsDataByDate(paramsMap);
		EntryStatistics hj=new EntryStatistics();
		int hjAppcnt=0;//进件数量  APPCNT
		int hjPass=0;//核准 PASS
		int hjRefuse=0;//拒绝 REFUSE
		int hjNoFinish=0;//未完成 NOFINISH
		int hjCancel=0;//取消 CANCEL
		int hjApproveSum=0;//审批量 APPROVESUM
		String hjPassRate="0.0";//核准率% PASSRATE
		String hjRefuseRate="0.0";//拒绝率% REFUSERATE
		String hjWaitDealRate="0.0";//待处理率% WAITDEALRATE
		for (EntryStatistics entryStatistics : listMap) {
			hjAppcnt+=Integer.parseInt(entryStatistics.getAppcnt());
			hjPass+=Integer.parseInt(entryStatistics.getPass());
			hjCancel+=Integer.parseInt(entryStatistics.getCancel());
			hjRefuse+=Integer.parseInt(entryStatistics.getRefuse());
			hjNoFinish+=Integer.parseInt(entryStatistics.getNoFinish());
		}
		hjApproveSum=hjPass+hjRefuse;//审批量=核准数+拒绝数
		double approveSum = hjApproveSum;
		double noFinish = hjNoFinish;
		hj.setCrtDate("合计");
		hj.setAppcnt(String.valueOf(hjAppcnt));
		hj.setPass(String.valueOf(hjPass));
		hj.setRefuse(String.valueOf(hjRefuse));
		hj.setCancel(String.valueOf(hjCancel));
		hj.setNoFinish(String.valueOf(hjNoFinish));
		if (hjApproveSum!=0){
			hjPassRate=String.format("%.2f",(hjPass/approveSum)*100);
			hjRefuseRate=String.format("%.2f",(hjRefuse/approveSum)*100);
		} 
		if (hjAppcnt!=0){
			hjWaitDealRate=String.format("%.2f",(noFinish/hjAppcnt)*100);
		} 
		
		hj.setPassRate(hjPassRate);
		hj.setRefusuRate(hjRefuseRate);
		hj.setWaitDealRate(hjWaitDealRate);
		List<EntryStatistics> listMapUse=new ArrayList<EntryStatistics>();
		listMapUse.add(hj);
		listMapUse.addAll(listMap);
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("rows", listMapUse);
		return dataMap;
	}
	
}
