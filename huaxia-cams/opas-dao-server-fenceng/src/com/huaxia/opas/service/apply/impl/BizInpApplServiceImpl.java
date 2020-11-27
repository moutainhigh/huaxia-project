package com.huaxia.opas.service.apply.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.xfire.client.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.allot.AllotApplyAllotDao;
import com.huaxia.opas.dao.allot.AllotApplyAllotHisDao;
import com.huaxia.opas.dao.allot.AllotQueueDao;
import com.huaxia.opas.dao.allot.ReviewDecisionDao;
import com.huaxia.opas.dao.apply.ApplyLifeCicleDao;
import com.huaxia.opas.dao.apply.BizInpApplC1Dao;
import com.huaxia.opas.dao.apply.BizInpApplDao;
import com.huaxia.opas.dao.checking.QualityCheckingDao;
import com.huaxia.opas.dao.collect.InfoCollectDao;
import com.huaxia.opas.dao.compare.RevCompInfoDao;
import com.huaxia.opas.dao.credit.CreditCheckDao;
import com.huaxia.opas.dao.credit.PatchboltDao;
import com.huaxia.opas.dao.decision.BizApprovalDao;
import com.huaxia.opas.dao.decision.BizApprovalHisDao;
import com.huaxia.opas.dao.decision.FicoSdBlazeDao;
import com.huaxia.opas.dao.decision.FicoYdjBlazeDao;
import com.huaxia.opas.dao.decision.TelcheckAddnoteDao;
import com.huaxia.opas.dao.sysparm.CardProductDao;
import com.huaxia.opas.dao.sysparm.SubReportDao;
import com.huaxia.opas.dao.sysparm.SysparmRateConfDao;
import com.huaxia.opas.dao.sysparm.SysparmRepayFreeTermConfDao;
import com.huaxia.opas.dao.sysparm.SysparmRepayLimitConfDao;
import com.huaxia.opas.dao.system.ApOrgDao;
import com.huaxia.opas.dao.system.ApUserDao;
import com.huaxia.opas.dao.system.ApUserOrgDao;
import com.huaxia.opas.dao.thirdparty.PBOCDao;
import com.huaxia.opas.dao.thirdparty.PoliceDao;
import com.huaxia.opas.domain.allot.AllotApplyAllot;
import com.huaxia.opas.domain.allot.AllotApplyAllotHis;
import com.huaxia.opas.domain.allot.AllotQueue;
import com.huaxia.opas.domain.apply.ApplyLifeCicle;
import com.huaxia.opas.domain.credit.CreditCheck;
import com.huaxia.opas.domain.credit.OpasTelcheckResult;
import com.huaxia.opas.domain.decision.OpasReviewDecisionResult;
import com.huaxia.opas.domain.input.BizInpAppl;
import com.huaxia.opas.domain.input.BizInpApplC1;
import com.huaxia.opas.domain.input.FicoSdBlaze;
import com.huaxia.opas.domain.input.FicoYdjBlaze;
import com.huaxia.opas.domain.input.TelcheckResult;
import com.huaxia.opas.domain.sysparm.CardProduct;
import com.huaxia.opas.domain.sysparm.SubReport;
import com.huaxia.opas.domain.sysparm.SysparmRateConf;
import com.huaxia.opas.domain.sysparm.SysparmRepayFreeTermConf;
import com.huaxia.opas.domain.sysparm.SysparmRepayLimitConf;
import com.huaxia.opas.domain.system.ApOrg;
import com.huaxia.opas.domain.system.ApUser;
import com.huaxia.opas.domain.system.ApUserOrg;
import com.huaxia.opas.domain.thirdparty.BizApproval;
import com.huaxia.opas.domain.thirdparty.BizApprovalHis;
import com.huaxia.opas.service.AbstractService;
import com.huaxia.opas.service.apply.BizInpApplService;
import com.huaxia.opas.service.collect.InfoCollectService;
import com.huaxia.opas.service.credit.CreditCheckService;
import com.huaxia.opas.service.voice.IntelligentVoiceService;
import com.huaxia.opas.service.workflow.TopbpmService;
import com.huaxia.opas.util.CommonProperties;
import com.huaxia.opas.util.CommonUtil;
import com.huaxia.opas.util.StringUtil;
import com.huaxia.opas.util.UUIDGenerator;

/**
 * 进件信息
 * 
 * @author xiebinxu 2017年2月25日
 */
public class BizInpApplServiceImpl extends AbstractService implements BizInpApplService , Serializable{

	private static final long serialVersionUID = -8888388888357864632L;
	private static Logger logger = LoggerFactory.getLogger(BizInpApplServiceImpl.class);

	@Resource(name = "bizInpApplDao")
	private BizInpApplDao bizInpApplDao;

	@Resource(name = "allotApplyAllotDao")
	private AllotApplyAllotDao allotApplyAllotDao;
	@Resource(name = "allotApplyAllotHisDao")
	private AllotApplyAllotHisDao allotApplyAllotHisDao;
	@Resource(name = "creditCheckDao")
	private CreditCheckDao creditCheckDaoImp;

	@Resource(name = "subReportDao")
	private SubReportDao subReportDaoImp;
	
	@Resource(name="apOrgDao")
	private ApOrgDao apOrgDao;
	@Resource(name = "patchboltDao")
	public PatchboltDao patchboltDao;
	@Resource(name = "applyLifeCicleDao")
	private ApplyLifeCicleDao applyLifeCicleDao;
	@Resource(name = "apUserDao")
	private ApUserDao apUserDao;
	@Resource(name = "apUserOrgDao")
	private ApUserOrgDao apUserOrgDao;
	@Resource(name = "allotQueueDao")
	private AllotQueueDao allotQueueDao;
	@Resource(name = "ficoSdBlazeDao")
	private FicoSdBlazeDao ficoSdBlazeDao;
	@Resource(name = "ficoYdjBlazeDao")
	private FicoYdjBlazeDao ficoYdjBlazeDao;
	@Resource(name = "bizApprovalDao")
	private BizApprovalDao bizApprovalDao;
	@Resource(name = "bizApprovalHisDao")
	private BizApprovalHisDao bizApprovalHisDao;
	@Resource(name = "bizInpApplC1Dao")
	private BizInpApplC1Dao bizInpApplC1Dao;
	@Resource(name ="telcheckAddnoteDao")
	private TelcheckAddnoteDao telcheckAddnoteDao;
	@Resource(name ="pbocDao")
	private PBOCDao pbocDao;
	@Resource(name ="policeDao")
	private PoliceDao policeDao;
	
	@Resource(name = "sysparmRateConfDao")
	private SysparmRateConfDao sysparmRateConfDao;
	
	@Resource(name = "sysparmRepayLimitConfDao")
	private SysparmRepayLimitConfDao sysparmRepayLimitConfDao;
	
	@Resource(name = "sysparmRepayFreeTermConfDao")
	private SysparmRepayFreeTermConfDao sysparmRepayFreeTermConfDao;
	
	@Resource(name = "cardProductDao")
	private CardProductDao cardProductDao;
	
	@Resource(name="qualityCheckingDao")
	private QualityCheckingDao qualityCheckingDao;
	
	@Resource(name = "reviewDecisionDao")
	private ReviewDecisionDao reviewDecisionDao;	
	@Resource(name = "revCompInfoDao")
	private RevCompInfoDao revCompInfoDao;

	@Resource(name = "topbpmService")
	private TopbpmService topbpmService;
	
	@Resource(name = "intelligentVoiceService")
	private IntelligentVoiceService intelligentVoiceService;
	
	public RevCompInfoDao getRevCompInfoDao() {
		return revCompInfoDao;
	}

	public void setRevCompInfoDao(RevCompInfoDao revCompInfoDao) {
		this.revCompInfoDao = revCompInfoDao;
	}	
	@Resource(name = "infoCollectDao")
	private InfoCollectDao infoCollectDao;
	

	public InfoCollectDao getInfoCollectDao() {
		return infoCollectDao;
	}

	public void setInfoCollectDao(InfoCollectDao infoCollectDao) {
		this.infoCollectDao = infoCollectDao;
	}

	public PoliceDao getPoliceDao() {
		return policeDao;
	}

	public void setPoliceDao(PoliceDao policeDao) {
		this.policeDao = policeDao;
	}

	public PBOCDao getPbocDao() {
		return pbocDao;
	}

	public void setPbocDao(PBOCDao pbocDao) {
		this.pbocDao = pbocDao;
	}
	public AllotQueueDao getAllotQueueDao() {
		return allotQueueDao;
	}
	public void setAllotQueueDao(AllotQueueDao allotQueueDao) {
		this.allotQueueDao = allotQueueDao;
	}
	public ApplyLifeCicleDao getApplyLifeCicleDao() {
		return applyLifeCicleDao;
	}
	public void setApplyLifeCicleDao(ApplyLifeCicleDao applyLifeCicleDao) {
		this.applyLifeCicleDao = applyLifeCicleDao;
	}
	public PatchboltDao getPatchboltDao() {
		return patchboltDao;
	}
	public void setPatchboltDao(PatchboltDao patchboltDao) {
		this.patchboltDao = patchboltDao;
	}
	
	public BizInpApplDao getBizInpApplDao() {
		return bizInpApplDao;
	}
	public void setBizInpApplDao(BizInpApplDao bizInpApplDao) {
		this.bizInpApplDao = bizInpApplDao;
	}
	public AllotApplyAllotDao getAllotApplyAllotDao() {
		return allotApplyAllotDao;
	}
	public void setAllotApplyAllotDao(AllotApplyAllotDao allotApplyAllotDao) {
		this.allotApplyAllotDao = allotApplyAllotDao;
	}
	public CreditCheckDao getCreditCheckDaoImp() {
		return creditCheckDaoImp;
	}
	public void setCreditCheckDaoImp(CreditCheckDao creditCheckDaoImp) {
		this.creditCheckDaoImp = creditCheckDaoImp;
	}
	public SubReportDao getSubReportDaoImp() {
		return subReportDaoImp;
	}
	public void setSubReportDaoImp(SubReportDao subReportDaoImp) {
		this.subReportDaoImp = subReportDaoImp;
	}
	public ApOrgDao getApOrgDao() {
		return apOrgDao;
	}
	public void setApOrgDao(ApOrgDao apOrgDao) {
		this.apOrgDao = apOrgDao;
	}
	public ApUserDao getApUserDao() {
		return apUserDao;
	}
	public void setApUserDao(ApUserDao apUserDao) {
		this.apUserDao = apUserDao;
	}
	@Override
	public int queryCount(Map record) throws CoreException {
		return bizInpApplDao.queryCount(record);
	}
	@Override
	public int queryCountSp(Map record) throws CoreException {
		return bizInpApplDao.queryCountSp(record);
	}
	@Override
	public int queryCountWc(Map record) throws CoreException {
		return bizInpApplDao.queryCountWc(record);
	}
	@Override
	public int queryGtCount(Map record) throws CoreException {
		return bizInpApplDao.queryGtCount(record);
	}
	@Override
	public List queryGtList(Map record, int curNum, int pageNum) throws CoreException {
		List<BizInpAppl> list =  bizInpApplDao.queryGtList(record, curNum, pageNum);
		String ydjFlag = null,appId=null,matchingCardFlag=null;
		for (int j = 0; j < list.size(); j++) {
			ydjFlag=list.get(j).getYdjFlag();
			appId=list.get(j).getAppId();
			matchingCardFlag=list.get(j).getMatchingCardFlag();
			if("1".equals(ydjFlag)&&"1".equals(matchingCardFlag)){
				if(appId.endsWith("1")){
					appId=appId.substring(0,15)+"2";
				}else if(appId.endsWith("2")){
					appId=appId.substring(0,15)+"1";
				}
			}
			Integer patchStatusR=pbocDao.selectCountPbocPersonInfoByAppId(appId);
			list.get(j).setPatchStatusR(patchStatusR);
			Integer patchStatusP=policeDao.selectPoliceCountByAppId(appId);
			list.get(j).setPatchStatusP(patchStatusP);
			//可信身份认证信息(只有主卡发起可信身份认证查询，附卡不查，且是网申件才会查询)（易达金卡目前也不查询）
			Integer patchStatusPP=policeDao.selectDependableIdentityCardByAppid(appId);
			if(patchStatusPP!=0){
				//可信身份认证信息查询有结果时查询里面的“认证结果”，根据此结果的第一个字符判断是否提示“P”：0和5这在这两种情况不提示
				Map<String,String> map = policeDao.selectAuthResultByAppid(appId);
				if(map!=null){
					String authResult = map.get("AUTHRESULT");
					if(authResult!=null){
						if(!(authResult.startsWith("0") || authResult.startsWith("5"))){
							patchStatusPP=0;
						}
					}else{
						patchStatusPP=0;
					}
				}else{
					patchStatusPP=0;
				}
			}
			list.get(j).setPatchStatusPP(patchStatusPP);
			//智能语音
			Integer patchStatusS=creditCheckDaoImp.selectCallSVoiceCount(appId);
			list.get(j).setPatchStatusS(patchStatusS);
		}
		return list;
	}
	@Override
	public List queryListSp(Map record, int curNum, int pageNum) throws CoreException {
		List<BizInpAppl> list =  bizInpApplDao.queryListSp(record, curNum, pageNum);
		String ydjFlag = null,appId=null,matchingCardFlag=null;
		for (int j = 0; j < list.size(); j++) {
			ydjFlag=list.get(j).getYdjFlag();
			appId=list.get(j).getAppId();
			matchingCardFlag=list.get(j).getMatchingCardFlag();
			if("1".equals(ydjFlag)&&"1".equals(matchingCardFlag)){
				if(appId.endsWith("1")){
					appId=appId.substring(0,15)+"2";
				}else if(appId.endsWith("2")){
					appId=appId.substring(0,15)+"1";
				}
			}
			Integer patchStatusR=pbocDao.selectCountPbocPersonInfoByAppId(appId);
			list.get(j).setPatchStatusR(patchStatusR);
			Integer patchStatusP=policeDao.selectPoliceCountByAppId(appId);
			list.get(j).setPatchStatusP(patchStatusP);
			//可信任身份证信息(只有主卡发起可信身份认证查询，附卡不查，且是网申件才会查询)（易达金卡目前也不查询）
			Integer patchStatusPP=policeDao.selectDependableIdentityCardByAppid(appId);
			if(patchStatusPP!=0){
				//可信身份认证信息查询有结果时查询里面的“认证结果”，根据此结果的第一个字符判断是否提示“P”：0和5这在这两种情况不提示
				Map<String,String> map = policeDao.selectAuthResultByAppid(appId);
				if(map!=null){
					String authResult = map.get("AUTHRESULT");
					if(authResult!=null){
						if(!(authResult.startsWith("0") || authResult.startsWith("5"))){
							patchStatusPP=0;
						}
					}else{
						patchStatusPP=0;
					}
				}else{
					patchStatusPP=0;
				}
			}
			list.get(j).setPatchStatusPP(patchStatusPP);
			//智能语音
			Integer patchStatusS=creditCheckDaoImp.selectCallSVoiceCount(appId);
			list.get(j).setPatchStatusS(patchStatusS);
		}
		return list;
	}
	@Override
	public int queryCountForExamine(Map<String, Object> map) throws CoreException {
		return bizInpApplDao.queryCountForExamine(map);
	}
	@Override
	public List queryList(Map record, int curNum, int pageNum) throws CoreException {
		return bizInpApplDao.queryList(record, curNum, pageNum);
	}
	@Override
	public List<BizInpAppl> queryBizInpApplListForExamine(Map map, int curNum, int pageNum) throws CoreException {
		return bizInpApplDao.queryBizInpApplListForExamine(map,curNum,pageNum);
	}
	@Override
	public List queryListWc(Map record, int curNum, int pageNum) throws CoreException {
		List<BizInpAppl> list =  bizInpApplDao.queryListWc(record, curNum, pageNum);
		String ydjFlag = null,appId=null,matchingCardFlag=null;
		for (int j = 0; j < list.size(); j++) {
			ydjFlag=list.get(j).getYdjFlag();
			appId=list.get(j).getAppId();
			matchingCardFlag=list.get(j).getMatchingCardFlag();
			if("1".equals(ydjFlag)&&"1".equals(matchingCardFlag)){
				if(appId.endsWith("1")){
					appId=appId.substring(0,15)+"2";
				}else if(appId.endsWith("2")){
					appId=appId.substring(0,15)+"1";
				}
			}
			Integer patchStatusR=pbocDao.selectCountPbocPersonInfoByAppId(appId);
			Integer patchStatusP=policeDao.selectPoliceCountByAppId(appId);
			//可信身份证信息(只有主卡发起可信身份认证查询，附卡不查，且是网申件才会查询)（易达金卡目前也不查询）
			Integer patchStatusPP=policeDao.selectDependableIdentityCardByAppid(appId);
			if(patchStatusPP!=0){
				//可信身份认证信息查询有结果时查询里面的“认证结果”，根据此结果的第一个字符判断是否提示“P”：0和5这在这两种情况不提示
				Map<String,String> map = policeDao.selectAuthResultByAppid(appId);
				if(map!=null){
					String authResult = map.get("AUTHRESULT");
					if(authResult!=null){
						if(!(authResult.startsWith("0") || authResult.startsWith("5"))){
							patchStatusPP=0;
						}
					}else{
						patchStatusPP=0;
					}
				}else{
					patchStatusPP=0;
				}
			}
			list.get(j).setPatchStatusP(patchStatusP);
			list.get(j).setPatchStatusR(patchStatusR);
			list.get(j).setPatchStatusPP(patchStatusPP);
			//智能语音
			Integer patchStatusS=creditCheckDaoImp.selectCallSVoiceCount(appId);
			list.get(j).setPatchStatusS(patchStatusS);
		}
		return list;
	}
	
