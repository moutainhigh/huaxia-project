package com.huaxia.opas.action.sysparm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.huateng.neofp.core.Action;
import com.huateng.neofp.core.Context;
import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.sysparm.CardDegradeFace;
import com.huaxia.opas.domain.sysparm.CardProduct;
import com.huaxia.opas.service.decision.SysApprovalCommonService;
import com.huaxia.opas.service.sysparm.CardLayoutService;

/**
 * 卡产品版面降级信息
 * @author 
 * @since 
 * @version 1.1
 */
public class CardLayoutAction implements Action {

	@Resource(name = "cardLayoutService")
	private CardLayoutService cardLayoutService;
	
	@Resource(name = "sysApprovalCommonService")
	private SysApprovalCommonService sysApprovalCommonService;

	 //新增卡产品版面降级信息
	public void saveCardLayout(Context ctx) throws CoreException {
		int  num=0;
		try{
			Map<String,Object> map = ctx.getDataMap();
			String startCard=StringUtils.trimToEmpty((String) map.get("cardCode"));
			String endCard=StringUtils.trimToEmpty((String) map.get("degradeCardNo"));
			String faceReation=StringUtils.trimToEmpty((String) map.get("queueInfo"));
			String userName=StringUtils.trimToEmpty((String) map.get("userName"));
			map.put("startCard", startCard);
			map.put("endCard", endCard);
			map.put("crtUser", userName);
			map.put("lastUser", userName);
			map.put("faceReation", faceReation);
			map.put("faceReationDesc", faceReation);
			if(!"".equals(startCard)&&!"".equals(endCard)){
				CardDegradeFace cardDegradeFace=cardLayoutService.queryCardLayout(map);
				if(cardDegradeFace!=null){//已存在
					ctx.setData("isSave", true);
					return;
				}else{
					num=cardLayoutService.saveCardLayout(map);
					if(num==0){
						ctx.setData("isFailed", true);
					}else{
						ctx.setData("isSuccess", true);
					}
				}
			}else{
				ctx.setData("isNull", true);
				return;
			}
		}catch(Exception e){
			ctx.setData("isException", true);
			throw e;
		}
		
	}
	
	/**
	 * 卡产品版面信息展示
	 */
	public void queryCardLayout(Context ctx) throws CoreException {
		try{
			String cardCode1 = ctx.getData("cardCode1");
			
			String cardCode2 = ctx.getData("cardCode2");
			//查询卡版面信息
			List<String> cardFaceList=cardLayoutService.queryCardFace(cardCode1);
			//降级卡版面信息
			List<String> cardFaceList2=cardLayoutService.queryCardFace(cardCode2);
			Map<String, Object> dataMap = new HashMap<String, Object>();
			dataMap.put("cardFaceList", cardFaceList);
			dataMap.put("cardFaceList2", cardFaceList2);
			ctx.setDataMap(dataMap);
		}catch(Exception e){
			throw e;
		}
	}
	//列表展示
	public void queryCardLayoutList(Context ctx) throws CoreException {
		Map<String,Object> map = ctx.getDataMap();
		int curNum = 0;
		int curPage = Integer
				.parseInt(String.valueOf(ctx.getDataMap().get("page") == null ? 0 : ctx.getDataMap().get("page")));
		int pageNum = Integer
				.parseInt(String.valueOf(ctx.getDataMap().get("rows") == null ? 0 : ctx.getDataMap().get("rows")));

		if (curPage > 1) {
			curNum = (curPage - 1) * pageNum;
		}
		
		List<CardDegradeFace> list = new ArrayList<CardDegradeFace>();

		int count = cardLayoutService.queryCardLayoutCount(map);
		if(count>0){
			list=cardLayoutService.queryCardLayoutList(map, curNum, pageNum);
		}
		
		Map<String, Object> dataMap = new HashMap<String, Object>();

		dataMap.put("total", count);

		dataMap.put("rows", list);

		ctx.setDataMap(dataMap);

	}
	//删除卡版面信息
	public void removeCardLayout(Context ctx) throws CoreException {

		Map<String, Object> map = ctx.getDataMap();

		String id = (String) map.get("id");
		
		try {
			cardLayoutService.deleteCardLayoutById(id);

		} catch (CoreException e) {

			ctx.setData("isSuccess", true);

			throw e;
		}

		ctx.setData("isSuccess", true);

	}
	//修改卡版面信息
	public void updateCardLayout(Context ctx) throws CoreException {
		try {
			Gson json = new Gson();
			CardDegradeFace cardDegradeFace = json.fromJson(json.toJson(ctx.getDataMap()), CardDegradeFace.class);
			//降级卡产品卡版面校验是否正确
			String cardCode=cardDegradeFace.getEndCard();
			CardProduct cardProduct=null;
			cardProduct=sysApprovalCommonService.queryCardByCardCode(cardCode);
			if(cardProduct!=null&&cardProduct.getProductFace()!=null&&!"".equals(cardProduct.getProductFace())){
				String faceReation=cardDegradeFace.getFaceReation().replace("=>", ":");
				String[] reationList=faceReation.split(";");
				//JSONObject jsonStr=JSONObject.parseObject(faceReation);
				List<String> productFaceList=Arrays.asList(cardProduct.getProductFace().split(","));
				for(int i=0;i<reationList.length;i++){
					String[] strList=reationList[i].split(":");
					//不包含该版面
					if(!productFaceList.contains(strList[1])){
						ctx.setData("isFailed", true);
						return;
					}
				}
				cardDegradeFace.setFaceReation(faceReation);
				String userName = ctx.getData("userName");
				cardDegradeFace.setLastUser(userName);
				cardLayoutService.updateCardLayout(cardDegradeFace);
				ctx.setData("isSuccess", true);
			}else{
				//卡产品或卡版面不存在
				ctx.setData("isNoExisted", true);
			}
		} catch (CoreException e) {
			ctx.setData("exMsg", e.getMessage());
			ctx.setData("isSuccess", false);
			throw e;
		}
		
	}

}