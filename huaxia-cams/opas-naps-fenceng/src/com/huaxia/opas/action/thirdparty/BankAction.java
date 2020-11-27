package com.huaxia.opas.action.thirdparty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.google.gson.Gson;
import com.huateng.neofp.core.Action;
import com.huateng.neofp.core.Context;
import com.huaxia.opas.domain.system.ApRole;
import com.huaxia.opas.domain.system.ApUser;
import com.huaxia.opas.service.system.ApRoleService;
import com.huaxia.opas.service.system.ApUserService;
import com.huaxia.opas.service.thirdparty.BankService;

import net.sf.json.JSONObject;

/**
 * 第三方-人行(新版)
 * @author wuxinchao
 * 2019年1月14日
 */
public class BankAction implements Action{

	private static Logger logger = LoggerFactory.getLogger(BankAction.class);
	
	@Resource(name = "bankService")
	private BankService bankService;
	public BankService getBankService() {
		return bankService;
	}

	public void setBankService(BankService bankService) {
		this.bankService = bankService;
	}

	@Autowired
	private HttpServletRequest request;
	
	@Resource(name = "apUserService")
	private ApUserService apUserService;
	
	@Resource(name = "apRoleService")
	private ApRoleService apRoleService;
	
	/**
	 * 查询基本信息
	 * @param context
	 * @throws Exception
	 */
	public void queryBaseInfo(Context context) throws Exception{
		
		String appId = (String) context.getData("appId");
		//String appId = "1705151611P02441";
		Map<String, String> bankSummaryInfo = getBankService().selectBaseInfo(appId);
		if (bankSummaryInfo != null) {
		//人行详情中某些中文字段描述前显示数字，使用字符串截取去掉显示数字
		String maritalStatus = bankSummaryInfo.get("maritalStatus");
		String queryedCertType = bankSummaryInfo.get("queryedCertType");
		String gender = bankSummaryInfo.get("gender");
		String queryResultPrompt = bankSummaryInfo.get("queryResultPrompt");
		String degree =  bankSummaryInfo.get("degree");
		String qryReason = bankSummaryInfo.get("qryReason");
		String residencyStatus = bankSummaryInfo.get("residencyStatus");
		String mateCertType= bankSummaryInfo.get("mateCertType");//配偶证件类型去掉   类似  1|
		if(maritalStatus!=null&&!"".equals(maritalStatus)){
			bankSummaryInfo.put("maritalStatus", maritalStatus.substring(maritalStatus.indexOf("|")+1,maritalStatus.length()));
		}
		if(queryedCertType!=null&&!"".equals(queryedCertType)){
			bankSummaryInfo.put("queryedCertType", queryedCertType.substring(queryedCertType.indexOf("|")+1,queryedCertType.length()));
		}
		if(gender!=null&&!"".equals(gender)){
			bankSummaryInfo.put("gender", gender.substring(gender.indexOf("|")+1,gender.length()));
		}
		if(queryResultPrompt!=null&&!"".equals(queryResultPrompt)){
			bankSummaryInfo.put("queryResultPrompt", queryResultPrompt.substring(queryResultPrompt.indexOf("|")+1,queryResultPrompt.length()));
		}
		if(degree!=null&&!"".equals(degree)){
			bankSummaryInfo.put("degree", degree.substring(degree.indexOf("|")+1,degree.length()));
		}
		if(qryReason!=null&&!"".equals(qryReason)){
			bankSummaryInfo.put("qryReason", qryReason.substring(qryReason.indexOf("|")+1,qryReason.length()));
		}
		if(residencyStatus!=null&&!"".equals(residencyStatus)){
		bankSummaryInfo.put("residencyStatus", residencyStatus.substring(residencyStatus.indexOf("|")+1,residencyStatus.length()));
		}
		if(mateCertType!=null&&!"".equals(mateCertType)){
			bankSummaryInfo.put("mateCertType", mateCertType.substring(mateCertType.indexOf("|")+1,mateCertType.length()));
		}
		JSONObject jsonObject = JSONObject.fromObject(bankSummaryInfo);
		context.setData("success", true);
		context.setData("queryBaseInfo", jsonObject.toString());
		}else{
			context.setData("success", false);
		}
		
	}
	
	/**
	 * 其他身份信息查询
	 * @param context
	 * @throws Exception
	 */
	public void queryOtherBaseInfo (Context context) {
		
		String appId = (String) context.getData("appId");
		
		List<Map<String, String>> bankSummaryInfo = getBankService().selectOtherBaseInfo(appId);
	
		if (bankSummaryInfo != null) {
			String jsonObject=null;
			try {
			//	jsonObject = MAPPER.writeValueAsString(bankSummaryInfo);
				Gson gson = new Gson();
				jsonObject=gson.toJson(bankSummaryInfo);	
			} catch (Exception e) {
				e.printStackTrace();
			} 
			
			//JSONObject jsonObject = JSONObject.fromObject(bankSummaryInfo);
			context.setData("success", true);
			context.setData("queryResideInfo", jsonObject);
			return;
		}
		
		context.setData("success", false);
	}
	
	/**
	 * 防欺诈警示
	 * @param context
	 * @throws Exception
	 */
	public void queryAntiFraudInfo (Context context) {
		
		String appId = (String) context.getData("appId");
		
		List<Map<String, String>> bankSummaryInfo = getBankService().selectAntiFraudInfo(appId);
	
		if (bankSummaryInfo != null) {
			String jsonObject=null;
			try {
			//	jsonObject = MAPPER.writeValueAsString(bankSummaryInfo);
				Gson gson = new Gson();
				jsonObject=gson.toJson(bankSummaryInfo);	
			} catch (Exception e) {
				e.printStackTrace();
			} 
			
			//JSONObject jsonObject = JSONObject.fromObject(bankSummaryInfo);
			context.setData("success", true);
			context.setData("queryResideInfo", jsonObject);
			return;
		}
		
		context.setData("success", false);
	}
	
	/**
	 * 异议信息提示
	 * @param context
	 * @throws Exception
	 */
	public void queryDissentHintInfo (Context context) {
		
		String appId = (String) context.getData("appId");
		
		List<Map<String, String>> bankSummaryInfo = getBankService().selectDissentHintInfo(appId);
	
		if (bankSummaryInfo != null) {
			String jsonObject=null;
			try {
			//	jsonObject = MAPPER.writeValueAsString(bankSummaryInfo);
				Gson gson = new Gson();
				jsonObject=gson.toJson(bankSummaryInfo);	
			} catch (Exception e) {
				e.printStackTrace();
			} 
			
			//JSONObject jsonObject = JSONObject.fromObject(bankSummaryInfo);
			context.setData("success", true);
			context.setData("queryResideInfo", jsonObject);
			return;
		}
		
		context.setData("success", false);
	}
	/**
	 * 居住信息
	 * @param context
	 * @throws Exception
	 */
	public void queryResideInfo (Context context) {
		
		String appId = (String) context.getData("appId");
		
		List<Map<String, String>> bankSummaryInfo = getBankService().selectResideInfo(appId);
	
		if (bankSummaryInfo != null) {
			String jsonObject=null;
			try {
			//	jsonObject = MAPPER.writeValueAsString(bankSummaryInfo);
				Gson gson = new Gson();
				jsonObject=gson.toJson(bankSummaryInfo);	
			} catch (Exception e) {
				e.printStackTrace();
			} 
			
			//JSONObject jsonObject = JSONObject.fromObject(bankSummaryInfo);
			context.setData("success", true);
			context.setData("queryResideInfo", jsonObject);
			return;
		}
		
		context.setData("success", false);
	}
	
	/**
	 * 手机信息
	 * @param context
	 * @throws Exception
	 */
	public void queryPhoneInfo (Context context) {
		
		String appId = (String) context.getData("appId");
		List<Map<String, String>> bankPhoneInfo = getBankService().selectPhoneInfo(appId);
	
		if (bankPhoneInfo != null) {
			String jsonObject=null;
			try {
			//	jsonObject = MAPPER.writeValueAsString(bankSummaryInfo);
				Gson gson = new Gson();
				jsonObject=gson.toJson(bankPhoneInfo);	
			} catch (Exception e) {
				e.printStackTrace();
			} 
			
			//JSONObject jsonObject = JSONObject.fromObject(bankSummaryInfo);
			context.setData("success", true);
			context.setData("queryResideInfo", jsonObject);
			return;
		}
		
		context.setData("success", false);
	}
	
/**
 * 职业信息	
 * @param context
 * @throws Exception
 */
	
