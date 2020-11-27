package com.huaxia.opas.service.decision.impl;

import java.io.File;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.allot.AllotApplyAllotDao;
import com.huaxia.opas.dao.allot.AllotApplyAllotHisDao;
import com.huaxia.opas.dao.apply.ApplyLifeCicleDao;
import com.huaxia.opas.dao.apply.BizInpApplDao;
import com.huaxia.opas.dao.archive.FileAppDetDao;
import com.huaxia.opas.dao.credit.CreditCheckDao;
import com.huaxia.opas.dao.decision.BizApprovalHisDao;
import com.huaxia.opas.dao.decision.BizApprovalOpeexchisDao;
import com.huaxia.opas.dao.decision.BizApprovalOperatedateDao;
import com.huaxia.opas.dao.decision.BizApprovalOperateexcDao;
import com.huaxia.opas.dao.report.KeyMessageModifyDao;
import com.huaxia.opas.dao.system.ApUserDao;
import com.huaxia.opas.domain.allot.AllotApplyAllot;
import com.huaxia.opas.domain.allot.AllotApplyAllotHis;
import com.huaxia.opas.domain.apply.ApplyLifeCicle;
import com.huaxia.opas.domain.input.BizApprovalOpeexchis;
import com.huaxia.opas.domain.input.BizApprovalOperatedate;
import com.huaxia.opas.domain.input.BizApprovalOperateexc;
import com.huaxia.opas.domain.report.KeyMessageModify;
import com.huaxia.opas.domain.system.ApUser;
import com.huaxia.opas.domain.thirdparty.BizApprovalHis;
import com.huaxia.opas.service.AbstractService;
import com.huaxia.opas.service.decision.SysButtonCommonService;
import com.huaxia.opas.service.workflow.TopbpmService;
import com.huaxia.opas.util.CommonUtil;
import com.huaxia.opas.util.StringUtil;
import com.huaxia.opas.util.Utils;

/**
 * @author xuzhiguo
 * @description isBack = 0;为审批提交，1为待归档收回，
 * 2 征信提交 3 征审审批提交4为退回征信 11征审审批待归档收回 12 征审合一（HY）的转上级或退回审批(向非征审合一角色)
 */
public class SysButtonCommonServiceImpl extends AbstractService implements SysButtonCommonService,Serializable {

	private static Logger logger = LoggerFactory.getLogger(SysButtonCommonServiceImpl.class);

	private static final long serialVersionUID = -8008508483239828116L;
	@Resource(name = "allotApplyAllotDao")
	private AllotApplyAllotDao allotApplyAllotDao;
	@Resource(name = "bizApprovalOperatedateDao")
	private BizApprovalOperatedateDao bizApprovalOperatedateDao;
	@Resource(name = "bizApprovalOperateexcDao")
	private BizApprovalOperateexcDao bizApprovalOperateexcDao;
	@Resource(name = "bizApprovalOpeexchisDao")
	private BizApprovalOpeexchisDao bizApprovalOpeexchisDao;
	@Resource(name = "bizApprovalHisDao")
	private BizApprovalHisDao bizApprovalHisDao;
	@Resource(name = "allotApplyAllotHisDao")
	private AllotApplyAllotHisDao allotApplyAllotHisDao;
	@Resource(name = "keyMessageModifyDao")
	private KeyMessageModifyDao keyMessageModifyDao;
	@Resource(name = "bizInpApplDao")
	private BizInpApplDao bizInpApplDao;
	@Resource(name = "fileAppDetDao")
	private FileAppDetDao fileAppDetDao;
	@Resource(name = "apUserDao")
	private ApUserDao apUserDao;
	@Resource(name = "applyLifeCicleDao")
	private ApplyLifeCicleDao applyLifeCicleDao;
	@Resource(name = "topbpmService")
	private TopbpmService topbpmService;
	@Resource(name = "creditCheckDao")
	private CreditCheckDao creditCheckDaoImp;
	/**
	 * 获得申请件信息
	 */
	@Override
	public AllotApplyAllot queryAllotApplyAllot(String appId) throws Exception {
		return allotApplyAllotDao.selectByPrimaryKey(appId);
	}
	
	/**
	 * 获得申请件信息和其对应的卡产品
	 */
	@Override
	public AllotApplyAllot selectAllotAndAppProdByAppId(String appId) throws Exception {
		return allotApplyAllotDao.selectAllotAndAppProdByAppId(appId);
	}
	
