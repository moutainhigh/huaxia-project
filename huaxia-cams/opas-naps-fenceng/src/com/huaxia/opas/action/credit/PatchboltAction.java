package com.huaxia.opas.action.credit;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.google.gson.Gson;
import com.huateng.neofp.core.Action;
import com.huateng.neofp.core.Context;
import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.allot.AllotApplyAllot;
import com.huaxia.opas.domain.allot.AllotApplyAllotHis;
import com.huaxia.opas.domain.apply.ApplyLifeCicle;
import com.huaxia.opas.domain.archive.SuppArchive;
import com.huaxia.opas.domain.input.BizInpApplC1;
import com.huaxia.opas.domain.sysparm.Patchbolt;
import com.huaxia.opas.domain.sysparm.PatchboltYdj;
import com.huaxia.opas.domain.system.ApUser;
import com.huaxia.opas.service.apply.ApplyLifeCicleService;
import com.huaxia.opas.service.archive.SuppArService;
import com.huaxia.opas.service.credit.PatchboltService;
import com.huaxia.opas.service.system.ApUserService;
import com.huaxia.opas.util.PadProperties;
import com.huaxia.opas.util.SequenceUtil;
import com.huaxia.opas.util.TransDateUtil;

public class PatchboltAction implements Action{

	private static Logger logger = LoggerFactory.getLogger(PatchboltAction.class);

	@Resource(name = "patchboltService")
	private PatchboltService patchboltService;
	
	@Resource(name = "suppArService")
	private SuppArService suppArService;
	
	@Resource(name = "apUserService")
	private ApUserService apUserService;
	
	@Resource(name = "applyLifeCicleService")
	ApplyLifeCicleService applyLifeCicleService;

	public void querybizInfo(Context ctx) throws CoreException {
		Map<String, Object> dataMap = ctx.getDataMap();
		BizInpApplC1 bizInfo = patchboltService.querybizInpApplList((String)dataMap.get("appId"));
		ctx.setData("result",bizInfo);
	}
	
	public void queryTargetDate(Context ctx) throws CoreException, ParseException {
		Map<String, Object> dataMap = ctx.getDataMap();
		String beforeDate = ((String) dataMap.get("beforeDate")).substring(0, 10);
		int dayNum = (int) dataMap.get("dayNum");
		String afterDate = patchboltService.queryTargetDate(beforeDate,dayNum);
		ctx.setData("result",afterDate);
	}
	
