package com.huaxia.opas.service.credit.impl;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Resource;

import com.alibaba.fastjson.JSON;
import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.apply.ApplyInfoDao;
import com.huaxia.opas.dao.apply.BizInpApplC1Dao;
import com.huaxia.opas.dao.apply.BizInpApplDao;
import com.huaxia.opas.dao.baseinfo.BaseCustInfoDao;
import com.huaxia.opas.dao.credit.CreditCheckSearchDao;
import com.huaxia.opas.dao.decision.TelcheckAddnoteDao;
import com.huaxia.opas.dao.sysparm.ApproveZipcodeDao;
import com.huaxia.opas.dao.thirdparty.BankDao;
import com.huaxia.opas.dao.thirdparty.PBOCDao;
import com.huaxia.opas.dao.thirdparty.PoliceDao;
import com.huaxia.opas.domain.apply.ApplyModifyLog;
import com.huaxia.opas.domain.credit.T5eEsMiddle;
import com.huaxia.opas.domain.decision.TelcheckAddnote;
import com.huaxia.opas.domain.input.BizInpApplC1;
import com.huaxia.opas.domain.input.BizInpApplC2;
import com.huaxia.opas.domain.input.TelcheckResult;
import com.huaxia.opas.service.AbstractService;
import com.huaxia.opas.service.credit.CreditCheckSearchService;
import com.huaxia.opas.util.StringUtil;
import com.huaxia.opas.util.UUIDGenerator;

