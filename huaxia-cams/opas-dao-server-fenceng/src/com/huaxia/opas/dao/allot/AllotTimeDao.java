package com.huaxia.opas.dao.allot;

import java.util.List;
import java.util.Map;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.allot.AllotSwitch;
import com.huaxia.opas.domain.allot.AllotTime;

public interface AllotTimeDao {
	
	int insertAllotTime(AllotTime allotTime) throws CoreException;
	

	int updateAllotTime(AllotTime allotTime) throws CoreException;
	
	int deleteAllotTime(String id) throws CoreException;
	
	int countAllotTime(Map map) throws CoreException;

	List<AllotTime> selectAllotTime(Map map,int currentPage, int pageSize) throws CoreException;
	
	AllotTime selectAllotTime(Map map) throws CoreException;
}
