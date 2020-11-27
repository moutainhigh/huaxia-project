package com.huaxia.opas.dao.apply.impl;

import java.util.List;

import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.apply.ApplyModifyLogDao;
import com.huaxia.opas.domain.apply.ApplyModifyLog;

public class ApplyModifyLogDaoImpl extends AbstractDAO  implements ApplyModifyLogDao  {
	private static final long serialVersionUID = 2757582318545513971L;
	
	private static final String NAMESPACES = "ApplyModifyLog.";
	@Override
	public int deleteByPrimaryKey(String autoId) {
		return getSqlMap().delete(NAMESPACES + "deleteByPrimaryKey", autoId);
	}

	@Override
	public int insert(ApplyModifyLog record) {
		return getSqlMap().insert(NAMESPACES + "insert", record);
	}

	@Override
	public int insertSelective(ApplyModifyLog record) {
		return getSqlMap().insert(NAMESPACES + "insertSelective", record);
	}

	@Override
	public ApplyModifyLog selectByPrimaryKey(String autoId) {
		return (ApplyModifyLog) (getSqlMap().queryForObject(NAMESPACES + "selectByPrimaryKey", autoId));
	}

	@Override
	public int updateByPrimaryKeySelective(ApplyModifyLog record) {
		return getSqlMap().update(NAMESPACES + "updateByPrimaryKeySelective", record);
	}

	@Override
	public int updateByPrimaryKey(ApplyModifyLog record) {
		return getSqlMap().update(NAMESPACES + "updateByPrimaryKey", record);
	}
	
	@Override
	public List<ApplyModifyLog> selectApplyLogByAppId(String appId) {
		List list = getSqlMap().queryForList(NAMESPACES + "selectApplyLogByAppId", appId);
		return (List<ApplyModifyLog>)list;
	}

	@Override
	public int insertinsertApplyModifyLogList(List<ApplyModifyLog> applyModifyLogList) {
		return getSqlMap().insert(NAMESPACES + "insertinsertApplyModifyLogList", applyModifyLogList);
	}

}