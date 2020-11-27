package com.huaxia.opas.service.retrieve.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.LoggerFactory;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.allot.AllotApplyAllotDao;
import com.huaxia.opas.dao.allot.AllotApplyAllotHisDao;
import com.huaxia.opas.dao.apply.ApplyRemarkDao;
import com.huaxia.opas.dao.apply.BizInpApplC1Dao;
import com.huaxia.opas.dao.apply.BizInpApplDao;
import com.huaxia.opas.dao.decision.BizApprovalDao;
import com.huaxia.opas.dao.decision.BizApprovalOperatedateDao;
import com.huaxia.opas.dao.decision.BizApprovalOperateexcDao;
import com.huaxia.opas.dao.retrieve.FicoBigResponseDao;
import com.huaxia.opas.dao.retrieve.FinishCountDao;
import com.huaxia.opas.dao.rule.OpasBizApprovalDao;
import com.huaxia.opas.dao.system.ApOrgDao;
import com.huaxia.opas.dao.system.ApUserDao;
import com.huaxia.opas.dao.system.ApUserOrgDao;
import com.huaxia.opas.domain.allot.AllotApplyAllot;
import com.huaxia.opas.domain.allot.AllotApplyAllotHis;
import com.huaxia.opas.domain.apply.ApplyRemark;
import com.huaxia.opas.domain.input.BizApprovalOperatedate;
import com.huaxia.opas.domain.input.BizApprovalOperateexc;
import com.huaxia.opas.domain.input.BizInpAppl;
import com.huaxia.opas.domain.input.BizInpApplC1;
import com.huaxia.opas.domain.retrieve.MemberEcxception;
import com.huaxia.opas.domain.retrieve.MemberJobCount;
import com.huaxia.opas.domain.retrieve.MemberJobCountApproveCheckUp;
import com.huaxia.opas.domain.retrieve.QueryStaffJobDetail;
import com.huaxia.opas.domain.system.ApUser;
import com.huaxia.opas.domain.system.ApUserOrg;
import com.huaxia.opas.service.AbstractService;
import com.huaxia.opas.service.retrieve.RetrieveService;

public class RetrieveServiceImpl extends AbstractService implements RetrieveService, Serializable  {

	private static final long serialVersionUID = -5396119375549045415L;

	private static org.slf4j.Logger logger = LoggerFactory
			.getLogger(RetrieveServiceImpl.class);

	
	@Resource(name = "bizApprovalOperateexcDao")
	private BizApprovalOperateexcDao bizApprovalOperateexcDaoImpl;

	@Resource(name = "bizApprovalOperatedateDao")
	private BizApprovalOperatedateDao bizApprovalOperatedateDaoImpl;

	@Resource(name = "ficoBigResponseDao")
	private FicoBigResponseDao ficoBigResponseDaoImpl;

	@Resource(name = "opasBizApprovalDao")
	private OpasBizApprovalDao opasBizApprovalDaoImpl;

	@Resource(name = "apOrgDao")
	private ApOrgDao apOrgDaoImpl;

	@Resource(name = "apUserDao")
	private ApUserDao apUserDaoImpl;

	@Resource(name = "allotApplyAllotDao")
	private AllotApplyAllotDao allotApplyAllotDaoImpl;

	@Resource(name = "apUserOrgDao")
	private ApUserOrgDao apUserOrgDaoImpl;

	@Resource(name = "bizInpApplDao")
	private BizInpApplDao bizInpApplDaoImpl;

	@Resource(name = "allotApplyAllotHisDao")
	private AllotApplyAllotHisDao allotApplyAllotHisDaoImpl;

	@Resource(name = "bizInpApplC1nDao")
	private BizInpApplC1Dao bizInpApplC1nDaoDaoImpl;

	@Resource(name = "finishCountDao")
	private FinishCountDao finishCountDaoImpl;

	@Resource(name = "applyRemarkDao")
	private ApplyRemarkDao applyRemarkDaoImpl;

	@Resource(name = "bizApprovalDao")
	private BizApprovalDao bizApprovalDaoImpl;
	
	@Resource(name = "bizInpApplDao")
	private BizInpApplDao bizInpApplDao;
	

	@Override
	public ApUser queryApUserByUserCode(String userCode) throws CoreException {
		return apUserDaoImpl.queryApUserByUserCode(userCode);
	}

	@Override
	public ApUserOrg queryApUserOrgByUserIdMember(String userId) throws CoreException {
		return (ApUserOrg) apUserOrgDaoImpl.queryApUserOrgByUserIdMember(userId);
	}

	@Override
	public int queryApUserOrgByOrgIdCount(String userId) throws CoreException {
		return apUserOrgDaoImpl.queryApUserOrgByOrgIdCount(userId);
	}

	@Override
	public List<ApUserOrg> queryApUserOrgByOrgId(String orgId, int curNum, int pageNum) throws CoreException {
		return apUserOrgDaoImpl.queryApUserOrgByOrgId(orgId, curNum, pageNum);
	}

	@Override
	public ApUser queryApUserByUserId(String userId) throws CoreException {
		return apUserDaoImpl.queryApUserByUserId(userId);
	}

	@Override
	public int queryBizInpApplCountStatusZero(String userCode) throws CoreException {
		return bizInpApplDaoImpl.queryBizInpApplCountStatusZero(userCode);
	}

	@Override
	public int queryBizInpApplCountStatusFinised(String userCode) throws CoreException {
		
		return bizInpApplDaoImpl.queryBizInpApplCountStatusFinised(userCode);
	}
	
	@Override
	public int queryBizInpApplCountStatusTwo(String userCode) throws CoreException {
		return bizInpApplDaoImpl.queryBizInpApplCountStatusTwo(userCode);
	}

	@Override
	public int queryBizInpApplCountStatusThree(String userCode) throws CoreException {
		return bizInpApplDaoImpl.queryBizInpApplCountStatusThree(userCode);
	}

	@Override
	public int queryBizInpApplCountStatusFour(String userCode) throws CoreException {
		return bizInpApplDaoImpl.queryBizInpApplCountStatusFour(userCode);
	}

	@Override
	public int queryBizInpApplHisCount(Map map) throws CoreException {
		return allotApplyAllotHisDaoImpl.queryBizInpApplHisCount(map);
	}

	@Override
	public List<BizInpAppl> queryBizInpApplByCurrOptUser(String currOptUser) throws CoreException {
		return bizInpApplDaoImpl.queryBizInpApplByCurrOptUser(currOptUser);
	}

	@Override
	public List<AllotApplyAllotHis> queryDetailBizInpApplHisByCurrOptUser(Map map) throws CoreException {
		return allotApplyAllotHisDaoImpl.queryDetailBizInpApplHisByCurrOptUser(map);
	}

	@Override
	public BizInpApplC1 queryBizInpApplC1ByAppIdOrder(String appId) throws CoreException {
		return bizInpApplC1nDaoDaoImpl.queryBizInpApplC1ByAppIdOrder(appId);
	}

