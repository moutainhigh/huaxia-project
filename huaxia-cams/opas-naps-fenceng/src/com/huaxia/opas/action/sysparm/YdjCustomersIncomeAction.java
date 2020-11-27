package com.huaxia.opas.action.sysparm;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.google.gson.Gson;
import com.huateng.neofp.core.Action;
import com.huateng.neofp.core.Context;
import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.sysparm.CustomersIncome;
import com.huaxia.opas.domain.sysparm.Income;
import com.huaxia.opas.service.sysparm.YdjCustomersIncomeService;
import com.huaxia.opas.util.SequenceUtil;
/**
 *  优化易达金客群策略  职级替代收入参数表 action
 * @author liyanjie
 *
 */
public class YdjCustomersIncomeAction implements Action{
	
	@Resource
	private YdjCustomersIncomeService ydjCustomersIncomeService;
	/**
	 * 查询功能实现
	 * @param ctx
	 * @throws CoreException
	 */
	public void queryIncome(Context ctx) throws CoreException{
		Gson gson = new Gson();
		CustomersIncome income = gson.fromJson(gson.toJson(ctx.getDataMap()), CustomersIncome.class);
		int curNum = 0;
		Object pageObj = ctx.getDataMap().get("page");
		int curPage = Integer.parseInt(String.valueOf(pageObj == null ? 0 : pageObj));
		Object rowsObj = ctx.getDataMap().get("rows");
		int pageNum = Integer.parseInt(String.valueOf(rowsObj == null ? 0 : rowsObj));
		if(curPage > 1){
			curNum = (curPage-1)*pageNum;
		}
		List<CustomersIncome> list = new ArrayList<CustomersIncome>();
		int count = ydjCustomersIncomeService.queryIncomeCount(income);
		if(count > 0){
			list = ydjCustomersIncomeService.queryIncomeList(income,curNum,pageNum);
		}
		Map<String,Object> dataMap = new HashMap<String,Object>();
		dataMap.put("total", count);
		dataMap.put("rows", list);
		ctx.setDataMap(dataMap);
	}
	/**
	 * 添加 客群细分 职级替代收入
	 * @param ctx
	 * @throws CoreException
	 */
	public void addCustomersIncome(Context ctx) throws CoreException{
		Gson json = new Gson();
		String operator = ctx.getData("userId");
		CustomersIncome income = json.fromJson(json.toJson(ctx.getDataMap()), CustomersIncome.class);
		income.setOperator(operator);
		income.setLstUpdUser(operator);
		income.setUuId(SequenceUtil.getUUID());
		
		CustomersIncome flag = ydjCustomersIncomeService.checkIsExist(income);
		if(flag != null){
			ctx.setData("isFailed", true);
		}
		try {
			if("1".equals(income.getStatus())){
				ydjCustomersIncomeService.insertCustomersIncomeStart(income);
			}else if ("0".equals(income.getStatus())){
				ydjCustomersIncomeService.insertCustomersIncomeEnd(income);
			}
		} catch (CoreException e) {
			ctx.setData("exMsg", e.getMessage());
			throw e;
		}
		ctx.setData("isSuccess", true);
	}
	/**
	 * 修改
	 * @param ctx
	 * @throws CoreException
	 */
	public void updateCustomersIncome(Context ctx) throws CoreException{
		Gson json = new Gson();
		CustomersIncome income = json.fromJson(json.toJson(ctx.getDataMap()), CustomersIncome.class);
		String userId = ctx.getData("userId");
		String autoId = ctx.getData("autoId");
		income.setLstUpdUser(userId);
		try {
			String status = ydjCustomersIncomeService.queryCustomersIncomeStatus(autoId);
			if(income.getStatus().equals(status)){
				ydjCustomersIncomeService.updateCustomersIncomeWithoutStatus(income);
			}else{
				ydjCustomersIncomeService.updateCustomersIncomeAndStatus(income);
			}
		} catch (CoreException e) {
			ctx.setData("exMsg", e.getMessage());
			throw e;
		}
		ctx.setData("isSuccess", true);
	}
	/**
	 * 批量删除
	 * @param ctx
	 * @throws CoreException
	 */
	public void deleteCustomersIncome(Context ctx) throws CoreException{
		try {
			CustomersIncome  income = getIncome(ctx,"ids");
			ydjCustomersIncomeService.deleteCustomersIncome(income);
			ctx.setData("isSuccess", true);
		} catch (CoreException e) {
			ctx.setData("isSuccess", false);
			ctx.setData("exMsg", e.getMessage());
			throw e;
		}
	}
	/**
	 * 批量启用
	 * @param ctx
	 * @throws CoreException
	 */
	public void setStatusOK(Context ctx) throws CoreException{
		try {
			CustomersIncome income = getIncome(ctx,"ids","Status");
			String userId = ctx.getData("userId");
			income.setLstUpdUser(userId);
			ydjCustomersIncomeService.setCustomersIncomeStatusOK(income);
			ctx.setData("isSuccess", true);
		} catch (CoreException e) {
			ctx.setData("exMsg", e.getMessage());
			ctx.setData("isSuccess", false);
			throw e;
		}
	}
	/**
	 *批量禁用
	 * @param ctx
	 * @throws CoreException
	 */
	public void setStatusNO(Context ctx) throws CoreException{
		try {
			CustomersIncome income = getIncome(ctx,"ids","Status");
			String userId = ctx.getData("userId");
			income.setLstUpdUser(userId);
			ydjCustomersIncomeService.setCustomersIncomeStatusNO(income);
			ctx.setData("isSuccess", true);
		} catch (CoreException e) {
			ctx.setData("exMsg", e.getMessage());
			ctx.setData("isSuccess", false);
			throw e;
		}
	}
	
	/**
	 * 公共部分的验证操作
	 */
	public CustomersIncome getIncome(Context ctx,String ...strings) throws CoreException{
		List<String> list = Arrays.asList(strings);
		Map<String, Object> map = ctx.getDataMap();
		Date createTime = null;
		if (list.contains("crtDate")) {
			String createDateString = (String)map.get("crtDate");
			if(null!=createDateString && !createDateString.trim().isEmpty()){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				try {
					createTime  = sdf.parse(createDateString);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			map.remove("crtDate");
		}
		Gson gson = new Gson();
		CustomersIncome income = gson.fromJson(gson.toJson(map), CustomersIncome.class);
		
		String operator = (String)ctx.getData("userId");
		if( null == operator || operator.trim().isEmpty()){
			CoreException e = new CoreException("操作用户operator不能为空，请先登录");
			throw e;
		}
		income.setOperator(operator);
		
		for (String key : strings) {
			switch (key) {
			case "ids":
				@SuppressWarnings("unchecked")
				List<String> ids = (List<String>) map.get("ids");
				if( null == ids || ids.size() <= 0){
					CoreException e = new CoreException("请选择需要操作的行ids");
					throw e;
				}
				income.setIds(ids);
				break;
				
			case "Status":
				String Status = (String)map.get("Status");
				if( null == Status || (!Status.trim().equals("1") && !Status.trim().equals("0"))){
					CoreException e = new CoreException("启用状态值Status非法");
					throw e;
				}
				break;
				
			case "crtDate":
				income.setCrtDate(createTime);
				break;
				
			default:
				break;
			}
		}
		return income;
	}

}
