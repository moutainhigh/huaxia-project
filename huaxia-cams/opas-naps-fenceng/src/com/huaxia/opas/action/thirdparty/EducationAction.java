package com.huaxia.opas.action.thirdparty;

import java.util.LinkedHashMap;
import java.util.Map;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.huateng.neofp.core.Action;
import com.huateng.neofp.core.Context;
import com.huaxia.opas.service.thirdparty.AntService;
import com.huaxia.opas.service.thirdparty.EducationService;

import net.sf.json.JSONObject;

/**
 * 第三方-学历
 * 
 * @author zhiguo.li
 *
 */
public class EducationAction implements Action {

	private static Logger logger = LoggerFactory.getLogger(EducationAction.class);

	@Resource(name = "educationService")
	private EducationService educationService;
	public EducationService getEducationService() {
		return educationService;
	}

	public void setEducationService(EducationService educationService) {
		this.educationService = educationService;
	}
	@Resource(name = "antService")
	private AntService antService;
	public AntService getAntService() {
		return antService;
	}

	public void setAntService(AntService antService) {
		this.antService = antService;
	}
	/**
	 *@Title:querySummaryInfo
	 *@Description:查询学历摘要信息 (征信调查页面)
	 *@param context
	 *@throws Exception
	 *@author: gaohui
	 *@Date:2017年7月31日下午2:48:08
	 */
	public void querySummaryInfo(Context context) throws Exception {
		Map map = context.getDataMap();
		String appId = (String) context.getData("appId");
		/*Assert.notNull(appId, "请求申请件编号为空!");
		if (logger.isDebugEnabled()) {
			logger.debug("查询申请件[" + appId + "]学历摘要信息");
		}*/
		Map<String, String> educationSummaryInfo =new  LinkedHashMap<String,String>();
				
		Map<String, String>	 educationSummaryData=  educationService.selectSummaryInfo(appId);
		if(educationSummaryData!=null){
			educationSummaryInfo.putAll(educationSummaryData);
		}
		//Map<String,String> taskRequestInfo= antService.findThreePartiesTaskRequestInfo(map);//三方 任务结果表信息 
		String searchStatus="";
        if(educationSummaryInfo!=null){
        	String quertStatus=educationSummaryInfo.get("queryStatus");
        	if(quertStatus!=null){
        		if("0".equals(quertStatus)){
            		searchStatus="查询成功无数据";
            	}else if("1".equals(quertStatus)){
                 	searchStatus="查询成功有数据";
                }else {
                 	searchStatus="查询失败";
                }
        	}
		}
        educationSummaryInfo.put("searchStatus", searchStatus);
		JSONObject jsonObject = JSONObject.fromObject(educationSummaryInfo);
		context.setData("success", true);
		context.setData("applyEducationSummary", jsonObject.toString());
	}
	/**
	 *@Title:queryDetailInfo
	 *@Description:查询学历详情信息 (征信调查页面，学历详情按钮)
	 *@param context
	 *@throws Exception
	 *@author: gaohui
	 *@Date:2017年7月31日下午2:48:31
	 */
	public void queryDetailInfo(Context context) throws Exception {
		Map map = context.getDataMap();
		String appId = (String) context.getData("appId");
		/*Assert.notNull(appId, "请求申请件编号为空!");
        if (logger.isDebugEnabled()) {
			logger.debug("查询申请件[" + appId + "]学历摘要信息");
		}*/
		Map<String, String> educationDetailInfo =new  LinkedHashMap<String,String>();
				
		Map<String, String>	 educationDetailData= educationService.selectDetailInfo(appId);
		if(educationDetailData!=null){
			educationDetailInfo.putAll(educationDetailData);
		}
		//Map<String,String> taskRequestInfo= antService.findThreePartiesTaskRequestInfo(map);//三方 任务结果表信息 
		String searchStatus="";
		 if(educationDetailInfo!=null){
	        	String quertStatus=educationDetailInfo.get("queryStatus");
	        	if(quertStatus!=null){
	        		if("0".equals(quertStatus)){
	            		searchStatus="查询成功无数据";
	            	}else if("1".equals(quertStatus)){
	                 	searchStatus="查询成功有数据";
	                } else{
	                 	searchStatus="查询失败";
	                }
	        	}
			}
			educationDetailInfo.put("searchStatus", searchStatus);
			/*Gson gson = new Gson();
			String jsonObject= gson.toJson(educationDetailInfo);*/
			context.setData("success", true);
			context.setData("applyEducationDetail", educationDetailInfo);
	}
	
}
