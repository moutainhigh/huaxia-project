package com.huaxia.plaze.ui.tongdun.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.huaxia.plaze.ui.setting.domain.QueryConfiguration;
import com.huaxia.plaze.ui.tongdun.mapper.MulBorConfigurateMapper;
import com.huaxia.plaze.ui.tongdun.service.MulBorConfigurateService;

@Service("mulBorConfigurateService")
public class MulBorConfigurateServiceImpl implements MulBorConfigurateService {
	
	private final static Logger logger = LoggerFactory.getLogger(MulBorConfigurateServiceImpl.class);

	@Resource
	private MulBorConfigurateMapper mulBorConfigurateMapper;

	@Override
	public List<Map<String, Integer>> queryCountByParams(Map<String, Object> args) {
		return mulBorConfigurateMapper.selectCountByParams(args);
	}


	@Override
	 public int  isAllowSingleQuery(String queryFrom){
		int result = 1;
		    Map<String, Object> args = new HashMap<>();
		    if("0".equals(queryFrom)){
		    	args.put("sourceCode", "001500");
				args.put("channelCode", "00");
		    }else{
		    	args.put("sourceCode", "001500");
				args.put("channelCode", "01");
		    }
		    QueryConfiguration queryCount = mulBorConfigurateMapper.selectSettingByTaskType(args);
		    if(queryCount != null){
		    	Date date = new Date();
				if(date.getTime() < queryCount.getStartTime().getTime() || date.getTime() > queryCount.getEndTime().getTime() ){
					result = -1;
			} else {
				args.clear();
				args.put("startTime", queryCount.getStartTime());
				args.put("endTime", queryCount.getEndTime());
				args.put("queryFrom", queryFrom);
				if ("0".equals(queryFrom)) {
					args.clear();
					args.put("source_code_num", "001500");
					args.put("channel_code_num", "00");
					// 调用存储过程进行验证，是否可以进行实时查询
					try {
						mulBorConfigurateMapper.selectSingleCountByReferTime(args);
						String v_set = args.get("v_set").toString();
						int end_num = Integer.parseInt(args.get("end_num").toString());
						if (end_num >= 1) {
							logger.info("同盾单条实时查询 可以进行查询！");
							result = 1;
						} else {
							logger.info("同盾单条实时查询 超出数量限制！");
							result = -2;
						}
					} catch (Exception e) {
						logger.error("同盾实时查询 从数据库调用查询数量方法：调用存储过程:ds_mulbor_001500_count,异常！");
						result = 0;
					}
				} else {
					int count = mulBorConfigurateMapper.selectBatchCountByReferTime(args);
					if (count > queryCount.getMaxCount()) {
						logger.info("同盾批量查询 超出数量限制！");
						result = -2;
					}
				}
			}
		    }
		    return result;
	 }
}
