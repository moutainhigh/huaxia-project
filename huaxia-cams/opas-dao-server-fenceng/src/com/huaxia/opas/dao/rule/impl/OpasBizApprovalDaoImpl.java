package com.huaxia.opas.dao.rule.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.rule.OpasBizApprovalDao;
import com.huaxia.opas.domain.rule.OpasBizApproval;
import com.huaxia.opas.domain.rule.Opasbizinpapplc1;
public class OpasBizApprovalDaoImpl extends AbstractDAO implements OpasBizApprovalDao,Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 4098018922794855029L;

	@Override
	public int deleteByPrimaryKey(String appId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(OpasBizApproval record) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public List<OpasBizApproval> selectByExample(Opasbizinpapplc1 example) {
		return getSqlMap().queryForList("OpasBizApproval.selectByExample", example);
	}

	@Override
	public OpasBizApproval selectByPrimaryKey(String appId) {
		return getSqlMap().queryForObject("OpasBizApproval.selectByPrimaryKey", appId);
	}

	@Override
	public int querySPGJS(Map<String, Object> map) throws CoreException {
		return getSqlMap().queryForObject("OpasBizApproval.querySPGJS", map);
	}

	@Override
	public int querySPJJS(Map<String, Object> map) throws CoreException {
		return getSqlMap().queryForObject("OpasBizApproval.querySPJJS", map);
	}

	@Override
	public List<Map<String, String>> selectByHisAppIds(Map hisAppIdArrayParams) {
		return getSqlMap().queryForList("OpasBizApproval.selectByHisAppIds", hisAppIdArrayParams);
	}


}
