package com.huaxia.opas.service.decision;

import java.util.List;
import java.util.Map;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.apply.ApplyModifyLog;
import com.huaxia.opas.domain.apply.ApplyRemark;
import com.huaxia.opas.domain.decision.CreditTelcheckList;
import com.huaxia.opas.domain.decision.OpaCheckinerResultInfo;
import com.huaxia.opas.domain.decision.OpaInnerDataCheck;
import com.huaxia.opas.domain.decision.OpaRiskInfoCheck;
import com.huaxia.opas.domain.decision.OpasCustBaseInfo;
import com.huaxia.opas.domain.decision.OpasHaveCardCustInfo;
import com.huaxia.opas.domain.decision.TeamdealList;
import com.huaxia.opas.domain.decision.TelcheckAddnote;
import com.huaxia.opas.domain.decision.YdjSysresultInfo;
import com.huaxia.opas.domain.input.BizInpApplC1;
import com.huaxia.opas.domain.rule.Opasbizinpapplc1;
import com.huaxia.opas.domain.sysparm.TargetcompanyList;
import com.huaxia.opas.domain.thirdparty.StockcustTelsaleList;

public interface SysDecisionYdjService  {
	/**
	 * 查询团队是否存在
	 * @author xuzhiguo
	 */
	Map<String,String> queryTeam(String appId) throws Exception;
	
	List queryByAppId(String appId, String flag,Map paramMap,String paramFlag,int currNum,int pageNum) throws Exception;
	TargetcompanyList queryByAppId(String appId) throws Exception;
	TeamdealList queryTeamdealByAppId(String appId) throws Exception;
	List queryDescByAppId(Map map, int curNum,int page) throws Exception;
	 int queryCountDesc(Map record) throws CoreException;
	 int queryCountList(String appId, String flag,Map paramMap,String paramFlag) throws CoreException, Exception;
	OpasCustBaseInfo selectCustInfoByAppId(String appId);

	YdjSysresultInfo selectSysResultInfoByAppId(String appId);

	OpaInnerDataCheck selectInnerDataByAppId(String appId) throws Exception;

	OpaCheckinerResultInfo selectChecKinerInfoByAppId(String appId);

	OpasHaveCardCustInfo selectHaveCardInfoByAppId(String appId);
	
	BizInpApplC1 selectBizInpApplC1ByAppId(String appId) throws CoreException;
	
	void updateBizInpApplC1ByKey(BizInpApplC1 bizInpApplC1,List<ApplyModifyLog> logList,ApplyRemark applyRemark) throws CoreException;
	Map<String, String> queryPbocInfo(String appId);
	//save征信电核注记信息
	void saveTelcheckAddnote(TelcheckAddnote telcheckAddnote) throws CoreException;
	
	void deleteTelcheckAddnote(String appId) throws CoreException;
	
	List<CreditTelcheckList> queryCreditTelcheckList(Map<String, Object> map, int curNum, int pageNum);
	
	int queryCreditTelcheckListCount(Map<String, Object> map);
	
	void insertCreditTelcheckList(CreditTelcheckList creditTelcheckList);
	/**
	 * 内部数据检查 -列表
	 */
	String queryRefuseCodeList (Map map) throws Exception;
	List queryInDataChecK(Map map,int curNum,int pageNum) throws Exception;
	
	List queryInDataChecK(Map map) throws Exception;
	/**
	 * 内部数据检查 -列表
	 */
	int countInDataChecK(Map map) throws Exception;
	/**
	 * 内部数据检查 -公共代码
	 */
	Map queryInDataChecK(String appId) throws Exception;
	//存量电销名单
	StockcustTelsaleList queryStockcustTelsaleList(String autoId) throws Exception;
	
	OpasCustBaseInfo selectCustAndPreSellimitByAppId(String appId);
	/**
	 * 审批页面-客户信息 wdb 
	 */
	OpasCustBaseInfo selectCustAndPreSellimitByAppNo(Map map);
	public Map<String,String> selectCustInfoByCustId(Opasbizinpapplc1 on);
	/**
	 *@Title:findZxCreditInvestigationYdjByAppId
	 *@Description:征信大页面中 征信调查页面 数据返显(易达金)
	 *@param paramMap
	 *@return
	 *@author: gaohui
	 *@Date:2017年4月27日下午1:52:56
	 */
	public Map<String,Object> findZxCreditInvestigationYdjByAppId(Map paramMap);
	/**
	 *@Title:findZxCreditInvestigationBzkByAppId
	 *@Description:征信大页面中 征信调查页面 数据返显(标准卡)
	 *@param paramMap
	 *@return
	 *@author: gaohui
	 *@Date:2017年5月2日下午7:13:49
	 */
	public Map<String,Object> findZxCreditInvestigationBzkByAppId(Map paramMap);
	Object selectSystemDecisionByAppId(String appId, String flag) throws Exception;

	Map<String, Object> queryYDJCreditTelcheckMap(Map<String, Object> map);

	Map<String, Object> queryBZKCreditTelcheckMap(Map<String, Object> map);
	
	String queryWorkCompany(String appId);
	String queryRequestTypeByAppId(String appId);
	
	OpaRiskInfoCheck selectRiskInfoByAppId(String appId, String certifiNo) throws Exception;

	OpaCheckinerResultInfo selectChecKinerInfoByAppId_15(String appId);

	Map<String, Object> selectFqzRstDespByAppId(String appId);
	
	Map<String, String> queryBankInfo(String appId);

	//wdb 查询人像比对结果  2019年8月28日下午16:22:49
	String selectPoliceXPInfo(String appId);
	
}
