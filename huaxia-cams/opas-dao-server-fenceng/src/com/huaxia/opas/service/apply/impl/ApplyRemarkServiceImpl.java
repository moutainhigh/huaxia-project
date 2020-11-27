package com.huaxia.opas.service.apply.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.apply.ApplyRemarkDao;
import com.huaxia.opas.domain.apply.ApplyRemark;
import com.huaxia.opas.service.apply.ApplyRemarkService;

public class ApplyRemarkServiceImpl implements ApplyRemarkService , Serializable{

	@Resource(name = "applyRemarkDao")
	private ApplyRemarkDao applyRemarkDao;

	@Override
	public int deleteByPrimaryKey(String remarkId) throws CoreException {
		return getApplyRemarkDao().deleteByPrimaryKey(remarkId);
	}

	@Override
	public int insert(ApplyRemark record) throws CoreException {
		return getApplyRemarkDao().insert(record);
	}

	@Override
	public int insertSelective(ApplyRemark record) throws CoreException {
		return getApplyRemarkDao().insertSelective(record);
	}

	@Override
	public ApplyRemark selectByPrimaryKey(String remarkId) throws CoreException {
		return getApplyRemarkDao().selectByPrimaryKey(remarkId);
	}

	@Override
	public int updateByPrimaryKeySelective(ApplyRemark record) throws CoreException {
		return getApplyRemarkDao().updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(ApplyRemark record) throws CoreException {
		return getApplyRemarkDao().updateByPrimaryKey(record);
	}

	@Override
	public int queryCount(ApplyRemark record) throws CoreException {
		return getApplyRemarkDao().queryCount(record);
	}

	@Override
	public List queryList(ApplyRemark record, int curNum, int pageNum) throws CoreException {
		return getApplyRemarkDao().queryList(record, curNum, pageNum);
	}

	public ApplyRemarkDao getApplyRemarkDao() {
		return applyRemarkDao;
	}

	public void setApplyRemarkDao(ApplyRemarkDao applyRemarkDao) {
		this.applyRemarkDao = applyRemarkDao;
	}

	@Override
	public int insertBatch(List<ApplyRemark> list) throws CoreException {
		return getApplyRemarkDao().insertBatch(list);
	}

	@Override
	public int queryCountWithYs(ApplyRemark record) throws CoreException {
		return getApplyRemarkDao().queryCountWithYs(record);
	}

	@Override
	public List queryListWithYs(ApplyRemark record, int curNum, int pageNum) throws CoreException {
		return getApplyRemarkDao().queryListWithYs(record, curNum, pageNum);
	}

}
