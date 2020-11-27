package com.huaxia.opas.mapper.pboc;

import java.util.List;

import com.huaxia.opas.domain.pboc.pom.PB04;

/**
 * 职业信息 POM  
 * @author gaoh
 *
 */
public interface BankProfessionInfoDao {
	/**
	 *@Title:insertProfessionInfoList
	 *@Description:保存职业信息单元集合
	 *@param pb04List 职业信息单元 PB04  集合
	 *@author: gaohui
	 *@Date:2018年9月6日下午2:06:09
	 */
	void insertProfessionInfoList(List<PB04> pb04List);
}
