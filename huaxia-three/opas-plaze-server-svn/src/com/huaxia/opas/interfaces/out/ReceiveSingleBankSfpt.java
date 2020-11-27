package com.huaxia.opas.interfaces.out;
/**
 * 二代人行三方平台调用信审接口
 * @author gaoh
 *
 */
public interface ReceiveSingleBankSfpt {
	/**
	 *@Title:getBankBackMessage
	 *@Description:获取人行
	 *@param json
	 *@return
	 *@author: gaohui
	 *@Date:2019年2月22日下午4:26:23
	 */
	public String getBankBackMessage(String json);

}
