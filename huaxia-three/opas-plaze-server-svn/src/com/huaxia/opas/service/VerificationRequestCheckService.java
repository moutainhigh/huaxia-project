package com.huaxia.opas.service;

import com.huaxia.opas.domain.VerificationRequestCheck;

/**
 * 人行请求检查服务接口
 * 
 * @author zhiguo.li
 *
 */
public interface VerificationRequestCheckService {
	
	VerificationRequestCheck get();
	
	int update();

}