	/**
	 * 保存\提交按钮-业务逻辑处理
	 * 
	 * @throws Exception
	 */
	@Override
	public Map<String,Object> saveOrSubmitButtonDeal(AllotApplyAllot allot, AllotApplyAllotHis allotHis, Map<String, String> map,
			String button, Map<String, String> resMap) throws Exception {
		// step1:当前系统时间
		Date date = new Date();
		int hours = date.getHours();
		int minutes = date.getMinutes();
		// ste1.1设置中午12点、13点时间
		Date date10dian = new Date();//10点
		date10dian.setHours(10);
		date10dian.setMinutes(0);
		date10dian.setSeconds(0);
		Date noon = new Date();// 12点
		noon.setHours(12);
		noon.setMinutes(0);
		noon.setSeconds(0);
		Date afone = new Date();// 13点
		afone.setHours(13);
		afone.setMinutes(0);
		afone.setSeconds(0);
		// step2查询当前操作员当天的最近的上一笔审批作业数据信息
		BizApprovalHis approvalhis = bizApprovalHisDao.selectByAppIdAndUserId(map);
		Date upDate = null, downDate = null;// 参数化配置
		boolean tstime = false;
		//判断当前时间是否是下午五点，若是，则不再记录异常时间
		Date dian_17 = new Date();
		dian_17.setHours(17);
		dian_17.setMinutes(0);
		dian_17.setSeconds(0);
		if(new Date().after(dian_17)){
			logger.info("【当前时间"+new Date()+"已经超过五点了，不再记录异常时间】");
		}else if(new Date().before(date10dian)){
			logger.info("【还不到10点的时候，故而不记录异常时间】");
		}else{
			boolean lean = true;
			BizApprovalOperateexc oldexc = null;
			BizApprovalOpeexchis oldhis = null;
			// step3若数据信息为空，则说明当前点击保存或提交按钮是当天办理的第一笔业务
			if (approvalhis == null || approvalhis.getAppId() == null) {
				lean = false;
				//is first yw
				if(!lean){
					oldexc = bizApprovalOperateexcDao.selectByUserCode(allot.getCurrOptUser());
					oldhis = bizApprovalOpeexchisDao.selectByUserCode(allot.getCurrOptUser());
					if(oldexc==null){
						lean = true;
					}
				}
				// step3.1当天办理的第一笔业务，以10点为准，计算当前业务距离10点的时间段是否超过半个小时
				if (timeCha(date10dian, date,tstime) > 30 && timeCha(date10dian, date,tstime) <= 120) {// 第一笔业务，已经10点半以后,12点前
					upDate = date10dian;
					downDate = date;
				} else if (timeCha(date10dian, date,tstime) > 120 && timeCha(date10dian, date,tstime) <= 180) {// 12-13点之间
					upDate = date10dian;
					downDate = noon;
				} else if(timeCha(date10dian, date,tstime) > 180){// 13点后
					if(lean){
						//首先，先记录10点--12点时间段的异常数据
						addTime(map, date10dian, noon, tstime);
						//其次，记录11点后的异常时间数据
						upDate = afone;
						downDate = date;
					}else{
						if(oldexc.getEnddate().before(noon)){
							//首先，先记录10点--12点时间段的异常数据
							addTime(map, date10dian, noon, tstime);
						}
						//其次，记录11点后的异常时间数据
						upDate = afone;
						downDate = date;
					}
				} 
			} else {// step3.2办理的已经不是第一笔了
				// 上一笔业务的办理时间
				Date beforeDate = approvalhis.getApproveTime();
				// 如果为上午，小于12点,且距离上一笔业务时间已经超过30分钟
				if (timeCha(beforeDate, date,tstime) > 30 && timeCha(beforeDate, noon,tstime) >= 0 && timeCha(date,noon,tstime)>=0) {
					upDate = beforeDate;
					downDate = date;
				} else if (timeCha(noon, date,tstime) >= 60) {// 如果为下午
					if (timeCha(beforeDate, noon,tstime) >= 0) {// 若中午没有干活，下午一点后的第一笔
						if (timeCha(beforeDate, noon,tstime) > 30 ) {// 上午最后一笔到12点时长超过30分钟
							/*upDate = beforeDate;
							downDate = noon;*/
							addTime(map, beforeDate, noon, tstime);
						} 
						if (timeCha(afone, date,tstime) > 30) {// 当前这笔距离1点的长超过30分钟
							upDate = afone;
							downDate = date;
						}
					} else if (timeCha(beforeDate, noon,tstime) < 0 && timeCha(beforeDate, afone,tstime) > 0
							&& timeCha(afone, date,tstime) > 30) {// 若中午12-13点干活了
						upDate = afone;
						downDate = date;
						logger.info("中午都干活了，逆天了，你挺忙的呀");
					} else if (timeCha(afone, beforeDate,tstime) > 0 && timeCha(beforeDate, date,tstime) > 30) {// 已经不是下午的第一笔了
						upDate = beforeDate;
						downDate = date;
						logger.info("下午干活，但已经不是下午的第一笔了");
					}
				}
			}
			// 记录操作时间
			BizApprovalOperatedate atedate = setBizApprovalOperatedate(map);
			// 记录异常操作
			if (upDate != null && downDate != null && lean) {
				BizApprovalOperateexc exc = setBizApprovalOperateexc(map, upDate, downDate,tstime);
				BizApprovalOpeexchis his = setBizApprovalOpeexchis(map, upDate, downDate,tstime);
				bizApprovalOperateexcDao.insertSelective(exc);
				bizApprovalOpeexchisDao.insertSelective(his);
				BizApprovalOperatedate opda = bizApprovalOperatedateDao.selectByUserIdAndDT(map.get("userId"));
				if(opda==null){
					bizApprovalOperatedateDao.insertSelective(atedate);
				}
			}else if(!lean&& oldhis!=null && oldexc!=null){
				BizApprovalOperateexc exc = setBizApprovalOperateexc(map, upDate, downDate,tstime);
				BizApprovalOpeexchis his = setBizApprovalOpeexchis(map, upDate, downDate,tstime);
				exc.setId(oldexc.getId());
				his.setId(oldhis.getId());
				bizApprovalOperateexcDao.updateByPrimaryKeySelective(exc);
				bizApprovalOpeexchisDao.updateByPrimaryKeySelective(his);
			}
		}
		
		//判断是否可以触发流程
		Map<String,Object> resStrMap = new HashMap<String,Object>();
		boolean jbpmFlag = false;
		AllotApplyAllot allot_fk = null;
		if(allot.getLaojianflag()==null||"".equals(allot.getLaojianflag().trim())||"00".equals(allot.getLaojianflag())){
			if("1".equals(allot.getYdjFlag()) && !"0".equals(allot.getMatchingCardFlag())){//现在暂时只针对易达金,且有副卡
				String appId_fk = CommonUtil.getAnothorCardAppId(allot.getAppId(), allot.getMatchingCardFlag());
				logger.info("appId_fk="+appId_fk);
				if(appId_fk!=null){
					allot_fk = allotApplyAllotDao.selectByPrimaryKey(appId_fk);
					//只有其主副卡都已经被审批了，流程才可以提交
					if(allot_fk!=null && "03".equals(allot_fk.getCurrNode()) && "0".equals(allot_fk.getSynFlag())){
						logger.info("当前卡暂时不触发流程，可能原因为其对应的主卡或副卡没有审批，当前卡其appId = "+allot.getAppId());
						jbpmFlag = false;
						resStrMap.put("tkIsSubmit", "0");//1代表副卡已经提交，0为未提交
						resStrMap.put("allot_fk", allot_fk);
					}else if(allot_fk!=null&& "04".equals(allot_fk.getCurrNode())){//若此件为副卡,则取另一卡为主卡
						jbpmFlag = true;
						logger.info("准备进行流程提交，所用卡号为appId = "+allot.getAppId());
						resStrMap.put("tkIsSubmit", "1");
					}else{
						jbpmFlag = false;
						resStrMap.put("tkIsSubmit", "1");
					}
				}
			}else{
				jbpmFlag = true;
			}
		}else{//若当前卡位捞件回来的卡，而且其捞件标示不是“00”
			logger.info("【捞件标示为空或为00】");
			jbpmFlag = true;
		}
		logger.info("【此件为捞件，而且jbpmFlag="+jbpmFlag);
		logger.info("========开始进行申请件分配表数据更新==========");
		//申请件数据更新
		if(jbpmFlag){
			allot.setSynFlag("0");
			allot.setUserDate(new Date());
			allot.setLstTeamDate(new Date());
			allot.setLastOptUser(allot.getCurrOptUser());
			allot.setBackFlag("0");
			allotHis.setSynFlag("0");
			allotHis.setBackFlag("0");
			if(allot_fk!=null){
				// 为历史表赋值
				AllotApplyAllotHis allotHis_fk = setAllotApplyAllotHis(allot_fk, allot_fk.getAppId());
				allot_fk.setCurrNode(allot.getCurrNode());
				allot_fk.setLastOptUser(allot.getLastOptUser());
				allot_fk.setDelStatus(allot.getDelStatus());
				allot_fk.setCurrStatus(allot.getCurrStatus());
				allot_fk.setSynFlag("0");
				allot_fk.setUserDate(new Date());
				allot_fk.setCurrOptUser(allot.getCurrOptUser());
				allot_fk.setLstTeamDate(new Date());
				allot_fk.setCheck_meuoFlag(allot.getCheck_meuoFlag());
				allot_fk.setBackFlag("0");
				allotHis_fk.setBackFlag("0");
				// 修改申请件分配表
				allotApplyAllotDao.updateByPrimaryKeySelective(allot_fk);
				// 增加流水记录
				allotApplyAllotHisDao.insertSelective(allotHis_fk);
				logger.info("========update套卡中另一件卡的 申请件分配表数据==sucesss========");
			}
		}else{
			allot.setUserDate(new Date());
			allot.setLstTeamDate(new Date());
			allot.setLastOptUser(allot.getCurrOptUser());
			allot.setSynFlag("1");//已结完
			allot.setBackFlag("0");
			allotHis.setSynFlag("1");
			allotHis.setBackFlag("0");
			logger.info("========update套卡中另一件卡的 申请件分配表数据==========");
			if(allot_fk!=null&&"1".equals(allot_fk.getSynFlag())&&!"04".equals(allot_fk.getCurrNode())){
				allot_fk.setSynFlag("0");
				allot_fk.setCheck_meuoFlag(allot.getCheck_meuoFlag());
				// 修改副卡的申请件分配表
				allotApplyAllotDao.updateByPrimaryKeySelective(allot_fk);
			}
		}
		// 修改申请件分配表
		allotApplyAllotDao.updateByPrimaryKeySelective(allot);
		// 增加流水记录
		allotHis.setLstTeamDate(new Date());
		allotApplyAllotHisDao.insertSelective(allotHis);
		// 增加流程生命周期
		addApplyLifeCicle(allot,map.get("userName"),"审批流程提交");
		logger.info("保存、提交按钮-->修改申请件分配表success");
		// 调用提交按钮所对应的流程接口
		if ("submit".equals(button)&&jbpmFlag) {
			/*if(allot_fk!=null && !allot_fk.getCurrOptUser().equals(allot.getCurrOptUser())){
				if("2".equals(map.get("check_meuoFlag"))){
					String isBack = "12";
					//若当前审批人不是征审合一角色
					int ap = apUserDao.queryCurrUserCode(allot.getCurrOptUser());
					System.out.println("政审合一是否调用流程----ap="+ap);
					if(ap == 0){
						lineNewUrlClientZSHY(allot.getAppId(), isBack, resMap);
					}
				}
			}else{*/
				logger.info("调用流程接口，进行流程提交");
				if("2".equals(map.get("check_meuoFlag"))){//征审合一工作流
					lineNewUrlClient(allot.getAppId(), "3", resMap);
				}else{//审批工作流
					logger.info("审批工作流appId="+map.get("appId"));
					lineNewUrlClient(allot.getAppId(), "0", resMap);
				}
				
			/*}*/
		}
		return resStrMap;
	}
	public void addTime(Map<String, String> map,Date upDate,Date downDate,boolean tstime) throws Exception{
		// 记录操作时间
		BizApprovalOperatedate atedate = setBizApprovalOperatedate(map);
		// 记录异常操作
		if (upDate != null && downDate != null) {
			BizApprovalOperateexc exc = setBizApprovalOperateexc(map, upDate, downDate,tstime);
			BizApprovalOpeexchis his = setBizApprovalOpeexchis(map, upDate, downDate,tstime);
			bizApprovalOperateexcDao.insertSelective(exc);
			bizApprovalOpeexchisDao.insertSelective(his);
			BizApprovalOperatedate opda = bizApprovalOperatedateDao.selectByUserIdAndDT(map.get("userId"));
			if(opda==null){
				bizApprovalOperatedateDao.insertSelective(atedate);
			}
		}
	}
	public void addApplyLifeCicle(AllotApplyAllot allot,String chineseName,String desc) throws Exception{
		ApplyLifeCicle applyLifeCicle = new ApplyLifeCicle();
		applyLifeCicle.setUuid(StringUtil.randomUUIDPlainString());
		applyLifeCicle.setAppId(allot.getAppId());
		applyLifeCicle.setOperater(chineseName + "-" + allot.getLastOptUser());
		applyLifeCicle.setOperateDesc(desc);
		applyLifeCicle.setOperateResult("完成");
		applyLifeCicle.setCrtDate(new Date());
		applyLifeCicle.setCrtUser(allot.getCurrOptUser());
		applyLifeCicle.setLstUpdDate(new Date());
		applyLifeCicle.setLstUpdUser(allot.getCurrOptUser());
		applyLifeCicleDao.addApplyLifeCicle(applyLifeCicle);
	}
	/**
	 * 调用流程接口
	 * 审批工作流
	 * @throws Exception
	 */
	public void lineNewUrlClient(String appId, String isBack, Map<String, String> resMap) throws Exception {
		// isBack = 0;为提交，4为审批退回,2征信，3征审
		Map paramMap = new HashMap();
		//try {
			paramMap.put("appId", appId);
			Map<String, Object> needMap = bizInpApplDao.selectProcessIdByAppId(paramMap);
			String processId = needMap.get("PROCESSID").toString();
			logger.info("调用流程>>>>>>>参数信息:" + processId + ",nodeId=" + resMap.get("nodeId")+",process_IP="+resMap.get("process_IP"));
//			Client client = new Client(new URL(
//					"http://" + resMap.get("process_IP") + "/topbpm_test/services/BranchBankSearchServiceI?wsdl"));
			String intputXml = "";
			intputXml = "{'processId':'" + processId + "','nodeId':'" + resMap.get("nodeId") + "','isBack':'" + isBack
					+ "'}";
			logger.info(">>>>>>>参数信息:" + intputXml);
			/*client.setTimeout(60);
			logger.info(">>>>>>>设置超时时间:" + 60+"s");*/
//			Object[] obj = new Object[] { intputXml.toString() };
//			Object[] result = null;
			String obj = intputXml.toString();
//			String result = client.invoke("signal", obj);
			String result = topbpmService.signal(intputXml).toString();
			String res = "";
			if(result!=null && result.length()>0){
				res = result.toString();
			}
			logger.info("res=" + res);
			if("1".equals(res)){
				logger.info("===========调用流程接口【成功】============");
			}else{
				logger.error("===========调用流程接口【出错】============res="+res);
				throw new Exception("工作流繁忙,请稍后再试。res="+res);
			}
//			if(client!=null){
//				client.close();//释放连接数
//				client=null;
//			}
			
		/*} catch (Exception e) {
			logger.error("===========调用流程接口【出错】============"+e.getMessage());
			throw new Exception("流程提交，调用流程接口失败");
		}*/
	}
	public void lineNewUrlClientZSHY(String appId, String isBack, Map<String, String> resMap) throws Exception {
		// isBack = 0;为提交，4为审批退回,2征信，3征审
		Map paramMap = new HashMap();
		paramMap.put("appId", appId);
		Map<String, Object> needMap = bizInpApplDao.selectProcessIdByAppId(paramMap);
		String processId = needMap.get("PROCESSID").toString();
		logger.info("调用流程>>>>>>>参数信息:" + processId + ",nodeId2=" + resMap.get("nodeId2")+",process_IP="+resMap.get("process_IP"));
//		Client client = new Client(new URL(
//				"http://" + resMap.get("process_IP") + "/topbpm_test/services/BranchBankSearchServiceI?wsdl"));
		String intputXml = "";
		intputXml = "{'processId':'" + processId + "','nodeId':'" + resMap.get("nodeId2") + "','isBack':'" + isBack
				+ "'}";
		logger.info(">>>>>>>参数信息:" + intputXml);
		/*client.setTimeout(60);
		logger.info(">>>>>>>设置超时时间:" + 60+"s");*/
//		Object[] obj = new Object[] { intputXml.toString() };
//		Object[] result = null;
		String obj = intputXml.toString();
		String result = "";
		try {
//			result = client.invoke("signal", obj);
			result = topbpmService.signal(intputXml).toString();
			String res = "";
			if(result!=null && result.length()>0){
				res = result.toString();
			}
			logger.info("res=" + res);
			if("1".equals(res)){
				logger.info("===========调用流程接口【成功】============");
			}else{
				logger.error("===========调用流程接口【出错】============res="+res);
				throw new Exception("工作流繁忙,请稍后再试。res="+res);
			}
		} catch (Exception e) {
			logger.error("resultException -> {}", new Object[] { e.getMessage() }, e);
			throw new Exception("申请件流水号"+appId+"调用工作流异常原因："+e.getMessage());
		}finally{
			// if(client!=null){
			// client.close();//释放连接数
			// client=null;
			// }
		}
	}
	@Override
	public AllotApplyAllot queryOneByUserId(Map<String,String> map) throws Exception {
		return allotApplyAllotDao.selectOneByUserId(map);
	}
	@Override
	public AllotApplyAllot queryZSOneByUserId(Map<String,String> map) throws Exception {
		return allotApplyAllotDao.selectZSOneByUserId(map);
	}
	@Override
	public AllotApplyAllot selectZSNextOneByUserId(Map<String,String> map) throws Exception {
		return allotApplyAllotDao.selectZSNextOneByUserId(map);
	}
	@Override
	public String selectZSCurrentNumByUserId(Map<String,String> map) throws Exception {
		return allotApplyAllotDao.selectZSCurrentNumByUserId(map);
	}
	@Override
	public AllotApplyAllot selectByPrimaryKey(String appId) throws Exception {
		return allotApplyAllotDao.selectByPrimaryKey(appId);
	}

