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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huaxia.plaze.common.PageProperty;
import com.huaxia.plaze.ui.system.domain.Role;
import com.huaxia.plaze.ui.system.service.RoleResourceService;
import com.huaxia.plaze.ui.system.service.RoleService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 角色管理
 * 
 * @author zhiguo.li
 *
 */
@Controller
@RequestMapping(value = "system/role")
public class RoleManageController {

	private final static Logger logger = LoggerFactory.getLogger(RoleManageController.class);
	
	@Resource(name = "pageProperty")
	private PageProperty page;
	
	@Resource
	private RoleService roleService;
	
	@Resource
	private RoleResourceService roleResourceService;
	
	/** 角色分页列表 */
	@RequestMapping(value = "pageList")
	public String findRoleListByPage(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
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
		String roleName = request.getParameter("roleName");
		if (StringUtils.isNotBlank(roleName)) {
			page.addParameter("roleName", roleName);
		}

		if (page.getDataMap().size() > 0) {
			if (logger.isDebugEnabled()) {
				logger.debug("[菜单管理] 查询参数[{}]", new Object[] { page.getDataMap() });
			}
		}

		int totalCount = roleService.queryListCountByPage(page);
		List<Role> roleList = roleService.queryListByPage(page);

		page.setTotalCount(totalCount);
		modelMap.put("page", page);
		modelMap.put("records", roleList);
		return "system/role_list_page";
	}
	
	/** 添加角色 */
	@RequestMapping(value = "add")
	public String addRole() {
		return "system/role_add";
	}
	
	/** 保存角色 */
	@ResponseBody
	@RequestMapping(value = "save")
	public Object saveRole(Role role) throws JsonGenerationException, JsonMappingException, IOException {
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("result", false);
		response.put("code", "999999");
		response.put("message", "记录添加失败!");

		if (role != null) {
			role.setCreateUser("SYSTEM");
			int result = roleService.save(role);
			if (result > 0) {
				response.put("result", true);
				response.put("code", "000000");
				response.put("message", "记录添加成功!");
			}
		}

		return new ObjectMapper().writeValueAsString(response);
	}
	
	/** 修改用户 */
	@RequestMapping(value = "get", method = RequestMethod.GET)
	public String modifyRole(String id, ModelMap modelMap) {
		Role role = roleService.queryById(id);
		if (role != null) {
			modelMap.put("records", role);
		}
		return "system/role_modify";
	}
	
	/** 更新用户 */
	@ResponseBody
	@RequestMapping(value = "update")
	public Object updateRole(Role role) throws JsonGenerationException, JsonMappingException, IOException {
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("result", false);
		response.put("code", "999999");
		response.put("message", "记录更新失败!");

		if (role != null) {
			int result = roleService.update(role);
			if (result > 0) {
				response.put("result", true);
				response.put("code", "000000");
				response.put("message", "记录更新成功!");
			}
		}

		return new ObjectMapper().writeValueAsString(response);
	}

	/** 删除角色 */
	@ResponseBody
	@RequestMapping(value = "remove")
	public Object removeRole(String id) throws Exception {
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("result", false);
		response.put("code", "999");
		response.put("message", "记录删除失败!");

		int counts = roleService.queryListCountByMapping(id);
		if (counts == 0) {
			int result = roleService.removeById(id);
			if (result > 0) {
				response.put("result", true);
				response.put("code", "000");
				response.put("message", "记录删除成功!");
			}
		}

		return new ObjectMapper().writeValueAsString(response);
	}
	
	/** 授权角色 */
	@ResponseBody
	@RequestMapping(value = "authorize")
	public Object authorizeRole(@RequestBody String params) throws Exception {
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("result", false);
		response.put("code", "999");
		response.put("message", "记录授权失败!");

		if (params != null) {
			JSONObject jsonObject = JSONObject.fromObject(params);
			if (jsonObject.containsKey("id")) {
				String roleId = jsonObject.getString("id");
			
				if (jsonObject.containsKey("menus")) {
					JSONArray jsonArray = jsonObject.getJSONArray("menus");
					String[] menuArray = new String[jsonArray.size()];
					for (int i = 0; i < jsonArray.size(); i++) {
						menuArray[i] = jsonArray.getString(i);
					}
					int result = roleResourceService.mergeListMapping(roleId, menuArray);
					if (result > 0) {
						response.put("result", true);
						response.put("code", "000000");
						response.put("message", "记录映射成功!");
					}
				}
			}
		}

		return new ObjectMapper().writeValueAsString(response);
	}
	
}
