package com.huaxia.opas.dao.sysparm.impl;

import java.util.List;
import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.sysparm.CreditInfoDao;
import com.huaxia.opas.domain.sysparm.CreditWhiteList;

/**
 *名单库管理_标准卡征信信息名单dao层实现类的相关方法
 * 
 * @author luzhen.ou
 * 
 * @since 2017-3-16
 * 
 * @version 1.0
 */
public class CreditInfoDaoImpl extends AbstractDAO implements CreditInfoDao {
	private static final long serialVersionUID = 7592372751814232904L;
	
	private static final String NAMESPACES = "CreditWhiteList.";

	@Override
	public int updateCreditInfo(CreditWhiteList creditWhiteList) throws CoreException {
		return getSqlMap().update(NAMESPACES + "updateCreditInfo", creditWhiteList);
	}

	@Override
	public int queryCreditInfoCount(CreditWhiteList creditWhiteList) throws CoreException{
		return getSqlMap().queryForObject(NAMESPACES + "queryCreditInfoCount", creditWhiteList);
	}

	@Override
	public List<CreditWhiteList> queryCreditInfoList(CreditWhiteList creditWhiteList, int curNum, int pageNum) throws CoreException{
		return getSqlMap().queryForList(NAMESPACES + "queryCreditInfoList", creditWhiteList, curNum, pageNum);
	}

	@Override
	public int deleteCreditInfo(CreditWhiteList creditWhiteList) throws CoreException{
		return getSqlMap().delete(NAMESPACES + "deleteCreditInfo", creditWhiteList);
	}

	@Override
	public CreditWhiteList queryCreditInfo(CreditWhiteList creditWhiteList) throws CoreException {
		return (CreditWhiteList)getSqlMap().queryForObject(NAMESPACES + "queryCreditInfo", creditWhiteList);
	}

	@Override
	public int updateCreditInfoActive(CreditWhiteList creditWhiteList) throws CoreException {
		return getSqlMap().update(NAMESPACES + "updateCreditInfoActive", creditWhiteList);
	}

	@Override
	public int queryCreditInfoHistoryCount(CreditWhiteList creditWhiteList) throws CoreException {
		return getSqlMap().queryForObject(NAMESPACES + "queryCreditInfoHistoryCount", creditWhiteList);
	}

	@Override
	public List<CreditWhiteList> queryCreditInfoHistoryList(CreditWhiteList creditWhiteList, int curNum, int pageNum){
		return getSqlMap().queryForList(NAMESPACES + "queryCreditInfoHistoryList", creditWhiteList, curNum, pageNum);
	}
	
	@Override
	public int insertCreditInfo(CreditWhiteList creditWhiteList) throws CoreException {
		return getSqlMap().insert(NAMESPACES + "insertCreditInfo", creditWhiteList);
	}

	@Override
	public int insertCreditInfoList(List<CreditWhiteList> list) throws CoreException {
		return getSqlMap().insert(NAMESPACES + "insertCreditInfoList", list);
	}
	
	@Override
	public int insertCreditInfoHistory(CreditWhiteList creditWhiteList) throws CoreException {
		return getSqlMap().insert(NAMESPACES + "insertCreditInfoHistory", creditWhiteList);
	}

	@Override
	public int insertCreditInfoHistoryList(List<CreditWhiteList> list)  throws CoreException{
		return getSqlMap().insert(NAMESPACES + "insertCreditInfoHistoryList", list);
	}

	@Override
	public List<CreditWhiteList> queryCreditInfoListWithOutOperator(CreditWhiteList creditWhiteList, int i, int j) {
		return getSqlMap().queryForList(NAMESPACES + "queryCreditInfoListWithOutOperator",creditWhiteList,i,j);
	}
	
}
