package com.huaxia.opas.action.report;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import com.huateng.neofp.core.Action;
import com.huateng.neofp.core.Context;
import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.allot.AllotApply;
import com.huaxia.opas.domain.apply.ApplyLifeCicle;
import com.huaxia.opas.domain.input.BizInpAppC1Spec;
import com.huaxia.opas.domain.report.KeyMessageModify;
import com.huaxia.opas.domain.sysparm.SubReport;
import com.huaxia.opas.service.apply.ApplyInfoService;
import com.huaxia.opas.service.report.KeyMessageModifyService;
import com.huaxia.opas.service.sysparm.SubReportService;
import com.huaxia.opas.util.CommonProperties;
import com.huaxia.opas.util.StringUtil;

import net.sf.json.JSONObject;
import oracle.sql.TIMESTAMP;

/**
 * 关键信息修改表
 * 
 * @author gaohui
 *
 */
public class KeyMessageModifyAction implements Action {
	private static final Logger logger = LoggerFactory.getLogger(KeyMessageModifyAction.class);
	// private static Logger logger =
	// LoggerFactory.getLogger(FishingAction.class);
	@Resource(name = "keyMessageModifyService")
	private KeyMessageModifyService keyMessageModifyService;
	@Resource(name = "applyInfoService")
	private ApplyInfoService applyService;
	@Resource(name = "subReportService")
	private SubReportService subReportService;
//	@Resource(name = "provideService")
//	private ProvideService provideService;

	public KeyMessageModifyService getKeyMessageModifyService() {
		return keyMessageModifyService;
	}

	public void setKeyMessageModifyService(KeyMessageModifyService keyMessageModifyService) {
		this.keyMessageModifyService = keyMessageModifyService;
	}

