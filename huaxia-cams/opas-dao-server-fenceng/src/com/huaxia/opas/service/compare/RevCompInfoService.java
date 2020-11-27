package com.huaxia.opas.service.compare;

import java.util.Map;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.compare.RevCompInfo;
import com.huaxia.opas.domain.riskcheck.KeyfiledMarchinfo;

/**
 * 审查录入比对信息
 * @author wangtao v1.1初始版本v1.0
 * 2017-10-20
 * 添加从反欺诈系统查询人行匹配结果的方法
 * @author xiebinxu 2017年2月25日
 */
public interface RevCompInfoService {

	int deleteByPrimaryKey(String appId) throws CoreException;

	int insert(RevCompInfo record, Map<String, Object> newMap, Map<String, Object> beanMap, String appId, String userCode, String isWebBolt, String commitFlag) throws CoreException;

	int insertSelective(RevCompInfo record) throws CoreException;

	RevCompInfo selectByPrimaryKey(String appId) throws CoreException;

	int updateByPrimaryKeySelective(RevCompInfo record) throws CoreException;

	int updateByPrimaryKey(RevCompInfo record, Map<String, Object> newMap, Map<String, Object> beanMap, String appId, String userCode, String isWebBolt, String commitFlag) throws CoreException;
	
	KeyfiledMarchinfo selectKeyfiledMarchinfoByAppId(Map<String, String> map); 
	
	String selectMonByAppId(String appId) throws CoreException ;
	
	Map<String,String> selectGongAnByAppId(Map map) throws CoreException ;

	int insertRemark(Map<String, Object> remarkMap);

	Map<String, String> selectPoliceMapByAppId(String appId);

	Map<String, String> selectIvsMap(String appId);

	int deleteRemarkByAppId(String appId);

    //Map<String, String> selectFqzRiskDesc(Map<String, String> map2);

	Map<String,String>  selectGjjMonth(String appId);
	
	Map<String,String>  selectCurrentStatus(String appId);
	
	Map<String,String>  selectInsuredStatus(String appId);
	
	Map<String,String>  selectPayStatus(String appId);

	Map<String,String>  selectCurrNodeKey(String appId);

	Map<String, Object> selectMonthsByAppId(String appId) throws CoreException;
}