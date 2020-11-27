package com.huaxia.opas.allot.action;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.huateng.neofp.core.Action;
import com.huateng.neofp.core.Context;
import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.common.util.UUIDGenerator;
import com.huaxia.opas.domain.allot.AllotApply;
import com.huaxia.opas.domain.allot.AllotApplyAllotHis;
import com.huaxia.opas.domain.allot.AllotCommon;
import com.huaxia.opas.domain.allot.AllotQueue;
import com.huaxia.opas.domain.apply.ApplyLifeCicle;
import com.huaxia.opas.domain.input.TelcheckResult;
import com.huaxia.opas.domain.thirdparty.BizApproval;
import com.huaxia.opas.service.allot.AllotApplyHisService;
import com.huaxia.opas.service.allot.AllotApplyService;
import com.huaxia.opas.service.allot.AllotCommonService;
import com.huaxia.opas.service.allot.AllotMappingRuleService;
import com.huaxia.opas.service.allot.AllotMethodService;
import com.huaxia.opas.service.allot.AllotQueueService;
import com.huaxia.opas.service.allot.AllotRuleService;
import com.huaxia.opas.service.allot.AllotSwitchService;
import com.huaxia.opas.service.allot.ReviewDecisionService;
import com.huaxia.opas.service.apply.ApplyLifeCicleService;
import com.huaxia.opas.service.apply.BizInpApplService;
import com.huaxia.opas.service.checking.QualityCheckingService;
import com.huaxia.opas.service.credit.TeamPhoneService;
import com.huaxia.opas.service.fico.FicoService;
import com.huaxia.opas.util.StringUtil;

/**
 * 手动分件条件查询分配  
 * @author wangdebin
 * @version v1.1(初始v1.0)
 * @history 修改历史记录
 * ------------------------------------------------
 *  2017-10-10  修复按百分比分配方式百分百分配有申请件剩余的问题
 *  2017-10-19  添加审批池或组环节 特定征信人员及征信环节操作的过的申请件展示
 *  2017-10-20  添加特定征信到特定审批功能
 *  2017-10-24  修复要分配的申请件大于待分配件仍能部分分配的问题
 *  2017-10-24  修复最后操作员赋值问题
 *  2017-11-02  按特定申请件判断最新质检是否为叫停状态
 * ------------------------------------------------
 */
@SuppressWarnings({ "rawtypes" })
public class AllotHandAction implements Action {
	private static final Logger log = LoggerFactory.getLogger(AllotHandAction.class);
	
	@Resource(name="allotApplyService")
	private AllotApplyService allotApplyService;
	
	@Resource(name="allotMappingRuleService")
	private AllotMappingRuleService allotMappingRuleService;
	
	@Resource(name="allotQueueService")
	private AllotQueueService allotQueueService;
	
	@Resource(name="allotRuleService")
	private AllotRuleService allotRuleService;
	
	@Resource(name = "allotSwitchService")
	private	AllotSwitchService allotSwitchService;
	
	@Resource(name="allotCommonService")
	private AllotCommonService allotCommonService;
	
	@Resource(name="allotApplyHisService")
	private AllotApplyHisService allotApplyHisService;
	
	@Resource(name="ficoServiceImp")
	private FicoService ficoService;
	
	@Resource(name="reviewDecisionService")
	private ReviewDecisionService reviewDecisionService;
	
	@Resource(name = "applyLifeCicleService")
	private ApplyLifeCicleService applyLifeCicleService;
	
	@Resource(name = "bizInpApplService")
	private BizInpApplService bizInpApplService;
	
	@Resource(name = "teamPhoneService")
	private TeamPhoneService teamPhoneService;
	
	@Resource(name = "allotMethodService")
	private AllotMethodService allotMethodService;
	
	@Resource(name="qualityCheckingService")
	private QualityCheckingService qualityCheckingService ;
	
