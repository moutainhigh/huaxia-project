package com.huaxia.opas.service.sysparm;

import java.util.List;
import java.util.Map;
import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.sysparm.BatchfileInfo;
import com.huaxia.opas.domain.sysparm.CreditWhiteList;

/**
 * 名单库管理_标准卡征信信息名单service层接口的相关方法
 * 
 * @author luzhen.ou
 * 
 * @since 2017-03-16
 * 
 * @version 1.0
 */
public interface CreditInfoService {
	
	/**
	 * 批量设置标准卡征信信息名单实例启用或禁用
	 *  
	 * @author luzhen.ou
	 */	
	int updateCreditInfoActive(CreditWhiteList creditWhiteList) throws CoreException;
	
	/**
	 * 删除标准卡征信信息名单实例,通过主键集合ids
	 *  
	 * @author luzhen.ou
	 */	
	int deleteCreditInfo(CreditWhiteList creditWhiteList) throws CoreException;
	
	/**
	 * 修改标准卡征信信息名单实例
	 *  
	 * @author luzhen.ou
	 */
	int updateCreditInfo(CreditWhiteList creditWhiteList) throws CoreException;
	
	/**
	 * 新增标准卡征信信息名单实例
	 * 
	 * @author luzhen.ou
	 * */
	int insertCreditInfo(CreditWhiteList creditWhiteList) throws CoreException;
	
	/**
	 * 分页查询标准卡征信信息名单实例列表
	 * 
	 * @author luzhen.ou
	 * */	
	Map<String, Object> queryCreditInfoList(CreditWhiteList creditWhiteList, int curPage, int pageNum) throws CoreException;
	
	/**
	 * 分页查询标准卡征信信息名单实例操作历史记录列表
	 * 
	 * @author luzhen.ou
	 * */
	Map<String, Object> queryCreditInfoHistoryList(CreditWhiteList creditWhiteList, int curPage, int pageNum) throws CoreException;
	
	/**
	 * 批量导入标准卡征信信息名单实例Excel
	 * 
	 * @author luzhen.ou
	 * */
	int insertCreditInfoList(List<CreditWhiteList> list,BatchfileInfo batchfileInfo) throws CoreException;

}
