package com.huaxia.opas.action.login;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import com.huaxia.opas.domain.system.OperationLog;
import com.huaxia.opas.service.system.OperationLogService;

/**
 * 操作日志
 * 
 * @author shihuan
 *
 */
public class OperationLogAction implements Action {

	private static Logger logger = LoggerFactory
			.getLogger(OperationLogAction.class);

	@Resource(name = "operationLogService")
	private OperationLogService operationLogService;

	/**
	 * 查询用户列表信息
	 */
	public void queryOperaLog(Context ctx) {

		Map<String, Object> map = ctx.getDataMap();

		String userDate1 = (String) map.get("userDate");

		Date userDate = null;
		if (!"".equals(userDate1) && null != userDate1) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			try {
				userDate = sdf.parse(userDate1);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

		map.remove("userDate");

		Gson gson = new Gson();

		OperationLog operationlog = gson.fromJson(gson.toJson(map),
				OperationLog.class);

		if (!"".equals(userDate1)) {

			operationlog.setUserDate(userDate);
		}

		int curNum = 0;

		int curPage = Integer.parseInt(String.valueOf(ctx.getDataMap().get(
				"page") == null ? 0 : ctx.getDataMap().get("page")));

		int pageNum = Integer.parseInt(String.valueOf(ctx.getDataMap().get(
				"rows") == null ? 0 : ctx.getDataMap().get("rows")));

		if (curPage > 1) {

			curNum = (curPage - 1) * pageNum;

		}

		int count = operationLogService.queryLogCount(operationlog);

		List<OperationLog> list = new ArrayList<OperationLog>();

		if (count > 0) {

			list = operationLogService.queryLogList(operationlog, curNum,
					pageNum);

		}

		Map<String, Object> dataMap = new HashMap<String, Object>();

		dataMap.put("total", count);

		dataMap.put("rows", list);

		ctx.setDataMap(dataMap);

	}

}
