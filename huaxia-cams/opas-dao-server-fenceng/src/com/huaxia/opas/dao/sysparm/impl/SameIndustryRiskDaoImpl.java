package com.huaxia.opas.dao.sysparm.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.sysparm.SameIndustryRiskDao;
import com.huaxia.opas.domain.dict.ApDictDetail;
import com.huaxia.opas.domain.sysparm.SameIndustryRisk;

/**
 * 同业关注风险名单
 * 
 * @author 汪涛
 *
 */
public class SameIndustryRiskDaoImpl extends AbstractDAO implements SameIndustryRiskDao {

	private static final long serialVersionUID = -6002613898537857165L;
	private static final String NAMESPACES = "SameIndustryRisk.";

	@Override
	public int querySameIndustryRiskCount(SameIndustryRisk sameIndustryRisk) {
		return getSqlMap().queryForObject(NAMESPACES + "querySameIndustryRiskCount", sameIndustryRisk);
	}

	/**
	 * 同业关注风险名单的列表显示
	 */
	@Override
	public List<SameIndustryRisk> querySameIndustryRiskList(SameIndustryRisk sameIndustryRisk, int curNum,
			int pageNum) {
		List<SameIndustryRisk> list = new ArrayList<SameIndustryRisk>();
		list = getSqlMap().queryForList(NAMESPACES + "querySameIndustryRiskList", sameIndustryRisk, curNum, pageNum);
		return list;
	}

	/**
	 * 同业关注风险名单的新增
	 */
	@Override
	public int insertSameIndustryRisk(SameIndustryRisk sameIndustryRisk) {
		return getSqlMap().insert(NAMESPACES + "insertSameIndustryRisk", sameIndustryRisk);
	}

	/**
	 * 同业关注风险名单的修改
	 */
	@Override
	public int updateSameIndustryRisk(SameIndustryRisk sameIndustryRisk) {
		return getSqlMap().update(NAMESPACES + "updateSameIndustryRisk", sameIndustryRisk);
	}

	/**
	 * 同业关注风险名单的删除
	 */
	@Override
	public int deleteSameIndustryRisk(String auto_id) {
		return getSqlMap().delete(NAMESPACES + "deleteSameIndustryRisk", auto_id);
	}

	/**
	 * 同业关注风险名单的批量启用
	 */
	@Override
	public int batchStartSameIndustryRisk(List<Map<Object, Object>> sameIndustryRiskMaps) {
		return getSqlMap().update(NAMESPACES + "batchStartSameIndustryRisk", sameIndustryRiskMaps);
	}

	/**
	 * 同业关注风险名单的批量禁用
	 */
	@Override
	public int batchStopSameIndustryRisk(List<Map<Object, Object>> sameIndustryRiskMaps) {
		return getSqlMap().update(NAMESPACES + "batchStartSameIndustryRisk", sameIndustryRiskMaps);
	}

	/**
	 * 同业关注风险名单的批量删除
	 */
	@Override
	public int batchDeleteSameIndustryRisk(List<String> autoIds) {
		return getSqlMap().delete(NAMESPACES + "batchDeleteSameIndustryRisk", autoIds);
	}

	/**
	 * 同业关注风险名单的新增一条历史纪录（新增和修改）
	 */
	@Override
	public int insertSameIndustryRiskHistory(SameIndustryRisk sameIndustryRisk) {
		return getSqlMap().insert(NAMESPACES + "insertSameIndustryRiskHistory", sameIndustryRisk);
	}

	/**
	 * 同业关注风险名单的历史纪录列表显示
	 */
	@Override
	public List<SameIndustryRisk> querySameIndustryRiskHistoryList(String auto_id, int curNum, int pageNum) {
		List<SameIndustryRisk> list = new ArrayList<SameIndustryRisk>();
		list = getSqlMap().queryForList(NAMESPACES + "querySameIndustryRiskHistoryList", auto_id, curNum, pageNum);
		return list;
	}

	@Override
	public int querySameIndustryRiskHistoryCount(String auto_id) {
		return getSqlMap().queryForObject(NAMESPACES + "querySameIndustryRiskHistoryCount", auto_id);
	}

	/**
	 * 同业关注风险名单的批量导入
	 */
	@Override
	public int insertSIRiskImportFile(List<SameIndustryRisk> sameIndustryRiskList) {
		return getSqlMap().insert(NAMESPACES + "insertSIRiskImportFile", sameIndustryRiskList);
	}

	/**
	 * 同业关注风险名单的导入成功后新增历史纪录
	 */
	@Override
	public int insertSIRiskImportFileHis(List<SameIndustryRisk> sameIndustryRiskList) {
		return getSqlMap().insert(NAMESPACES + "insertSIRiskImportFileHis", sameIndustryRiskList);
	}

	/**
	 * 下面两个方法是动态维护同业关注风险名单的“证件类型”
	 */
	@Override
	public String queryApDictForDictCode(String dictName) {
		return getSqlMap().queryForObject(NAMESPACES + "queryApDictForDictCode", dictName);
	}

	@Override
	public List<ApDictDetail> queryApDictDetailForCertifiType(String dictCode) {
		List<ApDictDetail> list = new ArrayList<ApDictDetail>();
		list = getSqlMap().queryForList(NAMESPACES + "queryApDictDetailForCertifiType", dictCode);
		return list;
	}

	@Override
	public int insertSameIndustryRiskHisOfBatch(List<Map<Object, Object>> sameIndustryRiskMaps) {
		return getSqlMap().insert(NAMESPACES + "insertSameIndustryRiskHisOfBatch", sameIndustryRiskMaps);
	}

	@Override
	public SameIndustryRisk selectByPrimaryKey(String autoId) {
		return getSqlMap().queryForObject(NAMESPACES + "querySameIndustry", autoId);
	}

}