	/**
	 * 标准卡/易达金的配发卡一次补件
	 * @param ctx
	 * @throws Exception
	 */
	public void insertPatchbolt(Context ctx) throws Exception {
		Gson gson = new Gson();
		Map<String, Object> dataMap = ctx.getDataMap();
		String user = (String) dataMap.get("userCode");
		String appId = ctx.getData("appId");
		Date date = new Date();
		String ydjFlag ="";
		String laoJianFlag = "";
		try {
			Map<String,String> flagMap = patchboltService.querySomeFlagFromAllot(appId);//判断是标卡还是配发卡还是是否捞件 
			if(flagMap!=null){
				ydjFlag = flagMap.get("YDJ_FLAG");
			    laoJianFlag = flagMap.get("LAOJIANFLAG");
			}
			//补件操作记录到申请生命周期表
			ApUser apUser = apUserService.queryApUserByUserCode(user);
			String operator = apUser.getUserName() + "-" +user;
			ApplyLifeCicle applyLifeCicle = new ApplyLifeCicle();
			applyLifeCicle.setUuid(SequenceUtil.getUUID());
			applyLifeCicle.setAppId(appId);
			applyLifeCicle.setLstUpdDate(date);
			applyLifeCicle.setLstUpdUser(user);
			applyLifeCicle.setOperateDesc(apUser.getUserName()+"一次提交补件");
			applyLifeCicle.setCrtUser(user);
			applyLifeCicle.setOperater(operator);
			applyLifeCicle.setCrtDate(date);
			applyLifeCicle.setOperateResult("一次补件提交成功");
			applyLifeCicleService.addApplyLifeCicle(applyLifeCicle);
		
			if(dataMap.get("dueDate")!=null&&!"".equals((String)dataMap.get("dueDate"))){
				dataMap.put("dueDate", TransDateUtil.String2Date((String) dataMap.get("dueDate"), "yyyy-MM-dd"));
			}else{
				dataMap.remove("dueDate");
			}
			if(dataMap.get("applyerCheckbox")==null){
				dataMap.put("applyer","");
			}
			if(dataMap.get("promoterCheckbox")==null){
				dataMap.put("promoter","");
			}
			if(dataMap.get("riskCheckbox")==null){
				dataMap.put("risk","");
			}
			Patchbolt patchbolt = gson.fromJson(gson.toJson(dataMap), Patchbolt.class);
			patchbolt.setAutoId(SequenceUtil.getUUID());
			patchbolt.setCrtDate(date);
			patchbolt.setCrtUser(user);
			patchbolt.setSendFlag("0");
			patchbolt.setCompletedFlag("0");
			//调用pad接口
			patchbolt = getCommunicationWithPad(patchbolt);
			
			patchboltService.insertPatchbolt(patchbolt);
			AllotApplyAllotHis allotApplyAllot = patchboltService.selectAllotApplyByAppId(appId);
			if(!"4".equals(allotApplyAllot.getCurrStatus())){
				AllotApplyAllot allotapplyallot = new AllotApplyAllot();
				allotapplyallot.setAppId(appId);
				allotapplyallot.setDelStatus("2");
				allotapplyallot.setLstTeamDate(date);
				allotapplyallot.setLastOptUser(user);
				patchboltService.updateDelStatusByAppId(allotapplyallot);
				//判断是标卡还是易达金的配发卡
				if(!"01".equals(laoJianFlag)&&!"02".equals(laoJianFlag)){//易达金的配发卡
					String matchingCardFlag = flagMap.get("MATCHING_CARD_FLAG");
					if(!"0".equals(matchingCardFlag)){
						if("1".equals(appId.substring(15,16))){
							allotapplyallot.setAppId(appId.substring(0,15)+"2");
							applyLifeCicle.setAppId(appId.substring(0,15)+"2");
						}else{
							allotapplyallot.setAppId(appId.substring(0,15)+"1");
							applyLifeCicle.setAppId(appId.substring(0,15)+"1");
						}
						patchboltService.updateDelStatusByAppId(allotapplyallot);
						
						applyLifeCicle.setUuid(SequenceUtil.getUUID());
						applyLifeCicleService.addApplyLifeCicle(applyLifeCicle);
						
					}
				}			
			}
			ctx.setData("isSuccess", true);
		} catch (CoreException e) {
			ctx.setData("exMsg", e.getMessage());
			ctx.setData("isSuccess", false);
		} catch (ParseException e2) {
			logger.error("日期转换错误");
			e2.printStackTrace();
			ctx.setData("isSuccess", false);
		}
	}
	/**
	 * 易达金的一次补件
	 * @param ctx
	 * @throws Exception
	 */
	public void insertPatchboltYdj(Context ctx) throws Exception {
		Gson gson = new Gson();
		Map<String, Object> dataMap = ctx.getDataMap();
		String user = (String) dataMap.get("userCode");
		String appId = ctx.getData("appId");
		Date date = new Date();
		String ydjFlag = "";
		String laoJianFlag = "";
		try {
			Map<String,String> flagMap = patchboltService.querySomeFlagFromAllot(appId);//判断是标卡还是配发卡还是是否捞件 
			if(flagMap!=null){
				ydjFlag = flagMap.get("YDJ_FLAG");
			    laoJianFlag = flagMap.get("LAOJIANFLAG");
			}
			//补件操作记录到申请生命周期表
			ApUser apUser = apUserService.queryApUserByUserCode(user);
			String operator = apUser.getUserName() + "-" +user;
			ApplyLifeCicle applyLifeCicle = new ApplyLifeCicle();
			applyLifeCicle.setUuid(SequenceUtil.getUUID());
			applyLifeCicle.setAppId(appId);
			applyLifeCicle.setLstUpdDate(new Date());
			applyLifeCicle.setLstUpdUser(user);
			applyLifeCicle.setOperateDesc(apUser.getUserName()+"一次提交补件");
			applyLifeCicle.setCrtUser(user);
			applyLifeCicle.setOperater(operator);
			applyLifeCicle.setCrtDate(new Date());
			applyLifeCicle.setOperateResult("一次补件提交成功");
			applyLifeCicleService.addApplyLifeCicle(applyLifeCicle);
			
			if(dataMap.get("dueDate")!=null&&"".equals((String)dataMap.get("dueDate"))==false){
				dataMap.put("dueDate", TransDateUtil.String2Date((String) dataMap.get("dueDate"), "yyyy-MM-dd"));
			}else{
				dataMap.remove("dueDate");
			}
			if(dataMap.get("applyerCheckbox")==null){
				dataMap.put("applyer","");
			}
			if(dataMap.get("promoterCheckbox")==null){
				dataMap.put("promoter","");
			}
			if(dataMap.get("riskCheckbox")==null){
				dataMap.put("risk","");
			}
			PatchboltYdj patchboltYdj = gson.fromJson(gson.toJson(dataMap), PatchboltYdj.class);
			patchboltYdj.setCrtDate(date);
			patchboltYdj.setAutoId(SequenceUtil.getUUID());
			patchboltYdj.setCrtUser(user);
			patchboltYdj.setCompletedFlag("0");
			patchboltYdj.setSendFlag("0");
			//调用pad接口
			patchboltYdj = getCommunicationWithPad(patchboltYdj);
			patchboltService.insertPatchboltYdj(patchboltYdj);
			
			AllotApplyAllotHis allotApplyAllot = patchboltService.selectAllotApplyByAppId(appId);
			if(!"4".equals(allotApplyAllot.getCurrStatus())){
				AllotApplyAllot allotapplyallot = new AllotApplyAllot();
				allotapplyallot.setAppId(appId);
				allotapplyallot.setDelStatus("2");
				allotapplyallot.setLstTeamDate(new Date());
				allotapplyallot.setLastOptUser(user);
				patchboltService.updateDelStatusByAppId(allotapplyallot);
				if("1".equals(ydjFlag)&&!"01".equals(laoJianFlag)&&!"02".equals(laoJianFlag)){
					if(!"0".equals(flagMap.get("MATCHING_CARD_FLAG"))){
						if("1".equals(appId.substring(15,16))){
							allotapplyallot.setAppId(appId.substring(0,15)+"2");
						}else{
							allotapplyallot.setAppId(appId.substring(0,15)+"1");
						}
						patchboltService.updateDelStatusByAppId(allotapplyallot);
					}
				}
			}
			if(!"01".equals(laoJianFlag)&&!"02".equals(laoJianFlag)){
				applyLifeCicle.setUuid(SequenceUtil.getUUID());
				if(!"0".equals(flagMap.get("MATCHING_CARD_FLAG"))){
					if("1".equals(appId.substring(15,16))){
						applyLifeCicle.setAppId(appId.substring(0,15)+"2");
					}else{
						applyLifeCicle.setAppId(appId.substring(0,15)+"1");
					}
					applyLifeCicleService.addApplyLifeCicle(applyLifeCicle);
				}
			}
			ctx.setData("isSuccess", true);
		} catch (CoreException e) {
			ctx.setData("exMsg", e.getMessage());
			ctx.setData("isSuccess", false);
		} catch (ParseException e2) {
			logger.error("日期转换错误");
			e2.printStackTrace();
			ctx.setData("isSuccess", false);
		}
	}
	
	
	/**
	 * 二次补件  标准卡/易达金的配发卡
	 * @param ctx
	 * @throws Exception 
	 */
	public void insertPatchboltTwo(Context ctx) throws Exception {
		Gson gson = new Gson();
		Map<String, Object> dataMap = ctx.getDataMap();
		String user = (String)dataMap.get("userCode");
		String appId = (String)dataMap.get("appId");
		String ydjFlag = "";
		String laoJianFlag = "";
		Date date = new Date();
		try {
			Map<String,String> flagMap = patchboltService.querySomeFlagFromAllot(appId);//判断是标卡还是配发卡还是是否捞件 
			if(flagMap!=null){
				ydjFlag = flagMap.get("YDJ_FLAG");
			    laoJianFlag = flagMap.get("LAOJIANFLAG");
			}
			//补件操作记录到申请生命周期表
			ApUser apUser = apUserService.queryApUserByUserCode(user);
			String operator = apUser.getUserName() + "-" +user;
			ApplyLifeCicle applyLifeCicle = new ApplyLifeCicle();
			applyLifeCicle.setUuid(SequenceUtil.getUUID());
			applyLifeCicle.setAppId(appId);
			applyLifeCicle.setLstUpdDate(new Date());
			applyLifeCicle.setLstUpdUser(user);
			applyLifeCicle.setOperateDesc(apUser.getUserName()+"二次提交补件");
			applyLifeCicle.setCrtUser(user);
			applyLifeCicle.setOperater(operator);
			applyLifeCicle.setCrtDate(new Date());
			applyLifeCicle.setOperateResult("二次补件提交成功");
			applyLifeCicleService.addApplyLifeCicle(applyLifeCicle);
			
			if(dataMap.get("dueDate")!=null&&!"".equals((String)dataMap.get("dueDate"))){
				dataMap.put("dueDate", TransDateUtil.String2Date((String) dataMap.get("dueDate"), "yyyy-MM-dd"));
			}else{
				dataMap.remove("dueDate");
			}
			if(dataMap.get("applyerCheckbox")==null){
				dataMap.put("applyer","");
			}
			if(dataMap.get("promoterCheckbox")==null){
				dataMap.put("promoter","");
			}
			if(dataMap.get("riskCheckbox")==null){
				dataMap.put("risk","");
			}
			Patchbolt patchbolt = gson.fromJson(gson.toJson(dataMap), Patchbolt.class);
			patchbolt.setCrtDate(date);
			patchbolt.setCrtUser(user);
			patchbolt.setSendFlag("0");
			patchbolt.setCompletedFlag("0");
		
			Patchbolt selectByPrimaryKey = patchboltService.selectByPrimaryKey(appId);
			patchbolt.setAutoId(selectByPrimaryKey.getAutoId());
			//调用pad接口
			patchbolt = getCommunicationWithPad(patchbolt);
			
			patchboltService.updateByAppId(patchbolt);
			if(!"01".equals(laoJianFlag)&&!"02".equals(laoJianFlag)){//易达金的配发卡
				if(!"0".equals(flagMap.get("MATCHING_CARD_FLAG"))){
					if("1".equals(appId.substring(15,16))){
						applyLifeCicle.setAppId(appId.substring(0,15)+"2");	
					}else{
						applyLifeCicle.setAppId(appId.substring(0,15)+"1");	
					}
					applyLifeCicle.setUuid(SequenceUtil.getUUID());
					applyLifeCicleService.addApplyLifeCicle(applyLifeCicle);
				}
			}
			ctx.setData("isSuccess", true);
		} catch (CoreException e) {
			ctx.setData("exMsg", e.getMessage());
			ctx.setData("isSuccess", false);
		}
	}
	
