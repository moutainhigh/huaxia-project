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
import com.huaxia.opas.service.sysparm.BalanceService;
import com.huaxia.opas.util.SequenceUtil;

/**
 * 余额转移
 * 
 * @author lipengfei
 *
 */
public class BalanceAction implements Action {

	@Resource(name = "balanceService")
	private BalanceService balanceService;

	/**
	 * 查询
	 */
	public void querybalanceMove(Context ctx) {
		Gson gson = new Gson();
		Balance balance = gson.fromJson(gson.toJson(ctx.getDataMap()), Balance.class);

		int curNum = 0;
		int curPage = Integer
				.parseInt(String.valueOf(ctx.getDataMap().get("page") == null ? 0 : ctx.getDataMap().get("page")));
		int pageNum = Integer
				.parseInt(String.valueOf(ctx.getDataMap().get("rows") == null ? 0 : ctx.getDataMap().get("rows")));

		if (curPage > 1) {
			curNum = (curPage - 1) * pageNum;
		}

		List<Balance> list = new ArrayList<Balance>();

		int count = balanceService.queryBalanceCount(balance);
		if (count > 0) {
			list = balanceService.queryBalanceMove(balance, curNum, pageNum);
		}

		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("total", count);
		dataMap.put("rows", list);
		ctx.setDataMap(dataMap);
	}

	/**
	 * 添加
	 */
	public void saveBalance(Context ctx) throws CoreException {
		try {
			Balance balance = getBalance(ctx);
			String prodCode = balance.getProdCode();
			int count = balanceService.queryBalanceCountByProCode(prodCode);
			if(count>0){
				throw new CoreException("产品编号重复");
			}
			// 调用校验方法,校验添加结果
			checkData(balance);
			balance.setAutoId(SequenceUtil.getUUID());
			String operator = balance.getOperator();
			balance.setCrtUser(operator);
			balanceService.insertBalance(balance);
			ctx.setData("isSuccess", true);
		} catch (CoreException e) {
			ctx.setData("exMsg", e.getMessage());
			ctx.setData("isSuccess", false);
			throw e;
		}
	}

	/**
	 * 修改,hu
	 */
	public void updateBalance(Context ctx) throws CoreException {
		Balance balance = getBalance(ctx);
		
		//获取id,根据id查询数据库状态码
		String id = balance.getAutoId();
		Balance balance2 = balanceService.findByid(id);
		//获取到修改之前的状态（1启用，0停用）
		String status1 = balance2.getStatus();//修改前的状态
		System.out.println(status1+"========修改前的状态updateBalance2");
		String status = balance.getStatus();//修改后的状态
		System.out.println(status+"========修改后的状态updateBalance2");
		if (!status.equals(status1)) {//未修改的当前状态和修改后的当前状态进行对比，不一样的情况下
			System.out.println("方法二updateBalance2");
			try {
				
				// 调用校验方法,校验添加结果
				checkData(balance);
				String operator = balance.getOperator();
				balance.setLstUpUser(operator);
				balanceService.updateBalance2(balance);
				ctx.setData("isSuccess", true);
			} catch (CoreException e) {
				ctx.setData("exMsg", e.getMessage());
				ctx.setData("isSuccess", false);
				throw e;
			}

		}else if(status.equals(status1)){
			System.out.println("方法一updateBalance");
			try {
				System.out.println(status1+"========修改前的状态updateBalance");
				System.out.println(status+"========修改后的状态updateBalance");
				// 调用校验方法,校验添加结果
				checkData(balance);
				String operator = balance.getOperator();
				balance.setLstUpUser(operator);
				balanceService.updateBalance(balance);
				ctx.setData("isSuccess", true);
			} catch (CoreException e) {
				ctx.setData("exMsg", e.getMessage());
				ctx.setData("isSuccess", false);
				throw e;
			}
			
		}
		

	}

	/**
	 * 删除
	 */
	public void deleteBalance(Context ctx) throws CoreException {
		try {
			 Balance balance = getBalance(ctx, "ids");
			 balanceService.deleteBalance(balance);
			ctx.setData("isSuccess",true);
		}catch(CoreException e){
			ctx.setData("exMsg",e.getMessage());
			ctx.setData("isSuccess",false);
			throw e;
		}
	}

