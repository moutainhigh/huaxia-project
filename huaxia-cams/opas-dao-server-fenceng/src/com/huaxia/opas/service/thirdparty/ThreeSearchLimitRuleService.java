package com.huaxia.opas.service.thirdparty;

import java.util.List;
import java.util.Map;

/**
 *  第三方-查询限制规则
 * 
 * @author gaohui
 *
 */
public interface ThreeSearchLimitRuleService {

	/**
	 *@Title:saveAddRule
	 *@Description:保存新建的查询限制规则
	 *@param paramMap
	 *@author: gaohui
	 *@Date:2017年10月25日下午1:57:56
	 */
	void saveAddRule(Map<String,Object> paramMap);
	/**
	 *@Title:queryCountByTypeUniqueCode
	 *@Description:根据 类型 和唯一标识 查询条数 
	 *@param queryParamMap
	 *@return
	 *@author: gaohui
	 *@Date:2017年10月25日下午2:53:56
	 */
	int queryCountByTypeUniqueCode(Map<String,Object> queryParamMap);
	/**
	 *@Title:queryExistedRuleByType
	 *@Description:根据类型 查询已存在规则
	 *@param queryParamMap
	 *@return
	 *@author: gaohui
	 *@Date:2017年10月25日下午3:22:58
	 */
	List<Map<String,Object>> queryExistedRuleByType(Map<String,Object> queryParamMap);
	/**
	 *@Title:deleteRulesByIdList
	 *@Description:根据id的list集合 删除相应数据
	 *@param deleteParamMap
	 *@author: gaohui
	 *@Date:2017年10月25日下午4:51:28
	 */
	void deleteRulesByIdList(Map<String,Object> deleteParamMap);
	
	/**
	 *@Title:saveOrUpdateSearchNumControl
	 *@Description:保存或修改 最大查询条数 和起止时间的限制
	 *@param saveOrUpdateParamMap
	 *@author: gaohui
	 *@Date:2017年10月26日下午4:23:14
	 */
	void saveOrUpdateSearchNumControl(Map<String,Object> saveOrUpdateParamMap);
	/**
	 *@Title:querySearchNumControlTimeQuantum
	 *@Description:根据类型 和时间段 返显数量查询限制的内容
	 *@param queryParamMap
	 *@return
	 *@author: gaohui
	 *@Date:2017年10月26日下午6:45:37
	 */
	Map<String,Object> querySearchNumControlTimeQuantum(Map<String,Object> queryParamMap);
	/**
	 *@Title:saveOrUpdateTypeLimitSearch
	 *@Description:限制查询  某些类型是否 限制的控制
	 *@param initialMap  前台传来的 原始map
	 *@author: gaohui
	 *@Date:2017年10月27日上午10:35:07
	 */
	void saveOrUpdateTypeLimitSearch(Map initialMap);
	/**
	 *@Title:queryTypeLimitSearch
	 *@Description:限制查询  返显 选中的某些类型 
	 *@param queryParamMap
	 *@return
	 *@author: gaohui
	 *@Date:2017年10月27日下午1:34:44
	 */
	List<Map<String,Object>> queryTypeLimitSearch(Map<String,Object> queryParamMap);
	/**
	 *@Title:queryVerhicleCheckInfo
	 *@Description:证信信息页面查询车辆的核查信息
	 *@param queryParamMap
	 *@return
	 *@author: gaohui
	 *@Date:2017年10月30日下午4:24:34
	 */
	Map<String,Object> queryVerhicleCheckInfo(Map<String,Object> queryParamMap);
	/**
	 *@Title:queryVerhicleCheckInfo
	 *@Description:证信信息页面查询宝信车辆信息
	 *@param appId
	 *@return
	 *@author: gaohui
	 *@Date:2017年10月30日下午4:24:34
	 */
	Map<String,Object> queryBxVerhicleCheckInfo(String appId);
	/**
	 * 查询条数详细部分
	 */
	public Map<String, String> queryLoanNumber(String yearNumber);
	/**
	 * 查询条数详细部分
	 */
	public Map<String, String> querySumLoanNumber(String yearNumber);

	/**
	 * 互联网多头借贷查询条数详细部分
	 */
	Map<String, String> queryLoanNumberNet(String yearNumber);
	
	/**
	 *@Title:queryBaoXinRuleByType
	 *@Description:查询保信汽车已经存在的卡产品信息
	 *@param initialMap 保存三方查询标志  001900-保信汽车
	 *@author: lipengfei
	 * @return 
	 *@Date:2020年4月20日下午3:27:43
	 */
	List<Map<String, Object>> queryBaoXinRuleByType(Map<String,Object> initialMap);
	
	/**
	 *@Title:saveBaoXinTypeLimitSearch
	 *@Description:添加保信汽车查询卡产品
	 *@param initialMap  前台传来的 原始map
	 *@author: lipengfei
	 *@Date:2020年4月20日下午1:00:12
	 */
	void saveBaoXinTypeLimitSearch(Map<String,Object> queryMap);
	
	/**
	 * 查询所有启用状态的卡产品ID
	 * 数据表OPAS_PARAM_CARD_PRODUCT
	 * @return
	 */
	List<String> queryAllTypeLimitSearch();
	/**
	 *@Title:deleteBaoXinRulesByIdList
	 *@Description:删除保信汽车查询卡产品
	 *@param initialMap  前台传来的 原始map
	 *@author: lipengfei
	 *@Date:2020年4月20日下午1:00:12
	 */
	void deleteBaoXinRulesByIdList(Map<String, Object> deleteParamMap);
	
	/**
	 * 保信汽车查询数量详细展示
	 * @param yearNumber
	 * @return
	 */
	Map<String, String> queryBaoXinNumberNet(String yearNumber);
	
	/**
	 *@Title:queryPadPortraitComparisonRuleByType
	 *@Description:查询pad人像比对已经存在的卡产品信息
	 *@param initialMap 保存三方查询标志  000201-pad人像比对
	 *@author: chenmeng
	 * @return 
	 *@Date:2020年4月29日下午2:26:43
	 */
	List<Map<String, Object>> queryPadPortraitComparisonRuleByType(Map<String, Object> queryParamMap);
	
	/**
	 *@Title:savePadPortraitComparisonTypeLimitSearch
	 *@Description:添加pad人像比对查询卡产品
	 *@param initialMap  前台传来的 原始map
	 *@author: chenmeng
	 *@Date:2020年4月29日下午2:48:55
	 */
	void savePadPortraitComparisonTypeLimitSearch(Map<String, Object> kaMap);
	
	/**
	 *@Title:deletePadPortraitComparisonRulesByIdList
	 *@Description:删除pad人像比对查询卡产品
	 *@param deleteParamMap  前台传来的 原始map
	 *@author: chenmeng
	 *@Date:2020年4月29日下午3:35:12
	 */
	void deletePadPortraitComparisonRulesByIdList(Map<String, Object> deleteParamMap);
	

}
