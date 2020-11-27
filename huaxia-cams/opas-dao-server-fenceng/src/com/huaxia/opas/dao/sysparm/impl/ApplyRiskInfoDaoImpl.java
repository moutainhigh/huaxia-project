package com.huaxia.opas.dao.sysparm.impl;

import java.util.List;
import java.util.Map;

import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.sysparm.ApplyRiskInfoDao;
import com.huaxia.opas.domain.sysparm.ApplyRiskInfo;
/**
 * 参数管理 风险联系人信息
 * @author wangtao
 *2018-06-26 9：13
 */
public class ApplyRiskInfoDaoImpl extends AbstractDAO implements ApplyRiskInfoDao {
	/**
	 * 
	 */
	private static final String NAMESPACES = "ApplyRiskInfo.";
	@Override
	public int queryApplyRiskInfoCount(ApplyRiskInfo applyRiskInfo) {
		return getSqlMap().queryForObject(NAMESPACES+"queryApplyRiskInfoCount",applyRiskInfo);
	}

	@Override
	public List<ApplyRiskInfo> queryApplyRiskInfoList(ApplyRiskInfo applyRiskInfo, int curNum, int pageNum) {
		return getSqlMap().queryForList(NAMESPACES+"queryApplyRiskInfoList",applyRiskInfo,curNum,pageNum);
	}

	@Override
	public int addApplyRiskInfo(ApplyRiskInfo applyRiskInfo) {
		return getSqlMap().insert(NAMESPACES+"addApplyRiskInfo",applyRiskInfo);
	}

	@Override
	public int updateApplyRiskInfo(ApplyRiskInfo applyRiskInfo) {
		return getSqlMap().update(NAMESPACES+"updateApplyRiskInfo",applyRiskInfo);
	}

	@Override
	public int deleteApplyRiskInfo(List<String> autoIds) {
		return getSqlMap().delete(NAMESPACES+"deleteApplyRiskInfo",autoIds);
	}

	@Override
	public int insertApplyRiskInfoImportFile(List<ApplyRiskInfo> applyRiskInfoList) {
		return getSqlMap().insert(NAMESPACES+"insertApplyRiskInfoImportFile",applyRiskInfoList);
	}

	@Override
	public List<Map<String, String>> queryAllApplyRiskInfo() {
		return getSqlMap().queryForList(NAMESPACES+"queryAllApplyRiskInfo");
	}


	@Override
	public List<ApplyRiskInfo> queryApplyRiskInfoByAbCode(String abCode, int curNum, int pageNum) {
		return getSqlMap().queryForList(NAMESPACES+"queryApplyRiskInfoByAbCode",abCode,curNum,pageNum);
	}

	@Override
	public int queryApplyRiskInfoByAbCodeCount(String abCode) {
		return getSqlMap().queryForObject(NAMESPACES+"queryApplyRiskInfoByAbCodeCount",abCode);
	}

	@Override
	public String queryC5AbCode(String appId) {
		return getSqlMap().queryForObject(NAMESPACES+"queryC5AbCode",appId);
	}

}
