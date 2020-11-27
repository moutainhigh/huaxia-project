package com.huaxia.opas.service.riskcheck;

import java.io.Serializable;


public interface KeyfiledResultService extends Serializable {

	/**
	 * @param 
	 * appId 申请件编号
	 * stakeholderType 关系人类型
	 * @throws Exception
	 */
	void insertKeyfiledResult(String appId,String stakeholderType) throws Exception;

}
