package com.huaxia.opas.service.system;

import java.util.List;
import java.util.Map;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.system.ApOnline;

public interface ApOnlineService {
	public int insertApOnline(ApOnline apOnline) throws CoreException;
	public int updateApOnline(ApOnline apOnline) throws CoreException;
	public int queryOnlineCount(Map<String, Object> map) throws CoreException;
	public List<ApOnline> queryOnlineList(Map<String, Object> map, int curNum, int pageNum)throws CoreException;
	public ApOnline queryApUserByUserId(String userId) throws CoreException;
}
