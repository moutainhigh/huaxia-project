package com.huaxia.opas.action.thirdparty;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import com.huateng.neofp.core.Action;
import com.huateng.neofp.core.Context;
import com.huaxia.opas.service.thirdparty.Credit100Service;

import net.sf.json.JSONObject;

/**
 * 第三方-百融
 * 
 * @author zhiguo.li
 *
 */
public class Credit100Action implements Action {

	private static Logger logger = LoggerFactory.getLogger(Credit100Action.class);

	@Resource(name = "credit100Service")
	private Credit100Service credit100Service;

	/**
	 * 查询百融摘要信息
	 * 
	 * @param context
	 * @throws Exception
	 */
	public void querySummaryInfo(Context context) throws Exception {
		String appId = (String) context.getData("appId");
		Assert.notNull(appId, "请求申请件编号为空!");

		if (logger.isDebugEnabled()) {
			logger.debug("查询申请件[" + appId + "]百融摘要信息");
		}
		//身份证检查
		Map<String, String> pbocSummaryInfo = getCredit100Service().selectSummaryInfo(appId);
		//通过手机号
		Map<String, String> pbocCellData = getCredit100Service().Query_biz_speciallist_cell_data(appId);
		// 查询结果 add by qingfeng.xiu 2019021817:40
		int queryResult = getCredit100Service().Query_bairong_query_result(appId);
		//------------------------------------------
		int flag = 0;
		if (pbocSummaryInfo != null) {
			JSONObject jsonObject = JSONObject.fromObject(pbocSummaryInfo);
			context.setData("IdDate", true);
			context.setData("applyCredit100Id", jsonObject.toString());
			flag++;
		}
		if(pbocCellData != null){
			JSONObject jsonObject = JSONObject.fromObject(pbocCellData);
			context.setData("CellDate", true);
			context.setData("applyCredit100Cell", jsonObject.toString());
			flag++;
		}
		//----------add by qingfeng.xiu 20190218 17:40
		String searchStatus= null;
        if(queryResult==0){
        	searchStatus="未发起查询";
		} else if (queryResult >0) {
			searchStatus="查询成功";
		}
        context.setData("searchStatus",searchStatus==null?"":searchStatus);
		//--------------------------------------------
		if(flag == 0){
			context.setData("success", false);
		}else{
			context.setData("success", true);
		}
	}
	public void queryDetailInfo(Context context) throws Exception {
		String appId = (String) context.getData("appId");
		String str_app = "";
		Assert.notNull(appId, "请求申请件编号为空!");

		if (logger.isDebugEnabled()) {
			logger.debug("查询申请件[" + appId + "]百融详情信息");
		}
		Map<String, String> pbocDetailInfo = new HashMap<>();
		//身份证检查
		Map<String, String> pbocSummaryInfoID = getCredit100Service().selectSummaryInfo(appId);
		//通过手机号
		Map<String, String> pbocCellData = getCredit100Service().Query_biz_speciallist_cell_data(appId);
		Map<String, String> pbocDetailInfoLocation = getCredit100Service().Query_OPAS_BIZ_SPECIALLIST_LM_DATA(appId);
		Map<String, String> pbocDetailInfoConsumption = getCredit100Service().Query_OPAS_BIZ_CONSUMPTION_DATA(appId);
		Map<String, String> pbocDetailInfoScore = getCredit100Service().selectDetailInfo(appId);
		//判断状态
		//Map<String, String> str_appId = getCredit100Service().selectDetailAppId(appId);
		if(pbocSummaryInfoID != null){
			pbocDetailInfo.putAll(pbocSummaryInfoID);
		}
		if(pbocCellData != null){
			pbocDetailInfo.putAll(pbocCellData);
			
		}
		if(pbocDetailInfoLocation != null){
			pbocDetailInfo.putAll(pbocDetailInfoLocation);
			
		}
		if(pbocDetailInfoConsumption !=null){
			pbocDetailInfo.putAll(pbocDetailInfoConsumption);
			
		}
		if(pbocDetailInfoScore !=null){
			pbocDetailInfo.putAll(pbocDetailInfoScore);
			
		}
	
		
		/*if (pbocDetailInfo != null && pbocDetailInfo.size()>0) {
			if(pbocSummaryInfoID != null && pbocSummaryInfoID.size()>0){
				context.setData("QUERYID", true);
			}
			if(pbocCellData != null && pbocCellData.size()>0){
				context.setData("QUERYCELL", true);
			}
			str_app = "查询成功";
			pbocDetailInfo.put("recStatus", str_app);
			JSONObject jsonObject = JSONObject.fromObject(pbocDetailInfo);
			context.setData("success", true);
			context.setData("applyCredit100Detail", jsonObject.toString());
			return;
		}else{
			str_app = "未查询";
			pbocDetailInfo.put("recStatus", str_app);
			JSONObject jsonObject = JSONObject.fromObject(pbocDetailInfo);
			context.setData("success", true);
			context.setData("applyCredit100Detail", jsonObject.toString());
		}*/
		
		List<String> chaxunFlags = getCredit100Service().Query_task_request_info(appId);
		if(chaxunFlags != null && chaxunFlags.size()>0){
			for (String chaxunFlag : chaxunFlags) {
				if("0".equals(chaxunFlag)){
					str_app = "未查询";
					pbocDetailInfo.put("recStatus", str_app);
					JSONObject jsonObject = JSONObject.fromObject(pbocDetailInfo);
					context.setData("success", true);
					context.setData("applyCredit100Detail", jsonObject.toString());
				}else if("1".equals(chaxunFlag)){
					str_app = "查询成功";
					if(pbocSummaryInfoID != null && pbocSummaryInfoID.size()>0){
						context.setData("QUERYID", true);
					}
					if(pbocCellData != null && pbocCellData.size()>0){
						context.setData("QUERYCELL", true);
					}
					pbocDetailInfo.put("recStatus", str_app);
					JSONObject jsonObject = JSONObject.fromObject(pbocDetailInfo);
					context.setData("success", true);
					context.setData("applyCredit100Detail", jsonObject.toString());
				}
			}
		}else{ // 未查询
			str_app = "未查询";
			pbocDetailInfo.put("recStatus", str_app);
			JSONObject jsonObject = JSONObject.fromObject(pbocDetailInfo);
			context.setData("success", true);
			context.setData("applyCredit100Detail", jsonObject.toString());
		}
		context.setData("success", false);
	}

	public Credit100Service getCredit100Service() {
		return credit100Service;
	}

	public void setCredit100Service(Credit100Service credit100Service) {
		this.credit100Service = credit100Service;
	}

}
