package com.huaxia.opas.service.sysparm;

import java.util.List;
import java.util.Map;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.decision.CreditTelcheckList;
import com.huaxia.opas.domain.sysparm.BatchfileInfo;
import com.huaxia.opas.domain.sysparm.LowRiskCustomers;


/**
 * 新增低风险客户名单库
 * 
 * @author  liuwei
 * 
 * @since 2020
 * 
 * @version 1.0
 */
public interface LowRiskCustomersService {
	
	/**
	 * 批量设置征信电话核查白名单实例启用或禁用
	 *  
	 * @author luzhen.ou
	 */	
	int updateLowRiskCustomersActive(LowRiskCustomers lowRiskCustomers) throws CoreException;
	
	/**
	 * 删除低风险客户名单实例,通过主键集合ids
	 *  
	 * @author liuwei
	 */	
	int deleteLowRiskCustomers(LowRiskCustomers lowRiskCustomer) throws CoreException;
	/**
	 * 全部删除低风险客户名单实例
	 *  
	 * @author liuwei
	 */	
	int deleteLowRiskCustomersAll(String str) throws CoreException;
	/**
	 * 全部删除低风险客户名单临时表实例
	 *  
	 * @author liuwei
	 */	
	int deleteLowRiskCustomersTempAll(String str) throws CoreException;
	/**
	 * 修改低风险客户白名单实例
	 *  
	 * @author luzhen.ou
	 */
	int updateLowRiskCustomers(LowRiskCustomers lowRiskCustomers) throws CoreException;
	
	/**
	 * 新增低风险客户名单实例
	 * 
	 * @author luzhen.ou
	 * */
	int insertLowRiskCustomer(LowRiskCustomers lowRiskCustomers) throws CoreException;
	
	/**
	 * 分页查询低风险客户名单库实例列表
	 * 
	 * @author luzhen.ou
	 * */	
	Map<String, Object> queryLowRiskDomainList(LowRiskCustomers lowRiskCustomers, int curPage, int pageNum) throws CoreException;
	/**
	 * 分页查询低风险客户名单库实例列表
	 * 
	 * @author luzhen.ou
	 * */	
	Map<String, Object> queryLowRiskDomainList(LowRiskCustomers lowRiskCustomers,Map<String, Object> args) throws CoreException;
	
	/**
	 * 分页查询征信电话核查白名单实例操作历史记录列表
	 * 
	 * @author luzhen.ou
	 * */
	Map<String, Object> queryCreditTelcheckHistoryList(CreditTelcheckList creditTelcheckList, int curPage, int pageNum) throws CoreException;
	
	/**
	 * 批量导入征信电话核查白名单实例Excel
	 * wangtao
	 * @author luzhen.ou
	 * */
	int insertCreditTelcheckList(List<CreditTelcheckList> list,BatchfileInfo batchFileInfo) throws CoreException;
	/**
	 * 批量导入低风险客户名单库
	 * wangtao
	 * @author liuwei
	 * */
	int insertLowRiskCustomersList(List<LowRiskCustomers> list ) throws Exception;
	/**
	 * 
	 * @param param
	 * @return
	 */
	public Map executeProceLowRiskRename(Map param);
	/**
	 * 数据库存储过程入库
	 * @param map
	 * @return
	 */
	int insertLowRiskCustomersListCall(Map map);
}