	@Override
	public AllotApplyAllot selectByPrimaryKey(String appId) {
		return allotApplyAllotDao.selectByPrimaryKey(appId);
	}

	@Override
	public int updateByPrimaryKey(AllotApplyAllot record) {
		return allotApplyAllotDao.updateByPrimaryKey(record);
	}
	@Override
	public void updateListAllotApplyAllot(List<AllotApplyAllot> list,String userId,String flag) throws Exception {
		for(int j=0;j<list.size();j++){
			AllotApplyAllot allot=list.get(j);
			allot.setCurrStatus("3");
			allot.setCurrOptUser(userId);
			allot.setUserDate(new Date());
			allot.setLstTeamDate(new Date());
			allot.setGroupTeamDate(new Date());
			allot.setLastOptUser(userId);
			ApUser user = apUserDao.queryApUserByUserCode(userId);
			// 增加流程生命周期
			if("1".equals(flag)){
				List<ApUserOrg> userOrgList = apUserOrgDao.queryApUserOrgByUserId(user.getUserId());
				if(userOrgList!=null && userOrgList.size()>0){
					ApOrg apOrg = apOrgDao.queryApOrgByOrgId(userOrgList.get(0).getOrgId());
					if(apOrg != null){
						allot.setCurrOptGroup(apOrg.getOrgNo());
					}
				}
				addApplyLifeCicle(allot,user.getUserName(),user.getUserName()+"将申请件获取到审批队列");
			}else if("2".equals(flag)){
				addApplyLifeCicle(allot,user.getUserName(),user.getUserName()+"抢件到审批队列");
			}
			allotApplyAllotDao.updateByPrimaryKeySelective(allot);
			/*if("01".equals(allot.getLaojianflag())||"02".equals(allot.getLaojianflag())){
				return;
			}*/
			//如果当前卡有套卡
			if(!"0".equals(allot.getMatchingCardFlag())&&"1".equals(allot.getYdjFlag())){
				String appId = allot.getAppId();
				String appId_fk = CommonUtil.getAnothorCardAppId(appId, allot.getMatchingCardFlag());
				if(appId_fk!=null){
					AllotApplyAllot allot_fk = allotApplyAllotDao.selectByPrimaryKey(appId_fk);
					if(allot_fk!=null && !allot.getCurrNode().equals(allot_fk.getCurrNode())){
						continue;
					}
					if(allot_fk==null || "3".equals(allot_fk.getCurrStatus()))
						continue;
					allot_fk.setCurrStatus("3");
					allot_fk.setCurrOptUser(userId);
					allot_fk.setUserDate(new Date());
					allot_fk.setLstTeamDate(new Date());
					allot_fk.setLastOptUser(userId);
					// 增加流程生命周期
					if("1".equals(flag)){
						allot_fk.setCurrOptGroup(allot.getCurrOptGroup());
						addApplyLifeCicle(allot_fk,user.getUserName(),user.getUserName()+"将申请件获取到审批队列");
					}/*else if("2".equals(flag)){
						addApplyLifeCicle(allot_fk,user.getUserName(),user.getUserName()+"抢件到审批队列");
					}*/
					allotApplyAllotDao.updateByPrimaryKeySelective(allot_fk);
				}
			}
		}
	}
	@Override
	public void updateByPrimaryKeySelective(AllotApplyAllot record) {
		 Map<String,Object> map = new HashMap<>();
		 map.put("appId", record.getAppId());
		 //将此流水号的在申请件分配表的信息插入到申请件分配历史表
		 bizInpApplDao.insertCopyOpasAllotToAllotHis(map);
		 allotApplyAllotDao.updateByPrimaryKeySelective(record);
	}
	
	public void addApplyLifeCicle(AllotApplyAllot allot,String chineseName,String desc) throws Exception{
		ApplyLifeCicle applyLifeCicle = new ApplyLifeCicle();
		applyLifeCicle.setUuid(StringUtil.randomUUIDPlainString());
		applyLifeCicle.setAppId(allot.getAppId());
		applyLifeCicle.setOperater(chineseName + "-" + allot.getCurrOptUser());
		applyLifeCicle.setOperateDesc(desc);
		applyLifeCicle.setOperateResult("完成");
		applyLifeCicle.setCrtDate(new Date());
		applyLifeCicle.setCrtUser(allot.getCurrOptUser());
		applyLifeCicle.setLstUpdDate(new Date());
		applyLifeCicle.setLstUpdUser(allot.getCurrOptUser());
		applyLifeCicleDao.addApplyLifeCicle(applyLifeCicle);
	}
	@Override
	public void updateCreditSubmitNewspaper(CreditCheck creditCheck, SubReport subReport) {
		try {
		// 修改申请件分配表
		creditCheckDaoImp.updateCreditCheck(creditCheck);
		//插入征信提报其他信息表,多次提报多次插入,不会覆盖
		subReport.setAutoId(UUIDGenerator.getUUID());
		//判断是否要进行插入
		int count = subReportDaoImp.queryUnSubReportByAppId(subReport.getAppId());
		if(count>0){
			subReportDaoImp.updateSubReportByAppId(subReport);
			
		}else{
			subReportDaoImp.insertOpasSubmittypeInfo(subReport);
		}
		subReportDaoImp.insertOpasSubmittypeInfoHis(subReport);
		Map<String,Object> userMap=new HashMap<String,Object>();//全流程记录
		userMap.put("userCode", subReport.getCrtUser());
		Map<String,Object> apUserMap=bizInpApplDao.selectApUserByUuIdUserCode(userMap);
		String userCode=apUserMap.get("userCode").toString();
		String userName=apUserMap.get("userName").toString();
			ApplyLifeCicle a=new ApplyLifeCicle();
			a.setAppId(subReport.getAppId());
			
			a.setOperateResult("完成");
			a.setCrtDate(new Date());
			a.setUuid(StringUtil.randomUUIDPlainString());
			
		String submitType = creditCheck.getCheck_submitType();
		if("5".equals(submitType)){//add by wenyh
			if(apUserMap!=null){
				a.setOperateDesc(userName+"将申请件提报到提报反洗钱未完成队列");
				if(!"".equals(userCode)){
					a.setOperater(userName+"-"+userCode);
					a.setCrtUser(userCode);
				}else{
					a.setOperater("systemTb");
					a.setCrtUser("systemTb");
				}
			}
		}else if("4".equals(submitType)){
			if(apUserMap!=null){
				a.setOperateDesc(userName+"将申请件提报到提报授权未完成队列");
				if(!"".equals(userCode)){
					a.setOperater(userName+"-"+userCode);
					a.setCrtUser(userCode);
				}else{
					a.setOperater("systemTb");
					a.setCrtUser("systemTb");
				}
			}
		}else if("3".equals(submitType)){
			if(apUserMap!=null){
				a.setOperateDesc(userName+"将申请件提报到提报账户未完成队列");
				if(!"".equals(userCode)){
					a.setOperater(userName+"-"+userCode);
					a.setCrtUser(userCode);
				}else{
					a.setOperater("systemTb");
					a.setCrtUser("systemTb");
				}
			}
		}else if("2".equals(submitType)){
			if(apUserMap!=null){
				a.setOperateDesc(userName+"将申请件提报到提报催收未完成队列");
				if(!"".equals(userCode)){
					a.setOperater(userName+"-"+userCode);
					a.setCrtUser(userCode);
				}else{
					a.setOperater("systemTb");
					a.setCrtUser("systemTb");
				}
			}
		}
		applyLifeCicleDao.addApplyLifeCicle(a);
		//根据appId判断该申请件是否为套卡
		//根据appId查询进件卡
		BizInpApplC1 bizinpapplc1 = patchboltDao.querybizInpApplList(creditCheck.getCheck_number());
		//0-无套卡标识  1-主卡  2-套卡
		char[] arr = creditCheck.getCheck_number().toCharArray();
		String ydjFlag = bizinpapplc1.getYdjFlag();
		if("1".equals(ydjFlag)){
		if(!"0".equals(bizinpapplc1.getMatchingCardFlag())){
			String appIdMatching=null;
			if(arr.length==16&&arr[15]=='1'){
				arr[15]='2';
			}else{
				arr[15]='1';
				 appIdMatching=new String(arr);//套卡 appId
			}
		    appIdMatching=new String(arr);//套卡 appId
			subReport.setAppId(appIdMatching);
			creditCheck.setCheck_number(appIdMatching);
			//根据appId查询申请件分件表  套卡 
			AllotApplyAllot allotApplyAllot=allotApplyAllotDao.selectByPrimaryKey(appIdMatching);
			if(allotApplyAllot!=null){
			//提报套卡
			//修改申请件分配表
			creditCheckDaoImp.updateCreditCheck(creditCheck);
			//插入征信提报其他信息表
			subReport.setAutoId(UUIDGenerator.getUUID());
			int count1 = subReportDaoImp.querySubReportByAppId(subReport.getAppId());
			if(count1>0){
				subReportDaoImp.updateSubReportByAppId(subReport);
				
			}else{
				subReportDaoImp.insertOpasSubmittypeInfo(subReport);
			}
			subReportDaoImp.insertOpasSubmittypeInfoHis(subReport);
			}
			a.setAppId(new String(arr));
			a.setUuid(StringUtil.randomUUIDPlainString());
			if("5".equals(submitType)){//add by wenyh
				if(apUserMap!=null){
					a.setOperateDesc(userName+"将申请件提报到提报反洗钱未完成队列");
					if(!"".equals(userCode)){
						a.setOperater(userName+"-"+userCode);
						a.setCrtUser(userCode);
					}else{
						a.setOperater("systemTb");
						a.setCrtUser("systemTb");
					}
				}
			}else if("4".equals(submitType)){
				if(apUserMap!=null){
					a.setOperateDesc(userName+"将申请件提报到提报授权未完成队列");
					if(!"".equals(userCode)){
						a.setOperater(userName+"-"+userCode);
						a.setCrtUser(userCode);
					}else{
						a.setOperater("systemTb");
						a.setCrtUser("systemTb");
					}
				}
			}else if("3".equals(submitType)){
				if(apUserMap!=null){
					a.setOperateDesc(userName+"将申请件提报到提报账户未完成队列");
					if(!"".equals(userCode)){
						a.setOperater(userName+"-"+userCode);
						a.setCrtUser(userCode);
					}else{
						a.setOperater("systemTb");
						a.setCrtUser("systemTb");
					}
				}
			}else if("2".equals(submitType)){
				if(apUserMap!=null){
					a.setOperateDesc(userName+"将申请件提报到提报催收未完成队列");
					if(!"".equals(userCode)){
						a.setOperater(userName+"-"+userCode);
						a.setCrtUser(userCode);
					}else{
						a.setOperater("systemTb");
						a.setCrtUser("systemTb");
					}
				}
			}
			applyLifeCicleDao.addApplyLifeCicle(a);
		}
		}
		} catch (Exception e) {
			throw new RuntimeException("提报异常。");
		}
	}

	@Override
	public void updateCreditSubmit(CreditCheck creditCheck, OpasTelcheckResult telcheckResult) {
		//申请件分配表数据记录历史表中
		creditCheckDaoImp.insertCopyOpasAllotApplyAllot(creditCheck.getCheck_number());
		//修改申请件分配表
		creditCheckDaoImp.updateCreditCheck(creditCheck);
		//添加征信电核结果信息表
		//creditCheckDaoImp.insertOpasTelcheckResult(telcheckResult);

	}
	@Override
	public int queryFinishedCount(Map<String, Object> map) throws CoreException {
		return bizInpApplDao.queryFinishedCount(map);
	}

	@Override
	public int queryToHighlevelCount(Map<String, Object> map) throws CoreException {
		return bizInpApplDao.queryToHighlevelCount(map);
	}

	@Override
	public int queryToFileCount(Map<String, Object> map) throws CoreException {
		return bizInpApplDao.queryToFileCount(map);
	}

	@Override
	public int queryFinishedCount1(Map<String, Object> map) throws CoreException {
		return bizInpApplDao.queryFinishedCount1(map);
	}

	@Override
	public int queryUnfinishedCount(Map<String, Object> map) throws CoreException {
		return bizInpApplDao.queryUnfinishedCount(map);
	}

	@Override
	public List<AllotApplyAllot> selectAllotByCondition(Map map) {
		return allotApplyAllotDao.selectAllotByCondition(map);
	}
	@Override
	public List<AllotApplyAllot> selectByAppId_15(String appId) {
		return allotApplyAllotDao.selectByAppId_15(appId);
	}
	@Override
	public List<AllotApplyAllot> getSpAllotByCondition(Map map) {
		return allotApplyAllotDao.getSpAllotByCondition(map);
	}
	@Override
	public List<ApOrg> queryUserOrgs(String userId) {
		return apOrgDao.queryUserOrgs(userId);
	}

	@Override
	public Map<String, Object> queryProcessIdByAppId(Map paramMap) {
		return bizInpApplDao.selectProcessIdByAppId(paramMap);
	}

	@Override
	public int findBizInpApplZxListFinishedCount(Map paramMap) {
		
		 return bizInpApplDao.selectBizInpApplZxListFinishedCount(paramMap);
	}

	@Override
	public List findBizInpApplZxListFinishedList(Map record, int curNum, int pageNum){
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		list=bizInpApplDao.selectBizInpApplZxListFinishedList(record, curNum, pageNum);
		Map<String,Object> userMap=new HashMap<String,Object>();
		if(!list.isEmpty()){
		for (int i = 0; i < list.size(); i++) {
			String ydjFlag=list.get(i).get("ydjFlag").toString();
			String appId=list.get(i).get("appId").toString();
			String matchingCardFlag=list.get(i).get("matchingCardFlag").toString();
			if("1".equals(ydjFlag)&&"1".equals(matchingCardFlag)){
				if(appId.endsWith("1")){
					appId=appId.substring(0,15)+"2";
				}else if(appId.endsWith("2")){
					appId=appId.substring(0,15)+"1";
				}
			}
			Integer patchStatusR=pbocDao.selectCountPbocPersonInfoByAppId(appId);
			Integer patchStatusP=policeDao.selectPoliceCountByAppId(appId);
			//可信任身份证(只有主卡发起可信身份认证查询，附卡不查，且是网申件才会查询)（易达金卡目前也不查询）
			Integer patchStatusPP=policeDao.selectDependableIdentityCardByAppid(appId);
			if(patchStatusPP!=0){
				//可信身份认证信息查询有结果时查询里面的“认证结果”，根据此结果的第一个字符判断是否提示“P”：0和5这在这两种情况不提示
				Map<String,String> map = policeDao.selectAuthResultByAppid(appId);
				if(map!=null){
					String authResult = map.get("AUTHRESULT");
					if(authResult!=null){
						if(!(authResult.startsWith("0") || authResult.startsWith("5"))){
							patchStatusPP=0;
						}
					}else{
						patchStatusPP=0;
					}
				}else{
					patchStatusPP=0;
				}
			}
			list.get(i).put("patchStatusR",patchStatusR);
			list.get(i).put("patchStatusP",patchStatusP);
			list.get(i).put("patchStatusPP",patchStatusPP);
			//智能语音
			Integer patchStatusS=creditCheckDaoImp.selectCallSVoiceCount(appId);
			list.get(i).put("patchStatusS",patchStatusS);
			if(list.get(i)!=null&&!"".equals(list.get(i).get("lastOptUser"))&&list.get(i).get("lastOptUser")!=null){
			//最后一次操作人赋值为中文
			userMap.put("userCode",list.get(i).get("lastOptUser"));
			Map<String,Object> apUserMap=bizInpApplDao.selectApUserByUuIdUserCode(userMap);
			if(apUserMap!=null){
			String userName=apUserMap.get("userName").toString();
			list.get(i).put("lastOptUser",userName);
			}
		}
		}
		}
		return list;
	}

