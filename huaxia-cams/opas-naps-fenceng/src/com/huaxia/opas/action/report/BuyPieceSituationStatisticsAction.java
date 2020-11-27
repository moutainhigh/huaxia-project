package com.huaxia.opas.action.report;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import com.huateng.neofp.core.Action;
import com.huateng.neofp.core.Context;
import com.huaxia.opas.service.report.BuyPieceSituationStatisticsService;

import net.sf.json.JSONObject;

/**
 * 进件情况统计表
 * 
 * @author gaohui
 *
 */
public class BuyPieceSituationStatisticsAction implements Action{

	//private static Logger logger = LoggerFactory.getLogger(FishingAction.class);
	@Resource(name = "buyPieceSituationStatisticsService")
	private BuyPieceSituationStatisticsService buyPieceSituationStatisticsService;
	public BuyPieceSituationStatisticsService getBuyPieceSituationStatisticsService() {
		return buyPieceSituationStatisticsService;
	}
	public void setBuyPieceSituationStatisticsService(
			BuyPieceSituationStatisticsService buyPieceSituationStatisticsService) {
		this.buyPieceSituationStatisticsService = buyPieceSituationStatisticsService;
	}

	/**
	*@Title:queryBuyPieceSituationStatisticsDataByDate
	*@Description:按日期区间对进件量进行查询，并可对未完成申请件条形码进行调阅链接
	*@param context
	*@author: gaohui
	*@Date:2017年3月20日下午4:05:15
	 */
	public void queryBuyPieceSituationStatisticsDataByDate(Context context) {
		Map<String, Object> paraMap = new HashMap<String, Object>();
		JSONObject jsonObject = JSONObject.fromObject(context.getDataMap());
		//SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		String beginDate = null;
		String endDate = null;
		if(jsonObject.containsKey("beginDate")){//用于放入map中的时间字段的控制、及键是否存在的控制
			try {//前台控制 时间、维度 为必填项
				if(!jsonObject.getString("beginDate").toString().equals("")){
				beginDate=jsonObject.getString("beginDate").toString();
				paraMap.put("beginDate", beginDate);
				}else{
				paraMap.put("beginDate","");
				}
				if(!jsonObject.getString("endDate").toString().equals("")){
					endDate=jsonObject.getString("endDate").toString();
					paraMap.put("endDate", endDate);
				}else{
					paraMap.put("endDate","");
				}
				paraMap.put("ydjFlag",jsonObject.getString("ydjFlag").toString());//易达金标识
				context.setDataMap(buyPieceSituationStatisticsService.findBuyPieceSituationStatisticsDataByDate(paraMap));
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}else{//页面第一次加载"beginDate"等键不存在，不进行查询
			Map<String, Object> paraNullMap = new HashMap<String, Object>();
			paraNullMap.put("rows","");
			context.setDataMap(paraNullMap);
		}
	}
}