	@Override
	public int queryCountStatusAndUserCodeAnddelStatus(Map map) throws CoreException {
		return bizInpApplDaoImpl.queryCountStatusAndUserCodeAnddelStatus(map);
	}
	
	@Override
	public int queryFinisedApp(Map map) throws CoreException {
		return bizInpApplDaoImpl.queryFinisedApp(map);
	}

	@Override
	public int selectByCurrNodeAndUserCode(Map map) throws CoreException {
		return allotApplyAllotHisDaoImpl.selectByCurrNodeAndUserCode(map);
	}

	@Override
	public List<BizInpAppl> queryBizInpApplByCurrOptUserCheckup(String userCode) throws CoreException {
		return bizInpApplDaoImpl.queryBizInpApplByCurrOptUserCheckup(userCode);
	}

	@Override
	public List<BizInpAppl> queryBizInpApplByCurrOptUserApprove(String userCode) throws CoreException {
		return bizInpApplDaoImpl.queryBizInpApplByCurrOptUserApprove(userCode);
	}

	
	@Override
	public List<ApplyRemark> checkRemarkInfo(String appId) throws CoreException {
		return applyRemarkDaoImpl.checkRemarkInfo(appId);
	}

	@Override
	public int saveNemberRemarks(ApplyRemark applyRemark) throws CoreException {
		return applyRemarkDaoImpl.saveNemberRemarks(applyRemark);
	}

	@Override
	public List<AllotApplyAllotHis> querySingleInfoHis(String appId) throws CoreException {
		return allotApplyAllotHisDaoImpl.querySingleInfoHis(appId);
	}

	@Override
	public List<AllotApplyAllot> querySingleInfo(String appId) throws CoreException {
		return allotApplyAllotDaoImpl.querySingleInfo(appId);
	}

	@Override
	public List<Map<String, String>> queryApOrg() throws CoreException {
		return apOrgDaoImpl.queryApOrg();
	}

	@Override
	public List<AllotApplyAllotHis> queryStaffJobDetailByCurrNodeHis(String currNode) throws CoreException {
		return allotApplyAllotHisDaoImpl.queryStaffJobDetailByCurrNodeHis(currNode);
	}

	@Override
	public List<AllotApplyAllot> queryStaffJobDetailByCurrNode(String currNode) throws CoreException {
		return allotApplyAllotDaoImpl.queryStaffJobDetailByCurrNode(currNode);
	}

	@Override
	public int querySPGJS(Map<String, Object> dataMap) throws CoreException {
		return opasBizApprovalDaoImpl.querySPGJS(dataMap);
	}

	@Override
	public int querySPJJS(Map<String, Object> dataMap) throws CoreException {
		return opasBizApprovalDaoImpl.querySPJJS(dataMap);
	}

	@Override
	public int queryYCFQZJJS(Map<String, Object> dataMap) throws CoreException {
		return ficoBigResponseDaoImpl.queryYCFQZJJS(dataMap);
	}

	@Override
	public int queryECFQZJJS(Map<String, Object> dataMap) throws CoreException {
		return ficoBigResponseDaoImpl.queryECFQZJJS(dataMap);
	}

	@Override
	public int queryYCJCJJS(Map<String, Object> dataMap) throws CoreException {
		return ficoBigResponseDaoImpl.queryYCJCJJS(dataMap);
	}

	@Override
	public int queryYCJCGJS(Map<String, Object> dataMap) throws CoreException {
		return ficoBigResponseDaoImpl.queryYCJCGJS(dataMap);
	}

	@Override
	public int queryECJCJJS(Map<String, Object> dataMap) throws CoreException {
		return ficoBigResponseDaoImpl.queryECJCJJS(dataMap);
	}

	@Override
	public int queryECJCGJS(Map<String, Object> dataMap) throws CoreException {
		return ficoBigResponseDaoImpl.queryECJCGJS(dataMap);
	}

	@Override
	public int selectAllCount() throws CoreException {
		return bizApprovalOperatedateDaoImpl.selectAllCount();
	}

	@Override
	public List<BizApprovalOperatedate> selectAll(int curNum, int pageNum) throws CoreException {
		return bizApprovalOperatedateDaoImpl.selectAll(curNum, pageNum);
	}

	@Override
	public int queryExceptionDateLong(String operateCode) throws CoreException {
		return bizApprovalOperateexcDaoImpl.queryExceptionDateLong(operateCode);
	}

	@Override
	public List<BizApprovalOperateexc> memberJobEcxceptionDetail(String operateCode) {
		return bizApprovalOperateexcDaoImpl.memberJobEcxceptionDetail(operateCode);
	}
	@Override
	public AllotApplyAllot queryCurrNodeByAppId(String appId) throws CoreException {
		return allotApplyAllotDaoImpl.selectByPrimaryKey(appId);
	}

	@Override
	public List<BizApprovalOperatedate> queryByName(String userName) {
		return bizApprovalOperatedateDaoImpl.queryByName(userName);
	}
	
	//查出在  征信  审批  录入 角色
	public Map<String, Object> getRoleCode(Map<String, Object> map){
		//区分角色
		List<String> roleCodes=new ArrayList<String>();
		int count = 0;
		List<ApUserOrg> apUserOrgList= new ArrayList<ApUserOrg>();
		String orgId = (String) map.get("orgId");
		if (null==orgId||"".equals(orgId)){
			// 根据userCode查询 ApUser
			String userCode = (String) map.get("userCode");
			Map<String, String> apUserOrgMap = apUserOrgDaoImpl.selectApOrgByUserCode(userCode);
			orgId = apUserOrgMap.get("orgId");
		}
		
		String currNode = (String) map.get("currNode");
		if("01".equals(currNode)){
			 roleCodes.add("DE");
		 }else if("02".equals(currNode)){
			 roleCodes.add("CI");
			 //政审合一统一用一个
			 roleCodes.add("HY");
		 }else if("03".equals(currNode)){
			 roleCodes.add("L1");
			 roleCodes.add("L2");
			 roleCodes.add("L3");
		 }else if("05".equals(currNode)){//欺诈
			 roleCodes.add("AP");
			 roleCodes.add("DA");
			 roleCodes.add("DAN");
		 } else if ("06".equals(currNode)){
			//政审合一统一用一个
			 roleCodes.add("HY");
		 }
		map.put("roleCodes", roleCodes);
		map.put("orgId", orgId);
		
		count = apUserOrgDaoImpl.selectApUserOrgCount(map);
		if (count > 0) {
			// 查看 所有成员list
			apUserOrgList  = apUserOrgDaoImpl.selectApUserOrgByOrgId(map);
		}
		map.put("count",count);
		map.put("apUserOrgList",apUserOrgList);
		return map;
	}
	
