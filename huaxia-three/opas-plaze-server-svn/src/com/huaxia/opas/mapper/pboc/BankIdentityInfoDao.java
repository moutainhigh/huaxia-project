package com.huaxia.opas.mapper.pboc;

import java.util.List;

import com.huaxia.opas.domain.pboc.pim.PB01BH;
import com.huaxia.opas.domain.pboc.pim.PB01Bdata;

/**
 * 1.2 身份信息 PIM   
 * @author gaoh
 *
 */
public interface BankIdentityInfoDao {
	/**
	 *@Title:insertPhoneNumberData
	 *@Description:保存手机号码信息段数据
	 *@param pb01bData 手机号码信息段数据PB01B/PB01Bdata
	 *@author: gaohui
	 *@Date:2018年9月6日上午8:48:55
	 */
	void insertPhoneNumberData(PB01Bdata pb01bData);
	/**
	 *@Title:insertPhoneNumberDetailList
	 *@Description:保存 手机号码信息list集合
	 *@param pb01bhList
	 *@author: gaohui
	 *@Date:2018年9月6日上午9:28:35
	 */
	void insertPhoneNumberDetailList(List<PB01BH> pb01bhList);
}
