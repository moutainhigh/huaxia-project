package com.huaxia.opas.dao.sysparm;

import java.util.List;
import java.util.Map;

import com.huaxia.opas.domain.sysparm.Business;

public interface NetCooperativeDao {

	//查询
	public int queryBusinessCount(Business business);
	public List<Business> queryBusinessList(Business business, int curNum, int pageNum);
	//添加
	public int insertBusiness(Business business);
	//修改
	public int updateBusiness(Business business);
	//删除
	public int deleteBusiness(Business business);
	//批量操作
	public int BusinesssetStatusOK(Business business);
	public int BusinesssetStatusNO(Business business);
	//唯一校验
	public Business queryBusinessOnly(Business business);
	//上传
	public int insertBusiness(List<Business> list);
	//查询全量分件展示
	public List<Map<String,String>> queryAllNet();
	
}
