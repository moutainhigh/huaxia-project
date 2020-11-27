package com.huaxia.opas.dao.sysparm.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.sysparm.CompanyInfoDao;
import com.huaxia.opas.domain.sysparm.CompanyInfoList;

public class CompanyInfoDaoImpl extends AbstractDAO implements CompanyInfoDao, Serializable {

	private static final long serialVersionUID = -4784592158851749403L;
	
	private static final String NAMESPACES = "CompanyInfoList.";

	@Override
	public int insert(CompanyInfoList companyInfolist) {
		return getSqlMap().insert(NAMESPACES + "insert", companyInfolist);
	}

	@Override
	public int insertSelective(CompanyInfoList companyInfolist) {
		return getSqlMap().insert(NAMESPACES + "insertSelective", companyInfolist);
	}

	@Override
	public int deleteByPrimaryKey(String autoId) {
		CompanyInfoList companyInfolist = new CompanyInfoList();
		companyInfolist.setAutoId(autoId);
		return getSqlMap().delete(NAMESPACES + "deleteByPrimaryKey", autoId);
	}

	@Override
	public int updateByPrimaryKey(CompanyInfoList companyInfolist) {
		return getSqlMap().update(NAMESPACES + "updateByPrimaryKey", companyInfolist);
	}

	@Override
	public int updateByPrimaryKeySelective(CompanyInfoList companyInfolist) {
		return getSqlMap().update(NAMESPACES + "updateByPrimaryKeySelective", companyInfolist);
	}

	@Override
	public CompanyInfoList selectByPrimaryKey(String autoId) {
		CompanyInfoList companyInfolist = new CompanyInfoList();
		companyInfolist.setAutoId(autoId);
		return (getSqlMap().queryForObject(NAMESPACES + "selectByPrimaryKey", autoId));
	}

	
	//chenmeng 2019-04-12   3311企业名单库列表查询 
	@Override
	public List<CompanyInfoList> queryCompanyInfo(CompanyInfoList companyInfolist, int curNum, int pageNum) {

		List<CompanyInfoList> list = new ArrayList<CompanyInfoList>();

		list = getSqlMap().queryForList(NAMESPACES + "queryCompanyInfoList", companyInfolist, curNum, pageNum);

		return list;
	}

	//chenmeng 2019-04-12   3311企业名单库列表查询 条数
	@Override
	public int queryCompanyInfoCount(CompanyInfoList companyInfolist) {
		return getSqlMap().queryForObject(NAMESPACES + "queryCompanyInfoCount", companyInfolist);
	}
	
	//chenmeng 2019-04-12   3311企业名单库批量启动停用修改状态
	/*@Override
	public int updateStartStatus(String autoId) throws CoreException {
		return getSqlMap().update(NAMESPACES + "updateStartStatus", autoId);
	}*/
	
	//chenmeng 2019-04-12   3311企业名单库批量启动停用修改状态
	/*@Override
	public int updateStopStatus(String autoId) throws CoreException {
		return getSqlMap().update(NAMESPACES + "updateStopStatus", autoId);
	}*/
	
	//chenmeng 2019-04-12    添加历史修改记录表
	@Override
	public int insertHisSelective(CompanyInfoList companyInfolist) {
		return getSqlMap().insert(NAMESPACES + "insertHisSelective", companyInfolist);
	}
	
	//chenmeng 2019-04-12  历史3311企业名单库列表查询 
	@Override
	public List<CompanyInfoList> queryCompanyInfoHistory(String autoId, int curNum, int pageNum) {

		List<CompanyInfoList> list = new ArrayList<CompanyInfoList>();

		list = getSqlMap().queryForList(NAMESPACES + "queryCompanyInfoHistory", autoId, curNum, pageNum);

		return list;
	}

	//chenmeng 2019-04-12  历史3311企业名单库列表查询 条数
	@Override
	public int queryCompanyInfoHistoryCount(String autoId) {
		return getSqlMap().queryForObject(NAMESPACES + "queryCompanyInfoHistoryCount", autoId);
	}
	
	//chenmeng 2019-04-12  3311企业名单库批量导入文件
	@Override
	public int insertCompanyInfoUpload(List<CompanyInfoList> obj) throws CoreException {
		return getSqlMap().insert(NAMESPACES + "insertCompanyInfoUpload", obj);
	}
	
	//chenmeng 2019-04-12  历史3311企业名单库列表查询 条数
	@Override
	public int queryCompanyName(String companyName) {
		return getSqlMap().queryForObject(NAMESPACES + "queryCompanyName", companyName);
	}
}
