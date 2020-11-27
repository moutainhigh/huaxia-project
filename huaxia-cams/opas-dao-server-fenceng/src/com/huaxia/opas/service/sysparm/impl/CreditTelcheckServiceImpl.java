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
import com.huaxia.opas.domain.decision.CreditTelcheckList;
import com.huaxia.opas.domain.sysparm.BatchfileInfo;
import com.huaxia.opas.service.AbstractService;
import com.huaxia.opas.service.sysparm.CreditTelcheckService;
/**
 * 单库管理_征信电话核查白名单service层实现类的相关方法
 * 
 * @author luzhen.ou
 * 
 * @since 2017-03-15
 * 
 * @version 1.0
 */
public class CreditTelcheckServiceImpl extends AbstractService implements CreditTelcheckService ,Serializable{

	private static final long serialVersionUID = 1858281900877294404L;
	
	@Resource(name="creditTelcheckDao")
	private CreditTelcheckDao creditTelcheckDao;
	@Resource(name="batchFileInfoDao")
	private BatchFileInfoDao batchFileInfoDao;
	@Override
	public int  updateCreditTelcheckActive(CreditTelcheckList creditTelcheckList) throws CoreException {
		List<String> ids = creditTelcheckList.getIds();
		String currStatus = creditTelcheckList.getCurrStatus();
		//分页最大页数是50
		List<CreditTelcheckList> resutIdentitylist = creditTelcheckDao.queryCreditTelcheckDomainList(creditTelcheckList, 0, 50);
		ListIterator<CreditTelcheckList> iterator = resutIdentitylist.listIterator();
		CreditTelcheckList tmp =new CreditTelcheckList();
		while (iterator.hasNext()) {
			tmp = iterator.next();
			if (currStatus.equals(tmp.getCurrStatus())) {
				ids.remove(tmp.getAutoId());
				iterator.remove();
			}else{
				tmp.setOpretionId(UUID.randomUUID().toString().replace("-", ""));
			}
		}
		if (null == resutIdentitylist || resutIdentitylist.size() == 0) {
			String flag = currStatus.equals("0")?"禁用":"启用";
			throw new CoreException("所有选中数据已经"+flag+",无需修改!");
		}
		creditTelcheckDao.insertCreditTelcheckHistoryList(resutIdentitylist);
		return creditTelcheckDao.updateCreditTelcheckActive(creditTelcheckList);
	}

	@Override
	public int  deleteCreditTelcheck(CreditTelcheckList creditTelcheckList) throws CoreException {
		return creditTelcheckDao.deleteCreditTelcheck(creditTelcheckList);
	}

	@Override
	public int  updateCreditTelcheck(CreditTelcheckList creditTelcheckList) throws CoreException {
		CreditTelcheckList returnCreditTelcheck = creditTelcheckDao.queryCreditTelcheck(creditTelcheckList);
		returnCreditTelcheck.setOpretionId(creditTelcheckList.getOpretionId());
		creditTelcheckDao.insertCreditTelcheckHistory(returnCreditTelcheck);
		return creditTelcheckDao.updateCreditTelcheck(creditTelcheckList);
	}

	@Override
	public int  insertCreditTelcheck(CreditTelcheckList creditTelcheckList) throws CoreException {
		return creditTelcheckDao.insertCreditTelcheck(creditTelcheckList);
	}

	@Override
	public Map<String, Object> queryCreditTelcheckDomainList(CreditTelcheckList creditTelcheckList, int curPage, int pageNum)
			throws CoreException {
		int curNum = 0;
		if (curPage > 1) {
			curNum = (curPage - 1) * pageNum;
		}
		List<CreditTelcheckList> list = new ArrayList<CreditTelcheckList>();
		int count = creditTelcheckDao.queryCreditTelcheckCount(creditTelcheckList);
		if(count > 0){
			list = creditTelcheckDao.queryCreditTelcheckDomainList(creditTelcheckList, curNum, pageNum);
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
	
}
