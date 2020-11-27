package com.huaxia.opas.service.sysparm;

import java.util.List;
import java.util.Map;
import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.decision.TeamdealList;
import com.huaxia.opas.domain.sysparm.BatchfileInfo;
import com.huaxia.opas.service.BaseService;

/**
 * 名单库管理_易达金团办名单service层接口的相关方法
 * 
 * @author luzhen.ou
 * 
 * @since 2017-03-19
 * 
 * @version 1.0
 */
public interface TeamdealService extends BaseService<TeamdealList>{
	
	/**
	 * 批量设置易达金团办名单实例启用或禁用
	 *  
	 * @author luzhen.ou
	 */	
	int updateTeamdealActive(TeamdealList teamdealList) throws CoreException;
	
	/**
	 * 分页查询易达金团办名单实例列表
	 * 
	 * @author luzhen.ou
	 * */	
	Map<String, Object> queryTeamdealList(TeamdealList teamdealList, int curPage, int pageNum) throws CoreException;
	
	/**
	 * 分页查询易达金团办名单实例操作历史记录列表
	 * 
	 * @author luzhen.ou
	 * */
	Map<String, Object> queryTeamdealHistoryList(TeamdealList teamdealList, int curPage, int pageNum) throws CoreException;
	
	/**
	 * 批量导入易达金团办名单实例Excel
	 * 
	 * @author luzhen.ou
	 * */
	int insertTeamdealList(List<TeamdealList> list,BatchfileInfo batchfileInfo) throws CoreException;

}
