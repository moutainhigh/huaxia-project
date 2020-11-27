package com.huaxia.opas.service.allot;

import java.util.Map;

/**
 * 工作流调动人工环节
 * @author  wangdebin
 *
 */
public interface WorkFlowService {

	void saveAllotApply(Map<String,String> map) throws Exception;
	
	//申请件生命周期保存
	int saveAppLifeCicle(String appId,String flag,String demoCode,String back_synFlag) throws Exception;

}
