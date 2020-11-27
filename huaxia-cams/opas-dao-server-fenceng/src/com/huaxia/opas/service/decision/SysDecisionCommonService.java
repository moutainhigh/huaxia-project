package com.huaxia.opas.service.decision;

import java.util.List;
import java.util.Map;

import com.huaxia.opas.domain.allot.AllotApplyAllot;
import com.huaxia.opas.domain.allot.AllotApplyAllotHis;
import com.huaxia.opas.domain.decision.YdjSysresultInfo;
import com.huaxia.opas.domain.sysparm.InnerRiskInfo;
import com.huaxia.opas.domain.thirdparty.OpasConfQuestion;

public interface SysDecisionCommonService {
	
	/**
	 * 风险名单检测 -公共代码
	 */
	Object queryRisklist(String autoId, String flag) throws Exception;

	int queryConfQuestionCount() throws Exception;

	List<OpasConfQuestion> queryConfQuestion(List<String> list) throws Exception;
	
	YdjSysresultInfo querySysResultfoByAppId(String autoId) throws Exception;
	InnerRiskInfo queryInnerRiskInfo(String autoId) throws Exception;
	List<AllotApplyAllot> queryByUserId(String currOptUser) throws Exception;
	AllotApplyAllot queryOneByUserId(Map<String,String> map) throws Exception;
	void updateAllotApplyAllot(AllotApplyAllot allotApplyAllot, AllotApplyAllotHis record) throws Exception;
	AllotApplyAllot queryByPrimaryKey(String appId) throws Exception;
	String selectReultByAppId(String appId, String status);
	String queryConfQuestionCount(List<String> ids);

	List<OpasConfQuestion> questionLibrary(Map<Object,String> map);
}
