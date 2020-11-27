package com.huaxia.opas.service.credit.impl;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.annotation.Resource;
import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.apply.ApplyLifeCicleDao;
import com.huaxia.opas.dao.apply.BizInpApplC1Dao;
import com.huaxia.opas.dao.apply.BizInpApplDao;
import com.huaxia.opas.dao.credit.CreditCheckDao;
import com.huaxia.opas.dao.credit.PatchboltDao;
import com.huaxia.opas.dao.sysparm.AddrRiskListDao;
import com.huaxia.opas.dao.sysparm.CompanyRiskDao;
import com.huaxia.opas.dao.sysparm.IdentityRiskDao;
import com.huaxia.opas.dao.sysparm.TelRiskListDao;
import com.huaxia.opas.dao.system.ApUserDao;
import com.huaxia.opas.dao.thirdparty.PoliceDao;
import com.huaxia.opas.domain.allot.AllotApplyAllot;
import com.huaxia.opas.domain.apply.ApplyLifeCicle;
import com.huaxia.opas.domain.credit.Candidate;
import com.huaxia.opas.domain.credit.CreditCheck;
import com.huaxia.opas.domain.credit.CreditSVoice;
import com.huaxia.opas.domain.input.BizInpApplC1;
import com.huaxia.opas.domain.riskcheck.AddrRiskList;
import com.huaxia.opas.domain.riskcheck.CompanyRisk;
import com.huaxia.opas.domain.riskcheck.TelRiskList;
import com.huaxia.opas.domain.sysparm.IdentityRisk;
import com.huaxia.opas.domain.system.ApUser;
import com.huaxia.opas.service.credit.CreditCheckService;
import com.huaxia.opas.util.StringUtil;
import com.huaxia.opas.util.UUIDGenerator;

public class CreditCheckServiceImpl implements CreditCheckService, Serializable {
	
	private static final long serialVersionUID = 2367700226995386599L;

	@Resource(name = "creditCheckDao")
	private CreditCheckDao creditCheckDaoImp;
	
	@Resource(name = "bizInpApplC1Dao")
	private BizInpApplC1Dao bizInpApplC1DaoImp;
	
	@Resource(name = "addrRiskListDao")
	private AddrRiskListDao addrRiskListDao;
	
	@Resource(name = "companyRiskDao")
	private CompanyRiskDao companyRiskDao;
	
	@Resource(name="identityRiskDao")
	private IdentityRiskDao identityRiskDao;
	
	@Resource(name = "telRiskListDao")
	private TelRiskListDao telRiskListDao;
	
	@Resource(name = "patchboltDao")
	private PatchboltDao patchboltDao;
	
	@Resource(name = "applyLifeCicleDao")
	private ApplyLifeCicleDao applyLifeCicleDao;
	
	@Resource(name = "apUserDao")
	private ApUserDao apUserDao;
	
	@Resource(name = "bizInpApplDao")
	private BizInpApplDao bizInpApplDao;

	@Resource(name ="policeDao")
	private PoliceDao policeDao;

	public CreditCheckDao getCreditCheckDaoImp() {
		return creditCheckDaoImp;
	}

	public void setCreditCheckDaoImp(CreditCheckDao creditCheckDaoImp) {
		this.creditCheckDaoImp = creditCheckDaoImp;
	}

	@Override
	public Map<String, Object> selectCreditCheckPage(Map<String, Object> paraMap, int begNum, int pageNum) {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		String total = creditCheckDaoImp.selectCreditCheckCount(paraMap);
		List<CreditCheck> rows = creditCheckDaoImp.selectCreditCheckPage(paraMap, begNum, pageNum);
		String ydjFlag = null,appId=null,matchingCardFlag=null;
		for (int j = 0; j < rows.size(); j++) {
			/**
			 * 可信身份认证信息   需求中 要求加的
			 * 提示栏位对于未查询公安的（未发起查询的）申请件显示“P”。未查询公安是指简项公安及可信认证身份信息均未查询成功的。
			 */
			String check_prompt = "";//提示 （二卡/补件完成/P(公安未匹配)/R（人行未匹配））
			ydjFlag=rows.get(j).getCheck_ydjFlag();//易达金标示 1易达金 2标准卡
			appId=rows.get(j).getCheck_number();//流水号
			matchingCardFlag=rows.get(j).getMatchingCardFlag();//套卡标志:0单独一张申请件;1:套卡的附属卡;2:套卡的申请卡
			if("1".equals(ydjFlag)&&"1".equals(matchingCardFlag)){
				if(appId.endsWith("1")){
					appId=appId.substring(0,15)+"2";
				}else if(appId.endsWith("2")){
					appId=appId.substring(0,15)+"1";
				}
			}
			//人行
			/*Integer patchStatusR=pbocDao.selectCountPbocPersonInfoByAppId(appId);
			rows.get(j).setPatchStatusR(patchStatusR);*/
			//公安
			Integer patchStatusP=policeDao.selectPoliceCountByAppId(appId);
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
			//智能语意
			Integer patchStatusS=creditCheckDaoImp.selectCallSVoiceCount(appId);
			if (patchStatusP==0 && patchStatusPP==0) {
				check_prompt+="P ";
			}
			if (patchStatusS != 0){
				check_prompt+="Y ";
			}
			rows.get(j).setCheck_prompt(check_prompt);
		}
		dataMap.put("total", Integer.parseInt(total));
		dataMap.put("rows", rows);
		return dataMap;
	}

	@Override
	public CreditCheck selectCreditCheckByApp_id(Map<String, String> paraMap) {
		return creditCheckDaoImp.selectCreditCheckByApp_id(paraMap);
	}

	@Override
	public int updateCreditCheck(CreditCheck creditCheck) {
		return creditCheckDaoImp.updateCreditCheck(creditCheck);
	}

	@Override
	public Map<String, Object> selectCreditCheckCheatPage(Map<String, Object> paraMap, int begNum, int pageNum) {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		String total = creditCheckDaoImp.selectCreditCheckCheatCount(paraMap);
		List<CreditCheck> rows = creditCheckDaoImp.selectCreditCheckCheatPage(paraMap, begNum, pageNum);
		dataMap.put("total", Integer.parseInt(total));
		dataMap.put("rows", rows);
		return dataMap;
	}

	@Override
	public void submitCheat(List<CreditCheck> lists, CreditCheck credit) throws Exception {
		String subType = creditCheckDaoImp.querySubmitTypeByAppId(credit.getCheck_number());
		if(null!=subType)
			return;
		StringBuffer sb = new StringBuffer();
		List<CreditCheck> creditChecks = null;
		// 根据申请进件号查询提报原因码
		List<CreditCheck> credit1s = creditCheckDaoImp.selectReasonByAppId(credit.getCheck_number());
		// 页面选择添加提报原因码
		if (lists != null && lists.size() > 0) {
			creditChecks = new ArrayList<CreditCheck>();
			for (int i = 0; i < lists.size(); i++) {
				boolean falg = false;
				CreditCheck check = lists.get(i);
				sb.append(check.getCheck_reasonTypeCode()).append(",");
				// 数据库返回提报原因码
				if (credit1s != null && credit1s.size() > 0) {
					for (int j = 0; j < credit1s.size(); j++) {
						CreditCheck credit1 = credit1s.get(j);
						// 添加原因码已在数据库中
						if (credit1.getCheck_number().equals(check.getCheck_number())
								&& credit1.getCheck_reasonTypeCode().equals(check.getCheck_reasonTypeCode())) {
							check.setCheck_reasonAutoId(credit1.getCheck_reasonAutoId());
							falg = true;
						}
					}
				}

				if (falg) {
					// 存在就修改已有的数据库
					creditCheckDaoImp.updateReasonByAppId(check);
				} else {
					// 不存在就添加到数据库
					creditChecks.add(check);
				}
			}
		}

		// 一次性添加多条数据
		if (creditChecks != null && creditChecks.size() > 0) {
			creditCheckDaoImp.insertReasonList(creditChecks);
		}
		if (credit1s != null && credit1s.size() > 0) {
			for (int i = 0; i < credit1s.size(); i++) {
				if (sb.toString().indexOf(credit1s.get(i).getCheck_reasonTypeCode()) < 0) {
					creditCheckDaoImp.deleteReasonById(credit1s.get(i).getCheck_reasonAutoId());
				}
			}
		}

		// 修改 申请件分配表
		//提报人把件提交给本人所在组里拥有复核员角色的人
		
		creditCheckDaoImp.updateCreditCheck(credit);
		credit.setCheck_autoId(UUIDGenerator.getUUID());
		BizInpApplC1 bizinpapplc1 = patchboltDao.querybizInpApplList(credit.getCheck_number());
		String ydjFlag = bizinpapplc1.getYdjFlag();
		String currNode = creditCheckDaoImp.queryCurrNodeByAppId(credit.getCheck_number());
		credit.setCheck_currNode(currNode);
		credit.setCheck_ydjFlag(ydjFlag);
		creditCheckDaoImp.insertCopyOpasAllotApplyAllot(credit.getCheck_number());
		
		Map<String,Object> userMap=new HashMap<String,Object>();//全流程记录
		userMap.put("userCode", credit.getCheck_lastOU());
		Map<String,Object> apUserMap=bizInpApplDao.selectApUserByUuIdUserCode(userMap);
		String userCode=apUserMap.get("userCode").toString();
		String userName=apUserMap.get("userName").toString();
			ApplyLifeCicle a=new ApplyLifeCicle();
			a.setAppId(credit.getCheck_number());
			a.setOperateResult("完成");
			a.setCrtDate(new Date());
			a.setUuid(StringUtil.randomUUIDPlainString());
			if(!"".equals(userCode)){
				a.setOperater(userName+"-"+userCode);
				a.setCrtUser(userCode);
			}else{
				a.setOperater("systemTb");
				a.setCrtUser("systemTb");
			}
			if("1".equals(ydjFlag)){
				if(apUserMap!=null){
					a.setOperateDesc(userName+"将申请件提交到易达金反欺诈复核队列");
					}
			}else if("2".equals(ydjFlag)){
				if(apUserMap!=null){
					a.setOperateDesc(userName+"将申请件提交到标准卡反欺诈复核队列");
					
					}
			}
			applyLifeCicleDao.addApplyLifeCicle(a);
	}

	@Override
	public int insertReasonInfo(CreditCheck creditCheck) {

		return creditCheckDaoImp.insertReasonInfo(creditCheck);
	}

