package com.huaxia.opas.dao.sysparm.impl;

import java.util.ArrayList;
import java.util.List;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.sysparm.BalanceDao;
import com.huaxia.opas.domain.sysparm.Balance;
import com.huaxia.opas.domain.sysparm.Warning;

public class BalanceDaoImpl extends AbstractDAO implements BalanceDao {

	private static final long serialVersionUID = -2500913519182816381L;

	private static final String NAMESPACES = "Balance.";
	
	@Override
	public int insertBalance(Balance balance) throws CoreException{
		return getSqlMap().insert(NAMESPACES + "insertBalance", balance);
	}

	@Override
	public int updateBalance(Balance balance) throws CoreException{
		return getSqlMap().update(NAMESPACES + "updateBalance", balance);
	}

	@Override
	public int deleteBalance(Balance balance) throws CoreException{
		List ids = balance.getIds();
		return getSqlMap().delete(NAMESPACES + "deleteBalance",ids);
	}

	@Override
	public int queryBalanceCount(Balance balance) {
		return getSqlMap().queryForObject(NAMESPACES + "queryBalanceCount", balance);
	}

	@Override
	public List<Balance> queryBalance(Balance balance, int curNum, int pageNum) {
		List<Balance> list = new ArrayList<Balance>();
		list = getSqlMap().queryForList(NAMESPACES + "queryBalance", balance, curNum, pageNum);
		return list;
	}

	@Override
	public int setBalanceStatusOK(Balance balance) {
		return getSqlMap().update(NAMESPACES + "setBalanceStatusOK", balance);
	}

	@Override
	public int setBalanceStatusNO(Balance balance) throws CoreException {
		return getSqlMap().update(NAMESPACES + "setBalanceStatusNO", balance);
	}

	//根据id查询数据库
	@Override
	public Balance findByid(String id) {
		
		return  getSqlMap().queryForObject(NAMESPACES + "findByid", id);
	}
	//修改的方法
	@Override
	public int updateBalance2(Balance balance) {
		// TODO Auto-generated method stub
		return getSqlMap().update(NAMESPACES + "updateBalance2", balance);
	}

	@Override
	public int queryBalanceCountByProCode(String prodCode) {
		return getSqlMap().queryForObject(NAMESPACES + "queryBalanceCountByProCode", prodCode);
	}

}
