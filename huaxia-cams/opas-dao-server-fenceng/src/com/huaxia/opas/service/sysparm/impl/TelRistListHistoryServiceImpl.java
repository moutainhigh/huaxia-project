package com.huaxia.opas.service.sysparm.impl;

import java.util.List;

import javax.annotation.Resource;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.sysparm.TelRiskListHistoryDao;
import com.huaxia.opas.domain.riskcheck.TelRiskList;
import com.huaxia.opas.domain.riskcheck.TelRiskListHistory;
import com.huaxia.opas.service.sysparm.TelRiskListHistoryService;

public class TelRistListHistoryServiceImpl extends AbstractDAO implements TelRiskListHistoryService {

	private static final long serialVersionUID = 5757582418545513970L;
	
	@Resource(name = "telRiskListHistoryDao")
	private TelRiskListHistoryDao telRiskListHistoryDao;
	
	public TelRiskListHistoryDao getTelRiskListHistoryDao() {
		return telRiskListHistoryDao;
	}

	public void setTelRiskListHistoryDao(TelRiskListHistoryDao telRiskListHistoryDao) {
		this.telRiskListHistoryDao = telRiskListHistoryDao;
	}

	@Override
	public int insert(TelRiskListHistory telRiskListHistory) throws CoreException {
		return getTelRiskListHistoryDao().insert(telRiskListHistory);
	}

	@Override
	public int insertSelective(TelRiskListHistory telRiskListHistory) throws CoreException {
		return getTelRiskListHistoryDao().insertSelective(telRiskListHistory);
	}

	@Override
	public int deleteByPrimaryKey(String autoId) throws CoreException {
		return getTelRiskListHistoryDao().deleteByPrimaryKey(autoId);
	}

	@Override
	public int updateByPrimaryKey(TelRiskListHistory telRiskListHistory) throws CoreException {
		return getTelRiskListHistoryDao().updateByPrimaryKey(telRiskListHistory);
	}

	@Override
	public int updateByPrimaryKeySelective(TelRiskListHistory telRiskListHistory) throws CoreException {
		return getTelRiskListHistoryDao().updateByPrimaryKeySelective(telRiskListHistory);
	}

	@Override
	public TelRiskList selectByPrimaryKey(String autoId) throws CoreException {
		return getTelRiskListHistoryDao().selectByPrimaryKey(autoId);
	}

	//shihuan 2017-03-03 地址风险名单列表查询 
	@Override
	public List<TelRiskListHistory> queryTelRistHisList(String autoId, int curNum, int pageNum) {
		return getTelRiskListHistoryDao().queryTelRistHisList(autoId, curNum, pageNum);
	}

	//shihuan 2017-03-03 地址风险名单列表查询 条数
	@Override
	public int queryTelRistHisCount(String autoId) {
		return getTelRiskListHistoryDao().queryTelRistHisCount(autoId);
	}
	
	
}
