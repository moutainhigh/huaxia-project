package com.huaxia.opas.action.common;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.huateng.neofp.core.Action;
import com.huateng.neofp.core.Context;
import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.cache.service.riskcheck.RiskMachCheckService;
import com.huaxia.opas.domain.MenuObj;
import com.huaxia.opas.domain.allot.AllotApplyAllot;
import com.huaxia.opas.domain.allot.AllotApplyAllotHis;
import com.huaxia.opas.domain.allot.AllotCommon;
import com.huaxia.opas.domain.apply.ApplyLifeCicle;
import com.huaxia.opas.domain.apply.ApplyRemark;
import com.huaxia.opas.domain.archive.FileAppDet;
import com.huaxia.opas.domain.decision.OpaCheckinerResultInfo;
import com.huaxia.opas.domain.input.BizInpApplC1;
import com.huaxia.opas.domain.input.FicoSdBlaze;
import com.huaxia.opas.domain.report.KeyMessageModify;
import com.huaxia.opas.domain.system.ApUser;
import com.huaxia.opas.domain.thirdparty.BizApproval;
import com.huaxia.opas.service.allot.AllotCommonService;
import com.huaxia.opas.service.apply.ApplyLifeCicleService;
import com.huaxia.opas.service.apply.ApplyRemarkService;
import com.huaxia.opas.service.apply.BizInpApplService;
import com.huaxia.opas.service.archive.FileAppDetService;
import com.huaxia.opas.service.decision.SysApprovalCommonService;
import com.huaxia.opas.service.decision.SysButtonCommonService;
import com.huaxia.opas.service.decision.SysDecisionYdjService;
import com.huaxia.opas.service.report.KeyMessageModifyService;
import com.huaxia.opas.service.system.ApUserService;
import com.huaxia.opas.util.CommonProperties;
import com.huaxia.opas.util.CommonUtil;
import com.huaxia.opas.util.StringUtil;

/**
 * 
 * @author xuzhiguo 授权审批tab页的展示 2017-3-7 14:59:27
 */
public class SystemButtonCommonAction implements Action {

