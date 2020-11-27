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
import com.huaxia.opas.domain.system.ApOrg;
import com.huaxia.opas.service.system.ApOrgService;
import com.huaxia.opas.service.system.ApUserOrgService;
import com.huaxia.opas.util.SequenceUtil;

import net.sf.json.JSONArray;

/**
 * 机构
 * 
 * @author shihuan
 *
 */
public class OrgAction implements Action {

	private static Logger logger = LoggerFactory.getLogger(OrgAction.class);

	@Resource(name = "apOrgService")
	private ApOrgService apOrgService;

	@Resource(name = "apUserOrgService")
	private ApUserOrgService apUserOrgService;

	/**
	 * 查询机构列表信息
	 */
	public void listOrgs(Context ctx) throws CoreException {

		Gson gson = new Gson();

		ApOrg apOrg = gson.fromJson(gson.toJson(ctx.getDataMap()), ApOrg.class);

		int curNum = 0;

		int curPage = Integer.parseInt(String.valueOf(ctx.getDataMap().get(
				"page") == null ? 0 : ctx.getDataMap().get("page")));

		int pageNum = Integer.parseInt(String.valueOf(ctx.getDataMap().get(
				"rows") == null ? 0 : ctx.getDataMap().get("rows")));

		if (curPage > 1) {

			curNum = (curPage - 1) * pageNum;

		}

		// 查询机构总条数
		int count = apOrgService.queryOrgCount(apOrg);

		List<ApOrg> list = new ArrayList<ApOrg>();

		if (count > 0) {

			list = apOrgService.queryOrgList(apOrg, curNum, pageNum);
		}

		Map<String, Object> dataMap = new HashMap<String, Object>();

		dataMap.put("total", count);

		dataMap.put("rows", list);

		ctx.setDataMap(dataMap);

	}

	/**
	 * 修改机构信息
	 */
	public void updateOrg(Context ctx) throws CoreException {
		Gson json = new Gson();
		ApOrg apOrg = json.fromJson(json.toJson(ctx.getDataMap()), ApOrg.class);
		String orgName = apOrg.getOrgName();
		String orgNo = apOrg.getOrgNo();
		String orgId = apOrg.getOrgId();
		String flag = apOrg.getStatus();
		// 设置机构停用
		if ("0".equals(flag)) {
			// 关联性校验，机构下挂有用户，提示不能更改
			int count = apUserOrgService.queryApUserOrgCountByOrgId(apOrg
					.getOrgId());
			if (count > 0) {
				ctx.setData("checkFlag", "1");
				ctx.setData("isSuccess", true);
				return;
			}
		}
		//比较是否相同
		ApOrg apOrg1 = apOrgService.queryApOrgByOrgId(orgId);
		ApOrg apOrg2=apOrgService.queryOrgByOrgNo(orgNo);
		if(!apOrg1.getOrgName().equals(apOrg.getOrgName())){
			int count = apOrgService.queryOrgName(orgName);
			if(count>0){
				ctx.setData("isFalse", true);
				return;
			}
			int count1 = apOrgService.queryOrgCountByOrgNo(orgNo);
			if(count1>0){
				ctx.setData("isFalses", true);
				return;
			}
		}
		String orgId2="";
		//判断是否存在orgNo时候的组
		if(apOrg2!=null){
			orgId2=apOrg2.getOrgId();
			//不是相同的组   不应该更新
			if(!orgId.equals(orgId2)){
				ctx.setData("isFalses2", true);
				return;
			}
		}
		try {

			apOrgService.updateApOrg(apOrg);

		} catch (CoreException e) {

			ctx.setData("exMsg", e.getMessage());

			throw e;
		}
		ctx.setData("isSuccess", true);
	}

	/**
	 * 删除机构信息
	 */
	public void removeOrg(Context ctx) throws CoreException {
		Map map = ctx.getDataMap();
		String orgId = (String) map.get("orgId");
		try {
			apOrgService.deleteApOrgByOrgId(orgId);
		} catch (CoreException e) {
			ctx.setData("exMsg", e.getMessage());
			throw e;
		}
		ctx.setData("isSuccess", true);
	}

	/**
	 * 新增机构信息
	 */
	public void addOrg(Context ctx) throws CoreException {
		Gson json = new Gson();
		String crtuser = ctx.getData("userId");
		ApOrg apOrg = json.fromJson(json.toJson(ctx.getDataMap()), ApOrg.class);
		String orgName = apOrg.getOrgName();
		String orgNo = apOrg.getOrgNo();
		apOrg.setOrgId(SequenceUtil.getUUID());
		apOrg.setCrtUser(crtuser);
		int count = apOrgService.queryOrgName(orgName);
		if(count>0){
			ctx.setData("isFalse", true);
			return;
		}
		int count1 = apOrgService.queryOrgCountByOrgNo(orgNo);
		if(count1>0){
			ctx.setData("isFalses", true);
			return;
		}
		try {
			apOrgService.insertApOrg(apOrg);
		} catch (CoreException e) {
			ctx.setData("exMsg", e.getMessage());
			throw e;
		}
		ctx.setData("isSuccess", true);
	}
	
	/**
	 * 根据组级别获取组数据-标准卡
	 * @param ctx
	 * @throws CoreException
	 */
	public void queryOrgByOrgLevelBzk(Context ctx) throws CoreException {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgLevel", "4");
		List<Map<String, Object>> resultList = apOrgService.queryOrgByOrgLevel(map);
		JSONArray ja = JSONArray.fromObject(resultList);
		ctx.setData("resultList", ja.toString());
	}
	/**
	 * 根据组级别获取组数据-易达金
	 * @param ctx
	 * @throws CoreException
	 */
	public void queryOrgByOrgLevelYdj(Context ctx) throws CoreException {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgLevel", "5");
		List<Map<String, Object>> resultList = apOrgService.queryOrgByOrgLevel(map);
		JSONArray ja = JSONArray.fromObject(resultList);
		ctx.setData("resultList", ja.toString());
	}
}
