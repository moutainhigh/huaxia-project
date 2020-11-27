package com.huaxia.opas.service.apply.impl;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.codehaus.xfire.client.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.apply.ApplyInfoDao;
import com.huaxia.opas.dao.baseinfo.BaseCustInfoDao;
import com.huaxia.opas.dao.report.KeyMessageModifyDao;
import com.huaxia.opas.domain.allot.AllotApply;
import com.huaxia.opas.domain.allot.AllotApplyAllotHis;
import com.huaxia.opas.domain.apply.ApplyLifeCicle;
import com.huaxia.opas.domain.apply.ApplyModifyLog;
import com.huaxia.opas.domain.baseinfo.BaseCustInfo;
import com.huaxia.opas.domain.input.BizInpAppC1Spec;
import com.huaxia.opas.domain.input.BizInpApplC1;
import com.huaxia.opas.domain.input.BizInpApplC2;
import com.huaxia.opas.domain.report.Fishing;
import com.huaxia.opas.domain.riskcheck.KeyfiledMarchinfo;
import com.huaxia.opas.domain.riskcheck.RiskCheckResult;
import com.huaxia.opas.domain.rule.OpasBizApproval;
import com.huaxia.opas.service.AbstractService;
import com.huaxia.opas.service.allot.AllotApplyHisService;
import com.huaxia.opas.service.apply.ApplyInfoService;
import com.huaxia.opas.service.apply.BizInpApplService;
import com.huaxia.opas.service.report.FishingService;
import com.huaxia.opas.service.riskcheck.RiskCheckService;
import com.huaxia.opas.service.workflow.TopbpmService;
import com.huaxia.opas.util.JsonUtil;
import com.huaxia.opas.util.StringUtil;

import net.sf.json.JSONArray;

public class ApplyInfoServiceImpl extends AbstractService implements ApplyInfoService,Serializable {

	private static final Logger logger = LoggerFactory.getLogger(ApplyInfoServiceImpl.class);
	private static final long serialVersionUID = 8699416809613318094L;

	@Resource(name = "applyInfoDao")
	private ApplyInfoDao applyInfoDao;
	@Resource(name = "bizInpApplServiceImpl")
	private BizInpApplService bizInpApplService;
	@Resource(name = "fishingServiceImpl")
	private FishingService fishingService;
	@Resource(name = "allotApplyHisServiceImpl")
	private AllotApplyHisService allotApplyHisService;
	@Resource(name = "baseCustInfoDao")
	private BaseCustInfoDao baseCustInfoDao;
	@Resource(name = "keyMessageModifyDao")
	private KeyMessageModifyDao keyMessageModifyDao;
	@Resource(name = "riskCheckServiceImpl")
	private RiskCheckService riskCheckService;
	@Resource(name = "topbpmService")
	private TopbpmService topbpmService;
	public ApplyInfoDao getApplyInfoDao() {
		return applyInfoDao;
	}

	public void setApplyInfoDao(ApplyInfoDao applyInfoDao) {
		this.applyInfoDao = applyInfoDao;
	}

	@Override
	public int selectCurrentApplyCount1(Map<String, String> params) {
		return getApplyInfoDao().selectCurrentApplyCount1(params);
	}

	@Override
	public int selectCurrentApplyCount2(Map<String, String> params) {
		return getApplyInfoDao().selectCurrentApplyCount2(params);
	}

	@Override
	public int selectCurrentApplyCount3(Map<String, String> params) {
		return getApplyInfoDao().selectCurrentApplyCount3(params);
	}

	@Override
	public List<Map<String, String>> selectCurrentApplyList1(Map<String, String> params, int curNum, int pageNum) {

		return getApplyInfoDao().selectCurrentApplyList1(params, curNum, pageNum);
	}

	@Override
	public List<Map<String, String>> selectCurrentApplyList2(Map<String, String> params, int curNum, int pageNum) {

		return getApplyInfoDao().selectCurrentApplyList2(params, curNum, pageNum);
	}

	@Override
	public List<Map<String, String>> selectCurrentApplyList3(Map<String, String> params, int curNum, int pageNum) {

		return getApplyInfoDao().selectCurrentApplyList3(params, curNum, pageNum);
	}

	@Override
	public int selectHistoryCount1(Map<String, String> params) {
		return getApplyInfoDao().selectHistoryCount1(params);
	}

	@Override
	public int selectHistoryCount2(Map<String, String> params) {
		return getApplyInfoDao().selectHistoryCount2(params);
	}

	@Override
	public int selectHistoryCount3(Map<String, String> params) {
		return getApplyInfoDao().selectHistoryCount3(params);
	}

	@Override
	public List<Map<String, String>> selectHistoryList1(Map<String, String> params, int curNum, int pageNum) {
		return getApplyInfoDao().selectHistoryList1(params, curNum, pageNum);
	}

	@Override
	public List<Map<String, String>> selectHistoryList2(Map<String, String> params, int curNum, int pageNum) {
		return getApplyInfoDao().selectHistoryList2(params, curNum, pageNum);
	}

	@Override
	public List<Map<String, String>> selectHistoryList3(Map<String, String> params, int curNum, int pageNum) {
		return getApplyInfoDao().selectHistoryList3(params, curNum, pageNum);
	}

	@Override
	public List<Map<String, Object>> selectCurrentState() {
		return getApplyInfoDao().selectCurrentState();
	}

	@Override
	public List<Map<String, Object>> selectHistoryState() {
		return getApplyInfoDao().selectHistoryState();
	}

	@Override
	public int selectHighCurrentCount(Map<String, Object> params) {
		return getApplyInfoDao().selectHighCurrentCount(params);
	}

	@Override
	public List<Map<String, Object>> selectHighCurrentList(Map<String, Object> params, int curNum, int pageNum) {
		return getApplyInfoDao().selectHighCurrentList(params, curNum, pageNum);
	}

	@Override
	public int selectHighHistoryCount(Map<String, Object> params) {
		return getApplyInfoDao().selectHighHistoryCount(params);
	}

	@Override
	public List<Map<String, Object>> selectHighHistoryList(Map<String, Object> params, int curNum, int pageNum) {
		return getApplyInfoDao().selectHighHistoryList(params, curNum, pageNum);
	}

	@Override
	public int selectArchiveCount(Map<String, String> params) {
		return getApplyInfoDao().selectArchiveCount(params);
	}