	@Override
	public List<CreditCheck> selectMentionAntiFraud(Map<String, String> paraMap) {

		return creditCheckDaoImp.selectMentionAntiFraud(paraMap);
	}

	@Override
	public int insertOpasSubmitFraudInfo(CreditCheck creditCheck) {

		return creditCheckDaoImp.insertOpasSubmitFraudInfo(creditCheck);
	}

	@Override
	public void cheatCheck(CreditCheck creditCheck, String type,String userUuid,Map map) throws Exception {
		if (!"back".equals(type)) {
			String checkCurrNode = creditCheckDaoImp.queryCurrNodeByAppId(creditCheck.getCheck_number());
			if("05".equals(checkCurrNode))
				return;
			String appId = creditCheck.getCheck_number();
			String reason ="";
			String orgName = "";
			//欺诈原因码
			List <Map<String,Object>> reasonCode = creditCheckDaoImp.queryReasonCodeByAppId(appId);
			
			for(int i=0;i<reasonCode.size();i++){
				if(reasonCode.get(i).get("REASON_CODE").toString().substring(0, reasonCode.get(i).get("REASON_CODE").toString().length()-1).equals((String)map.get("reasonCode"))){
					reason = reasonCode.get(i).get("REASON_CODE").toString().substring(0, reasonCode.get(i).get("REASON_CODE").toString().length()-1);	
					orgName = creditCheckDaoImp.queryOrgNameByAppId((String)map.get("orgNo"));
				}			
			}
			if(!"".equals(reason)){
				String check_lastOU = creditCheckDaoImp.queryCurrOptUserByAppId(creditCheck.getCheck_number());//上级操作人
				creditCheck.setCheck_lastOU(check_lastOU);
				//String crtUser = creditCheck.getCheck_currOptUser();
				int count = creditCheckDaoImp.countOpasSubmitfraudInfoByAppID(appId);
				//确认欺诈进入欺诈分件环节
				//插入提报反欺诈信息表
				creditCheck.setInvest_autoId(UUIDGenerator.getUUID());
				creditCheck.setInvest_crtUser(null);
				if(count>0){
					String currNode = creditCheckDaoImp.queryCurrNodeByAppId(appId);
					creditCheck.setCheck_currNode(currNode);
					creditCheckDaoImp.updateOpasSubmitFraudInfo(creditCheck);
				}else{
					String currNode = creditCheckDaoImp.queryCurrNodeByAppId(appId);
					creditCheck.setCheck_currNode(currNode);
					creditCheckDaoImp.insertOpasSubmitFraudInfo(creditCheck);	
				}
				creditCheck.setInvest_crtUser(null);
				creditCheck.setCheck_lastOptQueue(null);
				creditCheck.setCheck_lastOptGroup(orgName);
				creditCheck.setCheck_lastOU(null);
				creditCheck.setCheck_lastOD(null);
				creditCheck.setCheck_currOptGroup((String)map.get("orgNo"));
				creditCheck.setCheck_currOptUser(null);
				creditCheck.setCheck_queueDate(null);
				creditCheck.setCheck_currNode("05");
				creditCheck.setCheck_currStatus("1");
				creditCheck.setCheck_submitType("1");
				//更新分件信息表
				creditCheckDaoImp.updateCreditCheck1(creditCheck);
				//生命周期表中插入数据
				ApplyLifeCicle applyLifeCicle = qryApplyLifeCicle(userUuid);
				applyLifeCicle.setAppId(appId);
				ApUser apUser  = apUserDao.queryApUserByUserId(userUuid);
				String userName = apUser.getUserName();
				applyLifeCicle.setOperateDesc(userName + "欺诈复核通过进入贷后欺诈组");
				applyLifeCicle.setUuid(UUID.randomUUID().toString().replace("-", ""));
				applyLifeCicleDao.addApplyLifeCicle(applyLifeCicle);
				
				BizInpApplC1 bizinpapplc1 = patchboltDao.querybizInpApplList(appId);
				String ydjFlag = bizinpapplc1.getYdjFlag();
				char[] arr = appId.toCharArray();
				if(ydjFlag.equals("1")){
					if(!"0".equals(bizinpapplc1.getMatchingCardFlag())&&!"".equals(bizinpapplc1.getMatchingCardFlag())&&null!=bizinpapplc1.getMatchingCardFlag()){
						if(arr[15]=='1'){
							arr[15]='2';
							creditCheck.setCheck_number(new String(arr));
							creditCheck.setInvest_autoId(UUIDGenerator.getUUID());
							String check_lastOU1 = creditCheckDaoImp.queryCurrOptUserByAppId(creditCheck.getCheck_number());//上级操作人
							creditCheck.setCheck_lastOU(check_lastOU1);
							if(count>0){
								String currNode = creditCheckDaoImp.queryCurrNodeByAppId(new String(arr));
								creditCheck.setCheck_currNode(currNode);
								creditCheckDaoImp.updateOpasSubmitFraudInfo(creditCheck);
							}else{
								String currNode = creditCheckDaoImp.queryCurrNodeByAppId(new String(arr));
								creditCheck.setCheck_currNode(currNode);
								creditCheckDaoImp.insertOpasSubmitFraudInfo(creditCheck);	
							}
							//creditCheckDaoImp.updateCreditCheck1(creditCheck);
						}else{
							arr[15]='1';
							creditCheck.setCheck_number(new String(arr));
							creditCheck.setInvest_autoId(UUIDGenerator.getUUID());
							String check_lastOU1 = creditCheckDaoImp.queryCurrOptUserByAppId(creditCheck.getCheck_number());//上级操作人
							creditCheck.setCheck_lastOU(check_lastOU1);
							if(count>0){
								String currNode = creditCheckDaoImp.queryCurrNodeByAppId(new String(arr));
								creditCheck.setCheck_currNode(currNode);
								creditCheckDaoImp.updateOpasSubmitFraudInfo(creditCheck);
							}else{
								String currNode = creditCheckDaoImp.queryCurrNodeByAppId(new String(arr));
								creditCheck.setCheck_currNode(currNode);
								creditCheckDaoImp.insertOpasSubmitFraudInfo(creditCheck);	
							}
							
						}
						creditCheck.setCheck_currNode("05");
						//更新分件信息表
						creditCheck.setCheck_lastOU(null);
						creditCheckDaoImp.updateCreditCheck1(creditCheck);
						applyLifeCicle.setAppId(new String(arr));
						applyLifeCicle.setOperateDesc(userName + "欺诈复核通过进入欺诈组");
						applyLifeCicle.setUuid(UUID.randomUUID().toString().replace("-", ""));
						applyLifeCicleDao.addApplyLifeCicle(applyLifeCicle);
					}
				}
				
			}else{
				String check_lastOU = creditCheckDaoImp.queryCurrOptUserByAppId(creditCheck.getCheck_number());//上级操作人
				creditCheck.setCheck_lastOU(check_lastOU);
				//String crtUser = creditCheck.getCheck_currOptUser();
				int count = creditCheckDaoImp.countOpasSubmitfraudInfoByAppID(appId);
				//确认欺诈进入欺诈分件环节
				//插入提报反欺诈信息表
				creditCheck.setInvest_autoId(UUIDGenerator.getUUID());
				creditCheck.setInvest_crtUser(null);
				if(count>0){
					String currNode = creditCheckDaoImp.queryCurrNodeByAppId(appId);
					creditCheck.setCheck_currNode(currNode);
					creditCheckDaoImp.updateOpasSubmitFraudInfo(creditCheck);
				}else{
					String currNode = creditCheckDaoImp.queryCurrNodeByAppId(appId);
					creditCheck.setCheck_currNode(currNode);
					creditCheckDaoImp.insertOpasSubmitFraudInfo(creditCheck);	
				}
				//历史表中插入数据
			//	creditCheckDaoImp.insertOpasSubmitFraudInfoHis(creditCheck);
				
				
				//清空组  队列  操作人 操作日期等信息 
				creditCheck.setCheck_lastOptQueue(null);
				creditCheck.setCheck_lastOptGroup(null);
				creditCheck.setCheck_lastOU(null);
				creditCheck.setCheck_lastOD(null);
				creditCheck.setCheck_currOptGroup(null);
				creditCheck.setCheck_currOptUser(null);
				creditCheck.setCheck_queueDate(null);
				creditCheck.setCheck_currNode("05");
				creditCheck.setCheck_currStatus("0");
				creditCheck.setCheck_submitType("1");
				//更新分件信息表
				creditCheckDaoImp.updateCreditCheck1(creditCheck);
				//生命周期表中插入数据
				ApplyLifeCicle applyLifeCicle = qryApplyLifeCicle(userUuid);
				applyLifeCicle.setAppId(appId);
				ApUser apUser  = apUserDao.queryApUserByUserId(userUuid);
				String userName = apUser.getUserName();
				applyLifeCicle.setOperateDesc(userName + "欺诈复核通过进入欺诈池");
				applyLifeCicle.setUuid(UUID.randomUUID().toString().replace("-", ""));
				applyLifeCicleDao.addApplyLifeCicle(applyLifeCicle);
				//根据appId判断该申请件是否为套卡
				//根据appId查询进件卡
				
				BizInpApplC1 bizinpapplc1 = patchboltDao.querybizInpApplList(appId);
				String ydjFlag = bizinpapplc1.getYdjFlag();
				//0-无套卡标识  1-主卡  2-套卡
				char[] arr = appId.toCharArray();
				if(bizinpapplc1 != null){
					if("1".equals(ydjFlag)){
				
					if(!"0".equals(bizinpapplc1.getMatchingCardFlag())&&!"".equals(bizinpapplc1.getMatchingCardFlag())&&null!=bizinpapplc1.getMatchingCardFlag()){
						if(arr.length==16&&arr[15]=='1'){
							arr[15]='2';
							creditCheck.setCheck_number(new String(arr));
							creditCheck.setInvest_autoId(UUIDGenerator.getUUID());
							String check_lastOU1 = creditCheckDaoImp.queryCurrOptUserByAppId(creditCheck.getCheck_number());//上级操作人
							creditCheck.setCheck_lastOU(check_lastOU1);
							if(count>0){
								String currNode = creditCheckDaoImp.queryCurrNodeByAppId(new String(arr));
								creditCheck.setCheck_currNode(currNode);
								creditCheckDaoImp.updateOpasSubmitFraudInfo(creditCheck);
							}else{
								String currNode = creditCheckDaoImp.queryCurrNodeByAppId(new String(arr));
								creditCheck.setCheck_currNode(currNode);
								creditCheckDaoImp.insertOpasSubmitFraudInfo(creditCheck);	
							}
							//复核套卡
							//插入提报反欺诈信息表
							//历史表中插入数据
						//	creditCheckDaoImp.insertOpasSubmitFraudInfoHis(creditCheck);
							applyLifeCicle.setAppId(new String(arr));
							applyLifeCicle.setOperateDesc(userName + "欺诈复核通过进入欺诈池");
							applyLifeCicle.setUuid(UUID.randomUUID().toString().replace("-", ""));
							applyLifeCicleDao.addApplyLifeCicle(applyLifeCicle);
							creditCheck.setCheck_currNode("05");
							//更新分件信息表
							creditCheck.setCheck_lastOU(null);
							creditCheckDaoImp.updateCreditCheck1(creditCheck);
						}else{
							arr[15]='1';
							creditCheck.setCheck_number(new String(arr));
							String check_lastOU1 = creditCheckDaoImp.queryCurrOptUserByAppId(creditCheck.getCheck_number());//上级操作人
							creditCheck.setCheck_lastOU(check_lastOU1);
							//复核套卡
							//插入提报反欺诈信息表
							creditCheck.setInvest_autoId(UUIDGenerator.getUUID());
							if(count>0){
								String currNode = creditCheckDaoImp.queryCurrNodeByAppId(new String(arr));
								creditCheck.setCheck_currNode(currNode);
								creditCheckDaoImp.updateOpasSubmitFraudInfo(creditCheck);
							}else{
								String currNode = creditCheckDaoImp.queryCurrNodeByAppId(new String(arr));
								creditCheck.setCheck_currNode(currNode);
								creditCheckDaoImp.insertOpasSubmitFraudInfo(creditCheck);	
							}
							//历史表中插入数据
						//	creditCheckDaoImp.insertOpasSubmitFraudInfoHis(creditCheck);
							creditCheck.setCheck_currNode("05");
							creditCheck.setCheck_lastOU(null);
							//更新分件信息表
							creditCheckDaoImp.updateCreditCheck1(creditCheck);
							//生命周期表插入
							applyLifeCicle.setAppId(new String(arr));
							applyLifeCicle.setOperateDesc(userName + "欺诈复核通过进入欺诈池");
							applyLifeCicle.setUuid(UUID.randomUUID().toString().replace("-", ""));
							applyLifeCicleDao.addApplyLifeCicle(applyLifeCicle);
						}
					}
					
					}
				}
			}
			
			
		}else{
			String appId = creditCheck.getCheck_number();
			//String currNode = creditCheckDaoImp.queryCurrNodeByAppId(appId);
			String check_lastOU = creditCheckDaoImp.queryCurrOptUserByAppId(creditCheck.getCheck_number());//上级操作人
			creditCheck.setCheck_lastOU(creditCheck.getCheck_currOptUser());
//			String qzCurrNode = creditCheckDaoImp.queryqzCurrNodeByAppId(appId);
//			if(qzCurrNode==null||"".equals(qzCurrNode)){
//				qzCurrNode = "05";
//			}
//			if(Integer.parseInt(currNode)<=Integer.parseInt(qzCurrNode)){
//				creditCheck.setCheck_currNode(currNode);		
//			}else{
//				creditCheck.setCheck_currNode(currNode);
//			}
//			int count = creditCheckDaoImp.countOpasSubmitfraudInfoByAppID(appId);
			creditCheck.setInvest_autoId(UUIDGenerator.getUUID());
//			if(count > 0){
//				creditCheckDaoImp.updateOpasSubmitFraudInfo(creditCheck);
//			}else{
//				creditCheckDaoImp.insertOpasSubmitFraudInfo(creditCheck);	
//			}
			//向分配表历史表中插入数据

			creditCheck.setCheck_autoId(UUIDGenerator.getUUID());
			creditCheckDaoImp.insertOpasSubmitFraudInfoHis(creditCheck);
			creditCheckDaoImp.insertCopyOpasAllotApplyAllot(appId);
			//将当前操作人更新为提报人(即将上次操作人更新为当前操作人),更新申请件分配表
			creditCheck.setCheck_currOptUser(check_lastOU);
			creditCheck.setCheck_SubmitStatus(null);
			creditCheck.setCheck_submitType(null);
			
			creditCheck.setCheck_currStatus("3");
			creditCheck.setCheck_lastOD(new Date());
			creditCheckDaoImp.updateOpasAllotApplyAllot(creditCheck);
			
			creditCheckDaoImp.deleteOpasReasonInfoByAppID(appId);
			creditCheckDaoImp.updateOpasAllotApplySubmitMemo(appId);			
			//根据appId判断该申请件是否为套卡
			//根据appId查询进件卡
			//生命周期表中插入数据
			ApplyLifeCicle applyLifeCicle = qryApplyLifeCicle(userUuid);
			applyLifeCicle.setAppId(appId);
			ApUser apUser = apUserDao.queryApUserByUserId(userUuid);
			String userName = apUser.getUserName();
			applyLifeCicle.setOperateDesc(userName + "欺诈复核退回");
			applyLifeCicle.setUuid(UUID.randomUUID().toString().replace("-", ""));
			applyLifeCicleDao.addApplyLifeCicle(applyLifeCicle);
			BizInpApplC1 bizinpapplc1 = patchboltDao.querybizInpApplList(appId);
			//0-无套卡标识  1-主卡  2-套卡
			char[] arr = appId.toCharArray();
			String ydjFlag = bizinpapplc1.getYdjFlag();
			if("1".equals(ydjFlag)){
		
			if(bizinpapplc1 != null){
				
				if(!"0".equals(bizinpapplc1.getMatchingCardFlag())&&!"".equals(bizinpapplc1.getMatchingCardFlag())&&null!=bizinpapplc1.getMatchingCardFlag()){
					if(arr.length==16&&arr[15]=='1'){
						arr[15]='2';
						creditCheck.setCheck_number(new String(arr));
						creditCheck.setInvest_autoId(UUIDGenerator.getUUID());
						//String check_lastOU1 = creditCheckDaoImp.queryAllotApplyAllotByAppId(creditCheck.getCheck_number());//上级操作人
						//creditCheck.setCheck_lastOU(check_lastOU1);
//						if(count > 0){
//							creditCheckDaoImp.updateOpasSubmitFraudInfo(creditCheck);
//						}else{
//							creditCheckDaoImp.insertOpasSubmitFraudInfo(creditCheck);	
//						}
						//向分配表历史表中插入数据
						creditCheck.setCheck_autoId(UUIDGenerator.getUUID());
						creditCheckDaoImp.insertOpasSubmitFraudInfoHis(creditCheck);
						//更新申请件分配表
						creditCheckDaoImp.updateOpasAllotApplyAllot(creditCheck);
						creditCheckDaoImp.insertCopyOpasAllotApplyAllot(creditCheck.getCheck_number());
						
						creditCheckDaoImp.deleteOpasReasonInfoByAppID(creditCheck.getCheck_number());
						creditCheckDaoImp.updateOpasAllotApplySubmitMemo(creditCheck.getCheck_number());			
						
						applyLifeCicle.setAppId(new String(arr));
						applyLifeCicle.setOperateDesc(userName + "欺诈复核退回");
						applyLifeCicle.setUuid(UUID.randomUUID().toString().replace("-", ""));
						applyLifeCicleDao.addApplyLifeCicle(applyLifeCicle);
						
					}else{
						arr[15]='1';
						creditCheck.setCheck_number(new String(arr));
						//String check_lastOU1 = creditCheckDaoImp.queryAllotApplyAllotByAppId(creditCheck.getCheck_number());//上级操作人
						//creditCheck.setCheck_lastOU(check_lastOU1);
						creditCheck.setInvest_autoId(UUIDGenerator.getUUID());
//						if(count > 0){
//							creditCheckDaoImp.updateOpasSubmitFraudInfo(creditCheck);
//						}else{
//							creditCheckDaoImp.insertOpasSubmitFraudInfo(creditCheck);	
//						}
						//向分配表历史表中插入数据

						creditCheck.setCheck_autoId(UUIDGenerator.getUUID());
						creditCheckDaoImp.insertOpasSubmitFraudInfoHis(creditCheck);
						//更新申请件分配表
						creditCheckDaoImp.updateOpasAllotApplyAllot(creditCheck);
						creditCheckDaoImp.insertCopyOpasAllotApplyAllot(creditCheck.getCheck_number());
						
						creditCheckDaoImp.deleteOpasReasonInfoByAppID(creditCheck.getCheck_number());
						creditCheckDaoImp.updateOpasAllotApplySubmitMemo(creditCheck.getCheck_number());			
						
						applyLifeCicle.setAppId(new String(arr));
						applyLifeCicle.setOperateDesc(userName + "欺诈复核退回");
						applyLifeCicle.setUuid(UUID.randomUUID().toString().replace("-", ""));
						applyLifeCicleDao.addApplyLifeCicle(applyLifeCicle);
						
					}
				}
			}
		}
			
		}

	}

