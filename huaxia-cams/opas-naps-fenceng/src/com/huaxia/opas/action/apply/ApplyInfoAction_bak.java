package com.huaxia.opas.action.apply;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.huateng.neofp.core.Action;
import com.huateng.neofp.core.Context;
import com.huaxia.opas.service.apply.ApplyInfoService;

/**
 * 申请件一般(历史)、高级(历史)查询
 * 
 * @author gezhihui
 *
 */
public class ApplyInfoAction_bak implements Action {

	private static Logger logger = LoggerFactory.getLogger(ApplyInfoAction_bak.class);

	@Resource(name = "applyInfoService")
	private ApplyInfoService applyInfoService;

	public ApplyInfoService getApplyInfoService() {
		return applyInfoService;
	}

	public void setApplyInfoService(ApplyInfoService applyInfoService) {
		this.applyInfoService = applyInfoService;
	}

	/**
	 * 申请件信息查询
	 * 
	 * @param context
	 * @throws Exception
	 */
	public void queryApplyCurrentInfo(Context ctx) throws Exception {

		Map dataMap = ctx.getDataMap();
		// 参数
		Map<String, String> params = new HashMap<String, String>();
		params.put("appId", (String) dataMap.get("appId"));
		params.put("c1Cname", (String) dataMap.get("c1Cname"));
		params.put("c1Idnbr", (String) dataMap.get("c1Idnbr"));
		params.put("c1Mobile", (String) dataMap.get("c1Mobile"));
		params.put("c2Cname", (String) dataMap.get("c2Cname"));
		params.put("c2Idnbr", (String) dataMap.get("c2Idnbr"));
		params.put("patchCode", (String) dataMap.get("patchCode")); // 补件码
		int curNum = 0;
		int curPage = Integer
				.parseInt(String.valueOf(ctx.getDataMap().get("page") == null ? 0 : ctx.getDataMap().get("page")));
		int pageNum = Integer
				.parseInt(String.valueOf(ctx.getDataMap().get("rows") == null ? 0 : ctx.getDataMap().get("rows")));

		if (curPage > 1) {
			curNum = (curPage - 1) * pageNum;
		}

		int currentVVIPCount = 0;
		int currentArchiveCount = 0;
		// 设置查询条件
		if ((params.get("appId") != null && !"".equals(params.get("appId")) && params.get("appId").length() == 16)
				|| (params.get("c1Mobile") != null && !"".equals(params.get("c1Mobile")))
				|| (params.get("c1Idnbr") != null && !"".equals(params.get("c1Idnbr")))) {
			// 创建 applyMap集合 存储证件码
			Map<String, String> applyMap = new HashMap<String, String>();
			// 没输入证件码
			if (!(params.get("c1Idnbr") != null &&!"".equals( params.get("c1Idnbr")))) {
				// 从主卡进件表中查询客户信息
				List<Map<String, String>> applylist = getApplyInfoService().selectCurrentBIZC1Info(params);
				// 将查询出来的证件码放到applyMap中
				applyMap.put("c1Idnbr", applylist.get(0).get("c1Idnbr"));
			} else {// 前台页面输入证件码
				// 直接把页面输入证件码存到applyMap中
				applyMap.put("c1Idnbr", params.get("c1Idnbr"));
			}
			// 根据 c1Idnbr(证件码) 查询 VVIP 客户数量
			currentVVIPCount = getApplyInfoService().selectCurrentVVIP(applyMap);
			// 根据 c1Idnbr(证件码) 查询申请件是否归档(数量)
			currentArchiveCount = getApplyInfoService().selectCurrentArchive(applyMap);
		}
		// 判断VVIP客户是否存在,以及申请件是否归档. 如条件为true，该客户信息不显示
		if (currentVVIPCount > 0 && currentArchiveCount > 0) {
			// 设置提示信息
			ctx.setData("msg", "您没有权限查看该用户的信息!");
			// 设置分页信息
			Map<String, Object> dataMap1 = new HashMap<String, Object>();
			dataMap1.put("total", 0);
			dataMap1.put("rows", "");
			ctx.setDataMap(dataMap1);
		} else {
			// 将提示消息设置为空(前台接受时不显示)
			ctx.setData("msg", "");

			List<Map<String, String>> list = new ArrayList<Map<String, String>>();

			// ===============没输入补件码 ==================

			if ("".equals(params.get("patchCode"))) {
				// 以下为正常查询
				int count = getApplyInfoService().selectCurrentApplyCount1(params);

				if (count > 0) {
					list = getApplyInfoService().selectCurrentApplyList1(params, curNum, pageNum);
				}

				// 创建 dataMap1 存储分页数据
				Map<String, Object> dataMap1 = new HashMap<String, Object>();
				// 创建 dataMap2
				List<Map<String, String>> dataMap2 = new ArrayList<Map<String, String>>();
				// 申请件的状态查询
				List<Map<String, Object>> stateList = getApplyInfoService().selectCurrentState();
				// 遍历 list 集合 获取 集合中 appId
				for (Map<String, String> map : list) {
					// 遍历 stateList 集合 获取集合中 APP_ID
					for (Map<String, Object> map2 : stateList) {
						// 比较 appId 是否相同
						if (map.get("appId").equals(map2.get("APP_ID"))) {
							// 给向前台传的数据中，增加状态字段列(该字段在 list 集合中没有)
							if (map2.containsKey("OPERATE_RESULT")) {
								// 从stateList集合中获取OPERATE_RESULT字段， 添加 到 map中
								map.put("operateResult", map2.get("OPERATE_RESULT").toString());
							}
						}
					}
					dataMap2.add(map);
				}

				dataMap1.put("total", count);
				dataMap1.put("rows", dataMap2);

				ctx.setDataMap(dataMap1);

			}

			// ===============输入补件码 ==================

			if (params.get("patchCode") != null && !"".equals(params.get("patchCode"))) {
				int count = getApplyInfoService().selectCurrentApplyCount2(params);

				if (count > 0) {
					list = getApplyInfoService().selectCurrentApplyList2(params, curNum, pageNum);
				}

				// 创建 dataMap1 存储分页数据
				Map<String, Object> dataMap1 = new HashMap<String, Object>();
				// 创建 dataMap2
				List<Map<String, String>> dataMap2 = new ArrayList<Map<String, String>>();
				// 申请件的状态查询
				List<Map<String, Object>> stateList = getApplyInfoService().selectCurrentState();
				// 遍历 list 集合 获取 集合中 appId
				if(null!=list&&list.size()>0){
					for (Map<String, String> map : list) {
						// 遍历 stateList 集合 获取集合中 APP_ID
						for (Map<String, Object> map2 : stateList) {
							// 比较 appId 是否相同
							if (map.get("appId").equals(map2.get("APP_ID"))) {
								// 给向前台传的数据中，增加状态字段列(该字段在 list 集合中没有)
								if (map2.containsKey("OPERATE_RESULT")) {
									// 从stateList集合中获取OPERATE_RESULT字段， 添加 到 map中
									map.put("operateResult", map2.get("OPERATE_RESULT").toString());
								}
							}
						}
						dataMap2.add(map);
					}
				}
				dataMap1.put("total", count);
				dataMap1.put("rows", dataMap2);

				ctx.setDataMap(dataMap1);

			}

			// ===============所有条件全部输入 ==================
			String appId = params.get("appId");
			String c1Cname = params.get("c1Cname");
			String c1Idnbr = params.get("c1Idnbr");
			String c1Mobile = params.get("c1Mobile");
			String c2Cname = params.get("c2Cname");
			String c2Idnbr = params.get("c2Idnbr");
			String patchCode = params.get("patchCode");

			if ((appId != null && !"".equals(appId)) && (c1Cname != null && !"".equals(c1Cname))
					&& (c1Idnbr != null && !"".equals(c1Idnbr)) && (c1Mobile != null && !"".equals(c1Mobile))
					&& (c2Cname != null && !"".equals(c2Cname)) && (c2Idnbr != null && !"".equals(c2Idnbr))
					&& (patchCode != null && !"".equals(patchCode))) {

				int count = getApplyInfoService().selectCurrentApplyCount3(params);

				if (count > 0) {
					list = getApplyInfoService().selectCurrentApplyList3(params, curNum, pageNum);
				}

				// 创建 dataMap1 存储分页数据
				Map<String, Object> dataMap1 = new HashMap<String, Object>();
				// 创建 dataMap2
				List<Map<String, String>> dataMap2 = new ArrayList<Map<String, String>>();
				// 申请件的状态查询
				List<Map<String, Object>> stateList = getApplyInfoService().selectCurrentState();
				// 遍历 list 集合 获取 集合中 appId
				for (Map<String, String> map : list) {
					// 遍历 stateList 集合 获取集合中 APP_ID
					for (Map<String, Object> map2 : stateList) {
						// 比较 appId 是否相同
						if (map.get("appId").equals(map2.get("APP_ID"))) {
							// 给向前台传的数据中，增加状态字段列(该字段在 list 集合中没有)
							if (map2.containsKey("OPERATE_RESULT")) {
								// 从stateList集合中获取OPERATE_RESULT字段， 添加 到 map中
								map.put("operateResult", map2.get("OPERATE_RESULT").toString());
							}
						}
					}
					dataMap2.add(map);
				}

				dataMap1.put("total", count);
				dataMap1.put("rows", dataMap2);

				ctx.setDataMap(dataMap1);

			}

		}

	}