	@Override
	public List<Map<String, String>> selectCurrentBIZC1Info(Map<String, String> params) {
		return getApplyInfoDao().selectCurrentBIZC1Info(params);
	}

	@Override
	public int selectCurrentVVIP(Map<String, String> applyMap) {
		return getApplyInfoDao().selectCurrentVVIP(applyMap);
	}

	@Override
	public int selectCurrentArchive(Map<String, String> applyMap) {
		return getApplyInfoDao().selectCurrentArchive(applyMap);
	}

	@Override
	public Map<String, String> queryApplyCompanyInfo(String appId) {
		return getApplyInfoDao().selectApplyCompanyInfo(appId);
	}

	@Override
	public BizInpApplC1 get(BizInpApplC1 t) {
		try {
			return applyInfoDao.queryBizInpApplC1ByAppId(t.getAppId());
		} catch (CoreException e) {
		}
		return null;
	}
	
	@Override
	public BizInpAppC1Spec get2(BizInpAppC1Spec t) {
		try {
			return applyInfoDao.queryBizInpApplC1ByAppId2(t.getAppId());
		} catch (CoreException e) {
		}
		return null;
	}
	
	@Override
	public BizInpAppC1Spec get2New(BizInpAppC1Spec t) {
		try {
			return applyInfoDao.queryBizInpApplC1ByAppId2New(t.getAppId());
		} catch (CoreException e) {
		}
		return null;
	}

	@Override
	public void updateBizInfo(BizInpAppC1Spec bizInpApplC1,List<BaseCustInfo> bcfList) throws CoreException {
		applyInfoDao.updateBizInfoMatchingCard(bizInpApplC1);
		if(bcfList != null && bcfList.size()>0){
			for (BaseCustInfo baseCustInfo : bcfList) {
				baseCustInfoDao.updateCustInfo(baseCustInfo);
			}
		}
	}
	
	@Override
	public void updateBizInfoYdj(BizInpAppC1Spec bizInpApplC1,List<BaseCustInfo> bcfList) throws CoreException {
		applyInfoDao.updateBizInfoMatchingCardYdj(bizInpApplC1);
		if(bcfList != null && bcfList.size()>0){
			for (BaseCustInfo baseCustInfo : bcfList) {
				baseCustInfoDao.updateCustInfo(baseCustInfo);
			}
		}
	}
	
	@Override
	public void updateBizInfoBzk(BizInpAppC1Spec bizInpApplC1,List<BaseCustInfo> bcfList) throws CoreException {
		applyInfoDao.updateBizInfoMatchingCardBzk(bizInpApplC1);
		if(bcfList != null && bcfList.size()>0){
			for (BaseCustInfo baseCustInfo : bcfList) {
				baseCustInfoDao.updateCustInfo(baseCustInfo);
			}
		}
	}

	@Override
	public BizInpApplC2 findBiz2info(String appno) throws CoreException {
		try {
			return applyInfoDao.findBiz2info(appno);
		} catch (CoreException e) {
		}
		return null;
	}

	@Override
	public int queryCount(Map<String, Object> map) throws CoreException {
		return applyInfoDao.queryCount(map);
	}

	@Override
	public List<Map<Object, Object>> queryList(Map<String, Object> map, int curNum, int pageNum) throws CoreException {

		return applyInfoDao.queryList(map, curNum, pageNum);
	}

	@Override
	public Map queryDragAqqlyById(String appId) throws CoreException {
		try {
			return applyInfoDao.queryDragAqqlyById(appId);
		} catch (CoreException e) {
		}
		return null;
	}

	@Override
	public List<ApplyLifeCicle> queryApplyLifeList(String appId) throws CoreException {
		try {
			return applyInfoDao.queryApplyLifeList(appId);
		} catch (CoreException e) {
		}
		return null;
	}

	@Override
	public void saveApplyModifyLog(List<ApplyModifyLog> modifyList) throws CoreException {
		if (modifyList != null && modifyList.size() > 0) {
			for (ApplyModifyLog applyModifyLog : modifyList) {
				applyModifyLog.setFlag("2");
				//ApplyModifyLog check = applyInfoDao.queryApplyModify(applyModifyLog);
//				if (check == null) {
//					applyInfoDao.saveApplyModifyLog(applyModifyLog);
//				} else {
//					applyInfoDao.updateApplyModify(applyModifyLog);
//				}
				applyInfoDao.saveApplyModifyLog(applyModifyLog);
				
			}
		}
	}

	@Override
	public void updateApply2Info(BizInpApplC2 keyMesageApply2) throws CoreException {
		applyInfoDao.updateApply2Info(keyMesageApply2);
	}

	// @Override
	// public BizInpApplC1 queryBizAppToFish(String appId) {
	// return applyInfoDao.queryBizAppToFish(appId);
	// }

	// @Override
	// public Map<String, String> queryHisallot(String appId) {
	// return applyInfoDao.queryHisallot(appId);
	// }

	// @Override
	// public void deleteIcationById(String appId) throws CoreException {
	// applyInfoDao.deleteIcationById(appId);
	// }

	// @Override
	// public Map<String, String> queryIcationByAppId(String appId) {
	// return applyInfoDao.queryIcationByAppId(appId);
	// }

	@Override
	public List<Map<String, Object>> selectHighAppayState() {
		return applyInfoDao.selectHighAppayState();
	}

	@Override
	public List<Map<String, Object>> selectHighHistoryAppayState() {
		return applyInfoDao.selectHighHistoryAppayState();
	}

	@Override
	public int selectNodeCount(Map<String, String> params) {
		return applyInfoDao.selectNodeCount(params);
	}

	@Override
	public List<Map<String, Object>> selectNodeList(Map<String, String> params, int curNum, int pageNum) {
		return getApplyInfoDao().selectNodeList(params, curNum, pageNum);
	}

	@Override
	public int selectRemarkCount(Map<String, String> params) {
		return applyInfoDao.selectRemarkCount(params);
	}

