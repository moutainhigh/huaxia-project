package com.huaxia.opas.service.credit.impl;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.apply.ApplyLifeCicleDao;
import com.huaxia.opas.dao.archive.SuppArDao;
import com.huaxia.opas.dao.credit.PatchboltDao;
import com.huaxia.opas.dao.decision.TelcheckAddnoteDao;
import com.huaxia.opas.dao.system.ApUserDao;
import com.huaxia.opas.domain.allot.AllotApplyAllot;
import com.huaxia.opas.domain.allot.AllotApplyAllotHis;
import com.huaxia.opas.domain.apply.ApplyLifeCicle;
import com.huaxia.opas.domain.archive.SuppArchive;
import com.huaxia.opas.domain.decision.TelcheckAddnote;
import com.huaxia.opas.domain.input.BizInpApplC1;
import com.huaxia.opas.domain.sysparm.Patchbolt;
import com.huaxia.opas.domain.sysparm.PatchboltYdj;
import com.huaxia.opas.domain.system.ApUser;
import com.huaxia.opas.service.AbstractService;
import com.huaxia.opas.service.credit.PatchboltService;
import com.huaxia.opas.util.TransDateUtil;

public class PatchboltServiceImpl extends AbstractService implements PatchboltService, Serializable {

	private static final long serialVersionUID = -5286276550554333492L;
	private static Logger logger = LoggerFactory.getLogger(PatchboltServiceImpl.class);

	@Resource(name = "suppArDao")
	private SuppArDao suppArDao;
	@Resource(name = "patchboltDao")
	public PatchboltDao patchboltDao;
	@Resource(name = "telcheckAddnoteDao")
	private TelcheckAddnoteDao telcheckAddnoteDao;
	@Resource(name = "apUserDao")
	ApUserDao apUserDao;
	@Resource(name = "applyLifeCicleDao")
	ApplyLifeCicleDao applyLifeCicleDao;
	public PatchboltDao getPatchboltDao() {
		return patchboltDao;
	}

	public void setPatchboltDao(PatchboltDao patchboltDao) {
		this.patchboltDao = patchboltDao;
	}

	public TelcheckAddnoteDao getTelcheckAddnoteDao() {
		return telcheckAddnoteDao;
	}

	public void setTelcheckAddnoteDao(TelcheckAddnoteDao telcheckAddnoteDao) {
		this.telcheckAddnoteDao = telcheckAddnoteDao;
	}

	//标准卡/易达金配发卡一次补件
	@Override
	public void insertPatchbolt(Patchbolt patchbolt) throws CoreException, ParseException {
		insertOrUpdatePatchbolt(patchbolt);
		// 注记信息保存
		TelcheckAddnote record = new TelcheckAddnote();
		String uuid = getUUID();
		String appId = patchbolt.getAppId();
		record.setAppId(appId);
		record.setAutoId(uuid);
		record.setCrtDate(new Date());
		// bigMemo保存
		StringBuffer bigMemo = new StringBuffer("补件材料:");
		if ("1".equals(patchbolt.getIdentityCard())) {
			bigMemo.append("身份证明文件;");
		}
		if ("1".equals(patchbolt.getIsHavesign())) {
			bigMemo.append("签名;");
		}
		if ("1".equals(patchbolt.getWorkConfirm())) {
			bigMemo.append("工作证明文件;");
		}
		if ("1".equals(patchbolt.getIncomeConfirm())) {
			bigMemo.append("收入证明文件;");
		}
		if ("1".equals(patchbolt.getLiveConfirm())) {
			bigMemo.append("居住证明文件;");
		}
		if ("1".equals(patchbolt.getHouseConfirm())) {
			bigMemo.append("房产证明;");
		}
		if ("1".equals(patchbolt.getDrivingLicense())) {
			bigMemo.append("行驶证;");
		}
		if ("1".equals(patchbolt.getEduConfirm())) {
			bigMemo.append("学历证明;");
		}
		if ("1".equals(patchbolt.getTitleConfirm())) {
			bigMemo.append("资格证书;");
		}
		if ("1".equals(patchbolt.getOtherCreditcard())) {
			bigMemo.append("他行信用卡或账单;");
		}
		if ("1".equals(patchbolt.getOwnerBankConfirm())) {
			bigMemo.append("我行业务往来证明;");
		}
		if ("1".equals(patchbolt.getFinancalConfirm())) {
			bigMemo.append("财力证明;");
		}
		if ("1".equals(patchbolt.getOthers())) {
			bigMemo.append("其他;");
		}
		if ("1".equals(patchbolt.getNonAddfiles())) {
			bigMemo.append("未补充资料;");
		}
		if (!"".equals(patchbolt.getMemo()) && patchbolt.getMemo() != null) {
			bigMemo.append(" 备注:").append(patchbolt.getMemo());
		}
		String crtUser = patchbolt.getCrtUser();
		ApUser apUser = apUserDao.queryApUserByUserCode(crtUser);
		bigMemo.append(" 操作人："+apUser.getUserName()).append(" 操作时间"+new SimpleDateFormat("yyyy-MM-dd").format(patchbolt.getCrtDate()));
		record.setBigMemo(bigMemo.toString());
		record.setTypeFlag("5");
		String talId = UUID.randomUUID().toString().replace("-", "");
		record.setTalId(talId);
		telcheckAddnoteDao.insert(record);
		
		Map<String,String> map= patchboltDao.querySomeFlagFromAllot(appId);
		String ydjFlag = "";
		String laoJianFlag = "";
		if(map!=null){
			ydjFlag = map.get("YDJ_FLAG");
		    laoJianFlag = map.get("LAOJIANFLAG");
		}
		
		PatchboltYdj patchboltYdj = new PatchboltYdj();
		String uuid2 = getUUID();
		if("1".equals(ydjFlag)&&!"01".equals(laoJianFlag)&&!"02".equals(laoJianFlag)){
			
			if(!"0".equals(map.get("MATCHING_CARD_FLAG"))){
				if("1".equals(appId.substring(15,16))){
					record.setAppId(appId.substring(0,15)+"2");
					patchboltYdj.setAppId(appId.substring(0,15)+"2");
				}else{
					record.setAppId(appId.substring(0,15)+"1");
					patchboltYdj.setAppId(appId.substring(0,15)+"1");
				}
				patchboltYdj.setAutoId(uuid2);
				patchboltYdj.setDueDate(patchbolt.getDueDate());
				patchboltYdj.setMemo(patchbolt.getMemo());
				patchboltYdj.setSendFlag(patchbolt.getSendFlag());
				patchboltYdj.setCompletedFlag("0");
				insertOrUpdatePatchboltYdj(patchboltYdj);
				record.setAutoId(UUID.randomUUID().toString().replace("-", ""));
				telcheckAddnoteDao.insert(record);
			}
		}
	}

