package com.huaxia.opas.service.sysparm.impl;

import java.util.List;

import javax.annotation.Resource;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.sysparm.AddrRiskListHistroyDao;
import com.huaxia.opas.domain.riskcheck.AddrRiskListHistory;
import com.huaxia.opas.service.sysparm.AddrRiskListHistroyService;

public class AddrRistListHistoryServiceImpl extends AbstractDAO implements AddrRiskListHistroyService {

	private static final long serialVersionUID = 5757582418545513970L;
	
	@Resource(name = "addrRiskListHistroyDao")
	private AddrRiskListHistroyDao addrRiskListHistroyDao;
	
	public AddrRiskListHistroyDao getAddrRiskListHistroyDao() {
		return addrRiskListHistroyDao;
	}

	public void setAddrRiskListHistroyDao(AddrRiskListHistroyDao addrRiskListHistroyDao) {
		this.addrRiskListHistroyDao = addrRiskListHistroyDao;
	}

	@Override
	public int insert(AddrRiskListHistory addrrisklisthistory) throws CoreException {
		return getAddrRiskListHistroyDao().insert(addrrisklisthistory);
	}

	@Override
	public int insertSelective(AddrRiskListHistory addrrisklisthistory) throws CoreException {
		return getAddrRiskListHistroyDao().insertSelective(addrrisklisthistory);
	}

	@Override
	public int deleteByPrimaryKey(String autoId) throws CoreException {
		return getAddrRiskListHistroyDao().deleteByPrimaryKey(autoId);
	}

	@Override
	public int updateByPrimaryKey(AddrRiskListHistory addrrisklisthistory) throws CoreException {
		return getAddrRiskListHistroyDao().updateByPrimaryKey(addrrisklisthistory);
	}

	@Override
	public int updateByPrimaryKeySelective(AddrRiskListHistory addrrisklisthistory) throws CoreException {
		return getAddrRiskListHistroyDao().updateByPrimaryKeySelective(addrrisklisthistory);
	}

	@Override
	public AddrRiskListHistory selectByPrimaryKey(String autoId) throws CoreException {
		return getAddrRiskListHistroyDao().selectByPrimaryKey(autoId);
	}

	
	//shihuan 2017-03-03 地址风险名单列表查询 
	@Override
	public List<AddrRiskListHistory> queryAddrRistListHistory(String autoId, int curNum, int pageNum) {
		return getAddrRiskListHistroyDao().queryAddrRistListHistory(autoId, curNum, pageNum);
	}

	//shihuan 2017-03-03 地址风险名单列表查询 条数
	@Override
	public int queryAddrRistHistoryCount(String autoId) {
		return getAddrRiskListHistroyDao().queryAddrRistHistoryCount(autoId);
	}
	
	//shihuan 2017-03-10 地址风险名单批导入文件
	@Override
	public int insertAddrHistoryList(List<AddrRiskListHistory> obj) throws CoreException {
		return getAddrRiskListHistroyDao().insertAddrHistoryList(obj);
	}
}
