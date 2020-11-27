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
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonParseException;
import com.huaxia.plaze.common.Cache;
import com.huaxia.plaze.common.PageProperty;
import com.huaxia.plaze.ui.system.domain.Menu;
import com.huaxia.plaze.ui.system.domain.MenuTree;
import com.huaxia.plaze.ui.system.domain.Role;
import com.huaxia.plaze.ui.system.domain.User;
import com.huaxia.plaze.ui.system.service.ResourceService;
import com.huaxia.plaze.ui.system.service.RoleResourceService;
import com.huaxia.plaze.ui.system.service.RoleService;
import com.huaxia.plaze.ui.system.service.TeamService;
import com.huaxia.plaze.ui.system.service.UserRoleService;
import com.huaxia.plaze.ui.system.service.UserService;
import com.huaxia.util.CacheUtil;
import com.huaxia.util.JacksonUtil;

import net.sf.json.JSONObject;

@Controller
public class ApplicationController {

	private final static Logger logger = LoggerFactory.getLogger(ApplicationController.class);

	@Resource(name = "pageProperty")
	private PageProperty page;

	@Resource
	private UserService userService;

	@Resource
	private RoleService roleService;

	@Resource
	private UserRoleService userRoleService;

	@Resource
	private ResourceService resourceService;

	@Resource
	private RoleResourceService roleResourceService;
	
	@Resource
	private TeamService teamService;

	/** 系统首页  */
	@RequestMapping("default/index")
	public String index(String account, ModelMap model) throws JsonParseException, com.fasterxml.jackson.databind.JsonMappingException, IOException {
		String body = CacheUtil.get(Cache.NAMESPACE + ":USER:" + account);
		User loginUser = JacksonUtil.jsonToObject(body, User.class);
		model.put("loginUser", loginUser);
		return "index";
	}

	/** 初始密码修改 */
	@ResponseBody
	@RequestMapping("default/password/update")
	public String modifyPassword(@RequestBody String body) throws JsonGenerationException, JsonMappingException, IOException {
		Map<String, Object> response = new HashMap<String, Object>();
		JSONObject jsonObject = JSONObject.fromObject(body);
		if (jsonObject.containsKey("account")) {
			String account = jsonObject.getString("account");
			String password1 = String.valueOf(jsonObject.getString("password1")).toUpperCase();
			String password2 = String.valueOf(jsonObject.getString("password2")).toUpperCase();

			// 必输项校验
			if (account == null || "".equals(account) || password1 == null || "".equals(password1) || password2 == null
					|| "".equals(password2)) {
				if (logger.isDebugEnabled()) {
					logger.debug("账户或密码校验异常!");
				}
				
				response.put("result", false);
				response.put("code", "100");
				response.put("message", "账户或密码校验异常!");
				return new ObjectMapper().writeValueAsString(response);
			}

			User user = userService.queryByAccount(account);
			if (user != null) {
				// 旧密码有效性验证
				if (!password1.equals(user.getPassword())) {
					if (logger.isDebugEnabled()) {
						logger.debug("旧密码有效性校验失败!");
					}
					
					response.put("result", false);
					response.put("code", "102");
					response.put("message", "旧密码有效性校验失败!");
					return new ObjectMapper().writeValueAsString(response);
				}

				// 新密码是否之前设置过校验
				Map<String, Object> args = new HashMap<String, Object>();
				args.put("account", account);
				args.put("password", password2);
				int counts = userRoleService.queryCountByPwdDetail(args);
				if (counts > 0) {
					if (logger.isDebugEnabled()) {
						logger.debug("新密码不能与过往设置密码相同!");
					}
					
					response.put("result", false);
					response.put("code", "103");
					response.put("message", "新密码不能与过往设置密码相同!");
					return new ObjectMapper().writeValueAsString(response);
				} else {
					User pojo = new User();
					pojo.setAccount(account);
					pojo.setPassword(password2);
					pojo.setOldPassword(user.getPassword());
					pojo.setFirstLogin("0");
					int result = userService.synchronization(pojo);
					if (result > 0) {
						response.put("result", true);
						response.put("code", "000");
						response.put("message", "密码修改成功!");
						return new ObjectMapper().writeValueAsString(response);
					}
				}
			}
		} else {
			if (logger.isDebugEnabled()) {
				logger.debug("未知情况!");
			}
			
			response.put("result", false);
			response.put("code", "999");
			response.put("message", "未知情况");
		}
		return new ObjectMapper().writeValueAsString(response);
	}

	@ResponseBody
	@RequestMapping("default/usermenu/query")
	public String findUserMenuBody(String account) {
		// 查询用户所属菜单项
		List<Menu> userMenuList = roleResourceService.queryMenuByAccount(account);
		if (userMenuList != null) {
			userMenuList = MenuTree.walk(userMenuList);
			MenuTree.sort(userMenuList);
			return MenuTree.json(userMenuList);
		}
		return null;
	}

	/** 菜单选择分页列表 */
	@RequestMapping(value = "common/menu/pageList")
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

		// 业务参数
		page.clearDataMap();
		String name = request.getParameter("name");
		if (StringUtils.isNotBlank(name)) {
			page.addParameter("name", name);
		}

		int totalCount = resourceService.queryChooseListCountByPage(page);
		List<Menu> userList = resourceService.queryChooseListByPage(page);

		page.setTotalCount(totalCount);
		modelMap.put("page", page);
		modelMap.put("records", userList);

		return "common/menu";
	}

	/** 菜单选择列表 */
	@RequestMapping(value = "common/menu/list")
	public String findAllMenuList(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		List<Menu> menuList = resourceService.queryTreeList();
		if (menuList != null) {
			String roleId = request.getParameter("id");
			List<Map<String, String>> mappingList = roleResourceService.queryTreeListMappingById(roleId);
			for (Menu menu : menuList) {
				for (Map<String, String> mapping : mappingList) {
					if (menu.getId().equals(mapping.get("RSRC_ID"))) {
						menu.setChecked("1");
						break;
					}
				}
			}
		}
		modelMap.put("records", menuList);
		return "common/menu_authorize";
	}

	/** 角色选择列表 */
	@RequestMapping(value = "common/role/list")
	public String findAllRoleList(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		List<Role> roleList = roleService.queryAllList();
		if (roleList != null) {
			String userId = request.getParameter("id");
			List<Map<String, String>> mappingList = userRoleService.queryAllListMappingById(userId);
			for (Role role : roleList) {
				for (Map<String, String> mapping : mappingList) {
					if (role.getRoleId().equals(mapping.get("ROLE_ID"))) {
						role.setChecked("1");
						break;
					}
				}
			}
		}
		modelMap.put("records", roleList);
		return "common/role_authorize";
	}
	
	/** 团队选择列表 */
	@RequestMapping(value = "common/team/list")
	public String findAllTeamList(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		modelMap.put("records", teamService.queryAllList());
		return "common/team_associate";
	}

}