	private void insertOrUpdatePatchbolt(Patchbolt patchbolt) throws CoreException {
		int pbnum = patchboltDao.queryPatchboltCountByAppId(patchbolt.getAppId());
		if (pbnum == 0) {
			patchboltDao.insertPatchbolt(patchbolt);
		} else {
			patchboltDao.updateByAppId(patchbolt);
		}
	}

	//标准卡/配发卡
	@Override
	public void updateByAppId(Patchbolt patchbolt) throws CoreException, ParseException {
		patchboltDao.updateByAppId(patchbolt);
		patchboltDao.deleteByAppId(patchbolt.getAppId());
		String uuid = getUUID();
		// 注记信息保存
		TelcheckAddnote record = new TelcheckAddnote();
		String appId = patchbolt.getAppId();
		record.setAppId(appId);
		record.setAutoId(uuid);
		record.setCrtDate(new Date());
		// bigMemo保存
		StringBuffer bigMemo = new StringBuffer("补件材料:");
		if ("1".equals(patchbolt.getIdentityCard())) {
			bigMemo.append("身份证明文件;");
		}
		if ("1".equals(patchbolt.getIsHavesign())) {
			bigMemo.append("签名;");
		}
		if ("1".equals(patchbolt.getWorkConfirm())) {
			bigMemo.append("工作证明文件;");
		}
		if ("1".equals(patchbolt.getIncomeConfirm())) {
			bigMemo.append("收入证明文件;");
		}
		if ("1".equals(patchbolt.getLiveConfirm())) {
			bigMemo.append("居住证明文件;");
		}
		if ("1".equals(patchbolt.getHouseConfirm())) {
			bigMemo.append("房产证明;");
		}
		if ("1".equals(patchbolt.getDrivingLicense())) {
			bigMemo.append("行驶证;");
		}
		if ("1".equals(patchbolt.getEduConfirm())) {
			bigMemo.append("学历证明;");
		}
		if ("1".equals(patchbolt.getTitleConfirm())) {
			bigMemo.append("资格证书;");
		}
		if ("1".equals(patchbolt.getOtherCreditcard())) {
			bigMemo.append("他行信用卡或账单;");
		}
		if ("1".equals(patchbolt.getOwnerBankConfirm())) {
			bigMemo.append("我行业务往来证明;");
		}
		if ("1".equals(patchbolt.getFinancalConfirm())) {
			bigMemo.append("财力证明;");
		}
		if ("1".equals(patchbolt.getOthers())) {
			bigMemo.append("其他;");
		}
		if ("1".equals(patchbolt.getNonAddfiles())) {
			bigMemo.append("未补充资料;");
		}
		if (!"".equals(patchbolt.getMemo()) && patchbolt.getMemo() != null) {
			bigMemo.append(" 备注:").append(patchbolt.getMemo());
		}
		String crtUser = patchbolt.getCrtUser();
		ApUser apUser = apUserDao.queryApUserByUserCode(crtUser);
		bigMemo.append(" 操作人："+apUser.getUserName()).append("  操作时间:"+ new SimpleDateFormat("yyyy-MM-dd").format(patchbolt.getCrtDate()));
		record.setBigMemo(bigMemo.toString());
		record.setTypeFlag("5");
		record.setTalId(UUID.randomUUID().toString().replace("-", ""));
		telcheckAddnoteDao.insert(record);
		

		Map<String,String> map= patchboltDao.querySomeFlagFromAllot(appId);
		String ydjFlag = "";
		String laoJianFlag = "";
		if(map!=null){
			ydjFlag = map.get("YDJ_FLAG");
		    laoJianFlag = map.get("LAOJIANFLAG");
		}
		if("1".equals(ydjFlag)&&!"01".equals(laoJianFlag)&&!"02".equals(laoJianFlag)){//易达金的配发卡
			PatchboltYdj patchboltYdj = new PatchboltYdj();
			
			if(!"0".equals(map.get("MATCHING_CARD_FLAG"))){
				if("1".equals(appId.substring(15,16))){
					record.setAppId(appId.substring(0,15)+"2");
					patchboltYdj.setAppId(appId.substring(0,15)+"2");
				}else{
					record.setAppId(appId.substring(0,15)+"1");
					patchboltYdj.setAppId(appId.substring(0,15)+"1");
				}
				patchboltYdj.setCrtDate(patchbolt.getCrtDate());
				patchboltYdj.setCrtUser(patchbolt.getCrtUser());
				patchboltYdj.setMemo(patchbolt.getMemo());
				patchboltYdj.setDueDate(patchbolt.getDueDate());
				patchboltYdj.setSendFlag(patchbolt.getSendFlag());
				patchboltYdj.setCompletedFlag(patchbolt.getCompletedFlag());
				patchboltDao.updateByAppIdYdj(patchboltYdj);

				patchboltDao.deleteByAppId(patchboltYdj.getAppId());
				record.setAutoId(UUID.randomUUID().toString().replace("-", ""));
				telcheckAddnoteDao.insert(record);
			}
		}
	}

	// 用于二次补件反显已勾选数据 2017-03-28
	@Override
	public Patchbolt selectByPrimaryKey(String appId) throws CoreException {
		return patchboltDao.selectByPrimaryKey(appId);
	}