	/**
	 * 标准卡：对补件结果下结论 shihuan 2017-03-27
	 * @param ctx
	 * @throws CoreException
	 * @throws ParseException 
	 */
	public void insertFilePatch(Context ctx) throws CoreException {
		String uuidNewOne = SequenceUtil.getUUID();
		String uuidNewTwo = SequenceUtil.getUUID();
		Map<String, Object> paramMap=new HashMap<String,Object>();
		Map<String, Object> dataMap = ctx.getDataMap();
		paramMap.put("uuidNewOne", uuidNewOne);
		paramMap.put("uuidNewTwo", uuidNewTwo);
		paramMap.put("dataMap", dataMap);
		Map<String, String> htmlMap=new HashMap<String, String>();
		try {
			int count = patchboltService.queryCountByPatchCode(dataMap);
			if(count>0){
				throw new Exception("补件码重复");
			}
			htmlMap = patchboltService.saveOrUpdateFileWaitPatch(paramMap);
			ctx.setData("flag", htmlMap.get("flag"));
		} catch (Exception e) {
			ctx.setData("isSuccess", false);
			ctx.setData("exMsg", "补件码重复");
			e.printStackTrace();
		}
	}
	
	/**
	 * 用于二次补件反显已勾选数据 shihuan 2017-03-28
	 * @param ctx
	 * @throws CoreException
	 */
	public void queryPatchbolt(Context ctx) throws CoreException {
		Map<String, Object> dataMap = ctx.getDataMap();
		String appId = (String)dataMap.get("appId");
		Patchbolt patchbolt = patchboltService.selectByPrimaryKey(appId);
		SuppArchive suppArchive = patchboltService.selectByAppId(appId);
		BizInpApplC1 bizInfo = patchboltService.querybizInpApplList(appId);
		String jsonpatchbolt=JSON.toJSONStringWithDateFormat(patchbolt, "yyyy-MM-dd", SerializerFeature.WriteDateUseDateFormat);
		String suppArchiveJson = JSON.toJSONString(suppArchive);
		ctx.setData("jsonpatchbolt", jsonpatchbolt);
		ctx.setData("bizInfo", bizInfo);
		ctx.setData("suppArchiveJson", suppArchiveJson);
	}
	
