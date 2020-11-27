package com.huaxia.plaze.ui.tongdun.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.huaxia.plaze.ui.tongdun.domain.MulBorBatchFile;
import com.huaxia.plaze.ui.tongdun.mapper.MulBorBatchFileMapper;
import com.huaxia.plaze.ui.tongdun.service.MulBorBatchFileService;


@Service("mulBorBatchFileService")
public class MulBorBatchFileServiceImpl implements MulBorBatchFileService {
	@Resource
	private MulBorBatchFileMapper mulBorTrnBatchFileMapper;

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public int save(MulBorBatchFile mulBorTrnBatchFile) {
		return mulBorTrnBatchFileMapper.insertObject(mulBorTrnBatchFile);
	}

	@Override
	public List<MulBorBatchFile> queryDetailListByPage(String batchId) {
		return mulBorTrnBatchFileMapper.selectDetailListByPage(batchId);
	}
}
