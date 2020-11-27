package com.huaxia.opas.service.collect.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.common.SysConst;
import com.huaxia.opas.dao.collect.EnhanceCollectDao;
import com.huaxia.opas.dao.collect.SalaryAdoptItemDao;
import com.huaxia.opas.dao.collect.SalaryComputeDao;
import com.huaxia.opas.domain.collect.EnhanceBlazeResult;
import com.huaxia.opas.domain.collect.EnhanceInfo;
import com.huaxia.opas.domain.collect.SalaryAdoptItem;
import com.huaxia.opas.domain.collect.SalaryCompute;
import com.huaxia.opas.service.collect.EnhanceCollectService;

/**
 * 增强信息采集服务实现类
 * 
 * @author zhiguo.li
 *
 */
public class EnhanceCollectServiceImpl implements EnhanceCollectService, Serializable {

	private static final long serialVersionUID = -1089221552301260145L;

	@Resource(name = "enhanceCollectDao")
	private EnhanceCollectDao enhanceCollectDao;

	@Resource(name = "salaryComputeDao")
	private SalaryComputeDao salaryComputeDao;

	@Resource(name = "salaryAdoptItemDao")
	private SalaryAdoptItemDao salaryAdoptItemDao;

	@Override
	public int save(EnhanceInfo enhanceInfo) throws CoreException {
		return getEnhanceCollectDao().insert(enhanceInfo);
	}

	@Override
	public int remove(EnhanceInfo enhanceInfo) throws CoreException {
		return getEnhanceCollectDao().delete(enhanceInfo);
	}

	@Override
	public int update(EnhanceInfo enhanceInfo) throws CoreException {
		return getEnhanceCollectDao().update(enhanceInfo);
	}

	@Override
	public EnhanceInfo get(EnhanceInfo enhanceInfo) {
		return getEnhanceCollectDao().selectById(enhanceInfo);
	}

	@Override
	public EnhanceInfo getByAppId(String appId) {
		return getEnhanceCollectDao().selectByAppId(appId);
	}

	public EnhanceCollectDao getEnhanceCollectDao() {
		return enhanceCollectDao;
	}

	public void setEnhanceCollectDao(EnhanceCollectDao enhanceCollectDao) {
		this.enhanceCollectDao = enhanceCollectDao;
	}

	public SalaryComputeDao getSalaryComputeDao() {
		return salaryComputeDao;
	}

	public void setSalaryComputeDao(SalaryComputeDao salaryComputeDao) {
		this.salaryComputeDao = salaryComputeDao;
	}

	public SalaryAdoptItemDao getSalaryAdoptItemDao() {
		return salaryAdoptItemDao;
	}

	public void setSalaryAdoptItemDao(SalaryAdoptItemDao salaryAdoptItemDao) {
		this.salaryAdoptItemDao = salaryAdoptItemDao;
	}

	@Override
	public int updateBlazeResult(EnhanceBlazeResult blaze) {
		return getEnhanceCollectDao().updateEnhanceInfoForBlaze(blaze);
	}

	@Override
	public int saveAll(Map<String, Object> objects) throws CoreException {
		int result = -1;
		if (objects == null) {
			return result;
		}
		
		if (objects.containsKey(SysConst.YDJ_OBJECT_ENHANCE)) {
			EnhanceInfo enhance = (EnhanceInfo) objects.get(SysConst.YDJ_OBJECT_ENHANCE);
			save(enhance);
			result = 1;
		}
		
		if (objects.containsKey(SysConst.YDJ_OBJECT_ADOPT)) {
			SalaryAdoptItem adopt = (SalaryAdoptItem) objects.get(SysConst.YDJ_OBJECT_ADOPT);
			getSalaryAdoptItemDao().insert(adopt);
			result = 2;
		}
		
		if (objects.containsKey(SysConst.YDJ_OBJECT_COMPUTE)) {
			SalaryCompute compute = (SalaryCompute) objects.get(SysConst.YDJ_OBJECT_COMPUTE);
			getSalaryComputeDao().insert(compute);
			result = 4;
		}
		
		return result;
	}

	@Override
	public int mergeAll(Map<String, Object> objects) throws CoreException {
		int result = -1;
		if (objects == null) {
			return result;
		}
		
		EnhanceInfo enhance = null;
		if (objects.containsKey(SysConst.YDJ_OBJECT_ENHANCE)) {
			enhance = (EnhanceInfo) objects.get(SysConst.YDJ_OBJECT_ENHANCE);
			update(enhance);
			result = 1;
		}
		
		if (enhance != null) {
			// 收入可采纳项
			if (objects.containsKey(SysConst.YDJ_OBJECT_ADOPT)) {
				SalaryAdoptItem adopt = (SalaryAdoptItem) objects.get(SysConst.YDJ_OBJECT_ADOPT);
				
				SalaryAdoptItem adoptParam = new SalaryAdoptItem();
				adoptParam.setAdoptItemId(adopt.getAdoptItemId());
				SalaryAdoptItem adoptEntity = getSalaryAdoptItemDao().selectById(adoptParam);
				
				if (adoptEntity != null && adopt.getAdoptItemId().equals(adoptEntity.getAdoptItemId())) {
					getSalaryAdoptItemDao().update(adopt);
					result = 22;
				} else {
					getSalaryAdoptItemDao().insert(adopt);
					result = 21;
				}
			} else {
				String appId = enhance.getAppId();
				getSalaryAdoptItemDao().deleteByAppId(appId);
				getEnhanceCollectDao().updateForeignKey(appId);
			}
			
			// 收入计算
			if (objects.containsKey(SysConst.YDJ_OBJECT_COMPUTE)) {
				SalaryCompute compute = (SalaryCompute) objects.get(SysConst.YDJ_OBJECT_COMPUTE);
				
				SalaryCompute computeEntity = getSalaryComputeDao().selectByAppId(enhance.getAppId());
				if (computeEntity == null) {
					getSalaryComputeDao().insert(compute);
					result = 41;
				} else {
					getSalaryComputeDao().update(compute);
					result = 42;
				}
			}
		}
		
		return result;
	}

	@Override
	public Date selectEntryYearByAppId(String appId) {
		return getEnhanceCollectDao().selectEntryYearByAppId(appId);
	}

	@Override
	public int selectC1CoyrByAppId(String appId) {
		return getEnhanceCollectDao().selectC1CoyrByAppId(appId);
	}

	@Override
	public String getRequestTypeByAppId(String appId) {
		return enhanceCollectDao.selectRequestTypeByAppId(appId);
	}

}
