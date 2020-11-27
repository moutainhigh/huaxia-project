package com.huaxia.opas.dao.allot.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.allot.AllotCommonDao;
import com.huaxia.opas.domain.allot.AllotApply;
import com.huaxia.opas.domain.allot.AllotCommon;
import com.huaxia.opas.domain.allot.AllotQueue;

public class AllotCommonDaoImpl extends AbstractDAO implements AllotCommonDao {

	private static final long serialVersionUID = -8737375965899169233L;
	private static final String NAMESPACES = "AllotCommon.";
	private static final String QUEUESPACES = "AllotQueue.";
	@Override
	public List<AllotCommon> selectAllotGroup(Map map) throws CoreException {
		return getSqlMap().queryForList(NAMESPACES+"selectAllotGroup",map);
	}
	
	@Override
	public int countAllotGroup(Map map) throws CoreException {
		return getSqlMap().queryForObject(NAMESPACES + "countAllotGroup",map);
	}
	
	@Override
	public List<AllotCommon> selectAllotGroup(Map map,int currentPage, int pageSize) throws CoreException {
		return getSqlMap().queryForList(NAMESPACES+"selectAllotGroup",map,currentPage,pageSize);
	}
	
	@Override
	public AllotCommon selectGroupByUserCode(String userCode) throws CoreException {
		AllotCommon allotCommon=new AllotCommon();
		allotCommon=getSqlMap().queryForObject(NAMESPACES + "selectGroupByUserCode",userCode);
		return allotCommon;
	}
	
	@Override
	public List<String> selectUserRoleByOrgId(String orgId) throws CoreException {
		return getSqlMap().queryForList(NAMESPACES + "selectUserRoleByOrgId",orgId);
	}
	
	@Override
	public int countUserRoleByCode(String orgId) throws CoreException {
		return getSqlMap().queryForObject(NAMESPACES + "countUserRoleByCode",orgId);
	}
	

	@Override
	public List<String> selectUserIdByOrgId(String orgId) throws CoreException {
		List<String> userIdList=new ArrayList<String>();
		return userIdList=getSqlMap().queryForList(NAMESPACES + "selectUserIdByOrgId",orgId);
	}
	
	@Override
	public List<AllotCommon> selectAllotUserByList(List<String> list) throws CoreException {
		return getSqlMap().queryForList(NAMESPACES + "selectAllotUserByList",list);
	}
	
	@Override
	public AllotCommon selectGroupByOrgNo(String orgNo) throws CoreException {
		return getSqlMap().queryForObject(NAMESPACES + "selectGroupByOrgNo",orgNo);
	}
	
	//  查询组员信息 
	@Override
	public List<String> selectRoleCodeByUserId(String userId) throws CoreException {
		List<String> userIdList=new ArrayList<String>();
		return userIdList=getSqlMap().queryForList(NAMESPACES + "selectRoleCodeByUserId",userId);
	}
	
	@Override
	public AllotCommon selectUser(Map map) throws CoreException {
		return getSqlMap().queryForObject(NAMESPACES + "selectUser",map);
	}

	@Override
	public int updateAutoStatus(AllotCommon ac) throws CoreException {
		return getSqlMap().update(NAMESPACES + "updateAutoStatus", ac);
	}
	
	@Override
	public int selectCountShen(Map map) throws CoreException {
		return getSqlMap().queryForObject(NAMESPACES + "selectCountShen",map);
	}
	
	@Override
	public List<AllotCommon> selectApprovers(Map map) throws CoreException {
		return getSqlMap().queryForList(NAMESPACES + "selectApprovers",map);
	}
	
	@Override
	public List<AllotCommon> selectSpecialMen(Map map) throws CoreException {
		return getSqlMap().queryForList(NAMESPACES+"selectSpecialMen",map);
	}
	
	@Override
	public List<AllotQueue> selectCreditDict(String str,int currentPage, int pageSize) throws CoreException {
		return getSqlMap().queryForList(QUEUESPACES + "selectCreditDict",str,(currentPage -1) * pageSize, pageSize);
	}
	
	@Override
	public int countCreditDict() throws CoreException {
		 return getSqlMap().queryForObject(NAMESPACES + "countCreditDict");
	}
	
	@Override
	public List<AllotCommon> selectGroupByMap(Map map) throws CoreException {
		return getSqlMap().queryForList(NAMESPACES+"selectGroupByMap",map);
	}
}