package com.huaxia.plaze.ui.tongdun.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.huaxia.plaze.common.PageProperty;
import com.huaxia.plaze.ui.system.domain.User;
import com.huaxia.plaze.ui.tongdun.domain.MulBorBatch;
import com.huaxia.plaze.ui.tongdun.domain.MulBorQueryReview;

public interface MulBorBatchService {
	
	int save(MulBorBatch mulBorTrnBatch);
	
	List<MulBorQueryReview> queryBatchListByPage(PageProperty page);
	
	int queryBatchListCountByPage(PageProperty page);
}
