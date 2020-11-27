package com.huaxia.opas.service.sysparm;

import java.util.List;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.sysparm.SysparmRate;
/**
 * 利率差异化维护服务层接口
 * @author liudi
 * @since 2017-03-17
 * @version 1.0
 */
public interface SysparmRateService {
	//添加
	public int saveSysparmRate(SysparmRate sysparmRate) throws CoreException;
	//修改
	public int updateSysparmRate(SysparmRate sysparmRate) throws CoreException;
	//删除
	public int deleteSysparmRate(String autoId) throws CoreException;
	//全部删除
	public int deleteAllSysparmRate() throws CoreException;
	//利率差异化件数查询
	public int querySysparmRateCount(SysparmRate sysparmRate) throws CoreException;
	//利率差异化信息查询
	public List<SysparmRate> querySysparmRate(SysparmRate sysparmRate,int curNum,int pageNum) throws CoreException;
	//查询利率差异化代码是否重复
	public SysparmRate querySysparmRate(SysparmRate sysparmRate) throws CoreException;

}
