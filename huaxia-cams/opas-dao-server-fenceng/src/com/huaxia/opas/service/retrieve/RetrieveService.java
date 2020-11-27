package com.huaxia.opas.service.retrieve;
import java.util.List;
import java.util.Map;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.allot.AllotApplyAllot;
import com.huaxia.opas.domain.allot.AllotApplyAllotHis;
import com.huaxia.opas.domain.apply.ApplyRemark;
import com.huaxia.opas.domain.input.BizApprovalOperatedate;
import com.huaxia.opas.domain.input.BizApprovalOperateexc;
import com.huaxia.opas.domain.input.BizInpAppl;
import com.huaxia.opas.domain.input.BizInpApplC1;
import com.huaxia.opas.domain.system.ApUser;
import com.huaxia.opas.domain.system.ApUserOrg;

public interface RetrieveService{
	
	public ApUser queryApUserByUserCode(String userCode) throws CoreException;
	
	public ApUserOrg queryApUserOrgByUserIdMember(String userId) throws CoreException;
	
	public int queryApUserOrgByOrgIdCount(String userId) throws CoreException;
	
	public List<ApUserOrg> queryApUserOrgByOrgId(String orgId, int curNum ,int pageNum) throws CoreException;
	
	public ApUser queryApUserByUserId(String userId)throws CoreException;
	
	public int queryBizInpApplCountStatusZero(String userCode) throws CoreException;
	
	public int queryBizInpApplCountStatusTwo(String userCode) throws CoreException;
	
	public int queryBizInpApplCountStatusThree(String userCode) throws CoreException;
	
	public int queryBizInpApplCountStatusFour(String userCode) throws CoreException;
	
	public int queryBizInpApplCountStatusFinised(String userCode) throws CoreException;
	
	int queryBizInpApplHisCount(Map map) throws CoreException;
	//根据当前操作人CurrOptUser  查询件
	public List<BizInpAppl> queryBizInpApplByCurrOptUser(String currOptUser) throws CoreException;
	
	List<AllotApplyAllotHis> queryDetailBizInpApplHisByCurrOptUser(Map map)throws CoreException;

	public BizInpApplC1 queryBizInpApplC1ByAppIdOrder(String appId) throws CoreException;
	
	public int queryCountStatusAndUserCodeAnddelStatus(Map map) throws CoreException;
	
	public int queryFinisedApp(Map map) throws CoreException;
	
	int selectByCurrNodeAndUserCode(Map map) throws CoreException;
	
	public List<BizInpAppl> queryBizInpApplByCurrOptUserCheckup(String userCode)throws CoreException;
	
	public List<BizInpAppl> queryBizInpApplByCurrOptUserApprove(String userCode)throws CoreException;
	
	public List<ApplyRemark> checkRemarkInfo(String appId) throws CoreException;
	//新增组长备注
	public int saveNemberRemarks (ApplyRemark applyRemark) throws CoreException;
	//单条查询
	List<AllotApplyAllotHis> querySingleInfoHis(String appId) throws CoreException;
	//单条查询
	List<AllotApplyAllot> querySingleInfo(String appId) throws CoreException;
	//查询组名  组ID
	List<Map<String,String>> queryApOrg() throws CoreException;
	//根据环节查询
	List<AllotApplyAllotHis> queryStaffJobDetailByCurrNodeHis(String currNode)throws CoreException;
	
	List<AllotApplyAllot>  queryStaffJobDetailByCurrNode(String currNode)throws CoreException;
	//审批过件数 
	int querySPGJS(Map<String, Object> dataMap)throws CoreException;
	//审批拒件数
	int querySPJJS(Map<String, Object> dataMap)throws CoreException;
	//一次反欺诈拒件数
	int queryYCFQZJJS(Map<String, Object> dataMap)throws CoreException;
	//二次反欺诈拒件数
	int queryECFQZJJS(Map<String, Object> dataMap)throws CoreException; 
	//一次决策拒件数
	int queryYCJCJJS(Map<String, Object> dataMap)throws CoreException; 
	//一次决策过件数
	int queryYCJCGJS(Map<String, Object> dataMap)throws CoreException; 
	//二次决策拒件数
	int queryECJCJJS(Map<String, Object> dataMap)throws CoreException;
	//二次决策过件数
	int queryECJCGJS(Map<String, Object> dataMap)throws CoreException;
	
	int selectAllCount()throws CoreException;
	
	List<BizApprovalOperatedate> selectAll(int curNum, int pageNum)throws CoreException;

	public int queryExceptionDateLong(String operateCode) throws CoreException;
	
	List<BizApprovalOperateexc> memberJobEcxceptionDetail(String operateCode);

	AllotApplyAllot queryCurrNodeByAppId (String appId) throws CoreException;
	public List<BizApprovalOperatedate> queryByName(String userName);


	public Map<String, Object> queryMemberJobApprove(Map<String, Object>dataMap);

	public Map<String, Object> queryApUserOrgListByUserCode(Map<String, Object>dataMap);

	public Map<String, Object> queryMemberJobCountCheckup(Map<String, Object> dataMap);

	/*public Map<String, Object> memberJobCompletDataMap(
			Map<String, Object> dataMap);*/

	public Map<String, Object> memberJobCompletCountCheckupDataMap(
			Map<String, Object> dataMap);

	/*public Map<String, Object> memberJobCompletCountApproveDataMap(
			Map<String, Object> dataMap);
*/
	public Map<String, Object> queryStaffJobDetailByUserCode(
			Map<String, Object> map);

	/*public Map<String, Object> queryStaffJobDetailCheckupByUserCode(
			Map<String, Object> map);

	public Map<String, Object> queryStaffJobDetailApproveByUserCode(
			Map<String, Object> map);
*/
	//连续性工作异常统计
	public Map<String, Object> memberJobEcxceptionDetailDataMap(
			Map<String, Object> map);

	public Map<String, Object> memberJobEcxception(Map<String, Object> map);

	//征审合一count
	public Map<String, Object> queryMemberJobApproveCheckUp(
			Map<String, Object> dataMap);
	/*//征审合一List
	public Map<String, Object> queryStaffJobDetailApproveCheckUp(
			Map<String, Object> map);*/

	/*//征审合一工作量统计
	public Map<String, Object> queryMemberJobCompletCountApproveCheckup(
			Map<String, Object> dataMap);*/

}