	@Override
	public List<Map<String, Object>> selectRemarkList(Map<String, String> params, int curNum, int pageNum) {
		return getApplyInfoDao().selectRemarkList(params, curNum, pageNum);
	}

	
	@Override
	public List<Map<String, Object>> selectExprotCurrentBJInfo(List<String> appIds) {
		return applyInfoDao.selectExprotCurrentBJInfo(appIds);
	}
	@Override
	public List<Map<String, Object>> selectExprotHistoryBJInfo(List<String> appIds) {
		return applyInfoDao.selectExprotHistoryBJInfo(appIds);
	}
	@Override
	public void doDragApply(String appId, Map<String, String> paroperMap) throws Exception {
		String userCode = paroperMap.get("userCode");
		String laojianFlag = paroperMap.get("laojianFlag");
		String ctrUser = keyMessageModifyDao.qeurUserNameByUserCode(userCode);
		//BizInpApplC1 biz = applyInfoDao.queryBizAppToFish(appId);
		List<BizInpApplC1> bizList = applyInfoDao.queryBizAppToFish(appId);
		//捞件之修改京东卡标记
		keyMessageModifyDao.update_jd_appln_is_create_file_flag(appId);
		// Laojian_num 为历史审批页面  记录捞件次数
		Map<String,String> laojianNum = new HashMap<>();
		Integer Laojian_num = keyMessageModifyDao.queryLaojian_num(appId);
		if(Laojian_num == null){
			Laojian_num = 1;
		}else{
			Laojian_num++;
		}
		laojianNum.put("appId", appId);
		laojianNum.put("Laojian_num", Laojian_num+"");
		keyMessageModifyDao.updateLaojian_num(laojianNum);
		// 捞件修改申请件分配表  ZSHY_INNER_CHECK 为 0 
		keyMessageModifyDao.ZSHY_INNER_CHECK(appId);
		if("00".equals(laojianFlag)){ //配发卡一起捞
			String appId0="1".equals(appId.substring(appId.length()-1,appId.length()))?appId.substring(0, appId.length()-1)+"2":appId.substring(0, appId.length()-1)+"1";
			//捞件之修改京东卡标记 ---配发卡
			keyMessageModifyDao.update_jd_appln_is_create_file_flag(appId0);
			Map<String,String> laojianNum2 = new HashMap<>();
			Integer Laojian_num2 = keyMessageModifyDao.queryLaojian_num(appId0);
			if(Laojian_num2 == null){
				Laojian_num2 = 1;
			}else{
				Laojian_num2++;
			}
			laojianNum2.put("appId", appId0);
			laojianNum2.put("Laojian_num", Laojian_num2+"");
			keyMessageModifyDao.updateLaojian_num(laojianNum2);
			// 捞件修改申请件分配表  ZSHY_INNER_CHECK 为 0 
			keyMessageModifyDao.ZSHY_INNER_CHECK(appId0);
		}
		for (BizInpApplC1 biz : bizList) {
			Map<String, String> iacDetail = applyInfoDao.queryIcationByAppId(biz.getAppId());
			if (iacDetail != null) {
				String ydjFlag = iacDetail.get("ydjFlag");
				String approveWay = iacDetail.get("approveWay");
				String resultType = "";
				String decisionDesp = "";
				String fqzRstDesp = "";
				String fqzRst = "";
				String decisionResult = "";
				if ("0".equals(approveWay)) { //自动
					Map<String, String> ficoREsponse = applyInfoDao.queryFicoBigResponse(biz.getAppId());
					if(ficoREsponse != null){
						resultType = ficoREsponse.get("resultType");
						decisionDesp = ficoREsponse.get("decisionDesp");
						fqzRstDesp = ficoREsponse.get("fqzRstDesp");
						fqzRst = ficoREsponse.get("fqzRst");
						decisionResult = ficoREsponse.get("decisionResult");
					}
				}
				// 归档表数据不为空 才添加
				Fishing addFish = new Fishing();
				paroperMap.put("ydjflag", biz.getYdjFlag());
				paroperMap.put("taoFlag", biz.getMatchingCardFlag());
				paroperMap.put("idNbr", biz.getC1Idnbr());
				paroperMap.put("c1C2Flag", biz.getC1c2Flag());
				paroperMap.put("c1Cname", biz.getC1Cname());
				paroperMap.put("c1Idtype", biz.getC1Idtype());
				paroperMap.put("c1Mobile", biz.getC1Mobile());
				paroperMap.put("quickCardFlag", biz.getQuickCardFlag());
				OpasBizApproval hisAllot = applyInfoDao.queryHisallot(appId);
				if (hisAllot != null) {//OpasBizApproval
					addFish.setPreApprovelimit(hisAllot.getPreApprovelimit());
					addFish.setPolicyCode1(hisAllot.getPolicyCode1());
					addFish.setPolicyCode2(hisAllot.getPolicyCode2());
					addFish.setPolicyCode3(hisAllot.getPolicyCode3());
					addFish.setViolateCode1(hisAllot.getViolateCode1());
					addFish.setViolateCode2(hisAllot.getViolateCode2());
					addFish.setViolateCode3(hisAllot.getViolateCode3());
					addFish.setRefuseCode1(hisAllot.getRefuseCode1());
					addFish.setRefuseCode2(hisAllot.getRefuseCode2());
					addFish.setRefuseCode3(hisAllot.getRefuseCode3());
					addFish.setMemo(hisAllot.getMemo());
					addFish.setApproveReuslt(hisAllot.getApproveResult());
				}else{
					addFish.setApproveReuslt(decisionResult);
				}
				addFish.setAutoId(StringUtil.randomUUIDPlainString());
				addFish.setAppId(appId);
				addFish.setApplyChannle(biz.getInputChannel());
				addFish.setApplyerName(biz.getC1Cname());
				addFish.setC1Idtype(biz.getC1Idtype());
				addFish.setC1Idnbr(biz.getC1Idnbr());
				addFish.setCrtDate(new Date());
				addFish.setApproveWay(iacDetail.get("approveWay"));
				addFish.setOperName(ctrUser);
				// addFish.setOperName(userCode);
				addFish.setRefuseReason(decisionDesp+fqzRstDesp);
				//addFish.setApproveReuslt(decisionResult);
				addFish.setYdjFlag(ydjFlag);
				if ("1".equals(approveWay)) {
					//手动
					if (hisAllot != null) {
						//addFish.setOperName(hisAllot.get("lastOptUser"));
					}
					addFish.setReturnLink("征信");
				} else {
					if ("SD0100".equals(resultType) || "YDJ0100".equals(resultType)) {
						addFish.setReturnLink("第一梯队三方查询");
					}
					if ("SD0200".equals(resultType) || "YDJ0200".equals(resultType)) {
						addFish.setReturnLink("第二梯队三方查询");
					}
					if ("SD0300".equals(resultType)) {
						addFish.setReturnLink("反欺诈决策4");
					}
					if ("YDJ0300".equals(resultType)) {
						addFish.setReturnLink("三方比对");
					}
					if ("SD0400".equals(resultType) || "YDJ0400".equals(resultType)) {
						addFish.setReturnLink("第三梯队三方查询");
					}
					if ("SD0600".equals(resultType)) {
						addFish.setReturnLink("录入");
					}
					if ("SD0700".equals(resultType) || "YDJ0500".equals(resultType)) {
						addFish.setReturnLink("征信策略");
					}
					if ("SD0800".equals(resultType) || "YDJ0600".equals(resultType)) {
						addFish.setReturnLink("征信电核");
					}
				}
				try {
					//记录生命周期表
					ApplyLifeCicle alc = new ApplyLifeCicle();
					alc.setAppId(appId);alc.setUuid(StringUtil.randomGUIDPlainString());
					alc.setCrtDate(new Date());
					alc.setOperateResult("完成");alc.setCrtUser(userCode);
					alc.setOperater(ctrUser+"-"+userCode);
					// 调工作流
					if ("1".equals(approveWay)) {
						// 手动归档-->征信 易达金.标卡
						if (logger.isInfoEnabled()) {
							logger.info("[捞件]-----------手动归档捞回appId:" + appId);
						}
						if("1".equals(ydjFlag)){//易达金
							this.doFlowPath(appId, paroperMap, "107");
						}else{//标准卡
							this.doFlowPath(appId, paroperMap, "109");
						}
						fishingService.insertFishing(addFish);
						alc.setOperateDesc("人工拒绝,捞件到'征信'");
						applyInfoDao.insertApplyLifeCicle(alc);
						// 删除归档数据
						if (logger.isInfoEnabled()) {
							logger.info("[捞件]-----------修改主建表 申请核准代码:" + appId);
						}
						if("00".equals(laojianFlag)){
							taoToFish(appId, addFish);
							taoKaOpter(appId, alc);
						}
						applyInfoDao.deleteIcationById(appId);
						applyInfoDao.updateBizC1C4VCODE(appId);
					} else {
						
						// SD1001 内部反欺诈1 YDJ1001 -->捞回三方
						if (logger.isInfoEnabled()) {
							logger.info("[捞件]-----------自动归档捞回appId:" + appId + ",[拒绝码]:" + resultType);
						}
						//SD0100 blaze决策1 -->第一梯队三方查询   YDJ0100 blaze决策1  -->第一梯队三方查询   
						if ("SD0100".equals(resultType) || "YDJ0100".equals(resultType)) {
							this.doFlowPath(appId, paroperMap, "101");
							fishingService.insertFishing(addFish);
							alc.setOperateDesc("blaze决策1拒绝,捞件到'第一梯队三方查询'");
							applyInfoDao.insertApplyLifeCicle(alc);
							 //删除归档数据
							if("00".equals(laojianFlag)){
								taoToFish(appId, addFish);
								taoKaOpter(appId, alc);
							}
							applyInfoDao.deleteIcationById(appId);
							applyInfoDao.updateBizC1C4VCODE(appId);
					
						}
						//SD0200 blaze决策2 -->第二梯队三方查询  YDJ0200 blaze决策2  -->第二梯队三方查询   
						if ("SD0200".equals(resultType) || "YDJ0200".equals(resultType)) {
							this.doFlowPath(appId, paroperMap, "102");
							alc.setOperateDesc("blaze决策2拒绝,捞件到'第二梯队三方查询'");
							fishingService.insertFishing(addFish);
							applyInfoDao.insertApplyLifeCicle(alc);
							// 删除归档数据
							if("00".equals(laojianFlag)){
								taoToFish(appId, addFish);
								taoKaOpter(appId, alc);
							}
							applyInfoDao.deleteIcationById(appId);
							applyInfoDao.updateBizC1C4VCODE(appId);
						}
						//SD0300 blaze决策3 -->分行人工预审   YDJ0300 blaze决策3 -->三方比对
						if ("SD0300".equals(resultType) || "YDJ0300".equals(resultType)) {
							this.doFlowPath(appId, paroperMap, "103");
							fishingService.insertFishing(addFish);
							if("1".equals(ydjFlag)){//易达金
								alc.setOperateDesc("blaze决策3拒绝,<易达金>捞件到'三方比对'");
							}else{//标准卡
								alc.setOperateDesc("blaze决策3拒绝,捞件到'反欺诈决策4'");
							}
							applyInfoDao.insertApplyLifeCicle(alc);
							 //删除归档数据
							if("00".equals(laojianFlag)){
								taoToFish(appId, addFish);
								taoKaOpter(appId, alc);
							}
							applyInfoDao.deleteIcationById(appId);
							applyInfoDao.updateBizC1C4VCODE(appId);
						}
						//SD0400 blaze决策4 --> 第三梯队三方查询       YDJ0400 blaze决策4  -->第三梯队三方查询   
						if ("SD0400".equals(resultType) || "YDJ0400".equals(resultType)) {
							this.doFlowPath(appId, paroperMap, "104");
							fishingService.insertFishing(addFish);
							alc.setOperateDesc("blaze决策4拒绝,捞件到'第三梯队三方查询 '");
							applyInfoDao.insertApplyLifeCicle(alc);
							 //删除归档数据
							if("00".equals(laojianFlag)){
								taoToFish(appId, addFish);
								taoKaOpter(appId, alc);
							}
							applyInfoDao.deleteIcationById(appId);
							applyInfoDao.updateBizC1C4VCODE(appId);
						}
						//YDJ0500 blaze决策5 （一次决策 ) --> 征信策略
						if ("YDJ0500".equals(resultType)) {
							this.doFlowPath(appId, paroperMap, "105");
							fishingService.insertFishing(addFish);
							alc.setOperateDesc("blaze决策5 （一次决策 )拒绝,捞件到'征信策略'");
							applyInfoDao.insertApplyLifeCicle(alc);
							 //删除归档数据
							if("00".equals(laojianFlag)){
								taoToFish(appId, addFish);
								taoKaOpter(appId, alc);
							}
							applyInfoDao.deleteIcationById(appId);
							applyInfoDao.updateBizC1C4VCODE(appId);
						}
						//SD0600 blaze决策6 --> 录入  YDJ0600 blaze决策6(二次决策) --> 征信电核
						if ("SD0600".equals(resultType) || "YDJ0600".equals(resultType)) {
							this.doFlowPath(appId, paroperMap, "106");
							fishingService.insertFishing(addFish);
							if("1".equals(ydjFlag)){//易达金
								alc.setOperateDesc("blaze决策6(二次决策)拒绝,<易达金>捞件到'征信电核'");
							}else{//标准卡
								alc.setOperateDesc("blaze决策6拒绝,捞件到'录入'");
							}
							applyInfoDao.insertApplyLifeCicle(alc);
							 //删除归档数据
							if("00".equals(laojianFlag)){
								taoToFish(appId, addFish);
								taoKaOpter(appId, alc);
							}
							applyInfoDao.deleteIcationById(appId);
							applyInfoDao.updateBizC1C4VCODE(appId);
						}
						//SD0700  一次决策（blaze7） --> 征信策略
						if ("SD0700".equals(resultType)) {
							this.doFlowPath(appId, paroperMap, "107");
							fishingService.insertFishing(addFish);
							alc.setOperateDesc(" 一次决策（blaze7）拒绝,捞件到'征信策略'");
							applyInfoDao.insertApplyLifeCicle(alc);
							 //删除归档数据
							if("00".equals(laojianFlag)){
								taoToFish(appId, addFish);
								taoKaOpter(appId, alc);
							}
							applyInfoDao.deleteIcationById(appId);
							applyInfoDao.updateBizC1C4VCODE(appId);
						}
						//SD0800  二次决策（blaze8） --> 征信电核
						if ("SD0800".equals(resultType)) {
							this.doFlowPath(appId, paroperMap, "108");
							fishingService.insertFishing(addFish);
							alc.setOperateDesc("二次决策（blaze8）拒绝,捞件到'征信电核'");
							applyInfoDao.insertApplyLifeCicle(alc);
							 //删除归档数据
							if("00".equals(laojianFlag)){
								taoToFish(appId, addFish);
								taoKaOpter(appId, alc);
							}
							applyInfoDao.deleteIcationById(appId);
							applyInfoDao.updateBizC1C4VCODE(appId);
						}
					}
					if (logger.isInfoEnabled()) {
						logger.info(">>>>>>>>>>>调流成功>>>>>>>>>>");
					}
				} catch (Exception e) {
					if (logger.isInfoEnabled()) {
						logger.info(">>>>>>>>>>>调流失败>>>>>>>>>>");
					}
					throw e;
				}
			}
			
		}
	}