	private static final int commitCountEveryTime=10000;
	/**
	 * @Description: 池中待分配件的数量及快速待分配件查询
	 * @author 王德彬
	 * @version V1.0
	 * @param context
	 */
	public void countAppId(Context context) {
		String currNode=(String)context.getData("node");
		String ydjFlag=(String)context.getData("ydjFlag");
		int count=0;
		int count2=0;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("currNode", currNode);
		map.put("ydjFlag", ydjFlag);
		//欺诈标识
		String submitStr="";
		//欺诈审批标识  区分欺诈调查
		String fraudStr="";
		if(!"05".equals(currNode)){//提报状态
			submitStr="submit";
		}
		map.put("fraudStr", fraudStr);
		map.put("submitStr",submitStr);
		//池中未分配
		List<String> currStatusList=new  ArrayList<String>();
		currStatusList.add("0");
		map.put("currStatusList", currStatusList);
		//池中未叫停申请件  质检叫停 2 不能分件 
    	String stopStr="AND (info.STOP_FLAG is null or info.STOP_FLAG in('0','1')) ";
		map.put("stopStr", stopStr);
		try {
			//根据易达金标识 依主卡为主
			String ydjStr="";
			if("1".equals(ydjFlag)||"2".equals(ydjFlag)||"".equals(ydjFlag)){
				ydjStr="AND (allot.MATCHING_CARD_FLAG in ('0','2') or allot.LAOJIANFLAG='01' or allot.LAOJIANFLAG='02')";
			}
			map.put("ydjStr", ydjStr);
			count=allotApplyService.queryCount(map);
			//快速审批
			map.put("quickStr","and ac.QUICK_CARD_FLAG='1' ");
			count2=allotApplyService.queryCount(map);
		} catch (CoreException e) {
			log.error("AllotHandAction.countAppId occur error"+e);
		}
		Map<String, Object> dataMap = new HashMap<String, Object>();
		 dataMap.put("total", count);
		 dataMap.put("total2", count2);
		 context.setDataMap(dataMap);
	}
	/**
	 * @Description: 队列中待分配件的数量及快速待分配件查询
	 * @author 王德彬
	 * @version V1.0
	 * @param context
	 */
	public void countQueueApply(Context context) {
		int count=0;
		int count2=0;
		String currNode=(String)context.getData("node");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("currNode", currNode);
		map.put("ydjFlag", "2");
		String submitStr="";
		if(!"05".equals(currNode)){//提报状态
			submitStr="submit";
		}
		map.put("submitStr",submitStr);
		//队列中未分配
		List<String> currStatusList=new  ArrayList<String>();
		currStatusList.add("6");
		map.put("currStatusList", currStatusList);
		//根据易达金标识 依主卡为主
		map.put("ydjStr", "AND (allot.MATCHING_CARD_FLAG in ('0','2') or allot.LAOJIANFLAG='01' or allot.LAOJIANFLAG='02')");
		//队列中未叫停申请件  质检叫停 2 不能分件 
    	String stopStr="AND (info.STOP_FLAG is null or info.STOP_FLAG in('0','1')) ";
		map.put("stopStr", stopStr);
		try {
			count=allotApplyService.queryCount(map);
			//快速审批
			map.put("quickStr","and ac.QUICK_CARD_FLAG='1' ");
			count2=allotApplyService.queryCount(map);
		} catch (CoreException e) {
			log.error("AllotHandAction.countQueueApply occur error"+e);
		}
		Map<String, Object> dataMap = new HashMap<String, Object>();
		 dataMap.put("total", count);
		 dataMap.put("total2", count2);
		 context.setDataMap(dataMap);
	}
	/**
	 * @Description: 组中待分配件的数量及快速待分配件查询(组长页面)
	 * @author 王德彬
	 * @version V1.0
	 * @param context
	 */
	public void countGroupApply(Context context) {
		int count=0;
		int count2=0;
		String userCode=(String)context.getData("userCode");
		String currNode=(String)context.getData("node");
		String ydjFlag=(String)context.getData("ydjFlag");
		AllotCommon allotCommon;
		try {
			allotCommon=allotCommonService.queryGroupByUserCode(userCode);
			String orgNo=allotCommon.getOrgNo();
			Map<String,Object> params=new HashMap<String,Object>();
			params.put("currNode",currNode);
			params.put("currGroup", orgNo);
			params.put("ydjFlag", ydjFlag);
			String submitStr="";
			if(!"05".equals(currNode)){//提报状态
				submitStr="submit";
			}
			params.put("submitStr",submitStr);
			//根据易达金标识 依主卡为主
			String ydjStr="";
			if("1".equals(ydjFlag)||"2".equals(ydjFlag)||"".equals(ydjFlag)){
				ydjStr="AND (allot.MATCHING_CARD_FLAG in ('0','2') or allot.LAOJIANFLAG='01' or allot.LAOJIANFLAG='02')";
			}
			params.put("ydjStr", ydjStr);
			//组中未分配
			List<String> currStatusList=new  ArrayList<String>();
			currStatusList.add("1");
			params.put("currStatusList", currStatusList);
			//组中未叫停申请件  质检叫停 2 不能分件 
	    	String stopStr="AND (info.STOP_FLAG is null or info.STOP_FLAG in('0','1')) ";
	    	params.put("stopStr", stopStr);
			count=allotApplyService.queryCount(params);
			//快速审批
			params.put("quickStr","and ac.QUICK_CARD_FLAG='1' ");
			count2=allotApplyService.queryCount(params);
		} catch (CoreException e) {
			log.error("AllotHandAction.countGroupApply occur error"+e);
		}
		Map<String, Object> dataMap = new HashMap<String, Object>();
		 dataMap.put("total", count);
		 dataMap.put("total2", count2);
		 context.setDataMap(dataMap);
	}
	/**
	 * @Description: 当日新接收的申请件数量
	 * @author 王德彬
	 * @version V1.0
	 * @param context
	 * @throws CoreException 
	 */
	public void countTodayApp(Context context) throws CoreException {
		int count=0;
		String id=(String)context.getData("id")==null?"":(String)context.getData("id");
		String userCode=(String)context.getData("userCode")==null?"":(String)context.getData("userCode");
		String currNode=(String)context.getData("node")==null?"":(String)context.getData("node");
		String secondNode=(String)context.getData("secondNode")==null?"":(String)context.getData("secondNode");
		String currCode=(String)context.getData("currCode")==null?"":(String)context.getData("currCode");
		String ydjFlag=(String)context.getData("ydjFlag");
		int value=(Integer) context.getData("value");
		try {
			Map<String, Object> map = (Map<String, Object>) (context.getData("jsondata")==null?new HashMap<String, Object>():context.getData("jsondata"));
			String isHangUp=(String)map.get("isHangUp")==null?"":(String)map.get("isHangUp");
			if(value==6){
				AllotCommon allotCommon=allotCommonService.queryGroupByUserCode(userCode);
				if(allotCommon!=null){
					currCode=allotCommon.getOrgNo();
				}
			}
			Date date=new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String startDate=sdf.format(date);
			map.put("startDate", startDate);
			log.info("AllotHandAction.countTodayApp	查询当日新接收申请件数量的日期=="+startDate);
			map.put("currNode", currNode);
			map.put("ydjFlag",ydjFlag);
			//池转移或回收  组或队列(标准卡征信) 队列转移回收组中   组可以转移自己组 或转移回收组员
			String nowStr="";
			List<String> currStatusList=new ArrayList<String>();
			if("1".equals(ydjFlag)||"".equals(ydjFlag)){//易达金或者反欺诈 
				if("1".equals(secondNode)){
					currStatusList.add("1");
					if("02".endsWith(currNode)){
						nowStr="AND to_char(allot.GROUP_TEAM_DATE,'yyyy-MM-dd') =#{startDate}";
					}else if("03".equals(currNode)||"05".equals(currNode)){
						nowStr="AND to_char(allot.GROUP_TEAM_DATE,'yyyy-MM-dd') =#{startDate}";
					}
					map.put("currGroup", currCode);
				}else if("3".equals(secondNode)){
					if("1".equals(id)){
						currStatusList.add("3");
						nowStr="AND to_char(allot.USER_DATE,'yyyy-MM-dd') =#{startDate}";
						map.put("currUser", currCode);
						//欺诈环节 区分欺诈调查和欺诈审批
						if("".equals(ydjFlag)){
							map.put("fraudStr", "and allot.BATCH_APPROVAL_FLAG in ('0','1') ");
						}
					}else if("3".equals(id)){
						currStatusList.add("1");
						if("02".endsWith(currNode)){
							nowStr="AND to_char(allot.GROUP_TEAM_DATE,'yyyy-MM-dd') =#{startDate}";
						}else if("03".equals(currNode)||"05".equals(currNode)){
							nowStr="AND to_char(allot.GROUP_TEAM_DATE,'yyyy-MM-dd') =#{startDate}";
						}
						map.put("currGroup", currCode);
					}
				}
			}else if("2".equals(ydjFlag)){//标准卡
				if("1".equals(secondNode)&&("01".equals(currNode)||"03".equals(currNode))){
					currStatusList.add("1");
					if("01".equals(currNode)){
						nowStr="AND to_char(allot.GROUP_DATE,'yyyy-MM-dd') =#{startDate}";
					}else if("03".equals(currNode)){
						nowStr="AND to_char(allot.GROUP_TEAM_DATE,'yyyy-MM-dd') =#{startDate}";
					}
					map.put("currGroup", currCode);
				}if("1".equals(secondNode)&&("02".equals(currNode))){
					currStatusList.add("6");
					nowStr="AND to_char(allot.QUEUE_DATE,'yyyy-MM-dd') =#{startDate}";
					map.put("currQueue", currCode);
				}else if("2".equals(secondNode)){
					currStatusList.add("1");
					nowStr="AND to_char(allot.GROUP_TEAM_DATE,'yyyy-MM-dd') =#{startDate}";
					map.put("currGroup", currCode);
				}else if("3".equals(secondNode)){
					if("1".equals(id)){
						currStatusList.add("3");
						nowStr="AND to_char(allot.USER_DATE,'yyyy-MM-dd') =#{startDate}";
						map.put("currUser", currCode);
					}else if("3".equals(id)){
						currStatusList.add("1");
						if("01".equals(currNode)){
							nowStr="AND to_char(allot.GROUP_DATE,'yyyy-MM-dd') =#{startDate}";
						}else if("02".equals(currNode)||"03".equals(currNode)||"05".equals(currNode)){
							nowStr="AND to_char(allot.GROUP_TEAM_DATE,'yyyy-MM-dd') =#{startDate}";
						}
						map.put("currGroup", currCode);
					}
				}
			}
			if("1".equals(isHangUp)){//转移情况包含挂起申请件
				if(currStatusList.contains("1")){
					currStatusList.add("2");
				}else if(currStatusList.contains("3")){
					currStatusList.add("4");
				}else if(currStatusList.contains("6")){
					currStatusList.add("7");
				}
			}
			map.put("nowStr",nowStr);
			map.put("strType", "3");
			map.put("currStatusFlag","true");
			map.put("currStatusList",currStatusList);
			Map<String,Object> allMap=allotApplyService.saveApplyByConditions(map);
			if(allMap.size()>0){
				count= (Integer) allMap.get("allResult");
			}
			log.info("AllotHandAction.countTodayApp	当日新接收申请件数量=="+count);
			context.setData("msg", "查询当日申请件成功,件数为"+count);
		} catch (CoreException e) {
			log.error("AllotHandAction.countTodayApp occur error"+e);
			context.setData("msg", "查询当日申请件失败");
		}
		Map<String, Object> dataMap = new HashMap<String, Object>();
		 dataMap.put("total", count);
		 context.setDataMap(dataMap);
	}
	/**
	 * @Description: 手动查询  团办号去重及查询申请件数量
	 * @author 王德彬
	 * @version V1.0
	 * @param context
	 * @throws CoreException 
	 */
	public void findC4Batch(Context context) throws CoreException  {
		int count=0;
		List<AllotApply> list=new  ArrayList<AllotApply>();
		try {
			String ydjFlag=StringUtils.trimToEmpty((String)context.getData("ydjFlag"));
			String currNode=StringUtils.trimToEmpty((String)context.getData("currNode"));
			String secondNode=StringUtils.trimToEmpty((String)context.getData("secondNode"));
			String userCode=StringUtils.trimToEmpty((String)context.getData("userCode"));
			Map<String,Object> map=new HashMap<String,Object>();
			int curNum = 0;
			int curPage = Integer.parseInt(
					String.valueOf(context.getDataMap().get("page") == null ? 0 : context.getDataMap().get("page")));

			int pageNum = Integer.parseInt(
					String.valueOf(context.getDataMap().get("rows") == null ? 20 : context.getDataMap().get("rows")));

			if (curPage > 1) {
				curNum = (curPage - 1) * pageNum;
			}
			List<String> currStatusList=context.getData("list");
			if("3".equals(secondNode)){
				AllotCommon acm=allotCommonService.queryGroupByUserCode(userCode);
				String currGroup=acm.getOrgNo();
				map.put("currGroup", currGroup);
			}
			//根据易达金标识 依主卡为主
			String ydjStr="";
			if("1".equals(ydjFlag)||"2".equals(ydjFlag)||"".equals(ydjFlag)){
				ydjStr="AND (allot.MATCHING_CARD_FLAG in ('0','2') or allot.LAOJIANFLAG='01' or allot.LAOJIANFLAG='02')";
			}
			map.put("ydjStr", ydjStr);
			String submitStr="";
			if(!"05".equals(currNode)){//提报状态
				submitStr="submit";
			}
			map.put("submitStr",submitStr);
			map.put("currStatusList", currStatusList);
			map.put("curNum", curNum);
			map.put("pageNum", pageNum);
			map.put("ydjFlag", ydjFlag);
			map.put("currNode", currNode);
			//未叫停申请件  质检叫停 2 不能分件 
	    	String stopStr="AND (info.STOP_FLAG is null or info.STOP_FLAG in('0','1')) ";
	    	map.put("stopStr", stopStr);
			count=allotApplyService.countC4ApBatch(map);
			if(count>0){
				list=allotApplyService.queryC4ApBatch(map, curNum, pageNum);
			}
		} catch (CoreException e) {
			log.error("AllotHandAction.findC4Batch occur error"+e);
		}
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("total",count);
		dataMap.put("rows", list);
		context.setDataMap(dataMap);
	}
	public void findC5JCType(Context context) throws CoreException  {
		int count=0;
		List<AllotApply> list=new  ArrayList<AllotApply>();
		try {
			String ydjFlag=StringUtils.trimToEmpty((String)context.getData("ydjFlag"));
			String currNode=StringUtils.trimToEmpty((String)context.getData("currNode"));
			String secondNode=StringUtils.trimToEmpty((String)context.getData("secondNode"));
			String userCode=StringUtils.trimToEmpty((String)context.getData("userCode"));
			Map<String,Object> map=new HashMap<String,Object>();
			int curNum = 0;
			int curPage = Integer.parseInt(
					String.valueOf(context.getDataMap().get("page") == null ? 0 : context.getDataMap().get("page")));

			int pageNum = Integer.parseInt(
					String.valueOf(context.getDataMap().get("rows") == null ? 20 : context.getDataMap().get("rows")));

			if (curPage > 1) {
				curNum = (curPage - 1) * pageNum;
			}
			List<String> currStatusList=context.getData("list");
			if("3".equals(secondNode)){
				AllotCommon acm=allotCommonService.queryGroupByUserCode(userCode);
				String currGroup=acm.getOrgNo();
				map.put("currGroup", currGroup);
			}
			//根据易达金标识 依主卡为主
			String ydjStr="";
			if("1".equals(ydjFlag)||"2".equals(ydjFlag)||"".equals(ydjFlag)){
				ydjStr="AND (allot.MATCHING_CARD_FLAG in ('0','2') or allot.LAOJIANFLAG='01' or allot.LAOJIANFLAG='02')";
			}
			map.put("ydjStr", ydjStr);
			String submitStr="";
			if(!"05".equals(currNode)){//提报状态
				submitStr="submit";
			}
			map.put("submitStr",submitStr);
			map.put("currStatusList", currStatusList);
			map.put("curNum", curNum);
			map.put("pageNum", pageNum);
			map.put("ydjFlag", ydjFlag);
			map.put("currNode", currNode);
			//未叫停申请件  质检叫停 2 不能分件 
	    	String stopStr="AND (info.STOP_FLAG is null or info.STOP_FLAG in('0','1')) ";
	    	map.put("stopStr", stopStr);
			count=allotApplyService.countC5JCType(map);
			if(count>0){
				list=allotApplyService.queryC5JCType(map, curNum, pageNum);
			}
		} catch (CoreException e) {
			log.error("AllotHandAction.findC5JCType occur error"+e);
		}
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("total",count);
		dataMap.put("rows", list);
		context.setDataMap(dataMap);
	}
	/**
	 * @Description: 手动查询  推广机构去重及查询申请件数量
	 * @author 王德彬
	 * @version V1.0
	 * @param context
	 */
	public void findC5AbCode(Context context)  {
		int count=0;
		List<AllotApply> list=new  ArrayList<AllotApply>();
		try {
				String ydjFlag=StringUtils.trimToEmpty((String)context.getData("ydjFlag"));
				String currNode=StringUtils.trimToEmpty((String)context.getData("currNode"));
				String secondNode=StringUtils.trimToEmpty((String)context.getData("secondNode"));
				String userCode=StringUtils.trimToEmpty((String)context.getData("userCode"));
				Map<String,Object> map=new HashMap<String,Object>();
				int curNum = 0;
				int curPage = Integer.parseInt(
						String.valueOf(context.getDataMap().get("page") == null ? 0 : context.getDataMap().get("page")));
	
				int pageNum = Integer.parseInt(
						String.valueOf(context.getDataMap().get("rows") == null ? 20 : context.getDataMap().get("rows")));
	
				if (curPage > 1) {
					curNum = (curPage - 1) * pageNum;
				}
				List<String> currStatusList=context.getData("list");
				if("3".equals(secondNode)){//组
					AllotCommon acm=allotCommonService.queryGroupByUserCode(userCode);
					String currGroup=acm.getOrgNo();
					map.put("currGroup", currGroup);
				}
				//根据易达金标识 依主卡为主
				String ydjStr="";
				if("1".equals(ydjFlag)||"2".equals(ydjFlag)||"".equals(ydjFlag)){
					ydjStr="AND (allot.MATCHING_CARD_FLAG in ('0','2') or allot.LAOJIANFLAG='01' or allot.LAOJIANFLAG='02')";
				}
				map.put("ydjStr", ydjStr);
				String submitStr="";
				if(!"05".equals(currNode)){//提报状态
					submitStr="submit";
				}
				map.put("submitStr",submitStr);
				map.put("currStatusList", currStatusList);
				map.put("curNum", curNum);
				map.put("pageNum", pageNum);
				map.put("ydjFlag", ydjFlag);
				map.put("currNode", currNode);
				//未叫停申请件  质检叫停 2 不能分件 
		    	String stopStr="AND (info.STOP_FLAG is null or info.STOP_FLAG in('0','1')) ";
		    	map.put("stopStr", stopStr);
				count=allotApplyService.countC5AbCode(map);
				if(count>0){
					list=allotApplyService.queryC5AbCode(map, curNum, pageNum);
				}
			} catch (CoreException e) {
				log.error("AllotHandAction.findC5AbCode occur error"+e);
			}
			Map<String, Object> dataMap = new HashMap<String, Object>();
			dataMap.put("total",count);
			dataMap.put("rows", list);
			context.setDataMap(dataMap);
	}
	/**
	 * @Description: 手动查询  推广人员去重及查询申请件数量
	 * @author 王德彬
	 * @version V1.0
	 * @param context
	 */
	public void findC4AbUser(Context context)  {
		int count=0;
		List<AllotApply> list=new  ArrayList<AllotApply>();
		try {
			String ydjFlag=StringUtils.trimToEmpty((String)context.getData("ydjFlag"));
			String currNode=StringUtils.trimToEmpty((String)context.getData("currNode"));
			String secondNode=StringUtils.trimToEmpty((String)context.getData("secondNode"));
			String userCode=StringUtils.trimToEmpty((String)context.getData("userCode"));
			Map<String,Object> map=new HashMap<String,Object>();
			int curNum = 0;
			int curPage = Integer.parseInt(
					String.valueOf(context.getDataMap().get("page") == null ? 0 : context.getDataMap().get("page")));

			int pageNum = Integer.parseInt(
					String.valueOf(context.getDataMap().get("rows") == null ? 20 : context.getDataMap().get("rows")));

			if (curPage > 1) {
				curNum = (curPage - 1) * pageNum;
			}
			List<String> currStatusList=context.getData("list");
			 if("3".equals(secondNode)){//组
				AllotCommon acm=allotCommonService.queryGroupByUserCode(userCode);
				String currGroup=acm.getOrgNo();
				map.put("currGroup", currGroup);
			}
			//根据易达金标识 依主卡为主
			String ydjStr="";
			if("1".equals(ydjFlag)||"2".equals(ydjFlag)||"".equals(ydjFlag)){
				ydjStr="AND (allot.MATCHING_CARD_FLAG in ('0','2') or allot.LAOJIANFLAG='01' or allot.LAOJIANFLAG='02')";
			}
			map.put("ydjStr", ydjStr);
			String submitStr="";
			if(!"05".equals(currNode)){//提报状态
				submitStr="submit";
			}
			map.put("submitStr",submitStr);
			map.put("currStatusList", currStatusList);
			map.put("curNum", curNum);
			map.put("pageNum", pageNum);
			map.put("ydjFlag", ydjFlag);
			map.put("currNode", currNode);
			//未叫停申请件  质检叫停 2 不能分件 
	    	String stopStr="AND (info.STOP_FLAG is null or info.STOP_FLAG in('0','1')) ";
	    	map.put("stopStr", stopStr);
			count=allotApplyService.countC4AbUser(map);
			if(count>0){
				list=allotApplyService.queryC4AbUser(map, curNum, pageNum);
			}
		} catch (CoreException e) {
			log.error("AllotHandAction.findC4AbUser occur error"+e);
		}
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("total",count);
		dataMap.put("rows", list);
		context.setDataMap(dataMap);
	}

