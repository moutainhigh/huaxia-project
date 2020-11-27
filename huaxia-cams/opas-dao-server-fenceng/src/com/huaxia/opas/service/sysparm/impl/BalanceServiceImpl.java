package com.huaxia.opas.service.sysparm.impl;

import java.io.Serializable;
import java.util.List;
import java.util.ListIterator;
import java.util.UUID;

import javax.annotation.Resource;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.sysparm.BalanceDao;
import com.huaxia.opas.domain.decision.CreditTelcheckList;
import com.huaxia.opas.domain.sysparm.Balance;
import com.huaxia.opas.service.AbstractService;
import com.huaxia.opas.service.sysparm.BalanceService;

public class BalanceServiceImpl extends AbstractService implements BalanceService,Serializable {

	private static final long serialVersionUID = 2535208490898308631L;

	// 调用Dao层
	@Resource(name = "balanceDao")
	private BalanceDao balanceDao;

	public BalanceDao getBalanceDao() {
		return balanceDao;
	}

	public void setBalanceDao(BalanceDao balanceDao) {
		this.balanceDao = balanceDao;
	}

	// 添加
	@Override
	public int insertBalance(Balance balance) throws CoreException {
		return getBalanceDao().insertBalance(balance);
	}

	// 修改
	@Override
	public int updateBalance(Balance balance) throws CoreException {
		return getBalanceDao().updateBalance(balance);
	}

	// 删除
	@Override
	public int deleteBalance(Balance balance) throws CoreException {
		return getBalanceDao().deleteBalance(balance);
	}

	// 分页查询
	@Override
	public int queryBalanceCount(Balance balance) {
		return getBalanceDao().queryBalanceCount(balance);
	}

	@Override
	public List<Balance> queryBalanceMove(Balance balance, int curNum, int pageNum) {
		return getBalanceDao().queryBalance(balance, curNum, pageNum);
	}

	//批量启用
	@Override
	public int setBalanceStatusOK(Balance balance) throws CoreException{
		return getBalanceDao().setBalanceStatusOK(balance);
	}

	//批量禁用
	@Override
	public int setBalanceStatusNO(Balance balance) throws CoreException {
		return getBalanceDao().setBalanceStatusNO(balance);
	}
	
	//根据id查询数据
	@Override
	public Balance findByid(String id) {
		// TODO Auto-generated method stub
		return getBalanceDao().findByid(id);
	}
	//修改的方法
	@Override
	public int updateBalance2(Balance balance) {
		// TODO Auto-generated method stub
		return getBalanceDao().updateBalance2(balance);
	}

	@Override
	public int queryBalanceCountByProCode(String prodCode) {
		return getBalanceDao().queryBalanceCountByProCode(prodCode);
	}

}