	@Override
	public Map<String, Object> updateZxApplyGainBzk(Map<String, Object> paraMap) {
	 Map<String, Object> msgMap=new HashMap<String,Object>();//存放 返回前台的信息
	 Map<String, Object> paraMapUpdateAllot=new HashMap<String,Object>();//存放 需要修改的分件表的数据信息
	 paraMapUpdateAllot.put("currOptUser", paraMap.get("userId"));//更改 CURR_OPT_USER
	 String currAppId=paraMap.get("appId").toString();
	 String currAppIdFront=currAppId.substring(0,15);//流水号 前十五位
	// paraMapUpdateAllot.put("appId", paraMap.get("appId"));//根据参数 appId修改相应 数据
	 paraMapUpdateAllot.put("appId",currAppIdFront);
	 paraMapUpdateAllot.put("delStatus","0");//人工处理状态DEL_STATUS 变为0
	 paraMapUpdateAllot.put("batchCreditFlag","0");// BATCH_CREDIT_FLAG 变为0  变为非批量处理的件
	 paraMapUpdateAllot.put("lstTeamDate", paraMap.get("userDate"));
	 paraMapUpdateAllot.put("userDate",paraMap.get("userDate"));// 手动抢件附上 userDate时间（这个件分配到人的时间）
	 //获取 申请件分配表 currStatus
	 //Map allotMap=bizInpApplDao.selectAllotApplyCurrStatusByMap(paraMap);
	 paraMap.put("appId", currAppIdFront);//进行 前15位查询
	 List<Map> ListAllotMap= bizInpApplDao.selectAllotApplyByMapLikeAppId(paraMap);
	 //==全流程记录
	 Map<String,Object> userMap=new HashMap<String,Object>();//全流程记录
	 userMap.put("userCode", paraMap.get("userId").toString());//前台封装的userId其实是userCode，数据库的userId是uuid
	 Map<String,Object> apUserMap=bizInpApplDao.selectApUserByUuIdUserCode(userMap);
	 String userCode=apUserMap.get("userCode").toString();
	 String userName=apUserMap.get("userName").toString();
	 if(userName!=null&&!"".equals(userName)){
		 paraMapUpdateAllot.put("currUserName", userName);//附上 当前人的中文名
	 }
	 int updateNum=0;//修改是否 成功的标识
	 //----
	 if(!ListAllotMap.isEmpty()){
	 for (Map allotMap : ListAllotMap) {
	 boolean isGainSuccess=false;//表示申请件 是否获取成功  用于 控制 全流程节点的插入 
	 paraMap.put("appId", allotMap.get("APPID"));
	 if(allotMap!=null&&allotMap.get("CURRSTATUS")!=null){
		paraMapUpdateAllot.put("lastOptUser",paraMap.get("userId"));//更改 LAST_OPT_USER allotMap.get("CURRUSER")
		paraMapUpdateAllot.put("allotVersion",allotMap.get("ALLOTVERSION"));//乐观锁 
		String currStatus=allotMap.get("CURRSTATUS").toString();
		String delStatus=allotMap.get("DELSTATUS").toString();
		if((currStatus.equals("0")||currStatus.equals("6"))&&!"1".equals(delStatus)){//分配状态 是 当前环节池中 或是 队列中 （队列 是标准卡  获取时 特殊拥有的）
			//将此流水号的在申请件分配表的信息插入到申请件分配历史表
			bizInpApplDao.insertCopyOpasAllotToAllotHis(paraMap);//插入当前卡
			//获取  征信决策结果 
			Map creditDecisionMap=	bizInpApplDao.selectCreditDecisionResultByAppId(paraMap);
			if(creditDecisionMap!=null&&creditDecisionMap.get("CREDITDICISIONRESULT")!=null){
			String creditDicisionResult=creditDecisionMap.get("CREDITDICISIONRESULT").toString();
			if(!creditDicisionResult.equals("")){
				String[] strs=creditDicisionResult.split("-");
				String currOptQueue=strs[0];
				if(currStatus.equals("0")){
				if(currOptQueue!=null&&!currOptQueue.equals("")){
					paraMapUpdateAllot.put("currOptQueue", currOptQueue);//更改  curr_Opt_Queue,last_opt_queue 队列
					AllotQueue AllotQueue;
					try {
						AllotQueue = allotQueueDao.selectAllotQueueByCode(currOptQueue);
						if(AllotQueue!=null){
							if(AllotQueue.getQueueName()!=null&&!"".equals(AllotQueue.getQueueName())){
								paraMapUpdateAllot.put("lastOptQueue", AllotQueue.getQueueName());//更改  curr_Opt_Queue,last_opt_queue 队列
							}
						}
					} catch (CoreException e) {
					}
				}
				}
			}
			}
			//从 表opas_ap_user_org(用户组别关系表) 中 获取  org_id （机构Id）
			Map orgIdMap=bizInpApplDao.selectUserOrgOrgIdByUserUuid(paraMap);
			if(orgIdMap!=null&&orgIdMap.get("ORGID")!=null){
				String orgId=orgIdMap.get("ORGID").toString();
				if(!orgId.equals("")){//根据 org_id（组序列值） --->查表 opas_ap_org（用户组管理） 表 获取 org_no(组代码) 
					paraMap.put("orgId", orgId);
					Map orgNoMap=bizInpApplDao.selectApOrgOrgNoByOrgId(paraMap);	
					if(orgNoMap.get("ORGNO")!=null){
						String orgNo=orgNoMap.get("ORGNO").toString();//组代码
						if(!orgNo.equals("")){
							paraMapUpdateAllot.put("currOptGroup", orgNo);//更改  curr_opt_group,last_opt_group 
							if(orgNoMap.get("ORGNAME")!=null&&!"".equals(orgNoMap.get("ORGNAME").toString())){
								String orgName=orgNoMap.get("ORGNAME").toString();//组中文名
								paraMapUpdateAllot.put("lastOptGroup", orgName);//更改  curr_opt_group,last_opt_group 
							}
						}
					}	
				}
			}
			if(allotMap.get("GROUPDATE")!=null&&!"".equals(allotMap.get("GROUPDATE").toString())){
			}else{//组天数 不存在时才赋值
				paraMapUpdateAllot.put("groupDate",paraMap.get("userDate"));//更改groupDate 组时间 
			}
			paraMapUpdateAllot.put("queueDate", paraMap.get("userDate"));
			paraMapUpdateAllot.put("groupTeamDate", paraMap.get("userDate"));
			paraMapUpdateAllot.put("currStatus", "3");//分配状态改为 3 已分配 未挂起	
		   //进行  更改	 修改申请件分配表操作 
		   //bizInpApplDao.updateAllotApplyAllotByAppId(paraMapUpdateAllot);
			updateNum= bizInpApplDao.updateAllotByMapLikeAppId(paraMapUpdateAllot);
			if(updateNum>0){
			 isGainSuccess=true;
			 msgMap.put("msg", "申请件获取成功。");
			}
		}else if(currStatus.equals("1")&&!"1".equals(delStatus)){//分配状态 未分配 未挂起
			//将此流水号的在申请件分配表的信息插入到申请件分配历史表
			bizInpApplDao.insertCopyOpasAllotToAllotHis(paraMap);//插入当前卡
			//从 表opas_ap_user_org(用户组别关系表) 中 获取  org_id （机构Id）
			Map orgIdMap=bizInpApplDao.selectUserOrgOrgIdByUserUuid(paraMap);
			if(orgIdMap!=null&&orgIdMap.get("ORGID")!=null){
				String orgId=orgIdMap.get("ORGID").toString();
				if(!orgId.equals("")){//根据 org_id（组序列值） --->查表 opas_ap_org（用户组管理） 表 获取 org_no(组代码) 
					paraMap.put("orgId", orgId);
					Map orgNoMap=bizInpApplDao.selectApOrgOrgNoByOrgId(paraMap);	
					if(orgNoMap!=null&&orgNoMap.get("ORGNO")!=null){
						String orgNo=orgNoMap.get("ORGNO").toString();//组代码
						if(!orgNo.equals("")){
							paraMapUpdateAllot.put("currOptGroup", orgNo);//更改  curr_opt_group,last_opt_group 
							if(orgNoMap.get("ORGNAME")!=null&&!"".equals(orgNoMap.get("ORGNAME").toString())){
								String orgName=orgNoMap.get("ORGNAME").toString();//组中文名
								paraMapUpdateAllot.put("lastOptGroup", orgName);//更改  curr_opt_group,last_opt_group 
							}
						}
					}	
				}
			}
			paraMapUpdateAllot.put("currStatus", "3");//分配状态改为 3 已分配 未挂起	
			//进行  更改	 修改申请件分配表操作 
			//bizInpApplDao.updateAllotApplyAllotByAppId(paraMapUpdateAllot);
			updateNum=bizInpApplDao.updateAllotByMapLikeAppId(paraMapUpdateAllot);
			if(updateNum>0){
				 isGainSuccess=true;
				 msgMap.put("msg", "申请件获取成功。");
			}
		}else{//无法抓取此件
			msgMap.put("msg", "此流水号因分配状态不能获取。申请件获取失败。");
		}
	}else{//无法抓取此件 
		msgMap.put("msg", "此流水号不存在或已被提报。申请件获取失败。");
	}	
	 if(isGainSuccess){
	 //全流程记录
			ApplyLifeCicle a=new ApplyLifeCicle();
			a.setAppId(allotMap.get("APPID").toString());
			if(apUserMap!=null){
			a.setOperateDesc(userName+"将申请件获取到征信未完成队列");
			if(!"".equals(userCode)){
				a.setOperater(userName+"-"+userCode);
				a.setCrtUser(userCode);
			}else{
				a.setOperater("systemZx");
				a.setCrtUser("systemZx");
			}
			}
			a.setOperateResult("完成");
			a.setCrtDate(new Date());
			a.setUuid(StringUtil.randomUUIDPlainString());
			try {
				applyLifeCicleDao.addApplyLifeCicle(a);
			} catch (Exception e) {
				throw new RuntimeException();
			}
	 }else{
		 msgMap.put("msg", "此申请件已被分件,申请件获取失败。");
	 }
	}
	}else{
		 msgMap.put("msg", "此流水号不存在或已被提报。申请件获取失败。");
    }
	return msgMap;
	}

	@Override
	public Map<String, Object> updateZxApplyGainYdj(Map<String, Object> paraMap) {
		 Map<String, Object> msgMap=new HashMap<String,Object>();//存放 返回前台的信息
		 Map<String, Object> paraMapUpdateAllot=new HashMap<String,Object>();//存放 需要修改的分件表的数据信息
		 paraMapUpdateAllot.put("currOptUser", paraMap.get("userId"));//更改 CURR_OPT_USER
		 String currAppId=paraMap.get("appId").toString();
		 String currAppIdFront=currAppId.substring(0,15);//流水号 前十五位
		 // paraMapUpdateAllot.put("appId", paraMap.get("appId"));//根据参数 appId修改相应 数据
		 paraMapUpdateAllot.put("appId",currAppIdFront);
		 paraMapUpdateAllot.put("delStatus","0");//人工处理状态DEL_STATUS 变为0
		 paraMapUpdateAllot.put("batchCreditFlag","0");// BATCH_CREDIT_FLAG 变为0  变为非批量处理的件
		 paraMapUpdateAllot.put("lstTeamDate", paraMap.get("userDate"));
		 paraMapUpdateAllot.put("userDate",paraMap.get("userDate"));// 手动抢件附上 userDate时间（这个件分配到人的时间）
		//获取 申请件分配表 currStatus
		//Map allotMap=bizInpApplDao.selectAllotApplyCurrStatusByMap(paraMap);
		paraMap.put("appId", currAppIdFront);//进行 前15位查询
	//	List<Map> ListAllotMap= bizInpApplDao.selectAllotApplyByMapLikeAppId(paraMap);
		List<Map> ListAllotMap=bizInpApplDao.selectAllotApplyByMapLikeCurrstatusAppId(paraMap);//根据appId直接获取   池中或组中的申请件
		//==全流程记录
		 Map<String,Object> userMap=new HashMap<String,Object>();//全流程记录
		 userMap.put("userCode", paraMap.get("userId").toString());//前台封装的userId其实是userCode，数据库的userId是uuid
		 Map<String,Object> apUserMap=bizInpApplDao.selectApUserByUuIdUserCode(userMap);
		 String userCode=apUserMap.get("userCode").toString();
		 String userName=apUserMap.get("userName").toString();
		 if(userName!=null&&!"".equals(userName)){
			 paraMapUpdateAllot.put("currUserName", userName);//附上 当前人的中文名
		 }
		 int updateNum=0;//修改是否 成功的标识
		 //----
		if(!ListAllotMap.isEmpty()){
		for (Map allotMap : ListAllotMap) {
		boolean isGainSuccess=false;//表示申请件 是否获取成功  用于 控制 全流程节点的插入 
		paraMap.put("appId", allotMap.get("APPID"));
		if(allotMap!=null&&allotMap.get("CURRSTATUS")!=null){
			paraMapUpdateAllot.put("lastOptUser",paraMap.get("userId"));//更改 LAST_OPT_USER allotMap.get("CURRUSER")
			paraMapUpdateAllot.put("allotVersion",allotMap.get("ALLOTVERSION"));//乐观锁 
			String currStatus=allotMap.get("CURRSTATUS").toString();
			String delStatus=allotMap.get("DELSTATUS").toString();
			 if((currStatus.equals("0")||currStatus.equals("1"))&&!"1".equals(delStatus)){//分配状态  当前环节池中、  未分配 未挂起
				//将此流水号的在申请件分配表的信息插入到申请件分配历史表
					bizInpApplDao.insertCopyOpasAllotToAllotHis(paraMap);//插入当前卡
				//从 表opas_ap_user_org(用户组别关系表) 中 获取  org_id （机构Id）
				Map orgIdMap=bizInpApplDao.selectUserOrgOrgIdByUserUuid(paraMap);
				if(orgIdMap!=null&&orgIdMap.get("ORGID")!=null){
					String orgId=orgIdMap.get("ORGID").toString();
					if(!orgId.equals("")){//根据 org_id（组序列值） --->查表 opas_ap_org（用户组管理） 表 获取 org_no(组代码) 
						paraMap.put("orgId", orgId);
						Map orgNoMap=bizInpApplDao.selectApOrgOrgNoByOrgId(paraMap);	
						if(orgNoMap!=null&&orgNoMap.get("ORGNO")!=null){
							String orgNo=orgNoMap.get("ORGNO").toString();//组代码
							if(!orgNo.equals("")){
								paraMapUpdateAllot.put("currOptGroup", orgNo);//更改  curr_opt_group,last_opt_group 
								if(orgNoMap.get("ORGNAME")!=null&&!"".equals(orgNoMap.get("ORGNAME").toString())){
									String orgName=orgNoMap.get("ORGNAME").toString();//组中文名
									paraMapUpdateAllot.put("lastOptGroup", orgName);//更改  curr_opt_group,last_opt_group 
								}
							}
						}	
					}
				}
				if(currStatus.equals("0")){//池中 获取才改 groupDate
					if(allotMap.get("GROUPDATE")!=null&&!"".equals(allotMap.get("GROUPDATE").toString())){
					}else{//组天数 不存在时才赋值
						paraMapUpdateAllot.put("groupDate",paraMap.get("userDate"));//更改groupDate 组时间 
					}
					paraMapUpdateAllot.put("groupTeamDate", paraMap.get("userDate"));
					paraMapUpdateAllot.put("queueDate", paraMap.get("userDate"));
				}
				paraMapUpdateAllot.put("currStatus", "3");//分配状态改为 3 已分配 未挂起	
				//进行  更改	 修改申请件分配表操作 
				//bizInpApplDao.updateAllotApplyAllotByAppId(paraMapUpdateAllot);
				updateNum=bizInpApplDao.updateAllotByMapLikeAppId(paraMapUpdateAllot);
				if(updateNum>0){
					isGainSuccess=true;
					msgMap.put("msg", "申请件获取成功。");
				}
			}else{//无法抓取此件
				msgMap.put("msg", "此流水号因分配状态不能获取。申请件获取失败。");
			}
		}else{//无法抓取此件 
			msgMap.put("msg", "此流水号不存在或已被提报。申请件获取失败。");
		}	
		if(isGainSuccess){
		//全流程记录
		ApplyLifeCicle a=new ApplyLifeCicle();
		a.setAppId(allotMap.get("APPID").toString());
		if(apUserMap!=null){
		a.setOperateDesc(userName+"将申请件获取到征信未完成队列");
		if(!"".equals(userCode)){
			a.setOperater(userName+"-"+userCode);
			a.setCrtUser(userCode);
		}else{
			a.setOperater("systemZx");
			a.setCrtUser("systemZx");
		}
		}
		a.setOperateResult("完成");
		a.setCrtDate(new Date());
		a.setUuid(StringUtil.randomUUIDPlainString());
		try {
			applyLifeCicleDao.addApplyLifeCicle(a);
		} catch (Exception e) {
			throw new RuntimeException();
		}
		}else{
			msgMap.put("msg", "此申请件已被分件,申请件获取失败。");
		 }
		}
		}else{
			msgMap.put("msg", "此流水号不存在或已被提报。申请件获取失败。");
		}
		return msgMap;
	}

