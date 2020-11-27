package com.huaxia.plaze.ui.system.controller;

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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huaxia.plaze.common.PageProperty;
import com.huaxia.plaze.ui.system.domain.Menu;
import com.huaxia.plaze.ui.system.service.ResourceService;

/**
 * 菜单管理
 * 
 * @author zhiguo.li
 *
 */
@Controller
@RequestMapping(value = "system/menu")
public class MenuManageController {

	private final static Logger logger = LoggerFactory.getLogger(MenuManageController.class);

	@Resource(name = "pageProperty")
	private PageProperty page;

	@Resource
	private ResourceService resourceService;

	/** 菜单分页列表 */
	@RequestMapping(value = "page/list")
	public String findMenuListByPage(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
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

		if (logger.isDebugEnabled()) {
			logger.debug("[菜单管理] 当前页号[{}]，每页大小[{}]", new Object[] { iPageNo, iPageSize });
		}

		// 业务参数
		page.clearDataMap();
		String name = request.getParameter("name");
		if (StringUtils.isNotBlank(name)) {
			page.addParameter("name", name);
		}

		if (page.getDataMap().size() > 0) {
			if (logger.isDebugEnabled()) {
				logger.debug("[菜单管理] 查询参数[{}]", new Object[] { page.getDataMap() });
			}
		}

		int totalCount = resourceService.queryListCountByPage(page);
		List<Menu> menuList = resourceService.queryListByPage(page);

		page.setTotalCount(totalCount);
		modelMap.put("page", page);
		modelMap.put("records", menuList);
		
		return "system/menu_list_page";
	}
	
	/** 添加菜单 */
	@RequestMapping(value = "add")
	public String addMenu() {
		return "system/menu_add";
	}
	
	/** 保存菜单 */
	@ResponseBody
	@RequestMapping(value = "save")
	public Object saveMenu(Menu menu) throws JsonGenerationException, JsonMappingException, IOException {
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("result", false);
		response.put("code", "999999");
		response.put("message", "记录添加失败!");

		if (menu != null) {
			menu.setCreateUser("SYSTEM");
			int result = resourceService.save(menu);
			if (result > 0) {
				response.put("result", true);
				response.put("code", "000000");
				response.put("message", "记录添加成功!");
			}
		}

		return new ObjectMapper().writeValueAsString(response);
	}
	
	/** 修改菜单 */
	@RequestMapping(value = "get", method = RequestMethod.GET)
	public String modifyMenu(String id, ModelMap modelMap) {
		Menu menu = resourceService.queryObjectById(id);
		if (menu != null) {
			modelMap.put("records", menu);
		}
		return "system/menu_modify";
	}
	
	/** 更新菜单 */
	@ResponseBody
	@RequestMapping(value = "update")
	public Object updateMenu(Menu menu) throws JsonGenerationException, JsonMappingException, IOException {
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("result", false);
		response.put("code", "999999");
		response.put("message", "记录更新失败!");

		if (menu != null) {
			int result = resourceService.update(menu);
			if (result > 0) {
				response.put("result", true);
				response.put("code", "000000");
				response.put("message", "记录更新成功!");
			}
		}

		return new ObjectMapper().writeValueAsString(response);
	}

	/** 删除菜单（逻辑删除） */
	@ResponseBody
	@RequestMapping(value = "remove")
	public Object removeMenu(String id)
			throws JsonGenerationException, JsonMappingException, IOException {
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("result", false);
		response.put("code", "999");
		response.put("message", "记录删除失败!");

		// 判断待删除菜单是否为叶子菜单
		int counts = resourceService.queryListCountByParentId(id);
		if (counts == 0) {
			int result = resourceService.removeById(id);
			if (result > 0) {
				response.put("result", true);
				response.put("code", "000");
				response.put("message", "记录删除成功!");
			}
		}

		return new ObjectMapper().writeValueAsString(response);
	}

	/** 查询新增节点关键信息 */
	@ResponseBody
	@RequestMapping(value = "queryNextById")
	public Object queryNextById(String id)
			throws JsonGenerationException, JsonMappingException, IOException {
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("result", false);

		Menu node = resourceService.queryNextObjectById(id);
		if (node != null) {
			response.put("result", true);
			response.put("level", node.getLevel());
			response.put("index", node.getIndex());
		}

		return new ObjectMapper().writeValueAsString(response);
	}

}
