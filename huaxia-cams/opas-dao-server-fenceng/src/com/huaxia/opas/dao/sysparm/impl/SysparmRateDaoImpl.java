package com.huaxia.opas.dao.sysparm.impl;

import java.util.List;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.domain.sysparm.SysparmRate;
import com.huaxia.opas.dao.sysparm.SysparmRateDao;

/**
 * 利率差异化维护DAO层实现类
 * @author liudi
 * @since 2017-03-17
 * @version 1.0
 */
public class SysparmRateDaoImpl extends AbstractDAO implements SysparmRateDao{


	/**
	 * 
	 */
	private static final long serialVersionUID = -1256525498301519344L;
	/**
	 * 
	 */
	private static final String NAMESPACES = "SysparmRate.";
	//添加
	@Override
	public int saveSysparmRate(SysparmRate sysparmRate) throws CoreException {
		return getSqlMap().insert(NAMESPACES + "insertSysparmRate", sysparmRate);
	}
	//修改
	@Override
	public int updateSysparmRate(SysparmRate sysparmRate) throws CoreException {
		return getSqlMap().update(NAMESPACES + "updateSysparmRate", sysparmRate);
	}
	//删除
	@Override
	public int deleteSysparmRate(String autoId) throws CoreException {
		return getSqlMap().delete(NAMESPACES + "deleteSysparmRateById", autoId);
	}
	//全部删除
	@Override
	public int deleteAllSysparmRate() throws CoreException {
		return getSqlMap().delete(NAMESPACES + "deleteAllSysparmRate");
	}
	//利率差异化件数查询
	@Override
	public int querySysparmRateCount(SysparmRate sysparmRate) throws CoreException {
		return getSqlMap().queryForObject(NAMESPACES + "querySysparmRateCount", sysparmRate);
	}
	//利率差异化信息查询
	@Override
	public List<SysparmRate> querySysparmRate(SysparmRate sysparmRate,int curNum,int pageNum) throws CoreException {
		return getSqlMap().queryForList(NAMESPACES + "querySysparmRate1", sysparmRate, curNum, pageNum);
	}
	//查询利率差异化代码是否重复
	@Override
	public SysparmRate querySysparmRate(SysparmRate sysparmRate) throws CoreException {
		return getSqlMap().queryForObject(NAMESPACES + "querySysparmRate2", sysparmRate);
	}

}
