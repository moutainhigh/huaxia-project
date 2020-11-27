package com.huaxia.opas.dao.sysparm.impl;

import java.util.List;
import java.util.Map;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.domain.sysparm.TelNo;
import com.huaxia.opas.dao.sysparm.TelNoDao;

/**
 * 电话号码参数DAO层实现类
 * @author liudi
 * @since 2017-04-07
 * @version 1.0
 */
public class TelNoDaoImpl extends AbstractDAO implements TelNoDao{



	/**
	 * 
	 */
	private static final long serialVersionUID = -4190762801320990777L;
	/**
	 * 
	 */
	private static final String NAMESPACES = "TelNo.";
	//添加状态为Start
	@Override
	public int saveTelNoStart(TelNo telNo) throws CoreException {
		return getSqlMap().insert(NAMESPACES + "insertTelNo1", telNo);
	}
	//添加状态为End
	@Override
	public int saveTelNoEnd(TelNo telNo) throws CoreException {
		return getSqlMap().insert(NAMESPACES + "insertTelNo2", telNo);
	}
	//修改
	@Override
	public int updateTelNo(TelNo telNo) throws CoreException {
		return getSqlMap().update(NAMESPACES + "updateTelNo", telNo);
	}
	//删除
	@Override
	public int deleteTelNo(String autoId) throws CoreException {
		return getSqlMap().delete(NAMESPACES + "deleteTelNoById", autoId);
	}
	//件数查询
	@Override
	public int queryTelNoCount(TelNo telNo) throws CoreException {
		return getSqlMap().queryForObject(NAMESPACES + "queryTelNoCount", telNo);
	}
	//查询
	@Override
	public List<TelNo> queryTelNo(TelNo telNo,int curNum,int pageNum) throws CoreException {
		return getSqlMap().queryForList(NAMESPACES + "queryTelNo1", telNo, curNum, pageNum);
	}
	//点击查询后，触发查询和排序功能
	@Override
	public List<TelNo> queryTelNo(Map<String,Object> params,int curNum,int pageNum) throws CoreException {
		return getSqlMap().queryForList(NAMESPACES + "queryTelNo2", params, curNum, pageNum);
	}
	//查询是否重复
	@Override
	public TelNo queryTelNo(TelNo telNo) throws CoreException {
		return getSqlMap().queryForObject(NAMESPACES + "queryTelNo3", telNo);
	}
	@Override
	public String queryTelNoStatus(String autoId) {
		return getSqlMap().queryForObject(NAMESPACES + "queryTelNoStatus", autoId);
	}
	@Override
	public int updateTelNoWithoutStatus(TelNo telNo) {
		return getSqlMap().update(NAMESPACES + "updateTelNoWithoutStatus", telNo);
	}

}
