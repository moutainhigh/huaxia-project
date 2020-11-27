package com.huaxia.opas.service.thirdparty;

import java.util.List;
import java.util.Map;

/**
 * 第三方-蚂蚁接口
 * 
 * @author zhiguo.li
 *
 */
public interface AntService {

	/**
	 * 查询摘要信息
	 * 
	 * @param appId
	 *            申请件编号
	 */
	Map<String, String> selectSummaryInfo(String appId);

	Map<String, String> selectDetailInfo(String appId);
	
	Map<String,String> selectDetailAppId(String appId);
	/**
	 *@Title:findZMRiskTypesGroup
	 *@Description:查询出芝麻的不同的风险类型集合
	 *@param paramMap
	 *@return
	 *@author: gaohui
	 *@Date:2017年7月18日下午4:18:43
	 */
	List<Map<String,Object>>findZMRiskTypesGroup(Map paramMap);
	/**
	 *@Title:findZMriskListMessageCount
	 *@Description:蚂蚁详情的风险名单信息总条数
	 *@param paramMap
	 *@return
	 *@author: gaohui
	 *@Date:2017年7月19日上午10:11:37
	 */
	int findZMriskListMessageCount(Map paramMap);
	/**
	 *@Title:findZMriskListMessageData
	 *@Description:蚂蚁详情的风险名单信息 分页数据
	 *@param map
	 *@return
	 *@author: gaohui
	 *@Date:2017年7月19日上午10:32:02
	 */
	List<Map<String,String>>findZMriskListMessageData(Map map);
	/**
	 *@Title:findThreePartiesTaskRequestInfo
	 *@Description:查询 三方 结果信息表的相关数据 （处理页面 查询状态的显示）
	 *@param map
	 *@return
	 *@author: gaohui
	 *@Date:2017年7月31日上午10:51:49
	 */
	Map<String,String> findThreePartiesTaskRequestInfo(Map map);
	
	String selectIvsScoreByAppId(String appId);
	
	/**
	 *@Description:系统决策页面风险检查查询相关三方信息
	 *@param map
	 *@return List<Map<String,Object>>
	 *@author: 
	 */
	List<Map<String,Object>> selectAntRiskOrder(Map map);
	
	List<Map<String, Object>>  selectBrRistOrder1(Map map);
	
	List<Map<String, Object>>  selectBrRistOrder2(Map map);
	
	String selectCustRiskLevel(Map map);
}