public class CreditCheckSearchServiceImpl extends AbstractService implements CreditCheckSearchService , Serializable{

private static final long serialVersionUID = -5286276550553333492L;
	
@Resource(name = "creditCheckSearchDao")
private CreditCheckSearchDao creditCheckSearchDao;
@Resource(name = "bizInpApplC1Dao")
private BizInpApplC1Dao bizInpApplC1Dao;
@Resource(name = "pbocDao")
private PBOCDao pbocDao;
@Resource(name = "telcheckAddnoteDao")
private TelcheckAddnoteDao telcheckAddnoteDao;
@Resource(name = "applyInfoDao")
private ApplyInfoDao applyInfoDao;
@Resource(name = "baseCustInfoDao")
private BaseCustInfoDao baseCustInfoDao;
//电话区号
@Resource(name = "approveZipcodeDao")
private ApproveZipcodeDao approveZipcodeDao;
@Resource(name = "bizInpApplDao")
private BizInpApplDao bizInpApplDao;
@Resource(name = "bankDao")
private BankDao bankDao;
//公安
@Resource(name = "policeDao")
private PoliceDao policeDao;

public CreditCheckSearchDao getCreditCheckSearchDao() {
	return creditCheckSearchDao;
}

public void setCreditCheckSearchDao(CreditCheckSearchDao creditCheckSearchDao) {
	this.creditCheckSearchDao = creditCheckSearchDao;
}

public BizInpApplC1Dao getBizInpApplC1Dao() {
	return bizInpApplC1Dao;
}

public void setBizInpApplC1Dao(BizInpApplC1Dao bizInpApplC1Dao) {
	this.bizInpApplC1Dao = bizInpApplC1Dao;
}

public PBOCDao getPbocDao() {
	return pbocDao;
}

public void setPbocDao(PBOCDao pbocDao) {
	this.pbocDao = pbocDao;
}

public TelcheckAddnoteDao getTelcheckAddnoteDao() {
	return telcheckAddnoteDao;
}

public void setTelcheckAddnoteDao(TelcheckAddnoteDao telcheckAddnoteDao) {
	this.telcheckAddnoteDao = telcheckAddnoteDao;
}

public ApplyInfoDao getApplyInfoDao() {
	return applyInfoDao;
}

public void setApplyInfoDao(ApplyInfoDao applyInfoDao) {
	this.applyInfoDao = applyInfoDao;
}

public BaseCustInfoDao getBaseCustInfoDao() {
	return baseCustInfoDao;
}

public void setBaseCustInfoDao(BaseCustInfoDao baseCustInfoDao) {
	this.baseCustInfoDao = baseCustInfoDao;
}

public ApproveZipcodeDao getApproveZipcodeDao() {
	return approveZipcodeDao;
}

public void setApproveZipcodeDao(ApproveZipcodeDao approveZipcodeDao) {
	this.approveZipcodeDao = approveZipcodeDao;
}

public BizInpApplDao getBizInpApplDao() {
	return bizInpApplDao;
}

public void setBizInpApplDao(BizInpApplDao bizInpApplDao) {
	this.bizInpApplDao = bizInpApplDao;
}

@Override
public Map<String, Object> queryCreditInfo(Map<String ,String> argMap) throws CoreException{
	Map<String, Object> custInfoMap = creditCheckSearchDao.queryCreditCustInfo(argMap);
	Map<String, Object> nodeInfoMap = creditCheckSearchDao.queryCheckNodeInfo(argMap);
	List<Map<String, Object>> approvalInfoList = creditCheckSearchDao.queryApprovalInfo(argMap);
	Map<String, Object> checkInfoMap = creditCheckSearchDao.queryCheckResultInfo(argMap);
	Map<String, Object> batchInfoMap = creditCheckSearchDao.queryBatchMarkInfo(argMap);
	//List<String> creditRefuseReasonList = creditCheckSearchDao.queryDenyMemo(argMap);
	Map<String, Object> resultMap = new HashMap<String,Object>();
	if(custInfoMap!=null){
		resultMap.putAll(custInfoMap);
	}
	if(nodeInfoMap!=null){
		resultMap.putAll(nodeInfoMap);
	}
	/*if(approvalInfoList!=null){
		resultMap.put("approvalInfoList",approvalInfoList);
	}*/
	if(checkInfoMap!=null){
		resultMap.putAll(checkInfoMap);
	}
	if(batchInfoMap!=null){
		resultMap.putAll(batchInfoMap);
	}
	/*if(creditRefuseReasonList!=null){
		resultMap.put("creditRefuseReasonList",creditRefuseReasonList);
	}*/
	SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
	if(approvalInfoList!=null){
		List<Map<String, Object>> approvalInfoListAft = new ArrayList<Map<String, Object>>();
		for (Map<String, Object> approvalInfomap : approvalInfoList) {
			Date approveTimeDate =(Date) approvalInfomap.get("approveTime");
			if(approveTimeDate!=null){
				String approveTime = fmt.format(approveTimeDate);
				approvalInfomap.put("approveTime", approveTime);
			}
			approvalInfoListAft.add(approvalInfomap);
		}
		resultMap.put("approvalInfoList",approvalInfoListAft);
	}
	Date crtDate =(Date) resultMap.get("crtDate");
	if(crtDate!=null){
		String crtDateTime = fmt.format(crtDate);
		resultMap.put("crtDate", crtDateTime);
	}
	String bc = (String)resultMap.get("batchCode");
	if ("1".equals(bc)) {
		resultMap.put("batchCode", "送往决策处理中");
	}else if("2".equals(bc)){
		resultMap.put("batchCode", "决策返回批量处理");
	}else if("3".equals(bc)){
		resultMap.put("batchCode", "决策返回分件处理");
	}
	return resultMap;
}
public BizInpApplC1 selectBizInpApplC1ByAppId(String appId){
	try {
		return bizInpApplC1Dao.selectBizInpApplC1ByAppId(appId);
	} catch (CoreException e) {
	}
	return null;
}

/**
 * 人行摘要信息 
 */
public Map<String, String> queryPbocInfo(String appId) {
	return pbocDao.queryPbocInfo(appId);
}

/**
 * 二代人行摘要信息 
 */
public Map<String, String> queryBankInfo(String appId) {
	return bankDao.queryBankInfo(appId);
}

@Override
public Map<String, String> queryTeam(String appId) {
	return null;
}

public String saveOrUpdateNotes(TelcheckAddnote note) {
	note.setCrtDate(new Date());
	if(null==note.getAutoId()||"".equals(note.getAutoId())){
		note.setAutoId(StringUtil.randomUUIDPlainString());
		telcheckAddnoteDao.insert(note);
	}else{
		TelcheckAddnote notes =telcheckAddnoteDao.selectByPrimaryKey(note.getAutoId());
		if(null!=notes){
			note.setUpdateDate(new Date());
			telcheckAddnoteDao.updateByPrimaryKey(note);
		}else{
			note.setAutoId(StringUtil.randomUUIDPlainString());
			telcheckAddnoteDao.insert(note);
		}
	}
	 return "更新成功";
}

public void saveNotes(TelcheckAddnote note) {
	note.setCrtDate(new Date());
	note.setAutoId(StringUtil.randomUUIDPlainString());
	telcheckAddnoteDao.insert(note);
}
@Override
public List<TelcheckAddnote> selectNotesList(String appId) {
	return telcheckAddnoteDao.queryNotesList(appId);
}

@Override
public String deleteByAutoId(String autoId) {
	telcheckAddnoteDao.deleteByPrimaryKey(autoId);
	return "删除成功";
}
	
