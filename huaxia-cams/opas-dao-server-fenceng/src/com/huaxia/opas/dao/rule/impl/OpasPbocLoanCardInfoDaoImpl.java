package com.huaxia.opas.dao.rule.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.rule.OpasPbocLoanCardInfoDao;

public class OpasPbocLoanCardInfoDaoImpl extends AbstractDAO implements OpasPbocLoanCardInfoDao ,Serializable{

	@Override
	public List<Map<String,String>> selectloanOrCreditMsgByAppId(String appId) {
		return getSqlMap().queryForList("OpasPbocLoanCardInfo.selectloanOrCreditMsgByAppId", appId);
	}

	@Override
	public List<Map<String, String>> selectDebitCardMsgByAppId(String appId) {
		return getSqlMap().queryForList("OpasPbocLoanCardInfo.selectDebitCardMsgByAppId", appId);
	}
	
	// 人行账户有无贷款、贷记卡、准贷记卡信息
	@Override
	public int selectDebitMsgCount(String appId) {
		return getSqlMap().queryForObject("OpasPbocLoanCardInfo.selectDebitMsgCount", appId);
	}
	
	//查询人行最新的贷记卡中最大的当前逾期金额   add by yuanquan  
	@Override
	public List<Double> selectLateOverdueCountByAppId(String appId) {
		return getSqlMap().queryForList("OpasPbocLoanCardInfo.selectLateOverdueCountByAppId", appId);
	}
	//查询人行最新的贷记卡中最大的额度使用率   add by yuanquan  
	@Override
	public List<Double> selectLateOverdueAmtRateByAppId(String appId) {
		return getSqlMap().queryForList("OpasPbocLoanCardInfo.selectLateOverdueAmtRateByAppId", appId);
	}

	//查询人行所有贷记卡当前逾期的逾期金额
	@Override
	public Double selectOverdueCountByAppId(String appId) {
		return getSqlMap().queryForObject("OpasPbocLoanCardInfo.selectOverdueCountByAppId", appId);
	}
}
