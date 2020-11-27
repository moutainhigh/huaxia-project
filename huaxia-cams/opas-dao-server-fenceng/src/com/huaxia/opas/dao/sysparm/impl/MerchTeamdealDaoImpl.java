package com.huaxia.opas.dao.sysparm.impl;

import java.util.List;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.sysparm.MerchTeamdealDao;
import com.huaxia.opas.domain.decision.MerchTeamdealList;

/**
 *名单库管理_征信电话核查白名单dao层实现类的相关方法
 * 
 * @author luzhen.ou
 * 
 * @since 2017-3-18
 * 
 * @version 1.0
 */
public class MerchTeamdealDaoImpl extends AbstractDAO implements MerchTeamdealDao {
	
	private static final long serialVersionUID = -4207650765650515131L;
	
	private static final String NAMESPACES = "MerchTeamdealList.";

	@Override
	public int updateMerchTeamdeal(MerchTeamdealList merchTeamdealList) throws CoreException {
		return getSqlMap().update(NAMESPACES + "updateMerchTeamdeal", merchTeamdealList);
	}

	@Override
	public int queryMerchTeamdealCount(MerchTeamdealList merchTeamdealList) throws CoreException{
		return getSqlMap().queryForObject(NAMESPACES + "queryMerchTeamdealCount", merchTeamdealList);
	}

	@Override
	public List<MerchTeamdealList> queryMerchTeamdealList(MerchTeamdealList merchTeamdealList, int curNum, int pageNum) throws CoreException{
		return getSqlMap().queryForList(NAMESPACES + "queryMerchTeamdealList", merchTeamdealList, curNum, pageNum);
	}

	@Override
	public int deleteMerchTeamdeal(MerchTeamdealList merchTeamdealList) throws CoreException{
		return getSqlMap().delete(NAMESPACES + "deleteMerchTeamdeal", merchTeamdealList);
	}

	@Override
	public MerchTeamdealList queryMerchTeamdeal(MerchTeamdealList merchTeamdealList) throws CoreException {
		return (MerchTeamdealList)getSqlMap().queryForObject(NAMESPACES + "queryMerchTeamdeal", merchTeamdealList);
	}

	@Override
	public int updateMerchTeamdealActive(MerchTeamdealList merchTeamdealList) throws CoreException {
		return getSqlMap().update(NAMESPACES + "updateMerchTeamdealActive", merchTeamdealList);
	}

	@Override
	public int queryMerchTeamdealHistoryCount(MerchTeamdealList merchTeamdealList) throws CoreException {
		return getSqlMap().queryForObject(NAMESPACES + "queryMerchTeamdealHistoryCount", merchTeamdealList);
	}

	@Override
	public List<MerchTeamdealList> queryMerchTeamdealHistoryList(MerchTeamdealList merchTeamdealList, int curNum, int pageNum){
		return getSqlMap().queryForList(NAMESPACES + "queryMerchTeamdealHistoryList", merchTeamdealList, curNum, pageNum);
	}
	
	@Override
	public int insertMerchTeamdeal(MerchTeamdealList merchTeamdealList) throws CoreException {
		return getSqlMap().insert(NAMESPACES + "insertMerchTeamdeal", merchTeamdealList);
	}

	@Override
	public int insertMerchTeamdealList(List<MerchTeamdealList> list) throws CoreException {
		return getSqlMap().insert(NAMESPACES + "insertMerchTeamdealList", list);
	}
	
	@Override
	public int insertMerchTeamdealHistory(MerchTeamdealList merchTeamdealList) throws CoreException {
		return getSqlMap().insert(NAMESPACES + "insertMerchTeamdealHistory", merchTeamdealList);
	}

	@Override
	public int insertMerchTeamdealHistoryList(List<MerchTeamdealList> list)  throws CoreException{
		return getSqlMap().insert(NAMESPACES + "insertMerchTeamdealHistoryList", list);
	}
	
}