	/**
	 * 申请件历史信息查询
	 * 
	 * @param ctx
	 * @throws Exception
	 */
	public void queryApplyHistoryInfo(Context ctx) throws Exception {

		Map dataMap = ctx.getDataMap();
		// 参数
		Map<String, String> params = new HashMap<String, String>();
		params.put("appId", (String) dataMap.get("appId"));
		params.put("c1Cname", (String) dataMap.get("c1Cname"));
		params.put("c1Idnbr", (String) dataMap.get("c1Idnbr"));
		params.put("c1Mobile", (String) dataMap.get("c1Mobile"));
		params.put("c2Cname", (String) dataMap.get("c2Cname"));
		params.put("c2Idnbr", (String) dataMap.get("c2Idnbr"));
		params.put("patchCode", (String) dataMap.get("patchCode")); // 补件码

		int curNum = 0;
		int curPage = Integer
				.parseInt(String.valueOf(ctx.getDataMap().get("page") == null ? 0 : ctx.getDataMap().get("page")));
		int pageNum = Integer
				.parseInt(String.valueOf(ctx.getDataMap().get("rows") == null ? 0 : ctx.getDataMap().get("rows")));

		if (curPage > 1) {
			curNum = (curPage - 1) * pageNum;
		}

		int archiveCount = 0;
		// 判断c1Idnbr是否存在
		if (params.get("c1Idnbr") != null && !"".equals(params.get("c1Idnbr")) ) {
			// 查询VVIP客户(根据证件号查询)
			archiveCount = getApplyInfoService().selectArchiveCount(params);
		}
		// 判断VVIP客户是否存在 如>0,说明VVIP客户存在，该客户信息不显示
		if (archiveCount > 0) {
			ctx.setData("msg", "您没有权限查看该用户的信息。");
			Map<String, Object> dataMap1 = new HashMap<String, Object>();
			// 设置分页信息
			dataMap1.put("total", 0);
			dataMap1.put("rows", "");
			ctx.setDataMap(dataMap1);
		} else {
			// 将提示消息设置为空(前台接受时不显示)
			ctx.setData("msg", "");

			List<Map<String, String>> list = new ArrayList<Map<String, String>>();

			// ===============没输入补件码 ==================

			if ("".equals(params.get("patchCode"))) {
				// 以下为正常查询
				int count = getApplyInfoService().selectHistoryCount1(params);

				if (count > 0) {
					list = getApplyInfoService().selectHistoryList1(params, curNum, pageNum);
				}

				// 创建 dataMap1 存储分页数据
				Map<String, Object> dataMap1 = new HashMap<String, Object>();
				// 创建 dataMap2
				List<Map<String, String>> dataMap2 = new ArrayList<Map<String, String>>();
				// 申请件的状态查询
				List<Map<String, Object>> stateList = getApplyInfoService().selectHistoryState();
				// 遍历 list 集合 获取 集合中 appId
				for (Map<String, String> map : list) {
					// 遍历 stateList 集合 获取集合中 APP_ID
					for (Map<String, Object> map2 : stateList) {
						// 比较 appId 是否相同
						if (map.get("appId").equals(map2.get("APP_ID"))) {
							// 给向前台传的数据中，增加状态字段列(该字段在 list 集合中没有)
							if (map2.containsKey("OPERATE_RESULT")) {
								// 从stateList集合中获取OPERATE_RESULT字段， 添加 到 map中
								map.put("operateResult", map2.get("OPERATE_RESULT").toString());
							}
						}
					}
					dataMap2.add(map);
				}

				dataMap1.put("total", count);
				dataMap1.put("rows", dataMap2);

				ctx.setDataMap(dataMap1);

			}

			// ===============输入补件码 ==================

			if (params.get("patchCode") != null && !"".equals(params.get("patchCode"))) {
				int count = getApplyInfoService().selectHistoryCount2(params);

				if (count > 0) {
					list = getApplyInfoService().selectHistoryList2(params, curNum, pageNum);
				}

				// 创建 dataMap1 存储分页数据
				Map<String, Object> dataMap1 = new HashMap<String, Object>();
				// 创建 dataMap2
				List<Map<String, String>> dataMap2 = new ArrayList<Map<String, String>>();
				// 申请件的状态查询
				List<Map<String, Object>> stateList = getApplyInfoService().selectHistoryState();
				// 遍历 list 集合 获取 集合中 appId
				for (Map<String, String> map : list) {
					// 遍历 stateList 集合 获取集合中 APP_ID
					for (Map<String, Object> map2 : stateList) {
						// 比较 appId 是否相同
						if (map.get("appId").equals(map2.get("APP_ID"))) {
							// 给向前台传的数据中，增加状态字段列(该字段在 list 集合中没有)
							if (map2.containsKey("OPERATE_RESULT")) {
								// 从stateList集合中获取OPERATE_RESULT字段， 添加 到 map中
								map.put("operateResult", map2.get("OPERATE_RESULT").toString());
							}
						}
					}
					dataMap2.add(map);
				}

				dataMap1.put("total", count);
				dataMap1.put("rows", dataMap2);

				ctx.setDataMap(dataMap1);

			}

			// ===============所有条件全部输入 ==================
			String appId = params.get("appId");
			String c1Cname = params.get("c1Cname");
			String c1Idnbr = params.get("c1Idnbr");
			String c1Mobile = params.get("c1Mobile");
			String c2Cname = params.get("c2Cname");
			String c2Idnbr = params.get("c2Idnbr");
			String patchCode = params.get("patchCode");

			if ((appId != null && !"".equals(appId)) && (c1Cname != null && !"".equals(c1Cname))
					&& (c1Idnbr != null && !"".equals(c1Idnbr)) && (c1Mobile != null && !"".equals(c1Mobile))
					&& (c2Cname != null && !"".equals(c2Cname)) && (c2Idnbr != null && !"".equals(c2Idnbr))
					&& (patchCode != null && !"".equals(patchCode))) {

				int count = getApplyInfoService().selectHistoryCount3(params);

				if (count > 0) {
					list = getApplyInfoService().selectHistoryList3(params, curNum, pageNum);
				}

				// 创建 dataMap1 存储分页数据
				Map<String, Object> dataMap1 = new HashMap<String, Object>();
				// 创建 dataMap2
				List<Map<String, String>> dataMap2 = new ArrayList<Map<String, String>>();
				// 申请件的状态查询
				List<Map<String, Object>> stateList = getApplyInfoService().selectHistoryState();
				// 遍历 list 集合 获取 集合中 appId
				for (Map<String, String> map : list) {
					// 遍历 stateList 集合 获取集合中 APP_ID
					for (Map<String, Object> map2 : stateList) {
						// 比较 appId 是否相同
						if (map.get("appId").equals(map2.get("APP_ID"))) {
							// 给向前台传的数据中，增加状态字段列(该字段在 list 集合中没有)
							if (map2.containsKey("OPERATE_RESULT")) {
								// 从stateList集合中获取OPERATE_RESULT字段， 添加 到 map中
								map.put("operateResult", map2.get("OPERATE_RESULT").toString());
							}
						}
					}
					dataMap2.add(map);
				}

				dataMap1.put("total", count);
				dataMap1.put("rows", dataMap2);

				ctx.setDataMap(dataMap1);

			}

		}

	}

