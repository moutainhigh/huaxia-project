package com.huaxia.opas.service.sysparm;

import java.util.List;
import java.util.Map;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.sysparm.TelSource;
/**
 * 照会对象参数服务层接口
 * @author liudi
 * @since 2017-02-28
 * @version 1.0
 */
public interface TelSourceService {
	//添加状态为Start
	public int saveTelSourceStart(TelSource telSource) throws CoreException;
	//添加状态为End
	public int saveTelSourceEnd(TelSource telSource) throws CoreException;
	//修改
	public int updateTelSource(TelSource telSource) throws CoreException;
	//删除
	public int deleteTelSource(String autoId) throws CoreException;
	//件数查询
	public int queryTelSourceCount(TelSource telSource) throws CoreException;
	//查询
	public List<TelSource> queryTelSource(TelSource telSource,int curNum,int pageNum) throws CoreException;
	//查询是否重复
	public List<TelSource> queryTelSource(Map<String,Object> params,int curNum,int pageNum) throws CoreException;
	//点击查询后，触发查询和排序功能
	public TelSource queryTelSource(TelSource telSource) throws CoreException;
	
	//查询更新前的状态
	public String queryTelSourceStatus(String autoId);
	//修改对象信息(不包括状态的修改)
	public int updateTelSourceWithoutStatus(TelSource telSource);
}
