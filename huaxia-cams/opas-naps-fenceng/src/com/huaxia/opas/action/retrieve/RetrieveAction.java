package com.huaxia.opas.action.retrieve;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huateng.neofp.core.Action;
import com.huateng.neofp.core.Context;
import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.allot.AllotApplyAllot;
import com.huaxia.opas.domain.apply.ApplyRemark;
import com.huaxia.opas.domain.retrieve.FicoBigResponse;
import com.huaxia.opas.service.apply.BizInpApplService;
import com.huaxia.opas.service.retrieve.RetrieveService;
import com.huaxia.opas.util.StringUtil;

import net.sf.json.JSONArray;

/**
 * 组长调阅 经理调阅
 * 
 * @author zhanglibo
 * 
 *         2017年3月13日
 */
public class RetrieveAction implements Action {

	private static Logger logger = LoggerFactory.getLogger(RetrieveAction.class);

	@Resource(name = "retrieveService")
	private RetrieveService retrieveServiceImpl;

	public RetrieveService getRetrieveServiceImpl() {
		return retrieveServiceImpl;
	}

	@Resource(name = "bizInpApplService")
	private BizInpApplService bizInpApplService;
	
	public void setRetrieveServiceImpl(RetrieveService retrieveServiceImpl) {
		this.retrieveServiceImpl = retrieveServiceImpl;
	}

	// 征信 组长调阅 
	public void queryMemberJobCount(Context ctx) throws CoreException {

		logger.debug("征信--->组长调阅--->start");
		Map<String, Object> dataMap = new HashMap<String, Object>();
		Map<String, Object> map = ctx.getDataMap();
		String userCode = (String) map.get("userId");
		String startTime = (String) map.get("startTime");
		
		int curPage = Integer
				.parseInt(String.valueOf(ctx.getDataMap().get("page") == null ? 0 : ctx.getDataMap().get("page")));
		int pageNum = Integer
				.parseInt(String.valueOf(ctx.getDataMap().get("rows") == null ? 0 : ctx.getDataMap().get("rows")));
		
		String currNode = "02";
		//String currNode = (String) map.get("currNode");
		map.put("currNode", currNode);
		map.put("userCode", userCode);
		map.put("curNum", curPage);
		map.put("pageNum", pageNum);
		map.put("startTime", startTime);
		//经理调阅传过来的组ID
		map.put("orgId", (String)map.get("orgId"));
		dataMap = retrieveServiceImpl.queryApUserOrgListByUserCode(map);

		ctx.setDataMap(dataMap);

		logger.debug("currNode"+currNode+"01审查 02 征信 03 审批 06 征审合一 --->组长调阅--->start");
	}

	// 审查 组长调阅
	public void queryMemberJobCountCheckup(Context ctx) throws CoreException {
		logger.debug("组长调阅--->审查--->start");
		Map<String, Object> dataMap = new HashMap<String, Object>();
		Map<String, Object> map = ctx.getDataMap();
		String startTime = (String) map.get("startTime");
		String userCode = (String) map.get("userId");
		int curPage = Integer
				.parseInt(String.valueOf(ctx.getDataMap().get("page") == null ? 0 : ctx.getDataMap().get("page")));
		int pageNum = Integer
				.parseInt(String.valueOf(ctx.getDataMap().get("rows") == null ? 0 : ctx.getDataMap().get("rows")));

		String currNode = "01";
		//String currNode = (String) map.get("currNode");
		map.put("currNode", currNode);
		map.put("userCode", userCode);
		map.put("curNum", curPage);
		map.put("pageNum", pageNum);
		map.put("startTime", startTime);
		//经理调阅传过来的组ID
		map.put("orgId", (String)map.get("orgId"));
		dataMap = retrieveServiceImpl.queryMemberJobCountCheckup(map);

		ctx.setDataMap(dataMap);
		logger.debug("currNode"+currNode+"审查 --->组长调阅--->end");
	}

	// 审批 组长调阅
	public void queryMemberJobCountApprove(Context ctx) throws CoreException {
		logger.debug("审批 --->组长调阅 --->start");
		//Map<String, String> parMap = new HashMap<String, String>();
		Map<String, Object> map = ctx.getDataMap();
		Map<String, Object> dataMap = new HashMap<String, Object>();
		String userCode = (String) map.get("userId");
		String startTime = (String) map.get("startTime");
		
		int curPage = Integer
				.parseInt(String.valueOf(ctx.getDataMap().get("page") == null ? 0 : ctx.getDataMap().get("page")));
		int pageNum = Integer
				.parseInt(String.valueOf(ctx.getDataMap().get("rows") == null ? 0 : ctx.getDataMap().get("rows")));


		String currNode ="03";
		map.put("currNode", currNode);
		map.put("userCode", userCode);
		map.put("curNum", curPage);
		map.put("pageNum", pageNum);
		map.put("startTime", startTime);
		//经理调阅传过来的组ID
		map.put("orgId", (String)map.get("orgId"));
		dataMap  = retrieveServiceImpl.queryMemberJobApprove(map);
		
		ctx.setDataMap(dataMap);
	}
	