	/**
	 * 申请件高级查询
	 * 
	 * @param ctx
	 * @throws Exception
	 */
	public void highApplyCurrentInfo(Context ctx) throws Exception {

		Map<String, Object> data = ctx.getDataMap();
		// 参数
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("c1Coname", data.get("c1Coname"));
		params.put("c1Rename", data.get("c1Rename"));
		params.put("c1Cotel", data.get("c1Cotel"));
		params.put("c1Retel", data.get("c1Retel"));
		params.put("c1Hmtel", data.get("c1Hmtel"));
		params.put("c1Remobil", data.get("c1Remobil"));
		params.put("c1Idnbr", data.get("c1Idnbr"));
		params.put("c4Apbatch", data.get("c4Apbatch"));
		params.put("c1Mobile", data.get("c1Mobile"));
		params.put("approveResult", data.get("approveResult")); // 审批结果字段
		params.put("c5Abcode", data.get("c5Abcode"));
		params.put("c1Gender", data.get("c1Gender"));
		params.put("c4Abuser", data.get("c4Abuser"));
		params.put("c1Xname1", data.get("c1Xname1"));
		params.put("c1Xname2", data.get("c1Xname2"));
		params.put("c1Xtel1", data.get("c1Xtel1"));
		params.put("c1Xtel2", data.get("c1Xtel2"));
		params.put("c1Xmobil1", data.get("c1Xmobil1"));
		params.put("c1Xmobil2", data.get("c1Xmobil2"));

		int curNum = 0;
		int curPage = Integer
				.parseInt(String.valueOf(ctx.getDataMap().get("page") == null ? 0 : ctx.getDataMap().get("page")));
		int pageNum = Integer
				.parseInt(String.valueOf(ctx.getDataMap().get("rows") == null ? 0 : ctx.getDataMap().get("rows")));

		if (curPage > 1) {
			curNum = (curPage - 1) * pageNum;
		}

		int count = getApplyInfoService().selectHighCurrentCount(params);

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		if (count > 0) {
			list = getApplyInfoService().selectHighCurrentList(params, curNum, pageNum);
		}

		// 创建 dataMap1 存储分页数据
		Map<String, Object> dataMap1 = new HashMap<String, Object>();
		// 创建 dataMap2
		List<Map<String, Object>> dataMap2 = new ArrayList<Map<String, Object>>();

		for (Map<String, Object> map : list) {
			// 直系亲属姓名
			if ("".equals(map.get("c1Rename")) || map.get("c1Rename") == null) {
				if (!"".equals(map.get("c1Xname1")) && map.get("c1Xname1") != null) {
					map.put("c1Rename", map.get("c1Xname1"));
				} else {
					if (!"".equals(map.get("c1Xname2")) && map.get("c1Xname2") != null) {
						map.put("c1Rename", map.get("c1Xname2"));
					}
				}

			} else {
				map.put("c1Rename", map.get("c1Rename"));
			}

			// 直系亲属电话
			if ("".equals(map.get("c1Retel")) || map.get("c1Retel") == null) {
				if (!"".equals(map.get("c1Xtel1")) && map.get("c1Xtel1") != null) {
					map.put("c1Retel", map.get("c1Xtel1"));
				} else {
					if (!"".equals(map.get("c1Xtel2")) && map.get("c1Xtel2") != null) {
						map.put("c1Retel", map.get("c1Xtel2"));
					}
				}

			} else {
				map.put("c1Retel", map.get("c1Retel"));
			}

			// 直系亲属手机
			if ("".equals(map.get("c1Remobil")) || map.get("c1Remobil") == null) {
				if (!"".equals(map.get("c1Xmobil1")) && map.get("c1Xmobil1") != null) {
					map.put("c1Remobil", map.get("c1Xmobil1"));
				} else {
					if (!"".equals(map.get("c1Xmobil2")) && map.get("c1Xmobil2") != null) {
						map.put("c1Remobil", map.get("c1Xmobil2"));
					}
				}

			} else {
				map.put("c1Remobil", map.get("c1Remobil"));
			}
		}

		for (Map<String, Object> map : list) {
			if ("".equals(map.get("approveResult")) || map.get("approveResult") == null) {
				// 审批结果状态查询
				List<Map<String, Object>> stateList = getApplyInfoService().selectHighAppayState();
				map.put("approveResult", "");// 首先给 map 集合中添加 该字段
				// 遍历 stateList 把 stateList集合中的字段 添加到list集合中
				for (Map<String, Object> map2 : stateList) {
					if (map.get("appId").equals(map2.get("appId"))) {
						if (!"".equals(map2.get("CURR_OPT_USER")) && map2.get("CURR_OPT_USER") != null) {
							map.put("currOptUser", map2.get("CURR_OPT_USER").toString());
						}
						if (!"".equals(map2.get("CURR_NODE")) && map2.get("CURR_NODE") != null) {
							map.put("currNode", map2.get("CURR_NODE").toString());
						}
					}
				}
			}
			dataMap2.add(map);
		}
		// 遍历 dataMap2
		for (Map<String, Object> map3 : dataMap2) {
			// 条件判断
			if (map3.get("approveResult") != null
					&& !(map3.get("approveResult").equals("0") && map3.get("approveResult").equals("1"))) {
				if ((!"".equals(map3.get("currNode"))  && map3.get("currNode") != null)
						|| (!"".equals(map3.get("currOptUser"))  && map3.get("currOptUser") != null)) {
					if (!"".equals(map3.get("currOptUser"))  && map3.get("currOptUser") != null) {
						map3.put("approveResult", map3.get("currOptUser"));
					}
					if (!"".equals(map3.get("currNode"))  && map3.get("currNode") != null) {
						if (map3.get("currNode").equals("01")) {
							map3.put("currNode", "录入比对");
						}
						if (map3.get("currNode").equals("02")) {
							map3.put("currNode", "征信调查");
						}
						if (map3.get("currNode").equals("03")) {
							map3.put("currNode", "人工审批");
						}
						map3.put("approveResult", map3.get("currNode"));
					}
					if ((!"".equals(map3.get("currNode")) && map3.get("currNode") != null)
							&& (!"".equals(map3.get("currOptUser"))  && map3.get("currOptUser") != null)) {
						map3.put("approveResult", map3.get("currOptUser") + " " + map3.get("currNode"));
					}
				}

			}
		}
		dataMap1.put("total", count);
		dataMap1.put("rows", dataMap2);

		ctx.setDataMap(dataMap1);
	}

