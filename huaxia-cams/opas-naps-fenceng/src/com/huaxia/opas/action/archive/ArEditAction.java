package com.huaxia.opas.action.archive;

import java.text.ParseException;
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
import com.huaxia.opas.domain.archive.ArDetail;
import com.huaxia.opas.domain.archive.ArFailBack;
import com.huaxia.opas.service.archive.ArEditService;

/**
 * 归档修改
 * 
 * @author qbn
 *
 */
public class ArEditAction implements Action{
	
	private static Logger logger = LoggerFactory.getLogger(ArEditAction.class);
	
	@Resource(name = "arEditService")
	private ArEditService arEditService;
	
	/**
	 * 查询归档报发卡失败件列表信息
	 * @throws ParseException 
	 */
	public void listFailBack(Context ctx) throws ParseException {
		Gson gson = new Gson();
		Map map = ctx.getDataMap();
		ArFailBack arFailBack = gson.fromJson(gson.toJson(map), ArFailBack.class);
		
		//将查询的流水号转换成大写
		String appId = arFailBack.getAppId();
		if(appId != null && !"".equals(appId)){
			String APPID = appId.toUpperCase();
			arFailBack.setAppId(APPID);
		}
				
		int curNum = 0;
		int curPage = Integer
				.parseInt(String.valueOf(ctx.getDataMap().get("page") == null ? 0 : ctx.getDataMap().get("page")));
		int pageNum = Integer
				.parseInt(String.valueOf(ctx.getDataMap().get("rows") == null ? 0 : ctx.getDataMap().get("rows")));

		if (curPage > 1) {
			curNum = (curPage - 1) * pageNum;
		}

		List<ArFailBack> list = new ArrayList<ArFailBack>();

		int count = arEditService.queryFailBackCount(arFailBack);
		if (count > 0) {
			list = arEditService.queryFailBackList(arFailBack, curNum, pageNum);
		}

		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("total", count);
		dataMap.put("rows", list);
		ctx.setDataMap(dataMap);
	}

	/**修改归档明细表中申请件的发卡状态
	 * */
	public void updateArDetail(Context ctx){
		Map map = ctx.getDataMap();
		String appId = (String) map.get("appId");
		String userId = (String) map.get("userId");
		ArDetail arDetail = new ArDetail();
		arDetail.setAppId(appId);
		arDetail.setOperator(userId);
		arEditService.updateArDetail(arDetail);
		ctx.setData("isSucc", true);
	}
	
	/**修改失败归档信息表中申请件的状态为"已修改"
	 * */
	public void updateArFailBack(Context ctx){
		Map map = ctx.getDataMap();
		String appId = (String) map.get("appId");
		String userId = (String) map.get("userId");
		ArFailBack arFailBack = new ArFailBack();
		arFailBack.setAppId(appId);
		arFailBack.setOperator(userId);
		int i = arEditService.updateArFailBack(arFailBack);
		if(i>0)
		ctx.setData("isSucc", true);
	}
}
