package com.huaxia.opas.service.credit.impl;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.apply.BizInpApplC1Dao;
import com.huaxia.opas.dao.credit.SubmitReplyDao;
import com.huaxia.opas.dao.decision.BizApprovalDao;
import com.huaxia.opas.dao.decision.BizApprovalHisDao;
import com.huaxia.opas.domain.input.BizInpApplC1;
import com.huaxia.opas.domain.thirdparty.BizApproval;
import com.huaxia.opas.domain.thirdparty.BizApprovalHis;
import com.huaxia.opas.service.AbstractService;
import com.huaxia.opas.service.credit.SubmitReplyService;

public class SubmitReplyServiceImpl extends AbstractService implements SubmitReplyService, Serializable {

private static final long serialVersionUID = -5286276550553333492L;
	
	@Resource(name = "submitReplyDao")
	private SubmitReplyDao submitReplyDao;
	
	@Resource(name = "bizApprovalHisDao")
	private BizApprovalHisDao bizApprovalHisDao;
	
	@Resource(name = "bizInpApplC1Dao")
	private BizInpApplC1Dao bizInpApplC1Dao;
	
	@Resource(name = "bizApprovalDao")
	private BizApprovalDao bizApprovalDao;
	
	public SubmitReplyDao getSubmitReplyDao() {
		return submitReplyDao;
	}

	public void setSubmitReplyDao(SubmitReplyDao submitReplyDao) {
		this.submitReplyDao = submitReplyDao;
	}
	
	public BizApprovalHisDao getBizApprovalHisDao() {
		return bizApprovalHisDao;
	}

	public void setBizApprovalHisDao(BizApprovalHisDao bizApprovalHisDao) {
		this.bizApprovalHisDao = bizApprovalHisDao;
	}

