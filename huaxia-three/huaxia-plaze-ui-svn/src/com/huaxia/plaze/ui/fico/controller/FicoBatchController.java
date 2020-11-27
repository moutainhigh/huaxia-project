package com.huaxia.plaze.ui.fico.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.huaxia.plaze.common.Cache;
import com.huaxia.plaze.common.PageProperty;
import com.huaxia.plaze.ui.fico.domain.FicoBatch;
import com.huaxia.plaze.ui.fico.domain.FicoBatchDetail;
import com.huaxia.plaze.ui.fico.domain.FicoBatchFile;
import com.huaxia.plaze.ui.fico.service.FicoBatchService;
import com.huaxia.plaze.ui.system.domain.User;
import com.huaxia.util.BatchNoGenerator;
import com.huaxia.util.CacheUtil;
import com.huaxia.util.JacksonUtil;

/**
 * Fico风险预警的批量数据入表的功能
 * 
 * @author liuwei
 * @date 2019.06.10 上午
 */
@Controller
@RequestMapping("fico/batch")
public class FicoBatchController {

	private final static Logger logger = LoggerFactory.getLogger(FicoBatchController.class);

	@Resource(name = "pageProperty")
	private PageProperty page;

	@Resource
	private FicoBatchService ficoBatchService;

	/**Fico风险预警--批量实时查询页面 */
	@RequestMapping("index")
	public String index() {
		return "fico/fico_batch_index";
	}
	

	/**
	 * Fico风险预警批量文件提交
	 * 
	 * @param request
	 * @param files
	 *            批量数据文件
	 * @return
	 * @throws org.codehaus.jackson.map.JsonMappingException
	 * @throws IOException
	 * @throws org.codehaus.jackson.map.JsonMappingException
	 * @throws JsonGenerationException
	 */
	@RequestMapping("submit")
	@ResponseBody
	public String saveBatchUploadFile(HttpServletRequest request, @RequestParam("files") MultipartFile[] files)
			throws JsonGenerationException, org.codehaus.jackson.map.JsonMappingException, IOException {
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("result", true);
		if(request.getAttribute("submitFlag")!=null&&request.getAttribute("submitFlag").equals("false")){
			long allowFileSize = (long) request.getAttribute("allowFileSize");
			long fileMaxLength = (long) request.getAttribute("fileMaxSize");
			response.put("success", false);
			response.put("message", "当前文件"+allowFileSize+"M,上传文件大小不能超过"+fileMaxLength+"M");
			return new ObjectMapper().writeValueAsString(response);
		}
		String account = CacheUtil.get(Cache.NAMESPACE + ":USER:" + request.getSession().getId());
		if (files != null && files.length > 0) {
			List<String> successfulFileList = new ArrayList<String>();
			List<String> failedFileList = new ArrayList<String>();
			String batchNo = BatchNoGenerator.generateNextNumber();
			int total = 0;
			logger.info("开始fico风险预警批量数据文件处理[ {} ]", new Object[] { batchNo });
			for (int i = 0; i < files.length; i++) {
				MultipartFile file = files[i];
				logger.info("准备处理第[ {} ]个文件[ {} ]", new Object[] { (i + 1), file.getOriginalFilename() });
				int result = 0;
				try {
					result = ficoBatchService.saveUploadRecord(file, batchNo, account);
					successfulFileList.add(file.getOriginalFilename());
					logger.info("批次[ {} ]文件[ {} ]处理成功", new Object[] { batchNo, file.getOriginalFilename() });
				} catch (Exception e) {
					failedFileList.add(file.getOriginalFilename());
					logger.error("批次[ {} ]文件[ {} ]处理失败，异常信息[ {} ]。",
							new Object[] { batchNo, file.getOriginalFilename(), e.getMessage() });
				}
				total += result;
			}

			logger.info("fico批量数据文件处理完成，处理[ {} ]个，成功[ {} ]个，失败[ {} ]个",
					new Object[] { files.length, successfulFileList.size(), failedFileList.size() });

			if (total > 0) {
				// 数据批次信息持久化
				FicoBatch ficoBatch = new FicoBatch();
				ficoBatch.setCrtUser(account);
				ficoBatch.setBatchId(batchNo);
				ficoBatch.setBatchRecordSize(total);
				ficoBatchService.saveTrnBatch(ficoBatch);
			}
			response.put("success", successfulFileList);
			response.put("failure", failedFileList);
		}
		return new ObjectMapper().writeValueAsString(response);
	}
	
