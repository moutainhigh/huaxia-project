package com.huaxia.opas.action.login;

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
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.huateng.neofp.core.Action;
import com.huateng.neofp.core.Context;
import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.cache.service.ITokenService;
import com.huaxia.opas.domain.MenuObj;
import com.huaxia.opas.domain.system.ApMenu;
import com.huaxia.opas.domain.system.ApOnline;
import com.huaxia.opas.domain.system.ApOrg;
import com.huaxia.opas.domain.system.ApRole;
import com.huaxia.opas.domain.system.ApUser;
import com.huaxia.opas.domain.system.ApUserRole;
import com.huaxia.opas.service.system.ApMenuService;
import com.huaxia.opas.service.system.ApOnlineService;
import com.huaxia.opas.service.system.ApOrgService;
import com.huaxia.opas.service.system.ApRoleService;
import com.huaxia.opas.service.system.ApUserService;
import com.huaxia.opas.util.Base64;
import com.huaxia.opas.util.Constant;
import com.huaxia.opas.util.MenuTreeUtil;
import com.huaxia.opas.util.StringUtil;

/**
 * 用户登录
 * 
 * @author shihuan
 *
 */
public class LoginAction implements Action {

	private static Logger logger = LoggerFactory.getLogger(LoginAction.class);

	@Resource(name = "apUserService")
	private ApUserService apUserService;

	@Resource(name = "apMenuService")
	private ApMenuService apMenuService;

	@Resource(name = "apRoleService")
	private ApRoleService apRoleService;

	@Resource(name = "apOrgService")
	private ApOrgService apOrgService;

	@Resource(name = "tokenService")
	private ITokenService tokenService;
	
	@Resource(name = "apOnlineService")
	private ApOnlineService apOnlineService;

