package com.huaxia.plaze.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.huaxia.plaze.ui.system.domain.AuthorizeLog;
import com.huaxia.plaze.ui.system.domain.User;
import com.huaxia.plaze.ui.system.service.AuthorizeLogService;
import com.huaxia.plaze.ui.system.service.UserService;

@Aspect
@Component
public class AuthorizeLogAspect {
	
	private static final Logger logger = LoggerFactory.getLogger(AuthorizeLogAspect.class);
	
	@Autowired
	private AuthorizeLogService authorizeLogService;
	
	@Autowired
	private UserService userService;

	@Around("execution(* com.huaxia.plaze.ui.system.controller.AuthorizationController.log*(..))")
	public Object after(ProceedingJoinPoint point) {
		Object response = null;
		try {
			// 获取目标方法参数
			Object[] args = point.getArgs();
			if (args != null && args.length > 0) {
				String account = null;
				
				// 执行动作
				String action = point.getSignature().getName();
				if (action.endsWith("in")) {
					action = "登录";
					account = String.valueOf(args[1]);
				} else if (action.endsWith("out")) {
					action = "退出";
					account = String.valueOf(args[0]);
				} else {
					action = "未知";
				}
				
				User user = userService.queryByAccount(account);
				if (user != null) {
					AuthorizeLog log = new AuthorizeLog();
					log.setAccount(account);
					log.setStaffName(user.getStaffName());
					log.setCertNo(user.getCertNo());
					log.setIp(user.getIp());
					log.setAction(action);
					authorizeLogService.saveAuthorizeLog(log);
				}
			}
			response = point.proceed();
		} catch (Throwable e) {
			logger.error("授权日志记录异常:{}", new Object[] { e.getMessage() }, e);
		}
		return response;
	}

}
