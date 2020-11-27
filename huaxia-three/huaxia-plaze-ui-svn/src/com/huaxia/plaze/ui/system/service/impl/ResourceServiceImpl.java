package com.huaxia.plaze.ui.system.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.huaxia.plaze.common.PageProperty;
import com.huaxia.plaze.ui.system.domain.Menu;
import com.huaxia.plaze.ui.system.mapper.ResourceMapper;
import com.huaxia.plaze.ui.system.mapper.RoleResourceMapper;
import com.huaxia.plaze.ui.system.service.ResourceService;

@Service("menuService")
public class ResourceServiceImpl implements ResourceService {
	
	@Resource
	private ResourceMapper<Menu> resourceMapper;
	
	@Resource
	private RoleResourceMapper<Menu> roleResourceMapper;
	
	@Override
	public List<Menu> queryList() {
		return resourceMapper.selectList();
	}

	@Override
	public int queryListCountByPath(String requestPath) {
		return resourceMapper.selectListCountByPath(requestPath);
	}

	@Override
	public int queryListCountByPage(PageProperty property) {
		Map<String, Object> args = new HashMap<String, Object>();
		args.putAll(property.getDataMap());
		return resourceMapper.selectListCountByPage(args);
	}

	@Override
	public List<Menu> queryListByPage(PageProperty property) {
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("pageNo", property.getPageNo());
		args.put("pageSize", property.getPageSize());
		args.putAll(property.getDataMap());
		return resourceMapper.selectListByPage(args);
	}
	
	@Override
	public Menu queryObjectById(String id) {
		return resourceMapper.selectObjectById(id);
	}
	
	@Override
	public int queryListCountByParentId(String pid) {
		return resourceMapper.selectListCountByParentId(pid);
	}

	@Override
	public int queryChooseListCountByPage(PageProperty property) {
		Map<String, Object> args = new HashMap<String, Object>();
		args.putAll(property.getDataMap());
		return resourceMapper.selectChooseListCountByPage(args);
	}

	@Override
	public List<Menu> queryChooseListByPage(PageProperty property) {
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("pageNo", property.getPageNo());
		args.put("pageSize", property.getPageSize());
		args.putAll(property.getDataMap());
		return resourceMapper.selectChooseListByPage(args);
	}

	@Override
	public Menu queryNextObjectById(String id) {
		return resourceMapper.selectNextObjectByKey(id);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public int save(Menu menu) {
		return resourceMapper.insertObject(menu);
	}

	@Override
	public List<Menu> queryTreeList() {
		return resourceMapper.selectTreeList();
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public int update(Menu menu) {
		return resourceMapper.updateObject(menu);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public int removeById(String id) {
		return resourceMapper.deleteObjectById(id);
	}

}
