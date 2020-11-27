package com.huaxia.opas.service.collect;

import java.util.List;
import java.util.Map;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.apply.ApplyModifyLog;
import com.huaxia.opas.domain.audit.ApprovaInfoSupp;
import com.huaxia.opas.domain.audit.FicoSdBlaze;
import com.huaxia.opas.domain.collect.InfoCollect;

public interface InfoCollectService {

	InfoCollect queryInfoCollect(Map map) throws CoreException;

	ApprovaInfoSupp queryApprovaInfoSupp(Map s) throws CoreException;

	int insertApprovaInfoSupp(ApprovaInfoSupp s) throws CoreException;

	int updateApprovaInfoSupp(ApprovaInfoSupp s) throws CoreException;

	int deleteApprovaInfoSupp(String s) throws CoreException;
	/**
	*@Title:queryEnteringPageNeedDataByAppId
	*@Description:通过appid获取录入页面所需的 返显数据
	 */
    public Map<String,String> queryEnteringPageNeedDataByAppId(Map<String,Object> paramMap)throws CoreException;
    
    /**
   	*@Title:queryEnteringPageNeedDataByAppId
   	*@Description:通过appid获取录入页面所需的 返显数据
   	 */
    public FicoSdBlaze queryEnteringPageNeedDataByAppId1(Map<String,Object> paramMap)throws CoreException;
    
    /**
     * 征信环节修改录入页面信息(标卡增强信息采集)时插入申请信息修改日志表
     * @param applyModifyLogList
     * @author wangtao
     * @return
     */
	int insertApplyModifyLogList(List<ApplyModifyLog> applyModifyLogList);
	/**
	 * 查询主卡申请表信息的申请人姓名，证件类型，证件号码
	 * @param appId
	 * @return
	 */
	Map<String, String> selectNameAndCardMap(String appId);
	
	/**
	 * 匹配行业代码库中行业代码
	 * @param industryCode
	 * @return
	 */
	int queryCountByIndustryCode(String industryCode);
	
	/**
	 * 查询易大金决策表中区域公积金相关信息
	 * @param appId
	 * @return
	 */
	Map<String, String> queryRegionalReserveFundByAppid(String appId);
}