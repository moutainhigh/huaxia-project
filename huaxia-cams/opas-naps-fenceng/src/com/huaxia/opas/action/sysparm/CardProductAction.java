package com.huaxia.opas.action.sysparm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.huateng.neofp.core.Action;
import com.huateng.neofp.core.Context;
import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.sysparm.CardFace;
import com.huaxia.opas.domain.sysparm.CardProduct;
import com.huaxia.opas.domain.sysparm.UserCard;
import com.huaxia.opas.domain.sysparm.YearFeeDerate;
import com.huaxia.opas.service.sysparm.CardProductService;
import com.huaxia.opas.util.SequenceUtil;

/**
 * 卡产品信息
 * 
 * @author liudi
 * @since 2017-03-06
 * @version 1.0
 */
public class CardProductAction implements Action {

	@Resource(name = "cardProductService")
	private CardProductService cardProductService;

	/**
	 * 获取卡产品信息
	 */
	public void queryCardProduct(Context ctx) throws CoreException {
		Gson gson = new Gson();
		CardProduct cardProduct = gson.fromJson(gson.toJson(ctx.getDataMap()), CardProduct.class);

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
		if (ctx.getDataMap().get("dateType") != null ) {
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
				sort="";
			}
			if(!"".equals(field)){
				params.put("field", field);
			}
			if(!"".equals(sort)){
				params.put("sort", sort);
			}
		}
		if (cardProduct.getProductCode() != null && !"".equals(cardProduct.getProductCode())) {
			params.put("productCode", cardProduct.getProductCode());
		}
		if (cardProduct.getCardType() != null && !"".equals(cardProduct.getCardType())) {
			params.put("cardType", cardProduct.getCardType());
		}
		if (cardProduct.getStatus() != null && !"".equals(cardProduct.getStatus())) {
			params.put("status", cardProduct.getStatus());
		}

		List<CardProduct> list = null;
		int count = 0;
		count = cardProductService.queryCardProductCount(cardProduct);
		if (params.size() == 0 && count > 0) {
			list = cardProductService.queryCardProduct(cardProduct, curNum, pageNum);
		}
		if (params.size() > 0 && count >= 0) {
			list = cardProductService.queryCardProduct(params, curNum, pageNum);
		}

		Map<String, Object> dataMap = new HashMap<String, Object>();

		dataMap.put("total", count);

		dataMap.put("rows", list);

