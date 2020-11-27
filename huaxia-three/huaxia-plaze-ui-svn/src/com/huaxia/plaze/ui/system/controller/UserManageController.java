package com.huaxia.plaze.ui.system.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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

import com.huaxia.opas.util.AppConfig;
import com.huaxia.plaze.cache.CacheSupport;
import com.huaxia.plaze.common.Account;
import com.huaxia.plaze.common.Cache;
import com.huaxia.plaze.common.PageProperty;
import com.huaxia.plaze.ui.system.domain.OperateLog;
import com.huaxia.plaze.ui.system.domain.Team;
import com.huaxia.plaze.ui.system.domain.User;
import com.huaxia.plaze.ui.system.service.OperateLogService;
import com.huaxia.plaze.ui.system.service.TeamService;
import com.huaxia.plaze.ui.system.service.UserRoleService;
import com.huaxia.plaze.ui.system.service.UserService;
import com.huaxia.util.CacheUtil;
import com.huaxia.util.JacksonUtil;
import com.huaxia.util.SHAUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 用户管理
 * 
 * @author zhiguo.li
 *
 */
@Controller
@RequestMapping(value = "system/user")
public class UserManageController {

	private final static Logger logger = LoggerFactory.getLogger(UserManageController.class);

	@Resource(name = "pageProperty")
	private PageProperty page;

	@Resource
	private UserService userService;

	@Resource
	private UserRoleService userRoleService;

	@Resource
	private TeamService teamService;

	@Resource(name = "logService")
	private OperateLogService operateLogService;

	private final static String DEFAULT_PASSWORD = AppConfig.getInstance().getValue("authorize.default_password");

	/** 用户分页列表 */
	@RequestMapping(value = "pageList")
	public String findUserListByPage(HttpServletRequest request, ModelMap modelMap) {
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
			logger.debug("[用户管理] 当前页号[{}]，每页大小[{}]", new Object[] { iPageNo, iPageSize });
		}

		// 业务参数
		page.clearDataMap();
		String account = request.getParameter("account");
		if (StringUtils.isNotBlank(account)) {
			page.addParameter("account", account);
		}
		String staffName = request.getParameter("staffName");
		if (StringUtils.isNotBlank(staffName)) {
			page.addParameter("staffName", staffName);
		}
		String status = request.getParameter("status");
		if (StringUtils.isNotBlank(status)) {
			page.addParameter("status", status);
		}
		String pbocAuth = request.getParameter("pbocAuth");
		if (StringUtils.isNotBlank(pbocAuth)) {
			page.addParameter("pbocAuth", pbocAuth);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("[用户管理] 查询参数[{}]", new Object[] { page.getDataMap() });
		}

		List<Team> teamList = teamService.queryAllList();
		int totalCount = userService.queryListCountByPage(page);
		List<User> userList = userService.queryListByPage(page);
		for (User user : userList) {
			user.setStatusName(Account.getStatusDesc(user.getStatus()));
			for (Team team : teamList) {
				if (user.getTeam() != null && user.getTeam().equals(team.getTeamId())) {
					user.setTeamName(team.getTeamName());
				}
			}
		}

		page.setTotalCount(totalCount);
		modelMap.put("page", page);
		modelMap.put("records", userList);

