package com.huaxia.plaze.ui.system.mapper;

import java.util.List;
import java.util.Map;

import com.huaxia.plaze.ui.system.domain.Role;
import com.huaxia.plaze.ui.system.domain.User;

public interface UserRoleMapper {

	List<User> selectAllUsers();

	List<Role> selectAllRoles();

	List<Map<String, String>> selectAllUserRoleMapping();

	List<Map<String, String>> selectListMapping();

	int deleteListMappingById(String id);
	
	int insertListMapping(Map<String, Object> args);
	
	List<Map<String, String>> selectAllListMappingById(String id);

}
