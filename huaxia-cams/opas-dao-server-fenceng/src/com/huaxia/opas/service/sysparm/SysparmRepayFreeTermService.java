package com.huaxia.opas.service.sysparm;

import java.util.List;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.sysparm.SysparmRepayFreeTerm;
/**
 * 免息还款期维护服务层接口
 * @author liudi
 * @since 2017-03-21
 * @version 1.0
 */
public interface SysparmRepayFreeTermService {
	//添加
	public int saveSysparmRepayFreeTerm(SysparmRepayFreeTerm sysparmRepayFreeTerm) throws CoreException;
	//修改
	public int updateSysparmRepayFreeTerm(SysparmRepayFreeTerm sysparmRepayFreeTerm) throws CoreException;
	//删除
	public int deleteSysparmRepayFreeTerm(String autoId) throws CoreException;
	//全部删除
	public int deleteAllSysparmRepayFreeTerm() throws CoreException;
	//免息还款期件数查询
	public int querySysparmRepayFreeTermCount(SysparmRepayFreeTerm sysparmRepayFreeTerm) throws CoreException;
	//免息还款期信息查询
	public List<SysparmRepayFreeTerm> querySysparmRepayFreeTerm(SysparmRepayFreeTerm sysparmRepayFreeTerm,int curNum,int pageNum) throws CoreException;
	//免息还款期代码是否重复
	public SysparmRepayFreeTerm querySysparmRepayFreeTerm(SysparmRepayFreeTerm sysparmRepayFreeTerm) throws CoreException;

}
