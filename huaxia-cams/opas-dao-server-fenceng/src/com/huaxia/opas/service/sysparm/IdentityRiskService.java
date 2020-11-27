package com.huaxia.opas.service.sysparm;

import java.util.List;
import java.util.Map;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.sysparm.BatchfileInfo;
import com.huaxia.opas.domain.sysparm.IdentityRisk;
import com.huaxia.opas.service.BaseService;
/**
 * 系统参数--身份类风险名单service层接口的相关方法
 * 
 * @author luzhen.ou
 * 
 * @since 2017-03-10
 * 
 * @version 1.0
 */
public interface IdentityRiskService extends BaseService<IdentityRisk>{
	
	/**
	 * 批量设置身份类风险名单实例启用或禁用
	 *  
	 * @author luzhen.ou
	 */	
	int updateIdentityRiskActive(IdentityRisk identityRisk) throws CoreException;	
	
	/**
	 * 分页查询身份类风险名单实例列表
	 * 
	 * @author luzhen.ou
	 * */	
	Map<String, Object> queryIdentityRiskList(IdentityRisk identityRisk, int curPage, int pageNum) throws CoreException;
	
	/**
	 * 分页查询身份类风险名单实例操作历史记录列表
	 * 
	 * @author luzhen.ou
	 * */
	Map<String, Object> queryIdentityRiskHistoryList(IdentityRisk identityRisk, int curPage, int pageNum) throws CoreException;
	
	/**
	 * 批量导入身份类风险名单实例Excel
	 * 
	 * @author luzhen.ou
	 * */
	int insertIdentityRiskList(List<IdentityRisk> list, BatchfileInfo batchfileInfo) throws CoreException;

}