	public void queryProessionInfo (Context context) throws Exception{
		
			String appId = (String) context.getData("appId");
		
		List<Map<String, String>> bankSummaryInfo = getBankService().selectProessionInfo(appId);
		
		if (!bankSummaryInfo.isEmpty()) {
			String jsonObject=null;
			try {
				Gson gson = new Gson();
				//jsonObject = MAPPER.writeValueAsString(bankSummaryInfo);
				jsonObject=gson.toJson(bankSummaryInfo);
			} catch (Exception e) {
				e.printStackTrace();
			} 
			
			context.setData("success", true);
			context.setData("proessionInfo", jsonObject);
			return;
		}
		
		context.setData("success", false);
		
	}
	
/**
 * 	信息提示
 * @param context
 * @throws Exception
 */
	public void queryMessageInfo(Context context) throws Exception {
		String appId = (String) context.getData("appId");
		List<Map<String, String>> bankSummaryInfo = getBankService().selectMessageInfo(appId);

		if (bankSummaryInfo != null) {
			String jsonObject=null;
			try {
				Gson gson = new Gson();
				//jsonObject = MAPPER.writeValueAsString(bankSummaryInfo);
				jsonObject=gson.toJson(bankSummaryInfo);
			} catch (Exception e) {
				e.printStackTrace();
			} 
			context.setData("success", true);
			context.setData("queryResideInfo", jsonObject);
			return;
		}
		
		context.setData("success", false);
	}
/**
 * 	信息提示
 * @param context
 * @throws Exception
 */
	public void queryRecoveryInfo(Context context) throws Exception {
		String appId =  (String) context.getData("appId");
		List<Map<String, String>> bankSummaryInfo = getBankService().selectRecoveryInfo(appId);

		if (bankSummaryInfo != null) {
			String jsonObject=null;
			try {
				Gson gson = new Gson();
				//jsonObject = MAPPER.writeValueAsString(bankSummaryInfo);
				jsonObject=gson.toJson(bankSummaryInfo);
			} catch (Exception e) {
				e.printStackTrace();
			} 
			context.setData("success", true);
			context.setData("queryResideInfo", jsonObject);
			return;
		}
		
		context.setData("success", false);
	}
	
/**
 * 	逾期信息汇总
 * @param context
 * @throws Exception
 */
	public void queryOverDraftInfo(Context context) throws Exception {
		String appId =  (String) context.getData("appId");
		List<Map<String, String>> bankSummaryInfo = getBankService().selectOverDraftInfo(appId);

		if (bankSummaryInfo != null) {
			String jsonObject=null;
			try {
				Gson gson = new Gson();
				//jsonObject = MAPPER.writeValueAsString(bankSummaryInfo);
				jsonObject=gson.toJson(bankSummaryInfo);
			} catch (Exception e) {
				e.printStackTrace();
			} 
			context.setData("success", true);
			context.setData("queryResideInfo", jsonObject);
			return;
		}
		
		context.setData("success", false);
	}
	
	/**
	 * 个人信用报告"数字解读"	
	 * @param context
	 * @throws Exception
	 */
	
