package com.huaxia.opas.service.channel.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.alibaba.fastjson.JSON;
import com.huaxia.opas.dao.channel.InputChannelDao;
import com.huaxia.opas.domain.allot.AllotApplyAllot;
import com.huaxia.opas.domain.archive.FileAppDet;
import com.huaxia.opas.domain.decision.InputChannel;
import com.huaxia.opas.domain.input.BizInpApplC1;
import com.huaxia.opas.domain.input.BizInpApplErr;
import com.huaxia.opas.service.channel.InputChannelService;

public class InputChannelServiceImpl implements InputChannelService {
	@Resource(name="inputChannelDao")
	private InputChannelDao inputChannelDao;

	@Override
	public void updateInputChannel(Map<String, Object> dataMap) {
		/*for (Map.Entry<String, Object> entry: dataMap.entrySet()){
			InputChannel inputChannel = new InputChannel();
			inputChannel.setInputChannel(entry.getKey());
			inputChannel.setTime1((String) entry.getValue());
			inputChannelDao.saveInputChannel(inputChannel);
		}*/
		InputChannel inputChannel = new InputChannel();
		inputChannel.setInputChannel((String)dataMap.get("inputChannel"));
		inputChannel.setTime1((String)dataMap.get("time1"));
		inputChannel.setTurn((String)dataMap.get("turn"));
		inputChannelDao.saveInputChannel(inputChannel);
	}


	@Override
	public List<InputChannel> queryInputChannel(Map<String, Object> map) {
		return inputChannelDao.queryInputChannel(map);
	}


	@Override
	public BizInpApplC1 queryErrorCode(Map<String, Object> map) {
		String appId= (String) map.get("appId");
		return inputChannelDao.queryErrorCode(appId);
	}


	@Override
	public void updateErrorCode(Map<String, Object> map) {
		String string = JSON.toJSONString(map);
		BizInpApplC1 bizInpApplC1 = JSON.parseObject(string, BizInpApplC1.class);
		inputChannelDao.updateErrorCode(bizInpApplC1);
	}


	/**
	 * --步骤
		opas_file_application_detail 中 flag = '2'  归档可以送发卡的前提条件
		--1.快速发卡成功条件
		opas_file_application_detail 中 interface_4093_invoke_flag = ‘1’ 表示调用 开卡联机接口成功
		--2.批量I02送发卡处理成功条件
		opas_file_application_detail  中 already_send_flag  ='2'  表示已经送发卡文件  
		--3.I02返回结果件处理
		opas_file_application_detail 中  back_flag is null  则表示还未处理结果件 ；= '1’  送发卡成功 
		 则在opas_file_success 中找明细； =‘0’ 送发卡失败  则在opas_file_failed 找明细
		--注：先用快速发卡条件筛选，若没有再用批量条件筛选；若这两种情况都没能找到申请件，则表示该件归档后还未送发卡
	 */
	@Override
	public String querySendCard(Map<String, Object> map) {
		String result = "";
		String appId= (String) map.get("appId");
		//查询归档表opas_file_application_detail
		FileAppDet fileAppDet =  inputChannelDao.queryFileApplicationDetail(appId);
		//flag='2'  归档可以送发卡的前提条件
		
		if(fileAppDet!=null &&"2".equals(fileAppDet.getFlag())&&fileAppDet.getFlag()!=null){
			if("1".equals(fileAppDet.getInterface4093InvokeFlag())){
				result = "快速件发卡成功";
			} else {
				if("2".equals(fileAppDet.getAlreadySendFlag())){
					result = "批量件送发卡成功";
				} else {
					if("1".equals(fileAppDet.getBackFlag())){
						//成功--原因
						result = inputChannelDao.queryOpas_file_success(appId);
					} else {
						//失败--原因
						result = inputChannelDao.queryOpas_file_failed(appId);
					}
				}
			}
		} else {
			result = "此申请件尚未送发卡";
		}
		
		return result;
	}


	@Override
	public void updateEveryTable(Map<String, Object> map) {
		inputChannelDao.updateEveryTable(map);
	}


	@Override
	public int searchEveryTableCount(Map<String, Object> map) {
		return inputChannelDao.searchEveryTableCount(map);
	}


