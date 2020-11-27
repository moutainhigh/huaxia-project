package com.huaxia.opas.dao.thirdparty;

import java.util.List;
import java.util.Map;

import com.huaxia.opas.domain.rule.Opasbizzmivsdata;

/**
 * 第三方-蚂蚁接口
 * 
 * @author zhiguo.li
 *
 */
public interface AntDao {

	/**
	 * 查询摘要信息
	 * 
	 * @param appId
	 *            申请件编号
	 */
	Map<String, String> selectSummaryInfo(String appId);

	Map<String, String> selectDetailInfo(String appId);
	
	Map<String,String> selectDetailAppId(String appId);

	Opasbizzmivsdata selectByPrimaryKey(String appId);
	/**
	 *@Title:selectZMRiskTypesGroup
	 *@Description:查询出芝麻不同的风险类型集合
	 *@param paramMap
	 *@return
	 *@author: gaohui
	 *@Date:2017年7月18日下午4:20:59
	 */
	List<Map<String,Object>> selectZMRiskTypesGroup(Map paramMap);
	/**
	 *@Title:selectZMriskListMessageCount
	 *@Description:蚂蚁详情的风险名单信息总条数
	 *@param paramMap
	 *@return
	 *@author: gaohui
	 *@Date:2017年7月19日上午10:14:28
	 */
	int selectZMriskListMessageCount(Map paramMap);
	/**
	 *@Title:selectZMriskListMessageData
	 *@Description:蚂蚁详情的风险名单信息 分页数据
	 *@param map
	 *@return
	 *@author: gaohui
	 *@Date:2017年7月19日上午10:34:06
	 */
	List<Map<String,String>> selectZMriskListMessageData(Map map);
	/**
	 *@Title:selectThreePartiesTaskRequestInfo
	 *@Description:查询 三方 结果信息表的相关数据 （处理页面 查询状态的显示）
	 *@param map
	 *@return
	 *@author: gaohui
	 *@Date:2017年7月31日上午10:54:26
	 */
	Map<String,String> selectThreePartiesTaskRequestInfo(Map map);

	List<Map<String, Object>> selectAntRiskOrder(Map paramMap);

	List<Map<String, Object>> selectBrRistOrder1(Map paramMap);

	List<Map<String, Object>> selectBrRistOrder2(Map paramMap);
	
	String selectIvsScoreByAppId(String appId);

	String selectCustRiskLevel(Map paramMap);
}
