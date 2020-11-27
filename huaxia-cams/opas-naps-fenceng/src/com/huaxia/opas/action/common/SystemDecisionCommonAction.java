package com.huaxia.opas.action.common;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.huateng.neofp.core.Action;
import com.huateng.neofp.core.Context;
import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.allot.AllotApplyAllot;
import com.huaxia.opas.domain.allot.AllotApplyAllotHis;
import com.huaxia.opas.domain.allot.YsPatchboltInfo;
import com.huaxia.opas.domain.allot.YsTelcheckAddnote;
import com.huaxia.opas.domain.archive.SuppArchive;
import com.huaxia.opas.domain.decision.YdjSysresultInfo;
import com.huaxia.opas.domain.input.BizInpApplC1;
import com.huaxia.opas.domain.input.FicoSdBlaze;
import com.huaxia.opas.domain.input.FicoYdjBlaze;
import com.huaxia.opas.domain.input.Union3118;
import com.huaxia.opas.domain.sysparm.InnerRiskInfo;
import com.huaxia.opas.domain.sysparm.Patchbolt;
import com.huaxia.opas.domain.thirdparty.OpasConfQuestion;
import com.huaxia.opas.service.allot.YsTelcheckAddnoteService;
import com.huaxia.opas.service.credit.PatchboltService;
import com.huaxia.opas.service.decision.SysApprovalCommonService;
import com.huaxia.opas.service.decision.SysDecisionCommonService;
import com.huaxia.opas.service.decision.SysDecisionService;
import com.huaxia.opas.service.decision.SysDecisionYdjService;
import com.huaxia.opas.util.StringUtil;

import net.sf.json.JSONObject;

/**
 * 
 * @author xuzhiguo 系统决策结果tab页的展示 2017-3-7 14:59:27
 *  update by liuzhihui  2017-4-5 10:35:49
 */

public class SystemDecisionCommonAction implements Action {

	private static Logger logger = LoggerFactory
			.getLogger(SystemDecisionCommonAction.class);

	@Resource(name = "sysDecisionYdjService")
	private SysDecisionYdjService sysDecisionYdjService;
	
	@Resource(name = "sysDecisionCommonService")
	private SysDecisionCommonService sysDecisionCommonService;