	//征信List
	@Override
	public Map<String, Object> queryApUserOrgListByUserCode(Map<String, Object> map) {
		//Map<String, Object> map = new HashMap<String, Object>();
		List<MemberJobCount> memberJobCountList = new ArrayList<MemberJobCount>();
		ApUser apUser = new ApUser();
		String startTime = (String) map.get("startTime");
		//查出组中所有成员
		Map<String, Object> maps = getRoleCode(map);
		List<ApUserOrg> apUserOrgList = (List<ApUserOrg>) maps.get("apUserOrgList");
		ApUserOrg apUserOrg = new ApUserOrg();
		int sumTotal = 0;
		int wwcTotal = 0;
		int wcTotal = 0;
		int bjTotal = 0;
		int tjTotal = 0 ;
		int gqTotal = 0;
		int total =0;
		int gdTotal = 0;
		int subTotal = 0;
		try {
			for (int i = 0; i < apUserOrgList.size(); i++) {
				apUserOrg = apUserOrgList.get(i);
				MemberJobCount memberJobCount = new MemberJobCount();
				memberJobCount.setUserId(apUserOrg.getUserId());
				apUser = apUserDaoImpl.queryApUserByUserId(apUserOrg.getUserId());
				if (apUser == null) {
					continue;
				}
				memberJobCount.setStaffName(apUser.getUserName());

				String code = apUser.getUserCode();
				Map<String, Object> paramMap = new HashMap<String, Object>();
				// DEL_STATUS = 0 未完成
				paramMap.put("userId", code);
				paramMap.put("currNode", "02");
				paramMap.put("delStatus", "0");
				paramMap.put("currStatus", "3");
				int hangInTheAirStatus=bizInpApplDao.selectZxMemerJobCount(paramMap);
				memberJobCount.setHangInTheAirStatus(hangInTheAirStatus);
				
				// 已完成
				paramMap.put("lastOptUser", code);
				paramMap.put("delStatus", "0");
				paramMap.put("currNode", "03");
				paramMap.put("currStatus", "");
				int finishedStatus = bizInpApplDao.selectZxMemerJobFinishedCount(paramMap);
				memberJobCount.setFinishedStatus(finishedStatus);

				// DEL_STATUS = 2 补件
				paramMap.put("userId", code);
				paramMap.put("delStatus", "2");
				paramMap.put("currNode", "02");
				paramMap.put("currStatus", "3");
				int supplementLetter = bizInpApplDao.selectZxMemerJobCount(paramMap);
				memberJobCount.setSupplementLetter(supplementLetter);

				// DEL_STATUS = 3 退回
				paramMap.put("userId", code);
				paramMap.put("delStatus", "3");
				paramMap.put("currNode", "02");
				paramMap.put("currStatus", "3");
				int returnDocument = bizInpApplDao.selectZxMemerJobCount(paramMap);
				memberJobCount.setReturnDocument(returnDocument);

				// DEL_STATUS = 4 挂起
				paramMap.put("userId", code);
				paramMap.put("currNode", "02");
				paramMap.put("currStatus", "4");
				paramMap.put("delStatus", "");
				int pauseDocument = bizInpApplDao.selectZxMemerJobCount(paramMap);
				memberJobCount.setPauseDocument(pauseDocument);
				
				Date s = new Date();
				SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
				String startDate = null;
				startDate = sf.format(s);
				if("".equals(startTime)||null==startTime){
					paramMap.put("startTime", startDate);
					paramMap.put("endTime", startDate);
				} else {
					paramMap.put("startTime", startTime);
					paramMap.put("endTime", startTime);
				}
				paramMap.put("auditor", code);
				 
				int subNum = finishCountDaoImpl.memberJobCompletCount(paramMap);
				memberJobCount.setSubNum(subNum);
				int gdNum = finishCountDaoImpl.memberJobCompletCountHis(paramMap);
				memberJobCount.setGdNum(gdNum);
				// 合计
				total = hangInTheAirStatus + supplementLetter + returnDocument + pauseDocument ;
				sumTotal = total+sumTotal;
				wwcTotal = wwcTotal+hangInTheAirStatus;
				wcTotal = wcTotal+finishedStatus;
				bjTotal = bjTotal+supplementLetter;
				tjTotal = tjTotal+returnDocument ;
				gqTotal = gqTotal+pauseDocument;
				gdTotal = gdTotal+gdNum;
				subTotal = subTotal+subNum;
				memberJobCount.setTotal(total);
				memberJobCount.setSumTotal(sumTotal);

				memberJobCountList.add(memberJobCount);
			}
		} catch (Exception e) {
		}
		MemberJobCount memberJobCountHj = new MemberJobCount();
		memberJobCountHj.setTotal(sumTotal);
		memberJobCountHj.setStaffName("合计");
		memberJobCountHj.setHangInTheAirStatus(wwcTotal);
		memberJobCountHj.setFinishedStatus(wcTotal);
		memberJobCountHj.setSupplementLetter(bjTotal);
		memberJobCountHj.setReturnDocument(tjTotal);
		memberJobCountHj.setPauseDocument(gqTotal);
		memberJobCountHj.setSubNum(subTotal);
		memberJobCountHj.setGdNum(gdTotal);
		List<MemberJobCount> memberJobCountListHtml = new ArrayList<MemberJobCount>();
		memberJobCountListHtml.add(memberJobCountHj);
		memberJobCountListHtml.addAll(memberJobCountList);
		map.put("total", memberJobCountListHtml.size());
		map.put("rows", memberJobCountListHtml);
		return map;
	}
	//分页
	public Map<String, Object> paging(Map<String, Object> map) {
		List<MemberJobCount> memberJobCountList = (List<MemberJobCount>) map.get("memberJobCountList");
		int page=(int) map.get("curNum");
		int pageSize=(int) map.get("pageNum");
		int showRowsCountOne=0;
		if(page>1){
		 showRowsCountOne=pageSize*(page-1);
		}
		int showRowsCount=page*pageSize;
		int allCount=memberJobCountList.size();
		List<MemberJobCount> memberJobList = new ArrayList<MemberJobCount>();
		int num=1;
		for (MemberJobCount queryStaffJobDetail2 : memberJobCountList) {
			if(showRowsCountOne<num&&num<=showRowsCount){
				memberJobList.add(queryStaffJobDetail2);
			}
			num++;
		}
		map.put("sumTotal", map.get("sumTotal"));
		map.put("total", allCount);
		map.put("rows", memberJobList);
		map.put("fig", "true");
		return map;
	}
	//审查
	@Override
	public Map<String, Object> queryMemberJobCountCheckup(Map<String, Object> map) {
		//Map<String, Object> map = new HashMap<String, Object>();
		List<MemberJobCount> memberJobCountList = new ArrayList<MemberJobCount>();
		ApUser apUser = new ApUser();
		//查出组中所有成员
		String startTime = (String) map.get("startTime");
		Map<String, Object> maps = getRoleCode(map);
		List<ApUserOrg> apUserOrgList = (List<ApUserOrg>) maps.get("apUserOrgList");
		ApUserOrg apUserOrg = new ApUserOrg();
		int sumTotal = 0;
		int wwcTotal = 0;
		int wcTotal = 0;
		int gdTotal = 0;
		int subTotal = 0;
		try {
			for (int i = 0; i < apUserOrgList.size(); i++) {
				apUserOrg = apUserOrgList.get(i);
				MemberJobCount memberJobCount = new MemberJobCount();
				memberJobCount.setUserId(apUserOrg.getUserId());
				apUser = apUserDaoImpl.queryApUserByUserId(apUserOrg.getUserId());
				if (apUser == null) {
					continue;
				}
				memberJobCount.setStaffName(apUser.getUserName());

				String code = apUser.getUserCode();

				map.put("userId", code);
				map.put("currNode", "01");
				map.put("delStatus", "0");
				map.put("currStatus", "3");
				// DEL_STATUS = 0 未完成
				int hangInTheAirStatus = bizInpApplDao.selectCountCheckup(map);
				memberJobCount.setHangInTheAirStatus(hangInTheAirStatus);

				// 已挂起
				map.put("userId", code);
				map.put("currNode", "01");
				map.put("delStatus", "");
				map.put("currStatus", "4");
				int finishedStatus = bizInpApplDao.selectCountCheckup(map);
				memberJobCount.setFinishedStatus(finishedStatus);
				
				Date s = new Date();
				SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
				String startDate = null;
				startDate = sf.format(s);
				if("".equals(startTime)||null==startTime){
					map.put("startTime", startDate);
					map.put("endTime", startDate);
				} else {
					map.put("startTime", startTime);
					map.put("endTime", startTime);
				}
				map.put("auditor", code);
				
				int subNum = finishCountDaoImpl.memberJobCompletCountCheckup(map);
				memberJobCount.setSubNum(subNum);
				int gdNum = finishCountDaoImpl.memberJobCheckup(map);
				memberJobCount.setGdNum(gdNum);
				// 合计
				subTotal = subTotal + subNum;
				gdTotal = gdTotal +gdNum;
				wwcTotal = hangInTheAirStatus+wwcTotal;
				wcTotal = finishedStatus+wcTotal;
				int total = hangInTheAirStatus + finishedStatus;
				
				sumTotal = sumTotal + total;
				memberJobCount.setTotal(total);
				memberJobCountList.add(memberJobCount);
			}
		} catch (Exception e) {
		}
		MemberJobCount memberJobCountHj = new MemberJobCount();
		memberJobCountHj.setTotal(sumTotal);
		List<MemberJobCount> memberJobCountListHtml = new ArrayList<MemberJobCount>();
		memberJobCountHj.setStaffName("合计");
		memberJobCountHj.setHangInTheAirStatus(wwcTotal);
		memberJobCountHj.setFinishedStatus(wcTotal);
		memberJobCountHj.setGdNum(gdTotal);
		memberJobCountHj.setSubNum(subTotal);
		memberJobCountListHtml.add(memberJobCountHj);
		memberJobCountListHtml.addAll(memberJobCountList);
		map.put("total", memberJobCountListHtml.size());
		map.put("rows", memberJobCountListHtml);
		return map;
	}