	/**
	 * @param appId
	 * @param addFish
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws CoreException
	 */
	private void taoToFish(String appId, Fishing addFish)
			throws IllegalAccessException, InvocationTargetException, CoreException {
		Fishing addFishTao = new Fishing();
//		appId = appId.substring(0, appId.length()-1)+"1";
		appId="1".equals(appId.substring(appId.length()-1,appId.length()))?appId.substring(0, appId.length()-1)+"2":appId.substring(0, appId.length()-1)+"1";
		BeanUtils.copyProperties(addFishTao, addFish);
		addFishTao.setAppId(appId);
		addFishTao.setAutoId(StringUtil.randomUUIDPlainString());
		Map<String, String> iacDetail = applyInfoDao.queryIcationByAppId(appId);
		List<BizInpApplC1> queryBizAppToFish = applyInfoDao.queryBizAppToFish(appId);
		BizInpApplC1 biz = new BizInpApplC1();
		if(queryBizAppToFish != null && queryBizAppToFish.size()>0){
			biz = queryBizAppToFish.get(0);
		}
		if (iacDetail != null) {
			String ydjFlag = iacDetail.get("ydjFlag");
			String approveWay = iacDetail.get("approveWay");
			String resultType = "";
			String decisionDesp = "";
			String fqzRstDesp = "";
			String fqzRst = "";
			String decisionResult = "";
			if ("0".equals(approveWay)) { //自动
				Map<String, String> ficoREsponse = applyInfoDao.queryFicoBigResponse(appId);
				if(ficoREsponse != null){
					resultType = ficoREsponse.get("resultType");
					decisionDesp = ficoREsponse.get("decisionDesp");
					fqzRstDesp = ficoREsponse.get("fqzRstDesp");
					fqzRst = ficoREsponse.get("fqzRst");
					decisionResult = ficoREsponse.get("decisionResult");
				}
			}
			// 归档表数据不为空 才添加
			OpasBizApproval hisAllot = applyInfoDao.queryHisallot(appId);
			if (hisAllot != null) {//OpasBizApproval
				addFishTao.setPreApprovelimit(hisAllot.getPreApprovelimit());
				addFishTao.setPolicyCode1(hisAllot.getPolicyCode1());
				addFishTao.setPolicyCode2(hisAllot.getPolicyCode2());
				addFishTao.setPolicyCode3(hisAllot.getPolicyCode3());
				addFishTao.setViolateCode1(hisAllot.getViolateCode1());
				addFishTao.setViolateCode2(hisAllot.getViolateCode2());
				addFishTao.setViolateCode3(hisAllot.getViolateCode3());
				addFishTao.setRefuseCode1(hisAllot.getRefuseCode1());
				addFishTao.setRefuseCode2(hisAllot.getRefuseCode2());
				addFishTao.setRefuseCode3(hisAllot.getRefuseCode3());
				addFishTao.setMemo(hisAllot.getMemo());
				addFishTao.setApproveReuslt(hisAllot.getApproveResult());
			}else{
				addFishTao.setApproveReuslt(decisionResult);
			}
			addFishTao.setApplyChannle(biz.getInputChannel());
			addFishTao.setApplyerName(biz.getC1Cname());
			addFishTao.setC1Idtype(biz.getC1Idtype());
			addFishTao.setC1Idnbr(biz.getC1Idnbr());
			addFishTao.setCrtDate(new Date());
			addFishTao.setApproveWay(iacDetail.get("approveWay"));
			addFishTao.setRefuseReason(decisionDesp+fqzRstDesp);
			addFishTao.setYdjFlag(ydjFlag);
		}
		fishingService.insertFishing(addFishTao);
	}

