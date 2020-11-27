package com.huaxia.opas.action.thirdparty;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.huateng.neofp.core.Action;
import com.huateng.neofp.core.Context;
import com.huaxia.opas.service.thirdparty.PYPWSLService;

/**
 * 深圳鹏元高信分
 * @author Wss
 *
 */
public class PYPWSLAction implements Action {
	
	private static Logger logger = LoggerFactory.getLogger(PYPWSLAction.class);
	
	@Autowired
	public PYPWSLService pypwslService;
	
	public void queryPYPWSLInfo(Context context) {
		
		String appId = context.getData("appId").toString();
		Assert.notNull(appId, "请求申请件编号为空!");
		
		try {
			Map<String, Object> pypwsl = pypwslService.queryAllPYPWSLInfo(appId);
			context.setData("success", true);
			context.setData("pypwsl", pypwsl);
			return;
		} catch (Exception e) {
			logger.error("深圳鹏元高信分信息查询异常[" + appId + "]:" + e.getMessage());
		}

		context.setData("success", false);
	}

}