	/**
	 * 批量启用
	 */
	public void setBalanceStatusOK(Context ctx) throws CoreException{
		try {
			Balance balance = getBalance(ctx,"ids","Status");
			balanceService.setBalanceStatusOK(balance);
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
	public void setBalanceStatusNO(Context ctx) throws CoreException{
		try {
			Balance balance = getBalance(ctx,"ids","Status");
			balanceService.setBalanceStatusNO(balance);
			ctx.setData("isSuccess",true);
		}catch(CoreException e){
			ctx.setData("exMsg",e.getMessage());
			ctx.setData("isSuccess",false);
			throw e;
		}
	}
	
	/**
	 * 校验的方法
	 */
	public void checkData(Balance balance) throws CoreException {
		// 正则表达式的字符串
		String txt = "[^_][\\u4e00-\\u9fa5[^\\x00-\\xff][\\w]]*";
		String code = "[a-zA-Z0-9]{1,20}";
		String number = "^[0-9]{1,10}$";

		String prodCode = balance.getProdCode();
		if (null == prodCode || prodCode.trim().isEmpty()) {
			throw new CoreException("产品编号不能为空!");
		} else if (prodCode.trim().length() > 4) {
			throw new CoreException("产品编号长度不能超过4个字符!");
		} else if (!prodCode.trim().matches(code)) {
			throw new CoreException("产品编号应为英文、数字组成!");
		}

		String prodName = balance.getProdName();
		if (null == prodName || prodName.trim().isEmpty()) {
			throw new CoreException("产品名称不能为空!");
		} else if (prodName.trim().length() > 32) {
			throw new CoreException("产品名称长度不能超过32个字符!");
		}/* else if (!prodName.trim().matches(txt)) {
			throw new CoreException("产品名称应为中英文、数字、下滑线组成，首字母不能为下滑线!");
		}*/

		String moveLimitPer = balance.getMoveLimitPer();
		if (null == moveLimitPer || moveLimitPer.trim().isEmpty()) {
			throw new CoreException("转移额度百分比不能为空!");
		}

		String maxLimit = balance.getMaxLimit();
		if (maxLimit.trim().length() > 10) {
			throw new CoreException("最高限额长度不能超过10位!");
		} 

		String LowLimit = balance.getLowLimit();
		if (LowLimit.trim().length() > 10) {
			throw new CoreException("最低限额长度不能超过10位!");
		} 

		String tmp = balance.getStatus();
		if (!"".equals(tmp) && !"0".equals(tmp) && !"1".equals(tmp)) {
			throw new CoreException("启用状态格式不正确!");
		} else if ("".equals(tmp)) {
			balance.setStatus("1");
		}
	}
	
	/**
	 * 公共部分的验证操作
	 */
	public Balance getBalance(Context ctx,String ...strings) throws CoreException{
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
		Balance balance = gson.fromJson(gson.toJson(map), Balance.class);
		
		String operator = (String)ctx.getData("userId");
		if( null == operator || operator.trim().isEmpty()){
			CoreException e = new CoreException("操作用户operator不能为空，请先登录");
			throw e;
		}
		balance.setOperator(operator);
		
		
		for (String key : strings) {
			switch (key) {
			case "ids":
				@SuppressWarnings("unchecked")
				List<String> ids = (List<String>) map.get("ids");
				if( null == ids || ids.size() <= 0){
					CoreException e = new CoreException("请选择需要操作的行ids");
					throw e;
				}
				balance.setIds(ids);
				break;
				
			case "Status":
				String Status = (String) map.get("Status");
				if( null == Status || (!Status.trim().equals("1") && !Status.trim().equals("0"))){
					CoreException e = new CoreException("启用状态值Status非法");
					throw e;
				}
				break;
				
			case "crtDate":
				balance.setCrtDate(createTime);
				break;
				
			default:
				break;
			}
		}
		return balance;
	}
}
