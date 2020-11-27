package com.huaxia.opas.dao.channel.impl;

import java.util.List;
import java.util.Map;

import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.channel.InputChannelDao;
import com.huaxia.opas.domain.allot.AllotApplyAllot;
import com.huaxia.opas.domain.archive.FileAppDet;
import com.huaxia.opas.domain.decision.InputChannel;
import com.huaxia.opas.domain.input.BizInpApplC1;
import com.huaxia.opas.domain.input.BizInpApplErr;

public class InputChannelDaoImpl extends AbstractDAO implements InputChannelDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = -351553558914256250L;

	private static final String NAMESPACES = "InputChannel.";
	
	@Override
	public void updateInputChannel(Map<String, Object> dataMap) {
		getSqlMap().update(NAMESPACES + "updateInputChannel", dataMap);
	}

	@Override
	public List<InputChannel> queryInputChannel(Map<String, Object> map) {
		return getSqlMap().queryForList(NAMESPACES+"queryInputChannel",map);
	}

	@Override
	public void saveInputChannel(InputChannel inputChannel) {
		getSqlMap().update(NAMESPACES + "updateInputChannel", inputChannel);
	}
	
	@Override
	public BizInpApplC1 queryErrorCode(String appId) {
		return getSqlMap().queryForObject(NAMESPACES + "selectErrorCode", appId);
	}

	@Override
	public void updateErrorCode(BizInpApplC1 bizInpApplC1) {
		getSqlMap().update(NAMESPACES + "updateErrorCode", bizInpApplC1);
	}

	@Override
	public FileAppDet queryFileApplicationDetail(String appId) {
		return getSqlMap().queryForObject(NAMESPACES + "queryFileApplicationDetail", appId);
	}

	@Override
	public String queryOpas_file_success(String appId) {
		return getSqlMap().queryForObject(NAMESPACES + "queryOpas_file_success", appId);
	}

	@Override
	public String queryOpas_file_failed(String appId) {
		return getSqlMap().queryForObject(NAMESPACES + "queryOpas_file_failed", appId);
	}

	@Override
	public void updateEveryTable(Map<String, Object> map) {
		getSqlMap().update(NAMESPACES + "updateEveryTable", map);
	}

	@Override
	public int searchEveryTableCount(Map<String, Object> map) {
		return getSqlMap().queryForObject(NAMESPACES + "searchEveryTableCount", map);
	}

	@Override
	public List<Map<String, Object>> searchEveryTable(Map<String, Object> map, int curNum, int pageNum) {
		return getSqlMap().queryForList(NAMESPACES + "searchEveryTable", map, curNum, pageNum);
	}

	@Override
	public AllotApplyAllot queryAllotInfo(String appId) {
		return getSqlMap().queryForObject(NAMESPACES + "queryAllotInfo", appId);
	}

	@Override
	public void updateAllotInfo(AllotApplyAllot allotApply) {
		getSqlMap().update(NAMESPACES + "updateAllotInfo", allotApply);
	}

	@Override
	public AllotApplyAllot queryYsAllotInfo(String appId) {
		return getSqlMap().queryForObject(NAMESPACES + "queryYsAllotInfo", appId);
	}

	@Override
	public void updateYsAllotInfo(AllotApplyAllot allotApply) {
		getSqlMap().update(NAMESPACES + "updateYsAllotInfo", allotApply);
	}

	@Override
	public String queryBreakFqz() {
		return getSqlMap().queryForObject(NAMESPACES + "queryBreakFqz") ;
	}

	@Override
	public void updateBreakFqz(Map<String, Object> mp) {
		getSqlMap().update(NAMESPACES + "updateBreakFqz", mp);
		
	}

	@Override
	public int queryRepeatDataAppidCountbzk(Map<String, String> paramMap) {
		
		return getSqlMap().queryForObject(NAMESPACES + "queryRepeatDataAppidCountbzk",paramMap);
	}
	@Override
	public int queryRepeatDataAppidCountydj(Map<String, String> paramMap) {
		
		return getSqlMap().queryForObject(NAMESPACES + "queryRepeatDataAppidCountydj",paramMap);
	}

	@Override
	public List<Map<String, Object>> queryRepeatDataAppid(Map<String, String> paramMap, int curNum, int pageNum) {
		
		return getSqlMap().queryForList(NAMESPACES + "queryRepeatDataAppid",paramMap,curNum,pageNum);

	}
	
	@Override
	public List<Map<String, Object>> queryRepeatDataNoAppid(Map<String, String> paramMap, int curNum, int pageNum) {
		
		return getSqlMap().queryForList(NAMESPACES + "queryRepeatDataNoAppid",paramMap,curNum,pageNum);

	}
	

	@Override
	public void updateAppid4bzk(Map<String, Object> mp) {
		getSqlMap().update(NAMESPACES + "updateAppidbzk", mp);
		
	}
	
	@Override
	public void updateAppid4ydj(Map<String, Object> mp) {
		getSqlMap().update(NAMESPACES + "updateAppidydj", mp);
		
	}

	@Override
	public List<Map<String, Object>> queryOpasBizInpApplErr(
			Map<String, String> paramMap, int curNum, int pageNum) {
		return getSqlMap().queryForList(NAMESPACES + "queryOpasBizInpApplErr",paramMap,curNum,pageNum);
	}

	@Override
	public int queryOpasBizInpApplErrCount(BizInpApplErr bizInpApplErr) {
		return getSqlMap().queryForObject(NAMESPACES + "queryOpasBizInpApplErrCount",bizInpApplErr);
	}

	@Override
	public List<BizInpApplErr> queryOpasBizInpApplErrList(
			BizInpApplErr bizInpApplErr, int curNum, int pageNum) {
		return getSqlMap().queryForList(NAMESPACES + "queryOpasBizInpApplErrList",bizInpApplErr,curNum,pageNum);
	}
	
	@Override
	public List<Map<String, Object>> queryTaskFicoFraudAppid(Map<String, String> paramMap, int curNum, int pageNum) {
		return getSqlMap().queryForList(NAMESPACES + "queryTaskFicoFraudAppid",paramMap,curNum,pageNum);

	}

	@Override
	public Map<String, Object> queryTopbpmExpMessageDetail(Map<String, String> paramMap) {
		return getSqlMap().queryForObject(NAMESPACES + "queryTopbpmExpMessageDetail",paramMap);
	}

	@Override
	public void insertTaskFicoFraudAppid(Map<String, String> paramMap) {
		 getSqlMap().insert(NAMESPACES + "insertTaskFicoFraudAppid",paramMap);
	}

	@Override
	public void updatefqzficoSingleOrBatch(Map<String, String> paramMap) {
		getSqlMap().update(NAMESPACES + "updatefqzficoSingleOrBatch", paramMap);
		
	}

	@Override
	public int queryTaskFicoFraudAppidCount(Map<String, String> paramMap) {
		return getSqlMap().queryForObject(NAMESPACES + "queryTaskFicoFraudAppidCount",paramMap);
	}
}
