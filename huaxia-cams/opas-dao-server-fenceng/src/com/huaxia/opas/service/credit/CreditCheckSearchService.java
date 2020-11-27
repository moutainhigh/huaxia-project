package com.huaxia.opas.service.credit;

import java.util.List;
import java.util.Map;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.credit.T5eEsMiddle;
import com.huaxia.opas.domain.decision.TelcheckAddnote;
import com.huaxia.opas.domain.input.BizInpApplC1;
import com.huaxia.opas.domain.input.TelcheckResult;

public interface CreditCheckSearchService{

	public Map<String,Object> queryCreditInfo(Map<String ,String> argMap) throws CoreException;
	/**征信调查注记 by 刘志辉**/
	public BizInpApplC1 selectBizInpApplC1ByAppId(String appId);

	public Map<String, String> queryPbocInfo(String appId);
	
	public Map<String, String> queryBankInfo(String appId);

	public Map<String, String> queryTeam(String appId);
	
	public String saveOrUpdateNotes(TelcheckAddnote note);
	
	public List<TelcheckAddnote> selectNotesList(String appId);
	
	public String deleteByAutoId(String autoId);
	
	
	/**征信调查注记 by 刘志辉**/
	
	/**征信调查  by 安东**/
	public void saveOrUpdateTelcheckResult(TelcheckResult result, String appId);
	public void saveOrUpdateResult(TelcheckResult result, String appId,BizInpApplC1 bizInpApplC1) throws CoreException;
	public void saveNotes(TelcheckAddnote note);
	public String queryTelno(String c1Cotel,String ydjFlag);
	/**征信调查查看  by 安东**/
	public Map<String, Object> queryCreditCheckInfo(Map<String, String> argMap) throws CoreException;
	public List<Object> queryCreditInvestigationSurvey(String appId);
	public List<Object> queryApprovalBack(String appId);
	public Map<String,String> querySVoiceConclusion(String appId);
	public List<Object> querySVoiceBack(String appId);
	public void updateByAutoId(TelcheckAddnote note);
	public List<Object> queryCheckingDetail(String appId);
	/**
	 *@Title:saveOrUpdateTelcheckAddNoteMsg
	 *@Description:征信调查页面 插入、修改、删除   纪要信息方法
	 *@param note
	 *@param map
	 *@author: gaohui
	 *@Date:2017年8月12日下午2:15:57
	 */
	void saveOrUpdateTelcheckAddNoteMsg(Map map);
	/**
	 *@Title:saveTelcheckAddNoteCommitMsg
	 *@Description:征信调查页面  纪要提交 保存方法 
	 *@param map
	 *@author: gaohui
	 *@Date:2017年8月12日下午6:48:23
	 */
	void saveTelcheckAddNoteCommitMsg(Map map);
	/**
	 *@Title:updateTelcheckAddNoteByTalId
	 *@Description:根据talId修改 纪要信息
	 *@param map
	 *@author: gaohui
	 *@Date:2017年8月12日下午7:03:55
	 */
	void  updateTelcheckAddNoteByTalId(Map map);
	/**
	 *@Title:updateBizInpApplComporeByAppId
	 *@Description:根据AppId修改 opas_biz_inp_appl_compore 的住宅地址字段
	 *@param map
	 *@author: andong
	 *@Date:2017年9月15日下午9:52:35
	 */
	public void updateBizInpApplComporeByAppId(Map<Object, Object> param);
	public T5eEsMiddle queryT5eEsMiddle(Map<String, Object> dataMap);
}
