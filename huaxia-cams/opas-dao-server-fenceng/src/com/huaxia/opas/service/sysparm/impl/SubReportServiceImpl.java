package com.huaxia.opas.service.sysparm.impl;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.apply.ApplyLifeCicleDao;
import com.huaxia.opas.dao.apply.BizInpApplDao;
import com.huaxia.opas.dao.credit.PatchboltDao;
import com.huaxia.opas.dao.sysparm.SubReportDao;
import com.huaxia.opas.dao.system.ApUserDao;
import com.huaxia.opas.domain.apply.ApplyLifeCicle;
import com.huaxia.opas.domain.input.BizInpApplC1;
import com.huaxia.opas.domain.sysparm.SubReport;
import com.huaxia.opas.service.AbstractService;
import com.huaxia.opas.service.sysparm.SubReportService;
import com.huaxia.opas.util.StringUtil;
import com.huaxia.opas.util.UUIDGenerator;

public class SubReportServiceImpl extends AbstractService implements SubReportService,Serializable {
	
	private static final long serialVersionUID = 1678672601627872966L;

	@Resource(name="subReportDao")
	private SubReportDao subReportDao;
	public SubReportDao getSubReportDao() {
		return subReportDao;
	}

	public void setSubReportDao(SubReportDao subReportDao) {
		this.subReportDao = subReportDao;
	}

	public PatchboltDao getPatchboltDao() {
		return patchboltDao;
	}

	public void setPatchboltDao(PatchboltDao patchboltDao) {
		this.patchboltDao = patchboltDao;
	}

	@Resource(name = "patchboltDao")
	private PatchboltDao patchboltDao;

	@Resource(name = "applyLifeCicleDao")
	private ApplyLifeCicleDao applyLifeCicleDao;
	
	@Resource(name = "apUserDao")
	private ApUserDao apUserDao;
	
	@Resource(name = "bizInpApplDao")
	private BizInpApplDao bizInpApplDao;
	

	@Override
	public int save(SubReport t) throws CoreException {
		String appId = t.getAppId();
		//判断是否已存在该申请件记录
		int count = subReportDao.querySubReportByAppId(appId);
		if(count>0){
			subReportDao.updateSubReportByAppId(t);
		}else{
			t.setAutoId(UUIDGenerator.getUUID());
			subReportDao.insertOpasSubmittypeInfo(t);
		}
		 return 1;
	}

	@Override
	public int remove(SubReport t) throws CoreException {
		return 0;
	}

	@Override
	public int update(SubReport t) throws CoreException {
		return subReportDao.updateApplyAllot(t);
	}

	@Override
	public SubReport get(SubReport t) {
		return null;
	}

	@Override
	public int queryCount(SubReport subReport) throws CoreException {
		return subReportDao.queryCount(subReport);
	}

	@Override
	public List<SubReport> queryUnfinishedList(SubReport subReport, int curNum, int pageNum) throws CoreException {
		return subReportDao.queryUnfinishedList(subReport, curNum, pageNum);
	}

	@Override
	public int queryCount1(SubReport subReport) throws CoreException {
		return subReportDao.queryCount1(subReport);
	}

	@Override
	public List<SubReport> queryFinishedList(SubReport subReport, int curNum, int pageNum) throws CoreException {
		return subReportDao.queryFinishedList(subReport, curNum, pageNum);
	}