		return "system/user_list_page";
	}

	/** 添加用户 */
	@RequestMapping("add")
	public String addUser() {
		return "system/user_add";
	}

	/** 保存用户 */
	@ResponseBody
	@RequestMapping(value = "save")
	public Object saveUser(HttpSession session, User user)
			throws JsonGenerationException, JsonMappingException, IOException {
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("result", false);
		response.put("code", "999999");
		response.put("message", "记录添加失败!");

		if (user != null) {
			Map<String, Object> args = new HashMap<String, Object>();
			args.put("account", user.getAccount());
			args.put("certNo", user.getCertNo());
			args.put("mobile", user.getMobile());
			boolean isKeyExist = userService.isKeyExist(args);
			if (isKeyExist) {
				response.put("code", "000007");
				response.put("message", "验证客户关键元素失败!");
				return new ObjectMapper().writeValueAsString(response);
			}

			if (user.getPassword() != null) {
				user.setPassword(SHAUtil.sha256(user.getPassword()).toUpperCase());
			} else {
				user.setPassword(SHAUtil.sha256(DEFAULT_PASSWORD).toUpperCase());
			}

			// 设置默认值
			user.setFirstLogin("1");

			int result = userService.save(user);
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
	public String modifyUser(String id, ModelMap modelMap) {
		List<Team> teamList = teamService.queryAllList();
		User user = userService.queryById(id);
		if (user != null) {
			for (Team team : teamList) {
				if (user.getTeam() != null && user.getTeam().equals(team.getTeamId())) {
					user.setTeamName(team.getTeamName());
				}
			}
			modelMap.put("record", user);
		}
		return "system/user_modify";
	}

	/** 更新用户并同步缓存 */
	@ResponseBody
	@RequestMapping(value = "update")
	public Object updateUser(HttpServletRequest request, User user)
			throws JsonGenerationException, JsonMappingException, IOException {
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("result", false);
		response.put("code", "999999");
		response.put("message", "记录更新失败!");

		if (user != null) {
			// 客户关键元素校验
			// 登录名称不允许修改, 所以不用做校验
			Map<String, Object> args = new HashMap<String, Object>();
			args.put("account", user.getAccount());
			args.put("certNo", user.getCertNo());
			args.put("mobile", user.getMobile());
			boolean isKeySame = userService.isKeySame(args);
			if (isKeySame) {
				response.put("code", "000007");
				response.put("message", "验证客户关键元素失败!");
				return new ObjectMapper().writeValueAsString(response);
			}

			int result = userService.updateByUser(user);
			if (result > 0) {
				String beforeStatus = request.getParameter("beforeStatus");
				String afterStatus = request.getParameter("status");

				// 用户账号状态发生变更则进行操作日志记录
				if (StringUtils.isNotBlank(beforeStatus) && StringUtils.isNotBlank(afterStatus)
						&& !beforeStatus.equals(afterStatus)) {
					doSaveOperateLog(request, user.getAccount());
				}

				// 用户信息同步缓存
				User object = userService.queryByAccount(user.getAccount());
				if (object != null) {
					CacheUtil.set(Cache.NAMESPACE + ":USER:" + user.getAccount(), JacksonUtil.objectToJson(object));
				}

				response.put("result", true);
				response.put("code", "000000");
				response.put("message", "记录更新成功!");
			}
		} else {
			response.put("result", false);
			response.put("code", "000008");
			response.put("message", "参数异常!");
		}

		return new ObjectMapper().writeValueAsString(response);
	}

	private void doSaveOperateLog(HttpServletRequest request, String operator) {
		String loginAccount = CacheUtil.get(Cache.NAMESPACE + ":USER:" + request.getRequestedSessionId());
		JSONObject cache1 = JSONObject.fromObject(CacheUtil.get(Cache.NAMESPACE + ":USER:" + loginAccount));
		OperateLog log = new OperateLog();
		log.setUserAcct(operator);
		JSONObject cache2 = JSONObject.fromObject(CacheUtil.get(Cache.NAMESPACE + ":USER:" + operator));
		if (cache2 != null && cache2.containsKey("userName")) {
			String userName = cache2.getString("userName");
			if (StringUtils.isBlank(userName)) {
				User user = userService.queryByAccount(operator);
				log.setUserName(user.getStaffName());
			} else {
				log.setUserName(userName);
			}
		} else {
			User user = userService.queryByAccount(operator);
			log.setUserName(user.getStaffName());
		}
		log.setOperateBefore(request.getParameter("beforeStatus"));
		log.setOperateAfter(request.getParameter("status"));
		log.setOperateAcct(loginAccount);
		log.setOperateName(cache1.getString("staffName"));
		logger.info("登录用户 [ {} ] 变更用户 [ {} ] 状态信息 [ 修改前 ={} - 修改后 ={} ]", loginAccount, operator,
				request.getParameter("beforeStatus"), request.getParameter("status"));
		try {
		operateLogService.saveOperateLog(log);
		} catch (Exception e) {
			logger.error("记录操作日志失败 [ {} ]", e.getMessage(), e);
		}
	}

	/** 删除用户及与之相关的映射关系 */
	@ResponseBody
	@RequestMapping(value = "remove")
	public Object removeUserMapping(HttpServletRequest request, String id) throws Exception {
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("result", false);
		response.put("code", "999999");
		response.put("message", "记录删除失败!");

		int result = userService.removeObjectAndMappingById(id);
		if (result > 0) {
			// 记录操作日志
			doSaveOperateLog(request, id);

			// 删除用户缓存
			User user = userService.queryById(id);
			if (user != null) {
				CacheSupport.clean(user.getAccount());
			}

			response.put("result", true);
			response.put("code", "000000");
			response.put("message", "记录删除成功!");
		}

		return new ObjectMapper().writeValueAsString(response);
	}

	/** 重置登录密码 */
	@ResponseBody
	@RequestMapping(value = "reset")
	public Object reset(String id) throws Exception {
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("result", false);
		response.put("code", "010121");
		response.put("message", "密码重置失败!");

		Map<String, Object> args = new HashMap<String, Object>();
		args.put("id", id);
		args.put("password", SHAUtil.sha256(DEFAULT_PASSWORD).toUpperCase());
		args.put("firstLogin", "1");
		int result = userService.updateForResetById(args);
		if (result > 0) {
			// 同步删除缓存相关信息
			User object = userService.queryById(id);
			if (object != null) {
				logger.info("重置登录口令同步删除缓存信息 [ {} ]", object.getAccount());
				CacheSupport.clean(object.getAccount());
			}

			response.put("result", true);
			response.put("code", "010120");
			response.put("message", "密码重置成功");
		}
		return new ObjectMapper().writeValueAsString(response);
	}

	/** 授权用户 */
	@ResponseBody
	@RequestMapping(value = "authorize")
	public Object authorizeUser(@RequestBody String params) throws Exception {
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("result", false);
		response.put("code", "999");
		response.put("message", "记录授权失败!");

		if (params != null) {
			JSONObject jsonObject = JSONObject.fromObject(params);
			if (jsonObject.containsKey("id")) {
				String userId = jsonObject.getString("id");

				if (jsonObject.containsKey("roles")) {
					JSONArray jsonArray = jsonObject.getJSONArray("roles");
					String[] roleArray = new String[jsonArray.size()];
					for (int i = 0; i < jsonArray.size(); i++) {
						roleArray[i] = jsonArray.getString(i);
					}
					int result = userRoleService.mergeListMapping(userId, roleArray);
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

	/** 获取账户所对应的用户名称 */
	@ResponseBody
	@RequestMapping(value = "getName")
	public Object getStaffName(String account) throws Exception {
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("result", false);
		response.put("code", "999");
		response.put("message", "姓名获取失败!");

		String staffName = userService.getUserName(account);
		if (staffName != null && !"".equals(staffName)) {
			response.put("result", true);
			response.put("name", staffName);
			response.put("code", "000000");
			response.put("message", "姓名获取失败!");
		}

		return new ObjectMapper().writeValueAsString(response);
	}

}
