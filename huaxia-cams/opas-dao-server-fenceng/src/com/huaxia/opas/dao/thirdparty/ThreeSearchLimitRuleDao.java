package com.huaxia.opas.dao.thirdparty;

import java.util.List;
import java.util.Map;

import com.huaxia.opas.domain.rule.Opasbizzmivsdata;

/**
 * 第三方-查询限制规则
 * 
 * @author gaohui
 *
 */
public interface ThreeSearchLimitRuleDao {
	/**
	 *@Title:saveAddRule
	 *@Description:保存 新建的限制规则
	 *@param paramMap
	 *@author: gaohui
	 *@Date:2017年10月25日下午2:01:35
	 */
	void saveAddRule(Map<String,Object> paramMap);
	/**
	 *@Title:selectCountByTypeUniqueCode
	 *@Description:根据 类型和唯一标识 查询条数
	 *@param queryParamMap
	 *@return
	 *@author: gaohui
	 *@Date:2017年10月25日下午2:56:06
	 */
	int selectCountByTypeUniqueCode(Map<String,Object> queryParamMap);
	/**
	 *@Title:selectExistedRuleByType
	 *@Description:根据 类型 查询已存在规则
	 *@param queryParamMap
	 *@return
	 *@author: gaohui
	 *@Date:2017年10月25日下午3:24:56
	 */
	List<Map<String,Object>> selectExistedRuleByType(Map<String,Object> queryParamMap);
	/**
	 *@Title:deleteRulesByIdList
	 *@Description:根据id的list集合 删除相应数据 
	 *@param deleteParamMap
	 *@author: gaohui
	 *@Date:2017年10月25日下午5:48:27
	 */
	void deleteRulesByIdList(Map<String,Object> deleteParamMap);
	/**
	 *@Title:saveSearchNumControl
	 *@Description:保存 最大查询条数 和起止时间的限制
	 *@param saveOrUpdateParamMap
	 *@author: gaohui
	 *@Date:2017年10月26日下午4:31:46
	 */
	void saveSearchNumControl(Map<String,Object> saveOrUpdateParamMap);
	/**
	 *@Title:selectCountSearchNumLimitByType
	 *@Description:根据类型 查询数据库的数量
	 *@param queryParamMap
	 *@return
	 *@author: gaohui
	 *@Date:2017年10月26日下午6:15:51
	 */
	int selectCountSearchNumLimitByType(Map<String,Object> queryParamMap);
	/**
	 *@Title:updateSearchNumControl
	 *@Description:修改最大查询条数 和起止时间的限制
	 *@param saveOrUpdateParamMap
	 *@author: gaohui
	 *@Date:2017年10月26日下午6:19:55
	 */
	void updateSearchNumControl(Map<String,Object> saveOrUpdatequeryParamMap);
	/**
	 *@Title:selectObjectSearchNumLimitByType
	 *@Description:根据类型 查询数据库对应数据
	 *@param queryParamMap
	 *@return 
	 *@author: gaohui
	 *@Date:2017年10月26日下午6:53:06
	 */
	Map<String, Object> selectObjectSearchNumLimitByType (Map<String,Object> queryParamMap);
	/**
	 *@Title:selectCountVehicleTimeQuantumSucceed
	 *@Description:当前时间段 查询成功的条数
	 *@param queryParamMap
	 *@return
	 *@author: gaohui
	 *@Date:2017年10月26日下午7:13:49
	 */
	int selectCountVehicleTimeQuantumSucceed(Map<String,Object> queryParamMap);
	/**
	 *@Title:selectCountTypeLimitSearch
	 *@Description:根据 大类、小类、value 值 确定条数 判断 数据是否存在
	 *@param queryParamMap
	 *@return
	 *@author: gaohui
	 *@Date:2017年10月27日上午10:42:27
	 */
	int selectCountTypeLimitSearch(Map<String,Object> queryParamMap);
	/**
	 *@Title:updateTypeLimitSearch
	 *@Description:根据 大类、小类、value 值 修改 启用标志
	 *@param saveOrUpdatequeryParamMap
	 *@author: gaohui
	 *@Date:2017年10月27日上午10:51:48
	 */
	void updateTypeLimitSearch(Map<String,Object> saveOrUpdatequeryParamMap);
	/**
	 *@Title:saveTypeLimitSearch
	 *@Description:保存 大类、小类、value 值 、 启用标志 等
	 *@param saveOrUpdatequeryParamMap
	 *@author: gaohui
	 *@Date:2017年10月27日上午11:01:31
	 */
	void saveTypeLimitSearch(Map<String,Object> saveOrUpdatequeryParamMap);
	/**
	 *@Title:selectTypeLimitSearch
	 *@Description:返显 限制查询 不同类型的 启用 数据 
	 *@param queryParamMap
	 *@return
	 *@author: gaohui
	 *@Date:2017年10月27日下午1:41:00
	 */
	List<Map<String,Object>> selectTypeLimitSearch(Map<String,Object> queryParamMap);
	/**
	 *@Title:selectVerhicleCheckInfo
	 *@Description: 查询车辆的核查信息
	 *@param queryParamMap
	 *@return
	 *@author: gaohui
	 *@Date:2017年10月30日下午4:32:22
	 */
	Map<String,Object> selectVerhicleCheckInfo(Map<String,Object> queryParamMap);
	/**
	 *@Title:selectVerhicleCheckInfo
	 *@Description: 查询车辆的核查信息
	 *@param queryParamMap
	 *@return
	 *@author: gaohui
	 *@Date:2017年10月30日下午4:32:22
	 */
	Map<String,Object> selectBxVerhicleCheckInfo(String appId);
	/**
	 *@Title:selectCountFicoTimeQuantumSucceed
	 *@Description:当前时间段 Fico查询成功的条数
	 *@param queryParamMap
	 *@return
	 *@author: gaohui
	 *@Date:2018年1月17日下午7:16:15
	 */
	int selectCountFicoTimeQuantumSucceed(Map<String,Object> queryParamMap);
	/**
	 *@Title:selectCountTyTimeQuantumSucceed
	 *@Description:当前时间段 天御分查询成功的条数
	 *@param queryParamMap
	 *@return
	 *@author: gaohui
	 *@Date:2018年2月27日下午1:50:40
	 */
	int selectCountTyTimeQuantumSucceed(Map<String,Object> queryParamMap);
	/**
	 *@selectCountSjTimeQuantumSucceed
	 *@Description:当前时间段 手机实名查询成功的条数
	 *@param queryParamMap
	 *@return
	 *@author: lipengfei
	 *@Date:2018年4月11日上午9:21:20
	 */
	int selectCountSjTimeQuantumSucceed(Map<String, Object> queryMapTwo);
	/**
	 *@selectCountSjTimeQuantumSucceed
	 *@Description:当前时间段 企业及行业信息查询成功的条数
	 *@param queryParamMap
	 *@return
	 *@author: lipengfei
	 *@Date:2018年4月16日下午14:01:20
	 */
	int selectCountQyTimeQuantumSucceed(Map<String, Object> queryMapTwo);
	/**
	 *@Title:selectCountTxyyTimeQuantumSucceed
	 *@Description::当前时间段 通讯运营商查询成功的条数
	 *@param queryParamMap
	 *@return
	 *@author: gaohui
	 *@Date:2018年7月12日下午3:16:36
	 */
	int selectCountTxyyTimeQuantumSucceed(Map<String, Object> queryParamMap);
	/**
	 * 当前时间段 多头借贷查询成功的条数
	 * @param queryParamMap
	 * @return
	 */
	Map<String, String> selectLoanNumber(String yearNumber);
	/**
	 * 当前时间段 多头借贷查询成功的条数
	 * @param queryParamMap
	 * @return
	 */
	Map<String, String> selectSumLoanNumber(String yearNumber);
	/**
	 * 当前时间段 多头借贷查询成功的条数
	 * @param queryParamMap
	 * @return
	 */
	int selectRealLoanCount(Map<String, Object> queryParamMap);
	/**
	 * 当前时间段 多头借贷查询成功的条数
	 * @param queryParamMap
	 * @return
	 */
	int selectLongLoanCountSucceed(Map<String, Object> queryParamMap);
	
