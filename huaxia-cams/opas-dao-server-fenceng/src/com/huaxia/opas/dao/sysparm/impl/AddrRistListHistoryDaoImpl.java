package com.huaxia.opas.dao.sysparm.impl;

import java.util.ArrayList;
import java.util.List;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.sysparm.AddrRiskListHistroyDao;
import com.huaxia.opas.domain.riskcheck.AddrRiskList;
import com.huaxia.opas.domain.riskcheck.AddrRiskListHistory;

public class AddrRistListHistoryDaoImpl extends AbstractDAO implements AddrRiskListHistroyDao {

	private static final long serialVersionUID = 5757582418545513970L;
	
	private static final String NAMESPACES = "AddrRiskListHistory.";

	@Override
	public int insert(AddrRiskListHistory addrrisklisthistory) {
		return getSqlMap().insert(NAMESPACES + "insert", addrrisklisthistory);
	}

	@Override
	public int insertSelective(AddrRiskListHistory addrrisklisthistory) {
		return getSqlMap().insert(NAMESPACES + "insertSelective", addrrisklisthistory);
	}

	@Override
	public int deleteByPrimaryKey(String autoId) {
		AddrRiskList addrrisklist = new AddrRiskList();
		addrrisklist.setAutoId(autoId);
		return getSqlMap().delete(NAMESPACES + "deleteByPrimaryKey", autoId);
	}

	@Override
	public int updateByPrimaryKey(AddrRiskListHistory addrrisklisthistory) {
		return getSqlMap().update(NAMESPACES + "updateByPrimaryKey", addrrisklisthistory);
	}

	@Override
	public int updateByPrimaryKeySelective(AddrRiskListHistory addrrisklisthistory) {
		return getSqlMap().update(NAMESPACES + "updateByPrimaryKeySelective", addrrisklisthistory);
	}

	@Override
	public AddrRiskListHistory selectByPrimaryKey(String autoId) {
		AddrRiskListHistory addrrisklisthistory = new AddrRiskListHistory();
		addrrisklisthistory.setAutoId(autoId);
		return (getSqlMap().queryForObject(NAMESPACES + "selectByPrimaryKey", autoId));
	}

	
	//shihuan 2017-03-03 地址风险名单列表查询 
	@Override
	public List<AddrRiskListHistory> queryAddrRistListHistory(String autoId, int curNum, int pageNum) {

		List<AddrRiskListHistory> list = new ArrayList<AddrRiskListHistory>();

		list = getSqlMap().queryForList(NAMESPACES + "queryAddrRistListHistory", autoId, curNum, pageNum);

		return list;
	}

	//shihuan 2017-03-03 地址风险名单列表查询 条数
	@Override
	public int queryAddrRistHistoryCount(String autoId) {
		return getSqlMap().queryForObject(NAMESPACES + "queryAddrRistHistoryCount", autoId);
	}
	
	//shihuan 2017-03-10 地址风险名单批导入文件
	@Override
	public int insertAddrHistoryList(List<AddrRiskListHistory> obj) throws CoreException {
		return getSqlMap().insert(NAMESPACES + "insertAddrHistoryList", obj);
	}
}
