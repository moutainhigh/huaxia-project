package com.huaxia.opas.dao.rule;

import java.util.List;
import java.util.Map;

public interface OpasPbocLoanCardInfoDao {
	public List<Map<String,String>> selectloanOrCreditMsgByAppId(String appId);
	
	public List<Map<String,String>> selectDebitCardMsgByAppId(String appId);
	
	int selectDebitMsgCount(String appId);
	
	public List<Double> selectLateOverdueCountByAppId(String appId);
	
	public List<Double>  selectLateOverdueAmtRateByAppId(String appId);

	public Double selectOverdueCountByAppId(String appId);

}
