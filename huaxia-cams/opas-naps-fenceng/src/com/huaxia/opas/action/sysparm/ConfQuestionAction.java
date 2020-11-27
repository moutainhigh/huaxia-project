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
import com.huaxia.opas.domain.sysparm.ConfQuestion;
import com.huaxia.opas.domain.sysparm.CreditWhiteList;
import com.huaxia.opas.domain.sysparm.Income;
import com.huaxia.opas.service.sysparm.ConfQuestionService;
import com.huaxia.opas.util.SequenceUtil;

import net.sf.json.JSONArray;

/**
 * 征信问题提问库
 * 
 * @author lipengfei
 *
 */
public class ConfQuestionAction implements Action{

	@Resource(name = "confQuestionService")
	private ConfQuestionService confQuestionService;

	/**
	 * 查询
	 * @throws CoreException 
	 * @throws ParseException 
	 */
	public void queryConfQuestion(Context ctx) throws CoreException, ParseException {
		ConfQuestion confQuestion = getConfQuestion(ctx);
		//ConfQuestion confQuestion = gson.fromJson(gson.toJson(ctx.getDataMap()), ConfQuestion.class);
		String date = confQuestion.getCrtDate1();
		if(!"".equals(date)&&date!=null){
	
			confQuestion.setCrtDate(new SimpleDateFormat("yyyy-MM-dd").parse(date));
		}
		int curNum = 0;
		int curPage = Integer
				.parseInt(String.valueOf(ctx.getDataMap().get("page") == null ? 0 : ctx.getDataMap().get("page")));
		int pageNum = Integer
				.parseInt(String.valueOf(ctx.getDataMap().get("rows") == null ? 0 : ctx.getDataMap().get("rows")));

		if (curPage > 1) {
			curNum = (curPage - 1) * pageNum;
		}

		List<ConfQuestion> list = new ArrayList<ConfQuestion>();
		int count = confQuestionService.queryConfQuestionCount(confQuestion);
		if (count > 0) {
			list = confQuestionService.queryConfQuestionList(confQuestion, curNum, pageNum);
		}

		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("total", count);
		dataMap.put("rows", list);
		ctx.setDataMap(dataMap);
	}

	/**
	 * 添加
	 */
	public void saveConfQuestion(Context ctx) throws CoreException {
		try {
			ConfQuestion confQuestion = getConfQuestion(ctx);
			confQuestion.setAutoId(SequenceUtil.getUUID());
			checkData(confQuestion);
			confQuestionService.insertConfQuestion(confQuestion);
		} catch (CoreException e) {
			ctx.setData("exMsg", e.getMessage());
			throw e;
		}
		ctx.setData("isSuccess", true);
	}

	/**
	 * 修改
	 */
	public void updateConfQuestion(Context ctx) throws CoreException {
		try {
			ConfQuestion confQuestion = getConfQuestion(ctx);
			checkData(confQuestion);
			confQuestionService.updateConfQuestion(confQuestion);
		} catch (CoreException e) {
			ctx.setData("exMsg", e.getMessage());
			throw e;
		}
		ctx.setData("isSuccess", true);
	}

	/**
	 * 删除
	 */
	public void deleteConfQuestion(Context ctx) throws CoreException {
		try {
			ConfQuestion confQuestion = getConfQuestion(ctx,"ids");
			confQuestionService.deleteConfQuestion(confQuestion);
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
	public void confQuestionsetStatusOK(Context ctx) throws CoreException{
		try {
			ConfQuestion confQuestion = getConfQuestion(ctx,"ids","Status");
			confQuestionService.confQuestionsetStatusOK(confQuestion);
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
	public void confQuestionsetStatusNO(Context ctx) throws CoreException{
		try {
			ConfQuestion confQuestion = getConfQuestion(ctx,"ids","Status");
			confQuestionService.confQuestionsetStatusNO(confQuestion);
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
	public void checkData(ConfQuestion confQuestion) throws CoreException{
		String questionDesc = confQuestion.getQuestionDesc();
		if (questionDesc.trim().length() > 512) {
			throw new CoreException("请把问题描述控制在512个字符以内!");
		}
	}
	
	/**
	 * 公共部分的验证操作
	 */
	public ConfQuestion getConfQuestion(Context ctx,String ...strings) throws CoreException{
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
		ConfQuestion confQuestion = gson.fromJson(gson.toJson(map), ConfQuestion.class);
		
		String operator = (String)ctx.getData("userId");
		if( null == operator || operator.trim().isEmpty()){
			CoreException e = new CoreException("操作用户operator不能为空，请先登录");
			throw e;
		}
		confQuestion.setOperator(operator);
		
		for (String key : strings) {
			switch (key) {
			case "ids":
				@SuppressWarnings("unchecked")
				List<String> ids = (List<String>) map.get("ids");
				if( null == ids || ids.size() <= 0){
					CoreException e = new CoreException("请选择需要操作的行");
					throw e;
				}
				confQuestion.setIds(ids);
				break;
				
			case "Status":
				String Status = (String)map.get("Status");
				if( null == Status || (!Status.trim().equals("1") && !Status.trim().equals("0"))){
					CoreException e = new CoreException("启用状态值Status非法");
					throw e;
				}
				break;
				
			case "crtDate":
				confQuestion.setCrtDate(createTime);
				break;
				
			default:
				break;
			}
		}
		return confQuestion;
	}
	
	/**
	 * 查看历史记录
	 */
	public void queryconfQuestionhistory(Context ctx)throws CoreException{
		ConfQuestion confQuestion = getConfQuestion(ctx,"autoId");
		int curPage = Integer.parseInt(String.valueOf(ctx.getDataMap().get("page")==null?1:ctx.getDataMap().get("page")));
		int pageNum = Integer.parseInt(String.valueOf(ctx.getDataMap().get("rows")==null?10:ctx.getDataMap().get("rows")));
		Map<String, Object> dataMap = confQuestionService.queryCreditInfoHistoryList(confQuestion, curPage, pageNum);
		ctx.setDataMap(dataMap);
	}
}
