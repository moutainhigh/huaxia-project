package com.huaxia.opas.parser;

import java.util.ArrayList;
import java.util.List;

import com.huaxia.opas.common.AppConst;
import com.huaxia.opas.domain.ZMXYIvsScore;
import com.huaxia.opas.domain.ZMXYIvsScore.IvsDetail;
import com.huaxia.opas.domain.ZMXYScore;
import com.huaxia.opas.domain.ZMXYWatchList2;
import com.huaxia.opas.domain.ZMXYWatchList2.WatchList2Detail;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 芝麻信用报文解析器
 * 
 * @author zhiguo.li
 *
 */
public class ZMXYMessageParser {

	/**
	 * 解析反欺诈信息验证报文
	 * 
	 * @param message
	 *            报文
	 * @return
	 * @throws Exception
	 */
	public ZMXYIvsScore parseMessageOfIvsScore(String message) {
		if (message == null) {
			throw new IllegalArgumentException("反欺诈信息验证报文为空");
		}

		ZMXYIvsScore ivsScore = new ZMXYIvsScore();

		JSONObject json = JSONObject.fromObject(message);
		boolean success = json.getBoolean("success");
		if (success) {
			ivsScore.setResponseResult(true);
			ivsScore.setCrtUser(AppConst.DEFAULT_USER);

			String bizNo = json.getString("biz_no");
			ivsScore.setBizNo(bizNo);
			ivsScore.setIvsScore(json.getString("ivs_score"));

			JSONArray detailArray = JSONArray.fromObject(json.get("ivs_detail"));
			if (!detailArray.isEmpty()) {
				List<IvsDetail> ivsDetailList = new ArrayList<IvsDetail>();
				for (int i = 0; i < detailArray.size(); i++) {
					IvsDetail ivsDetail = ivsScore.new IvsDetail();

					JSONObject object = (JSONObject) detailArray.get(i);
					ivsDetail.setBizNo(bizNo);
					ivsDetail.setCode(object.getString("code"));
					ivsDetail.setDescription(object.getString("description"));
					ivsDetailList.add(ivsDetail);
				}
				ivsScore.setIvsDetailList(ivsDetailList);
			}
		} else {
			ivsScore.setResponseResult(false);
		}

		return ivsScore;
	}

	/**
	 * 解析芝麻信用评分
	 * 
	 * @param message
	 *            报文
	 * @return
	 * @throws Exception
	 */
	public ZMXYScore parseMessageOfScore(String message) {
		if (message == null) {
			throw new IllegalArgumentException("芝麻信用评分报文为空");
		}

		ZMXYScore score = new ZMXYScore();

		JSONObject json = JSONObject.fromObject(message);
		boolean success = json.getBoolean("success");
		if (success) {
			score.setSuccess("T");

			if (json.containsKey("biz_no") && json.getString("biz_no") != null) {
				score.setBizNo(json.getString("biz_no"));
			}

			if (json.containsKey("zm_score") && json.getString("zm_score") != null) {
				score.setZmScore(json.getString("zm_score"));
			}
		} else {
			score.setResponseResult(true);

			// [说明] error_code 和 error_message 标签针对"未签署开通协议,无法开通芝麻账户"情况
			if (json.containsKey("error_code") && json.getString("error_code") != null) {
				score.setErrorCode(json.getString("error_code"));
			}

			if (json.containsKey("error_message") && json.getString("error_message") != null) {
				score.setErrorDesc(json.getString("error_message"));
			}
		}

		return score;
	}

