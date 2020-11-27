package com.huaxia.opas.dao.dict;

import java.util.List;
import java.util.Map;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.dict.ApDict;

public interface ApDictDao {

	public int insertApDict(ApDict apDict) throws CoreException;

	public int insertApDictSelective(ApDict apDict) throws CoreException;

	public int deleteApDictByDictId(String dictId) throws CoreException;

	public int updateApDict(ApDict apDict) throws CoreException;

	public int updateApDictSelective(ApDict apDict) throws CoreException;

	public ApDict queryApDictByDictId(String dictId) throws CoreException;
	
	public ApDict queryApDictByDictName(String dictName) throws CoreException;

	public Map<String, Object> queryDicts(ApDict dict, int curNum, int pageNum);

	public int queryDictTotal(ApDict dict);

	public List<Map<String, Object>> queryDicts() throws CoreException;

	public List<String> queryDictCodes();

	public ApDict queryApDictByDictCode(String dictCode) throws CoreException;
	
	public List<ApDict> queryUpApDictByDictCode(String dictCode) throws CoreException;
	//修改时校验
	public List<ApDict> queryUpApDictByDictName(String dictName) throws CoreException;

}