	@Override
	public Map<String, Object> selectCreditCheatInvestigationPage(Map<String, Object> paraMap, int begNum, int pageNum) {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		String total = creditCheckDaoImp.selectCreditCheatInvestigationCount(paraMap);
		List<CreditCheck> rows = creditCheckDaoImp.selectCreditCheatInvestigationPage(paraMap, begNum, pageNum);
		dataMap.put("total", Integer.parseInt(total));
		dataMap.put("rows", rows);
		return dataMap;
	}

	@Override
	public Map<String, Object> selectCreditCheatApprovalPage(CreditCheck creditCheck, int begNum, int pageNum) {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		String total = creditCheckDaoImp.selectCreditCheatApprovalCount(creditCheck);
		List<CreditCheck> rows = creditCheckDaoImp.selectCreditCheatApprovalPage(creditCheck, begNum, pageNum);
		dataMap.put("total",Integer.parseInt(total));
		dataMap.put("rows", rows);
		return dataMap;
	}

	@Override
	public List<CreditCheck> selectOpasApDict() {
		return creditCheckDaoImp.selectOpasApDict();
	}

	@Override
	public void updateCheatInvestigation(CreditCheck creditCheck) {
		creditCheckDaoImp.updateCheatInvestigation(creditCheck);

	}

	@Override
	public void updateOpasAllotApplyAllot(CreditCheck creditCheck) {
		creditCheckDaoImp.updateOpasAllotApplyAllot(creditCheck);

	}

