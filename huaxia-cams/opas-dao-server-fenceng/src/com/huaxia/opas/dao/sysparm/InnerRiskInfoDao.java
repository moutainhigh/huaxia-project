package com.huaxia.opas.dao.sysparm;

import java.util.List;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.sysparm.InnerRiskInfo;



/**
 * 名单库管理--内部风险信息名单dao层接口的相关方法
 * 
 * @author luzhen.ou
 * 
 * @since 2017-03-16
 * 
 * @version 1.0
 */
public interface InnerRiskInfoDao {
	
	/**
	 * 新增内部风险信息名单实例
	 * 
	 * @author luzhen.ou
	 * 
	 * @param InnerRiskInfo
	 * 
	 * @return Int
	 * */
	public int insertInnerRiskInfo(InnerRiskInfo innerRiskInfo) throws CoreException;
	
	/**
	 * 批量导入内部风险信息名单实例（excel文件）
	 * 
	 * @author luzhen.ou
	 * 
	 * @param InnerRiskInfo
	 * 
	 * @return Int
	 * */
	public int insertInnerRiskInfoList(List<InnerRiskInfo> list) throws CoreException;
	
	/**
	 * 查询内部风险信息名单实例通过主键ids
	 * 
	 * @author luzhen.ou
	 * 
	 * @param String autoId
	 * 
	 * @return InnerRiskInfo
	 * */	
	public InnerRiskInfo queryInnerRiskInfo(String autoId) throws CoreException;	
	/**
	 * 修改内部风险信息名单实例
	 * 
	 * @author luzhen.ou
	 * 
	 * @param InnerRiskInfo
	 * 
	 * @return Int
	 * */
	public int updateInnerRiskInfo(InnerRiskInfo innerRiskInfo) throws CoreException;
	
	/**
	 * 条件查询内部风险信息名单的总记录数，供分页使用
	 * 
	 * @author luzhen.ou
	 * 
	 * @param innerRiskInfo
	 * 
	 * @return int
	 * */	
	public int queryInnerRiskInfoCount(InnerRiskInfo innerRiskInfo) throws CoreException;
	
	/**
	 * 条件查询内部风险信息名单，分页查询
	 * 
	 * @author luzhen.ou
	 * 
	 * @param InnerRiskInfo innerRiskInfo，int curNum, int pageNum
	 * 
	 * @return List<InnerRiskInfo>
	 * */	
	public List<InnerRiskInfo> queryInnerRiskInfoList(InnerRiskInfo innerRiskInfo, int curNum, int pageNum)throws CoreException;
	
	/**
	 * 删除内部风险信息名单实例通过主键ids
	 * 
	 * @author luzhen.ou
	 * 
	 * @param innerRiskInfo
	 * 
	 * @return int
	 * */	
	public int deleteInnerRiskInfo(InnerRiskInfo innerRiskInfo)throws CoreException;

	/**
	 * 批量设置身份类风险实例启用或禁用通过主键ids
	 * 
	 * @author luzhen.ou
	 * 
	 * @param innerRiskInfo
	 * 
	 * @return int
	 * */	
	public int updateInnerRiskInfoActive(InnerRiskInfo innerRiskInfo)throws CoreException;

	/**
	 * 查询内部风险信息名单实例操作的总记录数，供分页使用
	 * 
	 * @author luzhen.ou
	 * 
	 * @param innerRiskInfo
	 * 
	 * @return int
	 * */	
	public int queryInnerRiskInfoHistoryCount(InnerRiskInfo innerRiskInfo)throws CoreException;

	/**
	 * 分页查询，分页查询内部风险信息名单实例操作历史记录
	 * 
	 * @author luzhen.ou
	 * 
	 * @param InnerRiskInfo innerRiskInfo，int curNum, int pageNum
	 * 
	 * @return List<InnerRiskInfo>
	 * */	
	public List<InnerRiskInfo> queryInnerRiskInfoHistoryList(InnerRiskInfo innerRiskInfo, int curNum, int pageNum)throws CoreException;
	
	/**
	 * 新增内部风险信息名单实例操作历史记录
	 * 
	 * @author luzhen.ou
	 * 
	 * @param InnerRiskInfo
	 * 
	 * @return Int
	 * */
	public int insertInnerRiskInfoHistory(InnerRiskInfo innerRiskInfo) throws CoreException;
	
	/**
	 * 批量新增内部风险信息名单实例操作历史记录
	 * 
	 * @author luzhen.ou
	 * 
	 * @param List<InnerRiskInfo> list
	 * 
	 * @return int
	 * */
	public int insertInnerRiskInfoHistoryList(List<InnerRiskInfo> list) throws CoreException;

}
