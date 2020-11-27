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
import com.huaxia.opas.dao.sysparm.MerchTeamdealDao;
import com.huaxia.opas.dao.system.ApUserDao;
import com.huaxia.opas.domain.decision.MerchTeamdealList;
import com.huaxia.opas.domain.sysparm.BatchfileInfo;
import com.huaxia.opas.domain.system.ApUser;
import com.huaxia.opas.service.AbstractService;
import com.huaxia.opas.service.sysparm.MerchTeamdealService;

/**
 * 单库管理_商户团办名单service层实现类的相关方法
 * 
 * @author luzhen.ou
 * 
 * @since 2017-03-18
 * 
 * @version 1.0
 */
public class MerchTeamdealServiceImpl extends AbstractService implements MerchTeamdealService,Serializable{
	
	private static final long serialVersionUID = 5328730164679564344L;
	
	@Resource(name = "apUserDao")
	private ApUserDao apUserDao;
	
	@Resource(name="merchTeamdealDao")
	private MerchTeamdealDao merchTeamdealDao;
	public MerchTeamdealDao getMerchTeamdealDao() {
		return merchTeamdealDao;
	}

	public void setMerchTeamdealDao(MerchTeamdealDao merchTeamdealDao) {
		this.merchTeamdealDao = merchTeamdealDao;
	}

	public BatchFileInfoDao getBatchFileInfoDao() {
		return batchFileInfoDao;
	}

	public void setBatchFileInfoDao(BatchFileInfoDao batchFileInfoDao) {
		this.batchFileInfoDao = batchFileInfoDao;
	}

	@Resource(name="batchFileInfoDao")
	private BatchFileInfoDao batchFileInfoDao;
	@Override
	public int updateMerchTeamdealActive(MerchTeamdealList merchTeamdealList) throws CoreException {
		String operator = merchTeamdealList.getOperator();
		ApUser user = apUserDao.queryApUserByUserCode(operator);
		String userName = user.getUserName();
		merchTeamdealList.setOperator(userName);
		
		List<String> ids = merchTeamdealList.getIds();
		String currStatus = merchTeamdealList.getCurrStatus();
		//分页最大页数是50
		List<MerchTeamdealList> resutIdentitylist = merchTeamdealDao.queryMerchTeamdealList(merchTeamdealList, 0, 50);
		ListIterator<MerchTeamdealList> iterator = resutIdentitylist.listIterator();
		MerchTeamdealList tmp =new MerchTeamdealList();
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
		merchTeamdealDao.insertMerchTeamdealHistoryList(resutIdentitylist);
		return merchTeamdealDao.updateMerchTeamdealActive(merchTeamdealList);
	}

	@Override
	public int deleteMerchTeamdeal(MerchTeamdealList merchTeamdealList) throws CoreException {
		return merchTeamdealDao.deleteMerchTeamdeal(merchTeamdealList);
	}

	@Override
	public int updateMerchTeamdeal(MerchTeamdealList merchTeamdealList) throws CoreException {
		String operator = merchTeamdealList.getOperator();
		ApUser user = apUserDao.queryApUserByUserCode(operator);
		String userName = user.getUserName();
		merchTeamdealList.setOperator(userName);
		
		//去除无效的零，再由科学技术法转成String类型
		MerchTeamdealList returnMerchTeamdealList = merchTeamdealDao.queryMerchTeamdeal(merchTeamdealList);
		if (returnMerchTeamdealList.toString().equals(merchTeamdealList.toString())) {
			throw new CoreException("数据没有发生变化，无需保存");
		}
		returnMerchTeamdealList.setOpretionId(merchTeamdealList.getOpretionId());
		merchTeamdealDao.insertMerchTeamdealHistory(returnMerchTeamdealList);
		return merchTeamdealDao.updateMerchTeamdeal(merchTeamdealList);
	}

	@Override
	public int insertMerchTeamdeal(MerchTeamdealList merchTeamdealList) throws CoreException {
		//将操作用户设置成操作员的姓名
		String operator = merchTeamdealList.getOperator();
		ApUser user = apUserDao.queryApUserByUserCode(operator);
		String userName = user.getUserName();
		merchTeamdealList.setOperator(userName);
		
		return merchTeamdealDao.insertMerchTeamdeal(merchTeamdealList);
	}

	@Override
	public Map<String, Object> queryMerchTeamdealList(MerchTeamdealList merchTeamdealList, int curPage, int pageNum)
			throws CoreException {
		int curNum = 0;
		if (curPage > 1) {
			curNum = (curPage - 1) * pageNum;
		}
		List<MerchTeamdealList> list = new ArrayList<MerchTeamdealList>();
		int count = merchTeamdealDao.queryMerchTeamdealCount(merchTeamdealList);
		if(count > 0){
			list = merchTeamdealDao.queryMerchTeamdealList(merchTeamdealList, curNum, pageNum);
		}
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("total", count);
		dataMap.put("rows", list);
		return dataMap;
	}

	@Override
	public Map<String, Object> queryMerchTeamdealHistoryList(MerchTeamdealList merchTeamdealList, int curPage, int pageNum)
			throws CoreException {
		int curNum = 0;
		if (curPage > 1) {
			curNum = (curPage - 1) * pageNum;
		}
		List<MerchTeamdealList> list = new ArrayList<MerchTeamdealList>();
		int count = merchTeamdealDao.queryMerchTeamdealHistoryCount(merchTeamdealList);
		if(count > 0){
			list = merchTeamdealDao.queryMerchTeamdealHistoryList(merchTeamdealList, curNum, pageNum);
		}
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("total", count);
		dataMap.put("rows", list);
		return dataMap;
	}

	@Override
	public int insertMerchTeamdealList(List<MerchTeamdealList> list, BatchfileInfo batchfileInfo) throws CoreException {
		int inputCounts =  merchTeamdealDao.insertMerchTeamdealList(list);
		batchfileInfo.setInputCounts(new BigDecimal(inputCounts));
		batchFileInfoDao.insert(batchfileInfo);
		return inputCounts;
	}

	
}
