package com.huaxia.opas.service.system.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.allot.AllotApplyAllotDao;
import com.huaxia.opas.dao.system.ApUserDao;
import com.huaxia.opas.domain.allot.AllotApplyAllot;
import com.huaxia.opas.domain.system.ApUser;
import com.huaxia.opas.service.AbstractService;
import com.huaxia.opas.service.system.ApUserService;

public class ApUserServiceImpl extends AbstractService implements ApUserService, Serializable {

	private static final long serialVersionUID = -6607523310232587550L;

	@Resource(name = "apUserDao")
	private ApUserDao apUserDao;
	
	@Resource(name = "allotApplyAllotDao")
	private AllotApplyAllotDao allotApplyAllotDao;

	@Override
	public int insertApUser(ApUser apUser) throws CoreException {
		return getApUserDao().insertApUser(apUser);
	}

	@Override
	public int insertApUserSelective(ApUser apUser) throws CoreException {
		return getApUserDao().insertApUserSelective(apUser);
	}

	@Override
	public int deleteApUserByUserId(String userId) throws CoreException {
		return getApUserDao().deleteApUserByUserId(userId);
	}

	@Override
	public int updateApUser(ApUser apUser) throws CoreException {
		return getApUserDao().updateApUser(apUser);
	}

	@Override
	public int updateApUserSelective(ApUser apUser) throws CoreException {
		return getApUserDao().updateApUserSelective(apUser);
	}

	@Override
	public ApUser queryApUserByUserId(String userId) throws CoreException {
		return getApUserDao().queryApUserByUserId(userId);
	}

	@Override
	public ApUser queryApUserByUserCode(String userCode) throws CoreException {
		return getApUserDao().queryApUserByUserCode(userCode);
	}

	@Override
	public int queryUserCodeCount(String userCode) {
		return getApUserDao().queryUserCodeCount(userCode);
	}

	@Override
	public String queryIsOpenUser(String userCode) {
		return getApUserDao().queryIsOpenUser(userCode);
	}

	@Override
	public void updatePassword(ApUser apUser) throws CoreException {
		getApUserDao().updatePassword(apUser);
	}

	@Override
	public List<ApUser> queryAllApUser() throws CoreException {
		return getApUserDao().queryAllApUser();
	}

	@Override
	public int updateFileRightControlBatch(List<ApUser> list) {
		getApUserDao().updateFileRightControlBatch(list);
		return 0;
	}

	public ApUserDao getApUserDao() {
		return apUserDao;
	}

	public void setApUserDao(ApUserDao apUserDao) {
		this.apUserDao = apUserDao;
	}

	@Override
	public List<ApUser> queryUserByThreeApRole(Map<String, String> map) throws CoreException {
		return getApUserDao().queryUserByThreeApRole(map);
	}
	@Override
	public List<ApUser> queryUserByZSJ(Map<String, String> map) throws CoreException {
		return getApUserDao().queryUserByZSJ(map);
	}
	@Override
	public ApUser queryUserCodeByUserId(String userCode) throws CoreException {
		return getApUserDao().queryUserCodeByUserId(userCode);
	}
	
	@Override
	public List<ApUser> queryAllUserByRoleCode(Map<String, Object> map) throws CoreException {
		return getApUserDao().queryAllUserByRoleCode(map);
	}
	@Override
	public List<ApUser> queryZXAllUserByRoleCode(Map<String, Object> map) throws CoreException {
		return getApUserDao().queryZXAllUserByRoleCode(map);
	}
	@Override
	public void updateApUserFailNumAndFailTime(ApUser apUser) {
		getApUserDao().updateApUserFailNumAndFailTime(apUser);
	}
	
	@Override
	public int queryConfUserCodeCount(Map<String, Object> map) {
		return getApUserDao().queryConfUserCodeCount(map);
	}
	@Override
	public List<ApUser> queryConfApUserByUserCode(Map<String, Object> map, int curNum, int pageNum) {
		return getApUserDao().queryConfApUserByUserCode(map, curNum, pageNum);
	}
	@Override
	public List<AllotApplyAllot> queryApplyByUserId(Map map) {
		return allotApplyAllotDao.selectApplyByUserId(map);
	}

	@Override
	public Map<String, String> queryTime() {
		return getApUserDao().queryTime();
	}
}