	/**
	 * @param appId
	 * @param alc
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	private void taoKaOpter(String appId, ApplyLifeCicle alc) throws IllegalAccessException, InvocationTargetException {
		String appId0="1".equals(appId.substring(appId.length()-1,appId.length()))?appId.substring(0, appId.length()-1)+"2":appId.substring(0, appId.length()-1)+"1";
		applyInfoDao.deleteIcationById(appId0);
		applyInfoDao.updateBizC1C4VCODE(appId0);
		ApplyLifeCicle alcCopy = new ApplyLifeCicle();
		BeanUtils.copyProperties(alcCopy, alc);
		alcCopy.setAppId(appId0);
		alcCopy.setUuid(StringUtil.randomGUIDPlainString());
		applyInfoDao.insertApplyLifeCicle(alcCopy);
	}

	/**
	 * 调用工作流节点
	 * 
	 * @param appId
	 * @param nodeId
	 */
	private void doFlowPath(String appId, Map<String, String> paroperMap, String ruleOpt) throws Exception {
		String ydjflag = paroperMap.get("ydjflag");
		String taoFlag = paroperMap.get("taoFlag");
		String idNbr = paroperMap.get("idNbr");
		String c1C2Flag = paroperMap.get("c1C2Flag");
//		String process_IP = paroperMap.get("process_IP");
		String laojianFlag = paroperMap.get("laojianFlag");
		String c1Cname = paroperMap.get("c1Cname");
		String c1Idtype = paroperMap.get("c1Idtype");
		String c1Mobile = paroperMap.get("c1Mobile");
		String quickCardFlag = paroperMap.get("quickCardFlag");
		
//		Client client = null;
		try {
			// 判断 主附卡
			if ("2".equals(c1C2Flag)) { // 单附
				BizInpApplC2 biz2 = applyInfoDao.findBiz2info(appId);
				idNbr = biz2.getC2Idnbr();
				appId = biz2.getAppId();
				c1Cname = biz2.getC2Cname();
				c1Idtype = biz2.getC2Idtype();
				c1Mobile = biz2.getC2Mobile();
			}
//			client = new Client(
//					new URL("http://" + process_IP + "/topbpm_test/services/BranchBankSearchServiceI?wsdl"));
			ArrayList<String> list = new ArrayList<String>();
			ArrayList<String> list1 = new ArrayList<String>();

			if ("2".equals(ydjflag)) {
				if(!"00".equals(laojianFlag)){
					laojianFlag = "0"+appId.substring(appId.length()-1, appId.length());
				}
				list.add("{'app_id':'" + appId + "','ydjflag':'" + ydjflag + "','taoFlag':'" + taoFlag + "','c1C2Flag':'"
						+ c1C2Flag + "','idNbr':'" + idNbr + "','ruleOpt':'" + ruleOpt +"','laojianFlag':'"+laojianFlag+ "','name':'" + c1Cname +"','idType':'"+c1Idtype+"','mobile':'" + c1Mobile
						+ "','quickCardFlag':'" + quickCardFlag + "'}");
				list1.add("{'app_id':'" + appId + "','ydjflag':'" + ydjflag + "','taoFlag':'" + taoFlag + "','c1C2Flag':'"
						+ c1C2Flag + "','idNbr':'" + "******" + "','ruleOpt':'" + ruleOpt +"','laojianFlag':'"+laojianFlag+ "','name':'" + "***" +"','idType':'"+c1Idtype+"','mobile':'" + "******"
						+ "','quickCardFlag':'" + quickCardFlag + "'}");
			} else if ("1".equals(ydjflag)) {
				if(!"00".equals(laojianFlag)){
					laojianFlag = "0"+appId.substring(appId.length()-1, appId.length());
				}
				list.add("{'app_id':'" + appId + "','ydjflag':'" + ydjflag + "','taoFlag':'" + taoFlag + "','c1C2Flag':'"
						+ c1C2Flag + "','idNbr':'" + idNbr + "','ruleOpt_':'" + ruleOpt + "','laojianFlag':'"+laojianFlag+"','name':'" + c1Cname +"','idType':'"+c1Idtype+"','mobile':'" + c1Mobile
						+ "','quickCardFlag':'" + quickCardFlag + "'}");
				
				list1.add("{'app_id':'" + appId + "','ydjflag':'" + ydjflag + "','taoFlag':'" + taoFlag + "','c1C2Flag':'"
						+ c1C2Flag + "','idNbr':'" + "******" + "','ruleOpt_':'" + ruleOpt + "','laojianFlag':'"+laojianFlag+"','name':'" + "***" +"','idType':'"+c1Idtype+"','mobile':'" + "******"
						+ "','quickCardFlag':'" + quickCardFlag + "'}");
			}
			
			for (int i = 0; i < list.size(); i++) {
//				Object[] obj = new Object[] { list.get(i) };
				String obj = list1.get(i);
				if (logger.isInfoEnabled()) {
					logger.info(">>参数:>>----"+list1.get(i).toString()+"----->>>>>>>>>>");
				}
//				Object[] result = null;
				String result = "";
				if ("2".equals(ydjflag)) {
//					result = client.invoke("StartProcessBzk", obj);// .toString();

					result = topbpmService.StartProcessBzk(obj).toString();
				} else if ("1".equals(ydjflag)) {
//					result = client.invoke("StartProcessYdj", obj);// .toString();
					result = topbpmService.StartProcessYdj(obj).toString();
				}
				logger.info("startProcess==============[ {} ]" , result);
			}
		} catch (Exception e) {
			if (logger.isInfoEnabled()) {
				logger.info(">>捞件:>>----"+"调流失败"+"----->>>>>>>>>>");
			}
			throw new Exception(">>捞件:>>----"+"调流失败"+"----->>>>>>>>>>");
		}finally {
//			if (client != null) {
//				client.close();
//				client = null;
//			}
		}
		
	}
	
