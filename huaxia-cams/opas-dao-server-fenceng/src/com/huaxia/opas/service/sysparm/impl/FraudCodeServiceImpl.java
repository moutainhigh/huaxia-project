package com.huaxia.opas.service.sysparm.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.domain.sysparm.FraudCode;
import com.huaxia.opas.dao.sysparm.FraudCodeDao;
import com.huaxia.opas.service.sysparm.FraudCodeService;
/**
 * 欺诈结果原因码服务层实体类
 * @author liudi
 * @since 2017-02-26
 * @version 1.0
 */
public class FraudCodeServiceImpl extends AbstractDAO implements FraudCodeService,Serializable  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7624504146164049349L;

	@Resource(name = "fraudCodeDao")
	private FraudCodeDao fraudCodeDao;

	public FraudCodeDao getFraudCodeDao() {
		return fraudCodeDao;
	}

	public void setFraudCodeDao(FraudCodeDao fraudCodeDao) {
		this.fraudCodeDao = fraudCodeDao;
	}

	//添加状态为Start
	public int saveFraudCodeStart(FraudCode fraudCode) throws CoreException {
		return getFraudCodeDao().saveFraudCodeStart(fraudCode);
	}
	
	//添加状态为End
	public int saveFraudCodeEnd(FraudCode fraudCode) throws CoreException {
		return getFraudCodeDao().saveFraudCodeEnd(fraudCode);
	}
	
	//修改
	public int updateFraudCode(FraudCode fraudCode) throws CoreException {
		return getFraudCodeDao().updateFraudCode(fraudCode);
	}
	
	//删除
	public int deleteFraudCode(String autoID) throws CoreException {
		return getFraudCodeDao().deleteFraudCode(autoID);
	}
	
	//查询
	public List<FraudCode> queryFraudCode(FraudCode fraudCode,int curNum,int pageNum) throws CoreException {
		return getFraudCodeDao().queryFraudCode(fraudCode, curNum, pageNum);
	}
	
	//件数查询
	public int queryFraudCodeCount(FraudCode fraudCode) throws CoreException {
		return getFraudCodeDao().queryFraudCodeCount(fraudCode);
	}
	
	//查询是否重复
	public FraudCode queryFraudCode(FraudCode fraudCode) throws CoreException {
		return getFraudCodeDao().queryFraudCode(fraudCode);
	}
	
	//点击查询后，触发查询和排序功能
	public List<FraudCode> queryFraudCode(Map<String,Object> params,int curNum,int pageNum) throws CoreException {
		return getFraudCodeDao().queryFraudCode(params, curNum, pageNum);
	}

	@Override
	public String selectFraudCode(String autoId) {
		return getFraudCodeDao().selectFraudCode(autoId);
	}

	@Override
	public int updateFraudCodeWithoutStatus(FraudCode fraudCode) {
		return getFraudCodeDao().updateFraudCodeWithoutStatus(fraudCode);
	}
}