	@Override
	public void deleteOpasReasonInfoByAppID(String app_id) {
		creditCheckDaoImp.deleteOpasReasonInfoByAppID(app_id);
	}

	@Override
	public void deleteOpasSubmitfraudInfoByautoID(String autoId) {
		creditCheckDaoImp.deleteOpasSubmitfraudInfoByautoID(autoId);

	}

	@Override
	public List<CreditCheck> selectOpasOpasSubmitfraudInfoByautoID(String autoId) {

		return creditCheckDaoImp.selectOpasOpasSubmitfraudInfoByautoID(autoId);
	}

	@Override
	public void cheatInvestigationHandle(CreditCheck creditCheck,String userCode,String userUuid) throws Exception {
		String delStatus = creditCheckDaoImp.queryqzVdelStatusByAppId(creditCheck.getCheck_number());
		if("2".equals(delStatus)||"3".equals(delStatus))
			return;
		creditCheckDaoImp.updateCheatInvestigation(creditCheck);
		if("3".equals(creditCheck.getInvest_delStatus())){
			creditCheck.setCheck_lastOU(userCode);//设置调查员为上一操作人 更新到分配表
			creditCheckDaoImp.updateOpasAllotApplyAllot1(creditCheck);
		}else if("2".equals(creditCheck.getInvest_delStatus())){
			String zxCrediter = creditCheckDaoImp.querySubmitFraud(creditCheck.getCheck_number());
			String status = creditCheck.getInvest_delStatus();
			String curUser = creditCheck.getInvest_crtUser();
			creditCheck.setInvest_delStatus("6");
			creditCheck.setInvest_crtUser(userCode);
			creditCheck.setInvest_lastOU(zxCrediter);
			creditCheck.setInvest_autoId(UUIDGenerator.getUUID());
			if("1".equals(delStatus)){
				creditCheck.setInvest_delStatus("9");//欺诈退回队列正常提交欺诈审批欺诈历史表落值为9
				creditCheckDaoImp.insertOpasSubmitFraudInfoHis(creditCheck);
			}else{
				creditCheckDaoImp.insertOpasSubmitFraudInfoHis(creditCheck);
			}
			creditCheck.setInvest_autoId(null);
			creditCheck.setInvest_delStatus(status);
			creditCheck.setInvest_crtUser(curUser);
			creditCheck.setCheck_lastOU(userCode);//设置调查员为上一操作人 更新到分配表
			creditCheckDaoImp.updateOpasAllotApplyAllot1(creditCheck);
		}
		
		
		//根据appId判断该申请件是否为套卡
		//根据appId查询进件卡
		String appId = creditCheck.getCheck_number();
		BizInpApplC1 bizinpapplc1 = patchboltDao.querybizInpApplList(appId);
		//生命周期表中插入数据
		ApUser apUser = apUserDao.queryApUserByUserId(userUuid);
		ApplyLifeCicle applyLifeCicle = qryApplyLifeCicle(userUuid);
		String userName = apUser.getUserName();
		if("2".equals(creditCheck.getInvest_delStatus())){
		applyLifeCicle.setAppId(appId);
	//	String crtUser = creditCheck.getCheck_currOptUser();
		applyLifeCicle.setOperateDesc(userName + "欺诈调查进入欺诈审批未完成队列");
		applyLifeCicleDao.addApplyLifeCicle(applyLifeCicle);
		}
		String ydjFlag = bizinpapplc1.getYdjFlag();
		//0-无套卡标识  1-主卡  2-套卡
		char[] arr = appId.toCharArray();
		if("1".equals(ydjFlag)){
		if(bizinpapplc1 != null){
			
			if(!"0".equals(bizinpapplc1.getMatchingCardFlag())&&!"".equals(bizinpapplc1.getMatchingCardFlag())&&null!=bizinpapplc1.getMatchingCardFlag()){
				if(arr.length==16&&arr[15]=='1'){
					arr[15]='2';
					creditCheck.setCheck_number(new String(arr));
					creditCheck.setInvest_autoId(null);
					//更新提报欺诈结果信息表
					creditCheckDaoImp.updateCheatInvestigation(creditCheck);
					if("3".equals(creditCheck.getInvest_delStatus())){
						//更新申请件分配信息表
						creditCheckDaoImp.updateOpasAllotApplyAllot1(creditCheck);
					}else if("2".equals(creditCheck.getInvest_delStatus())){
						
						String status = creditCheck.getInvest_delStatus();
						String curUser = creditCheck.getInvest_crtUser();
						creditCheck.setInvest_delStatus("6");
						creditCheck.setInvest_crtUser(userCode);
						creditCheck.setInvest_autoId(UUIDGenerator.getUUID());
						if("1".equals(delStatus)){
							creditCheck.setInvest_delStatus("9");//欺诈退回队列正常提交欺诈审批欺诈历史表落值为9
							creditCheckDaoImp.insertOpasSubmitFraudInfoHis(creditCheck);
						}else{
							creditCheckDaoImp.insertOpasSubmitFraudInfoHis(creditCheck);
						}
						creditCheck.setInvest_autoId(null);
						creditCheck.setInvest_delStatus(status);
						creditCheck.setInvest_crtUser(curUser);
						//更新申请件分配信息表
						creditCheckDaoImp.updateOpasAllotApplyAllot1(creditCheck);
					}
					
					if("2".equals(creditCheck.getInvest_delStatus())){
						applyLifeCicle.setAppId(new String(arr));
						applyLifeCicle.setOperateDesc(userName + "欺诈调查进入欺诈审批未完成队列");
						applyLifeCicle.setUuid(UUID.randomUUID().toString().replace("-", ""));
						applyLifeCicleDao.addApplyLifeCicle(applyLifeCicle);
					}
				}else{
					arr[15]='1';
					creditCheck.setCheck_number(new String(arr));
					creditCheck.setInvest_autoId(null);
					//更新提报欺诈结果信息表
					creditCheckDaoImp.updateCheatInvestigation(creditCheck);
					if("3".equals(creditCheck.getInvest_delStatus())){
						//更新申请件分配信息表
						creditCheckDaoImp.updateOpasAllotApplyAllot1(creditCheck);
					}else if("2".equals(creditCheck.getInvest_delStatus())){
						
						String status = creditCheck.getInvest_delStatus();
						String curUser = creditCheck.getInvest_crtUser();
						creditCheck.setInvest_delStatus("6");
						creditCheck.setInvest_crtUser(userCode);
						creditCheck.setInvest_autoId(UUIDGenerator.getUUID());
						if("1".equals(delStatus)){
							creditCheck.setInvest_delStatus("9");//欺诈退回队列正常提交欺诈审批欺诈历史表落值为9
							creditCheckDaoImp.insertOpasSubmitFraudInfoHis(creditCheck);
						}else{
							creditCheckDaoImp.insertOpasSubmitFraudInfoHis(creditCheck);
						}
						creditCheck.setInvest_autoId(null);
						creditCheck.setInvest_delStatus(status);
						creditCheck.setInvest_crtUser(curUser);
						//更新申请件分配信息表
						creditCheckDaoImp.updateOpasAllotApplyAllot1(creditCheck);
					}
					
					if("2".equals(creditCheck.getInvest_delStatus())){
						applyLifeCicle.setAppId(new String(arr));
						applyLifeCicle.setOperateDesc(userName + "欺诈调查进入欺诈审批未完成队列");
						applyLifeCicle.setUuid(UUID.randomUUID().toString().replace("-", ""));
						applyLifeCicleDao.addApplyLifeCicle(applyLifeCicle);
					}
				}
				
			}
		}
		}
		
		
		if ("3".equals(creditCheck.getInvest_delStatus())) {//退回征信
			creditCheck.setCheck_SubmitStatus(null);// 清除 提报状态
			creditCheck.setCheck_submitType(null);// 清除 提报类型
			//在OPAS_SUBMITFRAUD_INFO中查询该条记录的最新一条信息的LAST_OU字段确定征信员
			String zxCrediter = creditCheckDaoImp.querySubmitFraud(creditCheck.getCheck_number());
			creditCheck.setCheck_currOptUser(zxCrediter);//设置征信员
			String orgNo = creditCheckDaoImp.queryGroup(zxCrediter);
			Map<String,Object> userMap=new HashMap<String,Object>();//全流程记录
			userMap.put("userCode", zxCrediter);
			Map<String,Object> apUserMap=bizInpApplDao.selectApUserByUuIdUserCode(userMap);
			String zxCrediterName=apUserMap.get("userName").toString();
			String orgName = creditCheckDaoImp.queryGroupName(zxCrediter);
			creditCheck.setCheck_currOptGroup(orgNo);
			creditCheck.setCheck_lastOptGroup(orgName);
			String curr_node = creditCheckDaoImp.queryqzCurrNodeByAppId(appId);
			creditCheck.setCheck_currNode(curr_node);//设置为征信环节
			// 修改申请件分配表
			creditCheckDaoImp.updateOpasAllotApplyAllot(creditCheck);
			// 复制申请件分配表
			creditCheckDaoImp.insertCopyOpasAllotApplyAllot(creditCheck.getCheck_number());
			
			creditCheck.setInvest_autoId(UUIDGenerator.getUUID());
			creditCheck.setInvest_crtUserName(userName);
			creditCheckDaoImp.insertOpasSubmitFraudInfoHis(creditCheck);
			//生命周期表中插入数据
			if("02".equals(curr_node)){					
				applyLifeCicle.setOperateDesc(userName + "欺诈调查未完成退回"+zxCrediterName+"的征信队列");	
			}else if("03".equals(curr_node)){				
				applyLifeCicle.setOperateDesc(userName + "欺诈调查未完成退回"+zxCrediterName+"的审批队列");
			}
			applyLifeCicle.setAppId(appId);
		//	String crtUser = creditCheck.getCheck_currOptUser();
			applyLifeCicle.setUuid(UUID.randomUUID().toString().replace("-", ""));
			applyLifeCicleDao.addApplyLifeCicle(applyLifeCicle);
			
			
		
			if("1".equals(ydjFlag)){
			if(bizinpapplc1 != null){
				
				if(!"0".equals(bizinpapplc1.getMatchingCardFlag())&&!"".equals(bizinpapplc1.getMatchingCardFlag())&&null!=bizinpapplc1.getMatchingCardFlag()){
					if(arr.length==16&&arr[15]=='1'){
						arr[15]='2';
						creditCheck.setCheck_number(new String(arr));
						
						// 修改申请件分配表
						creditCheckDaoImp.updateOpasAllotApplyAllot(creditCheck);
						// 复制申请件分配表
						creditCheckDaoImp.insertCopyOpasAllotApplyAllot(creditCheck.getCheck_number());
						
						
						applyLifeCicle.setAppId(new String(arr));
						if("02".equals(curr_node)){					
							applyLifeCicle.setOperateDesc(userName + "欺诈调查未完成退回"+zxCrediterName+"的征信队列");	
						}else if("03".equals(curr_node)){				
							applyLifeCicle.setOperateDesc(userName + "欺诈调查未完成退回"+zxCrediterName+"的审批队列");
						}
						applyLifeCicle.setUuid(UUID.randomUUID().toString().replace("-", ""));						
						applyLifeCicleDao.addApplyLifeCicle(applyLifeCicle);
						
						creditCheck.setInvest_autoId(UUIDGenerator.getUUID());
						creditCheckDaoImp.insertOpasSubmitFraudInfoHis(creditCheck);
						
					}else{
						arr[15]='1';
						creditCheck.setCheck_number(new String(arr));
						
						// 修改申请件分配表
						creditCheckDaoImp.updateOpasAllotApplyAllot(creditCheck);
						// 复制申请件分配表
						creditCheckDaoImp.insertCopyOpasAllotApplyAllot(creditCheck.getCheck_number());
						
						
						applyLifeCicle.setAppId(new String(arr));
						applyLifeCicle.setUuid(UUID.randomUUID().toString().replace("-", ""));	
						if("02".equals(curr_node)){					
							applyLifeCicle.setOperateDesc(userName + "欺诈调查未完成退回"+zxCrediterName+"的征信队列");	
						}else if("03".equals(curr_node)){				
							applyLifeCicle.setOperateDesc(userName + "欺诈调查未完成退回"+zxCrediterName+"的审批队列");
						}
						applyLifeCicleDao.addApplyLifeCicle(applyLifeCicle);
						
						creditCheck.setInvest_autoId(UUIDGenerator.getUUID());
						creditCheckDaoImp.insertOpasSubmitFraudInfoHis(creditCheck);
					}
				}
			}
			}
			
		}
		
	}
	//生命周期公共方法
	private ApplyLifeCicle qryApplyLifeCicle(String uuid) throws CoreException{
		ApplyLifeCicle applyLifeCicle = new ApplyLifeCicle();
		ApUser apUser = apUserDao.queryApUserByUserId(uuid.trim());
		String userName = apUser.getUserName();
		String userCode = apUser.getUserCode();
		applyLifeCicle.setUuid(UUID.randomUUID().toString().replace("-", ""));
		applyLifeCicle.setOperater(userName+"-"+userCode);
		applyLifeCicle.setOperateResult("完成");
		applyLifeCicle.setCrtDate(new Date());
		applyLifeCicle.setCrtUser(apUser.getUserCode());
		applyLifeCicle.setLstUpdUser(apUser.getUserCode());
		applyLifeCicle.setLstUpdDate(new Date());
		return applyLifeCicle;
		
	} 
	@Override
	public void insertOpasSubmitFraudInfoHis(CreditCheck creditCheck) {
		creditCheckDaoImp.insertOpasSubmitFraudInfoHis(creditCheck);
	}

