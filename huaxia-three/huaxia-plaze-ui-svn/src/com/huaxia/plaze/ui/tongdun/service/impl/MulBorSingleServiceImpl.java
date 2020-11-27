package com.huaxia.plaze.ui.tongdun.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.huaxia.plaze.ui.tongdun.domain.MulBorSingle;
import com.huaxia.plaze.ui.tongdun.mapper.MulBorSingleMapper;
import com.huaxia.plaze.ui.tongdun.service.MulBorSingleService;

@Service("mulBorSingleService")
public class MulBorSingleServiceImpl implements MulBorSingleService {

	@Resource
	private MulBorSingleMapper mulBorSingleMapper;

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public int save(MulBorSingle mulBorTrnSingle) {
		return mulBorSingleMapper.insertObject(mulBorTrnSingle);
	}

	@Override
	public List<Map<String, Object>> findFormularyNumTasksByType(Map<String, Object> params) {
		return mulBorSingleMapper.selectFormularyNumTasksByType(params);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public int updateStatusById(String trnId, String status) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("trnId", trnId);
		params.put("status", status);
		return mulBorSingleMapper.updateStatusById(params);
	}
}
