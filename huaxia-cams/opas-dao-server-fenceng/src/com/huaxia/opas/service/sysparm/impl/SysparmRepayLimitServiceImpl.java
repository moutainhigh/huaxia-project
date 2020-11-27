package com.huaxia.opas.service.sysparm.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.sysparm.SysparmRepayLimitDao;
import com.huaxia.opas.domain.sysparm.SysparmRepayLimit;
import com.huaxia.opas.service.sysparm.SysparmRepayLimitService;
/**
 * 最低还款额维护服务层实现类
 * @author liudi
 * @since 2017-03-20
 * @version 1.0
 */
public class SysparmRepayLimitServiceImpl extends AbstractDAO implements SysparmRepayLimitService,Serializable  {


	/**
	 * 
	 */
	private static final long serialVersionUID = -5272162116330331902L;

	@Resource(name = "sysparmRepayLimitDao")
	private SysparmRepayLimitDao sysparmRepayLimitDao;

	public SysparmRepayLimitDao getSysparmRepayLimitDao() {
		return sysparmRepayLimitDao;
	}

	public void setSysparmRepayLimitDao(SysparmRepayLimitDao sysparmRepayLimitDao) {
		this.sysparmRepayLimitDao = sysparmRepayLimitDao;
	}

	//添加
	public int saveSysparmRepayLimit(SysparmRepayLimit sysparmRepayLimit) throws CoreException {
		return getSysparmRepayLimitDao().saveSysparmRepayLimit(sysparmRepayLimit);
	}
	
	//修改
	public int updateSysparmRepayLimit(SysparmRepayLimit sysparmRepayLimit) throws CoreException {
		return getSysparmRepayLimitDao().updateSysparmRepayLimit(sysparmRepayLimit);
	}
	
	//删除
	public int deleteSysparmRepayLimit(String autoId) throws CoreException {
		return getSysparmRepayLimitDao().deleteSysparmRepayLimit(autoId);
	}
	
	//全部删除
	public int deleteAllSysparmRepayLimit() throws CoreException {
		return getSysparmRepayLimitDao().deleteAllSysparmRepayLimit();
	}
	
	//最低还款额件数查询
	public int querySysparmRepayLimitCount(SysparmRepayLimit sysparmRepayLimit) throws CoreException {
		return getSysparmRepayLimitDao().querySysparmRepayLimitCount(sysparmRepayLimit);
	}
		
	//最低还款额信息查询
	public  List<SysparmRepayLimit> querySysparmRepayLimit(SysparmRepayLimit sysparmRepayLimit,int curNum,int pageNum) throws CoreException {
		return getSysparmRepayLimitDao().querySysparmRepayLimit(sysparmRepayLimit, curNum, pageNum);
	}
	
	//最低还款额代码是否重复
	public SysparmRepayLimit querySysparmRepayLimit(SysparmRepayLimit sysparmRepayLimit) throws CoreException {
		return getSysparmRepayLimitDao().querySysparmRepayLimit(sysparmRepayLimit);
	}

}
