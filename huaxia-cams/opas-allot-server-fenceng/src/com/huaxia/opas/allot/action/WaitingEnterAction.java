package com.huaxia.opas.allot.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huateng.neofp.core.Action;
import com.huateng.neofp.core.Context;
import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.service.allot.AllotApplyService;

/**
 * 待进入人工列表
 * 
 * @author wenyh
 *
 */
public class WaitingEnterAction implements Action {
	private static Logger log = LoggerFactory.getLogger(WaitingEnterAction.class);
	@Resource(name="allotApplyService")
	private AllotApplyService allotApplyService;
	
	/**
	 * @Description: 待进入人工件数查询
	 * @author 王德彬
	 * @version V1.0
	 * @param context
	 */
	public void countAppIdByShareTime(Context context) {
		String ydjFlag = (String)context.getData("ydjFlag");
		int count = 0;
		Map<String, Object> map = context.getData("jsondata");
		map.put("ydjFlag", ydjFlag);
		try {
			count = allotApplyService.queryWaitingEnterCount(map);
			log.info("WaitingEnterAction.countAppIdByShareTime 件数为：", count);
		} catch (Exception e) {
			log.error("WaitingEnterAction.countAppIdByShareTime occur error", e);
		}
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("total", count);
		context.setDataMap(dataMap);
	}
	
	/**
	 * @Description: 待进入人工件数查询
	 * @author 王德彬
	 * @version V1.0
	 * @param context
	 */
	public void countAppIdByYdjFlag(Context context) {
		String ydjFlag = (String)context.getData("ydjFlag");
		int count = 0;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ydjFlag", ydjFlag);
		try {
			count = allotApplyService.queryWaitingEnterCount(map);
			log.info("WaitingEnterAction.countAppIdByShareTime 件数为：", count);
		} catch (Exception e) {
			log.error("WaitingEnterAction.countAppIdByShareTime occur error", e);
		}
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("total", count);
		context.setDataMap(dataMap);
	}
	
	/**
	 * 根据时间查询待进入人工列表数据方法
	 * @param context
	 * @throws CoreException
	 */
	public void queryListDataByShareTime(Context ctx)throws CoreException{
		Map dataMap = ctx.getDataMap();
		// 参数
		String ydjFlag = (String)ctx.getData("ydjFlag");
		Map<String, Object> map = ctx.getData("jsondata"); 
		map.put("ydjFlag", ydjFlag);
		int curNum = 0;
		int curPage = Integer
				.parseInt(String.valueOf(ctx.getDataMap().get("page") == null ? 0 : ctx.getDataMap().get("page")));
		int pageNum = Integer
				.parseInt(String.valueOf(ctx.getDataMap().get("rows") == null ? 0 : ctx.getDataMap().get("rows")));

		if (curPage > 1) {
			curNum = (curPage - 1) * pageNum;
		}
		int count = 0;
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		count = allotApplyService.queryWaitingEnterCount(map);
		if (count > 0) {
			list = allotApplyService.queryWaitingEnterList(map, curNum, pageNum);
		}
		log.info("WaitingEnterAction.queryListDataByShareTime 根据时间查询数据：", count);
		dataMap.put("total", count);
		dataMap.put("rows", list);
		ctx.setDataMap(dataMap);
	}
	
}
