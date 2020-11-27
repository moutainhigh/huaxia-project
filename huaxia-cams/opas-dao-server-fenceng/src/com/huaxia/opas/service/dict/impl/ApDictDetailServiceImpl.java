package com.huaxia.opas.service.dict.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.dict.ApDictDetailDao;
import com.huaxia.opas.domain.dict.ApDictDetail;
import com.huaxia.opas.domain.dict.ApDictDetailSmall;
import com.huaxia.opas.service.dict.ApDictDetailService;

/**
 * 字典小类实现类
 * 
 * @author shihuan
 *
 */
public class ApDictDetailServiceImpl implements ApDictDetailService, Serializable {

	@Resource(name = "apDictDetailDao")
	private ApDictDetailDao apDictDetailDao;

	@Override
	public int save(ApDictDetail apDictDetail) throws CoreException {
		return getApDictDetailDao().insertApDictDetail(apDictDetail);
	}

	@Override
	public int remove(ApDictDetail apDictDetail) throws CoreException {
		return getApDictDetailDao().deleteDictDetailByDictId(
				apDictDetail.getDictDetailId());
	}

	@Override
	public int update(ApDictDetail apDictDetail) throws CoreException {
		return getApDictDetailDao().updateApDictDetail(apDictDetail);
	}

	@Override
	public ApDictDetail get(ApDictDetail apDictDetail) {
		try {
			return getApDictDetailDao().queryApDictDetailByDictDetailId(
					apDictDetail.getDictDetailId());
		} catch (CoreException e) {
		}
		return null;
	}

	@Override
	public int insertApDictDetailSelective(ApDictDetail apDictDetail)
			throws CoreException {
		return getApDictDetailDao().insertApDictDetailSelective(apDictDetail);
	}

	@Override
	public int updateApDictDetailSelective(ApDictDetail apDictDetail)
			throws CoreException {
		return getApDictDetailDao().updateApDictDetailSelective(apDictDetail);
	}

	@Override
	public ApDictDetail queryApDictDetailByDictDetailId(String dictDetailId)
			throws CoreException {
		return getApDictDetailDao().queryApDictDetailByDictDetailId(
				dictDetailId);
	}

	@Override
	public List<ApDictDetailSmall> queryDictDetailByCode(String dictCode) {
		return getApDictDetailDao().queryDictDetailByCode(dictCode);
	}

	@Override
	public Map<String, Object> queryDictDetails(ApDictDetail dictDetail,
			int curNum, int pageNum) {
		return getApDictDetailDao().queryDictDetails(dictDetail, curNum,
				pageNum);
	}

	@Override
	public int queryDictDetailTotal(ApDictDetail dictDetail) {
		return getApDictDetailDao().queryDictDetailTotal(dictDetail);
	}

	@Override
	public List<ApDictDetail> queryDictDetailListByDictId(String dictId)
			throws CoreException {
		return getApDictDetailDao().queryDictDetailListByDictId(dictId);
	}
	
	//推荐来源
	@Override
	public Map<String, String> queryRcdSrcMap() throws CoreException {
		return getApDictDetailDao().queryRcdSrcMap();
	}
	
	//平台类型
	@Override
	public Map<String, String> queryPlatTypeMap() throws CoreException {
		return getApDictDetailDao().queryPlatTypeMap();
	}

	//合作类型
	@Override
	public Map<String, String> queryCoprTypeMap() throws CoreException {
		return getApDictDetailDao().queryCoprTypeMap();
	}

	//平台风险层级
	@Override
	public Map<String, String> queryPlatRiskLvlMap() throws CoreException {
		return getApDictDetailDao().queryPlatRiskLvlMap();
	}
	
	/**
	 * 接口模式-wenyh
	 * @return
	 * @throws CoreException
	 */
	@Override
	public Map<String, String> queryInterfaceModeMap() throws CoreException {
		return getApDictDetailDao().queryInterfaceModeMap();
	}

	
	//添加业务字典时校验是否添加重复   shihuan 2017-04-10
	@Override
	public int queryApDetailByDictCode(ApDictDetail dictDetail)
			throws CoreException {
		return getApDictDetailDao().queryApDetailByDictCode(
				dictDetail);
	}
	//添加业务字典时校验是否添加重复   shihuan 2017-04-10
	@Override
	public int queryApDetailByDictName(ApDictDetail dictDetail)
			throws CoreException {
		return getApDictDetailDao().queryApDetailByDictName(
				dictDetail);
	}
	public ApDictDetailDao getApDictDetailDao() {
		return apDictDetailDao;
	}

	public void setApDictDetailDao(ApDictDetailDao apDictDetailDao) {
		this.apDictDetailDao = apDictDetailDao;
	}

}