	@Override
	public List<Map<String, Object>> searchEveryTable(Map<String, Object> map, int curNum, int pageNum) {
		return inputChannelDao.searchEveryTable(map,curNum,pageNum);
	}


	@Override
	public AllotApplyAllot queryAllotInfo(Map<String, Object> map) {
		String appId= (String) map.get("appId");
		return inputChannelDao.queryAllotInfo(appId);
	}


	@Override
	public void updateAllotInfo(Map<String, Object> map) {
		String string = JSON.toJSONString(map);
		AllotApplyAllot allotApply = JSON.parseObject(string, AllotApplyAllot.class);
		inputChannelDao.updateAllotInfo(allotApply);
	}


	@Override
	public AllotApplyAllot queryYsAllotInfo(Map<String, Object> map) {
		String appId= (String) map.get("appId");
		return inputChannelDao.queryYsAllotInfo(appId);
	}


	@Override
	public void updateYsAllotInfo(Map<String, Object> map) {
		String string = JSON.toJSONString(map);
		AllotApplyAllot allotApply = JSON.parseObject(string, AllotApplyAllot.class);
		inputChannelDao.updateYsAllotInfo(allotApply);
	}


	@Override
	public String queryBreakFqz() {
		return inputChannelDao.queryBreakFqz();
	}


	@Override
	public void updateBreakFqz(String turn) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("app_id", turn);
		inputChannelDao.updateBreakFqz(paramMap);
		
	}


	@Override
	public Map<String, Object> queryRepeatDataAppid(Map<String, String> paramMap, int curNum, int pageNum) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> rows = new ArrayList<Map<String, Object>>();
		String appid = (String)paramMap.get("queryAppid");
		if(appid != null && (!"".equals(appid))){
		//int total1 = inputChannelDao.queryRepeatDataAppidCountbzk(paramMap);
		//int total2 = inputChannelDao.queryRepeatDataAppidCountydj(paramMap);
		//int total = total1+total2;
		//if (total > 0) {
			rows = inputChannelDao.queryRepeatDataAppid(paramMap,curNum,pageNum);
		//}
		}else{
			rows = inputChannelDao.queryRepeatDataNoAppid(paramMap,curNum,pageNum);
		}
		map.put("total", rows.size());
		map.put("rows", rows);
		return map;
		
	}


	@Override
	public void updateAppid(Map<String, Object> mp) {
		inputChannelDao.updateAppid4bzk(mp);
		inputChannelDao.updateAppid4ydj(mp);
	}


	@Override
	public int queryOpasBizInpApplErrCount(BizInpApplErr bizInpApplErr) {
		return inputChannelDao.queryOpasBizInpApplErrCount(bizInpApplErr);
	}


	@Override
	public List<BizInpApplErr> queryOpasBizInpApplErrList(
			BizInpApplErr bizInpApplErr, int curNum, int pageNum) {
		return inputChannelDao.queryOpasBizInpApplErrList(bizInpApplErr,curNum,pageNum);
	}

	@Override
	public Map<String, Object> queryTaskFicoFraudAppid(Map<String, String> paramMap, int curNum, int pageNum) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> rows = new ArrayList<Map<String, Object>>();
		rows = inputChannelDao.queryTaskFicoFraudAppid(paramMap,curNum,pageNum);
		Map <String,String> param = new HashMap<String,String>();
		param.put("appid_", paramMap.get("appid"));
		int total = inputChannelDao.queryTaskFicoFraudAppidCount(param);
		map.put("total", total);
		map.put("rows", rows);
		return map;
	}


	@Override
	public Map<String, Object> queryTopbpmExpMessageDetail(Map<String, String> paramMap) {
		return inputChannelDao.queryTopbpmExpMessageDetail(paramMap);
	}


	@Override
	public void insertTaskFicoFraudAppid(Map<String, String> paramMap) {
		inputChannelDao.insertTaskFicoFraudAppid(paramMap);
		
	}


	@Override
	public void updatefqzficoSingleOrBatch(Map<String, String> paramMap) {
		inputChannelDao.updatefqzficoSingleOrBatch(paramMap);
		
	}


	@Override
	public int queryTaskFicoFraudAppidCount(Map<String, String> paramMap) {
		return inputChannelDao.queryTaskFicoFraudAppidCount(paramMap);
	}
	
}
