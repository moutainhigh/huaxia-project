package com.huaxia.opas.service.sysparm;

import java.io.File;
import java.util.List;

import com.huateng.neofp.core.Context;
import com.huaxia.opas.domain.sysparm.BatchfileInfo;
import com.huaxia.opas.domain.sysparm.SameIndustryRisk;
/**
 * 
 * @author 汪涛
 *
 */
public interface SameIndustryRiskService {
	/**
	 *同业关注风险名单的查询列表显示
	 */
	int querySameIndustryRiskCount(SameIndustryRisk sameIndustryRisk);

	List<SameIndustryRisk> querySameIndustryRiskList(SameIndustryRisk sameIndustryRisk, int curNum, int pageNum);
	/**
	 * 同业关注风险名单的新增
	 * @throws Exception 
	 */
	int insertSameIndustryRisk(SameIndustryRisk sameIndustryRisk) throws Exception;
	/**
	 * 同业关注风险名单的修改
	 * @throws Exception 
	 */
	int updateSameIndustryRisk(SameIndustryRisk sameIndustryRisk) throws Exception;
	/**
	 * 同业关注风险名单的删除
	 */
	int deleteSameIndustryRisk(String auto_id);
	/**
	 * 同业关注风险名单的批量启用 
	 */
	void batchStartSameIndustryRisk(Context ctx);
	/**
	 * 同业关注风险名单的批量禁用
	 */
	void batchStopSameIndustryRisk(Context ctx);
	/**
	 * 同业关注风险名单的批量删除
	 */
	void batchDeleteSameIndustryRisk(Context ctx);
	/**
	 * 同业关注风险名单历史纪录的新增
	 */
	int insertSameIndustryRiskHistory(SameIndustryRisk sameIndustryRisk);
	/**
	 * 同业关注风险名单历史纪录列表显示
	 */
	List<SameIndustryRisk> querySameIndustryRiskHistoryList(String auto_id,int curNum,int pageNum);

	int querySameIndustryRiskHistoryCount(String auto_id);
	/**
	 * 同业关注风险名单批量导入
	 */
	int insertSIRiskImportFile(List<SameIndustryRisk> sameIndustryRiskList,BatchfileInfo batchfileInfo) throws Exception;

}
