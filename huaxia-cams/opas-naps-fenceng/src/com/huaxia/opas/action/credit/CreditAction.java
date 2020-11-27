package com.huaxia.opas.action.credit;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.google.gson.Gson;
import com.huateng.neofp.core.Action;
import com.huateng.neofp.core.Context;
import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.common.SysConst;
import com.huaxia.opas.domain.allot.AllotApplyAllot;
import com.huaxia.opas.domain.allot.AllotApplyAllotHis;
import com.huaxia.opas.domain.apply.ApplyLifeCicle;
import com.huaxia.opas.domain.archive.SuppArchive;
import com.huaxia.opas.domain.credit.CreditCheck;
import com.huaxia.opas.domain.credit.OpasTelcheckResult;
import com.huaxia.opas.domain.input.BizInpAppl;
import com.huaxia.opas.domain.sysparm.SubReport;
import com.huaxia.opas.service.apply.ApplyLifeCicleService;
import com.huaxia.opas.service.apply.BizInpApplService;
import com.huaxia.opas.service.decision.SysButtonCommonService;
import com.huaxia.opas.service.fico.FicoService;
import com.huaxia.opas.service.rule.InteliVoiceService;
import com.huaxia.opas.service.credit.PatchboltService;
import com.huaxia.opas.util.CommonProperties;
import com.huaxia.opas.util.SequenceUtil;
import com.huaxia.opas.util.StringUtil;

/**********************************
 * @describe:征信功能处理Action
 * @author xiebinxu
 * @date:2017年3月9日
 * @updateAuthor:xiaoJianFeng
 ***********************************/
public class CreditAction implements Action {

	private static Logger logger = LoggerFactory.getLogger(CreditAction.class);

	@Resource(name = "bizInpApplService")
	private BizInpApplService bizInpApplService;
	@Resource(name = "ficoService")
	private FicoService ficoService;
	@Resource(name = "applyLifeCicleService")
	private ApplyLifeCicleService applyLifeCicleService;
	@Resource(name = "sysButtonCommonService")
	private SysButtonCommonService sysButtonCommonService;
	@Resource(name = "patchboltService")
	private PatchboltService patchboltService;
	@Resource(name = "inteliVoiceService")
	private InteliVoiceService inteliVoiceService;
	
	
	public PatchboltService getPatchboltService() {
		return patchboltService;
	}

	public void setPatchboltService(PatchboltService patchboltService) {
		this.patchboltService = patchboltService;
	}

	public FicoService getFicoService() {
		return ficoService;
	}

	public void setFicoService(FicoService ficoService) {
		this.ficoService = ficoService;
	}

	public BizInpApplService getBizInpApplService() {
		return bizInpApplService;
	}

	public void setBizInpApplService(BizInpApplService bizInpApplService) {
		this.bizInpApplService = bizInpApplService;
	}

	@SuppressWarnings("unchecked")
	//此方法  征信已不用， 正信的新方法名为 findZxIndividualQueueData  20170527 gaohui 改
	public void listBizInpAppl(Context ctx) {

		Map<String, Object> map = ctx.getDataMap();
		// 流水号、证件号码包含字母的部分全部转大写
		if (map != null) {
			String lsh = (String) map.get("lsh");
			if (lsh != null && !"".equals(lsh)) {
				lsh = lsh.toUpperCase();
				map.put("lsh", lsh);
			}
			String zjhm = (String) map.get("zjhm");
			if (zjhm != null && !"".equals(zjhm)) {
				zjhm = zjhm.toUpperCase();
				map.put("zjhm", zjhm);
			}
		}
		String checkStr = (String) map.get("checkStr");
		if (checkStr != null && !"".equals(checkStr)) {
			checkStr = checkStr.replace("GROUP_DATE", "b.GROUP_DATE");
			checkStr = checkStr.replace("QUICK_CARD_FLAG", "a.QUICK_CARD_FLAG");
			checkStr = checkStr.replace("APP_ID", "substr(a.APP_ID,7,4),substr(a.APP_ID,6,1),substr(a.APP_ID,1,6)");
			checkStr = checkStr.replace("PATCH_STATUS", "c.PATCH_STATUS");
			checkStr = checkStr.replace("C1_CONAME", "nlssort( a.C1_CONAME,'NLS_SORT=SCHINESE_PINYIN_M')");
			map.put("checkStr", "a.QUICK_CARD_FLAG desc,a.CRT_DATE ," + checkStr.substring(0, checkStr.length() - 1));
		} else {
			map.put("checkStr", "a.QUICK_CARD_FLAG desc,a.CRT_DATE");
		}
		// map.put("currNode","02");
		int curNum = 0;
		int curPage = Integer
				.parseInt(String.valueOf(ctx.getDataMap().get("page") == null ? 0 : ctx.getDataMap().get("page")));
		int pageNum = Integer
				.parseInt(String.valueOf(ctx.getDataMap().get("rows") == null ? 0 : ctx.getDataMap().get("rows")));

		if (curPage > 1) {
			curNum = (curPage - 1) * pageNum;
		}

		List<BizInpAppl> list = new ArrayList<BizInpAppl>();
		int count = 0;
		try {
			count = bizInpApplService.queryCount(map);
		} catch (CoreException e) {
			e.printStackTrace();
		}
		if (count > 0) {
			try {
				list = bizInpApplService.queryList(map, curNum, pageNum);
			} catch (CoreException e) {
				e.printStackTrace();
			}
		}

		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("total", count);
		dataMap.put("rows", list);
		ctx.setDataMap(dataMap);
	}

	/*******************************
	 * @describe:征信页面提报
	 * @author:xiaoJianFeng  2017.5.17 gaohui 改
	 * @date:2017-03-28
	 ******************************/
	public void creditSubmitNewspaper(Context ctx) throws CoreException {
		logger.info("creditSubmitNewspaper>>>>>>>>");
		try {
			Gson gson = new Gson();
			CreditCheck creditCheck = gson.fromJson(gson.toJson(ctx.getDataMap()), CreditCheck.class);
			SubReport subReport = new SubReport();
			subReport.setAppId(creditCheck.getCheck_number());
			subReport.setAutoId(SequenceUtil.getUUID());
			subReport.setCrtDate(new Date());
			subReport.setDelStatus("0");
			subReport.setSubmitType(creditCheck.getCheck_submitType());
			subReport.setYdjFlag(creditCheck.getCheck_ydjFlag());
			subReport.setCrtUser(ctx.getData("userCode").toString());
			bizInpApplService.updateCreditSubmitNewspaper(creditCheck, subReport);
			ctx.setData("success", true);
		} catch (Exception e) {
			ctx.setData("success", false);
			logger.info("creditSubmitNewspaper征信页面提报>>>失败>>>>>>>>");
			e.printStackTrace();
		}
	}

