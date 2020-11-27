package com.huaxia.opas.dao.sysparm;

import java.util.List;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.sysparm.SysparmRepayLimit;
/**
 * 最低还款额维护DAO层接口
 * @author liudi
 * @since 2017-03-20
 * @version 1.0
 */
public interface SysparmRepayLimitDao {
	//添加
	public int saveSysparmRepayLimit(SysparmRepayLimit sysparmRepayLimit) throws CoreException;
	//修改
	public int updateSysparmRepayLimit(SysparmRepayLimit sysparmRepayLimit) throws CoreException;
	//删除
	public int deleteSysparmRepayLimit(String autoId) throws CoreException;
	//全部删除
	public int deleteAllSysparmRepayLimit() throws CoreException;
	//最低还款额件数查询
	public int querySysparmRepayLimitCount(SysparmRepayLimit sysparmRepayLimit) throws CoreException;
	//最低还款额信息查询
	public List<SysparmRepayLimit> querySysparmRepayLimit(SysparmRepayLimit sysparmRepayLimit,int curNum,int pageNum) throws CoreException;
	//最低还款额代码是否重复
	public SysparmRepayLimit querySysparmRepayLimit(SysparmRepayLimit sysparmRepayLimit) throws CoreException;

}