	//征信调查防止重复提交
	@Override
	public void saveOrUpdateTelcheckResult(TelcheckResult result, String appId) {
		TelcheckResult telcheckResult = telcheckAddnoteDao.selectResultById(appId);
		if (null != telcheckResult) {
			telcheckAddnoteDao.updateTelcheckResult(result);
		} else {
			result.setAutoId(UUIDGenerator.getUUID());
			telcheckAddnoteDao.saveTelcheckResult(result);
		}
	}	
	
	//保存并且更新申请表信息
	@Override
	public void saveOrUpdateResult(TelcheckResult result, String appId,BizInpApplC1 bizInpApplC1) throws CoreException{
		//更新  申请表的 的住宅地址 c1Hmadd1,c1Hmadd2,c1Hmadd3,c1Hmadd4,更新到opas_biz_inp_appl_compore 表的c1Hmadd1字段中
		String c1Hmadd1 = bizInpApplC1.getC1Hmadd1();
		String c1Hmadd2 = bizInpApplC1.getC1Hmadd2();
		String c1Hmadd3 = bizInpApplC1.getC1Hmadd3();
		String c1Hmadd4 = bizInpApplC1.getC1Hmadd4();
		String c1Hmadd = c1Hmadd1+c1Hmadd2+c1Hmadd3+c1Hmadd4;
		String id = bizInpApplC1.getAppId();
		String c1Idnbr = bizInpApplC1.getC1Idnbr();
		Map<Object, Object> map = new HashMap<>();
		map.put("c1Hmadd", c1Hmadd);
		map.put("appId", id);
		map.put("c1Idnbr", c1Idnbr);
		telcheckAddnoteDao.updateBizInpApplComporeByAppId(map);
		
		TelcheckResult telcheckResult = telcheckAddnoteDao.selectResultById(appId);
		String crtUser = result.getCrtUser();
		Map<String,Object> user = creditCheckSearchDao.selectUserNameByUserCode(crtUser);
		String crediterName = (String) user.get("userName");
		String crediterUuid = (String) user.get("userId");
		result.setCrediterName(crediterName);
		result.setCrediter(crtUser);
		result.setCrtUser(crtUser);
		//附上当前组
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("userUuid",crediterUuid);// uuid
		//从 表opas_ap_user_org(用户组别关系表) 中 获取  org_id （机构Id）
		Map orgIdMap=bizInpApplDao.selectUserOrgOrgIdByUserUuid(paraMap);
		if(orgIdMap!=null&&orgIdMap.get("ORGID")!=null){
			String orgId=orgIdMap.get("ORGID").toString();
			if(!orgId.equals("")){//根据 org_id（组序列值） --->查表 opas_ap_org（用户组管理） 表 获取 org_no(组代码) 
				paraMap.put("orgId", orgId);
				Map orgMap=bizInpApplDao.selectApOrgOrgNoByOrgId(paraMap);	
				if(orgMap!=null&&orgMap.get("ORGNO")!=null){
					String orgNo=orgMap.get("ORGNO").toString();//组代码
					if(!orgNo.equals("")){
						result.setCurrOptGroup(orgNo);
					}
				}
			}
		}
		if (null != telcheckResult) {
			result.setCrtDate(new Date());
			telcheckAddnoteDao.updateByAppIdTelcheckResult(result);
			
		} else {
			result.setAutoId(UUIDGenerator.getUUID());
			result.setCrtDate(new Date());
			telcheckAddnoteDao.saveTelcheckResult(result);
			
		}
		
		
		
		// 申请表信息
		BizInpApplC1 oldBiz1 = new BizInpApplC1();
//		oldBiz1 = applyInfoDao.queryBizInpApplC1ByAppId(appId);//del by jpc
		oldBiz1 = applyInfoDao.queryBizInpApplC1C2ByAppId(appId);//added by jpc
		
		String oldJson = JSON.toJSONString(oldBiz1);
		String newJson = JSON.toJSONString(bizInpApplC1);
		Map<String, Object> newApplyMap = JSON.parseObject(newJson, Map.class);
		Map<String, Object> oldApplyMap = JSON.parseObject(oldJson, Map.class);
		List<ApplyModifyLog> modifyList = new ArrayList<ApplyModifyLog>();
		boolean c1Flag = false;//added by jpc c1表更新标志
		boolean c2Flag = false;//added by jpc c2表更新标志
		List<String> c2Column = new ArrayList<String>();
		c2Column.add("c5Isdpif2");
		c2Column.add("c6Ethnic2");
		
		//主卡 国籍、国家/地区、职业 以及附卡 的  国籍 、国家/地区 字段信息修改记录 是否 有记录判断：
		//如果原本数据库中有值(页面反显有值)，页面修改成空值后，这些字段不做信息修改记录
		if(oldApplyMap.get("c2Natncd1") != null && 
				(newApplyMap.get("c2Natncd1") == null || newApplyMap.get("c2Natncd1").toString().trim().equals(""))
				){
			newApplyMap.remove("c2Natncd1");//主卡国籍
		}
		if(oldApplyMap.get("c1Nation") != null && 
				(newApplyMap.get("c1Nation") == null || newApplyMap.get("c1Nation").toString().trim().equals(""))
				){
			newApplyMap.remove("c1Nation");//国家/地区
		}
		if(oldApplyMap.get("c6OccuDes1") != null &&
				(newApplyMap.get("c6OccuDes1") == null || newApplyMap.get("c6OccuDes1").toString().trim().equals(""))
				){
			newApplyMap.remove("c6OccuDes1");//职业
		}
		// 遍历Map 对比信息是否有修改
		Set<Entry<String, Object>> entrySet = newApplyMap.entrySet();
		
		for (Entry<String, Object> entry : entrySet) {
			ApplyModifyLog applyModify = new ApplyModifyLog();
			String column = entry.getKey();
			Object newValue = entry.getValue();
			Object oldValue = oldApplyMap.get(column);
			Date date = new Date();
			if (oldValue == null) {
				oldValue = "";
			}
			
			if (!oldValue.equals(newValue)) {// 说明有改变 需要记录到 --申请表信息修改日志表
				applyModify.setAutoId(StringUtil.randomGUIDPlainString());
				applyModify.setAppId(appId);
				applyModify.setFieldName(column);
				if(!c2Flag && c2Column.contains(column)){// added by jpc
					c2Flag = true;
				}else if(!c1Flag){
					c1Flag = true;
				}
				applyModify.setFlag("2");
				applyModify.setFieldOldValue(oldValue);
				applyModify.setFieldNewValue(newValue);
				applyModify.setCrtDate(date);
				applyModify.setCrtUser(crtUser);
				applyModify.setApplyName(oldBiz1.getC1Cname());
				applyModify.setCretifiType(oldBiz1.getC1Idtype());
				applyModify.setCretifiId(oldBiz1.getC1Idnbr());
				applyModify.setLstUpdDate(date);
				applyModify.setCheckFlag("0");
				applyModify.setIsKeyField("0");
				modifyList.add(applyModify);
			}
			
		} 
		if (!modifyList.isEmpty() && modifyList.size() > 0) {
			for (ApplyModifyLog applyModifyLog : modifyList) {
				if(("c1Idtype").equals(applyModifyLog.getFieldName())||("c1Cname").equals(applyModifyLog.getFieldName())||("c1Idnbr").equals(applyModifyLog.getFieldName())){
					
				} else {
					String matchingCardFlag = oldBiz1.getMatchingCardFlag();
					//matchingCardFlag=0 是非套卡  1,2是套卡
					
					if (!"0".equals(matchingCardFlag)){
						//appId最后一位
						String str = appId.substring(appId.length()-1);
						//appId前十五位
						String app_id = "";
						String appIdL = appId.substring(0, appId.length()-1);
						if ("1".equals(str)){
							applyInfoDao.saveApplyModifyLog(applyModifyLog);
							app_id=appIdL+"2";
							applyModifyLog.setAppId(app_id);
							applyModifyLog.setAutoId(StringUtil.randomGUIDPlainString());
							applyInfoDao.saveApplyModifyLog(applyModifyLog);
						} else if ("2".equals(str)){
							applyInfoDao.saveApplyModifyLog(applyModifyLog);
							app_id=appIdL+"1";
							applyModifyLog.setAppId(app_id);
							applyModifyLog.setAutoId(StringUtil.randomGUIDPlainString());
							applyInfoDao.saveApplyModifyLog(applyModifyLog);
						} 
					} else {
						applyInfoDao.saveApplyModifyLog(applyModifyLog);
					}
					
				}
			}
		}
		if (!modifyList.isEmpty()) {
			//截取appid 前十五位，如果是主副卡就同时更新，如果是单卡就只更新一张
			String appids = appId.substring(0, appId.length()-1);
			//added by jpc
			if(c1Flag){
				bizInpApplC1.setAppId(appids);
				bizInpApplC1Dao.updateBizInpApplInfo(bizInpApplC1);
			}
			if(c2Flag){
				BizInpApplC2 bizInpApplC2 = new BizInpApplC2();
				bizInpApplC2.setC5Isdpif2(bizInpApplC1.getC5Isdpif2());
				bizInpApplC2.setC6Ethnic2(bizInpApplC1.getC6Ethnic2());
				bizInpApplC2.setAppId(appids);
				bizInpApplC1Dao.updateBizInpApplC2Info(bizInpApplC2);
			}
		}
	}

