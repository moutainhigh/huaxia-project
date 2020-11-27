package com.huaxia.opas.action.examine;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.huateng.neofp.core.Action;
import com.huateng.neofp.core.Context;
import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.allot.AllotApply;
import com.huaxia.opas.domain.apply.ApplyLifeCicle;
import com.huaxia.opas.domain.apply.ApplyModifyLog;
import com.huaxia.opas.domain.input.BizInpAppC1Spec;
import com.huaxia.opas.domain.input.BizInpApplC2;
import com.huaxia.opas.service.apply.ApplyInfoService;
import com.huaxia.opas.service.apply.BizInpApplService;
import com.huaxia.opas.service.credit.CreditCheckSearchService;
import com.huaxia.opas.service.report.FishingService;
import com.huaxia.opas.service.report.KeyMessageModifyService;
import com.huaxia.opas.service.sysparm.CardProductService;
import com.huaxia.opas.util.CommonProperties;
import com.huaxia.opas.util.StringUtil;

import oracle.sql.TIMESTAMP;

/**
 * 申请件信息
 * 
 * @author yang.jiangming
 *
 */
public class BizInpApplC1Action implements Action {
	private static Logger logger = LoggerFactory.getLogger(BizInpApplC1Action.class);
	@Resource(name = "applyInfoService")
	private ApplyInfoService applyService;
	@Resource(name = "keyMessageModifyService")
	private KeyMessageModifyService keyMessageModifyService;
	@Resource(name = "fishingService")
	private FishingService fishingService;
	@Resource(name = "bizInpApplService")
	private BizInpApplService bizInpApplService;
	@Resource(name = "cardProductService")
	private CardProductService cardProductService;
	@Resource(name = "creditCheckSearchService")
	private CreditCheckSearchService creditCheckSearchService;
	
	public BizInpApplService getBizInpApplService() {
		return bizInpApplService;
	}

	public void setBizInpApplService(BizInpApplService bizInpApplService) {
		this.bizInpApplService = bizInpApplService;
	}

