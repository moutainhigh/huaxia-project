package com.huaxia.opas.action.logRecord;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.huateng.neofp.core.Action;
import com.huateng.neofp.core.Context;
import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.system.ApUser;
import com.huaxia.opas.service.record.OperaMarkService;
import com.huaxia.opas.service.system.ApUserService;
import com.huaxia.opas.util.OperaMarkUtil;

/**
 * 后台菜单操作记录留痕
 * 
 * @author wangyang
 * @since 2020-10-10
 *
 */
public class OperaMarkAction implements Action {

	private final static Logger Logger = LoggerFactory.getLogger(OperaMarkAction.class);

	@Autowired
	private ApUserService apUserService;

	@Autowired
	private OperaMarkService operaMarkService;

	public void operaMarkRecord(Context ctx) {
		Logger.info("【OperaMarkAction】 record start.");

		Map<String, Object> dataMap = ctx.getDataMap();
		Assert.notNull(dataMap, "参数不能为空");
		Logger.info("【OperaMarkAction】 dataMap:{}", dataMap.toString());
		
		// 获取请求对象
		ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes();
		HttpServletRequest request = requestAttributes.getRequest();
		dataMap.put("userIp", OperaMarkUtil.getIpAddr(request));
		
		if (!dataMap.containsKey("userId")) {
			String userId = (String) request.getSession().getAttribute("userId");
			dataMap.put("userId", getUserCodeByUserId(userId));
		}

		operaMarkService.insertRecord(dataMap);
	}

	/**
	 * 根据userId查询用户信息
	 * 
	 * @param userId
	 * @return userCode 用户登录名
	 */
	private String getUserCodeByUserId(String userId) {
		ApUser user = null;
		try {
			user = apUserService.queryApUserByUserId(userId);
		} catch (CoreException e) {
			Logger.error("【OperaMarkAction】 query ApUser Error.userId:{}", userId);
			e.printStackTrace();
		}

		if (null != user)
			return user.getUserCode();

		return userId;
	}

}
