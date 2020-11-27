package com.huaxia.opas.dao.sysparm.impl;

import java.util.List;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.domain.sysparm.SysparmRepayLimitConf;
import com.huaxia.opas.dao.sysparm.SysparmRepayLimitConfDao;

/**
 * 最低还款额配置DAO层实现类
 * @author liudi
 * @since 2017-03-20
 * @version 1.0
 */
public class SysparmRepayLimitConfDaoImpl extends AbstractDAO implements SysparmRepayLimitConfDao{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8942397238024063996L;
	/**
	 * 
	 */
	private static final String NAMESPACES = "SysparmRepayLimitConf.";
	//添加
	@Override
	public int saveSysparmRepayLimitConf(SysparmRepayLimitConf sysparmRepayLimitConf) throws CoreException {
		return getSqlMap().insert(NAMESPACES + "insertSysparmRepayLimitConf", sysparmRepayLimitConf);
	}
	//修改
	@Override
	public int updateSysparmRepayLimitConf(SysparmRepayLimitConf sysparmRepayLimitConf) throws CoreException {
		return getSqlMap().update(NAMESPACES + "updateSysparmRepayLimitConf", sysparmRepayLimitConf);
	}
	//删除
	@Override
	public int deleteSysparmRepayLimitConf(String autoId) throws CoreException {
		return getSqlMap().delete(NAMESPACES + "deleteSysparmRepayLimitConfById", autoId);
	}
	//全部删除
	@Override
	public int deleteAllSysparmRepayLimitConf() throws CoreException {
		return getSqlMap().delete(NAMESPACES + "deleteAllSysparmRepayLimitConf");
	}
	//最低还款额配置件数查询
	@Override
	public int querySysparmRepayLimitConfCount(SysparmRepayLimitConf sysparmRepayLimitConf) throws CoreException {
		return getSqlMap().queryForObject(NAMESPACES + "querySysparmRepayLimitConfCount", sysparmRepayLimitConf);
	}
	//最低还款额配置信息查询
	@Override
	public List<SysparmRepayLimitConf> querySysparmRepayLimitConf(SysparmRepayLimitConf sysparmRepayLimitConf,int curNum,int pageNum) throws CoreException {
		return getSqlMap().queryForList(NAMESPACES + "querySysparmRepayLimitConf1", sysparmRepayLimitConf, curNum, pageNum);
	}
	//查询最低还款额比例代码是否存在
	@Override
	public SysparmRepayLimitConf querySysparmRepayLimitConf1(SysparmRepayLimitConf sysparmRepayLimitConf) throws CoreException {
		return getSqlMap().queryForObject(NAMESPACES + "querySysparmRepayLimitConf2", sysparmRepayLimitConf);
	}
	//查询最低还款额客户分层结果标签是否重复
	@Override
	public SysparmRepayLimitConf querySysparmRepayLimitConf2(SysparmRepayLimitConf sysparmRepayLimitConf) throws CoreException {
		return getSqlMap().queryForObject(NAMESPACES + "querySysparmRepayLimitConf3", sysparmRepayLimitConf);
	}
	//查询最低还款额比例代码存在情况下，状态是否开启
	@Override
	public SysparmRepayLimitConf querySysparmRepayLimitConf3(SysparmRepayLimitConf sysparmRepayLimitConf) throws CoreException {
		return getSqlMap().queryForObject(NAMESPACES + "querySysparmRepayLimitConf4", sysparmRepayLimitConf);
	}
	//自动归档查询最低还款额比例代码
	@Override
	public SysparmRepayLimitConf querySysparmRepayLimitConf5(SysparmRepayLimitConf sysparmRepayLimitConf) throws CoreException {
		return getSqlMap().queryForObject(NAMESPACES + "querySysparmRepayLimitConf5", sysparmRepayLimitConf);
	}
}