	/**
	 * 显示申请表信息
	 * 
	 * @param cxt
	 */
	public void queryBizInpApplC1ByAppId(Context cxt) {
		Map<String, Object> dataMap = cxt.getDataMap();
		String appId = (String) dataMap.get("appId");
		BizInpAppC1Spec t = new BizInpAppC1Spec();
		t.setAppId(appId);
		try {
			BizInpAppC1Spec bizInpApplC1 = applyService.get2(t);
			BizInpApplC2 bizInpApplC2 = applyService.findBiz2info(appId);
			if(bizInpApplC2 == null){
				bizInpApplC2 = new BizInpApplC2();
			}
			String c1Nation = bizInpApplC1.getC1Nation();
			String c2Natncd1 = bizInpApplC1.getC2Natncd1();
			/*if("1".equals(c1Nation) && (c2Natncd1 == null || "".equals(c2Natncd1) )){
				bizInpApplC1.setC2Natncd1("CHN");
				applyService.updateBizInfoMatchingCard(bizInpApplC1, null);
			}*/
			String jsonBiz2Info = null;
			String jsonBizInfo = JSON.toJSONString(bizInpApplC1);
			jsonBiz2Info = JSON.toJSONString(bizInpApplC2);
			//查询卡产品维护
			List<Map<String,String>> cardList = new ArrayList<>();
			Map<String,String> m = new HashMap<>();
			//m.put("cardCode", "");
		//	m.put("prodName", "-----请选择-----");
			//cardList.add(m);
			List<Map<String,String>> cardList1 =cardProductService.queryCodeAndName();
			cardList.addAll(cardList1);
			String cardInfo = JSON.toJSONString(cardList);
			cxt.setData("jsonBizInfo", jsonBizInfo);
			cxt.setData("jsonBiz2Info", jsonBiz2Info);
			cxt.setData("bizInpApplC1", bizInpApplC1);
			cxt.setData("bizInpApplC2", bizInpApplC2);
			cxt.setData("cardInfo", cardInfo);
		} catch (CoreException e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * 显示优化后申请表信息 from wjf
	 * 
	 * @param cxt
	 */
	public void queryBizInpApplC1ByAppIdNew(Context cxt) {
		Map<String, Object> dataMap = cxt.getDataMap();
		String appId = (String) dataMap.get("appId");
		BizInpAppC1Spec t = new BizInpAppC1Spec();
		t.setAppId(appId);
		try {
			BizInpAppC1Spec bizInpApplC1 = applyService.get2New(t);
			BizInpApplC2 bizInpApplC2 = applyService.findBiz2info(appId);
			if(bizInpApplC2 == null){
				bizInpApplC2 = new BizInpApplC2();
			}
			String c1Nation = bizInpApplC1.getC1Nation();
			String c2Natncd1 = bizInpApplC1.getC2Natncd1();
			//国籍为空的情况下，如果 国家/地区 为 境内居民，则设置 国籍为 中国（厦门公积金区域数据需求中让去掉此逻辑）
			/*if("1".equals(c1Nation) && (c2Natncd1 == null || "".equals(c2Natncd1) )){
				bizInpApplC1.setC2Natncd1("CHN");
				applyService.updateBizInfoMatchingCardBzk(bizInpApplC1, null);
			}*/
			String jsonBiz2Info = null;
			String jsonBizInfo = JSON.toJSONString(bizInpApplC1);
			jsonBiz2Info = JSON.toJSONString(bizInpApplC2);
			//查询卡产品维护
			List<Map<String,String>> cardList = new ArrayList<>();
			Map<String,String> m = new HashMap<>();
			//m.put("cardCode", "");
			//	m.put("prodName", "-----请选择-----");
			//cardList.add(m);
			List<Map<String,String>> cardList1 =cardProductService.queryCodeAndName();
			cardList.addAll(cardList1);
			String cardInfo = JSON.toJSONString(cardList);
			cxt.setData("jsonBizInfo", jsonBizInfo);
			cxt.setData("jsonBiz2Info", jsonBiz2Info);
			cxt.setData("bizInpApplC1", bizInpApplC1);
			cxt.setData("bizInpApplC2", bizInpApplC2);
			cxt.setData("cardInfo", cardInfo);
		} catch (CoreException e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * 保存申请表信息 获得当前登陆用户
	 * 
	 * @param ctx
	 */
	public void bizInpApplC1InfoUpdate(Context ctx) throws CoreException {
		Map<String, Object> map = ctx.getDataMap();
		String user = (String) map.get("userCode");
		String string = JSON.toJSONString(map);
		//JSONObject jsonObject = JSON.parseObject(string);
		BizInpAppC1Spec newBizInpApplC1 = JSON.parseObject(string, BizInpAppC1Spec.class);
		//20190315 改凸字姓名逻辑
		String c1EnameStr = (String) newBizInpApplC1.getC1Ename();
		if(c1EnameStr != null && !"".equals(c1EnameStr.trim())){
			String c4Bname = c1EnameStr.replaceFirst(" ","/")+"/";
			newBizInpApplC1.setC4Bname(" ".equals(c1EnameStr)?"":c4Bname);
		}else{
			newBizInpApplC1.setC4Bname("");
		}
		//申请表的 的住宅地址 c1Hmadd1,c1Hmadd2,c1Hmadd3,c1Hmadd4,更新到opas_biz_inp_appl_compore 表的c1Hmadd1字段中
		String sappId = newBizInpApplC1.getAppId();
		
		// 2018/6/6 from wjf 修改当此申请表信息已进行过关键信息修改,需要跳转提示,防止同一时间内重复修改
		String delStatus = (String)keyMessageModifyService.selectDelStatus(sappId);
		if("A".equals(delStatus) || "B".equals(delStatus) || "C".equals(delStatus)){
			ctx.setData("isSuccess", false);
			ctx.setData("exMsg", "此申请件已做过关键信息修改!");
			return;
		}
		String c1Idnbr = newBizInpApplC1.getC1Idnbr();
		String c1Hmadd1 = newBizInpApplC1.getC1Hmadd1();
		String c1Hmadd2 = newBizInpApplC1.getC1Hmadd2();
		String c1Hmadd3 = newBizInpApplC1.getC1Hmadd3();
		String c1Hmadd4 = newBizInpApplC1.getC1Hmadd4();
		String c1Hmadd = c1Hmadd1+c1Hmadd2+c1Hmadd3+c1Hmadd4;
		
		Map<Object, Object> param = new HashMap<>();
		param.put("c1Hmadd", c1Hmadd);
		param.put("appId", sappId);
		param.put("c1Idnbr", c1Idnbr);
		creditCheckSearchService.updateBizInpApplComporeByAppId(param);
		
		BizInpApplC2 newBizInpApplC2 = JSON.parseObject(string, BizInpApplC2.class);
		// 加入附卡凸字姓名联动修改
		String c4SnameStr = (String) newBizInpApplC2.getC2Ename();
		if(c4SnameStr != null && !"".equals(c4SnameStr.trim())){
			String c4Sname = c4SnameStr.replaceFirst(" ","/")+"/";
			newBizInpApplC2.setC4Sname(" ".equals(c4SnameStr)?"":c4Sname);
		}else{
			newBizInpApplC2.setC4Sname("");
		}
		
		String c1c2Flag = newBizInpApplC1.getC1c2Flag();
		/*if(!"2".equals(c1c2Flag)){
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
			Date c1Birth = null;
			Date c2Iddt1 = null;
			Date c5Idte1 = null;
			Date c2Birth = null;
			Date c5Sgndte1 = null;
			try {
				if (!"".equals(jsonObject.getString("c1Birth"))) {
					c1Birth = sf.parse(jsonObject.getString("c1Birth"));
				}
				if (!"".equals(newBizInpApplC1.getC2Iddt1())) {
					String format = sf.format(newBizInpApplC1.getC2Iddt1());
					c2Iddt1 = sf.parse(format);
				}
				if (!"".equals(jsonObject.getString("c5Idte1"))) {
					c5Idte1 = sf.parse(jsonObject.getString("c5Idte1"));
				}
				if (!"".equals(jsonObject.getString("c2Birth"))) {
					c2Birth = sf.parse(jsonObject.getString("c2Birth"));
				}
				if (!"".equals(jsonObject.getString("c5Sgndte1"))) {
					c5Sgndte1 = sf.parse(jsonObject.getString("c5Sgndte1"));
				}
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			newBizInpApplC1.setC1Birth(c1Birth);
			newBizInpApplC1.setC2Iddt1(c2Iddt1);
			newBizInpApplC1.setC5Idte1(c5Idte1);
			newBizInpApplC1.setC5Sgndte1(c5Sgndte1);
			newBizInpApplC2.setC2Birth(c2Birth);
		}*/
		// 比较是否修改
		// 主卡
		BizInpAppC1Spec oldBiz1 = new BizInpAppC1Spec();
		oldBiz1.setAppId(newBizInpApplC1.getAppId());
		//查询c1表原有信息
		oldBiz1 = applyService.get2New(oldBiz1);
		String oldJson = JSON.toJSONString(oldBiz1);
		String newJson = JSON.toJSONString(newBizInpApplC1,SerializerFeature.WriteMapNullValue);
		Map newApp1Map = JSON.parseObject(newJson, Map.class);
		
		Map<String, Object> newApplyMap = JSON.parseObject(newJson, Map.class);
		Map<String, Object> oldApplyMap = JSON.parseObject(oldJson, Map.class);
		List<ApplyModifyLog> modifyList = new ArrayList<ApplyModifyLog>();
		// 遍历Map 对比信息是否有修改
		// 易达金删去以下字段 wjf 2018/09/04
		Map<String, String> matchingCard = null;
		matchingCard = applyService.queryMatchingCardFlagAndYdjFalg(newBizInpApplC1.getAppId());
		if("1".equals(oldApplyMap.get("ydjFlag")) && !"1".equals(matchingCard.get("matchingCardFlag"))){
			oldApplyMap.remove("c4Ccredit");
			oldApplyMap.remove("c4Cdsel");
			oldApplyMap.remove("c4Gtoc");
			oldApplyMap.remove("c1Cardnbr");
			oldApplyMap.remove("c4Recname");
			oldApplyMap.remove("c4Reccard");
			oldApplyMap.remove("c2Signck");
			oldApplyMap.remove("c5Sgndte1");
			oldApplyMap.remove("appAcctyp");
		}
		//主卡 国籍、国家/地区、职业 字段信息修改记录 是否 有记录判断：
		//如果原本数据库中有值(页面反显有值)，页面修改成空值后，这些字段不做信息修改记录
		if(oldApplyMap.get("c2Natncd1") != null && 
				(newApplyMap.get("c2Natncd1") == null || newApplyMap.get("c2Natncd1").toString().trim().equals(""))
				){
			newApplyMap.remove("c2Natncd1");//主卡国籍
		}
		if(oldApplyMap.get("c1Nation") != null && 
				(newApplyMap.get("c1Nation") == null || newApplyMap.get("c1Nation").toString().trim().equals(""))
				){
			newApplyMap.remove("c1Nation");//国家/地区
		}
		if(oldApplyMap.get("c6OccuDes1") != null &&
				(newApplyMap.get("c6OccuDes1") == null || newApplyMap.get("c6OccuDes1").toString().trim().equals(""))
				){
			newApplyMap.remove("c6OccuDes1");//职业
		}

		Boolean kxFlag = false; 
		int flag = 0;
		List<String> columnList = new ArrayList<>();
		Set<Entry<String, Object>> entrySet = newApplyMap.entrySet();
		for (Entry<String, Object> entry : entrySet) {
			ApplyModifyLog applyModify = new ApplyModifyLog();
			String column = entry.getKey();
			Object newValue = entry.getValue();
			Object oldValue = oldApplyMap.get(column);
			
			Date date = new Date();
			if (oldValue == null) {
				oldValue = "";
			}
			if (newValue == null) {
				newValue = "";
			}
			
			if (!oldValue.equals(newValue)) {// 说明有改变 需要记录到 --申请表信息修改日志表
				//判断是否可信信息修改
				//2020-08-12 lhy 新增可信身份认证关键信息修改判断，条码第7位为B时修改证件签发日期和证件有效期才算关键信息修改
				if(("B").equals(newBizInpApplC1.getAppId().substring(6, 7))&&(column.equals("c2Iddt1")||column.equals("c5Idte1"))){
					kxFlag = true;
				}
				// 判断此信息是否是关键信息
				if (newApp1Map.containsKey(column)) {
					columnList.add(column);
				}	
				Integer isKey = null;
				if(!"2".equals(newBizInpApplC1.getC1c2Flag())){
					isKey = keyMessageModifyService.queryKeyParamsByProperty(column);
				}
				if (isKey != null) {
					if (isKey.intValue() == 1) {// 是关键字段
						if (newApp1Map.containsKey(column)) {
							newApp1Map.remove(column);
						}
						applyModify.setIsKeyField("1");
						flag++;
					} else {
						applyModify.setIsKeyField("0");
					}
				} else {
					applyModify.setIsKeyField("0");
				}
				applyModify.setAutoId(StringUtil.randomGUIDPlainString());
				applyModify.setAppId(newBizInpApplC1.getAppId());
				applyModify.setFieldName(column);
				applyModify.setFieldOldValue(oldValue);
				applyModify.setFieldNewValue(newValue);
				applyModify.setCrtDate(date);
				applyModify.setCrtUser(user);
				applyModify.setLstUpdDate(date);
				applyModify.setCheckFlag("0");
				applyModify.setApplyName(oldBiz1.getC1Cname());
				applyModify.setCretifiType(oldBiz1.getC1Idtype());
				applyModify.setCretifiId(oldBiz1.getC1Idnbr());
				modifyList.add(applyModify);
			}

		}
		// 附卡  2018/09/04 wjf 易达金卡不再查询附属卡申请人资料
		Map newApply2Map = new HashMap();
		BizInpApplC2 oldbizInpApplC2 = applyService.findBiz2info(newBizInpApplC1.getAppId());
		String newBiz2 = JSON.toJSONString(newBizInpApplC2,SerializerFeature.WriteMapNullValue);
		// 易达金卡不匹配附卡信息
		if("1".equals(oldApplyMap.get("ydjFlag")) && !"1".equals(matchingCard.get("matchingCardFlag"))){
			newBiz2 = "{}";
		}
		String oldBiz2 = "";
		newApply2Map = JSON.parseObject(newBiz2, Map.class);
		// 去除原有C2表无数据的情况
		if (oldbizInpApplC2 != null) {
			oldBiz2 = JSON.toJSONString(oldbizInpApplC2);
		}else{
			oldBiz2 = "{}";
		}
		Map<String, Object> newApp2Map = JSON.parseObject(newBiz2, Map.class);
		Map<String, Object> oldApp2Map = JSON.parseObject(oldBiz2, Map.class);
		//附卡 的  国籍 、国家/地区 字段信息修改记录 是否 有记录判断：
		//如果原本数据库中有值(页面反显有值)，页面修改成空值后，这些字段不做信息修改记录
		if(oldApp2Map.get("c2Natncd2") != null && 
				(newApp2Map.get("c2Natncd2") == null || newApp2Map.get("c2Natncd2").toString().trim().equals(""))
				){
			newApp2Map.remove("c2Natncd2");//附卡国籍
		}
		if(oldApp2Map.get("c2Nation") != null && 
				(newApp2Map.get("c2Nation") == null || newApp2Map.get("c2Nation").toString().trim().equals(""))
				){
			newApp2Map.remove("c2Nation");//附卡国家/地区
		}
		
		Set<Entry<String, Object>> entrySet2 = newApp2Map.entrySet();
		for (Entry<String, Object> entry : entrySet2) {
			ApplyModifyLog applyModify = new ApplyModifyLog();
			String column = entry.getKey();
			Object newValue = entry.getValue();
			Object oldValue = oldApp2Map.get(column);
			
			Date date = new Date();
			if (oldValue == null) {
				oldValue = "";
			}
			if (newValue == null) {
				newValue = "";
			}
			
			if (!oldValue.equals(newValue)) {// 说明有改变 需要记录到 --申请表信息修改日志表
				// 判断此信息是否是关键信息
				if (newApp2Map.containsKey(column)) {
					columnList.add(column);
				}	
				Integer isKey = null;
				if(!"3".equals(newBizInpApplC1.getC1c2Flag())){
					isKey = keyMessageModifyService.queryKeyParamsByProperty(column);

				}
				if (isKey != null) {
					if (isKey.intValue() == 1) {// 是关键字段
						if (newApply2Map.containsKey(column)) {
							newApply2Map.remove(column);
						}
						applyModify.setIsKeyField("1");
						flag++;
					} else {
						applyModify.setIsKeyField("0");
					}
				} else {
					applyModify.setIsKeyField("0");
				}
				applyModify.setAutoId(StringUtil.randomGUIDPlainString());
				applyModify.setAppId(newBizInpApplC1.getAppId());
				if("c2BirthS".equals(column)){
					applyModify.setFieldName("c2Birth");
				}else{
					applyModify.setFieldName(column);
				}
				applyModify.setFieldOldValue(oldValue);
				applyModify.setFieldNewValue(newValue);
				applyModify.setCrtDate(date);
				applyModify.setCrtUser(user);
				applyModify.setLstUpdDate(date);
				applyModify.setCheckFlag("0");
				if("1".equals(oldBiz1.getC1c2Flag()) || "2".equals(oldBiz1.getC1c2Flag())){
					applyModify.setApplyName(oldbizInpApplC2.getC2Cname());
					applyModify.setCretifiType(oldbizInpApplC2.getC2Idtype());
					applyModify.setCretifiId(oldbizInpApplC2.getC2Idnbr());
				}else{
					applyModify.setApplyName(oldBiz1.getC1Cname());
					applyModify.setCretifiType(oldBiz1.getC1Idtype());
					applyModify.setCretifiId(oldBiz1.getC1Idnbr());
				}
				modifyList.add(applyModify);
			}

		}
		try {
			if (modifyList != null && modifyList.size() > 0) {
				// 主卡
				String updateApp1String = JSON.toJSONString(newApp1Map);//newApp1Map为页面上的信息
				BizInpAppC1Spec updateBizInpApplC1 = JSON.parseObject(updateApp1String, BizInpAppC1Spec.class);
				// 20180709 from wjf 添加卡片递送方式保存
				Integer c4Rushflg = updateBizInpApplC1.getC4Rushflg();
				if(c4Rushflg!=null && !"".equals(c4Rushflg)){
					if(c4Rushflg==1){
						updateBizInpApplC1.setC4Cdespmd("POST");
					}else if(c4Rushflg==0){
						updateBizInpApplC1.setC4Cdespmd("COUR");
					}
				}
				// 附卡   2018/09/04 wjf 易达金卡不再查询附属卡申请人资料
				String updateApp2String = JSON.toJSONString(newApply2Map);
				BizInpApplC2 updateBizInpApplC2 = JSON.parseObject(updateApp2String, BizInpApplC2.class);
				// 查看是否是有套卡
				String matchingCardFlag = matchingCard.get("matchingCardFlag");
				List<ApplyModifyLog> modifyListMatchingCard = new ArrayList<ApplyModifyLog>();
				if ("1".equals(matchingCardFlag) || "2".equals(matchingCardFlag)) {
					String appIdTao = newBizInpApplC1.getAppId();
					appIdTao = "1".equals(appIdTao.substring(appIdTao.length()-1,appIdTao.length()))?appIdTao.substring(0, appIdTao.length()-1)+"2":appIdTao.substring(0, appIdTao.length()-1)+"1";
					matchingCard.put("appId", appIdTao);
					BizInpAppC1Spec copyBiz = null;
					if (columnList != null && columnList.size() > 0) {// 主卡有修改
						// 查套卡appId
						List<Map<String, String>> matchingCardAppId = applyService.queryMatchingCardFlagAndYdjFalgByIdNbr(matchingCard);
						if (matchingCardAppId != null && matchingCardAppId.size() > 0) {
							for (Map<String, String> matchingCardl : matchingCardAppId) {
								//复制信息 
								copyBiz = new BizInpAppC1Spec();
								BeanUtils.copyProperties(updateBizInpApplC1, copyBiz);//页面信息
								copyBiz.setAppId(matchingCardl.get("appId"));
								//查看原套卡的信息
								BizInpAppC1Spec copyBizOld = new BizInpAppC1Spec();
								copyBizOld.setAppId(matchingCardl.get("appId"));
								copyBizOld = applyService.get2New(copyBizOld);//数据库信息
								// 除去不需要同步的字段
								// 1.年费方案 2.开卡优享礼 3.银行专用栏位团办号 4.附属卡申请人资料5.轻松周转申请表全部内容
								copyBiz.setAppAcctyp(copyBizOld.getAppAcctyp());
								//copyBiz.setC4Giftn2(copyBizOld.getC4Giftn2());//申请表修改页面已没有这些字段
								//copyBiz.setC4Giftno(copyBizOld.getC4Giftno());
								//copyBiz.setC4Apbtid(copyBizOld.getC4Apbtid());
								copyBiz.setC4Apbatch(copyBizOld.getC4Apbatch());
								//copyBiz.setC4Othcard(copyBizOld.getC4Othcard());
								//copyBiz.setC4Othcrq(copyBizOld.getC4Othcrq());
								copyBiz.setC5Sgndte1(copyBizOld.getC5Sgndte1());
								//copyBiz.setC4Othcred(copyBizOld.getC4Othcred());
								//copyBiz.setC4Vccred(copyBizOld.getC4Vccred());
								//copyBiz.setC4Vcode(copyBizOld.getC4Vcode());
								copyBiz.setAppProd(copyBizOld.getAppProd());
								copyBiz.setC1c2Flag(copyBizOld.getC1c2Flag());
								copyBiz.setC4Vercode(copyBizOld.getC4Vercode());
								List<String> tao = new ArrayList<>();
								tao.add("appAcctyp");
								//tao.add("c4Giftn2");tao.add("c4Giftno");tao.add("c4Apbtid");
								tao.add("c4Apbatch");
								//tao.add("c4Othcard");tao.add("c4Othcrq");
								tao.add("c5Sgndte1");
								//tao.add("c4Othcred");tao.add("c4Vccred");tao.add("c4Vcode");
								tao.add("appProd");
								tao.add("c1c2Flag");tao.add("c4Vercode");
								for (ApplyModifyLog applyModify : modifyList) {
									if (columnList.contains(applyModify.getFieldName()) && !tao.contains(applyModify.getFieldName())) {
										ApplyModifyLog colne = new ApplyModifyLog();
										BeanUtils.copyProperties(applyModify, colne);
										colne.setAppId(matchingCardl.get("appId"));
										colne.setAutoId(StringUtil.randomGUIDPlainString());
										modifyListMatchingCard.add(colne);
									}
								}
							}
						}
					}
					modifyList.addAll(modifyListMatchingCard);
					if("1".equals(matchingCard.get("ydjFlag")) && !"1".equals(matchingCardFlag)){
						applyService.updateBizInfoMatchingCardYdj(updateBizInpApplC1, null);
					}else{
 						applyService.updateBizInfoMatchingCardBzk(updateBizInpApplC1, null);
					}
					if(copyBiz != null){
						if("1".equals(matchingCard.get("ydjFlag"))){ // && !"1".equals(matchingCardFlag)
							applyService.updateBizInfoMatchingCardYdj(copyBiz, null);
						}else{
							applyService.updateBizInfoMatchingCardBzk(copyBiz, null);
						}
					}
				} else {
					if("1".equals(matchingCard.get("ydjFlag")) && !"1".equals(matchingCardFlag)){
						applyService.updateBizInfoYdj(updateBizInpApplC1,null);
					}else{
						applyService.updateBizInfoBzk(updateBizInpApplC1,null);
					}
				}
				applyService.saveApplyModifyLog(modifyList);
				// applyService.updateBizInfo(updateBizInpApplC1);
				if("2".equals(matchingCard.get("ydjFlag")) || "1".equals(matchingCardFlag)){
					applyService.updateApply2Info(updateBizInpApplC2);
				}
			}
			if (flag > 0) {
				// 有关键信息修改
				//查询分件表
				AllotApply allot = applyService.queryApplyAllotByAppId(sappId);
				//添加流程节点
				//根据userCode查询中文名称 qeurUserNameByUserCode
				String opear = applyService.qeurUserNameByUserCode(user);
				ApplyLifeCicle alc = new ApplyLifeCicle();
				alc.setAppId(newBizInpApplC1.getAppId());alc.setUuid(StringUtil.randomGUIDPlainString());
				alc.setCrtDate(new Date());
				alc.setOperateResult("完成");alc.setCrtUser(user);
				alc.setOperater(opear+"-"+user);
				if("02".equals(allot.getCurrNode())){ //征信
					if("0".equals(allot.getDelStatus())){
						alc.setOperateDesc(opear+"-征信未完成队列-修改关键信息-申请件流转到关键信息复核队列!");
						matchingCard.put("DEL_STATUS", "A");
					}
					if("3".equals(allot.getDelStatus())){
						matchingCard.put("DEL_STATUS", "B");
						alc.setOperateDesc(opear+"-征信退回队列-修改关键信息-申请件流转到关键信息复核队列!");
					}
					if("2".equals(allot.getDelStatus())){ //补件队列
						matchingCard.put("DEL_STATUS", "C");
						alc.setOperateDesc(opear+"-征信待补件队列-修改关键信息-申请件流转到关键信息复核队列!");
					}
				}
				if("03".equals(allot.getCurrNode())){ //审批
					if("0".equals(allot.getDelStatus())){
						matchingCard.put("DEL_STATUS", "A");
						alc.setOperateDesc(opear+"-审批未完成队列-修改关键信息-申请件流转到关键信息复核队列!");
					}
					if("3".equals(allot.getDelStatus())){
						matchingCard.put("DEL_STATUS", "B");
						alc.setOperateDesc(opear+"-征信退回队列-修改关键信息-申请件流转到关键信息复核队列!");
					}
					if("2".equals(allot.getDelStatus())){ //补件队列
						matchingCard.put("DEL_STATUS", "C");
						alc.setOperateDesc(opear+"-审批待补件队列-修改关键信息-申请件流转到关键信息复核队列!");
					}
				}
				applyService.insertApplyLifeCicle(alc);
				if("1".equals(oldBiz1.getYdjFlag())&&!"0".equals(matchingCard)){
					String appIdTao = newBizInpApplC1.getAppId();
					appIdTao = "1".equals(appIdTao.substring(appIdTao.length()-1,appIdTao.length()))?appIdTao.substring(0, appIdTao.length()-1)+"2":appIdTao.substring(0, appIdTao.length()-1)+"1";
					ApplyLifeCicle alcTao = new ApplyLifeCicle();
					BeanUtils.copyProperties(alc, alcTao);
					alcTao.setUuid(StringUtil.randomGUIDPlainString());
					alcTao.setAppId(appIdTao);
					applyService.insertApplyLifeCicle(alcTao);
				}
				// 更新状态申请件分配表
				applyService.updateApplyAllotAndInsert(matchingCard);
			}
			//判断在不走关键信息修改的情况下才进行可信重查
			if(kxFlag && flag == 0){
				
				//查询分件表
				AllotApply allot = applyService.queryApplyAllotByAppId(sappId);
				//添加流程节点
				//根据userCode查询中文名称 qeurUserNameByUserCode
				String opear = applyService.qeurUserNameByUserCode(user);
				ApplyLifeCicle alc = new ApplyLifeCicle();
				alc.setAppId(newBizInpApplC1.getAppId());
				alc.setUuid(StringUtil.randomGUIDPlainString());
				alc.setCrtDate(new Date());
				alc.setOperateResult("完成");alc.setCrtUser(user);
				alc.setOperater(opear+"-"+user);
				if("02".equals(allot.getCurrNode())){ //征信
					if("0".equals(allot.getDelStatus())){
						alc.setOperateDesc(opear+"-征信未完成队列-修改可信身份信息-申请件重新查询可信身份信息!");
					}
					if("3".equals(allot.getDelStatus())){
						alc.setOperateDesc(opear+"-征信退回队列-修改可信身份信息-申请件重新查询可信身份信息!");
					}
					if("2".equals(allot.getDelStatus())){ //补件队列
						alc.setOperateDesc(opear+"-征信待补件队列-修改可信身份信息-申请件重新查询可信身份信息!");
					}
				}
				if("03".equals(allot.getCurrNode())){ //审批
					if("0".equals(allot.getDelStatus())){
						alc.setOperateDesc(opear+"-审批未完成队列-修改可信身份信息-申请件重新查询可信身份信息!");
					}
					if("3".equals(allot.getDelStatus())){
						alc.setOperateDesc(opear+"-征信退回队列-修改可信身份信息-申请件重新查询可信身份信息!");
					}
					if("2".equals(allot.getDelStatus())){ //补件队列
						alc.setOperateDesc(opear+"-审批待补件队列-修改可信身份信息-申请件重新查询可信身份信息!");
					}
				}
				keyMessageModifyService.saveKxSFRZPass(newBizInpApplC1.getAppId(), alc);
			}
			//校验是否发起可信身份证认证
			ctx.setData("isSuccess", true);
		} catch (CoreException e) {

			ctx.setData("isSuccess", false);

			ctx.setData("exMsg", e.getMessage());

			throw e;
		}
	}

	/**
	 * 捞件信息列表查询
	 * 
	 * @param ctx
	 */
	public void bizInpApplDragList(Context ctx) throws CoreException {
		Map<String, Object> map = ctx.getDataMap();
		String appId = (String) map.get("appId");
		String upperCase = appId.toUpperCase();
		map.put("appId", upperCase);
		int curNum = 0;
		int curPage = Integer
				.parseInt(String.valueOf(ctx.getDataMap().get("page") == null ? 0 : ctx.getDataMap().get("page")));
		int pageNum = Integer
				.parseInt(String.valueOf(ctx.getDataMap().get("rows") == null ? 0 : ctx.getDataMap().get("rows")));

		if (curPage > 1) {
			curNum = (curPage - 1) * pageNum;
		}

		int count = 0;
		try {
			count = applyService.queryCount(map);
		} catch (CoreException e) {
			e.printStackTrace();
		}
		List<Map<Object, Object>> list = new ArrayList<>();
		if (count > 0) {
			try {
				list = applyService.queryList(map, curNum, pageNum);
			} catch (CoreException e) {
				e.printStackTrace();
			}
		}
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("total", count);
		dataMap.put("rows", list);
		ctx.setDataMap(dataMap);

	}

	/**
	 * 捞件页面信息展示
	 * 
	 * @param ctx
	 * @throws CoreException
	 */
	public void dragApplyInfoView(Context ctx) throws CoreException {
		Map<String, Object> map = ctx.getDataMap();
		String appId = (String) map.get("appId");
		Map dragAppMap = applyService.queryDragAqqlyById(appId);
		TIMESTAMP timestmp = (TIMESTAMP) dragAppMap.get("operatTime");
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(timestmp != null){
			try {
				java.sql.Date dateValue = timestmp.dateValue();
				long time = dateValue.getTime();
				Date date = new Date(time);
				String parse = sf.format(date);
				dragAppMap.put("operatTime", parse);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		List<ApplyLifeCicle> applyLifeList = applyService.queryApplyLifeList(appId);
		/*if(applyLifeList != null && applyLifeList.size()>0){
			for (ApplyLifeCicle applyLifeCicle : applyLifeList) {
				Date crtDate = applyLifeCicle.getCrtDate();
				String format = sf.format(crtDate);
				try {
					applyLifeCicle.setCrtDate(sf.parse(format));
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
		}*/
		String dragJsonString = JSON.toJSONString(dragAppMap);
		String applyJsonString = JSON.toJSONString(applyLifeList);
		ctx.setData("dragJsonString", dragJsonString);
		ctx.setData("applyJsonString", applyJsonString);
	}

	/**
	 * 捞件
	 * 
	 * @param ctx
	 */
	public void dragApply(Context ctx) {
		Map<String, Object> map = ctx.getDataMap();
		//String appId = (String) map.get("appId");
		List<Map<String,Object>> listAppIdMap=(List<Map<String,Object>>)map.get("listAppId");
		List<String> appList = new ArrayList<>();
		List<String> taoList = new ArrayList<>();
		List<String> subappList = new ArrayList<>();
		for (int i = 0; i < listAppIdMap.size(); i++) {
			Map<String, Object> map2 = listAppIdMap.get(i);
			String appId = (String) map2.get("appId");
			String substring = appId.substring(0, appId.length()-1);
			if(!subappList.contains(substring)){//无套卡
				subappList.add(substring);
				appList.add(appId);
			}else{//套卡
				String lastC = appId.substring(appId.length()-1,appId.length());
				if("2".equals(lastC)){
					Iterator<String> iterator = appList.iterator();
					while(iterator.hasNext()){
						String next = iterator.next();
						if(next.equals(substring+"1")){
							iterator.remove();
						}
					}
				}else if("1".equals(lastC)){
					Iterator<String> iterator = appList.iterator();
					while(iterator.hasNext()){
						String next = iterator.next();
						if(next.equals(substring+"2")){
							iterator.remove();
						}
					}
				}
				Map<String, String> matchingCardFlagMap = applyService.queryMatchingCardFlagAndYdjFalg(appId);
				String matchingCardFlag = matchingCardFlagMap.get("matchingCardFlag");
				if("0".equals(matchingCardFlag)){
					taoList.add(appId);
				}else if("2".equals(matchingCardFlag)){
					taoList.add(appId);
				}else{
					taoList.add("1".equals(appId.substring(appId.length()-1,appId.length()))?appId.substring(0, appId.length()-1)+"2":appId.substring(0, appId.length()-1)+"1");
				}
//				taoList.add(substring+2);
			}
		}
		String userCode = (String) map.get("userCode");
		Map<String, String> paroperMapP = new HashMap<>();
		Map<String, String> paroperMap = CommonProperties.getParoperMap();
		paroperMapP.putAll(paroperMap);
		paroperMapP.put("userCode", userCode);
		try {
			//单独操作
			if(appList != null && appList.size() >0){
				for (int j = 0; j < appList.size(); j++) {
					applyService.doDragApply(appList.get(j),paroperMapP);
				}
			}
			//ydj 一起操作  
			if(taoList != null && taoList.size() >0){
				paroperMapP.put("laojianFlag", "00");
				for (int j = 0; j < taoList.size(); j++) {
					applyService.doDragApply(taoList.get(j),paroperMapP);
				}
			}
			ctx.setData("isSuccess", true);
		} catch (CoreException e) {
			ctx.setData("isSuccess", false);
			ctx.setData("exMsg", e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			ctx.setData("isSuccess", false);
			ctx.setData("exMsg", e.getMessage());
			e.printStackTrace();
		}
	}
}