	/**
	 * 当前时间段 互联网多头借贷查询成功的条数
	 * @param queryParamMap
	 * @return
	 */
	int selectNetDtjdCount(Map<String, Object> queryParamMap);
	/**
	 * 当前时间段 互联网多头借贷查询成功的条数
	 * @param queryParamMap
	 * @return
	 */
	Map<String, String> queryLoanNumberNet(String yearNumber);
	
	/**
	 *@Title:selectBaoXinRuleByType
	 *@Description:查询已存在的保信汽车卡产品
	 *@param queryMap 保存三方标志   001900-保信汽车
	 *@return
	 *@author: lipengfei
	 *@Date:2020年4月20日下午3:27:43
	 */
	List<Map<String,Object>> selectBaoXinRuleByType(Map<String,Object> queryMap);
	
	/**
	 *@Title:deleteBaoXinRulesByIdList
	 *@Description:删除保信汽车查询卡产品
	 *@param initialMap  前台传来的 原始map
	 *@author: lipengfei
	 *@Date:2020年4月20日下午1:00:12
	 */
	void deleteBaoXinRulesByIdList(Map<String, Object> deleteParamMap);
	
	/**
	 * 保险汽车查询成功数量 
	 * @param queryMapTwo
	 * @return
	 */
	int selectBaoXinCount(Map<String, Object> queryMapTwo);
	
