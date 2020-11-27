package com.huaxia.plaze.ui.pboc.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.huaxia.plaze.common.PageProperty;
import com.huaxia.plaze.ui.pboc.domain.HistoryQuery;
import com.huaxia.plaze.ui.pboc.mapper.HistoryQueryMapper;
import com.huaxia.plaze.ui.pboc.service.HistoryQueryService;

//历史数据查询业务处理层
@Service("historyQueryService")
public class HistoryQueryServiceImpl implements HistoryQueryService {

	@Resource
	private HistoryQueryMapper<HistoryQuery> historyQueryMapper;

	// 分页查询总数
	@Override
	public int queryListCountByPage(PageProperty page) {
		Map<String, Object> args = new HashMap<String, Object>();
		args.putAll(page.getDataMap());
		return historyQueryMapper.selectListCountByPage(args);
	}

	// 分页查询记录
	@Override
	public List<HistoryQuery> queryListByPage(PageProperty page) {
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("pageNo", page.getPageNo());
		args.put("pageSize", page.getPageSize());
		args.putAll(page.getDataMap());

		return historyQueryMapper.selectListByPage(args);
	}

}