	/**
	 * 申请件高级历史查询
	 * 
	 * @param ctx
	 * @throws Exception
	 */
	public void highApplyHistoryInfo(Context ctx) throws Exception {

		Map<String, Object> data = ctx.getDataMap();
		// 参数
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("c1Coname", data.get("c1Coname"));
		params.put("c1Rename", data.get("c1Rename"));
		params.put("c1Cotel", data.get("c1Cotel"));
		params.put("c1Retel", data.get("c1Retel"));
		params.put("c1Hmtel", data.get("c1Hmtel"));
		params.put("c1Remobil", data.get("c1Remobil"));
		params.put("c1Idnbr", data.get("c1Idnbr"));
		params.put("c4Apbatch", data.get("c4Apbatch"));
		params.put("c1Mobile", data.get("c1Mobile"));
		params.put("approveResult", data.get("approveResult")); // 审批结果字段
		params.put("c4Abuser", data.get("c4Abuser"));
		params.put("c1Gender", data.get("c1Gender"));
		params.put("c5Abcode", data.get("c5Abcode"));
		params.put("c1Xname1", data.get("c1Xname1"));
		params.put("c1Xname2", data.get("c1Xname2"));
		params.put("c1Xtel1", data.get("c1Xtel1"));
		params.put("c1Xtel2", data.get("c1Xtel2"));
		params.put("c1Xmobil1", data.get("c1Xmobil1"));
		params.put("c1Xmobil2", data.get("c1Xmobil2"));
		int curNum = 0;
		int curPage = Integer
				.parseInt(String.valueOf(ctx.getDataMap().get("page") == null ? 0 : ctx.getDataMap().get("page")));
		int pageNum = Integer
				.parseInt(String.valueOf(ctx.getDataMap().get("rows") == null ? 0 : ctx.getDataMap().get("rows")));

		if (curPage > 1) {
			curNum = (curPage - 1) * pageNum;
		}

		int count = getApplyInfoService().selectHighHistoryCount(params);

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		if (count > 0) {
			list = getApplyInfoService().selectHighHistoryList(params, curNum, pageNum);
		}

		for (Map<String, Object> map : list) {
			// 直系亲属姓名
			if ("".equals(map.get("c1Rename")) || map.get("c1Rename") == null) {
				if (!"".equals(map.get("c1Xname1")) && map.get("c1Xname1") != null) {
					map.put("c1Rename", map.get("c1Xname1"));
				} else {
					if (!"".equals(map.get("c1Xname2")) && map.get("c1Xname2") != null) {
						map.put("c1Rename", map.get("c1Xname2"));
					}
				}

			} else {
				map.put("c1Rename", map.get("c1Rename"));
			}

			// 直系亲属电话
			if ("".equals(map.get("c1Retel")) || map.get("c1Retel") == null) {
				if (!"".equals(map.get("c1Xtel1")) && map.get("c1Xtel1") != null) {
					map.put("c1Retel", map.get("c1Xtel1"));
				} else {
					if (!"".equals(map.get("c1Xtel2")) && map.get("c1Xtel2") != null) {
						map.put("c1Retel", map.get("c1Xtel2"));
					}
				}

			} else {
				map.put("c1Retel", map.get("c1Retel"));
			}

			// 直系亲属手机
			if ("".equals(map.get("c1Remobil")) || map.get("c1Remobil") == null) {
				if (!"".equals(map.get("c1Xmobil1")) && map.get("c1Xmobil1") != null) {
					map.put("c1Remobil", map.get("c1Xmobil1"));
				} else {
					if (!"".equals(map.get("c1Xmobil2")) && map.get("c1Xmobil2") != null) {
						map.put("c1Remobil", map.get("c1Xmobil2"));
					}
				}

			} else {
				map.put("c1Remobil", map.get("c1Remobil"));
			}
		}

		// 创建 dataMap1 存储分页数据
		Map<String, Object> dataMap1 = new HashMap<String, Object>();
		// 创建 dataMap2
		List<Map<String, Object>> dataMap2 = new ArrayList<Map<String, Object>>();

		for (Map<String, Object> map : list) {
			if ("".equals(map.get("approveResult"))  || map.get("approveResult") == null) {
				// 审批结果状态查询
				List<Map<String, Object>> stateHistoryList = getApplyInfoService().selectHighHistoryAppayState();
				map.put("approveResult", "");
				// 遍历 stateHistoryList 给 map 添加需要字段
				for (Map<String, Object> map2 : stateHistoryList) {
					if (map.get("appId").equals(map2.get("appId"))) {
						if (!"".equals(map2.get("CURR_OPT_USER"))  && map2.get("CURR_OPT_USER") != null) {
							map.put("currOptUser", map2.get("CURR_OPT_USER").toString());
						}
						if (!"".equals(map2.get("CURR_NODE")) && map2.get("CURR_NODE") != null) {
							map.put("currNode", map2.get("CURR_NODE").toString());
						}
					}
				}
			}
			dataMap2.add(map);
		}
		// 遍历 dataMap2
		for (Map<String, Object> map3 : dataMap2) {
			// 条件判断
			if (map3.get("approveResult") != null
					&& !(map3.get("approveResult").equals("0") && map3.get("approveResult").equals("1"))) {
				if ((!"".equals(map3.get("currNode"))  && map3.get("currNode") != null)
						|| (!"".equals(map3.get("currOptUser"))  && map3.get("currOptUser") != null)) {
					if (!"".equals(map3.get("currOptUser") ) && map3.get("currOptUser") != null) {
						map3.put("approveResult", map3.get("currOptUser"));
					}
					if (!"".equals(map3.get("currNode"))&& map3.get("currNode") != null) {
						if (map3.get("currNode").equals("01")) {
							map3.put("currNode", "录入比对");
						}
						if (map3.get("currNode").equals("02")) {
							map3.put("currNode", "征信调查");
						}
						if (map3.get("currNode").equals("03")) {
							map3.put("currNode", "人工审批");
						}
						map3.put("approveResult", map3.get("currNode"));
					}
					if ((!"".equals(map3.get("currNode"))  && map3.get("currNode") != null)
							&& (!"".equals(map3.get("currOptUser"))  && map3.get("currOptUser") != null)) {
						map3.put("approveResult", map3.get("currOptUser") + " " + map3.get("currNode"));
					}
				}

			}
		}
		dataMap1.put("total", count);
		dataMap1.put("rows", dataMap2);

		ctx.setDataMap(dataMap1);
	}

