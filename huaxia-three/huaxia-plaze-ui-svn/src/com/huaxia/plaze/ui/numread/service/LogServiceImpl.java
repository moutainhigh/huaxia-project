package com.huaxia.plaze.ui.numread.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.huaxia.plaze.common.PageProperty;
import com.huaxia.plaze.ui.numread.domain.NumReadLog;
import com.huaxia.plaze.ui.numread.mapper.BatchLogMapper;
/**
 * 人行数字解读 日志
 * @author liyanjie
 *
 */
@Service
public class LogServiceImpl implements LogService {

	
	private final static Logger logger = LoggerFactory.getLogger(LogServiceImpl.class);
	@Resource
	private BatchLogMapper batchLogMapper;
	/**
	 * 分页数量统计
	 */
	@Override
	public int queryLogListCountByPage(PageProperty page) {
		 try {
			 Map<String, Object> args = new HashMap<String, Object>();
			args.putAll(page.getDataMap());
			return batchLogMapper.selectLogCountByPage(args);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return 0;
	}
	/**
	 * 查询分页数据
	 */
	@Override
	public List<NumReadLog> queryLogListByPage(PageProperty page) {
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("pageNo", page.getPageNo());
		args.put("pageSize", page.getPageSize());
		args.putAll(page.getDataMap());
		
		try {
			return batchLogMapper.selectLogListByPage(args);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}

}
