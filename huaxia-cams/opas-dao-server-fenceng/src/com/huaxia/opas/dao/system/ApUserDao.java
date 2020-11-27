package com.huaxia.opas.dao.system;

import java.util.List;
import java.util.Map;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.system.ApUser;
import com.huaxia.opas.domain.system.FileRoleRelation;

public interface ApUserDao {

	public int insertApUser(ApUser apUser) throws CoreException;

	public int insertApUserSelective(ApUser apUser) throws CoreException;

	public int deleteApUserByUserId(String userId) throws CoreException;

	public int updateApUser(ApUser apUser) throws CoreException;

	public int updateApUserSelective(ApUser apUser) throws CoreException;

	public ApUser queryApUserByUserId(String userId);

	public ApUser queryApUserByUserCode(String userCode) throws CoreException;

	public int queryUserCodeCount(String userCode);

	public String queryIsOpenUser(String userCode);

	public void updatePassword(ApUser apUser) throws CoreException;

	public List<ApUser> queryAllApUser() throws CoreException;

	public int updateFileRightControlBatch(List<ApUser> list);

	ApUser queryUserCodeByUserId(String userCode);

	List<ApUser> queryUserByThreeApRole(Map<String, String> map) throws CoreException;

	List<ApUser> queryAllUserByRoleCode(Map<String, Object> map) throws CoreException;

	int queryCurrUserCode(String userCode) throws CoreException;

	List<ApUser> queryUserByZSJ(Map<String, String> map) throws CoreException;

	/**
	 * @Title:updateApUserFailNumAndFailTime
	 *@Description:userCode修改登录失败次数 及登录失败时间 字段
	 *@param apUser
	 *@author: gaohui
	 *@Date:2017年9月7日上午11:01:27
	 */
	void updateApUserFailNumAndFailTime(ApUser apUser);

	List<ApUser> queryConfApUserByUserCode(Map<String, Object> map, int curNum, int pageNum);

	int queryConfUserCodeCount(Map<String, Object> map);

	List<ApUser> queryZXAllUserByRoleCode(Map<String, Object> map) throws CoreException;
	
	public Map<String, String> queryTime();
}