	private static Logger logger = LoggerFactory.getLogger(SystemButtonCommonAction.class);
	@Resource(name = "sysButtonCommonService")
	private SysButtonCommonService sysButtonCommonService;
	@Resource(name = "apUserService")
	private ApUserService apUserService;
	@Resource(name = "fileAppDetService")
	private FileAppDetService fileAppDetService;
	@Resource(name = "keyMessageModifyService")
	private KeyMessageModifyService keyMessageModifyService;
	@Resource(name = "sysApprovalCommonService")
	private SysApprovalCommonService sysApprovalCommonService;
	@Resource(name = "sysDecisionYdjService")
	private SysDecisionYdjService sysDecisionYdjService;
	@Resource(name = "riskMachCheckService")
	private RiskMachCheckService riskMachCheckService;
	@Resource(name = "applyLifeCicleService")
	private ApplyLifeCicleService applyLifeCicleService;
	@Resource(name = "allotCommonService")
	private AllotCommonService allotCommonService;
	@Resource(name="applyRemarkService")
	private ApplyRemarkService applyRemarkService;
	@Resource(name = "bizInpApplService")
	private BizInpApplService bizInpApplService;
	/**
	 * 提交、保存按钮公共代码
	 */
	@SuppressWarnings("unchecked")
	public void saveOrSubmitButtonCommon(Context ctx) {
		try {
			String button = StringUtils.trimToEmpty((String) ctx.getDataMap().get("button"));
			String appId = StringUtils.trimToEmpty((String) ctx.getDataMap().get("appId"));
			String check_meuoFlag = StringUtils.trimToEmpty((String) ctx.getDataMap().get("check_meuoFlag"));
			String userId = StringUtils.trimToEmpty((String) ctx.getDataMap().get("userIdParam"));
			Map<String,String> dataCheckMap = (Map<String, String>) ctx.getDataMap().get("dataCheckMap");
			//根据质检结果，判断是否可以提交,若质检结构为“叫停”，则审批不可提交，但可保存
			OpaCheckinerResultInfo checkInfo = sysDecisionYdjService.selectChecKinerInfoByAppId(appId);
			if(checkInfo!= null && "submit".equals(button) && "2".equals(checkInfo.getStopFlag())){
				throw new Exception("当前件质检结果为【叫停】，故而不可被提交");
			}
			//判断是否需要转上级 ------start
			// 获得当前用户的等级---转上级功能
			ApUser user = apUserService.queryUserCodeByUserId(userId);
			BigDecimal lineLevel = BigDecimal.ZERO;
			if(user == null){
				logger.error("当前用户没有审批权限。请必须为当前用户配置【L1、L2、L3】其中一个角色");
				throw new Exception("当前用户没有审批权限。请必须为当前用户配置【L1、L2、L3】其中一个角色");
			}
			String roleCode = user.getRoleCode();
			if(user!=null&&"1".equals(dataCheckMap.get("ydjFlag"))){
				lineLevel = user.getYdjLimitLevel();
			}else if(user!=null&&"2".equals(dataCheckMap.get("ydjFlag"))){
				lineLevel = user.getBzkLimitLevel();
			}
			if((lineLevel == null || "".equals(lineLevel))&&"1".equals(dataCheckMap.get("radio_approveResult"))){
				ctx.setData("isSuccess", "3");
				logger.info("用户表中没有配置额度");
				return;
			}
			BizApproval  bizApproval =  sysApprovalCommonService.selectByAppId(appId);
			//wdb 审批数据未保存提示失败或提交时是转上级状态
			if(bizApproval==null||"2".equals(bizApproval.getCreditRefuseReason())){
				logger.info("申请件审批数据保存失败appId==============="+appId);
				ctx.setData("msg", "审批数据未保存成功,请重新保存再提交。");
				ctx.setData("isSuccess", "0");
				return;
			}
			logger.info("申请件审批数据保存成功appId=============="+appId);
			boolean bolean = queryCurrUser(lineLevel,roleCode,dataCheckMap,bizApproval);
			if(bolean){
				ctx.setData("isSuccess", "2");
				logger.info("当前员工无法审批这笔额度，需要提交上级");
				return;
			}
			//判断是否需要提交上级-----end
			Map<String, String> reqMap = new HashMap<String, String>();
			reqMap.put("appId", appId);
			reqMap.put("userId", userId);
			reqMap.put("check_meuoFlag", check_meuoFlag);
			reqMap.put("userName", user ==null ? "":user.getUserName());
			AllotApplyAllot allot = sysButtonCommonService.queryAllotApplyAllot(appId);
			if (allot == null) {
				throw new Exception("申请表数据缺失异常，请检测appId=" + appId);
			}else if(!"03".equals(allot.getCurrNode())){
				throw new Exception("当前件已经不在审批环节，无法提交！");
			}
			// 为历史表赋值
			AllotApplyAllotHis record = setAllotApplyAllotHis(allot, appId);
			// 从配置文件中获取节点nodeId  wdb  修改del_status为已完成
			Map resMap = queryNodeIdAndProcessIpSp(allot,check_meuoFlag);
			allot.setCurrNode("04");// 归档
			allot.setDelStatus("1");
			allot.setUserDate(new Date());
			allot.setBackFlag("0");
			// 提交业务流程
			Map<String,Object> rest = sysButtonCommonService.saveOrSubmitButtonDeal(allot, record, reqMap, button, resMap);
			if("0".equals(rest.get("tkIsSubmit")) && "2".equals(check_meuoFlag)){
				AllotApplyAllot allot_fk = (AllotApplyAllot)rest.get("allot_fk");
				ctx.setData("allot_fk", allot_fk);
				ctx.setData("isSuccess", "11");
			}else{
				ctx.setData("isSuccess", "1");
			}
			logger.info("提交保存成功");
		} catch (Exception e) {
			logger.info("提交保存失败"+e.getMessage());
			//e.printStackTrace();后台日志不打印
			logger.error(StringUtils.trimToEmpty((String) ctx.getDataMap().get("appId")),e);//打印堆栈信息
			ctx.setData("isSuccess", "0");
			if(e.getMessage()!=null && !"".equals(e.getMessage())){
				ctx.setData("msg", e.getMessage());
			}else{
				ctx.setData("msg", "系统资源繁忙，请稍后再试");
			}
		}
	}
	/**
	 * 返回true,则强制提交上级，返回false则正常提交
	 * @param userId
	 * @param dataCheckMap
	 * @return
	 */
	public boolean queryCurrUser(BigDecimal lineLevel,String roleCode,Map<String,String> dataCheckMap,BizApproval  bizApproval) throws CoreException{
		if(bizApproval==null){
			logger.error("审批结果表中数据为空，没记录");
			return false;
		}
		//当审批额度大于该用工的审批额度上限时。自动调用转上级
		if("1".equals(dataCheckMap.get("radio_approveResult"))){
			String approveCreditLimitStr = dataCheckMap.get("approveCreditLimit") == null ? "0":dataCheckMap.get("approveCreditLimit");
			BigDecimal approveCreditLimit = new BigDecimal(approveCreditLimitStr).multiply(new BigDecimal(1000));
			if(approveCreditLimit.compareTo(lineLevel)==1){
				return true;
			}
			//违例码如下，也提交上级
			if("1".equals(dataCheckMap.get("ydjFlag"))){
				return queryYdjViolateCode(roleCode, dataCheckMap,bizApproval);
			}else{
				return queryBzkViolateCode(roleCode, dataCheckMap,bizApproval);
			}
		}else{
			//拒绝码如下，也提交上级
			String refuseCode1 = bizApproval.getRefuseCode1();
			String refuseCode2 = bizApproval.getRefuseCode2();
			String refuseCode3 = bizApproval.getRefuseCode3();
			String rc[] = {refuseCode1,refuseCode2,refuseCode3};
			for (int i = 0; i < rc.length; i++) {
				if(rc[i]==null)
					continue;
				if("1".equals(dataCheckMap.get("ydjFlag"))){
					if("L1".equals(roleCode)||"L2".equals(roleCode)){
						if("YD207".equals(rc[i]))
							return true;
					}
				}else{
					if("L1".equals(roleCode)){
						//对应L2、L3角色强制提交的违例码
						if("D213".equals(rc[i])||"D401".equals(rc[i])||"D704".equals(rc[i]))
							return true;
					}
				}
			}
		}
		return false;
	} 
	public boolean queryYdjViolateCode(String roleCode,Map<String,String> dataCheckMap,BizApproval  bizApproval){
		String violateCode1 = bizApproval.getViolateCode1();
		String violateCode2 = bizApproval.getViolateCode2();
		String violateCode3 = bizApproval.getViolateCode3();
		String violateCode[] = {violateCode1,violateCode2,violateCode3};
		for (int i = 0; i < violateCode.length; i++) {
			if(violateCode[i]==null)
				continue;
			if("L1".equals(roleCode)||"L2".equals(roleCode)){
				if("YA101".equals(violateCode[i])||"YA103".equals(violateCode[i])||"YA104".equals(violateCode[i])||
					"YA113".equals(violateCode[i])||"YA202".equals(violateCode[i])||"YA206".equals(violateCode[i])){
					return true;
				}else if("L1".equals(roleCode)){
					if("YA107".equals(violateCode[i])||"YA108".equals(violateCode[i])||"YA109".equals(violateCode[i])||
						"YA114".equals(violateCode[i])||"YA701".equals(violateCode[i])||"YA801".equals(violateCode[i])||
						"YA901".equals(violateCode[i])){
					}else{
						return true;
					}
				}
			}
		}
		return false;
	}
	public boolean queryBzkViolateCode(String roleCode,Map<String,String> dataCheckMap,BizApproval  bizApproval){
		String violateCode1 = bizApproval.getViolateCode1();
		String violateCode2 = bizApproval.getViolateCode2();
		String violateCode3 = bizApproval.getViolateCode3();
		String violateCode[] = {violateCode1,violateCode2,violateCode3};
		String vc = "|A101|A102|A106|A107|A108|A109|A115|A116|A203|A204|A205|A209|A210|A214|A215|A216|A217|A219|A301|A302|A304|A401|A402|A702";
		String vc2 =  "|A107|A108|A217|A402";
		for (int i = 0; i < violateCode.length; i++) {
			if(violateCode[i]==null)
				continue;
			if("L1".equals(roleCode)||"L2".equals(roleCode)){
				//对应L2、L3角色强制提交的违例码
				if("L1".equals(roleCode)&&vc.indexOf(violateCode[i])>0){
					return true;
				}else if("L1".equals(roleCode)&&"A207".equals(violateCode[i])){//对应L2、L3角色强制提交的违例码  A207(下浮大于50%提交时)
					//若审批额度大于等于系统建议额度的50%并小于其审批额度，则不用转
					try {
						FicoSdBlaze bzk = (FicoSdBlaze) sysApprovalCommonService.querySystemApprovalResult(bizApproval.getAppId(), "2");
						if(bizApproval.getApproveCreditLimit().compareTo(bzk.getProposedLmt().longValue()/2)==-1){
							return true;
						}
					} catch (Exception e) {
						//e.printStackTrace();
						logger.error("",e);
					}
					//return true;
				}else if(vc2.indexOf(violateCode[i])>0){ 
					//对应L3角色强制提交的违例码
					return true;
				}else if("L2".equals(roleCode) && "A203".equals(violateCode[i]) 
						&& dataCheckMap.get("age")!=null &&Integer.parseInt(dataCheckMap.get("age"))<18){//A203年龄违例，主卡申请人年龄低于18周岁违例核发也需提交L3
					return true;
				}
			}
		}
		return false;
	}
	/**
	 * 获取当前用户的审批额度权限
	 * @param ctx
	 */
	public void queryCurrUserControl(Context ctx){
		String userId = StringUtils.trimToEmpty((String) ctx.getDataMap().get("userIdParam"));
		String ydjFlag = StringUtils.trimToEmpty((String) ctx.getDataMap().get("ydjFlag"));
		try {
			// 获得当前用户的等级---转上级功能
			ApUser user = apUserService.queryUserCodeByUserId(userId);
			if(user!=null&&"1".equals(ydjFlag)){
				ctx.setData("lineUpControl", user.getYdjLimitLevel());//用户额度上限
				ctx.setData("roleCode", user.getRoleCode());//用户等级角色
			}else if(user!=null&&"2".equals(ydjFlag)){
				ctx.setData("lineUpControl", user.getBzkLimitLevel());//用户额度上限
				ctx.setData("roleCode", user.getRoleCode());//用户等级角色
			}
		} catch (CoreException e) {
//			e.printStackTrace();
			logger.error("",e);
		}
	} 
	/**
	 * 初始化自动推送
	 * 自动推送逻辑修改 20201016
	 * 之前是全局推送即所有审批未完成件的下一个件
	 * 现在是选择性推送根据条件筛选出的审批未完成队列中的下一个件
	 * 
	 */
	public void zidonPush(Context ctx) {
		try {
			// 获得userId
			String userId = StringUtils.trimToEmpty((String) ctx.getDataMap().get("userIdParam"));
			String ydjFlag = StringUtils.trimToEmpty((String) ctx.getDataMap().get("ydjFlag"));
			String check_meuoFlag = StringUtils.trimToEmpty((String) ctx.getDataMap().get("check_meuoFlag"));
			String delStatus = StringUtils.trimToEmpty((String) ctx.getDataMap().get("delStatus"));//与筛选条件共用
			String currNode = StringUtils.trimToEmpty((String) ctx.getDataMap().get("currNode"));//与筛选条件共用
			String shaixuan = StringUtils.trimToEmpty((String) ctx.getDataMap().get("shaixuan"));//与筛选条件共用
			String lsh="";String khxm="";String zjhm="";String dwmc="";//20201020 此四项条件具有很大可能的唯一性,暂时不做筛选条件
			String zxzc="";String telNet="";
			//shaixuan=telNet_1#zxzc_2#zjhm_411522194502130012
			//shaixuan ={"lsh":lsh,"khxm":khxm,"zjhm":zjhm,"dwmc":dwmc,"zxzc":zxzc,"telNet":telNet};
			if(!"".equals(shaixuan)){
				String[] shaixuan_arr = shaixuan.split("#");
				for(String str:shaixuan_arr){
					if(str.contains("lsh")){
						lsh = str.split("_")[1];
					}
					if(str.contains("khxm")){
						khxm = str.split("_")[1];
					}
					if(str.contains("zjhm")){
						zjhm = str.split("_")[1];
					}
					if(str.contains("dwmc")){
						dwmc = str.split("_")[1];
					}
					if(str.contains("zxzc")){
						zxzc = str.split("_")[1];
					}
					if(str.contains("telNet")){
						telNet = str.split("_")[1];
					}
				}
			}
			if(currNode==null ||"".equals(currNode)){
				currNode ="02";
			}
			AllotApplyAllot applyAllot = null;
			Map<String,String> map = new HashMap<String,String>();
			map.put("currOptUser",userId);
			map.put("ydjFlag",ydjFlag);
			map.put("delStatus",delStatus);
			//包含筛选条件并将筛选条件放在map中
			if(!"".equals(shaixuan)){
				map.put("telNet",telNet);
				map.put("zxzc",zxzc);
			}
			
			if("1".equals(ydjFlag)){
				String appId = StringUtils.trimToEmpty((String) ctx.getDataMap().get("appId"));
				AllotApplyAllot allotOld = sysButtonCommonService.queryAllotApplyAllot(appId);
				if(!"0".equals(allotOld.getMatchingCardFlag())){
					String appId_fk = CommonUtil.getAnothorCardAppId(appId, allotOld.getMatchingCardFlag());
					AllotApplyAllot applyAllot_fk = sysButtonCommonService.selectAllotAndAppProdByAppId(appId_fk);
					if(applyAllot_fk!=null&&"1".equals(check_meuoFlag)){//普通审批的
						if("03".equals(applyAllot_fk.getCurrNode()) && "0".equals(applyAllot_fk.getSynFlag()) && applyAllot_fk.getCurrOptUser().equals(userId))
							applyAllot = applyAllot_fk;
					}else if(applyAllot_fk!=null){//征审合一
						if("02".equals(applyAllot_fk.getCurrNode()) && "0".equals(applyAllot_fk.getSynFlag()))
							applyAllot = applyAllot_fk;
					}	
				}
				if(applyAllot==null){
					// 获得自己名下需要推送单件
					if("2".equals(check_meuoFlag)){//征审合一
						map.put("currNode", currNode);
						applyAllot = sysButtonCommonService.queryZSOneByUserId(map);
					}else{
						applyAllot = sysButtonCommonService.queryOneByUserId(map);
					}
				}
			}else{
				// 获得自己名下需要推送单件
				if("2".equals(check_meuoFlag)){//征审合一
					map.put("currNode", currNode);
					String index = StringUtils.trimToEmpty((String) ctx.getDataMap().get("index"));
					//获得当前提交的件属于第几条
					String rownum = sysButtonCommonService.selectZSCurrentNumByUserId(map);
					//获得当前件的下一个件
					//applyAllot = sysButtonCommonService.queryZSOneByUserId(map);
					if(rownum!=null && !"".equals(rownum) && Integer.parseInt(index)<Integer.parseInt(rownum)){
						map.put("num", (Integer.parseInt(index)+1)+"");
						applyAllot = sysButtonCommonService.selectZSNextOneByUserId(map);
					}
				}else{
					applyAllot = sysButtonCommonService.queryOneByUserId(map);
				}
			}
			
			ctx.setData("applyAllot", applyAllot);
			ctx.setData("msg", "推送成功");
		} catch (Exception e) {
//			e.printStackTrace();
			logger.error("",e);
		}
	}

