package com.huaxia.opas.service.sysparm;

import java.util.List;
import java.util.Map;
import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.sysparm.BatchfileInfo;
import com.huaxia.opas.domain.sysparm.InnerRiskInfo;
/**
 * 名单库管理--内部风险信息名单service层接口的相关方法
 * 
 * @author luzhen.ou
 * 
 * @since 2017-03-16
 * 
 * @version 1.0
 */
public interface InnerRiskInfoService {
	
	/**
	 * 批量设置内部风险信息名单实例启用或禁用
	 *  
	 * @author luzhen.ou
	 */	
	int  updateInnerRiskInfoActive(InnerRiskInfo innerRiskInfo) throws CoreException;
	
	/**
	 * 删除内部风险信息名单实例,通过主键集合ids
	 *  
	 * @author luzhen.ou
	 */	
	int  deleteInnerRiskInfo(InnerRiskInfo innerRiskInfo) throws CoreException;
	
	/**
	 * 修改内部风险信息名单实例
	 *  
	 * @author luzhen.ou
	 */
	int  updateInnerRiskInfo(InnerRiskInfo innerRiskInfo) throws CoreException;
	
	/**
	 * 新增内部风险信息名单实例
	 * 
	 * @author luzhen.ou
	 * */
	int  insertInnerRiskInfo(InnerRiskInfo innerRiskInfo) throws CoreException;	
	
	/**
	 * 分页查询内部风险信息名单实例列表
	 * 
	 * @author luzhen.ou
	 * */	
	Map<String, Object> queryInnerRiskInfoList(InnerRiskInfo innerRiskInfo, int curPage, int pageNum) throws CoreException;
	
	/**
	 * 分页查询内部风险信息名单实例操作历史记录列表
	 * 
	 * @author luzhen.ou
	 * */
	Map<String, Object> queryInnerRiskInfoHistoryList(InnerRiskInfo innerRiskInfo, int curPage, int pageNum) throws CoreException;
	
	/**
	 * 批量导入内部风险信息名单实例Excel
	 * 
	 * @author luzhen.ou
	 * */
	int  insertInnerRiskInfoList(List<InnerRiskInfo> list, BatchfileInfo batchfileInfo) throws CoreException;

}