	@Override
	public String queryTelno(String c1Cotel,String ydjFlag) {
		String msg ="";
		String telno = null;
		String substring2 = c1Cotel.substring(0, 3);
		telno =substring2;
		Map<String,Object> map = new HashMap<String,Object>();
		if ("1".equals(ydjFlag)){
			ydjFlag ="2";
		} else {
			ydjFlag ="1";
		}
		map.put("telNo", telno);
		map.put("ydjFlag",ydjFlag);
		int count = approveZipcodeDao.queryTelno(map);
		if (count == 0){
			String substring = c1Cotel.substring(0, 4);
			telno = substring;
			map.put("telNo", telno);
			int no = approveZipcodeDao.queryTelno(map);
			if (no == 0) {
				msg = "无网点";//"请填写正确单位电话区号！正确格式：01012345678。";
			}
		}
		return msg;
	}

	@Override
	public Map<String, Object> queryCreditCheckInfo(Map<String, String> argMap) throws CoreException {
		Map<String, Object> custInfoMap = creditCheckSearchDao.queryCreditCustInfo(argMap);
		String  blockCode = (String) custInfoMap.get("blockCode");
		String creditDecisionResult = (String) custInfoMap.get("creditDecisionResult");
		if (blockCode!=null&&!"".equals(blockCode)){
			blockCode = blockCode.trim();
			if ("H100".equals(blockCode)){
				blockCode = "高风险通过(H100)";
			} else if ("H900".equals(blockCode)){
				blockCode = "高风险未通过(H900)";
			} else if ("M100".equals(blockCode)){
				blockCode = "中风险通过(M100)";
			} else if ("M900".equals(blockCode)){
				blockCode = "中风险未通过(M900)";
			} else if ("L100".equals(blockCode)){
				blockCode = "低风险通过(L100)";
			} else if ("L900".equals(blockCode)){
				blockCode = "低风险未通过(L900)";
			} else if ("1".equals(blockCode)){
				blockCode = "400A";
			} else if ("2".equals(blockCode)){
				blockCode = "900";
			} else if ("3".equals(blockCode)){
				blockCode = "100";
			} 
		} else if ("S100".equals(creditDecisionResult)){
			blockCode = "S100";
		} 
		String verifyinfoFlag = (String)custInfoMap.get("verifyinfoFlag");
		if (verifyinfoFlag!=null && !"".equals(verifyinfoFlag)){
			if ("1".equals(verifyinfoFlag)){
				verifyinfoFlag = "是";
			} else if ("0".equals(verifyinfoFlag)){
				verifyinfoFlag = "否";
			} 
		}
		custInfoMap.put("blockCode", blockCode);
		custInfoMap.put("verifyinfoFlag", verifyinfoFlag);
		Map<String, Object> resultMap = new HashMap<String,Object>();
		if(custInfoMap!=null){
			resultMap.putAll(custInfoMap);
		}
		return resultMap;
	}

