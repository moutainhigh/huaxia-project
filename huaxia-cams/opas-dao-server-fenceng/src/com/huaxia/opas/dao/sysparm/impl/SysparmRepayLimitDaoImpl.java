package com.huaxia.opas.dao.sysparm.impl;

import java.util.List;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.domain.sysparm.SysparmRepayLimit;
import com.huaxia.opas.dao.sysparm.SysparmRepayLimitDao;

/**
 * 最低还款额维护DAO层实现类
 * @author liudi
 * @since 2017-03-20
 * @version 1.0
 */
public class SysparmRepayLimitDaoImpl extends AbstractDAO implements SysparmRepayLimitDao{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4939200780868651511L;
	/**
	 * 
	 */
	private static final String NAMESPACES = "SysparmRepayLimit.";
	//添加
	@Override
	public int saveSysparmRepayLimit(SysparmRepayLimit sysparmRepayLimit) throws CoreException {
		return getSqlMap().insert(NAMESPACES + "insertSysparmRepayLimit", sysparmRepayLimit);
	}
	//修改
	@Override
	public int updateSysparmRepayLimit(SysparmRepayLimit sysparmRepayLimit) throws CoreException {
		return getSqlMap().update(NAMESPACES + "updateSysparmRepayLimit", sysparmRepayLimit);
	}
	//删除
	@Override
	public int deleteSysparmRepayLimit(String autoId) throws CoreException {
		return getSqlMap().delete(NAMESPACES + "deleteSysparmRepayLimitById", autoId);
	}
	//全部删除
	@Override
	public int deleteAllSysparmRepayLimit() throws CoreException {
		return getSqlMap().delete(NAMESPACES + "deleteAllSysparmRepayLimit");
	}
	//最低还款额件数查询
	@Override
	public int querySysparmRepayLimitCount(SysparmRepayLimit sysparmRepayLimit) throws CoreException {
		return getSqlMap().queryForObject(NAMESPACES + "querySysparmRepayLimitCount", sysparmRepayLimit);
	}
	//最低还款额信息查询
	@Override
	public List<SysparmRepayLimit> querySysparmRepayLimit(SysparmRepayLimit sysparmRepayLimit,int curNum,int pageNum) throws CoreException {
		return getSqlMap().queryForList(NAMESPACES + "querySysparmRepayLimit1", sysparmRepayLimit, curNum, pageNum);
	}
	//最低还款额代码是否重复
	@Override
	public SysparmRepayLimit querySysparmRepayLimit(SysparmRepayLimit sysparmRepayLimit) throws CoreException {
		return getSqlMap().queryForObject(NAMESPACES + "querySysparmRepayLimit2", sysparmRepayLimit);
	}

}