	// 征审合一 组长调阅
	public void queryMemberJobApproveCheckUp(Context ctx) throws CoreException {
		logger.debug("审批 --->组长调阅 --->start");
		//Map<String, String> parMap = new HashMap<String, String>();
		Map<String, Object> map = ctx.getDataMap();
		Map<String, Object> dataMap = new HashMap<String, Object>();
		String userCode = (String) map.get("userId");
		String startTime = (String) map.get("startTime");
		int curPage = Integer
				.parseInt(String.valueOf(ctx.getDataMap().get("page") == null ? 0 : ctx.getDataMap().get("page")));
		int pageNum = Integer
				.parseInt(String.valueOf(ctx.getDataMap().get("rows") == null ? 0 : ctx.getDataMap().get("rows")));
		
		//征审合一角色区分
		String currNode ="06";
		map.put("currNode", currNode);
		map.put("userCode", userCode);
		map.put("curNum", curPage);
		map.put("pageNum", pageNum);
		map.put("startTime", startTime);
		//经理调阅传过来的组ID
		map.put("orgId", (String)map.get("orgId"));
		dataMap  = retrieveServiceImpl.queryMemberJobApproveCheckUp(map);
		ctx.setDataMap(dataMap);
	}

	//  征信 审查 审批  征审合一明细查询
	public void queryStaffJobDetail(Context ctx) throws CoreException {
		logger.debug("组长调阅-->征信 审查 审批  征审合一-->明细查询--->start");
		Map<String, Object> map = ctx.getDataMap();
		String currNode = (String) map.get("currNode");
		//int curNum = 0;
		int curPage = Integer
				.parseInt(String.valueOf(ctx.getDataMap().get("page") == null ? 0 : ctx.getDataMap().get("page")));
		int pageNum = Integer
				.parseInt(String.valueOf(ctx.getDataMap().get("rows") == null ? 0 : ctx.getDataMap().get("rows")));
		map.put("curNum", curPage);
		map.put("pageNum", pageNum);
		Map<String, Object> dataMap = retrieveServiceImpl.queryStaffJobDetailByUserCode(map);
		ctx.setDataMap(dataMap);
		logger.debug("currNode"+currNode+"组长调阅-->01审查 02 征信 03 审批 06 征审合一 -->明细查询--->end");
	}
	
