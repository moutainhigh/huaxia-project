package com.huaxia.opas.cache.service.rule.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.huateng.huaxia.xom.FicoGradeInfo;
import com.huateng.huaxia.xom.LoansInfo;
import com.huateng.huaxia.xom.QueryThdSide;
import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.cache.common.CacheConsts;
import com.huaxia.opas.cache.service.rule.PlazeRuleModelBuildService;
import com.huaxia.opas.dao.rule.OpasFicoMsgDao;
import com.huaxia.opas.dao.rule.OpasFqzResultDao;
import com.huaxia.opas.dao.rule.OpasLoansMsgDao;
import com.huaxia.opas.dao.rule.OpasThdCarMsgDao;
import com.huaxia.opas.dao.rule.OpasThdMsgDao;
import com.huaxia.opas.dao.rule.Opasbizinpapplc1Dao;
import com.huaxia.opas.domain.rule.Opasbizinpapplc1;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;

public class PlazeRuleModelBuildServiceImpl implements PlazeRuleModelBuildService {
	private static final Logger log = Logger.getLogger(PlazeRuleModelBuildServiceImpl.class);
	
	@Resource(name = "opasbizinpapplc1Daox")
	private Opasbizinpapplc1Dao opasbizinpapplc1Daox;

	@Resource(name = "opasThdCarMsgDaox")
	private OpasThdCarMsgDao opasThdCarMsgDaox;

	@Resource(name = "opasFicoMsgDaox")
	private OpasFicoMsgDao opasFicoMsgDaox;

	@Resource(name = "opasFqzResultDaox")
	private OpasFqzResultDao opasFqzResultDaox;
	
	@Resource(name = "opasLoansMsgDaox")
	private OpasLoansMsgDao opasLoansMsgDaox;
	