	/**
	 * 易达金：二次补件
	 * @param ctx
	 * @throws Exception 
	 */
	public void insertPatchboltTwoYdj(Context ctx) throws Exception {
		Gson gson = new Gson();
		Map<String, Object> dataMap = ctx.getDataMap();
		String appId = (String)dataMap.get("appId");
		String user = (String)dataMap.get("userCode");
		Date date = new Date();
		String ydjFlag = "";
		String laoJianFlag = "";
		try {
			Map<String,String> flagMap = patchboltService.querySomeFlagFromAllot(appId);//判断是标卡还是配发卡还是是否捞件 
			if(flagMap!=null){
				ydjFlag = flagMap.get("YDJ_FLAG");
			    laoJianFlag = flagMap.get("LAOJIANFLAG");
			}
			//补件操作记录到申请生命周期表
			ApUser apUser = apUserService.queryApUserByUserCode(user);
			String operator = apUser.getUserName() + "-" +user;
			ApplyLifeCicle applyLifeCicle = new ApplyLifeCicle();
			applyLifeCicle.setUuid(SequenceUtil.getUUID());
			applyLifeCicle.setAppId(appId);
			applyLifeCicle.setLstUpdDate(new Date());
			applyLifeCicle.setLstUpdUser(user);
			applyLifeCicle.setOperateDesc(apUser.getUserName()+"二次提交补件");
			applyLifeCicle.setCrtUser(user);
			applyLifeCicle.setOperater(operator);
			applyLifeCicle.setCrtDate(date);
			applyLifeCicle.setOperateResult("二次补件提交成功");
			applyLifeCicleService.addApplyLifeCicle(applyLifeCicle);
			
			if(dataMap.get("dueDate")!=null&&!"".equals((String)dataMap.get("dueDate"))){
				dataMap.put("dueDate", TransDateUtil.String2Date((String) dataMap.get("dueDate"), "yyyy-MM-dd"));
			}else{
				dataMap.remove("dueDate");
			}
			if(dataMap.get("applyerCheckbox")==null){
				dataMap.put("applyer","");
			}
			if(dataMap.get("promoterCheckbox")==null){
				dataMap.put("promoter","");
			}
			if(dataMap.get("riskCheckbox")==null){
				dataMap.put("risk","");
			}
			PatchboltYdj patchboltYdj = gson.fromJson(gson.toJson(dataMap), PatchboltYdj.class);
			patchboltYdj.setCrtDate(date);
			patchboltYdj.setCrtUser(user);
			patchboltYdj.setAppId(appId);
			patchboltYdj.setSendFlag("0");
			patchboltYdj.setCompletedFlag("0");
			PatchboltYdj selectByPrimaryKeyYdj = patchboltService.selectByPrimaryKeyYdj(appId);
			patchboltYdj.setAutoId(selectByPrimaryKeyYdj.getAutoId());
			patchboltYdj = getCommunicationWithPad(patchboltYdj);
			patchboltService.updateByAppIdYdj(patchboltYdj);
			if(!"01".equals(laoJianFlag)&&!"02".equals(laoJianFlag)){
				if(!"0".equals(flagMap.get("MATCHING_CARD_FLAG"))){
					if("1".equals(appId.substring(15,16))){
						applyLifeCicle.setAppId(appId.substring(0,15)+"2");
					}else{
						applyLifeCicle.setAppId(appId.substring(0,15)+"1");
					}
					applyLifeCicle.setUuid(SequenceUtil.getUUID());
					applyLifeCicleService.addApplyLifeCicle(applyLifeCicle);
				}
			}
			ctx.setData("isSuccess", true);
		} catch (CoreException e) {
			ctx.setData("exMsg", e.getMessage());
			ctx.setData("isSuccess", false);
		}
	}
	
