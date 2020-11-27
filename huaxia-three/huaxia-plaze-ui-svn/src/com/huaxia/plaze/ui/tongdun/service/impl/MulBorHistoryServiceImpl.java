package com.huaxia.plaze.ui.tongdun.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.huaxia.plaze.common.PageProperty;
import com.huaxia.plaze.ui.tongdun.domain.MulBorAttentionList;
import com.huaxia.plaze.ui.tongdun.domain.MulBorBase;
import com.huaxia.plaze.ui.tongdun.domain.MulBorDiscreditCountMain;
import com.huaxia.plaze.ui.tongdun.domain.MulBorInfo;
import com.huaxia.plaze.ui.tongdun.domain.MulBorRiskList;
import com.huaxia.plaze.ui.tongdun.mapper.MulBorHistoryMapper;
import com.huaxia.plaze.ui.tongdun.service.MulBorHistoryService;

@Service("mulBorHistoryService")
public class MulBorHistoryServiceImpl implements MulBorHistoryService{
	
	@Resource
	private MulBorHistoryMapper mulBorHistoryQueryMapper;
	
	@Override
	public List<MulBorBase> selectListByPage(PageProperty page) {
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("pageNo", page.getPageNo());
		args.put("pageSize", page.getPageSize());
		args.putAll(page.getDataMap());

		return mulBorHistoryQueryMapper.selectListByPage(args);
	}

	@Override
	public MulBorInfo queryMulbor(String trnId) {
		MulBorInfo  mulBorInfo = new MulBorInfo();
		
		mulBorInfo.setMulBorBase(mulBorHistoryQueryMapper.queryMulBorBase(trnId));
		mulBorInfo.setMulBorAntifraudIndex(mulBorHistoryQueryMapper.queryMulBorAntifraudInde(trnId));
		mulBorInfo.setMulBorRiskItemList(mulBorHistoryQueryMapper.queryMulBorRiskItem(trnId));
		mulBorInfo.setMulBorBlackListList(mulBorHistoryQueryMapper.queryMulBorBlackList(trnId));
		mulBorInfo.setMulBorDescreditCountList(mulBorHistoryQueryMapper.queryMulBorDescreditCount(trnId));
		mulBorInfo.setMulBorGreyListList(mulBorHistoryQueryMapper.queryMulBorGreyList(trnId));
		
		return mulBorInfo;
	}

	@Override
	public String queryName(String trnId) {
		// TODO Auto-generated method stub
		return  mulBorHistoryQueryMapper.queryName(trnId);
	}

	@Override
	public List<MulBorRiskList> queryMulBorRiskList(String trnId) {
		// TODO Auto-generated method stub
		return mulBorHistoryQueryMapper.queryMulBorRiskList(trnId);
	}

	@Override
	public List<MulBorDiscreditCountMain> queryMulBorDiscreditCountMain(String trnId) {
		// TODO Auto-generated method stub
		return mulBorHistoryQueryMapper.queryMulBorDiscreditCountMain(trnId);
	}

	@Override
	public List<MulBorAttentionList> queryMulBorAttentionList(String trnId) {
		// TODO Auto-generated method stub
		return mulBorHistoryQueryMapper.queryMulBorAttentionList(trnId);
	}
	
}
	