package com.huaxia.opas.service.sysparm.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.sysparm.SysparmRepayLimitConfDao;
import com.huaxia.opas.domain.sysparm.SysparmRepayLimitConf;
import com.huaxia.opas.service.sysparm.SysparmRepayLimitConfService;
/**
 * 最低还款额配置服务层实现类
 * @author liudi
 * @since 2017-03-20
 * @version 1.0
 */
public class SysparmRepayLimitConfServiceImpl extends AbstractDAO implements SysparmRepayLimitConfService,Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -2892065771902139131L;

	@Resource(name = "sysparmRepayLimitConfDao")
	private SysparmRepayLimitConfDao sysparmRepayLimitConfDao;

	public SysparmRepayLimitConfDao getSysparmRepayLimitConfDao() {
		return sysparmRepayLimitConfDao;
	}

	public void setSysparmRepayLimitConfDao(SysparmRepayLimitConfDao sysparmRepayLimitConfDao) {
		this.sysparmRepayLimitConfDao = sysparmRepayLimitConfDao;
	}

	//添加
	public int saveSysparmRepayLimitConf(SysparmRepayLimitConf sysparmRepayLimitConf) throws CoreException {
		return getSysparmRepayLimitConfDao().saveSysparmRepayLimitConf(sysparmRepayLimitConf);
	}
	
	//修改
	public int updateSysparmRepayLimitConf(SysparmRepayLimitConf sysparmRepayLimitConf) throws CoreException {
		return getSysparmRepayLimitConfDao().updateSysparmRepayLimitConf(sysparmRepayLimitConf);
	}
	
	//删除
	public int deleteSysparmRepayLimitConf(String autoId) throws CoreException {
		return getSysparmRepayLimitConfDao().deleteSysparmRepayLimitConf(autoId);
	}
	
	//全部删除
	public int deleteAllSysparmRepayLimitConf() throws CoreException {
		return getSysparmRepayLimitConfDao().deleteAllSysparmRepayLimitConf();
	}
	
	//最低还款额配置件数查询
	public int querySysparmRepayLimitConfCount(SysparmRepayLimitConf sysparmRepayLimitConf) throws CoreException {
		return getSysparmRepayLimitConfDao().querySysparmRepayLimitConfCount(sysparmRepayLimitConf);
	}
		
	//最低还款额配置信息查询
	public  List<SysparmRepayLimitConf> querySysparmRepayLimitConf(SysparmRepayLimitConf sysparmRepayLimitConf,int curNum,int pageNum) throws CoreException {
		return getSysparmRepayLimitConfDao().querySysparmRepayLimitConf(sysparmRepayLimitConf, curNum, pageNum);
	}
	
	//查询最低还款额比例代码是否存在
	public SysparmRepayLimitConf querySysparmRepayLimitConf1(SysparmRepayLimitConf sysparmRepayLimitConf) throws CoreException {
		return getSysparmRepayLimitConfDao().querySysparmRepayLimitConf1(sysparmRepayLimitConf);
	}
	
	//查询最低还款额客户分层结果标签是否重复
	public SysparmRepayLimitConf querySysparmRepayLimitConf2(SysparmRepayLimitConf sysparmRepayLimitConf) throws CoreException {
		return getSysparmRepayLimitConfDao().querySysparmRepayLimitConf2(sysparmRepayLimitConf);
	}
	
	//查询最低还款额比例代码存在情况下，状态是否开启
	public SysparmRepayLimitConf querySysparmRepayLimitConf3(SysparmRepayLimitConf sysparmRepayLimitConf) throws CoreException {
		return getSysparmRepayLimitConfDao().querySysparmRepayLimitConf3(sysparmRepayLimitConf);
	}
	//自动归档查询最低还款额比例代码
	public SysparmRepayLimitConf querySysparmRepayLimitConf5(SysparmRepayLimitConf sysparmRepayLimitConf) throws CoreException {
		return getSysparmRepayLimitConfDao().querySysparmRepayLimitConf5(sysparmRepayLimitConf);
	}
}
