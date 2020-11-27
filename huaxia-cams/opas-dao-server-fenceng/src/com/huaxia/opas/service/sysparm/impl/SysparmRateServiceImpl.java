package com.huaxia.opas.service.sysparm.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.sysparm.SysparmRateDao;
import com.huaxia.opas.domain.sysparm.SysparmRate;
import com.huaxia.opas.service.sysparm.SysparmRateService;
/**
 * 利率差异化维护服务层实现类
 * @author liudi
 * @since 2017-03-17
 * @version 1.0
 */
public class SysparmRateServiceImpl extends AbstractDAO implements SysparmRateService,Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1192988417184218429L;

	@Resource(name = "sysparmRateDao")
	private SysparmRateDao sysparmRateDao;




	public SysparmRateDao getSysparmRateDao() {
		return sysparmRateDao;
	}

	public void setSysparmRateDao(SysparmRateDao sysparmRateDao) {
		this.sysparmRateDao = sysparmRateDao;
	}

	//添加
	public int saveSysparmRate(SysparmRate sysparmRate) throws CoreException {
		return getSysparmRateDao().saveSysparmRate(sysparmRate);
	}
	
	//修改
	public int updateSysparmRate(SysparmRate sysparmRate) throws CoreException {
		return getSysparmRateDao().updateSysparmRate(sysparmRate);
	}
	
	//删除
	public int deleteSysparmRate(String autoId) throws CoreException {
		return getSysparmRateDao().deleteSysparmRate(autoId);
	}
	
	//全部删除
	public int deleteAllSysparmRate() throws CoreException {
		return getSysparmRateDao().deleteAllSysparmRate();
	}
	
	//利率差异化件数查询
	public int querySysparmRateCount(SysparmRate sysparmRate) throws CoreException {
		return getSysparmRateDao().querySysparmRateCount(sysparmRate);
	}
		
	//利率差异化信息查询
	public  List<SysparmRate> querySysparmRate(SysparmRate sysparmRate,int curNum,int pageNum) throws CoreException {
		return getSysparmRateDao().querySysparmRate(sysparmRate, curNum, pageNum);
	}
	
	//查询利率差异化代码是否重复
	public SysparmRate querySysparmRate(SysparmRate sysparmRate) throws CoreException {
		return getSysparmRateDao().querySysparmRate(sysparmRate);
	}

}
