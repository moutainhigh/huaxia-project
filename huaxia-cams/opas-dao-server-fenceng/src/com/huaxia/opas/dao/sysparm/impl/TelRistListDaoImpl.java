package com.huaxia.opas.dao.sysparm.impl;

import java.util.ArrayList;
import java.util.List;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.sysparm.TelRiskListDao;
import com.huaxia.opas.domain.riskcheck.AddrRiskList;
import com.huaxia.opas.domain.riskcheck.TelRiskList;

public class TelRistListDaoImpl extends AbstractDAO implements TelRiskListDao {

	private static final long serialVersionUID = 5757582418545513970L;
	
	private static final String NAMESPACES = "TelRiskList.";

	@Override
	public int insertTelRistList(List<TelRiskList> resutTelAddrlist) {
		return getSqlMap().insert(NAMESPACES + "insertTelRistList", resutTelAddrlist);
	}

	@Override
	public int insertSelective(TelRiskList telrisklist) {
		return getSqlMap().insert(NAMESPACES + "insertSelective", telrisklist);
	}

	@Override
	public int deleteByPrimaryKey(String autoId) {
		AddrRiskList addrrisklist = new AddrRiskList();
		addrrisklist.setAutoId(autoId);
		return getSqlMap().delete(NAMESPACES + "deleteByPrimaryKey", autoId);
	}

	@Override
	public int updateByPrimaryKey(TelRiskList telrisklist) {
		return getSqlMap().update(NAMESPACES + "updateByPrimaryKey", telrisklist);
	}

	@Override
	public int updateByPrimaryKeySelective(TelRiskList telrisklist) {
		return getSqlMap().update(NAMESPACES + "updateByPrimaryKeySelective", telrisklist);
	}

	@Override
	public TelRiskList selectByPrimaryKey(String autoId) {
		TelRiskList telrisklist = new TelRiskList();
		telrisklist.setAutoId(autoId);
		return (getSqlMap().queryForObject(NAMESPACES + "selectByPrimaryKey", autoId));
	}

	//shihuan 2017-03-03 地址风险名单列表查询 
	@Override
	public List<TelRiskList> queryTelRistList(TelRiskList telrisklist, int curNum, int pageNum) {

		List<TelRiskList> list = new ArrayList<TelRiskList>();

		list = getSqlMap().queryForList(NAMESPACES + "queryTelRistList", telrisklist, curNum, pageNum);

		return list;
	}

	//shihuan 2017-03-03 电话风险名单列表查询 条数
	@Override
	public int queryTelRistCount(TelRiskList telrisklist) {
		return getSqlMap().queryForObject(NAMESPACES + "queryTelRistCount", telrisklist);
	}
	
	//shihuan 2017-03-15  电话风险名单批量启动停用修改状态
	@Override
	public int updateStartStatus(String autoId) throws CoreException {
		return getSqlMap().update(NAMESPACES + "updateStartStatus", autoId);
	}
	
	//shihuan 2017-03-14  电话风险名单批量启动停用修改状态
	@Override
	public int updateStopStatus(String autoId) throws CoreException {
		return getSqlMap().update(NAMESPACES + "updateStopStatus", autoId);
	}
	
	//shihuan 2017-03-18  电话风险名单批导入文件
	@Override
	public int insertTelRistUpload(List<TelRiskList> obj) throws CoreException {
		return getSqlMap().insert(NAMESPACES + "insertTelRistUpload", obj);
	}
	
	//shihuan 新增修改时校验手机号是否添加重复 
	@Override
	public int queryByMobileId(String mobileId) {
		return getSqlMap().queryForObject(NAMESPACES + "queryByMobileId", mobileId);
	}
	
	//shihuan 新增修改时校验固话是否添加重复 
	@Override
	public int queryByCompanyTel(String companyTel) {
		return getSqlMap().queryForObject(NAMESPACES + "queryByCompanyTel", companyTel);
	}
	
	//shihuan 新增修改时校验固话是否添加重复 
	@Override
	public int queryByLivingTel(String livingTel) {
		return getSqlMap().queryForObject(NAMESPACES + "queryByLivingTel", livingTel);
	}
	
	//shihuan 新增修改时校验固话是否添加重复 
	@Override
	public int queryByOtherTel(String otherTel) {
		return getSqlMap().queryForObject(NAMESPACES + "queryByOtherTel", otherTel);
	}
}
