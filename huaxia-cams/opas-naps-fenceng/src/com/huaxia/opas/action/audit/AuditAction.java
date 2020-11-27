package com.huaxia.opas.action.audit;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.xfire.client.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huateng.neofp.core.Action;
import com.huateng.neofp.core.Context;
import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.allot.AllotApplyAllot;
import com.huaxia.opas.domain.decision.OpaCheckinerResultInfo;
import com.huaxia.opas.domain.input.BizInpAppl;
import com.huaxia.opas.domain.system.ApOrg;
import com.huaxia.opas.service.apply.BizInpApplService;
import com.huaxia.opas.service.decision.SysDecisionYdjService;
import com.huaxia.opas.service.workflow.TopbpmService;
/**
 * 审批
 * @author xiebinxu
 * 2017年3月9日
 */
public class AuditAction implements Action {

	private static Logger logger = LoggerFactory.getLogger(AuditAction.class);

	@Resource(name = "bizInpApplService")
	private BizInpApplService bizInpApplService;
	@Resource(name = "sysDecisionYdjService")
	private SysDecisionYdjService sysDecisionYdjService;
	@Resource(name = "topbpmService")
	private TopbpmService topbpmService;
	@SuppressWarnings("unchecked")
	public void listBizInpAppl(Context ctx) {

		Map map = ctx.getDataMap();
		String ydjFlag = StringUtils.trimToEmpty((String) map.get("ydjFlag"));
		String check_meuoFlag = StringUtils.trimToEmpty((String) map.get("check_meuoFlag"));
		String telNet = StringUtils.trimToEmpty((String) map.get("telNet"));//add by yuanquan  20190110  新增网申条件 
		String zxzc = StringUtils.trimToEmpty((String) map.get("zxzc"));//add by yuanquan  20201010  新增专项政策 
		map.put("check_meuoFlag", check_meuoFlag);
		map.put("telNet",telNet);
		map.put("zxzc",zxzc);
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
			checkStr = checkStr.replace("CRT_TEAM_DATE", "b.CRT_TEAM_DATE");
		//	checkStr = checkStr.replace("QUICK_CARD_FLAG", "a.QUICK_CARD_FLAG");
			checkStr = checkStr.replace("vip", "substr(APP_ID,10,1)");
			checkStr = checkStr.replace("APP_ID", "QUICK_CARD_FLAG desc,substr(APP_ID,8,3) asc,substr(APP_ID,7,1) desc,substr(APP_ID,1,6) asc,substr(APP_ID,11,5) asc,substr(APP_ID,16,1) desc,LST_TEAM_DATE");
			checkStr = checkStr.replace("GROUP_DATE", "QUICK_CARD_FLAG desc,GRODATE desc,LST_TEAM_DATE asc,substr(APP_ID,8,3) asc,substr(APP_ID,7,1) desc,substr(APP_ID,1,6) asc,substr(APP_ID,11,5) asc,substr(APP_ID,16,1) desc");
			checkStr = checkStr.replace("C1_CONAME", "QUICK_CARD_FLAG desc,NLSSORT(C1_CONAME,'NLS_SORT=SCHINESE_PINYIN_M'),LST_TEAM_DATE asc,substr(APP_ID,8,3) asc,substr(APP_ID,7,1) desc,substr(APP_ID,1,6) asc,substr(APP_ID,11,5) asc,substr(APP_ID,16,1) desc");
			checkStr = checkStr.replace("PATCH_STATUS", "QUICK_CARD_FLAG desc,PATCH_STATUS desc,substr(APP_ID,8,3) asc,substr(APP_ID,7,1) desc,substr(APP_ID,1,6) asc,substr(APP_ID,11,5) asc,substr(APP_ID,16,1) desc");
			map.put("checkStr",  checkStr.substring(0, checkStr.length() - 1));
		} else {
			map.put("checkStr", "QUICK_CARD_FLAG desc,GRODATE desc,TRUNC(LST_TEAM_DATE,'MI') asc,substr(APP_ID,1,6) asc,substr(APP_ID,8,3) asc,substr(APP_ID,7,1) desc,substr(APP_ID,11,5) asc,substr(APP_ID,16,1) desc");
		}
		if(map != null){
			if(map.get("currNode")==null||"".equals(map.get("currNode"))){
				map.put("currNode", "03");
			}
		}
		String lsh=map.get("lsh")!=null?map.get("lsh").toString().toUpperCase():"";
		map.put("lsh",lsh);
		String delStatus=(String) map.get("delStatus");
		//如果当前人工处理状态是未完成，查分配状态是已分配未挂起的，如果人工状态是挂起，则查已分配已挂起的
		if(delStatus!=null&&"0".equals(delStatus)){
			map.put("currStatus", "3");
		}else if(delStatus!=null&&"2".equals(delStatus)){
			map.put("currStatus", "3");
		}else if(delStatus!=null&&"4".equals(delStatus)){
			map.remove("delStatus");
			map.put("currStatus", "4");
			/*map.put("delStatus", "0");*/
		}else{
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
			count = bizInpApplService.queryCountSp(map);
		} catch (CoreException e) {
			e.printStackTrace();
		}
		if (count > 0) {
			try {
				list = bizInpApplService.queryListSp(map, curNum, pageNum);
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
	 * 审批已完成队列
	 * @param ctx
	 */
	public void listBizInpApplWc(Context ctx) {
		Map map=ctx.getDataMap();
		String userId = StringUtils.trimToEmpty((String) ctx.getDataMap().get("userId"));
		String check_meuoFlag = StringUtils.trimToEmpty((String) map.get("check_meuoFlag"));
		String zxzc = StringUtils.trimToEmpty((String) map.get("zxzc"));//add by yuanquan  20201010  新增专项政策 
		map.put("check_meuoFlag", check_meuoFlag);
		map.put("zxzc",zxzc);
		String checkStr = (String) map.get("checkStr");
		if (checkStr != null && !"".equals(checkStr)) {
			checkStr = checkStr.replace("CRT_TEAM_DATE", "b.CRT_TEAM_DATE");
			checkStr = checkStr.replace("QUICK_CARD_FLAG", "a.QUICK_CARD_FLAG");
			checkStr = checkStr.replace("vip", "substr(APP_ID,10,1)");
			checkStr = checkStr.replace("APP_ID", "QUICK_CARD_FLAG desc,substr(APP_ID,8,3) asc,substr(APP_ID,7,1) desc,substr(APP_ID,1,6) asc,substr(APP_ID,11,5) asc,substr(APP_ID,16,1) desc,LST_TEAM_DATE");
			checkStr = checkStr.replace("GROUP_DATE", "QUICK_CARD_FLAG desc,GRODATE desc,LST_TEAM_DATE asc,substr(APP_ID,8,3) asc,substr(APP_ID,7,1) desc,substr(APP_ID,1,6) asc,substr(APP_ID,11,5) asc,substr(APP_ID,16,1) desc");
			checkStr = checkStr.replace("C1_CONAME", "QUICK_CARD_FLAG desc,NLSSORT(C1_CONAME,'NLS_SORT=SCHINESE_PINYIN_M'),LST_TEAM_DATE asc,substr(APP_ID,8,3) asc,substr(APP_ID,7,1) desc,substr(APP_ID,1,6) asc,substr(APP_ID,11,5) asc,substr(APP_ID,16,1) desc");
			checkStr = checkStr.replace("PATCH_STATUS", "QUICK_CARD_FLAG desc,PATCH_STATUS desc,substr(APP_ID,8,3) asc,substr(APP_ID,7,1) desc,substr(APP_ID,1,6) asc,substr(APP_ID,11,5) asc,substr(APP_ID,16,1) desc");
			map.put("checkStr",  checkStr.substring(0, checkStr.length() - 1));
		//	map.put("checkStr", "QUICK_CARD_FLAG desc," + checkStr.substring(0, checkStr.length() - 1) +",USER_DATES asc,substr(APP_ID,1,6) asc,substr(APP_ID,8,3) asc,substr(APP_ID,7,1) desc,substr(APP_ID,11,6) asc");
		} else {
			map.put("checkStr", "QUICK_CARD_FLAG desc,GRODATE desc,TRUNC(LST_TEAM_DATE,'MI') asc,substr(APP_ID,1,6) asc,substr(APP_ID,8,3) asc,substr(APP_ID,7,1) desc,substr(APP_ID,11,5) asc,substr(APP_ID,16,1) desc");
			}
		map.put("userId", userId);
		map.put("currNode","04");
		map.put("delStatus","1");
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
			count = bizInpApplService.queryCountWc(map);
		} catch (CoreException e) {
			e.printStackTrace();
		}
		if (count > 0) {
			try {
				list = bizInpApplService.queryListWc(map, curNum, pageNum);
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
	 * 征审合一挂起队列查询
	 * @param ctx
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void gtListCreditAndSp(Context ctx) {
		Map map = ctx.getDataMap();
		String ydjFlag = StringUtils.trimToEmpty((String) ctx.getDataMap().get("ydjFlag"));
		String userId = StringUtils.trimToEmpty((String) ctx.getDataMap().get("userId"));
		String zxzc = StringUtils.trimToEmpty((String) map.get("zxzc"));//add by yuanquan  20201010  新增专项政策 
		map.put("zxzc",zxzc);
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
			checkStr = checkStr.replace("vip", "substr(APP_ID,10,1)");
			checkStr = checkStr.replace("APP_ID", "QUICK_CARD_FLAG desc,substr(APP_ID,8,3) asc,substr(APP_ID,7,1) desc,substr(APP_ID,1,6) asc,substr(APP_ID,11,5) asc,substr(APP_ID,16,1) desc,LST_TEAM_DATE");
			checkStr = checkStr.replace("GROUP_DATE", "QUICK_CARD_FLAG desc,GRODATE desc,LST_TEAM_DATE asc,substr(APP_ID,8,3) asc,substr(APP_ID,7,1) desc,substr(APP_ID,1,6) asc,substr(APP_ID,11,5) asc,substr(APP_ID,16,1) desc");
			checkStr = checkStr.replace("C1_CONAME", "QUICK_CARD_FLAG desc,NLSSORT(C1_CONAME,'NLS_SORT=SCHINESE_PINYIN_M'),LST_TEAM_DATE asc,substr(APP_ID,8,3) asc,substr(APP_ID,7,1) desc,substr(APP_ID,1,6) asc,substr(APP_ID,11,5) asc,substr(APP_ID,16,1) desc");
			checkStr = checkStr.replace("PATCH_STATUS", "QUICK_CARD_FLAG desc,PATCH_STATUS desc,substr(APP_ID,8,3) asc,substr(APP_ID,7,1) desc,substr(APP_ID,1,6) asc,substr(APP_ID,11,5) asc,substr(APP_ID,16,1) desc");
			map.put("checkStr",  checkStr.substring(0, checkStr.length() - 1));
		} else {
			
			map.put("checkStr", "QUICK_CARD_FLAG desc,GRODATE desc,TRUNC(LST_TEAM_DATE,'MI') asc,substr(APP_ID,1,6) asc,substr(APP_ID,8,3) asc,substr(APP_ID,7,1) desc,substr(APP_ID,11,5) asc,substr(APP_ID,16,1) desc");
		}
		String lsh=map.get("lsh")!=null?map.get("lsh").toString().toUpperCase():"";
		map.put("lsh",lsh);
		map.put("userId", userId);
		map.put("ydjFlag", ydjFlag);
		//分页功能
		int count = 0;
		int curNum = 0;
		int curPage = Integer.parseInt(String.valueOf(ctx.getDataMap().get("page") == null ? 0 : ctx.getDataMap().get("page")));
		int pageNum = Integer.parseInt(String.valueOf(ctx.getDataMap().get("rows") == null ? 0 : ctx.getDataMap().get("rows")));
		if (curPage > 1) {
			curNum = (curPage - 1) * pageNum;
		}
		
		List<BizInpAppl> list = new ArrayList<BizInpAppl>();
		try {
			count = bizInpApplService.queryGtCount(map);
			if (count > 0) 
				list = bizInpApplService.queryGtList(map, curNum, pageNum);
		} catch (CoreException e) {
			e.printStackTrace();
		}
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("total", count);
		dataMap.put("rows", list);
		ctx.setDataMap(dataMap);
	}
	
	/**
	 * 审批环节工作量统计方法
	 * 
	 * @author susha 2017/03/24
	 */
	public void queryWorkload(Context ctx) throws CoreException {
		// 获取查询时间
		String startTime = ctx.getData("startTime");
		String endTime = ctx.getData("endTime");
		String ydjFlag= ctx.getData("ydjFlag");
		String check_meuoflag = ctx.getData("check_meuoflag");
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
		map.put("check_meuoflag", check_meuoflag);
		map.put("ydjFlag",ydjFlag);
		// 调用service方法
		int count1 = bizInpApplService.queryFinishedCount(map);
		int count2 = bizInpApplService.queryToHighlevelCount(map);
		int count3 = bizInpApplService.queryToFileCount(map);

		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("total1", count1 + "");
		dataMap.put("total2", count2 + "");
		dataMap.put("total3", count3 + "");

		ctx.setDataMap(dataMap);

	}
	/**
	 *@Title:lineNewUrlClientZx
	 *@Description:连接另一个项目  审批
	 *@param ctx
	 *@author: gaohui
	 *@Date:2017年4月20日下午7:41:56
	 */
	public void lineNewUrlClientSp(Context ctx) {
		Map paramMap = ctx.getDataMap();
		boolean success=false;
		try {
			Map<String, Object> needMap = bizInpApplService.queryProcessIdByAppId(paramMap);
			String processId= needMap.get("PROCESSID")!=null?needMap.get("PROCESSID").toString():"";
			if(processId!=null&&!"".equals(processId)){
//				Client client = new Client(new URL("http://192.168.1.24:9091/topbpm_test/services/BranchBankSearchServiceI?wsdl"));
				
				String intputXml = "";
				intputXml = "{'processId':'" + processId + "','nodeId':'WaitNode_jpoo2','isBack':'0'}";
//				Object[] obj = new Object[] { intputXml.toString() };
//				Object[] result = client.invoke("signal", obj);
				String result = topbpmService.signal(intputXml.toString()).toString();
//				System.out.println("single======" + result.toString());
				logger.info("single======",result);
				AllotApplyAllot record = new AllotApplyAllot();
				record.setAppId(paramMap.get("appId").toString());
				record.setDelStatus("1");// 审查状态 变为已挂起
				bizInpApplService.updateByPrimaryKeySelective(record);
				success=true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		ctx.setData("isSuccess", success);
	}
	
	public void getOneBizInpAppl(Context ctx) {
		Map map = ctx.getDataMap();
		String sqjlsh = map.get("sqjlsh") != null ? map.get("sqjlsh").toString().trim().toUpperCase() : "";
		String ydjFlag = StringUtils.trimToEmpty((String) ctx.getDataMap().get("ydjFlag"));
		String userId = StringUtils.trimToEmpty((String) ctx.getDataMap().get("userId"));
		Map<String, String> conditionMap = new HashMap<String, String>();
		conditionMap.put("currNode", "03");
		conditionMap.put("appId", sqjlsh);
		conditionMap.put("ydjFlag", ydjFlag);
		try {
			List<AllotApplyAllot> allotApplyAllots = bizInpApplService.getSpAllotByCondition(conditionMap);
			String ret = "";
			Map<String, Object> dataMap = new HashMap<String, Object>();
			OpaCheckinerResultInfo checkInfo = sysDecisionYdjService.selectChecKinerInfoByAppId_15(sqjlsh+"%");
			if (allotApplyAllots == null || allotApplyAllots.size() == 0) {
				ret = "无此申请件，或该申请件不可被获取！";
			}else if(checkInfo!= null && "2".equals(checkInfo.getStopFlag())){
				ret = "该申请件被质检叫停，不可被获取！";
			}else {
				// 抢件
				bizInpApplService.updateListAllotApplyAllot(allotApplyAllots, userId,"1");
				ret = "获取成功！";
				map.put("currNode", "03");
				String delStatus = (String) map.get("delStatus");
				// 如果当前人工处理状态是未完成，查分配状态是已分配未挂起的，如果人工状态是挂起，则查已分配已挂起的
				if (delStatus != null && "0".equals(delStatus)) {
					map.put("currStatus", "3");
				} else if (delStatus != null && "4".equals(delStatus)) {
					map.put("currStatus", "4");
					map.remove("delStatus");
				} else {
					map.put("currStatus", "8");
				}
				int curNum = 0;
				int curPage = Integer.parseInt(
						String.valueOf(ctx.getDataMap().get("page") == null ? 0 : ctx.getDataMap().get("page")));
				int pageNum = Integer.parseInt(
						String.valueOf(ctx.getDataMap().get("rows") == null ? 0 : ctx.getDataMap().get("rows")));

				if (curPage > 1) {
					curNum = (curPage - 1) * pageNum;
				}

				List<BizInpAppl> list = new ArrayList<BizInpAppl>();
				int count = 0;
				try {
					count = bizInpApplService.queryCountSp(map);
				} catch (CoreException e) {
					e.printStackTrace();
				}
				if (count > 0) {
					try {
						list = bizInpApplService.queryListSp(map, curNum, pageNum);
					} catch (CoreException e) {
						e.printStackTrace();
					}
				}
				dataMap.put("total", count);
				dataMap.put("rows", list);
			}
			dataMap.put("ret", ret);
			ctx.setDataMap(dataMap);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	/**
	 * 手动抢件
	 * 
	 * @param ctx
	 */
	public void getThreeBizInpAppl(Context ctx) {
		Map map = ctx.getDataMap();
		String moRenFiveApply = StringUtils.trimToEmpty((String) ctx.getDataMap().get("moRenFiveApply"));
		String ydjFlag = StringUtils.trimToEmpty((String) ctx.getDataMap().get("ydjFlag"));
		String userId = StringUtils.trimToEmpty((String) ctx.getDataMap().get("userId"));
		Map<String, String> conditionMap = new HashMap<String, String>();
		conditionMap.put("currNode", "03");
		conditionMap.put("currStatus", "1");
		conditionMap.put("moRenFiveApply", moRenFiveApply);
		conditionMap.put("ydjFlag", ydjFlag);
		conditionMap.put("userCode", userId);
		int sum = bizInpApplService.countUnFinishByCondition(conditionMap);
		// add by yuanquan 20190107 标准卡少于20不变  易达金少于10件
		if("2".equals(ydjFlag)&&sum>20){
			//Map<String, Object> dataMap = new HashMap<String, Object>();
			ctx.setData("msg", "审批个人未完成队列中申请件数量超过20件，不允许抢件！");
		}else if ("1".equals(ydjFlag)&&sum>10){
			ctx.setData("msg", "审批个人未完成队列中申请件数量超过10件，不允许抢件！");
		}
		else{

			try {
				List<AllotApplyAllot> allots = bizInpApplService.selectAllotByCondition(conditionMap);
				//对获取到的数据进行过滤
				for (int i=allots.size()-1;i>=0; i--) {
					AllotApplyAllot allotOne = allots.get(i);
					OpaCheckinerResultInfo checkInfo = sysDecisionYdjService.selectChecKinerInfoByAppId_15(allotOne.getAppId()+"%");
					if(checkInfo!= null && "2".equals(checkInfo.getStopFlag())){
						allots.remove(i);
					}
				}
				Set<String> appIdSet = new HashSet<String>();
				String appIds = "";
				String appIdsMsg = "";
				for (AllotApplyAllot allotApplyAllot : allots) {
					appIdSet.add(allotApplyAllot.getAppId());
					if (appIdSet != null && (appIdSet.size() + "").equals(moRenFiveApply)) {
						break;
					}
				}
				int num = 1;
				for (String string : appIdSet) {
					if (num == appIdSet.size()) {
						appIds = appIds + "'" + string + "'";
						appIdsMsg = appIdsMsg + "'" + string + "' </br>";
						break;
					} else {
						appIds = appIds + "'" + string + "',";
						appIdsMsg = appIdsMsg + "'" + string + "' </br>,";
					}
					num++;
				}
				if ("".equals(appIds)) {
					ctx.setData("msg", "已经没有可以抢的件了!");
					return;
				}
				List<AllotApplyAllot> allots_15 = bizInpApplService.selectByAppId_15(appIds);
				// 抢件
				bizInpApplService.updateListAllotApplyAllot(allots_15, userId, "2");
				// 刷新页面
				map.put("currNode", "03");
				String delStatus = (String) map.get("delStatus");
				// 如果当前人工处理状态是未完成，查分配状态是已分配未挂起的，如果人工状态是挂起，则查已分配已挂起的
				if (delStatus != null && "0".equals(delStatus)) {
					map.put("currStatus", "3");
				} else if (delStatus != null && "4".equals(delStatus)) {
					map.put("currStatus", "4");
					map.remove("delStatus");
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
					count = bizInpApplService.queryCountSp(map);
				} catch (CoreException e) {
					e.printStackTrace();
				}
				if (count > 0) {
					try {
						list = bizInpApplService.queryListSp(map, curNum, pageNum);
					} catch (CoreException e) {
						e.printStackTrace();
					}
				}
				Map<String, Object> dataMap = new HashMap<String, Object>();
				dataMap.put("total", count);
				dataMap.put("rows", list);
				ctx.setDataMap(dataMap);
				ctx.setData("msg", "抢到的件前15位编号分别为：" + appIdsMsg);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
}