	@Override
	public Map<String, Object> querySubmitReplyInfo(Map<String ,String> argMap) throws CoreException{
		List<Map<String, Object>> submitFraudList = submitReplyDao.querySubmitFraudInfo(argMap);
		List<Map<String, Object>> submitAuthoList = submitReplyDao.querySubmitAuthoInfo(argMap);
		List<Map<String, Object>> submitAccountList = submitReplyDao.querySubmitAccountInfo(argMap);
		List<Map<String, Object>> submitAMLList = submitReplyDao.querySubmitAMLInfo(argMap);//add by wenyh
		List<Map<String, Object>> submitUrgeList = submitReplyDao.querySubmitUrgeInfo(argMap);
		List<Map<String, Object>> submitKeyList = submitReplyDao.querySubmitKeyInfo(argMap);
		List<Map<String, Object>> checkReturnList = submitReplyDao.queryCheckReturnInfo(argMap);
		List<Map<String, Object>> pfxcReturnList = submitReplyDao.queryPfxcReturnInfo(argMap);
		//分别将集合中的数据转换为字符串,减少js代码编码难度
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		if(submitFraudList!=null){
			for (Map<String, Object> map : submitFraudList) {
				//将时间改为字符串信息
				Date cd =(Date) map.get("crtDate");
				if(cd!=null)
					map.put("crtDate", fmt.format(cd));
				//将状态码改为对应信息
				/*String delStatus = (String)map.get("delStatus");
				if ("0".equals(delStatus)) {
					map.put("delStatus", "未完成");
				}else if("1".equals(delStatus)){
					map.put("delStatus", "退回欺诈调查");
				}else if("2".equals(delStatus)){
					map.put("delStatus", "提交欺诈审批");
				}else if("3".equals(delStatus)){
					map.put("delStatus", "退回征信调查");
				}else if("4".equals(delStatus)){
					map.put("delStatus", "已完成");
				}*/
			}
		}
		if(submitAuthoList!=null){
			for (Map<String, Object> map : submitAuthoList) {
				//将时间改为字符串信息
				Date cdAu =(Date) map.get("crtDateAu");
				if(cdAu!=null)
				map.put("crtDateAu", fmt.format(cdAu));
				//将状态码改为对应信息
				String subReasonAu = (String)map.get("subReasonAu");
				if("0".equals(subReasonAu)){
					map.put("delStatusAu", "交易异常，还款催收");
				}else if("1".equals(subReasonAu)){
					map.put("delStatusAu", "交易异常");
				}else if("2".equals(subReasonAu)){
					map.put("delStatusAu", "交易待观察，还款催收");
				}else if("3".equals(subReasonAu)){
					map.put("delStatusAu", "交易待观察");
				}else if("4".equals(subReasonAu)){
					map.put("delStatusAu", "未见异常，还款催收");
				}else if("5".equals(subReasonAu)){
					map.put("delStatusAu", "未见异常");
				}else if("6".equals(subReasonAu)){
					map.put("delStatusAu", "无用卡行为，暂无法进行交易评审");
				}else{
					map.put("delStatusAu", "");
				}
			}
		}
		if(submitAccountList!=null){
			for (Map<String, Object> map : submitAccountList) {
				//将时间改为字符串信息
				Date cdAc =(Date) map.get("crtDateAc");
				if(cdAc!=null)
				map.put("crtDateAc", fmt.format(cdAc));
				//将状态码改为对应信息
				String subReasonAc = (String)map.get("subReasonAc");
				if("0".equals(subReasonAc)){
					map.put("delStatusAc", "人行不良,已降额");
				}else if("1".equals(subReasonAc)){
					map.put("delStatusAc", "人行不良,暂不降额,关注");
				}else if("2".equals(subReasonAc)){
					map.put("delStatusAc", "人行不良,暂不降额");
				}else{
					map.put("delStatusAu", "");
				}
			}
		}
		if(submitAMLList!=null){//反洗钱 add by wenyh
			for (Map<String, Object> map : submitAMLList) {
				//将时间改为字符串信息
				Date cdAML =(Date) map.get("crtDateAML");
				if(cdAML!=null)
					map.put("crtDateAML", fmt.format(cdAML));
				//将状态码改为对应信息
				String subReasonAML = (String)map.get("subReasonAML");
				if("0".equals(subReasonAML)){
					map.put("delStatusAML", "J码管控");
				}else if("1".equals(subReasonAML)){
					map.put("delStatusAML", "解除J码管控");
				}else if("2".equals(subReasonAML)){
					map.put("delStatusAML", "不予解除J码管控");
				}else if("3".equals(subReasonAML)){
					map.put("delStatusAML", "其他（请说明）");
				}else{
					map.put("delStatusAML", "");
				}
			}
		}
		if(submitUrgeList!=null){
			for (Map<String, Object> map : submitUrgeList) {
				//将时间改为字符串信息
				Date cdU =(Date) map.get("crtDateU");
				if(cdU!=null)
				map.put("crtDateU", fmt.format(cdU));
				//将状态码改为对应信息
				String subReasonU = (String)map.get("subReasonU");
				if(map.get("percent")==null){
					map.put("percent", "");
				}
				if("0".equals(subReasonU)){
					map.put("delStatusU", "卡片状态异常,逾期停用");
				}else if("1".equals(subReasonU)){
					map.put("delStatusU", "当前逾期M1/M2等,额度使用率为  "+map.get("percent")+"%,正常催收");
				}else if("2".equals(subReasonU)){
					map.put("delStatusU", "当前催收状态正常,额度使用率为  "+map.get("percent")+"%");
				}else{
					map.put("delStatusU", "");
				}
			}
		}
		if(submitKeyList!=null){
			for (Map<String, Object> map : submitKeyList) {
				//将时间改为字符串信息
				Date md =(Date) map.get("modifyDate");
				if(md!=null)
				map.put("modifyDate", fmt.format(md));
				//将状态码改为对应信息
				String reviewStatus = (String)map.get("reviewStatus");
				if ("0".equals(reviewStatus)) {
					map.put("reviewStatus", "拒绝");
				}else if("1".equals(reviewStatus)){
					map.put("reviewStatus", "通过");
				}
			}
		}
		if(checkReturnList!=null){
			for (Map<String, Object> map : checkReturnList) {
				//将时间改为字符串信息
				Date cd =(Date) map.get("crtDateCr");
				if(cd!=null)
					map.put("crtDateCr", fmt.format(cd));
			}
		}
		if(pfxcReturnList!=null){
			for(Map<String,Object> map:pfxcReturnList ){
				//将时间改为字符串信息
				Date md =(Date) map.get("pfxctime");
				if(md!=null)
				map.put("pfxctime", fmt.format(md));
			}
		}
		Map<String, Object> resultMap = new HashMap<String,Object>();
		if(submitFraudList!=null){
			resultMap.put("submitFraudList",submitFraudList);
		}
		if(submitAuthoList!=null){
			resultMap.put("submitAuthoList",submitAuthoList);
		}
		if(submitAccountList!=null){
			resultMap.put("submitAccountList",submitAccountList);
		}
		if(submitAMLList!=null){//add by wenyh
			resultMap.put("submitAMLList",submitAMLList);
		}
		if(submitUrgeList!=null){
			resultMap.put("submitUrgeList",submitUrgeList);
		}
		if(submitKeyList!=null){
			resultMap.put("submitKeyList",submitKeyList);
		}
		if(checkReturnList!=null){
			resultMap.put("checkReturnList",checkReturnList);
		}
		if(pfxcReturnList!=null){
			resultMap.put("pfxcReturnList", pfxcReturnList);
		}
		
		return resultMap;
	}

