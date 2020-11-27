package com.huaxia.opas.service.sysparm;

import java.util.List;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.sysparm.SysparmRepayFreeTermConf;
/**
 * 免息还款期配置服务层接口
 * @author liudi
 * @since 2017-03-21
 * @version 1.0
 */
public interface SysparmRepayFreeTermConfService {
	//添加
	public int saveSysparmRepayFreeTermConf(SysparmRepayFreeTermConf sysparmRepayFreeTermConf) throws CoreException;
	//修改
	public int updateSysparmRepayFreeTermConf(SysparmRepayFreeTermConf sysparmRepayFreeTermConf) throws CoreException;
	//删除
	public int deleteSysparmRepayFreeTermConf(String autoId) throws CoreException;
	//全部删除
	public int deleteAllSysparmRepayFreeTermConf() throws CoreException;
	//免息还款期配置件数查询
	public int querySysparmRepayFreeTermConfCount(SysparmRepayFreeTermConf sysparmRepayFreeTermConf) throws CoreException;
	//免息还款期配置信息查询
	public List<SysparmRepayFreeTermConf> querySysparmRepayFreeTermConf(SysparmRepayFreeTermConf sysparmRepayFreeTermConf,int curNum,int pageNum) throws CoreException;
	//查询免息还款期免息还款期代码是否存在
	public SysparmRepayFreeTermConf querySysparmRepayFreeTermConf1(SysparmRepayFreeTermConf sysparmRepayFreeTermConf) throws CoreException;
	//查询免息还款期客户分层结果标签是否重复
	public SysparmRepayFreeTermConf querySysparmRepayFreeTermConf2(SysparmRepayFreeTermConf sysparmRepayFreeTermConf) throws CoreException;
	//查询免息还款期代码存在情况下，状态是否开启
	public SysparmRepayFreeTermConf querySysparmRepayFreeTermConf3(SysparmRepayFreeTermConf sysparmRepayFreeTermConf) throws CoreException;
	//自动归档免息还款期配置信息查询
	public SysparmRepayFreeTermConf querySysparmRepayFreeTermConf5(SysparmRepayFreeTermConf sysparmRepayFreeTermConf) throws CoreException;
		
}