	/**
	 * 转上级
	 * 
	 * @param context
	 */
	public void zhuanShangJi(Context context) {
		try {
			Gson gson = new Gson();
			//wdb 当前提交人 与该申请件对比是否为同一个人
			String userCode = StringUtils.trimToEmpty((String) context.getDataMap().get("userCode"));
			String appId = StringUtils.trimToEmpty((String) context.getDataMap().get("appId"));
			String jsonData = gson.toJson(context.getDataMap().get("menuInfo"));
			String check_meuoFlag = StringUtils.trimToEmpty((String) context.getDataMap().get("check_meuoFlag"));
			//根据质检结果，判断是否可以提交,若质检结构为“叫停”，则审批不可提交，但可保存
			OpaCheckinerResultInfo checkInfo = sysDecisionYdjService.selectChecKinerInfoByAppId(appId);
			if(checkInfo!= null && "2".equals(checkInfo.getStopFlag())){
				throw new Exception("当前件质检结果为【叫停】，故而不可被提交");
			}
			List<ApUser> list = gson.fromJson(jsonData, new TypeToken<List<ApUser>>() {
			}.getType());
			AllotApplyAllot applyAllot = sysButtonCommonService.selectByPrimaryKey(appId);
			//避免重复转上级或者与其它操作冲突
			if (list != null && list.size() > 0 && applyAllot != null&&userCode.equals(applyAllot.getCurrOptUser())
					&&"03".equals(applyAllot.getCurrNode())) {
				ApUser user = list.get(0);
				// 为历史表赋值
				AllotApplyAllotHis allotHis = setAllotApplyAllotHis(applyAllot, appId);
				allotHis.setBackFlag("2");
				allotHis.setLastOptUser(applyAllot.getCurrOptUser());
				// 变更为上级
				applyAllot.setLastOptUser(applyAllot.getCurrOptUser());
				applyAllot.setCurrOptUser(user.getUserId());
				//将组更改为上级的组  wdb
				try{
					AllotCommon allotCommon=allotCommonService.queryGroupByUserCode(user.getUserId());
					if(allotCommon!=null){
						applyAllot.setCurrOptGroup(allotCommon.getOrgNo());
						applyAllot.setLastOptGroup(allotCommon.getOrgName());
					}
				}catch(Exception e){
					logger.info("查询上级组异常"+e.getMessage());
				}
				applyAllot.setBackFlag("2");
				applyAllot.setSynFlag("0");
				applyAllot.setUserDate(new Date());
				String flag = "0";
				Map resMap = queryNodeIdAndProcessIp(applyAllot);
				resMap.put("check_meuoFlag", check_meuoFlag);
				AllotApplyAllot allot_fk = new AllotApplyAllot();
				if("1".equals(applyAllot.getYdjFlag()) && !"0".equals(applyAllot.getMatchingCardFlag())){//现在暂时只针对易达金,且有副卡
					String appId_fk = CommonUtil.getAnothorCardAppId(applyAllot.getAppId(), applyAllot.getMatchingCardFlag());
					allot_fk = sysButtonCommonService.selectByPrimaryKey(appId_fk);
				}
				sysButtonCommonService.updateByPrimaryKeySelective(applyAllot, allotHis, flag, resMap);
				if(allot_fk!=null && "0".equals(allot_fk.getSynFlag()) && "2".equals(check_meuoFlag)){
					context.setData("isSuccess", "11");
					context.setData("allot_fk", allot_fk);
				}else{
					context.setData("isSuccess", "1");
				}
				context.setData("msg", "提交成功!");
			} else {
				if((!"".equals(applyAllot.getCurrOptUser())&&!userCode.equals(applyAllot.getCurrOptUser()))||
						(!"03".equals(applyAllot.getCurrNode()))){
					context.setData("msg", "提交成功!");
				}else{
					context.setData("msg", "提交失败!");
				}
			}
		} catch (Exception e) {
//			e.printStackTrace();
			logger.error(StringUtils.trimToEmpty((String) context.getDataMap().get("appId")),e);
			context.setData("msg", "提交失败" + e.getMessage());
		}

	}