	/**
	 * @Title:creditSubmit
	 * @Description:征信提交功能
	 * @param ctx
	 * @throws CoreException
	 * @author:xiaojianfeng --- gaohui 改
	 * @Date:2017年4月21日上午11:31:30
	 */
	public void creditSubmit(Context ctx) {
		Map<String, Object> htmlMap = new HashMap<String, Object>();
		try {
			Map paramMap = ctx.getDataMap();
			CreditCheck creditCheck = new CreditCheck();
			//creditCheck.setCheck_delStatus(paramMap.get("check_delStatus").toString());
			creditCheck.setCheck_delStatus("1");//征信提交时 人工处理状态改为 1
			creditCheck.setCheck_currNode("02");//征信节点
			creditCheck.setCheck_ydjFlag(paramMap.get("check_ydjFlag").toString());
			creditCheck.setCheck_number(paramMap.get("check_number").toString());
			creditCheck.setCheck_meuoFlag(paramMap.get("check_meuoFlag").toString());//xzg add
			OpasTelcheckResult telcheckResult = new OpasTelcheckResult();
			telcheckResult.setAuto_id(SequenceUtil.getUUID());
			telcheckResult.setCrt_date(new Date());
			telcheckResult.setApp_id(creditCheck.getCheck_number());
			telcheckResult.setYdj_flag(creditCheck.getCheck_ydjFlag());
			telcheckResult.setCrt_user(paramMap.get("userCode").toString());
			// 从配置文件中获取节点nodeId
			Map<String, String> paroperMap = CommonProperties.getParoperMap();
			Map<String, Object> streamMap = new HashMap<String, Object>();
			streamMap.put("appId",paramMap.get("appId").toString());
			String matchingCardFlag = paramMap.get("matchingCardFlag").toString();
			streamMap.put("matchingCardFlag", matchingCardFlag);//当前卡的 套卡 标识
			streamMap.put("process_IP",paroperMap.get("process_IP"));//获取配置ip地址
			String ydjFlag= paramMap.get("ydjFlag").toString();
			streamMap.put("ydjFlag",ydjFlag);
			String SecDecisionFlag = sysButtonCommonService.selectSecDecisionFlag(paramMap.get("appId").toString());
			// 二次决策是否成功标示 
			if("1".equals(SecDecisionFlag)){
				ctx.setData("success", false);
				ctx.setData("msg", "申请件已经提交,请耐心等待。。。");
				return;
			}
			if(ydjFlag.equals("1")){//易达金
				streamMap.put("nodeId", paroperMap.get("nodeId_sc_ydj"));//获取配置节点
				if("0".equals(matchingCardFlag)||"2".equals(matchingCardFlag)){//易达金的主卡 才调fico
					//判断是否为 商品易达金  不是的话 调fico接口
					if(!paramMap.get("appProd").toString().equals("0085")){
						//防止调用fico方法过程中,业务重复点击提交,重复调用fico远程方法
						sysButtonCommonService.updateSecDecisionFlag(paramMap.get("appId").toString(),"1");
						getFicoService().ficoRequest(SysConst.STRATEGY_TYPE_YDJ_1007, paramMap.get("appId").toString(), "1");
						sysButtonCommonService.updateSecDecisionFlag(paramMap.get("appId").toString(),"0");
					}
				}
			}else if(ydjFlag.equals("2")){//标准卡
				streamMap.put("nodeId", paroperMap.get("nodeId_sc_bzk"));//获取配置节点
			}
			streamMap.put("userCode", paramMap.get("userCode").toString());
			streamMap.put("specialExamineUser", paramMap.get("specialExamineUser").toString());
			//征审合一 add xuzhiguo 调用二次决策
			String zshyResult = "";
			boolean jbpm = false;
			if("2".equals(creditCheck.getCheck_meuoFlag())){
				logger.info("征审合一模块====调用二次决策===start");
				streamMap.put("isBack", "3");
				streamMap.put("zshyInnerCheck",paramMap.get("zshyInnerCheck").toString());
				AllotApplyAllot applyAllot = sysButtonCommonService.selectByPrimaryKey(paramMap.get("appId").toString());
				if(applyAllot!=null&&"1".equals(applyAllot.getYdjFlag())){
					logger.info("征审合一模块====调用二次决策===易达金");
					sysButtonCommonService.updateSecDecisionFlag(paramMap.get("appId").toString(),"1");
					zshyResult = invokeYdj(applyAllot);
					//调用流程，提前结束   2 自动拒绝  3 自动核准 
					if("2".equals(zshyResult)||"3".equals(zshyResult)){
						logger.info(paramMap.get("appId").toString()+"二次决策返回值为"+zshyResult);
						jbpm = true;
					}
				}else if(applyAllot!=null&&"2".equals(applyAllot.getYdjFlag())){
					logger.info("征审合一模块====调用二次决策===标准卡");
					sysButtonCommonService.updateSecDecisionFlag(paramMap.get("appId").toString(),"1");
					zshyResult = invokeBzk(applyAllot);
					//调用流程，提前结束  2 自动拒绝  3 自动核准 
					if("2".equals(zshyResult)||"3".equals(zshyResult)){
						logger.info(paramMap.get("appId").toString()+"二次决策返回值为"+zshyResult);
						jbpm = true;
					}
				}
				streamMap.put("zshyResult", zshyResult);
				streamMap.put("jbpm", jbpm);
				logger.info("征审合一模块====调用二次决策===end");
			}
			htmlMap=bizInpApplService.updateCreditSubmitZx(creditCheck,telcheckResult,streamMap);
			if("2".equals(htmlMap.get("flag"))){
				if("1".equals(zshyResult)&&htmlMap.get("tkAppId")!=null&&!"".equals(htmlMap.get("tkAppId"))){
					//获得其对应的套卡信息
					AllotApplyAllot applyAllotFk = sysButtonCommonService.selectAllotAndAppProdByAppId(htmlMap.get("tkAppId").toString());
					ctx.setData("applyAllot", applyAllotFk);
					if("03".equals(applyAllotFk.getCurrNode())){
						zshyResult = "11";
					}
				}else if("1".equals(zshyResult)){
					zshyResult = "11";
				}
			}
			//===============================
			ctx.setData("zshyResult", zshyResult);
			ctx.setData("success", true);
			ctx.setData("msg", htmlMap.get("msg"));
		} catch (Exception e) {
			ctx.setData("success", false);
			ctx.setData("msg", e.getMessage());
			logger.error("征信提交出错,操作员为"+ctx.getDataMap().get("userCode").toString()+";流水号为"+ctx.getDataMap().get("appId").toString(),e);
		}
	}
	/**
	 * 易达金二次决策
	 * @param VariableMap
	 * @throws Exception
	 */
	public String invokeYdj(AllotApplyAllot applyAllot) throws Exception  {
		String appId = applyAllot.getAppId() ==null ?"":applyAllot.getAppId();
		String ydjFlag = applyAllot.getYdjFlag()==null?"":applyAllot.getYdjFlag();
		String taoflag = applyAllot.getMatchingCardFlag()==null?"":applyAllot.getMatchingCardFlag();
		String ret="";
		try {
			if ("2".equals(taoflag) || "0".equals(taoflag)){
				ret = ficoService.ficoRequest("YDJ0600", appId, "1");
			} else {
				ret = ficoService.ficoRequest("SD0800", appId, "2");
			}
		} catch (Exception e) {
			logger.error("征信提交二次决策调用出错,操作员为"+applyAllot.getCurrOptUser()+";流水号为"+applyAllot.getAppId(),e);
		} 
		sysButtonCommonService.updateSecDecisionFlag(applyAllot.getAppId(),"0");
		ApplyLifeCicle a=new ApplyLifeCicle();
		a.setAppId(appId);
		a.setOperater("system");
		a.setOperateDesc("二次决策");
		a.setOperateResult("完成");
		a.setCrtDate(new Date());
		a.setCrtUser("system");
		a.setUuid(StringUtil.randomUUIDPlainString());
		applyLifeCicleService.addApplyLifeCicle(a);
		return ret;
	}
	/**
	 *标准卡二次决策 
	 */
	public String invokeBzk(AllotApplyAllot applyAllot) throws Exception {
		String appId = applyAllot.getAppId() ==null ?"":applyAllot.getAppId();
		String ydjFlag = applyAllot.getYdjFlag()==null?"":applyAllot.getYdjFlag();
		String taoflag = applyAllot.getMatchingCardFlag()==null?"":applyAllot.getMatchingCardFlag();
		String ret="";
		try {
			ret = ficoService.ficoRequest("SD0800",appId,ydjFlag);
		} catch (Exception e) {
			logger.error("征信提交二次决策调用出错,操作员为"+applyAllot.getCurrOptUser()+";流水号为"+applyAllot.getAppId(),e);
		}
		sysButtonCommonService.updateSecDecisionFlag(applyAllot.getAppId(),"0");
		ApplyLifeCicle a=new ApplyLifeCicle();
		a.setAppId(appId);
		a.setOperater("system");
		a.setOperateDesc("二次决策");
		a.setOperateResult("完成");
		a.setCrtDate(new Date());
		a.setCrtUser("system");
		a.setUuid(StringUtil.randomUUIDPlainString());
		applyLifeCicleService.addApplyLifeCicle(a);
		return ret;
	}
	/**
	 *@Title:findZxIndividualQueueData
	 *@Description:征信个人队列的查询方法 （未完成，待补件，退回）
	 *@param ctx
	 *@author: gaohui
	 *@Date:2017年5月27日上午11:20:47
	 */
	public void findZxIndividualQueueData(Context ctx){
		Map<String, Object> map = ctx.getDataMap();
		String checkStr = (String) map.get("checkStr");
		String  orderByStr="";
		if(checkStr != null&& !"".equals(checkStr)){
			if("GROUP_DATE".equals(checkStr)){//进入队列天数
				orderByStr=" QUICK_CARD_FLAG desc,GRODATE desc,TRUNC(LST_TEAM_DATE,'MI') asc,substr(APP_ID,8,3) asc,substr(APP_ID,7,1) desc,substr(APP_ID,1,6) asc,substr(APP_ID,11,5) asc,substr(APP_ID,16,1) desc";
			}else if("APP_ID".equals(checkStr)){//流水号
				orderByStr=" QUICK_CARD_FLAG desc,substr(APP_ID,8,3) asc,substr(APP_ID,7,1) desc,substr(APP_ID,1,6) asc,substr(APP_ID,11,5) asc,substr(APP_ID,16,1) desc,TRUNC(LST_TEAM_DATE,'MI')";
			}else if("PATCH_STATUS".equals(checkStr)){//补件完成
				orderByStr=" QUICK_CARD_FLAG desc,PATCHORDER desc,TRUNC(LST_TEAM_DATE,'MI') asc,substr(APP_ID,8,3) asc,substr(APP_ID,7,1) desc,substr(APP_ID,1,6) asc,substr(APP_ID,11,5) asc,substr(APP_ID,16,1) desc";
			}else if("C1_CONAME".equals(checkStr)){//单位名称
				orderByStr=" QUICK_CARD_FLAG desc,NLSSORT(C1_CONAME,'NLS_SORT=SCHINESE_PINYIN_M'),TRUNC(LST_TEAM_DATE,'MI') asc,substr(APP_ID,8,3) asc,substr(APP_ID,7,1) desc,substr(APP_ID,1,6) asc,substr(APP_ID,11,5) asc,substr(APP_ID,16,1) desc";
			}
		}else{//前台没选checkbox
			orderByStr=" QUICK_CARD_FLAG desc,GRODATE desc,TRUNC(LST_TEAM_DATE,'MI') asc,substr(APP_ID,8,3) asc,substr(APP_ID,7,1) desc,substr(APP_ID,1,6) asc,substr(APP_ID,11,5) asc,substr(APP_ID,16,1) desc";
		}
		map.put("checkStr",orderByStr);
		int curNum = 0;
		int curPage = Integer
				.parseInt(String.valueOf(ctx.getDataMap().get("page") == null ? 0 : ctx.getDataMap().get("page")));
		int pageNum = Integer
				.parseInt(String.valueOf(ctx.getDataMap().get("rows") == null ? 0 : ctx.getDataMap().get("rows")));

		if (curPage > 1) {
			curNum = (curPage - 1) * pageNum;
		}

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		int count= bizInpApplService.findZxIndividualQueueDataCount(map);
		if (count > 0) {
				list = bizInpApplService.findZxIndividualQueueDataList(map, curNum, pageNum);
		}
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("total", count);
		dataMap.put("rows", list);
		ctx.setDataMap(dataMap);
	}
	/**
	 * @Title:FindBizInpApplZxListFinished
	 * @Description: 获取征信已完成队列所需数据
	 * @param ctx
	 * @author: gaohui
	 * @Date:2017年4月15日下午3:43:11
	 */
	public void findBizInpApplZxListFinished(Context ctx) {

		Map map=ctx.getDataMap();
		map.put("lastOptUser", map.get("userId"));//已完成队列 查出的数据是根据这个字段     lastOptUser（上次操作人员）
		String checkStr=(String) map.get("checkStr");
		String  orderByStr="";
		if(checkStr != null&& !"".equals(checkStr)){
			if("GROUP_DATE".equals(checkStr)){//进入队列天数
				orderByStr=" QUICK_CARD_FLAG desc,GRODATE desc,TRUNC(LST_TEAM_DATE,'MI') asc,substr(APP_ID,8,3) asc,substr(APP_ID,7,1) desc,substr(APP_ID,1,6) asc,substr(APP_ID,11,5) asc,substr(APP_ID,16,1) desc";
			}else if("APP_ID".equals(checkStr)){//流水号
				orderByStr=" QUICK_CARD_FLAG desc,substr(APP_ID,8,3) asc,substr(APP_ID,7,1) desc,substr(APP_ID,1,6) asc,substr(APP_ID,11,5) asc,substr(APP_ID,16,1) desc,TRUNC(LST_TEAM_DATE,'MI')";
			}else if("PATCH_STATUS".equals(checkStr)){//补件完成
				orderByStr=" QUICK_CARD_FLAG desc,PATCHORDER desc,TRUNC(LST_TEAM_DATE,'MI') asc,substr(APP_ID,8,3) asc,substr(APP_ID,7,1) desc,substr(APP_ID,1,6) asc,substr(APP_ID,11,5) asc,substr(APP_ID,16,1) desc";
			}else if("C1_CONAME".equals(checkStr)){//单位名称
				orderByStr=" QUICK_CARD_FLAG desc,NLSSORT(C1_CONAME,'NLS_SORT=SCHINESE_PINYIN_M'),TRUNC(LST_TEAM_DATE,'MI') asc,substr(APP_ID,8,3) asc,substr(APP_ID,7,1) desc,substr(APP_ID,1,6) asc,substr(APP_ID,11,5) asc,substr(APP_ID,16,1) desc";
			}
		}else{//前台没选checkbox
			orderByStr=" QUICK_CARD_FLAG desc,GRODATE desc,TRUNC(LST_TEAM_DATE,'MI') asc,substr(APP_ID,8,3) asc,substr(APP_ID,7,1) desc,substr(APP_ID,1,6) asc,substr(APP_ID,11,5) asc,substr(APP_ID,16,1) desc";
		}
		map.put("checkStr",orderByStr);
		int curNum = 0;
		int curPage = Integer
				.parseInt(String.valueOf(ctx.getDataMap().get("page") == null ? 0 : ctx.getDataMap().get("page")));
		int pageNum = Integer
				.parseInt(String.valueOf(ctx.getDataMap().get("rows") == null ? 0 : ctx.getDataMap().get("rows")));

		if (curPage > 1) {
			curNum = (curPage - 1) * pageNum;
		}
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		int count = 0;
		try {
			count = bizInpApplService.findBizInpApplZxListFinishedCount(map);

			if (count > 0) {
				list = bizInpApplService.findBizInpApplZxListFinishedList(map, curNum, pageNum);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("total", count);
		dataMap.put("rows", list);
		ctx.setDataMap(dataMap);
	}

	/**
	 * @Title:updateZxApplyGainBzk
	 * @Description:征信标准卡 申请件获取
	 * @param ctx
	 * @author: gaohui
	 * @Date:2017年4月17日下午3:50:28
	 */
	public void updateZxApplyGainBzk(Context ctx) {
		/**
		 * 一 、标准卡 1.ydjFlag appId ————>opas_allot_apply_allot ————>curr_status=0
		 * --->此数据 insert opas_allot_apply_allot_his 表 ---》更新
		 * opas_allot_apply_allot的curr_opt_queue 为 查 OPAS_BZK_SYSRESULT_INFO表 的
		 * credit_decision_result 不为空 截取 -前部分 更新curr_opt_queue字段
		 * 为空不更新curr_opt_queue字段 --->根据前台 user_id 查表opas_ap_user_org 的 org_id
		 * --->查表 opas_ap_org（用户组管理） 表 获取 org_no 更新 curr_opt_group --->更新
		 * curr_opt_user ==user_code(gaohui) 2.ydjFlag appId
		 * ————>opas_allot_apply_allot ————>curr_status=1 --->此数据 insert
		 * opas_allot_apply_allot_his 表 --->更新 opas_allot_apply_allot --->根据前台
		 * user_id 查表opas_ap_user_org 的 org_id --->查表 opas_ap_org 表 获取 org_no 更新
		 * curr_opt_group --->更新 curr_opt_user
		 * ==user_code(gaohui)--->curr_status更改为3
		 */
		Map map = ctx.getDataMap();
		Map<String, Object> dataMap = new HashMap<String, Object>();// 存放向前台提示的内容
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("appId", map.get("appId"));
		paraMap.put("ydjFlag", map.get("ydjFlag"));
		paraMap.put("userId", map.get("userId"));// 用户名 admin 。。。
		paraMap.put("userUuid", map.get("userUuid"));// 用户 的 uuid （主键 id）
		paraMap.put("currNode","02");// 人工节点 征信 02
		paraMap.put("userDate",new Date());// 手动抢件附上 userDate时间（这个件分配到人的时间）
		// System.err.println(map);
		try {
			Map<String, Object> msgMap = bizInpApplService.updateZxApplyGainBzk(paraMap);
			ctx.setDataMap(msgMap);
		} catch (Exception e) {
			e.printStackTrace();
			dataMap.put("msg", "数据异常。申请件获取失败。");
			ctx.setDataMap(dataMap);
		}
	}

	/**
	 * @Title:updateZxApplyGainYdj
	 * @Description:征信易达金 申请件获取
	 * @param ctx
	 * @author: gaohui
	 * @Date:2017年4月18日上午10:40:20
	 */
	public void updateZxApplyGainYdj(Context ctx) {
		/**
		 * 二、易达金 1.ydjFlag appId ————>opas_allot_apply_allot ————>curr_status=0
		 * 或1 --->此数据 insert opas_allot_apply_allot_his 表 --->更新
		 * opas_allot_apply_allot ---> 根据前台 user_id 查表opas_ap_user_org 的 org_id
		 * --->查表 opas_ap_org 表 获取 org_no 更新 curr_opt_group --->更新 curr_opt_user
		 * ==user_code(gaohui)--->curr_status更改为3
		 */
		Map map = ctx.getDataMap();
		Map<String, Object> dataMap = new HashMap<String, Object>();// 存放向前台提示的内容
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("appId", map.get("appId"));
		paraMap.put("ydjFlag", map.get("ydjFlag"));
		paraMap.put("userId", map.get("userId"));// 用户名 admin 。。。
		paraMap.put("userUuid", map.get("userUuid"));// 用户 的 uuid （主键 id）
		paraMap.put("currNode","02");// 人工节点 征信 02
		paraMap.put("userDate",new Date());// 手动抢件附上 userDate时间（这个件分配到人的时间）
		try {
			Map<String, Object> msgMap = bizInpApplService.updateZxApplyGainYdj(paraMap);
			ctx.setDataMap(msgMap);
		} catch (Exception e) {
			e.printStackTrace();
			dataMap.put("msg", "数据异常。申请件获取失败。");
			ctx.setDataMap(dataMap);
		}
	}

	/**
	 * @Title:updateZxApplyRobBzkYdj
	 * @Description:征信页面 未完成队列Bzk Ydj 手动抢件
	 * @param ctx
	 * @author: gaohui
	 * @Date:2017年4月18日上午11:31:24
	 */
	public void updateZxApplyRobBzkYdj(Context ctx) {
		Map map = ctx.getDataMap();
		Map<String, Object> dataMap = new HashMap<String, Object>();// 存放向前台提示的内容
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("userId", map.get("userId"));// 用户名 admin 。。。
		paraMap.put("userUuid", map.get("userUuid"));// 用户 的 uuid （主键 id）
		paraMap.put("currNode", "02");// 人工节点 征信 02
		paraMap.put("currStatus", "1");// 分配状态 1未分配未挂起
		paraMap.put("ydjFlag",map.get("ydjFlag"));// 用来判别抢那种申请件 （ydj或bzk）
		paraMap.put("userDate",new Date());// 手动抢件附上 userDate时间（这个件分配到人的时间）
		paraMap.put("applyRobNum",map.get("applyRobNum"));// 抢的申请件数量 
		// System.err.println(map);
		try {
			Map<String, Object> msgMap = bizInpApplService.updateZxApplyRobBzkYdj(paraMap);
			ctx.setDataMap(msgMap);
			ctx.setData("success", msgMap.get("success"));
		} catch (Exception e) {
			e.printStackTrace();
			ctx.setData("success", false);
			dataMap.put("msg", "数据异常。手动抢件失败。");
			ctx.setDataMap(dataMap);
		}
	}

	/**
	 * @Title:findBizInpApplZxListHangUp
	 * @Description:获取征信挂起队列所需数据
	 * @param ctx
	 * @author: gaohui
	 * @Date:2017年4月19日下午2:59:42
	 */
	public void findBizInpApplZxListHangUp(Context ctx) {
		Map map = ctx.getDataMap();
		String checkStr = (String) map.get("checkStr");
		String  orderByStr="";
		if(checkStr != null&& !"".equals(checkStr)){
			if("GROUP_DATE".equals(checkStr)){//进入队列天数
				orderByStr=" QUICK_CARD_FLAG desc,GRODATE desc,TRUNC(LST_TEAM_DATE,'MI') asc,substr(APP_ID,8,3) asc,substr(APP_ID,7,1) desc,substr(APP_ID,1,6) asc,substr(APP_ID,11,5) asc,substr(APP_ID,16,1) desc";
			}else if("APP_ID".equals(checkStr)){//流水号
				orderByStr=" QUICK_CARD_FLAG desc,substr(APP_ID,8,3) asc,substr(APP_ID,7,1) desc,substr(APP_ID,1,6) asc,substr(APP_ID,11,5) asc,substr(APP_ID,16,1) desc,TRUNC(LST_TEAM_DATE,'MI')";
			}else if("PATCH_STATUS".equals(checkStr)){//补件完成
				orderByStr=" QUICK_CARD_FLAG desc,PATCHORDER desc,TRUNC(LST_TEAM_DATE,'MI') asc,substr(APP_ID,8,3) asc,substr(APP_ID,7,1) desc,substr(APP_ID,1,6) asc,substr(APP_ID,11,5) asc,substr(APP_ID,16,1) desc";
			}else if("C1_CONAME".equals(checkStr)){//单位名称
				orderByStr=" QUICK_CARD_FLAG desc,NLSSORT(C1_CONAME,'NLS_SORT=SCHINESE_PINYIN_M'),TRUNC(LST_TEAM_DATE,'MI') asc,substr(APP_ID,8,3) asc,substr(APP_ID,7,1) desc,substr(APP_ID,1,6) asc,substr(APP_ID,11,5) asc,substr(APP_ID,16,1) desc";
			}
		}else{//前台没选checkbox
			orderByStr=" QUICK_CARD_FLAG desc,GRODATE desc,TRUNC(LST_TEAM_DATE,'MI') asc,substr(APP_ID,8,3) asc,substr(APP_ID,7,1) desc,substr(APP_ID,1,6) asc,substr(APP_ID,11,5) asc,substr(APP_ID,16,1) desc";
		}
		map.put("checkStr",orderByStr);
		int curNum = 0;
		int curPage = Integer
				.parseInt(String.valueOf(ctx.getDataMap().get("page") == null ? 0 : ctx.getDataMap().get("page")));
		int pageNum = Integer
				.parseInt(String.valueOf(ctx.getDataMap().get("rows") == null ? 0 : ctx.getDataMap().get("rows")));

		if (curPage > 1) {
			curNum = (curPage - 1) * pageNum;
		}
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		int count = 0;
		try {
			count = bizInpApplService.findBizInpApplZxListHangUpCount(map);

			if (count > 0) {
				list = bizInpApplService.findBizInpApplZxListHangUpList(map, curNum, pageNum);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("total", count);
		dataMap.put("rows", list);
		ctx.setDataMap(dataMap);
	}

	/**
	 * @Title:findZxExamineOperationPerson
	 * @Description:征信 易达金获取特定审批人 下拉框
	 * @param ctx
	 * @author: gaohui
	 * @Date:2017年4月20日上午10:48:11
	 */
	public void findZxExamineOperationPerson(Context ctx) {
		Map<String, Object> map = ctx.getDataMap();
		List<Map<String, Object>> htmlListMap = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> listApUser = bizInpApplService.findZxExamineOperationPerson(map);
		Map<String, Object> mapUse = new HashMap<String, Object>();
		mapUse.put("dictDetailCode", "");
		mapUse.put("dictDetailName", "");
		htmlListMap.add(mapUse);
		if (!listApUser.isEmpty()) {
			htmlListMap.addAll(listApUser);
		}
		ctx.setData("listMap", htmlListMap);
	}
	

	/**
	 * @Title:updateZxApplyYdjHangUp
	 * @Description:征信申请件易达金未完成队列 挂起按钮 ,将所选择的申请件挂起
	 * @param ctx
	 * @author: gaohui
	 * @Date:2017年4月22日上午11:06:09
	 */
	public void updateZxApplyYdjHangUp(Context ctx) {
		Map map = ctx.getDataMap();
		List<Map<String, Object>> listAppIdMap = (List<Map<String, Object>>) map.get("listAppId");
		try {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("listAppId", listAppIdMap);
			paramMap.put("currStatus", "4");
			paramMap.put("lstTeamDate",new Date());//更新 上次操作日期上次操作日期
			paramMap.put("userCode", map.get("userId"));
			paramMap.put("lastOptUser", map.get("userId"));//更新最后一次操作人
			paramMap.put("guaQiBeiZhu", map.get("guaQiBeiZhu"));//add by qingfeng.xiu 20190108 cause:增加挂起备注 
			bizInpApplService.updateZxApplyYdjHangUp(paramMap);
			ctx.setData("msg", "所选申请件已成功挂起。");
			ctx.setData("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			ctx.setData("msg", "所选申请件挂起失败。");
		}
	}
	/**
	 *@Title:updateZxApplyYdjHangUpCancle
	 *@Description:征信挂起队列的解挂功能 
	 *@param ctx
	 *@author: gaohui
	 *@Date:2017年5月16日上午11:04:43
	 */
	public void updateZxApplyYdjHangUpCancle(Context ctx) {
		Map map = ctx.getDataMap();
		List<Map<String, Object>> listAppIdMap = (List<Map<String, Object>>) map.get("listAppId");
		try {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("listAppId", listAppIdMap);
			paramMap.put("currStatus", "3");//分配状态 改为 已分配未挂起 
			paramMap.put("userCode", map.get("userId").toString());
			paramMap.put("lastOptUser", map.get("userId"));//更新最后一次操作人
			bizInpApplService.updateZxApplyYdjHangUpCancle(paramMap);
			ctx.setData("msg", "所选申请件已成功挂起。");
			ctx.setData("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			ctx.setData("msg", "所选申请件挂起失败。");
		}
	}
	/**
	 * @Description:征信电核环节易达金工作量统计
	 * @param ctx
	 * @author: susha
	 * @Date:2017年5月3日
	 */
	public void queryYDJWorkcount(Context ctx) {
		// 获取查询时间
		String startTime = ctx.getData("startTime");
		String endTime = ctx.getData("endTime");
		// 获取当前登录名
		String crediter = ctx.getData("userId");
		String ydjFlag = ctx.getData("ydjFlag");

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		Date start = null;
		Date end = null;

		try {
			start = sdf.parse(startTime);
			end = sdf.parse(endTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("crediter", crediter);
		map.put("start", start);
		map.put("end", end);
		map.put("ydjFlag", ydjFlag);
		// 调用service方法
		int count1 = bizInpApplService.queryYDJFinishedCount(map);
		int count2 = bizInpApplService.queryYDJUnfinishedCount(map);
		int count3 = bizInpApplService.queryYDJSub2Other(map);

		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("total1", count1 + "");
		dataMap.put("total2", count2 + "");
		dataMap.put("total3", count3 + "");

		ctx.setDataMap(dataMap);
	}

	/**
	 *@Title:updateZxApplyFinishedTakeBack
	 *@Description:征信已完成队列回收按钮
	 *@param ctx
	 *@author: gaohui
	 *@Date:2017年4月24日下午3:19:53
	 */
	public void updateZxApplyFinishedTakeBack(Context ctx){
		Map map=ctx.getDataMap();
		List<Map<String,Object>> listAppIdMap=(List<Map<String,Object>>)map.get("listAppId");
		try {
			Map<String,Object> paramMap=new HashMap<String,Object>();
			paramMap.put("listAppId", listAppIdMap);
			String ydjFlag= map.get("ydjFlag").toString();
			// 从配置文件中获取节点nodeId
			Map<String, String> paroperMap = CommonProperties.getParoperMap();
			paramMap.put("process_IP",paroperMap.get("process_IP"));//获取配置ip地址
			if(ydjFlag.equals("1")){//易达金
			paramMap.put("nodeId", paroperMap.get("nodeId_sp_ydj"));//获取配置节点
			}else if(ydjFlag.equals("2")){//标准卡
			paramMap.put("nodeId", paroperMap.get("nodeId_sp_bzk"));//获取配置节点
			}
		/*	paramMap.put("currStatus", "3");
			paramMap.put("currNode", "02");
			paramMap.put("currOptUser",map.get("userId").toString());//改一下 当前操作人  
			Date curDate=new Date();
			paramMap.put("crtTeamDate",curDate);//改一下 创建日期
			paramMap.put("lstTeamDate",curDate);//改一下 上一次操作日期
			paramMap.put("userDate",curDate);//改一下 当前操作员日期
			*/
			String specialExamineUser="4-";//已完成收回操作  调流后使用
			specialExamineUser=specialExamineUser+map.get("userId").toString();
			paramMap.put("specialExamineUser", specialExamineUser);
			paramMap.put("userId",map.get("userId").toString());
			Map<String,Object>htmlMap=bizInpApplService.updateZxApplyFinishedTakeBack(paramMap);
			ctx.setData("msg", htmlMap.get("msg"));
			ctx.setData("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			ctx.setData("msg","系统繁忙，请稍后再试。");
		}
	}
	/**
	 *@Title:findExamineLibraryCheckMessage
	 *@Description:征信调查页面 获取审查库 校验信息 
	 *@param ctx
	 *@author: gaohui
	 *@Date:2017年8月2日下午2:32:03
	 */
	public void findExamineLibraryCheckMessage(Context ctx){
		Map map = ctx.getDataMap();
	    Map<String,String>examineMap=bizInpApplService.findExamineLibraryCheckMessage(map);
		if(examineMap!=null){
		ctx.setData("success", true);
	    ctx.setData("examineMap", examineMap);
		}else{
		ctx.setData("success", false);
		}
	}
	/**
	 *@Title:findPatchBoltTwoReasonJudge
	 *@Description:判断征信提交二次补件是否进行过原因选择
	 *@param ctx
	 *@author: gaohui
	 *@Date:2017年8月21日下午3:33:52
	 */
	public void findPatchBoltTwoReasonJudge(Context ctx){
		Map<String,Object> map = ctx.getDataMap();
		String appId=map.get("appId").toString();
		SuppArchive suppArchive = patchboltService.selectByAppId(appId);//调用了  补件的查询方法 （若要改此查询 请重新写service方法，以免影响补件模块查询）
		if(suppArchive!=null){
			if(suppArchive.getPatchStatus()!=null&&!"".equals(suppArchive.getPatchStatus())){//进行过补件完成的 原因选择 
				ctx.setData("success", true);
			}else{
				ctx.setData("success", false);
			}	
		}else{
			ctx.setData("success", false);
		}
	}
	
	
	/**
	 * queryQrcodeList
	 * @Description:征信 审批提交二维码名单库匹配 只有单位电话区号无法匹配上时把公司名称取出来展示
	 * @param ctx
	 * @author: andong
	 * @Date:2020年4月26日
	 */
	public void queryQrcodeList(Context ctx) {
		Map<String, Object> map = ctx.getDataMap();
		Map<String, Object> resultMap = bizInpApplService.queryQrcodeList(map);
		ctx.setData("success", true);
		ctx.setData("resultMap", resultMap);
	}
	
	/**
	 * queryForeignCheckByAppId
	 * @Description:征信 提交获取公安信息查询结果
	 * @param ctx
	 * @author: lhy
	 * @Date:2020年8月1日
	 */
	public void queryForeignCheckByAppId(Context ctx) {
		Map<String, Object> dataMap = ctx.getDataMap();
		try{
			String appId = (String) dataMap.get("appId");
			List<Map<String, String>> resultMap = sysButtonCommonService.queryForeignCheckByAppId(appId);
			if(resultMap!=null){
				//判断获取第一条数据
				if (resultMap.get(0)!=null){
					ctx.setData("c1Idtype", resultMap.get(0).get("c1Idtype"));
					ctx.setData("c2Idtype", resultMap.get(0).get("c2Idtype"));
					if(resultMap.get(0).get("cardFlag").equals("0")){
						ctx.setData("idCheckRst1", resultMap.get(0).get("idCheckRst"));
					}else if(resultMap.get(0).get("cardFlag").equals("1")){
						ctx.setData("idCheckRst2", resultMap.get(0).get("idCheckRst"));
					}
				}else{
					logger.error("获取外国人公安信息查询数据异常,无法在申请件表中查到数据,条码为[ {} ]",appId);
					ctx.setData("success", false);
				}
				//判断获取第二条数据
				if (resultMap.size()>1&&resultMap.get(1)!=null){
					if(resultMap.get(1).get("cardFlag").equals("0")){
						ctx.setData("idCheckRst1", resultMap.get(1).get("idCheckRst"));
					}else if(resultMap.get(1).get("cardFlag").equals("1")){
						ctx.setData("idCheckRst2", resultMap.get(1).get("idCheckRst"));
					}
				}
			}else{
				logger.error("获取外国人公安信息查询数据异常,无法在申请件表中查到数据,条码为[ {} ]",appId);
				ctx.setData("success", false);
			}
			ctx.setData("success", true);
		}catch(Exception e){
			logger.error("获取外国人公安信息查询数据异常",e);
			ctx.setData("success", false);
		}
	}

	/**
	 * 申请件进入智能语音复核队列
	 * 
	 */
	@SuppressWarnings("unchecked")
	public void creditApplySVoice(Context ctx){
		Map paramMap = ctx.getDataMap();
		Map<String, Object> htmlMap = new HashMap<String, Object>();
		try {
		   String appId=paramMap.get("check_number").toString();
           try{// 判断智能语音是否能够进入智能语音队列，调用锦峰的接口
        	   logger.info("申请件:{}[智能语音]查询规则是否能够进入复核队列",appId);
    			Map<String,Object> outMap = inteliVoiceService.invoke(appId, "TOPUI"); 
    			String rule_znyy = "";
    			if(null != outMap && null != outMap.get("voice_rule_out")){
    				rule_znyy = ((List<String>) outMap.get("voice_rule_out")).get(0);
    				logger.info("申请件:{}[智能语音]查询规则结果为:{}",appId,rule_znyy);
    				if(!"1".equals(rule_znyy)){
    					ctx.setData("success", false);
    					ctx.setData("msg", appId+"申请件不符合发起智能语音条件");
    					logger.info(appId+"申请件不符合发起智能语音条件");
    					return;
    				}
    			}
    			logger.info("申请件:{}[智能语音]查询规则结束",appId);
            }catch(Exception e){
				ctx.setData("success", false);
				ctx.setData("msg", appId+"申请件查询是否能发起智能语音异常");
				logger.error("申请件{}查询是否能发起智能语音异常",appId,e);
				return;
            }
			//继续
			CreditCheck creditCheck = new CreditCheck();
			//creditCheck.setCheck_delStatus(paramMap.get("check_delStatus").toString());
			creditCheck.setCheck_delStatus("0");//征信提交到智能语音未完成队列
			creditCheck.setCheck_currNode("07");
			creditCheck.setCheck_ydjFlag(paramMap.get("check_ydjFlag").toString());
			creditCheck.setCheck_number(paramMap.get("check_number").toString());
			creditCheck.setCheck_meuoFlag(paramMap.get("check_meuoFlag").toString());//xzg add
			// 从配置文件中获取节点nodeId
			Map<String, String> paroperMap = CommonProperties.getParoperMap();
			Map<String, Object> streamMap = new HashMap<String, Object>();
			streamMap.put("appId",paramMap.get("check_number").toString());
			String matchingCardFlag = paramMap.get("matchingCardFlag").toString();
			streamMap.put("matchingCardFlag", matchingCardFlag);//当前卡的 套卡 标识
			streamMap.put("process_IP",paroperMap.get("process_IP"));//获取配置ip地址
			String ydjFlag= paramMap.get("ydjFlag").toString();
			streamMap.put("ydjFlag",ydjFlag);
			streamMap.put("nodeId", paroperMap.get("nodeId_sc_bzk"));//标准卡,获取配置节点
			streamMap.put("userCode", paramMap.get("userCode").toString());
			streamMap.put("specialExamineUser", paramMap.get("specialExamineUser").toString());
			htmlMap=bizInpApplService.updateApplyZxToSVoice(creditCheck,streamMap);
			ctx.setData("success", true);
			ctx.setData("msg", htmlMap.get("msg"));
		} catch (Exception e) {
			ctx.setData("success", false);
			ctx.setData("msg", e.getMessage());
			logger.error("申请件:{}[智能语音]查询规则异常",paramMap.get("check_number"),e);
		}
	}
    /**
     * 智能语音复核队列提交
     * @param ctx
     */
	public void creditSVoiceSubmit(Context ctx){
		Map paramMap = ctx.getDataMap();
		try{
			String   appIdStr = (String) ctx.getDataMap().get("appIds");
			String   isBatch = (String) ctx.getDataMap().get("batchFlag");
			String[] appIds=appIdStr.split("\\|");
            List<String> fail = new ArrayList<>();
			if(null==appIdStr||"".equals(appIdStr)||null==appIds||appIds.length==0){
				ctx.setData("success", false);
				ctx.setData("msg", "请先选中数据");
				return ;
			}
			for(String appId:appIds){
				Map<String,Object> resMap = new HashMap<>();
				try{
					CreditCheck creditCheck = new CreditCheck();
					AllotApplyAllot applyAllot = sysButtonCommonService.selectByPrimaryKey(appId);
					creditCheck.setCheck_number(appId);
					creditCheck.setCheck_synFlag("1");	
					Map<String, String> paroperMap = CommonProperties.getParoperMap();// 从配置文件中获取节点nodeId
					Map<String, Object> streamMap = new HashMap<String, Object>();
					streamMap.put("appId",appId);
					streamMap.put("check_meuoFlag", applyAllot.getCheck_meuoFlag());
					streamMap.put("matchingCardFlag", applyAllot.getMatchingCardFlag());
					streamMap.put("process_IP", paroperMap.get("process_IP"));
					streamMap.put("ydjFlag", paramMap.get("ydjFlag").toString());
					streamMap.put("nodeId", paroperMap.get("nodeId_sc_bzk"));// 标准卡,获取配置节点
					streamMap.put("userCode", paramMap.get("userCode").toString());
					streamMap.put("isBatch", isBatch); // 抽检/批量标识
					resMap = bizInpApplService.updateApplySVoiceToAppro(creditCheck,streamMap);
				}catch(Exception e){
					logger.error("申请件:{}发起语音征信->审批异常",appId,e);
				}
			    if(resMap!=null&&resMap.containsKey("result")){
			    	boolean result = (boolean)resMap.get("result");
			    	if(!result){fail.add(appId);}
			    }else{
			    	fail.add(appId);
			    }
			}
			ctx.setData("success", true);
			if(fail.size()>0)ctx.setData("fail",fail.toString());
		}catch(Exception e){
			logger.error("[智能语音]抽检/批量提交复核队列失败",e);
			ctx.setData("success", false);
			ctx.setData("msg", "提交异常" + e.getMessage());
		}
	}
    /** 
     * 智能语音复核队列退回征信
     * @param allot
     * @return
     * @throws Exception
     */
	public void svoiceBackToCredit(Context ctx){
		Map paramMap = ctx.getDataMap();
		//Map<String, Object> htmlMap = new HashMap<String, Object>();		
		try {
			String appId=paramMap.get("appId").toString();
			//查询分件表当前状态
			AllotApplyAllot applyAllot = sysButtonCommonService.selectByPrimaryKey(appId);
			String userCode = StringUtils.trimToEmpty((String) ctx.getDataMap().get("userCode"));
			List<String> applyList=new ArrayList<String>(Arrays.asList("0","1","2","3"));
			if(applyAllot!=null&&userCode.equals(applyAllot.getCurrOptUser())&&"07".equals(applyAllot.getCurrNode())
					&&applyList.contains(applyAllot.getDelStatus())){
				//为即将插入历史表的件赋值
				AllotApplyAllotHis allotHis = setAllotApplyAllotHis(applyAllot, appId);
				allotHis.setBackFlag("6");// 6:智能语音退回征信
				allotHis.setMemo(paramMap.get("memo").toString());//退回备注
				allotHis.setLastOptUser(applyAllot.getCurrOptUser());
				//查询上个征信环节的分件状态
				Map<String,String> pMap = new HashMap<>();
				pMap.put("currNode", "02");
				pMap.put("appId", appId);
				AllotApplyAllotHis oldAllot = sysButtonCommonService.queryAllotApplyAllotHisByAppId(pMap);
				if(oldAllot==null){
					//分件历史表中没有征信环节的记录，该申请件异常
					ctx.setData("success", false);
					ctx.setData("msg", "分件表中不存在征信提交的件");
					return;
				}else{
					//为即将更新的分件表中的状态和操作人赋值
					applyAllot.setCurrStatus("3");//个人队列未挂起
					applyAllot.setDelStatus("0"); //征信未完成
					applyAllot.setCurrOptUser(oldAllot.getCurrOptUser());
					allotHis.setCurrOptUser(applyAllot.getCurrOptUser());
				}
				allotHis.setCurrOptUser(applyAllot.getCurrOptUser());
				applyAllot.setCurrNode("02");// 01：录入比对02：征信调查03：人工审批   07：智能语音		
				applyAllot.setBackFlag("6"); // 智能语音退回
				applyAllot.setCurrOptGroup(oldAllot.getCurrOptGroup());//上个征信环节的操作组
				applyAllot.setLastOptGroup(oldAllot.getCurrOptGroup());//上个征信环节的操作组
				//发起智能语音执行的内容
				Map<String,Object> resMap = new HashMap<>();
				resMap.put("appId", appId);
				resMap.put("check_meuoFlag", applyAllot.getCheck_meuoFlag());
				resMap.put("memo", paramMap.get("memo").toString());
				bizInpApplService.updateApplySVoiceToZx(applyAllot,allotHis,resMap,"1");//更新数据库操作
			}else{
				ctx.setData("msg", "保存失败,该申请件已不在当前队列,请重新刷新!");
			}
			ctx.setData("success", true);
			ctx.setData("msg", "");
		} catch (Exception e) {
			ctx.setData("success", false);
			ctx.setData("msg", e.getMessage());
			e.printStackTrace();
		}
	}
	public Map<String, String> queryNodeIdAndProcessIp(AllotApplyAllot allot) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		// 从配置文件中获取节点nodeId
		Map<String, String> paroperMap = CommonProperties.getParoperMap();
		map.put("process_IP", paroperMap.get("process_IP"));
		if ("2".equals(allot.getYdjFlag())) {// 标准卡
			map.put("nodeId", paroperMap.get("nodeId_sp_bzk"));
			map.put("nodeId2", paroperMap.get("nodeId_sc_bzk"));
		} else {
			throw new Exception("申请件分配表易达金标志为空，请检测详情");
		}
		return map;
	}
	/**
	 * 
	 * @param allot
	 * @param appId
	 * @return
	 * @throws Exception
	 */
	public AllotApplyAllotHis setAllotApplyAllotHis(AllotApplyAllot allot, String appId) throws Exception {
		AllotApplyAllotHis record = new AllotApplyAllotHis();
		record.setId(StringUtil.randomUUIDPlainString());
		record.setAppId(appId);
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
		record.setCheck_meuoFlag(allot.getCheck_meuoFlag());
		return record;
	}

}

