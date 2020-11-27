package com.huaxia.opas.action.common;

import java.util.ArrayList;
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
import com.huaxia.opas.domain.apply.ApplyRemark;
import com.huaxia.opas.service.apply.ApplyRemarkService;
import com.huaxia.opas.util.SequenceUtil;

/**
 * 备注
 * 
 * @author xiebinxu 2017年2月25日
 */

public class ApplyRemarkAction implements Action {

	private static Logger logger = LoggerFactory
			.getLogger(ApplyRemarkAction.class);

	@Resource(name = "applyRemarkService")
	private ApplyRemarkService applyRemarkService;

	public void addApplyRemark(Context ctx) throws CoreException {

		Map map = ctx.getDataMap();
		String appId = (String) map.get("appId");
		String userId = (String) map.get("userId");
		String remarkInfo = (String) map.get("remarkInfo");
		ApplyRemark applyRemark = new ApplyRemark();
		applyRemark.setAppId(appId);
		applyRemark.setRemarkId(SequenceUtil.getUUID());
		applyRemark.setRemarkInfo(remarkInfo);
		applyRemark.setRemarkTime(new Date());
		applyRemark.setRemarkUser(userId);
		try {
			applyRemarkService.insert(applyRemark);
		} catch (CoreException e) {
			ctx.setData("exMsg", e.getMessage());
			throw e;
		}
		ctx.setData("isSuccess", true);
	}

	@SuppressWarnings("unchecked")
	public void listApplyRemark(Context ctx) {

		Map map = ctx.getDataMap();
		String appId = (String) map.get("appId");
		ApplyRemark applyRemark = new ApplyRemark();
		int curNum = 0;
		int curPage = Integer.parseInt(String.valueOf(ctx.getDataMap().get(
				"page") == null ? 0 : ctx.getDataMap().get("page")));
		int pageNum = Integer.parseInt(String.valueOf(ctx.getDataMap().get(
				"rows") == null ? 0 : ctx.getDataMap().get("rows")));

		if (curPage > 1) {
			curNum = (curPage - 1) * pageNum;
		}

		List<ApplyRemark> list = new ArrayList<ApplyRemark>();
		applyRemark.setAppId(appId);
		int count = 0;
		try {
			//count = applyRemarkService.queryCount(applyRemark);
			count=applyRemarkService.queryCountWithYs(applyRemark);//预审拒绝码加备注信息
		} catch (CoreException e) {
			e.printStackTrace();
		}
		if (count > 0) {
			try {
				//list = applyRemarkService.queryList(applyRemark, curNum,pageNum);
				list=applyRemarkService.queryListWithYs(applyRemark, curNum, pageNum);//预审拒绝码加备注信息	
			} catch (CoreException e) {
				e.printStackTrace();
			}
		}

		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("total", count);
		dataMap.put("rows", list);
		ctx.setDataMap(dataMap);
	}
}
