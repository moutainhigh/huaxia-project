package com.huaxia.opas.dao.dict.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.dict.ApDictDao;
import com.huaxia.opas.domain.dict.ApDict;

public class ApDictDaoImpl extends AbstractDAO implements ApDictDao {

	private static final long serialVersionUID = -1124934572563024216L;

	private static final String NAMESPACES = "ApDict.";

	@Override
	public int insertApDict(ApDict apDict) throws CoreException {
		return getSqlMap().insert(NAMESPACES + "insertApDict", apDict);
	}

	@Override
	public int insertApDictSelective(ApDict apDict) throws CoreException {
		return getSqlMap().insert(NAMESPACES + "insertApDictSelective", apDict);
	}

	@Override
	public int deleteApDictByDictId(String dictId) throws CoreException {
		return getSqlMap().delete(NAMESPACES + "deleteApDictByDictId", dictId);
	}

	@Override
	public int updateApDict(ApDict apDict) throws CoreException {
		return getSqlMap().update(NAMESPACES + "updateApDict", apDict);
	}

	@Override
	public int updateApDictSelective(ApDict apDict) throws CoreException {
		return getSqlMap().update(NAMESPACES + "updateApDictSelective", apDict);
	}

	@Override
	public ApDict queryApDictByDictId(String dictId) throws CoreException {
		return (ApDict) (getSqlMap().queryForObject(NAMESPACES + "queryApDictByDictId", dictId));
	}

	@Override
	public Map<String, Object> queryDicts(ApDict dict, int curNum, int pageNum) {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		List<ApDict> dicts = getSqlMap().queryForList(NAMESPACES + "queryApDictList", dict, curNum, pageNum);
		int total = queryDictTotal(dict);
		dataMap.put("rows", dicts);
		dataMap.put("total", total);
		return dataMap;
	}

	@Override
	public int queryDictTotal(ApDict dict) {
		return Integer.parseInt(String.valueOf(getSqlMap().queryForObject(NAMESPACES + "queryApDictCount", dict)));
	}

	// zhanghn
	@Override
	public List<Map<String, Object>> queryDicts() throws CoreException {
		return getSqlMap().queryForList(NAMESPACES + "queryDicts");
	}

	@Override
	public List<String> queryDictCodes() {
		return getSqlMap().queryForList(NAMESPACES + "queryDictCodes");
	}
	//添加业务字典时校验是否添加重复   shihuan 2017-04-10
	@Override
	public ApDict queryApDictByDictCode(String dictCode) throws CoreException {
		return getSqlMap().queryForObject(NAMESPACES + "queryApDictByDictCode", dictCode);
	}
	//添加业务字典时校验是否添加重复   shihuan 2017-04-10
	@Override
	public ApDict queryApDictByDictName(String dictName) throws CoreException {
		return getSqlMap().queryForObject(NAMESPACES + "queryApDictByDictName", dictName);
	}
	
	//修改业务字典时校验是否添加重复   shihuan 2017-04-10
	@Override
	public List<ApDict> queryUpApDictByDictCode(String dictCode) throws CoreException {
		return getSqlMap().queryForList(NAMESPACES + "queryUpApDictByDictCode", dictCode);
	}
	//修改业务字典时校验是否添加重复   shihuan 2017-04-10
	@Override
	public List<ApDict> queryUpApDictByDictName(String dictName) throws CoreException {
		return getSqlMap().queryForList(NAMESPACES + "queryUpApDictByDictName", dictName);
	}
}