	/**
	* @Description: 手动条件  匹配  查询批量提交  fico  调策略   征信批量 (过件码)  审批 (拒绝码)
	* @author 王德彬
	* @version  V1.0
	* @param context  
	 */
	public void batchFico(Context context) throws  Exception{
		Map<String,Object> map = context.getDataMap();
		String node=(String)map.get("node")!=null?(String)map.get("node"):"";
		String ydjFlag=(String)map.get("ydjFlag")!=null?(String)map.get("ydjFlag"):"";
		List<String> ordrList=(List<String>) map.get("list");
		int result=0;
		//批量提交申请件总数
		int sum=ordrList.size();
		//blaze返回空值的申请件
		int count=0;
		//调工作流失败申请件
		int count2=0;
		try {
			if("01".equals(node)){//审查批量
				String requestType="SD1006";
				String appId="";
				String industryCode="";
				String jobCode="";
				//查询审查池 排查由于长期未更新页面导致页面显示 与实际不符
				map.put("secondNode", "1");
				map.put("ydjFlag", "2");
				map.put("currStatus", "0");
				//调用fico  成功返回行员代码的调工作流 跳过录入   没有  继续走人工分件  
				for(int i=0;i<ordrList.size();i++){
					appId=ordrList.get(i);
					//IND_CD_RST 调用fico  返回行职业代码  字段
					log.info(" 审查批量往blaze送值 ,申请件编号为"+appId+"+++++++++++");
					String indCdRst= ficoService.ficoRequest(requestType, appId,"2");
					//String indCdRst="99999";
					log.info("AllotHandAction.batchFico 审查批量blaze 返回行职业代码为"+indCdRst);
					if(indCdRst!=null&&!"".equals(indCdRst)){//命中审查批量返回99999 跳过审查 调工作流   
						industryCode=indCdRst.substring(0, 3);
						jobCode=indCdRst.substring(3, 5);
						map.put("appId", appId);
						map.put("industryCode",industryCode);
						map.put("jobCode",jobCode);
						result=allotApplyService.updateFico(map);
						if(result==1){//批量提交成功
							count2++;
						}
					}else{
						result=1;
						count++;
						continue;
					}
				}
			}else if("02".equals(node)){//征信批量
				Map<String, TelcheckResult> linkedHashMap = new LinkedHashMap<String, TelcheckResult>();
				linkedHashMap = context.getData("jsondata");
				Gson gson = new Gson();
				TelcheckResult telcheckResult = gson.fromJson(gson.toJson(linkedHashMap), TelcheckResult.class);
				map.put("tel", telcheckResult);
				if(ordrList.size()>0){
					result=allotApplyService.updateFico(map);
				}
			}else  if("03".equals(node)){//审批批量
				Map<String, BizApproval> linkedHashMap = new LinkedHashMap<String, BizApproval>();
				linkedHashMap = context.getData("jsondata");
				Gson gson = new Gson();
				BizApproval biz = gson.fromJson(gson.toJson(linkedHashMap), BizApproval.class);
				map.put("biz", biz);
				if(ordrList.size()>0){
					result=allotApplyService.updateFico(map);
			    }
			}
		} catch (CoreException e) {
			log.error(node + "批量环节异常:", e);
		}
		if("02".equals(node)||"03".equals(node)){
			if(result==0){
				context.setData("isSuccess",false);
				context.setData("exMsg","存在不符合流程的申请件");
			}else{
				context.setData("isSuccess",true);
			}
		}else if("01".equals(node)){
			if(count>0){
				context.setData("batchSuccess",true);
				context.setData("exMsg","blaze不支持"+count+"个申请件批量提交");
			}else if(count2>0){
				if(count2==sum){//全部批量提交成功
					context.setData("isSuccess",true);
				}else{//部分批量提交成功
					context.setData("isSuccess2",true);
					context.setData("exMsg2","总共有"+count2+"件批量提交成功");
				}
			}else{//批量提交失败
				context.setData("isSuccess",false);
			}
		}
	}
	/**
	* @Description: 批量隐藏
	* @author 王德彬
	* @version  V1.2
	* @param context  
	 */
	public void batchUpdateSynFlag(Context context){
		log.info("batchUpdateSynFlag批量隐藏开始。");
		Map<String,Object> map = context.getDataMap();
		List<String> ordrList=(List<String>) map.get("list");
		String node=(String)map.get("node")!=null?(String)map.get("node"):"";
		try {
			allotMethodService.updateSynFlag(ordrList,node);
			context.setData("isSuccess",true);
		} catch (Exception e) {
			log.error("batchUpdateSynFlag批量隐藏失败" ,e);
			context.setData("isSuccess",false);
			context.setData("exMsg","存在不符合流程的申请件");
		}
	}
	/**
	* @Description: 手动条件  匹配  查询公共方法 分配
	* @author 王德彬
	* @version  V1.0
	* @param context  
	 */
	public Map<String, Object> shareCommon(Context context) {
		String userCode=(String)context.getData("userCode");
		String node=(String)context.getData("node");
		String secondNode=(String)context.getData("secondNode");
		String ydjFlag=(String)context.getData("ydjFlag");
		int id = Integer.parseInt((String) context.getData("id"));
		String shareWay="3";
		Map<String, Object> map = context.getData("jsondata"); 
		if("1".equals(ydjFlag)||"".equals(ydjFlag)){
			map.put("strType","1");
		}else if("2".equals(ydjFlag)){
			map.put("strType","4");
		}
		map.put("shareWay", shareWay);
		map.put("currNode",node);
		map.put("secondNode", secondNode);
		map.put("userCode", userCode);
		map.put("ydjFlag", ydjFlag);
		List<AllotApply> appList=new ArrayList<AllotApply>();
		Map<String,Object> allMap=new HashMap<String,Object>();
		Map<String, Object> appMap = new HashMap<String, Object>();
		//分批次查询标识
		map.put("rownumFlag", "false");
		try {
			//老查询
			if("1".equals(ydjFlag)||"".equals(ydjFlag)){//易达金和反欺诈查询   
				allMap=allotApplyService.saveReviewPoolByCondition(map);
			}else if("2".equals(ydjFlag)){//标准卡分件查询
				//新查询
				map.put("rownumFlag", "true");
				//总件数
				int totalWeight=0;
				//开始查询和结束查询数量
				int start=0,end=0;
				String value = context.getData("queueInfo");
				String[] eums = value.split(";");
				if(id==1){//按件数分配  统计本次要分配件的数量 
					//采用百分比分配时统计百分数 估算是否所有都分配    按件数提前校验申请件是否够数量
					if(eums.length!=0){
						for(int i=0;i<eums.length;i++){
							String pingEum=eums[i];
							String[] pingChr = pingEum.split(":");
							totalWeight=totalWeight+Integer.parseInt(pingChr[1].trim());
						}
					}
					if(totalWeight>commitCountEveryTime){
						//需要提交的次数  
						int	commitCount=(int)Math.ceil(totalWeight/(double)commitCountEveryTime);
						for(int i=0;i<commitCount;i++){
							try {
								//开始
								start=i*commitCountEveryTime;
								//数量
								end=Math.min(commitCountEveryTime,totalWeight-start);
								map.put("curNum", start);
								map.put("pageNum", end);
								Map<String,Object> rownumMap=allotApplyService.saveApplyByConditions(map);
								if(rownumMap!=null&&rownumMap.size()>0){
									appList.addAll((List<AllotApply>) rownumMap.get("list2"));
								}
							} catch (Exception e) {
								log.error("分件查询报错", e.getMessage());
							}
						}
					}else{
						end=totalWeight;
						map.put("curNum", start);
						map.put("pageNum", end);
						allMap=allotApplyService.saveApplyByConditions(map);
					}
				}else if(id==2){
					//查询当前总共有多少件  
					map.put("strType","3");
					Map<String,Object> numMap=allotApplyService.saveApplyByConditions(map);
					int allResult=(Integer) numMap.get("allResult");
					//查询具体件
					if("1".equals(ydjFlag)||"".equals(ydjFlag)){
						map.put("strType","1");
					}else{
						map.put("strType","4");
					}
					//采用百分比分配时统计估算分配数量 新修改====
					StringBuffer queueInfo2=new StringBuffer();
					int baiFen=0;
					int num=0;
					if(eums.length!=0){
						for(int i=0;i<eums.length;i++){
							if(totalWeight>=allResult){
								break;
							}
							String pingEum=eums[i];
							String[] pingChr = pingEum.split(":");
							baiFen=baiFen+Integer.parseInt(pingChr[1].trim());
							//当百分数总和为100%,最后对象将分配到剩余全部件(奇数情况按比例分配有剩余)
							if(baiFen>=100&&i==(eums.length-1)){
								num =allResult-totalWeight;
							}else{
								num=(int) (allResult *(Integer.parseInt(pingChr[1].trim())) * 0.01); 
							}
							if (num < 1) {
								num = 1;
							}
							queueInfo2.append(pingChr[0]+":"+num+";");
							totalWeight=totalWeight+num;
						}
					}
					appMap.put("queueInfo2", queueInfo2.toString());
					//============
					if(totalWeight>commitCountEveryTime){
						//需要提交的次数  
						int	commitCount=(int)Math.ceil(totalWeight/(double)commitCountEveryTime);
						for(int i=0;i<commitCount;i++){
							try {
								//开始
								start=i*commitCountEveryTime;
								//数量
								end=Math.min(commitCountEveryTime,totalWeight-start);
								map.put("curNum", start);
								map.put("pageNum", end);
								Map<String,Object> rownumMap=allotApplyService.saveApplyByConditions(map);
								if(rownumMap!=null&&rownumMap.size()>0){
									appList.addAll((List<AllotApply>) rownumMap.get("list2"));
								}
							} catch (Exception e) {
								log.error("分件查询报错", e);
							}
						}
					}else{
						end=totalWeight;
						map.put("curNum", start);
						map.put("pageNum", end);
						allMap=allotApplyService.saveApplyByConditions(map);
					}
				}
			}
			if(allMap.size()>0){
				appList=(List<AllotApply>) allMap.get("list2");
			}
			map.clear();
		} catch (Exception e) {
			log.error("分件查询异常：", e.getMessage());
		}
		//审查池分件时避免并发问题 打标动作  新修改====
		/*int updateResult=0;
		if("1".equals(secondNode)&&"2".equals(ydjFlag)&&"01".equals(node)&&(id==1)){
			synchronized (this) {
				try {
					updateResult=allotApplyService.updateReviewStatus(appList);
				} catch (Exception e) {
					log.info("分件更新标识异常"+e.getMessage());
				}
			}
			if(updateResult==0){//更新失败  本次分件失败  
				appMap.put("total", 0);
				appMap.put("appList", appList);
			}else{
				for(int i=0;i<appList.size();i++){
					AllotApply ap=appList.get(i);
					ap.setAllotVersion(ap.getAllotVersion()+1);
				}
				appMap.put("total", appList.size());
				appMap.put("appList", appList);
			}
		}else{
			appMap.put("total", appList.size());
			appMap.put("appList", appList);
		}*/
		//====新修改
		appMap.put("total", appList.size());
		appMap.put("appList", appList);
		return appMap;
	}
	/**
	* @Description: 手动按前台条件查询申请件信息表   将查询结果结果返回界面
	* @author 王德彬
	* @version  V1.0
	* @param context  
	 */
	public void queryAppByConditions(Context context) {
		try {
			String ydjFlag=(String)context.getData("ydjFlag");
			String userCode=(String)context.getData("userCode");
			String node=(String)context.getData("node");
			String secondNode=(String)context.getData("secondNode");
			Map<String, Object> map = context.getData("jsondata"); 
			map.put("currNode",node);
			map.put("secondNode", secondNode);
			map.put("userCode", userCode);
			map.put("ydjFlag", ydjFlag);
			map.put("strType","3");
			Map<String, Object> dataMap=allotApplyService.saveApplyByConditions(map);
			dataMap.put("total", dataMap.get("allResult"));
			context.setDataMap(dataMap);
		} catch (Exception e) {
			log.info("AllotHandAction.queryAppByConditions Occer e"+e);
		}
	}
	/**
	* @Description: 手动按前台条件查询  申请件分配表  及分页展示
	* @author 王德彬
	* @version  V1.0
	* @param context  
	 */
	public void shareAppByConditions(Context context) {
		//初始时间
		String node=(String)context.getData("node");
		String secondNode=(String)context.getData("secondNode");
		String userCode=(String)context.getData("userCode");
		String ydjFlag=(String)context.getData("ydjFlag");
		Map<String, Object> map = context.getData("jsondata"); 
		map.put("strType","2");
		String shareWay="3";
		map.put("ydjFlag", ydjFlag);
		map.put("shareWay", shareWay);
		map.put("currNode",node);
		map.put("secondNode", secondNode);
		map.put("userCode", userCode);
		List<AllotApply> appList=new ArrayList<AllotApply>();
		int curNum = 0;
		int curPage = Integer.parseInt(
				String.valueOf(context.getDataMap().get("page") == null ? 0 : context.getDataMap().get("page")));

		int pageNum = Integer.parseInt(
				String.valueOf(context.getDataMap().get("rows") == null ? 0 : context.getDataMap().get("rows")));

		if (curPage > 1) {
			curNum = (curPage - 1) * pageNum;
		}
		map.put("curNum", curNum);
		map.put("pageNum", pageNum);
		int count=0;
		try {
			Map<String,Object> allMap=allotApplyService.saveApplyByConditions(map);
			if(allMap.size()>0){
				appList=(List<AllotApply>) allMap.get("list2");
				count=(Integer) allMap.get("allResult");
			}
			log.info("分件条件查询,查询满足筛条件申请件完成!");
		} catch (CoreException e) {
			log.error("分件条件查询,查询满足筛条件申请件异常:", e);
		}
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("total", count);
		dataMap.put("rows", appList);
		context.setDataMap(dataMap);	
	}
	/**
	* @Description: 征信  环节   标准卡手动分配队列查询
	* @author 王德彬
	* @version  V1.0
	* @param context  
	 */
	public void showAllotQueue(Context context){
		List<AllotQueue> queueList=new ArrayList<AllotQueue>();
		String userCode=(String)context.getData("userCode");
		String currNode=(String)context.getData("node");
		String ydjFlag=(String)context.getData("ydjFlag");
		String isHangUp=(String)context.getData("isHangUp")==null?"":(String)context.getData("isHangUp");
		Map<String,Object> map =new HashMap<String,Object>();
		map.put("userCode", userCode);
		map.put("currNode", currNode);
		map.put("ydjFlag", ydjFlag);
		map.put("isHangUp", isHangUp);
		try {
			 queueList=allotQueueService.queryAllotQueue(map);
		} catch (CoreException e) {
			log.error("AllotHandAction.showAllotQueue occur error"+e);
		}
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("queueList", queueList);
		context.setDataMap(dataMap);
	}
	/**
	* @Description:  易达金   标准卡审查 审批 征信    环节    手动分配组查询
	* @author 王德彬
	* @version  V1.0
	* @param context  
	 */
	public void showAllotGroup(Context context){
		String userCode=(String)context.getData("userCode")==null?"":(String)context.getData("userCode");
		String currNode=(String)context.getData("node")==null?"":(String)context.getData("node");
		String orgLevel=(String)context.getData("orgLevel")==null?"":(String)context.getData("orgLevel");
		String secondNode=(String)context.getData("secondNode")==null?"":(String)context.getData("secondNode");
		//只有分配方式为特定指定分配时specialFlag为1 其它情况为0或""
		String specialFlag=(String)context.getData("specialFlag")==null?"":(String)context.getData("specialFlag");
		//审批池启用sortFlag2排序  added by jipengchun
		String sortFlag2=(String)context.getData("sortFlag2")==null?"":(String)context.getData("sortFlag2");
		//是否需要加挂起标志 added by jipengchun 
		String isHangUp=(String)context.getData("isHangUp")==null?"":(String)context.getData("isHangUp");
		//组
		List<AllotCommon> groupList= new ArrayList<AllotCommon>();
		//特定征信人员
		List<AllotCommon> crediterList= new ArrayList<AllotCommon>();
		//特定审批人员
		List<AllotCommon> approverList=new ArrayList<AllotCommon>();
		Map<String,Object> map =new HashMap<String,Object>();
		String orgName="";
		String orgNo="";
		int currGroupNum=0;
		int total=0;
		try {
			map.put("userCode", userCode);
			map.put("currNode", currNode);
			map.put("orgLevel", orgLevel);
			map.put("secondNode", secondNode);
			map.put("sortFlag2", sortFlag2);//审批池启用sortFlag2排序 added by jipengchun
			List<String> currStatusList = new ArrayList<String>();
			if(StringUtils.isNotEmpty(isHangUp)){//added by jipengchun
				currStatusList.add("1");
				currStatusList.add("2");
			}else{
				currStatusList.add("1");
			}
			map.put("currStatusList", currStatusList);
			if(!"1".equals(specialFlag)){//非特定分配
				if("".equals(currNode)){//自动分件映射情况
					total=allotCommonService.countAllotGroup(map);
					int curNum = 0;
					int curPage = Integer.parseInt(
							String.valueOf(context.getDataMap().get("page") == null ? 0 : context.getDataMap().get("page")));

					int pageNum = Integer.parseInt(
							String.valueOf(context.getDataMap().get("rows") == null ? 0 : context.getDataMap().get("rows")));
					if (curPage > 1) {
						curNum = (curPage - 1) * pageNum;
					}
					map.put("curNum", curNum);
					map.put("pageNum", pageNum);
				}
				groupList=allotCommonService.queryAllotGroupByMap(map);
			}else if("1".equals(specialFlag)){//特定分配
				if("4".equals(orgLevel)){
					map.put("ydjFlag", "2");
				}else if("5".equals(orgLevel)){
					map.put("ydjFlag", "1");
				}
				crediterList=allotCommonService.querySpecialMen(map);
				approverList=allotCommonService.queryApprovers(map);
			}
			if("3".equals(secondNode)&&!"1".equals(specialFlag)){
				AllotCommon allotCommon=new AllotCommon();
				allotCommon=allotCommonService.queryGroupByUserCode(userCode);
				orgNo=allotCommon.getOrgNo();
				orgName=allotCommon.getOrgName();
			}
			context.setData("isSuccess",true);
		} catch (CoreException e) {
			context.setData("isSuccess",false);
			log.error("AllotHandAction.showAllotGroup occur error"+e);
		}
		Map<String, Object> dataMap = new HashMap<String, Object>();
		//当前人组中文及英文名称 申请件数量（非特定征信到特定审批）
		if(!"1".equals(specialFlag)){
			if(currNode!=null&&!"".equals(currNode)){
				if("3".equals(secondNode)){
					for(int i=0;i<groupList.size();i++){
						AllotCommon acm=new AllotCommon();
						acm=groupList.get(i);
						String group=acm.getOrgNo();
						if(group.equals(orgNo)){
							currGroupNum=acm.getOrgNum();
							groupList.remove(i);
							break;
						}else{
							continue;
						}
					}
				}
				dataMap.put("groupList", groupList);
				dataMap.put("orgName", orgName);
				dataMap.put("orgNo", orgNo);
				dataMap.put("currGroupNum", currGroupNum);
			}else{
				dataMap.put("total",total);
				dataMap.put("rows", groupList);
			}
		}else if("1".equals(specialFlag)){//特定征信到特定审批
			dataMap.put("crediterList", crediterList);
			dataMap.put("approverList", approverList);
		}
		context.setDataMap(dataMap);
	}
	/**
	 * @Description:根据组编号  组员查询
	 * @author 王德彬
	 * @version  V1.0
	 * @param context  
	 */
	public void showAllotUser(Context context){
		//自动分件标识
		String auto=(String)context.getData("auto")==null?"":(String)context.getData("auto");
		//当前操作人
		String userCode=(String)context.getData("userCode")==null?"":(String)context.getData("userCode");
		//当前环节
		String currNode=(String)context.getData("node")==null?"":(String)context.getData("node");
		//标准卡或易达金
		String ydjFlag=(String)context.getData("ydjFlag")==null?"":(String)context.getData("ydjFlag");
		//当前组级别  4  5  6
		String orgLevel=(String)context.getData("orgLevel")==null?"":(String)context.getData("orgLevel");
		//自动分件规则传过来
		String groupCode=(String)context.getData("groupCode")==null?"":(String)context.getData("groupCode");
		//分件方式     特定征信到特定审批
		String specialFlag=(String)context.getData("specialFlag")==null?"":(String)context.getData("specialFlag");
		//当前  池  队列   组 
		String secondNode=(String)context.getData("secondNode")==null?"":(String)context.getData("secondNode");
		//是否需要加挂起标志 added by jipengchun 
		String isHangUp=(String)context.getData("isHangUp")==null?"":(String)context.getData("isHangUp");
		//普通分件组成员 或自动分件组员参与表  或自动分件规则人员查询(目前不使用)
		List<AllotCommon> userList=new ArrayList<AllotCommon>();
		//特定征信人员
		List<AllotCommon> crediterList= new ArrayList<AllotCommon>();
		//特定审批人员
		List<AllotCommon> approverList=new ArrayList<AllotCommon>();
		try {
			AllotCommon allotCommon=new AllotCommon();
			if(!"".equals(userCode)){
				//根据userCode查询组信息
				allotCommon=allotCommonService.queryGroupByUserCode(userCode);
			}else if(!"".equals(groupCode)){
				//自动分件规则 根据组查组员
				allotCommon=allotCommonService.queryGroupByOrgNo(groupCode);
			}
			String level="";
			Map<String,Object> userMap=new HashMap<String,Object>();
			userMap.put("ydjFlag", ydjFlag);
			userMap.put("currNode", currNode);
			userMap.put("auto", auto);
			userMap.put("isHangUp", isHangUp);//added by jipengchun 
			userMap.put("secondNode", secondNode);
			//根据组查询 该环节所有的组员
			if(allotCommon!=null){
				//判断组员是否在该环节的组
				level=allotCommon.getOrgLevel();
				if(level.equals(orgLevel)&&!"1".equals(specialFlag)){//非特定征信到审批
					userList=allotCommonService.queryAllotUserByOrgId(userMap,allotCommon);
				}else if(level.equals(orgLevel)&&"1".equals(specialFlag)){//特定征信到审批
					approverList=allotCommonService.queryAllotUserByOrgId(userMap,allotCommon);
					userMap.put("currGroup", allotCommon.getOrgNo());
					userMap.put("secondNode",secondNode);
					crediterList=allotCommonService.querySpecialMen(userMap);
				}
			}
		} catch (CoreException e) {
			context.setData("exMsg",e.getMessage());
			log.error("RuleAction.showAllotUser occur error"+e);
		}
		Map<String, Object> dataMap = new HashMap<String, Object>();
		if("1".equals(specialFlag)){
			dataMap.put("approverList",approverList);
			dataMap.put("crediterList",crediterList);
		}else if(!"1".equals(specialFlag)){
			if(!"".equals(groupCode)||"1".equals(auto)){//自动分件组员列表
				dataMap.put("total",userList.size());
				dataMap.put("rows", userList);
			}else{//手动分件组员
				dataMap.put("userList", userList);
			}
		}
		context.setDataMap(dataMap);
	}
	/**
	* @Description:  原件管理岗   池中标准卡待分配件查询及按三种方式分配 到  队列或组
	* @author 王德彬
	* @version  V1.0
	* @param context  
	 * @throws CoreException 
	 */
	public void handQueueOrGroup(Context context) {
		//分件方式
		int id = Integer.parseInt((String) context.getData("id"));
		//节点
		String node = context.getData("node")!=null?(String)context.getData("node"):"";
		String secondNode = context.getData("secondNode")!=null?(String)context.getData("secondNode"):"";
		String ydjFlag = context.getData("ydjFlag")!=null?(String)context.getData("ydjFlag"):"";
		String userCode = (String) context.getData("userCode")!=null?(String)context.getData("userCode"):"";
		Map<String, Object> dataMap = new HashMap<String, Object>();
		Map<String, Object> lifeMap = new HashMap<String, Object>();
		List<String> currStatusList=new  ArrayList<String>();
		if("1".equals(secondNode)){
			currStatusList.add("0");
		}else if("2".equals(secondNode)){
			currStatusList.add("6");
		}else if("3".equals(secondNode)){
			currStatusList.add("1");
		}
		lifeMap.put("flag", "6");
		lifeMap.put("currStatusList", currStatusList);
		lifeMap.put("currNode", node);
		lifeMap.put("secondNode", secondNode);
		lifeMap.put("ydjFlag", ydjFlag);
		lifeMap.put("userCode", userCode);
		List<AllotApply> applyList =new ArrayList<AllotApply>();
		//总数  (变化中的)
		int total=0;
		//总数  (不变化的直至此次分件结束)
		int sum=0;
		//新修改====
		String queueInfo2="";
		String approvalCode=null;
		AllotCommon approvalCommon=null;
		if(id==1||id==2){//按件数或比例
			// 查询要分配的件数
			dataMap=shareCommon(context);
			applyList =(List<AllotApply>) dataMap.get("appList");
			//本次分配随分配变化的总数
			total=(Integer) dataMap.get("total");
			//本次分配开始的总件数
			sum=(Integer) dataMap.get("total");
			//新修改====
			queueInfo2=(String) dataMap.get("queueInfo2");
		}else if(id==4){//特定征信到特定审批
			//特定征信人员  
			String crediterCode = context.getData("crediterCode")!=null?(String)context.getData("crediterCode"):"";
			//特定审批人员
			approvalCode = (String) context.getData("approvalCode");
			//  查特定审批人员组  组中文  及 姓名
			Map<String,Object> userMap=new HashMap<String,Object>();
			userMap.put("userCode", approvalCode);
			lifeMap.put("currUser", crediterCode);
			lifeMap.put("specialMen", "yes");
			try {
				approvalCommon=allotCommonService.queryUser(userMap);
				if("3".equals(secondNode)){
					lifeMap.put("currGroup", approvalCommon.getOrgNo());
				}
				applyList=allotApplyService.queryAllotApplyByUser(lifeMap);
			} catch (CoreException e) {
				log.error("特定征信到特定审批异常：", e);
			}
		}
		dataMap.clear();
		//本次分件完成后的分配实际总数
		int allotTotal = 0;
		try {
		// 按件数或 百分比分配
		if (id == 1 || id == 2) {
			int result=0;
			//总分配比或总件数
			int totalWeight=0;
			String currName="";
			String value="";
			if("2".equals(ydjFlag)){
				if(id==1){
					value = context.getData("queueInfo");
				}else if(id ==2){
					value=queueInfo2;
				}
			}else{
				value = context.getData("queueInfo");
			}
			String[] eums = value.split(";");
			//采用百分比分配时统计百分数 估算是否所有都分配    按件数提前校验申请件是否够数量
			if(eums.length!=0){
				for(int i=0;i<eums.length;i++){
					String pingEum=eums[i];
					String[] pingChr = pingEum.split(":");
					totalWeight=totalWeight+Integer.parseInt(pingChr[1].trim());
				}
			}
			Date sqlDate=new Date();
			for (int k=0;k<eums.length;k++) {
				//按件数申请件校验
				if(totalWeight>sum&&id == 1){
					context.setData("exMsg","分件发生冲突,请稍后再试!");
					break;
				}
				//申请件详细信息集合
				List<AllotApply> allotApplyList=new ArrayList<AllotApply>();
				//申请件编号集合  
				List<String> lifeList=new ArrayList<String>();
				String eum=eums[k];
				int num = 0;
				int count = 0;
				if (eum == null || "".equals(eum)) {
					continue;
				}
				String[] chr = eum.split(":");
				String code = chr[0];
				//查询中文队列名或组名  
				if("02".equals(node)&&"2".equals(ydjFlag)&&"1".equals(secondNode)){//标准卡征信池
					AllotQueue allotQueue=allotQueueService.queryAllotQueueByCode(code);
					if(allotQueue!=null){
						currName=allotQueue.getQueueName();
					}
				}else {//易达金 或欺诈  或标准卡 录入 审批  
					if("1".equals(secondNode)||"2".equals(secondNode)){//池分件  
						AllotCommon allotCommon=allotCommonService.queryGroupByOrgNo(code);
						if(allotCommon!=null){
							currName=allotCommon.getOrgName();
						}
					}else if("3".equals(secondNode)){
						Map<String,Object> userMap=new HashMap<String,Object>();
						userMap.put("userCode", code);
						//组分件  查中文名
						AllotCommon allotCommon=allotCommonService.queryUser(userMap);
						if(allotCommon!=null){
							currName=allotCommon.getUserName();
						}
					}
				}
				lifeMap.put("code", code);
				// 件数
				if("2".equals(ydjFlag)){
					num = Integer.parseInt(chr[1].trim());
				}else{
					if (id == 1) {
						num = Integer.parseInt(chr[1].trim());
					}else if(id == 2){
						int weight = 0;
						weight=Integer.parseInt(chr[1].trim());
						//当百分数总和为100%,最后对象将分配到剩余全部件(奇数情况按比例分配有剩余)
						if(totalWeight>=100&&k==(eums.length-1)){
							num =sum-allotTotal;
						}else{
							num = (int) (sum * weight * 0.01);
						}
						if (num < 1) {
							num = 1;
						}
					}
				}
				if(num<=total){
					while(num>0 && total>0){
						int bin=0;
						AllotApply apply=new AllotApply();
						apply=applyList.get(num-1);
						
						if("1".endsWith(ydjFlag)||"".equals(ydjFlag)){// 易达金或者  反欺诈分件 需要易达金套卡判断
							String appNo=apply.getAppNo();
							//查询待分配件数
							lifeMap.put("appId", appNo);
							int appNum = allotApplyService.queryHandCount(lifeMap);
							AllotApply allotApply=new AllotApply();
							//套卡区分
							if(appNum==1){//该环节只有一件(标准卡1件放行   易达金除了捞件情况 其他单件不分  )
								allotApply=allotApplyService.queryHandByAppId(lifeMap);
								allotApply.setLstTeamDate(sqlDate);
								String appId=allotApply.getAppId();
								lifeList.add(appId);
								//逆向标识 1和2 捞件两种情况  只有一件时候 可以分下去
								//易达金标识
								String  yf=allotApply.getYdjFlag();
								//套卡标识
								String matchingCardFlag=allotApply.getMatchingCardFlag();
								//捞件情况
								String laoJianFlag=allotApply.getLaoJianFlag();
								//退回标识 back_flag 1 审批退回 2 转上级  4 退回征信  都可以单件操作
								String backFlag=allotApply.getBackFlag();
								List<String> backFlagList=new ArrayList<String>();
								backFlagList.add("1");
								backFlagList.add("2");
								backFlagList.add("4");
								if("0".equals(matchingCardFlag)||(("1".equals(matchingCardFlag)||"2".equals(matchingCardFlag))&&"1".equals(ydjFlag)
										&&("01".equals(laoJianFlag)||"02".equals(laoJianFlag)||backFlagList.contains(backFlag)))
										||"".equals(ydjFlag)){//非易达金套卡 或者 捞件或者反欺诈件
									if ("02".equals(node)&&"1".equals(secondNode)&&"2".equals(yf)) {// 标准卡征信池
										allotApply.setCurrQueue(code);
										allotApply.setLastQueue(currName);
										allotApply.setQueueDate(sqlDate);
										allotApply.setCurrStatus("6");
									}else if("3".equals(secondNode)){//
										allotApply.setCurrUser(code);
										allotApply.setLastUser(code);
										allotApply.setCurrUserName(currName);
										allotApply.setUserDate(sqlDate);
										allotApply.setCurrStatus("3");
										allotApply.setSynFlag("0");
										//批量标签变为0
										if("01".equals(node)){
											allotApply.setBatchOperateFlag("0");
										}else if("02".equals(node)){
											allotApply.setBatchCreditFlag("0");
										}else if("03".equals(node)){
											allotApply.setBatchApprovalFlag("0");
										}
									}else {// 易达金征信  审批 
										allotApply.setCurrGroup(code);
										allotApply.setLastGroup(currName);
										allotApply.setGroupTeamDate(sqlDate);
										if("01".equals(node)){// 审查 分组日期变    欺诈  审批  归档 不更改
											allotApply.setGroupDate(sqlDate);
										}else if("02".equals(node)){//征信分组日期存在 不变  没有  赋值
											if(allotApply.getGroupDate()==null){
												allotApply.setGroupDate(sqlDate);
											}
										}
										allotApply.setCurrStatus("1");
									}
									allotApply.setCurrNode(node);
									allotApplyList.add(allotApply);
									bin=1;
								}else{
									bin=0;
								}
							}else if(appNum==2){//套卡(算一件)
								List<AllotApply> appList = allotApplyService.queryHand(lifeMap);
								for(AllotApply app:appList){
									app.setLstTeamDate(sqlDate);
									String appId=app.getAppId();
									lifeList.add(appId);
									String yf=app.getYdjFlag();
									if ("02".equals(node)&&"1".equals(secondNode)&&"2".equals(yf)) {// 标准卡征信池
										app.setCurrQueue(code);
										app.setLastQueue(currName);	
										app.setQueueDate(sqlDate);
										app.setCurrStatus("6");
									}else if("3".equals(secondNode)){//
										app.setCurrUser(code);
										app.setLastUser(code);
										app.setCurrUserName(currName);
										app.setUserDate(sqlDate);
										app.setCurrStatus("3");
										app.setSynFlag("0");
										//批量标签变为0
										if("01".equals(node)){
											allotApply.setBatchOperateFlag("0");
										}else if("02".equals(node)){
											allotApply.setBatchCreditFlag("0");
										}else if("03".equals(node)){
											allotApply.setBatchApprovalFlag("0");
										}
									}else {//  易达金征信  审批 
										app.setCurrGroup(code);
										app.setLastGroup(currName);
										app.setGroupTeamDate(sqlDate);
										if("01".equals(node)){// 审查 分组日期变    欺诈  审批  归档 不更改
											app.setGroupDate(sqlDate);
										}else if("02".equals(node)){//征信分组日期存在 不变  没有  赋值(保留最原始分组日期)
											if(app.getGroupDate()==null){
												app.setGroupDate(sqlDate);
											}
										}
										app.setCurrStatus("1");
									}
									app.setCurrNode(node);
									allotApplyList.add(app);
									bin=1;
								}
							}
						}else if("2".endsWith(ydjFlag)){//标准卡分件 
							String appId=apply.getAppId();
							lifeList.add(appId);
							String currStatus=apply.getCurrStatus();
							if(currStatusList.contains(currStatus)){
								if ("02".equals(node)&&"1".equals(secondNode)) {// 标准卡征信池
									apply.setCurrQueue(code);
									apply.setLastQueue(currName);
									apply.setQueueDate(sqlDate);
									apply.setCurrStatus("6");
								}else if("3".equals(secondNode)){//标准卡  审查  征信  审批  组到人
									apply.setCurrUser(code);
									apply.setLastUser(code);
									apply.setCurrUserName(currName);
									apply.setUserDate(sqlDate);
									apply.setCurrStatus("3");
									apply.setSynFlag("0");
									//批量标签变为0
									if("01".equals(node)){
										apply.setBatchOperateFlag("0");
									}else if("02".equals(node)){
										apply.setBatchCreditFlag("0");
									}else if("03".equals(node)){
										apply.setBatchApprovalFlag("0");
									}
								}else {//  标准卡  审查 审批 池到组 或者 征信 队列到组
									apply.setCurrGroup(code);
									apply.setLastGroup(currName);
									apply.setGroupTeamDate(sqlDate);
									if("01".equals(node)){//审查  分组日期变    欺诈  审批  归档 不更改
										apply.setGroupDate(sqlDate);
									}else if("02".equals(node)){//征信分组日期存在 不变  没有  赋值(保留最原始分组日期)
										if(apply.getGroupDate()==null){
											apply.setGroupDate(sqlDate);
										}
									}
									apply.setCurrStatus("1");
								}
								apply.setCurrNode(node);
								allotApplyList.add(apply);
								bin=1;
							}
						}
						if(bin==1){
							//实际分配
							count++;
							applyList.remove(num-1);// 已分配的就从待分配的任务件数中拿掉
							//减去一件
							num--;
							total--;
						}else if(bin==0){
							applyList.remove(num-1);// 将不符合的就从待分配的任务件数中拿掉
							total--;
						}
					}
				}
				//修改逻辑每次最多传1万件进行更新操作
				if(allotApplyList.size()>0){
						//二期更改分批次更新
						result=updateBatchList(allotApplyList, lifeList, lifeMap);
						lifeList.clear();
						allotApplyList.clear();
				}
					// 已分配总件数
					allotTotal += count;
				}
				if(result==0){
					log.info("AllotHandAction.handQueueOrGroup 分件失败");
					context.setData("allotTotal", 0);
					context.setData("isSuccess",false);
					context.setData("exMsg","分件发生冲突，请稍后再试");
				}else{
					log.info("AllotHandAction.handQueueOrGroup 成功分件");
					context.setData("allotTotal", allotTotal);
					context.setData("isSuccess",true);
				}
		} 
		else if (id == 3) {
			String appId = (String) context.getData("appId");
			String code = (String) context.getData("code");
			List<String> lifeList=new ArrayList<String>();
			lifeMap.put("code", code);
			int count = 0;
			int result=0;
			if(appId.trim().length()==15||appId.trim().length()==16){
				String appNo="";
				if(appId.trim().length()==15){
					appNo=appId.trim().toString().toUpperCase();
				}else if(appId.trim().length()==16){
					appNo=appId.trim().toString().substring(0, 15).toUpperCase();
				}
				//分件查询是否该申请件被中断 
				List<String> stopFlagList=qualityCheckingService.queryStopFlagByAppId(appNo);
				if(stopFlagList.contains("2")){
					context.setData("isSuccess",false);
					context.setData("exMsg","该申请件或其套卡为质检叫停状态,不能分件");
				}else{
					lifeMap.put("appId", appNo);
					count = allotApplyService.queryHandCount(lifeMap);
					//队列或组中文名称
					String currName="";
					//申请件所在组
					String orgGroup="";
					//要分配到组员的组
					String zu="";
					if (count == 1) {//非套卡件
						AllotApply allotApply=new AllotApply();
						allotApply=allotApplyService.queryHandByAppId(lifeMap);
						//如果是组分件  判断该件所在的组  与要分配到的人的组是否相同
						if("3".equals(secondNode)){
							orgGroup=allotApply.getCurrGroup()==null?"":allotApply.getCurrGroup();
							AllotCommon userGroup=allotCommonService.queryGroupByUserCode(code);
							if(userGroup!=null){
								zu=userGroup.getOrgNo();
							}
						}
						lifeList.add(appId);
						//当前环节
						String currNode=allotApply.getCurrNode();
						//当前分配状态
						String curr=allotApply.getCurrStatus();
						//当前处理状态
						String del=allotApply.getDelStatus();
						//易达金标识
						String yf=allotApply.getYdjFlag();
						//套卡标识
						String mcf=allotApply.getMatchingCardFlag();
						//捞件标识  1和2 捞件两种情况  只有一件时候 可以分下去
						String  laoJianFlag=allotApply.getLaoJianFlag();
						//退回标识 back_flag 1 审批退回 2 转上级  4 退回征信  都可以单件操作
						String backFlag=allotApply.getBackFlag();
						List<String> backFlagList=new ArrayList<String>();
						backFlagList.add("1");
						backFlagList.add("2");
						backFlagList.add("4");
						if(("0".equals(del)||"2".equals(del)||"3".equals(del))&&currNode.equals(node)&&zu.equals(orgGroup)
								&&(currStatusList.contains(curr))&&(("2".equals(yf))||
								("1".equals(yf)&&"0".equals(mcf))||("01".equals(laoJianFlag)||"02".equals(laoJianFlag)||backFlagList.contains(backFlag)))){//判断此件处于该环节 且为未完成未挂起件
							Date sqlDate=new Date();
							allotApply.setLstTeamDate(sqlDate);
							allotApply.setCurrNode(node);
							if ("02".equals(node)&&"2".equals(yf)
									&&"1".equals(secondNode)&&(currStatusList.contains(curr))) {// 标准卡征信池到队列
								allotApply.setCurrQueue(code);
								AllotQueue allotQueue=allotQueueService.queryAllotQueueByCode(code);
								if(allotQueue!=null){
									currName=allotQueue.getQueueName();
								}
								allotApply.setLastQueue(currName);	
								allotApply.setQueueDate(sqlDate);
								allotApply.setCurrStatus("6");
							}else if("3".equals(secondNode)&&"02".equals(node)&&"2".equals(yf)&&(currStatusList.contains(curr))
									){//标准卡 征信 组到组员
								allotApply.setCurrUser(code);
								allotApply.setLastUser(code);
								AllotCommon allotCommon=allotCommonService.queryGroupByUserCode(code);
								if(allotCommon!=null){
									currName=allotCommon.getUserName();
								}
								allotApply.setCurrUserName(currName);
								allotApply.setUserDate(sqlDate);
								allotApply.setCurrStatus("3");
								allotApply.setSynFlag("0");
								//批量标签变为0
								if("01".equals(node)){
									allotApply.setBatchOperateFlag("0");
								}else if("02".equals(node)){
									allotApply.setBatchCreditFlag("0");
								}else if("03".equals(node)){
									allotApply.setBatchApprovalFlag("0");
								}
							}else if("3".equals(secondNode)&&(currStatusList.contains(curr))&&
									(("01".equals(node)&&"2".equals(yf))||"03".equals(node)||"05".equals(node)||("02".equals(node)&&"1".equals(yf)))
									){//审查   审批  组到组员
								allotApply.setCurrUser(code);
								allotApply.setLastUser(code);
								AllotCommon allotCommon=allotCommonService.queryGroupByUserCode(code);
								if(allotCommon!=null){
									currName=allotCommon.getUserName();
								}
								allotApply.setCurrUserName(currName);
								allotApply.setUserDate(sqlDate);
								allotApply.setCurrStatus("3");
								allotApply.setSynFlag("0");
								//批量标签变为0
								if("01".equals(node)){
									allotApply.setBatchOperateFlag("0");
								}else if("02".equals(node)){
									allotApply.setBatchCreditFlag("0");
								}else if("03".equals(node)){
									allotApply.setBatchApprovalFlag("0");
								}
							}else if((("01".equals(node)||"03".equals(node)||"05".equals(node))
									&&"1".equals(secondNode)&&(currStatusList.contains(curr)))
									||("02".equals(node)&&"2".equals(yf)&&"2".equals(secondNode)&&(currStatusList.contains(curr)))
									||("02".equals(node)&&"1".equals(yf)&&(currStatusList.contains(curr)))
									){//易达金征信 审批   标准卡  审批 审查 池到组 及队列到组
								allotApply.setCurrGroup(code);
								AllotCommon allotCommon=allotCommonService.queryGroupByOrgNo(code);
								if(allotCommon!=null){
									currName=allotCommon.getOrgName();
								}
								allotApply.setLastGroup(currName);
								allotApply.setGroupTeamDate(sqlDate);
								if("01".equals(node)||"02".equals(node)){//征信 审查 分组日期变    欺诈  审批  归档 不更改
									allotApply.setGroupDate(sqlDate);
								}
								allotApply.setCurrStatus("1");
							}
							//组装数据
							lifeMap.put("lifeList", lifeList);
							lifeMap.put("app", allotApply);
							lifeMap.put("diaoliu", "yes");
							result=allotMethodService.updateMethod(lifeMap);
							lifeList.clear();
							if(result==0){
								log.info("AllotHandAction.handQueueOrGroup 分件失败");
								context.setData("isSuccess",false);
								context.setData("exMsg","该件分配跨节点,不符合规则");
							}else{
								allotTotal = allotTotal + 1;
								log.info("AllotHandAction.handQueueOrGroup 成功分件");
								context.setData("allotTotal", allotTotal);
								context.setData("isSuccess",true);
							}
						}else{
							if(!currNode.equals(node)){
								context.setData("exMsg","该件不处于该环节");
							}else if(!currStatusList.contains(curr)){
								if("5".equals(curr)||"2".equals(curr)||"4".equals(curr)||"7".equals(curr)){
									context.setData("exMsg","申请件已挂起,不得分配");
								}else{
									if("1".equals(curr)){
										context.setData("exMsg","申请件已分到组,不得分配");
									}else if("6".equals(curr)){
										context.setData("exMsg","申请件已分到队列,不得分配");
									}else if("3".equals(curr)){
										context.setData("exMsg","申请件已分到人,不得分配");
									}
								}
							}else if(!"0".equals(del)&&!"2".equals(del)&&!"3".equals(del)){
								context.setData("exMsg","该件不是未完成件或者补件的件或者退回件");
							}else if("1".equals(mcf)){
								if("1".equals(mcf)){
									context.setData("exMsg","易达金套卡只有配发卡");
								}else if("2".equals(mcf)){
									context.setData("exMsg","易达金套卡只有主卡");
								}
							}else if(!zu.equals(orgGroup)){
								context.setData("exMsg","该申请件所在组与当前操作员不在一个组");
							}
							log.info("AllotHandAction.handQueueOrGroup 该件不处于该环节或不是未完成未挂起件或分配跨节点");
							context.setData("isSuccess",false);
							
						}
					} else if (count >1) {//套卡件
						List<AllotApply> appList = allotApplyService.queryHand(lifeMap);
						Date sqlDate=new Date();
						for (int i = 0; i < appList.size(); i++) {
							AllotApply allotApply=new AllotApply();
							allotApply= appList.get(i);
							//如果是组分件  判断该件所在的组  与要分配到的人的组是否相同
							if("3".equals(secondNode)){
								orgGroup=allotApply.getCurrGroup()==null?"":allotApply.getCurrGroup();
								AllotCommon userGroup=allotCommonService.queryGroupByUserCode(code);
								if(userGroup!=null){
									zu=userGroup.getOrgNo();
								}
							}
							String appId2=allotApply.getAppId();
							lifeList.add(appId2);
							String currNode=allotApply.getCurrNode();
							String curr=allotApply.getCurrStatus();
							String del=allotApply.getDelStatus();
							String yf= allotApply.getYdjFlag();
							if(("0".equals(del)||"2".equals(del)||"3".equals(del))&&currNode.equals(node)
									&&(currStatusList.contains(curr))&&zu.equals(orgGroup)){//判断此件处于该环节 且为未完成未挂起件
								allotApply.setLstTeamDate(sqlDate);
								allotApply.setCurrNode(node);
								if ("02".equals(node)&&"2".equals(yf)
										&&"1".equals(secondNode)&&(currStatusList.contains(curr))) {// 标准卡征信池到队列
									allotApply.setCurrQueue(code);
									AllotQueue allotQueue=allotQueueService.queryAllotQueueByCode(code);
									if(allotQueue!=null){
										currName=allotQueue.getQueueName();
									}
									allotApply.setLastQueue(currName);	
									allotApply.setQueueDate(sqlDate);
									allotApply.setCurrStatus("6");
								}else if("3".equals(secondNode)&&"02".equals(node)&&
										(currStatusList.contains(curr))){//标准卡 征信 组到组员
									allotApply.setCurrUser(code);
									allotApply.setLastUser(code);
									AllotCommon allotCommon=allotCommonService.queryGroupByUserCode(code);
									if(allotCommon!=null){
										currName=allotCommon.getUserName();
									}
									allotApply.setCurrUserName(currName);
									allotApply.setUserDate(sqlDate);
									allotApply.setCurrStatus("3");
									allotApply.setSynFlag("0");
									//批量标签变为0
									if("01".equals(node)){
										allotApply.setBatchOperateFlag("0");
									}else if("02".equals(node)){
										allotApply.setBatchCreditFlag("0");
									}else if("03".equals(node)){
										allotApply.setBatchApprovalFlag("0");
									}
								}else if("3".equals(secondNode)&&(currStatusList.contains(curr))&&
										(("01".equals(node)&&"2".equals(yf))||"03".equals(node)||"05".equals(node)||("02".equals(node)&&"1".equals(yf)))
										){//审查   审批  组到组员
									allotApply.setCurrUser(code);
									allotApply.setLastUser(code);
									AllotCommon allotCommon=allotCommonService.queryGroupByUserCode(code);
									if(allotCommon!=null){
										currName=allotCommon.getUserName();
									}
									allotApply.setCurrUserName(currName);
									allotApply.setUserDate(sqlDate);
									allotApply.setCurrStatus("3");
									allotApply.setSynFlag("0");
									//批量标签变为0
									if("01".equals(node)){
										allotApply.setBatchOperateFlag("0");
									}else if("02".equals(node)){
										allotApply.setBatchCreditFlag("0");
									}else if("03".equals(node)){
										allotApply.setBatchApprovalFlag("0");
									}
								}else if((("01".equals(node)||"03".equals(node)||"05".equals(node))
										&&"1".equals(secondNode)&&(currStatusList.contains(curr)))
										||("02".equals(node)&&"2".equals(secondNode)&&(currStatusList.contains(curr)))
										||("02".equals(node)&&"1".equals(yf)&&(currStatusList.contains(curr)))
										){//易达金征信 审批   标准卡  审批 审查 池到组 及队列到组
									allotApply.setCurrGroup(code);
									AllotCommon allotCommon=allotCommonService.queryGroupByOrgNo(code);
									if(allotCommon!=null){
										currName=allotCommon.getOrgName();
									}
									allotApply.setLastGroup(currName);
									allotApply.setGroupTeamDate(sqlDate);
									if("01".equals(node)||"02".equals(node)){//征信 审查 分组日期变    欺诈  审批  归档 不更改
										allotApply.setGroupDate(sqlDate);
									}
									allotApply.setCurrStatus("1");
								}
								//组装数据
								lifeMap.put("lifeList", lifeList);
								lifeMap.put("app", allotApply);
								//套卡只有一件要调工作流
								if(i==1){
									lifeMap.put("diaoliu", "yes");
								}
								result=allotMethodService.updateMethod(lifeMap);
								lifeList.clear();
								if(result==0){
									log.info("AllotHandAction.handQueueOrGroup 分件失败");
									context.setData("isSuccess",false);
									context.setData("exMsg","该件分配跨节点,不符合规则");
									break;
								}else{
									count--;
									log.info("AllotHandAction.handQueueOrGroup 成功分件");
									context.setData("isSuccess",true);
								}
							}else{
								if(!currNode.equals(node)){
									context.setData("exMsg","该件不处于该环节");
								}else if(!currStatusList.contains(curr)){
									if("5".equals(curr)||"2".equals(curr)||"4".equals(curr)||"7".equals(curr)){
										context.setData("exMsg","申请件已挂起,不得分配");
									}else{
										if("1".equals(curr)){
											context.setData("exMsg","申请件已分到组,不得分配");
										}else if("6".equals(curr)){
											context.setData("exMsg","申请件已分到队列,不得分配");
										}else if("3".equals(curr)){
											context.setData("exMsg","申请件已分到人,不得分配");
										}
									}
								}else if(!"0".equals(del)&&!"2".equals(del)&&!"3".equals(del)){
									context.setData("exMsg","该件不是未完成件或者补件的件或者退回件");
								}else if(!zu.equals(orgGroup)){
									context.setData("exMsg","该申请件所在组与当前操作员不在一个组");
								}
								log.info("AllotHandAction.handQueueOrGroup 该件不处于该环节或不是未完成未挂起件或分配跨节点");
								context.setData("isSuccess",false);
								break;
							}
						}
					}else if(count==0){
						int number=0;
						number=allotApplyService.queryHandCount(appNo);
						if(number>0){
							List<AllotApply> appList = allotApplyService.queryApplyListByAppId(appNo);
							AllotApply allotApply=appList.get(0);
							String currNode=allotApply.getCurrNode();
							String curr=allotApply.getCurrStatus();
							String del=allotApply.getDelStatus();
							if(!currNode.equals(node)){
								context.setData("exMsg","该件不处于该环节");
							}else if(!currStatusList.contains(curr)){
								if("5".equals(curr)||"2".equals(curr)||"4".equals(curr)||"7".equals(curr)){
									context.setData("exMsg","申请件已挂起,不得分配");
								}else{
									if("1".equals(curr)){
										context.setData("exMsg","申请件已分到组,不得分配");
									}else if("6".equals(curr)){
										context.setData("exMsg","申请件已分到队列,不得分配");
									}else if("3".equals(curr)){
										context.setData("exMsg","申请件已分到人,不得分配");
									}
								}
							}else if(!"0".equals(del)&&!"2".equals(del)&&!"3".equals(del)){
								context.setData("exMsg","该件不是未完成件或者补件的件或者退回件");
							}
						}else{
							context.setData("exMsg","该件不存在");
						}
					}
				}
			
			}else{
				log.info("AllotHandAction.handQueueOrGroup 无该申请件");
				context.setData("isSuccess",false);
				context.setData("exMsg","无该申请件");
			}
			if(result==0){
				context.setData("allotTotal", "0");
			}else{
				context.setData("allotTotal", "1");
			}
		}else if(id == 4){//特定征信人员到特定审批人员  只有审批环节具有该功能
			//本次要分给特定审批人员件数
			int approvalNum =Integer.parseInt((String) context.getData("approvalNum"));
			//申请件详细信息集合
			List<AllotApply> allotApplyList=new ArrayList<AllotApply>();
			//申请件编号集合  
			List<String> lifeList=new ArrayList<String>();
			Date nowDate=new Date();
			int count=0;
			if("1".equals(ydjFlag)){//易达金卡
				for(int k=0;k<approvalNum;k++){
					AllotApply apply=applyList.get(k);
					String appNo=apply.getAppId().substring(0,15);
					lifeMap.put("appId", appNo);
					count = allotApplyService.queryHandCount(lifeMap);
					if(count==1){//只有一件
						AllotApply allotApply=allotApplyService.queryHandByAppId(lifeMap);
						allotApply.setCurrGroup(approvalCommon.getOrgNo());
						allotApply.setLastGroup(approvalCommon.getOrgName());
						allotApply.setCurrUser(approvalCode);
						allotApply.setCurrUserName(approvalCommon.getUserName());
						allotApply.setCurrStatus("3");
						allotApply.setDelStatus("0");
						Date groupDate=apply.getGroupDate();
						if(groupDate==null){
							allotApply.setGroupDate(nowDate);
						}
						allotApply.setLstTeamDate(nowDate);
						allotApply.setGroupTeamDate(nowDate);
						allotApply.setUserDate(nowDate);
						lifeList.add(allotApply.getAppId());
						allotApplyList.add(allotApply);
						allotTotal++;
					}else if(count==2){//两件
						List<AllotApply> appList = allotApplyService.queryHand(lifeMap);
						for(AllotApply app:appList){
							app.setCurrGroup(approvalCommon.getOrgNo());
							app.setLastGroup(approvalCommon.getOrgName());
							app.setCurrUser(approvalCode);
							app.setCurrUserName(approvalCommon.getUserName());
							app.setCurrStatus("3");
							app.setDelStatus("0");
							Date groupDate=apply.getGroupDate();
							if(groupDate==null){
								app.setGroupDate(nowDate);
							}
							app.setLstTeamDate(nowDate);
							app.setGroupTeamDate(nowDate);
							app.setUserDate(nowDate);
							lifeList.add(app.getAppId());
							allotApplyList.add(app);
						}
						allotTotal++;
					}else{
						continue;
					}
				}
			}else if("2".equals(ydjFlag)){//标准卡
				for(int k=0;k<approvalNum;k++){
					AllotApply apply=applyList.get(k);
					String appId=apply.getAppId();
					apply.setCurrGroup(approvalCommon.getOrgNo());
					apply.setLastGroup(approvalCommon.getOrgName());
					apply.setCurrUser(approvalCode);
					apply.setCurrUserName(approvalCommon.getUserName());
					apply.setCurrStatus("3");
					apply.setDelStatus("0");
					apply.setAppId(appId);
					Date groupDate=apply.getGroupDate();
					if(groupDate==null){
						apply.setGroupDate(nowDate);
					}
					apply.setLstTeamDate(nowDate);
					apply.setGroupTeamDate(nowDate);
					apply.setUserDate(nowDate);
					lifeList.add(appId);
					allotApplyList.add(apply);
					allotTotal++;
				}
			}
			if(allotApplyList.size()>0){
				//组装数据
				lifeMap.put("code", approvalCode);
				lifeMap.put("lifeList", lifeList);
				lifeMap.put("allotApplyList", allotApplyList);
				//分件
				allotMethodService.updateBatchMethod(lifeMap);
				lifeList.clear();
				allotApplyList.clear();
				context.setData("allotTotal", allotTotal);
				context.setData("isSuccess",true);
		    }
		}
		} catch (Exception e) {
			log.error("AllotHandAction.handQueueOrGroup occur error" + e);
			context.setData("exMsg","系统繁忙,请稍后再试");
		}
	}
	/**
	* @Description: 转换 double
	* @author 王德彬
	* @version  V1.1
	* @param context  
	 */
	public int doubleToint(String[] chr,int sum){
		int num=0;
		String str=chr[1].trim();
		String str2=str.substring(0, str.indexOf("."))+str.substring(str.indexOf(".")+1);
		int weight=Integer.parseInt(str2);
		num = (int) (sum * weight * 0.001);
		return num;
	}
	
