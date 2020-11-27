package com.huaxia.opas.action.login;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.huateng.neofp.core.Action;
import com.huateng.neofp.core.Context;
import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.allot.AllotApplyAllot;
import com.huaxia.opas.domain.sysparm.CardProduct;
import com.huaxia.opas.domain.sysparm.UserCard;
import com.huaxia.opas.domain.system.ApOrg;
import com.huaxia.opas.domain.system.ApRole;
import com.huaxia.opas.domain.system.ApUser;
import com.huaxia.opas.domain.system.ApUserOrg;
import com.huaxia.opas.domain.system.ApUserRole;
import com.huaxia.opas.service.sysparm.CardProductService;
import com.huaxia.opas.service.sysparm.UserCardService;
import com.huaxia.opas.service.system.ApOrgService;
import com.huaxia.opas.service.system.ApRoleService;
import com.huaxia.opas.service.system.ApUserOrgService;
import com.huaxia.opas.service.system.ApUserRoleService;
import com.huaxia.opas.service.system.ApUserService;
import com.huaxia.opas.util.Md5;
import com.huaxia.opas.util.SequenceUtil;

/**
 * 
 * @author shihuan  
 * @version v1.1(初始v1.0)
 * @history 修改历史记录
 * ------------------------------------------------
 *  2017-10-25  修改组员机构时添加提示功能
 * ------------------------------------------------
 */
public class UserAction implements Action {

	private static Logger logger = LoggerFactory.getLogger(UserAction.class);

	@Resource(name = "apUserService")
	private ApUserService apUserService;

	@Resource(name = "apUserRoleService")
	private ApUserRoleService apUserRoleService;

	@Resource(name = "apRoleService")
	private ApRoleService apRoleService;

	@Resource(name = "apOrgService")
	private ApOrgService apOrgService;

	@Resource(name = "apUserOrgService")
	private ApUserOrgService apUserOrgService;

	@Resource(name = "userCardService")
	private UserCardService userCardService;
	
	@Resource(name = "cardProductService")
	private CardProductService cardProductService;
	
	@Autowired
	private HttpServletRequest request;

