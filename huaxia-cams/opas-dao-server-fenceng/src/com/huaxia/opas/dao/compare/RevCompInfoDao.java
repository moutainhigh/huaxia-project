package com.huaxia.opas.dao.compare;

import java.util.Map;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.compare.RevCompInfo;

/**
 * 审查录入比对信息
 * 
 * @author xiebinxu 2017年2月25日
 */
public interface RevCompInfoDao {

	int deleteByPrimaryKey(String appId) throws CoreException;

	int insert(RevCompInfo record) throws CoreException;

	int insertSelective(RevCompInfo record) throws CoreException;

	RevCompInfo selectByPrimaryKey(String appId) throws CoreException;

	int updateByPrimaryKeySelective(RevCompInfo record) throws CoreException;

	int updateByPrimaryKey(RevCompInfo record) throws CoreException;
	
	String selectMonByAppId(String appId) throws CoreException;
	
	Map<String,String> selectGongAnByAppId(Map map) throws CoreException;

	int insertRemark(Map<String, Object> remarkMap);

	Map<String, String> selectPoliceMapByAppId(String appId);

	Map<String, String> selectIvsMap(String appId);

	int deleteRemarkByAppId(String appId);

//	Map<String, String> selectFqzRiskDesc(Map<String, String> map2);

	Map<String,String> selectGjjMonth(String appId);

	Map<String,String> selectCurrentStatus(String appId);
	
	Map<String,String> selectInsuredStatus(String appId);
	
	Map<String,String> selectPayStatus(String appId);
	
	Map<String,String> selectCurrNodeKey(String appId);

	Map<String, Object> selectMonthsByAppId(String appId) throws CoreException;

}