package com.huaxia.opas.service.dict.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.dict.ApDictDao;
import com.huaxia.opas.domain.dict.ApDict;
import com.huaxia.opas.service.dict.ApDictService;

/**
 * 字典大类实现类
 * 
 * @author shihuan
 *
 */
public class ApDictServiceImpl implements ApDictService, Serializable {

	@Resource(name = "apDictDao")
	private ApDictDao apDictDao;

	@Override
	public int save(ApDict apDict) throws CoreException {
		return getApDictDao().insertApDict(apDict);
	}

	@Override
	public int remove(ApDict apDict) throws CoreException {
		return getApDictDao().deleteApDictByDictId(apDict.getDictId());
	}

	@Override
	public int update(ApDict apDict) throws CoreException {
		return getApDictDao().updateApDict(apDict);
	}

	@Override
	public ApDict get(ApDict apDict) {
		try {
			return getApDictDao().queryApDictByDictCode(apDict.getDictCode());
		} catch (CoreException e) {
		}
		return null;
	}

	@Override
	public int insertApDictSelective(ApDict apDict) throws CoreException {
		return getApDictDao().insertApDictSelective(apDict);
	}

	@Override
	public int updateApDictSelective(ApDict apDict) throws CoreException {
		return getApDictDao().updateApDictSelective(apDict);
	}

	@Override
	public ApDict queryApDictByDictId(String dictId) throws CoreException {
		return getApDictDao().queryApDictByDictId(dictId);
	}

	@Override
	public Map<String, Object> queryDicts(ApDict dict, int curNum, int pageNum) {
		return getApDictDao().queryDicts(dict, curNum, pageNum);
	}

	@Override
	public int queryDictTotal(ApDict dict) {
		return getApDictDao().queryDictTotal(dict);
	}

	@Override
	public List<Map<String, Object>> queryDicts() throws CoreException {
		return getApDictDao().queryDicts();
	}

	@Override
	public List<String> queryDictCodes() {
		return getApDictDao().queryDictCodes();
	}
	//添加业务字典时校验是否添加重复   shihuan 2017-04-10
	@Override
	public ApDict queryApDictByDictCode(String dictCode) throws CoreException {
		return getApDictDao().queryApDictByDictCode(dictCode);
	}
	//添加业务字典时校验是否添加重复   shihuan 2017-04-10
	@Override
	public ApDict queryApDictByDictName(String dictName) throws CoreException {
		return getApDictDao().queryApDictByDictName(dictName);
	}
	
	//修改业务字典时校验是否添加重复   shihuan 2017-04-11
	@Override
	public List<ApDict> queryUpApDictByDictCode(String dictCode) throws CoreException {
		return getApDictDao().queryUpApDictByDictCode(dictCode);
	}
	//修改业务字典时校验是否添加重复   shihuan 2017-04-11
	@Override
	public List<ApDict> queryUpApDictByDictName(String dictName) throws CoreException {
		return getApDictDao().queryUpApDictByDictName(dictName);
	}

	public ApDictDao getApDictDao() {
		return apDictDao;
	}

	public void setApDictDao(ApDictDao apDictDao) {
		this.apDictDao = apDictDao;
	}

}
