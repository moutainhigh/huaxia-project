package com.huaxia.opas.dao.sysparm;

import java.util.List;
import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.decision.MerchTeamdealList;





/**
 * 名单库管理_商户团办名单dao层接口的相关方法
 * 
 * @author luzhen.ou
 * 
 * @since 2017-03-18
 * 
 * @version 1.0
 */
public interface MerchTeamdealDao {
	
	/**
	 * 新增商户团办名单实例
	 * 
	 * @author luzhen.ou
	 * 
	 * @param MerchTeamdealList
	 * 
	 * @return Int
	 * */
	public int insertMerchTeamdeal(MerchTeamdealList merchTeamdealList) throws CoreException;
	
	/**
	 * 批量导入商户团办名单实例（excel文件）
	 * 
	 * @author luzhen.ou
	 * 
	 * @param MerchTeamdealList
	 * 
	 * @return Int
	 * */
	public int insertMerchTeamdealList(List<MerchTeamdealList> list) throws CoreException;
	
	/**
	 * 查询商户团办名单实例通过主键ids
	 * 
	 * @author luzhen.ou
	 * 
	 * @param MerchTeamdealList
	 * 
	 * @return MerchTeamdealList
	 * */	
	public MerchTeamdealList queryMerchTeamdeal(MerchTeamdealList merchTeamdealList) throws CoreException;	
	/**
	 * 修改商户团办名单实例
	 * 
	 * @author luzhen.ou
	 * 
	 * @param MerchTeamdealList
	 * 
	 * @return Int
	 * */
	public int updateMerchTeamdeal(MerchTeamdealList merchTeamdealList) throws CoreException;
	
	/**
	 * 条件查询商户团办名单的总记录数，供分页使用
	 * 
	 * @author luzhen.ou
	 * 
	 * @param merchTeamdealList
	 * 
	 * @return int
	 * */	
	public int queryMerchTeamdealCount(MerchTeamdealList merchTeamdealList) throws CoreException;
	
	/**
	 * 条件查询商户团办名单，分页查询
	 * 
	 * @author luzhen.ou
	 * 
	 * @param MerchTeamdealList merchTeamdealList，int curNum, int pageNum
	 * 
	 * @return List<MerchTeamdealList>
	 * */	
	public List<MerchTeamdealList> queryMerchTeamdealList(MerchTeamdealList merchTeamdealList, int curNum, int pageNum)throws CoreException;
	
	/**
	 * 删除商户团办名单实例通过主键ids
	 * 
	 * @author luzhen.ou
	 * 
	 * @param merchTeamdealList
	 * 
	 * @return int
	 * */	
	public int deleteMerchTeamdeal(MerchTeamdealList merchTeamdealList)throws CoreException;

	/**
	 * 批量设置商户团办名单实例启用或禁用通过主键ids
	 * 
	 * @author luzhen.ou
	 * 
	 * @param merchTeamdealList
	 * 
	 * @return int
	 * */	
	public int updateMerchTeamdealActive(MerchTeamdealList merchTeamdealList)throws CoreException;

	/**
	 * 条件查询商户团办名单实例历史操作的总记录数，供分页使用
	 * 
	 * @author luzhen.ou
	 * 
	 * @param merchTeamdealList
	 * 
	 * @return int
	 * */	
	public int queryMerchTeamdealHistoryCount(MerchTeamdealList merchTeamdealList)throws CoreException;

	/**
	 * 分页查询，分页查询商户团办名单实例历史操作历史记录
	 * 
	 * @author luzhen.ou
	 * 
	 * @param MerchTeamdealList merchTeamdealList，int curNum, int pageNum
	 * 
	 * @return List<MerchTeamdealList>
	 * */	
	public List<MerchTeamdealList> queryMerchTeamdealHistoryList(MerchTeamdealList merchTeamdealList, int curNum, int pageNum)throws CoreException;
	
	/**
	 * 新增商户团办名单实例操作历史记录
	 * 
	 * @author luzhen.ou
	 * 
	 * @param MerchTeamdealList
	 * 
	 * @return Int
	 * */
	public int insertMerchTeamdealHistory(MerchTeamdealList merchTeamdealList) throws CoreException;
	
	/**
	 * 批量新增商户团办名单实例操作历史记录
	 * 
	 * @author luzhen.ou
	 * wangtao
	 * @param List<MerchTeamdealList> list
	 * 
	 * @return int
	 * */
	public int insertMerchTeamdealHistoryList(List<MerchTeamdealList> list) throws CoreException;

}
