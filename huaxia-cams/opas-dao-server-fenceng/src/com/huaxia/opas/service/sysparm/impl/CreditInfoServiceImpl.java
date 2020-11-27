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
import com.huaxia.opas.dao.sysparm.CreditInfoDao;
import com.huaxia.opas.dao.system.ApUserDao;
import com.huaxia.opas.domain.sysparm.BatchfileInfo;
import com.huaxia.opas.domain.sysparm.CreditWhiteList;
import com.huaxia.opas.domain.system.ApUser;
import com.huaxia.opas.service.AbstractService;
import com.huaxia.opas.service.sysparm.CreditInfoService;
/**
 * 名单库管理_标准卡征信信息名单service层实现类的相关方法
 * 
 * @author luzhen.ou
 * 
 * @since 2017-03-16
 * 
 * @version 1.0
 */
public class CreditInfoServiceImpl extends AbstractService implements CreditInfoService ,Serializable{
	
	private static final long serialVersionUID = 1574962805381539474L;
	
	@Resource(name="creditInfoDao")
	private CreditInfoDao creditInfoDao;
	@Resource(name="batchFileInfoDao")
	private BatchFileInfoDao batchFileInfoDao;
	@Resource(name = "apUserDao")
	private ApUserDao apUserDao;
	
	@Override
	public int updateCreditInfoActive(CreditWhiteList creditWhiteList) throws CoreException {
		List<String> ids = creditWhiteList.getIds();
		String currStatus = creditWhiteList.getCurrStatus();
		//将操作用户设置成操作员的姓名
		String operator = creditWhiteList.getOperator();
		ApUser user = apUserDao.queryApUserByUserCode(operator);
		String userName = user.getUserName();
		creditWhiteList.setOperator(userName);
		
		//分页最大页数是50
		List<CreditWhiteList> resutIdentitylist = creditInfoDao.queryCreditInfoListWithOutOperator(creditWhiteList, 0, 50);
		ListIterator<CreditWhiteList> iterator = resutIdentitylist.listIterator();
		CreditWhiteList tmp =new CreditWhiteList();
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
		creditInfoDao.insertCreditInfoHistoryList(resutIdentitylist);
		return creditInfoDao.updateCreditInfoActive(creditWhiteList);
	}

	@Override
	public int deleteCreditInfo(CreditWhiteList creditWhiteList) throws CoreException {
		return creditInfoDao.deleteCreditInfo(creditWhiteList);
	}

	@Override
	public int updateCreditInfo(CreditWhiteList creditWhiteList) throws CoreException {
		//将操作用户设置成操作员的姓名
		String operator = creditWhiteList.getOperator();
		ApUser user = apUserDao.queryApUserByUserCode(operator);
		String userName = user.getUserName();
		creditWhiteList.setOperator(userName);
			
		CreditWhiteList returnCreditInfo = creditInfoDao.queryCreditInfo(creditWhiteList);
		if(returnCreditInfo.toString().equals(creditWhiteList.toString())){
			throw new CoreException("修改数据没有变化！");
		}
		returnCreditInfo.setOpretionId(creditWhiteList.getOpretionId());
		creditInfoDao.insertCreditInfoHistory(returnCreditInfo);
		return creditInfoDao.updateCreditInfo(creditWhiteList);
	}

	@Override
	public int insertCreditInfo(CreditWhiteList creditWhiteList) throws CoreException {
		//将操作用户设置成操作员的姓名
		String operator = creditWhiteList.getOperator();
		ApUser user = apUserDao.queryApUserByUserCode(operator);
		String userName = user.getUserName();
		creditWhiteList.setOperator(userName);
		
		return creditInfoDao.insertCreditInfo(creditWhiteList);
	}

	@Override
	public Map<String, Object> queryCreditInfoList(CreditWhiteList creditWhiteList, int curPage, int pageNum)
			throws CoreException {
		int curNum = 0;
		if (curPage > 1) {
			curNum = (curPage - 1) * pageNum;
		}
		List<CreditWhiteList> list = new ArrayList<CreditWhiteList>();
		int count = creditInfoDao.queryCreditInfoCount(creditWhiteList);
		if(count > 0){
			list = creditInfoDao.queryCreditInfoList(creditWhiteList, curNum, pageNum);
		}
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("total", count);
		dataMap.put("rows", list);
		return dataMap;
	}

	@Override
	public Map<String, Object> queryCreditInfoHistoryList(CreditWhiteList creditWhiteList, int curPage, int pageNum)
			throws CoreException {
		int curNum = 0;
		if (curPage > 1) {
			curNum = (curPage - 1) * pageNum;
		}
		List<CreditWhiteList> list = new ArrayList<CreditWhiteList>();
		int count = creditInfoDao.queryCreditInfoHistoryCount(creditWhiteList);
		if(count > 0){
			list = creditInfoDao.queryCreditInfoHistoryList(creditWhiteList, curNum, pageNum);
		}
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("total", count);
		dataMap.put("rows", list);
		return dataMap;
	}
	/**
	 * 批量导入
	 * wangtao
	 */
	@Override
	public int insertCreditInfoList(List<CreditWhiteList> list,BatchfileInfo batchfileInfo) throws CoreException {
		int inputCounts =  creditInfoDao.insertCreditInfoList(list);
		batchfileInfo.setInputCounts(new BigDecimal(inputCounts));
		batchFileInfoDao.insert(batchfileInfo);
		return inputCounts;
	}
	
}
