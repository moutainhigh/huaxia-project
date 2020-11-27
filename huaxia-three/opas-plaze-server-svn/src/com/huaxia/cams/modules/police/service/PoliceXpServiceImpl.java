package com.huaxia.cams.modules.police.service;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.huaxia.cams.modules.police.domain.PoliceXpInfo;
import com.huaxia.cams.modules.police.mapper.PoliceXpServiceMapper;
@Service("policeXpService")
public class PoliceXpServiceImpl implements PoliceXpService{

	private static final Logger logger = LoggerFactory.getLogger(PoliceXpServiceImpl.class);
	@Resource
	private PoliceXpServiceMapper policeXpServiceMapper;
	@Override
	public int getCountByAppId(String appId) {
		try {
			return policeXpServiceMapper.selectCountByAppId(appId);
		} catch (Exception e) {
			logger.error("【审批&三方 &人像比对】查询该任务是否发起过查询失败，appId="+appId+"Exception:={}",e);
		}
		return 0;
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public void savePoliceXpInfo(PoliceXpInfo policeXpInfo) {

		try {
			policeXpServiceMapper.insertPoliceXpInfo(policeXpInfo);
		} catch (Exception e) {
			logger.error("【审批&三方 &人像比对】保存解析报文失败,Exception:={}",e);
		}
	}

}
