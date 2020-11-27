package com.huaxia.plaze.ui.quartz.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huaxia.plaze.common.PageProperty;
import com.huaxia.plaze.ui.quartz.domain.BatchQuartz;
import com.huaxia.plaze.ui.quartz.mapper.BatchQuartzMapper;
import com.huaxia.plaze.ui.quartz.service.BatchQuartzService;
@Service
public class BatchQuartzServiceImpl implements BatchQuartzService{

	private static final Logger logger = Logger.getLogger(BatchQuartzServiceImpl.class);
	@Resource
	private BatchQuartzMapper batchQuartzMapper;
	//查询符合条件的数据量
	@Override
	public int queryTotalCountByPage(PageProperty page) {
		try {
			Map<String, Object> args = new HashMap<String, Object>();
			args.putAll(page.getDataMap());
			return batchQuartzMapper.queryTotalCountByPage(args);
		} catch (Exception e) {
			logger.error("【批量定时】查询符合条件的数据量失败",e);
			return 0;
		}
	}

	//查询分页数据
	@Override
	public List<BatchQuartz> queryListByPage(PageProperty page) {
		try {
			Map<String, Object> args = new HashMap<String, Object>();
			args.putAll(page.getDataMap());
			args.put("pageNo", page.getPageNo());
			args.put("pageSize", page.getPageSize());
			return batchQuartzMapper.queryListByPage(args);
		} catch (Exception e) {
			logger.error("【批量定时】查询分页数据失败", e);
			return null;
		}
		
	}

	//添加
	@Transactional
	@Override
	public int saveQuartz(BatchQuartz quartz) {
		try {
			return batchQuartzMapper.saveQuartz(quartz);
		} catch (Exception e) {
			logger.error("【批量定时】添加失败", e);
		}
		return 0;
	}

	//删除
	@Transactional
	@Override
	public int deleteQuartzById(String id) {
		try {
			return batchQuartzMapper.deleteQuartzById(id);
		} catch (Exception e) {
			logger.error("【批量定时】删除失败", e);
		}
		return 0;
	}

	//根据id查询数据
	@Override
	public BatchQuartz selectQuartzById(String id) {
		try {
			return batchQuartzMapper.selectQuartzById(id);
		} catch (Exception e) {
			logger.error("【批量定时】根据id查询数据失败", e);
		}
		return null;
	}

	//更新
	@Transactional
	@Override
	public int updateQuartz(BatchQuartz quartz) {
		try {
			return batchQuartzMapper.updateQuartz(quartz);
		} catch (Exception e) {
			logger.error("【批量定时】更新数据失败", e);
		}
		return 0;
	}

}
