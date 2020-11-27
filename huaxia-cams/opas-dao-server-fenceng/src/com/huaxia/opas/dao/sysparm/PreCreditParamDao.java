package com.huaxia.opas.dao.sysparm;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.sysparm.BatchfileInfo;
import com.huaxia.opas.domain.sysparm.PreCreditParam;
/**
 * 
 * @author 汪涛
 *
 */
public interface PreCreditParamDao {
	//预授信信息列表显示查询
	public List<PreCreditParam> queryPreCreditParamList(PreCreditParam preCreditParam, int curNum, int pageNum);

	public int queryPreCreditParamCount(PreCreditParam preCreditParam);
	//预授信信息修改
	public int updatePreCreditParam(PreCreditParam preCreditParam) ;

	//	public int deletePreCreditParam(String autoID);
	//新增时，设置状态为启用
	public int insertPreCreditParamStart(PreCreditParam preCreditParam);
	//新增时，设置状态为禁用
	public int insertPreCreditParamEnd(PreCreditParam preCreditParam);
	//批量启用预授信信息
	public int batchStartPreCreditParam(List<Map<Object,Object>> preCreditParamMaps);
	//批量禁用预授信信息
	public int batchStopPreCreditParam(List<Map<Object,Object>> preCreditParamMaps);
	//批量删除预授信信息
	public int batchDeletePreCreditParam(List<String> autoIds);
	//批量导入
	public int insertPreCreditParamImportFile(List<PreCreditParam> preCreditParamList);
	//点击查看超链接，显示预授信信息的历史纪录
	public int queryPreCreditParamHistoryCount(String autoID);

	public List<PreCreditParam> queryPreCreditParamHistoryList(String autoID, int curNum, int pageNum);
	//预授信信息历史纪录的添加（对应修改）
	public int insertPreCreditParamHis(PreCreditParam preCreditParam);
	//批量启用和禁用添加历史记录
	public int insertPreCreditParamHisOfBatch(List<Map<Object, Object>> preCreditParamMaps);
	//导入成功插入预授信历史信息表
	public int insertCredit(BatchfileInfo batchfileinfo);

	public PreCreditParam selectById(String autoID);
	
	//预授信的批量导入的记录列表展示
	public int queryPreCreditParamUploadCount(BatchfileInfo batchfileInfo);

	public List<BatchfileInfo> queryPreCreditParamUpload(BatchfileInfo batchfileInfo, int curNum, int pageNum);
	//查询预授信的修改前的状态
	public String queryPreCreditParamStatus(String autoID);
	//修改预授信信息（不修改状态）
	public int updatePreCreditParamWithoutStatus(PreCreditParam preCreditParam);

	List<PreCreditParam> queryPreCreditParamListByIdno(String appId, int curNum, int pageNum);
	
	List<PreCreditParam> queryPreCreditParamListByIdno(String appId);

	int queryPreCreditParamListByIdnoCount(String appId);


}
