package com.huaxia.opas.dao.decision.impl;

import java.util.List;
import java.util.Map;

import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.decision.BizApprovalHisDao;
import com.huaxia.opas.domain.thirdparty.BizApprovalHis;

public class BizApprovalHisDaoImpl extends AbstractDAO  implements BizApprovalHisDao  {
	private static final long serialVersionUID = 2751582418545513921L;
	
	private static final String NAMESPACES = "BizApprovalHis.";
	@Override
	public int deleteByPrimaryKey(String autoId) {
		return getSqlMap().delete(NAMESPACES + "deleteByPrimaryKey", autoId);
	}

	@Override
	public int insert(BizApprovalHis record) {
		return getSqlMap().insert(NAMESPACES + "insert", record);
	}

	@Override
	public int insertSelective(BizApprovalHis record) {
		return getSqlMap().insert(NAMESPACES + "insertSelective", record);
	}

	@Override
	public BizApprovalHis selectByPrimaryKey(String autoId) {
		return (BizApprovalHis) (getSqlMap().queryForObject(NAMESPACES + "selectByPrimaryKey", autoId));
	}

	@Override
	public int updateByPrimaryKeySelective(BizApprovalHis record) {
		return getSqlMap().update(NAMESPACES + "updateByPrimaryKeySelective", record);
	}

	@Override
	public int updateByPrimaryKey(BizApprovalHis record) {
		return getSqlMap().update(NAMESPACES + "updateByPrimaryKey", record);
	}
	@Override
	public List<BizApprovalHis> selectByAppId(String appId) {
		List list = getSqlMap().queryForList(NAMESPACES + "selectByAppId", appId);
		return (List<BizApprovalHis>)list;
	}
	@Override
	public BizApprovalHis selectByAppIdAndUserId(Map<String,String> map) {
		return (BizApprovalHis)(getSqlMap().queryForObject(NAMESPACES + "selectByAppIdAndUserId", map));
	}

	@Override
	public Map<String, Object> selectHistoryAuditInfo(String appId) {
		return  getSqlMap().queryForObject(NAMESPACES + "queryHistoryAuditInfo", appId);
	}
	@Override
	public Map<String, Object> selectAuditInfo(String appId) {
		return  getSqlMap().queryForObject(NAMESPACES + "queryAuditInfo", appId);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<BizApprovalHis> selectApprovalMemo(String appId) {
		return getSqlMap().queryForList(NAMESPACES + "queryApprovalMemo", appId);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<BizApprovalHis> selectDisApprovalMemo(String appId) {
		return getSqlMap().queryForList(NAMESPACES + "queryDisApprovalMemo", appId);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<BizApprovalHis> queryApprovalMsg(Map<String, Object> param) {
		return getSqlMap().queryForList(NAMESPACES + "queryApprovalMsg", param);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<BizApprovalHis> queryApprovalMsg_fsk(Map<String, Object> param) {
		return getSqlMap().queryForList(NAMESPACES + "queryApprovalMsg_fsk", param);
	}
	
	@Override
	public int queryApprovalQzdcByAppId(String appId) {
		return getSqlMap().queryForObject(NAMESPACES + "queryApprovalQzdcByAppId", appId);
	}
}