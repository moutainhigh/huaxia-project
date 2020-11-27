package com.huaxia.plaze.ui.bairong.mapper;

import java.util.List;

import com.huaxia.plaze.ui.bairong.domain.ApplyLoanStrBatch;
import com.huaxia.plaze.ui.bairong.domain.ApplyLoanStrBatchDetail;

public interface ApplyLoanStrBatchDetailMapper {

	int insertObject(ApplyLoanStrBatchDetail applyLoanStrTrnBatchDetail);

	List<ApplyLoanStrBatchDetail> selectBatchDetailListByPage(String batchFileId);
}
