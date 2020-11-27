package com.huaxia.plaze.ui.system.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.huaxia.plaze.ui.system.domain.Menu;
import com.huaxia.plaze.ui.system.mapper.RoleResourceMapper;
import com.huaxia.plaze.ui.system.service.RoleResourceService;

@Service("roleMenuService")
public class RoleResourceServiceImpl implements RoleResourceService {
	
	@Resource
	private RoleResourceMapper<Menu> roleResourceMapper;

	@Override
	public List<Map<String, String>> queryListMapping() {
		return roleResourceMapper.selectListMapping();
	}

	@Override
	public List<Menu> queryMenuByAccount(String account) {
		return roleResourceMapper.selectListByAccount(account);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public int mergeListMapping(String id, String[] array) {
		int result = roleResourceMapper.deleteListMappingByKey(id);
		if (array != null && array.length > 0) {
			Map<String, Object> args = new HashMap<String, Object>();
			args.put("id", id);
			args.put("array", array);
			result = roleResourceMapper.insertListMapping(args);
		}
		return result;
	}

	@Override
	public List<Map<String, String>> queryTreeListMappingById(String id) {
		return roleResourceMapper.selectTreeListMappingByKey(id);
	}

}