	/**
	 * 查询当前所用户可以推送的角色
	 * 
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void queryThreeApRole(Context context) throws CoreException {
		try {
			Map dataMap = new HashMap();
			String appId = StringUtils.trimToEmpty((String) context.getDataMap().get("appId"));
			String flag = StringUtils.trimToEmpty((String) context.getDataMap().get("flag"));
			String userId = StringUtils.trimToEmpty((String) context.getDataMap().get("userIdParam"));
			Map<String,String> dataCode = (Map<String, String>) context.getDataMap().get("dataCode");
			//新修改
			String selectName = StringUtils.trimToEmpty((String) context.getDataMap().get("selectName"));
			Map<String, Object> selectMap = new HashMap<String, Object>();
			selectMap.put("selectName", selectName);
			String paramStr = "";
			// 获得当前用户的等级---转上级功能
			ApUser user = apUserService.queryUserCodeByUserId(userId);
			String roleCode = user != null ? user.getRoleCode() : "";
			ApUser morenUser = null;
			String morenUserCode = "";
			List<MenuObj> finalTreeMenuAll = new ArrayList<MenuObj>();
			if ("2".equals(flag) && !"".equals(roleCode)) {// 转上级
				if ("L1".equals(roleCode)) {
					if("2".equals(dataCode.get("ydjFlag"))){
						String wlm_str =  "|A107|A108|A217|A402";
						if((dataCode.get("violateCode1")!=null && !"".equals(dataCode.get("violateCode1")) &&wlm_str.indexOf(dataCode.get("violateCode1"))>0)
								||(dataCode.get("violateCode2")!=null && !"".equals(dataCode.get("violateCode2")) &&wlm_str.indexOf(dataCode.get("violateCode2"))>0)
								||(dataCode.get("violateCode3")!=null && !"".equals(dataCode.get("violateCode3")) &&wlm_str.indexOf(dataCode.get("violateCode3"))>0)){
							paramStr = "'L3'";
						}else if(("A203".equals(dataCode.get("violateCode1"))||"A203".equals(dataCode.get("violateCode2"))||"A203".equals(dataCode.get("violateCode3")))
								&& dataCode.get("age")!=null && !"".equals(dataCode.get("age")) && Integer.parseInt(dataCode.get("age"))<18){
							paramStr = "'L3'";
						}else{
							paramStr = "'L2','L3'";
						}
					}else{
						paramStr = "'L2','L3'";
					}
				} else if ("L2".equals(roleCode)) {
					paramStr = "'L2','L3'";
				} else if ("L3".equals(roleCode)) {
					paramStr = "'L3'";
				}
				Map<String, String> map = new HashMap<String, String>();
				map.put("roleCode", paramStr);
				map.put("userCode", userId);
				map.put("selectName", selectName);
				//获取起上级
				List<ApUser> listApUser = apUserService.queryUserByThreeApRole(map);
				// 获得默认上级
				List<ApUser> listApUserZSJ = apUserService.queryUserByZSJ(map);
				morenUser = listApUserZSJ != null && listApUserZSJ.size() > 0 ? listApUserZSJ.get(0) : null;
				if(morenUser == null && listApUserZSJ != null && listApUserZSJ.size() > 0){
					morenUser = listApUserZSJ.get(0);
				}
				// 默认工号
				morenUserCode = morenUser != null ? morenUser.getUserCode() : userId;
				//转上级
				List<MenuObj> finalTreeMenu = new ArrayList<MenuObj>();
				MenuObj obj = null;
				for (ApUser apUser : listApUser) {
					obj = new MenuObj(apUser.getUserCode(), apUser.getUserName() + "-(" + apUser.getUserCode() + ")("+apUser.getRoleCode()+")", "1",
							"1", apUser.getRoleCode().substring(1, 2));
					finalTreeMenu.add(obj);
				}
				dataMap.put("list", finalTreeMenu);
			} else if ("1".equals(flag)) {// 退回
				Map paramMap = new HashMap();
				paramMap.put("currNode", "03");
				paramMap.put("appId", appId);
				paramMap.put("currOptUser", userId);
				paramMap.put("backFlag", "4");
				AllotApplyAllotHis oldAllot = sysButtonCommonService.queryAllotApplyAllotHisNozjByAppId(paramMap);
				if(oldAllot != null && oldAllot.getCurrOptUser()!=null){
					morenUserCode = oldAllot.getCurrOptUser();
					ApUser user_moren = apUserService.queryApUserByUserCode(morenUserCode);
					MenuObj objAll1 = new MenuObj(user_moren.getUserCode(), user_moren.getUserName() + "-(" + user_moren.getUserCode() + ")(默认人)", "1",
							"1", "1");
					finalTreeMenuAll.add(objAll1);
				}
			} else if("3".equals(flag)){
				Map paramMap = new HashMap();
				paramMap.put("currNode", "02");
				paramMap.put("appId", appId);
				AllotApplyAllotHis oldAllot = sysButtonCommonService.queryAllotApplyAllotHisByAppId(paramMap);
				if(oldAllot != null && oldAllot.getCurrOptUser()!=null){
					morenUserCode = oldAllot.getCurrOptUser();
					ApUser user_moren = apUserService.queryApUserByUserCode(morenUserCode);
					MenuObj objAll2 = new MenuObj(user_moren.getUserCode(), user_moren.getUserName() + "-(" + user_moren.getUserCode() + ")(默认人)", "1",
							"1", "1");
					finalTreeMenuAll.add(objAll2);
				}
			}
			//其他
			List<ApUser> list = null;
			if("3".equals(flag)){//若是点击的查询的是征信队列
				list = apUserService.queryZXAllUserByRoleCode(selectMap);
			}else{
				list = apUserService.queryAllUserByRoleCode(selectMap);
			}
			MenuObj objAll = null;
			for (ApUser apUser : list) {
				if(!"2".equals(flag) && apUser.getUserCode().equals(morenUserCode))
					continue;
				objAll = new MenuObj(apUser.getUserCode(), apUser.getUserName() + "-(" + apUser.getUserCode() + ")("+apUser.getRoleCode()+")", "1",
						"1", apUser.getRoleCode().substring(1, 2));
				finalTreeMenuAll.add(objAll);
			}
			dataMap.put("listUser", finalTreeMenuAll);
			context.setDataMap(dataMap);
			context.setData("noselect", morenUserCode);
		} catch (Exception e) {
//			e.printStackTrace();
			logger.error(StringUtils.trimToEmpty((String) context.getDataMap().get("appId")), e);
		}
	}

	/**
	 * 退件（退回征信）---标准卡
	 */
	public void outJian(Context context) {
		try {
			String appId = StringUtils.trimToEmpty((String) context.getDataMap().get("appId"));
			String memo = StringUtils.trimToEmpty((String) context.getDataMap().get("memo"));
			String tkIsBack = StringUtils.trimToEmpty((String) context.getDataMap().get("tkIsBack"));
			String check_meuoFlag = StringUtils.trimToEmpty((String) context.getDataMap().get("check_meuoFlag"));
			//根据质检结果，判断是否可以提交,若质检结构为“叫停”，则审批不可提交，但可保存
			/*OpaCheckinerResultInfo checkInfo = sysDecisionYdjService.selectChecKinerInfoByAppId(appId);
			if(checkInfo!= null && "2".equals(checkInfo.getStopFlag())){
				throw new Exception("当前件质检结果为【叫停】，故而不可被提交");
			}*/
			AllotApplyAllot applyAllot = sysButtonCommonService.selectAllotAndAppProdByAppId(appId);
			//wdb 当前提交人 与该申请件对比是否为同一个人   只有在审批环节(非关键信息复核)才可以退回
			String userCode = StringUtils.trimToEmpty((String) context.getDataMap().get("userCode"));
			List<String> applyList=new ArrayList<String>(Arrays.asList("0","1","2","3"));
			if(applyAllot!=null&&userCode.equals(applyAllot.getCurrOptUser())&&"03".equals(applyAllot.getCurrNode())
					&&applyList.contains(applyAllot.getDelStatus())){
				// 为历史表赋值
				AllotApplyAllotHis allotHis = setAllotApplyAllotHis(applyAllot, appId);
				allotHis.setBackFlag("4");// 4:为退回征信
				allotHis.setMemo(memo);
				allotHis.setLastOptUser(applyAllot.getCurrOptUser());
				String flag = "1";
				//判断当前件是否为批量件，如果是则退回征信组，否则，退回到征信人2019.11.13新增一次决策智能语音正核件免征信件，征信历史记录为空,第一次从审批退回征信，退到征信池  
				Map paramMap = new HashMap();
				paramMap.put("currNode", "02");
				paramMap.put("appId", appId);
				AllotApplyAllotHis oldAllot = sysButtonCommonService.queryAllotApplyAllotHisByAppId(paramMap);
				applyAllot.setDelStatus("3");
				applyAllot.setLastOptUser(applyAllot.getCurrOptUser());
				if(oldAllot==null){//wdb 智能语音免征信 征信环节没有历史记录
					applyAllot.setCurrStatus("0");//到征信池
					applyAllot.setDelStatus("0");//定义为0
				}else if("1".equals(applyAllot.getBatchCreditFlag())){//wdb 新客群二期逻辑标准卡征信批量件退到该审批员征信退回队列易达金不变动
					if("2".equals(applyAllot.getYdjFlag())){//标准卡
						applyAllot.setCurrStatus("3");
					}else{//易达金及套卡
						applyAllot.setCurrStatus("1");
						applyAllot.setCurrOptUser("");
					}
					logger.info("=======该件是批量件=======");
					// 变更
				}else{//wdb 审批退回征信
					// 变更
					applyAllot.setCurrOptUser(oldAllot.getCurrOptUser());
				}
				allotHis.setCurrOptUser(applyAllot.getCurrOptUser());
				//======
				applyAllot.setCurrNode("02");// 01：录入比对02：征信调查03：人工审批			
				applyAllot.setSynFlag("0");
				applyAllot.setBackFlag("4");
				//wdb 新客群二期逻辑标准卡征信批量件退到该审批员征信退回队列易达金不变动 组修改成审批员所在的组
				if(oldAllot==null){//wdb 智能语音免征信 征信环节没有历史记录
					applyAllot.setCurrOptGroup("");
					applyAllot.setLastOptGroup("");
					applyAllot.setCurrOptUser("");
				}else if(!"1".equals(applyAllot.getBatchCreditFlag())||"1".equals(applyAllot.getYdjFlag())){
					applyAllot.setCurrOptGroup(oldAllot.getCurrOptGroup());
					applyAllot.setLastOptGroup(oldAllot.getCurrOptGroup());
				}
				Map resMap = queryNodeIdAndProcessIp(applyAllot);
				resMap.put("check_meuoFlag", check_meuoFlag);
				resMap.put("tkIsBack", tkIsBack);
				resMap.put("memo", memo);
				if("0".equals(applyAllot.getApproveReturn())&&oldAllot==null){//智能语音免征信申请件审批第一次退回征信
					resMap.put("approveReturn", "1");
				}
				resMap.put("appId", applyAllot.getAppId());
				sysButtonCommonService.updateByPrimaryKeySelective(applyAllot, allotHis, flag, resMap);
				if("2".equals(check_meuoFlag)){
					context.setData("applyAllot", applyAllot);
				}
				//判断审批表中是否存在审批数据
				BizApproval  bizApproval =  sysApprovalCommonService.selectByAppId(appId);
				if(bizApproval!=null && "1".equals(bizApproval.getInitSaveFlag())){
					bizApproval.setInitSaveFlag("0");
					sysApprovalCommonService.updateApprovalResult(bizApproval);
				}
				context.setData("msg", "保存成功!");
			}else{
				context.setData("msg", "保存失败,该申请件已不在当前队列,请重新刷新!");
			}
		} catch (Exception e) {
//			e.printStackTrace();
			logger.error(StringUtils.trimToEmpty((String) context.getDataMap().get("appId")), e);
			context.setData("msg", "保存失败" + e.getMessage());
		}
	}
	/**
	 * 退件（退回征信）---易达金
	 */
	public void outJianYdj(Context context) {
		try {
			Gson gson = new Gson();
			String appId = StringUtils.trimToEmpty((String) context.getDataMap().get("appId"));
			String memo = StringUtils.trimToEmpty((String) context.getDataMap().get("memo"));
			String tkIsBack = StringUtils.trimToEmpty((String) context.getDataMap().get("tkIsBack"));
			String check_meuoFlag = StringUtils.trimToEmpty((String) context.getDataMap().get("check_meuoFlag"));
			//根据质检结果，判断是否可以提交,若质检结构为“叫停”，则审批不可提交，但可保存
			/*OpaCheckinerResultInfo checkInfo = sysDecisionYdjService.selectChecKinerInfoByAppId(appId);
			if(checkInfo!= null && "2".equals(checkInfo.getStopFlag())){
				throw new Exception("当前件质检结果为【叫停】，故而不可被提交");
			}*/
			String jsonData = gson.toJson(context.getDataMap().get("menuInfo"));
			List<ApUser> list = gson.fromJson(jsonData, new TypeToken<List<ApUser>>() {
			}.getType());
			AllotApplyAllot applyAllot = sysButtonCommonService.selectAllotAndAppProdByAppId(appId);
			//wdb 当前提交人 与该申请件对比是否为同一个人只有在审批环节(非关键信息复核)才可以退回
			String userCode = StringUtils.trimToEmpty((String) context.getDataMap().get("userCode"));
			if((list==null||list.size()==0)&&!"1".equals(applyAllot.getBatchCreditFlag())){
				context.setData("msg", "提交失败,非批量件未选择征信人!");
				return;
			}
			List<String> applyList=new ArrayList<String>(Arrays.asList("0","1","2","3"));
			// 因为只能选一个，所以
			if (list != null && list.size() > 0 && applyAllot != null&&userCode.equals(applyAllot.getCurrOptUser())
					&&"03".equals(applyAllot.getCurrNode())&&applyList.contains(applyAllot.getDelStatus())) {
				ApUser user = list.get(0);
				// 为历史表赋值
				AllotApplyAllotHis allotHis = setAllotApplyAllotHis(applyAllot, appId);
				allotHis.setBackFlag("4");// 1:为退回征信
				allotHis.setMemo(memo);
				allotHis.setLastOptUser(applyAllot.getCurrOptUser());
				allotHis.setCurrOptUser(user.getUserId());
				allotHis.setCurrNode("03");
				//======
				// 变更为上级
				applyAllot.setLastOptUser(applyAllot.getCurrOptUser());
				applyAllot.setCurrOptUser(user.getUserId());
				applyAllot.setCurrNode("02");// 01：录入比对02：征信调查03：人工审批
				applyAllot.setDelStatus("3");//3:退回
				applyAllot.setSynFlag("0");
				applyAllot.setBackFlag("4");
				applyAllot.setBatchCreditFlag("0");
				applyAllot.setBatchApprovalFlag("0");
				String flag = "1";
				Map resMap = queryNodeIdAndProcessIp(applyAllot);
				resMap.put("check_meuoFlag", check_meuoFlag);
				resMap.put("tkIsBack", tkIsBack);
				resMap.put("memo", memo);
				sysButtonCommonService.updateByPrimaryKeySelective(applyAllot, allotHis, flag, resMap);
				if("2".equals(check_meuoFlag)){
					context.setData("applyAllot", applyAllot);
				}
				context.setData("msg", "提交成功!");
			} else if((list == null || list.size() == 0) && applyAllot != null && "1".equals(applyAllot.getBatchCreditFlag())
					&&userCode.equals(applyAllot.getCurrOptUser())&&"03".equals(applyAllot.getCurrNode())&&
					applyList.contains(applyAllot.getDelStatus())){
				logger.info("=======该件是批量件，并且没有选择退回征信人=======");
				// 为历史表赋值
				AllotApplyAllotHis allotHis = setAllotApplyAllotHis(applyAllot, appId);
				allotHis.setBackFlag("4");// 1:为退回征信
				allotHis.setMemo(memo);
				allotHis.setLastOptUser(applyAllot.getCurrOptUser());
				allotHis.setCurrOptUser("");
				allotHis.setCurrNode("03");
				//======
				// 变更为上级
				Map paramMap = new HashMap();
				paramMap.put("currNode", "02");
				paramMap.put("appId", appId);
				AllotApplyAllotHis oldAllot = sysButtonCommonService.queryAllotApplyAllotHisByAppId(paramMap);
				if (oldAllot == null) {
					throw new Exception("当前申请件历史数据缺失，请检测详情");
				}
				applyAllot.setLastOptUser(applyAllot.getCurrOptUser());
				applyAllot.setCurrOptUser("");
				applyAllot.setCurrStatus("1");
				applyAllot.setCurrNode("02");// 01：录入比对02：征信调查03：人工审批
				applyAllot.setDelStatus("3");//3:退回
				applyAllot.setSynFlag("0");
				applyAllot.setBackFlag("4");
				applyAllot.setCurrOptGroup(oldAllot.getCurrOptGroup());
				applyAllot.setLastOptGroup(applyAllot.getCurrOptGroup());
				String flag = "1";
				Map resMap = queryNodeIdAndProcessIp(applyAllot);
				resMap.put("check_meuoFlag", check_meuoFlag);
				resMap.put("tkIsBack", tkIsBack);
				resMap.put("memo", memo);
				sysButtonCommonService.updateByPrimaryKeySelective(applyAllot, allotHis, flag, resMap);
				if("2".equals(check_meuoFlag)){
					context.setData("applyAllot", applyAllot);
				}
				context.setData("msg", "提交成功!");
			} else {
				if(!userCode.equals(applyAllot.getCurrOptUser())||!"03".equals(applyAllot.getCurrNode())){
					context.setData("msg", "提交成功!");
				}else{
					context.setData("msg", "提交失败,该申请件已不在当前队列,请重新刷新!");
				}
				//context.setData("msg", "提交失败!");
			}
			//判断审批表中是否存在审批数据
			BizApproval  bizApproval =  sysApprovalCommonService.selectByAppId(appId);
			if(bizApproval!=null && "1".equals(bizApproval.getInitSaveFlag())){
				bizApproval.setInitSaveFlag("0");
				sysApprovalCommonService.updateApprovalResult(bizApproval);
			}
		} catch (Exception e) {
//			e.printStackTrace();
			logger.error(StringUtils.trimToEmpty((String) context.getDataMap().get("appId")),e);
			context.setData("msg", "提交失败:"+e.getMessage());
		}
	}
	/**
	 * 退回（审批）
	 */
	public void outHui(Context context) {
		try {
			Gson gson = new Gson();
			String appId = StringUtils.trimToEmpty((String) context.getDataMap().get("appId"));
			String memo = StringUtils.trimToEmpty((String) context.getDataMap().get("memo"));
			String check_meuoFlag = StringUtils.trimToEmpty((String) context.getDataMap().get("check_meuoFlag"));
			String tkIsBack = StringUtils.trimToEmpty((String) context.getDataMap().get("tkIsBack"));
			
			String jsonData = gson.toJson(context.getDataMap().get("menuInfo"));
			//根据质检结果，判断是否可以提交,若质检结构为“叫停”，则审批不可提交，但可保存
			OpaCheckinerResultInfo checkInfo = sysDecisionYdjService.selectChecKinerInfoByAppId(appId);
			if(checkInfo!= null && "2".equals(checkInfo.getStopFlag())){
				throw new Exception("当前件质检结果为【叫停】，故而不可被提交");
			}
			List<ApUser> list = gson.fromJson(jsonData, new TypeToken<List<ApUser>>() {
			}.getType());
			AllotApplyAllot applyAllot = sysButtonCommonService.selectByPrimaryKey(appId);
			//wdb 当前提交人 与该申请件对比是否为同一个人只有在审批环节(非关键信息复核)才可以退回
			String userCode = StringUtils.trimToEmpty((String) context.getDataMap().get("userCode"));
			if (list != null && list.size() > 0 && applyAllot != null && userCode.equals(applyAllot.getCurrOptUser())
					&&"03".equals(applyAllot.getCurrNode())) {
				ApUser user = list.get(0);
				// 为历史表赋值
				AllotApplyAllotHis allotHis = setAllotApplyAllotHis(applyAllot, appId);
				allotHis.setBackFlag("1");// 1:为退回审批
				allotHis.setMemo(memo);
				allotHis.setLastOptUser(user.getUserId());
				// 变更为上级
				applyAllot.setLastOptUser(applyAllot.getCurrOptUser());
				applyAllot.setCurrOptUser(user.getUserId());
				//将组更改为上级的组  wdb
				try{
					AllotCommon allotCommon=allotCommonService.queryGroupByUserCode(user.getUserId());
					if(allotCommon!=null){
						applyAllot.setCurrOptGroup(allotCommon.getOrgNo());
						applyAllot.setLastOptGroup(allotCommon.getOrgName());
					}
				}catch(Exception e){
					logger.info("查询上级组异常"+e.getMessage());
				}
				applyAllot.setBackFlag("1");
				applyAllot.setCurrNode("03");// 01：录入比对02：征信调查03：人工审批
				String flag = "0";
				Map resMap = queryNodeIdAndProcessIp(applyAllot);
				resMap.put("check_meuoFlag",check_meuoFlag);
				resMap.put("memo", memo);
				resMap.put("tkIsBack", tkIsBack);
				AllotApplyAllot allot_fk = new AllotApplyAllot();
				if("1".equals(applyAllot.getYdjFlag()) && !"0".equals(applyAllot.getMatchingCardFlag())){//现在暂时只针对易达金,且有副卡
					String appId_fk = CommonUtil.getAnothorCardAppId(applyAllot.getAppId(), applyAllot.getMatchingCardFlag());
					allot_fk = sysButtonCommonService.selectByPrimaryKey(appId_fk);
				}
				sysButtonCommonService.updateByPrimaryKeySelective(applyAllot, allotHis, flag, resMap);
				/*if("1".equals(applyAllot.getYdjFlag()) && !"0".equals(applyAllot.getMatchingCardFlag())){//现在暂时只针对易达金,且有副卡
					String appId_fk = CommonUtil.getAnothorCardAppId(applyAllot.getAppId(), applyAllot.getMatchingCardFlag());
					AllotApplyAllot allot_fk = sysButtonCommonService.selectByPrimaryKey(appId_fk);
					if(allot_fk!=null && "0".equals(allot_fk.getSynFlag()) && "2".equals(check_meuoFlag)){
						context.setData("isSuccess", "11");
						context.setData("allot_fk", allot_fk);
					}else{
						context.setData("isSuccess", "1");
					}
				}else{
					context.setData("isSuccess", "1");
				}*/
				if(allot_fk!=null && "0".equals(allot_fk.getSynFlag()) && "2".equals(check_meuoFlag)){
					context.setData("isSuccess", "11");
					context.setData("allot_fk", allot_fk);
				}else{
					context.setData("isSuccess", "1");
				}
				context.setData("msg", "提交成功!");
			} else {
				if(!userCode.equals(applyAllot.getCurrOptUser())||!"03".equals(applyAllot.getCurrNode())){
					context.setData("msg", "提交成功!");
				}else{
					context.setData("msg", "提交失败!");
				}
				//context.setData("msg", "提交失败!");
			}
		} catch (Exception e) {
//			e.printStackTrace();
			logger.error(StringUtils.trimToEmpty((String) context.getDataMap().get("appId")), e);
			context.setData("msg", "提交失败:"+e.getMessage());
		}
	}
	/**
	 * 解挂
	 * @author hang
	 * @param context
	 */
	public void updateSpApplyYdjHangUpCancle(Context context) {

		Map map = context.getDataMap();
		try {
			// 获得申请件编号
			String userId = StringUtils.trimToEmpty((String) context.getDataMap().get("userId"));
			String appIds = (String) map.get("appIds");
			logger.info("=====appIds====" + appIds);
			String appIds_str[] = appIds.split("[|]");
			for (String appId : appIds_str) {
				// 调用service
				// 根据appId获得对应到信息
				AllotApplyAllot allot = sysButtonCommonService.queryAllotApplyAllot(appId);
				if (allot == null) {
					throw new Exception("申请表数据缺失异常，请检测appId=" + appId);
				}
				if("3".equals(allot.getCurrStatus())){
					continue;
				}
				allot.setCurrStatus("3");
				allot.setBackFlag("0");
				allot.setCurrOptUser(userId);
				Map<String,String> resMap = new HashMap<String,String>();
				resMap.put("check_meuoFlag","1");
				// 为历史表赋值
				AllotApplyAllotHis allotHis = setAllotApplyAllotHis(allot, appId);
				sysButtonCommonService.updateByPrimaryKeySelective(allot, allotHis, "3", resMap);
			}
		} catch (Exception e) {
//			e.printStackTrace();
			logger.error("", e);
		}
	}

