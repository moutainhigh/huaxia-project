package com.huaxia.opas.action.sysparm;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.google.gson.Gson;
import com.huateng.neofp.core.Action;
import com.huateng.neofp.core.Context;
import com.huaxia.opas.domain.sysparm.BatchfileInfo;
import com.huaxia.opas.service.sysparm.BatchFileInfoService;

/**
 * 风险名单导入文件记录
 * 
 * @author shihuan
 *
 */
public class RistBatchFileInfoAction implements Action {
	@Resource(name = "batchFileInfoService")
	private BatchFileInfoService batchFileInfoService;

	/**
	 * 查询用户列表信息
	 */
	public void queryRistBatchFileInfo(Context ctx) {

		Map<String, Object> map = ctx.getDataMap();

		String userDate1 = (String) map.get("inputTime");

		Date userDate = null;
		if (!"".equals(userDate1) && null != userDate1) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			try {
				userDate = sdf.parse(userDate1);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

		map.remove("inputTime");

		Gson gson = new Gson();

		BatchfileInfo batchfileinfo = gson.fromJson(gson.toJson(map),BatchfileInfo.class);

		if (!"".equals(userDate1)) {

			batchfileinfo.setInputTime(userDate);
		}

		int curNum = 0;

		int curPage = Integer.parseInt(String.valueOf(ctx.getDataMap().get(
				"page") == null ? 0 : ctx.getDataMap().get("page")));

		int pageNum = Integer.parseInt(String.valueOf(ctx.getDataMap().get(
				"rows") == null ? 0 : ctx.getDataMap().get("rows")));

		if (curPage > 1) {

			curNum = (curPage - 1) * pageNum;

		}

		int count = batchFileInfoService.selectRistAllCount(batchfileinfo);

		List<BatchfileInfo> list = new ArrayList<BatchfileInfo>();

		if (count > 0) {

			list = batchFileInfoService.selectRistAll(batchfileinfo, curNum,pageNum);

		}

		Map<String, Object> dataMap = new HashMap<String, Object>();

		dataMap.put("total", count);

		dataMap.put("rows", list);

		ctx.setDataMap(dataMap);

	}

}