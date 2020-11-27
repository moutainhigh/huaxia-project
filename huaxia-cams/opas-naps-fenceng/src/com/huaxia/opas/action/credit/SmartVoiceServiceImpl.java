package com.huaxia.opas.action.credit;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.huaxia.opas.cache.service.riskcheck.RiskMachCheckService;
import com.huaxia.opas.domain.allot.AllotApplyAllot;
import com.huaxia.opas.domain.allot.AllotApplyAllotHis;
import com.huaxia.opas.domain.apply.ApplyLifeCicle;
import com.huaxia.opas.domain.credit.CreditCheck;
import com.huaxia.opas.domain.credit.OpasTelcheckResult;
import com.huaxia.opas.domain.input.BizInpApplC1;
import com.huaxia.opas.service.apply.ApplyLifeCicleService;
import com.huaxia.opas.service.apply.BizInpApplService;
import com.huaxia.opas.service.decision.SysButtonCommonService;
import com.huaxia.opas.service.decision.SysDecisionYdjService;
import com.huaxia.opas.service.fico.FicoService;
import com.huaxia.opas.service.voice.SmartVoiceService;
import com.huaxia.opas.util.CommonProperties;
import com.huaxia.opas.util.SequenceUtil;
import com.huaxia.opas.util.StringUtil;


