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
import com.huaxia.opas.domain.sysparm.Warning;
import com.huaxia.opas.service.sysparm.WarningService;
import com.huaxia.opas.util.SequenceUtil;

/**
 * WarningList
 * 
 * @author lipengfei
 *
 */
public class WarningAction implements Action {

	@Resource(name = "warningService")
	private WarningService warningService;

	/**
	 * 查询
	 */
	public void querywarningList(Context ctx) {
		Gson gson = new Gson();
		Warning warning = gson.fromJson(gson.toJson(ctx.getDataMap()), Warning.class);

		int curNum = 0;
		int curPage = Integer
				.parseInt(String.valueOf(ctx.getDataMap().get("page") == null ? 0 : ctx.getDataMap().get("page")));
		int pageNum = Integer
				.parseInt(String.valueOf(ctx.getDataMap().get("rows") == null ? 0 : ctx.getDataMap().get("rows")));

		if (curPage > 1) {
			curNum = (curPage - 1) * pageNum;
		}

		List<Warning> list = new ArrayList<Warning>();

		int count = warningService.queryWarningCount(warning);
		if (count > 0) {
			list = warningService.queryWarningList(warning, curNum, pageNum);
		}

		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("total", count);
		dataMap.put("rows", list);
		ctx.setDataMap(dataMap);
	}

	/**
	 * 添加
	 */
	public void savewarningList(Context ctx) throws CoreException {
		try {
			Warning warning = getWarning(ctx);
			warning.setAutoId(SequenceUtil.getUUID());
			String operator = warning.getOperator();
			warning.setCrtUser(operator);
			warningService.insertWarningList(warning);
		} catch (CoreException e) {
			ctx.setData("exMsg", e.getMessage());
			throw e;
		}
		ctx.setData("isSuccess", true);
	}

	/**
	 * 修改
	 */
	public void updateWarning(Context ctx) throws CoreException {
		try {
			Warning warning = getWarning(ctx);
			String operator = warning.getOperator();
			warning.setLstUpdUser(operator);
			warningService.updateWarning(warning);
		} catch (CoreException e) {
			ctx.setData("exMsg", e.getMessage());
			throw e;
		}
		ctx.setData("isSuccess", true);
	}

	/**
	 * 删除
	 */
	public void deletewarningList(Context ctx) throws CoreException {

		Map map = ctx.getDataMap();

		String autoId = (String) map.get("autoId");

		try {
			warningService.deleteWarningList(autoId);
			ctx.setData("isSuccess", true);
		} catch (CoreException e) {
			ctx.setData("isSuccess", false);
			ctx.setData("exMsg", e.getMessage());
			throw e;
		}
	}

	/**
	 * 后台校验
	 */
	public void checkData(Context ctx) throws CoreException {
		Gson json = new Gson();
		Warning warning = json.fromJson(json.toJson(ctx.getDataMap()), Warning.class);

		String riskType = warning.getRiskType();
		if (null == riskType || riskType.trim().isEmpty()) {
			throw new CoreException("风险类型不能为空!");
		} else if (riskType.trim().length() > 32) {
			throw new CoreException("申请件编号长度不能超过32个字符!");
		}

		String tmp = warning.getStatus();
		if (!"".equals(tmp) && !"0".equals(tmp) && !"1".equals(tmp)) {
			throw new CoreException("启用状态格式不正确!");
		} else if ("".equals(tmp)) {
			warning.setStatus("1");
		}
	}

	/**
	 * 公共部分的验证操作
	 */
	public Warning getWarning(Context ctx, String... strings) throws CoreException {
		List<String> list = Arrays.asList(strings);
		Map<String, Object> map = ctx.getDataMap();
		Date createTime = null;
		if (list.contains("crtDate")) {
			String createDateString = (String) map.get("crtDate");
			if (null != createDateString && !createDateString.trim().isEmpty()) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				try {
					createTime = sdf.parse(createDateString);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			map.remove("crtDate");
		}
		Gson gson = new Gson();
		Warning warning = gson.fromJson(gson.toJson(map), Warning.class);

		String operator = (String) ctx.getData("userId");
		if (null == operator || operator.trim().isEmpty()) {
			CoreException e = new CoreException("操作用户operator不能为空，请先登录");
			throw e;
		}
		warning.setOperator(operator);

		for (String key : strings) {
			switch (key) {
			case "crtDate":
				warning.setCrtDate(createTime);
				break;

			default:
				break;
			}
		}
		return warning;
	}
}