	@Override
	public int toFinished(SubReport subReport) throws Exception {
		//根据appId判断该申请件是否为套卡
		//根据appId查询进件卡
		subReportDao.toFinished(subReport);
		subReport.setAutoId(StringUtil.randomUUIDPlainString());
		subReport.setDelStatus("2");
		subReportDao.insertOpasSubmittypeInfoHis(subReport);
		String submitType = subReport.getSubmitType();
		Map<String,Object> userMap=new HashMap<String,Object>();//全流程记录
		userMap.put("userCode", subReport.getCrtUser());
		Map<String,Object> apUserMap=bizInpApplDao.selectApUserByUuIdUserCode(userMap);
		String userCode=apUserMap.get("userCode").toString();
		String userName=apUserMap.get("userName").toString();
			ApplyLifeCicle a=new ApplyLifeCicle();
			a.setAppId(new String(subReport.getAppId()));
			a.setOperateResult("完成");
			a.setCrtDate(new Date());
			a.setUuid(StringUtil.randomUUIDPlainString());
			if(!"".equals(userCode)){
				a.setOperater(userName+"-"+userCode);
				a.setCrtUser(userCode);
			}else{
				a.setOperater("systemTb");
				a.setCrtUser("systemTb");
			}
			if("5".equals(submitType)){//add by wenyh
				if(apUserMap!=null){
					a.setOperateDesc(userName+"将申请件提报到提报反洗钱已完成队列");
				}
			}else if("4".equals(submitType)){
				if(apUserMap!=null){
					a.setOperateDesc(userName+"将申请件提报到提报授权已完成队列");
				}
			}else if("3".equals(submitType)){
				if(apUserMap!=null){
					a.setOperateDesc(userName+"将申请件提报到提报账户已完成队列");
				}
			}else if("2".equals(submitType)){
				if(apUserMap!=null){
					a.setOperateDesc(userName+"将申请件提报到提报催收已完成队列");
				}
			}
			applyLifeCicleDao.addApplyLifeCicle(a);
		int count = 0;
		String appId = subReport.getAppId();
		BizInpApplC1 bizinpapplc1 = patchboltDao.querybizInpApplList(appId)==null? new BizInpApplC1():patchboltDao.querybizInpApplList(appId);
		//0-无套卡标识  1-主卡  2-套卡
		char[] arr = appId.toCharArray();
		String ydjFlag = bizinpapplc1.getYdjFlag();
		if("1".equals(ydjFlag)){
		if(bizinpapplc1!=null){
			if(!"0".equals(bizinpapplc1.getMatchingCardFlag())){
				if(arr.length==16&&arr[15]=='1'){
					arr[15] = '2';
					subReport.setAppId(new String(arr));
					count = subReportDao.toFinished(subReport);
				}else{
					arr[15] = '1';
					subReport.setAppId(new String(arr));
					count = subReportDao.toFinished(subReport);
				}
			}
			subReport.setAutoId(StringUtil.randomUUIDPlainString());
			subReportDao.insertOpasSubmittypeInfoHis(subReport);
			if("5".equals(submitType)){//add by wenyh
				if(apUserMap!=null){
					a.setOperateDesc(userName+"将申请件提报到提报反洗钱已完成队列");
				}
			}else if("4".equals(submitType)){
				if(apUserMap!=null){
					a.setOperateDesc(userName+"将申请件提报到提报授权已完成队列");
				}
			}else if("3".equals(submitType)){
				if(apUserMap!=null){
					a.setOperateDesc(userName+"将申请件提报到提报账户已完成队列");
				}
			}else if("2".equals(submitType)){
				if(apUserMap!=null){
					a.setOperateDesc(userName+"将申请件提报到提报催收已完成队列");
				}
			}
			a.setAppId(new String(arr));
			a.setUuid(StringUtil.randomUUIDPlainString());
			applyLifeCicleDao.addApplyLifeCicle(a);
		}
	}
		return count;
	}

	@Override
	public int toUnfinished(SubReport subReport) throws Exception {
		
		subReportDao.toUnfinished(subReport.getAppId());
		String submitType = subReport.getSubmitType();
		Map<String,Object> userMap=new HashMap<String,Object>();//全流程记录
		userMap.put("userCode", subReport.getCrtUser());
		Map<String,Object> apUserMap=bizInpApplDao.selectApUserByUuIdUserCode(userMap);
		String userCode=apUserMap.get("userCode").toString();
		String userName=apUserMap.get("userName").toString();
			ApplyLifeCicle a=new ApplyLifeCicle();
			a.setAppId(new String(subReport.getAppId()));
			a.setOperateResult("完成");
			a.setCrtDate(new Date());
			a.setUuid(StringUtil.randomUUIDPlainString());
			if(!"".equals(userCode)){
				a.setOperater(userName+"-"+userCode);
				a.setCrtUser(userCode);
			}else{
				a.setOperater("systemTb");
				a.setCrtUser("systemTb");
			}
			if("5".equals(submitType)){//add by wenyh
				if(apUserMap!=null){
					a.setOperateDesc(userName+"将申请件退回到提报反洗钱未完成队列");
				}
			}else if("4".equals(submitType)){
				if(apUserMap!=null){
					a.setOperateDesc(userName+"将申请件退回到提报授权未完成队列");
				}
			}else if("3".equals(submitType)){
				if(apUserMap!=null){
					a.setOperateDesc(userName+"将申请件退回到提报账户未完成队列");
				}
			}else if("2".equals(submitType)){
				if(apUserMap!=null){
					a.setOperateDesc(userName+"将申请件退回到提报催收未完成队列");
				}
			}
			applyLifeCicleDao.addApplyLifeCicle(a);
		int count = 0;
		String appId = subReport.getAppId();
		BizInpApplC1 bizinpapplc1 = patchboltDao.querybizInpApplList(appId);
		if(bizinpapplc1==null){
			bizinpapplc1 = new BizInpApplC1();
		}
		//0-无套卡标识  1-主卡  2-套卡
		char[] arr = appId.toCharArray();
		String ydjFlag = bizinpapplc1.getYdjFlag();
		if("1".equals(ydjFlag)){
		if(bizinpapplc1!=null){
			if(!"0".equals(bizinpapplc1.getMatchingCardFlag())){
				if(arr.length==16&&arr[15]=='1'){
					arr[15] = '2';
					subReport.setAppId(new String(arr));
					count = subReportDao.toUnfinished(subReport.getAppId());
				}else{
					arr[15] = '1';
					subReport.setAppId(new String(arr));
					count = subReportDao.toUnfinished(subReport.getAppId());
				}
			}
			
			if("5".equals(submitType)){//add by wenyh
				if(apUserMap!=null){
					a.setOperateDesc(userName+"将申请件退回到提报反洗钱未完成队列");
				}
			}else if("4".equals(submitType)){
				if(apUserMap!=null){
					a.setOperateDesc(userName+"将申请件退回到提报授权未完成队列");
				}
			}else if("3".equals(submitType)){
				if(apUserMap!=null){
					a.setOperateDesc(userName+"将申请件退回到提报账户未完成队列");
				}
			}else if("2".equals(submitType)){
				if(apUserMap!=null){
					a.setOperateDesc(userName+"将申请件退回到提报催收未完成队列");
				}
			}
			a.setAppId(new String(arr));
			a.setUuid(StringUtil.randomUUIDPlainString());
			applyLifeCicleDao.addApplyLifeCicle(a);
		}
		}
		return count;
		
	}