	@Override
	public void updateApplyAllotAndInsert(Map<String, String> matchingCard) {
		String appId = matchingCard.get("appId");
		Map mapParms = new HashMap<String, String>();
		String matchingCardFlag = matchingCard.get("matchingCardFlag");
		List<String> appIdList = new ArrayList<>();
		appIdList.add(appId);
		if("1".equals(matchingCardFlag) || "2".equals(matchingCardFlag)){//套卡
			String lastApp = appId.substring(appId.length()-1, appId.length());
			if("1".equals(lastApp)){
				appIdList.add(appId.substring(0, appId.length()-1)+"2");
			}else if("2".equals(lastApp)){
				appIdList.add(appId.substring(0, appId.length()-1)+"1");
			}
		}
		List<Map> map = applyInfoDao.selectAppByReviewDecision(appIdList);
		List<AllotApplyAllotHis> allotList = new ArrayList<>();
		if (map != null && map.size() > 0) {
			for (Map map2 : map) {
				AllotApplyAllotHis allotApplyHis = JSON.parseObject(JSON.toJSONString(map2), AllotApplyAllotHis.class);
				String id = StringUtil.randomUUIDPlainString();
				allotApplyHis.setId(id);
				allotApplyHis.setRecordTeamDate(new Date());
				String currNode = StringUtil.trimToEmpty(allotApplyHis.getCurrNode());
				if("02".equals(currNode)){ // 征信
					allotApplyHis.setBackFlag("A");
				}else if("03".equals(currNode)){ //审批
					allotApplyHis.setBackFlag("B");
				}
				allotList.add(allotApplyHis);
			}
		}
		try {
			if(allotList != null && allotList.size() >0){
				for (AllotApplyAllotHis allotApplyAllotHis : allotList) {
					allotApplyHisService.saveAllotApplyHis(allotApplyAllotHis);
				}
			}
			//----------------------更新  --------继续分件表
			String currNode = matchingCard.get("DEL_STATUS");
			mapParms.put("appId", appId.substring(0, appId.length()-1));
			mapParms.put("currNode", currNode);
			applyInfoDao.updateAllotApply(mapParms);
		} catch (CoreException e) {
			logger.error("更新新分件表异常：{}", new Object[] { e.getMessage() }, e);
		}
	}

