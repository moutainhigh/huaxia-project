package com.huaxia.opas.service.sysparm;

import java.util.List;
import java.util.Map;

import com.huaxia.opas.domain.sysparm.ApplyRiskInfo;
/**
 * 参数管理 风险联系人信息
 * @author wangtao
 *2018-06-26 9：13
 */
public interface ApplyRiskInfoService {
	public int queryApplyRiskInfoCount(ApplyRiskInfo applyRiskInfo);

	public List<ApplyRiskInfo> queryApplyRiskInfoList(ApplyRiskInfo applyRiskInfo, int curNum, int pageNum);

	public int addApplyRiskInfo(ApplyRiskInfo applyRiskInfo);

	public int updateApplyRiskInfo(ApplyRiskInfo applyRiskInfo);

	public int deleteApplyRiskInfo(List<String> autoIds);

	public int insertApplyRiskInfoImportFile(List<ApplyRiskInfo> applyRiskInfoList);

	public List<Map<String, String>> queryAllApplyRiskInfo();

	public int queryApplyRiskInfoByAbCodeCount(String abCode);
	
	public List<ApplyRiskInfo> queryApplyRiskInfoByAbCode(String abCode, int curNum, int pageNum);

	public String queryC5AbCode(String appId);

}