	/**
	 * 分件数量处理
	 * @param weight
	 * @param sum
	 * @return
	 */
	public int dealint(int weight,int sum){
		double pjnum=sum*weight*0.01;
		BigDecimal b=new BigDecimal(pjnum);
		int pjnum2=(int) (b.setScale(0,RoundingMode.HALF_UP).doubleValue());
		return pjnum2;
	}
	/**
	 * 批量批次更新
	 * @param list、list2
	 * @param lifeMap
	 * @return
	 */
	public int updateBatchList(List<AllotApply> list,List<String> list2,Map<String,Object> lifeMap)
	throws Exception{
		int result=0,end=0,totalWeight=list.size();
		//更新申请件大于一万件多次更新
		if(totalWeight>commitCountEveryTime){
			//需要提交的次数  
			int	commitCount=(int)Math.ceil(totalWeight/(double)commitCountEveryTime);
			for(int i=0;i<commitCount;i++){
				try {
					if(i<commitCount-1){
						end=commitCountEveryTime;
					}else if(i==commitCount-1){
						end=totalWeight-i*commitCountEveryTime;
					}
					//组装数据
					lifeMap.put("lifeList", list2.subList(0, end));
					lifeMap.put("allotApplyList", list.subList(0, end));
					//分件
					result=allotMethodService.updateBatchMethod(lifeMap);
					list2.subList(0, end).clear();
					list.subList(0, end).clear();
				} catch (Exception e) {
					log.error("分件批量批次更新报错", e);
				}
			}
		}else{
			//组装数据
			lifeMap.put("lifeList", list2);
			lifeMap.put("allotApplyList", list);
			//分件
			result=allotMethodService.updateBatchMethod(lifeMap);
		}
		return result;
	}
}