	public void queryCreditGradeInfo(Context context) throws Exception {
		String appId = (String) context.getData("appId");
		List<Map<String, String>> bankSummaryInfo = getBankService().selectCreditGradeInfo(appId);
		//Map<String, String> json = bankSummaryInfo.get(0);
		if (bankSummaryInfo != null) {
			String jsonObject=null;
			try {
				Gson gson = new Gson();
				//jsonObject = MAPPER.writeValueAsString(bankSummaryInfo);
				jsonObject=gson.toJson(bankSummaryInfo);
			} catch (Exception e) {
				e.printStackTrace();
			} 
			context.setData("creditGradeInfo", jsonObject);
			context.setData("success", true);
			return;
		}
		
		context.setData("success", false);
	}
	
	
/**
 * 逾期及违约信息	
 * @param context
 * @throws Exception
 */
	public void queryOverdueInfo(Context context) throws Exception {
		String appId = (String) context.getData("appId");
		//	Map<String, String> bankSummaryInfo = getBankService().queryMessageInfo(appId);
		List<Map<String, String>> bankSummaryInfo = getBankService().selectOverdueInfo(appId);
		if(!bankSummaryInfo.isEmpty()){
			if(bankSummaryInfo.size()>0){
			Map<String, String> json = bankSummaryInfo.get(0);
			
			if (bankSummaryInfo != null) {
				String jsonObject=null;
				try {
					Gson gson = new Gson();
					jsonObject=gson.toJson(json);
					
				} catch (Exception e) {
					e.printStackTrace();
				} 
				context.setData("success", true);
				context.setData("queryOverdueInfo", jsonObject);
			}
			}
		}
		
		
	}
/**
 * 授信及违约信息	
 * @param context
 * @throws Exception
 */
	/*public void queryCreditInfo(Context context)  {
		String appId = (String) context.getData("appId");
		//	Map<String, String> bankSummaryInfo = getBankService().queryMessageInfo(appId);
		List<Map<String, String>> bankSummaryInfo = getBankService().selectCreditInfo(appId);
		if(!bankSummaryInfo.isEmpty()){
			if(bankSummaryInfo.size()>0){
				Map<String, String> json = bankSummaryInfo.get(0);
				if (bankSummaryInfo != null) {
					String jsonObject=null;
					try {
						Gson gson = new Gson();
						jsonObject=gson.toJson(json);
					} catch (Exception e) {
						e.printStackTrace();
					} 
					context.setData("success", true);
					context.setData("queryCreditInfo", jsonObject);
				}
			}
		}
	}*/
/**
 * 非循环贷账户信息汇总	
 * @param context
 * @throws Exception
 */
	public void queryNoRevolveInfo(Context context)  {
		String appId = (String) context.getData("appId");
		//	Map<String, String> bankSummaryInfo = getBankService().queryMessageInfo(appId);
		List<Map<String, String>> bankSummaryInfo = getBankService().selectNoRevolveInfo(appId);
		if(!bankSummaryInfo.isEmpty()){
			if(bankSummaryInfo.size()>0){
				Map<String, String> json = bankSummaryInfo.get(0);
				if (bankSummaryInfo != null) {
					String jsonObject=null;
					try {
						Gson gson = new Gson();
						jsonObject=gson.toJson(json);
					} catch (Exception e) {
						e.printStackTrace();
					} 
					context.setData("success", true);
					context.setData("queryCreditInfo", jsonObject);
				}
			}
		}
	}
/**
 * 循环额度下分账户信息汇总 	
 * @param context
 * @throws Exception
 */
	public void queryRevolveLimInfo(Context context)  {
		String appId = (String) context.getData("appId");
		//	Map<String, String> bankSummaryInfo = getBankService().queryMessageInfo(appId);
		List<Map<String, String>> bankSummaryInfo = getBankService().selectRevolveLimInfo(appId);
		if(!bankSummaryInfo.isEmpty()){
			if(bankSummaryInfo.size()>0){
				Map<String, String> json = bankSummaryInfo.get(0);
				if (bankSummaryInfo != null) {
					String jsonObject=null;
					try {
						Gson gson = new Gson();
						jsonObject=gson.toJson(json);
					} catch (Exception e) {
						e.printStackTrace();
					} 
					context.setData("success", true);
					context.setData("queryCreditInfo", jsonObject);
				}
			}
		}
	}
/**
 * 循环贷账户信息汇总	
 * @param context
 * @throws Exception
 */
	public void queryRevolveInfo(Context context)  {
		String appId = (String) context.getData("appId");
		//	Map<String, String> bankSummaryInfo = getBankService().queryMessageInfo(appId);
		List<Map<String, String>> bankSummaryInfo = getBankService().selectRevolveInfo(appId);
		if(!bankSummaryInfo.isEmpty()){
			if(bankSummaryInfo.size()>0){
				Map<String, String> json = bankSummaryInfo.get(0);
				if (bankSummaryInfo != null) {
					String jsonObject=null;
					try {
						Gson gson = new Gson();
						jsonObject=gson.toJson(json);
					} catch (Exception e) {
						e.printStackTrace();
					} 
					context.setData("success", true);
					context.setData("queryCreditInfo", jsonObject);
				}
			}
		}
	}
/**
 * 贷记卡账户信息汇总
 * @param context
 * @throws Exception
 */
	public void queryCreditInfo(Context context)  {
		String appId = (String) context.getData("appId");
		//	Map<String, String> bankSummaryInfo = getBankService().queryMessageInfo(appId);
		List<Map<String, String>> bankSummaryInfo = getBankService().selectCreditInfo(appId);
		if(!bankSummaryInfo.isEmpty()){
			if(bankSummaryInfo.size()>0){
				Map<String, String> json = bankSummaryInfo.get(0);
				if (bankSummaryInfo != null) {
					String jsonObject=null;
					try {
						Gson gson = new Gson();
						jsonObject=gson.toJson(json);
					} catch (Exception e) {
						e.printStackTrace();
					} 
					context.setData("success", true);
					context.setData("queryCreditInfo", jsonObject);
				}
			}
		}
	}	
/**
 * 准贷记卡账户信息汇总
 * @param context
 * @throws Exception
 */
	public void querySemiCreditInfo(Context context)  {
		String appId = (String) context.getData("appId");
		//	Map<String, String> bankSummaryInfo = getBankService().queryMessageInfo(appId);
		List<Map<String, String>> bankSummaryInfo = getBankService().selectSemiCreditInfo(appId);
		if(!bankSummaryInfo.isEmpty()){
			if(bankSummaryInfo.size()>0){
				Map<String, String> json = bankSummaryInfo.get(0);
				if (bankSummaryInfo != null) {
					String jsonObject=null;
					try {
						Gson gson = new Gson();
						jsonObject=gson.toJson(json);
					} catch (Exception e) {
						e.printStackTrace();
					} 
					context.setData("success", true);
					context.setData("queryCreditInfo", jsonObject);
				}
			}
		}
	}	
/**
 * 相关还款责任信息汇总 个人-担保责任
 * @param context
 * @throws Exception
 */
	public void queryRepayInfo11(Context context)  {
		String appId = (String) context.getData("appId");
		//	Map<String, String> bankSummaryInfo = getBankService().queryMessageInfo(appId);
		List<Map<String, String>> bankSummaryInfo = getBankService().selectRepayInfo11(appId);
		if(!bankSummaryInfo.isEmpty()){
			if(bankSummaryInfo.size()>0){
				Map<String, String> json = bankSummaryInfo.get(0);
				if (bankSummaryInfo != null) {
					String jsonObject=null;
					try {
						Gson gson = new Gson();
						jsonObject=gson.toJson(json);
					} catch (Exception e) {
						e.printStackTrace();
					} 
					context.setData("success", true);
					context.setData("queryCreditInfo", jsonObject);
				}
			}
		}
	}
/**
 * 相关还款责任信息汇总 个人-其他相关还款责任
 * @param context
 * @throws Exception
 */
	public void queryRepayInfo19(Context context)  {
		String appId = (String) context.getData("appId");
		//	Map<String, String> bankSummaryInfo = getBankService().queryMessageInfo(appId);
		List<Map<String, String>> bankSummaryInfo = getBankService().selectRepayInfo19(appId);
		if(!bankSummaryInfo.isEmpty()){
			if(bankSummaryInfo.size()>0){
				Map<String, String> json = bankSummaryInfo.get(0);
				if (bankSummaryInfo != null) {
					String jsonObject=null;
					try {
						Gson gson = new Gson();
						jsonObject=gson.toJson(json);
					} catch (Exception e) {
						e.printStackTrace();
					} 
					context.setData("success", true);
					context.setData("queryCreditInfo", jsonObject);
				}
			}
		}
	}
/**
 * 相关还款责任信息汇总 企业-其他相关还款责任
 * @param context
 * @throws Exception
 */
	public void queryRepayInfo29(Context context)  {
		String appId = (String) context.getData("appId");
		//	Map<String, String> bankSummaryInfo = getBankService().queryMessageInfo(appId);
		List<Map<String, String>> bankSummaryInfo = getBankService().selectRepayInfo29(appId);
		if(!bankSummaryInfo.isEmpty()){
			if(bankSummaryInfo.size()>0){
				Map<String, String> json = bankSummaryInfo.get(0);
				if (bankSummaryInfo != null) {
					String jsonObject=null;
					try {
						Gson gson = new Gson();
						jsonObject=gson.toJson(json);
					} catch (Exception e) {
						e.printStackTrace();
					} 
					context.setData("success", true);
					context.setData("queryCreditInfo", jsonObject);
				}
			}
		}
	}
/**
 * 相关还款责任信息汇总 企业-担保责任
 * @param context
 * @throws Exception
 */
	public void queryRepayInfo21(Context context)  {
		String appId = (String) context.getData("appId");
		//	Map<String, String> bankSummaryInfo = getBankService().queryMessageInfo(appId);
		List<Map<String, String>> bankSummaryInfo = getBankService().selectRepayInfo21(appId);
		if(!bankSummaryInfo.isEmpty()){
			if(bankSummaryInfo.size()>0){
				Map<String, String> json = bankSummaryInfo.get(0);
				if (bankSummaryInfo != null) {
					String jsonObject=null;
					try {
						Gson gson = new Gson();
						jsonObject=gson.toJson(json);
					} catch (Exception e) {
						e.printStackTrace();
					} 
					context.setData("success", true);
					context.setData("queryCreditInfo", jsonObject);
				}
			}
		}
	}
/**
 * 后付费业务欠费信息汇总
 * @param context
 * @throws Exception
 */
	public void queryArrgepayInfo(Context context)  {
		String appId = (String) context.getData("appId");
		//	Map<String, String> bankSummaryInfo = getBankService().queryMessageInfo(appId);
		List<Map<String, String>> bankSummaryInfo = getBankService().selectArrgepayInfo(appId);
		if(!bankSummaryInfo.isEmpty()){
			if(bankSummaryInfo.size()>0){
				if (bankSummaryInfo != null) {
					String jsonObject=null;
					try {
						Gson gson = new Gson();
						jsonObject=gson.toJson(bankSummaryInfo);
					} catch (Exception e) {
						e.printStackTrace();
					} 
					context.setData("success", true);
					context.setData("queryCreditInfo", jsonObject);
				}
			}
		}
	}
/**
 * 公共信息汇总 
 * @param context
 * @throws Exception
 */
	public void queryPublicInfo(Context context)  {
		String appId = (String) context.getData("appId");
		//	Map<String, String> bankSummaryInfo = getBankService().queryMessageInfo(appId);
		List<Map<String, String>> bankSummaryInfo = getBankService().selectPublicInfo(appId);
		if(!bankSummaryInfo.isEmpty()){
			if(bankSummaryInfo.size()>0){
				if (bankSummaryInfo != null) {
					String jsonObject=null;
					try {
						Gson gson = new Gson();
						jsonObject=gson.toJson(bankSummaryInfo);
					} catch (Exception e) {
						e.printStackTrace();
					} 
					context.setData("success", true);
					context.setData("queryCreditInfo", jsonObject);
				}
			}
		}
	}
/**
 * 上一次查询记录 
 * @param context
 * @throws Exception
 */
	public void queryLastQueryRecInfo(Context context)  {
		String appId = (String) context.getData("appId");
		//	Map<String, String> bankSummaryInfo = getBankService().queryMessageInfo(appId);
		List<Map<String, String>> bankSummaryInfo = getBankService().selectLastQueryRecInfo(appId);
		if(!bankSummaryInfo.isEmpty()){
			if(bankSummaryInfo.size()>0){
				if (bankSummaryInfo != null) {
					String jsonObject=null;
					try {
						Gson gson = new Gson();
						jsonObject=gson.toJson(bankSummaryInfo);
					} catch (Exception e) {
						e.printStackTrace();
					} 
					context.setData("success", true);
					context.setData("queryCreditInfo", jsonObject);
				}
			}
		}
	}
/**
 * 最近一个月内查询机构数
 * @param context
 * @throws Exception
 */
	public void queryLastQueryInfo(Context context)  {
		String appId = (String) context.getData("appId");
		//	Map<String, String> bankSummaryInfo = getBankService().queryMessageInfo(appId);
		List<Map<String, String>> bankSummaryInfo = getBankService().selectLastQueryInfoNew(appId);
		if(!bankSummaryInfo.isEmpty()){
			if(bankSummaryInfo.size()>0){
				if (bankSummaryInfo != null) {
					String jsonObject=null;
					try {
						Gson gson = new Gson();
						jsonObject=gson.toJson(bankSummaryInfo);
					} catch (Exception e) {
						e.printStackTrace();
					} 
					context.setData("success", true);
					context.setData("queryCreditInfo", jsonObject);
				}
			}
		}
	}
/**
 * 被追偿信息账户
 * @param context
 * @throws Exception
 */
	public void queryC1Info(Context context)  {
		String appId = (String) context.getData("appId");
		//	Map<String, String> bankSummaryInfo = getBankService().queryMessageInfo(appId);
		List<Map<String, String>> bankSummaryInfo = getBankService().selectC1Info(appId);
		
		if(!bankSummaryInfo.isEmpty()){
			if(bankSummaryInfo.size()>0){
				if (bankSummaryInfo != null) {
					String jsonObject=null;
					try {
						Gson gson = new Gson();
						jsonObject=gson.toJson(bankSummaryInfo);
					} catch (Exception e) {
						e.printStackTrace();
					} 
					context.setData("success", true);
					context.setData("queryCreditInfo", jsonObject);
				}
			}
		}
	}
	
/**
 * 特殊交易类型
 * @param context
 * @throws Exception
 */
	public void querySpetradInfo(Context context)  {
		String appId = (String) context.getData("appId");
		//	Map<String, String> bankSummaryInfo = getBankService().queryMessageInfo(appId);
		List<Map<String, String>> bankSummaryInfo = getBankService().selectSpetradInfo(appId);
		
		if(!bankSummaryInfo.isEmpty()){
			if(bankSummaryInfo.size()>0){
				if (bankSummaryInfo != null) {
					String jsonObject=null;
					try {
						Gson gson = new Gson();
						jsonObject=gson.toJson(bankSummaryInfo);
					} catch (Exception e) {
						e.printStackTrace();
					} 
					context.setData("success", true);
					context.setData("queryCreditInfo", jsonObject);
				}
			}
		}
	}
/**
 * 非循环贷账户
 * @param context
 * @throws Exception
 */
	public void queryD1Info(Context context)  {
		String appId = (String) context.getData("appId");
		//	Map<String, String> bankSummaryInfo = getBankService().queryMessageInfo(appId);
		List<Map<String, String>> bankSummaryInfo = getBankService().selectD1Info(appId);
		if(!bankSummaryInfo.isEmpty()){
			if(bankSummaryInfo.size()>0){
				if (bankSummaryInfo != null) {
					String jsonObject=null;
					try {
						Gson gson = new Gson();
						jsonObject=gson.toJson(bankSummaryInfo);
					} catch (Exception e) {
						e.printStackTrace();
					} 
					context.setData("success", true);
					context.setData("queryCreditInfo", jsonObject);
				}
			}
		}
	}
	
