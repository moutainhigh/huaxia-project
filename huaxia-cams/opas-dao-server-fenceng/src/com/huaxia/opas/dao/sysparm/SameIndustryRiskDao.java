package com.huaxia.opas.dao.sysparm;

import java.util.List;
import java.util.Map;

import com.huaxia.opas.domain.dict.ApDictDetail;
import com.huaxia.opas.domain.sysparm.SameIndustryRisk;
/**
 * 同业关注风险名单库
 * @author 汪涛
 *
 */
public interface SameIndustryRiskDao {

	int querySameIndustryRiskCount(SameIndustryRisk sameIndustryRisk);
	/**
	 * 同业关注风险名单的列表显示
	 */
	List<SameIndustryRisk> querySameIndustryRiskList(SameIndustryRisk sameIndustryRisk, int curNum, int pageNum);
	/**
	 * 同业关注风险名单的新增
	 */
	int insertSameIndustryRisk(SameIndustryRisk sameIndustryRisk);
	/**
	 * 同业关注风险名单的修改
	 */
	int updateSameIndustryRisk(SameIndustryRisk sameIndustryRisk);
	/**
	 * 同业关注风险名单的删除
	 */
	int deleteSameIndustryRisk(String auto_id);
	/**
	 * 同业关注风险名单的批量启用
	 */
	int batchStartSameIndustryRisk(List<Map<Object, Object>> sameIndustryRiskMaps);
	/**
	 * 同业关注风险名单的批量禁用
	 */
	int batchStopSameIndustryRisk(List<Map<Object, Object>> sameIndustryRiskMaps);
	/**
	 * 同业关注风险名单的批量删除
	 */
	int batchDeleteSameIndustryRisk(List<String> autoIds);
	/**
	 * 同业关注风险名单新增一条历史纪录（对应新增/修改）
	 */
	int insertSameIndustryRiskHistory(SameIndustryRisk sameIndustryRisk);
	/**
	 * 同业关注风险名单的历史纪录列表显示
	 */
	List<SameIndustryRisk> querySameIndustryRiskHistoryList(String auto_id, int curNum, int pageNum);

	int querySameIndustryRiskHistoryCount(String auto_id);
	/**
	 * 同业关注风险名单的批量导入
	 */
	int insertSIRiskImportFile(List<SameIndustryRisk> sameIndustryRiskList);
	/**
	 * 导入成功后新增风险名单库历史纪录，显示导入
	 */
	int insertSIRiskImportFileHis(List<SameIndustryRisk> sameIndustryRiskList);
	/**
	 * 下面这两个方法都是动态维护同业关注风险名单库的字段“证件类型”
	 */
	String queryApDictForDictCode(String dictName);

	List<ApDictDetail> queryApDictDetailForCertifiType(String dictCode);
	
	/**
	 * 批量启用禁用对应的添加历史记录
	 */
	int insertSameIndustryRiskHisOfBatch(List<Map<Object, Object>> sameIndustryRiskMaps);
	/**
	 * 
	 * @param autoId
	 * @return SameIndustryRisk
	 * 通过autoid获取实体
	 * update by  liuzhihui 
	 * 2017-4-6 20:02:27
	 */
	SameIndustryRisk selectByPrimaryKey(String autoId);
}
