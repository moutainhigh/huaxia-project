package com.huaxia.plaze.ui.system.service;

import java.util.List;
import java.util.Map;

import com.huaxia.plaze.common.PageProperty;
import com.huaxia.plaze.ui.system.domain.Team;

/**
 * 团队服务接口
 * 
 * @author zhiguo.li
 *
 */
public interface TeamService {

	/** 分页总数量 */
	int queryListCountByPage(PageProperty property);

	/** 分页总记录 */
	List<Team> queryListByPage(PageProperty property);
	
	/** 查询全部记录列表 */
	List<Team> queryAllList();

	/** 查询指定编号对象 */
	Team queryById(String id);
	
	/** 保存对象 */
	int save(Team team);
	
	/** 删除指定编号对象 */
	int removeById(String id);
	
	/** 更新对象 */
	int update(Team team);
	
	/** 根据记录编号更新对象 */
	int updateById(Map<String, Object> args);
	
}
