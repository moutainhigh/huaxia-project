package com.huaxia.opas.service.sysparm;

import java.util.List;
import java.util.Map;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.sysparm.PhoneChkPassCode;
/**
 * 电核过件码服务层接口
 * @author liudi wangdebin 
 * @since 2017-02-28
 * @version 1.0
 */
public interface PhoneChkPassCodeService {
	//添加状态为Start
	public int savePhoneChkPassCodeStart(PhoneChkPassCode phoneChkPassCode) throws CoreException;
	//添加状态为End
	public int savePhoneChkPassCodeEnd(PhoneChkPassCode phoneChkPassCode) throws CoreException;
	//修改
	public int updatePhoneChkPassCode(PhoneChkPassCode phoneChkPassCode) throws CoreException;
	//删除
	public int deletePhoneChkPassCode(String autoId) throws CoreException;
	//件数查询
	public int queryPhoneChkPassCodeCount(PhoneChkPassCode phoneChkPassCode) throws CoreException;
	//查询
	public List<PhoneChkPassCode> queryPhoneChkPassCode(PhoneChkPassCode phoneChkPassCode,int curNum,int pageNum) throws CoreException;
	
	//查询是否重复
	public PhoneChkPassCode queryPhoneChkPassCode(PhoneChkPassCode phoneChkPassCode) throws CoreException;
	//点击查询后，触发查询和排序功能
	public List<PhoneChkPassCode> queryPhoneChkPassCode(Map<String,Object> params,int curNum,int pageNum) throws CoreException;

	//全部查询
	List<PhoneChkPassCode> queryAllPhoneChkPassCode(Map<String,Object> map) throws CoreException;
	//查询修改对象前的状态
	public String queryPhoneChkPassCodeStatus(String autoId);
	//修改对象的信息(不包括状态)
	public int updatePhoneChkPassCodeWithoutStatus(PhoneChkPassCode phoneChkPassCode);
}