	/**
	 * 申请件流程节点显示
	 * 
	 * @param ctx
	 * @throws Exception
	 */
	public void queryApplyNode(Context ctx) throws Exception {

		Map dataMap = ctx.getDataMap();
		// 参数
		Map<String, String> params = new HashMap<String, String>();
		params.put("appId", (String) dataMap.get("appId"));
		params.put("crtDate", (String) dataMap.get("crtDate"));
		params.put("operater", (String) dataMap.get("operater"));
		params.put("operateDesc", (String) dataMap.get("operateDesc"));
		params.put("operateResult", (String) dataMap.get("operateResult"));
		int curNum = 0;
		int curPage = Integer
				.parseInt(String.valueOf(ctx.getDataMap().get("page") == null ? 0 : ctx.getDataMap().get("page")));
		int pageNum = Integer
				.parseInt(String.valueOf(ctx.getDataMap().get("rows") == null ? 0 : ctx.getDataMap().get("rows")));

		if (curPage > 1) {
			curNum = (curPage - 1) * pageNum;
		}

		int count = getApplyInfoService().selectNodeCount(params);

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		if (count > 0) {
			list = getApplyInfoService().selectNodeList(params, curNum, pageNum);
		}
		// 创建 dataMap1 存储分页数据
		Map<String, Object> dataMap1 = new HashMap<String, Object>();

		dataMap1.put("total", count);
		dataMap1.put("rows", list);
		ctx.setDataMap(dataMap1);

	}