	@Override
	public Map<String, Object> updateZxApplyRobBzkYdj(Map<String, Object> paraMap) {
	
		 Map<String, Object> msgMap=new HashMap<String,Object>();//存放 返回前台的信息
		try{
			boolean groupNotExist=false;//组不存在
			//从 表opas_ap_user_org(用户组别关系表) 中 获取  org_id （机构Id）
			Map orgIdMap=bizInpApplDao.selectUserOrgOrgIdByUserUuid(paraMap);
			if(orgIdMap!=null&&orgIdMap.get("ORGID")!=null){
				String orgId=orgIdMap.get("ORGID").toString();
				if(!orgId.equals("")){//根据 org_id（组序列值） --->查表 opas_ap_org（用户组管理） 表 获取 org_no(组代码) 
					paraMap.put("orgId", orgId);
					Map orgNoMap=bizInpApplDao.selectApOrgOrgNoByOrgId(paraMap);	
					if(orgNoMap!=null&&orgNoMap.get("ORGNO")!=null){
						String orgNo=orgNoMap.get("ORGNO").toString();//组代码
						if(!orgNo.equals("")){
//							paraMapUpdateAllot.put("currOptGroup", orgNo);//更改  curr_opt_group,last_opt_group 
//							paraMapUpdateAllot.put("lastOptGroup", orgNo);//更改  curr_opt_group,last_opt_group 
							paraMap.put("currOptGroup", orgNo);
						}else{
							groupNotExist=true;
						}
					}else{
						groupNotExist=true;
					}	
				}else{
					groupNotExist=true;
				}
			}else{
				groupNotExist=true;
			}
		 if(groupNotExist){
			msgMap.put("msg", "当前用户不存在于任何组，没有能够抢的申请件。");
			msgMap.put( "success", false);
			return msgMap;	
		 }
		 List<Map> allotListMap=bizInpApplDao.selectListAllotApplyZxRobByMap(paraMap);
		 int num=0;//用于控制每次抢件 最多抢三件
		 String msg="申请件 ";
		 Integer  applyRobNum=Integer.parseInt(paraMap.get("applyRobNum").toString());//抢的申请件数量
		//==全流程记录
		 Map<String,Object> userMap=new HashMap<String,Object>();//全流程记录
		 userMap.put("userCode", paraMap.get("userId").toString());//前台封装的userId其实是userCode，数据库的userId是uuid
		 Map<String,Object> apUserMap=bizInpApplDao.selectApUserByUuIdUserCode(userMap);
		 String userCode=apUserMap.get("userCode").toString();
		 String userName=apUserMap.get("userName").toString();
		 //----
		 for (Map map : allotListMap) {
			if(num>=applyRobNum){//当num 为applyRobNum时 跳出本循环
				break;
			}
		//	int updateSingleNum=0;//单个修改成功的标志
			Map<String, Object> paraMapUpdateAllot=new HashMap<String,Object>();//存放 需要修改的分件表的数据信息
			if(userName!=null&&!"".equals(userName)){
				 paraMapUpdateAllot.put("currUserName", userName);//附上 当前人的中文名
			 }
			 paraMapUpdateAllot.put("allotVersion",map.get("ALLOTVERSION"));//乐观锁 
			 paraMapUpdateAllot.put("currOptUser", paraMap.get("userId"));//更改 CURR_OPT_USER
			 paraMapUpdateAllot.put("lastOptUser", paraMap.get("userId"));//更改 LAST_OPT_USER   map.get("CURRUSER")
			 paraMapUpdateAllot.put("delStatus","0");//人工处理状态DEL_STATUS 变为0
			 paraMapUpdateAllot.put("batchCreditFlag","0");// BATCH_CREDIT_FLAG 变为0  变为非批量处理的件
			 paraMapUpdateAllot.put("userDate",paraMap.get("userDate"));// 手动抢件附上 userDate时间（这个件分配到人的时间）
			// paraMapUpdateAllot.put("appId", map.get("APPID"));//根据参数 appId修改相应 数据
			 String currAppIdFront=map.get("APPID").toString();//流水号 前十五位
			 // paraMapUpdateAllot.put("appId", paraMap.get("appId"));//根据参数 appId修改相应 数据
			 paraMapUpdateAllot.put("appId",currAppIdFront);
			 paraMapUpdateAllot.put("lstTeamDate", paraMap.get("userDate"));
			 paraMap.put("appId", currAppIdFront);
			 paraMapUpdateAllot.put("currStatus", "3");//分配状态改为 3 已分配 未挂起	
				
					msg+=" "+map.get("APPID").toString(); 
				    num+=1;
				    List<Map> ListAllotMap= bizInpApplDao.selectAllotApplyByMapLikeAppId(paraMap);
					 for (Map map2 : ListAllotMap) {
						 Map<String, Object> paraMapAllotHis=new HashMap<String,Object>();
						 paraMapAllotHis.put("appId", map2.get("APPID").toString());
						 bizInpApplDao.insertCopyOpasAllotToAllotHis(paraMapAllotHis);//插入当前卡
						 //全流程记录
							ApplyLifeCicle a=new ApplyLifeCicle();
							a.setAppId(map2.get("APPID").toString());
							if(apUserMap!=null){
							a.setOperateDesc(userName+"将申请件手动抢件至征信未完成队列");
							if(!"".equals(userCode)){
								a.setOperater(userName+"-"+userCode);
								a.setCrtUser(userCode);
							}else{
								a.setOperater("systemZx");
								a.setCrtUser("systemZx");
							}
							}
							a.setOperateResult("完成");
							a.setCrtDate(new Date());
							a.setUuid(StringUtil.randomUUIDPlainString());
							try {
								applyLifeCicleDao.addApplyLifeCicle(a);
							} catch (Exception e) {
								throw new RuntimeException("出现异常，手动抢件失败。");
							}
					}
			//进行  更改	 修改申请件分配表操作 
			//bizInpApplDao.updateAllotApplyAllotByAppId(paraMapUpdateAllot);
			  bizInpApplDao.updateAllotByMapLikeAppId(paraMapUpdateAllot);
				
		}
		 if(num>0){//num大于0代表 至少抢了一个申请件
			 msg+=" 手动抢件成功。";
			 msgMap.put("msg", msg);
			 msgMap.put( "success", true);
		 }else{
			 msgMap.put("msg", "没有能够抢的申请件。");
			 msgMap.put( "success", false);
		 }
		}catch(Exception e){
			throw new RuntimeException("出现异常，手动抢件失败。");
		}
		return msgMap;
	}
	@Override
	public int findBizInpApplZxListHangUpCount(Map paramMap) {
		return bizInpApplDao.selectBizInpApplZxListHangUpCount(paramMap);
	}

