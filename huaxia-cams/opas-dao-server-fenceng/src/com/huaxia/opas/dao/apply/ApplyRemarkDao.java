package com.huaxia.opas.dao.apply;

import java.util.List;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.apply.ApplyRemark;

/**
 * 备注
 * 
 * @author xiebinxu 2017年2月25日
 */
public interface ApplyRemarkDao {
	
	int deleteByPrimaryKey(String remarkId) throws CoreException;

	int insert(ApplyRemark record) throws CoreException;

	int insertSelective(ApplyRemark record) throws CoreException;

	ApplyRemark selectByPrimaryKey(String remarkId) throws CoreException;

	int updateByPrimaryKeySelective(ApplyRemark record) throws CoreException;

	int updateByPrimaryKey(ApplyRemark record) throws CoreException;

	int queryCount(ApplyRemark record) throws CoreException;

	List queryList(ApplyRemark record, int curNum, int pageNum)
			throws CoreException;
	//wdb
	int queryCountWithYs(ApplyRemark record) throws CoreException;

	List queryListWithYs(ApplyRemark record, int curNum, int pageNum)
			throws CoreException;
	//zlb
	List<ApplyRemark> checkRemarkInfo(String appId) throws CoreException;
	//zlb
	int saveNemberRemarks (ApplyRemark applyRemark) throws CoreException;
	//wdb
	int insertBatch(List<ApplyRemark> list) throws CoreException;
}