package com.huaxia.opas.service.sysparm;

import java.util.List;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.sysparm.SysparmRepayLimitConf;
/**
 * 最低还款额配置服务层接口
 * @author liudi
 * @since 2017-03-20
 * @version 1.0
 */
public interface SysparmRepayLimitConfService {
	//添加
	public int saveSysparmRepayLimitConf(SysparmRepayLimitConf sysparmRepayLimitConf) throws CoreException;
	//修改
	public int updateSysparmRepayLimitConf(SysparmRepayLimitConf sysparmRepayLimitConf) throws CoreException;
	//删除
	public int deleteSysparmRepayLimitConf(String autoId) throws CoreException;
	//全部删除
	public int deleteAllSysparmRepayLimitConf() throws CoreException;
	//最低还款额配置件数查询
	public int querySysparmRepayLimitConfCount(SysparmRepayLimitConf sysparmRepayLimitConf) throws CoreException;
	//最低还款额配置信息查询
	public List<SysparmRepayLimitConf> querySysparmRepayLimitConf(SysparmRepayLimitConf sysparmRepayLimitConf,int curNum,int pageNum) throws CoreException;
	//查询最低还款额比例代码是否存在
	public SysparmRepayLimitConf querySysparmRepayLimitConf1(SysparmRepayLimitConf sysparmRepayLimitConf) throws CoreException;
	//查询最低还款额客户分层结果标签是否重复
	public SysparmRepayLimitConf querySysparmRepayLimitConf2(SysparmRepayLimitConf sysparmRepayLimitConf) throws CoreException;
	//查询最低还款额比例代码存在情况下，状态是否开启
	public SysparmRepayLimitConf querySysparmRepayLimitConf3(SysparmRepayLimitConf sysparmRepayLimitConf) throws CoreException;
	//自动归档查询最低还款额比例代码
	public SysparmRepayLimitConf querySysparmRepayLimitConf5(SysparmRepayLimitConf sysparmRepayLimitConf) throws CoreException;

}
