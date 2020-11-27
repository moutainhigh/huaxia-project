package com.huaxia.opas.service.sysparm.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.domain.sysparm.TelChkConclusion;
import com.huaxia.opas.dao.sysparm.TelChkConclusionDao;
import com.huaxia.opas.service.sysparm.TelChkConclusionService;
/**
 * 电核结论服务层实现类
 * @author liudi
 * @since 2017-02-28
 * @version 1.0
 */
public class TelChkConclusionServiceImpl extends AbstractDAO implements TelChkConclusionService,Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2299230603532921974L;

	@Resource(name = "telChkConclusionDao")
	private TelChkConclusionDao telChkConclusionDao;

	public TelChkConclusionDao getTelChkConclusionDao() {
		return telChkConclusionDao;
	}

	public void setTelChkConclusionDao(TelChkConclusionDao telChkConclusionDao) {
		this.telChkConclusionDao = telChkConclusionDao;
	}

	//添加状态为Start
	public int saveTelChkConclusionStart(TelChkConclusion telChkConclusion) throws CoreException {
		return getTelChkConclusionDao().saveTelChkConclusionStart(telChkConclusion);
	}
	
	//添加状态为End
	public int saveTelChkConclusionEnd(TelChkConclusion telChkConclusion) throws CoreException {
		return getTelChkConclusionDao().saveTelChkConclusionEnd(telChkConclusion);
	}
	
	//修改
	public int updateTelChkConclusion(TelChkConclusion telChkConclusion) throws CoreException {
		return getTelChkConclusionDao().updateTelChkConclusion(telChkConclusion);
	}
	
	//删除
	public int deleteTelChkConclusion(String autoId) throws CoreException {
		return getTelChkConclusionDao().deleteTelChkConclusion(autoId);
	}
	
	//件数查询
	public int queryTelChkConclusionCount(TelChkConclusion telChkConclusion) throws CoreException {
		return getTelChkConclusionDao().queryTelChkConclusionCount(telChkConclusion);
	}
	
	//查询
	public List<TelChkConclusion> queryTelChkConclusion(TelChkConclusion telChkConclusion,int curNum,int pageNum) throws CoreException {
		return getTelChkConclusionDao().queryTelChkConclusion(telChkConclusion, curNum, pageNum);
	}
	
	//点击查询后，触发查询和排序功能
	public List<TelChkConclusion> queryTelChkConclusion(Map<String,Object> params,int curNum,int pageNum) throws CoreException {
		return getTelChkConclusionDao().queryTelChkConclusion(params, curNum, pageNum);
	}
	
	//查询电核结论编号是否重复
	public TelChkConclusion queryTelChkConclusion(TelChkConclusion telChkConclusion) throws CoreException {
		return getTelChkConclusionDao().queryTelChkConclusion(telChkConclusion);
	}

	@Override
	public String queryTelChkConclusionStatus(String autoId) {
		return getTelChkConclusionDao().queryTelChkConclusionStatus(autoId);
	}

	@Override
	public int updateTelChkConclusionWithoutStatus(TelChkConclusion telChkConclusion) {
		return getTelChkConclusionDao().updateTelChkConclusionWithoutStatus(telChkConclusion);
	}
	

}
