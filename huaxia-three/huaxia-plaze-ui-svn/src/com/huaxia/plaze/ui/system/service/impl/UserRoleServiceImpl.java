package com.huaxia.plaze.ui.system.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.huaxia.plaze.ui.system.domain.Role;
import com.huaxia.plaze.ui.system.domain.User;
import com.huaxia.plaze.ui.system.mapper.PasswordDetailMapper;
import com.huaxia.plaze.ui.system.mapper.UserRoleMapper;
import com.huaxia.plaze.ui.system.service.UserRoleService;

@Service("userRoleService")
public class UserRoleServiceImpl implements UserRoleService {

	@Resource
	private UserRoleMapper userRoleMapper;

	@Resource
	private PasswordDetailMapper passwordDetailMapper;
	
	@Override
	public List<User> queryAllUsers() {
		return userRoleMapper.selectAllUsers();
	}

	@Override
	public List<Role> queryAllRoles() {
		return userRoleMapper.selectAllRoles();
	}

	@Override
	public List<Map<String, String>> queryAllUserRoleMapping() {
		return userRoleMapper.selectAllUserRoleMapping();
	}

	@Override
	public int queryCountByPwdDetail(Map<String, Object> args) {
		return passwordDetailMapper.selectCountByPwdDetail(args);
	}

	@Override
	public int mergeListMapping(String id, String[] array) {
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("id", id);
		args.put("array", array);
		
		userRoleMapper.deleteListMappingById(id);
		return userRoleMapper.insertListMapping(args);
	}

	@Override
	public List<Map<String, String>> queryAllListMappingById(String id) {
		return userRoleMapper.selectAllListMappingById(id);
	}

}