	@Resource(name = "opasThdMsgDaox")
	private OpasThdMsgDao opasThdMsgDaox;
	/**
	 * 组装外部数据查询前判断模型
	 */
	@Override
	public QueryThdSide createQueryThdSide(Opasbizinpapplc1 on1) throws CoreException {
		QueryThdSide qts = new QueryThdSide();
		qts.setCompanyCheck(true);
		qts.setApplyCardFlag(on1.getAppId().substring(10, 11));
		if (on1.getC1Educls() != null) {
			qts.setEdudegree(on1.getC1Educls().trim());
		}
		// 0901 用xu.zg的函数求年龄
		try {
			qts.setAge(opasbizinpapplc1Daox.getPersonAge(on1.getAppId()));
		} catch (Exception e) {
			qts.setAge(0);
		}
		Opasbizinpapplc1 on2 = new Opasbizinpapplc1();
		on2.setAppId(on1.getAppId().trim().substring(0, 15));
		if (opasbizinpapplc1Daox.selectSerialCount(on2) >= 2) {
			qts.setSerialCard(true);
		} else {
			qts.setSerialCard(false);
		}
		/*// 自购车辆信息
		String c1Carst = on1.getC1Carst();
		// 银行专用栏位
		String c4Clyn1 = on1.getC4Clyn1();
		// 查询数量设置和日期
		List<Map<String, Object>> dateAndCountLimitList = opasThdCarMsgDaox.queryDateAndCountLimit();
		int vehicleCount = opasThdCarMsgDaox.queryVehicleCount();
		if (dateAndCountLimitList.size() > 0) {
			Map<String, Object> dateAndCountLimit = dateAndCountLimitList.get(0);
			Date limitStartDate = (Date) dateAndCountLimit.get("limitStartDate");
			Date limitEndDate = (Date) dateAndCountLimit.get("limitEndDate");
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
			String limitStartDateStr = simpleDateFormat.format(limitStartDate);
			String limitEndDateStr = simpleDateFormat.format(limitEndDate);
			Integer intLimitStart = Integer.parseInt(limitStartDateStr);
			Integer intLimitEnd = Integer.parseInt(limitEndDateStr);
			Date nowDate = new Date();
			Integer intCurrent = Integer.parseInt(simpleDateFormat.format(nowDate));

			if (intCurrent >= intLimitStart && intCurrent <= intLimitEnd) {
				qts.setLimitDate(true);
			}
			BigDecimal limitQueryCountB = (BigDecimal) dateAndCountLimit.get("limitQueryCount");
			int limitQueryCount = limitQueryCountB.intValue();
			if (vehicleCount < limitQueryCount) {
				qts.setLimitCount(true);
			}
		} else {
			qts.setLimitDate(false);
			qts.setLimitCount(false);
		}
		List<String> vehicleMsgList = opasThdCarMsgDaox.queryVehicleMsg();
		// 设置<车辆是否命中自购车辆>默认值为不查询车辆
		qts.setSelfCar(false);
		// 设置<车辆是否命中银行专用栏位_行驶证情况>默认值为不查询车辆
		qts.setHaveLicense(false);
		// 车辆是否命中账户类型标准卡
		qts.setHitBzkCard(false);
		// 车辆是否命中账户类型易达金
		qts.setHitYdjCard(false);
		if (vehicleMsgList.size() > 0) {
			// 复选框选中时,需要查询<自购车辆情况>,所以设置初始值为false
			if (vehicleMsgList.contains("ZGCLQK")) {// 先判断是否需要查询<车辆是否命中自购车辆>
				if (c1Carst != null && !"".equals(c1Carst)) {// 需要查询的时候则查询改申请件是否有自购车辆
					qts.setSelfCar(true);
				}
			} else {// 不需要查询,设置条件符合
				qts.setSelfCar(true);
			}
			if (vehicleMsgList.contains("YHZYLW")) {// 先判断是否需要查询<车辆是否命中银行专用栏位_行驶证情况>
				if (c4Clyn1 != null && "1".equals(c4Clyn1)) {// 需要查询的时候则查询改申请件是否有行驶证
					qts.setHaveLicense(true);
				}
			} else {// 不需要查询,设置条件符合
				qts.setHaveLicense(true);
			}
			if (!vehicleMsgList.contains("ZGCLQK") && !vehicleMsgList.contains("YHZYLW")) {
				qts.setSelfCar(false);
				qts.setHaveLicense(false);
			}
			if (vehicleMsgList.contains("BZK") && "2".equals(on1.getYdjFlag())) {// 判断是否需要查询标准卡
				qts.setHitBzkCard(true);
			}
			if (vehicleMsgList.contains("YDJ") && "1".equals(on1.getYdjFlag())) {// 判断是否需要查询易达金
				qts.setHitYdjCard(true);
			}
			if ("1".equals(on1.getYdjFlag()) && !"0".equals(on1.getMatchingCardFlag())
					&& (vehicleMsgList.contains("YDJ") || vehicleMsgList.contains("BZK"))) {
				qts.setHitYdjCard(true);
				qts.setHitBzkCard(true);
			}
		}

		String inputTraderStr = on1.getAppId().substring(6, 7);// 第7位为录入商
		String channelStr = on1.getAppId().substring(7, 8);// 第8位为渠道
		String cityStr = on1.getAppId().substring(8, 10);// 第9-10位为城市
		qts.setAppInput(false);
		qts.setAppChannel(false);
		qts.setAppRegion(false);
		List<Map<String, String>> ruleContentList = opasThdCarMsgDaox.queryRuleContent();
		if (ruleContentList.size() > 0) {
			for (Map<String, String> ruleContentMap : ruleContentList) {
				if (inputTraderStr.equals(ruleContentMap.get("inputTrader"))
						|| ruleContentMap.get("inputTrader") == null) {
					qts.setAppInput(true);
				}
				if (channelStr.equals(ruleContentMap.get("channel")) || ruleContentMap.get("channel") == null) {
					qts.setAppChannel(true);
				}
				if (cityStr.equals(ruleContentMap.get("city")) || ruleContentMap.get("city") == null) {
					qts.setAppRegion(true);
				}

			}
		}*/
		
		List<Map<String, Object>> tyDateAndCountLimitList = opasThdCarMsgDaox.queryTyDateAndCountLimit();
		int tyCount = opasThdCarMsgDaox.queryTyCount();
		if (tyDateAndCountLimitList.size() > 0) {
			Map<String, Object> dateAndCountLimit = tyDateAndCountLimitList.get(0);
			Date limitStartDate = (Date) dateAndCountLimit.get("limitStartDate");
			Date limitEndDate = (Date) dateAndCountLimit.get("limitEndDate");
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
			String limitStartDateStr = simpleDateFormat.format(limitStartDate);
			String limitEndDateStr = simpleDateFormat.format(limitEndDate);
			Integer intLimitStart = Integer.parseInt(limitStartDateStr);
			Integer intLimitEnd = Integer.parseInt(limitEndDateStr);
			Date nowDate = new Date();
			Integer intCurrent = Integer.parseInt(simpleDateFormat.format(nowDate));

			if (intCurrent >= intLimitStart && intCurrent <= intLimitEnd) {
				qts.setTyLimitDate(true);
			}
			BigDecimal limitQueryCountB = (BigDecimal) dateAndCountLimit.get("limitQueryCount");
			int limitQueryCount = limitQueryCountB.intValue();
			if (tyCount < limitQueryCount) {
				qts.setTyLimitCount(true);
			}
		} else {
			qts.setTyLimitDate(false);
			qts.setTyLimitCount(false);
		}
		//单位名称
		String c1Coname = on1.getC1Coname();
		if(c1Coname==null || "".equals(c1Coname.trim()) || CacheConsts.excludeKeywordList.contains(c1Coname)){
			qts.setCompanyCheck(false);
		}
		// 手机实名制
		if(!"B".equals(on1.getInputChannel())){//申请件渠道非B为非网申,非网申发起手机实名制查询
			qts.setCellPhoneCheck(true);
		}
		//反欺诈决策等级
		List<String> decisionLvList = opasFqzResultDaox.selectFqzDecisionLvByAppId(on1.getAppId());
		qts.setDecisionLevel(decisionLvList.size()==0?"B":decisionLvList.get(0));
		//手机实名制时间和数量限制
		List<Map<String, Object>> phoneDateAndCountLimitList = opasThdMsgDaox.queryDateAndCountLimit("1100");
		if (phoneDateAndCountLimitList.size() > 0) {
			Map<String, Object> dateAndCountLimit = phoneDateAndCountLimitList.get(0);
			// 起始和结束限定时间
			Date limitStartDate = (Date) dateAndCountLimit.get("limitStartDate");
			Date imitEndDate = (Date) dateAndCountLimit.get("limitEndDate");
			// 规定时间内限定数量
			BigDecimal limitQueryCountB = (BigDecimal) dateAndCountLimit.get("limitQueryCount");
			// 规定时间内已查询数量
			int currentCount = opasThdMsgDaox.queryPhoneCurrentCount(dateAndCountLimit);
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
			String limitStartDateStr = simpleDateFormat.format(limitStartDate);
			String limitEndDateStr = simpleDateFormat.format(imitEndDate);
			Integer intLimitStart = Integer.parseInt(limitStartDateStr);
			Integer intLimitEnd = Integer.parseInt(limitEndDateStr);
			Date nowDate = new Date();
			Integer intCurrent = Integer.parseInt(simpleDateFormat.format(nowDate));
			if (intCurrent >= intLimitStart && intCurrent <= intLimitEnd) {
				qts.setPhoneLimitDate(true);
			}
			int limitQueryCount = limitQueryCountB.intValue();
			if (currentCount < limitQueryCount) {
				qts.setPhoneLimitCount(true);
			}
		}else{
			qts.setPhoneLimitDate(false);
			qts.setPhoneLimitCount(false);
		}
		
		// 企业查询数量设置和日期
		List<Map<String, Object>> companyDateAndCountLimitList = opasThdCarMsgDaox.queryCompanyDateAndCountLimit();
		int companyCount = opasThdCarMsgDaox.queryCompanyCount();
		if (companyDateAndCountLimitList.size() > 0) {
			Map<String, Object> companyDateAndCountLimit = companyDateAndCountLimitList.get(0);
			Date limitStartDate = (Date) companyDateAndCountLimit.get("limitStartDate");
			Date limitEndDate = (Date) companyDateAndCountLimit.get("limitEndDate");
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
			String limitStartDateStr = simpleDateFormat.format(limitStartDate);
			String limitEndDateStr = simpleDateFormat.format(limitEndDate);
			Integer intLimitStart = Integer.parseInt(limitStartDateStr);
			Integer intLimitEnd = Integer.parseInt(limitEndDateStr);
			Date nowDate = new Date();
			Integer intCurrent = Integer.parseInt(simpleDateFormat.format(nowDate));

			if (intCurrent >= intLimitStart && intCurrent <= intLimitEnd) {
				qts.setCompanyLimitDate(true);
			}
			BigDecimal companyLimitQueryCountB = (BigDecimal) companyDateAndCountLimit.get("limitQueryCount");
			int companyLimitQueryCount = companyLimitQueryCountB.intValue();
			if (companyCount < companyLimitQueryCount) {
				qts.setCompanyLimitCount(true);
			}
		} else {
			qts.setCompanyLimitDate(false);
			qts.setCompanyLimitCount(false);
		}
		
		List<String> companyMsgList = opasThdCarMsgDaox.queryCompanyMsg();
		// 企业是否命中账户类型标准卡
		qts.setCompanyHitBzkCard(false);
		// 企业是否命中账户类型易达金
		qts.setCompanyHitYdjCard(false);
		if (companyMsgList.size() > 0) {
			if (companyMsgList.contains("BZK") && "2".equals(on1.getYdjFlag())) {// 判断是否需要查询标准卡
				qts.setCompanyHitBzkCard(true);
			}
			if (companyMsgList.contains("YDJ") && "1".equals(on1.getYdjFlag())) {// 判断是否需要查询易达金
				qts.setCompanyHitYdjCard(true);
			}
			if ("1".equals(on1.getYdjFlag()) && !"0".equals(on1.getMatchingCardFlag())
					&& (companyMsgList.contains("YDJ") || companyMsgList.contains("BZK"))) {
				qts.setCompanyHitYdjCard(true);
				qts.setCompanyHitBzkCard(true);
			}
		}
		
		// 通讯运营商查询
		List<Map<String, Object>> txDateAndCountLimitList = opasThdMsgDaox.queryDateAndCountLimit("1300");
		if (txDateAndCountLimitList.size() > 0) {
			Map<String, Object> txDateAndCountLimit = txDateAndCountLimitList.get(0);
			// 起始和结束限定时间
			Date txLimitStartDate = (Date) txDateAndCountLimit.get("limitStartDate");
			Date txLimitEndDate = (Date) txDateAndCountLimit.get("limitEndDate");
			// 规定时间内限定数量
			BigDecimal txLimitQueryCountB = (BigDecimal) txDateAndCountLimit.get("limitQueryCount");
			// 规定时间内已查询数量
			int currentCount = opasThdMsgDaox.queryTxyysCurrentCount(txDateAndCountLimit);
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
			String limitStartDateStr = simpleDateFormat.format(txLimitStartDate);
			String limitEndDateStr = simpleDateFormat.format(txLimitEndDate);
			Integer intLimitStart = Integer.parseInt(limitStartDateStr);
			Integer intLimitEnd = Integer.parseInt(limitEndDateStr);
			Date nowDate = new Date();
			Integer intCurrent = Integer.parseInt(simpleDateFormat.format(nowDate));
			if (intCurrent >= intLimitStart && intCurrent <= intLimitEnd) {
				qts.setBetreiberLimitDate(true);
			}
			int txLimitQueryCount = txLimitQueryCountB.intValue();
			if (currentCount < txLimitQueryCount) {
				qts.setBetreiberLimitCount(true);
			}
		}else{
			qts.setBetreiberLimitDate(false);
			qts.setBetreiberLimitCount(false);
		}
		return qts;
	}