	@Override
	public void creditcheatExamineHandle(CreditCheck creditCheck,String userUuid) throws Exception {
		String delStatus = creditCheckDaoImp.queryqzVdelStatusByAppId(creditCheck.getCheck_number());
		if(!"2".equals(delStatus)||"3".equals(delStatus))
			return;
		// 退回征信调查或者同意欺诈返回征信
		if ("3".equals(creditCheck.getInvest_delStatus()) || "4".equals(creditCheck.getInvest_delStatus())) {
			creditCheck.setCheck_SubmitStatus(null);// 清除 提报状态
			creditCheck.setCheck_submitType(null);// 清除 提报类型
			String curr_node = creditCheckDaoImp.queryqzCurrNodeByAppId(creditCheck.getCheck_number());
			String appId = creditCheck.getCheck_number();
			creditCheck.setCheck_currNode(curr_node);//设置为征信环节
			
			//在OPAS_SUBMITFRAUD_INFO中查询该条记录的最新一条信息的LAST_OU字段确定征信员
			String zxCrediter = creditCheckDaoImp.querySubmitFraud(creditCheck.getCheck_number());
			String invest_memo = creditCheckDaoImp.queryInvestMemo(creditCheck.getCheck_number());
			String orgNo = creditCheckDaoImp.queryGroup(zxCrediter);
			String orgName = creditCheckDaoImp.queryGroupName(zxCrediter);
			creditCheck.setCheck_currOptGroup(orgNo);
			creditCheck.setCheck_lastOptGroup(orgName);
			//插入生命周期表
			ApplyLifeCicle applyLifeCicle = qryApplyLifeCicle(userUuid);
			ApUser apUser = apUserDao.queryApUserByUserId(userUuid);
			String userName = apUser.getUserName();

			Map<String,Object> userMap=new HashMap<String,Object>();//全流程记录
			userMap.put("userCode", zxCrediter);
			Map<String,Object> apUserMap=bizInpApplDao.selectApUserByUuIdUserCode(userMap);
			String zxCrediterName=apUserMap.get("userName").toString();
			
			if("3".equals(creditCheck.getInvest_delStatus())){
				if("02".equals(curr_node)){					
					applyLifeCicle.setOperateDesc(userName + "欺诈审批退回"+zxCrediterName+"的征信队列");	
				}else if("03".equals(curr_node)){				
					applyLifeCicle.setOperateDesc(userName + "欺诈审批退回"+zxCrediterName+"的审批队列");
				}
			applyLifeCicle.setAppId(appId);			
			applyLifeCicleDao.addApplyLifeCicle(applyLifeCicle);
			}else{	
					if("02".equals(curr_node)){									
						applyLifeCicle.setOperateDesc(userName + "欺诈审批同意进入"+zxCrediterName+"的征信队列");						
					}else if("03".equals(curr_node)){
						applyLifeCicle.setOperateDesc(userName + "欺诈审批同意进入"+zxCrediterName+"的审批队列");
					}
					applyLifeCicle.setAppId(appId);
				//	applyLifeCicle.setOperateDesc(userName + "欺诈审批同意");
					applyLifeCicle.setUuid(UUID.randomUUID().toString().replace("-", ""));
					applyLifeCicleDao.addApplyLifeCicle(applyLifeCicle);
			}
			
			creditCheck.setCheck_currOptUser(zxCrediter);//设置征信员
			creditCheck.setInvest_memo(invest_memo);
			creditCheck.setInvest_lastOU(zxCrediter);
			// 修改征信提报欺诈结果信息表
			creditCheck.setInvest_crtUserName(userName);
			creditCheckDaoImp.updateCheatInvestigation(creditCheck);
			if("3".equals(creditCheck.getInvest_delStatus())){
				creditCheck.setInvest_autoId(UUIDGenerator.getUUID());
				creditCheck.setInvest_crtUser(apUser.getUserCode());
				creditCheckDaoImp.insertOpasSubmitFraudInfoHis(creditCheck);
			}else{
				creditCheck.setInvest_autoId(UUIDGenerator.getUUID());
				creditCheck.setInvest_crtUser(apUser.getUserCode());
				creditCheckDaoImp.insertOpasSubmitFraudInfoHis(creditCheck);
			}
			creditCheck.setInvest_autoId(null);
			creditCheck.setCheck_lastOU(apUser.getUserCode());
			// 修改申请件分配表
			creditCheckDaoImp.updateOpasAllotApplyAllot(creditCheck);
			creditCheckDaoImp.insertCopyOpasAllotApplyAllot(creditCheck.getCheck_number());
			//creditCheckDaoImp.updateCreditCheck(creditCheck);
			
			//根据appId判断该申请件是否为套卡
			//根据appId查询进件卡
			
			BizInpApplC1 bizinpapplc1 = patchboltDao.querybizInpApplList(appId);
			
			//0-无套卡标识  1-主卡  2-套卡
			char[] arr = appId.toCharArray();
			String ydjFlag = bizinpapplc1.getYdjFlag();
			if("1".equals(ydjFlag)){
			if(bizinpapplc1 != null){
				
				if(!"0".equals(bizinpapplc1.getMatchingCardFlag())&&!"".equals(bizinpapplc1.getMatchingCardFlag())&&null!=bizinpapplc1.getMatchingCardFlag()){
					if(arr.length==16&&arr[15]=='1'){
						arr[15]='2';
						creditCheck.setCheck_number(new String(arr));
						//向分配表历史表中插入数据
						creditCheck.setCheck_autoId(UUIDGenerator.getUUID());
						creditCheckDaoImp.insertCopyOpasAllotApplyAllot(creditCheck.getCheck_number());
						//更新申请件分配表
						creditCheckDaoImp.updateOpasAllotApplyAllot(creditCheck);
						creditCheckDaoImp.updateCheatInvestigation(creditCheck);
						if("3".equals(creditCheck.getInvest_delStatus())){
							creditCheck.setInvest_autoId(UUIDGenerator.getUUID());
							creditCheck.setInvest_crtUser(apUser.getUserCode());
							creditCheckDaoImp.insertOpasSubmitFraudInfoHis(creditCheck);
						}else{
							creditCheck.setInvest_autoId(UUIDGenerator.getUUID());
							creditCheck.setInvest_crtUserName(userName);
							creditCheckDaoImp.insertOpasSubmitFraudInfoHis(creditCheck);
							//creditCheck.setCheck_currOptUser(zxCrediter);//设置征信员
						}
						if("3".equals(creditCheck.getInvest_delStatus())){
							if("02".equals(curr_node)){					
								applyLifeCicle.setOperateDesc(userName + "欺诈审批退回"+zxCrediterName+"的征信队列");	
							}else if("03".equals(curr_node)){				
								applyLifeCicle.setOperateDesc(userName + "欺诈审批退回"+zxCrediterName+"的审批队列");
							}
						applyLifeCicle.setAppId(creditCheck.getCheck_number());	
						applyLifeCicle.setUuid(UUID.randomUUID().toString().replace("-", ""));
						applyLifeCicleDao.addApplyLifeCicle(applyLifeCicle);
						}else{	
								if("02".equals(curr_node)){									
									applyLifeCicle.setOperateDesc(userName + "欺诈审批同意进入"+zxCrediterName+"的征信队列");						
								}else if("03".equals(curr_node)){
									applyLifeCicle.setOperateDesc(userName + "欺诈审批同意进入"+zxCrediterName+"的审批队列");
								}
								applyLifeCicle.setAppId(creditCheck.getCheck_number());
							//	applyLifeCicle.setOperateDesc(userName + "欺诈审批同意");
								applyLifeCicle.setUuid(UUID.randomUUID().toString().replace("-", ""));
								applyLifeCicleDao.addApplyLifeCicle(applyLifeCicle);
						}
						
					}else{
						arr[15]='1';
						creditCheck.setCheck_number(new String(arr));
						//向分配表历史表中插入数据
						creditCheck.setCheck_autoId(UUIDGenerator.getUUID());
						//更新申请件分配表
						creditCheckDaoImp.updateOpasAllotApplyAllot(creditCheck);
						creditCheckDaoImp.insertCopyOpasAllotApplyAllot(creditCheck.getCheck_number());
						creditCheckDaoImp.updateCheatInvestigation(creditCheck);
						if("3".equals(creditCheck.getInvest_delStatus())){
							creditCheck.setInvest_autoId(UUIDGenerator.getUUID());
							creditCheck.setInvest_crtUser(apUser.getUserCode());
							creditCheckDaoImp.insertOpasSubmitFraudInfoHis(creditCheck);
							creditCheck.setCheck_currOptUser(zxCrediter);//设置征信员
						}else{
							creditCheck.setInvest_autoId(UUIDGenerator.getUUID());
							creditCheck.setInvest_crtUserName(userName);
							creditCheckDaoImp.insertOpasSubmitFraudInfoHis(creditCheck);
							creditCheck.setCheck_currOptUser(zxCrediter);//设置征信员
						}
						if("3".equals(creditCheck.getInvest_delStatus())){
							if("02".equals(curr_node)){					
								applyLifeCicle.setOperateDesc(userName + "欺诈审批退回"+zxCrediterName+"的征信队列");	
							}else if("03".equals(curr_node)){				
								applyLifeCicle.setOperateDesc(userName + "欺诈审批退回"+zxCrediterName+"的审批队列");
							}
						applyLifeCicle.setAppId(creditCheck.getCheck_number());	
						applyLifeCicle.setUuid(UUID.randomUUID().toString().replace("-", ""));
						applyLifeCicleDao.addApplyLifeCicle(applyLifeCicle);
						}else{	
								if("02".equals(curr_node)){									
									applyLifeCicle.setOperateDesc(userName + "欺诈审批同意进入"+zxCrediterName+"的征信队列");						
								}else if("03".equals(curr_node)){
									applyLifeCicle.setOperateDesc(userName + "欺诈审批同意进入"+zxCrediterName+"的审批队列");
								}
								applyLifeCicle.setAppId(creditCheck.getCheck_number());
							//	applyLifeCicle.setOperateDesc(userName + "欺诈审批同意");
								applyLifeCicle.setUuid(UUID.randomUUID().toString().replace("-", ""));
								applyLifeCicleDao.addApplyLifeCicle(applyLifeCicle);
						}
					}
				}
			}
			}
			
		} else {
			//设置欺诈调查员
			//查询分配表上一操作人
			String dcUser = creditCheckDaoImp.queryAllotApplyAllotByAppId(creditCheck.getCheck_number());
			int user = apUserDao.queryUserCodeCount(dcUser);
			if(user > 0){
				creditCheck.setCheck_currOptUser(dcUser);
			}
			
			ApUser apUser = apUserDao.queryApUserByUserId(userUuid);
			creditCheckDaoImp.updateCheatInvestigation(creditCheck);
			creditCheck.setInvest_autoId(null);
			//插入生命周期表
			ApplyLifeCicle applyLifeCicle = qryApplyLifeCicle(userUuid);
			creditCheck.setInvest_autoId(UUIDGenerator.getUUID());
			creditCheck.setInvest_crtUser(apUser.getUserCode());
			creditCheckDaoImp.insertOpasSubmitFraudInfoHis(creditCheck);
			String userName = apUser.getUserName();
			if("1".equals(creditCheck.getInvest_delStatus())){
				creditCheckDaoImp.updateOpasAllotApplyAllotToBack(creditCheck);
				applyLifeCicle.setAppId(creditCheck.getCheck_number());
				applyLifeCicle.setOperateDesc(userName + "欺诈审批退回欺诈调查");
				applyLifeCicleDao.addApplyLifeCicle(applyLifeCicle);
			}
			
			
			//根据appId判断该申请件是否为套卡
			//根据appId查询进件卡
			String appId = creditCheck.getCheck_number();
			BizInpApplC1 bizinpapplc1 = patchboltDao.querybizInpApplList(appId);
			//0-无套卡标识  1-主卡  2-套卡
			char[] arr = appId.toCharArray();
			String ydjFlag = bizinpapplc1.getYdjFlag();
			if("1".equals(ydjFlag)){
			if(bizinpapplc1 != null){
				
				if(!"0".equals(bizinpapplc1.getMatchingCardFlag())&&!"".equals(bizinpapplc1.getMatchingCardFlag())&&null!=bizinpapplc1.getMatchingCardFlag()){
					if(arr.length==16&&arr[15]=='1'){
						arr[15]='2';
						creditCheck.setCheck_number(new String(arr));
						creditCheckDaoImp.updateCheatInvestigation(creditCheck);
						creditCheck.setInvest_autoId(UUIDGenerator.getUUID());
						creditCheck.setInvest_crtUser(apUser.getUserCode());
						creditCheckDaoImp.insertOpasSubmitFraudInfoHis(creditCheck);
						//生平周期
						if("1".equals(creditCheck.getInvest_delStatus())){
							creditCheckDaoImp.updateOpasAllotApplyAllotToBack(creditCheck);
							applyLifeCicle.setAppId(creditCheck.getCheck_number());
							applyLifeCicle.setOperateDesc(userName + "欺诈审批退回欺诈调查");
							applyLifeCicle.setUuid(UUID.randomUUID().toString().replace("-", ""));
							
							applyLifeCicleDao.addApplyLifeCicle(applyLifeCicle);
						}
						
					}else{
						arr[15]='1';
						creditCheck.setCheck_number(new String(arr));
						creditCheckDaoImp.updateCheatInvestigation(creditCheck);
						creditCheck.setInvest_autoId(UUIDGenerator.getUUID());
						creditCheck.setInvest_crtUser(apUser.getUserCode());
						creditCheckDaoImp.insertOpasSubmitFraudInfoHis(creditCheck);
						//生命周期
						if("1".equals(creditCheck.getInvest_delStatus())){
							creditCheckDaoImp.updateOpasAllotApplyAllotToBack(creditCheck);
							applyLifeCicle.setAppId(creditCheck.getCheck_number());
							applyLifeCicle.setOperateDesc(userName + "欺诈审批退回欺诈调查");
							applyLifeCicle.setUuid(UUID.randomUUID().toString().replace("-", ""));
							
							applyLifeCicleDao.addApplyLifeCicle(applyLifeCicle);
						}
					}
				}
			}
			}
			
		}
	}

