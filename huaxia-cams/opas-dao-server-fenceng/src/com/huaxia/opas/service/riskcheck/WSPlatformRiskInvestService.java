package com.huaxia.opas.service.riskcheck;

import java.util.List;
import java.util.Map;

import com.huaxia.opas.domain.riskcheck.WSPlatformRiskInvest;

public interface WSPlatformRiskInvestService {
	/**
	 * 查询网申平台风险排查队列列表总记录数
	 * @param params
	 * @return
	 */
	int queryWSPlatformRiskInvestCount(Map<String, String> params);
	/**
	 * 查询网申平台风险排查队列列表数据
	 * @param params
	 * @return
	 */
	List<Map<String, String>> queryWSPlatformRiskInvestList(Map<String, String> params, int curNum, int pageNum);
	/**
	 * 查询不能继续流转的条形码列表总记录数
	 * @return
	 */
	int queryWSPlatformRiskInvestCountNotParam(Map<String, String> params);
	/**
	 * 查询不能继续流转的条形码列表数据
	 * @param params
	 * @return
	 */
	List<Map<String, String>> queryWSPlatformRiskInvestListNotParam(Map<String, String> params, int curNum, int pageNum);
	/**
	 * 查询平台调查结果查看队列列表总记录数
	 * @param params
	 * @return
	 */
	int queryWSPlatformInvestResultCount(Map<String, String> params);
	/**
	 * 查询平台调查结果查看队列列表数据
	 * @param params
	 * @return
	 */
	List<Map<String, String>> queryWSPlatformInvestResultList(Map<String, String> params, int curNum, int pageNum);
	/**
	 * 根据appId查询出备注信息方法
	 * @param params
	 * @return
	 */
	String checkRemarkInfoByAppId(Map<String, String> params);
	/**
	 * 变更人工调查结果和备注值方法
	 * @param wspri
	 * @return
	 * @throws Exception
	 */
	int updateRemark(WSPlatformRiskInvest wspri) throws Exception;
	/**
	 * 变更拒绝流转申请件的方法
	 * @param wspri
	 * @return
	 * @throws Exception
	 */
	int updateRefuse(WSPlatformRiskInvest wspri) throws Exception;
	/**
	 * 变更继续流转申请件的方法
	 * @param wspri
	 * @return
	 * @throws Exception
	 */
	int updateContinueCirculateSubmit(WSPlatformRiskInvest wspri) throws Exception;
	/**
	 * 继续流转调用工作流方法
	 * @param map
	 */
	public String continueCirculateInvokeBpm(Map<String,Object> map);
	/**
	 * 防止重复操作申请件的过滤方法
	 * @param appIdList
	 * @return
	 */
	public List<Map<String, String>> selectAppIds(List<String> appIdList);
	/**
	 * 批量把申请件插入异步临时表的方法 
	 * @param wspri
	 * @return
	 * @throws Exception
	 */
	int insertTempBatchAppId(List<String> list) throws Exception;
	/**
	 * 获取异步临时表中申请件数据的方法
	 * @param appIdList
	 * @return
	 */
	public List<Map<String, String>> selectTempAppIds();
	/**
	 * 删除异步临时申请表的申请件方法
	 * @param wspri
	 * @return
	 * @throws Exception
	 */
	int deleteTempAppId(String appId) throws Exception;
	/**
	 * 自动删除异步临时申请表的申请件方法
	 * @param wspri
	 * @return
	 * @throws Exception
	 */
	int autoDeleteTempAppIdByFlag() throws Exception;
}