	@Override
	public Opasbizinpapplc1 queryOpasbizinpapplc1(String appId) throws CoreException {
		List<Opasbizinpapplc1> opasbizinpapplc1s = opasbizinpapplc1Daox.selectByPrimaryKey(appId);
		return opasbizinpapplc1s.get(0);
	}
	
	@Override
	public FicoGradeInfo queryFicoMsg(Opasbizinpapplc1 on1) throws CoreException {
		FicoGradeInfo ficoGradeInfo = new FicoGradeInfo();
		ficoGradeInfo.setFicoQuery(false);
		String appId = on1.getAppId();
		String appIdBzk = appId;
		// 易达金fico查询标示保存在易达金套卡信息中(易达金套卡为标准卡),以下为查询主卡是易达金时,将查询appId改为易达金套卡--标准卡
		if("1".equals(on1.getYdjFlag()) && "2".equals(on1.getMatchingCardFlag())){
			if("1".equals(appId.substring(15))){
				appIdBzk = appId.substring(0,15)+"2";
			}else{
				appIdBzk = appId.substring(0,15)+"1";
			}
		}
		List<String>ficoQueryList = opasFicoMsgDaox.queryFicoQueryMsg(appIdBzk);
		//List<String>ficoQueryList = opasFicoMsgDaox.queryFicoQueryMsg(appId.substring(0,15)+"1");
		if(ficoQueryList!=null && ficoQueryList.size()>0 && ("1".equals(ficoQueryList.get(0))||"2".equals(ficoQueryList.get(0)))){
			ficoGradeInfo.setFicoQuery(true);
		}
		// 查询数量设置和日期
		List<Map<String, Object>> dateAndCountLimitList = opasFicoMsgDaox.queryDateAndCountLimit();
		int ficoCount = opasFicoMsgDaox.queryFicoCount();
		if (dateAndCountLimitList.size() > 0) {
			Map<String, Object> dateAndCountLimit = dateAndCountLimitList.get(0);
			Date limitStartDate = (Date) dateAndCountLimit.get("limitStartDate");
			Date limitEndDate = (Date) dateAndCountLimit.get("limitEndDate");
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
			String limitStartDateStr = simpleDateFormat.format(limitStartDate);
			String limitEndDateStr = simpleDateFormat.format(limitEndDate);
			Integer intLimitStart = Integer.parseInt(limitStartDateStr);
			Integer intLimitEnd = Integer.parseInt(limitEndDateStr);
			Date nowDate = new Date();
			Integer intCurrent = Integer.parseInt(simpleDateFormat.format(nowDate));

			if (intCurrent >= intLimitStart && intCurrent <= intLimitEnd) {
				ficoGradeInfo.setFicoLimitDate(true);
			}
			BigDecimal limitQueryCountB = (BigDecimal) dateAndCountLimit.get("limitQueryCount");
			int limitQueryCount = limitQueryCountB.intValue();
			if (ficoCount < limitQueryCount) {
				ficoGradeInfo.setFicoLimitCount(true);
			}
		} else {
			ficoGradeInfo.setFicoLimitDate(false);
			ficoGradeInfo.setFicoLimitCount(false);
		}
		List<String> ficoMsgList = opasFicoMsgDaox.queryFicoMsg();
		// fico是否命中账户类型标准卡
		ficoGradeInfo.setFicoHitBzkCard(false);
		// fico是否命中账户类型易达金
		ficoGradeInfo.setFicoHitYdjCard(false);
		if (ficoMsgList.size() > 0) {
			if (ficoMsgList.contains("BZK") && "2".equals(on1.getYdjFlag())) {// 判断是否需要查询标准卡
				ficoGradeInfo.setFicoHitBzkCard(true);
			}
			if (ficoMsgList.contains("YDJ") && "1".equals(on1.getYdjFlag())) {// 判断是否需要查询易达金
				ficoGradeInfo.setFicoHitYdjCard(true);
			}
			if ("1".equals(on1.getYdjFlag()) && !"0".equals(on1.getMatchingCardFlag())
					&& (ficoMsgList.contains("YDJ") || ficoMsgList.contains("BZK"))) {
				ficoGradeInfo.setFicoHitYdjCard(true);
				ficoGradeInfo.setFicoHitBzkCard(true);
			}
		}

		String inputTraderStr = appId.substring(6, 7);// 第7位为录入商
		String channelStr = appId.substring(7, 8);// 第8位为渠道
		String cityStr = appId.substring(8, 10);// 第9-10位为城市
		ficoGradeInfo.setFicoAppInput(false);
		ficoGradeInfo.setFicoAppChannel(false);
		ficoGradeInfo.setFicoAppRegion(false);
		List<Map<String, String>> ruleContentList = opasFicoMsgDaox.queryRuleContent();
		if (ruleContentList.size() > 0) {
			for (Map<String, String> ruleContentMap : ruleContentList) {
				if (inputTraderStr.equals(ruleContentMap.get("inputTrader"))
						|| ruleContentMap.get("inputTrader") == null) {
					ficoGradeInfo.setFicoAppInput(true);
				}
				if (channelStr.equals(ruleContentMap.get("channel")) || ruleContentMap.get("channel") == null) {
					ficoGradeInfo.setFicoAppChannel(true);
				}
				if (cityStr.equals(ruleContentMap.get("city")) || ruleContentMap.get("city") == null) {
					ficoGradeInfo.setFicoAppRegion(true);
				}

			}
		}
		/*log.error("Fico查询信息:");
		log.error("appIdBzk=:"+appIdBzk+"$是否发起查询:"+ficoGradeInfo.isFicoQuery()+"$标准卡命中:"
		+ficoGradeInfo.isFicoHitBzkCard()+"$易达金命中:"+ficoGradeInfo.isFicoHitYdjCard()
		+"$渠道命中:"+ficoGradeInfo.isFicoAppChannel()+"$录入商:"+ficoGradeInfo.isFicoAppInput()
		+"$城市:"+ficoGradeInfo.isFicoAppRegion()
		+"$查询数量内:"+ficoGradeInfo.isFicoLimitCount()+"$查询日期内:"+ficoGradeInfo.isFicoLimitDate());*/
		return ficoGradeInfo;
	}

