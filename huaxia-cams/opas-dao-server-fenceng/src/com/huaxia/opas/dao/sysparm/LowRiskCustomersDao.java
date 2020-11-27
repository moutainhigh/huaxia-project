package com.huaxia.opas.dao.sysparm;

import java.util.List;
import java.util.Map;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.decision.CreditTelcheckList;
import com.huaxia.opas.domain.sysparm.LowRiskCustomers;





/**
 * 新增低风险客户名单库
 * 
 * @author luzhen.ou
 * 
 * @since 2017-03-15
 * 
 * @version 1.0
 */
public interface LowRiskCustomersDao {
	
	/**
	 * 新增低风险客户名单实例
	 * 
	 * @author luzhen.ou
	 * 
	 * @param CreditTelcheckList
	 * 
	 * @return Int
	 * */
	public int insertLowRiskCustomer(LowRiskCustomers lowRiskCustomers) throws CoreException;
	
	/**
	 * 批量导入征信电话核查白名单实例（excel文件）
	 * 
	 * @author luzhen.ou
	 * 
	 * @param CreditTelcheckList
	 * 
	 * @return Int
	 * */
	public int insertCreditTelcheckList(List<CreditTelcheckList> list) throws CoreException;
	
	/**
	 * 查询征信电话核查白名单实例通过主键ids
	 * 
	 * @author luzhen.ou
	 * 
	 * @param creditTelcheckList
	 * 
	 * @return CreditTelcheckList
	 * */	
	public CreditTelcheckList queryCreditTelcheck(CreditTelcheckList creditTelcheckList) throws CoreException;	
	/**
	 * 修改征信电话核查白名单实例
	 * 
	 * @author luzhen.ou
	 * 
	 * @param CreditTelcheckList
	 * 
	 * @return Int
	 * */
	public int  updateLowRiskCustomers(LowRiskCustomers lowRiskCustomers) throws CoreException;
	
	/**
	 * 条件查询低风险客户名单库总记录数，供分页使用
	 * 
	 * @author luzhen.ou
	 * 
	 * @param creditTelcheckList
	 * 
	 * @return int
	 * */	
	public int queryLowRiskCustomerCount(LowRiskCustomers lowRiskCustomers) throws CoreException;
	
	/**
	 * 条件查询低风险客户名单，分页查询
	 * 
	 * @author luzhen.ou
	 * 
	 * @param CreditTelcheckList creditTelcheckList，int curNum, int pageNum
	 * 
	 * @return List<CreditTelcheckList>
	 * */	
	public List<LowRiskCustomers> queryLowRiskCustomerDomainList(LowRiskCustomers lowRiskCustomers, int curNum, int pageNum)throws CoreException;
	
	/**
	 * 条件查询低风险客户名单，分页查询
	 * 
	 * @author luzhen.ou
	 * 
	 * @param CreditTelcheckList creditTelcheckList，int curNum, int pageNum
	 * 
	 * @return List<CreditTelcheckList>
	 * */	
	public List<LowRiskCustomers> queryLowRiskCustomerDomainList(Map<String, Object> args)throws CoreException;
	
	/**
	 * 删除低风险客户名单实例通过主键ids
	 * 
	 * @author luzhen.ou
	 * 
	 * @param creditTelcheckList
	 * 
	 * @return intdelete
	 * */	
	public int deleteLowRiskCustomers(LowRiskCustomers lowRiskCustomer) throws CoreException;
	/**
	 * 全量删除低风险客户名单实例通过
	 * 
	 * @author luzhen.ou
	 * 
	 * @param creditTelcheckList
	 * 
	 * @return intdelete
	 * */	
	public int deleteLowRiskCustomersAll(String str) throws CoreException;
	/**
	 * 全量删除低风险客户名单临时表实例通过
	 * 
	 * @author luzhen.ou
	 * 
	 * @param creditTelcheckList
	 * 
	 * @return intdelete
	 * */	
	public int deleteLowRiskCustomersTempAll(String str) throws CoreException;
	/**
	 * 批量设置征信电话核查白名单实例启用或禁用通过主键ids
	 * 
	 * @author luzhen.ou
	 * 
	 * @param creditTelcheckList
	 * 
	 * @return int
	 * */	
	public int updateLowRiskCustomersActive(LowRiskCustomers lowRiskCustomers)  throws CoreException;

	/**
	 * 条件查询征信电话核查白名单实例历史操作的总记录数，供分页使用
	 * 
	 * @author luzhen.ou
	 * 
	 * @param creditTelcheckList
	 * 
	 * @return int
	 * */	
	public int queryCreditTelcheckHistoryCount(CreditTelcheckList creditTelcheckList)throws CoreException;

	/**
	 * 分页查询，分页查询征信电话核查白名单实例历史操作历史记录
	 * 
	 * @author luzhen.ou
	 * 
	 * @param CreditTelcheckList creditTelcheckList，int curNum, int pageNum
	 * 
	 * @return List<CreditTelcheckList>
	 * */	
	public List<CreditTelcheckList> queryCreditTelcheckHistoryList(CreditTelcheckList creditTelcheckList, int curNum, int pageNum)throws CoreException;
	
	/**
	 * 新增征信电话核查白名单实例操作历史记录
	 * 
	 * @author luzhen.ou
	 * 
	 * @param CreditTelcheckList
	 * 
	 * @return Int
	 * */
	public int insertCreditTelcheckHistory(CreditTelcheckList creditTelcheckList) throws CoreException;
	
	/**
	 * 批量新增征信电话核查白名单实例操作历史记录
	 * 
	 * @author luzhen.ou
	 * 
	 * @param List<CreditTelcheckList> list
	 * 
	 * @return int
	 * */
	public int insertCreditTelcheckHistoryList(List<CreditTelcheckList> list) throws CoreException;
	/**
	 * 批量导入低风险客户名单库
	 * wangtao
	 * @author liuwei
	 * */
	public	int insertLowRiskCustomersList(List<LowRiskCustomers> list ) throws Exception;
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
