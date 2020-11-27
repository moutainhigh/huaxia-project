package com.huaxia.opas.action.report;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import com.huateng.neofp.core.Action;
import com.huateng.neofp.core.Context;
import com.huaxia.opas.service.report.ArchiveApplicationFileDetailService;

import net.sf.json.JSONObject;

/**
 * 归档申请件明细表
 * 
 * @author gaohui
 *
 */
public class ArchiveApplicationFileDetailAction implements Action{

	//private static Logger logger = LoggerFactory.getLogger(FishingAction.class);
	@Resource(name = "archiveApplicationFileDetailService")
	private ArchiveApplicationFileDetailService archiveApplicationFileDetailService;
	public ArchiveApplicationFileDetailService getArchiveApplicationFileDetailService() {
		return archiveApplicationFileDetailService;
	}
	public void setArchiveApplicationFileDetailService(
			ArchiveApplicationFileDetailService archiveApplicationFileDetailService) {
		this.archiveApplicationFileDetailService = archiveApplicationFileDetailService;
	}

	/**
	*@Title:findArchiveApplicationFileDetailDataByDateDimension
	*@Description:显示相应数据 通过 时间区间和维度及对应维度的数据
	*@param context
	*@author: gaohui
	*@Date:2017年3月15日下午2:32:54
	 */
	public void findArchiveApplicationFileDetailDataByDateDimension(Context context) {
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
				paraMap.put("choseChannel",jsonObject.getString("choseChannel").toString());//渠道
				paraMap.put("choseArea",jsonObject.getString("choseArea").toString());//地区
				paraMap.put("allApplicationCard",jsonObject.getString("allApplicationCard").toString());//申请卡产品(全部)
				paraMap.put("choseApplicationCard",jsonObject.getString("choseApplicationCard").toString());//申请卡产品卡
				paraMap.put("choseOperator",jsonObject.getString("choseOperator").toString());//录入商
				paraMap.put("choseSerialWaterNum",jsonObject.getString("choseSerialWaterNum").toString());//流水号字母
				paraMap.put("choseDimension",jsonObject.getString("choseDimension").toString());//维度
				paraMap.put("choseApplyResult",jsonObject.getString("choseApplyResult").toString());//申请件结果
				paraMap.put("choseAprroveWay",jsonObject.getString("choseAprroveWay").toString());//审批方式
				paraMap.put("ydjFlag",jsonObject.getString("ydjFlag").toString());//易达金标识
				context.setDataMap(archiveApplicationFileDetailService.findArchiveApplicationFileDetailDataByDateDimension(paraMap));
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
