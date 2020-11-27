package com.huaxia.opas.dao.sysparm.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.sysparm.WarningDao;
import com.huaxia.opas.domain.sysparm.Warning;

public class WarningDaoImpl extends AbstractDAO implements WarningDao {

	private static final long serialVersionUID = 1545595118184670199L;

	// 增加namespaces项,对应mapping文件中的namespaces
	private static final String NAMESPACES = "WarningList.";

	// 查询
	@Override
	public int queryWarningList(Warning warningList) throws CoreException{
		return getSqlMap().insert(NAMESPACES + "queryAll", warningList);
	}

	// 添加
	@Override
	public int insertWarningList(Warning warningList)  throws CoreException{
		return getSqlMap().insert(NAMESPACES + "insertWarningList", warningList);
	}

	// 修改
	@Override
	public int updateWarning(Warning warning) throws CoreException{
		return getSqlMap().update(NAMESPACES + "updateWarningList", warning);
	}

	// 删除
	@Override
	public int deleteWarningList(String autoId) throws CoreException{
		return getSqlMap().delete(NAMESPACES + "deleteWarningListByAutoId", autoId);
	}

	//分页查询
	@Override
	public int queryWarningCount(Warning warning) {
		return getSqlMap().queryForObject(NAMESPACES + "queryWarningCount", warning);
	}

	@Override
	public List<Warning> queryWarningList(Warning warning, int curNum, int pageNum) {
		List<Warning> list = new ArrayList<Warning>();
		list = getSqlMap().queryForList(NAMESPACES + "queryWarningList", warning, curNum, pageNum);
		return list;
	}

	

}