	@Autowired
	private HttpServletRequest request;
	/**
	 * 登录
	 * 
	 * @param context
	 * @throws CoreException
	 */
	public void login(Context context) throws CoreException {
		String userCode = StringUtils.trimToEmpty((String) context.getDataMap().get("userCode"));
		String userPassword =context.getDataMap().get("userPassword").toString();
		String verifyStatus = "3";//校验有效期标志 
		boolean vaildateSuccess=false;//是否验证通过
		try {
			// 校验用户
			ApUser user =apUserService.queryApUserByUserCode(userCode);
			if(user!=null){
				Integer loginFailNum=user.getLoginFailNum();//登录失败次数
				if (!userPassword.equals(user.getUserPassword())) {
					//用户名 密码 登录失败 
					if(loginFailNum!=null&&loginFailNum>=3){
						// 当login_fail_num 等于3 时
						ApUser userParam=new ApUser();
						userParam.setUserCode(userCode);
						loginFailNum++;
						userParam.setLoginFailNum(loginFailNum);
						userParam.setLoginFailTime(new Date());
						apUserService.updateApUserFailNumAndFailTime(userParam);
						context.setData("msg", "登录失败次数过多，请3分钟后再登!");
					}else{// 将 login_fail_num 加1 
						ApUser userParam=new ApUser();
						userParam.setUserCode(userCode);
						if(loginFailNum==null||loginFailNum==0){
							userParam.setLoginFailNum(1);
						}else{
							loginFailNum++;
							userParam.setLoginFailNum(loginFailNum);
							if(loginFailNum>=3){//附上 登录失败次数过多，login_fail_time 附上当前值 
								userParam.setLoginFailTime(new Date());
							}
						}
						apUserService.updateApUserFailNumAndFailTime(userParam);
						context.setData("msg", "用户名或密码不正确!");
					}	
				}else{
					if(loginFailNum!=null&&loginFailNum>=3){// 判断  login_fail_time 与当前时间是否大于3分钟  是的话正常登录 不是的话 无法登录
						Date loginFailTime=user.getLoginFailTime();
						Date nowTime=new Date();
						if(nowTime.getTime()>=(loginFailTime.getTime()+180000)){//正常登录
							vaildateSuccess=true;
						}
						context.setData("msg", "登录失败次数过多，请3分钟后再登!");
					}else{
						vaildateSuccess=true;
					}
						if(vaildateSuccess){
							// GUID不容易重复，但是属性名称不再调整，还是叫“uuid”
							String guid = StringUtil.randomGUIDPlainString();
							boolean isTrue = tokenService.addUuid(guid, user.getUserId());
							tokenService.addUserId(user.getUserId(),guid);
							String guidRedis = tokenService.getGuid(user.getUserId());
							String str = tokenService.getToken(user.getUserId());
							if (isTrue) {
								String status = user.getStatus();
								//增加一个字段,单独用来判断用户是否需要修改密码
								String passwordFlag = user.getPasswordFlag();
								//用户到期日校验
								Date userValidityPerriod = user.getUserValidityPerriod();
								if(userValidityPerriod != null && !("").equals(userValidityPerriod)){
									verifyStatus = verifyTime(userValidityPerriod);
								}								
								if (status.equals("0")) {
									context.setData("msg", "该用户已被禁用!");
								} else if (status.equals("2")) {
									context.setData("msg", "该用户已被注销!");
								} else if(passwordFlag.equals("1")){
									//弹出修改密码的窗口
									context.setData("status", status);
								} else if(verifyStatus.equals("2")){
									context.setData("msg", "您的系统权限已到期,无法登陆,请联系系统管理员。");
								} else {
									//登录成功 将 login_fail_num 和 login_fail_time 字段 变为null
									ApUser userParam=new ApUser();
									userParam.setUserCode(userCode);
									userParam.setLoginFailNum(null);
									userParam.setLoginFailTime(null);
									apUserService.updateApUserFailNumAndFailTime(userParam);
									//-----
									HttpSession session = request.getSession();
									Map<String,Object> map = new HashMap<String,Object>();
									map.put("userId", user.getUserId());
									ApOnline apOnline = new ApOnline();
									int n = apOnlineService.queryOnlineCount(map);
									if(n > 0){
										apOnline = apOnlineService.queryApUserByUserId(user.getUserId());
										apOnline.setSessionId(guid);
										apOnline.setIpAddr(request.getRemoteAddr());
										apOnline.setLoginTime(new Date());
										apOnline.setStatus("1");
										apOnlineService.updateApOnline(apOnline);
									}else{
										int m = apOnlineService.queryOnlineCount(null);
										apOnline.setOnlineId((m + 1) + "");
										apOnline.setUserId(user.getUserId());
										apOnline.setUserCode(userCode);
										apOnline.setIpAddr(request.getRemoteAddr());
										apOnline.setSessionId(guid);
										apOnline.setUserName(user.getUserName());
										apOnline.setLoginTime(new Date());
										apOnline.setStatus("1");
										apOnlineService.insertApOnline(apOnline);
									}
									session.setAttribute("userId", user.getUserId());
									session.setAttribute("guid", guidRedis);
									//将session的过期时间设置为60秒
									session.setMaxInactiveInterval(3600);
									context.setData("uuid", guid);
									if(verifyStatus.equals("1")){
										context.setData("pro", "您的系统权限已不足十日，到期后将无法登录，请联系系统管理员。");
									}
								}
							} else {
								context.setData("msg", "uuid缓存失败!");
							}
						}
				}
			} else {
				context.setData("msg", "用户名不存在！！请核对后重新输入!");
			}
		} catch (Exception e) {
			logger.error("用户认证异常..", e);
			context.setData("msg", "用户认证异常!");
		}
	}

	/**
	 * 用户token 验证
	 * 
	 * @param user
	 * @return
	 */
	public boolean validateToken(ApUser user) {
		boolean isLogin = false;
		try {
			String tokenKey = user.getUserId();
			String tokenStr = tokenService.getToken(tokenKey);
			if (StringUtils.isNotBlank(tokenStr)) {
				isLogin = true;
			}
		} catch (Exception e) {
			logger.error("用户.." + user.getUserCode() + "获取登陆token验证异常..." + e.getMessage(), e);
		}
		return isLogin;
	}

	/**
	 * 用户信息验证
	 * 
	 * @param userPassword
	 *            用户密码
	 * @param userCode
	 *            用户登陆名
	 * @throws CoreException
	 */
	public ApUser validateUserCode(String userCode, String userPassword) throws CoreException {
		ApUser apUser = null;
		if (StringUtils.isBlank(userCode)) {
			return null;
		}
		if (logger.isDebugEnabled()) {
			logger.info("userCode=" + userCode + ",userPassword=" + userPassword);
		}

		apUser = apUserService.queryApUserByUserCode(userCode);

		if (logger.isDebugEnabled()) {
			logger.debug("Current login user is: " + (apUser != null ? apUser.getUserName() : "NULL"));
		}

		if (apUser != null) {
			// 对密码进行加密校验
			//String ciphertext = Md5.getMD5Str(userPassword);

			if (!userPassword.equals(apUser.getUserPassword())) {
				logger.info("[Warning] Password is error!");
				return null;
			}
		}
		return apUser;
	}