	/**
	 * 申请表信息修改查看
	 */
	public void applyDealView(Context ctx) {
		String appId = StringUtils.trimToEmpty((String) ctx.getDataMap().get("appId"));
		Map<String, Object> map = ctx.getDataMap();
		int curNum = 0;
		int curPage = Integer
				.parseInt(String.valueOf(ctx.getDataMap().get("page") == null ? 0 : ctx.getDataMap().get("page")));
		int pageNum = Integer
				.parseInt(String.valueOf(ctx.getDataMap().get("rows") == null ? 0 : ctx.getDataMap().get("rows")));

		if (curPage > 1) {
			curNum = (curPage - 1) * pageNum;
		}
		int count = 0;

		count = sysButtonCommonService.queryCount(appId);
	
		try {
			List<KeyMessageModify> list = sysButtonCommonService.selectApplyLogByAppId(appId,curNum, pageNum);
			Map<String, Object> dataMap = new HashMap<String, Object>();
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(list!=null && list.size()>0){
				for (KeyMessageModify keyMessageModify : list) {
					Date modifyDate = keyMessageModify.getModifyDate();//("modifyDate");
					String format = sf.format(modifyDate);
					keyMessageModify.setModifyDate(sf.parse(format));//("modifyDate", sf.parse(format));
					String crtUser = (String) keyMessageModify.getOperator();
					String flag = keyMessageModify.getFlag();
					if("1".equals(flag)){
						if(crtUser != null){
							crtUser = keyMessageModifyService.qeurUserNameByUserCode1(crtUser);
						}
					}else if("2".equals(flag)){
						if(crtUser != null){
							crtUser = keyMessageModifyService.qeurUserNameByUserCode(crtUser);
						}
					}
					keyMessageModify.setOperator(crtUser);
				}
			}
			dataMap.put("total", count);
			dataMap.put("rows", list);
			ctx.setDataMap(dataMap);
		} catch (Exception e) {
//			e.printStackTrace();
			logger.error("", e);
		}
	}

