package com.huaxia.opas.dao.sysparm.impl;

import java.util.List;
import java.util.Map;

import com.huateng.neofp.core.CoreException;
import com.huateng.neofp.service.sqlmap.SqlMap;
import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.sysparm.LowRiskCustomersDao;
import com.huaxia.opas.domain.decision.CreditTelcheckList;
import com.huaxia.opas.domain.sysparm.LowRiskCustomers;

/**
 *新增低风险客户名单库dao层实现类的相关方法
 * 
 * @author liuwei
 * 
 * @since 2020
 * 
 * @version 1.0
 */
public class LowRiskCustomersDaoImpl extends AbstractDAO implements LowRiskCustomersDao {

	private static final long serialVersionUID = 1062308779223148331L;

	private static final String NAMESPACES = "LowRiskCustomers.";

	@Override
	public int  updateLowRiskCustomers(LowRiskCustomers lowRiskCustomers) throws CoreException {
		return getSqlMap().update(NAMESPACES + "updateLowRiskCustomers", lowRiskCustomers);
	}

	@Override
	public int queryLowRiskCustomerCount(LowRiskCustomers lowRiskCustomers) throws CoreException{
		return getSqlMap().queryForObject(NAMESPACES + "queryLowRiskCustomerCount", lowRiskCustomers);
	}

	@Override
	public List<LowRiskCustomers> queryLowRiskCustomerDomainList(LowRiskCustomers lowRiskCustomers, int curNum, int pageNum) throws CoreException{
		return getSqlMap().queryForList(NAMESPACES + "queryLowRiskCustomerDomainList", lowRiskCustomers, curNum, pageNum);
	}
	@Override
	public List<LowRiskCustomers> queryLowRiskCustomerDomainList(Map<String, Object> args) throws CoreException{
		return getSqlMap().queryForList(NAMESPACES + "queryLowRiskCustomerDomainList2", args);
	}

	@Override
	public int  deleteLowRiskCustomers(LowRiskCustomers lowRiskCustomer) throws CoreException{
		return getSqlMap().delete(NAMESPACES + "deleteLowRiskCustomers", lowRiskCustomer);
	}

	@Override
	public CreditTelcheckList queryCreditTelcheck(CreditTelcheckList creditTelcheckList) throws CoreException {
		return (CreditTelcheckList)getSqlMap().queryForObject(NAMESPACES + "queryCreditTelcheck", creditTelcheckList);
	}

	@Override
	public int updateLowRiskCustomersActive(LowRiskCustomers lowRiskCustomers) throws CoreException {
		return getSqlMap().update(NAMESPACES + "updateLowRiskCustomersActive", lowRiskCustomers);
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
	public int insertLowRiskCustomer(LowRiskCustomers lowRiskCustomers) throws CoreException {
		return getSqlMap().insert(NAMESPACES + "insertLowRiskCustomer", lowRiskCustomers);
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
	@Override
	public int insertLowRiskCustomersList(List<LowRiskCustomers> list)  throws Exception{
		return getSqlMap().insert(NAMESPACES + "insertLowRiskCustomersList", list);
	}

	@Override
	public int deleteLowRiskCustomersAll(String str) throws CoreException {
	 	return getSqlMap().insert(NAMESPACES + "deleteLowRiskCustomersAll");
	}

	@Override
	public Map executeProceLowRiskRename(Map param) {
		return getSqlMap().queryForObject(NAMESPACES + "executeProceLowRiskRename",param);
	}

	@Override
	public int deleteLowRiskCustomersTempAll(String str) throws CoreException {
		SqlMap sqlMap2 = getSqlMap();
		return sqlMap2.delete(NAMESPACES + "deleteLowRiskCustomersTempAll",str);
	}

	@Override
	public int insertLowRiskCustomersListCall(Map map) {
		// TODO Auto-generated method stub
		return getSqlMap().insert(NAMESPACES + "insertLowRiskCustomersListCall", map);
	}
}
