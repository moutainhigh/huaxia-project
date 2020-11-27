package com.huaxia.opas.service.sysparm.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.domain.sysparm.TelSource;
import com.huaxia.opas.dao.sysparm.TelSourceDao;
import com.huaxia.opas.service.sysparm.TelSourceService;
/**
 * 照会对象参数服务层实现类
 * @author liudi
 * @since 2017-02-28
 * @version 1.0
 */
public class TelSourceServiceImpl extends AbstractDAO implements TelSourceService,Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1807099556729091827L;

	@Resource(name = "telSourceDao")
	private TelSourceDao telSourceDao;


	public TelSourceDao getTelSourceDao() {
		return telSourceDao;
	}

	public void setTelSourceDao(TelSourceDao telSourceDao) {
		this.telSourceDao = telSourceDao;
	}

	//添加状态为Start
	public int saveTelSourceStart(TelSource telSource) throws CoreException {
		return getTelSourceDao().saveTelSourceStart(telSource);
	}
	
	//添加状态为End
	public int saveTelSourceEnd(TelSource telSource) throws CoreException {
		return getTelSourceDao().saveTelSourceEnd(telSource);
	}
	
	//修改
	public int updateTelSource(TelSource telSource) throws CoreException {
		return getTelSourceDao().updateTelSource(telSource);
	}
	
	//删除
	public int deleteTelSource(String autoId) throws CoreException {
		return getTelSourceDao().deleteTelSource(autoId);
	}
	
	//件数查询
	public int queryTelSourceCount(TelSource telSource) throws CoreException {
		return getTelSourceDao().queryTelSourceCount(telSource);
	}
	
	//查询
	public List<TelSource> queryTelSource(TelSource telSource,int curNum,int pageNum) throws CoreException {
		return getTelSourceDao().queryTelSource(telSource, curNum, pageNum);
	}
	
	//查询是否重复
	public TelSource queryTelSource(TelSource telSource) throws CoreException {
		return getTelSourceDao().queryTelSource(telSource);
	}
	
	//点击查询后，触发查询和排序功能
	public List<TelSource> queryTelSource(Map<String,Object> params,int curNum,int pageNum) throws CoreException {
		return getTelSourceDao().queryTelSource(params, curNum, pageNum);
	}

	@Override
	public String queryTelSourceStatus(String autoId) {
		return getTelSourceDao().queryTelSourceStatus(autoId);
	}

	@Override
	public int updateTelSourceWithoutStatus(TelSource telSource) {
		return getTelSourceDao().updateTelSourceWithoutStatus(telSource);
	}
}
