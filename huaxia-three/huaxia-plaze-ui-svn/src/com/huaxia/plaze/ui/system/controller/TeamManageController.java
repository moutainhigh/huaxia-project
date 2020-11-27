package com.huaxia.plaze.ui.system.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import com.huaxia.plaze.ui.system.domain.Team;
import com.huaxia.plaze.ui.system.service.TeamService;

/**
 * 团队管理
 * 
 * @author zhiguo.li
 *
 */
@Controller
@RequestMapping(value = "system/team")
public class TeamManageController {

	private final static Logger logger = LoggerFactory.getLogger(TeamManageController.class);

	@Resource(name = "pageProperty")
	private PageProperty page;

	@Resource
	private TeamService teamService;

	/** 用户分页列表 */
	@RequestMapping(value = "pageList")
	public String findUserListByPage(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
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
			logger.debug("[团队管理] 当前页号[{}]，每页大小[{}]", new Object[] { iPageNo, iPageSize });
		}

		// 业务参数
		page.clearDataMap();
		String teamName = request.getParameter("teamName");
		if (StringUtils.isNotBlank(teamName)) {
			page.addParameter("teamName", teamName);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("[团队管理] 查询参数[{}]", new Object[] { page.getDataMap() });
		}

		int totalCount = teamService.queryListCountByPage(page);
		List<Team> teamList = teamService.queryListByPage(page);

		page.setTotalCount(totalCount);
		modelMap.put("page", page);
		modelMap.put("records", teamList);

		return "system/team_list_page";
	}

	/** 添加团队 */
	@RequestMapping("add")
	public String addTeam() {
		return "system/team_add";
	}

	/** 保存用户 */
	@ResponseBody
	@RequestMapping(value = "save")
	public Object saveTeam(HttpSession session, Team team)
			throws JsonGenerationException, JsonMappingException, IOException {
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("result", false);
		response.put("code", "999999");
		response.put("message", "记录添加失败!");

		if (team != null) {
			int result = teamService.save(team);
			if (result > 0) {
				response.put("result", true);
				response.put("code", "000000");
				response.put("message", "记录添加成功!");
			}
		}

		return new ObjectMapper().writeValueAsString(response);
	}

	/** 修改团队 */
	@RequestMapping(value = "get", method = RequestMethod.GET)
	public String modifyTeam(String id, ModelMap modelMap) {
		Team team = teamService.queryById(id);
		if (team != null) {
			modelMap.put("record", team);
		}
		return "system/team_modify";
	}

	/** 更新团队 */
	@ResponseBody
	@RequestMapping(value = "update")
	public Object updateTeam(Team team) throws JsonGenerationException, JsonMappingException, IOException {
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("result", false);
		response.put("code", "999999");
		response.put("message", "记录更新失败!");

		if (team != null) {
			int result = teamService.update(team);
			if (result > 0) {
				response.put("result", true);
				response.put("code", "000000");
				response.put("message", "记录更新成功!");
			}
		}

		return new ObjectMapper().writeValueAsString(response);
	}

	/** 删除团队 */
	@ResponseBody
	@RequestMapping(value = "remove")
	public Object removeUserMapping(String id) throws Exception {
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("result", false);
		response.put("code", "999999");
		response.put("message", "记录删除失败!");

		int result = teamService.removeById(id);
		if (result > 0) {
			response.put("result", true);
			response.put("code", "000000");
			response.put("message", "记录删除成功!");
		}

		return new ObjectMapper().writeValueAsString(response);
	}
	

}