	@Override
	public List<Object> queryCreditInvestigationSurvey(String appId) {
		List<Object> creditRefuseReasonList = new ArrayList<Object>();
		//4二次补件原因
		List<TelcheckAddnote> telcheckAddnote = creditCheckSearchDao.selectTelcheckAddnote(appId);
		if (!telcheckAddnote.isEmpty()){
			for (TelcheckAddnote twoPatchList : telcheckAddnote) {
				creditRefuseReasonList.add(twoPatchList);
			}
		}
		return creditRefuseReasonList;
	}

	@Override
	public Map<String,String> querySVoiceConclusion(String appId) {
		String  appIdSub = appId.substring(0,15) + "%";
		return policeDao.selectSVoiceInfo(appIdSub);
	}

	@Override
	public List<Object> querySVoiceBack(String appId) {
		List<Object> svoiceBackList = new ArrayList<Object>();
		//智能语音退回
		List<Map<String,Object>> svoiceBack = creditCheckSearchDao.selectSVoiceBackMemo(appId);
		if (!svoiceBack.isEmpty()){
			for (Map<String,Object> svoiceMap : svoiceBack) {
				svoiceBackList.add(svoiceMap);
			}
		}
		return svoiceBackList;
	}

	@Override
	public List<Object> queryApprovalBack(String appId) {
		List<Object> creditRefuseReasonList = new ArrayList<Object>();
		//2审批退回
		List<Map<String,Object>> approvalBack = creditCheckSearchDao.selectApprovalBackMemo(appId);
		if (!approvalBack.isEmpty()){
			for (Map<String,Object> approvalMap : approvalBack) {
				creditRefuseReasonList.add(approvalMap);
			}
		}
		return creditRefuseReasonList;
	}
	