	/**
	 * 登录用户相关信息（用户、角色、菜单）
	 * 
	 * @param context
	 * @throws Exception
	 */
	public void userRelationInfo(Context context) throws Exception {
		Map<String, Object> userRelationInfoMap = new HashMap<String, Object>();
		ApUserRole apUsrRole = new ApUserRole();
		String userId = String.valueOf(context.getDataMap().get("loginId"));
		String roleId = String.valueOf(context.getDataMap().get("roleId"));
		// String orgId = String.valueOf(context.getDataMap().get("orgId"));
		apUsrRole.setUserId(userId);
		apUsrRole.setRoleId(roleId);
		userRelationInfoMap = userRelatioInfo(apUsrRole);
		List<MenuObj> menus = (List) userRelationInfoMap.get("menuInfo");
		if (menus == null) {
			menus = new ArrayList<MenuObj>();
		}
		userRelationInfoMap.put("token", cacheUserToke(userRelationInfoMap));

		if (menus.size() <= 0) {
			userRelationInfoMap.put("exceptionMsg", "请为用户分配角色,菜单后再登陆!");
		} else {
			userRelationInfoMap.put("token", cacheUserToke(userRelationInfoMap));
		}
		context.setDataMap(userRelationInfoMap);

	}

	/**
	 * 用户角色与机构信息查询
	 * 
	 * @param context
	 * @throws CoreException
	 */
	public void userRoleOrg(Context context) {
		Map<String, Object> userRelation = new HashMap<String, Object>();
		ApUserRole userrole = new ApUserRole();
		List<MenuObj> menus = null;
		String roleId = "";
		// String orgId = "";
		String userId = "";
		String uuid = String.valueOf(context.getDataMap().get("uuid"));
		try {
			userId = tokenService.getUuid(uuid);
			if (userId == null || "".equals(userId)) {
				userRelation.put("exceptionMsg", Constant.EXCEPTION_UUID_MSG);
				context.setDataMap(userRelation);
				return;
			}
			userRelation.put("userId", userId);
			// 用户角色查询
			List<ApRole> userRoles = apRoleService.queryUserRoles(userId);
			// 用户机构查询
			List<ApOrg> userOrgs = apOrgService.queryUserOrgs(userId);
			// 单角色单机构用户
			if (userRoles.size() == 1) {
				roleId = userRoles.get(0).getRoleId();
				// orgId = userOrgs.get(0).getOrgId();
				userrole.setUserId(userId);
				userrole.setRoleId(roleId);
				userRelation = userRelatioInfo(userrole);
				menus = userRelation.get("menuInfo") == null ? new ArrayList<MenuObj>()
						: (List<MenuObj>) userRelation.get("menuInfo");
				if (menus.size() <= 0) {
					userRelation.put("exceptionMsg", "请为角色分配菜单后再登陆!");
				} else {
					userRelation.put("token", cacheUserToke(userRelation));
				}
			} else if (userRoles == null || userRoles.size() <= 0) {
				userRelation.put("exceptionMsg", "请为用户分配角色后再登陆!");
			} else if (userOrgs == null || userOrgs.size() <= 0) {
				userRelation.put("exceptionMsg", "请为用户分配机构后再登陆!");
			} else if (userRoles.size() > 1) {
				userRelation.put("userRoles", userRoles);
				// userRelation.put("userOrgs", userOrgs);
			}
		} catch (Exception e) {
			logger.error("用户机构与角色查询异常.." + e.getMessage());
			userRelation.put("exceptionMsg", Constant.EXCEPTION_OPERATE);
		}

		context.setDataMap(userRelation);
	}