	public AllotApplyAllotHis setAllotApplyAllotHis(AllotApplyAllot allot, String appId) throws Exception {
		AllotApplyAllotHis record = new AllotApplyAllotHis();
		record.setId(StringUtil.randomUUIDPlainString());
		record.setAppId(appId);
		record.setCurrOptQueue(allot.getCurrOptQueue()==null?"":allot.getCurrOptQueue());
		record.setLastOptQueue(allot.getLastOptQueue()==null?"":allot.getLastOptQueue());
		record.setCurrOptGroup(allot.getCurrOptGroup()==null?"":allot.getCurrOptGroup());
		record.setLastOptGroup(allot.getLastOptGroup()==null?"":allot.getLastOptGroup());
		record.setCurrOptUser(allot.getCurrOptUser()==null?"":allot.getCurrOptUser());
		record.setLastOptUser(allot.getLastOptUser()==null?"":allot.getLastOptUser());
		record.setCurrStatus(allot.getCurrStatus()==null?"":allot.getCurrStatus());
		record.setCurrNode(allot.getCurrNode()==null?"":allot.getCurrNode());
		record.setCrtTeamDate(allot.getCrtTeamDate()==null?new Date():allot.getCrtTeamDate());
		record.setLstTeamDate(new Date());
		record.setRecordTeamDate(new Date());
		record.setDelStatus(allot.getDelStatus()==null?"":allot.getDelStatus());
		record.setSubmitType(allot.getSubmitType()==null?"":allot.getSubmitType());
		record.setSubmitStatus(allot.getSubmitStatus()==null?"":allot.getSubmitStatus());
		record.setReviewStatus(allot.getReviewStatus()==null?"":allot.getReviewStatus());
		record.setQueueDate(allot.getQueueDate()==null?new Date():allot.getQueueDate());
		record.setGroupDate(allot.getGroupDate()==null?new Date():allot.getGroupDate());
		record.setSubmitMemo(allot.getSubmitMemo()==null?"":allot.getSubmitMemo());
		record.setSynFlag(allot.getSynFlag()==null?"":allot.getSynFlag());
		record.setYdjFlag(allot.getYdjFlag());
		record.setMatchingCardFlag(allot.getMatchingCardFlag());
		record.setProcessId(allot.getProcessId());
		record.setBackFlag(allot.getBackFlag());
		record.setLaojianflag(allot.getLaojianflag());
		record.setCheck_meuoFlag(allot.getCheck_meuoFlag());
		return record;
	}