	@Override
	public int toCredit(SubReport subReport) throws Exception {
		String currNode = subReportDao.queryCurrNode(subReport.getAppId());
		String delStatus = subReportDao.queryDelStatus(subReport.getAppId());
		if(currNode==null){
			currNode = new String();
		}
		if("2".equals(delStatus)){
			delStatus = "待补件";
		}else{
			delStatus = "未完成";
		}
		Map<String,Object> userMap=new HashMap<String,Object>();//全流程记录
		userMap.put("userCode", subReport.getCrtUser());
		Map<String,Object> apUserMap=bizInpApplDao.selectApUserByUuIdUserCode(userMap);
		String userCode=apUserMap.get("userCode").toString();
		String userName=apUserMap.get("userName").toString();
			ApplyLifeCicle a=new ApplyLifeCicle();
			a.setAppId(new String(subReport.getAppId()));
			a.setOperateResult("完成");
			a.setCrtDate(new Date());
			a.setUuid(StringUtil.randomUUIDPlainString());
			if(!"".equals(userCode)){
				a.setOperater(userName+"-"+userCode);
				a.setCrtUser(userCode);
			}else{
				a.setOperater("systemTb");
				a.setCrtUser("systemTb");
			}
			if("02".equals(currNode)){
				if(apUserMap!=null){
					a.setOperateDesc(userName+"将申请件提报到征信"+delStatus+"队列");
					}
			}else if("03".equals(currNode)){
				if(apUserMap!=null){
					a.setOperateDesc(userName+"将申请件提报到审批"+delStatus+"队列");
			
					}
			}
			applyLifeCicleDao.addApplyLifeCicle(a);
			subReportDao.toCredit(subReport);
			//申请件分配表SUBMIT_STATUS状态更新  
			subReportDao.updateApplyAllot(subReport);
		//	subReport.setCrtUser(userName);
			subReport.setAutoId(UUIDGenerator.getUUID());
			SubReport report = subReportDao.querySubReason(subReport.getAppId());
			subReport.setDelStatus(report.getDelStatus());
			subReport.setMemo(report.getMemo());
			subReport.setSubReason(report.getSubReason());
			subReport.setPercent(report.getPercent());
			subReportDao.insertOpasSubmittypeInfoHis(subReport);
			//subReport.setCrtUser(userCode);
			subReportDao.deleteOpasSubmittypeInfoByAppId(subReport.getAppId());
		int count = 0;
		String appId = subReport.getAppId();
		BizInpApplC1 bizinpapplc1 = patchboltDao.querybizInpApplList(appId)==null?new BizInpApplC1():patchboltDao.querybizInpApplList(appId);
		//0-无套卡标识  1-主卡  2-套卡
		char[] arr = appId.toCharArray();
		String ydjFlag = bizinpapplc1.getYdjFlag();
		if("1".equals(ydjFlag)){
		if(bizinpapplc1!=null){
			if(!"0".equals(bizinpapplc1.getMatchingCardFlag())){
				if(arr.length==16&&arr[15]=='1'){
					arr[15] = '2';
					subReport.setAppId(new String(arr));					
					count = subReportDao.toCredit(subReport);
					subReportDao.updateApplyAllot(subReport);
					//subReport.setCrtUser(userName);
					subReport.setAutoId(UUIDGenerator.getUUID());
					subReportDao.insertOpasSubmittypeInfoHis(subReport);
				}else{
					arr[15] = '1';
					subReport.setAppId(new String(arr));
					count = subReportDao.toCredit(subReport);
					subReportDao.updateApplyAllot(subReport);
					//subReport.setCrtUser(userName);
					subReport.setAutoId(UUIDGenerator.getUUID());
					subReportDao.insertOpasSubmittypeInfoHis(subReport);
				}
				subReportDao.deleteOpasSubmittypeInfoByAppId(subReport.getAppId());
			}
			
			if("02".equals(currNode)){
				if(apUserMap!=null){
					a.setOperateDesc(userName+"将申请件提报到征信"+delStatus+"队列");
					
					}
			}else if("03".equals(currNode)){
				if(apUserMap!=null){
					a.setOperateDesc(userName+"将申请件提报到审批"+delStatus+"队列");
					
					}
			}
			a.setAppId(new String(arr));
			a.setUuid(StringUtil.randomUUIDPlainString());
			applyLifeCicleDao.addApplyLifeCicle(a);
		}
		}
		return count;
	}

