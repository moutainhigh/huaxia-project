package com.huaxia.opas.service.record;

import java.util.Map;

/**
 * 后台菜单操作记录留痕
 * @author wangyang
 * @since 2020-10-10
 *
 */
public interface OperaMarkService {
	
	/**
	 * 操作记录入库业务
	 * @param paramMap 参数集合
	 */
	public boolean insertRecord(Map<String, Object> paramMap);
	
}
