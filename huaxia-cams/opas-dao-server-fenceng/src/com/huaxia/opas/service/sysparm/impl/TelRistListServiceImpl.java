package com.huaxia.opas.service.sysparm.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.sysparm.BatchFileInfoDao;
import com.huaxia.opas.dao.sysparm.TelRiskListDao;
import com.huaxia.opas.domain.riskcheck.TelRiskList;
import com.huaxia.opas.domain.sysparm.BatchfileInfo;
import com.huaxia.opas.service.sysparm.TelRiskListService;

public class TelRistListServiceImpl extends AbstractDAO implements TelRiskListService {

	private static final long serialVersionUID = 5757582418545513970L;
	
	@Resource(name = "telRiskListDao")
	private TelRiskListDao telRiskListDao;
	
	@Resource(name="batchFileInfoDao")
	private BatchFileInfoDao batchFileInfoDao;
	
	public TelRiskListDao getTelRiskListDao() {
		return telRiskListDao;
	}

	public void setTelRiskListDao(TelRiskListDao telRiskListDao) {
		this.telRiskListDao = telRiskListDao;
	}

	@Override
	public int insertTelRistList(List<TelRiskList> resutTelAddrlist) throws CoreException {
		return getTelRiskListDao().insertTelRistList(resutTelAddrlist);
	}

	@Override
	public int insertSelective(TelRiskList telrisklist) throws CoreException {
		return getTelRiskListDao().insertSelective(telrisklist);
	}

	@Override
	public int deleteByPrimaryKey(String autoId) throws CoreException {
		return getTelRiskListDao().deleteByPrimaryKey(autoId);
	}

	@Override
	public int updateByPrimaryKey(TelRiskList telrisklist) throws CoreException {
		return getTelRiskListDao().updateByPrimaryKey(telrisklist);
	}

	@Override
	public int updateByPrimaryKeySelective(TelRiskList telrisklist) throws CoreException {
		return getTelRiskListDao().updateByPrimaryKeySelective(telrisklist);
	}

	@Override
	public TelRiskList selectByPrimaryKey(String autoId) throws CoreException {
		return getTelRiskListDao().selectByPrimaryKey(autoId);
	}

	//shihuan 2017-03-15  电话风险名单列表查询 
	@Override
	public List<TelRiskList> queryTelRistList(TelRiskList telrisklist, int curNum, int pageNum) {
		return getTelRiskListDao().queryTelRistList(telrisklist, curNum, pageNum);
	}

	//shihuan 2017-03-15 电话风险名单列表查询 条数
	@Override
	public int queryTelRistCount(TelRiskList telrisklist) {
		return getTelRiskListDao().queryTelRistCount(telrisklist);
	}
	
	//shihuan 2017-03-15  电话风险名单批量启动停用修改状态
	@Override
	public int updateStartStatus(String autoId) throws CoreException {
		return getTelRiskListDao().updateStartStatus(autoId);
	}
	
	//shihuan 2017-03-15  电话风险名单批量启动停用修改状态
	@Override
	public int updateStopStatus(String autoId) throws CoreException {
		return getTelRiskListDao().updateStopStatus(autoId);
	}
	
	//shihuan 2017-03-18  电话风险名单批导入文件
	@Override
	public int insertTelRistUpload(List<TelRiskList> obj, BatchfileInfo batchfileInfo) throws CoreException {
		int inputCounts = telRiskListDao.insertTelRistUpload(obj);
		batchfileInfo.setInputCounts(new BigDecimal(inputCounts));
		batchFileInfoDao.insertRist(batchfileInfo);
		return inputCounts;
	}
	
	//新增修改时校验手机号是否添加重复
	@Override
	public int queryByMobileId(String mobileId) throws CoreException {
		return getTelRiskListDao().queryByMobileId(mobileId);
	}
	
	//新增修改时校验固话是否添加重复 
	@Override
	public int queryByCompanyTel(String companyTel) throws CoreException {
		return getTelRiskListDao().queryByCompanyTel(companyTel);
	}
	
	//新增修改时校验固话是否添加重复 
	@Override
	public int queryByLivingTel(String livingTel) throws CoreException {
		return getTelRiskListDao().queryByLivingTel(livingTel);
	}
	
	//新增修改时校验固话是否添加重复 
	@Override
	public int queryByOtherTel(String otherTel) throws CoreException {
		return getTelRiskListDao().queryByOtherTel(otherTel);
	}
}