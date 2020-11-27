package com.huaxia.opas.action.sysparm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.google.gson.Gson;
import com.huateng.neofp.core.Action;
import com.huateng.neofp.core.Context;
import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.sysparm.CardFace;
import com.huaxia.opas.domain.sysparm.MatchingCard;
import com.huaxia.opas.domain.sysparm.ProductCode;
import com.huaxia.opas.service.sysparm.MatchingCardService;
import com.huaxia.opas.util.SequenceUtil;

/**
 * 配发卡信息
 * 
 * @author liudi
 * @since 2017-03-06
 * @version 1.0
 */
public class MatchingCardAction implements Action {

	@Resource(name = "matchingCardService")
	private MatchingCardService matchingCardService;

	/**
	 * 获取配发卡信息
	 */
	public void queryMatchingCard(Context ctx) throws CoreException {
		Gson gson = new Gson();
		MatchingCard matchingCard = gson.fromJson(gson.toJson(ctx.getDataMap()), MatchingCard.class);

		int curNum = 0;
		int curPage = Integer
				.parseInt(String.valueOf(ctx.getDataMap().get("page") == null ? 0 : ctx.getDataMap().get("page")));
		int pageNum = Integer
				.parseInt(String.valueOf(ctx.getDataMap().get("rows") == null ? 0 : ctx.getDataMap().get("rows")));

		if (curPage > 1) {
			curNum = (curPage - 1) * pageNum;
		}

		String field = "";
		// 查询参数
		Map<String, Object> params = new HashMap<String, Object>();
		if (ctx.getDataMap().get("dateType") != null) {
			int type = Integer.parseInt(ctx.getDataMap().get("dateType").toString());
			switch (type) {
			case 1:
				field = "START_DATE";
				break;
			case 2:
				field = "STOP_DATE";
				break;
			case 3:
				field = "LST_UPD_DATE";
			}

			String sort = (String) ctx.getDataMap().get("order");
			if ("2".equals(sort)) {
				sort = "DESC";
			} else if("1".equals(sort)){
				sort = "ASC";
			}else{
				sort = "";
 			}
			if(!"".equals(field)){
				params.put("field", field);
			}
			if(!"".equals(sort)){
				params.put("sort", sort);
			}
		}
		if (matchingCard.getProdCode() != null && !"".equals(matchingCard.getProdCode())) {
			params.put("prodCode", matchingCard.getProdCode());
		}
		if (matchingCard.getAllotProdCode() != null && !"".equals(matchingCard.getAllotProdCode())) {
			params.put("allotProdCode", matchingCard.getAllotProdCode());
		}
		if (matchingCard.getStatus() != null && !"".equals(matchingCard.getStatus())) {
			params.put("status", matchingCard.getStatus());
		}

		List<MatchingCard> list = new ArrayList<MatchingCard>();

		int count = matchingCardService.queryMatchingCardCount(matchingCard);
		if (params.size() == 0 && count > 0) {
			list = matchingCardService.queryMatchingCard(matchingCard, curNum, pageNum);
		}
		if (params.size() > 0 && count >= 0) {
			list = matchingCardService.queryMatchingCard(params, curNum, pageNum);
		}

		Map<String, Object> dataMap = new HashMap<String, Object>();

		dataMap.put("total", count);

		dataMap.put("rows", list);

		ctx.setDataMap(dataMap);

	}

	/**
	 * 获取卡产品信息
	 */
	public void queryProductCode1(Context ctx) throws CoreException {
		Gson gson = new Gson();
		ProductCode productCode = gson.fromJson(gson.toJson(ctx.getDataMap()), ProductCode.class);

		int curNum = 0;
		int curPage = Integer
				.parseInt(String.valueOf(ctx.getDataMap().get("page") == null ? 0 : ctx.getDataMap().get("page")));
		int pageNum = Integer
				.parseInt(String.valueOf(ctx.getDataMap().get("rows") == null ? 0 : ctx.getDataMap().get("rows")));

		if (curPage > 1) {
			curNum = (curPage - 1) * pageNum;
		}

		List<ProductCode> list = new ArrayList<ProductCode>();

		int count = matchingCardService.queryProductCodeCount(productCode);

		list = matchingCardService.queryProductCode(productCode, curNum, pageNum);

		Map<String, Object> dataMap = new HashMap<String, Object>();

		dataMap.put("total", count);

		dataMap.put("rows", list);

		ctx.setDataMap(dataMap);

	}

	/**
	 * 获取卡版面信息
	 */
	public void queryCardFace(Context ctx) throws CoreException {
		Gson gson = new Gson();
		CardFace cardFace = gson.fromJson(gson.toJson(ctx.getDataMap()), CardFace.class);

		int curNum = 0;
		int curPage = Integer
				.parseInt(String.valueOf(ctx.getDataMap().get("page") == null ? 0 : ctx.getDataMap().get("page")));
		int pageNum = Integer
				.parseInt(String.valueOf(ctx.getDataMap().get("rows") == null ? 0 : ctx.getDataMap().get("rows")));

		if (curPage > 1) {
			curNum = (curPage - 1) * pageNum;
		}

		List<CardFace> list = new ArrayList<CardFace>();

		int count = matchingCardService.queryCardFaceCount(cardFace);

		list = matchingCardService.queryCardFace(cardFace, curNum, pageNum);

		Map<String, Object> dataMap = new HashMap<String, Object>();

		dataMap.put("total", count);

		dataMap.put("rows", list);

		ctx.setDataMap(dataMap);

	}

	/**
	 * 修改配发卡信息
	 */
	public void updateMatchingCard(Context ctx) throws CoreException {

		Gson json = new Gson();

		MatchingCard matchingCard = json.fromJson(json.toJson(ctx.getDataMap()), MatchingCard.class);

		String userId = ctx.getData("userId");

		matchingCard.setLstUpdUser(userId);

		try {

			matchingCardService.updateMatchingCard(matchingCard);

		} catch (CoreException e) {

			ctx.setData("exMsg", e.getMessage());

			throw e;
		}

		ctx.setData("isSuccess", true);

	}

	/**
	 * 删除配发卡信息
	 */
	public void removeMatchingCard(Context ctx) throws CoreException {

		Map<String, Object> map = ctx.getDataMap();

		String autoId = (String) map.get("autoId");

		try {

			matchingCardService.deleteMatchingCard(autoId);

		} catch (CoreException e) {

			ctx.setData("exMsg", e.getMessage());

			throw e;
		}

		ctx.setData("isSuccess", true);

	}

	/**
	 * 新增配发卡信息
	 */
	public void saveMatchingCard(Context ctx) throws CoreException {

		Gson json = new Gson();

		MatchingCard matchingCard = json.fromJson(json.toJson(ctx.getDataMap()), MatchingCard.class);

		matchingCard.setAutoId(SequenceUtil.getUUID());

		String userId = ctx.getData("userId");
		matchingCard.setCrtUser(userId);
		matchingCard.setLstUpdUser(userId);

		MatchingCard queryReasonCode = matchingCardService.queryMatchingCard(matchingCard);
		if (queryReasonCode != null) {
			ctx.setData("isFailed", true);
			return;
		}

		try {

			if (matchingCard.getStatus().equals("1")) {

				matchingCardService.saveMatchingCardStart(matchingCard);

			} else if (matchingCard.getStatus().equals("0")) {

				matchingCardService.saveMatchingCardEnd(matchingCard);

			} else {

			}

		} catch (CoreException e) {

			ctx.setData("exMsg", e.getMessage());

			throw e;
		}

		ctx.setData("isSuccess", true);

	}

}