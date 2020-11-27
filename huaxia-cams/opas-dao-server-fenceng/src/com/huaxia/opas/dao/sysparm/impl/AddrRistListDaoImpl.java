package com.huaxia.opas.dao.sysparm.impl;

import java.util.ArrayList;
import java.util.List;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.sysparm.AddrRiskListDao;
import com.huaxia.opas.domain.riskcheck.AddrRiskList;

public class AddrRistListDaoImpl extends AbstractDAO implements AddrRiskListDao {

	private static final long serialVersionUID = 5757582418545513970L;
	
	private static final String NAMESPACES = "AddrRiskList.";

	@Override
	public int insert(AddrRiskList addrrisklist) {
		return getSqlMap().insert(NAMESPACES + "insert", addrrisklist);
	}

	@Override
	public int insertSelective(AddrRiskList addrrisklist) {
		return getSqlMap().insert(NAMESPACES + "insertSelective", addrrisklist);
	}

	@Override
	public int deleteByPrimaryKey(String autoId) {
		AddrRiskList addrrisklist = new AddrRiskList();
		addrrisklist.setAutoId(autoId);
		return getSqlMap().delete(NAMESPACES + "deleteByPrimaryKey", autoId);
	}

	@Override
	public int updateByPrimaryKey(AddrRiskList addrrisklist) {
		return getSqlMap().update(NAMESPACES + "updateByPrimaryKey", addrrisklist);
	}

	@Override
	public int updateByPrimaryKeySelective(AddrRiskList addrrisklist) {
		return getSqlMap().update(NAMESPACES + "updateByPrimaryKeySelective", addrrisklist);
	}

	@Override
	public AddrRiskList selectByPrimaryKey(String autoId) {
		AddrRiskList addrrisklist = new AddrRiskList();
		addrrisklist.setAutoId(autoId);
		return (getSqlMap().queryForObject(NAMESPACES + "selectByPrimaryKey", autoId));
	}

	
	//shihuan 2017-03-03 地址风险名单列表查询 
	@Override
	public List<AddrRiskList> queryAddrRistList(AddrRiskList addrrisklist, int curNum, int pageNum) {

		List<AddrRiskList> list = new ArrayList<AddrRiskList>();

		list = getSqlMap().queryForList(NAMESPACES + "queryAddrRistList", addrrisklist, curNum, pageNum);

		return list;
	}

	//shihuan 2017-03-03 地址风险名单列表查询 条数
	@Override
	public int queryAddrRistCount(AddrRiskList addrrisklist) {
		return getSqlMap().queryForObject(NAMESPACES + "queryAddrRistCount", addrrisklist);
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
	
	//shihuan 2017-03-10 地址风险名单批导入文件
	@Override
	public int insertAddrUpload(List<AddrRiskList> obj) throws CoreException {
		return getSqlMap().insert(NAMESPACES + "insertAddrUpload", obj);
	}
}