	/**
	 * 循环额度下分账户
	 * @param context
	 * @throws Exception
	 */
		public void queryR4Info(Context context)  {
			String appId = (String) context.getData("appId");
			//	Map<String, String> bankSummaryInfo = getBankService().queryMessageInfo(appId);
			List<Map<String, String>> bankSummaryInfo = getBankService().selectR4Info(appId);
			if(!bankSummaryInfo.isEmpty()){
				if(bankSummaryInfo.size()>0){
					if (bankSummaryInfo != null) {
						String jsonObject=null;
						try {
							Gson gson = new Gson();
							jsonObject=gson.toJson(bankSummaryInfo);
						} catch (Exception e) {
							e.printStackTrace();
						} 
						context.setData("success", true);
						context.setData("queryCreditInfo", jsonObject);
					}
				}
			}
		}
	/**
	 * 循环贷账户
	 * @param context
	 * @throws Exception
	 */
		public void queryR2Info(Context context)  {
			String appId = (String) context.getData("appId");
			//	Map<String, String> bankSummaryInfo = getBankService().queryMessageInfo(appId);
			List<Map<String, String>> bankSummaryInfo = getBankService().selectR2Info(appId);
			if(!bankSummaryInfo.isEmpty()){
				if(bankSummaryInfo.size()>0){
					if (bankSummaryInfo != null) {
						String jsonObject=null;
						try {
							Gson gson = new Gson();
							jsonObject=gson.toJson(bankSummaryInfo);
						} catch (Exception e) {
							e.printStackTrace();
						} 
						context.setData("success", true);
						context.setData("queryCreditInfo", jsonObject);
					}
				}
			}
		}
	/**
	 * 贷记卡账户
	 * @param context
	 * @throws Exception
	 */
		public void queryR1Info(Context context)  {
			String appId = (String) context.getData("appId");
			//	Map<String, String> bankSummaryInfo = getBankService().queryMessageInfo(appId);
			List<Map<String, String>> bankSummaryInfo = getBankService().selectR1Info(appId);
			if(!bankSummaryInfo.isEmpty()){
				if(bankSummaryInfo.size()>0){
					if (bankSummaryInfo != null) {
						String jsonObject=null;
						try {
							Gson gson = new Gson();
							jsonObject=gson.toJson(bankSummaryInfo);
						} catch (Exception e) {
							e.printStackTrace();
						} 
						context.setData("success", true);
						context.setData("queryCreditInfo", jsonObject);
					}
				}
			}
		}
	/**
	 * 准贷记卡账户
	 * @param context
	 * @throws Exception
	 */
		public void queryR3Info(Context context)  {
			String appId = (String) context.getData("appId");
			//	Map<String, String> bankSummaryInfo = getBankService().queryMessageInfo(appId);
			List<Map<String, String>> bankSummaryInfo = getBankService().selectR3Info(appId);
			if(!bankSummaryInfo.isEmpty()){
				if(bankSummaryInfo.size()>0){
					if (bankSummaryInfo != null) {
						String jsonObject=null;
						try {
							Gson gson = new Gson();
							jsonObject=gson.toJson(bankSummaryInfo);
						} catch (Exception e) {
							e.printStackTrace();
						} 
						context.setData("success", true);
						context.setData("queryCreditInfo", jsonObject);
					}
				}
			}
		}
		
/**
 * 最近一次月度表现信息段
 * @param context
 * @throws Exception
 */
	public void queryNearestMInfo(Context context)  {
		String appId = (String) context.getData("appId");
		//	Map<String, String> bankSummaryInfo = getBankService().queryMessageInfo(appId);
		List<Map<String, String>> bankSummaryInfo = getBankService().selectNearestMInfo(appId);
		if(!bankSummaryInfo.isEmpty()){
			if(bankSummaryInfo.size()>0){
				if (bankSummaryInfo != null) {
					String jsonObject=null;
					try {
						Gson gson = new Gson();
						jsonObject=gson.toJson(bankSummaryInfo);
					} catch (Exception e) {
						e.printStackTrace();
					} 
					context.setData("success", true);
					context.setData("queryCreditInfo", jsonObject);
				}
			}
		}
	}
/**
 * 最近24个月还款状态信息段
 * @param context
 * @throws Exception
 */
	public void queryLatest24MonthInfo(Context context)  {
		String appId = (String) context.getData("appId");
		//	Map<String, String> bankSummaryInfo = getBankService().queryMessageInfo(appId);
		List<Map<String, String>> bankSummaryInfo = getBankService().selectLatest24MonthInfo(appId);
		if(!bankSummaryInfo.isEmpty()){
			if(bankSummaryInfo.size()>0){
				if (bankSummaryInfo != null) {
					String jsonObject=null;
					try {
						Gson gson = new Gson();
						jsonObject=gson.toJson(bankSummaryInfo);
					} catch (Exception e) {
						e.printStackTrace();
					} 
					context.setData("success", true);
					context.setData("queryCreditInfo", jsonObject);
				}
			}
		}
	}
/**
 * 最近5年内的历史表现信息段
 * @param context
 * @throws Exception
 */
	public void queryLatest5YInfo(Context context)  {
		String appId = (String) context.getData("appId");
		//	Map<String, String> bankSummaryInfo = getBankService().queryMessageInfo(appId);
		List<Map<String, String>> bankSummaryInfo = getBankService().selectLatest5YInfo(appId);
		if(!bankSummaryInfo.isEmpty()){
			if(bankSummaryInfo.size()>0){
				if (bankSummaryInfo != null) {
					String jsonObject=null;
					try {
						Gson gson = new Gson();
						jsonObject=gson.toJson(bankSummaryInfo);
					} catch (Exception e) {
						e.printStackTrace();
					} 
					context.setData("success", true);
					context.setData("queryCreditInfo", jsonObject);
				}
			}
		}
	}
	
