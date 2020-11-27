package com.huaxia.plaze.ui.quartz.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huaxia.plaze.common.PageProperty;
import com.huaxia.plaze.ui.quartz.domain.BatchQuartz;
import com.huaxia.plaze.ui.quartz.service.BatchQuartzService;
/**
 * 批量定时任务管理
 * @author User
 *
 */
@Controller
@RequestMapping("batch/quartz")
public class BatchQuartzSetting {

	private static final Logger logger = Logger.getLogger(BatchQuartzSetting.class);
	private PageProperty page;
	@Resource
	private BatchQuartzService batchQuartzServiceImpl;
	/*
	 * 批量定时任务分页列表
	 */
	@RequestMapping("pageList")
	public String showPageList(HttpServletRequest request, ModelMap modelMap){
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
		// 业务参数
		page.clearDataMap();
		
		String jobName = request.getParameter("jobName");
		if(StringUtils.isNotBlank(jobName)){
			page.addParameter("jobName", jobName);
		}
		String turn = request.getParameter("turn");
		if(StringUtils.isNotBlank(turn)){
			page.addParameter("turn", turn);
		}
		
		int totalCount = batchQuartzServiceImpl.queryTotalCountByPage(page);
		List<BatchQuartz> quartzs = batchQuartzServiceImpl.queryListByPage(page);
		
		page.setTotalCount(totalCount);
		modelMap.put("page", page);
		modelMap.put("data", quartzs);
		return "quartz/page_list";
	}
	//添加定时任务
	@RequestMapping("add")
	public String addQuartz(){
		return "quartz/add";
	}
	//保存定时任务
	@ResponseBody
	@RequestMapping("addSave")
	public Object saveQuartz(BatchQuartz quartz){
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("result", false);
		response.put("code", "999999");
		response.put("message", "记录添加失败!");
		if(quartz != null){
			int num = batchQuartzServiceImpl.saveQuartz(quartz);
			if(num > 0 ){
				response.put("result", true);
				response.put("code", "000000");
				response.put("message", "记录添加成功!");
			}
		}
		JSONObject jsonObject = new JSONObject(response);
		return JSON.toJSONString(jsonObject);
	}
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping("delete")
	public Object deleteQuartz(String id){
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("result", false);
		response.put("code", "999999");
		response.put("message", "记录删除失败!");
		if(StringUtils.isNotBlank(id)){
			int num = batchQuartzServiceImpl.deleteQuartzById(id);
			if(num > 0){
				response.put("result", true);
				response.put("code", "000000");
				response.put("message", "记录删除成功!");
			}
		}
		JSONObject jsonObject = new JSONObject(response);
		return JSON.toJSONString(jsonObject);
	}
	//展示修改页面
	@RequestMapping("query")
	public String showUpdatePage(String id,ModelMap modelMap){
		BatchQuartz quartz = batchQuartzServiceImpl.selectQuartzById(id);
		if(quartz != null){
			modelMap.put("data", quartz);
		}
		return "quartz/update";
	}
	//保存修改
	@ResponseBody
	@RequestMapping("update")
	public Object updateQuartz(BatchQuartz quartz){
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("result", false);
		response.put("code", "999999");
		response.put("message", "记录更新失败!");
		if(quartz != null){
			int num = batchQuartzServiceImpl.updateQuartz(quartz);
			if(num > 0 ){
				response.put("result", true);
				response.put("code", "000000");
				response.put("message", "记录更新成功!");
			}
		}
		JSONObject jsonObject = new JSONObject(response);
		return JSON.toJSONString(jsonObject);
	}
}