	@Override
	public Map<String, String> queryMatchingCardFlagAndYdjFalg(String appId) {
		return applyInfoDao.queryMatchingCardFlagAndYdjFalg(appId);
	}

	@Override
	public List<Map<String, String>> queryMatchingCardFlagAndYdjFalgByIdNbr(Map<String, String> matchingCard) {
		return applyInfoDao.queryMatchingCardFlagAndYdjFalgByIdNbr(matchingCard);
	}

	@Override
	public void updateBizInfoMatchingCard(BizInpAppC1Spec updateBizInpApplC1,List<BaseCustInfo> bcfList) {
		try {
			applyInfoDao.updateBizInfoMatchingCard(updateBizInpApplC1);
			if(bcfList != null && bcfList.size() >0){
				for (BaseCustInfo baseCustInfo : bcfList) {
					baseCustInfoDao.updateCustInfo(baseCustInfo);
				}
			}
		} catch (CoreException e) {
			logger.error("更新客户基本信息异常：{}", new Object[] { e.getMessage() }, e);
		}
	}
	
	@Override
	public void updateBizInfoMatchingCardBzk(BizInpAppC1Spec updateBizInpApplC1,List<BaseCustInfo> bcfList) {
		try {
			applyInfoDao.updateBizInfoMatchingCardBzk(updateBizInpApplC1);
			if(bcfList != null && bcfList.size() >0){
				for (BaseCustInfo baseCustInfo : bcfList) {
					baseCustInfoDao.updateCustInfo(baseCustInfo);
				}
			}
		} catch (CoreException e) {
			logger.error("更新标准卡客户基本信息异常：{}", new Object[] { e.getMessage() }, e);
		}
	}
	
	@Override
	public void updateBizInfoMatchingCardYdj(BizInpAppC1Spec updateBizInpApplC1,List<BaseCustInfo> bcfList) {
		try {
			applyInfoDao.updateBizInfoMatchingCardYdj(updateBizInpApplC1);
			if(bcfList != null && bcfList.size() >0){
				for (BaseCustInfo baseCustInfo : bcfList) {
					baseCustInfoDao.updateCustInfo(baseCustInfo);
				}
			}
		} catch (CoreException e) {
			logger.error("更新易达金客户基本信息异常：{}", new Object[] { e.getMessage() }, e);
		}
	}

	@Override
	public List<Map<String, String>> queryBaseCustByIdNbr(Map<String, String> matchingCard) {
		return baseCustInfoDao.queryBaseCustByIdNbr(matchingCard);
	}

	@Override
	public void updateBizInfoKeyMes(BizInpAppC1Spec keyMesageApply1) {
		applyInfoDao.updateBizInfoKeyMes(keyMesageApply1);
	}

	@Override
	public int queryApplyInfoCount(Map<String, Object> map) throws CoreException {
		return applyInfoDao.queryApplyInfoCount(map);
	}

	@Override
	public List<Map<String, String>> queryApplyInfoMap(Map<String, Object> map, int curNum, int pageNum)
			throws CoreException {
		return applyInfoDao.queryApplyInfoMap(map, curNum, pageNum);
	}

	@Override
	public void saveRiskCheck(List<RiskCheckResult> listriskcheckresult, BizInpApplC1 apply_Info1) {
		String queryAppId = apply_Info1.getAppId();
		if(apply_Info1 != null){
			 queryAppId = "1".equals(apply_Info1.getYdjFlag())?queryAppId.substring(0,queryAppId.length()-1)+"2":queryAppId;
		}
		// 判断数据库是否已经存在对应appid记录，没有新增，有则修改
		Map<String, String> oldriskinfo = riskCheckService.Query_checkResult(queryAppId);
		KeyfiledMarchinfo keyFiledMarchInfo = new KeyfiledMarchinfo();
		if (oldriskinfo != null && oldriskinfo.size() >= 0) {// 存在记录
			keyFiledMarchInfo.setAppId(oldriskinfo.get("appId"));
			// 将原存在结果集反序列成对象
			List<RiskCheckResult> listresult = JsonUtil.String2ObjectsList(oldriskinfo.get("marchResult"),
					RiskCheckResult.class);
			List<RiskCheckResult> listresult2 = new ArrayList<RiskCheckResult>();
			List<RiskCheckResult> listresult3 = new ArrayList<RiskCheckResult>();
			if (listresult == null)
				listresult = new ArrayList<RiskCheckResult>();
			// 2017-4-05 --修改
			for (RiskCheckResult riskCheckResult : listriskcheckresult) {
				if (!listresult.contains(riskCheckResult)) {// 原结果集不包含当前的结果
					listresult3.add(riskCheckResult);// 记录需要新加的结果
				} else {// 包含
					for (RiskCheckResult riskCheckResultOld : listresult) {
						if (riskCheckResult.equals(riskCheckResultOld)) {
							listresult2.add(riskCheckResultOld);// 记录需要删除的结果集
							listresult3.add(riskCheckResult);
						}
					}
				}

			}
			// 替换原来存在的结果 删除重复的结果
			listresult.removeAll(listresult2);
			listresult.addAll(listresult3);
			// listresult.addAll(listriskcheckresult);
			// 结果序列化成字符串保存到数据库
			JSONArray jarray = JSONArray.fromObject(listresult);
			keyFiledMarchInfo.setMarchResult(jarray.toString());
			keyFiledMarchInfo.setCrtDate(new Date());
			// 更新数据库记录
			riskCheckService.update_checkResult(keyFiledMarchInfo);
		} else {
			keyFiledMarchInfo.setAppId(queryAppId);
			keyFiledMarchInfo.setCrtUser("SYSTEM");
			keyFiledMarchInfo.setCrtDate(new Date());
			// 结果序列化
			// -------- 开始-----------------
			ArrayList<RiskCheckResult> lischeck = new ArrayList<RiskCheckResult>();
			lischeck.addAll(listriskcheckresult);
			JSONArray jarray = JSONArray.fromObject(lischeck);
			keyFiledMarchInfo.setMarchResult(jarray.toString());
			keyFiledMarchInfo.setAutoId(StringUtil.randomUUIDPlainString());
			// --------结束------------------
			// 保存入库
			riskCheckService.save_checkResult(keyFiledMarchInfo);
		}
	}

