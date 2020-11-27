package com.huaxia.opas.service.sysparm;

import java.util.List;
import java.util.Map;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.sysparm.FraudCode;
/**
 * 欺诈结果原因码服务层接口
 * @author liudi
 * @since 2017-02-26
 * @version 1.0
 */
public interface FraudCodeService {
	//添加状态为Start
	public int saveFraudCodeStart(FraudCode fraudCode) throws CoreException;
	//添加状态为End
	public int saveFraudCodeEnd(FraudCode fraudCode) throws CoreException;
	//修改
	public int updateFraudCode(FraudCode fraudCode) throws CoreException;
	//删除
	public int deleteFraudCode(String autoID) throws CoreException;
	//查询
	public List<FraudCode> queryFraudCode(FraudCode fraudCode,int curNum,int pageNum) throws CoreException;
	//件数查询
	public int queryFraudCodeCount(FraudCode fraudCode) throws CoreException;
	//查询是否重复
	public FraudCode queryFraudCode(FraudCode fraudCode) throws CoreException;
	//点击查询后，触发查询和排序功能
	public List<FraudCode> queryFraudCode(Map<String,Object> params,int curNum,int pageNum) throws CoreException;
	//查询更新前的状态
	public String selectFraudCode(String autoId);
	//修改对象（不包括状态）
	public int updateFraudCodeWithoutStatus(FraudCode fraudCode);

}
