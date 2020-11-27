package com.huaxia.plaze.ui.tongdun.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.huaxia.opas.util.AppConfig;
import com.huaxia.opas.util.SequenceUtil;
import com.huaxia.plaze.common.PageProperty;
import com.huaxia.plaze.exception.ApplicationException;
import com.huaxia.plaze.ui.system.domain.User;
import com.huaxia.plaze.ui.tongdun.domain.MulBorBatch;
import com.huaxia.plaze.ui.tongdun.domain.MulBorBatchDetail;
import com.huaxia.plaze.ui.tongdun.domain.MulBorBatchFile;
import com.huaxia.plaze.ui.tongdun.domain.MulBorQueryReview;
import com.huaxia.plaze.ui.tongdun.mapper.MulBorBatchMapper;
import com.huaxia.plaze.ui.tongdun.mapper.MulBorConfigurateMapper;
import com.huaxia.plaze.ui.tongdun.service.MulBorBatchDetailService;
import com.huaxia.plaze.ui.tongdun.service.MulBorBatchFileService;
import com.huaxia.plaze.ui.tongdun.service.MulBorBatchService;
@Service("mulBorBatchService")
public class MulBorBatchServiceImpl implements MulBorBatchService{
	@Resource
	private MulBorBatchMapper mulBorTrnBatchMapper;
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public int save(MulBorBatch mulBorTrnBatch) {
		return mulBorTrnBatchMapper.insertObject(mulBorTrnBatch);
	}

	/**
	 * 查询批次表
	 * @param args
	 * @return
	 */
	@Override
	public List<MulBorQueryReview> queryBatchListByPage(PageProperty property) {
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("pageNo", property.getPageNo());
		args.put("pageSize", property.getPageSize());
		args.putAll(property.getDataMap());
		return mulBorTrnBatchMapper.selectBatchListByPage(args);
	}
	/**
	 * 查询总记录数目
	 * @param args
	 * @return
	 */
	@Override
	public int queryBatchListCountByPage(PageProperty property) {
		Map<String, Object> args = new HashMap<String, Object>();
		args.putAll(property.getDataMap());
		return mulBorTrnBatchMapper.selectBatchListCountByPage(args);
	}
}