	//审批人员
	@Override
	public Map<String, Object> queryMemberJobApprove(Map<String, Object>map) {
		
		List<MemberJobCount> memberJobCountList = new ArrayList<MemberJobCount>();
		ApUser apUser = new ApUser();
		//查出组中所有成员
		Map<String, Object> maps = getRoleCode(map);
		List<ApUserOrg> apUserOrgList = (List<ApUserOrg>) maps.get("apUserOrgList");
		ApUserOrg apUserOrg = new ApUserOrg();
		String startTime = (String) map.get("startTime");
		int sumTotal = 0;
		int wwcTotal = 0;
		int wcTotal = 0;
		int bjTotal = 0;
		int gqTotal = 0;
		int gdTotal = 0;
		int subTotal = 0;
		if (!apUserOrgList.isEmpty()){
			for (int i = 0; i < apUserOrgList.size(); i++) {
				apUserOrg = apUserOrgList.get(i);
				
				MemberJobCount memberJobCount = new MemberJobCount();
				memberJobCount.setUserId(apUserOrg.getUserId());
				apUser = apUserDaoImpl.queryApUserByUserId(apUserOrg.getUserId());
				if (apUser == null) {
					continue;
				}
				memberJobCount.setStaffName(apUser.getUserName());
				//memberJobCount = new MemberJobCount();
				memberJobCount.setUserId(apUserOrg.getUserId());
				memberJobCount.setStaffName(apUser.getUserName());
				String code = apUser.getUserCode();
				
				Map<String, Object> record = new HashMap<String, Object>();
				// DEL_STATUS = 0 未完成
				record.put("currNode", "03");
				record.put("userId", code);
				record.put("delStatus", "0");
				record.put("currStatus", "3");
				record.put("checkMeuoflag", "1");//普通审批checkMeuoflag = 1
				int hangInTheAirStatus = bizInpApplDao.selectBizInpApplApproveCount(record);
				//hangInTheAirStatus = retrieveServiceImpl.queryCountStatusAndUserCodeAnddelStatus(parMap);
				memberJobCount.setHangInTheAirStatus(hangInTheAirStatus);
				//补件
				record.put("delStatus", "2");
				record.put("userId", code);
				record.put("currStatus", "3");
				record.put("currNode", "03");
				record.put("checkMeuoflag", "1");
				int supplementLetter = bizInpApplDao.selectBizInpApplApproveCount(record);
				//supplementLetter = retrieveServiceImpl.queryCountStatusAndUserCodeAnddelStatus(parMap);
				memberJobCount.setSupplementLetter(supplementLetter);
				
				//挂起
				record.put("currStatus", "4");
				record.put("currNode", "03");
				record.put("userId", code);
				record.put("delStatus", "");
				record.put("checkMeuoflag", "1");
				int pauseDocument = bizInpApplDao.selectBizInpApplApproveCount(record);
				//pauseDocument = retrieveServiceImpl.queryCountStatusAndUserCodeAnddelStatus(parMap);
				memberJobCount.setPauseDocument(pauseDocument);
	
				// 已完成
				record.put("delStatus", "1");
				record.put("userId", code);
				record.put("currNode","04");
				record.put("currStatus","");
				record.put("checkMeuoflag", "1");
				int finishedStatus = bizInpApplDao.selectApprovefinishedCount(record);
				memberJobCount.setFinishedStatus(finishedStatus);
				
				Date s = new Date();
				SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
				String startDate = null;
				startDate = sf.format(s);
				if("".equals(startTime)||null==startTime){
					record.put("endTime", startDate);
					record.put("startTime", startDate);
				} else {
					record.put("endTime", startTime);
					record.put("startTime", startTime);
				}
				
				record.put("checkMeuoFlag", "1");
				record.put("auditor", code);
				int subNum = bizApprovalDaoImpl.selectMemberJobCompletCountApprove(record);
				memberJobCount.setSubNum(subNum);
				int gdNum = finishCountDaoImpl.memberJobCompletCountApprove(record);
				memberJobCount.setGdNum(gdNum);
				// 合计
				int total = hangInTheAirStatus + supplementLetter + pauseDocument ;
				subTotal = subTotal + subNum;
				gdTotal = gdTotal +gdNum;
				sumTotal = sumTotal + total;
				memberJobCount.setTotal(total);
				wwcTotal =wwcTotal+hangInTheAirStatus;
				wcTotal = wcTotal+finishedStatus;
				bjTotal = bjTotal+supplementLetter;
				gqTotal = gqTotal+pauseDocument;
				memberJobCountList.add(memberJobCount);
			}
		} 	
		if (memberJobCountList.isEmpty()){
			map.put("total", memberJobCountList.size());
			map.put("rows", memberJobCountList);
			map.put("fig", "true");
		} else {
			MemberJobCount memberJobCountHj = new MemberJobCount();
			memberJobCountHj.setTotal(sumTotal);
			memberJobCountHj.setStaffName("合计");
			memberJobCountHj.setHangInTheAirStatus(wwcTotal);
			memberJobCountHj.setFinishedStatus(wcTotal);
			memberJobCountHj.setSupplementLetter(bjTotal);
			memberJobCountHj.setPauseDocument(gqTotal);
			memberJobCountHj.setGdNum(gdTotal);
			memberJobCountHj.setSubNum(subTotal);
			List<MemberJobCount> memberJobCountListHtml = new ArrayList<MemberJobCount>();
			memberJobCountListHtml.add(memberJobCountHj);
			memberJobCountListHtml.addAll(memberJobCountList);
			map.put("total", memberJobCountListHtml.size());
			map.put("rows", memberJobCountListHtml);
		}
		return map;
	}
	//征审合一
	@Override
	public Map<String, Object> queryMemberJobApproveCheckUp(
			Map<String, Object> map) {
		List<MemberJobCountApproveCheckUp> memberJobCountList = new ArrayList<MemberJobCountApproveCheckUp>();
		ApUser apUser = new ApUser();
		String startTime = (String) map.get("startTime");
		//查出组中所有成员
		Map<String, Object> maps = getRoleCode(map);
		List<ApUserOrg> apUserOrgList = (List<ApUserOrg>) maps.get("apUserOrgList");
		ApUserOrg apUserOrg = new ApUserOrg();
		int sumTotal = 0;
		int wwcTotal = 0;
		int wcTotal = 0;
		int bjTotal = 0;
		int tjTotal = 0;
		int gqTotal = 0;
		int zxwwcTotal = 0;
		int zxbjTotal = 0;
		int gdTotal = 0;
		int subTotal = 0;
		
		if (!apUserOrgList.isEmpty()){
			for (int i = 0; i < apUserOrgList.size(); i++) {
				apUserOrg = apUserOrgList.get(i);
				
				MemberJobCountApproveCheckUp memberJobCount = new MemberJobCountApproveCheckUp();
				memberJobCount.setUserId(apUserOrg.getUserId());
				apUser = apUserDaoImpl.queryApUserByUserId(apUserOrg.getUserId());
				if (apUser == null) {
					continue;
				}
				memberJobCount.setStaffName(apUser.getUserName());
				memberJobCount.setUserId(apUserOrg.getUserId());
				String code = apUser.getUserCode();
				
				Map<String, Object> record = new HashMap<String, Object>();
				// DEL_STATUS = 0审批 未完成
				record.put("currNode", "03");
				record.put("userId", code);
				record.put("delStatus", "0");
				record.put("currStatus", "3");
				record.put("checkMeuoflag", "2");//征审合一审批 checkMeuoflag = 2
				int hangInTheAirStatus = bizInpApplDao.selectBizInpApplApproveCount(record);
				memberJobCount.setHangInTheAirStatus(hangInTheAirStatus);
				//审批补件
				record.put("delStatus", "2");
				record.put("userId", code);
				record.put("currStatus", "3");
				record.put("currNode", "03");
				record.put("checkMeuoflag", "2");
				int supplementLetter = bizInpApplDao.selectBizInpApplApproveCount(record);
				memberJobCount.setSupplementLetter(supplementLetter);
				
				//审批 已挂起
				record.put("currStatus", "4");
				record.put("currNode", "03");
				record.put("userId", code);
				record.put("delStatus", "");
				record.put("checkMeuoflag", "2");
				int pauseDocument1 = bizInpApplDao.selectBizInpApplApproveCount(record);
				
				//征信  已挂起
				record.put("currStatus", "4");
				record.put("currNode", "02");
				record.put("userId", code);
				record.put("delStatus", "");
				int pauseDocument2 = bizInpApplDao.selectZxMemerJobCount(record);
				int pauseDocument = pauseDocument1 +pauseDocument2;
				memberJobCount.setPauseDocument(pauseDocument);
	
				//征审合一   已完成
				record.put("delStatus", "1");
				record.put("userId", code);
				record.put("currNode","04");
				record.put("currStatus","");
				record.put("checkMeuoflag", "2");
				int finishedStatus = bizInpApplDao.selectApprovefinishedCount(record);
				memberJobCount.setFinishedStatus(finishedStatus);
				// 合计
	
				Map<String, Object> paramMap = new HashMap<String, Object>();
				// DEL_STATUS = 0 征信未完成
				paramMap.put("userId", code);
				paramMap.put("currNode", "02");
				paramMap.put("delStatus", "0");
				paramMap.put("currStatus", "3");
				int completed=bizInpApplDao.selectZxMemerJobCount(paramMap);
				memberJobCount.setCompleted(completed);
				
				// DEL_STATUS = 2  征信 补件
				paramMap.put("userId", code);
				paramMap.put("delStatus", "2");
				paramMap.put("currNode", "02");
				paramMap.put("currStatus", "3");
				int supplement = bizInpApplDao.selectZxMemerJobCount(paramMap);
				memberJobCount.setSupplement(supplement);
	
				// DEL_STATUS = 3 退回
				paramMap.put("userId", code);
				paramMap.put("delStatus", "3");
				paramMap.put("currNode", "02");
				paramMap.put("currStatus", "3");
				int returnDocument = bizInpApplDao.selectZxMemerJobCount(paramMap);
				memberJobCount.setReturnDocument(returnDocument);
				Date s = new Date();
				SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
				String startDate = null;
				startDate = sf.format(s);
				if("".equals(startTime)||null==startTime){
					paramMap.put("startTime", startDate);
					paramMap.put("endTime", startDate);
				} else {
					paramMap.put("startTime", startTime);
					paramMap.put("endTime", startTime);
				}
				paramMap.put("auditor", code);
				paramMap.put("checkMeuoFlag", "2");
				int subNum = bizApprovalDaoImpl.selectMemberJobCompletCountApproveCheckup(paramMap);
				memberJobCount.setSubNum(subNum);
				int gdNum = finishCountDaoImpl.memberJobCompletCountApprove(paramMap);
				memberJobCount.setGdNum(gdNum);
				// 合计
				int total = hangInTheAirStatus + supplementLetter + pauseDocument+ 
						completed+supplement+returnDocument;
				sumTotal = sumTotal + total;
				wwcTotal = wwcTotal+hangInTheAirStatus;
				wcTotal = wcTotal+finishedStatus;
				bjTotal = bjTotal+supplementLetter;
				tjTotal = tjTotal+returnDocument ;
				gqTotal = gqTotal+pauseDocument1+pauseDocument2;
				subTotal = subTotal + subNum;
				gdTotal = gdTotal +gdNum;
				zxwwcTotal = zxwwcTotal+completed;
				zxbjTotal = zxbjTotal+supplement;
				memberJobCount.setTotal(total);
				memberJobCountList.add(memberJobCount);
			}
		}
		if (memberJobCountList.isEmpty()){
			map.put("total", memberJobCountList.size());
			map.put("rows", memberJobCountList);
			map.put("fig", "true");
		} else {
			
			/*//分页
			int page=(int) map.get("curNum");
			int pageSize=(int) map.get("pageNum");
			int showRowsCountOne=0;
			if(page>1){
			 showRowsCountOne=pageSize*(page-1);
			}
			int showRowsCount=page*pageSize;
			int allCount=memberJobCountList.size();
			List<MemberJobCountApproveCheckUp> memberJobList = new ArrayList<MemberJobCountApproveCheckUp>();
			int num=1;
			for (MemberJobCountApproveCheckUp queryStaffJobDetail2 : memberJobCountList) {
				if(showRowsCountOne<num&&num<=showRowsCount){
					memberJobList.add(queryStaffJobDetail2);
				}
				num++;
			}
			map.put("sumTotal", sumTotal);
			map.put("total", allCount);
			map.put("rows", memberJobList);
			map.put("fig", "true");*/
			MemberJobCountApproveCheckUp memberJobCountHj = new MemberJobCountApproveCheckUp();
			memberJobCountHj.setStaffName("合计");
			memberJobCountHj.setTotal(sumTotal);
			memberJobCountHj.setHangInTheAirStatus(wwcTotal);
			memberJobCountHj.setFinishedStatus(wcTotal);
			memberJobCountHj.setSupplementLetter(bjTotal);
			memberJobCountHj.setReturnDocument(tjTotal);
			memberJobCountHj.setPauseDocument(gqTotal);
			memberJobCountHj.setSupplement(zxbjTotal);
			memberJobCountHj.setCompleted(zxwwcTotal);
			memberJobCountHj.setGdNum(gdTotal);
			memberJobCountHj.setSubNum(subTotal);
			
			List<MemberJobCountApproveCheckUp> memberJobCountListHtml = new ArrayList<MemberJobCountApproveCheckUp>();
			memberJobCountListHtml.add(memberJobCountHj);
			memberJobCountListHtml.addAll(memberJobCountList);
			map.put("total", memberJobCountListHtml.size());
			map.put("rows", memberJobCountListHtml);
		}
		
		return map;
	}
	
