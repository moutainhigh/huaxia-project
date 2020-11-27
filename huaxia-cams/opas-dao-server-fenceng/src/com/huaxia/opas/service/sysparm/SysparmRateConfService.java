package com.huaxia.opas.service.sysparm;

import java.util.List;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.sysparm.SysparmRateConf;
/**
 * 利率差异化参数配置服务层接口
 * @author liudi
 * @since 2017-03-17
 * @version 1.0
 */
public interface SysparmRateConfService {
	//添加
	public int saveSysparmRateConf(SysparmRateConf sysparmRateConf) throws CoreException;
	//修改
	public int updateSysparmRateConf(SysparmRateConf sysparmRateConf) throws CoreException;
	//删除
	public int deleteSysparmRateConf(String autoId) throws CoreException;
	//全部删除
	public int deleteAllSysparmRateConf() throws CoreException;
	//利率差异化配置件数查询
	public int querySysparmRateConfCount(SysparmRateConf sysparmRateConf) throws CoreException;
	//利率差异化配置信息查询
	public List<SysparmRateConf> querySysparmRateConf(SysparmRateConf sysparmRateConf,int curNum,int pageNum) throws CoreException;
	//查询利率代码是否存在
	public SysparmRateConf querySysparmRateConf1(SysparmRateConf sysparmRateConf) throws CoreException;
	//查询利率客户分层结果标签是否重复
	public SysparmRateConf querySysparmRateConf2(SysparmRateConf sysparmRateConf) throws CoreException;
	//查询利率代码存在情况下，状态是否开启
	public SysparmRateConf querySysparmRateConf3(SysparmRateConf sysparmRateConf) throws CoreException;
	//自动归档查询利率代码
	public SysparmRateConf querySysparmRateConf5(SysparmRateConf sysparmRateConf) throws CoreException;

}
