package com.huaxia.opas.service.sysparm.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.domain.sysparm.ApproveZipcode;
import com.huaxia.opas.domain.sysparm.BatchfileInfo;
import com.huaxia.opas.dao.sysparm.ApproveZipcodeDao;
import com.huaxia.opas.dao.sysparm.BatchFileInfoDao;
import com.huaxia.opas.service.sysparm.ApproveZipcodeService;
/**
 * 邮编区号维护服务层实现类
 * @author liudi
 * @since 2017-03-16
 * @version 1.0
 */
public class ApproveZipcodeServiceImpl extends AbstractDAO implements ApproveZipcodeService,Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1142870982849673183L;
	
	@Resource(name = "approveZipcodeDao")
	private ApproveZipcodeDao approveZipcodeDao;
	//插入的历史记录
	@Resource(name="batchFileInfoDao")
	private BatchFileInfoDao batchFileInfoDao;

	public ApproveZipcodeDao getApproveZipcodeDao() {
		return approveZipcodeDao;
	}

	public void setApproveZipcodeDao(ApproveZipcodeDao approveZipcodeDao) {
		this.approveZipcodeDao = approveZipcodeDao;
	}

	//添加
	public int saveApproveZipcode(ApproveZipcode approveZipcode) throws CoreException {
		return getApproveZipcodeDao().saveApproveZipcode(approveZipcode);
	}
	
	//修改
	public int updateApproveZipcode(ApproveZipcode approveZipcode) throws CoreException {
		return getApproveZipcodeDao().updateApproveZipcode(approveZipcode);
	}
	
	//删除
	public int deleteApproveZipcode(String autoId) throws CoreException {
		return getApproveZipcodeDao().deleteApproveZipcode(autoId);
	}
	
	//邮编区号件数查询
	public int queryApproveZipcodeCount(ApproveZipcode approveZipcode) throws CoreException {
		return getApproveZipcodeDao().queryApproveZipcodeCount(approveZipcode);
	}
		
	//邮编区号页面初始化详细查询
	public  List<ApproveZipcode> queryApproveZipcode(ApproveZipcode approveZipcode,int curNum,int pageNum) throws CoreException {
		return getApproveZipcodeDao().queryApproveZipcode(approveZipcode, curNum, pageNum);
	}
	
	//点击查询后，触发卡邮编区号信息查询和排序功能
	public List<ApproveZipcode> queryApproveZipcode(Map<String,Object> params,int curNum,int pageNum) throws CoreException {
		return getApproveZipcodeDao().queryApproveZipcode(params, curNum, pageNum);
	}

	@Override
	public int updateApproveZipcodeWithoutAllStatus(ApproveZipcode approveZipcode) {
		return getApproveZipcodeDao().updateApproveZipcodeWithoutAllStatus(approveZipcode);
	}

	@Override
	public int updateApproveZipcodeWithoutStatus(ApproveZipcode approveZipcode) {
		return getApproveZipcodeDao().updateApproveZipcodeWithoutStatus(approveZipcode);
	}

	@Override
	public int updateApproveZipcodeWithoutRemoteStatus(ApproveZipcode approveZipcode) {
		return getApproveZipcodeDao().updateApproveZipcodeWithoutRemoteStatus(approveZipcode);
	}

	@Override
	public ApproveZipcode selectStatus(String autoId) {
		return getApproveZipcodeDao().selectStatus(autoId);
	}

	@Override
	public void deleteApproveZipcodes(List<String> ids) throws CoreException {
		getApproveZipcodeDao().deleteApproveZipcodes(ids);		
	}

	@Override
	public void deleteAllApproveZipcodes() {
		getApproveZipcodeDao().deleteAllApproveZipcodes();
	}

	@Override
	public int insertApproveZipcodeFromFile(List<ApproveZipcode> list, BatchfileInfo batchfileInfo) {
		int inputCounts = approveZipcodeDao.insertApproveZipcodeFromFile(list);
		//添加导入记录
		batchfileInfo.setInputCounts(new BigDecimal(inputCounts));
		batchFileInfoDao.insert(batchfileInfo);
		return inputCounts;
	}

	@Override
	public int queryRepetition(Map<String, String> map) {
		return approveZipcodeDao.queryRepetition(map);
	}





}
