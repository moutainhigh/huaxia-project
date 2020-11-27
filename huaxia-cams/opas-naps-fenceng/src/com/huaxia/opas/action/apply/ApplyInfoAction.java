package com.huaxia.opas.action.apply;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.drools.lang.DRLExpressions.neg_operator_key_return;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huateng.neofp.core.Action;
import com.huateng.neofp.core.Context;
import com.huaxia.opas.domain.system.ApUser;
import com.huaxia.opas.service.apply.ApplyInfoService;
import com.huaxia.opas.service.system.ApUserService;

/**
 * 申请件一般(历史)、高级(历史)查询
 * 
 * @author gezhihui
 *
 */
public class ApplyInfoAction implements Action {

	private static Logger logger = LoggerFactory.getLogger(ApplyInfoAction.class);

	@Resource(name = "applyInfoService")
	private ApplyInfoService applyInfoService;
	@Resource(name = "apUserService")
	private ApUserService  apUserService;
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
		Map<String, Object> params = new HashMap<String, Object>();
		String appId = dataMap.get("appId").toString();
		if(appId!=null&&!"".equals(appId)){
			appId = appId.trim().toUpperCase();
		}
		String c1Idnbr = (String) dataMap.get("c1Idnbr");
		if(c1Idnbr!=null&&!"".equals(c1Idnbr)){
			c1Idnbr = c1Idnbr.trim().toUpperCase();
		}
		String c1Cname = (String) dataMap.get("c1Cname");
		if(c1Cname!=null&&!"".equals(c1Cname)){
			c1Cname = c1Cname.trim();
		}
		String c1Mobile = (String) dataMap.get("c1Mobile");
		if(c1Mobile!=null&&!"".equals(c1Mobile)){
			c1Mobile = c1Mobile.trim();
		}
		String c2Cname = (String) dataMap.get("c2Cname");
		if(c2Cname!=null&&!"".equals(c2Cname)){
			c2Cname = c2Cname.trim();
		}
		String c2Idnbr = (String) dataMap.get("c2Idnbr");
		if(c2Idnbr!=null&&!"".equals(c2Idnbr)){
			c2Idnbr = c2Idnbr.trim();
		}
		String patchCod = (String) dataMap.get("patchCode");
		if(patchCod!=null&&!"".equals(patchCod)){
			patchCod = patchCod.trim();
		}
		params.put("appId", appId);
		params.put("c1Cname", c1Cname);
		params.put("c1Idnbr",c1Idnbr);
		params.put("c1Mobile", c1Mobile);
		params.put("c2Cname", c2Cname);
		params.put("c2Idnbr", c2Idnbr);
		params.put("patchCode", patchCod); // 补件码
		params.put("appFlag",(String)dataMap.get("appFlag"));
		params.put("flag", "0");
		int curNum = 0;
		int curPage = Integer
				.parseInt(String.valueOf(ctx.getDataMap().get("page") == null ? 0 : ctx.getDataMap().get("page")));
		int pageNum = Integer
				.parseInt(String.valueOf(ctx.getDataMap().get("rows") == null ? 0 : ctx.getDataMap().get("rows")));

