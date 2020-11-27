package com.huaxia.opas.service.system;

import com.huaxia.opas.domain.system.CustBaseInfo;

public interface CustBaseInfoService {

	CustBaseInfo selectByPrimaryKey(String autoId) throws Exception;

}