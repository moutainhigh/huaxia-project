package com.huaxia.opas.action.sysparm;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.huateng.neofp.core.Action;
import com.huateng.neofp.core.Context;
import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.sysparm.SubReport;
import com.huaxia.opas.service.sysparm.SubReportService;

/*********************************************
 *@describe:提报账户、授权、催收Action
 *@author susha
 *@date:2017-03-09
 **********************************************/
public class SubReportAction implements Action {

	private static Logger logger = LoggerFactory
			.getLogger(SubReportAction.class);

	@Resource(name = "subReportService")
	private SubReportService subReportService;

	// 查询未完成的列表
	public void listUnfinished(Context ctx) {

		Gson json = new Gson();
		SubReport subReport = json.fromJson(json.toJson(ctx.getDataMap()),
				SubReport.class);
		int curNum = 0;
		int curPage = Integer.parseInt(String.valueOf(ctx.getDataMap().get(
				"page") == null ? 0 : ctx.getDataMap().get("page")));

		int pageNum = Integer.parseInt(String.valueOf(ctx.getDataMap().get(
				"rows") == null ? 0 : ctx.getDataMap().get("rows")));
		if (curPage > 1) {
			curNum = (curPage - 1) * pageNum;
		}
		List<SubReport> list = new ArrayList<SubReport>();
		int count = 0;
		try {
			count = subReportService.queryCount(subReport);
		} catch (CoreException e) {
			e.printStackTrace();
		}
		if (count > 0) {
			try {
				list = subReportService.queryUnfinishedList(subReport, curNum,
						pageNum);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		Map<String, Object> dataMap = new HashMap<String, Object>();

		dataMap.put("total", count);
		dataMap.put("rows", list);
		ctx.setDataMap(dataMap);
	}

	// 查询提报已完成的列表
	public void listFinished(Context ctx) {

		Gson json = new Gson();
		SubReport subReport = json.fromJson(json.toJson(ctx.getDataMap()),
				SubReport.class);
		int curNum = 0;
		int curPage = Integer.parseInt(String.valueOf(ctx.getDataMap().get(
				"page") == null ? 0 : ctx.getDataMap().get("page")));

		int pageNum = Integer.parseInt(String.valueOf(ctx.getDataMap().get(
				"rows") == null ? 0 : ctx.getDataMap().get("rows")));
		if (curPage > 1) {
			curNum = (curPage - 1) * pageNum;
		}
		List<SubReport> list = new ArrayList<SubReport>();
		int count = 0;
		try {
			count = subReportService.queryCount1(subReport);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (count > 0) {
			try {
				list = subReportService.queryFinishedList(subReport, curNum,
						pageNum);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		Map<String, Object> dataMap = new HashMap<String, Object>();

		dataMap.put("total", count);
		dataMap.put("rows", list);
		ctx.setDataMap(dataMap);
	}
	
	//保存当前操作
	public void saveSubReport(Context ctx) throws CoreException {
		
		logger.info(">>>>>>>>>>saveSubReport方法开始执行<<<<<<<<<<");
		Gson json = new Gson();
		SubReport subReport = json.fromJson(json.toJson(ctx.getDataMap()),
				SubReport.class);
		String appId = ctx.getData("appIId");
		subReport.setAppId(appId);
		try {
			subReportService.save(subReport);
			ctx.setData("isSuccess", true);
		} catch (Exception e) {
			ctx.setData("isSuccess", false);
			logger.error(">>>>>>>>>>>saveSubReport方法执行失败<<<<<<<<<<");
			throw new RuntimeException(e);
		}
	}
	
	// 提报到已完成
	public void toFinished(Context ctx) throws Exception {

		Gson json = new Gson();
		SubReport subReport = json.fromJson(json.toJson(ctx.getDataMap()),
				SubReport.class);
		String appId = ctx.getData("appIId");
		subReport.setAppId(appId);
		try {
			subReportService.toFinished(subReport);
			int curNum = 0;
			int curPage = Integer.parseInt(String.valueOf(ctx.getDataMap().get("page") == null ? 0 : ctx.getDataMap().get("page")));
			int pageNum = Integer.parseInt(String.valueOf(ctx.getDataMap().get("rows") == null ? 0 : ctx.getDataMap().get("rows")));
			logger.info("start creditcheatInvestigationPage>>>>>>>>>>>>curPage="+curPage+",pageNum="+pageNum);
			if (curPage > 1) {
				curNum = (curPage - 1) * pageNum;
			}
			int count = 0;
			List<SubReport> list = null;
			// 查询未完成列表
			if ("0".equals(subReport.getDelStatus())) {

				count = subReportService.queryCount(subReport);

				list = subReportService.queryUnfinishedList(subReport, curNum,
						pageNum);
			}
			// 查询已完成列表
			if ("2".equals(subReport.getDelStatus())) {

				count = subReportService.queryCount1(subReport);

				list = subReportService.queryFinishedList(subReport, curNum,
						pageNum);
			}

			Map<String, Object> dataMap = new HashMap<String, Object>();

			dataMap.put("total", count);
			dataMap.put("rows", list);
			ctx.setDataMap(dataMap);

			ctx.setData("isSuccess", true);

		} catch (CoreException e) {

			ctx.setData("isSuccess", false);
			ctx.setData("exMsg", e.getMessage());
			throw new RuntimeException(e);

		}
	}

	// 回收提报到未完成
	public void toUnfinished(Context ctx) throws Exception {

		String appId = ctx.getData("appIId");
		String submitType = ctx.getData("submitType");
		String crtUser = ctx.getData("crtUser");
		SubReport subReport = new SubReport();
		subReport.setSubmitType(submitType);
		subReport.setCrtUser(crtUser);
		subReport.setAppId(appId);
		try {

			subReportService.toUnfinished(subReport);
			int curNum = 0;
			int curPage = Integer.parseInt(String.valueOf(ctx.getDataMap().get(
					"page") == null ? 0 : ctx.getDataMap().get("page")));

			int pageNum = Integer.parseInt(String.valueOf(ctx.getDataMap().get(
					"rows") == null ? 0 : ctx.getDataMap().get("rows")));
			if (curPage > 1) {
				curNum = (curPage - 1) * pageNum;
			}
			List<SubReport> list = new ArrayList<SubReport>();
			int count = subReportService.queryCount1(subReport);
			list = subReportService.queryFinishedList(subReport, curNum,
					pageNum);

			Map<String, Object> dataMap = new HashMap<String, Object>();

			dataMap.put("total", count);
			dataMap.put("rows", list);
			ctx.setDataMap(dataMap);
			ctx.setData("isSuccess", true);
		} catch (CoreException e) {
			ctx.setData("isSuccess", false);
			ctx.setData("exMsg", e.getMessage());
			throw new RuntimeException(e);
		}
	}

	/**
	 * 退回到征信系统 
	 * @param ctx
	 * @throws Exception 
	 */
	public void toCredit(Context ctx) throws Exception {

		Gson json = new Gson();
		SubReport subReport = json.fromJson(json.toJson(ctx.getDataMap()),
				SubReport.class);
		String appId = ctx.getData("appIId");
		subReport.setAppId(appId);
		try {
			//退回到征信系统
			subReportService.toCredit(subReport);
			//申请件分配表SUBMIT_STATUS状态更新 
		//	subReportService.update(subReport);
			int curNum = 0;
			int curPage = Integer.parseInt(String.valueOf(ctx.getDataMap().get(
					"page") == null ? 0 : ctx.getDataMap().get("page")));

			int pageNum = Integer.parseInt(String.valueOf(ctx.getDataMap().get(
					"rows") == null ? 0 : ctx.getDataMap().get("rows")));
			if (curPage > 1) {
				curNum = (curPage - 1) * pageNum;
			}
			int count = 0;

			List<SubReport> list = null;
			// 查询未完成队列
			if ("0".equals(subReport.getDelStatus())) {

				count = subReportService.queryCount(subReport);

				list = subReportService.queryUnfinishedList(subReport, curNum,
						pageNum);
			}
			// 查询已完成队列
			if ("2".equals(subReport.getDelStatus())) {

				count = subReportService.queryCount1(subReport);

				list = subReportService.queryFinishedList(subReport, curNum,
						pageNum);
			}

			Map<String, Object> dataMap = new HashMap<String, Object>();

			dataMap.put("total", count);
			dataMap.put("rows", list);
			ctx.setDataMap(dataMap);

			ctx.setData("isSuccess", true);

		} catch (CoreException e) {

			ctx.setData("isSuccess", false);
			ctx.setData("exMsg", e.getMessage());
			throw new RuntimeException(e);

		}
	}

	/**
	 * 提报全部到征信系统
	 * @param ctx
	 * @throws Exception 
	 */
	public void commitAll(Context ctx) throws Exception {
		Gson json = new Gson();
		SubReport subReport = json.fromJson(json.toJson(ctx.getDataMap()),
				SubReport.class);
//		String appId = ctx.getData("appIId");
//		subReport.setAppId(appId);
//		String submitType = ctx.getData("submitType");
//		subReport.setSubmitType(submitType);
		try {
			//获取退回征信的数据
			//List<SubReport> list = subReportService.selectCommitAll(subReport);
			//全部退回到征信系统
			subReportService.commitAll(subReport);
			//更新申请件分配表状态
			//subReportService.updateApplyAllotList(list);
			Map<String, Object> dataMap = new HashMap<String, Object>();
			List<SubReport> list1 = new ArrayList<SubReport>();
			dataMap.put("total", 0);
			dataMap.put("rows", list1);
			ctx.setDataMap(dataMap);
			ctx.setData("isSuccess", true);
		} catch (CoreException e) {

			ctx.setData("isSuccess", false);
			ctx.setData("exMsg", e.getMessage());
			throw new RuntimeException(e);

		}
	}

	// 查询提报原因
	public void querySubReason(Context ctx) throws CoreException {

		String appId = ctx.getData("appIId");
		SubReport subReport = null;
		try {
			subReport = subReportService.querySubReason(appId);
		} catch (CoreException e) {
			throw new RuntimeException(e);
		}

		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("subReport", subReport);
		ctx.setDataMap(dataMap);
	}

	// 查询当天的工作量
	public void queryDayWorkload(Context ctx) throws CoreException {

		String submitType = ctx.getData("submitType");
		int count = 0;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date end = new Date();
		String str = sdf.format(end);
		str += " 00:00:00";
		Date start = null;
		try {
			start = sdf1.parse(str);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("START", start);
		map.put("END", end);
		map.put("submitType", submitType);

		try {
			count = subReportService.queryDayCount(map);
		} catch (Exception e) {

			throw new RuntimeException(e);
		}

		ctx.setData("total", count + "");
	}

	// 返回一个月后的日期
	public void queryDate(Context ctx) throws CoreException {

		logger.info("queryDate方法开始执行>>>>>>>>>>>>>");
		String startTime = ctx.getData("startTime");
		startTime += " 00:00:00";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date start = null;
		try {
			start = sdf.parse(startTime);
			Date end = subReportService.queryDate(start);
			Map<String, Object> dataMap = new HashMap<String, Object>();
			dataMap.put("end", end);
			ctx.setData("data", dataMap);
			logger.info("queryDate方法执行结束>>>>>>>>>>>");
		} catch (Exception e) {
			logger.error("queryDate方法执行异常:"+e);
			throw new RuntimeException(e);
		}
	}

	// 查询当月工作量
	public void queryMonthWorkload(Context ctx) throws CoreException {
		logger.info("queryMonthWorkload方法开始执行>>>>>>>>>>>");
		Map<String, Object> map = ctx.getDataMap();
		String submitType = ctx.getData("submitType");
		map.put("submitType", submitType);
		int count = 0;
		try {
			count = subReportService.queryMonthCount(map);
			logger.info("queryMonthWorkload方法执行结束>>>>>>>>");
		} catch (Exception e) {
			logger.error("queryMonthWorkload方法执行异常:"+e);
			throw new RuntimeException(e);
		}
		ctx.setData("total", count + "");
	}
}
