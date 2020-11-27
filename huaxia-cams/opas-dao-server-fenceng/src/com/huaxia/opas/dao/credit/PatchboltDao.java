package com.huaxia.opas.dao.credit;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.allot.AllotApplyAllot;
import com.huaxia.opas.domain.allot.AllotApplyAllotHis;
import com.huaxia.opas.domain.archive.SuppArchive;
import com.huaxia.opas.domain.decision.TelcheckAddnote;
import com.huaxia.opas.domain.input.BizInpApplC1;
import com.huaxia.opas.domain.sysparm.Patchbolt;
import com.huaxia.opas.domain.sysparm.PatchboltYdj;

public interface PatchboltDao{
	
	
	//#############  标准卡    #############
	
	//weijinfeng
	public void insertPatchbolt(Patchbolt patchbolt) throws CoreException;
	
	//shihuan 
	public void updateByAppId(Patchbolt patchbolt) throws CoreException;
	
	//shihuan 用于二次补件反显已勾选数据 2017-03-28
	public Patchbolt selectByPrimaryKey(String appId) throws CoreException;
	
	
	//#############  易达金    #############
	
	//weijinfeng
	public void insertPatchboltYdj(PatchboltYdj patchboltYdj) throws CoreException;
	
	//shihuan Ydj
	public void updateByAppIdYdj(PatchboltYdj patchboltYdj) throws CoreException, ParseException;
	
	//shihuan 用于二次补件反显已勾选数据 2017-04-01 Ydj
	public PatchboltYdj selectByPrimaryKeyYdj(String appId) throws CoreException;
	
	//#############  公共部分    #############
	
	//shihuan 修改进件信息表的状态  由补件队列到未完成队列
	public void updateDelStatusByAppId(AllotApplyAllot allotapplyallot) throws CoreException;
	
	//shihuan 查询推广人手机号，申请人电话，姓名，证件号 selectByPrimaryKey
	public BizInpApplC1 querybizInpApplList(String appId) throws CoreException;

	//shihuan 将补件结果、时间、操作人存入大备注 2017-03-29 
	public void insertBigMemo(TelcheckAddnote telcheckaddnote) throws CoreException;
	
	//shihuan 补件修改申请件分配状态时，在历史表中插入数据  2017-04-03
	public void insertAllotApplyHis(AllotApplyAllotHis allotapplyallothis) throws CoreException;
	
	//shihuan 根据申请件appid查询申请件分配表数据 2017-04-03
	public AllotApplyAllotHis selectAllotApplyByAppId(String appId) throws CoreException;

	public Date queryTargetDate(Map<String, Object> params) throws CoreException;

	public int queryPatchboltCountByAppId(String appId);

	public int queryPatchboltYdjCountByAppId(String appId);
	//根据autoId查询标准卡补件信息表里面是否存在着条数据
	public int queryCountById(String autoId);
	//根据autoId更新标准卡补件信息表
	public void updateByAutoId(Map<String,String> paramMap);
	//根据autoId更新易达金卡补件信息表
	public void updateYdjByAutoId(Map<String,String> paramMap);
	//更新补件码至标准卡补件信息表
	public void updatePatchCode(Patchbolt patchbolt);
	//更新补件码至易达金补件信息表
	public void updatePatchCodeYdj(PatchboltYdj patchboltYdj);

	public SuppArchive selectByAppId(String appId);

	public String selectAppIdByAutoId(Map<String, String> paramMap);

	public int updateBzkByAppId(Map<String, String> paramMap);

	public Map<String, String> querySomeFlagFromAllot(String appId);

	public int deleteByAppId(String appId);
	
	//操作预审相关表的方法
	
	//根据autoId查询预审标准卡补件信息表里面是否存在着条数据
	public int queryYsCountById(String autoId);
	//根据autoId更新预审标准卡补件信息表
	public void updateYsByAutoId(Map<String,String> paramMap);
}
