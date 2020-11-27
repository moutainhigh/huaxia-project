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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.huateng.neofp.core.Action;
import com.huateng.neofp.core.Context;
import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.sysparm.MatchIdenity;
import com.huaxia.opas.domain.sysparm.MatchIdenity;
import com.huaxia.opas.domain.system.ApUser;
import com.huaxia.opas.service.sysparm.MatchIdenityService;
import com.huaxia.opas.service.system.ApUserService;
import com.huaxia.opas.util.SequenceUtil;

/**
 * 
 * 简项公安参数匹配
 * @author wangtao
 * @version v1.0
 *2017-10-12
 */
public class MatchIdenityAction implements Action {

	private static Logger logger = LoggerFactory.getLogger(MatchIdenityAction.class);

	@Resource(name = "matchIdenityService")
	private MatchIdenityService matchIdenityService;
	@Resource(name = "apUserService")
	private ApUserService  apUserService;
	/**
	 * 查询简项公安匹配参数列表
	 * 
	 * @param ctx
	 * @throws CoreException 
	 */

	public void queryMatchIdenityList(Context ctx) throws CoreException {
		Gson gson = new Gson();
		MatchIdenity matchIdenity = gson.fromJson(gson.toJson(ctx.getDataMap()), MatchIdenity.class);

		int curNum = 0;
		int curPage = Integer
				.parseInt(String.valueOf(ctx.getDataMap().get("page") == null ? 0 : ctx.getDataMap().get("page")));
		int pageNum = Integer
				.parseInt(String.valueOf(ctx.getDataMap().get("rows") == null ? 0 : ctx.getDataMap().get("rows")));

		if (curPage > 1) {
			curNum = (curPage - 1) * pageNum;
		}

		int count = matchIdenityService.queryMatchIdenityCount(matchIdenity);

		List<MatchIdenity> list = new ArrayList<MatchIdenity>();

		if (count > 0) {
			list = matchIdenityService.queryMatchIdenityList(matchIdenity, curNum, pageNum);
			for (MatchIdenity matchIdenity2 : list) {
				String lstUpdUser = matchIdenity2.getLstUpdUser();
				if(lstUpdUser!=null&&!"".equals(lstUpdUser)){
					ApUser apUser = apUserService.queryApUserByUserCode(matchIdenity2.getLstUpdUser());
					if(apUser!=null){
						matchIdenity2.setLstUpdUser(apUser.getUserName());
					}
				}
				
			}
		}

		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("total", count);

		dataMap.put("rows", list);

		ctx.setDataMap(dataMap);

	}

	/**
	 * 添加简项公安参数
	 * 
	 * @throws Exception
	 */
	public void insertMatchIdenity(Context ctx) throws Exception {

		Gson json = new Gson();
		Map<String, Object> map = ctx.getDataMap();

		MatchIdenity matchIdenity = json.fromJson(json.toJson(map), MatchIdenity.class);

		// 给客户设置autoID
		matchIdenity.setId(SequenceUtil.getUUID());

		String userId = ctx.getData("userId");
		matchIdenity.setLstUpdUser(userId);

		try {
			if ("1".equals(matchIdenity.getStatus())) {
				matchIdenityService.insertMatchIdenityStart(matchIdenity);
			} else {
				matchIdenityService.insertMatchIdenityEnd(matchIdenity);
			}
			ctx.setData("isSuccess", true);
		} catch (Exception e) {
			ctx.setData("isSuccess", false);
			ctx.setData("exMsg", e.getMessage());
			throw e;
		}
	}

	/**
	 * 修改预授信信息
	 * 
	 * @throws Exception
	 */
	public void updateMatchIdenity(Context ctx) throws Exception {

		Gson json = new Gson();
		Map<String, Object> map = ctx.getDataMap();
		MatchIdenity matchIdenity = json.fromJson(json.toJson(map), MatchIdenity.class);
		String id = ctx.getData("id");
		String userId = ctx.getData("userId");
		matchIdenity.setLstUpdUser(userId);
		try {
			String status = matchIdenityService.queryMatchIdenityStatus(id);
			if((matchIdenity.getStatus()).equals(status)){
				matchIdenityService.updateMatchIdenityWithoutStatus(matchIdenity);
			}else{
				matchIdenityService.updateMatchIdenity(matchIdenity);
			}
			ctx.setData("isSuccess", true);
		} catch (Exception e) {
			ctx.setData("exMsg", e.getMessage());
			throw e;
		}
	}