	public Map queryNodeIdAndProcessIpSp(AllotApplyAllot allot,String check_meuoFlag) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		// 从配置文件中获取节点nodeId
		Map<String, String> paroperMap = CommonProperties.getParoperMap();
		map.put("process_IP", paroperMap.get("process_IP"));
		if ("1".equals(allot.getYdjFlag())&&!"2".equals(check_meuoFlag)) {// 易达金
			map.put("nodeId", paroperMap.get("nodeId_sp_ydj"));
		} else if ("2".equals(allot.getYdjFlag())&&!"2".equals(check_meuoFlag)) {// 标准卡
			map.put("nodeId", paroperMap.get("nodeId_sp_bzk"));
		} else if("1".equals(allot.getYdjFlag())&&"2".equals(check_meuoFlag)){//征审合一 易达金
			map.put("nodeId", paroperMap.get("nodeId_sc_ydj"));
			//map.put("nodeId", paroperMap.get("nodeId_sc_ydj"));
		} else if("2".equals(allot.getYdjFlag())&&"2".equals(check_meuoFlag)){//征审合一 标准卡
			map.put("nodeId", paroperMap.get("nodeId_sc_bzk"));
			//map.put("nodeId", paroperMap.get("nodeId_sc_bzk"));
		} else {
			throw new Exception("申请件分配表易达金标志为空，请检测详情");
		}
		return map;
	}
	public Map queryNodeIdAndProcessIp(AllotApplyAllot allot) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		// 从配置文件中获取节点nodeId
		Map<String, String> paroperMap = CommonProperties.getParoperMap();
		map.put("process_IP", paroperMap.get("process_IP"));
		if ("1".equals(allot.getYdjFlag())) {// 易达金
			map.put("nodeId", paroperMap.get("nodeId_sp_ydj"));
			map.put("nodeId2", paroperMap.get("nodeId_sc_ydj"));
		} else if ("2".equals(allot.getYdjFlag())) {// 标准卡
			map.put("nodeId", paroperMap.get("nodeId_sp_bzk"));
			map.put("nodeId2", paroperMap.get("nodeId_sc_bzk"));
		} else {
			throw new Exception("申请件分配表易达金标志为空，请检测详情");
		}
		return map;
	}
	public Map queryNodeIdAndProcessIpGd(AllotApplyAllot allot) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		// 从配置文件中获取节点nodeId
		Map<String, String> paroperMap = CommonProperties.getParoperMap();
		map.put("process_IP", paroperMap.get("process_IP"));
		if ("1".equals(allot.getYdjFlag())) {// 易达金
			map.put("nodeId", paroperMap.get("nodeId_gd_ydj"));
		} else if ("2".equals(allot.getYdjFlag())) {// 标准卡
			map.put("nodeId", paroperMap.get("nodeId_gd_bzk"));
		} else {
			throw new Exception("申请件分配表易达金标志为空，请检测详情");
		}
		return map;
	}
	
	/**
	 * 已完成申请件---回收功能按钮
	 * 
	 * @author xuzhiguo
	 */
	
	public void huiShouList(Context context) {
		Map map = context.getDataMap();
		try {
			// 获得申请件编号
			String userId = StringUtils.trimToEmpty((String) context.getDataMap().get("userId"));
			String appIds = (String) map.get("appIds");
			String check_meuoFlag = (String) map.get("check_meuoFlag");
			logger.info("=====appIds====" + appIds);
			String appIds_str[] = appIds.split("[|]");
			for (String appId : appIds_str) {
				// 调用service
				// 根据appId获得对应到信息
				AllotApplyAllot allot = sysButtonCommonService.queryAllotApplyAllot(appId);
				if (allot == null) {
					throw new Exception("申请表数据缺失异常，请检测appId=" + appId);
				}
				// 为历史表赋值
				AllotApplyAllotHis allotHis = setAllotApplyAllotHis(allot, appId);
				//wdb del_status 修改为未完成
				allot.setDelStatus("0");
				allot.setCurrNode("03");
				allot.setCurrOptUser(userId);
				Map resMap = queryNodeIdAndProcessIpGd(allot);
				resMap.put("check_meuoFlag", check_meuoFlag);
				// 更新数据表
				sysButtonCommonService.updateByPrimaryKeySelective(allot, allotHis, "2", resMap);
			}
		} catch (Exception e) {
//			e.printStackTrace();
			logger.error("", e);
		}
	}
	
	/**
	 * 挂起功能
	 * @author xuzhiguo
	 */
	public void guaQi(Context context) {
		Map map = context.getDataMap();
		try {
			// 获得申请件编号
			String userId = StringUtils.trimToEmpty((String) context.getDataMap().get("userId"));
			String appIds = (String) map.get("appIds");
			logger.info("=====appIds====" + appIds);
			String appIds_str[] = appIds.split("[|]");
			//二期 易达金挂起备注 start
			String ydjFlag=(String) context.getData("ydjFlag")!=null?(String) context.getData("ydjFlag"):"";
			if("1".equals(ydjFlag)){
				String userCode=(String) context.getData("userCode")!=null?(String) context.getData("userCode"):"";
				String rs=(String) context.getData("remarkInfo")!=null?(String) context.getData("remarkInfo"):"";
				StringBuffer remarkInfo=new StringBuffer("审批环节个人队列挂起备注："+rs);
				List<String> applyList=new ArrayList<String>(Arrays.asList(appIds_str));
				List<ApplyRemark> remarkList=new ArrayList<ApplyRemark>();
				String appIdStr="";
				int sum=applyList.size();
				while(sum>0){
					appIdStr=applyList.get(sum-1).trim();
					//查询当前环节当前状态申请件
					ApplyRemark remark=new ApplyRemark();
					remark.setAppId(appIdStr);
					remark.setRemarkInfo(remarkInfo.toString());
					remark.setRemarkUser(userCode);
					remark.setRemarkTime(new Date());
					remark.setCurrNode("03");
					remarkList.add(remark);
					applyList.remove(sum-1);
					sum--;
					//查询是否有套卡
					String taokaId="";
					if(appIdStr.endsWith("2")){
						 taokaId = appIdStr.substring(0,15)+"1";
					}else if(appIdStr.endsWith("1")){
						taokaId=appIdStr.substring(0,15)+"2";
					}
					AllotApplyAllot allot_fk=sysButtonCommonService.queryAllotApplyAllot(taokaId);
					if(allot_fk!=null){
						ApplyRemark applyRemark2=new ApplyRemark();
						BeanUtils.copyProperties(remark, applyRemark2);
						applyRemark2.setAppId(taokaId);
						remarkList.add(applyRemark2);
						if(applyList.contains(taokaId)){//两者都有
							applyList.remove(sum-1);
							sum--;
						}
					}
				}
				//插入备注
				if(remarkList!=null&&remarkList.size()>0){
					applyRemarkService.insertBatch(remarkList);
				}
			}
			//end
			for (String appId : appIds_str) {
				// 调用service
				// 根据appId获得对应到信息
				AllotApplyAllot allot = sysButtonCommonService.queryAllotApplyAllot(appId);
				if (allot == null) {
					throw new Exception("申请表数据缺失异常，请检测appId=" + appId);
				}
				//
				if ("03".equals(allot.getCurrNode()) && !"4".equals(allot.getCurrStatus())) {
					allot.setCurrStatus("4");//4-已分配已挂起
					allot.setLastOptUser(allot.getCurrOptUser());
					// 为历史表赋值
					AllotApplyAllotHis allotHis = setAllotApplyAllotHis(allot, appId);
					Map<String,String> resMap = new HashMap<String,String>();
					resMap.put("check_meuoFlag","1");
					// 更新数据表
					sysButtonCommonService.updateByPrimaryKeySelective(allot, allotHis, "0", resMap);
				}
			}
		} catch (Exception e) {
//			e.printStackTrace();
			logger.error("", e);
		}
	}
	/**
	 * 征信提交，内部交叉检测
	 */
	public void innerJcCheck(Context context){
		String appId="",appID="";
		try {
			appId = StringUtils.trimToEmpty((String) context.getDataMap().get("appIds"));
			logger.info(appId+"征信提交，内部交叉检测[innerJcCheck]");
			ApplyLifeCicle applyLifeCicle = new ApplyLifeCicle();
			applyLifeCicle.setUuid(StringUtil.randomUUIDPlainString());
			applyLifeCicle.setAppId(appId);
			applyLifeCicle.setOperater("system");
			applyLifeCicle.setOperateDesc("二次内部交叉检查");
			applyLifeCicle.setOperateResult("完成");
			applyLifeCicle.setCrtDate(new Date());
			applyLifeCicle.setCrtUser("system");
			applyLifeCicle.setLstUpdDate(new Date());
			applyLifeCicle.setLstUpdUser("system");
			applyLifeCicleService.addApplyLifeCicle(applyLifeCicle);
			BizInpApplC1 app = sysDecisionYdjService.selectBizInpApplC1ByAppId(appId);
			if("1".equals(app.getMatchingCardFlag())){
				if(appId.endsWith("1")){
					appID=appId.substring(0, 15)+"2";
				}else if(appId.endsWith("2")){
					appID=appId.substring(0, 15)+"1";
				}
			}else{
				appID=appId;
			}
			riskMachCheckService.exeRiskCheck(appID, "4", app.getC1c2Flag());
		} catch (Exception e) {
			logger.info(appId+"异常原因："+e.getMessage());
//			e.printStackTrace();
			logger.error(appId, e);
		}
	}
	
	/**
	 * 提交、保存按钮公共代码  uat使用生产不需要此方法
	 * opas_file_application_detail
	 */
	public void submitOpasFileApplication(Context ctx) {
		Gson gson = new Gson();
		Map map = ctx.getDataMap();
		try {
			String appId = StringUtils.trimToEmpty((String) ctx.getDataMap().get("appId"));
			String flag = (String) ctx.getDataMap().get("flag");
			String applyCard = (String) ctx.getDataMap().get("applyCard");
			String appResult = (String) ctx.getDataMap().get("appResult");
			FileAppDet fileAppDet = new FileAppDet();
			if("1".equals(appResult)){
				fileAppDet.setAppResult("0");
			}else{
				fileAppDet.setAppResult("1");
			}
			BizApproval bizApproval = gson.fromJson(gson.toJson(map), BizApproval.class);
			bizApproval.setApproveResult(appResult);
			bizApproval.setApproveCreditProduct(applyCard);
			
			//默认值都是1
			fileAppDet.setAlreadySendFlag("1");
			fileAppDet.setAppId(appId);
			fileAppDet.setFlag(flag);
			fileAppDet.setApplyCard(applyCard);
			fileAppDetService.insertFileAppDet(fileAppDet,bizApproval);
			logger.info("提交保存成功");
		} catch (Exception e) {
			logger.info("提交保存失败"+e.getMessage());
			//e.printStackTrace();后台日志不打印
			logger.error(StringUtils.trimToEmpty((String) ctx.getDataMap().get("appId")),e);//打印堆栈信息
			if(e.getMessage()!=null && !"".equals(e.getMessage())){
				ctx.setData("msg", e.getMessage());
			}else{
				ctx.setData("msg", "系统资源繁忙，请稍后再试");
			}
		}
	}
}
