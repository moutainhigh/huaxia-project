package com.huaxia.opas.service.record.impl;

import java.io.Serializable;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.huaxia.opas.common.OperaMarkConstant;
import com.huaxia.opas.dao.record.OperaMarkDao;
import com.huaxia.opas.domain.record.OperaRecord;
import com.huaxia.opas.service.AbstractService;
import com.huaxia.opas.service.record.OperaMarkService;
import com.huaxia.opas.util.UUIDGenerator;

public class OperaMarkServiceImpl extends AbstractService implements OperaMarkService,Serializable {

	private static final long serialVersionUID = -6694843438263161610L;

	private final static Logger LOGGER = LoggerFactory.getLogger(OperaMarkServiceImpl.class);

	@Autowired
	private OperaMarkDao operaMarkDao;

	@Override
	public boolean insertRecord(Map<String, Object> paramMap) {
		// 只输出日志，实际业务在AOP中实现
		LOGGER.info("【OperaMarkService】 start，paramMap:{}", paramMap);

		// 获取前台页面的条形码和菜单名
		String appId = null;
		String menuName = null;
		String userId = null;

		if (paramMap.containsKey("appId"))
			appId = (String) paramMap.get("appId");
		if (paramMap.containsKey("menuName"))
			menuName = (String) paramMap.get("menuName");
		if (paramMap.containsKey("userId"))
			userId = (String) paramMap.get("userId");

		menuName = !StringUtils.isEmpty(menuName) ? menuName : OperaMarkConstant.DEFAULT_MENU;

		// 组装参数
		OperaRecord record = new OperaRecord();
		record.setId(UUIDGenerator.getUUID());
		record.setAppId(appId);
		if (null != paramMap)
			record.setParams(paramMap.toString());
		record.setUserIp((String) paramMap.get("userIp"));
		record.setModule(menuName);
		record.setType(OperaMarkConstant.MENU_TYPE);
		record.setUserCode(userId);
		// 入库
		int result = operaMarkDao.insertRecord(record);
		LOGGER.info("【OperaMarkService】后台用户操作记录入库结果:{}，申请件ID:{}", result > 0 ? "[成功]" : "[失败]", appId);
		if (result > 0) {
			return true;
		}
		return false;
	}

}
