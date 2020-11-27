package com.huaxia.opas.dao;

import java.util.Map;
/**
 * 
 * @author gaohui
 * 人行特殊交易类型
 */
public interface PBOCSpecialTradeInfoDao {
	
	int insertBatch(Map<String, Object> parameters);
 
}
