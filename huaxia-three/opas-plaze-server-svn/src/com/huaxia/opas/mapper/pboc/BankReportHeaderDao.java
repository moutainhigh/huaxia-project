package com.huaxia.opas.mapper.pboc;

import java.util.List;
import java.util.Map;

import com.huaxia.opas.domain.pboc.prh.PA01CH;
import com.huaxia.opas.domain.pboc.prh.PA01Cdata;
import com.huaxia.opas.domain.pboc.prh.PA01D;
import com.huaxia.opas.domain.pboc.prh.PA01E;


/**
 *  1.1 报告头 PRH 
 * @author gaoh
 *
 */
public interface BankReportHeaderDao {
	/**
	 *@Title:insertPersonalInfo
	 *@Description:保存人行基本信息
	 *@param personalInfo
	 *@author: gaohui
	 *@Date:2018年9月4日下午3:58:32
	 */
	void insertPersonalInfo(Map<String,String> personalInfo);
	/**
	 *@Title:insertOtherIdentityMark
	 *@Description:保存其他身份标识信息段数据
	 *@param pa01cData 其他身份标识信息段  PA01C/PA01Cdata
	 *@author: gaohui
	 *@Date:2018年9月5日下午3:40:36
	 */
	void insertOtherIdentityMark(PA01Cdata pa01cData);
	/**
	 *@Title:insertIdentityDataList
	 *@Description:插入身份信息list集合
	 *@param pa01chList pa01chList 身份信息  PA01C/PA01CH 集合
	 *@author: gaohui
	 *@Date:2018年9月5日下午4:14:51
	 */
	void insertIdentityDataList(List<PA01CH> pa01chList);
	/**
	 *@Title:insertCheatProofCaution
	 *@Description: 插入防欺诈警示信息段  PA01D 
	 *@param pa01d 防欺诈警示信息段
	 *@author: gaohui
	 *@Date:2018年9月5日下午4:14:51
	 */
	void insertCheatProofCaution(PA01D pa01d);
	/**
	 *@Title:insertDissentHint
	 *@Description:插入异议提示信息
	 *@param pa01e
	 *@author: gaohui
	 *@Date:2018年9月5日下午6:15:51
	 */
	void insertDissentHint(PA01E pa01e);
}
