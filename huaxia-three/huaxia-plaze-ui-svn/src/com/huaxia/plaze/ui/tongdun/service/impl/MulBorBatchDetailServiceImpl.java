package com.huaxia.plaze.ui.tongdun.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.huaxia.plaze.ui.tongdun.domain.MulBorBatchDetail;
import com.huaxia.plaze.ui.tongdun.domain.MulBorQueryReview;
import com.huaxia.plaze.ui.tongdun.mapper.MulBorBatchDetailMapper;
import com.huaxia.plaze.ui.tongdun.service.MulBorBatchDetailService;
@Service("mulBorBatchDetailService")
public class MulBorBatchDetailServiceImpl implements MulBorBatchDetailService{
	@Resource
	private MulBorBatchDetailMapper mulBorTrnBatchDetailMapper;
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public int save(MulBorBatchDetail mulBorTrnBatchDetail) {
		// TODO Auto-generated method stub
		return mulBorTrnBatchDetailMapper.insertObject(mulBorTrnBatchDetail);
	}
	/**
	 * 查询状态，任务类型
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)

	@Override
	public List<Map<String, Object>> findFormularyNumTasksByType(Map<String, Object> params) {
		return mulBorTrnBatchDetailMapper.selectFormularyNumTasksByType(params);
	}
	/**
	 * 更改状态
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public int updateSingleTask(String uuId, String status) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("uuId", uuId);
		params.put("status", status);
		return mulBorTrnBatchDetailMapper.updateSingleTask(params);
	}
	@Override
	public List<MulBorQueryReview> queryDetailListByPage(String batchFileId) {
		// TODO Auto-generated method stub
		return mulBorTrnBatchDetailMapper.selectDetailListByPage(batchFileId);
	}
}
