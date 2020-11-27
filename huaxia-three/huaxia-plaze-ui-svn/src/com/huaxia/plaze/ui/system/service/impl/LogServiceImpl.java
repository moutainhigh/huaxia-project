package com.huaxia.plaze.ui.system.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.huaxia.plaze.common.PageProperty;
import com.huaxia.plaze.ui.system.domain.AuthorizeLog;
import com.huaxia.plaze.ui.system.domain.OperateLog;
import com.huaxia.plaze.ui.system.mapper.AuthorizeLogMapper;
import com.huaxia.plaze.ui.system.mapper.OperateLogMapper;
import com.huaxia.plaze.ui.system.service.AuthorizeLogService;
import com.huaxia.plaze.ui.system.service.OperateLogService;

@Service("logService")
public class LogServiceImpl implements AuthorizeLogService, OperateLogService {
	
	@Resource
	private AuthorizeLogMapper authorizeLogMapper;
	
	@Resource
	private OperateLogMapper operateLogMapper;

	@Override
	public int saveOperateLog(OperateLog log) {
		return operateLogMapper.insert(log);
	}

	@Override
	public int saveAuthorizeLog(AuthorizeLog log) {
		return authorizeLogMapper.insert(log);
	}

	@Override
	public int queryListCountByPage(PageProperty property) {
		Map<String, Object> args = new HashMap<String, Object>();
		args.putAll(property.getDataMap());
		return authorizeLogMapper.selectListCountByPage(args);
	}

	@Override
	public List<AuthorizeLog> queryListByPage(PageProperty property) {
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("pageNo", property.getPageNo());
		args.put("pageSize", property.getPageSize());
		args.putAll(property.getDataMap());
		return authorizeLogMapper.selectListByPage(args);
	}

	@Override
	public int queryOperateListCountByPage(PageProperty property) {
		Map<String, Object> args = new HashMap<String, Object>();
		args.putAll(property.getDataMap());
		return operateLogMapper.selectListCountByPage(args);
	}
	@Override
	public List<OperateLog> queryOperateListByPage(PageProperty property) {
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("pageNo", property.getPageNo());
		args.put("pageSize", property.getPageSize());
		args.putAll(property.getDataMap());
		return operateLogMapper.selectListByPage(args);
	}
}