	//3 质检
	@Override
	public List<Object> queryCheckingDetail(String appId) {
		List<Object> checkingDetailList = new ArrayList<Object>();
		List<Map<String,Object>> checkingDetail = creditCheckSearchDao.selectCheckingDetail(appId);
		if (!checkingDetail.isEmpty()){
			for (Map<String,Object> checkingMap : checkingDetail) {
				String checkResult = (String) checkingMap.get("checkResult");
				String message= "";
				if ("0".equals(checkResult)){
					message ="正常";
				} else if ("1".equals(checkResult)){
					message ="问题-申请资料类";
				} else if ("2".equals(checkResult)){
					message ="问题-流程类";
				} else if ("3".equals(checkResult)){
					message ="问题-核实话术类";
				} else if ("4".equals(checkResult)){
					message ="问题-注记类";
				} else if ("5".equals(checkResult)){
					message ="问题-信息采集类";
				} else if ("6".equals(checkResult)){
					message ="问题-审批评估类";
				} else if ("7".equals(checkResult)){
					message ="问题-工单投诉类";
				} else if ("8".equals(checkResult)){
					message ="问题-其他类";
				} 
				String mess = "";
				String memo = (String) checkingMap.get("memo");
				if (null!=memo&&!"".equals(memo)){
					mess = "&&&备注:"+memo;
				}
				//String lstDate = (String) checkingMap.get("lstDate");
				String bigMemo = "质检结果:"+message+";"+mess;
				checkingMap.put("bigMemo", bigMemo);
				checkingDetailList.add(checkingMap);
			}
		}
		return checkingDetailList;
	}
	
	@Override
	public void updateByAutoId(TelcheckAddnote note) {
		note.setUpdateDate(new Date());
		telcheckAddnoteDao.updateByAutoIdTelcheckAddnote(note);
	}