	@Override
	public Map<String, Object> selectCandidate(Candidate candidate, int begNum, int pageNum) {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		String total = creditCheckDaoImp.selectCandidateCount(candidate);
		List<CreditCheck> rows = creditCheckDaoImp.selectCandidate(candidate, begNum, pageNum);
		dataMap.put("total", Integer.parseInt(total));
		dataMap.put("rows", rows);
		return dataMap;
	}

	@Override
	public String workloadStatistics(Map<String, String> paraMap) {
		String delStatus = paraMap.get("delStatus");
		if("2".equals(delStatus)){//反欺诈工作量
			return	creditCheckDaoImp.selectWorkCountInvestigation(paraMap);
		}else if("4".equals(delStatus)){//欺诈审批工作量			
			return creditCheckDaoImp.selectOpasSubmitFraudInfoByOperationDate(paraMap);
		}
		return null;
	}

	@Override
	public List<CreditCheck> selectReasonByAppId(String appId) {
		return creditCheckDaoImp.selectReasonByAppId(appId);
	}

	@Override
	public int getOne(Map<String, Object> map) {
		try {
			// 申请件获取
			int num = creditCheckDaoImp.updateOne(map);
			return num;
		} catch (CoreException e) {
		}
		return 0;
	}

	@Override
	public int getThree(Map<String, Object> map) {

		try {
			// 抢件
			int num = creditCheckDaoImp.updateThree(map);
		} catch (CoreException e) {
		}
		return 0;
	}

