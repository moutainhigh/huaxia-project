package com.huaxia.opas.dao.decision.impl;

import java.util.List;
import java.util.Map;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.decision.BizApprovalDao;
import com.huaxia.opas.domain.input.TelcheckResult;
import com.huaxia.opas.domain.thirdparty.BizApproval;

public class BizApprovalDaoImpl extends AbstractDAO  implements BizApprovalDao  {
	private static final long serialVersionUID = 2751582418545513921L;
	
	private static final String NAMESPACES = "BizApproval.";
	@Override
	public int deleteByPrimaryKey(String autoId) {
		return getSqlMap().delete(NAMESPACES + "deleteByPrimaryKey", autoId);
	}

	@Override
	public int insert(BizApproval record) {
		return getSqlMap().insert(NAMESPACES + "insert", record);
	}

	@Override
	public int insertSelective(BizApproval record) {
		return getSqlMap().insert(NAMESPACES + "insertSelective", record);
	}

	@Override
	public BizApproval selectByPrimaryKey(String autoId) {
		return (BizApproval) (getSqlMap().queryForObject(NAMESPACES + "selectByPrimaryKey", autoId));
	}

	@Override
	public int updateByPrimaryKeySelective(BizApproval record) {
		return getSqlMap().update(NAMESPACES + "updateByPrimaryKeySelective", record);
	}

	@Override
	public int updateByPrimaryKey(BizApproval record) {
		return getSqlMap().update(NAMESPACES + "updateByPrimaryKey", record);
	}
	
	@Override
	public BizApproval selectByAppId(String appId) {
		return (BizApproval) (getSqlMap().queryForObject(NAMESPACES + "selectByAppId", appId));
	}
	
	@Override
	public TelcheckResult selectTelcheckResultByAppId(String appId) {
		return (TelcheckResult) (getSqlMap().queryForObject(NAMESPACES + "selectTelcheckResultByAppId", appId));
	}
	@Override
	public int selectApprovalCard(Map<String,String> map){
		return getSqlMap().queryForObject(NAMESPACES + "selectApprovalCard", map);
	}
	
	@Override
	public int selectMemberJobCompletCountApprove(Map<String, Object> map) {
		return Integer.parseInt(String.valueOf(sqlMap.queryForObject(NAMESPACES + "selectMemberJobCompletCountApprove", map)));
	}

	@Override
	public int selectMemberJobCompletCountApproveCheckup(Map<String, Object> mapPar) {
		return Integer.parseInt(String.valueOf(sqlMap.queryForObject(NAMESPACES + "selectMemberJobCompletCountApproveCheckup", mapPar)));
	}

	@Override
	public String selectAppByAppId(String appId) {
		return getSqlMap().queryForObject(NAMESPACES+"selectAppByAppId", appId);
	}

	@Override
	public String selectApplyByAppId(String appId) {
		return getSqlMap().queryForObject(NAMESPACES+"selectApplyByAppId", appId);
	}
	@Override
	public String selectYdjFlagByAppId(String appId) {
		return getSqlMap().queryForObject(NAMESPACES+"selectYdjFlagByAppId", appId);
	}
	
	@Override
	public int updateInitSaveFlag(Map map) {
		return getSqlMap().update(NAMESPACES + "updateInitSaveFlag", map);
	}
	
	@Override
	public List<String> selectApplyCardByAppId(String appId) {
		return getSqlMap().queryForList(NAMESPACES + "selectApplyCardByAppId",appId);
	}
	@Override
	public int selectCountHaveCard(Map<String, Object> map) {
		return  getSqlMap().queryForObject(NAMESPACES + "selectCountHaveCard",map);
	}

	@Override
	public int selectCardLimit(Map<String, Object> map) {
		return  getSqlMap().queryForObject(NAMESPACES + "selectCardLimit",map);
	}
	@Override
	public int selectFkCardLimit(Map<String, Object> map) {
		return  getSqlMap().queryForObject(NAMESPACES + "selectFkCardLimit",map);
	}
	@Override
	public String selectApproveResult(String appId) {
		return  getSqlMap().queryForObject(NAMESPACES + "selectApproveResult",appId);
	}

}