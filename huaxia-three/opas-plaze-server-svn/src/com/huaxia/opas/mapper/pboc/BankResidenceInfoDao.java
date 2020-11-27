package com.huaxia.opas.mapper.pboc;

import java.util.List;
import com.huaxia.opas.domain.pboc.prm.PB03;

/**
 * 居住信息 PRM 
 * @author gaoh
 *
 */
public interface BankResidenceInfoDao {
	/**
	 *@Title:insertResidenceInfoList
	 *@Description: 保存居住信息单元集合
	 *@param pb03List 居住信息单元 PB03集合
	 *@author: gaohui
	 *@Date:2018年9月6日上午10:40:33
	 */
	void insertResidenceInfoList(List<PB03> pb03List);
}
