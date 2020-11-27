package com.huaxia.opas.dao.sysparm.impl;

import java.util.List;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.domain.sysparm.SysparmRateConf;
import com.huaxia.opas.dao.sysparm.SysparmRateConfDao;

/**
 * 利率差异化参数配置DAO层实现类
 * @author liudi
 * @since 2017-03-17
 * @version 1.0
 */
public class SysparmRateConfDaoImpl extends AbstractDAO implements SysparmRateConfDao{


	/**
	 * 
	 */
	private static final long serialVersionUID = 4180128849253050315L;
	/**
	 * 
	 */
	private static final String NAMESPACES = "SysparmRateConf.";
	//添加
	@Override
	public int saveSysparmRateConf(SysparmRateConf sysparmRateConf) throws CoreException {
		return getSqlMap().insert(NAMESPACES + "insertSysparmRateConf", sysparmRateConf);
	}
	//修改
	@Override
	public int updateSysparmRateConf(SysparmRateConf sysparmRateConf) throws CoreException {
		return getSqlMap().update(NAMESPACES + "updateSysparmRateConf", sysparmRateConf);
	}
	//删除
	@Override
	public int deleteSysparmRateConf(String autoId) throws CoreException {
		return getSqlMap().delete(NAMESPACES + "deleteSysparmRateConfById", autoId);
	}
	//全部删除
	@Override
	public int deleteAllSysparmRateConf() throws CoreException {
		return getSqlMap().delete(NAMESPACES + "deleteAllSysparmRateConf");
	}
	//利率差异化配置件数查询
	@Override
	public int querySysparmRateConfCount(SysparmRateConf sysparmRateConf) throws CoreException {
		return getSqlMap().queryForObject(NAMESPACES + "querySysparmRateConfCount", sysparmRateConf);
	}
	//利率差异化配置信息查询
	@Override
	public List<SysparmRateConf> querySysparmRateConf(SysparmRateConf sysparmRateConf,int curNum,int pageNum) throws CoreException {
		return getSqlMap().queryForList(NAMESPACES + "querySysparmRateConf1", sysparmRateConf, curNum, pageNum);
	}
	//查询利率代码是否存在
	@Override
	public SysparmRateConf querySysparmRateConf1(SysparmRateConf sysparmRateConf) throws CoreException {
		return getSqlMap().queryForObject(NAMESPACES + "querySysparmRateConf2", sysparmRateConf);
	}
	//查询利率客户分层结果标签是否重复
	@Override
	public SysparmRateConf querySysparmRateConf2(SysparmRateConf sysparmRateConf) throws CoreException {
		return getSqlMap().queryForObject(NAMESPACES + "querySysparmRateConf3", sysparmRateConf);
	}
	//查询利率代码存在情况下，状态是否开启
	@Override
	public SysparmRateConf querySysparmRateConf3(SysparmRateConf sysparmRateConf) throws CoreException {
		return getSqlMap().queryForObject(NAMESPACES + "querySysparmRateConf4", sysparmRateConf);
	}
	//自动查询利率代码
	@Override
	public SysparmRateConf querySysparmRateConf5(SysparmRateConf sysparmRateConf) throws CoreException {
		return getSqlMap().queryForObject(NAMESPACES + "querySysparmRateConf5", sysparmRateConf);
	}
}