		if (curPage > 1) {
			curNum = (curPage - 1) * pageNum;
		}
		int count = 0;
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		String patchCode = (String) dataMap.get("patchCode");
		if(patchCode!=null&&!"".equals(patchCode)){
			count = getApplyInfoService().queryApplyInfoCountWithPatchCode(params);
			if (count > 0) {
				list = getApplyInfoService().queryApplyInfoMapWithPatchCode(params, curNum, pageNum);
				for (Map<String, String> map : list) {
					String appIdPlus = map.get("appId");
					String ydjFlag = map.get("ydjFlag");
					String laojianFlag = map.get("laojianFlag");
					if(("2".equals(ydjFlag)&&("".equals(laojianFlag)||laojianFlag==null))||("2".equals(ydjFlag)&&"00".equals(laojianFlag))){
						appIdPlus = appIdPlus.substring(0,15) + "1";
					}
					String operateDesc = getApplyInfoService().queryApplyLifeCicle(appIdPlus);
					map.put("operateDesc", operateDesc);
				}
			}
		}else{
			count = getApplyInfoService().queryApplyInfoCount(params);
			if (count > 0) {
				list = getApplyInfoService().queryApplyInfoMap(params, curNum, pageNum);
				for (Map<String, String> map : list) {
					String appIdPlus = map.get("appId");
					String ydjFlag = map.get("ydjFlag");
					String laojianFlag = map.get("laojianFlag");
					if(("2".equals(ydjFlag)&&("".equals(laojianFlag)||laojianFlag==null))||("2".equals(ydjFlag)&&"00".equals(laojianFlag))){
						appIdPlus = appIdPlus.substring(0,15) + "1";
					}
					String operateDesc = getApplyInfoService().queryApplyLifeCicle(appIdPlus);
					map.put("operateDesc", operateDesc);
				}
			}
		}
		
		dataMap.put("total", count);
		dataMap.put("rows", list);
		ctx.setDataMap(dataMap);
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
		Map<String, Object> params = new HashMap<String, Object>();
		String appId = dataMap.get("appId").toString();
		if(appId!=null&&!"".equals(appId)){
			appId = appId.trim().toUpperCase();
		}
		String c1Idnbr = (String) dataMap.get("c1Idnbr");
		if(c1Idnbr!=null&&!"".equals(c1Idnbr)){
			c1Idnbr = c1Idnbr.trim().toUpperCase();
		}
		String c1Cname = (String) dataMap.get("c1Cname");
		if(c1Cname!=null&&!"".equals(c1Cname)){
			c1Cname = c1Cname.trim();
		}
		String c1Mobile = (String) dataMap.get("c1Mobile");
		if(c1Mobile!=null&&!"".equals(c1Mobile)){
			c1Mobile = c1Mobile.trim();
		}
		String c2Cname = (String) dataMap.get("c2Cname");
		if(c2Cname!=null&&!"".equals(c2Cname)){
			c2Cname = c2Cname.trim();
		}
		String c2Idnbr = (String) dataMap.get("c2Idnbr");
		if(c2Idnbr!=null&&!"".equals(c2Idnbr)){
			c2Idnbr = c2Idnbr.trim().toUpperCase();
		}
		String patchCod = (String) dataMap.get("patchCode");
		if(patchCod!=null&&!"".equals(patchCod)){
			patchCod = patchCod.trim();
		}
		params.put("appId", appId);
		params.put("c1Cname", c1Cname);
		params.put("c1Idnbr", c1Idnbr);
		params.put("c1Mobile", c1Mobile);
		params.put("c2Cname", c2Cname);
		params.put("c2Idnbr", c2Idnbr);
		params.put("patchCode", patchCod); // 补件码
		params.put("appFlag",(String)dataMap.get("appFlag"));
		params.put("flag","1");
		int curNum = 0;
		int count = 0;
		int curPage = Integer
				.parseInt(String.valueOf(ctx.getDataMap().get("page") == null ? 0 : ctx.getDataMap().get("page")));
		int pageNum = Integer
				.parseInt(String.valueOf(ctx.getDataMap().get("rows") == null ? 0 : ctx.getDataMap().get("rows")));

