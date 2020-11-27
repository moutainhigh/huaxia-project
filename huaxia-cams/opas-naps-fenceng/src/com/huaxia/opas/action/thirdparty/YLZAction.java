package com.huaxia.opas.action.thirdparty;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.huateng.neofp.core.Action;
import com.huateng.neofp.core.Context;
import com.huaxia.opas.service.thirdparty.YLZService;

/**
 * 厦门易联众社保
 * @author Wss
 * 2019年5月16日15:09:42
 */
public class YLZAction implements Action {
	
	private static Logger logger = LoggerFactory.getLogger(YLZAction.class);
	
	@Autowired YLZService ylzService;
	
	public void queryYLZInfo(Context context) {
		String appId = context.getData("appId");
		Assert.notNull(appId, "请求申请件编号为空!");
		try {
			Map<String, String> ylz = ylzService.queryAllInfo(appId);
			int queryResult=ylzService.queryCount(appId);
			String queryStatus= null;
	        if(queryResult==0){
	        	queryStatus="未发起查询";
			} else if (queryResult >0) {
				queryStatus="查询成功";
			}
	        ylz.put("queryStatus", queryStatus==null?"":queryStatus);
	        context.setData("success", true);
			context.setData("ylz", ylz);
			
			return;
		} catch (Exception e) {
			logger.error("厦门易联众社保信息查询异常[" + appId + "]:" + e.getMessage());
		}
		context.setData("success", false);
		
	}
	
	/***
	 * 杭州公积金信息
	 * @param context
	 */
	public void queryHzGjjxxInfo (Context context){
		String appId = context.getData("appId");
		Assert.notNull(appId, "请求申请件编号为空!");
		Map<String, String> gjjxxInfo = ylzService.queryHzGjjxxInfo(appId);
		if(null!=gjjxxInfo){
			context.setData("success", true);
			context.setData("gjjxxInfo", gjjxxInfo);
		}
        return;
	}
	
	/***
	 * 厦门公积金信息
	 * @param context
	 */
	public void queryXMPersonal (Context context){
		String appId = context.getData("appId");
		
		Map<String, String> gjjxxInfo = ylzService.queryXMPersonal(appId);
		if(null!=gjjxxInfo){
			context.setData("success", true);
			context.setData("gjjxxInfo", gjjxxInfo);
		}
        return;
	}
	
	/***
	 * 厦门公积金详情信息
	 * @param context
	 */
	public void queryXmGjjInfo (Context context){
		String appId = context.getData("appId");
		
		Map<String, String> gjjxxInfo = ylzService.queryXmGjjInfo(appId);
		if(null!=gjjxxInfo){
			context.setData("success", true);
			context.setData("gjjxxInfo", gjjxxInfo);
		}
        return;
	}
	
	public void queryYcInfo (Context context){
		String appId = context.getData("appId");
		Map<String, String> gjjxxInfo = ylzService.queryYcInfo(appId);
		if(null!=gjjxxInfo){
			context.setData("success", true);
			context.setData("gjjxxInfo", gjjxxInfo);
		}
        return;
	}
	
	public void queryWzgjjInfo (Context context){
		String appId = context.getData("appId");
		Map<String, String> gjjxxInfo = ylzService.queryWzgjjInfo(appId);
		if(null!=gjjxxInfo){
			context.setData("success", true);
			context.setData("gjjxxInfo", gjjxxInfo);
		}
        return;
	}
	
}
