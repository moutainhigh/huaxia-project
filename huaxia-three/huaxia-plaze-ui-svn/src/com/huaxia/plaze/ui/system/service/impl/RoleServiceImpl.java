package com.huaxia.plaze.ui.system.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.huaxia.plaze.common.PageProperty;
import com.huaxia.plaze.ui.system.domain.Role;
import com.huaxia.plaze.ui.system.mapper.RoleMapper;
import com.huaxia.plaze.ui.system.service.RoleService;

@Service("roleService")
public class RoleServiceImpl implements RoleService {
	
	@Resource
	private RoleMapper<Role> roleMapper;
	
	@Override
	public int queryListCountByPage(PageProperty property) {
		Map<String, Object> args = new HashMap<String, Object>();
		args.putAll(property.getDataMap());
		return roleMapper.selectListCountByPage(args);
	}

	@Override
	public List<Role> queryListByPage(PageProperty property) {
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("pageNo", property.getPageNo());
		args.put("pageSize", property.getPageSize());
		args.putAll(property.getDataMap());
		return roleMapper.selectListByPage(args);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public int removeById(String id) {
		return roleMapper.updateStatusById(id);
	}

	@Override
	public int queryListCountByMapping(String id) {
		return roleMapper.selectListCountByMapping(id);
	}

	@Override
	public List<Role> queryAllList() {
		return roleMapper.selectAllList();
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public int save(Role role) {
		return roleMapper.insert(role);
	}

	@Override
	public Role queryById(String id) {
		return roleMapper.selectById(id);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public int update(Role role) {
		return roleMapper.update(role);
	}
	
}