	/**
	 * 查询用户列表信息
	 */
	public void listUsers(Context ctx) {
		Gson gson = new Gson();
		ApUser apUser = gson.fromJson(gson.toJson(ctx.getDataMap()),
				ApUser.class);
		int curNum = 0;
		int curPage = Integer.parseInt(String.valueOf(ctx.getDataMap().get(
				"page") == null ? 0 : ctx.getDataMap().get("page")));
		int pageNum = Integer.parseInt(String.valueOf(ctx.getDataMap().get(
				"rows") == null ? 0 : ctx.getDataMap().get("rows")));
		if (curPage > 1) {
			curNum = (curPage - 1) * pageNum;
		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", apUser.getStatus());// 用户状态
		map.put("team", apUser.getTeam());// 团队
		map.put("station", apUser.getStation());// 岗位
		map.put("userCode", apUser.getUserCode());// 用户工号
		map.put("userName", apUser.getUserName());// 用户姓名
		map.put("department", apUser.getDepartment());// 组别
		map.put("orgId", ctx.getDataMap().get("orgId"));// 部门
		map.put("roleId", ctx.getDataMap().get("roleId"));// 角色
		map.put("userLdnamber", ctx.getDataMap().get("userLdnamber"));//身份证号

		int count = apUserOrgService.queryUserCount(map);

		List<ApUser> list = new ArrayList<ApUser>();

		if (count > 0) {

			list = apUserOrgService.queryUserList(map, curNum, pageNum);

		}

		Map<String, Object> dataMap = new HashMap<String, Object>();

		dataMap.put("total", count);

		dataMap.put("rows", list);

		ctx.setDataMap(dataMap);

	}

	/**
	 * 新增用户信息
	 * 
	 * @throws ParseException
	 */
	public void saveUser(Context ctx) throws CoreException, ParseException {
		Gson json = new Gson();
		Map<String, Object> map = ctx.getDataMap();
		String productCodeStr = (String)map.get("productCode");
		String cardcode[] = productCodeStr.split(",");
		//List<String> cardcode = (List<String>) map.get("productCode");
		String bzkLimitLevel1 = (String) map.get("bzkLimitLevel");
		String ydjLimitLevel1 = (String) map.get("ydjLimitLevel");
		BigDecimal bzkLimitLevel = null;
		if (!"".equals(bzkLimitLevel1)) {
			bzkLimitLevel = new BigDecimal(bzkLimitLevel1);
		}
		BigDecimal ydjLimitLevel = null;
		if (!"".equals(ydjLimitLevel1)) {
			ydjLimitLevel = new BigDecimal(ydjLimitLevel1);
		}
		String ipAddr = (String) map.get("ipAddr");
		String userValidityPerriod1 = (String) map.get("userValidityPerriod");
		Date userValidity = null;
		if (!"".equals(userValidityPerriod1)) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			userValidity = new Date();
			try {
				userValidity = sdf.parse(userValidityPerriod1);
			} catch (ParseException e1) {
				e1.printStackTrace();
				System.out.print("开始日期类型转换错误");
				return;
			}
		}
		//校验身份证号
		String jiaoyan = "^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9]|X)$";
		map.remove("userValidityPerriod");
		map.remove("bzkLimitLevel");
		map.remove("ydjLimitLevel");
		map.remove("ipAddr");
		ApUser apUser = json.fromJson(json.toJson(map), ApUser.class);
		String userLdnamber = (String) map.get("userLdnamber");
		apUser.setUserLdnamber(userLdnamber);
		String userCode = apUser.getUserCode();
		int count = apUserService.queryUserCodeCount(userCode);
		if (count > 0) {
			ctx.setData("isExist", true);
			return;
		}
		String crtuser = ctx.getData("userId");
		apUser.setCrtUser(crtuser);
		String uuid = SequenceUtil.getUUID();
		apUser.setUserId(uuid);
		if (!"".equals(userValidityPerriod1)) {
			apUser.setUserValidityPerriod(userValidity);
		}
		if (!"".equals(bzkLimitLevel1)) {
			apUser.setBzkLimitLevel(bzkLimitLevel);
		}
		if (!"".equals(ydjLimitLevel1)) {
			apUser.setYdjLimitLevel(ydjLimitLevel);
		}
		apUser.setIpAddr(ipAddr);
		String password = apUser.getUserPassword();
		apUser.setUserPassword(Md5.getMD5Str(password));
		apUser.setLoginStatus("0");
		UserCard usercard = null;
		try {
			if (productCodeStr != null && !"".equals(productCodeStr)) {// 获取前台穿的数组，进行遍历插入
				for (int i = 0;i<cardcode.length;i++) {
					usercard = new UserCard();
					String cardCode=cardcode[i];
					usercard.setCardId(SequenceUtil.getUUID());
					usercard.setUserId(uuid);
					usercard.setCardCode(cardCode);
					usercard.setLstUpdUser(crtuser);
					userCardService.insertSelective(usercard);
				}
			}
			apUserService.insertApUserSelective(apUser);
			ctx.setData("isSuccess", true);
		} catch (CoreException e) {
			ctx.setData("isSuccess", false);
			ctx.setData("exMsg", e.getMessage());
			throw e;
		}

	}

