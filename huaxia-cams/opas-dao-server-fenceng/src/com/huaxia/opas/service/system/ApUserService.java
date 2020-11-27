package com.huaxia.opas.service.system;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.allot.AllotApplyAllot;
import com.huaxia.opas.domain.system.ApUser;

public interface ApUserService {

	public int insertApUser(ApUser apUser) throws CoreException;

	public int insertApUserSelective(ApUser apUser) throws CoreException;

	public int deleteApUserByUserId(String userId) throws CoreException;

	public int updateApUser(ApUser apUser) throws CoreException;

	public int updateApUserSelective(ApUser apUser) throws CoreException;

	public ApUser queryApUserByUserId(String userId) throws CoreException;

	public ApUser queryApUserByUserCode(String userCode) throws CoreException;

	public int queryUserCodeCount(String userCode);

	public String queryIsOpenUser(String userCode);

	public void updatePassword(ApUser apUser) throws CoreException;

	public List<ApUser> queryAllApUser() throws CoreException;

	public int updateFileRightControlBatch(List<ApUser> list);
	
	List<ApUser> queryUserByThreeApRole(Map<String, String> map) throws CoreException;
	
	public ApUser queryUserCodeByUserId(String userCode) throws CoreException;

	List<ApUser> queryAllUserByRoleCode(Map<String, Object> map) throws CoreException;

	List<ApUser> queryUserByZSJ(Map<String, String> map) throws CoreException;
	
	/**
	 *@Title:updateApUserFailNumAndFailTime
	 *@Description:根据 userCode修改登录失败次数 及登录失败时间 字段
	 *@param apUser
	 *@author: gaohui
	 *@Date:2017年9月7日上午10:58:52
	 */
	void updateApUserFailNumAndFailTime(ApUser apUser);

	int queryConfUserCodeCount(Map<String, Object> map);

	List<ApUser> queryConfApUserByUserCode(Map<String, Object> map, int curNum, int pageNum);

	List<ApUser> queryZXAllUserByRoleCode(Map<String, Object> map) throws CoreException;
	/**
	 *@Title:queryApplyByUserId
	 *@Description:修改组时检测该组员是否有未处理完的申请件
	 *@param map
	 *@author: wangdebin
	 *@Date:2017年10月25日下午14:19:52
	 */
	List<AllotApplyAllot> queryApplyByUserId(Map map);

	/**
	 * @Description:查询当前数据库时间用于校验登录用户的有效期
	 * 
	 * @author:lipengfei
	 * @Date:2018年10月23日
	 */
	public Map<String, String> queryTime();
}
