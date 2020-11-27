package com.huaxia.opas.dao.sysparm;

import java.util.List;
import java.util.Map;

import com.huaxia.opas.domain.sysparm.ApplyRiskInfo;

/**
 * 参数管理 风险联系人信息
 * @author wangtao
 *2018-06-26 9：13
 */
public interface ApplyRiskInfoDao {

	int queryApplyRiskInfoCount(ApplyRiskInfo applyRiskInfo);

	List<ApplyRiskInfo> queryApplyRiskInfoList(ApplyRiskInfo applyRiskInfo, int curNum, int pageNum);

	int addApplyRiskInfo(ApplyRiskInfo applyRiskInfo);

	int updateApplyRiskInfo(ApplyRiskInfo applyRiskInfo);

	int deleteApplyRiskInfo(List<String> autoIds);

	int insertApplyRiskInfoImportFile(List<ApplyRiskInfo> applyRiskInfoList);

	List<Map<String, String>> queryAllApplyRiskInfo();

	List<ApplyRiskInfo> queryApplyRiskInfoByAbCode(String abCode,int curNum, int pageNum);

	int queryApplyRiskInfoByAbCodeCount(String abCode);

	String queryC5AbCode(String appId);

}