		ctx.setDataMap(dataMap);

	}

	/**
	 * 获取全部卡产品信息  反显到下拉框
	 */
	public void queryAllCardProduct(Context ctx) throws CoreException {
		//查询卡产品维护
		List<Map<String,String>> cardList = new ArrayList<>();
		Map<String,String> m = new HashMap<>();
		m.put("cardCode", "");
		m.put("prodName", "--请选择--");
		cardList.add(m);
		List<Map<String,String>> cardList1 =cardProductService.queryCodeAndName();
		cardList.addAll(cardList1);
		String cardInfo = JSON.toJSONString(cardList);
		ctx.setData("cardInfo", cardInfo);
	}

	/**
	 * 
	 * @param ctx
	 * @throws CoreException
	 */
	public void systemDecisionCardName(Context ctx) throws CoreException {

		Map<String, Object> map = ctx.getDataMap();
		String cardCode = (String) map.get("productCode");
		CardProduct cardProduct = cardProductService.queryCardProduct(cardCode);
		if (null == cardProduct) {
			cardProduct = new CardProduct();
		}
		ctx.setData("cardProduct", cardProduct);
	}

	/**
	 * 获取卡版面信息
	 */
	public void queryCardFace1(Context ctx) throws CoreException {
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

		int count = cardProductService.queryCardFaceCount(cardFace);

		list = cardProductService.queryCardFace(cardFace, curNum, pageNum);

		Map<String, Object> dataMap = new HashMap<String, Object>();

		dataMap.put("total", count);

		dataMap.put("rows", list);

		ctx.setDataMap(dataMap);

	}

	/**
	 * 获取年费信息
	 */
	public void queryYearFeeDerate1(Context ctx) throws CoreException {
		Gson gson = new Gson();
		YearFeeDerate yearFeeDerate = gson.fromJson(gson.toJson(ctx.getDataMap()), YearFeeDerate.class);

		int curNum = 0;
		int curPage = Integer
				.parseInt(String.valueOf(ctx.getDataMap().get("page") == null ? 0 : ctx.getDataMap().get("page")));
		int pageNum = Integer
				.parseInt(String.valueOf(ctx.getDataMap().get("rows") == null ? 0 : ctx.getDataMap().get("rows")));

		if (curPage > 1) {
			curNum = (curPage - 1) * pageNum;
		}

		List<YearFeeDerate> list = new ArrayList<YearFeeDerate>();

		int count = cardProductService.queryYearFeeDerateCount(yearFeeDerate);

		list = cardProductService.queryYearFeeDerate(yearFeeDerate, curNum, pageNum);

		Map<String, Object> dataMap = new HashMap<String, Object>();

		dataMap.put("total", count);

		dataMap.put("rows", list);

		ctx.setDataMap(dataMap);

	}

	/**
	 * 修改卡产品信息
	 */
	public void updateCardProduct(Context ctx) throws CoreException {

		Gson json = new Gson();

		CardProduct cardProduct = json.fromJson(json.toJson(ctx.getDataMap()), CardProduct.class);

		String userId = ctx.getData("userId");
		String autoId = ctx.getData("autoId");
		cardProduct.setLstUpdUser(userId);

		// 取得页面卡产品编号
		/* String productCode = cardProduct.getProductCode(); */

		// 修改卡产品表同时修改用户卡种信息表

		try {
			String status = cardProductService.queryCardProductStatus(autoId);
			if (cardProduct.getStatus().equals(status)) {
				cardProductService.updateCardProductWithoutStatus(cardProduct);
			} else {
				cardProductService.updateCardProduct(cardProduct);
			}
			/* cardProductService.updateByPrimaryKeyUserCard(productCode); */

		} catch (CoreException e) {

			ctx.setData("exMsg", e.getMessage());

			throw e;
		}

		ctx.setData("isSuccess", true);

	}

	/**
	 * 删除卡产品信息
	 */
	public void removeCardProduct(Context ctx) throws CoreException {

		Map<String, Object> map = ctx.getDataMap();
		// 取得页面UUID
		String autoId = (String) map.get("autoId");
		// 取得页面卡产品编号
		String productCode = (String) map.get("productCode");

		// 删除卡产品表同时删除用户卡种信息表
		try {

			cardProductService.deleteCardProduct(autoId);

			cardProductService.deleteByPrimaryKeyUserCard(productCode);

		} catch (CoreException e) {

			ctx.setData("exMsg", e.getMessage());

			throw e;
		}

		ctx.setData("isSuccess", true);

	}

	/**
	 * 新增卡产品信息
	 */
	public void saveCardProduct(Context ctx) throws CoreException {

		Gson json = new Gson();

		CardProduct cardProduct = json.fromJson(json.toJson(ctx.getDataMap()), CardProduct.class);

		cardProduct.setAutoId(SequenceUtil.getUUID());

		String userId = ctx.getData("userId");
		String productCode = cardProduct.getProductCode();
		cardProduct.setCrtUser(userId);
		cardProduct.setLstUpdUser(userId);

		CardProduct queryReasonCode = cardProductService.queryCardProduct(cardProduct);
		if (queryReasonCode != null) {
			ctx.setData("isFailed", true);
			return;
		}
		// 查询用户卡种信息表的序列值，用户主键
		List<UserCard> list = null;
		list = cardProductService.queryUserCard();
		// 插入卡产品表同时插入用户卡种信息表
		try {

			if (cardProduct.getStatus().equals("1")) {

				cardProductService.saveCardProductStart(cardProduct);

				for (int i = 0; i < list.size(); i++) {
					cardProduct.setUserId(list.get(i).getUserId());
					cardProduct.setProductCode(productCode);
					cardProduct.setAutoId(SequenceUtil.getUUID());
					cardProduct.setCrtUser(userId);
					cardProduct.setProductCode(productCode);
					cardProduct.setLstUpdUser(userId);
					cardProductService.saveUserCard(cardProduct);
				}

			} else if (cardProduct.getStatus().equals("0")) {

				cardProductService.saveCardProductEnd(cardProduct);

				for (int i = 0; i < list.size(); i++) {
					cardProduct.setUserId(list.get(i).getUserId());
					cardProduct.setProductCode(productCode);
					cardProduct.setAutoId(SequenceUtil.getUUID());
					cardProduct.setCrtUser(userId);
					cardProduct.setProductCode(productCode);
					cardProduct.setLstUpdUser(userId);
					cardProductService.saveUserCard(cardProduct);
				}

			}

		} catch (CoreException e) {

			ctx.setData("exMsg", e.getMessage());

			throw e;
		}

		ctx.setData("isSuccess", true);

	}

}