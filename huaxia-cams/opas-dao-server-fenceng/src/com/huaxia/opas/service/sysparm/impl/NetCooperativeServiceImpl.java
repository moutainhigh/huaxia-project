package com.huaxia.opas.service.sysparm.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.sysparm.BatchFileInfoDao;
import com.huaxia.opas.dao.sysparm.NetCooperativeDao;
import com.huaxia.opas.dao.system.ApUserDao;
import com.huaxia.opas.domain.sysparm.BatchfileInfo;
import com.huaxia.opas.domain.sysparm.Business;
import com.huaxia.opas.domain.system.ApUser;
import com.huaxia.opas.service.sysparm.NetCooperativeService;

public class NetCooperativeServiceImpl implements NetCooperativeService, Serializable {

	private static final long serialVersionUID = -8134953448605358592L;

	@Resource(name = "apUserDao")
	private ApUserDao apUserDao;
	@Resource(name = "netCooperativeDao")
	private NetCooperativeDao netCooperativeDao;
	@Resource(name="batchFileInfoDao")
	private BatchFileInfoDao batchFileInfoDao;

	public NetCooperativeDao getNetCooperativeDao() {
		return netCooperativeDao;
	}

	public void setNetCooperativeDao(NetCooperativeDao netCooperativeDao) {
		this.netCooperativeDao = netCooperativeDao;
	}

	// 查询
	@Override
	public int queryBusinessCount(Business business) {
		return getNetCooperativeDao().queryBusinessCount(business);
	}

	@Override
	public List<Business> queryBusinessList(Business business, int curNum, int pageNum) {
		return getNetCooperativeDao().queryBusinessList(business, curNum, pageNum);
	}

	// 添加
	@Override
	public int insertBusiness(Business business) throws CoreException {
		// 将操作用户设置成操作员的姓名
		String operator = business.getOperator();
		ApUser user = apUserDao.queryApUserByUserCode(operator);
		String userName = user.getUserName();
		business.setCrtUser(userName);
		business.setLstUpdUser(userName);
		return getNetCooperativeDao().insertBusiness(business);
	}

	// 修改
	@Override
	public int updateBusiness(Business business) throws CoreException {
		// 将操作用户设置成操作员的姓名
		String operator = business.getOperator();
		ApUser user = apUserDao.queryApUserByUserCode(operator);
		String userName = user.getUserName();
		business.setCrtUser(userName);
		business.setLstUpdUser(userName);
		if(business.getStatus() == "2" || "2".equals(business.getStatus())){
			business.setBlockingSwitch("0");
		}
		return getNetCooperativeDao().updateBusiness(business);

	}

	// 删除
	@Override
	public int deleteBusiness(Business business) {
		return getNetCooperativeDao().deleteBusiness(business);
	}

	// 批量设置
	@Override
	public int BusinesssetStatusOK(Business business) throws CoreException {
		// 将操作用户设置成操作员的姓名
		String operator = business.getOperator();
		ApUser user = apUserDao.queryApUserByUserCode(operator);
		String userName = user.getUserName();
		business.setCrtUser(userName);
		business.setLstUpdUser(userName);
		return getNetCooperativeDao().BusinesssetStatusOK(business);
	}

	@Override
	public int BusinesssetStatusNO(Business business) throws CoreException {
		// 将操作用户设置成操作员的姓名
		String operator = business.getOperator();
		ApUser user = apUserDao.queryApUserByUserCode(operator);
		String userName = user.getUserName();
		business.setCrtUser(userName);
		business.setLstUpdUser(userName);
		return getNetCooperativeDao().BusinesssetStatusNO(business);

	}

	//唯一校验
	@Override
	public Business queryBusinessOnly(Business business) {
		return getNetCooperativeDao().queryBusinessOnly(business);
	}

	//上传
	@Override
	public int insertBusinessFromFile(List<Business> list, BatchfileInfo batchfileInfo) {
		int inputCounts = netCooperativeDao.insertBusiness(list);
		//添加导入记录
		batchfileInfo.setInputCounts(new BigDecimal(inputCounts));
		batchFileInfoDao.insert(batchfileInfo);
		return inputCounts;
	}

	@Override
	public List<Map<String, String>> queryAllNet() throws CoreException {
		return getNetCooperativeDao().queryAllNet();
	}

}
