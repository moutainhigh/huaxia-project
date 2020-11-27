package com.huaxia.opas.dao.sysparm;

import java.util.List;
import java.util.Map;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.sysparm.Warning;

public interface WarningDao {

	//查询
	public int queryWarningList(Warning warningList) throws CoreException;
	//添加
	public int insertWarningList(Warning warningList) throws CoreException;
	//修改
	public int updateWarning(Warning warning) throws CoreException;
	//删除
	public int deleteWarningList(String autoId) throws CoreException;
	//分页查询
	public int queryWarningCount(Warning warning);
	public List<Warning> queryWarningList(Warning warning, int curNum, int pageNum);
	
	
}