	/**
	 * 特殊事件说明信息
	 * @param context
	 * @throws Exception
	 */
		public void querySpecialEventInfo(Context context)  {
			String appId = (String) context.getData("appId");
			//	Map<String, String> bankSummaryInfo = getBankService().queryMessageInfo(appId);
			List<Map<String, String>> bankSummaryInfo = getBankService().selectSpecialEventInfo(appId);
			if(!bankSummaryInfo.isEmpty()){
				if(bankSummaryInfo.size()>0){
					if (bankSummaryInfo != null) {
						String jsonObject=null;
						try {
							Gson gson = new Gson();
							jsonObject=gson.toJson(bankSummaryInfo);
						} catch (Exception e) {
							e.printStackTrace();
						} 
						context.setData("success", true);
						context.setData("queryCreditInfo", jsonObject);
					}
				}
			}
		}
		
/**
 * 标注及声明信息
 * @param context
 * @throws Exception
 */
	public void queryStateInfo(Context context)  {
		String appId = (String) context.getData("appId");
		String table = (String) context.getData("table");
		if(table == null){
			context.setData("success", false);
			context.setData("queryCreditInfo", null);
			return;
		}
		Map<String,String> map=new HashMap<String, String>();
		map.put("appId", appId);
		map.put("table", table);
		List<Map<String, String>> bankSummaryInfo = getBankService().selectStateInfo(map);
		if(!bankSummaryInfo.isEmpty()){
			if(bankSummaryInfo.size()>0){
				if (bankSummaryInfo != null) {
					String jsonObject=null;
					try {
						Gson gson = new Gson();
						jsonObject=gson.toJson(bankSummaryInfo);
					} catch (Exception e) {
						e.printStackTrace();
					} 
					context.setData("success", true);
					context.setData("queryCreditInfo", jsonObject);
				}
			}
		}
	}
	
/**
 * 大额专项分期信息
 * @param context
 * @throws Exception
 */
	public void queryDezxInfo(Context context)  {
		String appId = (String) context.getData("appId");
		//	Map<String, String> bankSummaryInfo = getBankService().queryMessageInfo(appId);
		List<Map<String, String>> bankSummaryInfo = getBankService().selectDezxInfo(appId);
		if(!bankSummaryInfo.isEmpty()){
			if(bankSummaryInfo.size()>0){
				if (bankSummaryInfo != null) {
					String jsonObject=null;
					try {
						Gson gson = new Gson();
						jsonObject=gson.toJson(bankSummaryInfo);
					} catch (Exception e) {
						e.printStackTrace();
					} 
					context.setData("success", true);
					context.setData("queryCreditInfo", jsonObject);
				}
			}
		}
	}	
	
	
/**
 * 相关还款责任信息-有相关还款责任的个人借款
 * @param context
 * @throws Exception
 */
	public void queryDezx4PersonInfo(Context context)  {
		String appId = (String) context.getData("appId");
		//	Map<String, String> bankSummaryInfo = getBankService().queryMessageInfo(appId);
		List<Map<String, String>> bankSummaryInfo = getBankService().selectDezx4PersonInfo(appId);
		if(!bankSummaryInfo.isEmpty()){
			if(bankSummaryInfo.size()>0){
				if (bankSummaryInfo != null) {
					String jsonObject=null;
					try {
						Gson gson = new Gson();
						jsonObject=gson.toJson(bankSummaryInfo);
					} catch (Exception e) {
						e.printStackTrace();
					} 
					context.setData("success", true);
					context.setData("queryCreditInfo", jsonObject);
				}
			}
		}
	}	
	
/**
 * 相关还款责任信息-有相关还款责任的企业借款
 * @param context
 * @throws Exception
 */
	public void queryDezx4GroupInfo(Context context)  {
		String appId = (String) context.getData("appId");
		//	Map<String, String> bankSummaryInfo = getBankService().queryMessageInfo(appId);
		List<Map<String, String>> bankSummaryInfo = getBankService().selectDezx4GroupInfo(appId);
		if(!bankSummaryInfo.isEmpty()){
			if(bankSummaryInfo.size()>0){
				if (bankSummaryInfo != null) {
					String jsonObject=null;
					try {
						Gson gson = new Gson();
						jsonObject=gson.toJson(bankSummaryInfo);
					} catch (Exception e) {
						e.printStackTrace();
					} 
					context.setData("success", true);
					context.setData("queryCreditInfo", jsonObject);
				}
			}
		}
	}	
	
/**
 * 授信协议信息
 * @param context
 * @throws Exception
 */
	public void queryPcaInfo(Context context)  {
		String appId = (String) context.getData("appId");
		//	Map<String, String> bankSummaryInfo = getBankService().queryMessageInfo(appId);
		List<Map<String, String>> bankSummaryInfo = getBankService().selectPcaInfo(appId);
		if(!bankSummaryInfo.isEmpty()){
			if(bankSummaryInfo.size()>0){
				if (bankSummaryInfo != null) {
					String jsonObject=null;
					try {
						Gson gson = new Gson();
						jsonObject=gson.toJson(bankSummaryInfo);
					} catch (Exception e) {
						e.printStackTrace();
					} 
					context.setData("success", true);
					context.setData("queryCreditInfo", jsonObject);
				}
			}
		}
	}	
	
/**
 * 后付费记录
 * @param context
 * @throws Exception
 */
	public void queryTelpaymentInfo(Context context)  {
		String appId = (String) context.getData("appId");
		//	Map<String, String> bankSummaryInfo = getBankService().queryMessageInfo(appId);
		List<Map<String, String>> bankSummaryInfo = getBankService().selectPaymentInfo(appId);
		if(!bankSummaryInfo.isEmpty()){
			if(bankSummaryInfo.size()>0){
				if (bankSummaryInfo != null) {
					String jsonObject=null;
					try {
						Gson gson = new Gson();
						jsonObject=gson.toJson(bankSummaryInfo);
					} catch (Exception e) {
						e.printStackTrace();
					} 
					context.setData("success", true);
					context.setData("queryCreditInfo", jsonObject);
				}
			}
		}
	}
	
/**
 * 欠税记录
 * @param context
 * @throws Exception
 */
	public void queryTaxArrearInfo(Context context)  {
		String appId = (String) context.getData("appId");
		//	Map<String, String> bankSummaryInfo = getBankService().queryMessageInfo(appId);
		List<Map<String, String>> bankSummaryInfo = getBankService().selectTaxArrearInfo(appId);
		if(!bankSummaryInfo.isEmpty()){
			if(bankSummaryInfo.size()>0){
				if (bankSummaryInfo != null) {
					String jsonObject=null;
					try {
						Gson gson = new Gson();
						jsonObject=gson.toJson(bankSummaryInfo);
					} catch (Exception e) {
						e.printStackTrace();
					} 
					context.setData("success", true);
					context.setData("queryCreditInfo", jsonObject);
				}
			}
		}
	}
	
/**
 * 民事判决记录
 * @param context
 * @throws Exception
 */
	public void queryCivJudgeInfo(Context context)  {
		String appId = (String) context.getData("appId");
		//	Map<String, String> bankSummaryInfo = getBankService().queryMessageInfo(appId);
		List<Map<String, String>> bankSummaryInfo = getBankService().selectCivJudgeInfo(appId);
		if(!bankSummaryInfo.isEmpty()){
			if(bankSummaryInfo.size()>0){
				if (bankSummaryInfo != null) {
					String jsonObject=null;
					try {
						Gson gson = new Gson();
						jsonObject=gson.toJson(bankSummaryInfo);
					} catch (Exception e) {
						e.printStackTrace();
					} 
					context.setData("success", true);
					context.setData("queryCreditInfo", jsonObject);
				}
			}
		}
	}
/**
 * 强制执行记录
 * @param context
 * @throws Exception
 */
	public void queryForceexeInfo(Context context)  {
		String appId = (String) context.getData("appId");
		//	Map<String, String> bankSummaryInfo = getBankService().queryMessageInfo(appId);
		List<Map<String, String>> bankSummaryInfo = getBankService().selectForceexeInfo(appId);
		if(!bankSummaryInfo.isEmpty()){
			if(bankSummaryInfo.size()>0){
				if (bankSummaryInfo != null) {
					String jsonObject=null;
					try {
						Gson gson = new Gson();
						jsonObject=gson.toJson(bankSummaryInfo);
					} catch (Exception e) {
						e.printStackTrace();
					} 
					context.setData("success", true);
					context.setData("queryCreditInfo", jsonObject);
				}
			}
		}
	}
/**
 * 行政处罚记录
 * @param context
 * @throws Exception
 */
	public void queryAdminPunishInfo(Context context)  {
		String appId = (String) context.getData("appId");
		//	Map<String, String> bankSummaryInfo = getBankService().queryMessageInfo(appId);
		List<Map<String, String>> bankSummaryInfo = getBankService().selectAdminPunishInfo(appId);
		if(!bankSummaryInfo.isEmpty()){
			if(bankSummaryInfo.size()>0){
				if (bankSummaryInfo != null) {
					String jsonObject=null;
					try {
						Gson gson = new Gson();
						jsonObject=gson.toJson(bankSummaryInfo);
					} catch (Exception e) {
						e.printStackTrace();
					} 
					context.setData("success", true);
					context.setData("queryCreditInfo", jsonObject);
				}
			}
		}
	}
/**
 * 住房公积金参缴记录
 * @param context
 * @throws Exception
 */
	public void queryAccfundInfo(Context context)  {
		String appId = (String) context.getData("appId");
		//	Map<String, String> bankSummaryInfo = getBankService().queryMessageInfo(appId);
		List<Map<String, String>> bankSummaryInfo = getBankService().selectAccfundInfo(appId);
		if(!bankSummaryInfo.isEmpty()){
			if(bankSummaryInfo.size()>0){
				if (bankSummaryInfo != null) {
					String jsonObject=null;
					try {
						Gson gson = new Gson();
						jsonObject=gson.toJson(bankSummaryInfo);
					} catch (Exception e) {
						e.printStackTrace();
					} 
					context.setData("success", true);
					context.setData("queryCreditInfo", jsonObject);
				}
			}
		}
	}
/**
 * 低保救助记录
 * @param context
 * @throws Exception
 */
	public void querySalvationInfo(Context context)  {
		String appId = (String) context.getData("appId");
		//	Map<String, String> bankSummaryInfo = getBankService().queryMessageInfo(appId);
		List<Map<String, String>> bankSummaryInfo = getBankService().selectSalvationInfo(appId);
		if(!bankSummaryInfo.isEmpty()){
			if(bankSummaryInfo.size()>0){
				if (bankSummaryInfo != null) {
					String jsonObject=null;
					try {
						Gson gson = new Gson();
						jsonObject=gson.toJson(bankSummaryInfo);
					} catch (Exception e) {
						e.printStackTrace();
					} 
					context.setData("success", true);
					context.setData("queryCreditInfo", jsonObject);
				}
			}
		}
	}
/**
 * 职业资格记录
 * @param context
 * @throws Exception
 */
	public void queryCompetenceInfo(Context context)  {
		String appId = (String) context.getData("appId");
		//	Map<String, String> bankSummaryInfo = getBankService().queryMessageInfo(appId);
		List<Map<String, String>> bankSummaryInfo = getBankService().selectCompetenceInfo(appId);
		if(!bankSummaryInfo.isEmpty()){
			if(bankSummaryInfo.size()>0){
				if (bankSummaryInfo != null) {
					String jsonObject=null;
					try {
						Gson gson = new Gson();
						jsonObject=gson.toJson(bankSummaryInfo);
					} catch (Exception e) {
						e.printStackTrace();
					} 
					context.setData("success", true);
					context.setData("queryCreditInfo", jsonObject);
				}
			}
		}
	}
/**
 * 行政奖励记录
 * @param context
 * @throws Exception
 */
	public void queryAdminAwardInfo(Context context)  {
		String appId = (String) context.getData("appId");
		//	Map<String, String> bankSummaryInfo = getBankService().queryMessageInfo(appId);
		List<Map<String, String>> bankSummaryInfo = getBankService().selectAdminAwardInfo(appId);
		if(!bankSummaryInfo.isEmpty()){
			if(bankSummaryInfo.size()>0){
				if (bankSummaryInfo != null) {
					String jsonObject=null;
					try {
						Gson gson = new Gson();
						jsonObject=gson.toJson(bankSummaryInfo);
					} catch (Exception e) {
						e.printStackTrace();
					} 
					context.setData("success", true);
					context.setData("queryCreditInfo", jsonObject);
				}
			}
		}
	}
/**
 * 查询记录
 * @param context
 * @throws Exception
 */
	public void queryQueryRecordInfo(Context context)  {
		String appId = (String) context.getData("appId");
		//	Map<String, String> bankSummaryInfo = getBankService().queryMessageInfo(appId);
		List<Map<String, String>> bankSummaryInfo = getBankService().selectQueryRecordInfo(appId);
		if(!bankSummaryInfo.isEmpty()){
			if(bankSummaryInfo.size()>0){
				if (bankSummaryInfo != null) {
					String jsonObject=null;
					try {
						Gson gson = new Gson();
						jsonObject=gson.toJson(bankSummaryInfo);
					} catch (Exception e) {
						e.printStackTrace();
					} 
					context.setData("success", true);
					context.setData("queryCreditInfo", jsonObject);
				}
			}
		}
	}
/**
 *  资产处置信息	
 * @param context
 * @throws Exception
 */
	public void queryAssetInfo(Context context) throws Exception {
		String appId = (String) context.getData("appId");
		List<Map<String, String>> bankSummaryInfo = getBankService().selectAssetInfo(appId);
	//	Map<String, String> json = bankSummaryInfo.get(0);
		
		if (bankSummaryInfo != null) {
			String jsonObject=null;
			try {
			//	jsonObject = MAPPER.writeValueAsString(bankSummaryInfo); 
				Gson gson = new Gson();
				jsonObject=gson.toJson(bankSummaryInfo);
			} catch (Exception e) {
				e.printStackTrace();
			} 
			context.setData("success", true);
			context.setData("queryAssetInfo", jsonObject);
			return;
		}
		
		context.setData("success", false);
	}
/**
 * 保证人代偿信息	
 * @param context
 * @throws Exception
 */
	
