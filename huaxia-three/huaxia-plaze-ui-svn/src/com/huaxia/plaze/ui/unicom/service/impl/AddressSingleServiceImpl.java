package com.huaxia.plaze.ui.unicom.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.huaxia.plaze.common.PageProperty;
import com.huaxia.plaze.ui.setting.domain.QueryConfiguration;
import com.huaxia.plaze.ui.setting.mapper.QueryConfigurateMapper;
import com.huaxia.plaze.ui.unicom.domain.AddressData;
import com.huaxia.plaze.ui.unicom.domain.AddressSingle;
import com.huaxia.plaze.ui.unicom.mapper.AddressQueryMapper;
import com.huaxia.plaze.ui.unicom.mapper.AddressSingleMapper;
import com.huaxia.plaze.ui.unicom.service.AddressSingleService;

@Service("addressSingleService")
public class AddressSingleServiceImpl implements AddressSingleService {

	private final static Logger logger = LoggerFactory.getLogger(AddressSingleServiceImpl.class);

	@Resource
	private AddressSingleMapper addressSingleMapper;

	@Resource
	private QueryConfigurateMapper queryConfigurateMapper;

	@Resource
	private AddressQueryMapper addressQueryMapper;

	@Override
	public int queryCountByMobile(Map args) {
		return addressSingleMapper.selectCountByMobile(args);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public int save(AddressSingle addressSingle) {
		// 返回值1：成功 0：失败 -1：查询未在时间段内 -2：查询数量已达到限定值 -3：没有设置查询数量
		int allow = isAllowQuery("00", addressSingle.getApiKey());
		if (allow == 1) {// 允许查询
			addressSingleMapper.insert(addressSingle);
			return allow;
		}
		return allow;
	}

	/**
	 * 联通地址类信息 单条实时查询 数量限制校验
	 * 
	 * @param channelcode
	 * @param apiKey
	 * @return 数量限制校验 1：成功 0：失败 -1：查询未在时间段内 -2：查询数量已达到限定值
	 */
	private int isAllowQuery(String channelcode, String apiKey) {
		int result = 1;
		String sourceCode = "";
		Map<String, Object> args = new HashMap<>();
		if (apiKey.equals("360015")) {
			// 工作地址
			sourceCode = "001102";
		} else {
			// 居住地址
			sourceCode = "001103";
		}
		// 00 代表单条查询
		args.put("sourceCode", sourceCode);
		args.put("channelCode", channelcode);
		QueryConfiguration queryTask = queryConfigurateMapper.selectSettingByTaskType(args);

		if (queryTask != null) {
			// 判断是否在可查时间范围
			Date date = new Date();
			if (date.getTime() < queryTask.getStartTime().getTime()
					|| date.getTime() > queryTask.getEndTime().getTime()) {

				result = -1;
				return result;
			}
			args.clear();
			args.put("source_code_num", sourceCode);
			args.put("channel_code_num", channelcode);
			// 调用存储过程验证，查询数量是否已达限制
			try {
				if ("001102".equals(sourceCode)) {
					addressQueryMapper.query001102ResultByResource(args);
				} else {
					addressQueryMapper.query001103ResultByResource(args);
				}
				String v_set = args.get("v_set").toString();
				String end_num = args.get("end_num").toString();
				if (v_set.equals("111111")) {
					if (end_num.equals("1")) {
						logger.info("联通地址类信息实时查询 可以进行查询！");
						result = 1;
					} else {
						logger.info("联通地址类信息实时查询 超出数量限制！");
						result = -2;
					}
				} else {
					logger.info("联通地址类信息实时查询 从数据库调用查询数量方法：调用" + sourceCode + "的存储过程,失败！");
					result = 0;
				}

			} catch (Exception e) {
				logger.error("联通地址类信息实时查询 从数据库调用查询数量方法：调用存储过程,异常！");
				result = 0;
			}
		}
		return result;
	}

	/**
	 * 更新单条查询状态
	 */
	@Override
	public int updateStatusById(String trnId, String string) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", trnId);
		params.put("status", string);
		return addressSingleMapper.updateStatusById(params);
	}

	@Override
	public AddressData querySingleResultByTrnid(String trnId) {
		return addressSingleMapper.selectSingleResultByTrnId(trnId);
	}

	@Override
	public int queryHistoryListCountByPage(String mobile) {
		return addressSingleMapper.queryHistoryCountByMobile(mobile);
	}

	@Override
	public List<AddressData> queryHistoryDataByPage(PageProperty page) {
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("pageNo", page.getPageNo());
		args.put("pageSize", page.getPageSize());
		args.putAll(page.getDataMap());
		List<AddressData> list = addressSingleMapper.queryHistoryDataByPage(args);
		for (AddressData ad : list) {
			if ("360015".equals(ad.getApiKey())) {
				ad.setApiKey("工作地址");
			} else if ("360016".equals(ad.getApiKey())) {
				ad.setApiKey("居住地址");
			}
		}
		return list;
	}

	@Override
	public List<Map<String, Integer>> queryCountByTime(Map<String, Object> args) {
		return addressQueryMapper.selectCountByTime(args);
	}

}
