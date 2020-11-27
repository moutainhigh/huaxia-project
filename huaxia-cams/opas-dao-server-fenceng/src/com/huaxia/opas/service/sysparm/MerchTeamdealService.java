package com.huaxia.opas.service.sysparm;

import java.util.List;
import java.util.Map;
import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.decision.MerchTeamdealList;
import com.huaxia.opas.domain.sysparm.BatchfileInfo;


/**
 * 名单库管理_商户团办名单service层接口的相关方法
 * 
 * @author luzhen.ou
 * 
 * @since 2017-03-18
 * 
 * @version 1.0
 */
public interface MerchTeamdealService {
	
	/**
	 * 批量设置商户团办名单实例启用或禁用
	 *  
	 * @author luzhen.ou
	 */	
	int updateMerchTeamdealActive(MerchTeamdealList merchTeamdealList) throws CoreException;
	
	/**
	 * 删除商户团办名单实例,通过主键集合ids
	 *  
	 * @author luzhen.ou
	 */	
	int deleteMerchTeamdeal(MerchTeamdealList merchTeamdealList) throws CoreException;
	
	/**
	 * 修改商户团办名单实例
	 *  
	 * @author luzhen.ou
	 */
	int updateMerchTeamdeal(MerchTeamdealList merchTeamdealList) throws CoreException;
	
	/**
	 * 新增商户团办名单实例
	 * 
	 * @author luzhen.ou
	 * */
	int insertMerchTeamdeal(MerchTeamdealList merchTeamdealList) throws CoreException;
	
	/**
	 * 分页查询商户团办名单实例列表
	 * 
	 * @author luzhen.ou
	 * */	
	Map<String, Object> queryMerchTeamdealList(MerchTeamdealList merchTeamdealList, int curPage, int pageNum) throws CoreException;
	
	/**
	 * 分页查询商户团办名单实例操作历史记录列表
	 * 
	 * @author luzhen.ou
	 * */
	Map<String, Object> queryMerchTeamdealHistoryList(MerchTeamdealList merchTeamdealList, int curPage, int pageNum) throws CoreException;
	
	/**
	 * 批量导入商户团办名单实例Excel
	 * 
	 * @author luzhen.ou
	 * @author wangtao
	 * @throws CoreException 
	 * */
	int insertMerchTeamdealList(List<MerchTeamdealList> list, BatchfileInfo batchfileInfo) throws CoreException;

}
