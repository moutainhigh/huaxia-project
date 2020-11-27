package com.huaxia.opas.service.sysparm.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.domain.sysparm.TelObject;
import com.huaxia.opas.dao.sysparm.TelObjectDao;
import com.huaxia.opas.service.sysparm.TelObjectService;
/**
 * 照会对象参数服务层实体类
 * @author liudi
 * @since 2017-02-28
 * @version 1.0
 */
public class TelObjectServiceImpl extends AbstractDAO implements TelObjectService,Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8930694345412866101L;

	@Resource(name = "telObjectDao")
	private TelObjectDao telObjectDao;

	public TelObjectDao getTelObjectDao() {
		return telObjectDao;
	}

	public void setTelObjectDao(TelObjectDao telObjectDao) {
		this.telObjectDao = telObjectDao;
	}

	//添加状态为Start
	public int saveTelObjectStart(TelObject telObject) throws CoreException {
		return getTelObjectDao().saveTelObjectStart(telObject);
	}
	
	//添加状态为End
	public int saveTelObjectEnd(TelObject telObject) throws CoreException {
		return getTelObjectDao().saveTelObjectEnd(telObject);
	}
	
	//修改
	public int updateTelObject(TelObject telObject) throws CoreException {
		return getTelObjectDao().updateTelObject(telObject);
	}
	
	//删除
	public int deleteTelObject(String autoId) throws CoreException {
		return getTelObjectDao().deleteTelObject(autoId);
	}
	
	//件数查询
	public int queryTelObjectCount(TelObject telObject) throws CoreException {
		return getTelObjectDao().queryTelObjectCount(telObject);
	}
	
	//查询
	public List<TelObject> queryTelObject(TelObject telObject,int curNum,int pageNum) throws CoreException {
		return getTelObjectDao().queryTelObject(telObject, curNum, pageNum);
	}
	
	//点击查询后，触发查询和排序功能
	public List<TelObject> queryTelObject(Map<String,Object> params,int curNum,int pageNum) throws CoreException {
		return getTelObjectDao().queryTelObject(params, curNum, pageNum);
	}
	
	//查询是否重复
	public TelObject queryTelObject(TelObject telObject) throws CoreException {
		return getTelObjectDao().queryTelObject(telObject);
	}

	@Override
	public String queryTelObjectStatus(String autoId) {
		return getTelObjectDao().queryTelObject(autoId);
	}

	@Override
	public int updateTelObjectWithoutStatus(TelObject telObject) {
		return getTelObjectDao().updateTelObjectWithoutStatus(telObject);
	}
}
