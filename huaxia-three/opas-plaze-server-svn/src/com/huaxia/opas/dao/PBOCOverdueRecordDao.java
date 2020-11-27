package com.huaxia.opas.dao;

import java.util.Map;

public interface PBOCOverdueRecordDao {
	
	int insertBatch(Map<String, Object> parameters);
 
}
