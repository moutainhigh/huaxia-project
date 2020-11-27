package com.huaxia.opas.service.decision;

import java.util.List;
import java.util.Map;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.allot.AllotApplyAllot;
import com.huaxia.opas.domain.allot.AllotApplyAllotHis;
import com.huaxia.opas.domain.apply.ApplyModifyLog;
import com.huaxia.opas.domain.input.TelcheckResult;
import com.huaxia.opas.domain.report.KeyMessageModify;
import com.huaxia.opas.domain.rule.OpasPbocCreditCue;
import com.huaxia.opas.domain.sysparm.ApproveZipcode;
import com.huaxia.opas.domain.sysparm.CardProduct;
import com.huaxia.opas.domain.thirdparty.BizApproval;
import com.huaxia.opas.domain.thirdparty.BizApprovalHis;

public interface SysButtonCommonService  {


	AllotApplyAllot queryAllotApplyAllot(String appId) throws Exception;


	AllotApplyAllot selectByPrimaryKey(String appId) throws Exception;

	List<KeyMessageModify> selectApplyLogByAppId(String appId,int curNum,int pageNum) throws Exception;

	void updateByPrimaryKeySelective(AllotApplyAllot allot, AllotApplyAllotHis allotHis, String flag,Map<String, String> resMap) throws Exception;

	Map<String,Object> saveOrSubmitButtonDeal(AllotApplyAllot record, AllotApplyAllotHis allotHis, Map<String, String> map,
			String button, Map<String, String> resMap) throws Exception;

	AllotApplyAllotHis queryAllotApplyAllotHisByAppId(Map map)  throws CoreException;

	AllotApplyAllotHis queryAllotApplyAllotHisNozjByAppId(Map map) throws CoreException;

	AllotApplyAllot queryOneByUserId(Map<String, String> map) throws Exception;

	AllotApplyAllot queryZSOneByUserId(Map<String, String> map) throws Exception;


	AllotApplyAllot selectAllotAndAppProdByAppId(String appId) throws Exception;


	int queryCount(String appId);


	String selectZSCurrentNumByUserId(Map<String, String> map) throws Exception;


	AllotApplyAllot selectZSNextOneByUserId(Map<String, String> map) throws Exception;


	void updateSecDecisionFlag(String appId,String flag);


	String selectSecDecisionFlag(String string);
	
	/**
	 * 通过appid获取外国人公安查询结果
	 * @param appIdThd
	 * @return
	 */
	List<Map<String, String>> queryForeignCheckByAppId(String appId);
	
}
