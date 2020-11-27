package com.huaxia.plaze.ui.pboc.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.huaxia.plaze.common.PageProperty;
import com.huaxia.plaze.ui.pboc.domain.SysLogQuery;
import com.huaxia.plaze.ui.pboc.mapper.SysLogQueryMapper;
import com.huaxia.plaze.ui.pboc.service.LogQueryService;

//批查询日志监控业务处理层
@Service("sysLogQueryService")
public class LogQueryServiceImpl implements LogQueryService {

	// 批量导入的Mapper层
	@Resource
	private SysLogQueryMapper<SysLogQuery> sysLogQueryMapper;

	// 插入单条记录
	@Override
	public int saveObject(SysLogQuery query) {
		return sysLogQueryMapper.insertObject(query);
	}

	// 分页查询总数
	@Override
	public int queryListCountByPage(PageProperty page) {
		Map<String, Object> args = new HashMap<String, Object>();
		args.putAll(page.getDataMap());
		return sysLogQueryMapper.selectListCountByPage(args);
	}

	// 人行异常查询监控
	@Override
	public int queryListCountByPageEx(PageProperty page) {
		Map<String, Object> args = new HashMap<String, Object>();
		args.putAll(page.getDataMap());
		return sysLogQueryMapper.selectListCountByPageEx(args);
	}

	// 分页查询记录
	@Override
	public List<SysLogQuery> queryListByPage(PageProperty page) {
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("pageNo", page.getPageNo());
		args.put("pageSize", page.getPageSize());
		args.putAll(page.getDataMap());

		return sysLogQueryMapper.selectListByPage(args);
	}

	// 人行异常查询
	@Override
	public List<SysLogQuery> queryListByPageEx(PageProperty page) {
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("pageNo", page.getPageNo());
		args.put("pageSize", page.getPageSize());
		args.putAll(page.getDataMap());

		return sysLogQueryMapper.selectListByPageEx(args);
	}

	// 下载结果集
	@Override
	public List<SysLogQuery> queryList(PageProperty page) {
		Map<String, Object> args = new HashMap<String, Object>();
		args.putAll(page.getDataMap());
		return sysLogQueryMapper.selectListBy(args);
	}

	@Override
	public List<Map<String, Object>> queryListByArguments(Map<String, Object> args) {
		return sysLogQueryMapper.selectListByArguments(args);
	}

}