	/**
	 * 申请件全局信息备注信息查询
	 * 
	 * @param ctx
	 * @throws Exception
	 */
	public void queryApplyRemark(Context ctx) throws Exception {

		Map dataMap = ctx.getDataMap();
		// 参数
		Map<String, String> params = new HashMap<String, String>();
		params.put("appId", (String) dataMap.get("appId"));
		params.put("remarkInfo", (String) dataMap.get("remarkInfo"));
		params.put("remarkUser", (String) dataMap.get("remarkUser"));
		params.put("remarkTime", (String) dataMap.get("remarkTime"));
		params.put("currNode", (String) dataMap.get("currNode"));
		int curNum = 0;
		int curPage = Integer
				.parseInt(String.valueOf(ctx.getDataMap().get("page") == null ? 0 : ctx.getDataMap().get("page")));
		int pageNum = Integer
				.parseInt(String.valueOf(ctx.getDataMap().get("rows") == null ? 0 : ctx.getDataMap().get("rows")));

		if (curPage > 1) {
			curNum = (curPage - 1) * pageNum;
		}
		int count = getApplyInfoService().selectRemarkCount(params);

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		if (count > 0) {
			list = getApplyInfoService().selectRemarkList(params, curNum, pageNum);
		}
		if (!list.isEmpty()) {
			for (Map<String, Object> map : list) {
				if (!"".equals(map.get("currNode")) && map.get("currNode") != null) {
					if (map.get("currNode").equals("01")) {
						map.put("currNode", "录入比对");
					}
					if (map.get("currNode").equals("02")) {
						map.put("currNode", "征信调查");
					}
					if (map.get("currNode").equals("03")) {
						map.put("currNode", "人工审批");
					}
					if (map.get("currNode").equals("04")) {
						map.put("currNode", "归档");
					}
				} else {
					map.put("currNode", map.get("currNode"));
				}
			}
		}

		// 创建 dataMap1 存储分页数据
		Map<String, Object> dataMap1 = new HashMap<String, Object>();

		dataMap1.put("total", count);
		dataMap1.put("rows", list);
		ctx.setDataMap(dataMap1);
	}

}
