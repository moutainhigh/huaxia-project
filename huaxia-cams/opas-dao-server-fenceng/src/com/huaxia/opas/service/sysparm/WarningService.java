package com.huaxia.opas.service.sysparm;

import java.util.List;
import java.util.Map;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.sysparm.Warning;

//warninglist的service接口
public interface WarningService {

	/*public int queryWarningCount(Map<String, Object> map);
	public List<Warning> queryWarningList(Map<String, Object> map, int curNum, int pageNum);*/
	
	//添加
	public int insertWarningList(Warning warningList) throws CoreException;
	
	//修改
	public int updateWarning(Warning warningList) throws CoreException;
	
	//删除
	public int deleteWarningList(String autoId) throws CoreException;

	//分页查询
	public int queryWarningCount(Warning warning);
	public List<Warning> queryWarningList(Warning warning, int curNum, int pageNum);

	
}