	@Override
	public AllotApply queryApplyAllotByAppId(String appId) {
		return applyInfoDao.queryApplyAllotByAppId(appId);
	}

	@Override
	public String qeurUserNameByUserCode(String user) {
		return keyMessageModifyDao.qeurUserNameByUserCode(user);
	}

	@Override
	public void insertApplyLifeCicle(ApplyLifeCicle alc) {
		applyInfoDao.insertApplyLifeCicle(alc);
	}

	@Override
	public Map<String,String> selectAllotMap(String appId) {
		return applyInfoDao.selectAllotMap(appId);
	}

	@Override
	public Map<String, Object> selectExprotCurrentInfoYdj(String appId) {
		return applyInfoDao.selectExprotCurrentInfoYdj(appId);
	}

	@Override
	public Map<String, Object> selectExprotCurrentInfoBzk(String appId) {
		return applyInfoDao.selectExprotCurrentInfoBzk(appId);
	}

	@Override
	public Map<String, Object> selectExprotCurrentInfoYdjHis(String appId) {
		return applyInfoDao.selectExprotCurrentInfoYdjHis(appId);
	}

	@Override
	public Map<String, Object> selectExprotCurrentInfoBzkHis(String appId) {
		return applyInfoDao.selectExprotCurrentInfoBzkHis(appId);
	}

	@Override
	public void updateBizInfoKeyMes2(BizInpApplC2 keyMesageApply2) {
		applyInfoDao.updateBizInfoKeyMes2(keyMesageApply2);
	}

	@Override
	public int queryApplyInfoCountWithPatchCode(Map<String, Object> params) {
		return applyInfoDao.queryApplyInfoCountWithPatchCode(params);
	}

	@Override
	public List<Map<String, String>> queryApplyInfoMapWithPatchCode(Map<String, Object> params, int curNum,
			int pageNum) {
		return applyInfoDao.queryApplyInfoMapWithPatchCode(params,curNum,pageNum);
	}

	@Override
	public String queryApplyLifeCicle(String appIdPlus) {
		return applyInfoDao.queryApplyLifeCicle(appIdPlus);
	}

	@Override
	public int queryWsdbInfoCount(Map<String, Object> map) throws CoreException {
		return applyInfoDao.queryWsdbInfoCount(map);
	}

	@Override
	public List<Map<String, String>> queryWsdbInfoMap(Map<String, Object> map, int curNum, int pageNum)
			throws CoreException {
		return applyInfoDao.queryWsdbInfoMap(map, curNum, pageNum);
	}

	@Override
	public int queryWsdbNodeCount(Map<String, String> params) {
		return applyInfoDao.queryWsdbNodeCount(params);
	}

	@Override
	public List<Map<String, Object>> selectWsdbNodeList(Map<String, String> params, int curNum, int pageNum) {
		return applyInfoDao.selectWsdbNodeList(params, curNum, pageNum);
	}

	@Override
	public Map<String, Object> selectWsdbInfoByAppId(String appId) {
		return applyInfoDao.selectWsdbInfoByAppId(appId);
	}
	
	@Override
	public int queryStockCustomerInfoCount(Map<String, Object> map) throws CoreException {
		return applyInfoDao.queryStockCustomerInfoCount(map);
	}

	@Override
	public List<Map<String, String>> queryStockCustomerInfoMap(Map<String, Object> map, int curNum, int pageNum)
			throws CoreException {
		return applyInfoDao.queryStockCustomerInfoMap(map, curNum, pageNum);
	}

	@Override
	public int queryStockCustomerNodeCount(Map<String, String> params) {
		return applyInfoDao.queryStockCustomerNodeCount(params);
	}

	@Override
	public List<Map<String, Object>> selectStockCustomerNodeList(Map<String, String> params, int curNum, int pageNum) {
		return applyInfoDao.selectStockCustomerNodeList(params, curNum, pageNum);
	}

	@Override
	public Map<String, Object> selectStockCustomerInfoByAppId(String appId) {
		return applyInfoDao.selectStockCustomerInfoByAppId(appId);
	}

	@Override
	public List<Object> selectStockCustomerCardInfoByAppId(String appId) {
		List<Object> stockCustomerCardList = new ArrayList<>();
		Map<String,String> stockCustomerResult = applyInfoDao.selectStockCustomerCardResultByAppId(appId);
		String finish = "", remark = "";
		String[] remarkList= null ;
		if(stockCustomerResult!=null){
			if(stockCustomerResult.containsKey("FINISH")){
				finish = stockCustomerResult.get("FINISH");
			}
			if(stockCustomerResult.containsKey("REMARK")){
				remark = stockCustomerResult.get("REMARK");
				remarkList = remark.split("\\|");
			}
		}
	    // M-1-0103-LM08-F -000000|S-2-0104-LM10-14-000000 快速发卡数据切割   
		if(remarkList !=null && remarkList.length>0){
			for(String reTemp : remarkList){
				String[] remarkCell = reTemp.split("-",-1);
				Map<String,Object> stockCardInfo = new HashMap<>();
				stockCardInfo.put("appId", appId.substring(0,appId.length()-1)+(String)remarkCell[1]);
				stockCardInfo.put("productName", (String)remarkCell[2]);
				stockCardInfo.put("remark", (String)remarkCell[7]);
				stockCustomerCardList.add(stockCardInfo);
			}
		}
		return stockCustomerCardList;
	}
	
}