	/**
	 * @Title:showKeyMessageModifyData
	 * @Description:显示关键信息修改表信息表的数据
	 * @param context
	 * @author: gaohui
	 * @Date:2017年3月14日下午1:52:58
	 */
	public void showKeyMessageModifyData(Context context) {
		Map<String, Object> paraMap = new HashMap<String, Object>();
		int pageNum = 0;// 当前页
		JSONObject jsonObject = JSONObject.fromObject(context.getDataMap());
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		Date beginDate = new Date();
		Date endDate = new Date();
		if (jsonObject.containsKey("beginDate")) {// 用于放入map中的时间字段的控制
			try {
				if (!jsonObject.getString("beginDate").toString().equals("")) {
					beginDate = fmt.parse(jsonObject.getString("beginDate").toString());
					paraMap.put("beginDate", beginDate);
				} else {
					paraMap.put("beginDate", "");
				}
				if (!jsonObject.getString("endDate").toString().equals("")) {
					endDate = fmt.parse(jsonObject.getString("endDate").toString());
					paraMap.put("endDate", endDate);
				} else {
					paraMap.put("endDate", "");
				}
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			paraMap.put("ydjFlag", jsonObject.getString("ydjFlag").toString());// 易达金标识
			int curNum = Integer.parseInt(
					String.valueOf(context.getDataMap().get("page") == null ? 0 : context.getDataMap().get("page")));
			// 每页显示的条数
			int pageRows = Integer.parseInt(
					String.valueOf(context.getDataMap().get("rows") == null ? 0 : context.getDataMap().get("rows")));
			if (curNum > 1) {
				pageNum = (curNum - 1) * pageRows;
			}
			try {
				context.setDataMap(keyMessageModifyService.findListKeyMessageModifyByDate(paraMap, pageNum, pageRows));
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {// 页面第一次加载"beginDate"等键不存在，不进行查询
			Map<String, Object> paraNullMap = new HashMap<String, Object>();
			paraNullMap.put("rows", "");
			context.setDataMap(paraNullMap);
		}
	}

	/**
	 * 关键信息修改复核--查询所有
	 * 
	 * @param ctx
	 * @throws SQLException
	 */
	public void queryAllKeyMessageModify(Context ctx) throws SQLException {
		Map<String, Object> dataMap = ctx.getDataMap();
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
			count = keyMessageModifyService.queryCount();
		} catch (CoreException e) {
			e.printStackTrace();
		}
		List<Map<Object, Object>> listAppId = new ArrayList<>();
		List<Map<Object, Object>> listTemp = new ArrayList<>();
		List<Map<Object, Object>> list = new ArrayList<>();
		if (count > 0) {
			try {
				listAppId = keyMessageModifyService.queryList(dataMap, curNum, pageNum);
				list  = keyMessageModifyService.queryDetialMessage(listAppId);
				if (list != null && list.size() > 0) {
					for (int i = 0; i < list.size(); i++) {
						if (i > 0) {
							int addFlag = 0;
							for (int j = 0; j < listTemp.size(); j++) {
								if (listTemp.get(j).get("appId").equals(list.get(i).get("appId"))) {
									listTemp.get(j).put("fieldName",
											listTemp.get(j).get("fieldName") + "<br />" + list.get(i).get("fieldName"));
									addFlag++;
								}
							}
							if (addFlag == 0) {
								listTemp.add(list.get(i));
							}
						} else {
							listTemp.add(list.get(i));
						}
					}
				}

			} catch (CoreException e) {
				e.printStackTrace();
			}
		}
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (list != null) {
			for (Map<Object, Object> map : listTemp) {
				TIMESTAMP timestamp = (TIMESTAMP) map.get("crtDate");
				java.sql.Date date = timestamp.dateValue();
				// String string = date.toString();
				String format = sf.format(date);
				map.put("crtDate", format);
				String crtUser = (String) map.get("crtUser");
				if (crtUser != null) {
					crtUser = keyMessageModifyService.qeurUserNameByUserCode(crtUser);
				}
				map.put("crtUser", crtUser);
			}
		}
		String username = (String) dataMap.get("userId");
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("total", count);
		data.put("rows", listTemp);
		ctx.setDataMap(data);
		ctx.setData("username", username);
	}

	/**
	 * 查询详情
	 * 
	 * @param ctx
	 */
	public void queryInfoKeyMessage(Context ctx) {
		Map<String, Object> map = ctx.getDataMap();
		String appId = (String) map.get("appId");
		List<Map<Object, Object>> list = null;
		Map<Object, Object> keyMesage = null;
		try {
			list = keyMessageModifyService.findListKeyMessageByAppId(appId);
			keyMesage = keyMessageModifyService.queryKeyMessageInBizC1AndBizC2(appId);
		} catch (CoreException e) {
			e.printStackTrace();
		}
		String keyMessageJson = JSON.toJSONString(list);
		String BizC1AndBizC2 = JSON.toJSONString(keyMesage);
		ctx.setData("jsonString", keyMessageJson);
		ctx.setData("bizc1andbizc2", BizC1AndBizC2);
	}

	/**
	 * 审核通过 -- 1.保存申请件信息 2.保存关键信息修改表
	 * 
	 * @throws SQLException
	 */
	public void keyMessageRecheckPass(Context ctx) throws CoreException {
		Map<String, Object> dataMap = ctx.getDataMap();
		String jsonString = JSON.toJSONString(dataMap);
		String appId = (String) dataMap.get("appId");
		String userCode = (String) dataMap.get("userCode");
		Map<String, String> ret = new HashMap<>();
		Map<String, String> paroperMap = CommonProperties.getParoperMap();
		ret.put("userId", userCode);
		ret.putAll(paroperMap);
		// 查询分件表
		AllotApply allot = applyService.queryApplyAllotByAppId(appId);
		String ydjFlag = allot.getYdjFlag();
		String matchingCardFlag = allot.getMatchingCardFlag();
//		appId = "2".equals(ydjFlag)?appId.substring(0,appId.length()-1)+"1":appId.substring(0,appId.length()-1)+"2";
		// 添加流程节点
		// 根据userCode查询中文名称 qeurUserNameByUserCode
		String opear = applyService.qeurUserNameByUserCode(userCode);
		ret.put("opear", opear);
		ApplyLifeCicle alc = new ApplyLifeCicle();
		alc.setAppId(appId);
		alc.setUuid(StringUtil.randomGUIDPlainString());
		alc.setCrtDate(new Date());
		alc.setOperateResult("完成");
		alc.setCrtUser(userCode);
		alc.setOperater(opear + "-" + userCode);
		alc.setOperateDesc("关键信息修改复核[通过],申请件重新发起[全流程]!");
		applyService.insertApplyLifeCicle(alc);
		if("1".equals(ydjFlag)&&!"0".equals(matchingCardFlag)){ // YDJ
			ApplyLifeCicle dest = new ApplyLifeCicle();
			try {
				BeanUtils.copyProperties(dest, alc);
				dest.setUuid(StringUtil.randomGUIDPlainString());
				String appIdPlus = "1".equals(appId.substring(appId.length()-1,appId.length()))?appId.substring(0, appId.length()-1)+"2":appId.substring(0, appId.length()-1)+"1";
				dest.setAppId(appIdPlus);
				applyService.insertApplyLifeCicle(dest);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		try {
			// 查询申请件信息修改记录表 -->关键信息修改表
			List<Map<Object, Object>> list = keyMessageModifyService.findAllMesByAppId(appId.substring(0, appId.length() - 1));
			int cnameflag = 0;
			int c1Idnbrflag = 0;
			int cnameflag2 = 0;
			int c1Idnbrflag2 = 0;
			for (int i = 0; i < list.size(); i++) {
				String fieldname = (String) list.get(i).get("fieldName");
				if ("c1Cname".equals(fieldname)) {
					cnameflag++;
				} else if ("c1Idnbr".equals(fieldname)) {
					c1Idnbrflag++;
				}
				if ("c2Cname".equals(fieldname)) {
					cnameflag2++;
				} else if ("c2Idnbr".equals(fieldname)) {
					c1Idnbrflag2++;
				}
			}
			keyMessageModifyService.keyMessageRecheckPass(appId, jsonString, ret);
			// 调发卡
			/*if(c1Idnbrflag>0 || c1Idnbrflag2>0){
				Map param = new HashMap<>();
				param.put("i_app_id", appId);
				logger.info("关键信息修改...修改身份证号...删除银联数据:"+appId);
				keyMessageModifyService.executeProceCLEAN_INTERFACE_UNION(param);
			}*/
			logger.info("关键信息修改--存量客户，调用input--"+appId);
//			provideService.reJudgeExistCust(appId);
			ctx.setData("isSuccess", true);
		} catch (CoreException e) {
			e.printStackTrace();
			ctx.setData("isSuccess", false);
		}

	}

	/**
	 * 关键信息退回
	 * 
	 * @param ctx
	 * @throws SQLException
	 */
	public void keyMessageRecheckRollBack(Context ctx) throws SQLException {
		Map<String, Object> dataMap = ctx.getDataMap();
		String appId = (String) dataMap.get("appId");
		String remark = (String) dataMap.get("remark");
		String userCode = (String) dataMap.get("userCode");
		String opear = applyService.qeurUserNameByUserCode(userCode);
		List<Map<Object, Object>> list = null;
		try {
			list = keyMessageModifyService.findAllMesByAppId(appId.substring(0, appId.length() - 1));
		} catch (CoreException e) {
			e.printStackTrace();
		}
		BizInpAppC1Spec t = new BizInpAppC1Spec();
		t.setAppId(appId);
		try {
			 t = applyService.get2(t);
		} catch (CoreException e1) {
			e1.printStackTrace();
		}
		//提报人
		String tibao = "";
		// 循环 -- 关键信息修改表
		List<KeyMessageModify> keyList = new ArrayList<KeyMessageModify>();
		for (Map<Object, Object> map : list) {
			tibao = (String) map.get("crtUser");
			// 拼接信息 复核人没有
			KeyMessageModify kmm = new KeyMessageModify();
			kmm.setAutoId((String) map.get("autoId"));
			TIMESTAMP timestamp = (TIMESTAMP) map.get("crtDate");
			java.sql.Date date = timestamp.dateValue();
			long time = date.getTime();
			Date modsfyDate = new Date(time);
			kmm.setModifyDate(modsfyDate);
			kmm.setOperator((String) map.get("crtUser"));
			kmm.setAppId((String) map.get("appId"));
			kmm.setKeyWordsId((String) map.get("fieldName"));
			kmm.setCurrValue((String) map.get("fieldOldValue"));
			kmm.setModifyValue((String) map.get("fieldNewValue"));
			kmm.setReviewStatus("0");
			kmm.setCrtDate(new Date());
			kmm.setReviewer(opear);
			kmm.setRemark(remark);
			kmm.setYdjFlag(t.getYdjFlag());
			kmm.setApplyName((String)map.get("applyName"));
			kmm.setCretifiId((String)map.get("cretifiId"));
			kmm.setCretifiType((String)map.get("cretifiType"));
			keyList.add(kmm);
		}
		// 查询分件表
		AllotApply allot = applyService.queryApplyAllotByAppId(appId);
		// 提报人中文姓名
		String tiBaoN = applyService.qeurUserNameByUserCode(tibao);
		// 添加流程节点
		// 根据userCode查询中文名称 qeurUserNameByUserCode
		ApplyLifeCicle alc = new ApplyLifeCicle();
		alc.setAppId(appId);
		alc.setUuid(StringUtil.randomGUIDPlainString());
		alc.setCrtDate(new Date());
		alc.setOperateResult("完成");
		alc.setCrtUser(userCode);
		alc.setOperater(opear + "-" + userCode);
		if ("02".equals(allot.getCurrNode())) { // 征信
			if("A".equals(allot.getDelStatus())){
				alc.setOperateDesc("关键信息修改复核[拒绝],申请件回到-"+tiBaoN + "-征信未完成队列");
			}
			if("B".equals(allot.getDelStatus())){
				alc.setOperateDesc("关键信息修改复核[拒绝],申请件回到-"+tiBaoN + "-征信已退回成队列");
			}
			if("C".equals(allot.getDelStatus())){
				alc.setOperateDesc("关键信息修改复核[拒绝],申请件回到-"+tiBaoN + "-征信待补件队列");
			}
		}
		if ("03".equals(allot.getCurrNode())) { // 审批
			if("A".equals(allot.getDelStatus())){
				alc.setOperateDesc("关键信息修改复核[拒绝],申请件回到-"+tiBaoN + "-审批未完成队列");
			}
			if("C".equals(allot.getDelStatus())){
				alc.setOperateDesc("关键信息修改复核[拒绝],申请件回到-"+tiBaoN + "-审批待补件队列");
			}
		}
		//更新最后操作人
		SubReport sbR = new SubReport();
		sbR.setAppId(appId);
		sbR.setCrtUser(userCode);
		try {
			subReportService.update(sbR);
		} catch (CoreException e1) {
			e1.printStackTrace();
		}
		allot.setLastUser(userCode);
		applyService.insertApplyLifeCicle(alc);
		if("1".equals(allot.getYdjFlag())){
			ApplyLifeCicle dest = new ApplyLifeCicle();
			try {
				BeanUtils.copyProperties(dest, alc);
				String appId2 = "";
				if("1".equals(appId.substring(appId.length()-1,appId.length()))){
					appId2 = appId.substring(0,appId.length()-1)+"2";
				}else{
					appId2 = appId.substring(0,appId.length()-1)+"1";
				}
				dest.setAppId(appId2);
				sbR.setAppId(appId2);
				dest.setUuid(StringUtil.randomGUIDPlainString());
				applyService.insertApplyLifeCicle(dest);
				subReportService.update(sbR);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		try {
			keyMessageModifyService.insertKeyMessage(keyList);
			keyMessageModifyService.updateModifyLogFlag(appId);
			ctx.setData("isSuccess", true);
		} catch (CoreException e) {
			e.printStackTrace();
		}
	}
}
