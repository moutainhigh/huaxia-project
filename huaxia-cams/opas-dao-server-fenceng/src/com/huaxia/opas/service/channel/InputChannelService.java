package com.huaxia.opas.service.channel;

import java.util.List;
import java.util.Map;

import com.huaxia.opas.domain.allot.AllotApplyAllot;
import com.huaxia.opas.domain.decision.InputChannel;
import com.huaxia.opas.domain.input.BizInpApplC1;
import com.huaxia.opas.domain.input.BizInpApplErr;

public interface InputChannelService {

	void updateInputChannel(Map<String, Object> dataMap);

	List<InputChannel> queryInputChannel(Map<String, Object> map);

	BizInpApplC1 queryErrorCode(Map<String, Object> map);

	void updateErrorCode(Map<String, Object> map);

	String querySendCard(Map<String, Object> map);

	void updateEveryTable(Map<String, Object> map);

	int searchEveryTableCount(Map<String, Object> map);

	List<Map<String, Object>> searchEveryTable(Map<String, Object> map, int curNum, int pageNum);

	AllotApplyAllot queryAllotInfo(Map<String, Object> map);

	void updateAllotInfo(Map<String, Object> map);

	AllotApplyAllot queryYsAllotInfo(Map<String, Object> map);

	void updateYsAllotInfo(Map<String, Object> map);

	String queryBreakFqz();

	void updateBreakFqz(String turn);

	Map<String, Object> queryRepeatDataAppid(Map<String, String> paramMap, int curNum, int pageNum);

	void updateAppid(Map<String, Object> mp);

	int queryOpasBizInpApplErrCount(BizInpApplErr bizInpApplErr);

	List<BizInpApplErr> queryOpasBizInpApplErrList(BizInpApplErr bizInpApplErr,
			int curNum, int pageNum);
	
	Map<String, Object> queryTaskFicoFraudAppid(Map<String, String> paramMap, int curNum, int pageNum);

	Map<String, Object> queryTopbpmExpMessageDetail(Map<String, String> paramMap);

	void insertTaskFicoFraudAppid(Map<String, String> paramMap);

	void updatefqzficoSingleOrBatch(Map<String, String> paramMap);
	int queryTaskFicoFraudAppidCount(Map<String, String> paramMap);
}
