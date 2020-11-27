package com.huaxia.opas.dao.sysparm.impl;

import java.util.List;
import java.util.Map;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.domain.sysparm.PhoneChkPassCode;
import com.huaxia.opas.dao.sysparm.PhoneChkPassCodeDao;

/**
 * 电核过件码DAO层实现类
 * @author liudi  wangdebin
 * @since 2017-02-28
 * @version 1.0
 */
public class PhoneChkPassCodeDaoImpl extends AbstractDAO implements PhoneChkPassCodeDao{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4776818757765799387L;
	/**
	 * 
	 */
	private static final String NAMESPACES = "PhoneChkPassCode.";
	//添加状态为Start
	@Override
	public int savePhoneChkPassCodeStart(PhoneChkPassCode phoneChkPassCode) throws CoreException {
		return getSqlMap().insert(NAMESPACES + "insertPhoneCode1", phoneChkPassCode);
	}
	//添加状态为End
	@Override
	public int savePhoneChkPassCodeEnd(PhoneChkPassCode phoneChkPassCode) throws CoreException {
		return getSqlMap().insert(NAMESPACES + "insertPhoneCode2", phoneChkPassCode);
	}
	//修改
	@Override
	public int updatePhoneChkPassCode(PhoneChkPassCode phoneChkPassCode) throws CoreException {
		return getSqlMap().update(NAMESPACES + "updatePhoneCode", phoneChkPassCode);
	}
	//删除
	@Override
	public int deletePhoneChkPassCode(String autoID) throws CoreException {
		return getSqlMap().delete(NAMESPACES + "deletePhoneCodeById", autoID);
	}
	//件数查询
	@Override
	public int queryPhoneChkPassCodeCount(PhoneChkPassCode phoneChkPassCode) throws CoreException {
		return getSqlMap().queryForObject(NAMESPACES + "queryPhoneCodeCount", phoneChkPassCode);
	}
	//查询
	@Override
	public List<PhoneChkPassCode> queryPhoneChkPassCode(PhoneChkPassCode phoneChkPassCode, int curNum, int pageNum) throws CoreException {
		return getSqlMap().queryForList(NAMESPACES + "queryPhoneCode", phoneChkPassCode, curNum, pageNum);
	}
	//点击查询后，触发查询和排序功能
	@Override
	public List<PhoneChkPassCode> queryPhoneChkPassCode(Map<String, Object> params, int curNum, int pageNum) throws CoreException {
		return getSqlMap().queryForList(NAMESPACES + "queryPhoneCode2", params, curNum, pageNum);
	}
	//查询是否重复
	@Override
	public PhoneChkPassCode queryPhoneChkPassCode(PhoneChkPassCode phoneChkPassCode) throws CoreException {
		return getSqlMap().queryForObject(NAMESPACES + "queryPhoneCode3", phoneChkPassCode);
	}
	
	//全部 查询
	@Override
	public List<PhoneChkPassCode> queryAllPhoneChkPassCode(Map<String, Object> params) throws CoreException {
		return getSqlMap().queryForList(NAMESPACES + "queryAllPhoneCode", params);
	}
	@Override
	public String queryPhoneChkPassCodeStatus(String autoId) {
		return getSqlMap().queryForObject(NAMESPACES + "queryPhoneChkPassCodeStatus", autoId);
	}
	
	@Override
	public int updatePhoneChkPassCodeWithoutStatus(PhoneChkPassCode phoneChkPassCode) {
		return getSqlMap().update(NAMESPACES + "updatePhoneChkPassCodeWithoutStatus", phoneChkPassCode);
	}
}
