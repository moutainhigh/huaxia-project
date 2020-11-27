package com.huaxia.opas.mapper.pboc;

import java.util.List;

import com.huaxia.opas.domain.pboc.ppo.PC040H;
import com.huaxia.opas.domain.pboc.ppo.PC04data;

/**
 * 1.9 公共信息概要 PPO  [1..1]
 * @author gaoh
 *
 */
public interface BankPublicInfoDao {
	/**
	 *@Title:insertPublicInfoData
	 *@Description: 保存公共信息概要信息数据 PC04/pc04Data
	 *@param pc04Data 公共信息概要信息数据
	 *@author: gaohui
	 *@Date:2018年9月10日下午1:59:03
	 */
	void insertPublicInfoData(PC04data pc04Data);
	/**
	 *@Title:insertPublicInfoDelList
	 *@Description:保存公共信息概要信息 PC040H 集合
	 *@param pc040hList 公共信息概要信息
	 *@author: gaohui
	 *@Date:2018年9月10日下午2:04:23
	 */
	void insertPublicInfoDelList(List<PC040H> pc040hList);
}
