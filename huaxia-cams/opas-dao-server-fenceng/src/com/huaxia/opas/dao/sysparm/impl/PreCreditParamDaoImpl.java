package com.huaxia.opas.dao.sysparm.impl;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.sysparm.PreCreditParamDao;
import com.huaxia.opas.domain.sysparm.BatchfileInfo;
import com.huaxia.opas.domain.sysparm.PreCreditParam;
/**
 * 
 * @author 汪涛
 *
 */
public class PreCreditParamDaoImpl extends AbstractDAO  implements PreCreditParamDao {

	private static final long serialVersionUID = 6497434023765964100L;
	private static final String NAMESPACES = "PreCreditParam.";
	/**
	 * 预授信信息的查询列表显示
	 */
	@Override
	public List<PreCreditParam> queryPreCreditParamList(PreCreditParam preCreditParam, int curNum, int pageNum) {
		List<PreCreditParam> list=new ArrayList<PreCreditParam>();
		list= getSqlMap().queryForList(NAMESPACES + "queryPreCreditParamList", preCreditParam, curNum, pageNum);
		return list;
	}

	@Override
	public int queryPreCreditParamListByIdnoCount(String appId) {
		return  getSqlMap().queryForObject(NAMESPACES + "queryPreCreditParamListByIdnoCount", appId);	
	}
	
	@Override
	public List<PreCreditParam> queryPreCreditParamListByIdno(String appId, int curNum, int pageNum) {
		List<PreCreditParam> list=new ArrayList<PreCreditParam>();
		list= getSqlMap().queryForList(NAMESPACES + "queryPreCreditParamListByIdno", appId, curNum, pageNum);
		return list;
	}

	@Override
	public List<PreCreditParam> queryPreCreditParamListByIdno(String appId) {
		List<PreCreditParam> list=new ArrayList<PreCreditParam>();
		list= getSqlMap().queryForList(NAMESPACES + "queryPreCreditParamListByIdno", appId);
		return list;
	}
	
	@Override
	public int queryPreCreditParamCount(PreCreditParam preCreditParam) {
		return  getSqlMap().queryForObject(NAMESPACES + "queryPreCreditParamCount", preCreditParam);	
	}
	/**
	 * 预授信信息的修改
	 */
	@Override
	public int updatePreCreditParam(PreCreditParam preCreditParam)  {
		return getSqlMap().update(NAMESPACES + "updatePreCreditParam", preCreditParam);
	}

	//	@Override
	//	public int deletePreCreditParam(String autoID) {
	//		return getSqlMap().delete(NAMESPACES + "deletePreCreditParam", autoID);
	//	}
	
	/**
	 * 新增时，设置状态为启用
	 */
	@Override
	public int insertPreCreditParamStart(PreCreditParam preCreditParam) {
		return getSqlMap().insert(NAMESPACES + "insertPreCreditParamStart", preCreditParam);
	}
	/**
	 * 新增时设置状态为禁用
	 */
	@Override
	public int insertPreCreditParamEnd(PreCreditParam preCreditParam) {
		return getSqlMap().insert(NAMESPACES + "insertPreCreditParamEnd", preCreditParam);
	}
	/**
	 * 批量启用
	 */
	@Override
	public int batchStartPreCreditParam(List<Map<Object,Object>> preCreditParamMaps) {
		return getSqlMap().update(NAMESPACES + "batchStartPreCreditParam", preCreditParamMaps);
	}
	/**
	 * 批量禁用
	 */
	@Override
	public int batchStopPreCreditParam(List<Map<Object,Object>> preCreditParamMaps) {
		return getSqlMap().update(NAMESPACES + "batchStopPreCreditParam", preCreditParamMaps);
	}
	/**
	 * 批量删除
	 */
	@Override
	public int batchDeletePreCreditParam(List<String> autoIds) {
		return getSqlMap().delete(NAMESPACES + "batchDeletePreCreditParam", autoIds);
	}
	/**
	 * 批量导入
	 */
	@Override
	public int insertPreCreditParamImportFile(List<PreCreditParam> preCreditParamList) {
		return getSqlMap().insert(NAMESPACES + "insertPreCreditParamImportFile", preCreditParamList);
	}
	/**
	 * 预授信信息的历史查询
	 */
	@Override
	public int queryPreCreditParamHistoryCount(String autoID) {
		return getSqlMap().queryForObject(NAMESPACES + "queryPreCreditParamHistoryCount", autoID);
	}
	@Override
	public List<PreCreditParam> queryPreCreditParamHistoryList(String autoID, int curNum, int pageNum) {
		List<PreCreditParam> list=new ArrayList<PreCreditParam>();
		list= getSqlMap().queryForList(NAMESPACES + "queryPreCreditParamHistoryList",autoID,curNum,pageNum);
		return list;
	}
	/**
	 * 新增和修改时插入一条历史纪录
	 */
	@Override
	public int insertPreCreditParamHis(PreCreditParam preCreditParam) {
		return getSqlMap().insert(NAMESPACES + "insertPreCreditParamHis", preCreditParam);
	}
	/**
	 * 批量启用和禁用添加历史记录
	 */
	@Override
	public int insertPreCreditParamHisOfBatch(List<Map<Object, Object>> preCreditParamMaps) {
		return getSqlMap().insert(NAMESPACES + "insertPreCreditParamHisOfBatch", preCreditParamMaps);
	}
	/**
	 * 导入时插入历史记录
	 */
	@Override
	public int insertCredit(BatchfileInfo batchfileinfo) {
		return  getSqlMap().insert(NAMESPACES + "insertCredit", batchfileinfo);
	}

	@Override
	public PreCreditParam selectById(String autoID) {
		return  getSqlMap().queryForObject(NAMESPACES + "selectById", autoID);	
	}

	@Override
	public int queryPreCreditParamUploadCount(BatchfileInfo batchfileInfo) {
		return getSqlMap().queryForObject(NAMESPACES + "queryPreCreditParamUploadCount", batchfileInfo);
	}

	@Override
	public List<BatchfileInfo> queryPreCreditParamUpload(BatchfileInfo batchfileInfo, int curNum, int pageNum) {
		return getSqlMap().queryForList(NAMESPACES+"queryPreCreditParamUpload",batchfileInfo,curNum,pageNum);
	}

	@Override
	public String queryPreCreditParamStatus(String autoID) {
		return getSqlMap().queryForObject(NAMESPACES+"queryPreCreditParamStatus",autoID);
	}

	@Override
	public int updatePreCreditParamWithoutStatus(PreCreditParam preCreditParam) {
		return getSqlMap().update(NAMESPACES+"updatePreCreditParamWithoutStatus",preCreditParam);
	}

}
