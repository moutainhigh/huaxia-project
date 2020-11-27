package com.huaxia.opas.dao.sysparm;

import java.util.List;
import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.decision.CreditTelcheckList;





/**
 * 名单库管理_征信电话核查白名单dao层接口的相关方法
 * 
 * @author luzhen.ou
 * 
 * @since 2017-03-15
 * 
 * @version 1.0
 */
public interface CreditTelcheckDao {
	
	/**
	 * 新增征信电话核查白名单实例
	 * 
	 * @author luzhen.ou
	 * 
	 * @param CreditTelcheckList
	 * 
	 * @return Int
	 * */
	public int insertCreditTelcheck(CreditTelcheckList creditTelcheckList) throws CoreException;
	
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
	public int updateCreditTelcheck(CreditTelcheckList creditTelcheckList) throws CoreException;
	
	/**
	 * 条件查询征信电话核查白名单的总记录数，供分页使用
	 * 
	 * @author luzhen.ou
	 * 
	 * @param creditTelcheckList
	 * 
	 * @return int
	 * */	
	public int queryCreditTelcheckCount(CreditTelcheckList creditTelcheckList) throws CoreException;
	
	/**
	 * 条件查询征信电话核查白名单，分页查询
	 * 
	 * @author luzhen.ou
	 * 
	 * @param CreditTelcheckList creditTelcheckList，int curNum, int pageNum
	 * 
	 * @return List<CreditTelcheckList>
	 * */	
	public List<CreditTelcheckList> queryCreditTelcheckDomainList(CreditTelcheckList creditTelcheckList, int curNum, int pageNum)throws CoreException;
	
	/**
	 * 删除征信电话核查白名单实例通过主键ids
	 * 
	 * @author luzhen.ou
	 * 
	 * @param creditTelcheckList
	 * 
	 * @return int
	 * */	
	public int deleteCreditTelcheck(CreditTelcheckList creditTelcheckList)throws CoreException;

	/**
	 * 批量设置征信电话核查白名单实例启用或禁用通过主键ids
	 * 
	 * @author luzhen.ou
	 * 
	 * @param creditTelcheckList
	 * 
	 * @return int
	 * */	
	public int updateCreditTelcheckActive(CreditTelcheckList creditTelcheckList)throws CoreException;

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

}
