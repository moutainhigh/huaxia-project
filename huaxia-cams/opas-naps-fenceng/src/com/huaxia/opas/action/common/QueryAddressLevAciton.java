package com.huaxia.opas.action.common;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huateng.neofp.core.Action;
import com.huateng.neofp.core.Context;
import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.cache.service.address.AddresMachLevService;


public class QueryAddressLevAciton implements Action{
	@SuppressWarnings("unused")
	private static Logger logger = LoggerFactory.getLogger(QueryAddressLevAciton.class);
	@Resource(name = "machLevService")
	private AddresMachLevService machLevService;
	public void getAddressLev(Context context) throws CoreException{
		String WorkType=(String) context.getData("worktype");
		logger.info("--------------------------->"+WorkType);
		String Address=(String) context.getData("address");
		logger.info("--------------------------->"+Address);
		String CompanyName=(String) context.getData("companyname");
		logger.info("--------------------------->"+CompanyName);
		
		String lev=machLevService.getMachResult(WorkType, Address, CompanyName);
		context.setData("LEV", lev);
		context.setData("success", true);
		return;
	}
	public AddresMachLevService getMachLevService() {
		return machLevService;
	}
	public void setMachLevService(AddresMachLevService machLevService) {
		this.machLevService = machLevService;
	}
}
