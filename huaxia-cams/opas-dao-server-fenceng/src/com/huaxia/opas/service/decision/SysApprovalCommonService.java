package com.huaxia.opas.service.decision;

import java.util.List;
import java.util.Map;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.allot.AllotApplyAllot;
import com.huaxia.opas.domain.allot.AllotApplyAllotHis;
import com.huaxia.opas.domain.input.BizInpApplC2;
import com.huaxia.opas.domain.input.TelcheckResult;
import com.huaxia.opas.domain.rule.OpasPbocCreditCue;
import com.huaxia.opas.domain.sysparm.ApproveZipcode;
import com.huaxia.opas.domain.sysparm.CardProduct;
import com.huaxia.opas.domain.system.ApRole;
import com.huaxia.opas.domain.thirdparty.BizApproval;
import com.huaxia.opas.domain.thirdparty.BizApprovalHis;

public interface SysApprovalCommonService  {


	Object querySystemApprovalResult(String appId, String flag) throws Exception;
	TelcheckResult selectTelcheckResultByAppId(String appId) throws Exception;
	AllotApplyAllot queryAllotApplyAllot(String appId) throws Exception;
	CardProduct queryCardByCardCode(String cardCode) throws CoreException;
	List<OpasPbocCreditCue> selectOpasPbocCreditCueByAppId(String appId) throws Exception;
	List<ApproveZipcode> queryApproveZipcodeByTelno(Map map) throws CoreException;
	ApRole queryRoleCodeByUserId(String userId) throws CoreException;
	BizApproval selectByAppId(String appId);
	int selectApprovalCard(Map<String, String> map);
	BizInpApplC2 findBiz2info(String appno) throws CoreException;
	int saveOrUpdateApprovalResult(BizApproval bizApproval, BizApprovalHis bizApprovalHis, boolean isSaveHis)
			throws Exception;
	int queryApprovalQzdcByAppId(String appId) throws Exception;
	String findYxqPd(String appId) throws CoreException;
	int findHaveCard(Map<String, Object> map) throws CoreException;
	int getPersonAge(String appId) throws CoreException;
	String selectAppByAppId(String appId);
	String selectApplyByAppId(String appId);
	AllotApplyAllotHis selectLastOneByAppId(String appId) throws CoreException;
	String selectYdjFlagByAppId(String appId);
	void updateApprovalResult(BizApproval bizApproval) throws Exception;
	//近期是否批准相同卡产品
	List<String> selectApplyCardByAppId(String appId);
	//刚性扣减城市
	List<String> selectCity();
	//0115、0116两种卡判定审批系统中该卡产品编号下已批准的卡片数量
	int selectCountHaveCard(Map<String, Object> map) throws CoreException;
	//客户级总卡数限制
	int selectCardLimit(Map<String, Object> map) throws Exception;
	int selectFkCardLimit(Map<String, Object> map) throws Exception;
	//迅卡审批结论查询
	String selectApproveResult(String appId) throws Exception;
}
