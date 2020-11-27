package com.huaxia.opas.dao.sysparm.impl;

import java.util.List;
import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.sysparm.CreditTelcheckDao;
import com.huaxia.opas.domain.decision.CreditTelcheckList;

/**
 *名单库管理_征信电话核查白名单dao层实现类的相关方法
 * 
 * @author luzhen.ou
 * 
 * @since 2017-3-15
 * 
 * @version 1.0
 */
public class CreditTelcheckDaoImpl extends AbstractDAO implements CreditTelcheckDao {

	private static final long serialVersionUID = 1062308779223148331L;

	private static final String NAMESPACES = "CreditTelcheckList.";

	@Override
	public int updateCreditTelcheck(CreditTelcheckList creditTelcheckList) throws CoreException {
		return getSqlMap().update(NAMESPACES + "updateCreditTelcheck", creditTelcheckList);
	}

	@Override
	public int queryCreditTelcheckCount(CreditTelcheckList creditTelcheckList) throws CoreException{
		return getSqlMap().queryForObject(NAMESPACES + "queryCreditTelcheckCount", creditTelcheckList);
	}

	@Override
	public List<CreditTelcheckList> queryCreditTelcheckDomainList(CreditTelcheckList creditTelcheckList, int curNum, int pageNum) throws CoreException{
		return getSqlMap().queryForList(NAMESPACES + "queryCreditTelcheckDomainList", creditTelcheckList, curNum, pageNum);
	}

	@Override
	public int deleteCreditTelcheck(CreditTelcheckList creditTelcheckList) throws CoreException{
		return getSqlMap().delete(NAMESPACES + "deleteCreditTelcheck", creditTelcheckList);
	}

	@Override
	public CreditTelcheckList queryCreditTelcheck(CreditTelcheckList creditTelcheckList) throws CoreException {
		return (CreditTelcheckList)getSqlMap().queryForObject(NAMESPACES + "queryCreditTelcheck", creditTelcheckList);
	}

	@Override
	public int updateCreditTelcheckActive(CreditTelcheckList creditTelcheckList) throws CoreException {
		return getSqlMap().update(NAMESPACES + "updateCreditTelcheckActive", creditTelcheckList);
	}

	@Override
	public int queryCreditTelcheckHistoryCount(CreditTelcheckList creditTelcheckList) throws CoreException {
		return getSqlMap().queryForObject(NAMESPACES + "queryCreditTelcheckHistoryCount", creditTelcheckList);
	}

	@Override
	public List<CreditTelcheckList> queryCreditTelcheckHistoryList(CreditTelcheckList creditTelcheckList, int curNum, int pageNum){
		return getSqlMap().queryForList(NAMESPACES + "queryCreditTelcheckHistoryList", creditTelcheckList, curNum, pageNum);
	}
	
	@Override
	public int insertCreditTelcheck(CreditTelcheckList creditTelcheckList) throws CoreException {
		return getSqlMap().insert(NAMESPACES + "insertCreditTelcheck", creditTelcheckList);
	}

	@Override
	public int insertCreditTelcheckList(List<CreditTelcheckList> list) throws CoreException {
		return getSqlMap().insert(NAMESPACES + "insertCreditTelcheckList", list);
	}
	
	@Override
	public int insertCreditTelcheckHistory(CreditTelcheckList creditTelcheckList) throws CoreException {
		return getSqlMap().insert(NAMESPACES + "insertCreditTelcheckHistory", creditTelcheckList);
	}

	@Override
	public int insertCreditTelcheckHistoryList(List<CreditTelcheckList> list)  throws CoreException{
		return getSqlMap().insert(NAMESPACES + "insertCreditTelcheckHistoryList", list);
	}
	
}
