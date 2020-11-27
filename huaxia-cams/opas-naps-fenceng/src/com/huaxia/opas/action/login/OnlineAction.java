package com.huaxia.opas.action.login;

import java.util.ArrayList;
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
import com.huaxia.opas.cache.service.ITokenService;
import com.huaxia.opas.domain.system.ApOnline;
import com.huaxia.opas.service.system.ApOnlineService;
/**
 * 在線用戶
 * 
 * @author 
 *
 */
public class OnlineAction implements Action{
	private static Logger logger = LoggerFactory.getLogger(LoginAction.class);
	@Resource(name = "apOnlineService")
	private ApOnlineService apOnlineService;
	@Resource(name = "tokenService")
	private ITokenService tokenService;
	/**
	 * 查询在线用户列表信息
	 * @throws CoreException 
	 */
	public void listOnline(Context ctx) throws CoreException {
		System.out.println("查询在线用户列表");
		Gson gson = new Gson();
		ApOnline apOnline = gson.fromJson(gson.toJson(ctx.getDataMap()),
				ApOnline.class);
		int curNum = 0;
		int curPage = Integer.parseInt(String.valueOf(ctx.getDataMap().get(
				"page") == null ? 0 : ctx.getDataMap().get("page")));
		int pageNum = Integer.parseInt(String.valueOf(ctx.getDataMap().get(
				"rows") == null ? 0 : ctx.getDataMap().get("rows")));
		if (curPage > 1) {
			curNum = (curPage - 1) * pageNum;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("onlineId", apOnline.getOnlineId());// 用户状态
		map.put("status", apOnline.getStatus());// 用户状态
		map.put("ipAddr", apOnline.getIpAddr());// IP地址
		map.put("userName", apOnline.getUserName());// 用户姓名
		map.put("userCode", apOnline.getUserCode());//登录名
		int count = apOnlineService.queryOnlineCount(map);
		List<ApOnline> list = new ArrayList<ApOnline>();
		if (count > 0) {
			list = apOnlineService.queryOnlineList(map, curNum, pageNum);
		}
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("total", count);
		dataMap.put("rows", list);
		ctx.setDataMap(dataMap);
	}
	
	/**
	 * 强制用户下线
	 * @throws CoreException 
	 */
	public void offLineUser(Context ctx) throws CoreException {
		System.out.println("踢下线");
		try{
			Map<String, Object> map = ctx.getDataMap();
			String userId = (String)map.get("userIds");
			ApOnline apOnline = apOnlineService.queryApUserByUserId(userId);
			apOnline.setStatus("0");
			apOnlineService.updateApOnline(apOnline);
			tokenService.deleteToken(userId);
			ctx.setData("isSuccess", true);
		}catch(Exception e){
			ctx.setData("isSuccess", false);
			ctx.setData("exMsg", e.getMessage());
		}
	}
}