public class SmartVoiceServiceImpl implements SmartVoiceService, Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private static Logger logger = LoggerFactory.getLogger(SmartVoiceServiceImpl.class);
		
	@Autowired
	private BizInpApplService bizInpApplService;
	@Autowired
	private FicoService ficoService;
	@Autowired
	private ApplyLifeCicleService applyLifeCicleService;
	@Autowired
	private SysButtonCommonService sysButtonCommonService;
	@Resource(name = "riskMachCheckService")
	private RiskMachCheckService riskMachCheckService;
	@Resource(name = "sysDecisionYdjService")
	private SysDecisionYdjService sysDecisionYdjService;
	
	@Override
	public String svoiceCallBack(String appId, String conclusion) throws Exception {
	
		if(!isCheck(appId, conclusion)){
			return "-1";
		}
		logger.info("申请件{}:[智能语音回调审批]开始",appId);
		
		try{
			AllotApplyAllot applyAllot = sysButtonCommonService.selectByPrimaryKey(appId);		
			if(applyAllot==null){//分件表为空
				return "0";
			}
			logger.info("申请件{}:[智能语音回调审批]分件表状态为:{}",appId,applyAllot.getCurrNode()+"+"+applyAllot.getDelStatus());
			if(null ==conclusion || !"1".equals(conclusion)){
				//没有结论或者未通过，将申请件退回征信;
				//String userCode = StringUtils.trimToEmpty((String) ctx.getDataMap().get("userCode"));
				//List<String> applyList=new ArrayList<String>(Arrays.asList("0","1","2","3"));
				/*&&applyList.contains(applyAllot.getDelStatus())*/
				if("07".equals(applyAllot.getCurrNode())){
					//为即将插入历史表的件赋值
					AllotApplyAllotHis allotHis = setAllotApplyAllotHis(applyAllot, appId);
					//allotHis.setBackFlag("6");// 6:智能语音退回征信
					allotHis.setMemo("发起智能语音未通过退回");//退回备注
					allotHis.setLastOptUser(applyAllot.getCurrOptUser());
					//查询上个征信环节的分件状态
					Map<String,String> pMap = new HashMap<>();
					pMap.put("currNode", "02");
					pMap.put("appId", appId);
					AllotApplyAllotHis oldAllot = sysButtonCommonService.queryAllotApplyAllotHisByAppId(pMap);
					if(oldAllot==null){
						//分件历史表中没有征信环节的记录，该申请件异常
						logger.info("申请件{}:[智能语音回调审批]分件历史表中没有记录",appId);
						return "0";
					}else{
						//为即将更新的分件表中的状态和操作人赋值
						applyAllot.setCurrStatus("3");//个人队列未挂起
						applyAllot.setDelStatus("0"); //征信未完成
						applyAllot.setCurrOptUser(oldAllot.getCurrOptUser());
						allotHis.setCurrOptUser(applyAllot.getCurrOptUser());
					}
					allotHis.setCurrOptUser(applyAllot.getCurrOptUser());
					applyAllot.setCurrNode("02");// 01：录入比对02：征信调查03：人工审批   07：智能语音		
					applyAllot.setBackFlag("6"); // 智能语音退回
					applyAllot.setSynFlag("0");  // 显示当前申请件
					applyAllot.setCurrOptGroup(oldAllot.getCurrOptGroup());//上个征信环节的操作组
					applyAllot.setLastOptGroup(oldAllot.getCurrOptGroup());//上个征信环节的操作组
					//发起智能语音执行的内容
					Map<String,Object> resMap = new HashMap<>();
					resMap.put("appId", appId);
					resMap.put("check_meuoFlag", applyAllot.getCheck_meuoFlag());
					resMap.put("memo", "复核智能语音未通过退回");
					bizInpApplService.updateApplySVoiceToZx(applyAllot,allotHis,resMap,"2");//更新数据库操作
				}else{
					//分件表当前申请件不存在
					logger.info("申请件{}:[智能语音回调审批]分件表不存在",appId);
					return "0";
				}
			}else{
				//结论为通过,发起工作流
				Map<String,String> param = new HashMap<>();
				param.put("appId", appId);
				param.put("userCode", applyAllot.getCurrOptUser());
				param.put("delStatus", applyAllot.getDelStatus());
				param.put("check_meuoFlag", applyAllot.getCheck_meuoFlag());
				param.put("zshyInnerCheck", applyAllot.getZshyInnerCheck());
				if("2".equals(applyAllot.getCheck_meuoFlag())){
					String specialExamineUser = "2-"+applyAllot.getCurrOptUser()+"- ";
					param.put("specialExamineUser", specialExamineUser);
				}else{
					if("3".equals(applyAllot.getDelStatus())){
						String specialExamineUser = "5-"+applyAllot.getCurrOptUser()+"- ";
						param.put("specialExamineUser", specialExamineUser);
					}else{
						String specialExamineUser = "1-"+applyAllot.getCurrOptUser()+"- ";
						param.put("specialExamineUser", specialExamineUser);
					}	
				}		
				creditSubmit(param);
			}
			return "1";
		}catch(Exception e){
			logger.error("申请件{}:[智能语音回调审批]出现异常：{}",appId, e.getMessage());
			return "99";
		}
	}	
	/**
	 * 
	 * @param allot
	 * @param appId
	 * @return
	 * @throws Exception
	 */
	public AllotApplyAllotHis setAllotApplyAllotHis(AllotApplyAllot allot, String appId) throws Exception {
		AllotApplyAllotHis record = new AllotApplyAllotHis();
		record.setId(StringUtil.randomUUIDPlainString());
		record.setAppId(appId);
		record.setCurrOptQueue(allot.getCurrOptQueue()==null?"":allot.getCurrOptQueue());
		record.setLastOptQueue(allot.getLastOptQueue()==null?"":allot.getLastOptQueue());
		record.setCurrOptGroup(allot.getCurrOptGroup()==null?"":allot.getCurrOptGroup());
		record.setLastOptGroup(allot.getLastOptGroup()==null?"":allot.getLastOptGroup());
		record.setCurrOptUser(allot.getCurrOptUser()==null?"":allot.getCurrOptUser());
		record.setLastOptUser(allot.getLastOptUser()==null?"":allot.getLastOptUser());
		record.setCurrStatus(allot.getCurrStatus()==null?"":allot.getCurrStatus());
		record.setCurrNode(allot.getCurrNode()==null?"":allot.getCurrNode());
		record.setCrtTeamDate(allot.getCrtTeamDate()==null?new Date():allot.getCrtTeamDate());
		record.setLstTeamDate(new Date());
		record.setRecordTeamDate(new Date());
		record.setDelStatus(allot.getDelStatus()==null?"":allot.getDelStatus());
		record.setSubmitType(allot.getSubmitType()==null?"":allot.getSubmitType());
		record.setSubmitStatus(allot.getSubmitStatus()==null?"":allot.getSubmitStatus());
		record.setReviewStatus(allot.getReviewStatus()==null?"":allot.getReviewStatus());
		record.setQueueDate(allot.getQueueDate()==null?new Date():allot.getQueueDate());
		record.setGroupDate(allot.getGroupDate()==null?new Date():allot.getGroupDate());
		record.setSubmitMemo(allot.getSubmitMemo()==null?"":allot.getSubmitMemo());
		record.setSynFlag(allot.getSynFlag()==null?"":allot.getSynFlag());
		record.setYdjFlag(allot.getYdjFlag());
		record.setMatchingCardFlag(allot.getMatchingCardFlag());
		record.setProcessId(allot.getProcessId());
		record.setBackFlag(allot.getBackFlag());
		record.setLaojianflag(allot.getLaojianflag());
		record.setCheck_meuoFlag(allot.getCheck_meuoFlag());
		return record;
	}
	
	/**
	 * @Title:creditSubmit
	 * @Description:征信提交功能
	 * @param ctx
	 * @throws CoreException
	 * @author:抄袭
	 * @throws Exception 
	 * @Date:2017年4月21日上午11:31:30
	 */
	public void creditSubmit(Map<String,String> paramMap) throws Exception {

		CreditCheck creditCheck = new CreditCheck();
		creditCheck.setCheck_delStatus("1");// 征信提交时 人工处理状态改为 1
		creditCheck.setCheck_ydjFlag("2");
		creditCheck.setCheck_currNode("07");
		creditCheck.setCheck_number(paramMap.get("appId").toString());
		creditCheck.setCheck_meuoFlag(paramMap.get("check_meuoFlag").toString());//xzg add
		creditCheck.setCheck_synFlag("0");  // 显示当前件
		OpasTelcheckResult telcheckResult = new OpasTelcheckResult();
		telcheckResult.setAuto_id(SequenceUtil.getUUID());
		telcheckResult.setCrt_date(new Date());
		telcheckResult.setApp_id(creditCheck.getCheck_number());
		telcheckResult.setYdj_flag(creditCheck.getCheck_ydjFlag());
		telcheckResult.setCrt_user(paramMap.get("userCode").toString());
		// 从配置文件中获取节点nodeId
		Map<String, String> paroperMap = CommonProperties.getParoperMap();
		Map<String, Object> streamMap = new HashMap<String, Object>();
		streamMap.put("appId",paramMap.get("appId").toString());
		streamMap.put("matchingCardFlag", "0");//当前卡的 套卡 标识
		streamMap.put("process_IP",paroperMap.get("process_IP"));//获取配置ip地址
		streamMap.put("ydjFlag","2");
		streamMap.put("nodeId", paroperMap.get("nodeId_sc_bzk"));//获取配置节点
		streamMap.put("userCode", paramMap.get("userCode").toString());
		streamMap.put("specialExamineUser", paramMap.get("specialExamineUser").toString());
		//征审合一 add xuzhiguo 调用二次决策
		String zshyResult = "";
		boolean jbpm = false;
		if("2".equals(creditCheck.getCheck_meuoFlag())){
			//调用内部交叉检查
			String appId=paramMap.get("appId"),appID="";
			logger.info("申请件:{}征审合一模块====调用[innerJcCheck]===start",appId);
			try {
				ApplyLifeCicle applyLifeCicle = new ApplyLifeCicle();
				applyLifeCicle.setUuid(StringUtil.randomUUIDPlainString());
				applyLifeCicle.setAppId(appId);
				applyLifeCicle.setOperater("system");
				applyLifeCicle.setOperateDesc("二次内部交叉检查");
				applyLifeCicle.setOperateResult("完成");
				applyLifeCicle.setCrtDate(new Date());
				applyLifeCicle.setCrtUser("system");
				applyLifeCicle.setLstUpdDate(new Date());
				applyLifeCicle.setLstUpdUser("system");
				applyLifeCicleService.addApplyLifeCicle(applyLifeCicle);
				BizInpApplC1 app = sysDecisionYdjService.selectBizInpApplC1ByAppId(appId);
				if("1".equals(app.getMatchingCardFlag())){
					if(appId.endsWith("1")){
						appID=appId.substring(0, 15)+"2";
					}else if(appId.endsWith("2")){
						appID=appId.substring(0, 15)+"1";
					}
				}else{
					appID=appId;
				}
				riskMachCheckService.exeRiskCheck(appID, "4", app.getC1c2Flag());
			} catch (Exception e) {
				logger.info(appId+"异常原因："+e.getMessage());
				logger.error(appId, e);
			}
			logger.info("申请件:{}征审合一模块====调用[innerJcCheck]===end",paramMap.get("appId"));
			// 内部交叉检查
			logger.info("申请件:{}征审合一模块====调用二次决策===start",appId);
			streamMap.put("isBack", "3");
			streamMap.put("zshyInnerCheck","1"); // 已经发起发起过内部交叉检查
			// streamMap.put("zshyInnerCheck",paramMap.get("zshyInnerCheck").toString());
			AllotApplyAllot applyAllot = sysButtonCommonService.selectByPrimaryKey(paramMap.get("appId").toString());
			if(applyAllot!=null&&"2".equals(applyAllot.getYdjFlag())){
				logger.info("征审合一模块====调用二次决策===标准卡");
				zshyResult = invokeBzk(applyAllot);
				// 调用流程，提前结束  2 自动拒绝  3 自动核准 
				if("2".equals(zshyResult)||"3".equals(zshyResult)){
					logger.info(paramMap.get("appId").toString()+"二次决策返回值为"+zshyResult);
					jbpm = true;
				}
			}
			streamMap.put("zshyResult", zshyResult);
			streamMap.put("jbpm", jbpm);
			logger.info("征审合一模块====调用二次决策===end");
		}
		bizInpApplService.updateCreditSubmitZx(creditCheck,telcheckResult,streamMap);
	}
	/**
	 *标准卡二次决策 
	 */
	public String invokeBzk(AllotApplyAllot applyAllot) throws Exception {
		String appId = applyAllot.getAppId() ==null ?"":applyAllot.getAppId();
		String ydjFlag = applyAllot.getYdjFlag()==null?"":applyAllot.getYdjFlag();
		String taoflag = applyAllot.getMatchingCardFlag()==null?"":applyAllot.getMatchingCardFlag();
		String ret=ficoService.ficoRequest("SD0800",appId,ydjFlag);
		ApplyLifeCicle a=new ApplyLifeCicle();
		a.setAppId(appId);
		a.setOperater("system");
		a.setOperateDesc("二次决策");
		a.setOperateResult("完成");
		a.setCrtDate(new Date());
		a.setCrtUser("system");
		a.setUuid(StringUtil.randomUUIDPlainString());
		applyLifeCicleService.addApplyLifeCicle(a);
		return ret;
	}
	/**
	 * 参数校验
	 */
	private boolean isCheck(String appId, String conclusion){
		try{
			if(appId==null||"".equals(appId)||conclusion==null||"".equals(conclusion)){
				logger.info("申请件{}进行智能语音回调参数为空",appId);
				return false;
			}
			if(appId.trim().length()!=16){
				logger.info("[智能语音回调参数_appId]:{},长度不等于16位",appId);
				return false;
			}
			List<String> conclusionList=new ArrayList<String>(Arrays.asList("1","2","3"));
			if(!conclusionList.contains(conclusion)){
				logger.info("[智能语音回调参数_conclusion]:{},智能语音征信结论参数超出指定值",conclusion);
				return false;
			}
			return true;
		}catch(Exception e){
			logger.info("[智能语音回调参数]:转换异常");
			return false;
		}
	}
}
