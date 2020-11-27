package com.huaxia.plaze.ui.system.service;

import com.huaxia.plaze.ui.system.domain.AuthorizeLog;

/**
 * 日志服务接口
 * 
 * @author zhiguo.li
 *
 */
public interface AuthorizeLogService extends LogService<AuthorizeLog> {

	int saveAuthorizeLog(AuthorizeLog log);
	
}
