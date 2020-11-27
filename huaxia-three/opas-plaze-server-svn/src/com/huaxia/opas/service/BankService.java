package com.huaxia.opas.service;

import java.util.Map;

import com.huaxia.opas.domain.pboc.Bank;


/**
 * 人行二期服务接口
 * @author gaoh
 *
 */
public interface BankService {
	/**
	 *@Title:saveBankReportMessage
	 *@Description:二代人行信息入库
	 *@param bank
	 *@author: gaohui
	 *@Date:2018年9月4日下午3:05:16
	 */
	void saveBankReportMessage(Bank bank);
    /**
     *@Title:saveCloneBankData
     *@Description:保存通过lastAppId(最近30天查过人行的app_id)克隆到新的app_id中的人行数据
     *@param params
     *@return
     *@author: gaohui
     *@Date:2018年11月23日上午11:02:16
     */
    Map<String,Object> saveCloneBankData(Map<String,Object> params);
    String queryBankLateAppIdByDayNameIdNo(Map<String,String> params);
}
