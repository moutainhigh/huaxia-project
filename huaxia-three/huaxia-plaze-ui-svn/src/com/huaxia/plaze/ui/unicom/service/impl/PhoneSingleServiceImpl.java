package com.huaxia.plaze.ui.unicom.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.huaxia.plaze.ui.setting.domain.QueryConfiguration;
import com.huaxia.plaze.ui.setting.mapper.QueryConfigurateMapper;
import com.huaxia.plaze.ui.unicom.domain.PhoneSingle;
import com.huaxia.plaze.ui.unicom.domain.UnicomPhoneData;
import com.huaxia.plaze.ui.unicom.mapper.PhoneQueryMapper;
import com.huaxia.plaze.ui.unicom.mapper.PhoneSingleMapper;
import com.huaxia.plaze.ui.unicom.service.PhoneSingleService;

@Service("phoneSingleService")
public class PhoneSingleServiceImpl implements PhoneSingleService {

	private final static Logger logger = LoggerFactory.getLogger(PhoneSingleServiceImpl.class);

	@Resource
	private PhoneSingleMapper phoneSingleMapper;

	@Resource
	private QueryConfigurateMapper queryConfigurateMapper;

	@Resource
	private PhoneQueryMapper phoneQueryMapper;

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public int save(PhoneSingle phoneTrnSingle) {
		// 返回值1：成功 0：失败 -1：查询未在时间段内 -2：查询数量已达到限定值 -3：没有设置查询数量
		int allow = isAllowQuery("00");
		if (allow == 1) {
			// 查询成功返回值为1
			phoneSingleMapper.insert(phoneTrnSingle);
			return allow;
		}
		return allow;
	}

	@Override
	public int updateStatusById(String trnId, String status) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", trnId);
		params.put("status", status);
		return phoneSingleMapper.updateStatusById(params);
	}

	/*
	 * 是否允许手机实名认证查询
	 * 
	 * @return 2:24小时数据有效性 1：成功 0：失败 -1：查询未在时间段内 -2：查询数量已达到限定值
	 */
	private int isAllowQuery(String channelcode) {
		int result = 1;
		Map<String, Object> args = new HashMap<>();
		// 00 单条查询
		args.put("sourceCode", "001100");
		args.put("channelCode", channelcode);

		QueryConfiguration queryTask = queryConfigurateMapper.selectSettingByTaskType(args);
		if (queryTask != null) {
			Date date = new Date();
			if (date.getTime() < queryTask.getStartTime().getTime()
					|| date.getTime() > queryTask.getEndTime().getTime()) {

				result = -1;
			}
			args.clear();
			args.put("source_code_num", "001100");
			args.put("channel_code_num", channelcode);
			// 调用存储过程进行验证，是否可以进行实时查询
			try {
				phoneQueryMapper.selectQueryResultBySource(args);
				String v_set = args.get("v_set").toString();
				String end_num = args.get("end_num").toString();
				if (v_set.equals("111111")) {
					if (end_num.equals("1")) {
						logger.info("手机实名制实时查询 可以进行查询！");
						result = 1;
					} else {
						logger.info("手机实名制实时查询 超出数量限制！");
						result = -2;
					}
				} else {
					logger.info("手机实名制实时查询 从数据库调用查询数量方法：调用存储过程:datasource_unicom_count,失败！");
					result = 0;
				}
			} catch (Exception e) {
				logger.error("手机实名制实时查询 从数据库调用查询数量方法：调用存储过程:datasource_id5_count,异常！");
				result = 0;
			}
		}
		return result;
	}

	@Override
	public int queryCountByParams(Map<String, Object> args) {
		return phoneSingleMapper.selectCountByParams(args);
	}

	@Override
	public UnicomPhoneData querySingleResultByTrnid(String trnId) {
		return phoneSingleMapper.selectSingleResultByTrnid(trnId);
	}
}
