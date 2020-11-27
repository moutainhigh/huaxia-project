package com.huaxia.opas.service.sysparm.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.sysparm.SysparmRepayFreeTermConfDao;
import com.huaxia.opas.domain.sysparm.SysparmRepayFreeTermConf;
import com.huaxia.opas.service.sysparm.SysparmRepayFreeTermConfService;
/**
 * 免息还款期配置服务层实现类
 * @author liudi
 * @since 2017-03-21
 * @version 1.0
 */
public class SysparmRepayFreeTermConfServiceImpl extends AbstractDAO implements SysparmRepayFreeTermConfService,Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6201842822792583271L;

	@Resource(name = "sysparmRepayFreeTermConfDao")
	private SysparmRepayFreeTermConfDao sysparmRepayFreeTermConfDao;

	public SysparmRepayFreeTermConfDao getSysparmRepayFreeTermConfDao() {
		return sysparmRepayFreeTermConfDao;
	}

	public void setSysparmRepayFreeTermConfDao(SysparmRepayFreeTermConfDao sysparmRepayFreeTermConfDao) {
		this.sysparmRepayFreeTermConfDao = sysparmRepayFreeTermConfDao;
	}

	//添加
	public int saveSysparmRepayFreeTermConf(SysparmRepayFreeTermConf sysparmRepayFreeTermConf) throws CoreException {
		return getSysparmRepayFreeTermConfDao().saveSysparmRepayFreeTermConf(sysparmRepayFreeTermConf);
	}
	
	//修改
	public int updateSysparmRepayFreeTermConf(SysparmRepayFreeTermConf sysparmRepayFreeTermConf) throws CoreException {
		return getSysparmRepayFreeTermConfDao().updateSysparmRepayFreeTermConf(sysparmRepayFreeTermConf);
	}
	
	//删除
	public int deleteSysparmRepayFreeTermConf(String autoId) throws CoreException {
		return getSysparmRepayFreeTermConfDao().deleteSysparmRepayFreeTermConf(autoId);
	}
	
	//全部删除
	public int deleteAllSysparmRepayFreeTermConf() throws CoreException {
		return getSysparmRepayFreeTermConfDao().deleteAllSysparmRepayFreeTermConf();
	}
	
	//免息还款期配置件数查询
	public int querySysparmRepayFreeTermConfCount(SysparmRepayFreeTermConf sysparmRepayFreeTermConf) throws CoreException {
		return getSysparmRepayFreeTermConfDao().querySysparmRepayFreeTermConfCount(sysparmRepayFreeTermConf);
	}
		
	//免息还款期配置信息查询
	public  List<SysparmRepayFreeTermConf> querySysparmRepayFreeTermConf(SysparmRepayFreeTermConf sysparmRepayFreeTermConf,int curNum,int pageNum) throws CoreException {
		return getSysparmRepayFreeTermConfDao().querySysparmRepayFreeTermConf(sysparmRepayFreeTermConf, curNum, pageNum);
	}
	
	//查询免息还款期免息还款期代码是否存在
	public SysparmRepayFreeTermConf querySysparmRepayFreeTermConf1(SysparmRepayFreeTermConf sysparmRepayFreeTermConf) throws CoreException {
		return getSysparmRepayFreeTermConfDao().querySysparmRepayFreeTermConf1(sysparmRepayFreeTermConf);
	}
	
	//查询免息还款期客户分层结果标签是否重复
	public SysparmRepayFreeTermConf querySysparmRepayFreeTermConf2(SysparmRepayFreeTermConf sysparmRepayFreeTermConf) throws CoreException {
		return getSysparmRepayFreeTermConfDao().querySysparmRepayFreeTermConf2(sysparmRepayFreeTermConf);
	}
	
	//查询免息还款期代码存在情况下，状态是否开启
	public SysparmRepayFreeTermConf querySysparmRepayFreeTermConf3(SysparmRepayFreeTermConf sysparmRepayFreeTermConf) throws CoreException {
		return getSysparmRepayFreeTermConfDao().querySysparmRepayFreeTermConf3(sysparmRepayFreeTermConf);
	}
	
	//自动归档查询免息还款期免息还款期代码
	public SysparmRepayFreeTermConf querySysparmRepayFreeTermConf5(SysparmRepayFreeTermConf sysparmRepayFreeTermConf) throws CoreException {
		return getSysparmRepayFreeTermConfDao().querySysparmRepayFreeTermConf5(sysparmRepayFreeTermConf);
	}
}