	/**
	 * 保信汽车查询成功数量详细展示
	 * @param yearNumber
	 * @return
	 */
	Map<String, String> queryBaoXinNumberNet(String yearNumber);
	
	List<String> queryAllTypeLimitSearch();
	
	/**
	 *@Title:selectPadPortraitComparisonRuleByType
	 *@Description:查询已存在的人像比对卡产品
	 *@param queryMap 保存三方标志   000201-pad人像比对
	 *@return
	 *@author: chenmeng
	 *@Date:2020年4月29日下午2:32:55
	 */
	List<Map<String, Object>> selectPadPortraitComparisonRuleByType(Map<String, Object> queryParamMap);
	
	/**
	 *@Title:selectCountPadPortraitComparisonTypeLimitSearch
	 *@Description:根据 大类、小类、value 值 确定条数 判断 数据是否存在
	 *@param initialMap
	 *@return
	 *@author: chenmeng
	 *@Date:2020年4月29日下午3:00:20
	 */
	int selectCountPadPortraitComparisonTypeLimitSearch(Map<String, Object> initialMap);
	
	/**
	 *@Title:updatePadPortraitComparisonTypeLimitSearch
	 *@Description:根据 大类、小类、value 值 修改 启用标志
	 *@param initialMap
	 *@author: chenmeng
	 *@Date:2020年4月29日下午3:07:48
	 */
	void updatePadPortraitComparisonTypeLimitSearch(Map<String, Object> initialMap);
	
	/**
	 *@Title:savePadPortraitComparisonTypeLimitSearch
	 *@Description:保存 大类、小类、value 值 、 启用标志 等
	 *@param initialMap
	 *@author: chenmeng
	 *@Date:2020年4月29日下午3:14:31
	 */
	void savePadPortraitComparisonTypeLimitSearch(Map<String, Object> initialMap);
	
	/**
	 *@Title:deletePadPortraitComparisonRulesByIdList
	 *@Description:删除pad人像比对查询卡产品
	 *@param deleteParamMap  前台传来的 原始map
	 *@author: chenmeng
	 *@Date:2020年4月29日下午3:37:12
	 */
	void deletePadPortraitComparisonRulesByIdList(Map<String, Object> deleteParamMap);
	

}