	/**
	 * 用户关联信息查询(用户,角色)
	 * 
	 * @param userId
	 * @param roleId
	 * @param orgId
	 * @return
	 * @throws CoreException
	 */
	public Map<String, Object> userRelatioInfo(ApUserRole userRole) throws CoreException {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		System.out.println(userRole.getRoleId());
		if (userRole.getRoleId() == null || "".equals(userRole.getRoleId())) {
			// 用户信息
			ApUser userInfo = apUserService.queryApUserByUserId(userRole.getUserId());
			// 菜单信息
			ArrayList<ApMenu> menus = (ArrayList<ApMenu>) apMenuService.queryUserMenuByUserId(userRole);
			List<MenuObj> treeMenu = MenuTreeUtil.buildMenuData(menus);
			//wdb sql已排序 去掉代码里排序
			//List<MenuObj> treeMenu = MenuTreeUtil.buildMenuData(MenuTreeUtil.sortMenus(menus));
			dataMap.put("menuInfo", treeMenu);
			dataMap.put("userInfo", userInfo);
		} else {
			// 用户信息
			ApUser userInfo = apUserService.queryApUserByUserId(userRole.getUserId());
			// 角色信息
			ApRole roleInfo = apRoleService.queryApRoleByRoleId(userRole.getRoleId());
			// 机构信息
			// ApOrg orgInfo = apOrgService.queryApOrgByOrgId(orgId);
			// 菜单信息
			ArrayList<ApMenu> menus = (ArrayList<ApMenu>) apMenuService.queryUserMenus(userRole);
			List<MenuObj> treeMenu = MenuTreeUtil.buildMenuData(menus);
			//wdb sql已排序 去掉代码里排序
			//List<MenuObj> treeMenu = MenuTreeUtil.buildMenuData(MenuTreeUtil.sortMenus(menus));
			dataMap.put("menuInfo", treeMenu);
			dataMap.put("userInfo", userInfo);
			dataMap.put("roleInfo", roleInfo);
		}
		// dataMap.put("orgInfo", orgInfo);
		return dataMap;
	}

	/**
	 * 缓存用户token信息(userid+usercode)
	 * 
	 * @param user
	 * @throws CoreException
	 */
	public String cacheUserToke(Map<String, Object> userRelation) throws Exception {
		// 生成并缓存token
		ApUser user = (ApUser) userRelation.get("userInfo");
		String strToken = Base64
				.encode(new StringBuffer(user.getUserId()).append(user.getUserCode()).toString().getBytes());
		tokenService.addToken(user.getUserId(), strToken);
		return strToken;
	}

	/**
	 * 注销
	 * 
	 * @param context
	 * @return
	 */
	public void logout(Context context) {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		String userId = null;
		try {
			userId = String.valueOf(context.getDataMap().get("userIds"));
			tokenService.deleteToken(userId);
			ApOnline apOnline = apOnlineService.queryApUserByUserId(userId);
			apOnline.setStatus("0");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh24:mm:ss");
			apOnline.setLogoutTime(new Date());
			apOnlineService.updateApOnline(apOnline);
			dataMap.put("backcode", "1");
		} catch (Exception e) {
			dataMap.put("backcode", "2");
			logger.error("用户.." + userId + "..登出异常!");
		} finally {
			context.setDataMap(dataMap);
		}
	}

	/**
	 * 登陆界面修改密码
	 * @return
	 */
	public void setPassword(Context ctx) throws CoreException {
		Map map = ctx.getDataMap();
		String userId=map.get("user_Id").toString();
		String oldPassword=map.get("oldPassword").toString();
		String userPassword=map.get("userPassword").toString();
		ApUser apUser = new ApUser();
		ApUser  apUserData = apUserService.queryApUserByUserCode(userId);//查询用户是否存在
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
	
	
	
	public ApUserService getApUserService() {
		return apUserService;
	}

	public void setApUserService(ApUserService apUserService) {
		this.apUserService = apUserService;
	}

	public ApMenuService getApMenuService() {
		return apMenuService;
	}

	public void setApMenuService(ApMenuService apMenuService) {
		this.apMenuService = apMenuService;
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

	public ITokenService getTokenService() {
		return tokenService;
	}

	public void setTokenService(ITokenService tokenService) {
		this.tokenService = tokenService;
	}

	public ITokenService getTokenImplService() {
		return tokenService;
	}

	public void setTokenImplService(ITokenService tokenImplService) {
		this.tokenService = tokenImplService;
	}

	//校验用户有效期
	public String verifyTime(Date userValidityPerriod){		
		String verifyStatus = "0";
		//查询数据库当前时间
		Map<String, String> timeMap = apUserService.queryTime();
		//将查询结果转换成毫秒
		String time = timeMap.get("TIME");
		SimpleDateFormat formate = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date date = formate.parse(time);
			//将date转换成毫秒,计算差值
			long time1 = date.getTime();
			long time2 = userValidityPerriod.getTime();
			long days = time2 - time1;
			//将毫秒转换成天,判断
			long day = days / (1000*60*60*24);
			if(day>10){
				verifyStatus = "0";
			}else if(day <= 10 && day > 0){
				verifyStatus = "1";
			}else{
				verifyStatus = "2";
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return verifyStatus;
	}
}