	@Override
	public LoansInfo queryLoansMsg(Opasbizinpapplc1 on1) throws CoreException {
		LoansInfo lis = new LoansInfo();
		lis.setLoansQuery(false);
		String appId = on1.getAppId();
		String appIdThd = new String();//主卡appId
		if("1".equals(on1.getMatchingCardFlag())){
			if("1".equals(appId.substring(15))){
				appIdThd = appId.substring(0,15)+"2";
			}else{
				appIdThd = appId.substring(0,15)+"1";
			}
		}else{
			appIdThd = appId;
		}
		List<String> loansQueryList = new ArrayList<String>();
		// 多头借贷查询查询主卡appId,易达金查易达金表,标准卡查标准卡表
		if("1".equals(on1.getYdjFlag())){
			loansQueryList = opasLoansMsgDaox.queryLoansQueryYdjMsg(appIdThd);
		}else{
			loansQueryList = opasLoansMsgDaox.queryLoansQueryBzkMsg(appIdThd);
		}
		//List<String>ficoQueryList = opasFicoMsgDaox.queryFicoQueryMsg(appId.substring(0,15)+"1");
		if(loansQueryList!=null && loansQueryList.size()>0 && ("1".equals(loansQueryList.get(0)))){
			lis.setLoansQuery(true);
		}
		// 查询数量设置和日期
		List<Map<String, Object>> dateAndCountLimitList = opasLoansMsgDaox.queryDateAndCountLimit();
//		int loansBfCount = opasLoansMsgDaox.queryLoansCount();
//		int loansTdCount = opasLoansMsgDaox.queryTodayLoansCount();
//		int loansCount = loansBfCount+loansTdCount;
		int loansCount = opasLoansMsgDaox.queryLoansCount()+opasLoansMsgDaox.queryTodayLoansCount();
		if (dateAndCountLimitList.size() > 0) {
			Map<String, Object> dateAndCountLimit = dateAndCountLimitList.get(0);
			Date limitStartDate = (Date) dateAndCountLimit.get("limitStartDate");
			Date limitEndDate = (Date) dateAndCountLimit.get("limitEndDate");
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
			String limitStartDateStr = simpleDateFormat.format(limitStartDate);
			String limitEndDateStr = simpleDateFormat.format(limitEndDate);
			Integer intLimitStart = Integer.parseInt(limitStartDateStr);
			Integer intLimitEnd = Integer.parseInt(limitEndDateStr);
			Date nowDate = new Date();
			Integer intCurrent = Integer.parseInt(simpleDateFormat.format(nowDate));

			if (intCurrent >= intLimitStart && intCurrent <= intLimitEnd) {
				lis.setLoansLimitDate(true);
			}
			BigDecimal limitQueryCountB = (BigDecimal) dateAndCountLimit.get("limitQueryCount");
			int limitQueryCount = limitQueryCountB.intValue();
			if (loansCount < limitQueryCount) {
				lis.setLoansLimitCount(true);
			}
		} else {
			lis.setLoansLimitDate(false);
			lis.setLoansLimitCount(false);
		}
		List<String> loansMsgList = opasLoansMsgDaox.queryLoansMsg();
		// fico是否命中账户类型标准卡
		lis.setLoansHitBzkCard(false);
		// fico是否命中账户类型易达金
		lis.setLoansHitYdjCard(false);
		if (loansMsgList.size() > 0) {
			if (loansMsgList.contains("BZK") && "2".equals(on1.getYdjFlag())) {// 判断是否需要查询标准卡
				lis.setLoansHitBzkCard(true);
			}
			if (loansMsgList.contains("YDJ") && "1".equals(on1.getYdjFlag())) {// 判断是否需要查询易达金
				lis.setLoansHitYdjCard(true);
			}
			if ("1".equals(on1.getYdjFlag()) && !"0".equals(on1.getMatchingCardFlag())
					&& (loansMsgList.contains("YDJ") || loansMsgList.contains("BZK"))) {
				lis.setLoansHitYdjCard(true);
				lis.setLoansHitBzkCard(true);
			}
		}

		String inputTraderStr = appId.substring(6, 7);// 第7位为录入商
		String channelStr = appId.substring(7, 8);// 第8位为渠道
		String cityStr = appId.substring(8, 10);// 第9-10位为城市
		lis.setLoansAppInput(false);
		lis.setLoansAppChannel(false);
		lis.setLoansAppRegion(false);
		List<Map<String, String>> ruleContentList = opasLoansMsgDaox.queryRuleContent();
		if (ruleContentList.size() > 0) {
			for (Map<String, String> ruleContentMap : ruleContentList) {
				if (inputTraderStr.equals(ruleContentMap.get("inputTrader"))
						|| ruleContentMap.get("inputTrader") == null) {
					lis.setLoansAppInput(true);
				}
				if (channelStr.equals(ruleContentMap.get("channel")) || ruleContentMap.get("channel") == null) {
					lis.setLoansAppChannel(true);
				}
				if (cityStr.equals(ruleContentMap.get("city")) || ruleContentMap.get("city") == null) {
					lis.setLoansAppRegion(true);
				}

			}
		}
		/*log.error("多头借贷查询信息:");
		log.error("appIdThd=:"+appIdThd+"$是否发起查询:"+lis.isLoansQuery()+"$标准卡命中:"+lis.isLoansHitBzkCard()+"$易达金命中:"+lis.isLoansHitYdjCard()
		+"$渠道命中:"+lis.isLoansAppChannel()+"$录入商:"+lis.isLoansAppInput()+"$城市:"+lis.isLoansAppRegion()
		+"$查询数量内:"+lis.isLoansLimitCount()+"$查询日期内:"+lis.isLoansLimitDate());*/
		return lis;
	}
	
}
