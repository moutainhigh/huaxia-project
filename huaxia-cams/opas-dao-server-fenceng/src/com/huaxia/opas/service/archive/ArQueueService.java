package com.huaxia.opas.service.archive;

import java.util.List;
import java.util.Map;

import com.huaxia.opas.domain.archive.ArBatch;
import com.huaxia.opas.domain.archive.ArDetail;

public interface ArQueueService {
	
	public List<ArDetail> queryArQueueList(ArDetail arDetail, int curNum, int pageNum) throws Exception;

	public int queryArQueueCount(ArDetail arDetail);

	public int updateArQueueToDel(ArDetail arDetail);

	public int updateFileNoForAr(ArDetail arDetail) throws Exception;

	public int insertArBatch(ArBatch arBatch);

	public int updAndInsAll(ArDetail arDetail);

	public int updateAppFlag(ArDetail arDetail);

	//查询批量归档件
	public List<ArDetail> queryArDetailList(ArDetail arDetail);
	//查询套卡标志
	public Map<String, Object> queryMatchingFlag(Map appIdMap);
	//套卡查询同步标志
	public Map<String, Object> querysynFlag(Map appIdMap);
	//修改同步标志	
	public void updatesynFlag(Map appIdMap) throws Exception;
	
	/** 更新归档批次信息 */
	int updateArchiveBatchNo(Map<String, String> params)throws Exception;
	
}
