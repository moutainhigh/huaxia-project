package com.huaxia.opas.action.thirdparty;

import java.util.Map;

import javax.annotation.Resource;
import com.huateng.neofp.core.Action;
import com.huateng.neofp.core.Context;
import com.huaxia.opas.service.thirdparty.TianYuService;

/**
 * 第三方-腾讯天御分
 * 
 * @author gaoh
 *
 */
public class TianYuAction implements Action {

//	private static Logger logger = LoggerFactory.getLogger(TianYuAction.class);

	@Resource(name = "tianYuService")
	private TianYuService tianYuService;
	
	public TianYuService getTianYuService() {
		return tianYuService;
	}

	public void setTianYuService(TianYuService tianYuService) {
		this.tianYuService = tianYuService;
	}

	/**
	 *@Title:queryTianYuSummaryInfo
	 *@Description: 查询天御分摘要信息
	 *@param context
	 *@throws Exception
	 *@author: gaohui
	 *@Date:2018年2月27日下午3:00:25
	 */
	public void queryTianYuSummaryInfo(Context context) {
		String appId =context.getData("appId");
		try {
			 Map<String,String> responseMap=tianYuService.queryTianYuSummaryInfoByAppId(appId);
			 context.setData("success", true);
			 context.setData("tianYuSummary", responseMap);
		} catch (Exception e) {
			e.printStackTrace();
			context.setData("success", false);
		}
	}
	
}