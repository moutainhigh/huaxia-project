package com.huaxia.opas.action.credit;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.huateng.neofp.core.Action;
import com.huateng.neofp.core.Context;
import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.apply.BizInpApplDao;
import com.huaxia.opas.domain.allot.AllotApplyAllot;
import com.huaxia.opas.domain.credit.Candidate;
import com.huaxia.opas.domain.credit.CreditCheck;
import com.huaxia.opas.domain.credit.CreditMatching;
import com.huaxia.opas.domain.input.BizInpApplC1;
import com.huaxia.opas.service.apply.BizInpApplService;
import com.huaxia.opas.service.credit.CreditCheckService;
import com.huaxia.opas.service.credit.CreditMatchingService;
import com.huaxia.opas.service.credit.PatchboltService;
import com.huaxia.opas.service.system.ApUserOrgService;

import com.huaxia.opas.util.PropertiesFile_util;
import com.huaxia.opas.util.SequenceUtil;

import net.sf.json.JSONObject;
/*********************************************
 *@describe:征信提报、复核、调查、审批处理Action
 *@author xiaoJianFeng 
 *@date:2017-03-02
 **********************************************/
public class CreditCheckAction implements Action {
	private static Logger logger=LoggerFactory.getLogger(CreditCheckAction.class);

	@Resource(name = "creditCheckService")
	private CreditCheckService creditCheckServiceImpl;
	
	@Resource(name = "creditMatchingService")
	private CreditMatchingService creditMatchingServiceImpl;

	@Resource(name = "apUserOrgService")
	private ApUserOrgService apUserOrgService;
	
	@Resource(name="patchboltService")
	private PatchboltService patchboltService;
	
	@Resource(name = "bizInpApplService")
	private BizInpApplService bizInpApplService;
	
	
	/*************************
	 *@describe:征信调查复核分页查询
	 *@param context
	 *@throws CoreException
	 *@author xiaoJianFeng
	 *@date:2017-03-09
	 *************************/
	public void creditCheckPage(Context context) throws CoreException{
		try{
			logger.info("start creditCheckPage>>>>>>>>>>>>>");
			Map<String, Object> paraMap = context.getDataMap();
			int curNum = 0;
			int curPage = Integer.parseInt(String.valueOf(context.getDataMap().get("page") == null ? 0 : context.getDataMap().get("page")));
			int pageNum = Integer.parseInt(String.valueOf(context.getDataMap().get("rows") == null ? 0 : context.getDataMap().get("rows")));
			String userCode = String.valueOf(context.getDataMap().get("userId") == null ? 0 : context.getDataMap().get("userId"));
			//查询所在组
			String orgNo = creditCheckServiceImpl.queryGroup(userCode);
			paraMap.put("orgNo", orgNo);
			logger.info("start creditCheckPage>>>>>>>>>>>>curPage="+curPage+",pageNum="+pageNum);
			if (curPage > 1) {
				curNum = (curPage - 1) * pageNum;
			}
			logger.info("start creditCheckPage>>>>>>>>>>>>curNum="+curNum);
			Map<String, Object> map=creditCheckServiceImpl.selectCreditCheckPage(paraMap, curNum, pageNum);
			logger.info("start creditCheckPage>>>>>>>>>>>>map="+map==null?null:map.toString());
			context.setDataMap(map);
		}catch (Exception e) {
			logger.info("失败>>>>>>>>>>>>>>>>>>");
			context.setData("success", false);
			e.printStackTrace();
		}	
	}
	/**
	 *@describe:智能语音分页查询
	 *@param context
	 *@throws CoreException
	 *@author daihao
	 *@date:2020-03-23
	 */
	public void creditSVoicePage(Context context) throws CoreException{
		try{
			logger.info("start selectCreditSVoiceCheckPage>>>>>>>>>>>>>");
			Map<String, Object> paraMap = context.getDataMap();
			int curNum = 0;
			int curPage = Integer.parseInt(String.valueOf(context.getDataMap().get("page") == null ? 0 : context.getDataMap().get("page")));
			int pageNum = Integer.parseInt(String.valueOf(context.getDataMap().get("rows") == null ? 0 : context.getDataMap().get("rows")));
			String userCode = String.valueOf(context.getDataMap().get("userId") == null ? 0 : context.getDataMap().get("userId"));
			//查询所在组，有待商榷
			String orgNo = creditCheckServiceImpl.queryGroup(userCode);
			paraMap.put("orgNo", orgNo);
			logger.info("start creditSVoicePage>>>>>>>>>>>>curPage="+curPage+",pageNum="+pageNum);
			if (curPage > 1) {
				curNum = (curPage - 1) * pageNum;
			}
			logger.info("start creditSVoicePage>>>>>>>>>>>>curNum="+curNum);
			Map<String, Object> map=creditCheckServiceImpl.selectCreditSVoiceCheckPage(paraMap, curNum, pageNum);
			logger.info("start creditSVoicePage>>>>>>>>>>>>map="+map==null?null:map.toString());
			context.setDataMap(map);
		}catch (Exception e) {
			logger.info("失败>>>>>>>>>>>>>>>>>>");
			context.setData("success", false);
			e.printStackTrace();
		}
	}
	
