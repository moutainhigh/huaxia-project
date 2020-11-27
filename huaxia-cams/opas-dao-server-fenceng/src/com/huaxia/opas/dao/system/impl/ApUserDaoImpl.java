package com.huaxia.opas.dao.system.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.system.ApUserDao;
import com.huaxia.opas.domain.system.ApUser;
import com.huaxia.opas.domain.system.FileRoleRelation;

public class ApUserDaoImpl extends AbstractDAO implements ApUserDao {

	private static final long serialVersionUID = 736293288782040004L;

	private static final String NAMESPACES = "ApUser.";

	@Override
	public int insertApUser(ApUser apUser) throws CoreException {
		return getSqlMap().insert(NAMESPACES + "insertApUser", apUser);
	}

	@Override
	public int insertApUserSelective(ApUser apUser) throws CoreException {
		return getSqlMap().insert(NAMESPACES + "insertApUserSelective", apUser);
	}

	@Override
	public int deleteApUserByUserId(String userId) throws CoreException {
		return getSqlMap().delete(NAMESPACES + "deleteApUserByUserId", userId);
	}

	@Override
	public int updateApUser(ApUser apUser) throws CoreException {
		return getSqlMap().update(NAMESPACES + "updateApUser", apUser);
	}

	@Override
	public int updateApUserSelective(ApUser apUser) throws CoreException {
		return getSqlMap().update(NAMESPACES + "updateApUserSelective", apUser);
	}

	@Override
	public ApUser queryApUserByUserId(String userId){
		return (ApUser) (getSqlMap().queryForObject(NAMESPACES + "queryApUserByUserId", userId));
	}

	@Override
	public ApUser queryApUserByUserCode(String userCode) throws CoreException {
		return (ApUser) (getSqlMap().queryForObject(NAMESPACES + "queryApUserByUserCode", userCode));
	}

	@Override
	public int queryUserCodeCount(String userCode) {
		return getSqlMap().queryForObject(NAMESPACES + "queryUserCodeCount", userCode);
	}

	@Override
	public String queryIsOpenUser(String userCode) {
		ApUser apUser = getSqlMap().queryForObject(NAMESPACES + "queryApUserByUserCode", userCode);
		if (null != apUser) {
			return apUser.getStatus();
		}
		return "";
	}

	@Override
	public void updatePassword(ApUser apUser) throws CoreException {
		getSqlMap().queryForObject(NAMESPACES + "updatePassword", apUser);
	}

	@Override
	public List<ApUser> queryAllApUser() throws CoreException {
		List<ApUser> list = new ArrayList<ApUser>();
		list = getSqlMap().queryForList(NAMESPACES + "queryAllApUser");
		return list;
	}
	
	@Override
	public int updateFileRightControlBatch(List<ApUser> list) {
		for (ApUser apUser : list) {
			getSqlMap().update(NAMESPACES + "updateFileRightControlBatch", apUser);
		}
		return 0;
	}
	@Override
	public List<ApUser> queryUserByThreeApRole(Map<String,String> map) throws CoreException {
		List<ApUser> list = new ArrayList<ApUser>();
		list = getSqlMap().queryForList(NAMESPACES + "queryUserByThreeApRole",map);
		return list;
	}
	@Override
	public List<ApUser> queryUserByZSJ(Map<String,String> map) throws CoreException {
		List<ApUser> list = new ArrayList<ApUser>();
		list = getSqlMap().queryForList(NAMESPACES + "queryUserByZSJ",map);
		return list;
	}
	@Override
	public ApUser queryUserCodeByUserId(String userCode) {
		ApUser apUser = getSqlMap().queryForObject(NAMESPACES + "queryUserCodeByUserId", userCode);
		return apUser;
	}
	@Override
	public int queryCurrUserCode(String userCode)  throws CoreException{
		return getSqlMap().queryForObject(NAMESPACES + "queryCurrUserCode",userCode);
	}
	
	@Override
	public List<ApUser> queryAllUserByRoleCode(Map<String, Object> map) throws CoreException {
		List<ApUser> list = new ArrayList<ApUser>();
		list = getSqlMap().queryForList(NAMESPACES + "queryAllUserByRoleCode",map);
		return list;
	}
	@Override
	public List<ApUser> queryZXAllUserByRoleCode(Map<String, Object> map) throws CoreException {
		List<ApUser> list = new ArrayList<ApUser>();
		list = getSqlMap().queryForList(NAMESPACES + "queryZXAllUserByRoleCode",map);
		return list;
	}
	@Override
	public void updateApUserFailNumAndFailTime(ApUser apUser) {
		getSqlMap().update(NAMESPACES + "updateApUserFailNumAndFailTime", apUser);
	}
	
	@Override
	public int queryConfUserCodeCount(Map<String, Object> map) {
		return getSqlMap().queryForObject(NAMESPACES + "queryConfUserCodeCount", map);
	}
	@Override
	public List<ApUser> queryConfApUserByUserCode(Map<String, Object> map, int curNum, int pageNum) {
		return getSqlMap().queryForList(NAMESPACES + "queryConfApUserByUserCode", map, curNum, pageNum);
	}

	@Override
	public Map<String, String> queryTime() {
		return getSqlMap().queryForObject(NAMESPACES + "queryTime");
	}
}
