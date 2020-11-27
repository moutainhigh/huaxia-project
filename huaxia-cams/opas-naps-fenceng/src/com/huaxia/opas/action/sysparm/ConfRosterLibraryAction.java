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
import com.huaxia.opas.domain.sysparm.ConfQuestion;
import com.huaxia.opas.domain.sysparm.ConfRosterLibrary;
import com.huaxia.opas.service.sysparm.ConfRosterLibraryService;
import com.huaxia.opas.util.SequenceUtil;

/**
 * 行员名单库
 * 
 * @author lipengfei
 *
 */
public class ConfRosterLibraryAction implements Action {

	@Resource(name = "confRosterLibraryService")
	private ConfRosterLibraryService confRosterLibraryService;

	// 查询
	public void queryRosterLibrary(Context ctx) throws CoreException, ParseException {
		ConfRosterLibrary confRosterLibrary = getConfRosterLibrary(ctx);
		String date = confRosterLibrary.getCrtDate1();
		if (!"".equals(date) && date != null) {

			confRosterLibrary.setCrtDate(new SimpleDateFormat("yyyy-MM-dd").parse(date));
		}
		int curNum = 0;
		int curPage = Integer
				.parseInt(String.valueOf(ctx.getDataMap().get("page") == null ? 0 : ctx.getDataMap().get("page")));
		int pageNum = Integer
				.parseInt(String.valueOf(ctx.getDataMap().get("rows") == null ? 0 : ctx.getDataMap().get("rows")));

		if (curPage > 1) {
			curNum = (curPage - 1) * pageNum;
		}

		List<ConfRosterLibrary> list = new ArrayList<ConfRosterLibrary>();
		int count = confRosterLibraryService.queryConfRosterLibraryCount(confRosterLibrary);
		if (count > 0) {
			list = confRosterLibraryService.queryConfRosterLibraryList(confRosterLibrary, curNum, pageNum);
		}

		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("total", count);
		dataMap.put("rows", list);
		ctx.setDataMap(dataMap);
	}

	/**
	 * 添加
	 */
	public void saveConfRosterLibrary(Context ctx) throws CoreException {
		try {
			ConfRosterLibrary confRosterLibrary = getConfRosterLibrary(ctx);
			confRosterLibrary.setRosterID(SequenceUtil.getUUID());
			checkData(confRosterLibrary);
			confRosterLibraryService.insertConfRosterLibrary(confRosterLibrary);
		} catch (CoreException e) {
			ctx.setData("exMsg", e.getMessage());
			throw e;
		}
		ctx.setData("isSuccess", true);
	}

	/**
	 * 修改
	 */
	public void updateConfRosterLibrary(Context ctx) throws CoreException {
		try {
			ConfRosterLibrary confRosterLibrary = getConfRosterLibrary(ctx);
			checkData(confRosterLibrary);
			confRosterLibraryService.updateConfRosterLibrary(confRosterLibrary);
		} catch (CoreException e) {
			ctx.setData("exMsg", e.getMessage());
			throw e;
		}
		ctx.setData("isSuccess", true);
	}

	/**
	 * 删除
	 */
	public void deleteConfRosterLibrary(Context ctx) throws CoreException {
		try {
			ConfRosterLibrary confRosterLibrary = getConfRosterLibrary(ctx, "ids");
			confRosterLibraryService.deleteConfRosterLibrary(confRosterLibrary);
			ctx.setData("isSuccess", true);
		} catch (CoreException e) {
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
			confRosterLibraryService.deleteAll();
			ctx.setData("isSuccess", true);
		} catch (CoreException e) {
			ctx.setData("isSuccess", false);
			ctx.setData("exMsg", e.getMessage());
			throw e;
		}
	}
	/**
	 * 校验的方法
	 */
	public void checkData(ConfRosterLibrary confRosterLibrary) throws CoreException {
		String rosterName = confRosterLibrary.getRosterName();
		if (rosterName.trim().length() > 32) {
			throw new CoreException("请把行员姓名控制在32个字符以内!");
		}
		String rosterLdnumber = confRosterLibrary.getRosterLdnumber();
		if (rosterLdnumber.trim().length() > 18) {
			throw new CoreException("身份证号长度不得大于18位!");
		}
		// 以身份证为主键,唯一校验
		try {
			ConfRosterLibrary only = confRosterLibraryService.queryOnly(confRosterLibrary);
			if (!"".equals(only.getRosterID()) && only.getRosterID() != null) {
				throw new CoreException("行员身份证信息重复!");
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 公共部分的验证操作
	 */
	public ConfRosterLibrary getConfRosterLibrary(Context ctx, String... strings) throws CoreException {
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
		ConfRosterLibrary confRosterLibrary = gson.fromJson(gson.toJson(map), ConfRosterLibrary.class);

		String operator = (String) ctx.getData("userId");
		if (null == operator || operator.trim().isEmpty()) {
			CoreException e = new CoreException("操作用户operator不能为空，请先登录");
			throw e;
		}
		confRosterLibrary.setOperator(operator);

		for (String key : strings) {
			switch (key) {
			case "ids":
				@SuppressWarnings("unchecked")
				List<String> ids = (List<String>) map.get("ids");
				if (null == ids || ids.size() <= 0) {
					CoreException e = new CoreException("请选择需要操作的行");
					throw e;
				}
				confRosterLibrary.setIds(ids);
				break;

			case "Status":
				String Status = (String) map.get("Status");
				if (null == Status || (!Status.trim().equals("1") && !Status.trim().equals("0"))) {
					CoreException e = new CoreException("启用状态值Status非法");
					throw e;
				}
				break;

			case "crtDate":
				confRosterLibrary.setCrtDate(createTime);
				break;

			default:
				break;
			}
		}
		return confRosterLibrary;
	}

}