	public void queryAssureInfo(Context context) throws Exception {
		String appId = (String) context.getData("appId");
		List<Map<String, String>> bankSummaryInfo = getBankService().selectAssureInfo(appId);
		//	Map<String, String> json = bankSummaryInfo.get(0);
		
		if (bankSummaryInfo != null) {
			String jsonObject=null;
			try {
			//	jsonObject = MAPPER.writeValueAsString(bankSummaryInfo);
				Gson gson = new Gson();
				jsonObject=gson.toJson(bankSummaryInfo);	
			} catch (Exception e) {
				e.printStackTrace();
			} 
			context.setData("success", true);
			context.setData("queryAssureInfo", jsonObject);
			return;
		}
		
		context.setData("success", false);
	}
	
/**
 * 	欠税记录
 * @param context
 * @throws Exception
 */
	public void queryArrearageInfo(Context context) throws Exception {
		String appId = (String) context.getData("appId");
		List<Map<String, String>> bankSummaryInfo = getBankService().selectArrearageInfo(appId);
		
		if (bankSummaryInfo != null) {
			String jsonObject=null;
			try {
			//	jsonObject = MAPPER.writeValueAsString(bankSummaryInfo);
				Gson gson = new Gson();
				jsonObject=gson.toJson(bankSummaryInfo);
			} catch (Exception e) {
				e.printStackTrace();
			} 
			context.setData("success", true);
			context.setData("arrearageInfo", jsonObject);
			return;
		}
		
		context.setData("success", false);
	}
	
/**
 * 民事判决记录	
 * @param context
 * @throws Exception
 */
	public void queryJudgmentInfo(Context context) throws Exception {
		String appId = (String) context.getData("appId");
		List<Map<String, String>> bankSummaryInfo = getBankService().selectJudgmentInfo(appId);
		
		if (bankSummaryInfo != null) {
			String jsonObject=null;
			try {
				//jsonObject = MAPPER.writeValueAsString(bankSummaryInfo);
				Gson gson = new Gson();
				jsonObject=gson.toJson(bankSummaryInfo);
			} catch (Exception e) {
				e.printStackTrace();
			} 
			context.setData("success", true);
			context.setData("judgmentInfo", jsonObject);
			return;
		}
		
		context.setData("success", false);
	}
	
/**
 * 强制执行记录	
 * @param context
 * @throws Exception
 */
	
	public void queryForceInfo(Context context) throws Exception {
		String appId = (String) context.getData("appId");
		List<Map<String, String>> bankSummaryInfo = getBankService().selectForceInfoInfo(appId);
		
		if (bankSummaryInfo != null) {
			String jsonObject=null;
			try {
			//	jsonObject = MAPPER.writeValueAsString(bankSummaryInfo);
				Gson gson = new Gson();
				jsonObject=gson.toJson(bankSummaryInfo);	
			} catch (Exception e) {
				e.printStackTrace();
			} 
			context.setData("success", true);
			context.setData("forceInfo", jsonObject);
			return;
		}
		
		context.setData("success", false);
	}
	
	
/**
 * 行政处罚记录	
 * @param context
 * @throws Exception
 */
	public void queryPenaltyInfo(Context context) throws Exception {
		String appId = (String) context.getData("appId");
		List<Map<String, String>> bankSummaryInfo = getBankService().selectPenaltyInfo(appId);
		
		if (bankSummaryInfo != null) {
			String jsonObject=null;
			try {
			//	jsonObject = MAPPER.writeValueAsString(bankSummaryInfo);
				Gson gson = new Gson();
				jsonObject=gson.toJson(bankSummaryInfo);		
			} catch (Exception e) {
				e.printStackTrace();
			} 
			context.setData("success", true);
			context.setData("penaltyInfo", jsonObject);
			return;
		}
		
		context.setData("success", false);
	}
	/**
	 * 住房公积金参数记录
	 * @param context
	 * @throws Exception
	 */
	
	public void queryHousingInfo(Context context) throws Exception {
		String appId = (String) context.getData("appId");
		List<Map<String, String>> bankSummaryInfo = getBankService().selectHousingInfo(appId);
		
		if (bankSummaryInfo != null) {
			String jsonObject=null;
			try {
				//jsonObject = MAPPER.writeValueAsString(bankSummaryInfo);
				Gson gson = new Gson();
				jsonObject=gson.toJson(bankSummaryInfo);	
			} catch (Exception e) {
				e.printStackTrace();
			} 
			context.setData("success", true);
			context.setData("housingInfo", jsonObject);
			return;
		}
		
		context.setData("success", false);
	}
/**
 * 养老保险金缴存记录	
 * @param context
 * @throws Exception
 */
	
	public void queryPayAnnuityInfo(Context context) throws Exception {
		String appId = (String) context.getData("appId");
		List<Map<String, String>> bankSummaryInfo = getBankService().selectPayAnnuityInfo(appId);
		
		if (bankSummaryInfo != null) {
			String jsonObject=null;
			try {
			//	jsonObject = MAPPquerySummaryInfoScBzkER.writeValueAsString(bankSummaryInfo);
				Gson gson = new Gson();
				jsonObject=gson.toJson(bankSummaryInfo);	
			} catch (Exception e) {
				e.printStackTrace();
			} 
			context.setData("success", true);
			context.setData("payAnnuityInfo", jsonObject);
			return;
		}
		
		context.setData("success", false);
	}
/**
 * 	养老保险金发放记录
 * @param context
 * @throws Exception
 */
	
	public void queryGrantAnnuityInfo(Context context) throws Exception {
		String appId = (String) context.getData("appId");
		List<Map<String, String>> bankSummaryInfo = getBankService().selectGrantAnnuityInfo(appId);
		
		if (bankSummaryInfo != null) {
			String jsonObject=null;
			try {
			//	jsonObject = MAPPER.writeValueAsString(bankSummaryInfo);
				Gson gson = new Gson();
				jsonObject=gson.toJson(bankSummaryInfo);	
			} catch (Exception e) {
				e.printStackTrace();
			} 
			context.setData("success", true);
			context.setData("grantAnnuityInfo", jsonObject);
			return;
		}
		
		context.setData("success", false);
	}
/**
 * 低保救助记录	
 * @param context
 * @throws Exception
 */
	
	public void queryLowHouseholdInfo(Context context) throws Exception {
		String appId = (String) context.getData("appId");
		List<Map<String, String>> bankSummaryInfo = getBankService().selectLowHouseholdInfo(appId);
		
		if (bankSummaryInfo != null) {
			String jsonObject=null;
			try {
			//	jsonObject = MAPPER.writeValueAsString(bankSummaryInfo);
				Gson gson = new Gson();
				jsonObject=gson.toJson(bankSummaryInfo);
			} catch (Exception e) {
				e.printStackTrace();
			} 
			context.setData("success", true);
			context.setData("lowHouseholdInfo", jsonObject);
			return;
		}
		
		context.setData("success", false);
	}
/**
 * 职业资格记录	
 * @param context
 * @throws Exception
 */
	
	public void queryOccupationInfo(Context context) throws Exception {
		String appId = (String) context.getData("appId");
		List<Map<String, String>> bankSummaryInfo = getBankService().selectOccupationInfo(appId);
		
		if (bankSummaryInfo != null) {
			String jsonObject=null;
			try {
		//		jsonObject = MAPPER.writeValueAsString(bankSummaryInfo);
				Gson gson = new Gson();
				jsonObject=gson.toJson(bankSummaryInfo);	
			} catch (Exception e) {
				e.printStackTrace();
			} 
			context.setData("success", true);
			context.setData("occupationInfo", jsonObject);
			return;
		}
		
		context.setData("success", false);
	}
/**
 * 行政奖励记录	
 * @param context
 * @throws Exception
 */
	
	public void queryAwardInfo(Context context) throws Exception {
		String appId = (String) context.getData("appId");
		List<Map<String, String>> bankSummaryInfo = getBankService().selectAwardInfo(appId);
		if (bankSummaryInfo != null) {
			String jsonObject=null;
			try {
			//	jsonObject = MAPPER.writeValueAsString(bankSummaryInfo);
				Gson gson = new Gson();
				jsonObject=gson.toJson(bankSummaryInfo);	
			} catch (Exception e) {
				e.printStackTrace();
			} 
			context.setData("success", true);
			context.setData("awardInfo", jsonObject);
			return;
		}
		
		context.setData("success", false);
	}
/**
 * 车辆交易和抵押记录	
 * @param context
 * @throws Exception
 */
	
	public void queryVehicleInfo(Context context) throws Exception {
		String appId = (String) context.getData("appId");
		List<Map<String, String>> bankSummaryInfo = getBankService().selectVehicleInfo(appId);
		if (bankSummaryInfo != null) {
			String jsonObject=null;
			try {
				//jsonObject = MAPPER.writeValueAsString(bankSummaryInfo);
				Gson gson = new Gson();
				jsonObject=gson.toJson(bankSummaryInfo);	
			} catch (Exception e) {
				e.printStackTrace();
			} 
			context.setData("success", true);
			context.setData("vehicleInfo", jsonObject);
			return;
		}
		
		context.setData("success", false);
	}
	
/**
 * 电信缴费记录	
 * @param context
 * @throws Exception
 */
	public void queryTelecomInfo(Context context) throws Exception {
		String appId = (String) context.getData("appId");
		List<Map<String, String>> bankSummaryInfo = getBankService().selectTelecomInfo(appId);
		
		if (bankSummaryInfo != null) {
			String jsonObject=null;
			try {
				//jsonObject = MAPPER.writeValueAsString(bankSummaryInfo);
				Gson gson = new Gson();
				jsonObject=gson.toJson(bankSummaryInfo);
			} catch (Exception e) {
				e.printStackTrace();
			} 
			context.setData("success", true);
			context.setData("telecomInfo", jsonObject);
			return;
		}
		
		context.setData("success", false);
	}
/**
 *本人声明	
 * @param context
 * @throws Exception
 */
	
