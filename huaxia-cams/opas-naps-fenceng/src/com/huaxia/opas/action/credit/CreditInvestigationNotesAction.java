package com.huaxia.opas.action.credit;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import com.alibaba.fastjson.JSON;
import com.huateng.neofp.core.Action;
import com.huateng.neofp.core.Context;
import com.huaxia.opas.domain.decision.TelcheckAddnote;
import com.huaxia.opas.domain.input.BizInpApplC1;
import com.huaxia.opas.domain.input.TelcheckResult;
import com.huaxia.opas.service.credit.CreditCheckSearchService;


/**
 * 征信调查  注记 
 * 2017-3-21 15:12:38
 * @author 刘志辉
 *
 */
public class CreditInvestigationNotesAction implements Action{

	private static Logger logger = LoggerFactory.getLogger(CreditInvestigationNotesAction.class);
	@Resource(name = "creditCheckSearchService")
	private CreditCheckSearchService creditCheckSearchService;
	/**
	 * 根据申请编号查询主卡进件信息
	 * @throws Exception 
	 */
	public void queryCreditNotesAppId(Context context){
		try {
			//获取申请编号
			String appId = StringUtils.trimToEmpty((String) context.getDataMap().get("appId"));
			//查询主卡进件信息
			BizInpApplC1 appl = creditCheckSearchService.selectBizInpApplC1ByAppId(appId);
			//生日
			Date birth = appl.getC1Birth(); 
			String sbirth = new SimpleDateFormat("yyyy-MM-dd").format(birth);
			//id有效期
			Date idDate = appl.getC5Idte1();
			String c5Idte1 = new SimpleDateFormat("yyyy-MM-dd").format(idDate);
			//查询人行摘要信息
			Map<String,String> pboc = creditCheckSearchService.queryPbocInfo(appId);
			//查询二代人行摘要信息
			Map<String,String> bank = creditCheckSearchService.queryPbocInfo(appId);
			//获得验证信息
			Map<String,String> yanzhenMap = creditCheckSearchService.queryTeam(appId);
			if(yanzhenMap==null){
				yanzhenMap = new HashMap<String,String>(); 
			}
			Map<String,String> yanzhenResMap = new HashMap<String,String>();
			yanzhenResMap.put("credit_COMPANY_NAME", yanzhenMap.get("OPAS_CREDIT_TELCHECK_LIST-COMPANY_NAME")==null?"0":"1");//征信电话核查白名单表
			yanzhenResMap.put("credit_COMPANY_TEL", yanzhenMap.get("OPAS_CREDIT_TELCHECK_LIST-COMPANY_TEL")==null?"0":"1");//征信电话核查白名单表
			
			yanzhenResMap.put("crm_MOBILEPHONE", yanzhenMap.get("SP_APS_IFS_CUST_INFO-MOBILEPHONE-MOBILEPHONE")==null?"0":"1");//CRM 客户基本信息(审批)--手机号码
			yanzhenResMap.put("crm_BUSPHONE", yanzhenMap.get("SP_APS_IFS_CUST_INFO-BUSPHONE")==null?"0":"1");//--单位电话  
			yanzhenResMap.put("crm_PHONE", yanzhenMap.get("SP_APS_IFS_CUST_INFO-PHONE")==null?"0":"1");//--住宅电话  
			yanzhenResMap.put("crm_COMPANY", yanzhenMap.get("SP_APS_IFS_CUST_INFO-COMPANY")==null?"0":"1");//--单位名称
			yanzhenResMap.put("crm_BUSADDR", yanzhenMap.get("SP_APS_IFS_CUST_INFO-BUSADDR")==null?"0":"1");//--单位地址
			yanzhenResMap.put("crm_HOMEADDR", yanzhenMap.get("SP_APS_IFS_CUST_INFO-HOMEADDR")==null?"0":"1");//--账单地址(住在地址)
			//人行比对结果
			yanzhenResMap.put("pboc_CELL_PHONE", yanzhenMap.get("OPAS_PBOC_PERSONAL_INFO-CELL_PHONE")==null?"0":"1");//--手机号码
			yanzhenResMap.put("pboc_C_COMP_PHONE", yanzhenMap.get("OPAS_PBOC_PERSONAL_INFO-C_COMP_PHONE")==null?"0":"1");//--单位电话  
			yanzhenResMap.put("pboc_COMPANY", yanzhenMap.get("OPAS_PBOC_PROFESSION_INFO-COMPANY")==null?"0":"1");//--单位名称
			
			yanzhenResMap.put("pboc_RESI_TEL", yanzhenMap.get("OPAS_PBOC_PERSONAL_INFO-RESI_TEL")==null?"0":"1");//--住宅电话  
			
			yanzhenResMap.put("pboc_COMP_ADDR", yanzhenMap.get("OPAS_PBOC_PROFESSION_INFO-COMP_ADDR")==null?"0":"1");//--单位地址
			yanzhenResMap.put("pboc_RESIDENT_ADDR", yanzhenMap.get("OPAS_PBOC_RESIDENCE_INFO-RESIDENT_ADDR")==null?"0":"1");//--账单地址(住在地址)
			if(appl!=null){
				context.setData("sbirth", sbirth);
				context.setData("c5Idte1", c5Idte1);
				context.setData("appl", appl);
				context.setData("pboc", pboc);
				context.setData("bank", bank);
				context.setData("yanzhenResMap", yanzhenResMap);
			}else{
				logger.error("【主卡进件信息为空，请检测】");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	//征信调查页面 纪要信息变色
	public void updateMemoChangeColor(Context ctx) {
		Map<String, Object> dataMap = ctx.getDataMap();
		creditCheckSearchService.updateTelcheckAddNoteByTalId(dataMap);
		/*String jsonString = JSON.toJSONString(dataMap);
		TelcheckAddnote note = JSON.parseObject(jsonString, TelcheckAddnote.class);
		creditCheckSearchService.updateByAutoId(note);*/
	}
	
	//征信注记页面 信息保存   单条插入
	public void saveOrUpdateInfo(Context ctx){
		String msg = "操作失败，请重试或联系管理员";
		Map<String, Object> dataMap = ctx.getDataMap();
		String flagStr = (String) ctx.getData("flagStr");; //标志位，1新增或修改 0删除
		String jsonString = JSON.toJSONString(dataMap);
		TelcheckAddnote note = JSON.parseObject(jsonString, TelcheckAddnote.class);
		if("0".equals(flagStr)&&null!=note){
			msg = creditCheckSearchService.deleteByAutoId(note.getAutoId());
		}else if("1".equals(flagStr)&&null!=note){
			msg = creditCheckSearchService.saveOrUpdateNotes(note);
		}
		String appId = (String) ctx.getData("appId");
		Assert.notNull(appId, "请求申请件编号为空!");
		List<TelcheckAddnote> notesList = creditCheckSearchService.selectNotesList(appId);
		Map<String, Object> dataMap1 = new HashMap<String, Object>();
		dataMap1.put("rows", notesList);
		ctx.setDataMap(dataMap1);
		ctx.setData("msg", msg);
	}
	//征信注记页面 信息保存   单条插入
	public void saveNotes(Context ctx){
		String msg = "操作失败，请重试或联系管理员";
		Map<String, Object> dataMap = ctx.getDataMap();
		String jsonString = JSON.toJSONString(dataMap);
		//String appId = (String) ctx.getData("appId");
		TelcheckAddnote note = JSON.parseObject(jsonString, TelcheckAddnote.class);
		creditCheckSearchService.saveNotes(note);
		/*List<TelcheckAddnote> notesList = creditCheckSearchService.selectNotesList(appId);
		Map<String, Object> dataMap1 = new HashMap<String, Object>();
		dataMap1.put("rows", notesList);
		ctx.setDataMap(dataMap1);*/
		ctx.setData("msg", msg);
	}
	//批量插入
	public void batchSave(Context ctx){
		String msg = "操作失败，请重试或联系管理员";
		Map<String, Object> dataMap = ctx.getDataMap();
		String jsonString = JSON.toJSONString(dataMap);
		String[] notesAttr = null;
		if(null!=jsonString){
			notesAttr = jsonString.split("");
		}
	}
	//征信调查--注记信息
	public void queryNotesList(Context ctx){
		String appId = (String) ctx.getData("appId");
		Assert.notNull(appId, "请求申请件编号为空!");
		List<TelcheckAddnote> notesList = creditCheckSearchService.selectNotesList(appId);
	/*	SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		if(null!=notesList){
			for(TelcheckAddnote notes :notesList){
				df.format(notes.getCrtDate());
			}
		}*/
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("rows", notesList);
		ctx.setDataMap(dataMap);
	}
	//征信调查查看 --智能语音退回   --ADD BY DAIHAO
	public void svoiceBackQuery(Context ctx) {
		String appId = (String) ctx.getData("appId");
		List<Object> creditSurvey = creditCheckSearchService.querySVoiceBack(appId);
		String conclusion = "";
		String svoiceMark = "";
		Map<String,String> svoiceMap = creditCheckSearchService.querySVoiceConclusion(appId);
		if(null!=svoiceMap){	
			if(svoiceMap.containsKey("CONCLUSION")){
				conclusion = svoiceMap.get("CONCLUSION").trim();
			}
			if(svoiceMap.containsKey("CRT_TIME")){
				svoiceMark+= svoiceMap.get("CRT_TIME")+" ";
			}
			if(svoiceMap.containsKey("SUMMARY")){
				svoiceMark+= svoiceMap.get("SUMMARY")+" ";
			}
		}
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("rows", creditSurvey);
		dataMap.put("conclusion", conclusion);
		dataMap.put("svoiceMark", svoiceMark);
		ctx.setDataMap(dataMap);
	}	
	//征信调查查看 --审批退回  安东
	public void approvalBackQuery(Context ctx) {
		String appId = (String) ctx.getData("appId");
		List<Object> creditSurvey = creditCheckSearchService.queryApprovalBack(appId);
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("rows", creditSurvey);
		ctx.setDataMap(dataMap);
	}
		
	//征信调查查看 --质检  安东
	public void queryCheckingDetailed(Context ctx) {
		String appId = (String) ctx.getData("appId");
		List<Object> checkingDetail = creditCheckSearchService.queryCheckingDetail(appId);
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("rows", checkingDetail);
		ctx.setDataMap(dataMap);
	}
		
	//征信调查查看--注记信息  安东
	public void queryCreditNotes(Context ctx) {
		String appId = (String) ctx.getData("appId");
		List<Object> creditSurvey = creditCheckSearchService.queryCreditInvestigationSurvey(appId);
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("rows", creditSurvey);
		ctx.setDataMap(dataMap);
	}
	
	//查询单位电话区号是否存在
	public void queryTelePhoneCode(Context ctx){
		try {
			String c1Cotel = (String) ctx.getData("c1Cotel");
			String ydjFlag = (String) ctx.getData("ydjFlag");
			String msg = creditCheckSearchService.queryTelno(c1Cotel,ydjFlag);
			if ("".equals(msg)){
				ctx.setData("success", true);
			} else {
				ctx.setData("success", false);
				ctx.setData("msg", msg);
			}
		} catch (Exception e1) {
			ctx.setData("success", false);
			//"区号查询对比异常。正确格式：01012345678。"
			//ctx.setData("msg", "无网点");
			e1.printStackTrace();
		}
	}
	
	//单条插入问题库结果
		public void saveQuestionResult(Context ctx){
			String msg = "操作失败，请重试或联系管理员";
			Map<String, Object> dataMap = ctx.getDataMap();
			String jsonString = JSON.toJSONString(dataMap);
			TelcheckAddnote note = JSON.parseObject(jsonString, TelcheckAddnote.class);
			creditCheckSearchService.saveOrUpdateNotes(note);
		}
		//单条插入 电核结果
		public void saveTelcheckResult(Context ctx){
			try {
			String msg = "保存成功。";
			String appId = (String) ctx.getData("appId");
			Map<String, Object> dataMap = ctx.getDataMap();
			String jsonString = JSON.toJSONString(dataMap);
			TelcheckResult result = JSON.parseObject(jsonString, TelcheckResult.class);
			creditCheckSearchService.saveOrUpdateTelcheckResult(result, appId);
			ctx.setData("msg", msg);
			} catch (Exception e1) {
			ctx.setData("msg", "保存失败。");
			e1.printStackTrace();
			}
			
		}
		//征信调查页面  保存 更新
		public void saveOrUpdateResult(Context ctx) throws Exception{
			String msg = "保存成功。";
			try {
			String appId = (String) ctx.getData("appId");
			String crtUser = (String) ctx.getData("crtUser1");
			String flag = (String) ctx.getData("ydjFlag1");
			Map<String, Object> map = ctx.getDataMap();
			String jsonString = JSON.toJSONString(map);
			
			if(logger.isInfoEnabled()){
				String blockCode ="";
				   if( map!=null && map.get("blockCode")!=null){
					   blockCode= map.get("blockCode").toString();
				   }
				logger.info(appId+crtUser+"征信调查页面  保存更新blockCode="+blockCode);
			}
			
			TelcheckResult result = JSON.parseObject(jsonString, TelcheckResult.class);
			//把拒绝原因字段从map中移除 否则会记录在信息修改记录表中
			map.remove("creditRefuseReason");
			String jsonString1 = JSON.toJSONString(map);
			com.alibaba.fastjson.JSONObject jsonObject = JSON.parseObject(jsonString1);
			BizInpApplC1 bizInpApplC1 = JSON.parseObject(jsonString1, BizInpApplC1.class);
			result.setCrtUser(crtUser);
			result.setYdjFlag(flag);
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
			Date c1Birth = null;
			Date c5Idte1 = null;
			if (!"".equals(jsonObject.getString("c1Birth")) && null != (jsonObject.getString("c1Birth"))) {
				c1Birth = sf.parse(jsonObject.getString("c1Birth"));
				bizInpApplC1.setC1Birth(c1Birth);
			}
			if (!"".equals(jsonObject.getString("c5Idte1")) && null != (jsonObject.getString("c5Idte1"))) {
				c5Idte1 = sf.parse(jsonObject.getString("c5Idte1"));
				bizInpApplC1.setC5Idte1(c5Idte1);
			}
			creditCheckSearchService.saveOrUpdateResult(result,appId,bizInpApplC1);
			ctx.setData("success", true);
			ctx.setData("msg", msg);
			} catch (Exception e1) {
			ctx.setData("success", false);
			ctx.setData("msg", "保存失败。");
			e1.printStackTrace();
			}
		}
		/**
		 *@Title:saveOrUpdateTelcheckAddNoteMsg
		 *@Description:征信调查页面 插入、修改、删除   纪要信息方法
		 *@param ctx
		 *@author: gaohui
		 *@Date:2017年8月12日下午2:11:54
		 */
		public void saveOrUpdateTelcheckAddNoteMsg(Context ctx){
			String msg = "操作成功。";
			Map<String, Object> dataMap = ctx.getDataMap();
			if (logger.isInfoEnabled()) {
				logger.info("[征信调查页面] 纪要提交删除参数: {}", dataMap);
			}
			try {
				creditCheckSearchService.saveOrUpdateTelcheckAddNoteMsg(dataMap);
			} catch (Exception e) {
				e.printStackTrace();
				msg = "操作失败，请联系管理员。";
			}
		
			ctx.setData("msg", msg);
		}
		/**
		 *@Title:saveTelcheckAddNoteCommitMsg
		 *@Description:征信调查页面  纪要提交 保存方法 
		 *@param ctx
		 *@author: gaohui
		 *@Date:2017年8月12日下午6:47:06
		 */
		public void saveTelcheckAddNoteCommitMsg(Context ctx){
			String msg = "操作成功";
			Map<String, Object> dataMap = ctx.getDataMap();
			if (logger.isInfoEnabled()) {
				logger.info("[征信调查页面] 纪要提交保存参数: {}", dataMap);
			}
			try {
			creditCheckSearchService.saveTelcheckAddNoteCommitMsg(dataMap);
			} catch (Exception e) {
				e.printStackTrace();
				msg = "操作失败，请联系管理员。";
			}
			ctx.setData("msg", msg);
		}
}
