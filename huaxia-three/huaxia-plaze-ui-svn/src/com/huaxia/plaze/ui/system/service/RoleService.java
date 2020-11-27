package com.huaxia.plaze.ui.system.service;

import java.util.List;

import com.huaxia.plaze.common.PageProperty;
import com.huaxia.plaze.ui.system.domain.Role;

/**
 * 角色服务接口
 * 
 * @author zhiguo.li
 *
 */
public interface RoleService {

	/** 分页总数量 */
	int queryListCountByPage(PageProperty property);

	/** 分页总记录 */
	List<Role> queryListByPage(PageProperty property);
	
	/** 查询指定映射条件的记录总数量 */
	int queryListCountByMapping(String id);

	/** 删除指定编号记录 */
	int removeById(String id);
	
	/** 查询全部记录列表 */
	List<Role> queryAllList();
	
	/** 保存记录 */
	int save(Role role);
	
	/** 更新记录 */
	int update(Role role);
	
	/** 查询指定编号记录 */
	Role queryById(String id);
	
}
