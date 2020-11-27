package com.huaxia.opas.dao.archive;

import java.util.List;
import java.util.Map;

import com.huaxia.opas.domain.archive.ArBatch;
import com.huaxia.opas.domain.archive.ArDetail;

public interface ArQueueDao {

	public int queryArQueueCount(ArDetail arDetail);

	public List<ArDetail> queryArQueueList(ArDetail arDetail, int curNum, int pageNum);

	public int updateArQueueToDel(ArDetail arDetail);

	public int updateFileNoForAr(ArDetail arDetail);

	public int insertArBatch(ArBatch arBatch);

	public int updAndInsAll(ArDetail arDetail);

	public int updateAppFlag(ArDetail arDetail);

	// 查询批量归档的件
	public List<ArDetail> queryArDetailList(ArDetail arDetail);

	// 修改C1表中的字段
	public void updateC1(ArDetail arDetail);

	// 查询套卡标志
	public Map<String, Object> queryMatchingFlag(Map appIdMap);

	// 套卡查询同步标志
	public Map<String, Object> querysynFlag(Map appIdMap);

	// 修改同步标志
	public void updatesynFlag(Map appIdMap);
	
	int updateArchiveBatchNo(Map<String, String> params);
}
