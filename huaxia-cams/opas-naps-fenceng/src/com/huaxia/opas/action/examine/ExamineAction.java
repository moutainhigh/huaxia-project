package com.huaxia.opas.action.examine;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.xfire.client.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huateng.neofp.core.Action;
import com.huateng.neofp.core.Context;
import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.allot.AllotApplyAllot;
import com.huaxia.opas.domain.apply.ApplyLifeCicle;
import com.huaxia.opas.domain.decision.OpasReviewDecisionResult;
import com.huaxia.opas.domain.input.BizInpAppl;
import com.huaxia.opas.domain.system.ApUser;
import com.huaxia.opas.service.allot.ReviewDecisionService;
import com.huaxia.opas.service.apply.ApplyLifeCicleService;
import com.huaxia.opas.service.apply.BizInpApplService;
import com.huaxia.opas.service.checking.QualityCheckingService;
import com.huaxia.opas.service.system.ApUserService;
import com.huaxia.opas.service.thirdparty.PoliceService;
import com.huaxia.opas.util.CommonProperties;
import com.sun.jmx.snmp.Timestamp;

public class ExamineAction implements Action {

	private static Logger logger = LoggerFactory.getLogger(ExamineAction.class);

	@Resource(name = "bizInpApplService")
	private BizInpApplService bizInpApplService;

	@Resource(name = "applyLifeCicleService")
	private ApplyLifeCicleService applyLifeCicleService;

	@Resource(name = "apUserService")
	private ApUserService  apUserService;
	
	@Resource(name = "reviewDecisionService")
	private ReviewDecisionService reviewDecisionService;
	
	@Resource(name = "qualityCheckingService")
	private QualityCheckingService qualityCheckingService;
	
