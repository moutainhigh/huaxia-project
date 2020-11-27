package com.huaxia.plaze.ui.setting.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.huaxia.plaze.ui.setting.mapper.PbocQueryMapper;
import com.huaxia.plaze.ui.setting.mapper.QueryAuthorizeMapper;
import com.huaxia.plaze.ui.setting.service.PbocQueryService;

@Service("pbocQueryService")
public class PbocQueryServiceImpl implements PbocQueryService {

	@Resource
	private PbocQueryMapper pbocQueryMapper;
	
	@Resource
	private QueryAuthorizeMapper queryAuthorizeMapper;



	@Override
	public List<Map<String, Integer>> queryCountByParams(Map<String, Object> args) {
		return pbocQueryMapper.selectCountByParams(args);
	}

	
	@Override
	public Map<String, String> queryAuthorizeSettingByParams(Map<String, Object> args) {
		return queryAuthorizeMapper.selectAuthorizeSettingByParams(args);
	}

	@Override
	public int mergeQueryAuthorizeSetting(Map<String, Object> args) {
		int result = 0;
		Map<String, String> queryAuthorize = queryAuthorizeMapper.selectAuthorizeSettingByParams(args);
		if (queryAuthorize != null && queryAuthorize.size() > 0) {
			args.put("querySource", queryAuthorize.get("SOURCE"));
			result += queryAuthorizeMapper.updateAuthorizeSetting(args);
		} else {
			result += queryAuthorizeMapper.insertAuthorizeSetting(args);
		}
		return result;
	}

}
