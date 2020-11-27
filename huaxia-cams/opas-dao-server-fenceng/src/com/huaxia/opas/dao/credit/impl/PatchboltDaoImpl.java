package com.huaxia.opas.dao.credit.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.credit.PatchboltDao;
import com.huaxia.opas.domain.allot.AllotApplyAllot;
import com.huaxia.opas.domain.allot.AllotApplyAllotHis;
import com.huaxia.opas.domain.archive.SuppArchive;
import com.huaxia.opas.domain.decision.TelcheckAddnote;
import com.huaxia.opas.domain.input.BizInpApplC1;
import com.huaxia.opas.domain.sysparm.Patchbolt;
import com.huaxia.opas.domain.sysparm.PatchboltYdj; 

public class PatchboltDaoImpl extends AbstractDAO implements PatchboltDao {
	private static final long serialVersionUID = -6902231423463254250L;

	private static final String NAMESPACES = "Patchbolt.";
	
	//#############  标准卡    #############
	
	//weijinfeng
	@Override
	public void insertPatchbolt(Patchbolt patchbolt) throws CoreException{
		getSqlMap().insert(NAMESPACES + "insertPatchbolt", patchbolt);
		return;
	}
	
	//shihuan
	@Override
	public void updateByAppId(Patchbolt patchbolt) throws CoreException{
		getSqlMap().update(NAMESPACES + "updateByAppId", patchbolt);
		return;
	}
	
	//shihuan 用于二次补件反显已勾选数据 2017-03-28
	@Override
	public Patchbolt selectByPrimaryKey(String appId) throws CoreException{
		return getSqlMap().queryForObject(NAMESPACES + "selectByPrimaryKey", appId);
	}
	
	//#############  易达金    #############
	
	//weijinfeng
	@Override 
	public void insertPatchboltYdj(PatchboltYdj patchboltYdj) throws CoreException{
		getSqlMap().insert(NAMESPACES + "insertPatchboltYdj", patchboltYdj);
		return;
	}
	
	//shihuan Ydj
	@Override
	public void updateByAppIdYdj(PatchboltYdj patchboltYdj) throws CoreException{
		getSqlMap().update(NAMESPACES + "updateByAppIdYdj", patchboltYdj);
		return;
	}
	
	//shihuan 用于二次补件反显已勾选数据 2017-03-28
	@Override
	public PatchboltYdj selectByPrimaryKeyYdj(String appId) throws CoreException{
		return getSqlMap().queryForObject(NAMESPACES + "selectByPrimaryKeyYdj", appId);
	}
	
	//#############  公共部分   #############
	
	//shihuan 修改进件信息表的状态  由补件队列到未完成队列
	@Override
	public void updateDelStatusByAppId(AllotApplyAllot allotapplyallot) throws CoreException{
		getSqlMap().update(NAMESPACES + "updateDelStatusByAppId", allotapplyallot);
		return;
	}
	
	//shihuan 修改进件信息表的状态  由补件队列到未完成队列
	@Override
	public BizInpApplC1 querybizInpApplList(String appId) throws CoreException{
		return getSqlMap().queryForObject(NAMESPACES + "querybizInpApplList", appId);
	}
	
	//shihuan 将补件结果、时间、操作人存入大备注 2017-03-29 
	@Override
	public void insertBigMemo(TelcheckAddnote telcheckaddnote) throws CoreException{
		getSqlMap().insert(NAMESPACES + "insertBigMemo", telcheckaddnote);
		return;
	}
	
	//shihuan 补件修改申请件分配状态时，在历史表中插入数据  2017-04-03
	@Override
	public void insertAllotApplyHis(AllotApplyAllotHis allotapplyallothis) throws CoreException{
		getSqlMap().insert(NAMESPACES + "insertAllotApplyHis", allotapplyallothis);
		return;
	}
	
	//shihuan 根据申请件appid查询申请件分配表数据 2017-04-03
	@Override
	public AllotApplyAllotHis selectAllotApplyByAppId(String appId) throws CoreException{
		return getSqlMap().queryForObject(NAMESPACES + "selectAllotApplyByAppId", appId);
	}

	@Override
	public Date queryTargetDate(Map<String, Object> params) throws CoreException {
		return getSqlMap().queryForObject(NAMESPACES + "queryTargetDate", params);
	}

	@Override
	public int queryPatchboltCountByAppId(String appId) {
		return getSqlMap().queryForObject(NAMESPACES + "queryPatchboltCountByAppId", appId);
	}

	@Override
	public int queryPatchboltYdjCountByAppId(String appId) {
		return getSqlMap().queryForObject(NAMESPACES + "queryPatchboltYdjCountByAppId", appId);
	}

	@Override
	public int queryCountById(String autoId) {
		return  getSqlMap().queryForObject(NAMESPACES + "queryCountById", autoId);
	}

	@Override
	public void updateByAutoId(Map<String,String> paramMap) {
		getSqlMap().update(NAMESPACES + "updateByAutoId", paramMap);
	}

	@Override
	public void updateYdjByAutoId(Map<String,String> paramMap) {
		getSqlMap().update(NAMESPACES + "updateYdjByAutoId",paramMap);
	}

	@Override
	public void updatePatchCode(Patchbolt patchbolt) {
		getSqlMap().update(NAMESPACES + "updatePatchCode",patchbolt);
	}

	@Override
	public void updatePatchCodeYdj(PatchboltYdj patchboltYdj) {
		getSqlMap().update(NAMESPACES + "updatePatchCodeYdj",patchboltYdj);
	}

	@Override
	public SuppArchive selectByAppId(String appId) {
		return getSqlMap().queryForObject(NAMESPACES + "selectByAppId", appId);
	}

	@Override
	public String selectAppIdByAutoId(Map<String, String> paramMap) {
		return getSqlMap().queryForObject(NAMESPACES + "selectAppIdByAutoId", paramMap);
	}

	@Override
	public int updateBzkByAppId(Map<String, String> paramMap) {
		return getSqlMap().update(NAMESPACES + "updateBzkByAppId",paramMap);
	}

	@Override
	public Map<String, String> querySomeFlagFromAllot(String appId) {
		return getSqlMap().queryForObject(NAMESPACES + "querySomeFlagFromAllot", appId);
	}

	@Override
	public int deleteByAppId(String appId) {
		return getSqlMap().delete(NAMESPACES + "deleteByAppId", appId);
	}

	@Override
	public int queryYsCountById(String autoId) {
		return  getSqlMap().queryForObject(NAMESPACES + "queryYsCountById", autoId);
	}

	@Override
	public void updateYsByAutoId(Map<String, String> paramMap) {
		getSqlMap().update(NAMESPACES + "updateYsByAutoId", paramMap);
	}
}
