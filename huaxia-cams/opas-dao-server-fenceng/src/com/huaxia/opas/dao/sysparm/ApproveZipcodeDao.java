package com.huaxia.opas.dao.sysparm;

import java.util.List;
import java.util.Map;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.sysparm.ApproveZipcode;
/**
 * 邮编区号维护DAO层接口
 * @author liudi
 * @since 2017-03-16
 * @version 1.0
 */
public interface ApproveZipcodeDao {
	//添加
	public int saveApproveZipcode(ApproveZipcode approveZipcode) throws CoreException;
	//更新
	public int updateApproveZipcode(ApproveZipcode approveZipcode) throws CoreException;
	//删除
	public int deleteApproveZipcode(String autoId) throws CoreException;
	//邮编区号件数查询
	public int queryApproveZipcodeCount(ApproveZipcode approveZipcode) throws CoreException;
	//邮编区号页面初始化详细查询
	public List<ApproveZipcode> queryApproveZipcode(ApproveZipcode approveZipcode,int curNum,int pageNum) throws CoreException;
	//邮编区号页面点击查询后实现查询和排序
	public List<ApproveZipcode> queryApproveZipcode(Map<String,Object> params,int curNum,int pageNum) throws CoreException;
	
	List<ApproveZipcode> queryApproveZipcodeByTelno(Map map) throws CoreException;
	public int queryTelno(Map<String,Object> map);
	//修改对象信息不包括状态
	public int updateApproveZipcodeWithoutAllStatus(ApproveZipcode approveZipcode);
	//修改对象信息不包括网点状态
	public int updateApproveZipcodeWithoutStatus(ApproveZipcode approveZipcode);
	//修改对象信息不包括异地状态
	public int updateApproveZipcodeWithoutRemoteStatus(ApproveZipcode approveZipcode);
	//查询修改前的状态
	public ApproveZipcode selectStatus(String autoId);
	//批量删除
	public void deleteApproveZipcodes(List<String> ids);
	//全部删除
	public void deleteAllApproveZipcodes();
	//导入
	public int insertApproveZipcodeFromFile(List<ApproveZipcode> list);
	//查重
	public int queryRepetition(Map<String, String> map);

	
	
}
