package com.huaxia.opas.service.baseinfo;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.baseinfo.BaseCustInfo;
import com.huaxia.opas.service.BaseService;

public interface BaseCustInfoService extends BaseService<BaseCustInfo>{
	
	public void insertCustInfo(String appId) throws CoreException;
}