	//审查 征信 审批 征审合一  组员工作完成量统计
	@Override
	public Map<String, Object> memberJobCompletCountCheckupDataMap(
			Map<String, Object> map) {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		ApUser apUser = new ApUser();
		
		List<MemberJobCount> memberJobCountList = new ArrayList<MemberJobCount>();
		//得到组里所有成员
		Map<String, Object> maps = getRoleCode(map);
		List<ApUserOrg> apUserOrgList = (List<ApUserOrg>) maps.get("apUserOrgList");
	    int complet = 0;
		int completTotal = 0;
		String startTime = (String) map.get("startTime");

		String endTime = (String) map.get("endTime");
		String currNode = (String)map.get("currNode");
		if(apUserOrgList.size() > 0){
			try {
				for (int i = 0; i < apUserOrgList.size(); i++) {
					apUser = apUserDaoImpl.queryApUserByUserId(apUserOrgList.get(i).getUserId());
					if(apUser == null){
						continue;
					}
					Map<String, Object> mapPar = new HashMap<String, Object>();
	
					mapPar.put("auditor", apUser.getUserCode());
	
					mapPar.put("startTime", startTime);
	
					mapPar.put("endTime", endTime);
					//currNode 01审查 02 征信 03 审批 06 征审合一
					if ("01".equals(currNode)){//审查
						complet = finishCountDaoImpl.memberJobCompletCountCheckup(mapPar);
					} else if ("02".equals(currNode)){//征信
						//获取当前是日期
						Date s = new Date();
						SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
						String startDate = null;
						startDate = sf.format(s);
						/*
						 * 如果是查询当天的就只查询提交量、
						 * 如果查询不是当天的则算历史查询  查询归档量
						 * */		
						if (startDate.equals(startTime)) {
							complet = finishCountDaoImpl.memberJobCompletCount(mapPar);
						} else {
							complet = finishCountDaoImpl.memberJobCompletCountHis(mapPar);
						}
					} else if ("03".equals(currNode)){
						//审批已完成
						mapPar.put("checkMeuoFlag", "1");
						complet = bizApprovalDaoImpl.selectMemberJobCompletCountApprove(mapPar);
					} else if ("06".equals(currNode)){
						//征审合一已完成
						mapPar.put("checkMeuoFlag", "2");
						complet = bizApprovalDaoImpl.selectMemberJobCompletCountApproveCheckup(mapPar);
					}
					
					MemberJobCount memberJobCount = new MemberJobCount();
	
					memberJobCount.setStaffName(apUser.getUserName());
	
					memberJobCount.setFinishedStatus(complet);
	
					completTotal += complet;
	
					memberJobCountList.add(memberJobCount);
				}
			} catch (CoreException e) {
			}
			
			//分页
			int page=(int) map.get("curNum");
			int pageSize=(int) map.get("pageNum");
			int showRowsCountOne=0;
			if(page>1){
			 showRowsCountOne=pageSize*(page-1);
			}
			int showRowsCount=page*pageSize;
			int allCount=memberJobCountList.size();
			List<MemberJobCount> memberJobCount = new ArrayList<MemberJobCount>();
			int num=1;
			for (MemberJobCount queryStaffJobDetail2 : memberJobCountList) {
				if(showRowsCountOne<num&&num<=showRowsCount){
					memberJobCount.add(queryStaffJobDetail2);
				}
				num++;
			}

			dataMap.put("rows", memberJobCount);

			dataMap.put("total", allCount);

			dataMap.put("member", allCount);

			dataMap.put("completTotal", completTotal);

			dataMap.put("fig", "true");

		} else {
			dataMap.put("fig", "false");
			dataMap.put("messager", "审查 组员工作完成量统计查询异常！");
		}
		return dataMap;
	}