	@Override
	public List findBizInpApplZxListHangUpList(Map record, int curNum, int pageNum) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		list=bizInpApplDao.selectBizInpApplZxListHangUpList(record, curNum, pageNum);
		Map<String,Object> userMap=new HashMap<String,Object>();
		if(!list.isEmpty()){
		for (int i = 0; i < list.size(); i++) {
			String ydjFlag=list.get(i).get("ydjFlag").toString();
			String appId=list.get(i).get("appId").toString();
			String matchingCardFlag=list.get(i).get("matchingCardFlag").toString();
			if("1".equals(ydjFlag)&&"1".equals(matchingCardFlag)){
				if(appId.endsWith("1")){
					appId=appId.substring(0,15)+"2";
				}else if(appId.endsWith("2")){
					appId=appId.substring(0,15)+"1";
				}
			}
			Integer patchStatusR=pbocDao.selectCountPbocPersonInfoByAppId(appId);
			Integer patchStatusP=policeDao.selectPoliceCountByAppId(appId);
			//可信任身份证
			Integer patchStatusPP=policeDao.selectDependableIdentityCardByAppid(appId);
			if(patchStatusPP!=0){
				//可信身份认证信息查询有结果时查询里面的“认证结果”，根据此结果的第一个字符判断是否提示“P”：0和5这在这两种情况不提示
				Map<String,String> map = policeDao.selectAuthResultByAppid(appId);
				if(map!=null){
					String authResult = map.get("AUTHRESULT");
					if(authResult!=null){
						if(!(authResult.startsWith("0") || authResult.startsWith("5"))){
							patchStatusPP=0;
						}
					}else{
						patchStatusPP=0;
					}
				}else{
					patchStatusPP=0;
				}
			}
			list.get(i).put("patchStatusR",patchStatusR);
			list.get(i).put("patchStatusP",patchStatusP);
			list.get(i).put("patchStatusPP", patchStatusPP);
			//智能语音
			Integer patchStatusS=creditCheckDaoImp.selectCallSVoiceCount(appId);
			list.get(i).put("patchStatusS",patchStatusS);
			if(list.get(i)!=null&&!"".equals(list.get(i).get("lastOptUser"))&&list.get(i).get("lastOptUser")!=null){
			//最后一次操作人赋值为中文
			userMap.put("userCode",list.get(i).get("lastOptUser"));
			Map<String,Object> apUserMap=bizInpApplDao.selectApUserByUuIdUserCode(userMap);
			if(apUserMap!=null){
			String userName=apUserMap.get("userName").toString();
			list.get(i).put("lastOptUser",userName);
			}
		}
		}
		}
		return list;
	}

	@Override
	public List<Map<String,Object>> findZxExamineOperationPerson(Map paramMap) {
		Map<String,Object> paramSevericeMap=new HashMap<String,Object>();
		paramSevericeMap.put("orgLevel", "5");// 易达金组orgLevel = 5
		paramSevericeMap.put("status", "1");// 启用状态 1启用 0停用
		//roleCode//角色代码 （L1、L2、L3 ） 审批。。。
		List<String> roleCodes=new ArrayList<String>();
		roleCodes.add("L1");
		roleCodes.add("L2");
		roleCodes.add("L3");
		paramSevericeMap.put("roleCodes", roleCodes);
		List<Map<String,Object>>listApUser=	bizInpApplDao.selectListApUserByListUserId(paramSevericeMap);
		if(!listApUser.isEmpty()){
		    return listApUser;
		}else{
			return null;
		}
	}

	@Override
	public void updateZxApplyYdjHangUp(Map paramMap) {
		try {
		List<Map<String, Object>> listAppIdMap=new ArrayList<Map<String, Object>>();
		Map<String, Object> selectMap = new HashMap<String, Object>();
		selectMap.put("listAppId", paramMap.get("listAppId"));
		selectMap.put("currNode", "02");
		listAppIdMap=bizInpApplDao.selectAllotApplyByMapLikeListAppId(selectMap);
		Map<String,Object> userMap=new HashMap<String,Object>();//全流程记录
		userMap.put("userCode", paramMap.get("userCode").toString());
		Map<String,Object> apUserMap=bizInpApplDao.selectApUserByUuIdUserCode(userMap);
		String userCode=apUserMap.get("userCode").toString();
		String userName=apUserMap.get("userName").toString();
		for (Map<String, Object> map : listAppIdMap) {
			ApplyLifeCicle a=new ApplyLifeCicle();
			a.setAppId(map.get("appId").toString());
			if(apUserMap!=null){
			a.setOperateDesc(userName+"将申请件挂起到征信挂起队列");
			if(!"".equals(userCode)){
				a.setOperater(userName+"-"+userCode);
				a.setCrtUser(userCode);
			}else{
				a.setOperater("systemZx");
				a.setCrtUser("systemZx");
			}
			}
			a.setOperateResult("完成");
			a.setCrtDate(new Date());
			a.setUuid(StringUtil.randomUUIDPlainString());
			applyLifeCicleDao.addApplyLifeCicle(a);
			//add by qingfeng.xiu 20190108 cause:插入备注表----------------
			if(!"".equals(paramMap.get("guaQiBeiZhu").toString().trim())){
				Map<String, Object> remarkMap = new HashMap<String, Object>();
				remarkMap.put("remarkInfo", paramMap.get("guaQiBeiZhu"));
				remarkMap.put("remarkId",UUID.randomUUID().toString().replace("-", ""));
				remarkMap.put("remarkUser",userCode);
				remarkMap.put("remarkTime", new Date());
				remarkMap.put("appId", map.get("appId"));
				remarkMap.put("currNode", "02");
				revCompInfoDao.insertRemark(remarkMap);
			}
			//------------------------------------------------------------
		}
		paramMap.put("listAppId",listAppIdMap);//放入真正挂起的流水号 
		bizInpApplDao.updateZxApplyYdjHangUpByAppIds(paramMap);
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}

	@Override
	public Map<String, Object> updateCreditSubmitZx(CreditCheck creditCheck, OpasTelcheckResult telcheckResult,Map<String, Object> streamMap) {
		Map<String, Object> htmlMap = new HashMap<String, Object>();
		String msg = "调流前失败.提交失败。";
		boolean needCallingProcess = false;// 是否调流 的判断依据
		String tkAppId = "";
		String ydjFlag_ts = "2";
		AllotApplyAllot allotApplyAllot = null;
		// 征审合一二次决策结果
		boolean lean = streamMap.get("jbpm") == null ? false : (boolean) streamMap.get("jbpm");
		
			String ydjFlag = streamMap.get("ydjFlag").toString();
			String currAppId = creditCheck.getCheck_number();// 申请件的appId
			String currAppIdFront = currAppId.substring(0, 15);// 流水号 前十五位
			Map<String, Object> judgeMap = new HashMap<String, Object>();
			judgeMap.put("appId", currAppId);
			judgeMap.put("currNode", creditCheck.getCheck_currNode());// 代表征信/智能语音提交操作
			judgeMap.put("ydjFlag", ydjFlag);
			judgeMap.put("userId", streamMap.get("userCode").toString());
			// ==
			Map<String, Object> needMap = bizInpApplDao.selectAllotApplyCurrStatusByMap(judgeMap);
			if (needMap != null) {
			try {
			// 全流程记录
			ApplyLifeCicle a = new ApplyLifeCicle();
			Map<String, Object> userMap = new HashMap<String, Object>();
			userMap.put("userCode", streamMap.get("userCode").toString());
			Map<String, Object> apUserMap = bizInpApplDao.selectApUserByUuIdUserCode(userMap);

			a.setAppId(currAppId);
			if (apUserMap != null) {
				String userCode = apUserMap.get("userCode").toString();
				String userName = apUserMap.get("userName").toString();
				if ("2".equals(creditCheck.getCheck_meuoFlag())) {// 征审合一
					a.setOperateDesc(userName + "进行征信(征审合一)提交");
				} else {
					a.setOperateDesc(userName + "进行征信提交");
				}
				if (!"".equals(userCode)) {
					a.setOperater(userName + "-" + userCode);
					a.setCrtUser(userCode);
				} else {
					a.setOperater("systemZx");
					a.setCrtUser("systemZx");
				}
			}
			a.setOperateResult("完成");
			a.setCrtDate(new Date());
			a.setUuid(StringUtil.randomUUIDPlainString());
			applyLifeCicleDao.addApplyLifeCicle(a);
			
				String laoJanFlag = "";
				if (needMap.get("LAOJIANFLAG") != null) {
					laoJanFlag = needMap.get("LAOJIANFLAG").toString();// 捞件标识 判断这个申请件是否是捞件捞回来了的
				}
				if (ydjFlag.equals("1")) {// 易达金
					if (!laoJanFlag.equals("01") && !laoJanFlag.equals("02")) {// 01 02 指的是 只捞回来一个件的情况
						if (streamMap.get("matchingCardFlag").toString().equals("0")) {// 0代表非套卡
							// 这个申请件需要调流
							needCallingProcess = true;
							ydjFlag_ts = ydjFlag;
						} else {// 有套卡
							String currAppIdLast = currAppId.substring(15);// 流水号最后一位
							if (currAppIdLast.equals("1")) {
								currAppIdLast = "2";
								//ydjFlag_ts = "2";
							} else if (currAppIdLast.equals("2")) {
								currAppIdLast = "1";
								//ydjFlag_ts = "1";
							}
							if("1".equals(streamMap.get("matchingCardFlag"))){//易达金套卡
								ydjFlag_ts = "2";//调标准卡决策
							}else if("2".equals(streamMap.get("matchingCardFlag"))){//易达金主卡
								ydjFlag_ts = "1";//调易达金决策
							}
							String matchingAppId = currAppIdFront + currAppIdLast;
							allotApplyAllot = allotApplyAllotDao.selectByPrimaryKey(matchingAppId);
							if (allotApplyAllot == null) {
								throw new RuntimeException("套卡不存在。提交失败。");
							}
							String synFlag = allotApplyAllot.getSynFlag();// 套卡提交标识
							if (synFlag.equals("1")) {
								// 这个申请件 需要调流
								needCallingProcess = true;
							} else {
								// 这个申请件不需要 调流 ，needCallingProcess状态不变
							}
							// add xzg
							tkAppId = matchingAppId;
						}
					} else {
						// 这个申请件 需要调流
						needCallingProcess = true;
						if("1".equals(streamMap.get("matchingCardFlag"))){//易达金套卡
							ydjFlag_ts = "2";//调标准卡决策
						}else if("2".equals(streamMap.get("matchingCardFlag"))){//易达金主卡
							ydjFlag_ts = "1";//调易达金决策
						}else{
							ydjFlag_ts = "1";//调易达金决策
						}
					}
					// 申请件分配表数据记录历史表中
					creditCheckDaoImp.insertCopyOpasAllotApplyAllot(currAppId);
					creditCheck.setCheck_synFlag("1");// 修改当前申请件的 提交状态
					// 征审合一判断,修改节点，xzg add
					if ("2".equals(creditCheck.getCheck_meuoFlag())) {
						creditCheck.setCheck_currNode("03");
						creditCheck.setCheck_delStatus("0");
						creditCheck.setCheck_meuoFlag("2");
						creditCheck.setCheck_USER_DATE(new Date());
						creditCheck.setCheck_lastOU(streamMap.get("userCode").toString());
						if (needCallingProcess) {
							creditCheck.setCheck_synFlag("0");
						}
						// 若为二次决策批准或拒绝，则该件直接提交到待归档
						if (lean) {
							creditCheck.setCheck_currNode("04");
							creditCheck.setCheck_delStatus("1");
							creditCheck.setCheck_synFlag("0");
							// 若当前件存在套卡，而且套卡第一个提交的卡被拒绝，则将其征信结论记录到另一张卡中
							TelcheckResult result = telcheckAddnoteDao.selectTelcheckResultByAppId(currAppId);
							if(result!=null&&!"".equals(tkAppId)&&allotApplyAllot!=null
									&&"0".equals(allotApplyAllot.getSynFlag())){//排除无套卡和单独捞件情况以及套卡已经提交
								TelcheckResult result_tk = telcheckAddnoteDao.selectTelcheckResultByAppId(tkAppId);
								if(result_tk==null){//为另一个卡保存征信信息过件码
									result.setAppId(tkAppId);
									result.setAutoId(StringUtil.randomUUIDPlainString());
									if("1".equals(ydjFlag_ts)){//易达金
										result.setBlockCode("900Y");
									}else{//标识卡及易达金套卡
										result.setBlockCode("L900");
									}
									telcheckAddnoteDao.saveTelcheckResult(result);
								}
							}
						}
						creditCheck.setCheck_zshy_inner_check(streamMap.get("zshyInnerCheck").toString());
					}
					// 修改申请件分配表
					creditCheckDaoImp.updateCreditCheck(creditCheck);
					// 添加征信电核结果信息表 ( 提交前 保存操作插入 此处不插入)
					// creditCheckDaoImp.insertOpasTelcheckResult(telcheckResult);
				} else {// 标准卡
						// 这个申请件 需要调流
					needCallingProcess = true;
					// 申请件分配表数据记录历史表中
					creditCheckDaoImp.insertCopyOpasAllotApplyAllot(currAppId);
					// 征审合一判断,修改节点，xzg add
					if ("2".equals(creditCheck.getCheck_meuoFlag())) {
						creditCheck.setCheck_currNode("03");
						creditCheck.setCheck_delStatus("0");
						creditCheck.setCheck_meuoFlag("2");
						creditCheck.setCheck_USER_DATE(new Date());
						creditCheck.setCheck_lastOU(streamMap.get("userCode").toString());
						// 若为二次决策批准或拒绝，则该件直接提交到待归档
						if (lean) {
							creditCheck.setCheck_currNode("04");
							creditCheck.setCheck_synFlag("0");
							creditCheck.setCheck_delStatus("1");
						}
						creditCheck.setCheck_zshy_inner_check(streamMap.get("zshyInnerCheck").toString());
					}
					// 修改申请件分配表
					creditCheckDaoImp.updateCreditCheck(creditCheck);
				}

				// 征审合一判断，xzg add
				if ("2".equals(creditCheck.getCheck_meuoFlag())) {
					htmlMap.put("flag", "2");
					// 判断当前件是否有套卡
					htmlMap.put("tkAppId", tkAppId);
					if (needCallingProcess || lean) {
						// 副卡
						if (allotApplyAllot != null) {
							allotApplyAllot.setSynFlag("0");
							allotApplyAllot.setCheck_meuoFlag("2");
							allotApplyAllot.setUserDate(new Date());
							allotApplyAllot.setCurrNode("03");
							allotApplyAllot.setDelStatus("0");
							allotApplyAllot.setLastOptUser(streamMap.get("userCode").toString());
							// 若为二次决策批准或拒绝，则该件直接提交到待归档
							if (lean) {
								allotApplyAllot.setCurrNode("04");
								allotApplyAllot.setDelStatus("1");
								allotApplyAllot.setSynFlag("0");
								allotApplyAllot.setCheck_meuoFlag("2");
								allotApplyAllot.setLastOptUser(streamMap.get("userCode").toString());
								saveBizApproval(allotApplyAllot.getAppId(), ydjFlag_ts,streamMap);
							}
							//allotApplyAllot.setZshyInnerCheck(streamMap.get("zshyInnerCheck").toString());
							allotApplyAllotDao.updateByPrimaryKeySelective(allotApplyAllot);
							Map<String,String> map = new HashMap<String,String>();
							map.put("appId", allotApplyAllot.getAppId());
							allotApplyAllotHisDao.insertCopyOpasAllotToAllotHis(map);
						}
					} else {
						// 判断当前件是否有套卡
						//htmlMap.put("tkAppId", tkAppId);
						// htmlMap.put("allotApplyAllotFk", allotApplyAllot);
						creditCheckDaoImp.insertCopyOpasAllotApplyAllot(currAppId);
					}
					// 增加流水记录
					// creditCheckDaoImp.insertCopyOpasAllotToAllotHis(creditCheck);
					// 若二次决策为批准或拒绝，则直接提交流程到待归档
					if (lean) {
						saveBizApproval(currAppId, ydjFlag_ts,streamMap);
						streamMap.put("isBack", "3");
						lineNewUrlClient(streamMap);
					}
					return htmlMap;
				}
				// ========end===========
				// 调流isBack=2,征信；3征审
				streamMap.put("isBack", "2");
				if (needCallingProcess) {
					/*
					 * if(ydjFlag.equals("1")){//易达金 Map<String,Object>
					 * updateMap=new HashMap<String,Object>();
					 * updateMap.put("appId", currAppIdFront);
					 * updateMap.put("synFlag", "0");//同步提交状态 都变为0 //根据流水号前15位
					 * 模糊修改 bizInpApplDao.updateAllotByMapLikeAppId(updateMap);
					 * }
					 */
					msg = "调流前成功,工作流异常.提交失败。";
					lineNewUrlClient(streamMap);
				}
				htmlMap.put("msg", "征信提交成功。");
			  } catch (Exception e) {
				throw new RuntimeException("系统繁忙，请稍后再试。");
			  }
			} else {
				throw new RuntimeException("此申请件已不存在，请重新刷新当前队列页面。提交失败。");
			}
		
		return htmlMap;
	}
	/**
	 * 数据保存
	 */
	public void saveBizApproval(String appId,String flag,Map streamMap) throws Exception{
		Date sysDeciTime = new Date();
		String decisionResult = "",decisionDesp="",gjm="",refuseCode1="";
		//利率  最低还款额 免息还款期
		String RATE_NAME = "",REPAY_RATIO_VALUE ="",REPAY_FREE_VALUE ="";
		//wdb
		String policyCode1="",policyCode2="",policyCode3="",fico_card="";
		String ydjFlag=(String) streamMap.get("ydjFlag");
		String matchingCardFlag=(String) streamMap.get("matchingCardFlag");
		FicoSdBlaze fico_bzk = null;
		FicoYdjBlaze fico_ydj = null;
		// 根据appId获得征信电核结果信息，获得过件码
		TelcheckResult result = bizApprovalDao.selectTelcheckResultByAppId(appId);
		if (result != null) {
			gjm = result.getBlockCode();
		}
		BizInpApplC1 appl = bizInpApplC1Dao.selectBizInpApplC1ByAppId(appId);
		if("2".equals(flag)){
			fico_bzk = (FicoSdBlaze)ficoSdBlazeDao.selectByAppId(appId);
			if(fico_bzk!=null){
				sysDeciTime = fico_bzk.getCrtTime();
				decisionResult = fico_bzk.getDecisionResult();
				decisionDesp = fico_bzk.getDecisionDesp();
				refuseCode1 = fico_bzk.getJujCd();
				//RATE_NAME = fico_bzk.getCifLabel();
				RATE_NAME=fico_bzk.getRateName();
				REPAY_RATIO_VALUE = fico_bzk.getLowestPayAmtLabel();
				REPAY_FREE_VALUE = fico_bzk.getFreePayTermLabel();
				policyCode1=fico_bzk.getPolicyCode1();
				policyCode2=fico_bzk.getGjmCd();
				policyCode3=fico_bzk.getPolicyCode3();
				//FICO 卡产品
				fico_card = fico_bzk.getCpRst();
			}
		}else{
			fico_ydj = (FicoYdjBlaze)ficoYdjBlazeDao.selectByAppId(appId);
			if(fico_ydj!=null){
				sysDeciTime = fico_ydj.getCrtTime();
				decisionResult = fico_ydj.getDecisionResult();
				decisionDesp = fico_ydj.getDecisionDesp();
				refuseCode1 = fico_ydj.getRejectCd();
				//RATE_NAME = fico_ydj.getCifLabel();
				RATE_NAME=fico_ydj.getRateName();
				REPAY_RATIO_VALUE = fico_ydj.getLowestPayAmtLabel();
				REPAY_FREE_VALUE = fico_ydj.getFreePayTermLabel();
				policyCode1=fico_ydj.getPolicyCd();
				fico_card = fico_ydj.getProductType();
			}
		}
		
		/*String approvalName = "system";
		if(streamMap.get("userCode")!=null && !"".equals(streamMap.get("userCode").toString())){
			ApUser apUser = apUserDao.queryApUserByUserCode(streamMap.get("userCode").toString());
			approvalName = apUser.getUserName();
		}*/
		//新添加  wdb   
		//利率差异化  修改直接取决策值 不需要匹配
	    /*SysparmRateConf sysparmRateConf = new SysparmRateConf();
		sysparmRateConf.setCustLevelTag(RATE_NAME);
		if(null != RATE_NAME && !"".equals(RATE_NAME)){
			sysparmRateConf = sysparmRateConfDao.querySysparmRateConf5(sysparmRateConf);
		}
		String rateCode = (sysparmRateConf==null?"":sysparmRateConf.getRateCode());*/
		//最低还款额比例
		SysparmRepayLimitConf sysparmRepayLimitConf = new SysparmRepayLimitConf();
		sysparmRepayLimitConf.setCustLevelTag(REPAY_RATIO_VALUE);
		if(null != REPAY_RATIO_VALUE && !"".equals(REPAY_RATIO_VALUE)){
			sysparmRepayLimitConf = sysparmRepayLimitConfDao.querySysparmRepayLimitConf5(sysparmRepayLimitConf);
		}
		String repayRationCode = sysparmRepayLimitConf==null?"":sysparmRepayLimitConf.getRepayRatioCode();
		//免息还款期免息还款期
		SysparmRepayFreeTermConf sysparmRepayFreeTermConf = new SysparmRepayFreeTermConf();
		sysparmRepayFreeTermConf.setCustLevelTag(REPAY_FREE_VALUE);
		if(null != REPAY_FREE_VALUE && !"".equals(REPAY_FREE_VALUE)){
			sysparmRepayFreeTermConf = sysparmRepayFreeTermConfDao.querySysparmRepayFreeTermConf5(sysparmRepayFreeTermConf);
		}
		String repayFreeCode = sysparmRepayFreeTermConf==null?"":sysparmRepayFreeTermConf.getRepayFreeCode();
		//自动核准还是自动拒绝  
		String zshyResult = (String) (streamMap.get("zshyResult") == null ? false : (String) streamMap.get("zshyResult"));
		BizApproval bizApproval = new BizApproval();
		bizApproval.setAutoId(StringUtil.randomUUIDPlainString());
		bizApproval.setAppId(appId);
		bizApproval.setApplyCard(appl.getAppProd());
		bizApproval.setSysDecisionTime(sysDeciTime);// 系统决策时间
		//bizApproval.setMastercardDecresult(decisionResult);
		bizApproval.setMastercardApprovereuslt(decisionDesp);
		bizApproval.setSysCreditProduce("");
		bizApproval.setChildcardDecresult("");// 附属卡决策结果
		bizApproval.setChildcardApprovereuslt("");// 附属卡审批结果描述
		bizApproval.setCreditResult(gjm);// 征信结果[过件码]
		bizApproval.setCreditRefuseReason("0");//存放backFlag
		bizApproval.setRefuseCode1(refuseCode1);
		//bizApproval.setApprover(streamMap.get("userCode")==null?"":streamMap.get("userCode").toString());
		//bizApproval.setApproverName(approvalName);
		bizApproval.setSubmitHighlevleTime(new Date());
		bizApproval.setApproveTime(new Date());
		bizApproval.setYdjFlag(flag);/* 1为易达金 2标准卡 */
		bizApproval.setApplyerName(appl.getC1Cname());
		//修改   wdb 
		bizApproval.setApprover("system");
		bizApproval.setApproverName("系统决策");
		//c1 卡产品
		String c1_card = appl.getAppProd();
		CardProduct cardProduct = new CardProduct(); 
		if("1".equals(flag)){
			bizApproval.setPolicyCode1(policyCode1);
		}else if("2".equals(flag)){
			bizApproval.setPolicyCode1(policyCode1);
			bizApproval.setPolicyCode2(policyCode2);
			bizApproval.setPolicyCode3(policyCode3);
		}
		if("2".equals(zshyResult)){//自动拒绝
			bizApproval.setApproveResult("0");// 审批结论
			bizApproval.setMemo("二次决策征信自动拒绝");
			bizApproval.setMastercardDecresult("自动拒绝");
			if("1".equals(ydjFlag)&&"1".equals(matchingCardFlag)){//易达金配发标准卡统一修改拒绝码
				bizApproval.setRefuseCode1("D700");
			}
		}else if("3".equals(zshyResult)){//自动核准
			bizApproval.setApproveResult("1");// 审批结论
			bizApproval.setApproveCreditProduct(fico_card);
			bizApproval.setMemo("二次决策征信自动核准");
			bizApproval.setMastercardDecresult("自动核准");
			
			if("1".equals(flag)){//易达金卡
				bizApproval.setApproveCreditLimit(fico_ydj.getProposedLmt().longValue());
				//年费卡版面
				bizApproval.setYearFeeDerateType(appl.getAppAcctyp());
				bizApproval.setCardFaceCode(appl.getC4Vercode());
			}else if("2".equals(flag)){//标准卡 及易达金套卡
				bizApproval.setApproveCreditLimit(fico_bzk.getProposedLmt().longValue());
				if(c1_card.equals(fico_card)){
					bizApproval.setYearFeeDerateType(appl.getAppAcctyp());
					bizApproval.setCardFaceCode(appl.getC4Vercode());
				}else {
					if(fico_card!=null&&!"".equals(fico_card)){
						cardProduct = cardProductDao.queryProductCode(fico_card); 
						if(cardProduct!=null){
							if(("0025".equals(c1_card) && "0020".equals(fico_card))||("0041".equals(c1_card) && "0040".equals(fico_card))){
								bizApproval.setYearFeeDerateType("14");
								bizApproval.setCardFaceCode(cardProduct.getProductFace());
							}else if(("0038".equals(c1_card) || "0088".equals(c1_card)) && ("0037".equals(fico_card) || "0087".equals(fico_card))){
								bizApproval.setYearFeeDerateType(cardProduct.getYearFeeDerateType());
								bizApproval.setCardFaceCode(appl.getC4Vercode());
							}else if("0092".equals(c1_card) || "0073".equals(c1_card)){
								bizApproval.setYearFeeDerateType(cardProduct.getYearFeeDerateType());
								bizApproval.setCardFaceCode("SM01");
							}else{	
								bizApproval.setYearFeeDerateType(cardProduct.getYearFeeDerateType());
								bizApproval.setCardFaceCode(cardProduct.getProductFace());
							}
						}
					}else{
						bizApproval.setYearFeeDerateType(appl.getAppAcctyp());
						bizApproval.setCardFaceCode(appl.getC4Vercode());
					}
				}
			}
		}
		bizApproval.setRateValue(RATE_NAME);//利率
		bizApproval.setRepayRationValue(repayRationCode);//最低还款额
		bizApproval.setRepayFreeValue(repayFreeCode);//免息还款期
		bizApproval.setInitSaveFlag("1");
		BizApprovalHis bizApprovalHis = copyApprovalRes(bizApproval);
		BizApproval app = bizApprovalDao.selectByAppId(appId);
		if (app == null) {
			bizApprovalDao.insertSelective(bizApproval);
		} else {
			bizApproval.setAutoId(app.getAutoId());
			bizApproval.setLaojianNum((app==null||app.getLaojianNum()==null)?"0":app.getLaojianNum());
			bizApprovalDao.updateByPrimaryKeySelective(bizApproval);
		}
		bizApprovalHis.setLaojianNum((app==null||app.getLaojianNum()==null)?"0":app.getLaojianNum());
		bizApprovalHisDao.insertSelective(bizApprovalHis);
		//判断当前卡是否是标卡的套卡
		if("2".equals(flag) && "2".equals(appl.getMatchingCardFlag())){
			BizApproval bizApproval_fk = bizApproval;
			String appId_fk = appId.substring(0, appId.length()-1)+"2";//标卡的套卡最后一位为2
			String RATE_NAME_fk = "",REPAY_RATIO_VALUE_fk ="",REPAY_FREE_VALUE_fk ="",refuseCode1_fk="";
			String policyCode1_fk="",policyCode2_fk="",policyCode3_fk="";
			Date sysDeciTime_fk = new Date();
			Object object_fk = ficoSdBlazeDao.selectSystemDecisionByAppId(appId_fk);
			FicoSdBlaze fico_bzk_fk = (FicoSdBlaze)object_fk;
			if(fico_bzk_fk!=null){
				sysDeciTime_fk = fico_bzk_fk.getCrtTime();
				RATE_NAME_fk = fico_bzk_fk.getRateName();
				REPAY_RATIO_VALUE_fk = fico_bzk_fk.getRepayRationValue();
				REPAY_FREE_VALUE_fk = fico_bzk_fk.getRepayFreeValue();
				refuseCode1_fk = fico_bzk_fk.getJujCd();
				policyCode1_fk=fico_bzk_fk.getPolicyCode1();
				policyCode2_fk=fico_bzk_fk.getGjmCd();
				policyCode3_fk=fico_bzk_fk.getPolicyCode3();
			}
			// 查询主卡进件信息
			BizInpApplC1 appl_fk = bizInpApplC1Dao.selectBizInpApplC1ByAppId(appId_fk);
			
			bizApproval_fk.setAutoId(StringUtil.randomUUIDPlainString());
			bizApproval_fk.setAppId(appId_fk);
			bizApproval_fk.setApplyCard(appl_fk!=null?appl_fk.getAppProd():"");
			bizApproval_fk.setSysDecisionTime(sysDeciTime_fk);// 系统决策时间
			bizApproval_fk.setRefuseCode1(refuseCode1_fk);
			bizApproval_fk.setMastercardApprovereuslt(fico_bzk_fk.getDecisionDesp());
			//c1 卡产品  wdb
			String c1_card_fk = appl_fk.getAppProd();
			CardProduct cardProduct_fk=new CardProduct();
			//FICO 卡产品
			String fico_card_fk = fico_bzk_fk.getCpRst();
			if("2".equals(zshyResult)){
				bizApproval_fk.setMemo("二次决策征信自动拒绝");
				bizApproval_fk.setApproveResult("0");
				bizApproval_fk.setMastercardDecresult("自动拒绝");
			}else if("3".equals(zshyResult)){
				bizApproval_fk.setMemo("二次决策征信自动核准");
				bizApproval_fk.setApproveResult("1");
				bizApproval_fk.setMastercardDecresult("自动核准");
				bizApproval_fk.setApproveCreditLimit(fico_bzk_fk.getProposedLmt().longValue());
				
				bizApproval_fk.setApproveCreditProduct(fico_card_fk);
				if(c1_card_fk.equals(fico_card_fk)){
					bizApproval_fk.setYearFeeDerateType(appl_fk.getAppAcctyp());
					bizApproval_fk.setCardFaceCode(appl_fk.getC4Vercode());
				}else{
					if(fico_card_fk!=null&&!"".equals(fico_card_fk)){
						cardProduct_fk = cardProductDao.queryProductCode(fico_card_fk); 
						if(cardProduct_fk!=null){
							if(("0025".equals(c1_card_fk) && "0020".equals(fico_card_fk))||("0041".equals(c1_card_fk) && "0040".equals(fico_card_fk))){
								bizApproval_fk.setYearFeeDerateType("14");
								bizApproval_fk.setCardFaceCode(cardProduct_fk.getProductFace());
							}else if(("0038".equals(c1_card_fk) || "0088".equals(c1_card_fk)) && ("0037".equals(fico_card_fk) || "0087".equals(fico_card_fk))){
								bizApproval_fk.setYearFeeDerateType(cardProduct_fk.getYearFeeDerateType());
								bizApproval_fk.setCardFaceCode(appl.getC4Vercode());
							}else if("0092".equals(c1_card_fk) || "0073".equals(c1_card_fk)){
								bizApproval_fk.setYearFeeDerateType(cardProduct_fk.getYearFeeDerateType());
								bizApproval_fk.setCardFaceCode("SM01");
							}else{	
								bizApproval_fk.setYearFeeDerateType(cardProduct_fk.getYearFeeDerateType());
								bizApproval_fk.setCardFaceCode(cardProduct_fk.getProductFace());
							}
						}
					}else{
						bizApproval_fk.setYearFeeDerateType(appl_fk.getAppAcctyp());
						bizApproval_fk.setCardFaceCode(appl_fk.getC4Vercode());
					}
				}
			}
			bizApproval_fk.setPolicyCode1(policyCode1_fk);
			bizApproval_fk.setPolicyCode2(policyCode2_fk);
			bizApproval_fk.setPolicyCode3(policyCode3_fk);
			bizApproval_fk.setRateValue(RATE_NAME_fk);//利率
			bizApproval_fk.setRepayRationValue(REPAY_RATIO_VALUE_fk);//最低还款额
			bizApproval_fk.setRepayFreeValue(REPAY_FREE_VALUE_fk);//免息还款期
			bizApproval_fk.setInitSaveFlag("1");
			BizApprovalHis bizApprovalHis_fk = copyApprovalRes(bizApproval_fk);
			BizApproval app_fk = bizApprovalDao.selectByAppId(appId_fk);
			if (app_fk == null) {
				bizApprovalDao.insertSelective(bizApproval_fk);
			} else {
				bizApproval_fk.setAutoId(app_fk.getAutoId());
				bizApproval_fk.setLaojianNum((app_fk==null||app_fk.getLaojianNum()==null)?"0":app_fk.getLaojianNum());
				bizApprovalDao.updateByPrimaryKeySelective(bizApproval_fk);
			}
			bizApprovalHis_fk.setLaojianNum((app_fk==null||app_fk.getLaojianNum()==null)?"0":app_fk.getLaojianNum());
			bizApprovalHisDao.insertSelective(bizApprovalHis_fk);
		}
	}
	public BizApprovalHis copyApprovalRes(BizApproval bizApproval){
		BizApprovalHis bizApprovalHis = new BizApprovalHis();
		
		bizApprovalHis.setAutoId(StringUtil.randomUUIDPlainString());
		bizApprovalHis.setAppId(bizApproval.getAppId());
		bizApprovalHis.setRefuseChildcard(bizApproval.getRefuseChildcard());// 拒绝附属卡
		bizApprovalHis.setPreApprovelimit(bizApproval.getPreApprovelimit());// 预审批额度
		bizApprovalHis.setApplyerName(bizApproval.getApplyerName());
		bizApprovalHis.setApplyCard(bizApproval.getApplyCard());
		bizApprovalHis.setIsAgreeDegrade(bizApproval.getIsAgreeDegrade());
		bizApprovalHis.setSysDecisionTime(bizApproval.getSysDecisionTime());// 系统决策时间
		bizApprovalHis.setMastercardDecresult(bizApproval.getMastercardDecresult());
		bizApprovalHis.setMastercardApprovereuslt(bizApproval.getMastercardApprovereuslt());
		bizApprovalHis.setSysCreditProduce(bizApproval.getSysCreditProduce());
		bizApprovalHis.setChildcardDecresult(bizApproval.getChildcardDecresult());// 附属卡决策结果
		bizApprovalHis.setChildcardApprovereuslt(bizApproval.getChildcardApprovereuslt());// 附属卡审批结果描述
		bizApprovalHis.setCreditResult(bizApproval.getCreditResult());// 征信结果[过件码]
		bizApprovalHis.setCreditRefuseReason(bizApproval.getCreditRefuseReason());//存放backFlag
		bizApprovalHis.setWarninglistResult(bizApproval.getWarninglistResult());// WARNINGLIST结果
		bizApprovalHis.setApproveResult(bizApproval.getApproveResult());// 审批结论
		bizApprovalHis.setApproveCreditLimit(bizApproval.getApproveCreditLimit());
		bizApprovalHis.setApproveCreditProduct(bizApproval.getApproveCreditProduct());
		bizApprovalHis.setManualLimit(bizApproval.getManualLimit());// 调整额度
		bizApprovalHis.setPolicyCode1(bizApproval.getPolicyCode1());
		bizApprovalHis.setPolicyCode2(bizApproval.getPolicyCode2());
		bizApprovalHis.setPolicyCode3(bizApproval.getPolicyCode3());
		bizApprovalHis.setViolateCode1(bizApproval.getViolateCode1());
		bizApprovalHis.setViolateCode2(bizApproval.getViolateCode2());
		bizApprovalHis.setViolateCode3(bizApproval.getViolateCode3());
		bizApprovalHis.setMemo(bizApproval.getMemo());
		bizApprovalHis.setRefuseCode1(bizApproval.getRefuseCode1());
		bizApprovalHis.setRefuseCode2(bizApproval.getRefuseCode2());
		bizApprovalHis.setRefuseCode3(bizApproval.getRefuseCode3());
		bizApprovalHis.setApprover(bizApproval.getApprover());
		bizApprovalHis.setApproverName(bizApproval.getApproverName());
		//bizApprovalHis.setHighlevleApprover(bizApproval.getHighlevleApprover());
		//bizApprovalHis.setHighlevleApprovetime(bizApproval.getHighlevleApprovetime());
		bizApprovalHis.setSubmitHighlevleTime(bizApproval.getSubmitHighlevleTime());
		bizApprovalHis.setApproveTime(bizApproval.getApproveTime());
		bizApprovalHis.setCurrOptGroup(bizApproval.getCurrOptGroup());//当前操作组
		bizApprovalHis.setIdType(bizApproval.getIdType());
		bizApprovalHis.setIdNo(bizApproval.getIdNo());
		bizApprovalHis.setYdjFlag(bizApproval.getYdjFlag());/* 1为易达金 2标准卡 */
		bizApprovalHis.setYearFeeDerateType(bizApproval.getYearFeeDerateType());
		bizApprovalHis.setCardFaceCode(bizApproval.getCardFaceCode());
		return bizApprovalHis;
	}
	public AllotApplyAllotHis setAllotApplyAllotHis(AllotApplyAllot allot) throws Exception {
		AllotApplyAllotHis record = new AllotApplyAllotHis();
		record.setId(StringUtil.randomUUIDPlainString());
		record.setAppId(allot.getAppId());
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
		return record;
	}
	/**
	 * 调用流程接口
	 * 根据正确参数  征信、 审批工作流调用
	 * @throws Exception
	 */
	public String lineNewUrlClient(Map<String, Object> streamMap) throws Exception {
		String msg="";
		Map<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("appId", streamMap.get("appId").toString());
		Map<String, Object> needMap = bizInpApplDao.selectProcessIdByAppId(paramMap);
		if(needMap!=null){
			String processId= needMap.get("PROCESSID")!=null?needMap.get("PROCESSID").toString():"";
			String nodeId= streamMap.get("nodeId").toString();//流节点
			String processIp= streamMap.get("process_IP").toString();//服务ip地址
			if(processId!=null&&!"".equals(processId)){
			//	Client client = new Client(new URL("http://192.168.1.24:9091/topbpm_test/services/BranchBankSearchServiceI?wsdl"));
				String intputXml = "";
				if(streamMap.get("specialExamineUser")!=null&&!streamMap.get("specialExamineUser").equals("")){
					String specialExamineUser= streamMap.get("specialExamineUser").toString();//易达金提交转特定审批人
					intputXml = "{'processId':'" + processId + "','nodeId':'" + nodeId + "','specialExamineUser':'" + specialExamineUser + 
							"','isBack':'" + streamMap.get("isBack")+"'}";
				}else{
					intputXml = "{'processId':'" + processId + "','nodeId':'" + nodeId + 
							"','isBack':'" + streamMap.get("isBack")+"'}";
				}
//				Object[] obj = new Object[] { intputXml.toString() };
//				Object[] result =null;
				String obj = intputXml.toString();
				String result = "";
//				Client client = null;
				try{
//					client = new Client(new URL("http://" + processIp+ "/topbpm_test/services/BranchBankSearchServiceI?wsdl"));
//					result = client.invoke("signal", obj);
					result = topbpmService.signal(obj).toString();
				}catch(Exception e){
					logger.error("回调工作流异常：{}", new Object[] { e.getMessage() }, e);
				}finally{
//					if (client != null) {
//						client.close();
//						client = null;
//					}
				}
//				if(result!=null&&result[0]!=null&&"1".equals(result[0].toString())){
				if(result!=null&&"1".equals(result)){
					msg="征信提交成功。";
				}else{
					throw  new RuntimeException("流异常，返回结果错误，提交失败。");
				}
				//System.out.println("single======" + result.toString());
				
			}else{
				msg="数据异常(processId不存在).提交失败。";
			//	htmlMap.put("msg", msg);
				throw  new RuntimeException(msg);//为了制造异常 使事物回滚;
			}
		}else{
			msg="数据异常(processId不存在).提交失败。";
			throw  new RuntimeException(msg);//为了制造异常 使事物回滚;
		}
		return msg;
	}
	@Override
	public int queryYDJFinishedCount(Map<String, Object> map) {
		String ydjFlag = (String) map.get("ydjFlag");
		int count1 = 0;
		int count2 = 0;
		int count3 = bizInpApplDao.queryYDJFinishedCount(map);
		if("1".equals(ydjFlag)){
			count1 = bizInpApplDao.queryFicoYdjCount(map); 
		}else{
			count2 = bizInpApplDao.queryFicoBzkCount(map); 
		}
		return count1+count2+count3;
	}
	@Override
	public int queryYDJUnfinishedCount(Map<String, Object> map) {
		return bizInpApplDao.queryYDJUnfinishedCount(map);
	}
	@Override
	public int queryYDJSub2Other(Map<String, Object> map) {
		return bizInpApplDao.queryYDJSub2Other(map);
	}


	@Override
	public Map<String,Object> updateZxApplyFinishedTakeBack(Map paramMap) {
		Map<String,Object> htmlMap=new HashMap<String,Object>();
		String msg="调流前失败.收回失败。";
		try {
		//状态由流根据 specialExamineUser中的数据进行相应修改   此方法不用了   bizInpApplDao.updateZxApplyFinishedTakeBackByListAppId(paramMap);
		List<Map<String,Object>> listAppIdMap=(List<Map<String,Object>>)paramMap.get("listAppId");
		String existMsg="申请件已不存在于当前队列：";
		boolean existNo=false;
		for (Map<String, Object> map : listAppIdMap) {
			map.put("delStatus", "0");
			map.put("currNode", "03");
		int currentNum= bizInpApplDao.selectApplyCurrentLinkCountByMap(map);
			if(currentNum<=0){//不存在
				existMsg+=map.get("appId").toString()+" ";
				existNo=true;
			}
		}
		if(existNo){
			htmlMap.put("msg", existMsg);
			return htmlMap;
		}
		String specialExamineUser=paramMap.get("specialExamineUser").toString();
		String nodeId=paramMap.get("nodeId").toString();
		String processIp= paramMap.get("process_IP").toString();//服务ip地址
		//==全流程记录
		 Map<String,Object> userMap=new HashMap<String,Object>();//全流程记录
		 userMap.put("userCode", paramMap.get("userId").toString());//前台封装的userId其实是userCode，数据库的userId是uuid
		 Map<String,Object> apUserMap=bizInpApplDao.selectApUserByUuIdUserCode(userMap);
		 String userCode=apUserMap.get("userCode").toString();
		 String userName=apUserMap.get("userName").toString();
		 Map<String, Object> judgeMap = new HashMap<String, Object>();
			judgeMap.put("judgeCurrNode", "03");// 代表（审批）对捞件情况  进行控制
			judgeMap.put("delStatus","5");//征信收回 时 修改del_status 为5 用于收回时隐藏已完成队列的数据用
		for (Map<String, Object> oneMap : listAppIdMap) {
			String currAppId=oneMap.get("appId").toString();//申请件的appId
			String currAppIdFront=currAppId.substring(0,15);//流水号 前十五位
			judgeMap.put("appId", currAppIdFront);
			String currAppIdLast=currAppId.substring(15);//流水号 最护一位
			AllotApplyAllot allotApplyAllot=allotApplyAllotDao.selectByPrimaryKey(currAppId);
			if(allotApplyAllot!=null){
				//将此流水号的在申请件分配表的信息插入到申请件分配历史表
				bizInpApplDao.insertCopyOpasAllotToAllotHis(oneMap);//插入当前卡
				//全流程记录
					ApplyLifeCicle a=new ApplyLifeCicle();
					a.setAppId(currAppId);
					if(apUserMap!=null){
					a.setOperateDesc(userName+"对申请件进行征信收回操作");
					if(!"".equals(userCode)){
						a.setOperater(userName+"-"+userCode);
						a.setCrtUser(userCode);
					}else{
						a.setOperater("systemZx");
						a.setCrtUser("systemZx");
					}
					}
					a.setOperateResult("完成");
					a.setCrtDate(new Date());
					a.setUuid(StringUtil.randomUUIDPlainString());
					try {
						applyLifeCicleDao.addApplyLifeCicle(a);
					} catch (Exception e) {
						throw new RuntimeException();
					}
				if(allotApplyAllot.getSynFlag().equals("1")){
					//不调流
					Map<String, Object> paraMapUpdateAllot=new HashMap<String,Object>();//存放 需要修改的分件表的数据信息
					//将  syn_flag 变为0    不调流 
					paraMapUpdateAllot.put("synFlag","0");
					paraMapUpdateAllot.put("appId",currAppId);
					bizInpApplDao.updateAllotApplyAllotByAppId(paraMapUpdateAllot);
					continue;
				}else{//syn_flag 0   调流
					if(!allotApplyAllot.getMatchingCardFlag().equals("0")){//套卡
						if(currAppIdLast.equals("1")){
							currAppIdLast="2";
						}else if(currAppIdLast.equals("2")){
							currAppIdLast="1";
						}
						String matchingAppId=currAppIdFront+currAppIdLast;
						AllotApplyAllot allotApplyAllotMatch=allotApplyAllotDao.selectByPrimaryKey(matchingAppId);//另一个 套卡
						if(allotApplyAllotMatch!=null){
							if(allotApplyAllotMatch.getSynFlag().equals("0")){
								Map<String, Object> paraMapUpdateAllot=new HashMap<String,Object>();//存放 需要修改的分件表的数据信息
								//将  syn_flag 变为1  
								paraMapUpdateAllot.put("synFlag","1");
								paraMapUpdateAllot.put("appId",matchingAppId);
								bizInpApplDao.updateAllotApplyAllotByAppId(paraMapUpdateAllot);
							}else{
								//调流
							}	
						}
					}else{//非套卡
						//调流
					}
				}
				bizInpApplDao.updateAllotByMapLikeAppId(judgeMap);
				//调流
				Map<String, Object> needMap = bizInpApplDao.selectProcessIdByAppId(oneMap);
				if(needMap!=null){
				String processId= needMap.get("PROCESSID")!=null?needMap.get("PROCESSID").toString():"";
				if(processId!=null&&!"".equals(processId)){
					msg="调流前成功,工作流异常.收回失败。";
					//Client client = new Client(new URL("http://192.168.1.24:9091/topbpm_test/services/BranchBankSearchServiceI?wsdl"));
//					Client client = null;
					String intputXml= "{'processId':'" + processId + "','nodeId':'" + nodeId +"','specialExamineUser':'" + specialExamineUser + "','isBack':'1'}";
//					Object[] obj = new Object[] { intputXml.toString() };
//					Object[] result = null;
					String obj = intputXml.toString();
					String result = "";
					try{
//						client = new Client(new URL("http://" + processIp+ "/topbpm_test/services/BranchBankSearchServiceI?wsdl"));
//						result = client.invoke("signal", obj);
						result = topbpmService.signal(obj).toString();
					}catch(Exception e){
						logger.error("回调工作流异常：{}", new Object[] { e.getMessage() }, e);
					}finally{
//						if (client != null) {
//							client.close();
//							client = null;
//						}
					}
//					if(result!=null&&result[0]!=null&&"1".equals(result[0].toString())){
					if(result!=null&&"1".equals(result)){
						msg="收回成功。";
					}else{
						throw  new RuntimeException("流异常，返回结果错误，收回失败。");
					}
				}else{
					msg="数据异常(流水号："+oneMap.get("appId")+"的processId不存在).收回失败。";
					throw  new RuntimeException(msg);//为了制造异常 使事物回滚;
				}
				}else{
					msg="数据异常(流水号："+oneMap.get("appId")+"的processId不存在).收回失败。";
					throw  new RuntimeException(msg);//为了制造异常 使事物回滚;
				}
			}
		}
		msg="收回成功。";
		} catch (Exception e) {
			throw  new RuntimeException(msg);//为了制造异常 使事物回滚;
		}
		htmlMap.put("msg", msg);
		return htmlMap;
		
	}
	@Override
	public void updateZxApplyYdjHangUpCancle(Map paramMap) {
		try {
		List<Map<String, Object>> listAppIdMap=new ArrayList<Map<String, Object>>();
		Map<String, Object> selectMap = new HashMap<String, Object>();
		selectMap.put("listAppId", paramMap.get("listAppId"));
		selectMap.put("currNode", "02");
		listAppIdMap=bizInpApplDao.selectAllotApplyByMapLikeListAppId(selectMap);
		paramMap.put("listAppId",listAppIdMap);//放入真正解挂的流水号 
		Map<String,Object> userMap=new HashMap<String,Object>();//全流程记录
		userMap.put("userCode", paramMap.get("userCode").toString());
		Map<String,Object> apUserMap=bizInpApplDao.selectApUserByUuIdUserCode(userMap);
		String userCode=apUserMap.get("userCode").toString();
		String userName=apUserMap.get("userName").toString();
		for (Map<String, Object> map : listAppIdMap) {
			ApplyLifeCicle a=new ApplyLifeCicle();
			a.setAppId(map.get("appId").toString());
			if(apUserMap!=null){
			String delStatus=map.get("delStatus").toString();
			    if("0".equals(delStatus)){//未完成
			    	 a.setOperateDesc(userName+"将申请件解挂到征信未完成队列");
				}else if("2".equals(delStatus)){//待补件
					 a.setOperateDesc(userName+"将申请件解挂到征信待补件队列");
				} else if("3".equals(delStatus)){//退回
					 a.setOperateDesc(userName+"将申请件解挂到征信退回队列");
				}else{ 
			         a.setOperateDesc(userName+"将申请件解挂");
				}
			if(!"".equals(userCode)){
				a.setOperater(userName+"-"+userCode);
				a.setCrtUser(userCode);
			}else{
				a.setOperater("systemZx");
				a.setCrtUser("systemZx");
			}
			}
			a.setOperateResult("完成");
			a.setCrtDate(new Date());
			a.setUuid(StringUtil.randomUUIDPlainString());
			applyLifeCicleDao.addApplyLifeCicle(a);
		}
		bizInpApplDao.updateZxApplyYdjHangUpByAppIds(paramMap);
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}
	
	/**
	 * wangtao
	 * 录入审查未完成队列根据AppId获取申请件
	 */
	@Override
	public Map<String,Object> selectAllotByConditionByAppId(Map<String, Object> conditionMap) {
		Map<String, Object> msgMap=new HashMap<String,Object>();//存放 返回前台的信息
		Map<String, Object> updateAllotMap=new HashMap<String,Object>();//存放 需要修改的申请件分配表的数据信息
		updateAllotMap.put("currOptUser", conditionMap.get("userId"));//更改 CURR_OPT_USER
		
		List<AllotApplyAllot> allotList = allotApplyAllotDao.selectAllotListByAppId((String)conditionMap.get("appId"));
		//根据appid获取对应的当前操作人作为修改申请分配表的上次操作人,对应的当前操作组作为修改申请分配表的上次操作人
		//AllotApplyAllot allot = allotApplyAllotDao.selectByPrimaryKey((String)conditionMap.get("appId"));
		for (AllotApplyAllot allot : allotList) {
			updateAllotMap.put("lastOptGroup", allot.getCurrOptGroup());//更改上次操作组
			updateAllotMap.put("lastOptUser", conditionMap.get("userId"));//更改 LAST_OPT_USER
			updateAllotMap.put("lstTeamDate", allot.getUserDate());//更改上次操作日期（为修改状态之前的进入当前操作员的日期）
			updateAllotMap.put("userDate", new Date());
			conditionMap.put("appId", allot.getAppId());
			updateAllotMap.put("appId", allot.getAppId());//根据参数 appId修改相应 数据
			//获取 申请件分配表 currStatus
			Map allotMap=bizInpApplDao.selectAllotApplyCurrStatusByMapForExamine(conditionMap);
			if(allotMap!=null&&allotMap.get("CURRSTATUS")!=null){
				String currStatus=allotMap.get("CURRSTATUS").toString();
				Map<String,Object> delMap = allotApplyAllotDao.selectDelStatusByAppId(allot.getAppId());
				String delStatus = "0";
				if(delMap!=null){
					 delStatus = (String)delMap.get("DEL_STATUS");
				}
				if("0".equals(currStatus)&&"0".equals(delStatus)){//分配状态 是 当前环节池中
					//将此流水号的在申请件分配表的信息插入到申请件分配历史表
					bizInpApplDao.insertCopyOpasAllotToAllotHis(conditionMap);
					//根据userId从用户组别关系表OPAS_AP_USER_ORG中获取机构ID(org_id)
					Map orgIdMap=bizInpApplDao.selectUserOrgOrgIdByUserUuid(conditionMap);
					if(orgIdMap!=null&&orgIdMap.get("ORGID")!=null){
						String orgId=orgIdMap.get("ORGID").toString();
						if(!"".equals(orgId)){//根据 org_id（组序列值） --->查表 opas_ap_org（用户组管理） 表 获取 org_no(组代码) 
							conditionMap.put("orgId", orgId);
							Map orgNoMap=bizInpApplDao.selectApOrgOrgNoByOrgId(conditionMap);	
							if(orgNoMap.get("ORGNO")!=null){
								String orgNo=orgNoMap.get("ORGNO").toString();//组代码
								if(!"".equals(orgNo)){
									updateAllotMap.put("currOptGroup", orgNo);//更改  curr_opt_group,
								}
							}	
						}
					}
					Date date = new Date();
					updateAllotMap.put("currStatus", "3");//分配状态改为 3 已分配 未挂起
					updateAllotMap.put("groupDate", date);
					updateAllotMap.put("lstTeamDate", date);
				   //进行  更改	 修改申请件分配表操作 
					allotApplyAllotDao.updateAllotForExamine(updateAllotMap);
				   msgMap.put("success",true);
					
				}else if("1".equals(currStatus)&&!"1".equals(delStatus)){//分配状态 未分配 未挂起
					//将此流水号的在申请件分配表的信息插入到申请件分配历史表
					bizInpApplDao.insertCopyOpasAllotToAllotHis(conditionMap);
					//从 表opas_ap_user_org(用户组别关系表) 中 获取  org_id （机构Id）
					Map orgIdMap=bizInpApplDao.selectUserOrgOrgIdByUserUuid(conditionMap);
					if(orgIdMap!=null&&orgIdMap.get("ORGID")!=null){
						String orgId=orgIdMap.get("ORGID").toString();
						if(!"".equals(orgId)){//根据 org_id（组序列值） --->查表 opas_ap_org（用户组管理） 表 获取 org_no(组代码) 
							conditionMap.put("orgId", orgId);
							Map orgNoMap=bizInpApplDao.selectApOrgOrgNoByOrgId(conditionMap);	
							if(orgNoMap!=null&&orgNoMap.get("ORGNO")!=null){
								String orgNo=orgNoMap.get("ORGNO").toString();//组代码
								if(!"".equals(orgNo)){
									updateAllotMap.put("currOptGroup", orgNo);//更改  curr_opt_group,
								}
							}	
						}
					}
					Date date = new Date();
					updateAllotMap.put("currStatus", "3");//分配状态改为 3 已分配 未挂起	
					updateAllotMap.put("groupDate", date);
					updateAllotMap.put("lstTeamDate", date);
					//将此流水号的在申请件分配表的信息插入到申请件分配历史表
					bizInpApplDao.insertCopyOpasAllotToAllotHis(conditionMap);
					//进行 更改修改申请件分配表操作 
					allotApplyAllotDao.updateAllotForExamine(updateAllotMap);
					msgMap.put("success",true);
				}else{//无法抓取此件
					msgMap.put("success", false);
					msgMap.put("msg", "此流水号因分配状态不能获取。申请件获取失败。");
				}
			}else{//无法抓取此件 
				msgMap.put("success", false);
				msgMap.put("msg", "此流水号不存在或已被提报。申请件获取失败!");
			}	
		}
		return msgMap;
	}
	/**
	 * wangtao
	 * 审查录入环节抢件功能
	 * @throws Exception 
	 */
	@Override
	public Map<String, Object> updateLrApplyForExamine(Map<String, Object> conditionMap) throws Exception {
		Map<String, Object> msgMap=new HashMap<String,Object>();//存放 返回前台的信息
		ApUser apUser = apUserDao.queryApUserByUserId((String) conditionMap.get("userUuid"));
		String userName = apUser.getUserName();
		try{
			List<AllotApplyAllot> allotApplyAllots = allotApplyAllotDao.selectAllotForExamineByRob(conditionMap);
			if(allotApplyAllots.isEmpty()){
				msgMap.put("success", false);
				msgMap.put("msg", "目前没有申请件可抢!");
			}else{
				for(int j=0;j<allotApplyAllots.size();j++){
					AllotApplyAllot allot=allotApplyAllots.get(j);
					//抢件时插入数据记录志至生命周期表
					ApplyLifeCicle applyLifeCicle = new ApplyLifeCicle();
					Date date = new Date();
					applyLifeCicle.setAppId(allot.getAppId());
					applyLifeCicle.setUuid(UUID.randomUUID().toString().replace("-", ""));
					applyLifeCicle.setOperateDesc(userName+"录入审查环节抢该件");
//					applyLifeCicle.setOperateResult("完成");
					applyLifeCicle.setCrtDate(date);
					applyLifeCicle.setLstUpdDate(date);
					applyLifeCicle.setCrtUser((String)conditionMap.get("userId"));
					applyLifeCicle.setOperater(userName+"-"+apUser.getUserCode());
					applyLifeCicle.setLstUpdUser((String)conditionMap.get("userId"));
					
					Map<String, Object> updateAllotMap=new HashMap<String,Object>();//存放 需要修改的分件表的数据信息
					updateAllotMap.put("currOptUser", conditionMap.get("userId"));//更改 CURR_OPT_USER
					updateAllotMap.put("appId", allot.getAppId());//根据参数 appId修改相应 数据
					updateAllotMap.put("lstTeamDate", allot.getUserDate());//更改上次操作日期（为修改状态之前的进入当前操作员的日期）
					updateAllotMap.put("userDate", new Date());
					//根据appid获取对应的当前操作人作为修改申请分配表的上次操作人,对应的当前操作组作为修改申请分配表的上次操作人
					updateAllotMap.put("lastOptGroup", allot.getCurrOptGroup());//更改上次操作组
					updateAllotMap.put("lastOptUser", conditionMap.get("userId"));//更改 LAST_OPT_USER
				   //将此流水号的在申请件分配表的信息插入到申请件分配历史表
				    bizInpApplDao.insertCopyOpasAllotToAllotHis(updateAllotMap);
					//从 表opas_ap_user_org(用户组别关系表) 中 获取  org_id （机构Id）
					Map orgIdMap=bizInpApplDao.selectUserOrgOrgIdByUserUuid(conditionMap);
					if(orgIdMap!=null&&orgIdMap.get("ORGID")!=null){
						String orgId=orgIdMap.get("ORGID").toString();
						if(!"".equals(orgId)){//根据 org_id（组序列值） --->查表 opas_ap_org（用户组管理） 表 获取 org_no(组代码) 
							conditionMap.put("orgId", orgId);
							Map orgNoMap=bizInpApplDao.selectApOrgOrgNoByOrgId(conditionMap);	
							if(orgNoMap!=null&&orgNoMap.get("ORGNO")!=null){
								String orgNo=orgNoMap.get("ORGNO").toString();//组代码
								if(!"".equals(orgNo)){
									updateAllotMap.put("currOptGroup", orgNo);//更改  curr_opt_group,
								}
							}	
						}
					}
					updateAllotMap.put("currStatus", "3");//分配状态改为 3 已分配 未挂起
					updateAllotMap.put("userDate", date);
					updateAllotMap.put("lstTeamDate", date);
					//进行 更改修改申请件分配表操作 
					allotApplyAllotDao.updateAllotForExamine(updateAllotMap);
					msgMap.put( "success", true);
					applyLifeCicle.setOperateResult("审查环节抢件成功");
					applyLifeCicleDao.addApplyLifeCicle(applyLifeCicle);
				}
			}
		}catch(Exception e){
			throw new Exception("出现异常，手动抢件失败!");
		}
		return msgMap;
	}
	@Override
	public int findZxIndividualQueueDataCount(Map paramMap) {
		 return bizInpApplDao.selectZxIndividualQueueDataCount(paramMap);
	}
	@Override
	public List findZxIndividualQueueDataList(Map paramMap, int curNum, int pageNum) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		list=bizInpApplDao.selectZxIndividualQueueDataList(paramMap, curNum, pageNum);
		Map<String,Object> userMap=new HashMap<String,Object>();
		if(!list.isEmpty()){
		for (int i = 0; i < list.size(); i++) {
			String ydjFlag=list.get(i).get("ydjFlag").toString();
			String appId=list.get(i).get("appId").toString();
			String matchingCardFlag=list.get(i).get("matchingCardFlag").toString();
			if("1".equals(ydjFlag)&&"1".equals(matchingCardFlag)){
				if(appId.endsWith("1")){
					appId=appId.substring(0,15)+"2";
				}else if(appId.endsWith("2")){
					appId=appId.substring(0,15)+"1";
				}
			}
			Integer patchStatusR=pbocDao.selectCountPbocPersonInfoByAppId(appId);
			Integer patchStatusP=policeDao.selectPoliceCountByAppId(appId);
			//可信任身份证(只有主卡发起可信身份认证查询，附卡不查，且是网申件才会查询)（易达金卡目前也不查询）
			Integer patchStatusPP=policeDao.selectDependableIdentityCardByAppid(appId);
			if(patchStatusPP!=0){
				//可信身份认证信息查询有结果时查询里面的“认证结果”，根据此结果的第一个字符判断是否提示“P”：0和5这在这两种情况不提示
				Map<String,String> map = policeDao.selectAuthResultByAppid(appId);
				if(map!=null){
					String authResult = map.get("AUTHRESULT");
					if(authResult!=null){
						if(!(authResult.startsWith("0") || authResult.startsWith("5"))){
							patchStatusPP=0;
						}
					}else{
						patchStatusPP=0;
					}
				}else{
					patchStatusPP=0;
				}
			}
			//智能语音已发起提示
			//智能语音
			Integer patchStatusS=creditCheckDaoImp.selectCallSVoiceCount(appId);
			list.get(i).put("patchStatusR",patchStatusR);
			list.get(i).put("patchStatusP",patchStatusP);
			list.get(i).put("patchStatusPP", patchStatusPP);
			list.get(i).put("patchStatusS",patchStatusS);
			if(list.get(i)!=null&&!"".equals(list.get(i).get("lastOptUser"))&&list.get(i).get("lastOptUser")!=null){
			//最后一次操作人赋值为中文
			userMap.put("userCode",list.get(i).get("lastOptUser"));
			Map<String,Object> apUserMap=bizInpApplDao.selectApUserByUuIdUserCode(userMap);
			if(apUserMap!=null){
			String userName=apUserMap.get("userName").toString();
			list.get(i).put("lastOptUser",userName);
			}
		}
		}
		}
		return list;
	}
	@Override
	public Map<String, Object> queryYdjFlag(Map appIdMap) {
		return bizInpApplDao.queryYdjFlag(appIdMap);
	}
	@Override
	public Map<String, Object> selectApUserByUuIdUserCode(
			Map<String, Object> userMap) {
		return bizInpApplDao.selectApUserByUuIdUserCode(userMap);
	}
	@Override
	public Map<String, String> findExamineLibraryCheckMessage(Map map) {
		return bizInpApplDao.selectExamineLibraryCheckMessage(map);
	}
	@Override
	public int countUnFinishByCondition(Map map) {
		return bizInpApplDao.countUnFinishByCondition(map);
	}

//	@Override
//	public int selectCountByCurrOptUserForExamine(String currOptUser) {
//		return bizInpApplDao.selectCountByCurrOptUserForExamine(currOptUser);
//	}

	@Override
	public Map<String, Object> addExamineToTopbpm(Map<String, Object> paramMap) throws Exception {
		String industryCode = (String) paramMap.get("industryCode");
		int count = infoCollectDao.queryCountByIndustryCode(industryCode);
		Map<String, Object> returnMap = new HashMap<>();
		if (count > 0) {
			ApUser apUser = apUserDao.queryApUserByUserId((String) paramMap.get("userUuid"));
			String userName = apUser.getUserName();
			String userCode = apUser.getUserCode();
			String operater = userName + "-" + userCode;
			ApplyLifeCicle applyLifeCicle = new ApplyLifeCicle();
			Date date = new Date();
			String appId = (String) paramMap.get("appId");
			applyLifeCicle.setAppId(appId);
			applyLifeCicle.setOperater(operater);
			applyLifeCicle.setOperateDesc(userName + "录入审查环节提交申请件");
			applyLifeCicle.setUuid(UUID.randomUUID().toString().replace("-", ""));
			applyLifeCicle.setCrtDate(date);
			applyLifeCicle.setCrtUser(userCode);
			applyLifeCicle.setLstUpdUser(userCode);
			applyLifeCicle.setLstUpdDate(date);

			Map<String, Object> someFlagMap = qualityCheckingDao.querySomeFlagByAppId(appId);
			String currNode = (String) someFlagMap.get("CURR_NODE");
			String currOptUser = (String) someFlagMap.get("CURR_OPT_USER");
			BigDecimal allotVersion = (BigDecimal) someFlagMap.get("ALLOT_VERSION");
			if (!"01".equals(currNode) || !userCode.equals(currOptUser)) {
				returnMap.put("exMsg", "该申请件已不在个人队列！");
				returnMap.put("isSuccess", false);
				return returnMap;
			}
			String stopFlag = qualityCheckingDao.queryStopFlag(appId);
			if ("2".equals(stopFlag)) {
				returnMap.put("exMsg", "该申请件被叫停不能提交！");
				returnMap.put("isSuccess", false);
				return returnMap;
			}
			applyLifeCicle.setOperateResult("审查环节提交成功");
			// applyLifeCicleDao.addApplyLifeCicle(applyLifeCicle);
			// 提交成功后录入审查决策结果信息表
			String userOrgNo = qualityCheckingDao.queryUserOrgNo((String) paramMap.get("userUuid"));
			OpasReviewDecisionResult opasReviewDecisionResult = new OpasReviewDecisionResult();
			opasReviewDecisionResult.setAutoId(UUID.randomUUID().toString().replace("-", ""));
			opasReviewDecisionResult.setAppId(appId);
			opasReviewDecisionResult.setAuditor(apUser.getUserCode());
			opasReviewDecisionResult.setAuditorName(apUser.getUserName());
			opasReviewDecisionResult.setCurrOptGroup(userOrgNo);
			opasReviewDecisionResult.setCrtDate(new Date());
			opasReviewDecisionResult.setCrtUser(apUser.getUserCode());
			/*
			 * int counts = reviewDecisionDao.countBatchCode(appId); if(counts >
			 * 0){ reviewDecisionDao.updateBatchCode(opasReviewDecisionResult);
			 * }else{
			 * reviewDecisionDao.insertBatchCode(opasReviewDecisionResult); }
			 */
			AllotApplyAllot record = new AllotApplyAllot();
			record.setAppId(paramMap.get("appId").toString());
			record.setDelStatus("1");// 审查状态 变为已完成
			record.setSynFlag("0");
			record.setAllotVersion(allotVersion.intValue());
			Map<String, Object> map = new HashMap<>();
			map.put("appId", record.getAppId());
			bizInpApplDao.insertCopyOpasAllotToAllotHis(map);
			int returnFlag = allotApplyAllotDao.updateByPrimaryKeySelective(record);
			if (returnFlag != 0) {
				// 记录生命周期
				applyLifeCicleDao.addApplyLifeCicle(applyLifeCicle);
				// 提交成功后录入审查决策结果信息表
				int counts = reviewDecisionDao.countBatchCode(appId);
				if (counts > 0) {
					reviewDecisionDao.updateBatchCode(opasReviewDecisionResult);
				} else {
					reviewDecisionDao.insertBatchCode(opasReviewDecisionResult);
				}

				Map<String, Object> needMap = bizInpApplDao.selectProcessIdByAppId(paramMap);
				Map<String, String> streamMap = CommonProperties.getParoperMap();
				if (null != needMap) {
					String processId = needMap.get("PROCESSID") != null ? needMap.get("PROCESSID").toString() : "";
//					String processIp = streamMap.get("process_IP").toString();// 服务ip地址
					String nodeId = streamMap.get("nodeId_lr_bzk").toString();// 流节点
					if (processId != null && !"".equals(processId)) {
						// Client client = new Client(new
						// URL("http://192.168.1.24:9091/topbpm_test/services/BranchBankSearchServiceI?wsdl"));
//						Client client = new Client(
//								new URL("http://" + processIp + "/topbpm_test/services/BranchBankSearchServiceI?wsdl"));
						String intputXml = "";
						intputXml = "{'processId':'" + processId + "','nodeId':'" + nodeId + "'}";
//						Object[] obj = new Object[] { intputXml.toString() };
//						Object[] result = client.invoke("signal", obj);
						String obj =  intputXml.toString();
						String result = topbpmService.signal(obj).toString();
						if (logger.isInfoEnabled()) {
							logger.info("查询数组：{}", result);
						}
//						if (result != null && result[0] != null && "1".equals(result[0].toString())) {
						if(result!=null&&"1".equals(result)){
							System.out.println("single======" + result.toString());
							returnMap.put("isSuccess", true);
						} else {
							returnMap.put("exMsg", "网络繁忙！请稍候再试......");
							returnMap.put("isSuccess", false);
							throw new CoreException("网络繁忙！请稍候再试......");
						}

					}
				} else {
					returnMap.put("exMsg", "申请表数据缺失processId");
					returnMap.put("isSuccess", false);
					throw new CoreException("申请表数据缺失processId，请检测appId=" + paramMap.get("appId"));
				}
			} else {
				returnMap.put("isSuccess", false);
				returnMap.put("exMsg", "该申请件已不在该人队列");
			}
		} else {
			returnMap.put("isSuccess", false);
			returnMap.put("exMsg", "未匹配到行业代码");
		}
		return returnMap;
	}

	@Override
	public Map<String, Object> queryQrcodeList(Map<String, Object> map) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String appId = map.get("appId").toString();
		List<String> list = new ArrayList<String>();
		list = bizInpApplDao.queryQrcodeList(appId);
		String companyName = "|";
		for(String name:list) {
			if(!"".equals(trimToEmpty(name))){
				companyName=companyName+name+" |";
			}
		}
		String code = bizInpApplDao.queryQrcode(appId);
		resultMap.put("companyName",companyName);
		resultMap.put("code",code);
		return resultMap;
	}
	
	public String trimToEmpty(String str) {
		if (str == null) {
			return "";
		}
		return str.trim();
	}

	@Override
	public Map<String, Object> updateApplyZxToSVoice(CreditCheck creditCheck,Map<String, Object> streamMap) {
		
			Map<String, Object> htmlMap = new HashMap<String, Object>();
			String msg = "调流前失败.提交失败。";
			boolean needCallingProcess =  false; //标准卡进入智能语音复核队列无需回调工作流
		
			String ydjFlag = streamMap.get("ydjFlag").toString();
			String currAppId = creditCheck.getCheck_number();// 申请件的appId
			Map<String, Object> judgeMap = new HashMap<String, Object>();
			judgeMap.put("appId", currAppId);
			judgeMap.put("currNode", "02"); //征信"02"到智能语音"07"
			judgeMap.put("ydjFlag", ydjFlag);// 根据当前用户查询申请件当前分件表状态
			String orgNo = creditCheckDaoImp.queryGroup(streamMap.get("userCode").toString());
			judgeMap.put("orgNo", orgNo); // 申请件查询当前分件表状态
			Map<String, Object> needMap = bizInpApplDao.selectAllotApplyCurrStatusByGroupMap(judgeMap);
			if (needMap != null) {
			try {
			// 全流程记录
				ApplyLifeCicle a = new ApplyLifeCicle();
				Map<String, Object> userMap = new HashMap<String, Object>();
				userMap.put("userCode", streamMap.get("userCode").toString());
				Map<String, Object> apUserMap = bizInpApplDao.selectApUserByUuIdUserCode(userMap);
	
				a.setAppId(currAppId);
				if (apUserMap != null) {
					String userCode = apUserMap.get("userCode").toString();
					String userName = apUserMap.get("userName").toString();
					a.setOperateDesc(userName + "进行征信->智能语音复核队列");
					if (!"".equals(userCode)) {
						a.setOperater(userName + "-" + userCode);
						a.setCrtUser(userCode);
					} else {
						a.setOperater("systemZx");
						a.setCrtUser("systemZx");
					}
				}
				a.setOperateResult("完成");
				a.setCrtDate(new Date());
				a.setUuid(StringUtil.randomUUIDPlainString());
				applyLifeCicleDao.addApplyLifeCicle(a);
				
				// 申请件分配表数据记录历史表中
				creditCheckDaoImp.insertCopyOpasAllotApplyAllot(currAppId);
				// 修改申请件分配表
				creditCheckDaoImp.updateCreditCheck(creditCheck);
				
				// ========end===========
				// 调流isBack=2,征信；3征审 ； 5 智能语音   6智能语音退回
				streamMap.put("isBack", "5");
				if (needCallingProcess) {
					/*
					 * if(ydjFlag.equals("1")){//易达金 Map<String,Object>
					 * updateMap=new HashMap<String,Object>();
					 * updateMap.put("appId", currAppIdFront);
					 * updateMap.put("synFlag", "0");//同步提交状态 都变为0 //根据流水号前15位
					 * 模糊修改 bizInpApplDao.updateAllotByMapLikeAppId(updateMap);
					 * }
					 */
					msg = "调流前成功,工作流异常.提交失败。";
					lineNewUrlClient(streamMap);
				}
				htmlMap.put("msg", "征信提交智能语音复核队列成功。");
			  } catch (Exception e) {
				logger.error("申请件:{}系统繁忙，请稍后再试。", currAppId, e);
				throw new RuntimeException("系统繁忙，请稍后再试。");
			  }
			} else {
				throw new RuntimeException("此申请件已不存在，请重新刷新当前队列页面。提交失败。");
			}
		
		return htmlMap;	
	}

	@Override
	public void updateApplySVoiceToZx(AllotApplyAllot allot, AllotApplyAllotHis allotHis,Map<String,Object> resMap, String processFlag) {
		Map<String, Object> htmlMap = new HashMap<String, Object>();
		String msg = "调流前失败.提交失败。";
		boolean needCallingProcess =  false; //标准卡智能语音申请件不需要调流
		try {
				allot.setUserDate(new Date());
				allot.setLstTeamDate(new Date());
				allot.setCheck_meuoFlag(resMap.get("check_meuoFlag").toString());
				//修改当前分件表状态
				allotApplyAllotDao.updateByPrimaryKeySelective(allot);
				//插入历史表状态
				allotApplyAllotHisDao.insertSelective(allotHis);
				//查询到当前操作员的中文姓名，记录生命周期
				ApUser user = apUserDao.queryApUserByUserCode(allot.getLastOptUser());
				String memo = null;
				if("1".equals(processFlag)){
					memo = "智能语音退回->征信未完成队列";
				}else if("2".equals(processFlag)){
					memo = "智能语音已核实未成功->征信未完成队列";
				}
				addApplyLifeCicle(allot,user!=null?user.getUserName():"",memo);
				// 暂时不需调用工作流，工作流认为此刻一直在征信环节
				// 调流isBack=2,征信；3征审 ； 5 智能语音   6智能语音退回
				resMap.put("isBack", "6");
				if (needCallingProcess) {
					msg = "调流前成功,工作流异常.提交失败。";
					lineNewUrlClient(resMap);
				}
				//htmlMap.put("msg", "智能语音复核队列退回征信未完成队列成功。");
		  } catch (Exception e) {
				logger.error("申请件{}:系统繁忙，请稍后再试。",allot.getAppId(), e);
				throw new RuntimeException("系统繁忙，请稍后再试。");
		  }
	} 

	@Override
	public boolean querySVoiceResult(String appId) {
		//增加智能语言结论信息  
		Map<String,String> svoiceMap = policeDao.selectSVoiceInfo(appId.substring(0,15)+"%");
		if(null!=svoiceMap){
			//CONCLUSION  
			if(svoiceMap.containsKey("CONCLUSION")){
				String conclusion = svoiceMap.get("CONCLUSION").trim();
				if(!"".equals(conclusion)){return true;}
			}
		}
		return false;
	}
	/**
	 * 调用智能语音dubbo
	 * 根据正确参数
	 * @throws Exception
	 */
	public String lineSVoiceUrlClient(Map<String, Object> streamMap) throws Exception {
		String msg="";
		String result =null;
		String appId = streamMap.get("appId").toString();
		String processId= "1DPU658FLIH11801A8C000007842BA3C";
		if(processId!=null&&!"".equals(processId)){
			String intputXml = "{'REQUEST':{'BODY':{'PUBLIC':{'APP_ID':'" +appId + 
					            "','SOURCEFLAG':'1'},'WORKFLOW':{'WF_PROCESS_ID':'"+processId+"'}}}}";
			logger.info("申请件:{}[审批系统]->[智能语音]请求数据:{}",appId,intputXml.toString());
			try{/*返回值  "1".成功  2.失败*/
				result =  intelligentVoiceService.call(intputXml);
				logger.info("申请件:{}[审批系统]->[智能语音]返回结果:{}",appId,result);
			}catch(Exception e){
				logger.error("申请件:{}[审批系统]->[智能语音]dubbo调用异常",appId,e);
				throw new RuntimeException("审批调用智能语音dubbo调用异常!");
			}
			if(result!=null&&"1".equals(result)){
				msg="智能语音调用成功。";
				logger.info("申请件:{}[审批系统]->[智能语音]调用成功",appId);
			}else{
				logger.info("申请件:{}[审批系统]->[智能语音]调用失败",appId);
				throw new RuntimeException("审批调用智能语音dubbo调用异常!");
			}
		}else{
			msg="数据异常(processId不存在).提交失败。";
			logger.info("申请件:{}[审批系统]->[智能语音]数据异常(processId不存在).提交失败。",appId);
			throw new RuntimeException(msg); //为了制造异常 使事物回滚;
		}
		return msg;
	}
	@Override
	public Map<String, Object> updateApplySVoiceToAppro(CreditCheck creditCheck,
			Map<String, Object> streamMap) {
		Map<String, Object> htmlMap = new HashMap<String, Object>();
		String msg = "调流前失败.提交失败。";
		String ydjFlag = streamMap.get("ydjFlag").toString();
		String currAppId = creditCheck.getCheck_number();// 申请件的appId
		String isBatch = streamMap.get("isBatch").toString();//批量提交标志
		Map<String, Object> judgeMap = new HashMap<String, Object>();
		judgeMap.put("appId", currAppId);
		judgeMap.put("currNode", "07");//智能语音节点复核提交操作
		judgeMap.put("ydjFlag", ydjFlag);
		judgeMap.put("userId", streamMap.get("userCode").toString());
		judgeMap.put("check_meuoFlag", streamMap.get("check_meuoFlag").toString());
		//申请件查询当前分件表状态
		Map<String, Object> needMap = bizInpApplDao.selectAllotApplyCurrStatusByMap(judgeMap);
		if (needMap != null) {
			try {
				//调用智能语音的接口
				String svoiceMsg = lineSVoiceUrlClient(streamMap);
				logger.info(currAppId+svoiceMsg);
				//成功则继续，失败则退回
				creditCheck.setCheck_delStatus("1");
			    //全流程记录
				ApplyLifeCicle a = new ApplyLifeCicle();
				Map<String, Object> userMap = new HashMap<String, Object>();
				userMap.put("userCode", streamMap.get("userCode").toString());
				Map<String, Object> apUserMap = bizInpApplDao.selectApUserByUuIdUserCode(userMap);
				a.setAppId(currAppId);
				if (apUserMap != null) {
					String userCode = apUserMap.get("userCode").toString();
					String userName = apUserMap.get("userName").toString();
					if(null!=isBatch&&"1".equals(isBatch)){
						a.setOperateDesc(userName + "将该件通过批量复核提交到智能语音");
					}else{
						a.setOperateDesc(userName + "将该件通过抽检复核提交到智能语音");
					}
					if (!"".equals(userCode)) {
						a.setOperater(userName + "-" + userCode);
						a.setCrtUser(userCode);
					} else {
						a.setOperater("systemZx");
						a.setCrtUser("systemZx");
					}
				}
				a.setOperateResult("完成");
				a.setCrtDate(new Date());
				a.setUuid(StringUtil.randomUUIDPlainString());
				applyLifeCicleDao.addApplyLifeCicle(a);
				// 申请件分配表数据记录历史表中
				creditCheckDaoImp.insertCopyOpasAllotApplyAllot(currAppId);
				// 修改申请件分配表
				creditCheckDaoImp.updateCreditCheck(creditCheck);
				htmlMap.put("result",true);
				htmlMap.put("msg", "发起智能语音征信成功");
			  } catch (Exception e) {
				logger.error("申请件{}:智能语音调用异常",currAppId,e);
				throw new RuntimeException("系统繁忙，请稍后再试。");
		      }
		} else {
			logger.info("申请件:{}已不存在，请重新刷新当前队列页面。提交失败。");
			throw new RuntimeException("此申请件已不存在，请重新刷新当前队列页面。提交失败。");
		}
	
	  return htmlMap;	
	}
}