	/**
	 * fico查询--批次查询主页面
	 * 
	 * @throws IOException
	 * @throws JsonMappingException
	 * @throws JsonParseException
	 */
	@RequestMapping("pagelist/batch")
	public String index(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap)
			throws JsonParseException, JsonMappingException, IOException {
		String account = CacheUtil.get(Cache.NAMESPACE + ":USER:" + request.getSession().getId());
		String body = CacheUtil.get(Cache.NAMESPACE + ":USER:" + account);
		User loginUser = JacksonUtil.jsonToObject(body, User.class);
		// 分页参数
		String pageNo = request.getParameter("pageNo");
		String pageSize = request.getParameter("pageSize");

		int iPageNo = page.getPageNo();
		if (StringUtils.isNotBlank(pageNo)) {
			iPageNo = Integer.parseInt(pageNo);
		}
		int iPageSize = page.getPageSize();
		if (StringUtils.isNotBlank(pageSize)) {
			iPageSize = Integer.parseInt(pageSize);
		}

		// 分页设置
		page.setPageNo(iPageNo);
		page.setPageSize(iPageSize);
		page.addParameter("team", loginUser.getTeam());

		if (logger.isDebugEnabled()) {
			logger.debug("[批次查询] 当前页号[{}]，每页大小[{}]", new Object[] { iPageNo, iPageSize });
		}

		int totalCount = ficoBatchService.queryBatchListCountByPage(page);
		List<FicoBatch> list = ficoBatchService.queryBatchListByPage(page);
		page.setTotalCount(totalCount);
		modelMap.put("page", page);
		modelMap.put("records", list);

		return "fico/fico_batch_list";
	}
	/** FICO--查询文件明细 */
	@RequestMapping("pagelist/batchfile")
	public String getFileDetail(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		String batchId = request.getParameter("batchId");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<FicoBatchFile> list = ficoBatchService.queryDetailListByPage(batchId);	
		/**
	          状态0  等待查询： crt_time 在当天0点之后，结束返回0
		状态2  查询成功：crt_time 在当天0点之前，该文件rsik_level 有数据，结束返回2
		状态3  查询失败：crt_time 在当天0点之前，该文件rsik_level 无数据，该crt_time 当天的rsik_level 有数据，结束返回3
		状态1  正在查询：crt_time 在当天0点之前，该文件rsik_level 无数据，该crt_time 当天的rsik_level 无数据，结束返回1
		 */
		for(int i=0;i<list.size();i++){
			FicoBatchFile temp = list.get(i);
			if(temp != null){
				if(isBeforeDate(temp.getCrtTime(),DateFormatUtils.format(new Date(),"yyyy-MM-dd 00:00:00"))){
					Map<String, Object> data = new HashMap<String, Object>();
					data.put("batchFileId", temp.getBatchFileId());
					int count = ficoBatchService.countBatchFileNumber(data);
					if(count > 0){
						temp.setQueryStatus("2");
					}else{
						Map<String, Object> timeDate = new HashMap<String, Object>();
						try {
							timeDate.put("beginTime", DateFormatUtils.format(sdf.parse(temp.getCrtTime()),"yyyy-MM-dd 00:00:00"));
							timeDate.put("endTime", DateFormatUtils.format(sdf.parse(temp.getCrtTime()),"yyyy-MM-dd 23:59:59"));
						} catch (ParseException e) {
							logger.error("[FICO] 批次查询日期转化异常[{}]", new Object[] { e.getMessage() }, e);
						}
						int cout = ficoBatchService.countBatchDetailNumber(timeDate);
						if(cout > 0){
							temp.setQueryStatus("3");
						}else{
							temp.setQueryStatus("1");
						}
					}
				}else{
					temp.setQueryStatus("0");
				}
			}
		}
		modelMap.put("records", list);
		return "fico/fico_batch_file_list";
	}
//	/** FICO--查询文件记录明细 */
//	@RequestMapping("pagelist/filedetail")
//	public String getFileDtail(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
//		String batchFileId = request.getParameter("batchFileId");
//		List<FicoBatchDetail> list = ficoBatchService.queryFileDetailListByPage(batchFileId);
//		modelMap.put("records", list);
//		return "fico/fico_batch_detail_list";
//	}
	/** FICO--查询文件记录明细 
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException */
	@RequestMapping("pagelist/filedetail")
	public String getFileDtail(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) throws JsonParseException, JsonMappingException, IOException {	
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String account = CacheUtil.get(Cache.NAMESPACE + ":USER:" + request.getSession().getId());
		String body = CacheUtil.get(Cache.NAMESPACE + ":USER:" + account);
		User loginUser = JacksonUtil.jsonToObject(body, User.class);
		// 分页参数
		String batchFileId = request.getParameter("batchFileId");
		String pageNo = request.getParameter("pageNo");
		String pageSize = request.getParameter("pageSize");

		int iPageNo = page.getPageNo();
		if (StringUtils.isNotBlank(pageNo)) {
			iPageNo = Integer.parseInt(pageNo);
		}
		int iPageSize = page.getPageSize();
		if (StringUtils.isNotBlank(pageSize)) {
			iPageSize = Integer.parseInt(pageSize);
		}
		// 分页设置
		page.setPageNo(iPageNo);
		page.setPageSize(iPageSize);
		page.addParameter("team", loginUser.getTeam());
		//若是batchFileId更改，则从第1页开始
		Map<String, Object> args = new HashMap<String, Object>();
		args.putAll(page.getDataMap());
		String beforeBatchFileId = (String) args.get("batchFileId");
		if (StringUtils.isNotBlank(beforeBatchFileId)&&StringUtils.isNotBlank(batchFileId)) {
			if(batchFileId.equals(beforeBatchFileId)){
			}else{
				page.setPageNo(1);
			}
		}
		page.addParameter("batchFileId",batchFileId);

		if (logger.isDebugEnabled()) {
			logger.debug("[查询] 当前页号[{}]，每页大小[{}]", new Object[] { iPageNo, iPageSize });
		}

		int totalCount = ficoBatchService.queryDetailListCountByPage(page);
		List<FicoBatchDetail> list = ficoBatchService.queryFileDetailListByPage(page);
		/**
			状态2  查询成功：这条记录 rsik_level 有数据，结束返回2
			状态0  等待查询：这条记录 rsik_level 无数据， crt_time 在当天0点之后，结束返回0
			状态1  正在查询：这条记录 rsik_level 无数据， crt_time 在当天0点之前，该crt_time 当天的rsik_level 都无数据，结束返回1
			状态3  查询失败：这条记录 rsik_level 无数据， crt_time 在当天0点之前，该crt_time 当天的rsik_level 有数据，结束返回3
		 */
		for(int i=0;i<list.size();i++) {
			FicoBatchDetail temp = list.get(i);
			if(temp != null){
				if(StringUtils.isNotBlank(temp.getRiskLevel())){
					temp.setQueryStatus("2");
				}else{
					if(isBeforeDate(temp.getCrtTime(),DateFormatUtils.format(new Date(),"yyyy-MM-dd 00:00:00"))){
						Map<String, Object> timeDate = new HashMap<String, Object>();
						try {
							timeDate.put("beginTime", DateFormatUtils.format(sdf.parse(temp.getCrtTime()),"yyyy-MM-dd 00:00:00"));
							timeDate.put("endTime", DateFormatUtils.format(sdf.parse(temp.getCrtTime()),"yyyy-MM-dd 23:59:59"));
						} catch (ParseException e) {
							logger.error("[FICO] 批次查询日期转化异常[{}]", new Object[] { e.getMessage() }, e);
						}
						int count = ficoBatchService.countBatchDetailNumber(timeDate);
						if(count > 0){
							temp.setQueryStatus("3");
						}else{
							temp.setQueryStatus("1");
						}
					}else{
						temp.setQueryStatus("0");
					}
				}
			}
		}
		page.setTotalCount(totalCount);
		modelMap.put("page", page);
		modelMap.put("records", list);
		return "fico/fico_batch_detail_list";
	}
	/**
	 * 判断某个日期是否在另外一个日期之前
	 */
	public  boolean isBeforeDate(String beginTime ,String endTime){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date bt = sdf.parse(beginTime);
			Date et = sdf.parse(endTime);
			if(bt.before(et)){
				return true;
			}else{
				return false;
			}
		} catch (ParseException e) {
			logger.error("[FICO] 批次查询日期异常[{}]", new Object[] { e.getMessage() }, e);
		}
		return false;
	}
}
