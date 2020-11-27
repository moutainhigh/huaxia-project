package com.huaxia.opas.service.sysparm;

import java.util.List;
import java.util.Map;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.sysparm.ApproveReasonCode;
/**
 * 审批原因码服务层接口
 * @author liudi
 * @since 2017-03-06
 * @version 1.0
 */
public interface ApproveReasonCodeService {
	//添加状态为启用
	public int saveApproveReasonCodeStart(ApproveReasonCode approveReasonCode) throws CoreException;
	//添加状态为禁用
	public int saveApproveReasonCodeEnd(ApproveReasonCode approveReasonCode) throws CoreException;
	//更新
	public int updateApproveReasonCode(ApproveReasonCode approveReasonCode) throws CoreException;
	//删除
	public int deleteApproveReasonCode(String autoId) throws CoreException;
	//审批原因码查询件数
	public int queryApproveReasonCodeCount(ApproveReasonCode approveReasonCode) throws CoreException;
	//审批原因码查询详细
	public List<ApproveReasonCode> queryApproveReasonCode(ApproveReasonCode approveReasonCode,int curNum,int pageNum) throws CoreException;
	//点击查询按钮，实现条件查询详细，排序功能
	public List<ApproveReasonCode> queryApproveReasonCode(Map<String,Object> params,int curNum,int pageNum) throws CoreException;
	//审批原因编码和银联原因代码组合是否重复
	public ApproveReasonCode queryApproveReasonCode(ApproveReasonCode approveReasonCode) throws CoreException;
	
	List<ApproveReasonCode> queryAllApproveReason(Map<String, String> map) throws CoreException;
	//查询修改对象前的状态
	public String queryAllApproveReasonCodeStatus(String autoId);
	//更新对象（不包括状态）
	public int updateApproveReasonCodeWithoutStatus(ApproveReasonCode approveReasonCode);
}
