package com.huaxia.opas.service.system.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.system.ApOnlineDao;
import com.huaxia.opas.domain.system.ApOnline;
import com.huaxia.opas.service.system.ApOnlineService;

public class ApOnlineServiceImpl implements ApOnlineService , Serializable{
	
	private static final long serialVersionUID = 7854831800931392762L;
	/**
	 * 
	 */
	@Resource(name = "apOnlineDao")
	private ApOnlineDao apOnlineDao;
	
	public int insertApOnline(ApOnline apOnline) throws CoreException {
		return apOnlineDao.insertApOnline(apOnline);
	}
	
	public int updateApOnline(ApOnline apOnline) throws CoreException{
		return apOnlineDao.updateApOnline(apOnline);
	}
	
	public int queryOnlineCount(Map<String, Object> map)throws CoreException{
		
		return apOnlineDao.queryOnlineCount(map);
	}
	
	public List<ApOnline> queryOnlineList(Map<String, Object> map, int curNum, int pageNum)throws CoreException{
		
		return apOnlineDao.queryOnlineList(map, curNum, pageNum);
	}
	
	public ApOnline queryApUserByUserId(String userId) throws CoreException {
		return apOnlineDao.queryApUserByUserId(userId);
	}
	
	public ApOnlineDao getApOnlineDao() {
		return apOnlineDao;
	}
	public void setApOnlineDao(ApOnlineDao apOnlineDao) {
		this.apOnlineDao = apOnlineDao;
	}

}
