package com.huaxia.cams.modules.police.service;

import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.huaxia.cams.modules.police.domain.NciicForeignerInfo;
import com.huaxia.cams.modules.police.mapper.PoliceForeignerServiceMapper;
@Service("policeForeignerService")
public class PoliceForeignerServiceImpl implements PoliceForeignerService{

	private static final Logger logger = LoggerFactory.getLogger(PoliceForeignerServiceImpl.class);
	@Resource
	private PoliceForeignerServiceMapper policeForeignerServiceMapper;
	@Override
	public int getCountByAppId(String appId) {
		try {
			return policeForeignerServiceMapper.selectCountByAppId(appId);
		} catch (Exception e) {
			logger.error("【审批&三方 &外国人永久居留证】查询该任务是否发起过查询失败，appId="+appId+"Exception:={}",e);
		}
		return 0;
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public void savePoliceForeignerInfo(NciicForeignerInfo nciicForeignerInfo) {

		try {
			policeForeignerServiceMapper.insertPoliceForeignerInfo(nciicForeignerInfo);
		} catch (Exception e) {
			logger.error("【审批&三方 &外国人永久居留证】保存解析报文失败,Exception:={}",e);
		}
	}

	@Override
	public Map<String, String> getBirthAndIDTE(String appId) {
		return policeForeignerServiceMapper.selectBirthAndIDTE(appId);
	}

	@Override
	public Map<String, String> getSupKarBirthAndIDTE(String appId) {
		return policeForeignerServiceMapper.selectSupKarBirthAndIDTE(appId);
	}

}
