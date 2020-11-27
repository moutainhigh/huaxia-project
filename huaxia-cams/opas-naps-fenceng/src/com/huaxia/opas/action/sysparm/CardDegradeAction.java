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
import com.huaxia.opas.domain.sysparm.CardDegrade;
import com.huaxia.opas.domain.sysparm.ProductCode;
import com.huaxia.opas.service.sysparm.CardDegradeService;
import com.huaxia.opas.util.SequenceUtil;

/**
 * 卡产品降级信息
 * @author liudi
 * @since 2017-03-14
 * @version 1.0
 */
public class CardDegradeAction implements Action {

	@Resource(name = "cardDegradeService")
	private CardDegradeService cardDegradeService;

	
	/**
	 * 获取卡产品降级信息
	 */
	public void queryCardDegrade(Context ctx) throws CoreException {
		Gson gson = new Gson();
		CardDegrade cardDegrade = gson.fromJson(gson.toJson(ctx.getDataMap()), CardDegrade.class);

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
		if(ctx.getDataMap().get("dateType")!= null){
			int type = Integer.parseInt(ctx.getDataMap().get("dateType").toString()) ;
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
		if (cardDegrade.getCardCode()!=null && !"".equals(cardDegrade.getCardCode())){
			params.put("cardCode", cardDegrade.getCardCode());
		}
		if (cardDegrade.getCardName()!=null && !"".equals(cardDegrade.getCardName())){
			params.put("cardName", cardDegrade.getCardName());
		}
		if (cardDegrade.getStatus()!=null && !"".equals(cardDegrade.getStatus())){
			params.put("status", cardDegrade.getStatus());
		}

		List<CardDegrade> list = new ArrayList<CardDegrade>();

		int count = cardDegradeService.queryCardDegradeCount(cardDegrade);
		if (params.size()==0 && count > 0) {
			list = cardDegradeService.queryCardDegrade(cardDegrade, curNum, pageNum);
		}
		if (params.size()>0 && count >= 0) {
			list = cardDegradeService.queryCardDegrade(params, curNum, pageNum);
		}

		Map<String, Object> dataMap = new HashMap<String, Object>();

		dataMap.put("total", count);

		dataMap.put("rows", list);

		ctx.setDataMap(dataMap);

	}
	
	/**
	 * 获取卡产品信息
	 */
	public void queryProductCode(Context ctx) throws CoreException {
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

		int count = cardDegradeService.queryProductCodeCount(productCode);
		
		list = cardDegradeService.queryProductCode(productCode, curNum, pageNum);

		Map<String, Object> dataMap = new HashMap<String, Object>();

		dataMap.put("total", count);

		dataMap.put("rows", list);

		ctx.setDataMap(dataMap);

	}
	
	/**
	 * 修改卡产品降级信息
	 */
	public void updateCardDegrade(Context ctx) throws CoreException {

		Gson json = new Gson();

		CardDegrade cardDegrade = json.fromJson(json.toJson(ctx.getDataMap()), CardDegrade.class);

		String userId = ctx.getData("userId");
		
		cardDegrade.setLstUpdUser(userId);

		try {

			cardDegradeService.updateCardDegrade(cardDegrade);

		} catch (CoreException e) {

			ctx.setData("exMsg", e.getMessage());

			throw e;
		}

		ctx.setData("isSuccess", true);

	}

	/**
	 * 删除卡产品降级信息
	 */
	public void removeCardDegrade(Context ctx) throws CoreException {

		Map<String, Object> map = ctx.getDataMap();

		String autoId = (String) map.get("autoId");

		try {

			cardDegradeService.deleteCardDegrade(autoId);

		} catch (CoreException e) {

			ctx.setData("exMsg", e.getMessage());

			throw e;
		}

		ctx.setData("isSuccess", true);

	}

	/**
	 * 新增卡产品降级信息
	 */
	public void saveCardDegrade(Context ctx) throws CoreException {
		
		Gson json = new Gson();
	
		CardDegrade cardDegrade = json.fromJson(json.toJson(ctx.getDataMap()), CardDegrade.class);

		cardDegrade.setAutoId(SequenceUtil.getUUID());

		String userId = ctx.getData("userId");
		cardDegrade.setCrtUser(userId);
		cardDegrade.setLstUpdUser(userId);
		
		CardDegrade cardDegrade1 = cardDegradeService.queryCardDegrade(cardDegrade);
		if(cardDegrade1 != null){
				ctx.setData("isFailed", true);
				return;
		}
		
		try {
			
			if(cardDegrade.getStatus().equals("1") ){
				
				cardDegradeService.saveCardDegradeStart(cardDegrade);
				
			}else if(cardDegrade.getStatus().equals("0") ){

				cardDegradeService.saveCardDegradeEnd(cardDegrade);
			
			}else{
				
			}

		} catch (CoreException e) {

			ctx.setData("exMsg", e.getMessage());

			throw e;
		}

		ctx.setData("isSuccess", true);

	}

}