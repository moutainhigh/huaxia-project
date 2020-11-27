package com.huaxia.plaze.ui.report.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.huaxia.plaze.common.PageProperty;
import com.huaxia.plaze.ui.report.service.PbocReportService;
import com.huaxia.plaze.ui.setting.mapper.PbocQueryMapper;

@Service("pbocReportService")
public class PbocReportServiceImpl implements PbocReportService {

	@Resource
	private PbocQueryMapper pbocQueryMapper;
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public int generateCountReport() {
		return pbocQueryMapper.insertCountReport();
	}

	@Override
	public int queryListCountReportByPage(PageProperty property) {
		Map<String, Object> args = new HashMap<String, Object>();
		return pbocQueryMapper.selectListCountReportByPage(args);
	}

	@Override
	public List<Map<String, Object>> queryListReportByPage(PageProperty property) {
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("pageNo", property.getPageNo());
		args.put("pageSize", property.getPageSize());
		return pbocQueryMapper.selectListReportByPage(args);
	}

}
