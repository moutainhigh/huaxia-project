package com.huaxia.opas.allot.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.huateng.neofp.core.Action;
import com.huateng.neofp.core.Context;
import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.common.util.UUIDGenerator;
import com.huaxia.opas.domain.allot.AllotApply;
import com.huaxia.opas.domain.allot.AllotApplyAllotHis;
import com.huaxia.opas.domain.allot.AllotCommon;
import com.huaxia.opas.domain.allot.AllotQueue;
import com.huaxia.opas.domain.decision.OpasReviewDecisionResult;
import com.huaxia.opas.domain.input.TelcheckResult;
import com.huaxia.opas.domain.rule.OpasBizApproval;
//import com.huaxia.opas.outerfaces.fico.FicoService;
import com.huaxia.opas.service.allot.AllotApplyHisService;
import com.huaxia.opas.service.allot.AllotApplyService;
import com.huaxia.opas.service.allot.AllotCommonService;
import com.huaxia.opas.service.allot.AllotMappingRuleService;
import com.huaxia.opas.service.allot.AllotQueueService;
import com.huaxia.opas.service.allot.AllotRuleService;
import com.huaxia.opas.service.allot.AllotSwitchService;
import com.huaxia.opas.service.allot.ReviewDecisionService;
import com.huaxia.opas.service.fico.FicoService;
/**
 * 公共方法
 * @author 王德彬
 * @version
 */
public class ComUtilAction implements Action {
	private static final Logger log = LoggerFactory.getLogger(ComUtilAction.class);
	
	@Resource(name="allotApplyHisService")
	private AllotApplyHisService allotApplyHisService;
	
	
	public ComUtilAction() {
	}
	/**
	* @Description:历史记录入库
	* @author 王德彬
	* @version  V1.0
	* @param context  
	 * @throws CoreException 
	 */
	public void saveAllotHis(AllotApply allotApply) {
		log.info("ComUtilAction.saveAllotHis Enter");
		try {
			AllotApplyAllotHis allotApplyHis=new AllotApplyAllotHis();
			String autoId=UUIDGenerator.getUUID();
			allotApplyHis.setId(autoId);
			allotApplyHis.setAppId(allotApply.getAppId());
			allotApplyHis.setCurrOptQueue(allotApply.getCurrQueue()==null?"":allotApply.getCurrQueue());
			allotApplyHis.setLastOptQueue(allotApply.getLastQueue()==null?"":allotApply.getLastQueue());
			allotApplyHis.setCurrOptGroup(allotApply.getCurrGroup()==null?"":allotApply.getCurrGroup());
			allotApplyHis.setLastOptGroup(allotApply.getLastGroup()==null?"":allotApply.getLastGroup());
			allotApplyHis.setCurrOptUser(allotApply.getCurrUser()==null?"":allotApply.getCurrUser());
			allotApplyHis.setLastOptUser(allotApply.getLastUser()==null?"":allotApply.getLastUser());
			allotApplyHis.setCurrStatus(allotApply.getCurrStatus()==null?"":allotApply.getCurrStatus());
			allotApplyHis.setDelStatus(allotApply.getDelStatus()==null?"":allotApply.getDelStatus());
			allotApplyHis.setCurrNode(allotApply.getCurrNode()==null?"":allotApply.getCurrNode());
			allotApplyHis.setCrtTeamDate(allotApply.getCrtTeamDate()==null?new Date():allotApply.getCrtTeamDate());
			allotApplyHis.setLstTeamDate(allotApply.getLstTeamDate()==null?new Date():allotApply.getLstTeamDate());
			allotApplyHis.setSubmitStatus(allotApply.getSubmitStatus()==null?"":allotApply.getSubmitStatus());
			allotApplyHis.setSubmitType(allotApply.getSubmitType()==null?"":allotApply.getSubmitType());
			allotApplyHis.setSubmitMemo(allotApply.getSubmitMemo()==null?"":allotApply.getSubmitMemo());
			allotApplyHis.setReviewStatus(allotApply.getReviewStatus()==null?"":allotApply.getReviewStatus());
			allotApplyHis.setQueueDate(allotApply.getQueueDate());
			allotApplyHis.setGroupDate(allotApply.getGroupDate());
			allotApplyHis.setUserDate(allotApply.getUserDate());
			Date date=new Date();
			allotApplyHis.setRecordTeamDate(date);
			allotApplyHis.setYdjFlag(allotApplyHis.getYdjFlag());
			allotApplyHis.setMatchingCardFlag(allotApply.getMatchingCardFlag());
			allotApplyHis.setBackFlag(allotApply.getBackFlag()==null?"":allotApply.getBackFlag());
			allotApplyHis.setSynFlag(allotApply.getSynFlag());
			allotApplyHis.setProcessId(allotApply.getProcessId());
			allotApplyHis.setLaojianflag(allotApply.getLaoJianFlag()==null?"":allotApply.getLaoJianFlag());
			int result=0;
			if(allotApplyHis!=null){
				result=allotApplyHisService.saveAllotApplyHis(allotApplyHis);
			}
			log.info("ComUtilAction.saveAllotHis 记录历史结果result="+result);
			if(result==0){
				log.info("ComUtilAction.saveAllotHis 记录历史失败");
			}else{
				log.info("ComUtilAction.saveAllotHis 记录历史成功");
			}
		}catch (Exception e) {
			log.error("ComUtilAction.saveAllotHis occur error", e);
		}
		log.info("ComUtilAction.saveAllotHis Out");
	}  
}
