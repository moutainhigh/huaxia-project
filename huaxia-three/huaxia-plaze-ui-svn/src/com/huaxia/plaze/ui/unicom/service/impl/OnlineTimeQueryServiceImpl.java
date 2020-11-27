package com.huaxia.plaze.ui.unicom.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.huaxia.plaze.ui.unicom.mapper.OnlineTimeQueryMapper;
import com.huaxia.plaze.ui.unicom.service.OnlineTimeQueryService;

@Service("onlineTimeQueryService")
public class OnlineTimeQueryServiceImpl implements OnlineTimeQueryService {

	@Resource
	private OnlineTimeQueryMapper onlineTimeQueryMapper;

	@Override
	public List<Map<String, Integer>> queryCountByParams(Map<String, Object> args) {
		return onlineTimeQueryMapper.selectCountByParams(args);
	}

}
