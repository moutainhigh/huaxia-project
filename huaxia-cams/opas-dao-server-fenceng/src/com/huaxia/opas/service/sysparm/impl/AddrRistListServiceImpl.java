package com.huaxia.opas.service.sysparm.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.sysparm.AddrRiskListDao;
import com.huaxia.opas.dao.sysparm.BatchFileInfoDao;
import com.huaxia.opas.domain.riskcheck.AddrRiskList;
import com.huaxia.opas.domain.sysparm.BatchfileInfo;
import com.huaxia.opas.service.sysparm.AddrRiskListService;

public class AddrRistListServiceImpl extends AbstractDAO implements AddrRiskListService {

	private static final long serialVersionUID = 5757582418545513970L;
	
	@Resource(name = "addrRiskListDao")
	private AddrRiskListDao addrRiskListDao;
	
	@Resource(name="batchFileInfoDao")
	private BatchFileInfoDao batchFileInfoDao;
	
	public AddrRiskListDao getAddrRiskListDao() {
		return addrRiskListDao;
	}

	public void setAddrRiskListDao(AddrRiskListDao addrRiskListDao) {
		this.addrRiskListDao = addrRiskListDao;
	}

	@Override
	public int insert(AddrRiskList addrrisklist) throws CoreException {
		return getAddrRiskListDao().insert(addrrisklist);
	}

	@Override
	public int insertSelective(AddrRiskList addrrisklist) throws CoreException {
		return getAddrRiskListDao().insertSelective(addrrisklist);
	}

	@Override
	public int deleteByPrimaryKey(String autoId) throws CoreException {
		return getAddrRiskListDao().deleteByPrimaryKey(autoId);
	}

	@Override
	public int updateByPrimaryKey(AddrRiskList addrrisklist) throws CoreException {
		return getAddrRiskListDao().updateByPrimaryKey(addrrisklist);
	}

	@Override
	public int updateByPrimaryKeySelective(AddrRiskList addrrisklist) throws CoreException {
		return getAddrRiskListDao().updateByPrimaryKeySelective(addrrisklist);
	}

	@Override
	public AddrRiskList selectByPrimaryKey(String autoId) throws CoreException {
		return getAddrRiskListDao().selectByPrimaryKey(autoId);
	}
	
	//shihuan 2017-03-03 地址风险名单列表查询 
	@Override
	public List<AddrRiskList> queryAddrRistList(AddrRiskList addrrisklist, int curNum, int pageNum) {
		return getAddrRiskListDao().queryAddrRistList(addrrisklist, curNum, pageNum);
	}

	//shihuan 2017-03-03 地址风险名单列表查询 条数
	@Override
	public int queryAddrRistCount(AddrRiskList addrrisklist) {
		return getAddrRiskListDao().queryAddrRistCount(addrrisklist);
	}
	
	//shihuan 2017-03-05  地址风险名单批量启动停用修改状态
	@Override
	public int updateStartStatus(String autoId) throws CoreException {
		return getAddrRiskListDao().updateStartStatus(autoId);
	}
	
	//shihuan 2017-03-05  地址风险名单批量启动停用修改状态
	@Override
	public int updateStopStatus(String autoId) throws CoreException {
		return getAddrRiskListDao().updateStopStatus(autoId);
	}
	
	//shihuan 2017-03-18 地址风险名单批导入文件
	@Override
	public int insertAddrUpload(List<AddrRiskList> obj,BatchfileInfo batchfileInfo) throws CoreException {
		int inputCounts = addrRiskListDao.insertAddrUpload(obj);
		batchfileInfo.setInputCounts(new BigDecimal(inputCounts));
		batchFileInfoDao.insertRist(batchfileInfo);
		return inputCounts;
	}
}