	// 征信 审查 审批  征审合一明细查询
	@Override
	public Map<String, Object> queryStaffJobDetailByUserCode(
			Map<String, Object> map) {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		//获取userId
		String nemberUserId = (String) map.get("nemberUserId");
		List<BizInpAppl> bizInpApplList = new ArrayList<BizInpAppl>();
		
			//根据nemberUserId 得到UserCode
		ApUser apUser =null;
		apUser = apUserDaoImpl.queryApUserByUserId(nemberUserId);
		
		String appId = (String) map.get("appId");
		String delStatus = (String) map.get("delStatus");
		if ("-1".equals(delStatus)||"".equals(delStatus)){
			delStatus = "08";
		}
		dataMap.put("delStatus", delStatus);
		dataMap.put("userCode", apUser.getUserCode());
		dataMap.put("appId", appId);
		String currNode = (String) map.get("currNode");
		//审查
		if ("01".equals(currNode)){
			bizInpApplList = bizInpApplDaoImpl.selectBizInpApplByUserCodeCheckup(dataMap);
		} else if ("02".equals(currNode)){
			//根据userCode 查询件  征信
			bizInpApplList = bizInpApplDaoImpl.selectBizInpApplByUserCode(dataMap);
		} else if ("03".equals(currNode)){
			//审批待补件，未完成，挂起
			List<BizInpAppl> bizInpApplList2 = null;
			if (!"02".equals(delStatus)) {
				bizInpApplList2 = bizInpApplDaoImpl.selectBizInpApplByUserCodeApprove(dataMap);
				bizInpApplList.addAll(bizInpApplList2);
			}
			//审批已完成
			List<BizInpAppl> bizInpApplList1 = null;
			if ("02".equals(delStatus)||"08".equals(delStatus)){
				dataMap.put("delStatus", "1");
				dataMap.put("currNode", "04");
				dataMap.put("currStatus","");
				dataMap.put("userId", apUser.getUserCode());
				dataMap.put("checkMeuoflag", "1");
				bizInpApplList1 = bizInpApplDaoImpl.selectBizInpApplByUserCodeApproveCompleted(dataMap);
				bizInpApplList.addAll(bizInpApplList1);
			}
		} else if ("06".equals(currNode)){
			//征审合一 审批待补件，未完成，挂起(包含审批跟 征信的挂起02，03) 征信的未完成  补件 挂起 退回
			List<BizInpAppl> bizInpApplList2 = null;
			if (!"03".equals(delStatus)) {
				bizInpApplList2 = bizInpApplDaoImpl.selectBizInpApplApprove(dataMap);
				bizInpApplList.addAll(bizInpApplList2);
			}
			//审批已完成
			List<BizInpAppl> bizInpApplList1 = null;
			if ("03".equals(delStatus)||"08".equals(delStatus)){
				dataMap.put("delStatus", "1");
				dataMap.put("currNode", "04");
				dataMap.put("currStatus","");
				dataMap.put("userId", apUser.getUserCode());
				dataMap.put("checkMeuoflag", "2");
				bizInpApplList1 = bizInpApplDaoImpl.selectBizInpApplByUserCodeApproveCompleted(dataMap);
				bizInpApplList.addAll(bizInpApplList1);
			}
		}
		
		dataMap = queryBizInpApplC1(bizInpApplList,map);
		return dataMap;
	}
	
