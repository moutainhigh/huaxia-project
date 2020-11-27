package com.huaxia.opas.dao.apply.impl;

import java.util.List;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.apply.ApplyRemarkDao;
import com.huaxia.opas.domain.allot.AllotApply;
import com.huaxia.opas.domain.apply.ApplyRemark;

/**
 * 备注
 * 
 * @author xiebinxu 2017年2月25日
 */
public class ApplyRemarkDaoImpl extends AbstractDAO implements ApplyRemarkDao {

	private static final long serialVersionUID = -4491455802504827274L;

	private static final String NAMESPACES = "ApplyRemark.";

	@Override
	public int deleteByPrimaryKey(String remarkId) throws CoreException {
		return getSqlMap().delete(NAMESPACES + "deleteByPrimaryKey", remarkId);
	}

	@Override
	public int insert(ApplyRemark record) throws CoreException {
		return getSqlMap().insert(NAMESPACES + "insert", record);
	}

	@Override
	public int insertSelective(ApplyRemark record) throws CoreException {
		return getSqlMap().insert(NAMESPACES + "insertSelective", record);
	}

	@Override
	public ApplyRemark selectByPrimaryKey(String remarkId) throws CoreException {
		return getSqlMap().queryForObject(NAMESPACES + "selectByPrimaryKey", remarkId);
	}

	@Override
	public int updateByPrimaryKeySelective(ApplyRemark record) throws CoreException {
		return getSqlMap().update(NAMESPACES + "updateByPrimaryKeySelective", record);
	}

	@Override
	public int updateByPrimaryKey(ApplyRemark record) throws CoreException {
		return getSqlMap().update(NAMESPACES + "updateByPrimaryKey", record);
	}

	@Override
	public int queryCount(ApplyRemark record) throws CoreException {
		return getSqlMap().queryForObject(NAMESPACES + "queryCount", record);
	}

	@Override
	public List queryList(ApplyRemark record, int curNum, int pageNum) throws CoreException {
		return getSqlMap().queryForList(NAMESPACES + "queryList", record, curNum, pageNum);
	}
	//zlb
	@Override
	public List<ApplyRemark> checkRemarkInfo(String appId) throws CoreException {
		return getSqlMap().queryForList(NAMESPACES + "checkRemarkInfo", appId);
	}

	@Override
	public int saveNemberRemarks(ApplyRemark applyRemark) throws CoreException {
		return getSqlMap().insert(NAMESPACES + "insert", applyRemark);
	}
	
	@Override
	public int insertBatch(List<ApplyRemark> list) throws CoreException {
		return getSqlMap().insert(NAMESPACES + "insertBatch", list);
	}

	@Override
	public int queryCountWithYs(ApplyRemark record) throws CoreException {
		return getSqlMap().queryForObject(NAMESPACES + "queryCountWithYs", record);
	}

	@Override
	public List queryListWithYs(ApplyRemark record, int curNum, int pageNum) throws CoreException {
		return getSqlMap().queryForList(NAMESPACES + "queryListWithYs", record, curNum, pageNum);
	}
}
