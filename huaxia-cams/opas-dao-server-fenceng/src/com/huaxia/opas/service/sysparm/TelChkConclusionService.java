package com.huaxia.opas.service.sysparm;

import java.util.List;
import java.util.Map;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.sysparm.TelChkConclusion;
/**
 * 电核结论服务层接口
 * @author liudi
 * @since 2017-02-28
 * @version 1.0
 */
public interface TelChkConclusionService {
	//添加状态为Start
	public int saveTelChkConclusionStart(TelChkConclusion telChkConclusion) throws CoreException;
	//添加状态为End
	public int saveTelChkConclusionEnd(TelChkConclusion telChkConclusion) throws CoreException;
	//修改
	public int updateTelChkConclusion(TelChkConclusion telChkConclusion) throws CoreException;
	//删除
	public int deleteTelChkConclusion(String autoId) throws CoreException;
	//件数查询
	public int queryTelChkConclusionCount(TelChkConclusion telChkConclusion) throws CoreException;
	//查询
	public List<TelChkConclusion> queryTelChkConclusion(TelChkConclusion telChkConclusion,int curNum,int pageNum) throws CoreException;
	//点击查询后，触发查询和排序功能
	public List<TelChkConclusion> queryTelChkConclusion(Map<String,Object> params,int curNum,int pageNum) throws CoreException;
	//查询电核结论编号是否重复
	public TelChkConclusion queryTelChkConclusion(TelChkConclusion telChkConclusion) throws CoreException;
	
	//查询修改对象信息前的状态
	public String queryTelChkConclusionStatus(String autoId);
	//修改对象信息(不包括状态)
	public int updateTelChkConclusionWithoutStatus(TelChkConclusion telChkConclusion);

}