	/**
	 * 解析行业关注名单2.0版
	 * 
	 * @param message
	 *            报文
	 * @param appId
	 *            申请件编号
	 * @return
	 * @throws Exception
	 */
	public List<ZMXYWatchList2> parseMessageOfWatchList2(String message, String appId) {
		if (message == null) {
			throw new IllegalArgumentException("芝麻信用行业关注名单2.0版报文为空");
		}

		List<ZMXYWatchList2> watchList2List = new ArrayList<ZMXYWatchList2>();

		JSONObject json = JSONObject.fromObject(message);
		if (json.getBoolean("success")) {
			// 响应成功: 正常情况 & 命中
			if (json.containsKey("details") && !json.getJSONArray("details").isEmpty()) {
				JSONArray details = json.getJSONArray("details");
				for (int i = 0; i < details.size(); i++) {
					ZMXYWatchList2 watchList2 = new ZMXYWatchList2();
					watchList2.setAppId(appId);
					watchList2.setResponseResult(true);

					// [外] 是否命中
					if (json.containsKey("is_matched")) {
						if (json.getBoolean("is_matched")) {
							watchList2.setIsMatched("1");
						} else {
							watchList2.setIsMatched("0");
						}
					}

					// [外]请求业务号
					if (json.containsKey("biz_no")) {
						String bizNo = json.getString("biz_no");
						if (bizNo != null && !"".equals(bizNo)) {
							watchList2.setBizNo(bizNo);
						}
					}

					// [内] 行业关注名单 & 明细
					JSONObject detail = (JSONObject) details.get(i);
					WatchList2Detail watchList2Detail = watchList2.new WatchList2Detail();

					// [内] 行业关注名单 & 明细 & 数据刷新时间
					if (detail.containsKey("refresh_time")) {
						String refreshTime = detail.getString("refresh_time");
						if (refreshTime != null && !"".equals(refreshTime)) {
							watchList2Detail.setRefreshTime(refreshTime);
						}
					}

					// [内] 行业关注名单 & 明细 & 风险信息行业编码（对应"行业类型":type）
					if (detail.containsKey("biz_code")) {
						String bizCode = detail.getString("biz_code");
						if (bizCode != null && !"".equals(bizCode)) {
							watchList2Detail.setBizCode(bizCode);
						}
					}

					// [内] 行业关注名单 & 明细 & LEVEL
					if (detail.containsKey("level")) {
						String level = detail.getString("level");
						if (level != null && !"".equals(level)) {
							watchList2Detail.setLevel(level);
						}
					}

					// [内] 行业关注名单 & 明细 & SETTLEMENT
					if (detail.containsKey("settlement")) {
						if (detail.getBoolean("settlement")) {
							watchList2Detail.setSettlement("1");
						} else {
							watchList2Detail.setStatement("0");
						}
					}

					// [内] 行业关注名单 & 明细 & 风险编码（对应"风险代码":riskCode）
					if (detail.containsKey("code")) {
						String code = detail.getString("code");
						if (code != null && !"".equals(code)) {
							watchList2Detail.setCode(code);
						}
					}

					// [内] 行业关注名单 & 明细 & 行业名单风险类型（对应"风险类型":riskType）
					if (detail.containsKey("type")) {
						String type = detail.getString("type");
						if (type != null && !"".equals(type)) {
							watchList2Detail.setType(type);
						}
					}

					// [内] 行业关注名单 & 明细 & EXTEND_INFO（数组形式）
					String extendInfo = detail.getString("extend_info");
					if (extendInfo != null && !"".equals(extendInfo)) {
						watchList2Detail.setExtendInfo(extendInfo);
					}

					watchList2.setWatchList2Detail(watchList2Detail);
					watchList2List.add(watchList2);
				}
			}
			// 响应成功: 正常情况 & 未命中（无"芝麻信用行业关注名单2.0版"内容）
			else {
				ZMXYWatchList2 watchList2 = new ZMXYWatchList2();
				watchList2.setAppId(appId);
				watchList2.setResponseResult(true);

				if (json.containsKey("is_matched")) {
					if (json.getBoolean("is_matched")) {
						watchList2.setIsMatched("1");
					} else {
						watchList2.setIsMatched("0");
					}
				}

				if (json.containsKey("biz_no")) {
					String bizNo = json.getString("biz_no");
					if (bizNo != null && !"".equals(bizNo)) {
						watchList2.setBizNo(bizNo);
					}
				}
				watchList2List.add(watchList2);
			}
		}
		// 响应失败: "未签署开通协议"（芝麻信用）或响应（自定义）异常情况
		else {
			ZMXYWatchList2 watchList2 = new ZMXYWatchList2();
			watchList2.setAppId(appId);
			watchList2.setResponseResult(false);

			// [说明] error_code 和 error_message 标签针对"未签署开通协议,无法开通芝麻账户"情况
			if (json.containsKey("error_code") && json.getString("error_code") != null) {
				watchList2.setErrorCode(json.getString("error_code"));
			}

			if (json.containsKey("error_message") && json.getString("error_message") != null) {
				watchList2.setErrorDesc(json.getString("error_message"));
			}
			watchList2List.add(watchList2);
		}

		return watchList2List;
	}

