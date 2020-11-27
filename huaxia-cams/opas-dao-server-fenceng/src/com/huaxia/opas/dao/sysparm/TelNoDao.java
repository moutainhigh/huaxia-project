package com.huaxia.opas.dao.sysparm;

import java.util.List;
import java.util.Map;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.sysparm.TelNo;
/**
 * 电话号码参数DAO层接口
 * @author liudi
 * @since 2017-04-07
 * @version 1.0
 */
public interface TelNoDao {
	//添加状态为Start
	public int saveTelNoStart(TelNo telNo) throws CoreException;
	//添加状态为End
	public int saveTelNoEnd(TelNo telNo) throws CoreException;
	//修改
	public int updateTelNo(TelNo telNo) throws CoreException;
	//删除
	public int deleteTelNo(String autoId) throws CoreException;
	//件数查询
	public int queryTelNoCount(TelNo telNo) throws CoreException;
	//查询
	public List<TelNo> queryTelNo(TelNo telNo,int curNum,int pageNum) throws CoreException;
	//点击查询后，触发查询和排序功能
	public List<TelNo> queryTelNo(Map<String,Object> params,int curNum,int pageNum) throws CoreException;
	//查询是否重复
	public TelNo queryTelNo(TelNo telNo) throws CoreException;
	//查询修改前的状态
	public String queryTelNoStatus(String autoId);
	//更新对象信息（不包括状态）
	public int updateTelNoWithoutStatus(TelNo telNo);

}
