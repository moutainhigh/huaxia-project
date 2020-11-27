package com.huaxia.opas.dao.decision.impl;

import java.util.List;
import java.util.Map;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.decision.BizApprovalOperatedateDao;
import com.huaxia.opas.domain.input.BizApprovalOperatedate;

public class BizApprovalOperatedateDaoImpl extends AbstractDAO implements BizApprovalOperatedateDao {
	
	private static final long serialVersionUID = 2757582418545513971L;

	private static final String NAMESPACES = "BizApprovalOperatedate.";

	@Override
	public int deleteByPrimaryKey(String autoId) {
		return getSqlMap().delete(NAMESPACES + "deleteByPrimaryKey", autoId);
	}

	@Override
	public int insert(BizApprovalOperatedate record) {
		return getSqlMap().insert(NAMESPACES + "insert", record);
	}

	@Override
	public int insertSelective(BizApprovalOperatedate record) {
		return getSqlMap().insert(NAMESPACES + "insertSelective", record);
	}

	@Override
	public BizApprovalOperatedate selectByPrimaryKey(String autoId) {
		return (BizApprovalOperatedate) (getSqlMap().queryForObject(NAMESPACES + "selectByPrimaryKey", autoId));
	}

	@Override
	public int updateByPrimaryKeySelective(BizApprovalOperatedate record) {
		return getSqlMap().update(NAMESPACES + "updateByPrimaryKeySelective", record);
	}

	@Override
	public int updateByPrimaryKey(BizApprovalOperatedate record) {
		return getSqlMap().update(NAMESPACES + "updateByPrimaryKey", record);
	}
	@Override
	public BizApprovalOperatedate selectByUserId(String userId) {
		return (BizApprovalOperatedate) (getSqlMap().queryForObject(NAMESPACES + "selectByUserId", userId));
	}
	@Override
	public BizApprovalOperatedate selectByUserIdAndDT(String userId) {
		return getSqlMap().queryForObject(NAMESPACES + "selectByUserIdAndDT", userId);
	}
	@Override
	public int selectAllCount() throws CoreException {
		return getSqlMap().queryForObject(NAMESPACES + "selectAllCount");
	}

	@Override
	public List<BizApprovalOperatedate> selectAll(int curNum ,int pageNum ) throws CoreException {
		return getSqlMap().queryForList(NAMESPACES + "selectAll",null,curNum ,pageNum );
		
	}

	@Override
	public List<BizApprovalOperatedate> queryByName(String userName) {
		return getSqlMap().queryForList(NAMESPACES + "queryByName",userName);
	}
}