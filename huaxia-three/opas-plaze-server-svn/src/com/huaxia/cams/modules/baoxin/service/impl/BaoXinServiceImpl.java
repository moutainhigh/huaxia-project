package com.huaxia.cams.modules.baoxin.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.huaxia.cams.modules.baoxin.domain.BaoXinBase;
import com.huaxia.cams.modules.baoxin.mapper.BaoXinMapper;
import com.huaxia.cams.modules.baoxin.service.BaoXinService;
@Service
public class BaoXinServiceImpl implements BaoXinService{

	private static final Logger logger  = LoggerFactory.getLogger(BaoXinServiceImpl.class);
	@Resource
	private BaoXinMapper baoXinMapper;
	@Override
	public int selectCountByAppId(String appId) {
		try {
			return baoXinMapper.selectCountByAppId(appId);
		} catch (Exception e) {
			logger.error("保信汽车查询该appid对应的数据条数", e);
		}
		return 0;
	}
	@Override
	public int saveBase(BaoXinBase base) {
		try {
			return baoXinMapper.saveBase(base);
		} catch (Exception e) {
			logger.error("保信汽车保存解析报文失败", e);
		}
		return 0;
	}

}
