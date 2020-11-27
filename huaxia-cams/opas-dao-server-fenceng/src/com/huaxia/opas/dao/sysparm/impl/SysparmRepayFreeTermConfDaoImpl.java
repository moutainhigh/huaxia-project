package com.huaxia.opas.dao.sysparm.impl;

import java.util.List;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.domain.sysparm.SysparmRepayFreeTermConf;
import com.huaxia.opas.dao.sysparm.SysparmRepayFreeTermConfDao;

/**
 * 免息还款期配置DAO层实现类
 * @author liudi
 * @since 2017-03-21
 * @version 1.0
 */
public class SysparmRepayFreeTermConfDaoImpl extends AbstractDAO implements SysparmRepayFreeTermConfDao{


	/**
	 * 
	 */
	private static final long serialVersionUID = -7605288127933583798L;

	/**
	 * 
	 */
	private static final String NAMESPACES = "SysparmRepayFreeTermConf.";
	//添加
	@Override
	public int saveSysparmRepayFreeTermConf(SysparmRepayFreeTermConf sysparmRepayFreeTermConf) throws CoreException {
		return getSqlMap().insert(NAMESPACES + "insertSysparmRepayFreeTermConf", sysparmRepayFreeTermConf);
	}
	//修改
	@Override
	public int updateSysparmRepayFreeTermConf(SysparmRepayFreeTermConf sysparmRepayFreeTermConf) throws CoreException {
		return getSqlMap().update(NAMESPACES + "updateSysparmRepayFreeTermConf", sysparmRepayFreeTermConf);
	}
	//删除
	@Override
	public int deleteSysparmRepayFreeTermConf(String autoId) throws CoreException {
		return getSqlMap().delete(NAMESPACES + "deleteSysparmRepayFreeTermConfById", autoId);
	}
	//全部删除
	@Override
	public int deleteAllSysparmRepayFreeTermConf() throws CoreException {
		return getSqlMap().delete(NAMESPACES + "deleteAllSysparmRepayFreeTermConf");
	}
	//免息还款期配置件数查询
	@Override
	public int querySysparmRepayFreeTermConfCount(SysparmRepayFreeTermConf sysparmRepayFreeTermConf) throws CoreException {
		return getSqlMap().queryForObject(NAMESPACES + "querySysparmRepayFreeTermConfCount", sysparmRepayFreeTermConf);
	}
	//免息还款期配置信息查询
	@Override
	public List<SysparmRepayFreeTermConf> querySysparmRepayFreeTermConf(SysparmRepayFreeTermConf sysparmRepayFreeTermConf,int curNum,int pageNum) throws CoreException {
		return getSqlMap().queryForList(NAMESPACES + "querySysparmRepayFreeTermConf1", sysparmRepayFreeTermConf, curNum, pageNum);
	}
	//查询免息还款期免息还款期代码是否存在
	@Override
	public SysparmRepayFreeTermConf querySysparmRepayFreeTermConf1(SysparmRepayFreeTermConf sysparmRepayFreeTermConf) throws CoreException {
		return getSqlMap().queryForObject(NAMESPACES + "querySysparmRepayFreeTermConf2", sysparmRepayFreeTermConf);
	}
	//查询免息还款期客户分层结果标签是否重复
	@Override
	public SysparmRepayFreeTermConf querySysparmRepayFreeTermConf2(SysparmRepayFreeTermConf sysparmRepayFreeTermConf) throws CoreException {
		return getSqlMap().queryForObject(NAMESPACES + "querySysparmRepayFreeTermConf3", sysparmRepayFreeTermConf);
	}
	//查询免息还款期代码存在情况下，状态是否开启
	@Override
	public SysparmRepayFreeTermConf querySysparmRepayFreeTermConf3(SysparmRepayFreeTermConf sysparmRepayFreeTermConf) throws CoreException {
		return getSqlMap().queryForObject(NAMESPACES + "querySysparmRepayFreeTermConf4", sysparmRepayFreeTermConf);
	}
	//自动归档查询免息还款期代码
	@Override
	public SysparmRepayFreeTermConf querySysparmRepayFreeTermConf5(SysparmRepayFreeTermConf sysparmRepayFreeTermConf) throws CoreException {
		return getSqlMap().queryForObject(NAMESPACES + "querySysparmRepayFreeTermConf5", sysparmRepayFreeTermConf);
	}
}
