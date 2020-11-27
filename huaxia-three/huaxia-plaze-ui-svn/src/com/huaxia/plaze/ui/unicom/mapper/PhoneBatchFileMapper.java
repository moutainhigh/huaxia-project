package com.huaxia.plaze.ui.unicom.mapper;

import java.util.List;

import com.huaxia.plaze.ui.unicom.domain.PhoneBatchFile;

public interface PhoneBatchFileMapper {

	int savePhoneTrnBatchFile(PhoneBatchFile phoneTrnBatchFile);

	List<PhoneBatchFile> selectFileDetail(String batchFileId);
}