	/**
	 * 流程退回或转上级业务 流程提交
	 */
	@Override
	public void updateByPrimaryKeySelective(AllotApplyAllot allot, AllotApplyAllotHis allotHis, String flag,
			Map<String, String> resMap) throws Exception {
		AllotApplyAllot allot_fk = null;
		AllotApplyAllotHis allotHis_fk = null;
		AllotApplyAllotHis oldAllotsss = null;
		boolean jbpmFlag = true;
		boolean tsZSJFlag = false;
		String msg = "";
		ApUser user = null;
		ApUser user2=null;
		String spUserName="";
		//判读是否政审合一角色(eg:ap>0,jieguo wei shi)
		int ap = 0;
		allot.setUserDate(new Date());
		allot.setLstTeamDate(new Date());
		String hidden_currStatus="";
		try {
			user = apUserDao.queryApUserByUserCode(allot.getLastOptUser());
			if("0".equals(flag)&&"3".equals(allot.getCurrStatus())){
				user2 = apUserDao.queryApUserByUserCode(allot.getCurrOptUser());
				spUserName=user2!=null?user2.getUserName():"";
			}
			if(allot.getLaojianflag()==null||"00".equals(allot.getLaojianflag())){
				if("0".equals(flag)){//转上级或退回审批或挂起
					if("1".equals(allot.getYdjFlag()) && !"0".equals(allot.getMatchingCardFlag())){//现在暂时只针对易达金,且有副卡
						String appId_fk = CommonUtil.getAnothorCardAppId(allot.getAppId(), allot.getMatchingCardFlag());
						allot_fk = allotApplyAllotDao.selectByPrimaryKey(appId_fk);
						if(allot_fk!=null && "3".equals(allot.getCurrStatus())){
							if("1".equals(allot_fk.getSynFlag()) && allot.getCurrOptUser().equals(allot_fk.getCurrOptUser())){
								allot.setSynFlag("0");
								allot_fk.setSynFlag("0");
								//allotHis_fk = setAllotApplyAllotHis(allot_fk, appId_fk);
								tsZSJFlag = true;
							}else if("1".equals(allot_fk.getSynFlag()) && !allot.getCurrOptUser().equals(allot_fk.getCurrOptUser())){
								if("1".equals(allot_fk.getBackFlag())||"2".equals(allot_fk.getBackFlag())){
									allot.setSynFlag("0");
									allot_fk.setSynFlag("0");
									//allotHis_fk = setAllotApplyAllotHis(allot_fk, appId_fk);
								}else{
									allot.setSynFlag("0");
									//allotHis_fk = setAllotApplyAllotHis(allot_fk, appId_fk);
								}
								allot_fk.setCurrOptUser(allot.getCurrOptUser());
								tsZSJFlag = true;
							}else{
								allot.setSynFlag("1");
								tsZSJFlag = false;
							}
							allot_fk.setUserDate(new Date());
							if("2".equals(allot.getBackFlag())){
								msg = "审批转上级环节-"+spUserName;
							}else{
								msg = "审批退回环节-"+spUserName;
								if("1".equals(resMap.get("tkIsBack"))){
									allot.setSynFlag("0");
									allot_fk.setCurrOptGroup(allot.getCurrOptGroup());
									allot_fk.setLastOptGroup(allot.getLastOptGroup());
									allot_fk.setCurrOptUser(allot.getCurrOptUser());
									allot_fk.setLastOptUser(allot.getLastOptUser());
									allot_fk.setCurrStatus(allot.getCurrStatus());
									allot_fk.setCurrNode(allot.getCurrNode());
									allot_fk.setDelStatus(allot.getDelStatus());
									allot_fk.setBackFlag(allot.getBackFlag());
									allot_fk.setUserDate(allot.getUserDate());
									allot_fk.setLstTeamDate(allot.getLstTeamDate());
									allot_fk.setDelStatus(allot.getDelStatus());
									allot_fk.setSynFlag("0");
									allotHis_fk = setAllotApplyAllotHis(allot_fk, appId_fk);
									allotHis_fk.setCurrOptUser(allotHis.getCurrOptUser());
									allotHis_fk.setBackFlag(allot.getBackFlag());
									allotHis_fk.setMemo(allotHis.getMemo());
									// 增加流程生命周期
									addApplyLifeCicle(allot_fk,user!=null?user.getUserName():"","审批退回环节-"+spUserName);
									tsZSJFlag = true;
								}
							}
						}else if(allot_fk!=null && "4".equals(allot.getCurrStatus())){
							// 为历史表赋值
							allotHis_fk = setAllotApplyAllotHis(allot_fk, appId_fk);
							allotHis_fk.setBackFlag(allot.getBackFlag());
							allotHis_fk.setMemo(resMap!=null?resMap.get("memo"):"");
							allot_fk.setCurrOptUser(allot.getCurrOptUser());
							allot_fk.setLastOptUser(allot.getLastOptUser());
							allot_fk.setCurrStatus(allot.getCurrStatus());
							allot_fk.setCurrNode(allot.getCurrNode());
							allot_fk.setDelStatus(allot.getDelStatus());
							allot_fk.setBackFlag(allot.getBackFlag());
							allot_fk.setUserDate(new Date());
							allot_fk.setLstTeamDate(new Date());
							allot_fk.setSynFlag("0");
							msg = "审批挂起环节";
							user = apUserDao.queryApUserByUserCode(allot.getCurrOptUser());
							// 增加流程生命周期
							addApplyLifeCicle(allot_fk,user!=null?user.getUserName():"","审批挂起环节");
						}
					}else if("1".equals(allot.getYdjFlag()) && "0".equals(allot.getMatchingCardFlag())){//易达金单件的情况
						if("3".equals(allot.getCurrStatus())){
							if("2".equals(allot.getBackFlag())){
								msg = "审批转上级环节-"+spUserName;
							}else{
								msg = "审批退回环节-"+spUserName;
							}
						}else if("4".equals(allot.getCurrStatus())){//审批个人未完成队列将申请件挂起
							msg = "审批挂起环节";
						}
					}else if("2".equals(allot.getYdjFlag())){
						tsZSJFlag = true;
						if("3".equals(allot.getCurrStatus())){
							if("2".equals(allot.getBackFlag())){
								msg = "审批转上级环节-"+spUserName;
							}else{
								msg = "审批退回环节-"+spUserName;
							}
						}
					}
					if("4".equals(allot.getCurrStatus())||"1".equals(resMap.get("check_meuoFlag"))){
						jbpmFlag = false;
					}
					
				}else if("1".equals(flag)){//退回征信	
					allot.setCheck_meuoFlag("1");
					if("1".equals(allot.getYdjFlag()) && !"0".equals(allot.getMatchingCardFlag())){//现在暂时只针对易达金,且有副卡
						String appId_fk = CommonUtil.getAnothorCardAppId(allot.getAppId(), allot.getMatchingCardFlag());
						allot_fk = allotApplyAllotDao.selectByPrimaryKey(appId_fk);
						if(allot_fk!=null){
							// 为历史表赋值
							allotHis_fk = setAllotApplyAllotHis(allot_fk, appId_fk);
							allotHis_fk.setBackFlag("4");
							allotHis_fk.setMemo(resMap!=null?resMap.get("memo"):"");
							allotHis_fk.setLastOptUser(allot_fk.getCurrOptUser());
							allotHis_fk.setCurrOptUser(allot.getCurrOptUser());
							allotHis_fk.setCurrNode("03");
							//
							allot_fk.setCurrStatus(allot.getCurrStatus());
							//[德彬]修改审批易达金两个码不在同一个人   只退易达金1个码  另一个码最后操作人不准问题
							allot_fk.setLastOptUser(allot_fk.getCurrOptUser());
							allot_fk.setCurrOptUser(allot.getCurrOptUser());
							//allot_fk.setLastOptUser(allot.getLastOptUser());
							allot_fk.setUserDate(new Date());
							allot_fk.setCurrOptGroup(allot.getCurrOptGroup());
							allot_fk.setLastOptGroup(allot.getLastOptGroup());
							allot_fk.setCheck_meuoFlag("1");
							allot_fk.setBackFlag("4");
							allot_fk.setBatchCreditFlag(allot.getBatchCreditFlag());
							allot_fk.setBatchApprovalFlag(allot.getBatchApprovalFlag());
							allot_fk.setCurrNode(allot.getCurrNode());
							allot_fk.setDelStatus(allot.getDelStatus());
							if("1".equals(resMap.get("tkIsBack"))){
								allot_fk.setSynFlag("0");
								// 增加流程生命周期
								addApplyLifeCicle(allot_fk,user!=null?user.getUserName():"","审批退回征信环节");
							}else{
								allot_fk.setSynFlag("1");
							}
						}
					}
					msg = "审批退回征信环节";
				}else if("2".equals(flag)){//审批收回
					if("1".equals(allot.getYdjFlag()) && !"0".equals(allot.getMatchingCardFlag())){//现在暂时只针对易达金,且有副卡
						String appId_fk = CommonUtil.getAnothorCardAppId(allot.getAppId(), allot.getMatchingCardFlag());
						allot_fk = allotApplyAllotDao.selectByPrimaryKey(appId_fk);
						if(allot_fk!=null&&"0".equals(allot_fk.getSynFlag())&&"04".equals(allot_fk.getCurrNode())){
							// 为历史表赋值
							allotHis_fk = setAllotApplyAllotHis(allot_fk, appId_fk);
							allot_fk.setCurrOptUser(allot.getCurrOptUser());
							allot_fk.setLastOptUser(allot.getLastOptUser());
							//allot_fk.setCurrNode("04");
							allot_fk.setDelStatus(allot.getDelStatus());
							allot_fk.setSynFlag("1");
							allot_fk.setUserDate(new Date());
							
							//删除
							fileAppDetDao.deleteByPrimaryKey(allot_fk.getAppId());
						}else if("1".equals(allot.getSynFlag())){
							jbpmFlag = false;
						}
						allot.setSynFlag("0");
					}
					//删除
					fileAppDetDao.deleteByPrimaryKey(allot.getAppId());
					msg = "审批收回环节";
					//若是标准卡套卡，则将其对应的套卡2码的数据删除
					if("2".equals(allot.getYdjFlag()) && "2".equals(allot.getMatchingCardFlag())){
						String appId_bzk_fk = allot.getAppId().substring(0, allot.getAppId().length()-1)+"2";//标卡的套卡最后一位为2
						//删除
						fileAppDetDao.deleteByPrimaryKey(appId_bzk_fk);
					}
				}else if("3".equals(flag)){//解挂
					user = apUserDao.queryApUserByUserCode(allot.getCurrOptUser());
					String appId_fk = CommonUtil.getAnothorCardAppId(allot.getAppId(), allot.getMatchingCardFlag());
					allot_fk = allotApplyAllotDao.selectByPrimaryKey(appId_fk);
					if(allot_fk!=null && "3".equals(allot.getCurrStatus())){//易达金两张卡时套卡也要解挂
						// 为历史表赋值
						allotHis_fk = setAllotApplyAllotHis(allot_fk, appId_fk);
						allotHis_fk.setBackFlag(allot.getBackFlag());
						allotHis_fk.setMemo(resMap!=null?resMap.get("memo"):"");
						allot_fk.setCurrOptUser(allot.getCurrOptUser());
						allot_fk.setLastOptUser(allot.getLastOptUser());
						allot_fk.setCurrStatus(allot.getCurrStatus());
						allot_fk.setCurrNode(allot.getCurrNode());
						allot_fk.setDelStatus(allot.getDelStatus());
						allot_fk.setBackFlag(allot.getBackFlag());
						allot_fk.setUserDate(allot_fk.getUserDate());
						allot_fk.setLstTeamDate(allot_fk.getLstTeamDate());
						allot_fk.setSynFlag("0");
						// 增加流程生命周期
						addApplyLifeCicle(allot_fk,user!=null?user.getUserName():"","审批解挂");
					}
					//易达金存在单件情况
					msg = "审批解挂";
					jbpmFlag = false;
				}
			}else if("1".equals(flag)){//捞件标识为01或02时审批退回征信做特殊处理
				allot.setCheck_meuoFlag("1");
				msg = "审批退回征信环节";
			}else if("2".equals(flag)){//捞件标识为01或02时审批收回做特殊处理
				allot.setSynFlag("0");
				//删除
				msg = "审批收回环节";
				fileAppDetDao.deleteByPrimaryKey(allot.getAppId());
			}else if("0".equals(flag)){//捞件标识为01或02时审批转上级、退回、挂起做特殊处理
				if("3".equals(allot.getCurrStatus())){
					if("2".equals(allot.getBackFlag())){
						msg = "审批转上级环节-"+spUserName;
					}else{
						msg = "审批退回环节-"+spUserName;
					}
				}else if("4".equals(allot.getCurrStatus())){
					user = apUserDao.queryApUserByUserCode(allot.getCurrOptUser());
					msg = "审批挂起环节";
				}
				if("4".equals(allot.getCurrStatus())||"1".equals(resMap.get("check_meuoFlag"))){//审批挂起或审批环节的申请件 wdb
					jbpmFlag = false;
				}
			}else if("3".equals(flag)){//捞件标识为01或02时审批解挂做特殊处理
				user = apUserDao.queryApUserByUserCode(allot.getCurrOptUser());
				msg = "审批解挂";
				jbpmFlag = false;
			}
			// 征审合一流程提交时，特殊字段变更
			if(jbpmFlag && "2".equals(resMap.get("check_meuoFlag"))){
				allot.setCheck_meuoFlag("2");
				if(allot_fk!=null&&allotHis_fk!=null){
					allot_fk.setCheck_meuoFlag("2");
				}
				if("0".equals(flag) && "3".equals(allot.getCurrStatus()) && (tsZSJFlag||"2".equals(allot.getYdjFlag()))){//政审合一的转上级或退回审批
					//若当前审批人不是征审合一角色
					ap = apUserDao.queryCurrUserCode(allot.getCurrOptUser());
					//System.out.println("政审合一是否调用流程----ap="+ap);
					if(ap == 0){
						allot.setCheck_meuoFlag("1");
						if(allot_fk!=null&&allotHis_fk!=null){
							allot_fk.setCheck_meuoFlag("1");
						}
					}
				}
			}
			if("0".equals(flag)){
				ap = apUserDao.queryCurrUserCode(allot.getCurrOptUser());
				//若当前审批人是征审合一角色
				if("0".equals(flag) && "3".equals(allot.getCurrStatus()) && (tsZSJFlag||"2".equals(allot.getYdjFlag())) && ap > 0){
					Map paramMap = new HashMap();
					paramMap.put("currNode", "03");
					paramMap.put("appId", allot.getAppId());
					paramMap.put("currOptUser", allot.getCurrOptUser());
					paramMap.put("backFlag", "4");
					oldAllotsss = allotApplyAllotHisDao.queryAllotApplyAllotHisSPByAppId(paramMap);
					if(oldAllotsss!=null && "2".equals(oldAllotsss.getCheck_meuoFlag())){
						allot.setCheck_meuoFlag("2");
						jbpmFlag = true;
					}
				}
			}
			//修改数据状态
			allot.setUserDate(new Date());
			allot.setLstTeamDate(new Date());
			allot.setZshyInnerCheck("0");
			//调流程的情况  修改分件表状态 避免页面和工作流节点不统一
			hidden_currStatus=allot.getCurrStatus();
			if(jbpmFlag && "2".equals(resMap.get("check_meuoFlag"))){//征审合一申请件
				if("2".equals(flag)){//征审合一的审批收回，isback = 11
					//当前隐藏申请件 下一节点改回来 wdb
					if(allot_fk!=null){
						allot_fk.setCurrStatus("9");
					}
					allot.setCurrStatus("9");
				}else if((ap==0)&&"0".equals(flag) && "3".equals(allot.getCurrStatus()) && (tsZSJFlag||"2".equals(allot.getYdjFlag()))){//征审合一的转上级或退回审批
					//若当前审批人不是征审合一角色isback = 12
					//当前隐藏申请件 下一节点改回来 wdb
					if(allot_fk!=null){
						allot_fk.setCurrStatus("9");
					}
					allot.setCurrStatus("9");
				}
			}else if(jbpmFlag){
				if("0".equals(flag) && "3".equals(allot.getCurrStatus()) && (tsZSJFlag||"2".equals(allot.getYdjFlag()))){//征审合一的转上级或退回审批
					//若当前审批人是征审合一角色
					if(ap > 0 && oldAllotsss!=null && "2".equals(oldAllotsss.getCheck_meuoFlag())){//isback =4
						//当前隐藏申请件 下一节点改回来 wdb
						if(allot_fk!=null){
							allot_fk.setCurrStatus("9");
						}
						allot.setCurrStatus("9");
					}
				}else if("2".equals(flag)){//普通审批从待归档收回   isback =1
					//当前隐藏申请件 下一节点改回来 wdb
					if(allot_fk!=null){
						allot_fk.setCurrStatus("9");
					}
					allot.setCurrStatus("9");
				}else{
					//当前隐藏申请件 下一节点改回来 wdb
					if(allot_fk!=null){
						allot_fk.setCurrStatus("9");
					}
					allot.setCurrStatus("9");
				}
			}
			allotApplyAllotDao.updateByPrimaryKeySelective(allot);
			// 增加流水记录
			allotApplyAllotHisDao.insertSelective(allotHis);
			// 增加流程生命周期
			addApplyLifeCicle(allot,user!=null?user.getUserName():"",msg);
			//是否需要更新智能语音表
			if(resMap.get("approveReturn")!=null&&!"".equals(resMap.get("approveReturn"))){
				allotApplyAllotDao.updateSpFlagByAppId(resMap);
			}
			if(allot_fk!=null){
				if(!"2".equals(allot.getBackFlag())){
					allot_fk.setUserDate(allot.getUserDate());
					allot_fk.setLstTeamDate(allot.getLstTeamDate());
				}
				allot_fk.setCheck_meuoFlag(allot.getCheck_meuoFlag());
				allot_fk.setZshyInnerCheck("0");
				allotApplyAllotDao.updateByPrimaryKeySelective(allot_fk);
				if(allotHis_fk!=null){
					// 增加流水记录
					allotApplyAllotHisDao.insertSelective(allotHis_fk);
				}
			}
			//调用流程
			// 退回  征审合一退回不调用工作流--(但是政审合一的审批收回需要掉工作流)
			String isBack = "4";
			if(jbpmFlag && "2".equals(resMap.get("check_meuoFlag"))){
				if("2".equals(flag)){//征审合一的审批收回，isback = 11
					isBack = "11";
					lineNewUrlClient(allot.getAppId(), isBack, resMap);
				}else if("0".equals(flag) && "3".equals(hidden_currStatus) && (tsZSJFlag||"2".equals(allot.getYdjFlag()))){//征审合一角色向普通审批的转上级或征审合一角色向普通审批的退回审批
					isBack = "12";
					//若当前审批人不是征审合一角色
					if(ap == 0){
						lineNewUrlClientZSHY(allot.getAppId(), isBack, resMap);
					}
				}
			}else if(jbpmFlag){
				if("0".equals(flag) && "3".equals(hidden_currStatus) && (tsZSJFlag||"2".equals(allot.getYdjFlag()))){//普通审批向征审合一的转上级或普通审批向征审合一退回审批或退回征信
					isBack = "4";
					//若当前审批人是征审合一角色
					if(ap > 0 && oldAllotsss!=null && "2".equals(oldAllotsss.getCheck_meuoFlag())){
						lineNewUrlClient(allot.getAppId(), isBack, resMap);
					}
					return;
				}else if("2".equals(flag)){
					isBack = "1";
				}
				lineNewUrlClient(allot.getAppId(), isBack, resMap);// isBack = 0;为审批提交，1为待归档收回，2 征信提交 3 征审审批提交4为退回征信 11征审审批待归档 12 征审合一的转上级或退回审批
			}
		} catch (Exception e) {
			logger.error("申请件{}-审批环节异常:{}", new Object[] { allot.getAppId(), e.getMessage() });
			throw new Exception("申请件"+allot.getAppId()+"-审批环节异常:"+e.getMessage());
		}
	}
	
