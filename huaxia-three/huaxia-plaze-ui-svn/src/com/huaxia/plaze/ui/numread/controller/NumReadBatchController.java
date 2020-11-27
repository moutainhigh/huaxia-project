package com.huaxia.plaze.ui.numread.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
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
import com.huaxia.plaze.exception.ApplicationException;
import com.huaxia.plaze.ui.numread.domain.NumReadBatch;
import com.huaxia.plaze.ui.numread.domain.NumReadBatchDetail;
import com.huaxia.plaze.ui.numread.domain.NumReadBatchFile;
import com.huaxia.plaze.ui.numread.domain.NumReadBatchResponse;
import com.huaxia.plaze.ui.numread.service.BatchService;
import com.huaxia.plaze.ui.system.domain.User;
import com.huaxia.plaze.ui.system.service.DictionaryService;
import com.huaxia.util.BatchNoGenerator;
import com.huaxia.util.CacheUtil;
import com.huaxia.util.JacksonUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
/**
 * 人行数字解读 
 * @author liyanjie
 *
 */
@Controller
@RequestMapping("numread/batch")
public class NumReadBatchController {
	
	private final static Logger logger = LoggerFactory.getLogger(NumReadBatchController.class);
	@Resource
	private BatchService batchService;

	private PageProperty page;
	@Resource
	private DictionaryService dictionaryService;

	/**
	 * 批量查询 页面
	 * @return
	 */
	@RequestMapping("index")
	public String index(){
		return "numread/numread_batch_index";
	}
	/**
	 * 批量查询 提交
	 * @param request
	 * @param files
	 * @return
	 */
	@RequestMapping("submit")
	@ResponseBody
	public String submit(HttpServletRequest request,@RequestParam("files")MultipartFile[] files){
		User loginUser = null;
		String batchNo = BatchNoGenerator.generateNextNumber();
		try {
			String account = CacheUtil.get(Cache.NAMESPACE + ":USER:" + request.getSession().getId());
			String body = CacheUtil.get(Cache.NAMESPACE + ":USER:" + account);
			loginUser = JacksonUtil.jsonToObject(body, User.class);
			return batchService.saveBatch(batchNo,files,loginUser);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			if(e instanceof ApplicationException){
				return e.getMessage();
			}
			return "提交失败！";
		}
	}
	/**
	 * 批次查询列表
	 * @return
	 */
	@RequestMapping("pagelist/batch")
	public String batchList(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap)
		throws JsonParseException, JsonMappingException, IOException {
			String account = CacheUtil.get(Cache.NAMESPACE + ":USER:" + request.getSession().getId());
			String body = CacheUtil.get(Cache.NAMESPACE + ":USER:" + account);
			User loginUser = JacksonUtil.jsonToObject(body, User.class);

			// 分页参数
			page = new PageProperty();
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
			int totalCount = batchService.queryBatchListCountByPage(page);
			List<NumReadBatch> list = batchService.queryBatchListByPage(page);
			page.setTotalCount(totalCount);
			modelMap.put("page", page);
			modelMap.put("records", list);
		return "numread/numread_batch_list";
	}
	/**
	 * 批次文件 查询列表
	 * @return
	 */
	@RequestMapping("pagelist/batchfile")
	public String showBatchFile(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap){
		String batchId = request.getParameter("batchId");
		List<NumReadBatchFile> list = batchService.queryFileDetail(batchId);
		modelMap.put("records", list);
		return "numread/numread_batch_file_list";
	}
	/**
	 * 批次文件明细
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 */
	@RequestMapping("pagelist/filedetail")
	public String showFileDetail(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) throws JsonParseException, JsonMappingException, IOException{String account = CacheUtil.get(Cache.NAMESPACE + ":USER:" + request.getSession().getId());
		String body = CacheUtil.get(Cache.NAMESPACE + ":USER:" + account);
		User loginUser = JacksonUtil.jsonToObject(body, User.class);
	
		// 分页参数
		page = new PageProperty();
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
		String batchFileId = request.getParameter("batchFileId");
		page.getMap().put("fileId", batchFileId);
		int totalCount = batchService.selectCountDetail(batchFileId);
		List<NumReadBatchDetail> list = batchService.selectDetail(page);
		for(NumReadBatchDetail result :list){
			if(StringUtils.isNotBlank(result.getCertType())){
				String jsonString = dictionaryService.queryObjectByGroup("CERT_TYPE");
				JSONArray jsonArray = JSONArray.fromObject(jsonString);
				for (int i = 0; i < jsonArray.size(); i++) {
					JSONObject jsonObject = jsonArray.getJSONObject(i);
					if(jsonObject.get("dictCode").equals(result.getCertType())){
						result.setCertType(jsonObject.get("dictName").toString());
						break;
					}
				}
			}
		}
		page.setTotalCount(totalCount);
		modelMap.put("page", page);
		modelMap.put("records", list);
		modelMap.put("batchFileId", batchFileId);
		return "numread/numread_batch_detail_list";
	}
	/**
	 * 展示详细查询结果
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 */
	@RequestMapping("view/detail")
	public String showDetail(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap){
		String trnId = request.getParameter("trnId");
		NumReadBatchResponse result = batchService.selectDetailResponseByTrnId(trnId);
		if(null!=result && StringUtils.isNotBlank(result.getIdType())){
			String jsonString = dictionaryService.queryObjectByGroup("CERT_TYPE");
			JSONArray jsonArray = JSONArray.fromObject(jsonString);
			for (int i = 0; i < jsonArray.size(); i++) {
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				if(jsonObject.get("dictCode").equals(result.getIdType())){
					result.setIdType(jsonObject.get("dictName").toString());
					break;
				}
			}
		}
		modelMap.put("batchDetail", JSONObject.fromObject(result));
		return "numread/numread_detail_show";
	}
	/**
	 * 展示历史查询页面
	 * @return
	 */
	@RequestMapping("history/index")
	public String showHistoryIndex(){
		return "numread/numread_history_list";
	}
	/**
	 * 历史分页数据
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	@RequestMapping("history/pagelist")
	public String showHistory(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) throws JsonParseException, JsonMappingException, IOException{
		String account = CacheUtil.get(Cache.NAMESPACE + ":USER:" + request.getSession().getId());
		String body = CacheUtil.get(Cache.NAMESPACE + ":USER:" + account);
		User loginUser = JacksonUtil.jsonToObject(body, User.class);

		// 分页参数
		page = new PageProperty();
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
		String certNo = request.getParameter("certNo");
		page.getMap().put("certNo", certNo);
		int totalCount = batchService.selectCountDetailSuccess(certNo);
		if(StringUtils.isNotBlank(certNo)){
			batchService.saveLog(certNo,loginUser);
		}
		List<NumReadBatchDetail> list = batchService.selectDetailByCertNoAndSuccess(page);
		for(NumReadBatchDetail result :list){
			if(StringUtils.isNotBlank(result.getCertType())){
				String jsonString = dictionaryService.queryObjectByGroup("CERT_TYPE");
				JSONArray jsonArray = JSONArray.fromObject(jsonString);
				for (int i = 0; i < jsonArray.size(); i++) {
					JSONObject jsonObject = jsonArray.getJSONObject(i);
					if(jsonObject.get("dictCode").equals(result.getCertType())){
						result.setCertType(jsonObject.get("dictName").toString());
						break;
					}
				}
			}
		}
		page.setTotalCount(totalCount);
		modelMap.put("page", page);
		modelMap.put("records", list);
		modelMap.put("certNo", certNo);
		return "numread/numread_history_list";
	}

}
