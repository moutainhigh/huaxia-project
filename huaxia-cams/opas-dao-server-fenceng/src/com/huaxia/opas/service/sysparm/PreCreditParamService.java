package com.huaxia.opas.service.sysparm;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import com.huaxia.opas.domain.sysparm.BatchfileInfo;
import com.huaxia.opas.domain.sysparm.PreCreditParam;

/**
 * 
 * @author 汪涛
 *
 */
public interface PreCreditParamService {
	int queryPreCreditParamCount(PreCreditParam preCreditParam);

	// 分页显示预授信信息
	List<PreCreditParam> queryPreCreditParamList(PreCreditParam preCreditParam, int curNum, int pageNum);

	// 当新增时，用户设置状态为启用
	int insertPreCreditParamStart(PreCreditParam preCreditParam) throws Exception;

	// 当新增时，用户设置状态为停用
	int insertPreCreditParamEnd(PreCreditParam preCreditParam) throws Exception;

	// 预授信的修改
	int updatePreCreditParam(PreCreditParam preCreditParam) throws Exception;

	// 预授信的删除(这块功能包含到批量删除之内)
	// int deletePreCreditParam(String autoID);
	// 预授信的批量启用
	int batchStartPreCreditParam(List<Map<Object, Object>> preCreditParamMaps) throws ParseException;

	// 预授信的批量禁用
	int batchStopPreCreditParam(List<Map<Object, Object>> preCreditParamMaps) throws ParseException;

	// 预授信的批量删除
	int batchDeletePreCreditParam(List<String> autoIds);

	// 预授信的批量导入
	int insertPreCreditParamImportFile(List<PreCreditParam> preCreditParamList, BatchfileInfo batchfileInfo)
			throws Exception;

	// 预授信信息的历史查询
	int queryPreCreditParamHistoryCount(String autoID);

	List<PreCreditParam> queryPreCreditParamHistoryList(String autoID, int curNum, int pageNum);

	// 导入插入历史信息表
	int insertCredit(BatchfileInfo batchfileInfo);

	// 预授信批量导入列表展示
	int queryPreCreditParamUploadCount(BatchfileInfo batchfileInfo);

	List<BatchfileInfo> queryPreCreditParamUpload(BatchfileInfo batchfileInfo, int curNum, int pageNum);

	// 查询修改前预授信的状态
	String queryPreCreditParamStatus(String autoID);

	// 修改预授信信息（启用状态没变）
	int updatePreCreditParamWithoutStatus(PreCreditParam preCreditParam) throws Exception;
}
