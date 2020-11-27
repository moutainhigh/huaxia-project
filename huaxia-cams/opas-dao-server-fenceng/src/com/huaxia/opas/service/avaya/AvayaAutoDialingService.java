package com.huaxia.opas.service.avaya;

import java.util.List;
import java.util.Map;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.avaya.Avaya;

public interface AvayaAutoDialingService {
	//新增
	public int add(Avaya avaya) throws CoreException;
	//删除
	public int del(String avaya) throws CoreException;
	//修改
	public int update(Avaya avaya) throws CoreException;
	//查询全部
	public List<Avaya> queryAll() throws CoreException;
	//获取符合当前条件的数据总数
	public int queryAvayaAutoDialingLimitCount(Avaya avaya) throws CoreException;
	//分页查询
	public List<Avaya> queryAvayaAutoDialingLimit(Avaya avaya,int curNum,int pageNum) throws CoreException;


}