	@Resource(name = "sysDecisionService")
	private SysDecisionService sysDecisionService;
	@Resource(name = "sysApprovalCommonService")
	private SysApprovalCommonService sysApprovalCommonService;
	@Resource(name="ysTelcheckAddnoteService")
	private YsTelcheckAddnoteService ysTelcheckAddnoteService;
	@Resource(name = "patchboltService")
	private PatchboltService patchboltService;
	/**
	 * 风险名单检测
	 */
	@SuppressWarnings("rawtypes")
	public void queryRisklist(Context ctx) {
		try {
			// step1:获取名单表中主键
			String autoId = StringUtils.trimToEmpty((String) ctx.getDataMap()
					.get("autoId"));
			String flag = StringUtils.trimToEmpty((String) ctx.getDataMap()
					.get("flag"));
			Object object = sysDecisionCommonService
					.queryRisklist(autoId, flag);
			if (object == null) {
				return;
			}
			List list = new ArrayList();
			list.add(object);
			Map<String, Object> dataMap = new HashMap<String, Object>();
			dataMap.put("total", "1");
			dataMap.put("rows", list);
			ctx.setDataMap(dataMap);
			// ctx.setData("object", object);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 内部数据检查 -公共代码
	 * 
	 * @author xuzhiguo
	 * update by liuzhihui 
	 */
	public void inDataChecK(Context ctx) {
		try {
			// step1:获取申请编号
			String appId = StringUtils.trimToEmpty((String) ctx.getDataMap()
					.get("appId"));
			String flag = StringUtils.trimToEmpty((String) ctx.getDataMap()
					.get("flag"));
			String autoId = StringUtils.trimToEmpty((String) ctx.getDataMap()
					.get("autoId"));
			// step2:根据申请编号查询内部数据监测的公共内容
			Map map = sysDecisionYdjService.queryInDataChecK(appId);
			// step3:获取关联上的①②④-⑦中具体的特别字段的信息
			Map specilMap = specialResult(appId, flag, map, autoId);
			// step4:将获取的数据传到前台
			ctx.setData("resData", map);
			ctx.setData("specilMap", specilMap);
			// ctx.setData("specilMap", specilMap);
		} catch (Exception e) {
			e.printStackTrace();
			ctx.setData("msg", "保存失败");
		}
	}

	/**
	 * 内部数据检查 -列表显示部分
	 * 
	 * @author hang
	 *  
	 */
	public void inDataChecKList(Context ctx) {
		Map map = ctx.getDataMap();
		
		try {
			int curNum = 0;
			int curPage = Integer
					.parseInt(String.valueOf(ctx.getDataMap().get("page") == null ? 0 : ctx.getDataMap().get("page")));
			int pageNum = Integer
					.parseInt(String.valueOf(ctx.getDataMap().get("rows") == null ? 0 : ctx.getDataMap().get("rows")));
			if (curPage > 1) {
				curNum = (curPage - 1) * pageNum;
			}
			// step1:获取申请编号
			String appIds = (String) map.get("appId");
			String appIds_str[] = appIds.split("[|]");
			Map params = new HashMap();
			params.put("appIds", appIds_str);
			List list = new ArrayList();
			int count = 0;
			try {
				count = sysDecisionYdjService.countInDataChecK(params);
			} catch (Exception e) {
				
			} if(count>0){
				list = sysDecisionYdjService.queryInDataChecK(params,curNum,pageNum);
				//list = sysDecisionYdjService.queryInDataChecK(params);
			}

			Map<String, Object> appMap=new HashMap<String, Object>();
			appMap.put("total", count);
			appMap.put("rows", list);
			ctx.setData("isSuccess", true);
			// step4:将获取的数据传到前
			ctx.setDataMap(appMap);
		} catch (Exception e) {
			e.printStackTrace();
			ctx.setData("msg", "保存失败");
		}
	}
	/**
	 * 获取关联上的①②④-⑦中具体的特别字段的信息,是inDataChecK()方法的子方法
	 * 
	 * @param appId
	 * @param flag
	 * @throws Exception
	 */
	@SuppressWarnings({ "unchecked", "deprecation" })
	public Map specialResult(String appId, String flag, Map paramMap,
			String autoId) throws Exception {
		Map map = new HashMap();
		Map<String, String> resMap = sysDecisionYdjService.queryTeam(appId);
		if (resMap == null) {
			resMap = new HashMap();
		}
		if("graylist".equals(flag)){//灰名单（个人）
			
		}else if ("certifinoVailud".equals(flag)) {// 证件有效期
			Object object = sysApprovalCommonService.querySystemApprovalResult(appId, (String)paramMap.get("YDJ_FLAG"));
			// 获得决策时间
			Date decisionDate = null;
			if("1".equals((String)paramMap.get("YDJ_FLAG"))){
				decisionDate = ((FicoYdjBlaze)object).getCrtTime();
			}else{
				decisionDate = ((FicoSdBlaze)object).getCrtTime();
			}
			// 证件有效期与身份证号
			Date C5_IDTE1 = (Date) paramMap.get("C5_IDTE1");// 证件有效期
			Date C1_BIRTH = (Date) paramMap.get("C1_BIRTH");// 出身日期
			/*
			 * 证件有效期截止时间—决策时间大于等于X天（X参数可配）。
			 * 显示内容：长期、未过期、已过期，其中已过期需高亮显示。申请人年龄大于等于46周岁且证件号码有效期为长期的需高亮显示
			 */
			// 现在参数配置为x=100天
			int x = 100;
			Date newdecisionDate = decisionDate;
			newdecisionDate.setDate(newdecisionDate.getDate() + x);
			if (C5_IDTE1.after(newdecisionDate)) {
				C1_BIRTH.setYear(C1_BIRTH.getYear() + 46);
				if (C1_BIRTH.before(new Date())) {
					map.put("certifinoVailud", "1");// 长期高亮，大于46岁
				} else {
					map.put("certifinoVailud", "2");// 长期
				}
			} else if (C5_IDTE1.after(decisionDate)) {
				map.put("certifinoVailud", "3");// 未过期
			} else {
				map.put("certifinoVailud", "4");// 已过期
			}
		} else if ("applyinfoBuscheck".equals(flag)) {
			// map.put("data_applyinfoBuscheck", "不符合正常逻辑");
		} else if ("ishaveInriskInfo".equals(flag)) {// 是否命中内部风险信息
			if (autoId != null) {
				InnerRiskInfo info = sysDecisionCommonService.queryInnerRiskInfo(autoId);
				map.put("data_COMPANY_NAME", info.getCompanyName());
				map.put("data_COMPANY_TEL", info.getCompanyTel());
				map.put("data_SPREAD_ORG", info.getSpreadOrg());
				map.put("data_SPREAD_PER", info.getSpreadPer());
				map.put("data_HOUSEHOLD", info.getHouseHold());
				map.put("data_SPREAD_NO", info.getSpreadNo());
				map.put("data_CERTIFI_NO", info.getCertifiNo());
			}
		}
		return map;
	}

	/**
	 * 问题库
	 */
	public void queryConfQuestion(Context ctx){
		try {
			String appId = (String) ctx.getData("appId");
			String status = (String) ctx.getData("status");//1 是标准卡 2是易达金
			//征信策略输出结果
			String  result = sysDecisionCommonService.selectReultByAppId(appId,status);
			String questionLevel =  "1','2";
			if("".equals(result)){
				questionLevel = "'/1'/,'/2'/";
			}
			List<String> ids=new ArrayList<String>();
			ids.add("1");
			ids.add("2");
			//获得符合条件的问题的编号
			String questionNos = sysDecisionCommonService.queryConfQuestionCount(ids);
			//将问题编号放入数组中 取下标随机
			String no[] =null;
			if(null!=questionNos&&!"".equals(questionNos)){
				no=questionNos.split(",");
				if(no.length<3){
					throw new Exception("问题库数据小于三个");
				}
			}else{
				throw new Exception("问题库没有数据");
			}
			//选取三个不同的随机值
			Set<String> set = new HashSet<String>();
			Random ran = new Random();
			while(set.size()<3){
				set.add(no[ran.nextInt(no.length)]);
			}
			List<String> questionNo=new ArrayList<String>();
			for(String nos:set){
				questionNo.add(nos);
			}
			//获取n个随机问题
			List<OpasConfQuestion> list = sysDecisionCommonService.queryConfQuestion(questionNo);
			//返回数据
			Map<String, Object> dataMap = new HashMap<String, Object>();
			dataMap.put("total", 3);
			dataMap.put("rows", list);
			ctx.setDataMap(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 问题库   by 安东
	 * 2017-5-9
	 * */
	public void queryQuestion(Context ctx){
		String appId = (String) ctx.getData("appId");
		String ydjFlag = (String) ctx.getData("ydjFlag");//2 是标准卡 1是易达金
		String c1Idnbr = (String) ctx.getData("c1Idnbr");//身份证号码
		String existCardFlag = (String) ctx.getData("existCardFlag");//判断是否是二次申请卡
		Map<Object,String> map = new HashMap<>();
		map.put("appId", appId);
		map.put("ydjFlag", ydjFlag);
		map.put("c1Idnbr", c1Idnbr);
		map.put("existCardFlag", existCardFlag);
		//获取n个随机问题
		List<OpasConfQuestion> list = sysDecisionCommonService.questionLibrary(map);
		//返回数据
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("total", 3);
		dataMap.put("rows", list);
		ctx.setDataMap(dataMap);
	}
	
	/**
	 * 已持卡客户信息  更新版    by 刘志辉
	 * 2017-4-3 13:38:29
	 */
	public void queryHaveCardCustInfoByAppID(Context context) throws CoreException {
		String appId = (String) context.getData("appId");
		Assert.notNull(appId, "请求申请件编号为空!");
		String flag = (String) context.getData("flag");
		String applyCard=(String) context.getData("applyCard");//申请卡产品编号
		String c1c2Flag=(String) context.getData("c1c2Flag");//申请卡主附卡标识  1主附同申  2 单办附卡  3单办主卡
		if (logger.isDebugEnabled()) {
			logger.debug("[" + appId + "]已持卡客户基本信息");
		}
		Map<String,String> paramMap = new HashMap<String,String>();
		paramMap.put("appId", appId);
		/*if("2".equals(flag)){//10-标准卡，5-易达金
			paramMap.put("cate", "10");
		}else{
			paramMap.put("cate", "5");
		}*/
		Map<String, String> summaryInfo = sysDecisionService.selectHaveCardInfoByAppIdByMap(paramMap);
		if (summaryInfo != null) {
			JSONObject jsonObject = JSONObject.fromObject(summaryInfo);
			context.setData("success", true);
			context.setData("summaryInfo", jsonObject.toString());
		}
		//Map<String, String> cardInfo = sysDecisionService.selectCardInfo(appId);
		Map<String, String> cardInfo=new HashMap<String,String>();
		//wdb 二期首先排查是否新入库3118表数据  如果是新入库3118表详细查询全部  筛选需要的不重复卡,老数据走原来逻辑。
		String cardstat="",product="",maflag="",sortflag="",newHaveFlag="";
		int credLmt,a=0,num=0;
		paramMap.put("applyCard", applyCard);
		newHaveFlag=sysDecisionService.selectNewHaveFlag(paramMap);
		if("0".equals(newHaveFlag)){//旧数据 以前查询方式
			cardInfo = sysDecisionService.selectCardInfo(appId);	
			for(int i=1;i<6;i++){//循环转成中文
				maflag=cardInfo.get("maflag"+i);
				if("1".equals(maflag)){//1 主卡  2-9 附卡 与申请表主附卡标识不相同
					cardInfo.put("maflag"+(i), "主卡");
				}else if(maflag!=null&&!"".equals(maflag)&&!"1".equals(maflag)){
					cardInfo.put("maflag"+(i), "附卡");
				}
				product=cardInfo.get("product"+i);
				if("".equals(product)||product==null){//不存在卡产品  将额度置空
					cardInfo.put("credLmt"+(i), "");
				}else{
					cardInfo.put("cardstat"+(i), cardInfo.get("cardstat"+i)==null?"正常":cardInfo.get("cardstat"+i));
				}
			}
		}else{//新数据新查询方式
			if("2".equals(c1c2Flag)){//单办附卡
				Union3118 union=sysDecisionService.selectfkByAppId(paramMap);
				if(union!=null){
					product=union.getProduct1()==null?"":union.getProduct1();
					cardstat=union.getCardstat1()==null?"正常":union.getCardstat1();
					cardInfo.put("product1", product);
					cardInfo.put("cardstat1", cardstat);
					//cardInfo.put("haveflag1", String.valueOf(union.getNum()));
				}
				List<Union3118> countList=sysDecisionService.select3070NumByAppId(paramMap);
				if(countList.size()>0){
					for(int j=0;j<countList.size();j++){
						Union3118 union3118=countList.get(j);
						sortflag=union3118.getSortFlag()==null?"":union3118.getSortFlag();
						num=union3118.getNum()==null?0:union3118.getNum();
						if("a".equals(sortflag)){
							cardInfo.put("fkNum", String.valueOf(num));
						}else if("b".equals(sortflag)){
							cardInfo.put("fkNum2", String.valueOf(num));
						}
						if(num!=0){
							cardInfo.put("haveflag1", "是");
						}else{
							cardInfo.put("haveflag1", "否");
						}
					}
				}else{
					cardInfo.put("haveflag1", "否");
				}
			}else{//主附同申 、单办主卡
				List<Union3118> unionList=sysDecisionService.select3118ByAppId(paramMap);
				if(unionList.size()>0){
					for(int i=0;i<unionList.size();i++){
						Union3118 union=unionList.get(i);
						product=union.getProduct1()==null?"":union.getProduct1();
						cardstat=union.getCardstat1()==null?"正常":union.getCardstat1();
						maflag=union.getMaflag1()==null?"":union.getMaflag1();
						credLmt=union.getCredLmt1()==null?0:union.getCredLmt1();
						if(a<10&&!cardInfo.containsValue(product)){
							cardInfo.put("product"+(a+1), product);
							cardInfo.put("cardstat"+(a+1), cardstat);
							if("1".equals(maflag)){//1 主卡  2-9 附卡 与申请表主附卡标识不相同
								cardInfo.put("maflag"+(a+1), "主卡");
							}else if(maflag!=null&&!"".equals(maflag)&&!"1".equals(maflag)){
								cardInfo.put("maflag"+(a+1), "附卡");
							}
							cardInfo.put("credLmt"+(a+1), String.valueOf(credLmt));
							a++;
						}else if(a>=10){
							break;
						}
					}
					List<Union3118> countList=sysDecisionService.select3118NumByAppId(appId);
					for(int j=0;j<countList.size();j++){
						Union3118 union=countList.get(j);
						sortflag=union.getSortFlag()==null?"":union.getSortFlag();
						num=union.getNum()==null?0:union.getNum();
						if("a".equals(sortflag)){
							cardInfo.put("ydjTCardNum", String.valueOf(num));
						}else if("b".equals(sortflag)){
							cardInfo.put("ydjCardNum", String.valueOf(num));
						}else if("c".equals(sortflag)){
							cardInfo.put("bzkTCardNum", String.valueOf(num));
						}else if("d".equals(sortflag)){
							cardInfo.put("bzkCardNum", String.valueOf(num));
						}
					}
				}
			}
		}
		if (cardInfo != null) {
			JSONObject jsonObject = JSONObject.fromObject(cardInfo);
			context.setData("success", true);
			context.setData("detail", jsonObject.toString());
			return;
		}
		context.setData("success", false);
	}
	public void queryCardInfoByAppID(Context context) throws CoreException {
		String appId = (String) context.getData("appId");
		Assert.notNull(appId, "请求申请件编号为空!");

		if (logger.isDebugEnabled()) {
			logger.debug("查询申请件[" + appId + "]已持卡客户 卡片信息");
		}
		Map<String, String> cardInfo = sysDecisionService.selectCardInfo(appId);
		if (cardInfo != null) {
				JSONObject jsonObject = JSONObject.fromObject(cardInfo);
				context.setData("success", true);
				context.setData("applyPoliceDetail", jsonObject.toString());
				return;
			}

			context.setData("success", false);
	}
	public void queryRefuseCodeList(Context ctx){
		Map map = ctx.getDataMap();
		try {
			// step1:获取申请编号
			String appIds = (String) map.get("appId");
			String appIds_str[] = appIds.split("[|]");
			Map params = new HashMap();
			params.put("appIds", appIds_str);
			String refuseCodeStr = null;
			int count = 0;
			count = sysDecisionYdjService.countInDataChecK(params);
			if(count>0){
				refuseCodeStr = sysDecisionYdjService.queryRefuseCodeList(params);
			}
			Map<String, Object> appMap=new HashMap<String, Object>();
			appMap.put("rows", refuseCodeStr);
			ctx.setData("isSuccess", true);
			// step4:将获取的数据传到前
			ctx.setDataMap(appMap);
		} catch (Exception e) {
			logger.info("是否关注客户灰名单查询拒绝（核批）原因代码"+e.getMessage());;
			ctx.setData("msg", "保存失败");
		}
	}
	/*查询预审信息*/
	public void queryYsAddnote(Context ctx){
		int count=0;
		List<YsTelcheckAddnote> list =null;
		List<YsTelcheckAddnote> allList=new LinkedList<YsTelcheckAddnote>();
		try {
			Map<String,Object> map=ctx.getDataMap();
			count=ysTelcheckAddnoteService.selectCount(map);
			if(count>0){
				map.put("approveStr", "approveStr");//预审结果不能为空
				YsTelcheckAddnote ysResultInfo = ysTelcheckAddnoteService.selectYsResultInfo(map);
				if(ysResultInfo!=null){//预审结论情况
					allList.add(ysResultInfo);
				}
				list=ysTelcheckAddnoteService.selectByExample(map);
				if(list!=null){
					allList.addAll(list);
				}
			}
			Map<String, Object> dataMap = new HashMap<String, Object>();
			dataMap.put("total", count);
			dataMap.put("rows", allList);
			ctx.setDataMap(dataMap);
		} catch (Exception e) {
			logger.info("queryYsAddnote-查询预审信息报错"+e.getMessage());
			ctx.setData("msg", "查询失败");
		}
	}
	public void queryYsComments(Context ctx) throws Exception {
		String appId =null,ysPatchboltStr="1";
		try {
			Map<String, Object> dataMap = ctx.getDataMap();
			appId = (String)dataMap.get("appId");
			YsPatchboltInfo ysPatchboltInfo=ysTelcheckAddnoteService.selectYsPatchbolt(dataMap);
			BizInpApplC1 bizInfo = patchboltService.querybizInpApplList(appId);
			YsTelcheckAddnote ysResultInfo = ysTelcheckAddnoteService.selectYsResultInfo(dataMap);
			//补件信息
			String patchboltJson="{\"\":\"\"}";
			if(ysPatchboltInfo!=null){//存在预审补件信息不隐藏补件提示框
				patchboltJson=JSON.toJSONStringWithDateFormat(ysPatchboltInfo, "yyyy-MM-dd", SerializerFeature.WriteDateUseDateFormat);
			}else{//预审补件信息存在隐藏补件提示框
				ysPatchboltStr="2";
			}
			String 	bizInfoJson="{\"\":\"\"}";
			if(bizInfo!=null){
				bizInfoJson=JSON.toJSONString(bizInfo);
			}
			String jsonpatchbolt=patchboltJson.substring(0, patchboltJson.length()-1)+","
					+bizInfoJson.substring(1, bizInfoJson.length());
			//资料审查信息
			String jsonysResultInfo="{\"\":\"\"}";
			if(ysResultInfo!=null){
				jsonysResultInfo=JSON.toJSONString(ysResultInfo);
			}
			ctx.setData("ysPatchboltStr", ysPatchboltStr);
			ctx.setData("jsonpatchbolt", jsonpatchbolt);
			ctx.setData("jsonysResultInfo", jsonysResultInfo);
		} catch (Exception e) {
			logger.info(appId+"-queryYsComments-查询预审信息报错"+e.getMessage());
			ctx.setData("msg", "查询失败");
		}
		
	}
}
