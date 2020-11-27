package com.huaxia.opas.service.credit;

import java.util.List;
import java.util.Map;
import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.sysparm.CreditWhiteList;
import com.huaxia.opas.domain.sysparm.ThdErrMsg;
import com.huaxia.opas.service.BaseService;
/**
 * 三方异常查询信息业务层接口
 * @author weijinfeng 2018/09/20
 *
 */
public interface ThdErrDataService{
	//查询数量方法
	int queryCount(ThdErrMsg thdErrMsg) throws CoreException;
	//查询列表方法
	List<Map<Object, Object>> queryList(ThdErrMsg thdErrMsg, int curNum, int pageNum) throws CoreException;
	//批量重查方法
	void updateRequeryData(List<Map<String, Object>> errMsgArr) throws CoreException;
	//批量流转方法
	void updatePassListData(List<Map<String, Object>> errMsgArr) throws CoreException;
	//修改记录插入历史表
	void addHis(List<Map<String, Object>> errMsgArr) throws CoreException;
	
	//查询海航和fico数量方法
	int querySailAndFicoCount(ThdErrMsg prom) throws CoreException;
	//查询海航和fico列表方法
	List<Map<Object, Object>> querySailAndFicoList(ThdErrMsg prom, int curNum, int pageNum) throws CoreException;
	//海航和fico修改记录插入历史表
	void addSailAndFicoHis(List<Map<String, Object>> errMsgArr) throws CoreException;
	//海航和fico批量重查方法
	void updateSailAndFicoRequeryData(List<Map<String, Object>> errMsgArr) throws CoreException;
	//海航和fico修改记录插入历史表
	void updateSailAndFicoPassListData(List<Map<String, Object>> errMsgArr) throws CoreException;
	
}
