package com.huaxia.plaze.ui.system.service;

import java.util.List;
import java.util.Map;

import com.huaxia.plaze.ui.system.domain.Role;
import com.huaxia.plaze.ui.system.domain.User;

/**
 * 用户角色服务接口
 * 
 * @author zhiguo.li
 *
 */
public interface UserRoleService {

	List<User> queryAllUsers();

	List<Role> queryAllRoles();

	List<Map<String, String>> queryAllUserRoleMapping();

	int queryCountByPwdDetail(Map<String, Object> args);
	
	/** 合并"用户-角色"映射关系 */
	int mergeListMapping(String id, String[] array);
	
	/** 查询用户对应角色映射关系 */
	List<Map<String, String>> queryAllListMappingById(String id);

}
