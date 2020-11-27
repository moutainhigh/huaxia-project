package com.huaxia.opas.dao.channel;

import java.util.List;
import java.util.Map;

import com.huaxia.opas.domain.allot.AllotApplyAllot;
import com.huaxia.opas.domain.archive.FileAppDet;
import com.huaxia.opas.domain.decision.InputChannel;
import com.huaxia.opas.domain.input.BizInpApplC1;
import com.huaxia.opas.domain.input.BizInpApplErr;

public interface InputChannelDao {

	void updateInputChannel(Map<String, Object> dataMap);

	List<InputChannel> queryInputChannel(Map<String, Object> map);

	void saveInputChannel(InputChannel inputChannel);

	BizInpApplC1 queryErrorCode(String appId);

	void updateErrorCode(BizInpApplC1 bizInpApplC1);

	FileAppDet queryFileApplicationDetail(String appId);

	String queryOpas_file_success(String appId);

	String queryOpas_file_failed(String appId);

	void updateEveryTable(Map<String, Object> map);

	int searchEveryTableCount(Map<String, Object> map);

	List<Map<String, Object>> searchEveryTable(Map<String, Object> map, int curNum, int pageNum);

	AllotApplyAllot queryAllotInfo(String appId);

	void updateAllotInfo(AllotApplyAllot allotApply);

	AllotApplyAllot queryYsAllotInfo(String appId);

	void updateYsAllotInfo(AllotApplyAllot allotApply);

	String  queryBreakFqz();

	void updateBreakFqz(Map<String, Object> paramMap);

	int queryRepeatDataAppidCountbzk(Map<String, String> paramMap);

	List<Map<String, Object>> queryRepeatDataAppid(Map<String, String> paramMap, int curNum, int pageNum);
	
	List<Map<String, Object>> queryRepeatDataNoAppid(Map<String, String> paramMap, int curNum, int pageNum);
	
	void updateAppid4bzk(Map<String, Object> paramMap);

	void updateAppid4ydj(Map<String, Object> paramMap);

	int queryRepeatDataAppidCountydj(Map<String, String> paramMap);

	List<Map<String, Object>> queryOpasBizInpApplErr(
			Map<String, String> paramMap, int curNum, int pageNum);

	int queryOpasBizInpApplErrCount(BizInpApplErr bizInpApplErr);

	List<BizInpApplErr> queryOpasBizInpApplErrList(BizInpApplErr bizInpApplErr,
			int curNum, int pageNum);
	
	List<Map<String, Object>> queryTaskFicoFraudAppid(Map<String, String> paramMap, int curNum, int pageNum);

	Map<String, Object> queryTopbpmExpMessageDetail(Map<String, String> paramMap);

	void insertTaskFicoFraudAppid(Map<String, String> paramMap);

	void updatefqzficoSingleOrBatch(Map<String, String> paramMap);

	int queryTaskFicoFraudAppidCount(Map<String, String> paramMap);

}
