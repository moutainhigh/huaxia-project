package com.huaxia.opas.dao.sysparm.impl;

import java.util.List;
import java.util.Map;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.domain.sysparm.TelChkConclusion;
import com.huaxia.opas.dao.sysparm.TelChkConclusionDao;

/**
 * 电核结论DAO层实现类
 * @author liudi
 * @since 2017-02-28
 * @version 1.0
 */
public class TelChkConclusionDaoImpl extends AbstractDAO implements TelChkConclusionDao{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2291524610943083530L;
	/**
	 * 
	 */
	private static final String NAMESPACES = "TelChkConclusion.";
	//添加状态为Start
	@Override
	public int saveTelChkConclusionStart(TelChkConclusion telChkConclusion) throws CoreException {
		return getSqlMap().insert(NAMESPACES + "insertTelChkConclusion1", telChkConclusion);
	}
	//添加状态为End
	@Override
	public int saveTelChkConclusionEnd(TelChkConclusion telChkConclusion) throws CoreException {
		return getSqlMap().insert(NAMESPACES + "insertTelChkConclusion2", telChkConclusion);
	}
	//修改
	@Override
	public int updateTelChkConclusion(TelChkConclusion telChkConclusion) throws CoreException {
		return getSqlMap().update(NAMESPACES + "updateTelChkConclusion", telChkConclusion);
	}
	//删除
	@Override
	public int deleteTelChkConclusion(String autoId) throws CoreException {
		return getSqlMap().delete(NAMESPACES + "deleteTelChkConclusionById", autoId);
	}
	//件数查询
	@Override
	public int queryTelChkConclusionCount(TelChkConclusion telChkConclusion) throws CoreException {
		return getSqlMap().queryForObject(NAMESPACES + "queryTelChkConclusionCount", telChkConclusion);
	}
	//查询
	@Override
	public List<TelChkConclusion> queryTelChkConclusion(TelChkConclusion telChkConclusion,int curNum,int pageNum) throws CoreException {
		return getSqlMap().queryForList(NAMESPACES + "queryTelChkConclusion1", telChkConclusion, curNum, pageNum);
	}
	//点击查询后，触发查询和排序功能
	@Override
	public List<TelChkConclusion> queryTelChkConclusion(Map<String,Object> params,int curNum,int pageNum) throws CoreException {
		return getSqlMap().queryForList(NAMESPACES + "queryTelChkConclusion2", params, curNum, pageNum);
	}
	//查询电核结论编号是否重复
	@Override
	public TelChkConclusion queryTelChkConclusion(TelChkConclusion telChkConclusion) throws CoreException {
		return getSqlMap().queryForObject(NAMESPACES + "queryTelChkConclusion3", telChkConclusion);
	}
	@Override
	public String queryTelChkConclusionStatus(String autoId) {
		return getSqlMap().queryForObject(NAMESPACES + "queryTelChkConclusionStatus", autoId);
	}
	@Override
	public int updateTelChkConclusionWithoutStatus(TelChkConclusion telChkConclusion) {
		return getSqlMap().update(NAMESPACES + "updateTelChkConclusionWithoutStatus", telChkConclusion);
	}

}
