package com.huaxia.opas.dao.sysparm.impl;

import java.util.ArrayList;
import java.util.List;

import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.sysparm.TelRiskListHistoryDao;
import com.huaxia.opas.domain.riskcheck.AddrRiskList;
import com.huaxia.opas.domain.riskcheck.TelRiskList;
import com.huaxia.opas.domain.riskcheck.TelRiskListHistory;

public class TelRistListHistoryDaoImpl extends AbstractDAO implements TelRiskListHistoryDao {

	private static final long serialVersionUID = 5757582418545513970L;
	
	private static final String NAMESPACES = "TelRiskListHistory.";

	@Override
	public int insert(TelRiskListHistory telRiskListHistory) {
		return getSqlMap().insert(NAMESPACES + "insert", telRiskListHistory);
	}

	@Override
	public int insertSelective(TelRiskListHistory telRiskListHistory) {
		return getSqlMap().insert(NAMESPACES + "insertSelective", telRiskListHistory);
	}

	@Override
	public int deleteByPrimaryKey(String autoId) {
		AddrRiskList addrrisklist = new AddrRiskList();
		addrrisklist.setAutoId(autoId);
		return getSqlMap().delete(NAMESPACES + "deleteByPrimaryKey", autoId);
	}

	@Override
	public int updateByPrimaryKey(TelRiskListHistory telRiskListHistory) {
		return getSqlMap().update(NAMESPACES + "updateByPrimaryKey", telRiskListHistory);
	}

	@Override
	public int updateByPrimaryKeySelective(TelRiskListHistory telRiskListHistory) {
		return getSqlMap().update(NAMESPACES + "updateByPrimaryKeySelective", telRiskListHistory);
	}

	@Override
	public TelRiskList selectByPrimaryKey(String autoId) {
		TelRiskListHistory telRiskListHistory = new TelRiskListHistory();
		telRiskListHistory.setAutoId(autoId);
		return (getSqlMap().queryForObject(NAMESPACES + "selectByPrimaryKey", autoId));
	}

	//shihuan 2017-03-03 地址风险名单列表查询 
	@Override
	public List<TelRiskListHistory> queryTelRistHisList(String autoId, int curNum, int pageNum) {

		List<TelRiskListHistory> list = new ArrayList<TelRiskListHistory>();

		list = getSqlMap().queryForList(NAMESPACES + "queryTelRistHisList", autoId, curNum, pageNum);

		return list;
	}

	//shihuan 2017-03-03 地址风险名单列表查询 条数
	@Override
	public int queryTelRistHisCount(String autoId) {
		return getSqlMap().queryForObject(NAMESPACES + "queryTelRistHisCount", autoId);
	}
	
}