	public void queryAnnounceInfo(Context context) throws Exception {
		String appId = (String) context.getData("appId");
		List<Map<String, String>> bankSummaryInfo = getBankService().selectAnnounceInfo(appId);
		
		if (bankSummaryInfo != null) {
			String jsonObject=null;
			try {
			//	jsonObject = MAPPER.writeValueAsString(bankSummaryInfo);
				Gson gson = new Gson();
				jsonObject=gson.toJson(bankSummaryInfo);	
			} catch (Exception e) {
				e.printStackTrace();
			} 
			context.setData("success", true);
			context.setData("announceInfo", jsonObject);
			return;
		}
		
		context.setData("success", false);
	}
	
/**
 * 异议标注	
 * @param context
 * @throws Exception
 */
	public void queryDissentInfo(Context context) throws Exception {
		String appId = (String) context.getData("appId");
		List<Map<String, String>> bankSummaryInfo = getBankService().selectDissentInfo(appId);
		
		if (bankSummaryInfo != null) {
			String jsonObject=null;
			try {
			//	jsonObject = MAPPER.writeValueAsString(bankSummaryInfo);
				Gson gson = new Gson();
				jsonObject=gson.toJson(bankSummaryInfo);
			} catch (Exception e) {
				e.printStackTrace();
			} 
			context.setData("success", true);
			context.setData("dissentInfo", jsonObject);
			return;
		}
		
		context.setData("success", false);
	}
	
	
/**
 * 信贷审批查询记录	
 * @param context
 * @throws Exception
 */
	
	
	public void queryReCreditInfo(Context context) throws Exception {
		String appId = (String) context.getData("appId");
		List<Map<String, String>> bankSummaryInfo = getBankService().selectReCreditInfo(appId);
		
		if (bankSummaryInfo != null) {
			String jsonObject=null;
			try {
			//	jsonObject = MAPPER.writeValueAsString(bankSummaryInfo);
				Gson gson = new Gson();
				jsonObject=gson.toJson(bankSummaryInfo);	
			} catch (Exception e) {
				e.printStackTrace();
			} 
			context.setData("success", true);
			context.setData("reCreditInfo", jsonObject);
			return;
		}
		
		context.setData("success", false);
	}
	
	/**
	 * 我行个人贷款情况
	 * @param context
	 * @throws Exception
	 */
		public void queryPersonaldk(Context context) throws Exception {
			String appId = (String) context.getData("appId");
			List<Map<String, String>> Personaldk = getBankService().selectPersonaldk(appId);
			if (Personaldk != null) {
				String jsonObject=null;
				try {
				//	jsonObject = MAPPER.writeValueAsString(bankSummaryInfo);
					Gson gson = new Gson();
					jsonObject=gson.toJson(Personaldk);	
				} catch (Exception e) {
					e.printStackTrace();
				} 
				context.setData("success", true);
				context.setData("Personaldk", jsonObject);
				return;
			}
			
			context.setData("success", false);
		}
	