	/**
	 * 易达金：对补件结果下结论 
	 * @param ctx
	 * @throws CoreException
	 * @throws ParseException 
	 */
	public void insertFilePatchYdj(Context ctx) throws CoreException {
		String uuidNewOne = SequenceUtil.getUUID();
		String uuidNewTwo = SequenceUtil.getUUID();
		Map<String, Object> paramMap=new HashMap<String,Object>();
		Map<String, Object> dataMap = ctx.getDataMap();
		paramMap.put("uuidNewOne", uuidNewOne);
		paramMap.put("uuidNewTwo", uuidNewTwo);
		paramMap.put("dataMap", dataMap);
		Map<String, String> htmlMap=new HashMap<String, String>();
		try {
			int count = patchboltService.queryCountByPatchCode(dataMap);
			if(count>0){
				throw new Exception("补件码重复");
			}
		    htmlMap = patchboltService.saveOrUpdateFileWaitPatchYdj(paramMap);
			ctx.setData("flag", htmlMap.get("flag"));
		} catch (Exception e) {
			ctx.setData("exMsg", "补件码重复");
			ctx.setData("isSuccess", false);
			e.printStackTrace();
		}
		
	}
	
	/**
	 *  易达金：用于二次补件反显已勾选数据 shihuan 2017-03-28
	 * @param ctx
	 * @throws CoreException
	 */
	public void queryPatchboltYdj(Context ctx) throws CoreException {
		String appId = (String)ctx.getDataMap().get("appId");
		PatchboltYdj patchboltYdj = patchboltService.selectByPrimaryKeyYdj(appId);
		SuppArchive suppArchive = patchboltService.selectByAppId(appId);
		BizInpApplC1 bizInfo = patchboltService.querybizInpApplList(appId);
		String jsonpatchbolt=JSON.toJSONStringWithDateFormat(patchboltYdj, "yyyy-MM-dd", SerializerFeature.WriteDateUseDateFormat);
		String suppArchiveJson = JSON.toJSONString(suppArchive);
		ctx.setData("jsonpatchbolt", jsonpatchbolt);
		ctx.setData("bizInfo", bizInfo);
		ctx.setData("suppArchiveJson", suppArchiveJson);
	}
	
	/**
	 * wangtao  标准卡提交补件时传到pad端的补件描述
	 * @param patchbolt
	 * @return
	 */
	public StringBuffer getAddDesc(Patchbolt patchbolt){
		StringBuffer addDesc = new StringBuffer("补件描述：");
		String dueDate = new SimpleDateFormat("yyyy-MM-dd").format(patchbolt.getDueDate());
		if(dueDate!=null&&!"".equals(dueDate)){
			addDesc.append("补件期限("+dueDate+")");
		}
		addDesc.append("补件材料：");
		if("1".equals(patchbolt.getIdentityCard())){
			addDesc.append("身份证明文件;");
		}
		if("1".equals(patchbolt.getIsHavesign())){
			addDesc.append("是否有签名;");
		}	
		if("1".equals(patchbolt.getWorkConfirm())){
			addDesc.append("工作证明文件;");
		}
		if("1".equals(patchbolt.getIncomeConfirm())){
			addDesc.append("收入证明文件;");
		}
		if("1".equals(patchbolt.getLiveConfirm())){
			addDesc.append("居住证明文件;");
		}
		if("1".equals(patchbolt.getHouseConfirm())){
			addDesc.append("房产证明;");
		}
		if("1".equals(patchbolt.getDrivingLicense())){
			addDesc.append("行驶证;");
		}
		if("1".equals(patchbolt.getEduConfirm())){
			addDesc.append("学历证明;");
		}
		if("1".equals(patchbolt.getTitleConfirm())){
			addDesc.append("资格证书;");
		}
		if("1".equals(patchbolt.getOtherCreditcard())){
			addDesc.append("他行信用卡或账单;");
		}
		if("1".equals(patchbolt.getOwnerBankConfirm())){
			addDesc.append("我行业务往来证明;");
		}
		if("1".equals(patchbolt.getFinancalConfirm())){
			addDesc.append("财力证明;");
		}
		if("1".equals(patchbolt.getOthers())){
			addDesc.append("其他;");
		}
		if("1".equals(patchbolt.getNonAddfiles())){
			addDesc.append("未补充资料;");
		}
		addDesc.append(patchbolt.getMemo());
		return addDesc;
	}
	
