package com.huaxia.plaze.ui.system.service;

import java.util.List;

import com.huaxia.plaze.common.PageProperty;
import com.huaxia.plaze.ui.system.domain.Menu;

/**
 * 菜单服务接口
 * 
 * @author zhiguo.li
 *
 */
public interface ResourceService {

	/** 分页总数量 */
	int queryListCountByPage(PageProperty property);

	/** 分页总记录 */
	List<Menu> queryListByPage(PageProperty property);

	/** 查询指定编号记录 */
	Menu queryObjectById(String id);

	/** 删除指定编号记录 */
	int removeById(String id);

	/** 查询全部 */
	List<Menu> queryList();

	/** 查询请求路径对应菜单项 */
	int queryListCountByPath(String requestPath);
	
	/** 查询指定父级编号的记录总数量 */
	int queryListCountByParentId(String pid);
	
	/** 分页总数量（所属菜单） */
	int queryChooseListCountByPage(PageProperty property);

	/** 分页总记录（所属菜单） */
	List<Menu> queryChooseListByPage(PageProperty property);
	
	/** 根据父级节点编号查询下一条记录摘要信息 */
	Menu queryNextObjectById(String id);
	
	/** 保存记录 */
	int save(Menu menu);
	
	/** 菜单总记录（菜单授权） */
	List<Menu> queryTreeList();
	
	/** 更新记录 */
	int update(Menu menu);

}
