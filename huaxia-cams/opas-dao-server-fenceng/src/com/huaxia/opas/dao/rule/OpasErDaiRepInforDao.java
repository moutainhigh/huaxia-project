package com.huaxia.opas.dao.rule;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface OpasErDaiRepInforDao {
	/**
	 * 贷款、贷记卡历史逾期 | 人行信用报告是否有一贷款/贷记卡账户状态异常 
	 * | 是否有不良表现 | 申请人所持全部贷记卡账户状态是否存在异常
	 * @param appId
	 * @return
	 */
	List<Map<String,String>> selectCreditAndLoan(String appId);
	
	/**
	 * 人行 近一个月查询次数 
	 * @param appId
	 * @return
	 */
	int selectCheckNum(String appId);
	
	/**
	 * 对外担保提示
	 * @param appId
	 * @return
	 */
	List<Map<String,String>> selectAssureHint(String appId);
	
	/**
	 * 首张贷记卡发卡距今月份  | 首次发放贷款帐龄
	 * @param appId
	 * @return
	 */
	List<Map<String,String>> selectMonthNum(String appId);
	
	/**
	 * 贷款和贷记卡逾期金额是否大于0
	 * @param appId
	 * @return
	 */
	List<Map<String, Object>> selectHaveLoanSum(String appId);
	
	/**
	 * 所有贷记卡状态是否有一张6个月以上  | 最新发卡贷记卡卡龄  | 最新发放贷款帐龄
	 * @param appId
	 * @return
	 */
	List<Map<String,String>> selectHave6MonCard(String appId);
	
	/**
	 * 人行是否存在信贷记录  | 人行有无贷记卡 | 人行有无贷款记录
	 * @param appId
	 * @return
	 */
	List<Map<String,String>> selectIsLoanCard(String appId);
	
	/**
	 * 最新一张贷记卡额度使用率 | 最近一张贷记卡当前逾期金额
	 * @param appId
	 * @return
	 */
	List<Map<String,BigDecimal>> selectDCNM(String appId);
	
	/**
	 * 人行当前所有信贷是否有金额逾期
	 * @param appId
	 * @return
	 */
	String selectOverdueStatu(String appId);
	
}
