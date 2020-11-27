package com.huaxia.opas.service.sysparm.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.huaxia.opas.dao.sysparm.ApplyRiskInfoDao;
import com.huaxia.opas.domain.sysparm.ApplyRiskInfo;
import com.huaxia.opas.service.sysparm.ApplyRiskInfoService;
/**
 * 参数管理 风险联系人信息
 * @author wangtao
 *2018-06-26 9：13
 */
public class ApplyRiskInfoServiceImpl implements ApplyRiskInfoService{
	@Resource(name="applyRiskInfoDao")
	private ApplyRiskInfoDao applyRiskInfoDao;
	

	@Override
	public int queryApplyRiskInfoCount(ApplyRiskInfo applyRiskInfo) {
		return applyRiskInfoDao.queryApplyRiskInfoCount(applyRiskInfo);
	}

	@Override
	public List<ApplyRiskInfo> queryApplyRiskInfoList(ApplyRiskInfo applyRiskInfo, int curNum, int pageNum) {
		return applyRiskInfoDao.queryApplyRiskInfoList(applyRiskInfo,curNum,pageNum);
	}
    
	@Override
	public int addApplyRiskInfo(ApplyRiskInfo applyRiskInfo) {
		return applyRiskInfoDao.addApplyRiskInfo(applyRiskInfo);
	}
	
	@Override
	public int updateApplyRiskInfo(ApplyRiskInfo applyRiskInfo) {
		return applyRiskInfoDao.updateApplyRiskInfo(applyRiskInfo);
	}

	@Override
	public int deleteApplyRiskInfo(List<String> autoIds) {
		return applyRiskInfoDao.deleteApplyRiskInfo(autoIds);
	}

	@Override
	public int insertApplyRiskInfoImportFile(List<ApplyRiskInfo> applyRiskInfoList) {
		return applyRiskInfoDao.insertApplyRiskInfoImportFile(applyRiskInfoList);
	}

	@Override
	public List<Map<String, String>> queryAllApplyRiskInfo() {
		return applyRiskInfoDao.queryAllApplyRiskInfo();
	}

	@Override
	public List<ApplyRiskInfo> queryApplyRiskInfoByAbCode(String abCode, int curNum, int pageNum) {
		return applyRiskInfoDao.queryApplyRiskInfoByAbCode(abCode, curNum, pageNum);
	}

	@Override
	public int queryApplyRiskInfoByAbCodeCount(String abCode) {
		return applyRiskInfoDao.queryApplyRiskInfoByAbCodeCount(abCode);
	}

	@Override
	public String queryC5AbCode(String appId) {
		return applyRiskInfoDao.queryC5AbCode(appId);
	}
}