	/**
	 * wangtao  易达金提交补件时传到pad端的补件描述
	 * @param patchbolt
	 * @return
	 */
	public StringBuffer getAddDescYdj(PatchboltYdj patchboltYdj){
		StringBuffer addDesc = new StringBuffer("补件描述：");
		String dueDate = new SimpleDateFormat("yyyy-MM-dd").format(patchboltYdj.getDueDate());
		if(dueDate!=null&&!"".equals(dueDate)){
			addDesc.append("补件期限("+dueDate+")");
		}
		addDesc.append("补件材料：");
		if("1".equals(patchboltYdj.getIdentityCard())){
			addDesc.append("身份证明文件;");
		}
		if("1".equals(patchboltYdj.getPayWater())){
			addDesc.append("工资流水;");
		}	
		if("1".equals(patchboltYdj.getWorkProof())){
			addDesc.append("工作证明文件;");
		}
		if("1".equals(patchboltYdj.getIncomeProof())){
			addDesc.append("收入证明文件;");
		}
		if("1".equals(patchboltYdj.getSocialSecurity())){
			addDesc.append("社保;");
		}
		if("1".equals(patchboltYdj.getHouseProof())){
			addDesc.append("房产证明;");
		}
		if("1".equals(patchboltYdj.getAccumulationFund())){
			addDesc.append("公积金;");
		}
		if("1".equals(patchboltYdj.getEducationProof())){
			addDesc.append("学历证明;");
		}
		if("1".equals(patchboltYdj.getTaxReceipt())){
			addDesc.append("税单;");
		}
		if("1".equals(patchboltYdj.getCreditCardBill())){
			addDesc.append("信用卡账单;");
		}
		if("1".equals(patchboltYdj.getOwnerBankProof())){
			addDesc.append("我行业务往来证明;");
		}
		if("1".equals(patchboltYdj.getFinancalProof())){
			addDesc.append("财力证明;");
		}
		if("1".equals(patchboltYdj.getLoanSettleProof())){
			addDesc.append("贷款结清证明;");
		}
		if("1".equals(patchboltYdj.getRprProof())){
			addDesc.append("户口证明;");
		}
		if("1".equals(patchboltYdj.getMarriageCertificate())){
			addDesc.append("结婚证;");
		}
		if("1".equals(patchboltYdj.getBusinessLicense())){
			addDesc.append("企业营业执照;");
		}
		if("1".equals(patchboltYdj.getCarProof())){
			addDesc.append("车产证明;");
		}
		if("1".equals(patchboltYdj.getRetricalMasterCopy())){
			addDesc.append("调阅原件;");
		}
		if("1".equals(patchboltYdj.getImageRepair())){
			addDesc.append("影像修复;");
		}
		if("1".equals(patchboltYdj.getOther())){
			addDesc.append("其他;");
		}
		addDesc.append(patchboltYdj.getMemo());
		return addDesc;
	}
	
	
	/**
	 * wangtao  标准卡提交补件和二次提交补件调用pad接口
	 * @throws CoreException 
	 */
	public Patchbolt getCommunicationWithPad(Patchbolt patchbolt) throws CoreException{
		//首先获取appId的根据appId判断该申请件是pad补件还是短信补件
		String appId = patchbolt.getAppId();
		String str = appId.substring(6, 7);
		if("A".equals(str)&&(null==patchbolt.getWorkerOrder()&&null==patchbolt.getVipAttr())){//说明是pad补件
			logger.info("说明是pad补件,证明是pad件=========================");
			//（标准卡） 调用pad补件接口返回pad端是否成功接收补件信息(整体逻辑---pad成功接收消息即可，若接收消息失败，则再调用接口直至3次)
			String method = "POST";
			Map<String, String> padMap = PadProperties.getParoperMap();
			String urlStr = padMap.get("padUrl");
			String id = patchbolt.getAutoId();
			Patchbolt patchbolt2 = patchboltService.selectByPrimaryKey(patchbolt.getAppId());
			if(null!=patchbolt2){
				id = patchbolt2.getAutoId();
			}
			Map<String,Object> paramMap = new HashMap<String, Object>();
			//调用接口的参数1是appId
			paramMap.put("appCode",appId);
			//调用接口的参数2是主键
			paramMap.put("id", id);
			//调用接口的参数3是补件描述
			StringBuffer addDesc = getAddDesc(patchbolt);
			paramMap.put("addDesc", addDesc);
//			if (logger.isInfoEnabled()) {
//				logger.info("查询参数：{}", paramMap);
//			}
			String result = patchboltApsInvoking(method,urlStr, paramMap);
			if(result!=null){
				//将pad返回的接收是否成功的串转成map
				Map<String,Object> resultMap = (Map<String, Object>) JSON.parse(result);
				//pad返回的接收消息
				String responseCode = (String)resultMap.get("responseCode");//1为接收成功，0为接收失败
				String responseDesc = (String) resultMap.get("responseDesc");//接收失败描述
				if("0".equals(responseCode)){
					//调用pad接口成功返回result但返回的responseCode返回的值错误，则再调用一次接口
					String result1 = patchboltApsInvoking(method,urlStr, paramMap);
					if(result1!=null){//判断返回是否成功
						Map<String,Object> resultMap1 = (Map<String, Object>) JSON.parse(result1);
						if("0".equals(resultMap1.get("responseCode"))){
							patchbolt.setSendFlag("1");
							if (logger.isInfoEnabled()) {
								logger.info("调pad接口成功，返回失败==========================,appid:"+patchbolt.getAppId());
								logger.error("padpatchbolt error responseCode:"+ (String)resultMap1.get("responseCode")+"\t responseDesc"+(String)resultMap1.get("responseDesc"));
							}
							throw new CoreException("网络异常，补件失败");
						}else{
							patchbolt.setSendFlag("2");
							if (logger.isInfoEnabled()) {
								logger.info("调pad接口成功，返回成功==========================,appid:"+patchbolt.getAppId());
							}
						}
					}else{//再次调用接口后没有返回数据，返回失败
						patchbolt.setSendFlag("1");
						if (logger.isInfoEnabled()) {
							logger.info("调pad接口成功，返回失败==========================,appid:"+patchbolt.getAppId());
							logger.error("padpatchbolt error resultMap1为空");
						}
						throw new CoreException("网络异常，补件失败");
					}
				}else{//一次性调用pad接口成功
					patchbolt.setSendFlag("2");
					if (logger.isInfoEnabled()) {
						logger.info("调pad接口成功==========================,appid:"+patchbolt.getAppId());
					}
				}
			}else{//第一次根本就没有调通接口的情况下，再调一次
				String result2 = patchboltApsInvoking(method,urlStr, paramMap);
				if(result2!=null){//第二次调用pad接口判断返回数据
					Map<String,Object> resultMap2 = (Map<String, Object>) JSON.parse(result2);
					if("0".equals(resultMap2.get("responseCode"))){
						patchbolt.setSendFlag("1");
						if (logger.isInfoEnabled()) {
							logger.info("调pad接口成功，返回失败==========================,appid:"+patchbolt.getAppId());
							logger.error("padpatchbolt error responseCode:"+ (String)resultMap2.get("responseCode")+"\t responseDesc"+(String)resultMap2.get("responseDesc"));
						}
						throw new CoreException("网络异常，补件失败");
					}else{
						patchbolt.setSendFlag("2");
						if (logger.isInfoEnabled()) {
							logger.info("调pad接口成功，返回成功==========================,appid:"+patchbolt.getAppId());
						}
					}
				}else{
					patchbolt.setSendFlag("1");
					if (logger.isInfoEnabled()) {
						logger.info("网络异常，补件失败==========================,appid:"+patchbolt.getAppId());
						logger.error("padpatchbolt error resultMap2为空");
					}
					throw new CoreException("网络异常，补件失败");
				}
			}
			//调用pad补件结束  wangtao
		}else if(null!=patchbolt.getWorkerOrder()||null!=patchbolt.getVipAttr()){
			logger.info("=====================工单和vip不触发pad,appid:"+patchbolt.getAppId());
		}else{//不是pad补件，是短信补件
			patchbolt.setSendFlag("3");//短信未发送补件
			logger.info("=====================短信补件,appid:"+patchbolt.getAppId());
		}
		return patchbolt;
	}
	