	/**
	 * 获取特点节点的最近一条allotApplyAllotHis信息
	 * @throws CoreException 
	 */
	@Override
	public AllotApplyAllotHis queryAllotApplyAllotHisByAppId(Map map) throws CoreException{
		return allotApplyAllotHisDao.queryAllotApplyAllotHisByAppId(map);
	}
	/**
	 * 获取特点节点的非当前操作员的最近一条allotApplyAllotHis信息
	 * @throws CoreException 
	 */
	@Override
	public AllotApplyAllotHis queryAllotApplyAllotHisNozjByAppId(Map map) throws CoreException{
		return allotApplyAllotHisDao.queryAllotApplyAllotHisNozjByAppId(map);
	}
	/**
	 * 查询申请信息修改日志
	 */
	@Override
	public List<KeyMessageModify> selectApplyLogByAppId(String appId,int curNum,int pageNum) throws Exception {
		List<KeyMessageModify> KeyMeslist = keyMessageModifyDao.selectApplyLogByAppId(appId,curNum,pageNum);
		//String file = System.getProperty("user.dir")+"\\resource\\config\\sqlmap\\BizInpAppl.xml";
		//\com\huaxia\opas\service\decision\impl
		String file = "config"+File.separator+"sqlmap"+File.separator+"BizInpAppl.xml";
		for (int i = 0;i<KeyMeslist.size(); i++){
			//String columnName = KeyMeslist.get(i).getKeyWordsId();
			String columnName = (String) KeyMeslist.get(i).getKeyWordsId();//("keyWordsId");
			//查字段对应的中文
			String property = Utils.getMapperColumnByProperty(file, "BizInpApplicationResultMap", columnName);
			List<String> keyWordsId = keyMessageModifyDao.selectColumnName(property);
			if(keyWordsId != null && keyWordsId.size()>0){
				KeyMeslist.get(i).setKeyWordsId(keyWordsId.get(0));//put("keyWordsId", keyWordsId);
			}
			KeyMeslist.get(i).setColumnName(columnName);
		}
		
		return KeyMeslist;
	}

