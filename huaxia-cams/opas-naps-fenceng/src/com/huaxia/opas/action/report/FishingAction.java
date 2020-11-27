package com.huaxia.opas.action.report;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import com.huateng.neofp.core.Action;
import com.huateng.neofp.core.Context;
import com.huaxia.opas.service.report.FishingService;

import net.sf.json.JSONObject;

/**
 * 捞件信息
 * 
 * @author gaohui
 *
 */
public class FishingAction implements Action{

	//private static Logger logger = LoggerFactory.getLogger(FishingAction.class);
	@Resource(name = "fishingService")
	private FishingService fishingService;
	public FishingService getFishingService() {
		return fishingService;
	}
	public void setFishingService(FishingService fishingService) {
		this.fishingService = fishingService;
	}

	/**
	*@Title:showFishingData
	*@Description:显示捞件信息表的数据
	*@param context
	*@author: gaohui
	*@Date:2017年3月12日下午1:52:58
	 */
	public void showFishingData(Context context) {
		Map<String, Object> paraMap = new HashMap<String, Object>();
		int pageNum = 0;//当前页
		JSONObject jsonObject	=JSONObject.fromObject(context.getDataMap());
		//SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		String beginDate;
		String endDate;
		if(jsonObject.containsKey("beginDate")){//用于放入map中的时间字段的控制
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
		    paraMap.put("applyChannle",jsonObject.getString("applyChannle"));
		    paraMap.put("ydjFlag",jsonObject.getString("ydjFlag").toString());//易达金标识
		int curNum = Integer.parseInt(String.valueOf(context.getDataMap().get("page") == null ? 0 : context.getDataMap().get("page")));
		//每页显示的条数
		int pageRows = Integer.parseInt(String.valueOf(context.getDataMap().get("rows") == null ? 0 : context.getDataMap().get("rows")));
		if (curNum > 1) {
			pageNum = (curNum - 1) * pageRows;
		}
		try{
			context.setDataMap(fishingService.findListFishingByCrtDateOrApplyChannle(paraMap, pageNum, pageRows));
		}catch(Exception e){
			e.printStackTrace();
		}
		}else{//页面第一次加载"beginDate"等键不存在，不进行查询
			Map<String, Object> paraNullMap = new HashMap<String, Object>();
			paraNullMap.put("rows","");
			context.setDataMap(paraNullMap);
		}
	}
	
	/**
	*@Title:showOutsourceData
	*@Description:显示外包报表的数据
	*@param context
	*@author: andong
	*@Date:2017年10月18日下午2:33:48
	 */
	public void showOutsourceData(Context context) {
		Map<String, Object> paraMap = new HashMap<String, Object>();
		int pageNum = 0;//当前页
		JSONObject jsonObject	=JSONObject.fromObject(context.getDataMap());
		String beginDate=null;
		String endDate=null;
		if(jsonObject.containsKey("beginDate")){//用于放入map中的时间字段的控制
			beginDate=context.getDataMap().get("beginDate").toString();
			endDate=context.getDataMap().get("endDate").toString();
			paraMap.put("beginDate", beginDate);
			paraMap.put("endDate", endDate);
			int curNum = Integer.parseInt(String.valueOf(context.getDataMap().get("page") == null ? 0 : context.getDataMap().get("page")));
			//每页显示的条数
			int pageRows = Integer.parseInt(String.valueOf(context.getDataMap().get("rows") == null ? 0 : context.getDataMap().get("rows")));
			if (curNum > 1) {
				pageNum = (curNum - 1) * pageRows;
			}
			try {
				context.setDataMap(fishingService.queryListOutsourceByCrtDate(paraMap, pageNum, pageRows));
			}catch(Exception e){
				e.printStackTrace();
			}
		}else{//页面第一次加载"beginDate"等键不存在，不进行查询
			Map<String, Object> paraNullMap = new HashMap<String, Object>();
			paraNullMap.put("rows","");
			context.setDataMap(paraNullMap);
		}
	}
	
	//退件量查询
	public void queryReturnNumInfo(Context ctx) {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		Map<String, Object> map = ctx.getDataMap();
		String beginDate = map.get("beginDate").toString();
		String endDate = map.get("endDate").toString();
		String userCode = map.get("userCode").toString();
		map.put("beginDate", beginDate);
		map.put("endDate", endDate);
		map.put("userCode", userCode);
		dataMap = fishingService.queryReturnNumInfo(map);
		ctx.setDataMap(dataMap);
		
	}
}
