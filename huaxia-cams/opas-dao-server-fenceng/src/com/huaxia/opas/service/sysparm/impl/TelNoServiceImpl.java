package com.huaxia.opas.service.sysparm.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.sysparm.TelNoDao;
import com.huaxia.opas.domain.sysparm.TelNo;
import com.huaxia.opas.service.sysparm.TelNoService;
/**
 * 电话号码参数服务层实体类
 * @author liudi
 * @since 2017-04-07
 * @version 1.0
 */
public class TelNoServiceImpl extends AbstractDAO implements TelNoService,Serializable  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8930694345412866101L;

	@Resource(name = "telNoDao")
	private TelNoDao telNoDao;


	public TelNoDao getTelNoDao() {
		return telNoDao;
	}

	public void setTelNoDao(TelNoDao telNoDao) {
		this.telNoDao = telNoDao;
	}

	//添加状态为Start
	public int saveTelNoStart(TelNo telNo) throws CoreException {
		return getTelNoDao().saveTelNoStart(telNo);
	}
	
	//添加状态为End
	public int saveTelNoEnd(TelNo telNo) throws CoreException {
		return getTelNoDao().saveTelNoEnd(telNo);
	}
	
	//修改
	public int updateTelNo(TelNo telNo) throws CoreException {
		return getTelNoDao().updateTelNo(telNo);
	}
	
	//删除
	public int deleteTelNo(String autoId) throws CoreException {
		return getTelNoDao().deleteTelNo(autoId);
	}
	
	//件数查询
	public int queryTelNoCount(TelNo telNo) throws CoreException {
		return getTelNoDao().queryTelNoCount(telNo);
	}
	
	//查询
	public List<TelNo> queryTelNo(TelNo telNo,int curNum,int pageNum) throws CoreException {
		return getTelNoDao().queryTelNo(telNo, curNum, pageNum);
	}
	
	//点击查询后，触发查询和排序功能
	public List<TelNo> queryTelNo(Map<String,Object> params,int curNum,int pageNum) throws CoreException {
		return getTelNoDao().queryTelNo(params, curNum, pageNum);
	}
	
	//查询是否重复
	public TelNo queryTelNo(TelNo telNo) throws CoreException {
		return getTelNoDao().queryTelNo(telNo);
	}

	@Override
	public String queryTelNoStatus(String autoId) {
		return getTelNoDao().queryTelNoStatus(autoId);
	}

	@Override
	public int updateTelNoWithoutStatus(TelNo telNo) {
		return getTelNoDao().updateTelNoWithoutStatus(telNo);
	}
}