	/**
	 * 计算一天内两个日期的时间差，返回分钟数
	 */
	@SuppressWarnings("deprecation")
	public int timeCha(Date start, Date end,boolean tstime) {
		int hours_start = start.getHours();
		int minutes_start = start.getMinutes();
		int hours_end = end.getHours();
		int minutes_end = end.getMinutes();
		// 时间间隔
		int time_jiange = (hours_end - hours_start) * 60 + (minutes_end - minutes_start);
		if(tstime){
			time_jiange = time_jiange - 60;
		}
		return time_jiange;
	}

	/**
	 * 为实体赋值
	 */
	@SuppressWarnings("deprecation")
	public BizApprovalOperateexc setBizApprovalOperateexc(Map<String, String> map, Date start, Date end,boolean tstime)
			throws Exception {
		// 时间间隔
		int time_jiange = timeCha(start, end,tstime);
		BizApprovalOperateexc bizApprovalOperateexc = new BizApprovalOperateexc();
		bizApprovalOperateexc.setId(StringUtil.randomUUIDPlainString());
		bizApprovalOperateexc.setOperateCode(map.get("userId"));
		bizApprovalOperateexc.setOperateName(map.get("userId"));
		bizApprovalOperateexc.setStartdate(start);
		bizApprovalOperateexc.setEnddate(end);
		bizApprovalOperateexc.setExceptiondatelong((long) time_jiange);
		bizApprovalOperateexc.setExceptiondate(new Date());
		return bizApprovalOperateexc;
	}

