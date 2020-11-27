package com.huaxia.opas.dao.rule.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.rule.OpasPadDao;

public class OpasPadDaoImpl   extends AbstractDAO implements OpasPadDao,Serializable {
	
	private static final long serialVersionUID = 8424995272342468312L;
	
	//查询pad设备指纹信息
	@Override
	public List<Map<String, String>> queryPadInforByAppId(String appId) {
		return getSqlMap().queryForList("OpasPadInfor.queryPadInforByAppId", appId);
	}

	//查询当前设备Id下所有申请件的不同申请人证件号数量
	@Override
	public int queryIdNumberCountByDeviceId(Map<String,String> paramMap) {
		return getSqlMap().queryForObject("OpasPadInfor.queryIdNumberCountByDeviceId", paramMap);
	}
	
	//查询当前设备Id下决策时间7天内所有申请件的不同申请人证件号数量
	@Override
	public int queryIdNumberCountByDeviceId4Week(Map<String,String> paramMap) {
		return getSqlMap().queryForObject("OpasPadInfor.queryIdNumberCountByDeviceId4Week", paramMap);
	}
	
	//查询当前设备Id下决策时间30天内所有申请件的不同申请人证件号数量
	@Override
	public int queryIdNumberCountByDeviceId4Month(Map<String,String> paramMap) {
		return getSqlMap().queryForObject("OpasPadInfor.queryIdNumberCountByDeviceId4Month", paramMap);
	}

	//查询设备高风险命中情况
	@Override
	public List<Map<String, String>> queryPadANDRiskInfoByAppId(String appId) {
		return getSqlMap().queryForList("OpasPadInfor.queryPadANDRiskInfoByAppId", appId);
	}

	@Override
	public List<Map<String, String>> queryPadIOSRiskInfoByAppId(String appId) {
		return getSqlMap().queryForList("OpasPadInfor.queryPadIOSRiskInfoByAppId", appId);
	}

	@Override
	public List<Map<String, String>> queryPadWAPRiskInfoByAppId(String appId) {
		return getSqlMap().queryForList("OpasPadInfor.queryPadWAPRiskInfoByAppId", appId);
	}

	@Override
	public List<Map<String, String>> queryPadWEBRiskInfoByAppId(String appId) {
		return getSqlMap().queryForList("OpasPadInfor.queryPadWEBRiskInfoByAppId", appId);
	}

	@Override
	public List<String> queryBodyCheckByAppId(String appId) {
		return getSqlMap().queryForList("OpasPadInfor.queryBodyCheckByAppId", appId);
	}

	@Override
	public List<String> queryPhotoCheckByAppId(String appId) {
		return getSqlMap().queryForList("OpasPadInfor.queryPhotoCheckByAppId", appId);
	}

}
