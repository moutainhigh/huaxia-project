package com.huaxia.opas.service.sysparm.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.sysparm.SysparmRepayFreeTermDao;
import com.huaxia.opas.domain.sysparm.SysparmRepayFreeTerm;
import com.huaxia.opas.service.sysparm.SysparmRepayFreeTermService;
/**
 * 免息还款期维护服务层实现类
 * @author liudi
 * @since 2017-03-21
 * @version 1.0
 */
public class SysparmRepayFreeTermServiceImpl extends AbstractDAO implements SysparmRepayFreeTermService,Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 3192960005753960916L;

	@Resource(name = "sysparmRepayFreeTermDao")
	private SysparmRepayFreeTermDao sysparmRepayFreeTermDao;

	public SysparmRepayFreeTermDao getSysparmRepayFreeTermDao() {
		return sysparmRepayFreeTermDao;
	}

	public void setSysparmRepayFreeTermDao(SysparmRepayFreeTermDao sysparmRepayFreeTermDao) {
		this.sysparmRepayFreeTermDao = sysparmRepayFreeTermDao;
	}

	//添加
	public int saveSysparmRepayFreeTerm(SysparmRepayFreeTerm sysparmRepayFreeTerm) throws CoreException {
		return getSysparmRepayFreeTermDao().saveSysparmRepayFreeTerm(sysparmRepayFreeTerm);
	}
	
	//修改
	public int updateSysparmRepayFreeTerm(SysparmRepayFreeTerm sysparmRepayFreeTerm) throws CoreException {
		return getSysparmRepayFreeTermDao().updateSysparmRepayFreeTerm(sysparmRepayFreeTerm);
	}
	
	//删除
	public int deleteSysparmRepayFreeTerm(String autoId) throws CoreException {
		return getSysparmRepayFreeTermDao().deleteSysparmRepayFreeTerm(autoId);
	}
	
	//全部删除
	public int deleteAllSysparmRepayFreeTerm() throws CoreException {
		return getSysparmRepayFreeTermDao().deleteAllSysparmRepayFreeTerm();
	}
	
	//免息还款期件数查询
	public int querySysparmRepayFreeTermCount(SysparmRepayFreeTerm sysparmRepayFreeTerm) throws CoreException {
		return getSysparmRepayFreeTermDao().querySysparmRepayFreeTermCount(sysparmRepayFreeTerm);
	}
		
	//免息还款期信息查询
	public  List<SysparmRepayFreeTerm> querySysparmRepayFreeTerm(SysparmRepayFreeTerm sysparmRepayFreeTerm,int curNum,int pageNum) throws CoreException {
		return getSysparmRepayFreeTermDao().querySysparmRepayFreeTerm(sysparmRepayFreeTerm, curNum, pageNum);
	}
	
	//免息还款期代码是否重复
	public SysparmRepayFreeTerm querySysparmRepayFreeTerm(SysparmRepayFreeTerm sysparmRepayFreeTerm) throws CoreException {
		return getSysparmRepayFreeTermDao().querySysparmRepayFreeTerm(sysparmRepayFreeTerm);
	}

}