	@Override
	public void saveOrUpdateTelcheckAddNoteMsg(Map map) {
		String flagStr = (String) map.get("flagStr");//标志位， 0删除   1新增  2修改
		String ydjFlag = (String) map.get("ydjFlag");
		String appId = (String) map.get("appId");
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("appId", appId);
		String matchingCardFlag = (String)map.get("matchingCardFlag");
		if("0".equals(flagStr)){//删除 
			String talId = (String) map.get("talId");
			param.put("talId", talId);
			telcheckAddnoteDao.deleteTelcheckAddNoteByTalId(param);
		}
		if("2".equals(flagStr)){//修改 
			Date date =new  Date();
			String jsonString = JSON.toJSONString(map);
			TelcheckAddnote note = JSON.parseObject(jsonString, TelcheckAddnote.class);
			note.setUpdateDate(date);
			telcheckAddnoteDao.updateTelcheckAddNoteByTalId(note);
		}
		if("1".equals(flagStr)){//新增 
		if("1".equals(ydjFlag)&&!"0".equals(matchingCardFlag)){//易达金有套卡 
				String talId=StringUtil.randomUUIDPlainString();//修改 删除 同步处理时使用 
				Date date =new  Date();
				String jsonString = JSON.toJSONString(map);
				TelcheckAddnote note = JSON.parseObject(jsonString, TelcheckAddnote.class);
				note.setCrtDate(date);
				note.setTalId(talId);
				note.setAutoId(StringUtil.randomUUIDPlainString());
				telcheckAddnoteDao.insert(note);
				//下面是对套卡的插入处理 
				note.setAutoId(StringUtil.randomUUIDPlainString());
				String currAppId=note.getAppId();
				String currAppIdFront=currAppId.substring(0,15);//流水号 前十五位
				String currAppIdLast=currAppId.substring(15);//流水号 最后一位
				String matchAppId=null;
				if("1".equals(currAppIdLast)){
					matchAppId=currAppIdFront+"2";
				}else{
					matchAppId=currAppIdFront+"1";
				}
				note.setAppId(matchAppId);
				telcheckAddnoteDao.insert(note);
		}else{
			String talId=StringUtil.randomUUIDPlainString();//修改 删除 同步处理时使用 
			Date date =new  Date();
			String jsonString = JSON.toJSONString(map);
			TelcheckAddnote note = JSON.parseObject(jsonString, TelcheckAddnote.class);
			note.setCrtDate(date);
			note.setTalId(talId);
			note.setAutoId(StringUtil.randomUUIDPlainString());
			telcheckAddnoteDao.insert(note);
		}
		}
	}

	@Override
	public void saveTelcheckAddNoteCommitMsg(Map map) {
		String ydjFlag = (String) map.get("ydjFlag");
		String matchingCardFlag = (String)map.get("matchingCardFlag");
		if("1".equals(ydjFlag)&&!"0".equals(matchingCardFlag)){//易达金有套卡 
			String talId=StringUtil.randomUUIDPlainString();//修改 删除 同步处理时使用 
			Date date =new  Date();
			String jsonString = JSON.toJSONString(map);
			TelcheckAddnote note = JSON.parseObject(jsonString, TelcheckAddnote.class);
			note.setCrtDate(date);
			note.setTalId(talId);
			note.setAutoId(StringUtil.randomUUIDPlainString());
			telcheckAddnoteDao.insert(note);
			//下面是对套卡的插入处理 
			note.setAutoId(StringUtil.randomUUIDPlainString());
			String currAppId=note.getAppId();
			String currAppIdFront=currAppId.substring(0,15);//流水号 前十五位
			String currAppIdLast=currAppId.substring(15);//流水号 最后一位
			String matchAppId=null;
			if("1".equals(currAppIdLast)){
				matchAppId=currAppIdFront+"2";
			}else{
				matchAppId=currAppIdFront+"1";
			}
			note.setAppId(matchAppId);
			telcheckAddnoteDao.insert(note);
	}else{
		String talId=StringUtil.randomUUIDPlainString();//修改 删除 同步处理时使用 
		Date date =new  Date();
		String jsonString = JSON.toJSONString(map);
		TelcheckAddnote note = JSON.parseObject(jsonString, TelcheckAddnote.class);
		note.setCrtDate(date);
		note.setTalId(talId);
		note.setAutoId(StringUtil.randomUUIDPlainString());
		telcheckAddnoteDao.insert(note);
	}
	}

	@Override
	public void updateTelcheckAddNoteByTalId(Map map) {
		Date date =new  Date();
		String jsonString = JSON.toJSONString(map);
		TelcheckAddnote note = JSON.parseObject(jsonString, TelcheckAddnote.class);
		note.setUpdateDate(date);
		telcheckAddnoteDao.updateTelcheckAddNoteByTalId(note);
	}

	@Override
	public void updateBizInpApplComporeByAppId(Map<Object, Object> param) {
		telcheckAddnoteDao.updateBizInpApplComporeByAppId(param);
	}

	@Override
	public T5eEsMiddle queryT5eEsMiddle(Map<String, Object> dataMap) {
		String fullName = (String) dataMap.get("fullName");
		String cardType = (String) dataMap.get("cardType");
		String cardNo = (String) dataMap.get("cardNo");
		dataMap.put("fullName", fullName);
		dataMap.put("cardType", cardType);
		dataMap.put("cardNo", cardNo);
		return telcheckAddnoteDao.queryT5eEsMiddle(dataMap);
		
	}

}
