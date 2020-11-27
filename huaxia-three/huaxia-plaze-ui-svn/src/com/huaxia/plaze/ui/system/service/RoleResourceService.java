package com.huaxia.plaze.ui.system.service;

import java.util.List;
import java.util.Map;

import com.huaxia.plaze.ui.system.domain.Menu;

/**
 * 角色菜单服务接口
 * 
 * @author zhiguo.li
 *
 */
public interface RoleResourceService {
	
	List<Map<String, String>> queryListMapping();
	
	/** 查询账号对应菜单项 */
	List<Menu> queryMenuByAccount(String account);
	
	/** 合并"角色-菜单"映射关系 */
	int mergeListMapping(String id, String[] array);
	
	/** 查询账号对应菜单映射关系 */
	List<Map<String, String>> queryTreeListMappingById(String id);

}