	@Override
	public void addRiskList(Map<String, Object> dataMap,String crediter) throws CoreException {
		//获取前台参数
		String appId = (String) dataMap.get("appId");
		String resultCode = (String) dataMap.get("resultCode");
		//根据appId查进件表相应字段
		BizInpApplC1 bizInpApplC1 = bizInpApplC1DaoImp.queryBizInpApplC1ByAppId(appId) == null?new BizInpApplC1():bizInpApplC1DaoImp.queryBizInpApplC1ByAppId(appId);
		String c1Cname = bizInpApplC1.getC1Cname();
		String c1Idtype = bizInpApplC1.getC1Idtype();
		String c1Idnbr = bizInpApplC1.getC1Idnbr();
		String c1Coname = bizInpApplC1.getC1Coname();
		String c1Mobile = bizInpApplC1.getC1Mobile();
		String c1Hmare  = bizInpApplC1.getC1Hmare();
		String c1Hmtell = bizInpApplC1.getC1Hmtel();
		String c1Cotel = bizInpApplC1.getC1Cotel();
		//拼接住宅地址
		String c1Hmtel = c1Hmare+c1Hmtell;
		//拼接家庭地址
		String c1Hmadd1 = bizInpApplC1.getC1Hmadd1() == null?"":bizInpApplC1.getC1Hmadd1();
		String c1Hmadd2 = bizInpApplC1.getC1Hmadd2() == null?"":bizInpApplC1.getC1Hmadd2();
		String c1Hmadd3 = bizInpApplC1.getC1Hmadd3() == null?"":bizInpApplC1.getC1Hmadd3();
		String c1Hmadd4 = bizInpApplC1.getC1Hmadd4() == null?"":bizInpApplC1.getC1Hmadd4();
		String hmAddr = c1Hmadd1+c1Hmadd2+c1Hmadd3+c1Hmadd4;
		//拼接户籍地址
		String c1Idadd1 = bizInpApplC1.getC1Idadd1() == null?"":bizInpApplC1.getC1Idadd1();
		String c1Idadd2 = bizInpApplC1.getC1Idadd2() == null?"":bizInpApplC1.getC1Idadd2();
		String c1Idadd3 = bizInpApplC1.getC1Idadd3() == null?"":bizInpApplC1.getC1Idadd3();
		String c1Idadd4 = bizInpApplC1.getC1Idadd4() == null?"":bizInpApplC1.getC1Idadd4();
		String idAddr = c1Idadd1+c1Idadd2+c1Idadd3+c1Idadd4;
		//拼接公司地址
		String c1Coadd1 = bizInpApplC1.getC1Coadd1() == null?"":bizInpApplC1.getC1Coadd1();
		String c1Coadd2 = bizInpApplC1.getC1Coadd2() == null?"":bizInpApplC1.getC1Coadd2();
		String c1Coadd3 = bizInpApplC1.getC1Coadd3() == null?"":bizInpApplC1.getC1Coadd3();
		String c1Coadd4 = bizInpApplC1.getC1Coadd4() == null?"":bizInpApplC1.getC1Coadd4();
		String coAddr = c1Coadd1+c1Coadd2+c1Coadd3+c1Coadd4;
		//拼接其他地址----房产地址
		String c1Estadd1 = bizInpApplC1.getC1Estadd1();
		String c1Estadd2 = bizInpApplC1.getC1Estadd2();
		String c1Estadd3 = bizInpApplC1.getC1Estadd3();
		String c1Estadd4 = bizInpApplC1.getC1Estadd4();
		String estAddr = c1Estadd1+c1Estadd2+c1Estadd3+c1Estadd4;
		//拼接账单地址
		String c4Cycadd1 = bizInpApplC1.getC4Cycadd1() == null?"":bizInpApplC1.getC4Cycadd1();
		String c4Cycadd2 = bizInpApplC1.getC4Cycadd2() == null?"":bizInpApplC1.getC4Cycadd2();
		String cycAddr = c4Cycadd1+c4Cycadd2;
		//创建身份类风险名单实体类
		IdentityRisk identityRisk = new IdentityRisk();
		//identityRisk.setAutoId(UUIDGenerator.getUUID());
		identityRisk.setName(c1Cname);
		identityRisk.setCertifiType(c1Idtype);
		identityRisk.setCertifiNo(c1Idnbr);
		identityRisk.setInfoChannel("4");
		identityRisk.setFraudType("1");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String str = "2999-12-31";
		Date date = null;
		try {
			date = sdf.parse(str);
		} catch (ParseException e) {
		}
		identityRisk.setInvalidTime(date);
		identityRisk.setCreateTime(new Date());
		identityRisk.setOperator(crediter);
		identityRisk.setOperatTime(new Date());
		identityRisk.setCurrStatus("1");
		
		//创建单位类风险名单实体类
		CompanyRisk companyRisk = new CompanyRisk();
		companyRisk.setCompanyName(c1Coname);
		companyRisk.setInfoChannel("4");
		companyRisk.setInvalidTime(date);
		companyRisk.setCurrStatus("1");
		companyRisk.setFraudType("1");
		companyRisk.setCreateTime(new Date());
		companyRisk.setOperator(crediter);
		companyRisk.setOperatTime(new Date());
		//创建电话类风险名单实体类
		TelRiskList telRiskList = new TelRiskList();
		telRiskList.setMobileId(c1Mobile);
		telRiskList.setCompanyTel(c1Cotel);
		telRiskList.setLivingTel(c1Hmtel);
		telRiskList.setInfoChannel("4");
		telRiskList.setInvalidTime(date);
		telRiskList.setCreateTime(new Date());
		telRiskList.setUserOperator(crediter);
		telRiskList.setOperatTime(new Date());
		telRiskList.setCurrStatus("1");
		telRiskList.setFraudType("1");
		//创建地址类风险名单实体类
		AddrRiskList addrRiskList = new AddrRiskList();
		addrRiskList.setCompanyAddr(coAddr);
		addrRiskList.setLivingAddr(hmAddr);
		addrRiskList.setBornAddr(idAddr);
		addrRiskList.setBillAddr(cycAddr);
		addrRiskList.setInfoChannel("4");
		addrRiskList.setInvalidTime(date);
		addrRiskList.setCreateTime(new Date());
		addrRiskList.setUserOperator(crediter);
		addrRiskList.setOperatTime(new Date());
		addrRiskList.setCurrStatus("1");
		addrRiskList.setFraudType("1");
		//每匹配一次欺诈原因码都会触发入库操作,会出现数据重复,但uuid不同
		String[] arr = resultCode.split("[|]");
		for (String string : arr) {
			if("101".equals(string)){
				//自动入库身份类风险名单
				identityRisk.setAutoId(UUIDGenerator.getUUID());
				identityRiskDao.insertIdentityRisk(identityRisk);
				//自动入库单位类风险名单
				companyRisk.setAutoId(UUIDGenerator.getUUID());
				companyRiskDao.insertCompanyRisk(companyRisk);
				//自动入库电话类风险名单库
				telRiskList.setAutoId(UUIDGenerator.getUUID());
				telRiskList.setCompanyTel("");
				telRiskList.setLivingTel("");
				telRiskListDao.insertSelective(telRiskList);
				telRiskList.setCompanyTel(c1Cotel);
				telRiskList.setLivingTel(c1Hmtel);
			}
			if("201".equals(string)){
				//自动入库身份类风险名单
				identityRisk.setAutoId(UUIDGenerator.getUUID());
				identityRiskDao.insertIdentityRisk(identityRisk);
				//自动入库电话类风险名单库
				telRiskList.setAutoId(UUIDGenerator.getUUID());
				telRiskList.setCompanyTel("");
				telRiskList.setLivingTel("");
				telRiskListDao.insertSelective(telRiskList);
				telRiskList.setCompanyTel(c1Cotel);
				telRiskList.setLivingTel(c1Hmtel);
			}
			if("401".equals(string)){
				//自动入库身份类风险名单
				identityRisk.setAutoId(UUIDGenerator.getUUID());
				identityRiskDao.insertIdentityRisk(identityRisk);
				//自动入库电话类风险名单库
				telRiskList.setAutoId(UUIDGenerator.getUUID());
				telRiskList.setCompanyTel("");
				telRiskList.setLivingTel("");
				telRiskListDao.insertSelective(telRiskList);
				telRiskList.setMobileId(c1Mobile);
				telRiskList.setCompanyTel(c1Cotel);
				telRiskList.setLivingTel(c1Hmtel);
			}
			if("501".equals(string)){
				//自动入库身份类风险名单
				identityRisk.setAutoId(UUIDGenerator.getUUID());
				identityRiskDao.insertIdentityRisk(identityRisk);
				//自动入库电话类风险名单库
				telRiskList.setAutoId(UUIDGenerator.getUUID());
				telRiskList.setCompanyTel("");
				telRiskList.setLivingTel("");
				telRiskListDao.insertSelective(telRiskList);
				telRiskList.setMobileId(c1Mobile);
				telRiskList.setCompanyTel(c1Cotel);
				telRiskList.setLivingTel(c1Hmtel);
			}
			if("601".equals(string)){
				//自动入库身份类风险名单
				identityRisk.setAutoId(UUIDGenerator.getUUID());
				identityRiskDao.insertIdentityRisk(identityRisk);
				//自动入库单位类风险名单
				companyRisk.setAutoId(UUIDGenerator.getUUID());
				companyRiskDao.insertCompanyRisk(companyRisk);
				//自动入库电话类风险名单库
				telRiskList.setAutoId(UUIDGenerator.getUUID());
				telRiskList.setLivingTel("");
				telRiskListDao.insertSelective(telRiskList);
				telRiskList.setMobileId(c1Mobile);
				telRiskList.setCompanyTel(c1Cotel);
				telRiskList.setLivingTel(c1Hmtel);
			}
			if("602".equals(string)){
				//自动入库身份类风险名单
				identityRisk.setAutoId(UUIDGenerator.getUUID());
				identityRiskDao.insertIdentityRisk(identityRisk);
				//自动入库电话类风险名单库
				telRiskList.setAutoId(UUIDGenerator.getUUID());
				telRiskList.setLivingTel("");
				telRiskListDao.insertSelective(telRiskList);
				telRiskList.setMobileId(c1Mobile);
				telRiskList.setCompanyTel(c1Cotel);
				telRiskList.setLivingTel(c1Hmtel);
			}
			if("202".equals(string)){
				//自动入库电话类风险名单库
				telRiskList.setAutoId(UUIDGenerator.getUUID());
				telRiskList.setCompanyTel("");
				telRiskList.setLivingTel("");
				telRiskListDao.insertSelective(telRiskList);
				telRiskList.setMobileId(c1Mobile);
				telRiskList.setCompanyTel(c1Cotel);
				telRiskList.setLivingTel(c1Hmtel);
			}
			if("203".equals(string)){
				//自动入库电话类风险名单库
				telRiskList.setAutoId(UUIDGenerator.getUUID());
				telRiskList.setMobileId("");
				telRiskList.setCompanyTel("");
				telRiskListDao.insertSelective(telRiskList);
				telRiskList.setMobileId(c1Mobile);
				telRiskList.setCompanyTel(c1Cotel);
				telRiskList.setLivingTel(c1Hmtel);
			}
			if("102".equals(string)){
				//自动入库电话类风险名单库
				telRiskList.setAutoId(UUIDGenerator.getUUID());
				telRiskList.setMobileId("");
				telRiskList.setLivingTel("");
				telRiskListDao.insertSelective(telRiskList);
				telRiskList.setMobileId(c1Mobile);
				telRiskList.setCompanyTel(c1Cotel);
				telRiskList.setLivingTel(c1Hmtel);
			}
			if("103".equals(string)){
				//自动入库地址类风险名单库
				addrRiskList.setAutoId(UUIDGenerator.getUUID());
				addrRiskList.setLivingAddr("");
				addrRiskList.setBornAddr("");
				addrRiskList.setBillAddr("");
				addrRiskListDao.insert(addrRiskList);
			}
			if("204".equals(string)){
				//自动入库地址类风险名单库
				addrRiskList.setAutoId(UUIDGenerator.getUUID());
				addrRiskList.setCompanyAddr("");
				addrRiskList.setLivingAddr(hmAddr);
				addrRiskList.setBornAddr("");
				addrRiskList.setBillAddr("");
				addrRiskListDao.insert(addrRiskList);
			}
		}
	}