	//易达金的卡
	@Override
	public void insertPatchboltYdj(PatchboltYdj patchboltYdj) throws CoreException, ParseException {
		insertOrUpdatePatchboltYdj(patchboltYdj);
		// 注记信息保存
		TelcheckAddnote record = new TelcheckAddnote();
		String uuid = getUUID();
		String appId = patchboltYdj.getAppId();
		record.setAppId(appId);
		record.setAutoId(uuid);
		record.setCrtDate(new Date());
		// bigMemo保存
		StringBuffer bigMemo = new StringBuffer("补件材料:");
		if ("1".equals(patchboltYdj.getIdentityCard())) {
			bigMemo.append("身份证明文件;");
		}
		if ("1".equals(patchboltYdj.getPayWater())) {
			bigMemo.append("工资流水;");
		}
		if ("1".equals(patchboltYdj.getWorkProof())) {
			bigMemo.append("工作证明文件;");
		}
		if ("1".equals(patchboltYdj.getIncomeProof())) {
			bigMemo.append("收入证明文件;");
		}
		if ("1".equals(patchboltYdj.getSocialSecurity())) {
			bigMemo.append("社保;");
		}
		if ("1".equals(patchboltYdj.getHouseProof())) {
			bigMemo.append("房产证明;");
		}
		if ("1".equals(patchboltYdj.getAccumulationFund())) {
			bigMemo.append("公积金;");
		}
		if ("1".equals(patchboltYdj.getEducationProof())) {
			bigMemo.append("学历证明;");
		}
		if ("1".equals(patchboltYdj.getTaxReceipt())) {
			bigMemo.append("税单;");
		}
		if ("1".equals(patchboltYdj.getCreditCardBill())) {
			bigMemo.append("信用卡账单;");
		}
		if ("1".equals(patchboltYdj.getOwnerBankProof())) {
			bigMemo.append("我行业务往来证明;");
		}
		if ("1".equals(patchboltYdj.getFinancalProof())) {
			bigMemo.append("财力证明;");
		}
		if ("1".equals(patchboltYdj.getLoanSettleProof())) {
			bigMemo.append("贷款结清证明;");
		}
		if ("1".equals(patchboltYdj.getRprProof())) {
			bigMemo.append("户口证明;");
		}
		if ("1".equals(patchboltYdj.getMarriageCertificate())) {
			bigMemo.append("结婚证;");
		}
		if ("1".equals(patchboltYdj.getBusinessLicense())) {
			bigMemo.append("企业营业执照;");
		}
		if ("1".equals(patchboltYdj.getCarProof())) {
			bigMemo.append("车产证明;");
		}
		if ("1".equals(patchboltYdj.getRetricalMasterCopy())) {
			bigMemo.append("调阅原件;");
		}
		if ("1".equals(patchboltYdj.getImageRepair())) {
			bigMemo.append("影像修复;");
		}
		if ("1".equals(patchboltYdj.getOther())) {
			bigMemo.append("其他;");
		}
		if (!"".equals(patchboltYdj.getMemo()) && patchboltYdj.getMemo() != null) {
			bigMemo.append(" 备注:").append(patchboltYdj.getMemo());
		}
		String crtUser = patchboltYdj.getCrtUser();
		ApUser apUser = apUserDao.queryApUserByUserCode(crtUser);
		bigMemo.append(" 操作人："+apUser.getUserName()).append("  操作时间:"+ new SimpleDateFormat("yyyy-MM-dd").format(patchboltYdj.getCrtDate()));
		record.setBigMemo(bigMemo.toString());
		record.setTypeFlag("5");
		String talId = UUID.randomUUID().toString().replace("-", "");
		record.setTalId(talId);
		telcheckAddnoteDao.insert(record);
		
		String ydjFlag = "";
		String laoJianFlag = "";
		Map<String, String> flagMap = patchboltDao.querySomeFlagFromAllot(appId);
		if(flagMap!=null){
			ydjFlag = flagMap.get("YDJ_FLAG");
		    laoJianFlag = flagMap.get("LAOJIANFLAG");
		}
		if("1".equals(ydjFlag)&&!"01".equals(laoJianFlag)&&!"02".equals(laoJianFlag)){//易达金的配发卡
			// 套卡保存
			Patchbolt patchbolt = new Patchbolt();
			String uuid2 = getUUID();
			
			if(!"0".equals(flagMap.get("MATCHING_CARD_FLAG"))){
				if("1".equals(appId.substring(15,16))){
					record.setAppId(appId.substring(0,15)+"2");
					patchbolt.setAppId(appId.substring(0,15)+"2");
				}else{
					record.setAppId(appId.substring(0,15)+"1");
					patchbolt.setAppId(appId.substring(0,15)+"1");
				}
				patchbolt.setAutoId(uuid2);
				patchbolt.setDueDate(patchboltYdj.getDueDate());
				patchbolt.setMemo(patchboltYdj.getMemo());
				patchbolt.setSendFlag(patchboltYdj.getSendFlag());
				patchbolt.setCompletedFlag("0");
				insertOrUpdatePatchbolt(patchbolt);
				record.setAutoId(UUID.randomUUID().toString().replace("-", ""));
				telcheckAddnoteDao.insert(record);
			}
		}
	}

	private void insertOrUpdatePatchboltYdj(PatchboltYdj patchboltYdj) throws CoreException, ParseException {
		int pbnum = patchboltDao.queryPatchboltYdjCountByAppId(patchboltYdj.getAppId());
		if (pbnum == 0) {
			patchboltDao.insertPatchboltYdj(patchboltYdj);
		} else {
			patchboltDao.updateByAppIdYdj(patchboltYdj);
		}
	}