	// 审查 征信 审批 征审合一  组员工作完成量统计组员工作完成量统计 
	public void memberJobCompletCount(Context ctx) throws CoreException {
		logger.debug("审查 征信 审批 征审合一  -->组员工作完成量统计-->start");
		Map<String, Object> dataMap = new HashMap<String, Object>();
		Map<String, Object> map = ctx.getDataMap();
		String userCode = (String) map.get("userId");
		String startTime = null;
		String endTime = null;
		List<String> obj = ctx.getData("startTime");
		List<String> objEnd = ctx.getData("endTime");
		if (obj == null) {
			Date sysDate = new Date();
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			startTime = format.format(sysDate);
		} else {
			startTime = obj.get(0);
		}
		if (objEnd == null) {
			endTime = startTime;
		} else {
			endTime = objEnd.get(0);
		}
		int curPage = Integer
				.parseInt(String.valueOf(ctx.getDataMap().get("page") == null ? 0 : ctx.getDataMap().get("page")));
		int pageNum = Integer
				.parseInt(String.valueOf(ctx.getDataMap().get("rows") == null ? 0 : ctx.getDataMap().get("rows")));
		//currNode 01审查 02 征信 03 审批 06 征审合一
		String currNode = (String)map.get("currNode");
		map.put("currNode", (String)map.get("currNode"));
		map.put("userCode", userCode);
		map.put("curNum", curPage);
		map.put("pageNum", pageNum);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		//经理调阅传过来的组ID
		dataMap.put("orgId", (String)map.get("orgId"));
		dataMap = retrieveServiceImpl.memberJobCompletCountCheckupDataMap(map);
		
		ctx.setDataMap(dataMap);
		
		logger.debug("currNode---"+currNode+"---currNode 01审查 02 征信 03 审批 06 征审合一  -->组员工作完成量统计-->end");
	}
	

	

	
	// 查看是否有组长备注
	public String checkRemarkInfoFig(String appId) throws CoreException {
		logger.debug("查看是否有组长备注-->start appId["+appId+"]");
		List<ApplyRemark> applyRemakrList = new ArrayList<ApplyRemark>();
		String fig = null;
		try {
			
			applyRemakrList = retrieveServiceImpl.checkRemarkInfo(appId);
			if(applyRemakrList==null || applyRemakrList.size()==0){//说明此appId没有组织备注
				fig = "0";
			}else{
				fig = "1";
			}
		} catch (Exception e) {
			logger.debug("查看是否有组长备注异常！"+e);
		}
		logger.debug("查看是否有组长备注-->end");
		return fig;
	}
	// 查看组长备注
	public void checkRemarkInfo(Context ctx) throws CoreException {

		logger.debug("查看组长备注-->start");

		Map<String, Object> dataMap = new HashMap<String, Object>();

		List<ApplyRemark> applyRemakrList = new ArrayList<ApplyRemark>();

		List<ApplyRemark> returnaApplyRemakrList = new ArrayList<ApplyRemark>();

		Map<String, Object> map = ctx.getDataMap();

		String appId = (String) map.get("appId");

		try {
			
			applyRemakrList = retrieveServiceImpl.checkRemarkInfo(appId);
			
			if (applyRemakrList == null || applyRemakrList.size() == 0) {
	
				ctx.setDataMap(dataMap);
	
			} else {

				for (int i = 0; i < applyRemakrList.size(); i++) {

					ApplyRemark applyRemark = new ApplyRemark();

					applyRemark.setAppId(applyRemakrList.get(i).getAppId());

					applyRemark.setRemarkInfo(applyRemakrList.get(i).getRemarkInfo());

					applyRemark.setRemarkUser(applyRemakrList.get(i).getRemarkUser());

					String currNode = applyRemakrList.get(i).getCurrNode();

					String currNodeStr = "";

					if ("01".equals(currNode)) {
						currNodeStr = "审查环节";
					} else if ("02".equals(currNode)) {
						currNodeStr = "征信环节";
					} else if ("03".equals(currNode)) {
						currNodeStr = "审批环节";
					} else if ("04".equals(currNode)) {
						currNodeStr = "征审合一环节";
					} else if ("05".equals(currNode)) {
						currNodeStr = "反欺诈环节";
					} else {
						currNodeStr = "未知环节";
					}

					applyRemark.setCurrNode(currNodeStr);

					applyRemark.setRemarkDate(applyRemakrList.get(i).getRemarkDate());

					returnaApplyRemakrList.add(applyRemark);
				}

				dataMap.put("rows", returnaApplyRemakrList);

				dataMap.put("total", returnaApplyRemakrList.size());

				dataMap.put("fig", "true");

				}
			} catch (Exception e) {
				logger.debug("查看组长备注异常！"+e);
			}

		ctx.setDataMap(dataMap);

		logger.debug("查看组长备注-->end");

	}

	// 新增组长备注
	public void saveNemberRemarks(Context ctx) throws CoreException {

		logger.debug("新增组长备注-->start");

		Map<String, Object> dataMap = new HashMap<String, Object>();

		Map<String, Object> map = ctx.getDataMap();

		Date sysDate = new Date();

		List<String> idsList = (List<String>) map.get("ids");

		String nemberRemarks = (String) map.get("nemberRemarks");

		String currNode = (String) map.get("currNode");

		String userCode = (String) map.get("userCode");

		for (int i = 0; i < idsList.size(); i++) {

			ApplyRemark applyRemark = new ApplyRemark();

			String appId = idsList.get(i);

			applyRemark.setRemarkId(StringUtil.randomUUIDPlainString());

			applyRemark.setAppId(appId);

			applyRemark.setRemarkInfo(nemberRemarks);

			applyRemark.setCurrNode(currNode);

			applyRemark.setRemarkUser(userCode);

			applyRemark.setRemarkTime(sysDate);

			try {

				int count = retrieveServiceImpl.saveNemberRemarks(applyRemark);

				if (count == 0) {

					dataMap.put("fig", "false");

				} else {

					dataMap.put("fig", "true");

				}

			} catch (Exception e) {

				logger.debug("新增组长备注失败！" + e);
			}

		}

		ctx.setDataMap(dataMap);

		logger.debug("新增组长备注-->end");
	}


