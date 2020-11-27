package com.huaxia.opas.action.thirdparty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.huateng.neofp.core.Action;
import com.huateng.neofp.core.Context;
import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.thirdparty.Grurantee;
import com.huaxia.opas.domain.thirdparty.LoanCardInfo;
import com.huaxia.opas.domain.thirdparty.last5yearOverdue;
import com.huaxia.opas.service.thirdparty.PBOCService;

import net.sf.json.JSONObject;

/**
 * 第三方-人行
 * 
 * @author zhiguo.li
 *
 */
public class PBOCAction implements Action {

	private static Logger logger = LoggerFactory.getLogger(PBOCAction.class);
	
//	private static final ObjectMapper MAPPER = new ObjectMapper();
	
	@Resource(name = "pbocService")
	private PBOCService pbocService;
	public PBOCService getPbocService() {
		return pbocService;
	}

	public void setPbocService(PBOCService pbocService) {
		this.pbocService = pbocService;
	}

	/**
	 *@Title:querySummaryInfoScBzk
	 *@Description:查询人行摘要信息_审查标准卡
	 *@param context
	 *@throws Exception
	 *@author: gaohui 改
	 *@Date:2017年7月3日上午11:24:05
	 */
	public void querySummaryInfoScBzk(Context context) {
		String appId = (String) context.getData("appId");
		/*Assert.notNull(appId, "请求申请件编号为空!");
		if (logger.isDebugEnabled()) {
			logger.debug("查询申请件[" + appId + "]人行摘要信息");
		}
		Map<String, String> pbocSummaryInfoScBzk = getPbocService().querySummaryInfoScBzk(appId);
		*/
		try{
	   Map<String, Object>pbocSummaryInfoScBzk=pbocService.findListPbocScBzkByAppId(appId);
		
		if (pbocSummaryInfoScBzk != null) {
			JSONObject jsonObject = JSONObject.fromObject(pbocSummaryInfoScBzk);
			context.setData("success", true);
			context.setData("applyPbocSummary", jsonObject.toString());
		}else{
		    context.setData("success", false);
		}
      }catch(Exception e){
    	   context.setData("success", false);
		}
	}
	/**
	 *@Title:findScBzkMyBankLoan
	 *@Description:根据appId，和 “华夏银行” 查出所需的“我行贷款”
	 *@param context
	 *@author: gaohui
	 *@Date:2017年7月3日下午7:21:05
	 */
    public void findScBzkMyBankLoan(Context ctx){
    	Map map=ctx.getDataMap();
		int curNum = 0;
		int curPage = Integer
				.parseInt(String.valueOf(ctx.getDataMap().get("page") == null ? 0 : ctx.getDataMap().get("page")));
		int pageNum = Integer
				.parseInt(String.valueOf(ctx.getDataMap().get("rows") == null ? 0 : ctx.getDataMap().get("rows")));

		if (curPage > 1) {
			curNum = (curPage - 1) * pageNum;
		}

		List<Map<String,Object>> scBzkMyBankLoanList = new ArrayList<Map<String,Object>>();
		int count = 0;
		try {
		    count = pbocService.findScBzkMyBankLoanCount(map);
		    if(count>0){
		    	scBzkMyBankLoanList =pbocService.findScBzkMyBankLoanList(map, curNum, pageNum);
		    }
		    Map<String, Object> dataMap = new HashMap<String, Object>();
			dataMap.put("total", count);
			dataMap.put("rows", scBzkMyBankLoanList);
			ctx.setDataMap(dataMap);
			} catch (Exception e) {
				e.printStackTrace();
			}
	
    }
	/**
	 * 查询人行摘要信息_征信审批标准卡
	 * 
	 * @param context
	 * @throws Exception
	 */
	public void querySummaryInfoZxSpBzk(Context context) throws Exception {
		String appId = (String) context.getData("appId");
		String str = "";
		String str_case = "";
		String str_credit_status = "";
		String str_err1 = "欠费记录";
		String str_err2 = "民事判决记录";
		String str_err3 = "强制执行记录";
	//	String str_err4 = "养老保险金缴存记录";
		String str_err5 = "养老保险金发放记录";
		String str_err6 = "行政处罚记录";
		
		Assert.notNull(appId, "请求申请件编号为空!");
		
		if (logger.isDebugEnabled()) {
			logger.debug("查询申请件[" + appId + "]人行摘要信息");
		}
		//人行摘要信息-标准卡征信审批
		Map<String, String> pbocSummaryInfoZxSpBzk = getPbocService().querySummaryInfoZxSpBzk(appId);
		//人行摘要信息-标准卡征信审批/易达金-工作单位、单位地址、更新时间第二条数据
		List<Map<String, String>> pbocSummaryInfoZxSpBzk2 = getPbocService().querySummaryInfoZxSpBzkYdj2(appId);
		if(pbocSummaryInfoZxSpBzk!=null){
		//欠费记录是否有值
		String str_status_err1 = getPbocService().querySummaryInfoErr1(appId);
		if (str_status_err1 != null){
			str += str_err1+",";
		}
		//民事判决记录是否有值
		String str_status_err2 = getPbocService().querySummaryInfoErr2(appId);
		if (str_status_err2 != null){
			str += str_err2+",";
		}
		//强制执行记录是否有值
		String str_status_err3 = getPbocService().querySummaryInfoErr3(appId);
		if (str_status_err3 != null){
			str += str_err3+",";
		}
		//养老保险金缴存记录是否有值
		/*String str_status_err4 = getPbocService().querySummaryInfoErr4(appId);
		if (str_status_err4 != null){
			str += str_err4+",";
		}*/
		//养老保险金发放记录是否有值
		String str_status_err5 = getPbocService().querySummaryInfoErr5(appId);
		if (str_status_err5 != null){
			str += str_err5+",";
		}
		//行政处罚记录是否有值
		String str_status_err6 = getPbocService().querySummaryInfoErr6(appId);
		if (str_status_err6 != null){
			str += str_err6+",";
		}
		if (str != ""){
			str = str.substring(0, str.length()-1);
		}
		//贷款当前逾期总额
		String str_amt1 = getPbocService().querySummaryInfoAmt1(appId);
		//信用卡当前逾期总额
		String str_amt2 = getPbocService().querySummaryInfoAmt2(appId);
		//是否列入失信被执行人名单
		List<Map<String,Object>> caseStatusMapList = getPbocService().querySummaryInfoCase(appId);
		
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
		List<String> str_credit = getPbocService().querySummaryInfoCredit(appId);
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
		String str_loan_accounts_num = getPbocService().queryLoanAccountsNum(appId);
		if(str_loan_accounts_num!=null){
			pbocSummaryInfoZxSpBzk.put("loan_accounts_count", str_loan_accounts_num);
		}
		}
		
		//判断取两条的LIST是否有数据
		if (!pbocSummaryInfoZxSpBzk2.isEmpty()){
			//取两条的LIST中，第二条是否有数据
			if (pbocSummaryInfoZxSpBzk2.size() >= 2 && pbocSummaryInfoZxSpBzk2.get(1) != null) {
				JSONObject jsonObject1 = JSONObject.fromObject(pbocSummaryInfoZxSpBzk);
				JSONObject jsonObject2 = JSONObject.fromObject(pbocSummaryInfoZxSpBzk2.get(1));
				context.setData("success", true);
				String json1 = jsonObject1.toString();
				String json2 = jsonObject2.toString();
				String json =  json1.replace('}', ',') + json2.substring(1,json2.length());
				context.setData("applyPbocSummary",json);
				return;
			}//取两条的LIST中，只有一条时
			else if (pbocSummaryInfoZxSpBzk2.size() == 1 ){
				JSONObject jsonObject1 = JSONObject.fromObject(pbocSummaryInfoZxSpBzk);
				context.setData("success", true);
				String json1 = jsonObject1.toString();
				String json =  json1;
				context.setData("applyPbocSummary",json);
				return;
			}
		}
		context.setData("success", false);
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
		String str_err1 = "欠费记录";
		String str_err2 = "民事判决记录";
		String str_err3 = "强制执行记录";
		//String str_err4 = "养老保险金缴存记录";
		String str_err5 = "养老保险金发放记录";
		String str_err6 = "行政处罚记录";
		
		Assert.notNull(appId, "请求申请件编号为空!");
		
		if (logger.isDebugEnabled()) {
			logger.debug("查询申请件[" + appId + "]人行摘要信息");
		}
		//人行摘要信息-标准卡征信审批
		Map<String, String> pbocSummaryInfoYdj = getPbocService().querySummaryInfoYdj(appId);
		//人行摘要信息-标准卡征信审批/易达金-工作单位、更新时间第二条数据
		List<Map<String, String>> pbocSummaryInfoYdj2 = getPbocService().querySummaryInfoZxSpBzkYdj2(appId);
		if(pbocSummaryInfoYdj!=null){
		//欠费记录是否有值
		String str_status_err1 = getPbocService().querySummaryInfoErr1(appId);
		if (str_status_err1 != null){
			str += str_err1+",";
		}
		//民事判决记录是否有值
		String str_status_err2 = getPbocService().querySummaryInfoErr2(appId);
		if (str_status_err2 != null){
			str += str_err2+",";
		}
		//强制执行记录是否有值
		String str_status_err3 = getPbocService().querySummaryInfoErr3(appId);
		if (str_status_err3 != null){
			str += str_err3+",";
		}
		//养老保险金缴存记录是否有值
		/*String str_status_err4 = getPbocService().querySummaryInfoErr4(appId);
		if (str_status_err4 != null){
			str += str_err4+",";
		}*/
		//养老保险金发放记录是否有值
		String str_status_err5 = getPbocService().querySummaryInfoErr5(appId);
		if (str_status_err5 != null){
			str += str_err5+",";
		}
		//行政处罚记录是否有值
		String str_status_err6 = getPbocService().querySummaryInfoErr6(appId);
		if (str_status_err6 != null){
			str += str_err6+",";
		}
		if (str != ""){
			str = str.substring(0, str.length()-1);
		}
		//贷款当前逾期总额
		String str_amt1 = getPbocService().querySummaryInfoAmt1(appId);
		//信用卡当前逾期总额
		String str_amt2 = getPbocService().querySummaryInfoAmt2(appId);
		//是否列入失信被执行人名单
        List<Map<String,Object>> caseStatusMapList = getPbocService().querySummaryInfoCase(appId);
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
		List<String> str_credit = getPbocService().querySummaryInfoCredit(appId);
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
		Map<String,String> loanCardMap=new HashMap<String, String>();
		loanCardMap.put("appId", appId);
		loanCardMap.put("messType", "01");
		List<LoanCardInfo> loanCardInfos=pbocService.findLoanDataByMap(loanCardMap);
		//五级分类 1 正常  2 关注 3 次级 4 可疑  5 损失  9 未知
        //“关注、次级、可疑、损失”
		if(!loanCardInfos.isEmpty()){
			int loanNum=1;
			int loanCardInfoSize=loanCardInfos.size();
			for (int i = 0; i <loanCardInfoSize; i++) {
			  if(loanCardInfos.get(i)!=null){
				String accClass5state=loanCardInfos.get(i).getAccClass5state();
				if(accClass5state!=null&&!"".equals(accClass5state)){
					String accClassZero=accClass5state.split("\\|")[0];
					if(accClassZero.equals("2")||accClassZero.equals("3")||accClassZero.equals("4")||accClassZero.equals("5")){
						if("".equals(str_rec_seq)){
							str_rec_seq=loanNum+str_rec_seq;
						}else{
							str_rec_seq+=","+loanNum;
						}
					}
				}
			  }
			  loanNum++;
		    }
		}
		/*List<String> str_seq = getPbocService().querySummaryInfoRecSeq(appId);
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
		String str_loan_sort5 = getPbocService().querySummaryInfoLoanSort5(appId);
		if (str_loan_sort5 == null){
			str_sort5 = "否";
		}else if((str_loan_sort5 != null)){
			str_sort5 = "是";
		}
		//到期未结清贷款笔数
		String str_expire_loans_num = getPbocService().querySummaryInfoExpireLoansNum(appId);
		//到期未结清贷款笔数
		String str_about_expire_loans_num = getPbocService().querySummaryInfoAboutExpireLoansNum(appId);
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
		String str_loan_accounts_num = getPbocService().queryLoanAccountsNum(appId);
		if(str_loan_accounts_num!=null){
			pbocSummaryInfoYdj.put("loan_accounts_count", str_loan_accounts_num);
		}
		}
		
		//判断取两条的LIST是否有值
		if (!pbocSummaryInfoYdj2.isEmpty()){
			//判断取两条的LIST是否有两条数据
			if (pbocSummaryInfoYdj2.size() >=2 && pbocSummaryInfoYdj2.get(1) != null) {
				JSONObject jsonObject1 = JSONObject.fromObject(pbocSummaryInfoYdj);
				JSONObject jsonObject2 = JSONObject.fromObject(pbocSummaryInfoYdj2.get(1));
				context.setData("success", true);
				String json1 = jsonObject1.toString();
				String json2 = jsonObject2.toString();
				String json =  json1.replace('}', ',') + json2.substring(1,json2.length());
				context.setData("applyPbocSummary",json);
				return;
			}
			//判断取两条的LIST，只有第一条有值时
			else if (pbocSummaryInfoYdj2.size() == 1 ){
				JSONObject jsonObject1 = JSONObject.fromObject(pbocSummaryInfoYdj);
				context.setData("success", true);
				String json1 = jsonObject1.toString();
				String json =  json1;
				context.setData("applyPbocSummary",json);
				return;
			}
		}
		context.setData("success", false);
	}
	
	/**
	 * 查询人行摘要信息_易达金
	 * 
	 * @param context
	 * @throws Exception
	 */
	/*public void querySummaryInfoYdj(Context context) throws Exception {
		String appId = (String) context.getData("appId");
		Assert.notNull(appId, "请求申请件编号为空!");
		
		if (logger.isDebugEnabled()) {
			logger.debug("查询申请件[" + appId + "]人行摘要信息");
		}
		
		Map<String, String> pbocSummaryInfo = getPbocService().selectSummaryInfoYdj(appId);
		
		if (pbocSummaryInfo != null) {
			JSONObject jsonObject = JSONObject.fromObject(pbocSummaryInfo);
			context.setData("success", true);
			context.setData("applyPbocSummary", jsonObject.toString());
			return;
		}
		
		context.setData("success", false);
	}*/
	
	/**
	 * 查询基本信息
	 * @param context
	 * @throws Exception
	 */
	public void queryBaseInfo(Context context) throws Exception{
		
		String appId = (String) context.getData("appId");
		//String appId = "1705151611P02441";
		Map<String, String> pbocSummaryInfo = getPbocService().selectBaseInfo(appId);
		if (pbocSummaryInfo != null) {
		//人行详情中某些中文字段描述前显示数字，使用字符串截取去掉显示数字
		String maritalStatus = pbocSummaryInfo.get("maritalStatus");
		String queryedCertType = pbocSummaryInfo.get("queryedCertType");
		String gender = pbocSummaryInfo.get("gender");
		String queryResultPrompt = pbocSummaryInfo.get("queryResultPrompt");
		String degree =  pbocSummaryInfo.get("degree");
		String qryReason = pbocSummaryInfo.get("qryReason");
		String residencyStatus = pbocSummaryInfo.get("residencyStatus");
		String mateCertType= pbocSummaryInfo.get("mateCertType");//配偶证件类型去掉   类似  1|
		if(maritalStatus!=null&&!"".equals(maritalStatus)){
			pbocSummaryInfo.put("maritalStatus", maritalStatus.substring(maritalStatus.indexOf("|")+1,maritalStatus.length()));
		}
		if(queryedCertType!=null&&!"".equals(queryedCertType)){
			pbocSummaryInfo.put("queryedCertType", queryedCertType.substring(queryedCertType.indexOf("|")+1,queryedCertType.length()));
		}
		if(gender!=null&&!"".equals(gender)){
			pbocSummaryInfo.put("gender", gender.substring(gender.indexOf("|")+1,gender.length()));
		}
		if(queryResultPrompt!=null&&!"".equals(queryResultPrompt)){
			pbocSummaryInfo.put("queryResultPrompt", queryResultPrompt.substring(queryResultPrompt.indexOf("|")+1,queryResultPrompt.length()));
		}
		if(degree!=null&&!"".equals(degree)){
			pbocSummaryInfo.put("degree", degree.substring(degree.indexOf("|")+1,degree.length()));
		}
		if(qryReason!=null&&!"".equals(qryReason)){
			pbocSummaryInfo.put("qryReason", qryReason.substring(qryReason.indexOf("|")+1,qryReason.length()));
		}
		if(residencyStatus!=null&&!"".equals(residencyStatus)){
		pbocSummaryInfo.put("residencyStatus", residencyStatus.substring(residencyStatus.indexOf("|")+1,residencyStatus.length()));
		}
		if(mateCertType!=null&&!"".equals(mateCertType)){
			pbocSummaryInfo.put("mateCertType", mateCertType.substring(mateCertType.indexOf("|")+1,mateCertType.length()));
		}
		JSONObject jsonObject = JSONObject.fromObject(pbocSummaryInfo);
		context.setData("success", true);
		context.setData("queryBaseInfo", jsonObject.toString());
		}else{
			context.setData("success", false);
		}
		
	}
	
	
	/**
	 * 居住信息
	 * @param context
	 * @throws Exception
	 */

	
	public void queryResideInfo (Context context) {
		
		String appId = (String) context.getData("appId");
		
		List<Map<String, String>> pbocSummaryInfo = getPbocService().selectResideInfo(appId);
	
		if (pbocSummaryInfo != null) {
			String jsonObject=null;
			try {
			//	jsonObject = MAPPER.writeValueAsString(pbocSummaryInfo);
				Gson gson = new Gson();
				jsonObject=gson.toJson(pbocSummaryInfo);	
			} catch (Exception e) {
				e.printStackTrace();
			} 
			
			//JSONObject jsonObject = JSONObject.fromObject(pbocSummaryInfo);
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
		
		List<Map<String, String>> pbocSummaryInfo = getPbocService().selectProessionInfo(appId);
		
		if (!pbocSummaryInfo.isEmpty()) {
			String jsonObject=null;
			try {
				Gson gson = new Gson();
				//jsonObject = MAPPER.writeValueAsString(pbocSummaryInfo);
				jsonObject=gson.toJson(pbocSummaryInfo);
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
		List<Map<String, String>> pbocSummaryInfo = getPbocService().selectMessageInfo(appId);

		if (pbocSummaryInfo != null) {
			String jsonObject=null;
			try {
				Gson gson = new Gson();
				//jsonObject = MAPPER.writeValueAsString(pbocSummaryInfo);
				jsonObject=gson.toJson(pbocSummaryInfo);
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
 * 中征信"信用1000"评分	
 * @param context
 * @throws Exception
 */
	
	public void queryCreditGradeInfo(Context context) throws Exception {
		String appId = (String) context.getData("appId");
		List<Map<String, String>> pbocSummaryInfo = getPbocService().selectCreditGradeInfo(appId);
		//Map<String, String> json = pbocSummaryInfo.get(0);
		if (pbocSummaryInfo != null) {
			String jsonObject=null;
			try {
				Gson gson = new Gson();
				//jsonObject = MAPPER.writeValueAsString(pbocSummaryInfo);
				jsonObject=gson.toJson(pbocSummaryInfo);
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
		//	Map<String, String> pbocSummaryInfo = getPbocService().queryMessageInfo(appId);
		List<Map<String, String>> pbocSummaryInfo = getPbocService().selectOverdueInfo(appId);
		if(!pbocSummaryInfo.isEmpty()){
			if(pbocSummaryInfo.size()>0){
			Map<String, String> json = pbocSummaryInfo.get(0);
			
			if (pbocSummaryInfo != null) {
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
	public void queryCreditInfo(Context context)  {
		String appId = (String) context.getData("appId");
		//	Map<String, String> pbocSummaryInfo = getPbocService().queryMessageInfo(appId);
		List<Map<String, String>> pbocSummaryInfo = getPbocService().selectCreditInfo(appId);
		if(!pbocSummaryInfo.isEmpty()){
			if(pbocSummaryInfo.size()>0){
				Map<String, String> json = pbocSummaryInfo.get(0);
				if (pbocSummaryInfo != null) {
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
 *  资产处置信息	
 * @param context
 * @throws Exception
 */
	public void queryAssetInfo(Context context) throws Exception {
		String appId = (String) context.getData("appId");
		List<Map<String, String>> pbocSummaryInfo = getPbocService().selectAssetInfo(appId);
	//	Map<String, String> json = pbocSummaryInfo.get(0);
		
		if (pbocSummaryInfo != null) {
			String jsonObject=null;
			try {
			//	jsonObject = MAPPER.writeValueAsString(pbocSummaryInfo);
				Gson gson = new Gson();
				jsonObject=gson.toJson(pbocSummaryInfo);
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
		List<Map<String, String>> pbocSummaryInfo = getPbocService().selectAssureInfo(appId);
		//	Map<String, String> json = pbocSummaryInfo.get(0);
		
		if (pbocSummaryInfo != null) {
			String jsonObject=null;
			try {
			//	jsonObject = MAPPER.writeValueAsString(pbocSummaryInfo);
				Gson gson = new Gson();
				jsonObject=gson.toJson(pbocSummaryInfo);	
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
		List<Map<String, String>> pbocSummaryInfo = getPbocService().selectArrearageInfo(appId);
		
		if (pbocSummaryInfo != null) {
			String jsonObject=null;
			try {
			//	jsonObject = MAPPER.writeValueAsString(pbocSummaryInfo);
				Gson gson = new Gson();
				jsonObject=gson.toJson(pbocSummaryInfo);
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
		List<Map<String, String>> pbocSummaryInfo = getPbocService().selectJudgmentInfo(appId);
		
		if (pbocSummaryInfo != null) {
			String jsonObject=null;
			try {
				//jsonObject = MAPPER.writeValueAsString(pbocSummaryInfo);
				Gson gson = new Gson();
				jsonObject=gson.toJson(pbocSummaryInfo);
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
		List<Map<String, String>> pbocSummaryInfo = getPbocService().selectForceInfoInfo(appId);
		
		if (pbocSummaryInfo != null) {
			String jsonObject=null;
			try {
			//	jsonObject = MAPPER.writeValueAsString(pbocSummaryInfo);
				Gson gson = new Gson();
				jsonObject=gson.toJson(pbocSummaryInfo);	
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
		List<Map<String, String>> pbocSummaryInfo = getPbocService().selectPenaltyInfo(appId);
		
		if (pbocSummaryInfo != null) {
			String jsonObject=null;
			try {
			//	jsonObject = MAPPER.writeValueAsString(pbocSummaryInfo);
				Gson gson = new Gson();
				jsonObject=gson.toJson(pbocSummaryInfo);		
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
		List<Map<String, String>> pbocSummaryInfo = getPbocService().selectHousingInfo(appId);
		
		if (pbocSummaryInfo != null) {
			String jsonObject=null;
			try {
				//jsonObject = MAPPER.writeValueAsString(pbocSummaryInfo);
				Gson gson = new Gson();
				jsonObject=gson.toJson(pbocSummaryInfo);	
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
		List<Map<String, String>> pbocSummaryInfo = getPbocService().selectPayAnnuityInfo(appId);
		
		if (pbocSummaryInfo != null) {
			String jsonObject=null;
			try {
			//	jsonObject = MAPPER.writeValueAsString(pbocSummaryInfo);
				Gson gson = new Gson();
				jsonObject=gson.toJson(pbocSummaryInfo);	
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
		List<Map<String, String>> pbocSummaryInfo = getPbocService().selectGrantAnnuityInfo(appId);
		
		if (pbocSummaryInfo != null) {
			String jsonObject=null;
			try {
			//	jsonObject = MAPPER.writeValueAsString(pbocSummaryInfo);
				Gson gson = new Gson();
				jsonObject=gson.toJson(pbocSummaryInfo);	
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
		List<Map<String, String>> pbocSummaryInfo = getPbocService().selectLowHouseholdInfo(appId);
		
		if (pbocSummaryInfo != null) {
			String jsonObject=null;
			try {
			//	jsonObject = MAPPER.writeValueAsString(pbocSummaryInfo);
				Gson gson = new Gson();
				jsonObject=gson.toJson(pbocSummaryInfo);
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
		List<Map<String, String>> pbocSummaryInfo = getPbocService().selectOccupationInfo(appId);
		
		if (pbocSummaryInfo != null) {
			String jsonObject=null;
			try {
		//		jsonObject = MAPPER.writeValueAsString(pbocSummaryInfo);
				Gson gson = new Gson();
				jsonObject=gson.toJson(pbocSummaryInfo);	
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
		List<Map<String, String>> pbocSummaryInfo = getPbocService().selectAwardInfo(appId);
		if (pbocSummaryInfo != null) {
			String jsonObject=null;
			try {
			//	jsonObject = MAPPER.writeValueAsString(pbocSummaryInfo);
				Gson gson = new Gson();
				jsonObject=gson.toJson(pbocSummaryInfo);	
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
		List<Map<String, String>> pbocSummaryInfo = getPbocService().selectVehicleInfo(appId);
		if (pbocSummaryInfo != null) {
			String jsonObject=null;
			try {
				//jsonObject = MAPPER.writeValueAsString(pbocSummaryInfo);
				Gson gson = new Gson();
				jsonObject=gson.toJson(pbocSummaryInfo);	
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
		List<Map<String, String>> pbocSummaryInfo = getPbocService().selectTelecomInfo(appId);
		
		if (pbocSummaryInfo != null) {
			String jsonObject=null;
			try {
				//jsonObject = MAPPER.writeValueAsString(pbocSummaryInfo);
				Gson gson = new Gson();
				jsonObject=gson.toJson(pbocSummaryInfo);
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
		List<Map<String, String>> pbocSummaryInfo = getPbocService().selectAnnounceInfo(appId);
		
		if (pbocSummaryInfo != null) {
			String jsonObject=null;
			try {
			//	jsonObject = MAPPER.writeValueAsString(pbocSummaryInfo);
				Gson gson = new Gson();
				jsonObject=gson.toJson(pbocSummaryInfo);	
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
		List<Map<String, String>> pbocSummaryInfo = getPbocService().selectDissentInfo(appId);
		
		if (pbocSummaryInfo != null) {
			String jsonObject=null;
			try {
			//	jsonObject = MAPPER.writeValueAsString(pbocSummaryInfo);
				Gson gson = new Gson();
				jsonObject=gson.toJson(pbocSummaryInfo);
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
		List<Map<String, String>> pbocSummaryInfo = getPbocService().selectReCreditInfo(appId);
		
		if (pbocSummaryInfo != null) {
			String jsonObject=null;
			try {
			//	jsonObject = MAPPER.writeValueAsString(pbocSummaryInfo);
				Gson gson = new Gson();
				jsonObject=gson.toJson(pbocSummaryInfo);	
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
	 * 一个月内的查询机构数
	 * @param context
	 * @throws Exception
	 */
	public void queryAllInfo(Context context) throws Exception {
		String appId = (String) context.getData("appId");
		Map<String,String> pbocSummaryInfo = getPbocService().selectAllInfo(appId);
		
		if (pbocSummaryInfo != null) {
			String jsonObject=null;
			try {
			//	jsonObject = MAPPER.writeValueAsString(pbocSummaryInfo);
				Gson gson = new Gson();
				jsonObject=gson.toJson(pbocSummaryInfo);	
			} catch (Exception e) {
				e.printStackTrace();
			} 
			context.setData("success", true);
			context.setData("allInfoInfo", jsonObject);
			return;
		}
		
		context.setData("success", false);
	}
	/**
	 *@Title:queryPbocAllRecordSummary
	 *@Description:查询总记录汇总(人行详情)
	 *@param context
	 *@author: gaohui
	 *@Date:2017年7月7日下午7:11:09
	 */
	public void queryPbocAllRecordSummary(Context context){
		Map map=context.getDataMap();
		Map<String,Object> pbocSummaryInfo=pbocService.queryPbocAllRecordSummary(map);
		if (pbocSummaryInfo != null) {
			String jsonObject=null;
			try {
				Gson gson = new Gson();
				jsonObject=gson.toJson(pbocSummaryInfo);	
			} catch (Exception e) {
				context.setData("success", false);
				e.printStackTrace();
			} 
			context.setData("success", true);
			context.setData("allInfoInfo", jsonObject);
		}else{
			context.setData("success", false);
		}
		
		
	}
	
	
	
	
	
	
	

	
	
	/**
	 *@Title:queryloanCardInfoByAppId
	 *@Description:人行  贷款   贷记卡、准贷记卡 的显示
	 *@param context
	 *@throws Exception
	 *@author: gaohui
	 *@Date:2017年7月12日上午9:36:20
	 *人行贷记卡，准贷记卡 排序显示
	 * 贷记卡、准贷记卡排序规则 20170711
	 * 1. 账户状态异常  (信用/免担保) 1>核销2>呆账3>止付4>冻结    时间 升序
	 * 2. 逾期(信用/免担保)
	 * 3.正常  (信用/免担保) 1>华夏银行 在最前  2>时间  升序
	 * 4.非"信用/免担保"类型 贷记卡
	 * 5.转出(信用/免担保)
	 * 6.未激活 (信用/免担保)
	 * 7.销户 (信用/免担保)
	 *  贷款 ，排序显示
	 *  1.账户状态异常  1>核销 2>呆账
	 *  2.逾期
	 *  3.正常 1>华夏银行 在最前  2>时间  升序
	 *  4.转出
	 *  5.结清
	 * 贷款     账户状态：1 正常2 逾期3 结清4 呆帐5 转出6 核销
	 * 贷记卡 /准贷记卡 账户状态：1 正常2 冻结3 止付4 销户5 呆帐6 未激活7 转出  8 核销
	 */
	public void queryloanCardInfoByAppId(Context context) throws Exception {
		
		String appId = (String) context.getData("appId");
		String messType = (String) context.getData("messType");
		Map<String,String> map=new HashMap<String, String>();
		map.put("appId", appId);
		map.put("messType", messType);
		List<LoanCardInfo> loanCardInfos =null;
		if("01".equals(messType)){//贷款
			loanCardInfos=pbocService.findLoanDataByMap(map);
		}else{//02 贷记卡    03 准贷记卡
			loanCardInfos=pbocService.findLoanCardDataByMap(map);// getPbocService().queryloanCardInfoByAppId(map);
		}
		
		for (LoanCardInfo loanCardInfo : loanCardInfos) {//人行详情  贷款、贷记卡。准贷记卡 账户状态看似另起一行的显示效果
			String accStatus= loanCardInfo.getLoanOrCreditAcctStatus();
			if(accStatus!=null){
			String[]  accStatusArray=accStatus.split("\\|");
			String basicInfoPrompt=loanCardInfo.getBasicInfoPrompt();
			String needLoanCardInfo=basicInfoPrompt+"<br>账户状态:"+accStatusArray[accStatusArray.length-1];
			loanCardInfo.setBasicInfoPrompt(needLoanCardInfo);
			}
		}
		if (!loanCardInfos.isEmpty()) {
			String jsonObject=null;
			try {
			//	jsonObject = MAPPER.writeValueAsString(loanCardInfos);
				Gson gson = new Gson();
				jsonObject=gson.toJson(loanCardInfos);	
			} catch (Exception e) {
				e.printStackTrace();
			} 
			context.setData("loanCardInfo", jsonObject);
			context.setData("success", true);
			return;
		}
		context.setData("success", false);
	}
	public void queryloanCardInfoBySeq(Context ctx) throws Exception {
		Map map=ctx.getDataMap();
		String seq= (String) map.get("seq");
		LoanCardInfo loanCardInfo=new LoanCardInfo();
		loanCardInfo=getPbocService().queryloanCardInfoBySeq(seq);
		if(loanCardInfo!=null){
			if(loanCardInfo.getAccCurMrealPayAmt()!=null){//对实际付款额的 返显控制 
				if(loanCardInfo.getAccCurMrealPayAmt().equals("")){
					loanCardInfo.setAccCurMrealPayAmt("0");
				}
			}else{
				loanCardInfo.setAccCurMrealPayAmt("0");
			}
		}
		String jsonloanCardInfo=JSON.toJSONString(loanCardInfo);
		ctx.setData("jsonloanCardInfo", jsonloanCardInfo);
		ctx.setData("loanCardInfo", loanCardInfo);
	}
	/**
	 *@Title:querylast5yearOverduelistBySeq
	 *@Description:人行详情页面查询 贷款 贷记卡的逾期数 
	 *@param ctx
	 *@throws Exception
	 *@author: gaohui
	 *@Date:2017年7月26日上午9:02:17
	 */
	public void querylast5yearOverduelistBySeq(Context ctx) throws Exception {
		Map map=ctx.getDataMap();
		List<last5yearOverdue> last5yearOverduelist = new ArrayList<last5yearOverdue>();
			try {
				last5yearOverduelist =pbocService.querylast5yearOverdue(map);
			} catch (CoreException e) {
				e.printStackTrace();
			}
		Map<String, Object> dataMap = new HashMap<String, Object>();
		String jsonObject=null;
		try {
			Gson gson = new Gson();
			jsonObject=gson.toJson(last5yearOverduelist);	
			dataMap.put("last5yearOverduelist", jsonObject);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		ctx.setDataMap(dataMap);
		
	}
	/**
	 *@Title:queryGruranteeByAppId
	 *@Description:人行详情页面  对外贷款担保信息（guaranteeType 1）   、对外信用卡担保信息 的查询（guaranteeType 2）
	 *@param context
	 *@throws Exception
	 *@author: gaohui
	 *@Date:2017年7月13日下午6:56:18
	 */
	public void queryGruranteeByAppId(Context context) throws Exception {
		String appId = (String) context.getData("appId");
		String guaranteeType = (String) context.getData("guaranteeType");
		Map<String,String> map=new HashMap<String, String>();
		map.put("appId", appId);
		map.put("guaranteeType", guaranteeType);
		List<Grurantee> grurantees = pbocService.queryGruranteeByAppId(map);
		
		if (!grurantees.isEmpty()) {
			String jsonObject=null;
			try {
				Gson gson = new Gson();
				jsonObject=gson.toJson(grurantees);	
			} catch (Exception e) {
				e.printStackTrace();
			} 
			context.setData("grurantee", jsonObject);
			context.setData("success", true);
			return;
		}
		context.setData("success", false);
	}
	/**
	 *@Title:findLoanSpecialDeal
	 *@Description:人行详情  贷款的 特殊交易查询
	 *@param context
	 *@throws Exception
	 *@author: gaohui
	 *@Date:2017年7月14日下午8:24:18
	 */
	public void findLoanSpecialDeal(Context ctx) throws CoreException {
		Map map=ctx.getDataMap();
		String faRecSeq = (String) map.get("faRecSeq");
		String messType = (String) map.get("messType");
		String appId = (String) map.get("appId");
		Map<String,String> paraMap=new HashMap<String, String>();
		paraMap.put("faRecSeq", faRecSeq);
		paraMap.put("messType", messType);
		paraMap.put("appId", appId);
		List<Map<String,Object>> listSpecialDeal= pbocService.findLoanSpecialDealByMap(paraMap);
		Map<String, Object> dataMap = new HashMap<String, Object>();
	if(!listSpecialDeal.isEmpty()){
		String jsonObject=null;
		try {
			Gson gson = new Gson();
			jsonObject=gson.toJson(listSpecialDeal);	
		} catch (Exception e) {
			e.printStackTrace();
		} 
			dataMap.put("listSpecialDeal", jsonObject);
	}
	ctx.setDataMap(dataMap);
	}

}
