package com.huaxia.opas.dao.collect;

import java.util.Map;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.audit.ApprovaInfoSupp;
import com.huaxia.opas.domain.audit.FicoSdBlaze;
import com.huaxia.opas.domain.collect.InfoCollect;

/**
 * 录入
 * 
 * @author xiebinxu 2017年3月3日
 */
public interface InfoCollectDao {

	InfoCollect queryInfoCollect(Map map) throws CoreException;

	ApprovaInfoSupp queryApprovaInfoSupp(Map s) throws CoreException;

	int insertApprovaInfoSupp(ApprovaInfoSupp s) throws CoreException;

	int updateApprovaInfoSupp(ApprovaInfoSupp s) throws CoreException;

	int deleteApprovaInfoSupp(String s) throws CoreException;
	/**
	*@Title:selectStandardCardReturnTableDataByAppid
	*@Description:从标准卡返回表中获取 客户类型数据 custType
	*@param paramMap
	*@return
	*@author: gaohui
	*@Date:2017年3月30日下午8:04:16
	 */
//	public Map<String,Object> selectStandardCardReturnTableDataByAppid(Map paramMap);
	
	/**
	 * 从标准卡的plaze表中获取数据反显至录入审查页面
	 * @param paramMap
	 * @author: wangtao
	 * @return
	 */
	Map<String, String> querySdBlaze(Map<String,Object> paramMap);
	
	FicoSdBlaze querySdBlaze1(Map<String,Object> paramMap);
	
	Map<String, String> selectNameAndCardMap(String appId);
	
	Map<String, Object> querySdBlazeBatch(Map<String,Object> paramMap);

	int insertInfoCollect(Map<String, String> blazeMap);
	
	public int queryCountByIndustryCode(String industryCode);
	/**
	 * 从易大金决策表中获取数据反显至易大金增强信息采集页面：“区域公积金月收入”栏位
	 * @param appId
	 * @return
	 */
	Map<String, String> selectRegionalReserveFundByAppid(String appId);
	/**
	 * 根据appid从基本信息补充表中查询是否已经存在对应的录入信息
	 * @param appId
	 * @return
	 */
	int queryInfoCollectByAppid(String appId);

	int updateInfoCollect(Map<String, String> blazeMap);
}