	@Override
	public int commitAll(SubReport subReport) throws Exception {
		List<SubReport> list = new ArrayList<SubReport>();
		 list = subReportDao.selectCommitAll(subReport);
		 Map<String,Object> userMap=new HashMap<String,Object>();//全流程记录
		 userMap.put("userCode", subReport.getCrtUser());
		 Map<String,Object> apUserMap=bizInpApplDao.selectApUserByUuIdUserCode(userMap);
		 String userCode=apUserMap.get("userCode").toString();
		 String userName=apUserMap.get("userName").toString();
		 ApplyLifeCicle a=new ApplyLifeCicle();
		 if(!"".equals(userCode)){
			 a.setOperater(userName+"-"+userCode);
			 a.setCrtUser(userCode);
		 }else{
			 a.setOperater("systemTb");
			 a.setCrtUser("systemTb");
		 }
		 a.setOperateResult("完成");
		 for(int i=0;i<list.size();i++){
			 subReport.setAppId(list.get(i).getAppId());
			 String currNode = subReportDao.queryCurrNode(subReport.getAppId());
				String delStatus = subReportDao.queryDelStatus(subReport.getAppId());
				SubReport report = subReportDao.querySubReason(subReport.getAppId());
				if("2".equals(delStatus)){
					delStatus = "待补件";
				}else{
					delStatus = "未完成";
				}
					a.setAppId(new String(subReport.getAppId()));
					a.setCrtDate(new Date());
					a.setUuid(StringUtil.randomUUIDPlainString());
					if("02".equals(currNode)){
						if(apUserMap!=null){
							a.setOperateDesc(userName+"将申请件提报到征信"+delStatus+"队列");
							}
					}else if("03".equals(currNode)){
						if(apUserMap!=null){
							a.setOperateDesc(userName+"将申请件提报到审批"+delStatus+"队列");

							}
					}
					applyLifeCicleDao.addApplyLifeCicle(a);
					subReport.setAutoId(UUIDGenerator.getUUID());
					subReport.setDelStatus("1");
					subReport.setMemo(report.getMemo());
					subReport.setSubReason(report.getSubReason());
					subReport.setPercent(report.getPercent());
					subReport.setYdjFlag(report.getYdjFlag());
					subReportDao.updateApplyAllot(subReport);
					//subReport.setCrtUser(userName);
					subReportDao.insertOpasSubmittypeInfoHis(subReport);
					subReportDao.deleteOpasSubmittypeInfoByAppId(subReport.getAppId());
					//subReport.setCrtUser(userCode);
		 }
		return subReportDao.commitAll(subReport);
	}

	@Override
	public SubReport querySubReason(String appId) throws CoreException {
		return subReportDao.querySubReason(appId);
	}

	@Override
	public int queryDayCount(Map<String, Object> map) throws CoreException {
		
		return subReportDao.queryDayCount(map);
	}

	@Override
	public int queryMonthCount(Map<String, Object> map) throws CoreException {
		
		Map<String, String> dataMap = (Map<String, String>) map.get("json");
		
		String startTime = dataMap.get("startTime");
		
		startTime += " 00:00:00";
		
		String endTime = dataMap.get("endTime");
		endTime += " 23:59:59";
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date start = null;
		Date end = null;
		try {
			start = sdf.parse(startTime);
			end = sdf.parse(endTime);
		} catch (ParseException e) {
		}
		
		map.put("startTime",start );
		map.put("endTime", end);
		return subReportDao.queryMonthCount(map);
	}

	@Override
	public Date queryDate(Date start) throws CoreException {
		return subReportDao.queryDate(start);
	}

	@Override
	public List<SubReport> selectCommitAll(SubReport subReport) throws CoreException {
		return subReportDao.selectCommitAll(subReport);
	}

	@Override
	public int updateApplyAllotList(List<SubReport> list) throws CoreException {
		return subReportDao.updateApplyAllotList(list);
	}
}
