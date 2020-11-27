package com.huaxia.opas.action.thirdparty;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.huateng.neofp.core.Action;
import com.huateng.neofp.core.Context;
import com.huaxia.opas.service.thirdparty.PYPCRService;

/**
 * 深圳鹏元个人社保
 * @author Wss
 *
 */
public class PYPCRAction implements Action {
	
	private static Logger logger = LoggerFactory.getLogger(PYPCRAction.class);
	
	@Autowired 
	public PYPCRService pypcrService;

	public void queryPYPCRInfo(Context context) {
		
		String appId = context.getData("appId").toString();
		Assert.notNull(appId, "请求申请件编号为空!");
		
		try {
			Map<String, Object> pypcr = pypcrService.queryPYPCRInfo(appId);
			Map<String, Object> pypcrBase = pypcrService.queryPYPCRBaseInfo(appId);
			/*Map<String, Object> pypcr60 = pypcrService.queryPYPCR60Info(appId);
			Map<String, Object> pypcr60Sylloge = pypcrService.queryPYPCR60SyllogeInfo(appId);*/
			
			Map<String, Object> resultPypcr = getResult(pypcr,pypcrBase); 
					
			context.setData("success", true);
			context.setData("pypcr", resultPypcr);
			return;
		} catch (Exception e) {
			logger.error("深圳鹏元个人社保信息查询异常[" + appId + "]:" + e.getMessage());
		}
		
		context.setData("success", false);
	}
	
	public Map<String, Object> getResult(Map<String, Object> ... maps) {
		Map<String, Object> resultMap = maps[0];
		for (int i = 1; i < maps.length; i++) {
			if (resultMap == null) {
				resultMap = maps[i];
			}
			if (maps[i] == null) {
				break;
			}
			resultMap.putAll(maps[i]);
		}
		return resultMap;
	}
	
}