	//征信审查审批 查主键表
	public Map<String, Object> queryBizInpApplC1(List<BizInpAppl> bizInpApplList,Map<String, Object> map){
		String delStatus;
		Map<String, Object> dataMap = new HashMap<String, Object>();
		List<QueryStaffJobDetail> queryStaffJobDetailList = new ArrayList<QueryStaffJobDetail>();
		try {
		if(bizInpApplList == null){
				logger.debug("组长调阅--> -->明细查询--->根据userCode 查询件  无结果集");
				//logger.debug("组长调阅-->征信 -->明细查询--->根据userCode 查询件  无结果集");
				dataMap.put("rows", queryStaffJobDetailList);
	
				dataMap.put("total", 0);
	
				dataMap.put("fig", "true");
			}else{
				
				for (int i = 0; i < bizInpApplList.size(); i++) {
	
					QueryStaffJobDetail queryStaffJobDetail = new QueryStaffJobDetail();
	
					BizInpApplC1 bizInpApplC1 = new BizInpApplC1();
					String fig = checkRemarkInfoFig(bizInpApplList.get(i).getAppId());
					queryStaffJobDetail.setRemarkInfoFig(fig);
					bizInpApplC1 = bizInpApplC1nDaoDaoImpl.queryBizInpApplC1ByAppIdOrder(bizInpApplList.get(i).getAppId());
	
					if (bizInpApplC1 == null) {
	
						queryStaffJobDetail.setC1Cname("");
	
						queryStaffJobDetail.setC1Idnbr("");
	
						queryStaffJobDetail.setC1Coname("");
						
						queryStaffJobDetail.setYdjFig("");
						
						queryStaffJobDetail.setAppProd("0");
						queryStaffJobDetail.setMatchingCardFlag("");
					} else {
	
						queryStaffJobDetail.setC1Cname(bizInpApplC1.getC1Cname());
	
						queryStaffJobDetail.setC1Idnbr(bizInpApplC1.getC1Idnbr());
	
						queryStaffJobDetail.setC1Coname(bizInpApplC1.getC1Coname());
						
						queryStaffJobDetail.setYdjFig(bizInpApplC1.getYdjFlag());
						queryStaffJobDetail.setAppProd(bizInpApplC1.getAppProd());
						queryStaffJobDetail.setMatchingCardFlag(bizInpApplC1.getMatchingCardFlag());
					}
	
					queryStaffJobDetail.setId(bizInpApplList.get(i).getAppId());
	
					queryStaffJobDetail.setAppId(bizInpApplList.get(i).getAppId());
	
					queryStaffJobDetail.setLastOptUser(bizInpApplList.get(i).getLastOptUser());
	
					delStatus = bizInpApplList.get(i).getDelStatus();
					String currNode = bizInpApplList.get(i).getCurrNode();
					queryStaffJobDetail.setCurrNode(bizInpApplList.get(i).getCurrNode());
					String currStatus =  bizInpApplList.get(i).getCurrStatus();
					queryStaffJobDetail.setDilStatusFig(delStatus);
					String delName = null;
	
					if ("0".equals(delStatus)&&"3".equals(currStatus)&&"02".equals(currNode)) {
	
						delName = "征信未完成队列";
	
					}  else if (("0").equals(delStatus)&&("3").equals(currStatus)&&"03".equals(currNode)) {
	
						delName = "审批未完成队列";
	
					} else if (("0").equals(delStatus)&&("3").equals(currStatus)&&"01".equals(currNode)) {
	
						delName = "录入未完成队列";
	
					} else if (("2").equals(delStatus)&&("3").equals(currStatus)&&"02".equals(currNode)) {
	
						delName = "征信补件队列";
	
					} else if (("3").equals(delStatus)&&("3").equals(currStatus)) {
	
						delName = "退回队列";
	
					}  else if (("2").equals(delStatus)&&("3").equals(currStatus)&&"03".equals(currNode)) {
	
						delName = "审批补件队列";
	
					} else if (("4").equals(currStatus)) {
	
						delName = "挂起队列";
	
					} else {
	
						delName = "已完成队列";
					}  
	
					queryStaffJobDetail.setDelStatus(delName);
					queryStaffJobDetail.setLstTeamDate(bizInpApplList.get(i).getLstDate());
					//进入队列天数统一加一天
					queryStaffJobDetail.setQueDate(bizInpApplList.get(i).getQueDate()+1);
					queryStaffJobDetailList.add(queryStaffJobDetail);
				}
			}
	
			if (queryStaffJobDetailList.size() > 0) {
				int page=(int) map.get("page");
				int pageSize=(int) map.get("pageNum");
				int showRowsCountOne=0;
				if(page>1){
				 showRowsCountOne=pageSize*(page-1);
				}
				int showRowsCount=page*pageSize;
				int allCount=queryStaffJobDetailList.size();
				List<QueryStaffJobDetail> queryStaffJobDetailRealNeed = new ArrayList<QueryStaffJobDetail>();
				int num=1;
				for (QueryStaffJobDetail queryStaffJobDetail2 : queryStaffJobDetailList) {
					if(showRowsCountOne<num&&num<=showRowsCount){
						queryStaffJobDetailRealNeed.add(queryStaffJobDetail2);
					}
					num++;
				}
				dataMap.put("rows", queryStaffJobDetailRealNeed);
	
				dataMap.put("total", allCount);
	
				dataMap.put("fig", "true");
	
			} else {
				
				dataMap.put("rows", queryStaffJobDetailList);
				dataMap.put("total", queryStaffJobDetailList.size());
				dataMap.put("fig", "true");
				dataMap.put("messager", "组长调阅-->-- -->明细查询异常！");
			}
	
		} catch (Exception e) {
			logger.debug("组长调阅-->--- -->明细查询异常！"+e);
		}
		return dataMap;
	}
	// 查看是否有组长备注
	public String checkRemarkInfoFig(String appId) throws CoreException {
		logger.debug("查看是否有组长备注-->start appId["+appId+"]");
		List<ApplyRemark> applyRemakrList = new ArrayList<ApplyRemark>();
		String fig = null;
		try {
			
			applyRemakrList = applyRemarkDaoImpl.checkRemarkInfo(appId);
			if(applyRemakrList==null || applyRemakrList.size()==0){//说明此appId没有组织备注
				fig = "0";
			}else{
				fig = "1";
			}
		} catch (Exception e) {
			logger.debug("查看是否有组长备注异常！"+e);
		}
		logger.debug("查看是否有组长备注-->end");
		return fig;
	}

	
	