	/**
	 * 修改用户信息
	 */
	public void updateUser(Context ctx) throws CoreException {
		Map<String, Object> map = ctx.getDataMap();
		String productCodeStr = (String)map.get("productCode");
		String cardcode[] = productCodeStr.split(",");
		String bzkLimitLevel1 = (String) map.get("bzkLimitLevel");
		String ydjLimitLevel1 = (String) map.get("ydjLimitLevel");
		BigDecimal bzkLimitLevel = null;
		try {
		if (!"".equals(bzkLimitLevel1)) {
			bzkLimitLevel = new BigDecimal(bzkLimitLevel1);
		}
		BigDecimal ydjLimitLevel = null;
		if (!"".equals(ydjLimitLevel1)) {
			ydjLimitLevel = new BigDecimal(ydjLimitLevel1);
		}
		String ipAddr = (String) map.get("ipAddr");
		String userValidityPerriod1 = (String) map.get("userValidityPerriod");
		Date userValidity = null;
		if (!"".equals(userValidityPerriod1)) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			userValidity = new Date();
			try {
				userValidity = sdf.parse(userValidityPerriod1);
			} catch (ParseException e1) {
				e1.printStackTrace();
				System.out.print("开始日期类型转换错误");
				return;
			}
		}
		map.remove("userValidityPerriod");
		map.remove("bzkLimitLevel");
		map.remove("ydjLimitLevel");
		map.remove("ipAddr");
		Gson json = new Gson();
		ApUser apUser = json.fromJson(json.toJson(map), ApUser.class);
		String userLdnamber = (String) map.get("userLdnamber");
		apUser.setUserLdnamber(userLdnamber);
		String lstUpdUser = ctx.getData("userId");
		String userId = ctx.getData("userIId");
		//修改登入名-userCode 时校验
		//先根据主键查询   对比userCode是否重复 如果重复 则可以进行其他修改保存  
		//如果不重复  则再根据userCode进行查询  是否有数据 如果没有则可以修改
		ApUser apuser = apUserService.queryApUserByUserId(userId);
		String userCode = apUser.getUserCode();
		if(!apuser.getUserCode().equals(apUser.getUserCode())){
			int count = apUserService.queryUserCodeCount(userCode);
			if (count > 0) {
				ctx.setData("isExist", true);
				return;
			}
		}
		apUser.setLstUpdUser(lstUpdUser);
		apUser.setUserId(userId);
		if (!"".equals(userValidityPerriod1)) {
			apUser.setUserValidityPerriod(userValidity);
		}
		if (!"".equals(bzkLimitLevel1)) {
			apUser.setBzkLimitLevel(bzkLimitLevel);
		}
		if (!"".equals(ydjLimitLevel1)) {
			apUser.setYdjLimitLevel(ydjLimitLevel);
		}
		apUser.setIpAddr(ipAddr);
		if (apUser.getUserPassword() != null) {
			String userPassword = apUser.getUserPassword();
			apUser.setUserPassword(Md5.getMD5Str(userPassword));
		}
		UserCard usercard = null;
		if(productCodeStr != null && !"".equals(productCodeStr)){
			userCardService.deleteByUserId(userId);// 修改审批卡种时先进行删除，在进行添加
			if (cardcode != null && !"".equals(cardcode)) {// 获取前台穿的数组，进行遍历插入
				for (int i = 0;i<cardcode.length;i++) {
					usercard = new UserCard();
					String cardCode=cardcode[i];
					usercard.setCardId(SequenceUtil.getUUID());
					usercard.setUserId(userId);
					usercard.setCardCode(cardCode);
					usercard.setLstUpdUser(lstUpdUser);
					userCardService.insertSelective(usercard);
				}
			  }
			}else{
				userCardService.deleteByUserId(userId);
			}
			apUserService.updateApUserSelective(apUser);
			ctx.setData("isSuccess", true);
		} catch (CoreException e) {
			ctx.setData("isSuccess", false);
			ctx.setData("exMsg", e.getMessage());
			throw e;
		}
	}

	/**
	 * 删除用户信息
	 */
	public void removeUser(Context ctx) throws CoreException {

		Map map = ctx.getDataMap();

		String userId = (String) map.get("userIId");

		try {
			// 删除用户前先删除对应的用户角色关系表对应数据、用户机构关系表对应数据
			apUserOrgService.deleteApUserOrgByUserId(userId);
			// 根据用户id删除用户角色信息表
			apUserRoleService.deleteApUserRoleByUserId(userId);
			// 根据用户id删除用户角色信息表
			apUserService.deleteApUserByUserId(userId);
			ctx.setData("isSuccess", true);
		} catch (CoreException e) {
			ctx.setData("isSuccess", false);
			ctx.setData("exMsg", e.getMessage());
			throw e;
		}

	}

	/**
	 * 根据用户id获取对应的角色信息
	 */
	public void getUserRoleListByUserId(Context ctx) throws CoreException {
		Map map = ctx.getDataMap();
		String userId = (String) map.get("userIId");
		List<ApUserRole> userRoleList = new ArrayList<ApUserRole>();
		try {
			userRoleList = apUserRoleService.queryApUserRoleByUserId(userId);
			queryRoleList(ctx);
			ctx.setData("isSuccess", true);
		} catch (CoreException e) {
			ctx.setData("isSuccess", false);
			ctx.setData("exMsg", e.getMessage());
			throw e;
		}
		ctx.setData("userRoleList", userRoleList);
	}

	public void queryRoleList(Context ctx) throws CoreException {
		ApRole apRole = new ApRole();
		// 查询用户总数
		int count = apRoleService.queryRoleCount(apRole);
		List<ApRole> list = new ArrayList<ApRole>();
		if (count > 0) {
			list = apRoleService.queryRoleListWithoutPages(apRole);
		}
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("total", count);
		dataMap.put("rows", list);
		ctx.setDataMap(dataMap);
	}

	/**
	 * 修改用户对应的角色
	 */
	public void updateRoleOfUser(Context ctx) throws CoreException {
		Map map = ctx.getDataMap();
		try {
			List roleList = (ArrayList) map.get("userRole");
			if (roleList == null || roleList.isEmpty()) {
				throw new CoreException("请选择角色信息!");
			}
			String userId = (String) map.get("userIId");
			// 根据用户id删除用户角色信息表
			apUserRoleService.deleteApUserRoleByUserId(userId);
			// 新增用户角色信息入表
			for (int i = 0; i < roleList.size(); i++) {
				ApUserRole apUserRole = new ApUserRole();
				apUserRole.setUserId(userId);
				Map rolemap = new HashMap();
				rolemap = (Map) roleList.get(i);
				apUserRole.setRoleId((String) rolemap.get("roleId"));
				apUserRoleService.insertApUserRole(apUserRole);
			}
			ctx.setData("isSuccess", true);
		} catch (CoreException e) {
			ctx.setData("isSuccess", false);
			throw e;
		}
	}

	/**
	 * 获取用户相关联的机构信息及机构总信息列表
	 */
	public void getUserRelationOrgList(Context ctx) throws CoreException {
		Map map = ctx.getDataMap();
		String userId = (String) map.get("userIId");
		List<ApUserOrg> userOrgList = new ArrayList<ApUserOrg>();
		try {
			userOrgList = apUserOrgService.queryApUserOrgByUserId(userId);
			queryOrgList(ctx);
			ctx.setData("isSuccess", true);
		} catch (CoreException e) {
			ctx.setData("isSuccess", false);
			ctx.setData("exMsg", e.getMessage());
			throw e;
		}
		ctx.setData("userOrgList", userOrgList);
	}

	public void queryOrgList(Context ctx) throws CoreException {
		int count = 0;
		ApOrg apOrg = new ApOrg();
		// 查询用户总数
		count = apOrgService.queryOrgCount(apOrg);
		List<ApOrg> list = new ArrayList<ApOrg>();
		if (count > 0) {
			list = apOrgService.queryOrgListWithoutPage(apOrg);
		}
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("total", count);
		dataMap.put("rows", list);
		ctx.setDataMap(dataMap);
	}

	/**
	 * 修改用户对应的角色
	 * 
	 */
	public void relationOrgOfUser(Context ctx) throws CoreException {
		Map map = ctx.getDataMap();
		try {
			List orgList = (ArrayList) map.get("userOrg");
			if (orgList == null || orgList.isEmpty()) {
				throw new CoreException("请选择机构信息!");
			}
			String userId = (String) map.get("userIId");
			//先查询申请件分配表 该组员是否有未处理的申请件 有不能修改组
			List<AllotApplyAllot> applyList=apUserService.queryApplyByUserId(map);
			if(applyList==null||applyList.size()==0){
				// 根据用户id删除用户角色信息表
				apUserOrgService.deleteApUserOrgByUserId(userId);
				// 新增用户角色信息入表
				for (int i = 0; i < orgList.size(); i++) {
					ApUserOrg apUserOrg = new ApUserOrg();
					apUserOrg.setUserId(userId);
					Map rolemap = new HashMap();
					rolemap = (Map) orgList.get(i);
					apUserOrg.setOrgId((String) rolemap.get("orgId"));
					apUserOrgService.insertApUserOrg(apUserOrg);
				}
				ctx.setData("isSuccess", true);
			}else if(applyList.size()>0){
				//有未处理申请件
				ctx.setData("userSuccess", true);
			}
		
		} catch (CoreException e) {
			ctx.setData("isSuccess", false);
			throw e;

		}

	}

	/**
	 * 校验登录名是否存在
	 */
	public void checkUserCodeExist(Context ctx) {
		Gson json = new Gson();
		ApUser apUser = json.fromJson(json.toJson(ctx.getDataMap()),ApUser.class);
		String userCode = apUser.getUserCode();
		int count = apUserService.queryUserCodeCount(userCode);
		if (count > 0) {
			ctx.setData("isExist", true);
		}
	}

	/**
	 * 修改用户名密码
	 */
	public void updatePassword(Context ctx) throws CoreException {
		Map map = ctx.getDataMap();
		HttpSession session = request.getSession();
		String userId=session.getAttribute("userId").toString();
		String oldPassword=map.get("oldPassword").toString();
		String userPassword=map.get("userPassword").toString();
		ApUser apUser = new ApUser();
		ApUser  apUserData = apUserService.queryApUserByUserId(userId);//用于查出 库中密码
		if (apUserData!=null){
			String dataPassWord=apUserData.getUserPassword();
			if(dataPassWord.equals(oldPassword)){
				apUser.setUserPassword(userPassword);
				apUser.setUserCode(apUserData.getUserCode());
				try {
					apUserService.updatePassword(apUser);
					ctx.setData("isSuccess", true);
				} catch (CoreException e) {
					ctx.setData("isSuccess", false);
					ctx.setData("exMsg", e.getMessage());
					throw e;
				}
			}else{
				ctx.setData("isSuccess", false);
				ctx.setData("exMsg","请输入正确的原密码");
			}
		}else{
			ctx.setData("isSuccess", false);
			ctx.setData("exMsg","用户不存在");
		}	
	}

	/**
	 * 修改审批卡种时反显卡种信息
	 */
	public void queryUserCode(Context ctx) throws CoreException {
		Map map = ctx.getDataMap();
		String userId = (String) map.get("userIId");
		List<UserCard> usercard = new ArrayList<UserCard>();
		try {
			usercard = userCardService.selectByUserid(userId);
			queryUserCardList(ctx);
			ctx.setData("isSuccess", true);
		} catch (CoreException e) {
			ctx.setData("isSuccess", false);
			ctx.setData("exMsg", e.getMessage());
			throw e;
		}
		ctx.setData("usercard", usercard);
	}
	
	
	public void queryUserCardList(Context ctx) throws CoreException {
		int count = 0;
		// 查询用户总数
		count = userCardService.queryUserCardCount();
		List<UserCard> list = new ArrayList<UserCard>();
		if (count > 0) {
			list = userCardService.queryUserCardList();
		}
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("total", count);
		dataMap.put("rows", list);
		ctx.setDataMap(dataMap);
	}
	

	/**
	 * 新增审批卡种   shihuan  2017-04-11
	 */
	public void queryCardProd(Context ctx) throws CoreException {
		Gson gson = new Gson();
		CardProduct cardProduct = gson.fromJson(gson.toJson(ctx.getDataMap()), CardProduct.class);
		int curNum = 0;
		int curPage = Integer.parseInt(String.valueOf(ctx.getDataMap().get("page") == null ? 0 : ctx.getDataMap().get("page")));
		int pageNum = Integer.parseInt(String.valueOf(ctx.getDataMap().get("rows") == null ? 0 : ctx.getDataMap().get("rows")));
		if (curPage > 1) {
			curNum = (curPage - 1) * pageNum;
		}
		List<CardProduct> list = new ArrayList<CardProduct>();
		int count = cardProductService.queryCardProductCount(cardProduct);
		if (count > 0) {
			list = cardProductService.queryCardProduct(cardProduct, curNum, pageNum);
		}
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("total", count);
		dataMap.put("rows", list);
		ctx.setDataMap(dataMap);
	}
	
	/**
	 * 用户筛选条件--角色  shihuan  2017-04-11
	 */
	public void queryRoleName(Context ctx) throws CoreException {
		List<ApRole> apUser = apRoleService.queryRoleForUser();
		String jsonapUser=JSON.toJSONString(apUser);
		ctx.setData("jsonapUser", jsonapUser);
	}
	
	/**
	 * 用户筛选条件--组别   shihuan  2017-04-11
	 */
	public void queryOrgName(Context ctx) throws CoreException {
		List<ApOrg> aporg = apOrgService.queryOrgForUser();
		String jsonapOrg=JSON.toJSONString(aporg);
		ctx.setData("jsonapOrg", jsonapOrg);
	}

	public ApUserService getApUserService() {
		return apUserService;
	}

	public void setApUserService(ApUserService apUserService) {
		this.apUserService = apUserService;
	}

	public ApUserRoleService getApUserRoleService() {
		return apUserRoleService;
	}

	public void setApUserRoleSezrvice(ApUserRoleService apUserRoleService) {
		this.apUserRoleService = apUserRoleService;
	}

	public ApRoleService getApRoleService() {
		return apRoleService;
	}

	public void setApRoleService(ApRoleService apRoleService) {
		this.apRoleService = apRoleService;
	}

	public ApOrgService getApOrgService() {
		return apOrgService;
	}

	public void setApOrgService(ApOrgService apOrgService) {
		this.apOrgService = apOrgService;
	}

	public ApUserOrgService getApUserOrgService() {
		return apUserOrgService;
	}

	public void setApUserOrgService(ApUserOrgService apUserOrgService) {
		this.apUserOrgService = apUserOrgService;
	}

	public UserCardService getUserCardService() {
		return userCardService;
	}

	public void setUserCardService(UserCardService userCardService) {
		this.userCardService = userCardService;
	}

	public CardProductService getCardProductService() {
		return cardProductService;
	}

	public void setCardProductService(CardProductService cardProductService) {
		this.cardProductService = cardProductService;
	}
	

	
}
