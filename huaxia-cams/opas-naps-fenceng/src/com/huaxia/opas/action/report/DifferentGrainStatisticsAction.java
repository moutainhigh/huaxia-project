package com.huaxia.opas.action.report;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import com.huateng.neofp.core.Action;
import com.huateng.neofp.core.Context;
import com.huaxia.opas.service.report.DifferentGrainStatisticsService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 不同粒度进件情况统计表
 * 
 * @author gaohui
 *
 */
public class DifferentGrainStatisticsAction implements Action {

	// private static Logger logger =
	// LoggerFactory.getLogger(FishingAction.class);
	@Resource(name = "differentGrainStatisticsService")
	private DifferentGrainStatisticsService differentGrainStatisticsService;

	public DifferentGrainStatisticsService getDifferentGrainStatisticsService() {
		return differentGrainStatisticsService;
	}

	public void setDifferentGrainStatisticsService(DifferentGrainStatisticsService differentGrainStatisticsService) {
		this.differentGrainStatisticsService = differentGrainStatisticsService;
	}

	/**
	 * @Title:queryDifferentGrainStatisticsDataByDateDimension
	 * @Description:从 不同粒度进件情况统计等表中， 按进件日期区间对进件情况进行多维度统计
	 * @param context
	 * @author: gaohui
	 * @Date:2017年3月19日下午5:22:05
	 */
	public void queryDifferentGrainStatisticsDataByDateDimension(Context context) {
		Map<String, Object> paraMap = new HashMap<String, Object>();
		JSONObject jsonObject = JSONObject.fromObject(context.getDataMap());
		//SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		String beginDate = null;
		String endDate = null;
		if (jsonObject.containsKey("beginDate")) {// 用于放入map中的时间字段的控制、及键是否存在的控制
			try {// 前台控制 时间、维度 为必填项
				if (!jsonObject.getString("beginDate").toString().equals("")) {
					beginDate = jsonObject.getString("beginDate").toString();
					paraMap.put("beginDate", beginDate);
				} else {
					paraMap.put("beginDate", "");
				}
				if (!jsonObject.getString("endDate").toString().equals("")) {
					endDate = jsonObject.getString("endDate").toString();
					paraMap.put("endDate", endDate);
				} else {
					paraMap.put("endDate", "");
				}
				paraMap.put("choseChannel", jsonObject.getString("choseChannel").toString());// 渠道
				paraMap.put("choseArea", jsonObject.getString("choseArea").toString());// 地区
				paraMap.put("allApplicationCard", jsonObject.getString("allApplicationCard").toString());// 申请卡产品(全部)
				paraMap.put("choseApplicationCard", jsonObject.getString("choseApplicationCard").toString());// 申请卡产品卡
				paraMap.put("choseOperator", jsonObject.getString("choseOperator").toString());// 录入商
				paraMap.put("choseSerialWaterNum", jsonObject.getString("choseSerialWaterNum").toString());// 流水号字母
				paraMap.put("choseDimension", jsonObject.getString("choseDimension").toString());// 维度
				paraMap.put("ydjFlag",jsonObject.getString("ydjFlag").toString());//易达金标识
				context.setDataMap(
						differentGrainStatisticsService.findDataDifferentGrainStatisticsByDateDimension(paraMap));
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		} else {// 页面第一次加载"beginDate"等键不存在，不进行查询
			Map<String, Object> paraNullMap = new HashMap<String, Object>();
			paraNullMap.put("rows", "");
			context.setDataMap(paraNullMap);
		}
	}
	
	public void queryApplicationCardProducts(Context ctx) {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		List<Map<String, Object>> applicationCardProductsList = differentGrainStatisticsService.queryApplicationCardProducts();

		JSONArray applicationCardProducts = JSONArray.fromObject(applicationCardProductsList);

		dataMap.put("applicationCardProducts", applicationCardProducts.toString());

		ctx.setDataMap(dataMap);
	}
}