	/**
	 * 查询二代人行摘要信息_征信审批标准卡
	 * 
	 * @param context
	 * @throws Exception
	 */
	public void querySummaryInfoZxSpBzk(Context context) throws Exception {
		String appId = (String) context.getData("appId");
		String str = "";
		String str_case = "";
		String str_credit_status = "";
		String str_err1 = "欠税记录";
		String str_err2 = "民事判决记录";
		String str_err3 = "强制执行记录";
	//	String str_err4 = "养老保险金缴存记录";
	//	String str_err5 = "养老保险金发放记录";
		String str_err6 = "行政处罚记录";
		
		Assert.notNull(appId, "请求申请件编号为空!");
		
		if (logger.isDebugEnabled()) {
			logger.debug("查询申请件[" + appId + "]人行摘要信息");
		}
		//人行摘要信息-标准卡征信审批
		Map<String, String> pbocSummaryInfoZxSpBzk = getBankService().querySummaryInfoZxSpBzk(appId);
		logger.info("pbocSummaryInfoZxSpBzk返回："+JSONObject.fromObject(pbocSummaryInfoZxSpBzk));
		//人行摘要信息-标准卡征信审批/易达金-工作单位、单位地址、更新时间第二条数据
		List<Map<String, String>> pbocSummaryInfoZxSpBzk2 = getBankService().querySummaryInfoZxSpBzkYdj2(appId);
		
		if(pbocSummaryInfoZxSpBzk!=null){
		//欠费记录是否有值
		String str_status_err1 = getBankService().querySummaryInfoErr1(appId);
		if (str_status_err1 != null){
			str += str_err1+",";
		}
		//民事判决记录是否有值
		String str_status_err2 = getBankService().querySummaryInfoErr2(appId);
		if (str_status_err2 != null){
			str += str_err2+",";
		}
		//强制执行记录是否有值
		String str_status_err3 = getBankService().querySummaryInfoErr3(appId);
		if (str_status_err3 != null){
			str += str_err3+",";
		}
		//养老保险金缴存记录是否有值
		/*String str_status_err4 = getBankService().querySummaryInfoErr4(appId);
		if (str_status_err4 != null){
			str += str_err4+",";
		}*/
		//养老保险金发放记录是否有值
		/*String str_status_err5 = getBankService().querySummaryInfoErr5(appId);
		if (str_status_err5 != null){
			str += str_err5+",";
		}*/
		//行政处罚记录是否有值
		String str_status_err6 = getBankService().querySummaryInfoErr6(appId);
		if (str_status_err6 != null){
			str += str_err6+",";
		}
		if (str != ""){
			str = str.substring(0, str.length()-1);
		}
		
		//贷款当前逾期总额
		String str_amt1 = getBankService().querySummaryInfoAmt1(appId);
		//信用卡当前逾期总额
		String str_amt2 = getBankService().querySummaryInfoAmt2(appId);
		//是否列入失信被执行人名单
		List<Map<String,Object>> caseStatusMapList = getBankService().querySummaryInfoCase(appId);
		
		if(caseStatusMapList!=null&&caseStatusMapList.size()>0){
			String caseStatus="";
			for (Map<String, Object> caseMap : caseStatusMapList) {
				if(caseMap.get("caseStatus")!=null){
					caseStatus+=caseMap.get("caseStatus").toString();
				}
			}
			if (caseStatus.equals("")){
				str_case = "否";
			}else {
				str_case = "是";
			}
		}else{
			 str_case = "否";
		}
		//信用卡状态异常
		List<String> str_credit = getBankService().querySummaryInfoCredit(appId);
		if (!str_credit.isEmpty()){
			for(int i=0;i<str_credit.size();i++){
				String strCredit=str_credit.get(i);
				if(strCredit!=null){
					strCredit=strCredit.substring(strCredit.indexOf("|")+1,strCredit.length());
					if(i!=str_credit.size()-1){
							str_credit_status += strCredit+",";
					}else{
							str_credit_status +=strCredit;
					}
				}
			}
	    }
		if(str!=null){
		pbocSummaryInfoZxSpBzk.put("common_err_status", str);
		}
		if(str_case!=null){
		pbocSummaryInfoZxSpBzk.put("case_status", str_case);
		}
		if(str_credit_status!=null){
		pbocSummaryInfoZxSpBzk.put("loan_or_credit_acct_status", str_credit_status);
		}
		if(str_amt1!=null){
		pbocSummaryInfoZxSpBzk.put("opas_pboc_public_adminpunish1", str_amt1);
		}
		if(str_amt2!=null){
		pbocSummaryInfoZxSpBzk.put("opas_pboc_public_adminpunish2", str_amt2);
		}
		//贷款账户数
		String str_loan_accounts_num = getBankService().queryLoanAccountsNum(appId);
		if(str_loan_accounts_num!=null){
			pbocSummaryInfoZxSpBzk.put("loan_accounts_count", str_loan_accounts_num);
		}
		//贷款账户数
		String str_loan_accounts_num1 = getBankService().queryLoanAccountsNum1(appId);
		if(str_loan_accounts_num1!=null){
			pbocSummaryInfoZxSpBzk.put("loan_accounts_count1", str_loan_accounts_num1);
		}
		}
		
		//判断取两条的LIST是否有数据
		if (!pbocSummaryInfoZxSpBzk2.isEmpty()){
			//取两条的LIST中，第二条是否有数据
			if (pbocSummaryInfoZxSpBzk2.size() == 1 && pbocSummaryInfoZxSpBzk2.get(0) != null) {
				JSONObject jsonObject1 = JSONObject.fromObject(pbocSummaryInfoZxSpBzk);
				JSONObject jsonObject2 = JSONObject.fromObject(pbocSummaryInfoZxSpBzk2.get(0));
				context.setData("success", true);
				String json1 = jsonObject1.toString();
				String json2 = jsonObject2.toString();
				String json =  json1.replace('}', ',') + json2.substring(1,json2.length());
				context.setData("applyPbocSummary",json);
				return;
			}else {
				logger.info("pbocSummaryInfoZxSpBzk2-else");
				JSONObject jsonObject1 = JSONObject.fromObject(pbocSummaryInfoZxSpBzk);
				context.setData("success", true);	
				logger.info("context-sucess-true");
				logger.info("jsonObject1:"+jsonObject1);
				String json1 = jsonObject1.toString();
				logger.info("json1："+json1);
				String json =  json1;
				context.setData("applyPbocSummary",json);
				return;
			}
		}//取两条的LIST中，只有一条时
			else {
				JSONObject jsonObject1 = JSONObject.fromObject(pbocSummaryInfoZxSpBzk);
				context.setData("success", true);	
				String json1 = jsonObject1.toString();
				String json =  json1;
				context.setData("applyPbocSummary",json);
				return;
			}
		//context.setData("success", false);
	}
	/**
	 * 查询人行摘要信息_易达金
	 * 
	 * @param context
	 * @throws Exception
	 */
	public void querySummaryInfoYdj(Context context) throws Exception {
		String appId = (String) context.getData("appId");
		String str = "";
		String str_case = "";
		String str_sort5 = "";
		String str_credit_status = "";
		String str_rec_seq = "";
		String str_err1 = "欠税记录";
		String str_err2 = "民事判决记录";
		String str_err3 = "强制执行记录";
		String str_err6 = "行政处罚记录";
		
		Assert.notNull(appId, "请求申请件编号为空!");
		
		if (logger.isDebugEnabled()) {
			logger.debug("查询申请件[" + appId + "]人行摘要信息");
		}
		//人行摘要信息-标准卡征信审批
		Map<String, String> pbocSummaryInfoYdj = getBankService().querySummaryInfoYdj(appId);
		logger.info("pbocSummaryInfoYdj返回："+JSONObject.fromObject(pbocSummaryInfoYdj));
		//人行摘要信息-标准卡征信审批/易达金-工作单位、更新时间第二条数据
		List<Map<String, String>> pbocSummaryInfoYdj2 = getBankService().querySummaryInfoZxSpBzkYdj2(appId);
		if(pbocSummaryInfoYdj!=null){
		//欠费记录是否有值
		String str_status_err1 = getBankService().querySummaryInfoErr1(appId);
		if (str_status_err1 != null){
			str += str_err1+",";
		}
		//民事判决记录是否有值
		String str_status_err2 = getBankService().querySummaryInfoErr2(appId);
		if (str_status_err2 != null){
			str += str_err2+",";
		}
		//强制执行记录是否有值
		String str_status_err3 = getBankService().querySummaryInfoErr3(appId);
		if (str_status_err3 != null){
			str += str_err3+",";
		}
		
		//行政处罚记录是否有值
		String str_status_err6 = getBankService().querySummaryInfoErr6(appId);
		if (str_status_err6 != null){
			str += str_err6+",";
		}
		if (str != ""){
			str = str.substring(0, str.length()-1);
		}
		//贷款当前逾期总额
		String str_amt1 = getBankService().querySummaryInfoAmt1(appId);
		//信用卡当前逾期总额
		String str_amt2 = getBankService().querySummaryInfoAmt2(appId);
		//是否列入失信被执行人名单
        List<Map<String,Object>> caseStatusMapList = getBankService().querySummaryInfoCase(appId);
		if(caseStatusMapList!=null&&caseStatusMapList.size()>0){
			String caseStatus="";
			for (Map<String, Object> caseMap : caseStatusMapList) {
				if(caseMap.get("caseStatus")!=null){
					caseStatus+=caseMap.get("caseStatus").toString();
				}
			}
			if (caseStatus.equals("")){
				str_case = "否";
			}else {
				str_case = "是";
			}
		}else{
			 str_case = "否";
		}
		//信用卡状态异常
		List<String> str_credit = getBankService().querySummaryInfoCredit(appId);
		if (!str_credit.isEmpty()){
			for(int i=0;i<str_credit.size();i++){
				String strCredit=str_credit.get(i);
				if(strCredit!=null){
					strCredit=strCredit.substring(strCredit.indexOf("|")+1,strCredit.length());
					if(i!=str_credit.size()-1){
						str_credit_status += strCredit+",";
					}else{
						str_credit_status +=strCredit;
					}
				}
			}
		}
		//贷款异常情况 
		List<String> str_credit1 = getBankService().querySummaryInfoRecSeq(appId);
		if (!str_credit1.isEmpty()){
			for(int i=0;i<str_credit1.size();i++){
				String strCredit=str_credit1.get(i);
				if(strCredit!=null){
					strCredit=strCredit.substring(strCredit.indexOf("|")+1,strCredit.length());
					if(i!=str_credit1.size()-1){
						str_rec_seq += strCredit+",";
					}else{
						str_rec_seq +=strCredit;
					}
				}
			}
		}
		/*List<String> str_seq = getBankService().querySummaryInfoRecSeq(appId);
			if (str_seq != null){
			for(int i=0;i<str_seq.size();i++){
				if(i!=str_seq.size()-1){
					str_rec_seq += str_seq.get(i)+",";
				}else{
					str_rec_seq += str_seq.get(i);
				}
			}
		}*/
		//担保信息异常情况
		String str_loan_sort5 = getBankService().querySummaryInfoLoanSort5(appId);
		if (str_loan_sort5 == null){
			str_sort5 = "否";
		}else if((str_loan_sort5 != null)){
			str_sort5 = "是";
		}
		//到期未结清贷款笔数
		String str_expire_loans_num = getBankService().querySummaryInfoExpireLoansNum(appId);
		//到期未结清贷款笔数
		String str_about_expire_loans_num = getBankService().querySummaryInfoAboutExpireLoansNum(appId);
		if(str!=null){
		pbocSummaryInfoYdj.put("common_err_status", str);
		}
		if(str_case!=null){
		pbocSummaryInfoYdj.put("case_status", str_case);
		}
		if(str_credit_status!=null){
		pbocSummaryInfoYdj.put("loan_or_credit_acct_status", str_credit_status);
		}
		if(str_rec_seq!=null){
		pbocSummaryInfoYdj.put("loan_err_no", str_rec_seq);
		}
		if(str_sort5!=null){
		pbocSummaryInfoYdj.put("guar_err_status", str_sort5);
		}
		if(str_expire_loans_num!=null){
		pbocSummaryInfoYdj.put("expire_unclear_count", str_expire_loans_num);
		}
		if(str_about_expire_loans_num!=null){
		pbocSummaryInfoYdj.put("about_expire_loan_count", str_about_expire_loans_num);
		}
		if(str_amt1!=null){
		pbocSummaryInfoYdj.put("opas_pboc_public_adminpunish1", str_amt1);
		}
		if(str_amt2!=null){
		pbocSummaryInfoYdj.put("opas_pboc_public_adminpunish2", str_amt2);
		}
		//贷款账户数
		String str_loan_accounts_num = getBankService().queryLoanAccountsNum(appId);
		if(str_loan_accounts_num!=null){
			pbocSummaryInfoYdj.put("loan_accounts_count", str_loan_accounts_num);
		}
		//贷款账户数
		String str_loan_accounts_num1 = getBankService().queryLoanAccountsNum1(appId);
		if(str_loan_accounts_num1!=null){
			pbocSummaryInfoYdj.put("loan_accounts_count1", str_loan_accounts_num1);
		}
		}
		
		//判断取两条的LIST是否有值
		if (!pbocSummaryInfoYdj2.isEmpty()){
			//判断取两条的LIST是否有两条数据
			if (pbocSummaryInfoYdj2.size()== 1 && pbocSummaryInfoYdj2.get(0) != null) {
				JSONObject jsonObject1 = JSONObject.fromObject(pbocSummaryInfoYdj);
				JSONObject jsonObject2 = JSONObject.fromObject(pbocSummaryInfoYdj2.get(0));
				context.setData("success", true);
				String json1 = jsonObject1.toString();
				String json2 = jsonObject2.toString();
				String json =  json1.replace('}', ',') + json2.substring(1,json2.length());
				context.setData("applyPbocSummary",json);
				return;
			}else {
				JSONObject jsonObject1 = JSONObject.fromObject(pbocSummaryInfoYdj);
				context.setData("success", true);
				String json1 = jsonObject1.toString();
				String json =  json1;
				context.setData("applyPbocSummary",json);
				return;
			}
		}else {
				JSONObject jsonObject1 = JSONObject.fromObject(pbocSummaryInfoYdj);
				context.setData("success", true);
				String json1 = jsonObject1.toString();
				String json =  json1;
				context.setData("applyPbocSummary",json);
				return;
			}
		//context.setData("success", false);
	}
	
	
	/**
	 * 查询当前登录用户信息
	 * 
	 * @param context
	 * @throws Exception
	 */
	public void queryUserInfo(Context context) throws Exception {
		
		String userCode= context.getData("userCode");
		ApUser user = apUserService.queryApUserByUserCode(userCode);
		// 用户角色查询
				List<ApRole> userRoles = new ArrayList<ApRole>();
				String userId = user.getUserId();
				if(userId != null){
					userRoles = apRoleService.queryUserRoles(userId);
				}
				context.setData("isOutsourcing","0");
				for(ApRole aprole : userRoles){
					if("WBZX".equals(aprole.getRoleCode())){
						context.setData("isOutsourcing","1");
						break;
					}else if("HXCX".equals(aprole.getRoleCode())){
						context.setData("isOutsourcing","1");
						break;
					}
				}
		String username = "";
		if(user != null && user.getUserCode() != null && user.getUserName() != null){
			username = userCode+"-"+user.getUserName();
		}
		context.setData("user",username);
	}
	
	public void queryDezx4Person(Context context)  {
		String appId = (String) context.getData("appId");
		List<Map<String, String>> bankSummaryInfo = getBankService().selectDezx4Person(appId,"1");
		List<Map<String, String>> bankSummaryInfo1 = getBankService().selectDezx4Person(appId,"0");
		if(!bankSummaryInfo.isEmpty()){
			if(bankSummaryInfo.size()>0){
				if (bankSummaryInfo != null) {
					String jsonObject=null;
					try {
						Gson gson = new Gson();
						jsonObject=gson.toJson(bankSummaryInfo);
					} catch (Exception e) {
						e.printStackTrace();
					} 
					
					context.setData("queryCreditInfo", jsonObject);
				}
			}
		}
		if(!bankSummaryInfo1.isEmpty()){
			if(bankSummaryInfo1.size()>0){
				if (bankSummaryInfo1 != null) {
					String jsonObject=null;
					try {
						Gson gson = new Gson();
						jsonObject=gson.toJson(bankSummaryInfo1);
					} catch (Exception e) {
						e.printStackTrace();
					} 
					
					context.setData("queryCreditInfo1", jsonObject);
				}
			}
		}
		context.setData("success", true);
	}	
}
