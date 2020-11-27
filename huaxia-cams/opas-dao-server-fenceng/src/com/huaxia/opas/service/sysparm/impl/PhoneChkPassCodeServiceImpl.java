package com.huaxia.opas.service.sysparm.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.domain.sysparm.PhoneChkPassCode;
import com.huaxia.opas.dao.sysparm.PhoneChkPassCodeDao;
import com.huaxia.opas.service.sysparm.PhoneChkPassCodeService;
/**
 * 电核过件码服务层实现类
 * @author liudi  wangdebin
 * @since 2017-02-28
 * @version 1.0
 */
public class PhoneChkPassCodeServiceImpl extends AbstractDAO implements PhoneChkPassCodeService,Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -818223651863282587L;

	@Resource(name = "phoneChkPassCodeDao")
	private PhoneChkPassCodeDao phoneChkPassCodeDao;

	public PhoneChkPassCodeDao getPhoneChkPassCodeDao() {
		return phoneChkPassCodeDao;
	}

	public void setPhoneChkPassCodeDao(PhoneChkPassCodeDao phoneChkPassCodeDao) {
		this.phoneChkPassCodeDao = phoneChkPassCodeDao;
	}

	//添加状态为Start
	public int savePhoneChkPassCodeStart(PhoneChkPassCode phoneChkPassCode) throws CoreException {
		return getPhoneChkPassCodeDao().savePhoneChkPassCodeStart(phoneChkPassCode);
	}
	
	//添加状态为End
	public int savePhoneChkPassCodeEnd(PhoneChkPassCode phoneChkPassCode) throws CoreException {
		return getPhoneChkPassCodeDao().savePhoneChkPassCodeEnd(phoneChkPassCode);
	}
	
	//修改
	public int updatePhoneChkPassCode(PhoneChkPassCode phoneChkPassCode) throws CoreException {
		return getPhoneChkPassCodeDao().updatePhoneChkPassCode(phoneChkPassCode);
	}
	
	//删除
	public int deletePhoneChkPassCode(String autoId) throws CoreException {
		return getPhoneChkPassCodeDao().deletePhoneChkPassCode(autoId);
	}
	
	//件数查询
	public int queryPhoneChkPassCodeCount(PhoneChkPassCode phoneChkPassCode) throws CoreException {
		return getPhoneChkPassCodeDao().queryPhoneChkPassCodeCount(phoneChkPassCode);
	}
	
	//查询
	public List<PhoneChkPassCode> queryPhoneChkPassCode(PhoneChkPassCode phoneChkPassCode,int curNum,int pageNum) throws CoreException {
		return getPhoneChkPassCodeDao().queryPhoneChkPassCode(phoneChkPassCode, curNum, pageNum);
	}
	
	//查询是否重复
	public PhoneChkPassCode queryPhoneChkPassCode(PhoneChkPassCode phoneChkPassCode) throws CoreException {
		return getPhoneChkPassCodeDao().queryPhoneChkPassCode(phoneChkPassCode);
	}
	
	//点击查询后，触发查询和排序功能
	public List<PhoneChkPassCode> queryPhoneChkPassCode(Map<String,Object> params,int curNum,int pageNum) throws CoreException {
		return getPhoneChkPassCodeDao().queryPhoneChkPassCode(params, curNum, pageNum);
	}
	
	//全部查询
	public List<PhoneChkPassCode> queryAllPhoneChkPassCode(Map<String,Object> params) throws CoreException {
		return getPhoneChkPassCodeDao().queryAllPhoneChkPassCode(params);
	}

	@Override
	public String queryPhoneChkPassCodeStatus(String autoId) {
		return getPhoneChkPassCodeDao().queryPhoneChkPassCodeStatus(autoId);
	}

	@Override
	public int updatePhoneChkPassCodeWithoutStatus(PhoneChkPassCode phoneChkPassCode) {
		return getPhoneChkPassCodeDao().updatePhoneChkPassCodeWithoutStatus(phoneChkPassCode);
	}
}