	/**
	 * 简项公安参数启用
	 * 
	 * @throws Exception
	 */
	public void startMatchIdenity(Context ctx) throws Exception {
		Map<String, Object> map = ctx.getDataMap();
		String id = ctx.getData("id");
		String userId = ctx.getData("userId");
		MatchIdenity matchIdenity = new MatchIdenity();
		matchIdenity.setLstUpdUser(userId);
		matchIdenity.setStatus("1");
		matchIdenity.setId(id);
		try {
			matchIdenityService.updateStartStatusMatchIdenity(matchIdenity);
			ctx.setData("isSuccess", true);
		} catch (Exception e) {
			ctx.setData("isSuccess", false);
			ctx.setData("exMsg", e.getMessage());
			throw e;
		}
	}

	/**
	 * 简项公安参数停用
	 * 
	 * @throws Exception
	 */
	public void stopMatchIdenity(Context ctx) throws Exception {
		Map<String, Object> map = ctx.getDataMap();
		String id = ctx.getData("id");
		String userId = ctx.getData("userId");
		MatchIdenity matchIdenity = new MatchIdenity();
		matchIdenity.setLstUpdUser(userId);
		matchIdenity.setStatus("0");
		matchIdenity.setId(id);
		try {
			matchIdenityService.updateStopStatusMatchIdenity(matchIdenity);
			ctx.setData("isSuccess", true);
		} catch (Exception e) {
			ctx.setData("isSuccess", false);
			ctx.setData("exMsg", e.getMessage());
			throw e;
		}
	}

	/**
	 * 删除
	 * 
	 * @throws Exception
	 */
	public void deleteMatchIdenity(Context ctx) throws Exception {//实际不删除，只是给某个字段赋值不让它展示
		Map<String, Object> map = ctx.getDataMap();
		String id = ctx.getData("id");
		String userId = ctx.getData("userId");
		MatchIdenity matchIdenity = new MatchIdenity();
		matchIdenity.setLstUpdUser(userId);
		matchIdenity.setStatus("0");//删除让它的状态变成停用
		matchIdenity.setId(id);
		matchIdenity.setViewFlag("1");
		try {
			matchIdenityService.updateMatchIdenityWithViewFlag(matchIdenity);
		} catch (Exception e) {
			ctx.setData("isSuccess", false);
			ctx.setData("exMsg", e.getMessage());
			throw e;
		}
		ctx.setData("isSuccess", true);
	}
	
	/**
	 * 批量删除
	 * 按照要求,删除只是将ViewFlag字段赋值为1,页面不显示该条数据,同时将状态设置为停用.全部删除同样操作.
	 */
	public void deleteMatchIdenitys(Context ctx) throws Exception{
		try{	
			MatchIdenity matchIdenity = getMatchIdenity(ctx, "ids");
			matchIdenityService.deleteMatchIdenitys(matchIdenity);
			ctx.setData("isSuccess", true);
		}catch(CoreException e){
			ctx.setData("isSuccess", false);
			ctx.setData("exMsg", e.getMessage());
			throw e;
		}
	}
	
	/**
	 * 全部删除
	 */
	public void deleteAll(Context ctx) throws CoreException {
		try {
			matchIdenityService.deleteAll();
			ctx.setData("isSuccess", true);
		} catch (Exception e) {
			ctx.setData("isSuccess", false);
			ctx.setData("exMsg", e.getMessage());
			throw e;
		}
	}
	
	/**
	 * 公共部分的验证操作
	 */
	public MatchIdenity getMatchIdenity(Context ctx, String... strings) throws CoreException {
		List<String> list = Arrays.asList(strings);
		Map<String, Object> map = ctx.getDataMap();
		Gson gson = new Gson();
		MatchIdenity MatchIdenity = gson.fromJson(gson.toJson(map), MatchIdenity.class);

		String operator = (String) ctx.getData("userId");
		if (null == operator || operator.trim().isEmpty()) {
			CoreException e = new CoreException("操作用户operator不能为空，请先登录");
			throw e;
		}
		MatchIdenity.setLstUpdUser(operator);

		for (String key : strings) {
			switch (key) {
			case "ids":
				@SuppressWarnings("unchecked")
				List<String> ids = (List<String>) map.get("ids");
				if (null == ids || ids.size() <= 0) {
					CoreException e = new CoreException("请选择需要操作的行");
					throw e;
				}
				MatchIdenity.setIds(ids);
				break;

			case "Status":
				String Status = (String) map.get("Status");
				if (null == Status || (!Status.trim().equals("1") && !Status.trim().equals("0"))) {
					CoreException e = new CoreException("启用状态值Status非法");
					throw e;
				}
				break;
				
			default:
				break;
			}
		}
		return MatchIdenity;
	}
}