	// 易达金卡
	@Override
	public void updateByAppIdYdj(PatchboltYdj patchboltYdj) throws CoreException, ParseException {
		patchboltDao.updateByAppIdYdj(patchboltYdj);
		patchboltDao.deleteByAppId(patchboltYdj.getAppId());
		String uuid = getUUID();
		TelcheckAddnote record = new TelcheckAddnote();
		record.setAppId(patchboltYdj.getAppId());
		record.setAutoId(uuid);
		record.setCrtDate(new Date());
		// bigMemo保存
		StringBuffer bigMemo = new StringBuffer("补件材料:");
		if ("1".equals(patchboltYdj.getIdentityCard())) {
			bigMemo.append("身份证明文件;");
		}
		if ("1".equals(patchboltYdj.getPayWater())) {
			bigMemo.append("工资流水;");
		}
		if ("1".equals(patchboltYdj.getWorkProof())) {
			bigMemo.append("工作证明文件;");
		}
		if ("1".equals(patchboltYdj.getIncomeProof())) {
			bigMemo.append("收入证明文件;");
		}
		if ("1".equals(patchboltYdj.getSocialSecurity())) {
			bigMemo.append("社保;");
		}
		if ("1".equals(patchboltYdj.getHouseProof())) {
			bigMemo.append("房产证明;");
		}
		if ("1".equals(patchboltYdj.getAccumulationFund())) {
			bigMemo.append("公积金;");
		}
		if ("1".equals(patchboltYdj.getEducationProof())) {
			bigMemo.append("学历证明;");
		}
		if ("1".equals(patchboltYdj.getTaxReceipt())) {
			bigMemo.append("税单;");
		}
		if ("1".equals(patchboltYdj.getCreditCardBill())) {
			bigMemo.append("信用卡账单;");
		}
		if ("1".equals(patchboltYdj.getOwnerBankProof())) {
			bigMemo.append("我行业务往来证明;");
		}
		if ("1".equals(patchboltYdj.getFinancalProof())) {
			bigMemo.append("财力证明;");
		}
		if ("1".equals(patchboltYdj.getLoanSettleProof())) {
			bigMemo.append("贷款结清证明;");
		}
		if ("1".equals(patchboltYdj.getRprProof())) {
			bigMemo.append("户口证明;");
		}
		if ("1".equals(patchboltYdj.getMarriageCertificate())) {
			bigMemo.append("结婚证;");
		}
		if ("1".equals(patchboltYdj.getBusinessLicense())) {
			bigMemo.append("企业营业执照;");
		}
		if ("1".equals(patchboltYdj.getCarProof())) {
			bigMemo.append("车产证明;");
		}
		if ("1".equals(patchboltYdj.getRetricalMasterCopy())) {
			bigMemo.append("调阅原件;");
		}
		if ("1".equals(patchboltYdj.getImageRepair())) {
			bigMemo.append("影像修复;");
		}
		if ("1".equals(patchboltYdj.getOther())) {
			bigMemo.append("其他;");
		}
		if (!"".equals(patchboltYdj.getMemo()) && patchboltYdj.getMemo() != null) {
			bigMemo.append(" 备注:").append(patchboltYdj.getMemo());
		}
		String crtUser = patchboltYdj.getCrtUser();
		ApUser apUser = apUserDao.queryApUserByUserCode(crtUser);
		bigMemo.append(" 操作人："+apUser.getUserName()).append("  操作时间:"+ new SimpleDateFormat("yyyy-MM-dd").format(patchboltYdj.getCrtDate()));
		record.setBigMemo(bigMemo.toString());
		record.setTypeFlag("5");
		record.setTalId(UUID.randomUUID().toString().replace("-", ""));
		telcheckAddnoteDao.insert(record);
		
		String ydjFlag = "";
		String laoJianFlag = "";
		Map<String, String> flagMap = patchboltDao.querySomeFlagFromAllot(patchboltYdj.getAppId());
		if(flagMap!=null){
			ydjFlag = flagMap.get("YDJ_FLAG");
		    laoJianFlag = flagMap.get("LAOJIANFLAG");
		}
		if("1".equals(ydjFlag)&&!"01".equals(laoJianFlag)&&!"02".equals(laoJianFlag)){//易达金的配发卡
			Patchbolt patchbolt = new Patchbolt();
			
			if(!"0".equals(flagMap.get("MATCHING_CARD_FLAG"))){
				if("1".equals(patchboltYdj.getAppId().substring(15,16))){
					record.setAppId(patchboltYdj.getAppId().substring(0,15)+"2");
					patchbolt.setAppId(patchboltYdj.getAppId().substring(0,15)+"2");
				}else{
					record.setAppId(patchboltYdj.getAppId().substring(0,15)+"1");
					patchbolt.setAppId(patchboltYdj.getAppId().substring(0,15)+"1");
				}
				patchbolt.setCrtDate(patchboltYdj.getCrtDate());
				patchbolt.setCrtUser(patchboltYdj.getCrtUser());
				patchbolt.setMemo(patchboltYdj.getMemo());
				patchbolt.setDueDate(patchboltYdj.getDueDate());
				patchbolt.setSendFlag(patchboltYdj.getSendFlag());
				patchbolt.setCompletedFlag(patchboltYdj.getCompletedFlag());
				patchboltDao.updateByAppId(patchbolt);
				patchboltDao.deleteByAppId(patchbolt.getAppId());
				record.setAutoId(UUID.randomUUID().toString().replace("-", ""));
				telcheckAddnoteDao.insert(record);
			}
		}
	}

	// shihuan 用于二次补件反显已勾选数据 2017-03-28
	@Override
	public PatchboltYdj selectByPrimaryKeyYdj(String appId) throws CoreException {
		return patchboltDao.selectByPrimaryKeyYdj(appId);
	}
	// ############# 公共部分 #############

	// shihuan 修改进件信息表的状态 由补件队列到未完成队列
	@Override
	public void updateDelStatusByAppId(AllotApplyAllot allotapplyallot) throws CoreException, ParseException {
		patchboltDao.updateDelStatusByAppId(allotapplyallot);
	}

	// shihuan 查询推广人手机号，申请人电话，姓名，证件号
	@Override
	public BizInpApplC1 querybizInpApplList(String appId) throws CoreException {
		return patchboltDao.querybizInpApplList(appId);
	}

	// shihuan 将补件结果、时间、操作人存入大备注 2017-03-29
	@Override
	public void insertBigMemo(TelcheckAddnote telcheckaddnote) throws CoreException {
		patchboltDao.insertBigMemo(telcheckaddnote);
	}

	// shihuan 补件修改申请件分配状态时，在历史表中插入数据 2017-04-03
	@Override
	public void insertAllotApplyHis(AllotApplyAllotHis allotapplyallothis) throws CoreException {
		patchboltDao.insertAllotApplyHis(allotapplyallothis);
	}

	// shihuan 根据申请件appid查询申请件分配表数据 2017-04-03
	@Override
	public AllotApplyAllotHis selectAllotApplyByAppId(String appId) throws CoreException {
		return patchboltDao.selectAllotApplyByAppId(appId);
	}

