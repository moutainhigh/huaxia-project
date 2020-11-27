package com.huaxia.plaze.ui.fico.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.huaxia.plaze.common.PageProperty;
import com.huaxia.plaze.ui.fico.domain.FicoRiskLevel;
import com.huaxia.plaze.ui.fico.mapper.FicoHistoryMapper;
import com.huaxia.plaze.ui.fico.service.FicoHistoryService;

@Service("ficoHistoryService")
public class FicoHistoryServiceImpl implements FicoHistoryService{
	
	@Resource
	private FicoHistoryMapper ficoHistoryMapper;
	
	@Override
	public List<FicoRiskLevel> selectListByPage(PageProperty page) {
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("pageNo", page.getPageNo());
		args.put("pageSize", page.getPageSize());
		args.putAll(page.getDataMap());

		return ficoHistoryMapper.selectListByPage(args);
	}	
	// 分页查询总数
	@Override
	public int queryListCountByPage(PageProperty page) {
		Map<String, Object> args = new HashMap<String, Object>();
		args.putAll(page.getDataMap());
		return ficoHistoryMapper.selectListCountByPage(args);
	}
}
	