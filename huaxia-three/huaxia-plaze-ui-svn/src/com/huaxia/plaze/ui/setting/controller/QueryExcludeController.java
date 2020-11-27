package com.huaxia.plaze.ui.setting.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huaxia.plaze.common.PageProperty;
import com.huaxia.plaze.ui.setting.domain.QueryExclude;
import com.huaxia.plaze.ui.setting.service.QueryExcludeService;

/**
 * 查询例外配置
 * 
 * @author zhiguo.li
 *
 */
@Controller
@RequestMapping(value = "setting")
public class QueryExcludeController {

	@Resource(name = "pageProperty")
	private PageProperty page;

	@Resource
	private QueryExcludeService queryExcludeService;

	/** 人行特殊查询分页列表 */
	@RequestMapping(value = "query/exclude/pageList")
	public String findExcludeListByPage(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
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

		// 业务参数
		page.clearDataMap();
		String account = request.getParameter("account");
		if (StringUtils.isNotBlank(account)) {
			page.addParameter("account", account);
		}

		int totalCount = queryExcludeService.queryListCountByPage(page);
		List<QueryExclude> queryExcludeList = queryExcludeService.queryListByPage(page);
		page.setTotalCount(totalCount);
		modelMap.put("page", page);
		modelMap.put("records", queryExcludeList);

		return "setting/query_exclude_list_page";
	}

	/** 添加人行特殊查询 */
	@RequestMapping("query/exclude/add")
	public String addQueryExclude() {
		return "setting/query_exclude_add";
	}

	/** 保存人行特殊查询 */
	@ResponseBody
	@RequestMapping(value = "query/exclude/save")
	public Object saveQueryExclude(QueryExclude queryExclude)
			throws JsonGenerationException, JsonMappingException, IOException {
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("result", false);
		response.put("code", "999999");
		response.put("message", "记录添加失败!");

		if (queryExclude != null) {
			int result = queryExcludeService.save(queryExclude);
			if (result > 0) {
				response.put("result", true);
				response.put("code", "000000");
				response.put("message", "记录添加成功!");
			}
		}

		return new ObjectMapper().writeValueAsString(response);
	}

	/** 删除指定编号的人行特殊查询记录 */
	@ResponseBody
	@RequestMapping(value = "query/exclude/remove")
	public Object removeQueryExclude(String id) throws Exception {
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("result", false);
		response.put("code", "999999");
		response.put("message", "记录删除失败!");

		int result = queryExcludeService.removeById(id);
		if (result > 0) {
			response.put("result", true);
			response.put("code", "000000");
			response.put("message", "记录删除成功!");
		}

		return new ObjectMapper().writeValueAsString(response);
	}

}
