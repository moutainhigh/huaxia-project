package com.huaxia.opas.mapper.pboc;

import java.util.Map;

/**
 * 人行二期
 * @author gaoh
 *
 */
public interface BankDao {
    /**
     *@Title:saveCloneBankData
     *@Description:保存通过lastAppId(最近30天查过人行的app_id)克隆到新的app_id中的人行数据
     *@param params
     *@return
     *@author: gaohui
     *@Date:2018年11月23日上午11:02:16
     */
    Map<String,Object> saveCloneBankData(Map<String,Object> params);
    String selectBankLateAppIdByDayNameIdNo(Map<String, String> params);
}
