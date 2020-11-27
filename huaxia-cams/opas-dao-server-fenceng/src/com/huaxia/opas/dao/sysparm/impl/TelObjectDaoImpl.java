package com.huaxia.opas.dao.sysparm.impl;

import java.util.List;
import java.util.Map;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.domain.sysparm.TelObject;
import com.huaxia.opas.dao.sysparm.TelObjectDao;

/**
 * 照会对象参数DAO层实现类
 * @author liudi
 * @since 2017-02-28
 * @version 1.0
 */
public class TelObjectDaoImpl extends AbstractDAO implements TelObjectDao{


	/**
	 * 
	 */
	private static final long serialVersionUID = -4863123787314935168L;
	/**
	 * 
	 */
	private static final String NAMESPACES = "TelObject.";
	//添加状态为Start
	@Override
	public int saveTelObjectStart(TelObject telObject) throws CoreException {
		return getSqlMap().insert(NAMESPACES + "insertTelObject1", telObject);
	}
	//添加状态为End
	@Override
	public int saveTelObjectEnd(TelObject telObject) throws CoreException {
		return getSqlMap().insert(NAMESPACES + "insertTelObject2", telObject);
	}
	//修改
	@Override
	public int updateTelObject(TelObject telObject) throws CoreException {
		return getSqlMap().update(NAMESPACES + "updateTelObject", telObject);
	}
	//删除
	@Override
	public int deleteTelObject(String autoId) throws CoreException {
		return getSqlMap().delete(NAMESPACES + "deleteTelObjectById", autoId);
	}
	//件数查询
	@Override
	public int queryTelObjectCount(TelObject telObject) throws CoreException {
		return getSqlMap().queryForObject(NAMESPACES + "queryTelObjectCount", telObject);
	}
	//查询
	@Override
	public List<TelObject> queryTelObject(TelObject telObject,int curNum,int pageNum) throws CoreException {
		return getSqlMap().queryForList(NAMESPACES + "queryTelObject1", telObject, curNum, pageNum);
	}
	//点击查询后，触发查询和排序功能
	@Override
	public List<TelObject> queryTelObject(Map<String,Object> params,int curNum,int pageNum) throws CoreException {
		return getSqlMap().queryForList(NAMESPACES + "queryTelObject2", params, curNum, pageNum);
	}
	//查询是否重复
	@Override
	public TelObject queryTelObject(TelObject telObject) throws CoreException {
		return getSqlMap().queryForObject(NAMESPACES + "queryTelObject3", telObject);
	}
	@Override
	public String queryTelObject(String autoId) {
		return getSqlMap().queryForObject(NAMESPACES + "queryTelObject",autoId);
	}
	@Override
	public int updateTelObjectWithoutStatus(TelObject telObject) {
		return getSqlMap().update(NAMESPACES + "updateTelObjectWithoutStatus",telObject);
	}

}
