package com.huaxia.opas.action.login;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.huateng.neofp.core.Action;
import com.huateng.neofp.core.Context;
import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.system.ApRole;
import com.huaxia.opas.service.system.ApRoleMenuService;
import com.huaxia.opas.service.system.ApRoleService;
import com.huaxia.opas.service.system.ApUserRoleService;
import com.huaxia.opas.util.SequenceUtil;

/**
 * 角色
 * 
 * @author shihuan
 *
 */
public class RoleAction implements Action {

	private static Logger logger = LoggerFactory.getLogger(RoleAction.class);

	@Resource(name = "apRoleService")
	private ApRoleService apRoleService;

	@Resource(name = "apUserRoleService")
	private ApUserRoleService apUserRoleService;

	@Resource(name = "apRoleMenuService")
	private ApRoleMenuService apRoleMenuService;

	/**
	 * 查询用户列表信息
	 * 
	 * @throws CoreException
	 */
	public void listRoles(Context ctx) throws CoreException {
		Gson gson = new Gson();
		ApRole apRole = gson.fromJson(gson.toJson(ctx.getDataMap()),
				ApRole.class);

		int curNum = 0;
		int curPage = Integer.parseInt(String.valueOf(ctx.getDataMap().get(
				"page") == null ? 0 : ctx.getDataMap().get("page")));
		int pageNum = Integer.parseInt(String.valueOf(ctx.getDataMap().get(
				"rows") == null ? 0 : ctx.getDataMap().get("rows")));

		if (curPage > 1) {
			curNum = (curPage - 1) * pageNum;
		}

		List<ApRole> list = new ArrayList<ApRole>();

		int count = apRoleService.queryRoleCount(apRole);
		if (count > 0) {
			list = apRoleService.queryRoleList(apRole, curNum, pageNum);
		}

		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("total", count);
		dataMap.put("rows", list);
		ctx.setDataMap(dataMap);
	}

	/**
	 * 保存角色信息
	 */
	public void saveRole(Context ctx) throws CoreException {
		Gson json = new Gson();
		ApRole apRole = json.fromJson(json.toJson(ctx.getDataMap()),ApRole.class);
		String roleCode = apRole.getRoleCode();
		String roleName = apRole.getRoleName();
		String lstUpdUser = ctx.getData("userId");
		apRole.setRoleId(SequenceUtil.getUUID());
		apRole.setCrtUser(lstUpdUser);
		int apRole1 = apRoleService.queryApRoleByRoleCode(roleCode);
		if (apRole1 > 0) {
			ctx.setData("isFailed", true);
			return;
		}
		int apRole2 = apRoleService.queryApRoleByRoleName(roleName);
		if (apRole2 > 0) {
			ctx.setData("isFailedName", true);
			return;
		}
		try {
			apRoleService.insertApRole(apRole);
		} catch (CoreException e) {
			ctx.setData("exMsg", e.getMessage());
			throw e;
		}
		ctx.setData("isSuccess", true);
	}

	/**
	 * 修改角色信息
	 */
	public void updateRole(Context ctx) throws CoreException {
		Gson json = new Gson();
		ApRole apRole = json.fromJson(json.toJson(ctx.getDataMap()),ApRole.class);
		String roleId = apRole.getRoleId();
		String roleCode = apRole.getRoleCode();
		String roleName = apRole.getRoleName();
		String lstUpdUser = ctx.getData("userId");
		apRole.setRoleId(apRole.getRoleId());
		apRole.setLstUpdUser(lstUpdUser);
		ApRole apRoles = apRoleService.queryApRoleByRoleId(roleId);
		int apRole1 = apRoleService.queryApRoleByRoleCode(roleCode);
		if(!apRoles.getRoleCode().equals(apRole.getRoleCode())){
			if (apRole1 > 0) {
				ctx.setData("isFailed", true);
				return;
			}
		}
		int apRole2 = apRoleService.queryApRoleByRoleName(roleName);
		if(!apRoles.getRoleName().equals(apRole.getRoleName())){
			if (apRole2 > 0) {
				ctx.setData("isFailedName", true);
				return;
			}
		}
		try {
			apRoleService.updateApRole(apRole);
		} catch (CoreException e) {
			ctx.setData("exMsg", e.getMessage());
			throw e;
		}
		ctx.setData("isSuccess", true);
	}

	/**
	 * 删除角色信息
	 */
	public void deleteRole(Context ctx) throws CoreException {
		Map map = ctx.getDataMap();
		String roleId = (String) map.get("roleId");
		try {
			// 删除角色表信息
			apRoleService.deleteApRoleByRoleId(roleId);
			// 删除用户角色表信息
			apUserRoleService.deleteApUserRoleByRoleId(roleId);
			// 删除角色菜单信息
			apRoleMenuService.deleteApRoleMenuByRoleId(roleId);
		} catch (CoreException e) {
			ctx.setData("exMsg", e.getMessage());
			throw e;
		}
		ctx.setData("isSuccess", true);
	}

}
