package com.huaxia.plaze.ui.system.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.huaxia.plaze.common.PageProperty;
import com.huaxia.plaze.ui.system.domain.Team;
import com.huaxia.plaze.ui.system.mapper.TeamMapper;
import com.huaxia.plaze.ui.system.service.TeamService;

@Service("teamService")
public class TeamServiceImpl implements TeamService {
	
	@Resource
	private TeamMapper<Team> teamMapper;
	
	@Override
	public int queryListCountByPage(PageProperty property) {
		Map<String, Object> args = new HashMap<String, Object>();
		args.putAll(property.getDataMap());
		return teamMapper.selectListCountByPage(args);
	}

	@Override
	public List<Team> queryListByPage(PageProperty property) {
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("pageNo", property.getPageNo());
		args.put("pageSize", property.getPageSize());
		args.putAll(property.getDataMap());
		return teamMapper.selectListByPage(args);
	}
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public int updateById(Map<String, Object> args) {
		int result = teamMapper.updateByParams(args);
		return result;
	}

	@Override
	public Team queryById(String id) {
		return teamMapper.selectById(id);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public int save(Team team) {
		return teamMapper.insert(team);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public int removeById(String id) {
		return teamMapper.updateStatusById(id);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public int update(Team team) {
		return teamMapper.update(team);
	}

	@Override
	public List<Team> queryAllList() {
		return teamMapper.selectAllList();
	}

}
