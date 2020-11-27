package com.huaxia.opas.mapper.pboc;

import com.huaxia.opas.domain.pboc.pqo.PC05A;
import com.huaxia.opas.domain.pboc.pqo.PC05B;

/**
 * 1.10 查询记录概要 PQO 
 * @author gaoh
 *
 */
public interface BankQueryRecordSummaryDao {
	/**
	 *@Title:insertLastQueryRecord
	 *@Description:保存上一次查询记录 PC05A
	 *@param pc05a  上一次查询记录 PC05A
	 *@author: gaohui
	 *@Date:2018年9月10日下午3:15:23
	 */
	void insertLastQueryRecord(PC05A pc05a);
	/**
	 *@Title:insertQueryRecordSum
	 *@Description:保存查询记录汇总 PC05B
	 *@param pc05b 查询记录汇总 
	 *@author: gaohui
	 *@Date:2018年9月10日下午3:23:15
	 */
	void insertQueryRecordSum(PC05B pc05b);
}
