package com.huaxia.opas.dao.thirdparty;

import java.util.List;
import java.util.Map;

import com.huaxia.opas.domain.rule.OpasBizPubsecGather;

/**
 * 第三方-公安接口
 * 
 * @author zhiguo.li
 *
 */
public interface PoliceDao {

	/**
	 * 查询摘要信息
	 * 
	 * @param params
	 *            查询参数集
	 */
	Map<String, String> selectSummaryInfo(Map<String, String> params);

	Map<String, String> selectDetailInfo(Map<String, String> params);

	List<Object> selectDetailList(Map<String, String> params);
	
	Map<String, String> querytaskStatusInfoByAppId(String appId);

	Map<String, String> queryPoliceInfo(Map<String, String> params);

	Map<String, String> selectPoliceDetailInfo(Map<String, String> map);
	/**
	 *@Title:selectPoliceCountByAppId
	 *@Description:根据appId获取简项公安的条数 
	 *@param appId
	 *@return
	 *@author: gaohui
	 *@Date:2017年11月9日上午9:21:28
	 */
	int selectPoliceCountByAppId(String appId);
	//String selectPoliceStatusInfoByAppId(String appId);
	Map<String,String> selectPoliceStatusInfoByAppId(String appId);
	/**
	 * 根据APPID查询税收居民信息是否一致
	 */
	Map<String,String> selectTaxidInfoByAppId(String appId);
    /**
     * 获取智能语音结论信息
     */
	Map<String, String> selectSVoiceInfo(String appId);
	/**
	 * 获取智能语音是否退回
	 */
	String querySVoiceBackInfoByAppId(String appId);
	Map<String, String> selectPoliceXpInfo(String appId);

	Map<String, String> selectpoliceSummaryInfo(Map<String, String> param);

	Map<String, String> selectPoliceXpDetail(String appId);

	Map<String, String> querySummaryByAppId(Map<String, Object> map);
	/**
	 *@Title:selectDependableIdentityCardByAppid
	 *@Description:根据appId获取可信任身份证的条数 
	 *@param appId
	 *@return
	 *@author: chenmeng
	 *@Date:2020年07月09日上午10:49:00
	 */
	Integer selectDependableIdentityCardByAppid(String appId);

	Map<String, String>  selectC1Idtype(String appId);

	Map<String, String> selectNciicForeignerInfo(Map<String, String> param);

	Map<String, String> selectC2Idtype(String appId);
	/**
	 * 根据appid查询 可信身份认证请求返回数据表  中的 认证结果
	 * @param appId
	 * @return
	 */
	Map<String, String> selectAuthResultByAppid(String appId);

}
