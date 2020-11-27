package com.huaxia.opas.dao.sysparm.impl;

import java.util.ArrayList;
import java.util.List;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.sysparm.CompanyRiskDao;
import com.huaxia.opas.domain.riskcheck.CompanyRisk;
import com.huaxia.opas.domain.riskcheck.CompanyRiskHistory;

public class CompanyRiskDaoImpl extends AbstractDAO implements CompanyRiskDao {
	
	private static final long serialVersionUID = 5470178573528036415L;
	
	private static final String NAMESPACES = "CompanyRisklist.";
	//添加
	public int insertCompanyRisk(CompanyRisk companyRisk) throws CoreException {
		return getSqlMap().insert(NAMESPACES + "insertCompanyRisk", companyRisk);
	}
	
	//查询的页数
	@Override
	public int queryCompanyRiskCount(CompanyRisk companyRisk) {
		return getSqlMap().queryForObject(NAMESPACES + "queryCompanyRiskCount", companyRisk);
	}
	
	//查询
	@Override
	public List<CompanyRisk> queryCompanyRiskList(CompanyRisk companyRisk, int curNum, int pageNum) {
		List<CompanyRisk> list = new ArrayList<CompanyRisk>();

		list = getSqlMap().queryForList(NAMESPACES + "queryCompanyRiskList", companyRisk, curNum, pageNum);

		return list;
	}
	//修改
	@Override
	public int updateCompanyRisk(CompanyRisk companyRisk) throws CoreException {
		return getSqlMap().update(NAMESPACES + "updateCompanyRisk", companyRisk);
	}
	//删除
	@Override
	public int deleteCompanyRisk(String autoId) throws CoreException {
		CompanyRisk companyRisk = new CompanyRisk();
		companyRisk.setAutoId(autoId);
		return getSqlMap().delete(NAMESPACES + "deleteCompanyRisk", autoId);
	}

	//shihuan 2017-03-05  地址风险名单批量启动停用修改状态
	@Override
	public int updateStartStatus(String autoId) throws CoreException {
		return getSqlMap().update(NAMESPACES + "updateStartStatus", autoId);
	}
	
	//shihuan 2017-03-05  地址风险名单批量启动停用修改状态
	@Override
	public int updateStopStatus(String autoId) throws CoreException {
		return getSqlMap().update(NAMESPACES + "updateStopStatus", autoId);
	}
	
	//shihuan 2017-03-14  历史重点院校列表查询 
	@Override
	public List<CompanyRisk> queryCompanyRistListHistory(String autoId, int curNum, int pageNum) {

		List<CompanyRisk> list = new ArrayList<CompanyRisk>();

		list = getSqlMap().queryForList(NAMESPACES + "queryCompanyRistListHistory", autoId, curNum, pageNum);

		return list;
	}

	//shihuan 2017-03-14  历史重点院校列表查询 条数
	@Override
	public int queryCompanyRistHistoryCount(String autoId) {
		return getSqlMap().queryForObject(NAMESPACES + "queryCompanyRistHistoryCount", autoId);
	}
	
	@Override
	public CompanyRisk selectByPrimaryKey(String autoId) {
		CompanyRisk companyrisk = new CompanyRisk();
		companyrisk.setAutoId(autoId);
		return (getSqlMap().queryForObject(NAMESPACES + "selectByPrimaryKey", autoId));
	}
	
	//添加
	public int insertCompanyRiskHis(CompanyRiskHistory companyriskhistory) throws CoreException {
		return getSqlMap().insert(NAMESPACES + "insertCompanyRiskHis", companyriskhistory);
	}
	
	//shihuan 2017-03-10 单位风险名单批导入文件
	@Override
	public int insertCompanyUpload(List<CompanyRisk> obj) throws CoreException {
		return getSqlMap().insert(NAMESPACES + "insertCompanyUpload", obj);
	}
	
	//新增修改时校验单位名称是否添加重复  shihuan  2017-04-18
	@Override
	public int queryByComName(String companyName) {
		return getSqlMap().queryForObject(NAMESPACES + "queryByComName", companyName);
	}
}
