package com.huaxia.opas.service.dict;

import java.util.List;
import java.util.Map;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.dict.ApDictDetail;
import com.huaxia.opas.domain.dict.ApDictDetailSmall;
import com.huaxia.opas.service.BaseService;

/**
 * 字典小类
 * 
 * @author shihuan
 *
 */
public interface ApDictDetailService extends BaseService<ApDictDetail> {

	public int insertApDictDetailSelective(ApDictDetail apDictDetail) throws CoreException;

	public int updateApDictDetailSelective(ApDictDetail apDictDetail) throws CoreException;

	public ApDictDetail queryApDictDetailByDictDetailId(String dictDetailId) throws CoreException;

	public List<ApDictDetailSmall> queryDictDetailByCode(String dictCode);

	public Map<String, Object> queryDictDetails(ApDictDetail dictDetail,int curNum, int pageNum);

	public int queryDictDetailTotal(ApDictDetail dictDetail);

	public List<ApDictDetail> queryDictDetailListByDictId(String dictId) throws CoreException;

	public int remove(ApDictDetail apDictDetail) throws CoreException;
	
	public Map<String, String> queryRcdSrcMap() throws CoreException;
	
	public Map<String, String> queryPlatTypeMap() throws CoreException;
	
	public Map<String, String> queryCoprTypeMap() throws CoreException;
	
	public Map<String, String> queryPlatRiskLvlMap() throws CoreException;
	/**
	 * 接口模式-wenyh
	 * @return
	 * @throws CoreException
	 */
	public Map<String, String> queryInterfaceModeMap() throws CoreException;
	
	//添加业务字典时校验是否添加重复   shihuan 2017-04-10
	public int queryApDetailByDictCode(ApDictDetail dictDetail) throws CoreException;
	
	//添加业务字典时校验是否添加重复   shihuan 2017-04-10
	public int queryApDetailByDictName(ApDictDetail dictDetail) throws CoreException;

}