	/**
	 * 为实体赋值
	 */
	@SuppressWarnings("deprecation")
	public BizApprovalOpeexchis setBizApprovalOpeexchis(Map<String, String> map, Date start, Date end,boolean tstime)
			throws Exception {
		// 时间间隔
		int time_jiange = timeCha(start, end,tstime);
		BizApprovalOpeexchis bizApprovalOpeexchis = new BizApprovalOpeexchis();
		bizApprovalOpeexchis.setId(StringUtil.randomUUIDPlainString());
		bizApprovalOpeexchis.setOperateCode(map.get("userId"));
		bizApprovalOpeexchis.setOperateName(map.get("userId"));
		bizApprovalOpeexchis.setStartdate(start);
		bizApprovalOpeexchis.setEnddate(end);
		bizApprovalOpeexchis.setExcdatelong((long) time_jiange);
		bizApprovalOpeexchis.setExcdatedate(new Date());
		return bizApprovalOpeexchis;
	}

	/**
	 * 为实体赋值
	 */
	@SuppressWarnings("deprecation")
	public BizApprovalOperatedate setBizApprovalOperatedate(Map<String, String> map) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		BizApprovalOperatedate bizApprovalOperatedate = new BizApprovalOperatedate();
		bizApprovalOperatedate.setId(StringUtil.randomGUIDPlainString());
		bizApprovalOperatedate.setOperateCode(map.get("userId"));
		bizApprovalOperatedate.setOperateName(map.get("userId"));
		bizApprovalOperatedate.setOperateTime(sdf.format(new Date()));
		return bizApprovalOperatedate;
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
		return record;
	}

	@Override
	public int queryCount(String appId) {
		return keyMessageModifyDao.queryCountModify(appId);
	}

	@Override
	public void updateSecDecisionFlag(String appId, String flag) {
		if("1".equals(flag)){
			allotApplyAllotDao.updateSecDecisionFlag1(appId);
		}else{
			allotApplyAllotDao.updateSecDecisionFlag0(appId);
		}
	}

	@Override
	public String selectSecDecisionFlag(String appId) {
		return allotApplyAllotDao.selectSecDecisionFlag(appId);
	}

	@Override
	public List<Map<String, String>> queryForeignCheckByAppId(String appId) {
		return allotApplyAllotDao.queryForeignCheckByAppId(appId);
	}
}
