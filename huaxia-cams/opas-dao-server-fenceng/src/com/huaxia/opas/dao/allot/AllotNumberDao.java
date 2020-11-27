package com.huaxia.opas.dao.allot;

import java.util.List;
import java.util.Map;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.allot.AllotNumber;

public interface AllotNumberDao {

	int insertAllotNumber(AllotNumber allotNumber) throws CoreException;

	int updateAllotNumber(AllotNumber allotNumber) throws CoreException;

	AllotNumber selectAllotNumber(Map map) throws CoreException;
	
	List<AllotNumber> selectAllotNumberList(Map map) throws CoreException;
	
	String countAllotNumber(Map map) throws CoreException;
	/**
	 * 根据条件查询征信规则分配数量方法-wenyh
	 * @param map
	 * @return
	 * @throws CoreException
	 */
	String queryCreditRuleAllotNumber(Map map) throws CoreException;
}