	@Override
	public Map<String, Object> memberJobEcxception(Map<String, Object> map) {
		// 查看 组中组员总数
		Map<String, Object> dataMap = new HashMap<String, Object>();
		String operateCode = (String) map.get("userId");
		map.put("userCode", operateCode);
		//flag 作为一个标识=工作量异常统计 统计审批件 === 不需要用政审合一角色
		map.put("flag", "1");
		Map<String, Object> maps = getRoleCode(map);
		List<ApUserOrg> apUserOrgList = (List<ApUserOrg>) maps.get("apUserOrgList");
		
		ApUserOrg apUserOrg = new ApUserOrg();
		
		//String exceptionDate =  dateformat.format((Date) map.get("exceptionDate"));
		String exceptionDate ="";
		List<String> obj = (List<String>) map.get("exceptionDate");
		String startTime = (String) map.get("startTime");
		if (startTime == null||"".equals(startTime)) {
			exceptionDate = obj.get(0);
			dataMap.put("exceptionDate", exceptionDate);
		} else {
			dataMap.put("exceptionDate", startTime);
		}
		
		
		List<MemberEcxception> listMemberEcxception = new ArrayList<MemberEcxception>();
		for (int i = 0; i < apUserOrgList.size(); i++) {
			apUserOrg = apUserOrgList.get(i);
			String userId = apUserOrg.getUserId();
			dataMap.put("userId", userId);
			//apUser = apUserDaoImpl.queryApUserByUserId(apUserOrg.getUserId());
			maps = bizApprovalOperateexcDaoImpl.selectMemberJobEcxception(dataMap);
			if (maps != null) {
				/*if ("".equals(maps.get("exceptiondatelong"))||null==maps.get("exceptiondatelong")) {
					continue;
				}*/
				MemberEcxception memberEcxception = new MemberEcxception();
				memberEcxception.setOperateCode((String)maps.get("operateCode"));
				memberEcxception.setOperateName((String)maps.get("userName"));
				memberEcxception.setSumTime((BigDecimal)maps.get("exceptiondatelong"));
				memberEcxception.setSysDate((String)maps.get("dateStr"));
				listMemberEcxception.add(memberEcxception);
			}
		}
		if (listMemberEcxception.isEmpty()){
			dataMap.put("fig", true);
			dataMap.put("rows", 0);
			dataMap.put("total", 0);
		} else {
			dataMap.put("fig", true);
			dataMap.put("rows", listMemberEcxception);
			dataMap.put("total", listMemberEcxception.size());
		}
		return dataMap;
	}
	
	//连续性工作异常统计
	@Override
	public Map<String, Object> memberJobEcxceptionDetailDataMap(
			Map<String, Object> map) {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		
		List<BizApprovalOperateexc> bizApprovalOperateexcListRet = new ArrayList<BizApprovalOperateexc>();
		String exceptionDate = "";
		List<String> obj = (List<String>) map.get("exceptionDate");
		exceptionDate = obj.get(0);
		map.put("exceptionDate", exceptionDate);
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		listMap = bizApprovalOperateexcDaoImpl.selectmemberJobEcxceptionDetailDataMap(map);
		if (!listMap.isEmpty()){
			for (int j = 0; j < listMap.size(); j++) {
				BizApprovalOperateexc bizApprovalOperateexc = new BizApprovalOperateexc();
				bizApprovalOperateexc.setId((String)listMap.get(j).get("id"));
				bizApprovalOperateexc.setDateStr((String)listMap.get(j).get("dateStr"));
				bizApprovalOperateexc.setExceptionTime(((String)listMap.get(j).get("startStr")+"---"+((String)listMap.get(j).get("endStr"))));
				bizApprovalOperateexc.setExceptionsTime((BigDecimal)listMap.get(j).get("exceptiondatelong"));
				bizApprovalOperateexcListRet.add(bizApprovalOperateexc);
			}
		}
		
		if (bizApprovalOperateexcListRet.isEmpty()){
			dataMap.put("fig", true);
			dataMap.put("rows", 0);
			dataMap.put("total", 0);
		} else {
			dataMap.put("fig", true);
			dataMap.put("rows", bizApprovalOperateexcListRet);
			dataMap.put("total", bizApprovalOperateexcListRet.size());
		}
		return dataMap;
	}

}