		if (curPage > 1) {
			curNum = (curPage - 1) * pageNum;
		}
		
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
//		int count = getApplyInfoService().queryApplyInfoCount(params);
//		if (count > 0) {
//			list = getApplyInfoService().queryApplyInfoMap(params, curNum, pageNum);
//		}
		String patchCode = (String) dataMap.get("patchCode");
		if(patchCode!=null&&!"".equals(patchCode)){
			count = getApplyInfoService().queryApplyInfoCountWithPatchCode(params);
			if (count > 0) {
				list = getApplyInfoService().queryApplyInfoMapWithPatchCode(params, curNum, pageNum);
				for (Map<String, String> map : list) {
					String appIdPlus = map.get("appId");
					String ydjFlag = map.get("ydjFlag");
					String laojianFlag = map.get("laojianFlag");
					if(("2".equals(ydjFlag)&&("".equals(laojianFlag)||laojianFlag==null))||("2".equals(ydjFlag)&&"00".equals(laojianFlag))){
						appIdPlus = appIdPlus.substring(0,15) + "1";
					}
					String operateDesc = getApplyInfoService().queryApplyLifeCicle(appIdPlus);
					map.put("operateDesc", operateDesc);
				}
			}
		}else{
			count = getApplyInfoService().queryApplyInfoCount(params);
			if (count > 0) {
				list = getApplyInfoService().queryApplyInfoMap(params, curNum, pageNum);
				for (Map<String, String> map : list) {
					String appIdPlus = map.get("appId");
					String ydjFlag = map.get("ydjFlag");
					String laojianFlag = map.get("laojianFlag");
					if(("2".equals(ydjFlag)&&("".equals(laojianFlag)||laojianFlag==null))||("2".equals(ydjFlag)&&"00".equals(laojianFlag))){
						appIdPlus = appIdPlus.substring(0,15) + "1";
					}
					String operateDesc = getApplyInfoService().queryApplyLifeCicle(appIdPlus);
					map.put("operateDesc", operateDesc);
				}
			}
		}
		dataMap.put("total", count);
		dataMap.put("rows", list);
		ctx.setDataMap(dataMap);

	}

	/**
	 * 申请件高级查询
	 * 
	 * @param ctx
	 * @throws Exception
	 */
	public void highApplyCurrentInfo(Context ctx) throws Exception {
		Date crtDate = null;
		Date crtDate1 = null;
		Map dataMap = ctx.getDataMap();
		Map<String, Object> data = ctx.getDataMap();
		String  crtDate2 = (String)data.get("crtDate");
		String  crtDate3 = (String)data.get("crtDate1");
		// 参数
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("c1Coname", data.get("c1Coname")==null?null:data.get("c1Coname").toString().trim());
		params.put("c1Rename", data.get("c1Rename")==null?null:data.get("c1Rename").toString().trim());
		params.put("c1Cotel", data.get("c1Cotel")==null?null:data.get("c1Cotel").toString().trim());
		params.put("c1Retel", data.get("c1Retel")==null?null:data.get("c1Retel").toString().trim());
		params.put("c1Hmtel", data.get("c1Hmtel")==null?null:data.get("c1Hmtel").toString().trim());
		params.put("c1Remobil", data.get("c1Remobil")==null?null:data.get("c1Remobil").toString().trim());
		params.put("c1Idnbr", data.get("c1Idnbr")==null?null:data.get("c1Idnbr").toString().trim().toUpperCase());
		params.put("c4Apbatch", data.get("c4Apbatch")==null?null:data.get("c4Apbatch").toString().trim());
		params.put("c1Mobile", data.get("c1Mobile")==null?null:data.get("c1Mobile").toString().trim());
		String approveResult = (String) data.get("approveResult")==null?"":(String) data.get("approveResult");
		if(!"".equals(approveResult)){
			List<String> approveResultNumList=new ArrayList<String>();
			String [] arr=approveResult.split(",");
			approveResultNumList=Arrays.asList(arr);
            if(approveResultNumList.indexOf("2") > -1){
            	List<String> approveResultList = new ArrayList<String>(); 
            	approveResultList.addAll(approveResultNumList);
            	approveResultList.remove("2");
            	params.put("approveResultNumList", approveResultList);
            	params.put("approveResultFlag", "0");
			}else{
				params.put("approveResultNumList1", approveResultNumList);
			}
		}
		//params.put("approveResult", data.get("approveResult")); // 审批结果字段
		params.put("c5Abcode", data.get("c5Abcode")==null?null:data.get("c5Abcode").toString().trim());
		params.put("c1Gender", data.get("c1Gender"));
		params.put("c4Abuser", data.get("c4Abuser")==null?null:data.get("c4Abuser").toString().trim());
		params.put("c1Xname1", data.get("c1Xname1")==null?null:data.get("c1Xname1").toString().trim());
		params.put("c1Xname2", data.get("c1Xname2")==null?null:data.get("c1Xname2").toString().trim());
		params.put("c1Xtel1", data.get("c1Xtel1")==null?null:data.get("c1Xtel1").toString().trim());
		params.put("c1Xtel2", data.get("c1Xtel2")==null?null:data.get("c1Xtel2").toString().trim());
		params.put("c1Xmobil1", data.get("c1Xmobil1")==null?null:data.get("c1Xmobil1").toString().trim());
		params.put("c1Xmobil2", data.get("c1Xmobil2")==null?null:data.get("c1Xmobil2").toString().trim());
		if(crtDate2 != null && !"".equals(crtDate2)){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			crtDate = sdf.parse(crtDate2);
		}
		if(crtDate3 != null && !"".equals(crtDate3)){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			crtDate1 = sdf.parse(crtDate3);
		}
		params.put("crtDate", data.get("crtDate")==null?null:crtDate);
		params.put("crtDate1", data.get("crtDate1")==null?null:crtDate1);
		params.put("ydjFlag", data.get("ydjFlag")==null?null:data.get("ydjFlag").toString().trim());
		params.put("flag", "0");
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
			for (Map<String, Object> map : list) {
				String c1Coadd = "";
				String c1Coadd1 = (String) map.get("c1Coadd1");
				if(c1Coadd1!=null){
					c1Coadd = c1Coadd + c1Coadd1;
				}
				String c1Coadd2 = (String) map.get("c1Coadd2");
				if(c1Coadd2!=null){
					c1Coadd = c1Coadd + c1Coadd2;
				}
				String c1Coadd3 = (String) map.get("c1Coadd3");
				if(c1Coadd3!=null){
					c1Coadd = c1Coadd + c1Coadd3;
				}
				String c1Coadd4 = (String) map.get("c1Coadd4");
				if(c1Coadd4!=null){
					c1Coadd = c1Coadd + c1Coadd4;
				}
				map.put("c1Coadd", c1Coadd);
			}
		}
		
		dataMap.put("total", count);
		dataMap.put("rows", list);

		ctx.setDataMap(dataMap);
	}

	/**
	 * 申请件高级历史查询
	 * 
	 * @param ctx
	 * @throws Exception
	 */
	public void highApplyHistoryInfo(Context ctx) throws Exception {
		Date crtDate = null;
		Date crtDate1 = null;
		Map<String, Object> data = ctx.getDataMap();
		String  crtDate2 = (String)data.get("crtDate");
		String  crtDate3 = (String)data.get("crtDate1");
		// 参数
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("c1Coname", data.get("c1Coname")==null?null:data.get("c1Coname").toString().trim());
		params.put("c1Rename", data.get("c1Rename")==null?null:data.get("c1Rename").toString().trim());
		params.put("c1Cotel", data.get("c1Cotel")==null?null:data.get("c1Cotel").toString().trim());
		params.put("c1Retel", data.get("c1Retel")==null?null:data.get("c1Retel").toString().trim());
		params.put("c1Hmtel", data.get("c1Hmtel")==null?null:data.get("c1Hmtel").toString().trim());
		params.put("c1Remobil", data.get("c1Remobil")==null?null:data.get("c1Remobil").toString().trim());
		params.put("c1Idnbr", data.get("c1Idnbr")==null?null:data.get("c1Idnbr").toString().trim().toUpperCase());
		params.put("c4Apbatch", data.get("c4Apbatch")==null?null:data.get("c4Apbatch").toString().trim());
		params.put("c1Mobile", data.get("c1Mobile")==null?null:data.get("c1Mobile").toString().trim());
		String approveResult = (String) data.get("approveResult")==null?"":(String) data.get("approveResult");
		if(!"".equals(approveResult)){
			List<String> approveResultNumList=new ArrayList<String>();
			String [] arr=approveResult.split(",");
			approveResultNumList=Arrays.asList(arr);
            if(approveResultNumList.indexOf("2") > -1){
            	List<String> approveResultList = new ArrayList<String>(); 
            	approveResultList.addAll(approveResultNumList);
            	approveResultList.remove("2");
            	params.put("approveResultNumList", approveResultList);
            	params.put("approveResultFlag", "0");
			}else{
				params.put("approveResultNumList1", approveResultNumList);
			}
		}
		params.put("c5Abcode", data.get("c5Abcode")==null?null:data.get("c5Abcode").toString().trim());
		params.put("c1Gender", data.get("c1Gender"));
		params.put("c4Abuser", data.get("c4Abuser")==null?null:data.get("c4Abuser").toString().trim());
		params.put("c1Xname1", data.get("c1Xname1")==null?null:data.get("c1Xname1").toString().trim());
		params.put("c1Xname2", data.get("c1Xname2")==null?null:data.get("c1Xname2").toString().trim());
		params.put("c1Xtel1", data.get("c1Xtel1")==null?null:data.get("c1Xtel1").toString().trim());
		params.put("c1Xtel2", data.get("c1Xtel2")==null?null:data.get("c1Xtel2").toString().trim());
		params.put("c1Xmobil1", data.get("c1Xmobil1")==null?null:data.get("c1Xmobil1").toString().trim());
		params.put("c1Xmobil2", data.get("c1Xmobil2")==null?null:data.get("c1Xmobil2").toString().trim());
		if(crtDate2 != null && !"".equals(crtDate2)){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			crtDate = sdf.parse(crtDate2);
		}
		if(crtDate3 != null && !"".equals(crtDate3)){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			crtDate1 = sdf.parse(crtDate3);
		}
		params.put("crtDate", data.get("crtDate")==null?null:crtDate);
		params.put("crtDate1", data.get("crtDate1")==null?null:crtDate1);
		params.put("ydjFlag", data.get("ydjFlag")==null?null:data.get("ydjFlag").toString().trim());
		params.put("flag", "1");
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
			String c1Coadd = "";
			String c1Coadd1 = (String) map.get("c1Coadd1");
			if(c1Coadd1!=null){
				c1Coadd = c1Coadd + c1Coadd1;
			}
			String c1Coadd2 = (String) map.get("c1Coadd2");
			if(c1Coadd2!=null){
				c1Coadd = c1Coadd + c1Coadd2;
			}
			String c1Coadd3 = (String) map.get("c1Coadd3");
			if(c1Coadd3!=null){
				c1Coadd = c1Coadd + c1Coadd3;
			}
			String c1Coadd4 = (String) map.get("c1Coadd4");
			if(c1Coadd4!=null){
				c1Coadd = c1Coadd + c1Coadd4;
			}
			map.put("c1Coadd", c1Coadd);
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
				if(!"".equals(map.get("remarkUser"))&&map.get("remarkUser")!=null){
					ApUser apUser = apUserService.queryApUserByUserCode((String)map.get("remarkUser"));
					if(apUser!=null){
						String userName = apUser.getUserName();
						map.put("remarkUser", userName);
					}
				}
			}
		}

		// 创建 dataMap1 存储分页数据
		Map<String, Object> dataMap1 = new HashMap<String, Object>();

		dataMap1.put("total", count);
		dataMap1.put("rows", list);
		ctx.setDataMap(dataMap1);
	}
	
	public static String[] removeItem(String[] strArr){
		List<String> list = new ArrayList<String>();
		for(int i=0; i<strArr.length;i++){
			list.add(strArr[i]);
		}
		list.remove("2");
		String[] newStr = list.toArray(new String[1]);
		return newStr;
		
	}
	

}
