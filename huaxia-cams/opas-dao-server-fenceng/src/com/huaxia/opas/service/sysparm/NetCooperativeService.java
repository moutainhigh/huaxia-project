package com.huaxia.opas.service.sysparm;

import java.util.List;
import java.util.Map;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.sysparm.BatchfileInfo;
import com.huaxia.opas.domain.sysparm.Business;

public interface NetCooperativeService {

	//查询
	public int queryBusinessCount(Business business);
	public List<Business> queryBusinessList(Business business, int curNum, int pageNum);
	//添加
	public int insertBusiness(Business business) throws CoreException;
	//修改
	public int updateBusiness(Business business) throws CoreException;
	//删除
	public int deleteBusiness(Business business) throws CoreException;
	//批量启用
	public int BusinesssetStatusOK(Business business) throws CoreException;
	//批量禁用
	public int BusinesssetStatusNO(Business business) throws CoreException;
	//公司编码和模块编码组合唯一校验
	public Business queryBusinessOnly(Business business);
	//上传方法
	public int insertBusinessFromFile(List<Business> list, BatchfileInfo batchfileInfo);
	//查询全量分件展示
	public List<Map<String,String>> queryAllNet() throws CoreException;
	
	
	
	

}
