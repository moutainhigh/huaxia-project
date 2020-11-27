package com.huaxia.opas.service.sysparm.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.sysparm.SysparmRateConfDao;
import com.huaxia.opas.domain.sysparm.SysparmRateConf;
import com.huaxia.opas.service.sysparm.SysparmRateConfService;
/**
 * 利率差异化参数配置服务层实现类
 * @author liudi
 * @since 2017-03-17
 * @version 1.0
 */
public class SysparmRateConfServiceImpl extends AbstractDAO implements SysparmRateConfService,Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1192988417184218429L;

	@Resource(name = "sysparmRateConfDao")
	private SysparmRateConfDao sysparmRateConfDao;


	public SysparmRateConfDao getSysparmRateConfDao() {
		return sysparmRateConfDao;
	}

	public void setSysparmRateConfDao(SysparmRateConfDao sysparmRateConfDao) {
		this.sysparmRateConfDao = sysparmRateConfDao;
	}

	//添加
	public int saveSysparmRateConf(SysparmRateConf sysparmRateConf) throws CoreException {
		return getSysparmRateConfDao().saveSysparmRateConf(sysparmRateConf);
	}
	
	//修改
	public int updateSysparmRateConf(SysparmRateConf sysparmRateConf) throws CoreException {
		return getSysparmRateConfDao().updateSysparmRateConf(sysparmRateConf);
	}
	
	//删除
	public int deleteSysparmRateConf(String autoId) throws CoreException {
		return getSysparmRateConfDao().deleteSysparmRateConf(autoId);
	}
	
	//全部删除
	public int deleteAllSysparmRateConf() throws CoreException {
		return getSysparmRateConfDao().deleteAllSysparmRateConf();
	}
	
	//利率差异化配置件数查询
	public int querySysparmRateConfCount(SysparmRateConf sysparmRateConf) throws CoreException {
		return getSysparmRateConfDao().querySysparmRateConfCount(sysparmRateConf);
	}
		
	//利率差异化配置信息查询
	public  List<SysparmRateConf> querySysparmRateConf(SysparmRateConf sysparmRateConf,int curNum,int pageNum) throws CoreException {
		return getSysparmRateConfDao().querySysparmRateConf(sysparmRateConf, curNum, pageNum);
	}
	
	//查询利率代码是否存在
	public SysparmRateConf querySysparmRateConf1(SysparmRateConf sysparmRateConf) throws CoreException {
		return getSysparmRateConfDao().querySysparmRateConf1(sysparmRateConf);
	}
	
	//查询利率客户分层结果标签是否重复
	public SysparmRateConf querySysparmRateConf2(SysparmRateConf sysparmRateConf) throws CoreException {
		return getSysparmRateConfDao().querySysparmRateConf2(sysparmRateConf);
	}
	
	//查询利率代码存在情况下，状态是否开启
	public SysparmRateConf querySysparmRateConf3(SysparmRateConf sysparmRateConf) throws CoreException {
		return getSysparmRateConfDao().querySysparmRateConf3(sysparmRateConf);
	}
	
	//自动归档查询利率代码
	public SysparmRateConf querySysparmRateConf5(SysparmRateConf sysparmRateConf) throws CoreException {
		return getSysparmRateConfDao().querySysparmRateConf5(sysparmRateConf);
	}
}
