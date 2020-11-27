package com.huaxia.opas.service.sysparm;

import java.util.List;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.sysparm.Balance;
import com.huaxia.opas.domain.sysparm.Warning;

public interface BalanceService {

	public int queryBalanceCount(Balance balance);

	public List<Balance> queryBalanceMove(Balance balance, int curNum, int pageNum);

	public int insertBalance(Balance balance) throws CoreException;

	public int updateBalance(Balance balance) throws CoreException;

	public int deleteBalance(Balance balance) throws CoreException;

	//批量启用
	public int setBalanceStatusOK(Balance balance) throws CoreException;
	//批量禁用
	public int setBalanceStatusNO(Balance balance) throws CoreException;
	//根据id查询对象
	public Balance findByid(String id);
	//修改的方法
	public int updateBalance2(Balance balance);

	public int queryBalanceCountByProCode(String prodCode);

}
