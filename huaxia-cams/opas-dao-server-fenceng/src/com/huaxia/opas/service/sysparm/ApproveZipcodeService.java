package com.huaxia.opas.service.sysparm;

import java.util.List;
import java.util.Map;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.sysparm.ApproveZipcode;
import com.huaxia.opas.domain.sysparm.BatchfileInfo;
/**
 * 邮编区号维护服务层接口
 * @author liudi
 * @since 2017-03-16
 * @version 1.0
 */
public interface ApproveZipcodeService {
	//添加
	public int saveApproveZipcode(ApproveZipcode approveZipcode) throws CoreException;
	//修改
	public int updateApproveZipcode(ApproveZipcode approveZipcode) throws CoreException;
	//删除
	public int deleteApproveZipcode(String autoId) throws CoreException;
	//邮编区号件数查询
	public int queryApproveZipcodeCount(ApproveZipcode approveZipcode) throws CoreException;
	//邮编区号页面初始化详细查询
	public List<ApproveZipcode> queryApproveZipcode(ApproveZipcode approveZipcode,int curNum,int pageNum) throws CoreException;
	//点击查询后，触发卡邮编区号信息查询和排序功能
	public List<ApproveZipcode> queryApproveZipcode(Map<String,Object> params,int curNum,int pageNum) throws CoreException;
	//修改对象信息不包括状态wangtao
	public int updateApproveZipcodeWithoutAllStatus(ApproveZipcode approveZipcode);
	//修改对象信息不包括网点状态wangtao
	public int updateApproveZipcodeWithoutStatus(ApproveZipcode approveZipcode);
	//修改对象信息不包括异地状态wangtao
	public int updateApproveZipcodeWithoutRemoteStatus(ApproveZipcode approveZipcode);
	//查询修改前的状态wangtao
	public ApproveZipcode selectStatus(String autoId);
	//批量删除
	public void deleteApproveZipcodes (List<String> ids) throws CoreException;
	//全部删除
	public void deleteAllApproveZipcodes();
	//上传
	public int insertApproveZipcodeFromFile(List<ApproveZipcode> list, BatchfileInfo batchfileInfo);
	//查重校验
	public int queryRepetition(Map<String, String> map);
	
}