	@Override
	public String queryGroup(String crediter) throws CoreException {
		
		return creditCheckDaoImp.queryGroup(crediter);
	}

	@Override
	public List findQzOperationPerson(Map paramMap,String userId) throws CoreException {
		String orgId = creditCheckDaoImp.selectOrgIdByUserId(userId);
		List listUserId = creditCheckDaoImp.selectListApUserByOrgId(orgId);
		if(listUserId.size()!=0){
			Map<String,Object> paramMaplistUserId=new HashMap<String,Object>();
			paramMaplistUserId.put("listUserId", listUserId);
			List<Map<String,Object>>listApUser=	creditCheckDaoImp.selectListApUserByListUserId(paramMaplistUserId);
			return listApUser;
		}else{
			return null;
		}
	}

	@Override
	public CreditCheck selfTheOne(Map paramMap) throws CoreException {
		CreditCheck credit = creditCheckDaoImp.selfTheOne(paramMap);
		return credit;
	}

	@Override
	public void cheatInvestigationCredit(CreditCheck creditCheck, String userCode, String userUuid) throws Exception {
		String delStatus = creditCheckDaoImp.queryqzVdelStatusByAppId(creditCheck.getCheck_number());
		creditCheck.setCheck_currNode("06");
		creditCheck.setCurr_node("04");
		creditCheck.setDel_stasus("0");
		creditCheck.setSyn_flag("0");
		creditCheck.setHis_vdel_status(delStatus);
		if ("5".equals(delStatus))
			return;
		// 修改征信提报欺诈结果信息表
		creditCheckDaoImp.updateCheatInvestigation(creditCheck);	
		creditCheck.setCheck_lastOU(userCode);// 设置调查员为上一操作人 更新到分件表

		// 根据appId判断该申请件是否为套卡
		// 根据appId查询进件卡
		String appId = creditCheck.getCheck_number();
		BizInpApplC1 bizinpapplc1 = patchboltDao.querybizInpApplList(appId);
		// 生命周期表中插入数据
		ApUser apUser = apUserDao.queryApUserByUserId(userUuid);
		ApplyLifeCicle applyLifeCicle = qryApplyLifeCicle(userUuid);
		String userName = apUser.getUserName();
		applyLifeCicle.setAppId(appId);
		if ("0".equals(delStatus)) {
			applyLifeCicle.setOperateDesc(userName + "欺诈调查未完成队列进入派发协查队列");
		} else if ("1".equals(delStatus)) {
			applyLifeCicle.setOperateDesc(userName + "欺诈调查回退队列进入派发协查队列");
		} else if ("2".equals(delStatus)) {
			applyLifeCicle.setOperateDesc(userName + "欺诈审批未完成队列进入派发协查队列");
		}
		applyLifeCicle.setUuid(UUID.randomUUID().toString().replace("-", ""));
		applyLifeCicleDao.addApplyLifeCicle(applyLifeCicle);
		String ydjFlag = bizinpapplc1.getYdjFlag();
		creditCheck.setCheck_ydjFlag(ydjFlag);
		creditCheck.setMatchingCardFlag(bizinpapplc1.getMatchingCardFlag());
		
		// 更新申请件分件信息表
		creditCheckDaoImp.updateOpasAllotApplyAllot1(creditCheck);
		// 修改申请件分配表
		creditCheckDaoImp.updateOpasAllotApplyAllot(creditCheck);
		// 复制申请件分配表到历史表
		creditCheckDaoImp.insertCopyOpasAllotApplyAllot(creditCheck.getCheck_number());
		creditCheck.setInvest_autoId(UUIDGenerator.getUUID());
		creditCheck.setInvest_crtUserName(userName);
		// 插入欺诈历史表
		creditCheckDaoImp.insertOpasSubmitFraudInfoHis(creditCheck);
		List<CreditCheck> list1 = creditCheckDaoImp.selectysallotapplyallotID(creditCheck.getCheck_number());
		// 插入预审分件表
		if (list1 != null && list1.size() > 0) {
			creditCheckDaoImp.updateYsAllotApplyAllot(creditCheck);
		} else {
			creditCheck.setCheck_autoId(UUID.randomUUID().toString().replace("-", ""));
			creditCheckDaoImp.insertysallotapplyallotID(creditCheck);
		}
		/*
		 * 0-无套卡标识 1-套卡的附属卡 2-套卡申请卡(主卡) 标卡到分件只有一个条码，所以无需判断标卡是否套卡，只判断易达金即可
		 */
		char[] arr = appId.toCharArray();
		if ("1".equals(ydjFlag)) {
			if (bizinpapplc1 != null) {
				if (!"0".equals(bizinpapplc1.getMatchingCardFlag()) && !"".equals(bizinpapplc1.getMatchingCardFlag())
						&& null != bizinpapplc1.getMatchingCardFlag()) {
					if (arr.length == 16 && arr[15] == '1') {
						arr[15] = '2';
						creditCheck.setCheck_number(new String(arr));
						creditCheck.setInvest_autoId(null);
						// 更新提报欺诈结果信息表
						creditCheckDaoImp.updateCheatInvestigation(creditCheck);
						if ("5".equals(creditCheck.getInvest_delStatus())) {
							// 更新申请件分件信息表
							creditCheckDaoImp.updateOpasAllotApplyAllot1(creditCheck);
						}
						applyLifeCicle.setAppId(new String(arr));
						if ("0".equals(delStatus)) {
							applyLifeCicle.setOperateDesc(userName + "欺诈调查未完成队列进入派发协查队列");
						} else if ("1".equals(delStatus)) {
							applyLifeCicle.setOperateDesc(userName + "欺诈调查回退队列进入派发协查队列");
						} else if ("2".equals(delStatus)) {
							applyLifeCicle.setOperateDesc(userName + "欺诈审批未完成队列进入派发协查队列");
						}
						applyLifeCicle.setUuid(UUID.randomUUID().toString().replace("-", ""));
						applyLifeCicleDao.addApplyLifeCicle(applyLifeCicle);
						// 修改申请件分配表
						creditCheckDaoImp.updateOpasAllotApplyAllot(creditCheck);
						// 复制申请件分配表到历史表
						creditCheckDaoImp.insertCopyOpasAllotApplyAllot(creditCheck.getCheck_number());
						creditCheck.setInvest_autoId(UUIDGenerator.getUUID());
						creditCheck.setInvest_crtUserName(userName);
						// 插入欺诈历史表
						creditCheckDaoImp.insertOpasSubmitFraudInfoHis(creditCheck);
						List<CreditCheck> list = creditCheckDaoImp
								.selectysallotapplyallotID(creditCheck.getCheck_number());
						// 插入预审分件表
						if (list != null && list.size() > 0) {
							creditCheckDaoImp.updateYsAllotApplyAllot(creditCheck);
						} else {
							creditCheck.setCheck_autoId(UUID.randomUUID().toString().replace("-", ""));
							creditCheckDaoImp.insertysallotapplyallotID(creditCheck);
						}
					} else {
						arr[15] = '1';
						creditCheck.setCheck_number(new String(arr));
						creditCheck.setInvest_autoId(null);
						// 更新提报欺诈结果信息表
						creditCheckDaoImp.updateCheatInvestigation(creditCheck);
						if ("5".equals(creditCheck.getInvest_delStatus())) {
							// 更新申请件分件信息表
							creditCheckDaoImp.updateOpasAllotApplyAllot1(creditCheck);
						}
						applyLifeCicle.setAppId(new String(arr));
						if ("0".equals(delStatus)) {
							applyLifeCicle.setOperateDesc(userName + "欺诈调查未完成队列进入派发协查队列");
						} else if ("1".equals(delStatus)) {
							applyLifeCicle.setOperateDesc(userName + "欺诈调查回退队列进入派发协查队列");
						} else if ("2".equals(delStatus)) {
							applyLifeCicle.setOperateDesc(userName + "欺诈审批未完成队列进入派发协查队列");
						}
						applyLifeCicle.setUuid(UUID.randomUUID().toString().replace("-", ""));
						applyLifeCicleDao.addApplyLifeCicle(applyLifeCicle);
						// 修改申请件分配表
						creditCheckDaoImp.updateOpasAllotApplyAllot(creditCheck);
						// 复制申请件分配表到历史表
						creditCheckDaoImp.insertCopyOpasAllotApplyAllot(creditCheck.getCheck_number());
						creditCheck.setInvest_autoId(UUIDGenerator.getUUID());
						creditCheck.setInvest_crtUserName(userName);
						// 插入欺诈历史表
						creditCheckDaoImp.insertOpasSubmitFraudInfoHis(creditCheck);
						List<CreditCheck> list = creditCheckDaoImp
								.selectysallotapplyallotID(creditCheck.getCheck_number());
						// 插入预审分件表
						if (list != null && list.size() > 0) {
							creditCheckDaoImp.updateYsAllotApplyAllot(creditCheck);
						} else {
							creditCheck.setCheck_autoId(UUID.randomUUID().toString().replace("-", ""));
							creditCheckDaoImp.insertysallotapplyallotID(creditCheck);
						}
					}
				}
			}
		} 
	}

	@Override
	public List<Map<String, Object>> queryPfxcTime(String appId) throws CoreException {
		 return creditCheckDaoImp.queryPfxcTime(appId);
	}

	@Override
	public Map<String, Object> selectCreditSVoiceCheckPage(Map<String, Object> paraMap, int begNum, int pageNum) {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		String total = creditCheckDaoImp.selectCreditSVoiceCount(paraMap);
		List<CreditSVoice> rows = creditCheckDaoImp.selectCreditSVoiceCheckPage(paraMap, begNum, pageNum);
		dataMap.put("total", Integer.parseInt(total));
		dataMap.put("rows", rows);
		return dataMap;
	}
}
