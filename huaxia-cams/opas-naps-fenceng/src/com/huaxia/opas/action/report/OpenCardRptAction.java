package com.huaxia.opas.action.report;

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
import com.huaxia.opas.domain.opencard.OpenCardRpt;
import com.huaxia.opas.service.opencard.OpenCardRptService;

/**
 * 开卡失败报告信息查询
 * 
 * @author wangk
 *
 */
public class OpenCardRptAction implements Action {

	private static Logger logger = LoggerFactory
			.getLogger(OpenCardRptAction.class);

	@Resource(name = "openCardRptService")
	private OpenCardRptService openCardRptService;

	/**
	 * Redis存储value查询
	 * 
	 * @param ctx
	 * @throws CoreException
	 */
	public void queryOpenCardRpt(Context ctx) throws Exception {

		int curNum = 0;

		int curPage = Integer.parseInt(String.valueOf(ctx.getDataMap().get(
				"page") == null ? 0 : ctx.getDataMap().get("page")));
		int pageNum = Integer.parseInt(String.valueOf(ctx.getDataMap().get(
				"rows") == null ? 0 : ctx.getDataMap().get("rows")));

		if (curPage > 1) {
			curNum = (curPage - 1) * pageNum;
		}

		Map<String, Object> infoMap = new HashMap<String, Object>();

		infoMap.put("startDate", ctx.getDataMap().get("startDate"));
		infoMap.put("endDate", ctx.getDataMap().get("endDate"));
		infoMap.put("openCardFileName", ctx.getDataMap()
				.get("openCardFileName"));

		int count = openCardRptService.queryOpenCardRptCountByInfo(infoMap);
		List<OpenCardRpt> openCardRptList = new ArrayList<OpenCardRpt>();
		if (count > 0) {
			openCardRptList = openCardRptService.queryOpenCardRptListByInfo(
					infoMap, curNum, pageNum);
		}

		Map<String, Object> dataMap = new HashMap<String, Object>();

		dataMap.put("total", count);

		dataMap.put("rows", openCardRptList);

		ctx.setDataMap(dataMap);

	}

	@Override
	public String toString() {
		return "OpenCardRptAction [openCardRptService=" + openCardRptService
				+ "]";
	}
}