	private String getUUID() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString().replace("-", "");
	}

	@Override
	public String queryTargetDate(String beforeDate, int dayNum) throws CoreException, ParseException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("beforeDate", beforeDate);
		params.put("dayNum", dayNum);
		Date targetDate = patchboltDao.queryTargetDate(params);
		String targetDateString = TransDateUtil.Date2String(targetDate, "yyyy-MM-dd");
		return targetDateString;
	}

	public Map<String, String> saveOrUpdateFileWaitPatch(Map paramMap) throws CoreException {
		String flag = "";
		Map<String, String> htmlDataMap = new HashMap<String, String>();
		Date date = new Date();
		String ydjFlag = "";
		String laoJianFlag = "";
		Map<String, Object> dataMap = (Map<String, Object>) paramMap.get("dataMap");// ctx.getDataMap();
		String user = (String) dataMap.get("userCode");
		String appId = (String) dataMap.get("appIds");
		try {
			Map<String, String> flagMap = patchboltDao.querySomeFlagFromAllot(appId);
			if(flagMap!=null){
				ydjFlag = flagMap.get("YDJ_FLAG");
			    laoJianFlag = flagMap.get("LAOJIANFLAG");
			}
			
			SuppArchive supparchive = new SuppArchive();// 待归档补件表
			Patchbolt patchbolt = new Patchbolt();// 补件信息表
			TelcheckAddnote telcheckaddnote = new TelcheckAddnote();// 电话征信注记
			AllotApplyAllot allotapplyallot = new AllotApplyAllot();// 进件信息表
			AllotApplyAllotHis allotapplyallothis = new AllotApplyAllotHis();// 申请件分配历史表
			String uuid = paramMap.get("uuidNewOne").toString();// SequenceUtil.getUUID();
			String uuidTwo = paramMap.get("uuidNewTwo").toString();
			// 查询进件信息表获取申请人姓名，证件号
			BizInpApplC1 bizinpapplc1 = patchboltDao.querybizInpApplList(appId);
			//补件操作记录到申请生命周期表
			ApUser apUser = apUserDao.queryApUserByUserCode(user);
			String operator = apUser.getUserName() + "-" +user;
			ApplyLifeCicle applyLifeCicle = new ApplyLifeCicle();
			applyLifeCicle.setUuid(UUID.randomUUID().toString().replace("-", ""));
			applyLifeCicle.setAppId(appId);
			applyLifeCicle.setLstUpdDate(date);
			applyLifeCicle.setLstUpdUser(user);
			applyLifeCicle.setCrtUser(user);
			applyLifeCicle.setOperater(operator);
			applyLifeCicle.setCrtDate(date);
			String patchStatus = (String) dataMap.get("patchStatus");// 补件状态:0-补件未达 1-补件完成 2-PAD补件
																	
			String operateDesc = "";
			switch(patchStatus){
				case "0" :
					operateDesc = "补件未达";
					break;
				case "1" :
					operateDesc = "补件完成";
					break;
				case "2" :
					operateDesc = "PAD补件";
					break;
				case "3" :
					operateDesc = "回收至未完成队列";
					break;
				default:
			}
			applyLifeCicle.setOperateDesc(apUser.getUserName()+"补件结果选择原因:"+operateDesc);
			applyLifeCicle.setOperateResult("补件结果选择原因完成");
			applyLifeCicleDao.addApplyLifeCicle(applyLifeCicle);
			if("1".equals(ydjFlag)&&!"01".equals(laoJianFlag)&&!"02".equals(laoJianFlag)){
				if(!"0".equals(flagMap.get("MATCHING_CARD_FLAG"))){
					if("1".equals(appId.substring(15,16))){
						applyLifeCicle.setAppId((appId.substring(0,15)+"2"));
					}
					applyLifeCicle.setUuid(UUID.randomUUID().toString().replace("-", ""));
					applyLifeCicleDao.addApplyLifeCicle(applyLifeCicle);
				}
			}
			
			String patchCode = (String) dataMap.get("patchCode");// 补件码
			if ("3".equals(patchStatus)) {// 如果补件状态为3时，申请件需进入征信操作员的未完成队列
				allotapplyallot.setDelStatus("0");// 0-未完成队列
				allotapplyallot.setAppId(appId);
				allotapplyallot.setLstTeamDate(date);
				allotapplyallot.setLastOptUser(user);
				patchboltDao.updateDelStatusByAppId(allotapplyallot);
				// 申请件分配历史表保存数据
				allotapplyallothis = patchboltDao.selectAllotApplyByAppId(appId);
				allotapplyallothis.setId(uuid);
				patchboltDao.insertAllotApplyHis(allotapplyallothis);
				if ("1".equals(ydjFlag)&&!"01".equals(laoJianFlag)&&!"02".equals(laoJianFlag)) {//易达金的配发卡
					if(!"0".equals(flagMap.get("MATCHING_CARD_FLAG"))){
						if("1".equals(appId.substring(15,16))){
							allotapplyallot.setAppId(appId.substring(0,15)+"2");
							allotapplyallothis.setAppId(appId.substring(0,15)+"2");
						}else{
							allotapplyallot.setAppId(appId.substring(0,15)+"1");
							allotapplyallothis.setAppId(appId.substring(0,15)+"1");
						}
						patchboltDao.updateDelStatusByAppId(allotapplyallot);
						allotapplyallothis.setId(uuidTwo);
						patchboltDao.insertAllotApplyHis(allotapplyallothis);
					}
				}
				flag = "1";
			} else {
				// 将数据插入到补件归档表
				String credNo = bizinpapplc1.getC1Idnbr();
				String custName = bizinpapplc1.getC1Cname();
				supparchive.setAppId(appId);// 流水号
				supparchive.setCredNo(credNo);// 证件号
				supparchive.setCustName(custName);// 客户姓名
				supparchive.setFlag("0");// 补件选中标志 0-初始值
				supparchive.setOperatTime(new Date());
				supparchive.setPatchCode(patchCode);// 补件码
				supparchive.setPatchStatus(patchStatus);// 补件完成状态
														// 0：补件未达1：补件完成（需补件码）2：PAD补件
				// 将补件码存入补件信息表中
				if (patchCode != null && !"".equals(patchCode)) {
					patchbolt.setPatchCode(patchCode);
					patchboltDao.updatePatchCode(patchbolt);
					if ("1".equals(ydjFlag)&&!"01".equals(laoJianFlag)&&!"02".equals(laoJianFlag)) {
						PatchboltYdj patchboltYdj = new PatchboltYdj();
						if(!"0".equals(flagMap.get("MATCHING_CARD_FLAG"))){
							if("1".equals(appId.substring(15,16))){
								patchboltYdj.setAppId(appId.substring(0,15)+"2");
							}else{
								patchboltYdj.setAppId(appId.substring(0,15)+"1");
							}
						}
						patchboltYdj.setPatchCode(patchCode);
						patchboltDao.updatePatchCodeYdj(patchboltYdj);
					}
				}
				
				int count = suppArDao.queryFilePatchCodeCount(appId);
				if(count>0){
					suppArDao.updateFilePatch(supparchive);
					supparchive.setAutoId(UUID.randomUUID().toString().replace("-", ""));
					suppArDao.insertFilePatchHis(supparchive);
					if("1".equals(ydjFlag)&&!"01".equals(laoJianFlag)&&!"02".equals(laoJianFlag)){
						if(!"0".equals(flagMap.get("MATCHING_CARD_FLAG"))){
							if("1".equals(appId.substring(15,16))){
								supparchive.setAppId(appId.substring(0,15)+"2");
							}else{
								supparchive.setAppId(appId.substring(0,15)+"1");
							}
						}	
						suppArDao.updateFilePatch(supparchive);
						supparchive.setAutoId(UUID.randomUUID().toString().replace("-", ""));
						suppArDao.insertFilePatchHis(supparchive);
					}
				}else{
					suppArDao.insertFilePatch(supparchive);
					supparchive.setAutoId(UUID.randomUUID().toString().replace("-", ""));
					suppArDao.insertFilePatchHis(supparchive);
					if("1".equals(ydjFlag)&&!"01".equals(laoJianFlag)&&!"02".equals(laoJianFlag)){
						if(!"0".equals(flagMap.get("MATCHING_CARD_FLAG"))){
							if("1".equals(appId.substring(15,16))){
								supparchive.setAppId(appId.substring(0,15)+"2");
							}else{
								supparchive.setAppId(appId.substring(0,15)+"1");
							}
						}	
						suppArDao.insertFilePatch(supparchive);
						supparchive.setAutoId(UUID.randomUUID().toString().replace("-", ""));
						suppArDao.insertFilePatchHis(supparchive);
					}
				}
				// 将补件结果、操作人、时间存在征信注记区域
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String date1 = sdf.format(date);
				switch (patchStatus) {
				case "0":
					patchStatus = "补件未达";
					break;
				case "1":
					patchStatus = "补件完成";
					break;
				case "2":
					patchStatus = "PAD补件";
					break;
				}
				String meo = null;
				if (patchCode != null && !"".equals(patchCode)) {
					char[] appIdArr = appId.toCharArray();
					// 套卡的补件结论反显在征信注记区域
					if (appIdArr.length == 16 && appIdArr[15] == '1') {
						meo = "套卡补件结果：" + patchStatus + ",补件码：" + patchCode + ",操作员：" + apUser.getUserName() + ",操作日期：" + date1;
					}
					if (appIdArr.length == 16 && appIdArr[15] == '2') {
						meo = "套卡补件结果：" + patchStatus + ",补件码：" + patchCode + ",操作员：" + apUser.getUserName() + ",操作日期：" + date1;
					}
					meo = "补件结果：" + patchStatus + ",补件码：" + patchCode + ",操作员：" + apUser.getUserName() + ",操作日期：" + date1;
				} else {
					meo = "补件结果：" + patchStatus + ",操作员：" + apUser.getUserName() + ",操作日期：" + date1;
				}
				telcheckaddnote.setAutoId(uuid);
				telcheckaddnote.setAppId(appId);
				telcheckaddnote.setBigMemo(meo);
				telcheckaddnote.setCrtUser(user);
				telcheckaddnote.setTypeFlag("2");
				telcheckaddnote.setTalId(UUID.randomUUID().toString().replace("-", ""));
				patchboltDao.insertBigMemo(telcheckaddnote);
				if("1".equals(ydjFlag)&&!"01".equals(laoJianFlag)&&!"02".equals(laoJianFlag)){
					if(!"0".equals(flagMap.get("MATCHING_CARD_FLAG"))){
						if("1".equals(appId.substring(15,16))){
							telcheckaddnote.setAppId((appId.substring(0,15)+"2"));
						}else{
							telcheckaddnote.setAppId((appId.substring(0,15)+"1"));
						}
					}	
					telcheckaddnote.setAutoId(UUID.randomUUID().toString().replace("-", ""));
					patchboltDao.insertBigMemo(telcheckaddnote);
				}
				flag = "2";
			}
			
		} catch (Exception e) {
			throw new CoreException("二次补件提交异常。");
		}
		htmlDataMap.put("flag", flag);
		return htmlDataMap;
	}

	@Override
	public Map<String, String> saveOrUpdateFileWaitPatchYdj(Map paramMap) {
		String flag = "";
		Map<String, String> htmlDataMap = new HashMap<String, String>();
		Date date = new Date();
		String ydjFlag = "";
		String laoJianFlag = "";
		Map<String, Object> dataMap = (Map<String, Object>) paramMap.get("dataMap");// ctx.getDataMap();
		String user = (String) dataMap.get("userCode");
		String appId = (String) dataMap.get("appIds");
		try {
			Map<String, String> flagMap = patchboltDao.querySomeFlagFromAllot(appId);
			if(flagMap!=null){
				ydjFlag = flagMap.get("YDJ_FLAG");
			    laoJianFlag = flagMap.get("LAOJIANFLAG");
			}
			
			SuppArchive supparchive = new SuppArchive();// 待归档补件表
			PatchboltYdj patchboltYdj = new PatchboltYdj();// 补件信息表
			TelcheckAddnote telcheckaddnote = new TelcheckAddnote();// 电话征信注记
			AllotApplyAllot allotapplyallot = new AllotApplyAllot();// 进件信息表
			AllotApplyAllotHis allotapplyallothis = new AllotApplyAllotHis();// 申请件分配历史表
			String uuid = paramMap.get("uuidNewOne").toString();// SequenceUtil.getUUID();
			String uuidTwo = paramMap.get("uuidNewTwo").toString();
	
			//补件操作记录到申请生命周期表
			ApUser apUser = apUserDao.queryApUserByUserCode(user);
			String operator = apUser.getUserName() + "-" +user;
			ApplyLifeCicle applyLifeCicle = new ApplyLifeCicle();
			applyLifeCicle.setUuid(UUID.randomUUID().toString().replace("-", ""));
			applyLifeCicle.setAppId(appId);
			applyLifeCicle.setLstUpdDate(date);
			applyLifeCicle.setLstUpdUser(user);
			applyLifeCicle.setCrtUser(user);
			applyLifeCicle.setOperater(operator);
			applyLifeCicle.setCrtDate(date);
			String patchStatus = (String) dataMap.get("patchStatus");// 补件状态	// 0-补件未达// 1-补件完成// 2-PAD补件
			String operateDesc = "";
			switch(patchStatus){
				case "0" :
					operateDesc = "补件未达";
					break;
				case "1" :
					operateDesc = "补件完成";
					break;
				case "2" :
					operateDesc = "PAD补件";
					break;
				case "3" :
					operateDesc = "回收至未完成队列";
					break;
				default:
			}
			applyLifeCicle.setOperateDesc(apUser.getUserName()+"二次补件结果选择原因:"+operateDesc);
			String patchCode = (String) dataMap.get("patchCode");// 补件码
			// 查询进件信息表获取申请人姓名，证件号
			BizInpApplC1 bizinpapplc1 = patchboltDao.querybizInpApplList(appId);
			if ("3".equals(patchStatus)) {// 如果补件状态为3时，申请件需进入征信操作员的未完成队列
				allotapplyallot.setDelStatus("0");// 0-未完成队列
				allotapplyallot.setAppId(appId);
				allotapplyallot.setLstTeamDate(new Date());
				allotapplyallot.setLastOptUser(user);
				patchboltDao.updateDelStatusByAppId(allotapplyallot);
				// 申请件分配历史表保存数据
				allotapplyallothis = patchboltDao.selectAllotApplyByAppId(appId);
				allotapplyallothis.setId(uuid);
				patchboltDao.insertAllotApplyHis(allotapplyallothis);
				
				if("1".equals(ydjFlag)&&!"01".equals(laoJianFlag)&&!"02".equals(laoJianFlag)){
					if(!"0".equals(flagMap.get("MATCHING_CARD_FLAG"))){
						if("1".equals(appId.substring(15,16))){
							allotapplyallot.setAppId(appId.substring(0,15)+"2");
							allotapplyallothis.setAppId(appId.substring(0,15)+"2");
						}else{
							allotapplyallot.setAppId(appId.substring(0,15)+"1");
							allotapplyallothis.setAppId(appId.substring(0,15)+"1");
						}
					}
					patchboltDao.updateDelStatusByAppId(allotapplyallot);
					allotapplyallothis.setId(uuidTwo);
					patchboltDao.insertAllotApplyHis(allotapplyallothis);
				}
				flag = "1";
			} else {
				supparchive.setAppId(appId);// 流水号
				supparchive.setCredNo(bizinpapplc1.getC1Idnbr() == null ? null : bizinpapplc1.getC1Idnbr());// 证件号
				supparchive.setCustName(bizinpapplc1.getC1Cname() == null ? null : bizinpapplc1.getC1Cname());// 客户姓名
				supparchive.setFlag("0");// 补件选中标志 0-初始值
				supparchive.setOperatTime(new Date());
				supparchive.setPatchCode(patchCode);// 补件码
				supparchive.setPatchStatus(patchStatus);// 补件完成状态
														// 0：补件未达1：补件完成（需补件码）2：PAD补件
				// 将补件码存入补件信息表中
				if (patchCode != null && !"".equals(patchCode)) {
					patchboltYdj.setPatchCode(patchCode);
					patchboltYdj.setAppId(appId);
					Patchbolt patchbolt = new Patchbolt();
					patchboltDao.updatePatchCodeYdj(patchboltYdj);

					if("1".equals(ydjFlag)&&!"01".equals(laoJianFlag)&&!"02".equals(laoJianFlag)){
						if(!"0".equals(flagMap.get("MATCHING_CARD_FLAG"))){
							if("1".equals(appId.substring(15,16))){
								patchbolt.setAppId(appId.substring(0,15)+"2");
							}else{
								patchbolt.setAppId(appId.substring(0,15)+"1");
							}
						}	
						patchbolt.setPatchCode(patchCode);
						patchboltDao.updatePatchCode(patchbolt);
					}
				}
				int count = suppArDao.queryFilePatchCodeCount(appId);
			
				if(count>0){
					suppArDao.updateFilePatch(supparchive);
					supparchive.setAutoId(UUID.randomUUID().toString().replace("-", ""));
					suppArDao.insertFilePatchHis(supparchive);
					String appId2 = appId;
					if("1".equals(ydjFlag)&&!"01".equals(laoJianFlag)&&!"02".equals(laoJianFlag)){
						if(!"0".equals(flagMap.get("MATCHING_CARD_FLAG"))){
							if("1".equals(appId.substring(15,16))){
								 appId2 = appId.substring(0,15)+"2";
							}else{
								 appId2 = appId.substring(0,15)+"1";
							}
						}
						supparchive.setAppId(appId2);
						int count2 = suppArDao.queryFilePatchCodeCount(appId2);
						if(count2>0){
							suppArDao.updateFilePatch(supparchive);
						}else{
							suppArDao.insertFilePatch(supparchive);
						}
						supparchive.setAutoId(UUID.randomUUID().toString().replace("-", ""));
						suppArDao.insertFilePatchHis(supparchive);
					}
				}else{
					suppArDao.insertFilePatch(supparchive);
					supparchive.setAutoId(UUID.randomUUID().toString().replace("-", ""));
					suppArDao.insertFilePatchHis(supparchive);
					String appId2 = appId;
					if("1".equals(ydjFlag)&&!"01".equals(laoJianFlag)&&!"02".equals(laoJianFlag)){
						if(!"0".equals(flagMap.get("MATCHING_CARD_FLAG"))){
							if("1".equals(appId.substring(15,16))){
								appId2 = appId.substring(0,15)+"2";
							}else{
								appId2 = appId.substring(0,15)+"1";
							}
						}
						supparchive.setAppId(appId2);
						int count2 = suppArDao.queryFilePatchCodeCount(appId2);
						if(count2>0){
							suppArDao.updateFilePatch(supparchive);
						}else{
							suppArDao.insertFilePatch(supparchive);
						}
						supparchive.setAutoId(UUID.randomUUID().toString().replace("-", ""));
						suppArDao.insertFilePatchHis(supparchive);
					}
				}
				// 将补件结果、操作人、时间存在征信注记区域
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String date1 = sdf.format(date);
				switch (patchStatus) {
				case "0":
					patchStatus = "补件未达";
					break;
				case "1":
					patchStatus = "补件完成";
					break;
				case "2":
					patchStatus = "PAD补件";
					break;
				}
				String meo = null;
				if (patchCode != null && !"".equals(patchCode)) {
					// 套卡的补件结论反显在征信注记区域
					char[] appIdArr = appId.toCharArray();
					if (appIdArr.length == 16 && appIdArr[15] == '1') {
						appIdArr[15] = '2';
						meo = "套卡补件结果：" + patchStatus + ",补件码：" + patchCode + ",操作员：" + apUser.getUserName() + ",操作日期：" + date1;
					}
					if (appIdArr.length == 16 && appIdArr[15] == '2') {
						appIdArr[15] = '1';
						meo = "套卡补件结果：" + patchStatus + ",补件码：" + patchCode + ",操作员：" + apUser.getUserName() + ",操作日期：" + date1;
					}
					meo = "补件结果：" + patchStatus + ",补件码：" + patchCode + ",操作员：" + apUser.getUserName() + ",操作日期：" + date1;
				} else {
					meo = "补件结果：" + patchStatus + ",操作员：" + apUser.getUserName() + ",操作日期：" + date1;
				}
				telcheckaddnote.setAutoId(uuid);
				telcheckaddnote.setAppId(appId);
				telcheckaddnote.setBigMemo(meo);
				telcheckaddnote.setCrtUser(user);
				telcheckaddnote.setTypeFlag("2");
				telcheckaddnote.setTalId(UUID.randomUUID().toString().replace("-", ""));
				patchboltDao.insertBigMemo(telcheckaddnote);
				if("1".equals(ydjFlag)&&!"01".equals(laoJianFlag)&&!"02".equals(laoJianFlag)){
					if(!"0".equals(flagMap.get("MATCHING_CARD_FLAG"))){
						if("1".equals(appId.substring(15,16))){
							telcheckaddnote.setAppId(appId.substring(0,15)+"2");
						}else{
							telcheckaddnote.setAppId(appId.substring(0,15)+"1");
						}
					}
					telcheckaddnote.setAutoId(UUID.randomUUID().toString().replace("-", ""));
					patchboltDao.insertBigMemo(telcheckaddnote);
				}
				flag = "2";
			}
			applyLifeCicle.setOperateResult("二次补件结果选择原因完成");
			
			applyLifeCicleDao.addApplyLifeCicle(applyLifeCicle);
			if("1".equals(ydjFlag)&&!"01".equals(laoJianFlag)&&!"02".equals(laoJianFlag)){
				if(!"0".equals(flagMap.get("MATCHING_CARD_FLAG"))){
					if("1".equals(appId.substring(15,16))){
						applyLifeCicle.setAppId((appId.substring(0,15)+"2"));
					}else{
						applyLifeCicle.setAppId((appId.substring(0,15)+"1"));
					}
				}
				applyLifeCicle.setUuid(UUID.randomUUID().toString().replace("-", ""));
				applyLifeCicleDao.addApplyLifeCicle(applyLifeCicle);
			}
		} catch (Exception e) {
			throw new RuntimeException("二次补件提交异常。");
		}
		htmlDataMap.put("flag", flag);
		return htmlDataMap;
	}

	@Override
	public String apsInvoking(String method, String urlStr, Map<String, Object> paramMap) {
		try {
			String json = JSON.toJSONString(paramMap);
			StringBuffer buff = null;
			// 创建制定链接的url对象
			URL url = new URL(urlStr);
			// 建立到url对象之间的链接
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			// 设置参数
			// 设置连接方式
			conn.setRequestMethod(method);
			// 用URL连接进行输出，则将dooutput标志设置为true
			conn.setDoOutput(true);
			// 用URL连接进行写入
			conn.setDoInput(true);
			// 不允许缓存
			conn.setUseCaches(false);
			// 设置请求的数据内容不被存储
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			// 设置该httpurlConnection实例支持自动执行重定向
			conn.setInstanceFollowRedirects(true);
			// 设置请求的字符集编码格式
			conn.setRequestProperty("Charset", "GBK");
			conn.setConnectTimeout(40000);
			conn.setReadTimeout(40000);
			// 建立连接
			conn.connect();
			// 构造向指定链接写入数据的输出流
			DataOutputStream out = new DataOutputStream(conn.getOutputStream());
			String param = "jsonString=" + URLEncoder.encode(json);
			out.writeBytes(param);
			out.flush();
			out.close();
			// 将pad端返回的数据读取到内存
			int resultCode = conn.getResponseCode();
			if (HttpURLConnection.HTTP_OK == resultCode) {
				InputStream in = conn.getInputStream();
				BufferedReader br = new BufferedReader(new InputStreamReader(in));
				buff = new StringBuffer();
				String resultLine = br.readLine();
				while (resultLine != null) {
					buff.append(URLDecoder.decode(resultLine, "GBK"));
					resultLine = br.readLine();
				}
				br.close();
				if (logger.isInfoEnabled()) {
					logger.info("此条显示调用pad接口的情况：{}","调用pad接口成功");
				}
				conn.disconnect();// 销毁连接
			}
			if (logger.isInfoEnabled()) {
				logger.info("查询pad参数：{}",conn.getResponseMessage());
			} 
			if (buff != null) {
				return buff.toString();
			} else {
				return null;
			}
		} catch (Exception e) {
			logger.error("pad**************：{}","调用pad接口失败");
			return null;
		}
	}

	@Override
	public int queryCountById(String autoId) {
		return patchboltDao.queryCountById(autoId);
	}

	@Override
	public void updateByAutoId(Map<String,String> paramMap) {
		patchboltDao.updateByAutoId(paramMap);
	}

	@Override
	public void updateYdjByAutoId(Map<String,String> paramMap) {
		patchboltDao.updateYdjByAutoId(paramMap);
	}

	@Override
	public SuppArchive selectByAppId(String appId) {
		return patchboltDao.selectByAppId(appId);
	}

	@Override
	public int queryCountByPatchCode(Map<String, Object> dataMap) {
		return suppArDao.queryCountByPatchCode(dataMap);
	}

	@Override
	public String selectAppIdByAutoId(Map<String, String> paramMap) {
		return patchboltDao.selectAppIdByAutoId(paramMap);
	}

	@Override
	public int updateBzkByAppId(Map<String, String> paramMap) {
		return patchboltDao.updateBzkByAppId(paramMap);
	}

	@Override
	public Map<String, String> querySomeFlagFromAllot(String appId) {
		return patchboltDao.querySomeFlagFromAllot(appId);
	}

	@Override
	public int queryYsCountById(String autoId) {
		return patchboltDao.queryYsCountById(autoId);
	}

	@Override
	public void updateYsByAutoId(Map<String, String> paramMap) {
		patchboltDao.updateYsByAutoId(paramMap);
	}
}