	/*public static void main(String[] args) {
		String appId = "1603151911P00241";
		appId = "'"+appId+"','"+appId.substring(0,15)+"2'";
		System.out.println(appId);
	}*/
	
	@Override
	public Map<String, Object> selectByHistoryAuditInfo(String appId) throws CoreException{
		Map<String, Object> historyAuditInfo = new HashMap<String, Object>();
		Map<String, Object> historyAuditInfoMap = bizApprovalHisDao.selectAuditInfo(appId);
		if(historyAuditInfoMap!=null){
			historyAuditInfo.putAll(historyAuditInfoMap);
		}
		BizInpApplC1 on1 = bizInpApplC1Dao.queryBizInpApplC1ByAppId(appId);
		String appIdStr = appId;
		if("2".equals(on1.getMatchingCardFlag())&&"2".equals(on1.getYdjFlag())){
			appIdStr = appId.substring(0,15)+"2";
		}
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("appId", appId);
		param.put("appIdStr", appIdStr);
		List<BizApprovalHis> approvalMsgList =null;
		//区分是否为只申请附卡情况
		if("2".equals(on1.getC1c2Flag())){
			approvalMsgList = bizApprovalHisDao.queryApprovalMsg_fsk(param);
		}else{
			approvalMsgList = bizApprovalHisDao.queryApprovalMsg(param);
		}
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//查询当前申请件是否在审批表保存过  wdb
		BizApproval biz=bizApprovalDao.selectByAppId(appId);
		Long approveTime;
		Long approveHisTime;
		List<BizApprovalHis> approvalMsgNewList =new ArrayList<BizApprovalHis>();
		if(approvalMsgList!=null&&approvalMsgList.size()>0){
			if(biz!=null&&biz.getApproveTime()!=null){
				approveTime=biz.getApproveTime().getTime();
				for (BizApprovalHis bizApprovalHis : approvalMsgList) {
					approveHisTime=bizApprovalHis.getApproveTime()==null?0:bizApprovalHis.getApproveTime().getTime();
					if(approveTime>=approveHisTime){
						bizApprovalHis.setApproveTimeStr(bizApprovalHis.getApproveTime()==null?"":fmt.format(bizApprovalHis.getApproveTime()));
						approvalMsgNewList.add(bizApprovalHis);
					}
				}
				historyAuditInfo.put("approvalMsgList", approvalMsgNewList);
			}else{
				for (BizApprovalHis bizApprovalHis : approvalMsgList) {
					bizApprovalHis.setApproveTimeStr(bizApprovalHis.getApproveTime()==null?"":fmt.format(bizApprovalHis.getApproveTime()));
				}
				historyAuditInfo.put("approvalMsgList", approvalMsgList);
			}
		}
		return historyAuditInfo;
	}

	@Override
	public Map<String, Object> queryYsSubmitReplyInfo(Map<String, String> argMap) throws CoreException {
		List<Map<String, Object>> submitFraudList = submitReplyDao.queryYsSubmitFraudInfo(argMap);
		List<Map<String, Object>> checkReturnList = submitReplyDao.queryYsCheckReturnInfo(argMap);
		//分别将集合中的数据转换为字符串,减少js代码编码难度
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		if(submitFraudList!=null){
			for (Map<String, Object> map : submitFraudList) {
				//将时间改为字符串信息
				Date cd =(Date) map.get("crtDate");
				if(cd!=null)
					map.put("crtDate", fmt.format(cd));
			}
		}
		if(checkReturnList!=null){
			for (Map<String, Object> map : checkReturnList) {
				//将时间改为字符串信息
				Date cd =(Date) map.get("crtDateCr");
				if(cd!=null)
					map.put("crtDateCr", fmt.format(cd));
			}
		}
		Map<String, Object> resultMap = new HashMap<String,Object>();
		if(submitFraudList!=null){
			resultMap.put("submitFraudList",submitFraudList);
		}
		if(checkReturnList!=null){
			resultMap.put("checkReturnList",checkReturnList);
		}
		return resultMap;
	}
}
