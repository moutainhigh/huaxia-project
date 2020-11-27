package com.huaxia.opas.service.sysparm.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.sysparm.ApproveReasonCodeDao;
import com.huaxia.opas.domain.sysparm.ApproveReasonCode;
import com.huaxia.opas.service.sysparm.ApproveReasonCodeService;
/**
 * 审批原因码服务层实现类
 * @author liudi
 * @since 2017-03-06
 * @version 1.0
 */
public class ApproveReasonCodeServiceImpl extends AbstractDAO implements ApproveReasonCodeService,Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1924235869858376810L;

	@Resource(name = "approveReasonCodeDao")
	private ApproveReasonCodeDao approveReasonCodeDao;


	public ApproveReasonCodeDao getApproveReasonCodeDao() {
		return approveReasonCodeDao;
	}

	public void setApproveReasonCodeDao(ApproveReasonCodeDao approveReasonCodeDao) {
		this.approveReasonCodeDao = approveReasonCodeDao;
	}

	//添加状态为Start
	public int saveApproveReasonCodeStart(ApproveReasonCode approveReasonCode) throws CoreException {
		return getApproveReasonCodeDao().saveApproveReasonCodeStart(approveReasonCode);
	}
	
	//添加状态为End
	public int saveApproveReasonCodeEnd(ApproveReasonCode approveReasonCode) throws CoreException {
		return getApproveReasonCodeDao().saveApproveReasonCodeEnd(approveReasonCode);
	}
	
	//修改
	public int updateApproveReasonCode(ApproveReasonCode approveReasonCode) throws CoreException {
		return getApproveReasonCodeDao().updateApproveReasonCode(approveReasonCode);
	}
	
	//删除
	public int deleteApproveReasonCode(String autoId) throws CoreException {
		return getApproveReasonCodeDao().deleteApproveReasonCode(autoId);
	}
	
	//件数查询
	public int queryApproveReasonCodeCount(ApproveReasonCode approveReasonCode) throws CoreException {
		return getApproveReasonCodeDao().queryApproveReasonCodeCount(approveReasonCode);
	}
	
	//查询
	public List<ApproveReasonCode> queryApproveReasonCode(ApproveReasonCode approveReasonCode,int curNum,int pageNum) throws CoreException {
		return getApproveReasonCodeDao().queryApproveReasonCode(approveReasonCode, curNum, pageNum);
	}
	
	//点击查询后，触发查询和排序功能
	public List<ApproveReasonCode> queryApproveReasonCode(Map<String,Object> params,int curNum,int pageNum) throws CoreException {
		return getApproveReasonCodeDao().queryApproveReasonCode(params, curNum, pageNum);
	}
	
	//审批原因编码和银联原因代码组合是否重复
	public ApproveReasonCode queryApproveReasonCode(ApproveReasonCode approveReasonCode) throws CoreException {
		return getApproveReasonCodeDao().queryApproveReasonCode(approveReasonCode);
	}
	@Override
	public List<ApproveReasonCode> queryAllApproveReason(Map<String, String> map) throws CoreException {
		return getApproveReasonCodeDao().queryAllApproveReason(map);
	}

	@Override
	public String queryAllApproveReasonCodeStatus(String autoId) {
		return getApproveReasonCodeDao().queryAllApproveReasonCodeStatus(autoId);
	}

	@Override
	public int updateApproveReasonCodeWithoutStatus(ApproveReasonCode approveReasonCode) {
		return getApproveReasonCodeDao().updateApproveReasonCodeWithoutStatus(approveReasonCode);
	}
}
