package com.huaxia.opas.service.dict;

import java.util.List;
import java.util.Map;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.dict.ApDict;
import com.huaxia.opas.service.BaseService;

/**
 * 字典大类
 * 
 * @author shihuan
 *
 */
public interface ApDictService extends BaseService<ApDict> {

	public int insertApDictSelective(ApDict apDict) throws CoreException;

	public int updateApDictSelective(ApDict apDict) throws CoreException;

	public ApDict queryApDictByDictId(String dictId) throws CoreException;

	public Map<String, Object> queryDicts(ApDict dict, int curNum, int pageNum);

	public int queryDictTotal(ApDict dict);

	public List<Map<String, Object>> queryDicts() throws CoreException;

	public List<String> queryDictCodes();
	//添加业务字典时校验是否添加重复   shihuan 2017-04-10
	public ApDict queryApDictByDictCode(String dictCode) throws CoreException;
	//添加业务字典时校验是否添加重复   shihuan 2017-04-10
	public ApDict queryApDictByDictName(String dictName) throws CoreException;
	
	//修改业务字典时校验是否添加重复   shihuan 2017-04-10
	public List<ApDict> queryUpApDictByDictCode(String dictCode) throws CoreException;
	//修改业务字典时校验是否添加重复   shihuan 2017-04-10
	public List<ApDict> queryUpApDictByDictName(String dictName) throws CoreException;

	public int remove(ApDict dict) throws CoreException;
}
