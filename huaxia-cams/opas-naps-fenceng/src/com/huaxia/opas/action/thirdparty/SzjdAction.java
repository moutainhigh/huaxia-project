package com.huaxia.opas.action.thirdparty;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huateng.neofp.core.Action;
import com.huateng.neofp.core.Context;
import com.huaxia.opas.service.thirdparty.SzjdService;

/**
 * 第三方-数字解读
 * 
 * @author yuanquan
 *
 */
public class SzjdAction implements Action {

	private static Logger logger = LoggerFactory.getLogger(SzjdAction.class);

	@Resource(name = "szjdService")
	private SzjdService szjdService;
	

	/**
	 *@Title:selectSzjdInfoByAppId
	 *@Description: 查询数字解读信息
	 *@param context
	 *@throws Exception
	 *@author: yuanquan
	 *@Date:20200716
	 */
	public void selectSzjdInfoByAppId(Context context) {
		String appId =context.getData("appId");
		try {
			 Map<String,String> responseMap=szjdService.selectSzjdInfoByAppId(appId);
			 context.setData("success", true);
			 context.setData("szjdSummary", responseMap);
		} catch (Exception e) {
			logger.info("查询"+appId+"数字解读信息失败", e);
			context.setData("success", false);
		}
	}
	
}