	/**************************
	 *@describe:信调查复核数据修改(确定/回退)
	 *@param context
	 *@throws CoreException
	 *@author xiaoJianFeng 
	 *@date:2017-03-09
	 **************************/
	public void creditCheckadopt(Context context) throws Exception{
		try {
			Properties propertiesObject = PropertiesFile_util.getPropertiesObject();
			logger.info("creditCheckadopt>>>>>>>>>>>>>");
			String orgNo = (String)propertiesObject.get("ORG_NO");
			String reasonCode = (String)propertiesObject.get("REASON_CODE");
			Map<String,String> map= new HashMap<String, String>();
			map.put("orgNo", orgNo);
			map.put("reasonCode", reasonCode);
			Gson gson = new Gson();
			CreditCheck creditCheck = gson.fromJson(gson.toJson(context.getDataMap()), CreditCheck.class);
			String type =String.valueOf(context.getDataMap().get("type") == null ? 0 : context.getDataMap().get("type"));
			String userUuid = String.valueOf(context.getDataMap().get("userUuid") == null ? 0 : context.getDataMap().get("userUuid"));
			creditCheck.setInvest_autoId(SequenceUtil.getUUID());
			String crtUser = (String) context.getDataMap().get("invest_crtUser");
			creditCheck.setCheck_currOptUser(crtUser);
			creditCheck.setInvest_crtUser(crtUser);
			creditCheckServiceImpl.cheatCheck(creditCheck,type,userUuid.trim(),map);
			logger.info("creditCheckadopt>>>>>>>>>>>>>修改数据成功");
			context.setData("isSuccess", true);
		}catch (Exception e) {
			logger.info("失败>>>>>>>>>>>>>>>>>>");
			context.setData("success", false);
			e.printStackTrace();
		}	
	 
	}
	/**************************
	 *@describe:智能语音复核数据修改(确定/回退)
	 *@param context
	 *@throws CoreException
	 *@author xiaoJianFeng 
	 *@date:2017-03-09
	 **************************/
	public void creditSVoiceadopt(Context context) throws Exception{
		try {
			Properties propertiesObject = PropertiesFile_util.getPropertiesObject();
			logger.info("creditSVoiceadopt>>>>>>>>>>>>>");
			String orgNo = (String)propertiesObject.get("ORG_NO");
			String reasonCode = (String)propertiesObject.get("REASON_CODE");
			Map<String,String> map= new HashMap<String, String>();
			map.put("orgNo", orgNo);
			map.put("reasonCode", reasonCode);
			Gson gson = new Gson();
			//此处需要修改
			CreditCheck creditCheck = gson.fromJson(gson.toJson(context.getDataMap()), CreditCheck.class);
			String type =String.valueOf(context.getDataMap().get("type") == null ? 0 : context.getDataMap().get("type"));
			String userUuid = String.valueOf(context.getDataMap().get("userUuid") == null ? 0 : context.getDataMap().get("userUuid"));
			creditCheck.setInvest_autoId(SequenceUtil.getUUID());
			String crtUser = (String) context.getDataMap().get("invest_crtUser");
			creditCheck.setCheck_currOptUser(crtUser);
			creditCheck.setInvest_crtUser(crtUser);
			creditCheckServiceImpl.cheatCheck(creditCheck,type,userUuid.trim(),map);
			logger.info("creditSVoiceadopt>>>>>>>>>>>>>修改数据成功");
			context.setData("isSuccess", true);
		}catch (Exception e) {
			logger.info("失败>>>>>>>>>>>>>>>>>>");
			context.setData("success", false);
			e.printStackTrace();
		}	
	 
	}
	/**************************
	 *@describe:根据流水号推送
	 *@param context
	 *@throws CoreException
	 *@author 李航 
	 **************************/
	public void selfPush(Context ctx) throws CoreException {
		try {
			// 获得userId
			String userId = StringUtils.trimToEmpty((String) ctx.getDataMap().get("userIdParam"));
			String delStatus = StringUtils.trimToEmpty((String) ctx.getDataMap().get("delStatus"));
			Map<String,String> map = new HashMap<String,String>();
			map.put("userCode",userId);
			map.put("delStatus",delStatus);
			CreditCheck credit  = null;
			credit = creditCheckServiceImpl.selfTheOne(map);		
			if(credit==null){
				credit = new CreditCheck();
			}
			ctx.setData("credit", credit);
			ctx.setData("msg", "推送成功");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	/**************************
	 *@describe:根据流水号查询
	 *@param context
	 *@throws CoreException
	 *@author xiaoJianFeng 
	 *@date:2017-03-09
	 **************************/
	public void selectCreditCheckByAppid(Context context)throws CoreException{
		try {
				logger.info("selectCreditCheckByAppid>>>>>>>>>>>>>");
				String check_number =String.valueOf(context.getDataMap().get("appId") == null ? 0 : context.getDataMap().get("appId"));
				Map<String, String> paraMap = new HashMap<String, String>();
				paraMap.put("check_number", check_number);
				logger.info("selectCreditCheckByAppid>>>>>>>>>>>>>paraMap="+paraMap.toString());
				CreditCheck creditCheck=creditCheckServiceImpl.selectCreditCheckByApp_id(paraMap);
				if(creditCheck!=null){
					JSONObject jsonObject = JSONObject.fromObject(creditCheck);
					context.setData("success", true);
					context.setData("creditCheck", jsonObject.toString());
					logger.info("selectCreditCheckByAppid>>>>>>>>>>>>>返回CreditCheck="+creditCheck);
				}else{
					context.setData("success", false);
					logger.info("selectCreditCheckByAppid>>>>>>>>>>>>>返回CreditCheck为空="+creditCheck);
				}
		}catch (Exception e) {
			logger.info("失败>>>>>>>>>>>>>>>>>>");
			context.setData("success", false);
			e.printStackTrace();
		}
	}
	/**************************
	 *@describe:征信提报反欺诈分页查询
	 *@param context
	 *@throws CoreException
	 *@author xiaoJianFeng 
	 *@date:2017-03-09
	 **************************/
	public void creditCheckCheatPage(Context context)throws CoreException{
		try {
				logger.info("start creditCheckPage>>>>>>>>>>>>>");
				Map<String, Object> paraMap = context.getDataMap();
				int curNum = 0;
				int curPage = Integer.parseInt(String.valueOf(context.getDataMap().get("page") == null ? 0 : context.getDataMap().get("page")));
				int pageNum = Integer.parseInt(String.valueOf(context.getDataMap().get("rows") == null ? 0 : context.getDataMap().get("rows")));
				logger.info("start creditCheckPage>>>>>>>>>>>>curPage="+curPage+",pageNum="+pageNum);
				if (curPage > 1) {
					curNum = (curPage - 1) * pageNum;
				}
				logger.info("start creditCheckPage>>>>>>>>>>>>curNum="+curNum);
				Map<String, Object> map=creditCheckServiceImpl.selectCreditCheckCheatPage(paraMap, curNum, pageNum);
				logger.info("start creditCheckPage>>>>>>>>>>>>map="+map==null?null:map.toString());
				context.setDataMap(map);
		} catch (Exception e) {
			logger.info("失败>>>>>>>>>>>>>>>>>>");
			context.setData("success", false);
			e.printStackTrace();
		}
	}
	/**************************************************
	 *@describe:提报反欺诈(添加提报原因备注表、修改申请件分配表)
	 *@param context
	 *@throws CoreException
	 *@author xiaoJianFeng 
	 *@date:2017-03-09
	 ***************************************************/
	public void submitCheat(Context context)throws CoreException{
		try {
			List<CreditCheck> lists=new ArrayList<CreditCheck>();
			String check_reason = String.valueOf(context.getDataMap().get("check_reason") == null ? "" : context.getDataMap().get("check_reason"));
			String check_memo = String.valueOf(context.getDataMap().get("check_memo") == null ? "" : context.getDataMap().get("check_memo"));
			String appId = String.valueOf(context.getDataMap().get("appId") == null ? "" : context.getDataMap().get("appId"));
			
			String checkSubmitType=String.valueOf(context.getDataMap().get("check_submitType") == null ? "" : context.getDataMap().get("check_submitType"));
			String check_SubmitStatus=String.valueOf(context.getDataMap().get("check_SubmitStatus") == null ? "" : context.getDataMap().get("check_SubmitStatus"));
			//获取当前操作人
			String crediter = String.valueOf(context.getDataMap().get("userCode") == null ? "" : context.getDataMap().get("userCode"));
			logger.info("submitCheat>>>>>check_reason="+check_reason+",check_memo="+check_memo+",appId="+appId+",checkSubmitType="+checkSubmitType);
			String [] reasonArray=check_reason.split("<end>");
			logger.info("submitCheat="+reasonArray);
			for(int i=0;i<reasonArray.length;i++){
				logger.info("submitCheat="+reasonArray[i]);
				Pattern pattern=Pattern.compile("<icon>([\\w\\W]*)</icon>");
				Matcher m=pattern.matcher(reasonArray[i]);	
				while(m.find()){
					String checkReasonType="";
					String checkReason="";
					String reasonTypeCode="";
					String checkReasonCode="";
					CreditCheck creditCheck=new CreditCheck();
					String []groupArray=m.group(1).split("=");
					if(groupArray!=null && groupArray.length>=2){
						//提报父原因码
						String reasonType=groupArray[0];
						if(reasonType!=null){
							String reasonTypes[]=reasonType.split("&");
							checkReasonType=reasonTypes[0];
							reasonTypeCode=reasonTypes[1];
						}
						//提报子原因码
						String reasonValue=groupArray[1];
						if(reasonValue!=null){
							String reasonValues[]=reasonValue.split("&");
							checkReasonCode=reasonValues[0];
							checkReason=reasonValues[1];
						}
						
					}
					creditCheck.setCheck_number(appId);
					creditCheck.setCheck_reasonAutoId(SequenceUtil.getUUID());
					creditCheck.setCheck_reason(checkReason);
					creditCheck.setCheck_reasonCode(checkReasonCode);
					
					creditCheck.setCheck_reasonType(checkReasonType);
					creditCheck.setCheck_reasonTypeCode(reasonTypeCode);
					lists.add(creditCheck);
				}
			}			
			logger.info("lists="+lists.toString());
			CreditCheck credit=new CreditCheck();
			credit.setCheck_number(appId);
			credit.setCheck_submitType(checkSubmitType);
			//credit.setCheck_lastOD(new Date());
			credit.setCheck_memo(check_memo);
			credit.setCheck_SubmitStatus(check_SubmitStatus);
			//设置当前用户为上次(最后)操作人
			credit.setCheck_lastOU(crediter);
			//查询该征信员所在组
			String orgNo = creditCheckServiceImpl.queryGroup(crediter);
			credit.setCheck_currOptGroup(orgNo);
			creditCheckServiceImpl.submitCheat(lists,credit);
			
			//根据appId判断该申请件是否为套卡
			//根据appId查询进件卡
			BizInpApplC1 bizinpapplc1 = patchboltService.querybizInpApplList(appId);
			if(bizinpapplc1==null){
				bizinpapplc1 = new BizInpApplC1();
			}
			//0-无套卡标识  1-主卡  2-套卡
			char[] arr = appId.toCharArray();
			if("1".equals(bizinpapplc1.getYdjFlag())){
			if(bizinpapplc1 != null){
				
				if(!"0".equals(bizinpapplc1.getMatchingCardFlag())){
					if(arr.length==16&&arr[15]=='1'){
						arr[15]='2';
						credit.setCheck_number(new String(arr));
						
						for(int i = 0;i<lists.size();i++){
							lists.get(i).setCheck_number(new String(arr));
							lists.get(i).setCheck_reasonAutoId(SequenceUtil.getUUID());
						}
						//提报套卡
						creditCheckServiceImpl.submitCheat(lists, credit);
					}else{
						arr[15]='1';
						credit.setCheck_number(new String(arr));
						
						for(int i = 0;i<lists.size();i++){
							lists.get(i).setCheck_number(new String(arr));
							lists.get(i).setCheck_reasonAutoId(SequenceUtil.getUUID());
						}
						//提报套卡
						creditCheckServiceImpl.submitCheat(lists, credit);
					}
				}
			}
		}
			logger.info("creditCheckadopt>>>>>>>>>>>>>修改数据成功");
			context.setData("isSuccess", true);
		} catch(Exception e) {
			context.setData("exMsg", e.getMessage());
			context.setData("isSuccess", false);
			logger.error("creditCheckadopt.>>>.error="+e);
			e.printStackTrace();
		}
		
	}
	/**
	 * @Title:findQzOperationPerson
	 * @Description:欺诈操作人 下拉框
	 * @param ctx
	 * @author: hang 
	 * @throws CoreException 
	 */
	public void findQzOperationPerson(Context ctx) throws CoreException {
		Map map = ctx.getDataMap();
		String crediter = String.valueOf(ctx.getDataMap().get("userCode") == null ? "" : ctx.getDataMap().get("userCode"));
		Map<String, Object> dataMap = new HashMap<String, Object>();
		List<Map<String, Object>> htmlListMap = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> listApUser = creditCheckServiceImpl.findQzOperationPerson(map,crediter);
		Map<String, Object> mapUse = new HashMap<String, Object>();
		mapUse.put("dictDetailCode", "");
		mapUse.put("dictDetailName", "");
		htmlListMap.add(mapUse);
		if (!listApUser.isEmpty()) {
			htmlListMap.addAll(listApUser);
		}
		ctx.setData("listMap", htmlListMap);
	}
	/**************************
	 *@describe:提报反欺诈原因码查询
	 *@param context
	 *@throws CoreException
	 *@author xiaoJianFeng 
	 *@date:2017-03-10
	 **************************/
	public void selectMentionAntiFraud(Context context)throws CoreException{
			try{
				StringBuffer sb=new StringBuffer();
				sb.append("<table style='width:100%;border-collapse:collapse;' border='1px'>");
				Map<String, String> paraMap = new HashMap<String, String>();
				String appId = String.valueOf(context.getDataMap().get("appId") == null ? "" : context.getDataMap().get("appId"));
				paraMap.put("appId", appId);
				String type = String.valueOf(context.getDataMap().get("type") == null ? "" : context.getDataMap().get("type"));
				List<CreditCheck> creditChecks=creditCheckServiceImpl.selectMentionAntiFraud(paraMap);
				String memo="";
				String check_currOptUser="";
				if(creditChecks!=null && creditChecks.size()>0){
					for(int i=0;i<creditChecks.size();i++){
						CreditCheck creditCheck=creditChecks.get(i);
						memo=creditCheck.getCheck_memo();
						//check_currOptUser=creditCheck.getCheck_currOptUser();
						Map<String,Object> userMap=new HashMap<String,Object>();//全流程记录
						userMap.put("userCode", creditCheck.getCheck_lastOPT());
						Map<String,Object> apUserMap=bizInpApplService.selectApUserByUuIdUserCode(userMap);
						check_currOptUser=apUserMap.get("userName").toString();
						String reason=creditCheck.getCheck_reason();
						String reasonReplace=reason.replaceAll(",", "");
						String reasonArry[]=reasonReplace.split("\\|");
						String reasonType=creditCheck.getCheck_reasonType();
						sb.append("<tr><td align='right'>"+reasonType+"</td>");
						sb.append("<td>");
						if(reasonArry!=null&&reasonArry.length>0){
							for(int j=0;j<reasonArry.length;j++){
								sb.append("<div>"+reasonArry[j]+"</div>");
							}
						}
						sb.append("</td></tr>");
					}
					
				}
				if(!"1".equals(type)){
					sb.append("<tr><td align='right'>提交备注:</td><td><textarea rows='5' disabled='disabled'  style='width:99%'>"+memo+"</textarea></td></tr>");
				}else{
					context.setData("memo", memo);
					context.setData("check_currOptUser", check_currOptUser);
				}
				sb.append("</table>");
				context.setData("creditReason", sb.toString());
				context.setData("success", true);
			} catch (Exception e) {
				logger.info("失败>>>>>>>>>>>>>>>>>>");
				context.setData("success", false);
				e.printStackTrace();
			}
	}
	
	/*************************
	 *@describe:反欺诈调查分页查询
	 *@param context
	 *@throws CoreException
	 *@author xiaoJianFeng
	 *@date:2017-03-09
	 *************************/
	public void creditcheatInvestigationPage(Context context)throws CoreException{
			try {
				// 流水号、证件号码包含字母的部分全部转大写
				Map map = context.getDataMap();
				if (map != null) {
					String lsh = (String) map.get("lsh");
					if (lsh != null && !"".equals(lsh)) {
						lsh = lsh.toUpperCase();
						map.put("lsh", lsh.trim());
					}
					String zjhm = (String) map.get("zjhm");
					if (zjhm != null && !"".equals(zjhm)) {
						zjhm = zjhm.toUpperCase();
						map.put("zjhm", zjhm);
					}
				}
				String checkStr = (String) map.get("checkStr");
				String currStatus =  (String) (map.get("currStatus") == null?"":map.get("currStatus"));
				map.put("currStatus", currStatus);
				if (checkStr != null && !"".equals(checkStr)) {
					checkStr = checkStr.replace("CRT_TEAM_DATE", "b.CRT_TEAM_DATE");
					checkStr = checkStr.replace("QUICK_CARD_FLAG", "a.QUICK_CARD_FLAG");
					checkStr = checkStr.replace("vip", "substr(APP_ID,10,1)");
					checkStr = checkStr.replace("APP_ID", "QUICK_CARD_FLAG desc,substr(APP_ID,8,3) asc,substr(APP_ID,7,1) desc,substr(APP_ID,1,6) asc,substr(APP_ID,11,5) asc,substr(APP_ID,16,1) desc,QUEUE_DATE");
					checkStr = checkStr.replace("GROUP_DATE", "QUICK_CARD_FLAG desc,GROUP_DATE desc,QUEUE_DATE asc,substr(APP_ID,8,3) asc,substr(APP_ID,7,1) desc,substr(APP_ID,1,6) asc,substr(APP_ID,11,5) asc,substr(APP_ID,16,1) desc");
					checkStr = checkStr.replace("C1_CONAME", "QUICK_CARD_FLAG desc,NLSSORT(C1_CONAME,'NLS_SORT=SCHINESE_PINYIN_M'),QUEUE_DATE asc,substr(APP_ID,8,3) asc,substr(APP_ID,7,1) desc,substr(APP_ID,1,6) asc,substr(APP_ID,11,5) asc,substr(APP_ID,16,1) desc");
					checkStr = checkStr.replace("PATCH_STATUS", "QUICK_CARD_FLAG desc,PATCH_STATUS desc,substr(APP_ID,8,3) asc,substr(APP_ID,7,1) desc,substr(APP_ID,1,6) asc,substr(APP_ID,11,5) asc,substr(APP_ID,16,1) desc");
					map.put("checkStr",  checkStr.substring(0, checkStr.length() - 1));
				//	map.put("checkStr", "QUICK_CARD_FLAG desc," + checkStr.substring(0, checkStr.length() - 1) +",USER_DATES asc,substr(APP_ID,1,6) asc,substr(APP_ID,8,3) asc,substr(APP_ID,7,1) desc,substr(APP_ID,11,6) asc");
				} else {
					map.put("checkStr", "QUICK_CARD_FLAG desc,GROUP_DATE desc,USER_DATE asc,substr(APP_ID,1,6) asc,substr(APP_ID,8,3) asc,substr(APP_ID,7,1) desc,substr(APP_ID,11,5) asc,substr(APP_ID,16,1) desc");
					}
			//	Map<String, Object> paraMap = context.getDataMap();
				logger.info("start creditcheatInvestigationPage>>>>>>>>>>>>>");
				int curNum = 0;
				int curPage = Integer.parseInt(String.valueOf(context.getDataMap().get("page") == null ? 0 : context.getDataMap().get("page")));
				int pageNum = Integer.parseInt(String.valueOf(context.getDataMap().get("rows") == null ? 0 : context.getDataMap().get("rows")));
				logger.info("start creditcheatInvestigationPage>>>>>>>>>>>>curPage="+curPage+",pageNum="+pageNum);
				if (curPage > 1) {
					curNum = (curPage - 1) * pageNum;
				}
				logger.info("start creditcheatInvestigationPage>>>>>>>>>>>>curNum="+curNum);
				String delStatus = (String) map.get("delStatus");
				map.put("delStatus", delStatus);
				Map<String, Object> paraMap=creditCheckServiceImpl.selectCreditCheatInvestigationPage(map, curNum, pageNum);
				logger.info("start creditcheatInvestigationPage>>>>>>>>>>>>map="+paraMap==null?null:paraMap.toString());
				context.setDataMap(paraMap);
				context.setData("success", true);
			} catch (Exception e) {
				logger.info("失败>>>>>>>>>>>>>>>>>>");
				context.setData("success", false);
				e.printStackTrace();
			}	
	}
	/*************************
	 *@describe:易达金反欺诈调查分页查询
	 *@param context
	 *@throws CoreException
	 *@author xiaoJianFeng
	 *@date:2017-03-09
	 *************************/
	/*public void yidajincheatInvestigationPage(Context context)throws CoreException{
			try {
				Gson gson = new Gson();
				CreditCheck creditCheck = gson.fromJson(gson.toJson(context.getDataMap()), CreditCheck.class);
				logger.info("start creditcheatInvestigationPage>>>>>>>>>>>>>");
				int curNum = 0;
				int curPage = Integer.parseInt(String.valueOf(context.getDataMap().get("page") == null ? 0 : context.getDataMap().get("page")));
				int pageNum = Integer.parseInt(String.valueOf(context.getDataMap().get("rows") == null ? 0 : context.getDataMap().get("rows")));
				logger.info("start creditcheatInvestigationPage>>>>>>>>>>>>curPage="+curPage+",pageNum="+pageNum);
				if (curPage > 1) {
					curNum = (curPage - 1) * pageNum;
				}
				logger.info("start creditcheatInvestigationPage>>>>>>>>>>>>curNum="+curNum);
				creditCheck.setCheck_ydjFlag("1");
				Map<String, Object> map=creditCheckServiceImpl.selectCreditCheatInvestigationPage(creditCheck, curNum, pageNum);
				logger.info("start creditcheatInvestigationPage>>>>>>>>>>>>map="+map==null?null:map.toString());
				context.setDataMap(map);
				context.setData("success", true);
			} catch (Exception e) {
				logger.info("失败>>>>>>>>>>>>>>>>>>");
				context.setData("success", false);
				e.printStackTrace();
			}	
	}*/
	/*******************************************
	 *@describe:征信欺诈调查原因码查询,征信页面栏位需要显示信息
	 *@param context
	 *@throws CoreException
	 *@author xiaoJianFeng
	 *@date:2017-03-09
	 *******************************************/
	public void selectOpasApDict(Context context)throws CoreException{
		try {
			StringBuffer sb=new StringBuffer();
			String invest_autoId= context.getDataMap().get("invest_autoId")==null?null:String.valueOf(context.getDataMap().get("invest_autoId"));
			List<CreditCheck> credits=creditCheckServiceImpl.selectOpasOpasSubmitfraudInfoByautoID(invest_autoId);
			List<CreditCheck> lists=creditCheckServiceImpl.selectOpasApDict();
			if(lists!=null && lists.size()>0){
				for(int i=0;i<lists.size();i++){
					CreditCheck creditCheck=lists.get(i);
					String invest_result =creditCheck.getInvest_detailCode()+"-"+creditCheck.getInvest_detailName();
					sb.append("<div style='font-size:12px;border:1px;border-style:solid;border-top:0px'><input type='checkbox'");
					sb.append("onclick='selectCheckFunction(this)' id="+i+" name='investResult"+i+"' value='"+invest_result+"'/>"+creditCheck.getInvest_detailName()+" &nbsp;&nbsp;&nbsp;&nbsp;");
					sb.append("( 是否入库<input type='checkbox' disabled='disabled' name='investResult"+i+"box' id='investResult"+i+"box' value='true'/>)</div>");
				}
			}
			context.setData("creditCheck", credits);
			context.setData("investResultSize", lists.size());
			context.setData("investResult", sb.toString());
			context.setData("success",true);
		} catch (Exception e) {
			logger.info("失败>>>>>>>>>>>>>>>>>>");
			context.setData("success", false);
			e.printStackTrace();
		}
	}
	
	/*************************
	 *@describe:反欺诈调查提交、保存、回退
	 *@param context
	 *@throws CoreException
	 *@date:2017-03-12
	 *************************/
	public void creditcheatInvestigationHandle(Context context)throws CoreException{
		try{
			CreditCheck creditCheck=new CreditCheck();
	
			String specialExamineUser = context.getDataMap().get("specialExamineUser")==null?null:String.valueOf(context.getDataMap().get("specialExamineUser"));
			String invest_memo =context.getDataMap().get("invest_memo")==null?null:String.valueOf(context.getDataMap().get("invest_memo"));
			//String invest_crtUser = context.getDataMap().get("invest_crtUser")==null?null:String.valueOf(context.getDataMap().get("invest_crtUser"));
			//String invest_crtUserName = context.getDataMap().get("invest_crtUserName")==null?null:String.valueOf(context.getDataMap().get("invest_crtUserName"));
			String invest_resultFlag = context.getDataMap().get("invest_resultFlag")==null?null:String.valueOf(context.getDataMap().get("invest_resultFlag"));
			String invest_resultName =  context.getDataMap().get("invest_resultName")==null?null:String.valueOf(context.getDataMap().get("invest_resultName"));
			String invest_resultCode =  context.getDataMap().get("invest_resultCode")==null?null:String.valueOf(context.getDataMap().get("invest_resultCode"));
			String check_number =  context.getDataMap().get("check_number")==null?null:String.valueOf(context.getDataMap().get("check_number"));
			String invest_delStatus= context.getDataMap().get("invest_delStatus")==null?null:String.valueOf(context.getDataMap().get("invest_delStatus"));
			String invest_autoId= context.getDataMap().get("invest_autoId")==null?null:String.valueOf(context.getDataMap().get("invest_autoId"));
			String userCode= context.getDataMap().get("userCode")==null?null:String.valueOf(context.getDataMap().get("userCode"));
			String userUuid = context.getDataMap().get("userUuid")==null?null:String.valueOf(context.getDataMap().get("userUuid"));
			String fenHang = context.getDataMap().get("fenHang")==null?null:String.valueOf(context.getDataMap().get("fenHang"));
			creditCheck.setCheck_number(check_number);
			creditCheck.setInvest_memo(invest_memo);
			creditCheck.setInvest_resultFlag(invest_resultFlag);
			//creditCheck.setInvest_crtUserName(invest_crtUserName);
			creditCheck.setInvest_operationDate(new Date());
			creditCheck.setInvest_resultName(invest_resultName);
			creditCheck.setInvest_resultCode(invest_resultCode);
			creditCheck.setInvest_delStatus(invest_delStatus);
			creditCheck.setInvest_autoId(invest_autoId);
			creditCheck.setFenHang(fenHang);
			if(specialExamineUser==null||specialExamineUser==""){
				//查询该征信员所在组
				String orgNo = creditCheckServiceImpl.queryGroup(userCode);
				creditCheck.setCheck_currOptGroup(orgNo);
			}else{
				creditCheck.setInvest_crtUser(specialExamineUser);//设置欺诈审批员
			}
			if("5".equals(invest_delStatus)){
				creditCheckServiceImpl.cheatInvestigationCredit(creditCheck, userCode, userUuid.trim());
			}else{
				//creditCheck.setCheck_lastOU(userCode);//设置当前操作员(欺诈调查员)为上一操作人
				creditCheckServiceImpl.cheatInvestigationHandle(creditCheck,userCode,userUuid.trim());
			}
			context.setData("success", true);
		}catch(Exception e){
			logger.info("失败>>>>>>>>>>>>>>>>>>");
			context.setData("success", false);
			e.printStackTrace();
		}
	}
	/*************************
	 *@describe:反欺诈审批分页查询
	 *@param context
	 *@throws CoreException
	 *@author xiaoJianFeng
	 *@date:2017-03-09
	 *************************/
	public void creditCheatApprovalPage(Context context)throws CoreException{
		try {
			Map map = context.getDataMap();
			Gson gson = new Gson();
			CreditCheck creditCheck = gson.fromJson(gson.toJson(context.getDataMap()), CreditCheck.class);
			// 流水号、证件号码包含字母的部分全部转大写dwmc
			if (map != null) {
				String lsh = (String) map.get("lsh");
				if (lsh != null && !"".equals(lsh)) {
					lsh = lsh.toUpperCase();
				
				}
				String zjhm = (String) map.get("zjhm");
				if (zjhm != null && !"".equals(zjhm)) {
					zjhm = zjhm.toUpperCase();
					creditCheck.setCheck_certNo(zjhm);
					
				}
			}
			String khxm=map.get("khxm")!=null?map.get("khxm").toString():"";
			String dwmc=map.get("dwmc")!=null?map.get("dwmc").toString():"";
			creditCheck.setCheck_custName(khxm);
			creditCheck.setCheck_currentCN(dwmc);
			String checkStr = (String) map.get("checkStr");
			if (checkStr != null && !"".equals(checkStr)) {
				checkStr = checkStr.replace("CRT_TEAM_DATE", "b.CRT_TEAM_DATE");
				checkStr = checkStr.replace("QUICK_CARD_FLAG", "a.QUICK_CARD_FLAG");
				checkStr = checkStr.replace("vip", "substr(APP_ID,10,1)");
				checkStr = checkStr.replace("APP_ID", "QUICK_CARD_FLAG desc,substr(APP_ID,8,3) asc,substr(APP_ID,7,1) desc,substr(APP_ID,1,6) asc,substr(APP_ID,11,5) asc,substr(APP_ID,16,1) desc,QUEUE_DATE");
				checkStr = checkStr.replace("GROUP_DATE", "QUICK_CARD_FLAG desc,GROUP_DATE desc,QUEUE_DATE asc,substr(APP_ID,8,3) asc,substr(APP_ID,7,1) desc,substr(APP_ID,1,6) asc,substr(APP_ID,11,5) asc,substr(APP_ID,16,1) desc");
				checkStr = checkStr.replace("C1_CONAME", "QUICK_CARD_FLAG desc,NLSSORT(C1_CONAME,'NLS_SORT=SCHINESE_PINYIN_M'),QUEUE_DATE asc,substr(APP_ID,8,3) asc,substr(APP_ID,7,1) desc,substr(APP_ID,1,6) asc,substr(APP_ID,11,5) asc,substr(APP_ID,16,1) desc");
				checkStr = checkStr.replace("PATCH_STATUS", "QUICK_CARD_FLAG desc,PATCH_STATUS desc,substr(APP_ID,8,3) asc,substr(APP_ID,7,1) desc,substr(APP_ID,1,6) asc,substr(APP_ID,11,5) asc,substr(APP_ID,16,1) desc");
				creditCheck.setCheckStr(checkStr.substring(0, checkStr.length() - 1));
			} else {
				creditCheck.setCheckStr("QUICK_CARD_FLAG desc,GROUP_DATE desc,USER_DATE asc,substr(APP_ID,1,6) asc,substr(APP_ID,8,3) asc,substr(APP_ID,7,1) desc,substr(APP_ID,11,5) asc,substr(APP_ID,16,1) desc");
			}
			String lsh=map.get("lsh")!=null?map.get("lsh").toString().toUpperCase():"";
			creditCheck.setCheck_number(lsh.trim());
			logger.info("start creditCheatApprovalPage>>>>>>>>>>>>>");
			
		
			String userCode = context.getDataMap().get("userId").toString();
			creditCheck.setInvest_crtUser(userCode);

			int curNum = 0;
			int curPage = Integer.parseInt(String.valueOf(context.getDataMap().get("page") == null ? 0 : context.getDataMap().get("page")));
			int pageNum = Integer.parseInt(String.valueOf(context.getDataMap().get("rows") == null ? 0 : context.getDataMap().get("rows")));
			logger.info("start creditCheatApprovalPage>>>>>>>>>>>>curPage="+curPage+",pageNum="+pageNum);
			if (curPage > 1) {
				curNum = (curPage - 1) * pageNum;
			}
			logger.info("start creditCheatApprovalPage>>>>>>>>>>>>curNum="+curNum);
			Map<String, Object> map1=creditCheckServiceImpl.selectCreditCheatApprovalPage(creditCheck, curNum, pageNum);
			logger.info("start creditCheatApprovalPage>>>>>>>>>>>>map="+map1==null?null:map1.toString());	
			context.setDataMap(map1);
			context.setData("success", true);
		} catch (Exception e) {
			logger.info("失败>>>>>>>>>>>>>>>>>>");
			context.setData("success", false);
			e.printStackTrace();
		}
	}
	/*************************
	 *@describe:易达金反欺诈审批分页查询
	 *@param context
	 *@throws CoreException
	 *@author xiaoJianFeng
	 *@date:2017-03-25
	 *************************/
/*	public void yidajinCheatApprovalPage(Context context)throws CoreException{
		try {
			logger.info("start creditCheatApprovalPage>>>>>>>>>>>>>");
			Gson gson = new Gson();
			CreditCheck creditCheck = gson.fromJson(gson.toJson(context.getDataMap()), CreditCheck.class);
			int curNum = 0;
			int curPage = Integer.parseInt(String.valueOf(context.getDataMap().get("page") == null ? 0 : context.getDataMap().get("page")));
			int pageNum = Integer.parseInt(String.valueOf(context.getDataMap().get("rows") == null ? 0 : context.getDataMap().get("rows")));
			logger.info("start creditCheatApprovalPage>>>>>>>>>>>>curPage="+curPage+",pageNum="+pageNum);
			if (curPage > 1) {
				curNum = (curPage - 1) * pageNum;
			}
			logger.info("start creditCheatApprovalPage>>>>>>>>>>>>curNum="+curNum);
			creditCheck.setCheck_ydjFlag("1");
			Map<String, Object> map=creditCheckServiceImpl.selectCreditCheatApprovalPage(creditCheck, curNum, pageNum);
			logger.info("start creditCheatApprovalPage>>>>>>>>>>>>map="+map==null?null:map.toString());	
			context.setDataMap(map);
			context.setData("success", true);
		} catch (Exception e) {
			logger.info("失败>>>>>>>>>>>>>>>>>>");
			context.setData("success", false);
			e.printStackTrace();
		}
	}*/
	/*********************************
	 *@describe:反欺诈调审核提交、保存、回退
	 *@param context
	 *@throws CoreException
	 *@author xiaoJianFeng
	 *@date:2017-03-12
	 *********************************/
	public void creditcheatExamineHandle(Context context)throws CoreException{
			try{
				String approvalMemo =context.getDataMap().get("invest_approvalMemo")==null?null:String.valueOf(context.getDataMap().get("invest_approvalMemo"));
				String resultFlag =context.getDataMap().get("invest_resultFlag")==null?null:String.valueOf(context.getDataMap().get("invest_resultFlag"));
				String resultName =context.getDataMap().get("invest_resultName")==null?null:String.valueOf(context.getDataMap().get("invest_resultName"));
				String invest_autoId =context.getDataMap().get("invest_autoId")==null?null:String.valueOf(context.getDataMap().get("invest_autoId"));
				String resultCode =context.getDataMap().get("invest_resultCode")==null?null:String.valueOf(context.getDataMap().get("invest_resultCode"));
				String check_number =context.getDataMap().get("check_number")==null?null:String.valueOf(context.getDataMap().get("check_number"));
				String invest_delStatus= context.getDataMap().get("invest_delStatus")==null?null:String.valueOf(context.getDataMap().get("invest_delStatus"));
				String userUuid = context.getDataMap().get("userUuid")==null?null:String.valueOf(context.getDataMap().get("userUuid"));
				String userCode = context.getDataMap().get("userCode")==null?null:String.valueOf(context.getDataMap().get("userCode"));
				String fenHang = context.getDataMap().get("fenHang")==null?null:String.valueOf(context.getDataMap().get("fenHang"));
				CreditCheck creditCheck=new CreditCheck();
				creditCheck.setInvest_approvalMemo(approvalMemo);
				creditCheck.setInvest_resultCode(resultCode);
				creditCheck.setInvest_resultFlag(resultFlag);
				creditCheck.setInvest_resultName(resultName);
				creditCheck.setCheck_number(check_number);
				creditCheck.setInvest_autoId(invest_autoId);
				creditCheck.setInvest_delStatus(invest_delStatus);
				creditCheck.setCheck_currOptUser(userCode);
				creditCheck.setFenHang(fenHang);
				if("5".equals(invest_delStatus)){
					creditCheckServiceImpl.cheatInvestigationCredit(creditCheck, userCode, userUuid.trim());
				}else{
					creditCheckServiceImpl.creditcheatExamineHandle(creditCheck,userUuid.trim());
				}
				context.setData("success", true);
			}catch(Exception e){
				logger.info("失败>>>>>>>>>>>>>>>>>>");
				context.setData("success", false);
				e.printStackTrace();
			}
	}
	
	/*********************************
	 *@describe:查询候选人列表
	 *@param context
	 *@throws CoreException
	 *@author xiaoJianFeng
	 *@date:2017-03-15
	 *********************************/
	public void selectCandidate(Context context)throws CoreException{
		try {
			logger.info("start selectCandidate>>>>>>>>>>>>>");
			Gson gson = new Gson();
			Candidate candidate = gson.fromJson(gson.toJson(context.getDataMap()), Candidate.class);
			int curNum = 0;
			int curPage = Integer.parseInt(String.valueOf(context.getDataMap().get("page") == null ? 0 : context.getDataMap().get("page")));
			int pageNum = Integer.parseInt(String.valueOf(context.getDataMap().get("rows") == null ? 0 : context.getDataMap().get("rows")));
			logger.info("start selectCandidate>>>>>>>>>>>>curPage="+curPage+",pageNum="+pageNum);
			if (curPage > 1) {
				curNum = (curPage - 1) * pageNum;
			}
			logger.info("start selectCandidate>>>>>>>>>>>>curNum="+curNum);
			Map<String, Object> map=creditCheckServiceImpl.selectCandidate(candidate, curNum, pageNum);
			logger.info("start selectCandidate>>>>>>>>>>>>map="+map==null?null:map.toString());	
			context.setDataMap(map);
			context.setData("success", true);
		} catch (Exception e) {
			logger.info("失败>>>>>>>>>>>>>>>>>>");
			context.setData("success", false);
			e.printStackTrace();
		}
		
	}
	/**************************
	 *@describe:工作量统计
	 *@param context
	 *@throws CoreException
	 *@author xiaoJianFeng
	 *@date:2017-03-19
	 **************************/
	public void workCount(Context context)throws CoreException{
		Map<String, String> paraMap = new HashMap<String, String>();
		String startTime =context.getDataMap().get("startTime")==null?null:String.valueOf(context.getDataMap().get("startTime"));
		String endTime =context.getDataMap().get("endTime")==null?null:String.valueOf(context.getDataMap().get("endTime"));
		String userId=context.getData("userId");
		String delStatus=context.getData("delStatus");
		paraMap.put("startTime", startTime);
		paraMap.put("endTime", endTime);
		paraMap.put("invest_crtUser", userId);
		paraMap.put("delStatus",delStatus);
		if("2".equals(delStatus)){
			paraMap.put("vdelStatus","6");
			String personalSubmitAccom = creditCheckServiceImpl.workloadStatistics(paraMap);
			paraMap.put("vdelStatus","3");
			paraMap.put("checkStr","1");
			String personalBackAccom = creditCheckServiceImpl.workloadStatistics(paraMap);
			context.setData("personalSubmitAccom", personalSubmitAccom);
			context.setData("personalBackAccom", personalBackAccom);
		}else if("4".equals(delStatus)){
			paraMap.put("vdelStatus","4");
			String personalSubmitAccom = creditCheckServiceImpl.workloadStatistics(paraMap);
			paraMap.put("vdelStatus","1");
			String personalBackFraudAccom = creditCheckServiceImpl.workloadStatistics(paraMap);
			paraMap.put("vdelStatus","3");
			paraMap.put("checkStr","1");
			String personalBackCreditAccom = creditCheckServiceImpl.workloadStatistics(paraMap);
			context.setData("personalSubmitAccom", personalSubmitAccom);
			context.setData("personalBackFraudAccom", personalBackFraudAccom);
			context.setData("personalBackCreditAccom", personalBackCreditAccom);
		}
		context.setData("success", true);
	}

	/**************************
	 *@describe:人行征信查询匹配设置 分布查询
	 *@param context
	 *@throws CoreException
	 *@author xiaoJianFeng
	 *@date:2017-03-19
	 **************************/
	public void selectCreditMatchPage(Context context)throws CoreException{
		try {
			logger.info("start selectCreditMatchPage>>>>>>>>>>>>>");
			Gson gson = new Gson();
			CreditMatching creditMatching = gson.fromJson(gson.toJson(context.getDataMap()), CreditMatching.class);
			int curNum = 0;
			int curPage = Integer.parseInt(String.valueOf(context.getDataMap().get("page") == null ? 0 : context.getDataMap().get("page")));
			int pageNum = Integer.parseInt(String.valueOf(context.getDataMap().get("rows") == null ? 0 : context.getDataMap().get("rows")));
			logger.info("start selectCreditMatchPage>>>>>>>>>>>>curPage="+curPage+",pageNum="+pageNum);
			if (curPage > 1) {
				curNum = (curPage - 1) * pageNum;
			}
			logger.info("start selectCreditMatchPage>>>>>>>>>>>>curNum="+curNum);
			Map<String, Object> map=creditMatchingServiceImpl.selectCreditMatchPage(creditMatching, curNum, pageNum);
			logger.info("start selectCreditMatchPage>>>>>>>>>>>>map="+map==null?null:map.toString());	
			context.setDataMap(map);
			context.setData("success", true);
		} catch (Exception e) {
			logger.info("失败>>>>>>>>>>>>>>>>>>");
			context.setData("success", false);
			e.printStackTrace();
		}
	}
	
	/**************************
	 *@describe:人行征信查询匹配设置 添加
	 *@param context
	 *@throws CoreException
	 *@author xiaoJianFeng
	 *@date:2017-03-19
	 **************************/
	public void addCreditMatch(Context context)throws CoreException{
		try {
			logger.info("start addCreditMatch>>>>>>>>>>>>>");
			Gson gson = new Gson();
			CreditMatching creditMatching = gson.fromJson(gson.toJson(context.getDataMap()), CreditMatching.class);
			creditMatching.setMatch_autoID(SequenceUtil.getUUID());
			creditMatching.setMatch_crtTime(new Date());
			creditMatching.setMatch_crtUser(context.getData("userId").toString());
			creditMatchingServiceImpl.addCreditMatch(creditMatching);
			context.setData("success", true);
		} catch (Exception e) {
			logger.info("失败>>>>>>>>>>>>>>>>>>");
			context.setData("success", false);
			e.printStackTrace();
		}
	}
	
	/**************************
	 *@describe:人行征信查询匹配设置 修改
	 *@param context
	 *@throws CoreException
	 *@author xiaoJianFeng
	 *@date:2017-03-19
	 **************************/
	public void updateCreditMatch(Context context)throws CoreException{
		try {
			logger.info("start updateCreditMatch>>>>>>>>>>>>>");
			Gson gson = new Gson();
			CreditMatching creditMatching = gson.fromJson(gson.toJson(context.getDataMap()), CreditMatching.class);
			creditMatchingServiceImpl.updateCreditMatch(creditMatching);
			context.setData("success", true);
		} catch (Exception e) {
			logger.info("失败>>>>>>>>>>>>>>>>>>");
			context.setData("success", false);
			e.printStackTrace();
		}
	}
	/**************************
	 *@describe:人行征信查询匹配设置 删除
	 *@param context
	 *@throws CoreException
	 *@author xiaoJianFeng
	 *@date:2017-03-19
	 **************************/
	public void deleteCreditMatch(Context context)throws CoreException{
		try {
			logger.info("start deleteCreditMatch>>>>>>>>>>>>>");
			String autoId =context.getDataMap().get("match_autoID")==null?null:String.valueOf(context.getDataMap().get("match_autoID"));
			creditMatchingServiceImpl.deleteCreditMatch(autoId); 
			context.setData("success", true);
		} catch (Exception e) {
			logger.info("失败>>>>>>>>>>>>>>>>>>");
			context.setData("success", false);
			e.printStackTrace();
		}
	}	
	
	/**************************
	 *@describe:易达金数据分页
	 *@param context
	 *@throws CoreException
	 *@author xiaoJianFeng
	 *@date:2017-03-26
	 **************************/
	public void yidajinCheckPage(Context context)throws CoreException{
		try{
			logger.info("start yidajinCheckPage>>>>>>>>>>>>>");
			Map<String, Object> paraMap = context.getDataMap();
			int curNum = 0;
			int curPage = Integer.parseInt(String.valueOf(context.getDataMap().get("page") == null ? 0 : context.getDataMap().get("page")));
			int pageNum = Integer.parseInt(String.valueOf(context.getDataMap().get("rows") == null ? 0 : context.getDataMap().get("rows")));
			logger.info("start yidajinCheckPage>>>>>>>>>>>>curPage="+curPage+",pageNum="+pageNum);
			if (curPage > 1) {
				curNum = (curPage - 1) * pageNum;
			}
			logger.info("start yidajinCheckPage>>>>>>>>>>>>curNum="+curNum);
			paraMap.put("check_ydjFlag", "1");
			Map<String, Object> map=creditCheckServiceImpl.selectCreditCheckPage(paraMap, curNum, pageNum);
			logger.info("start yidajinCheckPage>>>>>>>>>>>>map="+map==null?null:map.toString());
			context.setDataMap(map);
		}catch (Exception e) {
			logger.info("失败>>>>>>>>>>>>>>>>>>");
			context.setData("success", false);
			e.printStackTrace();
		}
	}
	/**************************
	 *@describe:查询提报原因码
	 *@param context
	 *@throws CoreException
	 *@author xiaoJianFeng
	 *@date:2017-03-30
	 **************************/
	public void selectSubmitReason(Context context)throws CoreException{
		try {
			String appId =context.getDataMap().get("appId")==null?null:String.valueOf(context.getDataMap().get("appId"));
			List<CreditCheck> creditChecks=creditCheckServiceImpl.selectReasonByAppId(appId);
			context.setData("creditChecks",creditChecks);
			context.setData("success",true);
		} catch (Exception e) {
			context.setData("success",false);
			 e.printStackTrace();
		}
	}
	
	/**************************
	 *@describe:申请件获取(根据appId获取一件)
	 *@param ctx
	 *@throws CoreException
	 *@author susha
	 *@date:2017-04-16
	 **************************/
	public void getBizInpAppl(Context ctx) throws CoreException{
		Map<String, Object> map = ctx.getDataMap();
		//获取当前用户
		String userCode = ctx.getData("userId");
		//获取用户所在组
		String userOrg = apUserOrgService.queryApUserOrgByUserCode(userCode);
		map.put("userCode", userCode);
		map.put("userOrg", userOrg);
		try {
			int num = creditCheckServiceImpl.getOne(map);
			if(num>0){
				ctx.setData("isSuccess",true);
			}else{
				ctx.setData("isSuccess",false);
			}
		} catch (Exception e) {
			ctx.setData("isSuccess", false);
			e.printStackTrace();
		}
		
		
	}
	/**************************
	 *@describe:手动抢件(随机获取该组员所在组组长手中未分配的3件)
	 *@param ctx
	 *@throws CoreException
	 *@author susha
	 *@date:2017-04-16
	 **************************/
	public void getBizInpApplHand(Context ctx) throws CoreException{
		Map<String, Object> map = ctx.getDataMap();
		//获取用户名
		String userCode = ctx.getData("userId");
		//获取用户所在组
		String userOrg = apUserOrgService.queryApUserOrgByUserCode(userCode);
		map.put("userCode", userCode);
		map.put("userOrg", userOrg);
		try {
			int num = creditCheckServiceImpl.getThree(map);
			if(num>0){
				ctx.setData("isSuccess",true);
			}else{
				ctx.setData("isSuccess",false);
			}
		} catch (Exception e) {
			ctx.setData("isSuccess", false);
			e.printStackTrace();
		}
	}
	/**************************
	 *@describe:反欺诈审批通过风险名单库自动维护
	 *@param ctx
	 *@throws CoreException
	 *@author susha
	 *@date:2017-05-12
	 **************************/
	public void addRiskList(Context ctx) throws CoreException{
		Map<String, Object> dataMap = ctx.getDataMap();
		try {
			// 获取当前登录名
			String crediter = (String) ctx.getDataMap().get("userCode");
			creditCheckServiceImpl.addRiskList(dataMap,crediter);
			ctx.setData("isSuccess",true);
		} catch (Exception e) {
			ctx.setData("isSuccess",false);
			e.printStackTrace();
			throw e;
		}
	}
	
	public CreditCheckService getCreditCheckServiceImpl() {
		return creditCheckServiceImpl;
	}

	public void setCreditCheckServiceImpl(CreditCheckService creditCheckServiceImpl) {
		this.creditCheckServiceImpl = creditCheckServiceImpl;
	}
	
	/**
	 * @describe:派发协查时间查询
	 * @param context
	 * @throws CoreException
	 */
	public void queryPfxcTime(Context context) throws CoreException{
		String appId =context.getDataMap().get("appId")==null?null:String.valueOf(context.getDataMap().get("appId"));
		Map<String, Object> dataMap = new HashMap<String, Object>();
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {
			list = creditCheckServiceImpl.queryPfxcTime(appId);
			dataMap.put("rows", list);
			dataMap.put("total", "1");
			context.setDataMap(dataMap);
//			context.setData("list", list);
			context.setData("success", true);
		} catch (Exception e) {
			logger.info("失败>>>>>>>>>>>>>>>>>>");
			context.setData("success", false);
			e.printStackTrace();
		}
	}
 
}
