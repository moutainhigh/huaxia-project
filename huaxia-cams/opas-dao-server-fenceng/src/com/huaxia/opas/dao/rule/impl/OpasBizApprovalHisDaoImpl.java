package com.huaxia.opas.dao.rule.impl;

import java.io.Serializable;
import java.util.List;

import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.rule.OpasBizApprovalHisDao;
import com.huaxia.opas.domain.rule.OpasBizApprovalHis;
import com.huaxia.opas.domain.rule.Opasbizinpapplc1;

public class OpasBizApprovalHisDaoImpl extends AbstractDAO implements OpasBizApprovalHisDao,Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -7362302108989417451L;

	@Override
	public int deleteByPrimaryKey(String appId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(OpasBizApprovalHis record) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public List<OpasBizApprovalHis> selectByExample(Opasbizinpapplc1 example) {
		return getSqlMap().queryForList("OpasBizApprovalHis.selectByExample", example);
	}

	@Override
	public OpasBizApprovalHis selectByPrimaryKey(String appId) {
		return null;
	}

}
