package com.huaxia.opas.dao.archive;

import java.util.List;

import com.huaxia.opas.domain.archive.ArBatch;
import com.huaxia.opas.domain.archive.ArDetail;

public interface ArBatchDao {
	
	public int queryArchiveCount(ArBatch arBatch);
	
	public List<ArBatch> queryArchiveList(ArBatch arBatch, int curNum, int pageNum);
	
	public int queryArDetailsCount(ArDetail arDetail);
	
	public List<ArDetail> queryArDetailsList(ArDetail arDetail, int curNum, int pageNum);
}