	@Resource(name ="policeService")
	private PoliceService policeService;
	
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
		String orderByStr = "";
		if (checkStr != null && !"".equals(checkStr)) {
			if ("QUEUE_DATE".equals(checkStr)) {// 进入队列天数
				orderByStr = "a.QUICK_CARD_FLAG desc,queDate desc,b.USER_DATE asc,substr(a.APP_ID,8,3) asc,substr(a.APP_ID,7,1) asc,substr(a.APP_ID,1,6) asc,substr(a.APP_ID,11,5) asc";
			} else if ("APP_ID".equals(checkStr)) {
				orderByStr = "a.QUICK_CARD_FLAG desc,substr(a.APP_ID,8,3) asc,substr(a.APP_ID,7,1) desc,substr(a.APP_ID,1,6) asc,substr(a.APP_ID,11,5) asc";
			} else if ("COMPLETED_FLAG".equals(checkStr)) {
				orderByStr = "a.QUICK_CARD_FLAG desc,c.COMPLETED_FLAG desc,b.USER_DATE asc,substr(a.APP_ID,8,3) asc,substr(a.APP_ID,7,1) desc,substr(a.APP_ID,1,6) asc,substr(a.APP_ID,11,5) asc";
			} else if ("C1_CONAME".equals(checkStr)) {
				orderByStr = "a.QUICK_CARD_FLAG desc,NLSSORT(a.C1_CONAME,'NLS_SORT=SCHINESE_PINYIN_M'),b.USER_DATE asc,substr(a.APP_ID,8,3) asc,substr(a.APP_ID,7,1) desc,substr(a.APP_ID,1,6) asc,substr(a.APP_ID,11,5) asc";
			}
		} else {// 前台未勾选排序规则
			orderByStr = "a.QUICK_CARD_FLAG desc,queDate desc,b.USER_DATE asc,substr(a.APP_ID,8,3) asc,substr(a.APP_ID,7,1) desc,substr(a.APP_ID,1,6) asc,substr(a.APP_ID,11,5) asc";
		}
		map.put("checkStr", orderByStr);
		if (map != null) {
			if (map.get("currNode") == null || "".equals(map.get("currNode"))) {
				map.put("currNode", "01");
			}
		}
		String lsh = map.get("lsh") != null ? map.get("lsh").toString().toUpperCase() : "";
		map.put("lsh", lsh);
		String delStatus = (String) map.get("delStatus");
		// 如果当前人工处理状态是未完成，查分配状态是已分配未挂起的，如果人工状态是挂起，则查已分配已挂起的
		if (delStatus != null && "0".equals(delStatus)) {
			map.put("currStatus", "3");
		} else if (delStatus != null && "4".equals(delStatus)) {
			map.remove("delStatus");
			map.put("currStatus", "4");
		} else {
			map.put("currStatus", "8");
		}
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
			count = bizInpApplService.queryCountForExamine(map);
		} catch (CoreException e) {
			e.printStackTrace();
		}
		if (count > 0) {
			try {
				list = bizInpApplService.queryBizInpApplListForExamine(map, curNum, pageNum);
				for (BizInpAppl bizInpAppl : list) {
					String userCode = bizInpAppl.getLastOptUser();
					if(userCode == null || "".equals(userCode)){
						userCode = bizInpAppl.getCurrUser();
					}
					ApUser apUser = apUserService.queryApUserByUserCode(userCode);
					bizInpAppl.setUserName(apUser.getUserName());
					String appId = bizInpAppl.getAppId();
					//可信身份查询有结果后需要根据  可信身份认证请求返回数据表 中的字段：“认证结果” 来判断是否需要显示“P”
					//目前只查询主卡：认证结果 以首字符“0”和“5”开始的不提示P
					Integer patchStatusPP = bizInpAppl.getPatchStatusPP();
					if(patchStatusPP!=0){
						//可信身份认证信息查询有结果时查询里面的“认证结果”，根据此结果的第一个字符判断是否提示“P”：0和5这在这两种情况不提示
						Map<String,String> map1 = policeService.selectAuthResultByAppid(appId);
						if(map1!=null){
							String authResult = map1.get("AUTHRESULT");
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
					bizInpAppl.setPatchStatusPP(patchStatusPP);
				}
			} catch (CoreException e) {
				e.printStackTrace();
			}
		}

		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("total", count);
		dataMap.put("rows", list);
		ctx.setDataMap(dataMap);
	}

	/**
	 * 申请件获取
	 * 
	 * @param ctx
	 * @throws Exception
	 * @throws CoreException
	 */
	public void getOneBizInpAppl(Context ctx) throws CoreException, Exception {
		Map<String, Object> map = ctx.getDataMap();
		// 获取申请件之前申请件周期表记录操作
		ApplyLifeCicle applyLifeCicle = insertApplyLifeCicle(map);
		String sqjlsh = map.get("sqjlsh") != null ? map.get("sqjlsh").toString().trim().toUpperCase() : "";
		applyLifeCicle.setAppId(sqjlsh);
		ApUser apUser = apUserService.queryApUserByUserId((String) map.get("userUuid"));
		String userName = apUser.getUserName();
		String userCode = apUser.getUserCode();
		String operater = userName + "-" + userCode;
		applyLifeCicle.setOperater(operater);
		applyLifeCicle.setOperateDesc(userName + "录入审查环节获取该申请件");

		// Map<String, Object> dataMap = new HashMap<String, Object>();//
		// 存放向前台提示的内容
		
		String ydjFlag = StringUtils.trimToEmpty((String) ctx.getDataMap().get("ydjFlag"));
		Map<String, Object> conditionMap = new HashMap<String, Object>();
		String delStatus = StringUtils.trimToEmpty((String) map.get("delStatus"));
		String currNode = StringUtils.trimToEmpty((String) map.get("currNode"));
		String userUuid = StringUtils.trimToEmpty((String) map.get("userUuid"));
		String userId = StringUtils.trimToEmpty((String) map.get("userId"));
		conditionMap.put("appId", sqjlsh);
		conditionMap.put("ydjFlag", ydjFlag);
		conditionMap.put("delStatus", delStatus);
		conditionMap.put("currNode", currNode);
		conditionMap.put("userUuid", userUuid);
		conditionMap.put("userId", userId);
		try {
			Map<String, Object> msgMap = bizInpApplService.selectAllotByConditionByAppId(conditionMap);
			Boolean isSuccess = (Boolean) msgMap.get("success");
			if(isSuccess){
				applyLifeCicle.setOperateResult("审查环节获取成功");
				applyLifeCicleService.addApplyLifeCicle(applyLifeCicle);
			}
			ctx.setDataMap(msgMap);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("msg", "数据异常。申请件获取失败。");
			ctx.setDataMap(map);
		}
	}

	/**
	 * 手动抢件
	 * 
	 * @param ctx
	 */
	public void getThreeBizInpAppl(Context ctx) {
		Map<String, Object> map = ctx.getDataMap();

		// 前台传过来的抢件的件数
		String moRenFiveApply = StringUtils.trimToEmpty((String) map.get("moRenFiveApply"));
		String ydjFlag = StringUtils.trimToEmpty((String) ctx.getDataMap().get("ydjFlag"));
		Map<String, Object> conditionMap = new HashMap<String, Object>();
		conditionMap.put("currNode", "01");
		conditionMap.put("currStatus", "1");
		conditionMap.put("moRenFiveApply", moRenFiveApply);
		conditionMap.put("ydjFlag", ydjFlag);
		conditionMap.put("userId", map.get("userId"));
		conditionMap.put("userUuid", map.get("userUuid"));
		try {
//			int count = bizInpApplService.selectCountByCurrOptUserForExamine((String)map.get("userId"));
//			if(count>20){
//				ctx.setData("success", false);
//				map.put("msg", "目前您手中申请件超过20件，不支持抢件，赶快操作吧!");
//				ctx.setDataMap(map);
//				return;
//			}
			Map<String, Object> msgMap = bizInpApplService.updateLrApplyForExamine(conditionMap);
			ctx.setDataMap(msgMap);
			ctx.setData("success", msgMap.get("success"));
		} catch (Exception e) {
			e.printStackTrace();
			ctx.setData("success", false);
			map.put("msg", "数据异常,手动抢件失败!");
			ctx.setDataMap(map);
		}
	}

	/**
	 * @Title:updateExamineStatus
	 * @Description:审查为 未完成页面时 点击提交 根据appid将opas_allot_apply_allot表的
	 *                  del_status字段的值变为4
	 * @param ctx
	 * @author: gaohui
	 * @Date:2017年3月31日上午11:03:43
	 */
	public void updateExamineStatus(Context ctx) {
		Map paramMap = ctx.getDataMap();
		AllotApplyAllot record = new AllotApplyAllot();
		record.setAppId(paramMap.get("appId").toString());
		record.setDelStatus("4");// 审查状态 变为已挂起
		try {
			bizInpApplService.updateByPrimaryKeySelective(record);
			ctx.setData("isSuccess", true);
		} catch (Exception e) {
			ctx.setData("isSuccess", false);
			e.printStackTrace();
		}

	}

	/**
	 * @Title:lineNewUrlClient
	 * @Description:连接另一个项目
	 * @param ctx
	 * @author: gaohui
	 * @throws Exception
	 * @Date:2017年3月31日下午6:59:34
	 */
	public void lineNewUrlClient(Context ctx)  throws Exception {
		Map<String,Object> paramMap = ctx.getDataMap();
		Map<String,Object> returnMap = bizInpApplService.addExamineToTopbpm(paramMap);
		if(!(boolean) returnMap.get("isSuccess")){
			ctx.setData("isSuccess", false);
			ctx.setData("exMsg",returnMap.get("exMsg"));
		}else{
			ctx.setData("isSuccess", true);
		}
	}

	
	
	/**
	 *审查录入自动推送功能
	 * @param ctx
	 */
	public void examineAutoPush(Context ctx){
		Map<String, Object> map = ctx.getDataMap();
		map.put("userId", (String) map.get("userCode"));
		String checkStr = (String) map.get("checkStr");
		String orderByStr = "";
		if (checkStr != null && !"".equals(checkStr)) {
			if ("QUEUE_DATE".equals(checkStr)) {// 进入队列天数
				orderByStr = "a.QUICK_CARD_FLAG desc,queDate desc,b.USER_DATE asc,substr(a.APP_ID,8,3) asc,substr(a.APP_ID,7,1) asc,substr(a.APP_ID,1,6) asc,substr(a.APP_ID,11,5) asc";
			} else if ("APP_ID".equals(checkStr)) {
				orderByStr = "a.QUICK_CARD_FLAG desc,substr(a.APP_ID,8,3) asc,substr(a.APP_ID,7,1) desc,substr(a.APP_ID,1,6) asc,substr(a.APP_ID,11,5) asc";
			} else if ("COMPLETED_FLAG".equals(checkStr)) {
				orderByStr = "a.QUICK_CARD_FLAG desc,c.COMPLETED_FLAG desc,b.USER_DATE asc,substr(a.APP_ID,8,3) asc,substr(a.APP_ID,7,1) desc,substr(a.APP_ID,1,6) asc,substr(a.APP_ID,11,5) asc";
			} else if ("C1_CONAME".equals(checkStr)) {
				orderByStr = "a.QUICK_CARD_FLAG desc,NLSSORT(a.C1_CONAME,'NLS_SORT=SCHINESE_PINYIN_M'),b.USER_DATE asc,substr(a.APP_ID,8,3) asc,substr(a.APP_ID,7,1) desc,substr(a.APP_ID,1,6) asc,substr(a.APP_ID,11,5) asc";
			}
		} else {// 前台未勾选排序规则
			orderByStr = "a.QUICK_CARD_FLAG desc,queDate desc,b.USER_DATE asc,substr(a.APP_ID,8,3) asc,substr(a.APP_ID,7,1) desc,substr(a.APP_ID,1,6) asc,substr(a.APP_ID,11,5) asc";
		}
		map.put("checkStr", orderByStr);
		if (logger.isInfoEnabled()) {
			logger.info("查询参数：{}", map);
		}
		int count = 0;
		try {
			count = bizInpApplService.queryCountForExamine(map);
		} catch (CoreException e) {
			e.printStackTrace();
		}
		if (count > 0) {
			try {
				List<BizInpAppl> list = bizInpApplService.queryBizInpApplListForExamine(map, 0, 1);
				ctx.setData("allot", list.get(0));
			} catch (CoreException e) {
				e.printStackTrace();
			}
		}else{
			ctx.setData("allot", null);
		}
	}
	

	/**
	 * 审查环节工作量统计方法
	 * 
	 * @author susha 2017/03/24
	 */
	public void queryWorkload(Context ctx) throws CoreException {
		// 获取查询时间
		String startTime = ctx.getData("startTime");
		String endTime = ctx.getData("endTime");
		// 获取当前登录名
		String crediter = ctx.getData("userId");

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
		// 调用service方法
		int count1 = bizInpApplService.queryFinishedCount1(map);
		int count2 = bizInpApplService.queryUnfinishedCount(map);

		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("total1", count1 + "");
		dataMap.put("total2", count2 + "");

		ctx.setDataMap(dataMap);
	}

	// 插入申请件周期表操作
	private ApplyLifeCicle insertApplyLifeCicle(Map paramMap) throws ParseException {
		Date date = new Date();
		String userCode = (String) paramMap.get("userId");
		ApplyLifeCicle applyLifeCicle = new ApplyLifeCicle();
		applyLifeCicle.setUuid(UUID.randomUUID().toString().replace("-", ""));
		applyLifeCicle.setCrtDate(date);
		applyLifeCicle.setCrtUser(userCode);
		applyLifeCicle.setLstUpdUser(userCode);
		applyLifeCicle.setLstUpdDate(date);
		return applyLifeCicle;
	}
}
