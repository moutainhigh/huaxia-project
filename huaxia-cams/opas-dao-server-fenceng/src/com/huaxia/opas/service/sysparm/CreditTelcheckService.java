package com.huaxia.opas.service.sysparm;

import java.util.List;
import java.util.Map;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.decision.CreditTelcheckList;
import com.huaxia.opas.domain.sysparm.BatchfileInfo;


/**
 * 名单库管理_征信电话核查白名单service层接口的相关方法
 * 
 * @author luzhen.ou
 * 
 * @since 2017-03-15
 * 
 * @version 1.0
 */
public interface CreditTelcheckService {
	
	/**
	 * 批量设置征信电话核查白名单实例启用或禁用
	 *  
	 * @author luzhen.ou
	 */	
	int updateCreditTelcheckActive(CreditTelcheckList creditTelcheckList) throws CoreException;
	
	/**
	 * 删除征信电话核查白名单实例,通过主键集合ids
	 *  
	 * @author luzhen.ou
	 */	
	int deleteCreditTelcheck(CreditTelcheckList creditTelcheckList) throws CoreException;
	
	/**
	 * 修改征信电话核查白名单实例
	 *  
	 * @author luzhen.ou
	 */
	int updateCreditTelcheck(CreditTelcheckList creditTelcheckList) throws CoreException;
	
	/**
	 * 新增征信电话核查白名单实例
	 * 
	 * @author luzhen.ou
	 * */
	int insertCreditTelcheck(CreditTelcheckList creditTelcheckList) throws CoreException;
	
	/**
	 * 分页查询征信电话核查白名单实例列表
	 * 
	 * @author luzhen.ou
	 * */	
	Map<String, Object> queryCreditTelcheckDomainList(CreditTelcheckList creditTelcheckList, int curPage, int pageNum) throws CoreException;
	
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
	
}
