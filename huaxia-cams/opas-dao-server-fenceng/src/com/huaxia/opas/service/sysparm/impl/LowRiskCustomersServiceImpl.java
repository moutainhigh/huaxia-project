package com.huaxia.opas.service.sysparm.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.sysparm.BatchFileInfoDao;
import com.huaxia.opas.dao.sysparm.CreditTelcheckDao;
import com.huaxia.opas.dao.sysparm.LowRiskCustomersDao;
import com.huaxia.opas.domain.decision.CreditTelcheckList;
import com.huaxia.opas.domain.sysparm.BatchfileInfo;
import com.huaxia.opas.domain.sysparm.LowRiskCustomers;
import com.huaxia.opas.service.AbstractService;
import com.huaxia.opas.service.sysparm.LowRiskCustomersService;
/**
 * 低风险service层实现类的相关方法
 * 
 * @author liuwei
 * 
 * @since 2020
 * 
 * @version 1.0
 */
public class LowRiskCustomersServiceImpl extends AbstractService implements LowRiskCustomersService ,Serializable{

	private static final long serialVersionUID = 1858281900877294404L;
	
	@Resource(name="creditTelcheckDao")
	private CreditTelcheckDao creditTelcheckDao;
	@Resource(name="batchFileInfoDao")
	private BatchFileInfoDao batchFileInfoDao;
	@Resource(name="lowRiskCustomersDao")
	private LowRiskCustomersDao lowRiskCustomersDao;
	@Override
	public int  updateLowRiskCustomersActive(LowRiskCustomers lowRiskCustomers) throws CoreException {
//		List<String> ids = lowRiskCustomers.getIds();
//		String currStatus = lowRiskCustomers.getSTATUS();
//		//分页最大页数是50
//		List<CreditTelcheckList> resutIdentitylist = creditTelcheckDao.queryCreditTelcheckDomainList(creditTelcheckList, 0, 50);
//		ListIterator<CreditTelcheckList> iterator = resutIdentitylist.listIterator();
//		CreditTelcheckList tmp =new CreditTelcheckList();
//		while (iterator.hasNext()) {
//			tmp = iterator.next();
//			if (currStatus.equals(tmp.getCurrStatus())) {
//				ids.remove(tmp.getAutoId());
//				iterator.remove();
//			}else{
//				tmp.setOpretionId(UUID.randomUUID().toString().replace("-", ""));
//			}
//		}
//		if (null == resutIdentitylist || resutIdentitylist.size() == 0) {
//			String flag = currStatus.equals("0")?"禁用":"启用";
//			throw new CoreException("所有选中数据已经"+flag+",无需修改!");
//		}
//		creditTelcheckDao.insertCreditTelcheckHistoryList(resutIdentitylist);
		return lowRiskCustomersDao.updateLowRiskCustomersActive(lowRiskCustomers);
	}

	@Override
	public int deleteLowRiskCustomers(LowRiskCustomers lowRiskCustomer) throws CoreException {
		return lowRiskCustomersDao.deleteLowRiskCustomers(lowRiskCustomer);
	}

	@Override
	public int  updateLowRiskCustomers(LowRiskCustomers lowRiskCustomers) throws CoreException {
//		CreditTelcheckList returnCreditTelcheck = creditTelcheckDao.queryCreditTelcheck(creditTelcheckList);
//		returnCreditTelcheck.setOpretionId(creditTelcheckList.getOpretionId());
//		creditTelcheckDao.insertCreditTelcheckHistory(returnCreditTelcheck);
		return lowRiskCustomersDao.updateLowRiskCustomers(lowRiskCustomers);
	}

	@Override
	public int  insertLowRiskCustomer(LowRiskCustomers lowRiskCustomers) throws CoreException {
		return lowRiskCustomersDao.insertLowRiskCustomer(lowRiskCustomers);
	}

	@Override
	public	Map<String, Object> queryLowRiskDomainList(LowRiskCustomers lowRiskCustomers, int curPage, int pageNum)
			throws CoreException {
		int curNum = 0;
		if (curPage > 1) {
			curNum = (curPage - 1) * pageNum;
		}
		List<LowRiskCustomers> list = new ArrayList<LowRiskCustomers>();
		int count = lowRiskCustomersDao.queryLowRiskCustomerCount(lowRiskCustomers);
		if(count > 0){
			list = lowRiskCustomersDao.queryLowRiskCustomerDomainList(lowRiskCustomers, curNum, pageNum);
		}
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("total", count);
		dataMap.put("rows", list);
		return dataMap;
	}
	@Override
	public	Map<String, Object> queryLowRiskDomainList(LowRiskCustomers lowRiskCustomers,Map<String, Object> args)
			throws CoreException {
		List<LowRiskCustomers> list = new ArrayList<LowRiskCustomers>();
		int count = lowRiskCustomersDao.queryLowRiskCustomerCount(lowRiskCustomers);
		if(count > 0){
			list = lowRiskCustomersDao.queryLowRiskCustomerDomainList(args);
		}
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("total", count);
		dataMap.put("rows", list);
		return dataMap;
	}
	@Override
	public Map<String, Object> queryCreditTelcheckHistoryList(CreditTelcheckList creditTelcheckList, int curPage, int pageNum)
			throws CoreException {
		int curNum = 0;
		if (curPage > 1) {
			curNum = (curPage - 1) * pageNum;
		}
		List<CreditTelcheckList> list = new ArrayList<CreditTelcheckList>();
		int count = creditTelcheckDao.queryCreditTelcheckHistoryCount(creditTelcheckList);
		if(count > 0){
			list = creditTelcheckDao.queryCreditTelcheckHistoryList(creditTelcheckList, curNum, pageNum);
		}
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("total", count);
		dataMap.put("rows", list);
		return dataMap;
	}

	/**
	 * wangtao 批量导入
	 * @param list
	 * @return
	 * @throws CoreException
	 */
	@Override
	public int  insertCreditTelcheckList(List<CreditTelcheckList> list,BatchfileInfo batchfileInfo) throws CoreException {
		int inputCounts =  creditTelcheckDao.insertCreditTelcheckList(list);
		batchfileInfo.setInputCounts(new BigDecimal(inputCounts));
		batchFileInfoDao.insert(batchfileInfo);
		return inputCounts;
	}

	@Override
	public int insertLowRiskCustomersList(List<LowRiskCustomers> list) throws Exception {
		int result =  lowRiskCustomersDao.insertLowRiskCustomersList(list);
		return result;
	}

	@Override
	public int deleteLowRiskCustomersAll(String str) throws CoreException {
		return lowRiskCustomersDao.deleteLowRiskCustomersAll(str);
	}

	@Override
	public int deleteLowRiskCustomersTempAll(String str) throws CoreException {
		return lowRiskCustomersDao.deleteLowRiskCustomersTempAll(str);
	}

	@Override
	public Map executeProceLowRiskRename(Map param) {
		return lowRiskCustomersDao.executeProceLowRiskRename(param);
	}

	@Override
	public int insertLowRiskCustomersListCall(Map map) {
		// TODO Auto-generated method stub
		return lowRiskCustomersDao.insertLowRiskCustomersListCall(map);
	}
}