	public static void main(String[] args) {
		// String message = "{\"details\":[{\"refresh_time\":\"2016-06-10
		// 00:00:00\",\"biz_code\":\"AA\",\"level\":0,\"extend_info\":[{\"description\":\"逾期金额（元）\",\"value\":\"M03\",\"key\":\"event_max_amt_code\"},{\"description\":\"编号\",\"value\":\"testlwztestlwz0d090d794bd563ba6053244b35fb2edc\",\"key\":\"id\"},{\"description\":\"最近一次违约时间\",\"value\":\"2016-01\",\"key\":\"event_end_time_desc\"}],\"settlement\":true,\"code\":\"AA001001\",\"type\":\"AA001\"},{\"refresh_time\":\"2016-06-10
		// 00:00:00\",\"biz_code\":\"AA\",\"level\":0,\"extend_info\":[{\"description\":\"逾期金额（元）\",\"value\":\"M08\",\"key\":\"event_max_amt_code\"},{\"description\":\"编号\",\"value\":\"testlwztestlwz0d090d794bd563ba6053244b35fb2xxx\",\"key\":\"id\"},{\"description\":\"最近一次违约时间\",\"value\":\"2016-05\",\"key\":\"event_end_time_desc\"}],\"settlement\":true,\"code\":\"AA001012\",\"type\":\"AA001\"},{\"refresh_time\":\"2016-06-10
		// 00:00:00\",\"biz_code\":\"AC\",\"level\":0,\"extend_info\":[{\"description\":\"编号\",\"value\":\"testlwztestlwz0d090d794bd563ba6053244b35fb2aaa\",\"key\":\"id\"}],\"settlement\":true,\"code\":\"AC001001\",\"type\":\"AC001\"},{\"refresh_time\":\"2016-06-10
		// 00:00:00\",\"biz_code\":\"AC\",\"level\":0,\"extend_info\":[{\"description\":\"编号\",\"value\":\"testlwztestlwz0d090d794bd563ba6053244b35fb2bbb\",\"key\":\"id\"}],\"settlement\":true,\"code\":\"AC005001\",\"type\":\"AC005\"},{\"refresh_time\":\"2016-06-10
		// 00:00:00\",\"biz_code\":\"AH\",\"level\":0,\"extend_info\":[{\"description\":\"编号\",\"value\":\"testlwztestlwz0d090d794bd563ba6053244b35fb2ccc\",\"key\":\"id\"},{\"description\":\"欠费停机时间\",\"value\":\"2016-01\",\"key\":\"event_end_time_desc\"}],\"settlement\":true,\"code\":\"AH001002\",\"type\":\"AH001\"}],\"is_matched\":true,\"biz_no\":\"ZM201607221021223895700042091111\",\"success\":true}";
		String message = "{\"is_matched\":true,\"biz_no\":\"ZM201607221021223895700042091111\",\"success\":true}";
		ZMXYMessageParser messageParser = new ZMXYMessageParser();
		List<ZMXYWatchList2> list = messageParser.parseMessageOfWatchList2(message, "900000000P050301");
		System.out.println(list);
	}

}
