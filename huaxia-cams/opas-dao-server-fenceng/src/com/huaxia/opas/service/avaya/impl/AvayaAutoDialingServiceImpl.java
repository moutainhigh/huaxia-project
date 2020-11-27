package com.huaxia.opas.service.avaya.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.avaya.AutoDialingDao;
import com.huaxia.opas.domain.avaya.Avaya;
import com.huaxia.opas.service.avaya.AvayaAutoDialingService;

public class AvayaAutoDialingServiceImpl extends AbstractDAO implements AvayaAutoDialingService,Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2892065884902139131L;
	
	@Resource(name = "autoDialingDao")
	private AutoDialingDao autoDialingDao;
	
	public int add(Avaya avaya){
		return getAutoDialingDao().add(avaya);
	}
	
	public int del(String avaya){
		return getAutoDialingDao().del(avaya);
	}
	
	public int update(Avaya avaya){
		return getAutoDialingDao().update(avaya);
	}
	
	
	public List<Avaya> queryAll(){
		return getAutoDialingDao().queryAll();
	}

	public AutoDialingDao getAutoDialingDao() {
		return autoDialingDao;
	}

	public int queryAvayaAutoDialingLimitCount(Avaya avaya) throws CoreException {
		return getAutoDialingDao().queryAvayaAutoDialingLimitCount(avaya);
	}

	public List<Avaya> queryAvayaAutoDialingLimit(Avaya avaya, int curNum, int pageNum) throws CoreException {
		return getAutoDialingDao().queryAvayaAutoDialingLimit(avaya, curNum, pageNum);
	}

}
