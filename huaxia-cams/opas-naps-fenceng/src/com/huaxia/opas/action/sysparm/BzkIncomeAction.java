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
import com.huaxia.opas.domain.sysparm.Balance;
import com.huaxia.opas.domain.sysparm.Income;
import com.huaxia.opas.service.sysparm.BzkIncomeService;
import com.huaxia.opas.util.SequenceUtil;


/**
 * 标准卡行职业收入
 * 
 * @author lipengfei
 *
 */
public class BzkIncomeAction implements Action{

	@Resource(name = "bzkIncomeService")
	private BzkIncomeService bzkIncomeService;

	/**
	 * 查询
	 * @throws 
	 */
	public void queryIncome(Context ctx) throws CoreException {
		Gson gson = new Gson();
		Income income = gson.fromJson(gson.toJson(ctx.getDataMap()), Income.class);

		int curNum = 0;
		int curPage = Integer
				.parseInt(String.valueOf(ctx.getDataMap().get("page") == null ? 0 : ctx.getDataMap().get("page")));
		int pageNum = Integer
				.parseInt(String.valueOf(ctx.getDataMap().get("rows") == null ? 0 : ctx.getDataMap().get("rows")));

		if (curPage > 1) {
			curNum = (curPage - 1) * pageNum;
		}

		List<Income> list = new ArrayList<Income>();

		int count = bzkIncomeService.queryIncomeCount(income);
		if (count > 0) {
			list = bzkIncomeService.queryIncomeList(income, curNum, pageNum);
		}

		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("total", count);
		dataMap.put("rows", list);
		ctx.setDataMap(dataMap);
	}

	/**
	 * 添加
	 */
	public void saveIncome(Context ctx) throws CoreException {
		Gson json = new Gson();
		String operator = ctx.getData("userId");
		Income income = json.fromJson(json.toJson(ctx.getDataMap()), Income.class);
		income.setOperator(operator);
		income.setUuId(SequenceUtil.getUUID());
		if(income.getStatus() == null || income.getStatus() == ""){
			income.setStatus("1");
		}
		try {
			bzkIncomeService.insertIncome(income);
		} catch (CoreException e) {
			ctx.setData("exMsg", e.getMessage());
			throw e;
		}
		ctx.setData("isSuccess", true);
	}

	/**
	 * 修改
	 */
	public void updateIncome(Context ctx) throws CoreException {
		Gson json = new Gson();
		Income income = json.fromJson(json.toJson(ctx.getDataMap()), Income.class);
		try {
			bzkIncomeService.updateIncome(income);
		} catch (CoreException e) {
			ctx.setData("exMsg", e.getMessage());
			throw e;
		}
		ctx.setData("isSuccess", true);
	}

	/**
	 * 删除
	 */
	public void deleteIncome(Context ctx) throws CoreException {	
		try {
			Income income = getIncome(ctx,"ids");
			bzkIncomeService.deleteIncome(income);
			ctx.setData("isSuccess", true);
		} catch (CoreException e) {
			ctx.setData("isSuccess", false);
			ctx.setData("exMsg", e.getMessage());
			throw e;
		}
	}
	
	/**
	 * 批量启用
	 */
	public void setIncomeStatusOK(Context ctx) throws CoreException{
		try {
			Income income = getIncome(ctx,"ids","Status");
			bzkIncomeService.setIncomeStatusOK(income);
			ctx.setData("isSuccess",true);
		}catch(CoreException e){
			ctx.setData("exMsg",e.getMessage());
			ctx.setData("isSuccess",false);
			throw e;
		}
	}
	
	/**
	 * 批量禁用
	 */
	public void setIncomeStatusNO(Context ctx) throws CoreException{
		try {
			Income income = getIncome(ctx,"ids","Status");
			bzkIncomeService.setIncomeStatusNO(income);
			ctx.setData("isSuccess",true);
		}catch(CoreException e){
			ctx.setData("exMsg",e.getMessage());
			ctx.setData("isSuccess",false);
			throw e;
		}
	}
	
	
	/**
	 * 后台校验
	 */
	public void checkData(Context ctx) throws CoreException {
		Gson json = new Gson();
		Income income = json.fromJson(json.toJson(ctx.getDataMap()), Income.class);
		String number = "^[0-9]{1,10}$";
		
		String cityCode = income.getCityCode();
		if (null == cityCode || cityCode.trim().isEmpty()) {
			throw new CoreException("请选择城市代码!");
		}
		
		String unionType = income.getUnionType();
		if (null == unionType || unionType.trim().isEmpty()) {
			throw new CoreException("请选择单位性质!");
		}
		
		String education = income.getEducation();
		if (null == education || education.trim().isEmpty()) {
			throw new CoreException("请选择教育程度!");
		}
		
		String industryCode = income.getIndustryCode();
		if (null == industryCode || industryCode.trim().isEmpty()) {
			throw new CoreException("请选择行业代码!");
		}
		
		String jobCode = income.getJobCode();
		if (null == jobCode || jobCode.trim().isEmpty()) {
			throw new CoreException("请选择职业代码!");
		}
		
		String matchIncome = income.getMatchIncome();
		if (null == matchIncome || matchIncome.trim().isEmpty()) {
			throw new CoreException("行职业匹配收入不能为空!");
		} else if (matchIncome.trim().length() > 10){
			throw new CoreException("行职业匹配收入长度不能超过10位!");
		} else if (!matchIncome.trim().matches(number)) {
			throw new CoreException("请输入10位以内的数字!");
		}
		
		String status = income.getStatus();
		if (!"".equals(status) && !"0".equals(status) && !"1".equals(status)) {
			throw new CoreException("启用状态格式不正确!");
		}else if("".equals(status)){
			income.setStatus("1");
		}
		
	}
	
	/**
	 * 公共部分的验证操作
	 */
	public Income getIncome(Context ctx,String ...strings) throws CoreException{
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
		Income income = gson.fromJson(gson.toJson(map), Income.class);
		
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
