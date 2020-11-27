package com.huaxia.opas.dao.sysparm.impl;

import java.util.List;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.domain.sysparm.SysparmRepayFreeTerm;
import com.huaxia.opas.dao.sysparm.SysparmRepayFreeTermDao;

/**
 * 免息还款期维护DAO层实现类
 * @author liudi
 * @since 2017-03-21
 * @version 1.0
 */
public class SysparmRepayFreeTermDaoImpl extends AbstractDAO implements SysparmRepayFreeTermDao{


	/**
	 * 
	 */
	private static final long serialVersionUID = 4425355728746943665L;

	/**
	 * 
	 */
	private static final String NAMESPACES = "SysparmRepayFreeTerm.";
	//添加
	@Override
	public int saveSysparmRepayFreeTerm(SysparmRepayFreeTerm sysparmRepayFreeTerm) throws CoreException {
		return getSqlMap().insert(NAMESPACES + "insertSysparmRepayFreeTerm", sysparmRepayFreeTerm);
	}
	//修改
	@Override
	public int updateSysparmRepayFreeTerm(SysparmRepayFreeTerm sysparmRepayFreeTerm) throws CoreException {
		return getSqlMap().update(NAMESPACES + "updateSysparmRepayFreeTerm", sysparmRepayFreeTerm);
	}
	//删除
	@Override
	public int deleteSysparmRepayFreeTerm(String autoId) throws CoreException {
		return getSqlMap().delete(NAMESPACES + "deleteSysparmRepayFreeTermById", autoId);
	}
	//全部删除
	@Override
	public int deleteAllSysparmRepayFreeTerm() throws CoreException {
		return getSqlMap().delete(NAMESPACES + "deleteAllSysparmRepayFreeTerm");
	}
	//免息还款期件数查询
	@Override
	public int querySysparmRepayFreeTermCount(SysparmRepayFreeTerm sysparmRepayFreeTerm) throws CoreException {
		return getSqlMap().queryForObject(NAMESPACES + "querySysparmRepayFreeTermCount", sysparmRepayFreeTerm);
	}
	//免息还款期信息查询
	@Override
	public List<SysparmRepayFreeTerm> querySysparmRepayFreeTerm(SysparmRepayFreeTerm sysparmRepayFreeTerm,int curNum,int pageNum) throws CoreException {
		return getSqlMap().queryForList(NAMESPACES + "querySysparmRepayFreeTerm1", sysparmRepayFreeTerm, curNum, pageNum);
	}
	//免息还款期代码是否重复
	@Override
	public SysparmRepayFreeTerm querySysparmRepayFreeTerm(SysparmRepayFreeTerm sysparmRepayFreeTerm) throws CoreException {
		return getSqlMap().queryForObject(NAMESPACES + "querySysparmRepayFreeTerm2", sysparmRepayFreeTerm);
	}

}