	/**
	 * wangtao  易达金提交补件和二次提交补件调用pad接口
	 * @throws CoreException 
	 */
	public PatchboltYdj getCommunicationWithPad(PatchboltYdj patchboltYdj) throws CoreException{
		//首先获取appId的根据appId判断该申请件是pad补件还是短信补件
		String appId = patchboltYdj.getAppId();
		String str = appId.substring(6, 7);
		if("A".equals(str)&&(null==patchboltYdj.getWorkerOrder()&&null==patchboltYdj.getVipAttr())){//说明是pad补件
			if (logger.isInfoEnabled()) {
				logger.info("开始进入pad补件,证明是pad件=============================");
			}
			// （易达金） 调用pad补件接口返回pad端是否成功接收补件信息(整体逻辑---pad成功接收消息即可，若接收消息失败，则再调用接口直至3次)
			String method = "POST";
			Map<String, String> padMap = PadProperties.getParoperMap();
			String urlStr = padMap.get("padUrl");
			String id = patchboltYdj.getAutoId();
			PatchboltYdj patchboltYdj2 = patchboltService.selectByPrimaryKeyYdj(patchboltYdj.getAppId());
			if(null!=patchboltYdj2){
				id = patchboltYdj2.getAutoId();
			}
			Map<String,Object> paramMap = new HashMap<String, Object>();
			//调用接口的参数1是appId
			paramMap.put("appCode",appId);
			//调用接口的参数2是主键autoId
			paramMap.put("id", id);
			//调用接口的参数3是补件描述
			StringBuffer addDesc = getAddDescYdj(patchboltYdj);
			paramMap.put("addDesc", addDesc);
//			if (logger.isInfoEnabled()) {
//				logger.info("查询参数：{}", paramMap);
//			}
			String result = patchboltApsInvoking(method,urlStr, paramMap);
			if(result!=null){
				//将pad返回的接收是否成功的串转成map
				Map<String,Object> resultMap = (Map<String, Object>) JSON.parse(result);
				//pad返回的接收消息
				String responseCode = (String)resultMap.get("responseCode");//1为接收成功，0为接收失败
				String responseDesc = (String) resultMap.get("responseDesc");//接收失败描述
				if("0".equals(responseCode)){
					String result1 = patchboltApsInvoking(method,urlStr, paramMap);
					if(result1!=null){
						Map<String,Object> resultMap1 = (Map<String, Object>) JSON.parse(result1);
						if("0".equals(resultMap1.get(responseCode))){
							patchboltYdj.setSendFlag("1");
							if (logger.isInfoEnabled()) {
								logger.info("=====================调取pad成功，返回失败,appid:"+patchboltYdj.getAppId());
								logger.error("padpatchbolt error responseCode:"+ (String)resultMap1.get("responseCode")+"\t responseDesc"+(String)resultMap1.get("responseDesc"));
							}
							throw new CoreException("网络异常，补件失败");
						}else{
							patchboltYdj.setSendFlag("2");
							if (logger.isInfoEnabled()) {
								logger.info("=====================调取pad成功，返回成功,appid:"+patchboltYdj.getAppId());
							}
						}
					}else{
						patchboltYdj.setSendFlag("1");
						if (logger.isInfoEnabled()) {
							logger.info("=====================调取pad成功，返回失败,appid:"+patchboltYdj.getAppId());
							logger.error("padpatchbolt error resultMap1 为空");
						}
						throw new CoreException("网络异常，补件失败");
					}
				}else{
					patchboltYdj.setSendFlag("2");
					if (logger.isInfoEnabled()) {
						logger.info("=====================调取pad成功，返回成功,appid:"+patchboltYdj.getAppId());
					}
				}
			}else{//第一次根本就没有调通接口的情况下，再调一次
				String result2 = patchboltApsInvoking(method,urlStr, paramMap);
				if(result2!=null){
					Map<String,Object> resultMap2 = (Map<String, Object>) JSON.parse(result2);
					if("0".equals(resultMap2.get("responseCode"))){
						patchboltYdj.setSendFlag("1");
						if (logger.isInfoEnabled()) {
							logger.info("=====================调取pad成功，返回失败,appid:"+patchboltYdj.getAppId());
							logger.error("padpatchbolt error responseCode:"+ (String)resultMap2.get("responseCode")+"\t responseDesc"+(String)resultMap2.get("responseDesc"));
						}
						throw new CoreException("网络异常，补件失败");
					}else{
						patchboltYdj.setSendFlag("2");
						if (logger.isInfoEnabled()) {
							logger.info("=====================调取pad成功，返回成功,appid:"+patchboltYdj.getAppId());
						}
					}
				}else{
					patchboltYdj.setSendFlag("1");
					if (logger.isInfoEnabled()) {
						logger.info("=====================网络异常，补件失败,appid:"+patchboltYdj.getAppId());
						logger.error("padpatchbolt error resultMap2为空");
					}
					throw new CoreException("网络异常，补件失败");
				}
			}
			//调用pad补件结束  wangtao
		}else if(null!=patchboltYdj.getWorkerOrder()||null!=patchboltYdj.getVipAttr()){
			logger.info("=====================工单和vip不触发pad,appid:"+patchboltYdj.getAppId());
		}else{//说明是短信补件
			patchboltYdj.setSendFlag("3");//短信尚未发送补件
			logger.info("=====================短信补件,appid:"+patchboltYdj.getAppId());
		}
		return patchboltYdj;
	}
	
	/**
	 * 调用pad的方法，这是信审部分接口，增加传参ysFlag = 0
	 * @param method
	 * @param urlStr
	 * @param paramMap
	 * @return
	 */
	private String patchboltApsInvoking(String method, String urlStr, Map<String, Object> paramMap) {
		try {

			//调用接口的参数4是预审调用pad专用参数ysFlag 0 信审 1预审
			paramMap.put("ysFlag", "0");
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
				logger.info("返回ResponseMessage参数：{}",conn.getResponseMessage());
			} 
			if (buff != null) {
				return buff.toString();
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (logger.isInfoEnabled()) {
				logger.info("pad**************：{}","调用pad接口失败");
			} 
			return null;
		}
	}

}
