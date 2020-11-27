package com.huaxia.opas.action.credit;

import java.text.ParseException;
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
import com.huaxia.opas.domain.sysparm.CreditWhiteList;
import com.huaxia.opas.domain.system.ApUser;
import com.huaxia.opas.service.credit.CreditWhiteListService;
import com.huaxia.opas.service.system.ApUserService;
import com.huaxia.opas.util.UUIDGenerator;

/**
 * 征信白名单复核action类
 * 
 * @author susha 2017/3/13
 *
 */
public class CreditWhiteListAction implements Action {

	@SuppressWarnings("unused")
	private static Logger logger = LoggerFactory.getLogger(CreditWhiteListAction.class);

	@Resource(name = "creditWhiteListService")
	private CreditWhiteListService creditWhiteListService;
	@Resource(name = "apUserService")
	private ApUserService  apUserService;
	// 列表查询
	public void queryList(Context ctx) {

		Gson json = new Gson();
		CreditWhiteList creditWhiteList = json.fromJson(json.toJson(ctx.getDataMap()), CreditWhiteList.class);

		int curNum = 0;

		int curPage = Integer
				.parseInt(String.valueOf(ctx.getDataMap().get("page") == null ? 0 : ctx.getDataMap().get("page")));

		int pageNum = Integer
				.parseInt(String.valueOf(ctx.getDataMap().get("rows") == null ? 0 : ctx.getDataMap().get("rows")));

		if (curPage > 1) {
			curNum = (curPage - 1) * pageNum;
		}

		//List<CreditWhiteList> list = new ArrayList<CreditWhiteList>();
		List<Map<Object, Object>> list = new ArrayList<Map<Object, Object>>();
		int count = 0;

		try {
			count = creditWhiteListService.queryCount(creditWhiteList);
		} catch (CoreException e) {
			e.printStackTrace();
		}

		if (count > 0) {
			try {
				list = creditWhiteListService.queryList(creditWhiteList, curNum, pageNum);
				for (Map<Object, Object> map : list) {
					String  operator = (String) map.get("operator");
					if(operator!=null&&!"".equals(operator)){
						ApUser apUser = apUserService.queryApUserByUserCode(operator);
						map.put("operator", apUser.getUserName());
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		Map<String, Object> dataMap = new HashMap<String, Object>();

		dataMap.put("total", count);
		dataMap.put("rows", list);
		ctx.setDataMap(dataMap);

	}

	// 复核确认--单条确认
	public void reConfirm(Context ctx) throws CoreException {
		Gson json = new Gson();

		CreditWhiteList creditWhiteList = json.fromJson(json.toJson(ctx.getDataMap()), CreditWhiteList.class);
		creditWhiteList.setCurrStatus("1");
		try {
			creditWhiteListService.save(creditWhiteList);
			ctx.setData("isSuccess", true);
		} catch (Exception e) {
			ctx.setData("noSuccess", false);
			e.printStackTrace();
		}
	}
	/**
	 *@Title:saveOrUpdateCreditWhiteCenter
	 *@Description:bzk征信库添加 （标准卡征信信息名单表（中间表））
	 *@param ctx
	 *@author: gaohui
	 *@Date:2017年5月4日下午8:32:06
	 */
	public void saveOrUpdateCreditWhiteCenter(Context ctx){
		Gson json = new Gson();
		CreditWhiteList creditWhiteList = json.fromJson(json.toJson(ctx.getDataMap()), CreditWhiteList.class);
		creditWhiteList.setAutoId(UUIDGenerator.getUUID());
		creditWhiteList.setCurrStatus("1");
		Date date=new Date();
		creditWhiteList.setOperatTime(date);
		creditWhiteList.setCreateTime(date);
		try {
			creditWhiteListService.saveOrUpdateCreditWhiteCenter(creditWhiteList);
			ctx.setData("isSuccess", true);
		} catch (Exception e) {
			ctx.setData("noSuccess", false);
			e.printStackTrace();
		}
		
	}
	
	//复核确认--拒绝
	public void refuse(Context ctx) throws CoreException {
		Gson json = new Gson();

		CreditWhiteList creditWhiteList = json.fromJson(json.toJson(ctx.getDataMap()), CreditWhiteList.class);

		try {
			creditWhiteListService.refuse(creditWhiteList);
			ctx.setData("isSuccess", true);
		} catch (Exception e) {
			ctx.setData("isSuccess", false);
			e.printStackTrace();
		}
	}
	// 批量确认
	public void reConfirnList(Context ctx) throws CoreException {
		// 获取前台参数--选中数据的id
		List list = ctx.getData("ids");
		// 调用业务层
		try {

			creditWhiteListService.insertList(list);
			ctx.setData("isSuccess", true);
		} catch (Exception e) {
			ctx.setData("isSuccess", false);
			e.printStackTrace();
		}
	}

	// 删除
	public void deleteList(Context ctx) throws CoreException {
		// 获取前台参数--选中数据的id
		List list = ctx.getData("ids");
		// 调用业务层
		try {

			creditWhiteListService.deleteList(list);
			ctx.setData("isSuccess", true);
		} catch (Exception e) {
			ctx.setData("isSuccess", false);
			e.printStackTrace();
		}
	}

	// 工作量查询方法
	public void queryWorkload(Context ctx) throws CoreException, ParseException {

		// 获取查询时间
		String startTime = ctx.getData("startTime");
		String endTime = ctx.getData("endTime");
		// 获取当前登录名
		String crediter = ctx.getData("userId");
		Map<String, Object> dataMap = new HashMap<String, Object>();
		try {
			dataMap = creditWhiteListService.queryWorkload(startTime,endTime,crediter);
			// 回传结果
			ctx.setData("data", dataMap);
		} catch (Exception e) {
			ctx.setData("isSuccess", false);
			e.printStackTrace();
		}
	}

	/**
	 * 白名单征信库查询
	 * @param ctx
	 */
	public void querycreditwhitelist(Context ctx) {

		Gson json = new Gson();
		CreditWhiteList creditWhiteList = json.fromJson(json.toJson(ctx.getDataMap()), CreditWhiteList.class);

		int curNum = 0;

		int curPage = Integer
				.parseInt(String.valueOf(ctx.getDataMap().get("page") == null ? 0 : ctx.getDataMap().get("page")));

		int pageNum = Integer
				.parseInt(String.valueOf(ctx.getDataMap().get("rows") == null ? 0 : ctx.getDataMap().get("rows")));

		if (curPage > 1) {
			curNum = (curPage - 1) * pageNum;
		}

		List<CreditWhiteList> list = new ArrayList<CreditWhiteList>();

		int count = 0;

		try {
			count = creditWhiteListService.queryCreditWhiteCountInfo(creditWhiteList);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (count > 0) {
			try {
				list = creditWhiteListService.queryCreditWhiteListInfo(creditWhiteList, curNum, pageNum);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		Map<String, Object> dataMap = new HashMap<String, Object>();

		dataMap.put("total", count);
		dataMap.put("rows", list);
		ctx.setDataMap(dataMap);

	}
}
