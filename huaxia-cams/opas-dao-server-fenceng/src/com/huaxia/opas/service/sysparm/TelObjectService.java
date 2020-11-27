package com.huaxia.opas.service.sysparm;

import java.util.List;
import java.util.Map;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.sysparm.TelObject;
/**
 * 照会对象参数服务层接口
 * @author liudi
 * @since 2017-02-28
 * @version 1.0
 */
public interface TelObjectService {
	//添加状态为Start
	public int saveTelObjectStart(TelObject telObject) throws CoreException;
	//添加状态为End
	public int saveTelObjectEnd(TelObject telObject) throws CoreException;
	//修改
	public int updateTelObject(TelObject telObject) throws CoreException;
	//删除
	public int deleteTelObject(String autoId) throws CoreException;
	//件数查询
	public int queryTelObjectCount(TelObject telObject) throws CoreException;
	//查询
	public List<TelObject> queryTelObject(TelObject telObject,int curNum,int pageNum) throws CoreException;
	//点击查询后，触发查询和排序功能
	public List<TelObject> queryTelObject(Map<String,Object> params,int curNum,int pageNum) throws CoreException;
	//查询是否重复
	public TelObject queryTelObject(TelObject telObject) throws CoreException;
	//查询修改信息前的状态
	public String queryTelObjectStatus(String autoId);
	//修改照会对象信息（不修改状态）
	public int updateTelObjectWithoutStatus(TelObject telObject);

}
