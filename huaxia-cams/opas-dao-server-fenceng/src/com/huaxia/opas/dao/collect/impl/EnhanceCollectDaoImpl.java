package com.huaxia.opas.dao.collect.impl;

import java.util.Date;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.collect.EnhanceCollectDao;
import com.huaxia.opas.domain.collect.EnhanceBlazeResult;
import com.huaxia.opas.domain.collect.EnhanceInfo;

/**
 * 增强信息采集接口实现类
 * 
 * @author zhiguo.li
 *
 */
public class EnhanceCollectDaoImpl extends AbstractDAO implements EnhanceCollectDao {

	private static final long serialVersionUID = 3894178603910492854L;
	
	private static final String NAMESPACES = "EnhanceCollect.";

	@Override
	public int insert(EnhanceInfo enhanceInfo) throws CoreException {
		return getSqlMap().insert(NAMESPACES + "insertEnhanceInfo", enhanceInfo);
	}

	@Override
	public int update(EnhanceInfo enhanceInfo) throws CoreException {
		return getSqlMap().update(NAMESPACES + "updateEnhanceInfo", enhanceInfo);
	}

	@Override
	public int delete(EnhanceInfo enhanceInfo) throws CoreException {
		return getSqlMap().delete(NAMESPACES + "deleteEnhanceInfo", enhanceInfo);
	}

	@Override
	public EnhanceInfo selectById(EnhanceInfo enhanceInfo) {
		return getSqlMap().queryForObject(NAMESPACES + "selectById", enhanceInfo);
	}

	@Override
	public EnhanceInfo selectByAppId(String appId) {
		return getSqlMap().queryForObject(NAMESPACES + "selectByAppId",  appId);
	}

	@Override
	public int updateEnhanceInfoForBlaze(EnhanceBlazeResult blaze) {
		return getSqlMap().update(NAMESPACES + "updateEnhanceInfoForBlaze",  blaze);
	}

	@Override
	public int updateForeignKey(String appId) {
		return getSqlMap().update(NAMESPACES + "updateForeignKey", appId);
	}

	@Override
	public Date selectEntryYearByAppId(String appId) {
		return getSqlMap().queryForObject(NAMESPACES+"selectEntryYearByAppId", appId);
	}

	@Override
	public int selectC1CoyrByAppId(String appId) {
		return getSqlMap().queryForObject(NAMESPACES+"selectC1CoyrByAppId", appId);
	}

	@Override
	public String selectRequestTypeByAppId(String appId) {
		return getSqlMap().queryForObject(NAMESPACES+"selectRequestTypeByAppId", appId);
	}

}
