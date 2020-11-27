package com.huaxia.plaze.ui.setting.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.huaxia.plaze.common.PageProperty;
import com.huaxia.plaze.ui.setting.domain.QueryExclude;
import com.huaxia.plaze.ui.setting.mapper.QueryExcludeMapper;
import com.huaxia.plaze.ui.setting.service.QueryExcludeService;

@Service("queryExcludeService")
public class QueryExcludeServiceImpl implements QueryExcludeService {
	
	@Resource
	private QueryExcludeMapper<QueryExclude> queryExcludeMapper;

	@Override
	public int queryListCountByPage(PageProperty property) {
		Map<String, Object> args = new HashMap<String, Object>();
		args.putAll(property.getDataMap());
		return queryExcludeMapper.selectListCountByPage(args);
	}

	@Override
	public List<QueryExclude> queryListByPage(PageProperty property) {
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("pageNo", property.getPageNo());
		args.put("pageSize", property.getPageSize());
		args.putAll(property.getDataMap());
		return queryExcludeMapper.selectListByPage(args);
	}

	@Override
	public int save(QueryExclude queryExclude) {
		return queryExcludeMapper.insert(queryExclude);
	}

	@Override
	public int removeById(String id) {
		return queryExcludeMapper.deleteById(id);
	}

}
