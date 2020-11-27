package com.huaxia.opas.dao.credit;

import java.util.List;
import java.util.Map;
import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.sysparm.CreditWhiteList;
import com.huaxia.opas.domain.sysparm.ThdErrMsg;
/**
 * 三方异常数据查询dao层接口
 * @author weijinfeng 2018/09/20
 *
 */
public interface ThdErrDataDao {
	//查询数量方法
	int queryCount(ThdErrMsg thdErrMsg) throws CoreException;
	//查询列表方法
	List<Map<Object, Object>> queryList(ThdErrMsg thdErrMsg, int curNum, int pageNum) throws CoreException;
	//重查
	void updateRequery(Map<String, Object> map) throws CoreException;
	//流转
	void updatePassData(Map<String, Object> map) throws CoreException;
	//历史表加修改记录
	void addHis(Map<String, Object> map) throws CoreException;
	
	//海航和fico查询数量方法
	int querySailAndFicoCount(ThdErrMsg thdErrMsg) throws CoreException;
	//海航和fico查询列表方法
	List<Map<Object, Object>> querySailAndFicoList(ThdErrMsg thdErrMsg, int curNum, int pageNum) throws CoreException;
	//海航和fico历史表加修改记录
	void addSailAndFicoHis(Map<String, Object> map) throws CoreException;
	//海航和fico重查
	void updateSailAndFicoRequery(Map<String, Object> map) throws CoreException;
	//海航和fico流转
	void updateSailAndFicoPassData(Map<String, Object> map) throws CoreException;
}
