package com.huaxia.opas.dao.dict;

import java.util.List;
import java.util.Map;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.dict.ApDictDetail;
import com.huaxia.opas.domain.dict.ApDictDetailSmall;

public interface ApDictDetailDao {

	public int insertApDictDetail(ApDictDetail apDictDetail) throws CoreException;

	public int insertApDictDetailSelective(ApDictDetail apDictDetail) throws CoreException;

	public int deleteApDictDetailByDictDetailId(String dictDetailId) throws CoreException;

	public int updateApDictDetail(ApDictDetail apDictDetail) throws CoreException;

	public int updateApDictDetailSelective(ApDictDetail apDictDetail) throws CoreException;

	public ApDictDetail queryApDictDetailByDictDetailId(String dictDetailId) throws CoreException;

	public List<ApDictDetailSmall> queryDictDetailByCode(String dictCode);

	public Map<String, Object> queryDictDetails(ApDictDetail dictDetail, int curNum, int pageNum);

	public int queryDictDetailTotal(ApDictDetail dictDetail);

	public int deleteDictDetailByDictId(String dictDetailId);

	public List<ApDictDetail> queryDictDetailListByDictId(String dictId) throws CoreException;
	
	//查询对比业务字典的方法(参数管理:行职业收入)
	public Map<String,String> queryCityMap();
	public Map<String, String> queryUnionMap();
	public Map<String, String> queryEducationMap();
	public Map<String, String> queryIndustryMap();
	public Map<String, String> queryJobMap();
	
	//添加业务字典时校验是否添加重复   shihuan 2017-04-10
	public int queryApDetailByDictCode(ApDictDetail dictDetail) throws CoreException;
	
	//添加业务字典时校验是否添加重复   shihuan 2017-04-10
	public int queryApDetailByDictName(ApDictDetail dictDetail) throws CoreException;

	public Map<String, String> queryRcdSrcMap();

	public Map<String, String> queryPlatTypeMap();

	public Map<String, String> queryCoprTypeMap();

	public Map<String, String> queryPlatRiskLvlMap();
	/**
	 * 接口模式-wenyh
	 * @return
	 */
	public Map<String, String> queryInterfaceModeMap();
}