	// 页面初始化加载组长下拉框
	public void queryApOrg(Context ctx) throws CoreException {

		//Map<String, Object> map = ctx.getDataMap();

		Map<String, Object> dataMap = new HashMap<String, Object>();

		List<Map<String, String>> queryApOrgList = retrieveServiceImpl.queryApOrg();

		JSONArray queryApOrg = JSONArray.fromObject(queryApOrgList);

		dataMap.put("queryApOrg", queryApOrg.toString());

		ctx.setDataMap(dataMap);
	}

	
	public void managerExamineDetail(Context ctx) throws CoreException{
		
		logger.debug("经理调阅--->start");
		
		FicoBigResponse ficoBigResponse = new FicoBigResponse();
		
		Map<String, Object> dataMap = new HashMap<String, Object>();
		Map<String, Object> map = ctx.getDataMap();

		String ydjFlag = (String) map.get("ydjFlag");
		//Map<String, Object> map = ctx.getDataMap();
		List<String> objStart = ctx.getData("managerTime");
		String managerTime = null;
		if (objStart == null){
			Date sysDate = new Date();
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			managerTime = format.format(sysDate);
		} else {
			managerTime = objStart.get(0);
		}
		dataMap.put("ydjFlag", ydjFlag);
		dataMap.put("managerTime", managerTime);
		//审批过件数
		int SPGJS = 0;
		//审批拒件数
		int SPJJS = 0;
		//一次反欺诈拒件数
		int YCFQZJJS = 0;
		//二次反欺诈拒件数
		int ECFQZJJS = 0;
		//一次决策拒件数
		int YCJCJJS = 0;
		//一次决策过件数
		int YCJCGJS = 0;
		//二次决策拒件数
		int ECJCJJS = 0;
		//二次决策过件数
		int ECJCGJS = 0;
		//申请件总量
		int count = 0;
		
		try {
			
			SPGJS = retrieveServiceImpl.querySPGJS(dataMap);
			
			SPJJS = retrieveServiceImpl.querySPJJS(dataMap);
			//一次反欺诈拒件数
			YCFQZJJS = retrieveServiceImpl.queryYCFQZJJS(dataMap);
			//二次反欺诈拒件数
			ECFQZJJS = retrieveServiceImpl.queryECFQZJJS(dataMap);
			//一次决策拒件数
			YCJCJJS = retrieveServiceImpl.queryYCJCJJS(dataMap);
			//一次决策过件数
			YCJCGJS = retrieveServiceImpl.queryYCJCGJS(dataMap);
			//二次决策拒件数
			ECJCJJS = retrieveServiceImpl.queryECJCJJS(dataMap);
			//二次决策过件数
			ECJCGJS = retrieveServiceImpl.queryECJCGJS(dataMap);
			//审批过件数
			SPGJS = SPGJS - YCJCGJS - ECJCGJS;
			//审批拒件数
			SPJJS = SPJJS - YCFQZJJS - ECFQZJJS - YCJCJJS - ECJCJJS;
			
			ficoBigResponse.setSpgjs(SPGJS);
			
			ficoBigResponse.setSpjjs(SPJJS);
			
			ficoBigResponse.setYcfqzjjs(YCFQZJJS);
			
			ficoBigResponse.setEcfqzjjs(ECFQZJJS);
			
			ficoBigResponse.setYcjcjjs(YCJCJJS);
			
			ficoBigResponse.setYcjcgjs(YCJCGJS);
			
			ficoBigResponse.setEcjcjjs(ECJCJJS);
			
			ficoBigResponse.setEcjcgjs(ECJCGJS);
			
			count = SPGJS+SPJJS+YCFQZJJS+ECFQZJJS+YCJCJJS+YCJCGJS+ECJCJJS+ECJCGJS;
			
			ficoBigResponse.setSqjzs(count);
			
			
		} catch (Exception e) {
			logger.debug("经理调阅查询异常！");
		}
		
		dataMap.put("SPGJS", SPGJS);
		
		dataMap.put("SPJJS", SPJJS);
		
		dataMap.put("YCFQZJJS", YCFQZJJS);
		
		dataMap.put("ECFQZJJS", ECFQZJJS);
		
		dataMap.put("YCJCJJS", YCJCJJS);
		
		dataMap.put("YCJCGJS", YCJCGJS);
		
		dataMap.put("ECJCJJS", ECJCJJS);
		
		dataMap.put("ECJCGJS", ECJCGJS);
		
		dataMap.put("count", count);
		
		dataMap.put("fig", "true");
		
		ctx.setDataMap(dataMap);
		
		logger.debug("经理调阅--->end");
		
	}
	public void memberJobEcxceptionCount(Context ctx) throws CoreException{
		
		logger.debug("组员工作连续性异常统计--->start");
		Map<String, Object> map = ctx.getDataMap();
		System.out.println(map);
		String startTime = (String) map.get("startTime");
		Map<String, Object> dataMap = new HashMap<String, Object>();
		map.put("currNode", "03");
		int curNum = 0;
		int curPage = Integer
				.parseInt(String.valueOf(ctx.getDataMap().get("page") == null ? 0 : ctx.getDataMap().get("page")));

		int pageNum = Integer
				.parseInt(String.valueOf(ctx.getDataMap().get("rows") == null ? 0 : ctx.getDataMap().get("rows")));
		if (curPage > 1) {
			curNum = (curPage - 1) * pageNum;
		}
		map.put("startTime", startTime);
		map.put("curNum", curNum);
		map.put("pageNum", pageNum);
		
		dataMap = retrieveServiceImpl.memberJobEcxception(map);
		ctx.setDataMap(dataMap);
		
		logger.debug("组员工作连续性异常统计--->end");
		
	}
	public void memberJobEcxceptionDetail(Context ctx) throws CoreException{
		logger.debug("组员工作连续性异常统计  详细查看--->start");
		Map<String, Object> dataMap = new HashMap<String, Object>();
		Map<String, Object> map = ctx.getDataMap();
		int curNum = 0;
		int curPage = Integer
				.parseInt(String.valueOf(ctx.getDataMap().get("page") == null ? 0 : ctx.getDataMap().get("page")));

		int pageNum = Integer
				.parseInt(String.valueOf(ctx.getDataMap().get("rows") == null ? 0 : ctx.getDataMap().get("rows")));
		if (curPage > 1) {
			curNum = (curPage - 1) * pageNum;
		}
		map.put("curNum", curNum);
		map.put("pageNum", pageNum);
		map.put("currNode", "03");
		dataMap = retrieveServiceImpl.memberJobEcxceptionDetailDataMap(map);
		ctx.setDataMap(dataMap);
		logger.debug("组员工作连续性异常统计  详细查看--->end");
	}
	public void queryCurrNodeByAppId(Context ctx) throws CoreException{
		logger.debug("根据appId查询currNode--->start");
		Map<String, Object> dataMap = new HashMap<String, Object>();
		AllotApplyAllot allotApplyAllot = new AllotApplyAllot();
		Map<String, Object> map = ctx.getDataMap();
		String appId = (String) map.get("appId");
		allotApplyAllot = retrieveServiceImpl.queryCurrNodeByAppId(appId);
		if(allotApplyAllot == null){
			logger.debug("根据appId查询cruuNode无结果!");
		}else{
			dataMap.put("currNode", allotApplyAllot.getCurrNode());
		}
		ctx.setDataMap(dataMap);
		logger.debug("根据appId查询currNode--->end");
	}
	public int daySubtract(String queDate) {
		int days = 0;
		try {
			//DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

			String sysDate = null;

			Date date = new Date();

			sysDate = sdf.format(date);
			// 时间转换类

			Date date1 = sdf.parse(sysDate);
			Date date2 = sdf.parse(queDate);
			// 将转换的两个时间对象转换成Calendard对象
			Calendar can1 = Calendar.getInstance();
			can1.setTime(date1);
			Calendar can2 = Calendar.getInstance();
			can2.setTime(date2);
			// 拿出两个年份
			int year1 = can1.get(Calendar.YEAR);
			int year2 = can2.get(Calendar.YEAR);
			// 天数

			Calendar can = null;
			// 如果can1 < can2
			// 减去小的时间在这一年已经过了的天数
			// 加上大的时间已过的天数
			if (can1.before(can2)) {
				days -= can1.get(Calendar.DAY_OF_YEAR);
				days += can2.get(Calendar.DAY_OF_YEAR);
				can = can1;
			} else {
				days -= can2.get(Calendar.DAY_OF_YEAR);
				days += can1.get(Calendar.DAY_OF_YEAR);
				can = can2;
			}
			for (int i = 0; i < Math.abs(year2 - year1); i++) {
				// 获取小的时间当前年的总天数
				days += can.getActualMaximum(Calendar.DAY_OF_YEAR);
				// 再计算下一年。
				can.add(Calendar.YEAR, 1);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return days;
	}

	public String daySubOne(String date) throws ParseException {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		Date d = sdf.parse(date);

		Calendar cal = Calendar.getInstance();

		cal.setTime(d);

		cal.add(Calendar.DATE, -1);

		return sdf.format(cal.getTime()).toString();
	}
}
