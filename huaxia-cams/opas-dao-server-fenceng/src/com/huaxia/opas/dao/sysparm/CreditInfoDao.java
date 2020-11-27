package com.huaxia.opas.dao.sysparm;

import java.util.List;
import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.sysparm.CreditWhiteList;

/**
 * 名单库管理_标准卡征信信息名单dao层接口的相关方法
 * 
 * @author luzhen.ou
 * 
 * @since 2017-03-16
 * 
 * @version 1.0
 */
public interface CreditInfoDao {
	
	/**
	 * 新增标准卡征信信息名单实例
	 * 
	 * @author luzhen.ou
	 * 
	 * @param CreditWhiteList
	 * 
	 * @return Int
	 * */
	public int insertCreditInfo(CreditWhiteList creditWhiteList) throws CoreException;
	
	/**
	 * 批量导入标准卡征信信息名单实例（excel文件）
	 * 
	 * @author luzhen.ou
	 * 
	 * @param CreditWhiteList
	 * 
	 * @return Int
	 * */
	public int insertCreditInfoList(List<CreditWhiteList> list) throws CoreException;
	
	/**
	 * 查询标准卡征信信息名单实例通过主键ids
	 * 
	 * @author luzhen.ou
	 * 
	 * @param creditWhiteList
	 * 
	 * @return CreditWhiteList
	 * */	
	public CreditWhiteList queryCreditInfo(CreditWhiteList creditWhiteList) throws CoreException;	
	/**
	 * 修改标准卡征信信息名单实例
	 * 
	 * @author luzhen.ou
	 * 
	 * @param CreditWhiteList
	 * 
	 * @return Int
	 * */
	public int updateCreditInfo(CreditWhiteList creditWhiteList) throws CoreException;
	
	/**
	 * 条件查询标准卡征信信息名单的总记录数，供分页使用
	 * 
	 * @author luzhen.ou
	 * 
	 * @param creditWhiteList
	 * 
	 * @return int
	 * */	
	public int queryCreditInfoCount(CreditWhiteList creditWhiteList) throws CoreException;
	
	/**
	 * 条件查询标准卡征信信息名单，分页查询
	 * 
	 * @author luzhen.ou
	 * 
	 * @param CreditWhiteList creditWhiteList，int curNum, int pageNum
	 * 
	 * @return List<CreditWhiteList>
	 * */	
	public List<CreditWhiteList> queryCreditInfoList(CreditWhiteList creditWhiteList, int curNum, int pageNum)throws CoreException;
	
	/**
	 * 删除标准卡征信信息名单实例通过主键ids
	 * 
	 * @author luzhen.ou
	 * 
	 * @param creditWhiteList
	 * 
	 * @return int
	 * */	
	public int deleteCreditInfo(CreditWhiteList creditWhiteList)throws CoreException;

	/**
	 * 批量设置标准卡征信信息名单实例启用或禁用通过主键ids
	 * 
	 * @author luzhen.ou
	 * 
	 * @param creditWhiteList
	 * 
	 * @return int
	 * */	
	public int updateCreditInfoActive(CreditWhiteList creditWhiteList)throws CoreException;

	/**
	 * 条件查询标准卡征信信息名单实例历史操作的总记录数，供分页使用
	 * 
	 * @author luzhen.ou
	 * 
	 * @param creditWhiteList
	 * 
	 * @return int
	 * */	
	public int queryCreditInfoHistoryCount(CreditWhiteList creditWhiteList)throws CoreException;

	/**
	 * 查询，分页查询标准卡征信信息名单实例历史操作历史记录
	 * 
	 * @author luzhen.ou
	 * 
	 * @param CreditWhiteList creditWhiteList，int curNum, int pageNum
	 * 
	 * @return List<CreditWhiteList>
	 * */	
	public List<CreditWhiteList> queryCreditInfoHistoryList(CreditWhiteList creditWhiteList, int curNum, int pageNum)throws CoreException;
	
	/**
	 * 新增标准卡征信信息名单实例操作历史记录
	 * 
	 * @author luzhen.ou
	 * 
	 * @param CreditWhiteList
	 * 
	 * @return Int
	 * */
	public int insertCreditInfoHistory(CreditWhiteList creditWhiteList) throws CoreException;
	
	/**
	 * 批量新增标准卡征信信息名单实例操作历史记录
	 * 
	 * @author luzhen.ou
	 * 
	 * @param List<CreditWhiteList> list
	 * 
	 * @return int
	 * */
	public int insertCreditInfoHistoryList(List<CreditWhiteList> list) throws CoreException;

	public List<CreditWhiteList> queryCreditInfoListWithOutOperator(CreditWhiteList creditWhiteList, int i, int j);

}
