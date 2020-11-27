package com.huaxia.opas.dao.system.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.system.ApOnlineDao;
import com.huaxia.opas.domain.system.ApOnline;

public class ApOnlineDaoImpl extends AbstractDAO implements ApOnlineDao {

	private static final long serialVersionUID = 736293288782040004L;

	private static final String NAMESPACES = "ApOnline.";

	public int insertApOnline(ApOnline apOnline) throws CoreException {
		return getSqlMap().insert(NAMESPACES + "insertApOnline", apOnline);
	}
	
	public int updateApOnline(ApOnline apOnline) throws CoreException {
		return getSqlMap().update(NAMESPACES + "updateApOnline", apOnline);
	}
	
	public int queryOnlineCount(Map<String, Object> map)throws CoreException{
		return getSqlMap().queryForObject(NAMESPACES + "queryOnlineCount", map);
	}
	
	public List<ApOnline> queryOnlineList(Map<String, Object> map, int curNum, int pageNum)throws CoreException{
		List<ApOnline> list = new ArrayList<ApOnline>();
		list = getSqlMap().queryForList(NAMESPACES + "queryOnlineList", map, curNum, pageNum);
		return list;
	}
	
	public ApOnline queryApUserByUserId(String userId) throws CoreException{
		return (ApOnline) (getSqlMap().queryForObject(NAMESPACES + "queryOnlineByUserId", userId));
	}
}
