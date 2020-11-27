package com.huaxia.opas.cache.service.rule.impl;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.huateng.huaxia.xom.ApplyMainCardInfo;
import com.huateng.huaxia.xom.EnterpriseNetwork;
import com.huateng.huaxia.xom.HaveCardInfo;
import com.huateng.huaxia.xom.PadInfor;
import com.huateng.huaxia.xom.QuyushujuInfo;
import com.huateng.huaxia.xom.RepInfor;
import com.huateng.huaxia.xom.RepInforCheck;
import com.huateng.huaxia.xom.StatisInfo;
import com.huateng.huaxia.xom.TYDataAnalyze;
import com.huateng.huaxia.xom.WebInforCheck;
import com.huateng.huaxia.xom.YuShenInfo;
import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.cache.common.CacheConsts;
import com.huaxia.opas.cache.service.rule.RuleModelBuildService;
import com.huaxia.opas.dao.rule.OpasApproveReasonDao;
import com.huaxia.opas.dao.rule.OpasBizApprovalDao;
import com.huaxia.opas.dao.rule.OpasBizApprovalHisDao;
import com.huaxia.opas.dao.rule.OpasBizPubsecGatherDao;
import com.huaxia.opas.dao.rule.OpasEnterpriseNetworkDao;
import com.huaxia.opas.dao.rule.OpasErDaiRepInforDao;
import com.huaxia.opas.dao.rule.OpasFicoMsgDao;
import com.huaxia.opas.dao.rule.OpasFicoSdBlazeDao;
import com.huaxia.opas.dao.rule.OpasFicoYdjBlazeDao;
import com.huaxia.opas.dao.rule.OpasFqzResultDao;
import com.huaxia.opas.dao.rule.OpasKeyfiledMarchinfoDao;
import com.huaxia.opas.dao.rule.OpasPadDao;
import com.huaxia.opas.dao.rule.OpasPbocCreditCueDao;
import com.huaxia.opas.dao.rule.OpasPbocLoanCardInfoDao;
import com.huaxia.opas.dao.rule.OpasPromoterRiskListDao;
import com.huaxia.opas.dao.rule.OpasQysjDao;
import com.huaxia.opas.dao.rule.OpasThdCarMsgDao;
import com.huaxia.opas.dao.rule.OpasTyMsgDao;
import com.huaxia.opas.dao.rule.OpasUniAddrDao;
import com.huaxia.opas.dao.rule.OpasWebMessageDao;
import com.huaxia.opas.dao.rule.OpasYuShenDao;
import com.huaxia.opas.dao.rule.Opasbizinpapplc1Dao;
import com.huaxia.opas.dao.rule.OpasbizspeciallistcelldataDao;
import com.huaxia.opas.dao.rule.OpasbizspeciallistiddataDao;
import com.huaxia.opas.dao.rule.OpasbizzmcreditwatchlistiDao;
import com.huaxia.opas.dao.rule.OpasbizzmivsdataDao;
import com.huaxia.opas.dao.rule.OpasbizzmscordataDao;
import com.huaxia.opas.dao.rule.OpaspbocgruranteeDao;
import com.huaxia.opas.dao.rule.OpaspbocoverdueandfellbackDao;
import com.huaxia.opas.dao.rule.OpaspbocqueryreclmrecsumDao;
import com.huaxia.opas.dao.rule.OpasrevieentrycompareinfoDao;
import com.huaxia.opas.dao.rule.OpassameindustryrisklistDao;
import com.huaxia.opas.domain.compare.RevCompInfo;
import com.huaxia.opas.domain.input.FicoSdBlaze;
import com.huaxia.opas.domain.input.FicoYdjBlaze;
import com.huaxia.opas.domain.riskcheck.KeyfiledMarchinfo;
import com.huaxia.opas.domain.riskcheck.RiskCheck;
import com.huaxia.opas.domain.rule.OpasPbocCreditCue;
import com.huaxia.opas.domain.rule.Opasbizinpapplc1;
import com.huaxia.opas.domain.rule.Opasbizspeciallistcelldata;
import com.huaxia.opas.domain.rule.Opasbizspeciallistiddata;
import com.huaxia.opas.domain.rule.Opasrevieentrycompareinfo;
import com.huaxia.opas.domain.rule.Opassameindustryrisklist;
import com.huaxia.opas.service.compare.RevCompInfoService;
import com.huaxia.opas.service.rule.StatisInfoService;

import net.sf.json.JSONArray;

public class RuleModelBuildServiceImpl implements RuleModelBuildService {
	private static final Logger log = Logger.getLogger(RuleModelBuildServiceImpl.class);
	@Resource(name = "opasKeyfiledMarchinfoDaox")
	private OpasKeyfiledMarchinfoDao opasKeyfiledMarchinfoDaox;
//	@Resource(name = "sysDecisionYdjServicex")
//	private SysDecisionYdjService sysDecisionYdjServicex;//
	@Resource(name = "opasbizzmivsdataDaox")
	private OpasbizzmivsdataDao opasbizzmivsdataDaox;//
	@Resource(name = "opasbizzmscordataDaox")
	private OpasbizzmscordataDao opasbizzmscordataDaox;//
	@Resource(name = "opaspbocqueryreclmrecsumDaox")
	private OpaspbocqueryreclmrecsumDao opaspbocqueryreclmrecsumDaox;//
	@Resource(name = "opaspbocoverdueandfellbackDaox")
	private OpaspbocoverdueandfellbackDao opaspbocoverdueandfellbackDaox;//
	@Resource(name = "opaspbocgruranteeDaox")
	private OpaspbocgruranteeDao opaspbocgruranteeDaox;//
	@Resource(name = "opasPbocCreditCueDaox")
	private OpasPbocCreditCueDao opasPbocCreditCueDaox;//
	@Resource(name = "opasbizzmcreditwatchlistiDaox")
	private OpasbizzmcreditwatchlistiDao opasbizzmcreditwatchlistiDaox;//
	@Resource(name = "opasPromoterRiskListDaox")
	private OpasPromoterRiskListDao opasPromoterRiskListDaox;
	@Resource(name = "opasbizspeciallistcelldataDaox")
	private OpasbizspeciallistcelldataDao opasbizspeciallistcelldataDaox;//
	@Resource(name = "opasbizspeciallistiddataDaox")
	private OpasbizspeciallistiddataDao opasbizspeciallistiddataDaox;//
	@Resource(name = "opassameindustryrisklistDaox")
	private OpassameindustryrisklistDao opassameindustryrisklistDaox;//
	@Resource(name = "opasrevieentrycompareinfoDaox")
	private OpasrevieentrycompareinfoDao opasrevieentrycompareinfoDaox;//
	@Resource(name = "opasBizApprovalDaox")
	private OpasBizApprovalDao opasBizApprovalDaox;//
	@Resource(name = "opasBizApprovalHisDaox")
	private OpasBizApprovalHisDao opasBizApprovalHisDaox;//
	@Resource(name = "opasApproveReasonDaox")
	private OpasApproveReasonDao opasApproveReasonDaox;
	@Resource(name = "opasBizPubsecGatherDaox")
	private OpasBizPubsecGatherDao opasBizPubsecGatherDaox;//
	@Resource(name = "opasbizinpapplc1Daox")
	private Opasbizinpapplc1Dao opasbizinpapplc1Daox;//
//	@Resource(name = "ydjSysresultInfoDaox")
//	private YdjSysresultInfoDao ydjSysresultInfoDaox;
	@Resource(name = "opasPbocLoanCardInfoDaox")
	private OpasPbocLoanCardInfoDao opasPbocLoanCardInfoDaox;//
	@Resource(name = "statisInfoServicex")
	private StatisInfoService statisInfoServicex;//
	
	@Resource(name = "opasFicoSdBlazeDaox")
	private OpasFicoSdBlazeDao opasFicoSdBlazeDaox;//
	@Resource(name = "opasFicoYdjBlazeDaox")
	private OpasFicoYdjBlazeDao opasFicoYdjBlazeDaox;//
	
	@Resource(name = "revCompInfoService")
	private RevCompInfoService revCompInfoService;
	@Resource(name = "opasFqzResultDaox")
	private OpasFqzResultDao opasFqzResultDaox;//
	@Resource(name = "opasThdCarMsgDaox")
	private OpasThdCarMsgDao opasThdCarMsgDaox;//
	
	@Resource(name = "opasFicoMsgDaox")
	private OpasFicoMsgDao opasFicoMsgDaox;
	
	@Resource(name = "opasTyMsgDaox")
	private OpasTyMsgDao opasTyMsgDaox;
	
	@Resource(name = "opasEnterpriseNetworkDaox")
	private OpasEnterpriseNetworkDao opasEnterpriseNetworkDaox;
	
	@Resource(name = "opasYuShenDaox")
	private OpasYuShenDao opasYuShenDaox;
	
	@Resource(name = "opasPadDaox")
	private OpasPadDao opasPadDaox;

	@Resource(name = "opasQysjDaox")
	private OpasQysjDao opasQysjDaox;
	
	@Resource(name = "opasUniAddrDaox")
	private OpasUniAddrDao opasUniAddrDaox;
	
	@Resource(name = "opasWebMessageDaox")
	private OpasWebMessageDao opasWebMessageDaox;
	
	//二代人行-wenyh
	@Resource(name = "opasErDaiRepInforDaox")
	private OpasErDaiRepInforDao opasErDaiRepInforDaox;
	
	/**
	 * 查询主进件表信息
	 */
	@Override
	public List<Opasbizinpapplc1> selectByPrimaryKey(String appId) throws CoreException {
		List<Opasbizinpapplc1> opasbizinpapplc1s = opasbizinpapplc1Daox.selectByPrimaryKey(appId);
		Opasbizinpapplc1 opasbizinpapplc1 = opasbizinpapplc1s.get(0);
		return opasbizinpapplc1s;
	}
	
	/**
	 * 组装主卡进件模型
	 */
	@Override
	public ApplyMainCardInfo creatApplyMainCardInfo(Opasbizinpapplc1 on1) throws CoreException {
//		List<Map<String,String>> ohcci = opasbizinpapplc1Daox.selectMainCardInfoByAppId(on1.getAppId());
		ApplyMainCardInfo applyMainCardInfo = new ApplyMainCardInfo();
		applyMainCardInfo.setInsideAppNo(on1.getInsideAppNo());
		applyMainCardInfo.setAppBank(on1.getAppBank());
		applyMainCardInfo.setAppId(on1.getAppId());
		applyMainCardInfo.setAppProd(on1.getAppProd());
		applyMainCardInfo.setAppFlag(on1.getAppFlag());
		applyMainCardInfo.setC1IdType(on1.getC1Idtype());
		applyMainCardInfo.setC1Gender(on1.getC1Gender());
		applyMainCardInfo.setC1Nation(on1.getC1Nation());
		applyMainCardInfo.setC1Marst(on1.getC1Marst());
		//2017/5/11 from weijinfeng 修改年收入若为空,则设置收入为0
		applyMainCardInfo.setC1Earn(on1.getC1Earn()==null?0:on1.getC1Earn().doubleValue());
		applyMainCardInfo.setC4FushFlg(on1.getC4Rushflg());
		applyMainCardInfo.setC4Excode(on1.getC4Excode());
		applyMainCardInfo.setC5Oversea(on1.getC5Oversea());
		applyMainCardInfo.setC5AbType(on1.getC5Abtype());
		//主附卡类型
		applyMainCardInfo.setC1c2flag(on1.getC1c2Flag());
		applyMainCardInfo.setHyCard(false);
		applyMainCardInfo.setTjCard(false);
		applyMainCardInfo.setAge(0);
		applyMainCardInfo.setC2IdType(on1.getC1Idtype());

		if(!"3".equals(on1.getC1c2Flag())){
			List<Map<String, String>> c2CardMsgList = opasbizinpapplc1Daox.queryC2CardMsg(on1.getAppId());
			if(c2CardMsgList.size() > 0){
				applyMainCardInfo.setC2IdType(c2CardMsgList.get(0).get("c2Idtype"));
				if("2".equals(on1.getC1c2Flag()))
				applyMainCardInfo.setC1IdType(c2CardMsgList.get(0).get("c2Idtype"));
			}
		}
		// 进件来源
		applyMainCardInfo.setC4Apsour(on1.getC4Apsour() == null || "".equals(on1.getC4Apsour()) ? "9" : on1.getC4Apsour());
		
		// 用xu.zg的函数求年龄
		try {
			applyMainCardInfo.setAge(opasbizinpapplc1Daox.getPersonAge(on1.getAppId()));
		} catch (Exception e) {
			applyMainCardInfo.setAge(0);
		}
		//金鹏会员号是否为空
		applyMainCardInfo.setJpVipCodeNull(true);
		//B为网申,其他为非网申
		if(!"B".equals(on1.getInputChannel())){
			applyMainCardInfo.setApplyTrench("0");//非网申
		}else{
			applyMainCardInfo.setApplyTrench("1");
		}
//		applyMainCardInfo.setMainCardStatus(on1.getMainCardSts());
		
		// 20181117 from wjf 修改判断是否已持卡逻辑为:判断c1表存量标示,EXIST_CARD_FLAG,存量卡标识 0:否 1：是 2：异常
		applyMainCardInfo.setHaveCard("1".equals(on1.getExistCardFlag()));
		
		applyMainCardInfo.setC1coname(on1.getC1Coname());
		applyMainCardInfo.setC4cycadd1(on1.getC4Cycadd1()==null?"B":on1.getC4Cycadd1());
		applyMainCardInfo.setC4cycadd2(on1.getC4Cycadd2());
		//业务受理必填项是否完整
		Boolean losemsgValue = getLosemsgValue(on1);
		applyMainCardInfo.setLosemsg(losemsgValue);
		//applyMainCardInfo.setLosemsg(true);
		//是否为行员及推荐类客户
		Boolean vipCardValue = getVipCardValue(on1);
		applyMainCardInfo.setVipCard(vipCardValue);
		//是否为行员
		Boolean hyCardValue = getHyCardValue(on1);
		applyMainCardInfo.setHyCard(hyCardValue);
		//是否为推荐类客户
		Boolean tjCardValue = getTjCardValue(on1);
		applyMainCardInfo.setTjCard(tjCardValue);
		//主附卡关系
		String relship = opasbizinpapplc1Daox.selectRelshipByAppId(on1.getAppId());
		applyMainCardInfo.setRelship(relship==null?"6":relship);
		// 判断为是否为单办附属卡节点的规则
		List<Map<String,String>> c1c2Maps = opasbizinpapplc1Daox.queryFlag(on1.getAppId());
		Map<String,String> c1c2Map = c1c2Maps.get(0);
		//判断单办附属卡,且主卡卡号不为空
		if("2".equals(c1c2Map.get("c1c2Flag"))){
			if(c1c2Map.get("c1Cardnbr")!=null){
				//根据appId查询3118表信息
				List<Map<String,String>> mainCardMsgs = opasbizinpapplc1Daox.selectMainCardInfoByAppId(on1.getAppId());
				if(mainCardMsgs.size()>0){
					if(!"1".equals(mainCardMsgs.get(0).get("newHaveFlag"))){
						Map<String,String> mainCardMsg = mainCardMsgs.get(0);
						boolean resultFlag = checkResult(mainCardMsg,c1c2Map);
						applyMainCardInfo.setMainCardStatus(resultFlag?"A":null);
					}else{
						String  c1Cardnbr   = c1c2Map.get("c1Cardnbr");//C1表卡片卡号
//						Map<String,String> mainCardMsg = mainCardMsgs.get(0);
//						//匹配结果,为true异常持卡
//						boolean resultFlag = checkResult(mainCardMsg,c1c2Map);
//					
						//cardStatus 结果为''为正常,其他为异常
//						//add by yuanquan 2018.11.22
						
						String cardStatus = queryAllCardStatus(c1Cardnbr,mainCardMsgs);
						//主卡申请件卡片状态信息  规则引擎不识别"",转换为null
						applyMainCardInfo.setMainCardStatus(cardStatus.equals("")?null:cardStatus);
						log.info("\n申请件号" + on1.getAppId() + "的3118表有信息");
					}
				}else{
					applyMainCardInfo.setMainCardStatus("A");
					log.info("\n申请件号" + on1.getAppId() + "的3118表无信息");
				}
				log.info("\n申请件号" + on1.getAppId() + "的主卡卡片状态为"+applyMainCardInfo.getMainCardStatus());
				/**
				 * 1-易达金 2-非易达金
				 */
			}else{
				applyMainCardInfo.setMainCardStatus("A");
				log.info("\n申请件号" + on1.getAppId() + "无主卡信息");
			}
		}
		/**
		 * 1-易达金 2-非易达金
		 */
		applyMainCardInfo.setYDJ_FLAG(on1.getYdjFlag());
		applyMainCardInfo.setJpVipCodeNull(on1.getC4Buemb()==null||"".equals(on1.getC4Buemb())?true:false);
		String c1Xname1 = on1.getC1Xname1();//其他联系人姓名
		String c1Xtel1 = on1.getC1Xtel1();//其他联系人座机
		String c1Xmobil1 = on1.getC1Xmobil1();//其他联系人手机
		//联系人为空或联系人座机手机都为空则二联信息为空
		if((c1Xname1 == null || "".equals(c1Xname1)) || 
				((c1Xtel1 == null || "".equals(c1Xtel1)) && (c1Xmobil1 == null || "".equals(c1Xmobil1)))){
			applyMainCardInfo.setOthRelatMsg(true);//true为空
		}
		String c1Hmadd1 = on1.getC1Hmadd1();
		String c1Hmadd2 = on1.getC1Hmadd2();
		String c1Hmadd3 = on1.getC1Hmadd3();
		String c1Hmadd4 = on1.getC1Hmadd4();
		if((c1Hmadd1==null || "".equals(c1Hmadd1)) && (c1Hmadd2==null || "".equals(c1Hmadd2))
				 && (c1Hmadd3==null || "".equals(c1Hmadd3))  && (c1Hmadd4==null || "".equals(c1Hmadd4))){
			applyMainCardInfo.setHmaddrCheck(true);//true为空WWW
		}
		/*log.error("主卡申请件信息:");
		log.error("$申请渠道: "+("1".equals(applyMainCardInfo.getApplyTrench())?"网申":"非网申")+"$内审编号:"+applyMainCardInfo.getInsideAppNo()+"$银行:"+applyMainCardInfo.getAppBank()
		+"$主卡卡片状态:"+applyMainCardInfo.getMainCardStatus()+"$申请件编号:"+applyMainCardInfo.getAppId()
		+"$申请卡片产品:"+applyMainCardInfo.getAppProd()+"$主附卡标志:"+applyMainCardInfo.getAppFlag()
		+"$主卡申请人证件类型:"+applyMainCardInfo.getC1IdType()+"$主卡申请人性别:"+applyMainCardInfo.getC1Gender()
		+"$主卡申请人国籍:"+applyMainCardInfo.getC1Nation()+"$主卡申请人婚姻状况:"+applyMainCardInfo.getC1Marst()
		+"$主卡申请人年收入:"+applyMainCardInfo.getC1Earn()+"$快速发卡标志:"+applyMainCardInfo.getC4FushFlg()
		+"$自动购汇还款方式:"+applyMainCardInfo.getC4Excode()+"$是否境外申请:"+applyMainCardInfo.getC5Oversea()
		+"$推广人方式:"+applyMainCardInfo.getC5AbType()+"$申请渠道:"+applyMainCardInfo.getApplyTrench()
		+"$是否已持卡:"+applyMainCardInfo.isHaveCard()+"$单位名称:"+applyMainCardInfo.getC1coname()
		+"$账单地址1:"+applyMainCardInfo.getC4cycadd1()+"$账单地址2:"+applyMainCardInfo.getC4cycadd2()
		+"$卡产品类型:"+applyMainCardInfo.getYDJ_FLAG()+"$是否为行员或推荐类客户:"+applyMainCardInfo.isVipCard()
		+"$是否为行员:"+applyMainCardInfo.isHyCard()+"$是否为推荐类客户:"+applyMainCardInfo.isTjCard()
		+"$必填业务受理项是否填写完整:"+applyMainCardInfo.isLosemsg()+"$申请主附卡类型:"+applyMainCardInfo.getC1c2flag()
		+"$主附卡关系:"+applyMainCardInfo.getRelship()+"$申请人年龄:"+applyMainCardInfo.getAge()
		+"$进件来源:"+applyMainCardInfo.getC4Apsour());*/
		return applyMainCardInfo;
	}
	//add by yuanquan 2018.11.22
	private String queryAllCardStatus(String c1Cardnbr, List<Map<String, String>> mainCardMsgs) {
		String cardStatus = "";
		//匹配是哪张主卡匹配附属卡成功,得到匹配结果
		for(Map<String,String> mainCardMsg : mainCardMsgs){
			if(mainCardMsg.get("cardnbr1")!=null && mainCardMsg.get("cardnbr1").equals(c1Cardnbr)){
				if((!"".equals(mainCardMsg.get("cardstat1")) && mainCardMsg.get("cardstat1")!=null && !"正常".equals(mainCardMsg.get("cardstat1")))
						|| (!"".equals(mainCardMsg.get("closeCode1")) && mainCardMsg.get("closeCode1")!=null && !"正常".equals(mainCardMsg.get("closeCode1"))))
					cardStatus = mainCardMsg.get("cardstat1")==null?"A":mainCardMsg.get("cardstat1");
				break;
			}
			
		}
		
		return cardStatus;
	}

	/**
	 * 组装已持卡模型
	 */
	@Override
	public HaveCardInfo creatHaveCardInfo(Opasbizinpapplc1 on1,String appIdThd) throws CoreException {
		HaveCardInfo hci = new HaveCardInfo();
		hci.setCardStatus(false);
		hci.setAcctStatus(false);
		hci.setHitCardStatusErrH1_9(false);
		hci.setHitCardStatusErrH_11(false);
		hci.setSameCard(false);
		hci.setNormalAUAnd6mth(false);
		hci.setNormalAAnd6mth(false);
		hci.setNormalAnd6mth(false);
		hci.setNormalAStatu(false);
		hci.setOtherCardHave(false);
		hci.setFastCardStatusErr(false);
		Map<String,String> ohcci = null;
		// 2017/09/22 from wjf 改套卡查询条件
		List<Map<String,String>> ohccis = opasbizinpapplc1Daox.selectMainCardInfoByAppId(appIdThd);
		if(ohccis.size()>0){
			ohcci = ohccis.get(0);
		}
		hci.setAppID(on1.getAppId());
		String createTime = opasFicoSdBlazeDaox.selectCreateTime(on1.getAppId());
		if (ohcci != null && !"1".equals(ohcci.get("newHaveFlag"))) {
			//卡片状态
			String cardstat1 = ohcci.get("cardstat1");
			String cardstat2 = ohcci.get("cardstat2");
			String cardstat3 = ohcci.get("cardstat3");
			String cardstat4 = ohcci.get("cardstat4");
			String cardstat5 = ohcci.get("cardstat5");
			List<String> cardstat = new ArrayList<String>();
			cardstat.add(cardstat1);
			cardstat.add(cardstat2);
			cardstat.add(cardstat3);
			cardstat.add(cardstat4);
			cardstat.add(cardstat5);
			//账户状态
			String closeCode1 = ohcci.get("closeCode1");
			String closeCode2 = ohcci.get("closeCode2");
			String closeCode3 = ohcci.get("closeCode3");
			String closeCode4 = ohcci.get("closeCode4");
			String closeCode5 = ohcci.get("closeCode5");
			List<String> closeCode = new ArrayList<String>();
			closeCode.add(closeCode1);
			closeCode.add(closeCode2);
			closeCode.add(closeCode3);
			closeCode.add(closeCode4);
			closeCode.add(closeCode5);
			// 易达金
			if("1".equals(on1.getYdjFlag())||"2".equals(on1.getMatchingCardFlag())){
				hci.setCardStatus(cardstat!=null?judgeStatus(cardstat):false);
				hci.setAcctStatus(closeCode!=null?judgeStatus(closeCode):false);
			}else{
				hci.setCardStatus(cardstat!=null?judgeBzkStatus(cardstat):false);
				hci.setAcctStatus(closeCode!=null?judgeBzkStatus(closeCode):false);
				hci.setHitCardStatusErr(closeCode!=null?judgeBzkH1Status(closeCode):false);
			}
		} else {
			for (Map<String, String> ohcciObj : ohccis) {
				// 易达金已持卡账户状态异常高风险H1-9
				if(judgeStatusH1_9(ohcciObj.get("closeCode1"))){
					hci.setHitCardStatusErrH1_9(true);
				}
				// 易达金已持卡账户状态异常高风险H-11
				if(judgeStatusH_11(ohcciObj.get("closeCode1"))){
					hci.setHitCardStatusErrH_11(true);
				}
				// 本次卡种是否与已持卡卡种相同且卡种状态非QT
				short product1 = Short.parseShort(ohcciObj.get("product1"));
				// 新增标准卡迅卡判断逻辑,若已持卡中只有迅卡,且卡状态正常,则不走二卡逻辑
				//判断当前已持卡是否为迅卡
				if(product1!=135){//非迅卡
					//卡片状态为Q和T的卡,为销户状态,这种状态认为他不算有效已持卡,所以排除这种状态,就说明他有迅卡以外的卡了
					if(!"Q".equals(ohcciObj.get("cardstat1")) && !"T".equals(ohcciObj.get("cardstat1"))){
						hci.setOtherCardHave(true);
					}
				}else{
					//有迅卡则判断迅卡状态是否异常
					if("F1".equals(ohcciObj.get("cardstat1")) || "F2".equals(ohcciObj.get("cardstat1")) 
							||"G1".equals(ohcciObj.get("cardstat1")) ||"R9".equals(ohcciObj.get("cardstat1")) ){
						hci.setFastCardStatusErr(true);
					}
				}

				if(!"Q".equals(ohcciObj.get("cardstat1")) && !"T".equals(ohcciObj.get("cardstat1")) && product1==(on1.getAppProd()) ){
					hci.setSameCard(true);
				}
				//卡片状态为空,A(卡片未激活)
				if(ohcciObj.get("cardstat1") == null || "".equals(ohcciObj.get("cardstat1")) || "A".equals(ohcciObj.get("cardstat1"))){
					hci.setNormalAStatu(true);
				}
				// 发卡日期
				String opendate1 = ohcciObj.get("opendate1");
				if(nullOrSpace(opendate1) && nullOrSpace(createTime)){//nullOrSpace方法,有值是true,无值是false
					String createTimeStr = createTime.substring(0,7);
					int monthNum = (Integer.parseInt(createTimeStr.substring(0,4))-Integer.parseInt(opendate1.substring(0,4))
							)*12+(Integer.parseInt(createTimeStr.substring(5,7))-Integer.parseInt(opendate1.substring(5,7)));
					if(monthNum >= 6){
						//卡片状态为空,A(卡片未激活),U
						if(ohcciObj.get("cardstat1") == null || "".equals(ohcciObj.get("cardstat1")) 
								|| "A".equals(ohcciObj.get("cardstat1")) || "U".equals(ohcciObj.get("cardstat1")) ){
							hci.setNormalAUAnd6mth(true);
						}
						//卡片状态为空,A(卡片未激活)
						if(ohcciObj.get("cardstat1") == null || "".equals(ohcciObj.get("cardstat1")) || "A".equals(ohcciObj.get("cardstat1"))){
							hci.setNormalAAnd6mth(true);
						}
						//卡片状态为空
						if(ohcciObj.get("cardstat1") == null || "".equals(ohcciObj.get("cardstat1"))){
							hci.setNormalAnd6mth(true);
						}
					}
				}
				
				//标准卡已持卡账户状态,账户状态,卡片状态的值设定
				//add by yuan 2018.11.7
				String cardStatus = ohcciObj.get("cardstat1");
				String closeCode = ohcciObj.get("closeCode1");

				//20190806 wjf修改多张卡其中一张正常其他异常可能会赋值正常的问题
				if(checkCardStatus(cardStatus)){
					hci.setCardStatus(true);
				}
				if(checkCardStatus(closeCode)){
					hci.setAcctStatus(true);
				}
				//20190806 wjf卡片/账户状态为U时,判断庄户额度是否为0,为0则算卡片/账户状态异常
				if("U".equals(cardStatus) || "U".equals(closeCode)){
					List<Map<String, Object>> creditLimitList = opasbizinpapplc1Daox.selectCreditLimitByAppId(appIdThd);
					BigDecimal creditLimit = new BigDecimal(0);
					if(creditLimitList.size() > 0){
						creditLimit = creditLimitList.get(0).get("creditLimit")==null?creditLimit:(BigDecimal)creditLimitList.get(0).get("creditLimit");
					}
					if(creditLimit.doubleValue() == 0d){
						if("U".equals(cardStatus)){
							hci.setCardStatus(true);
						}
						if("U".equals(closeCode)){
							hci.setAcctStatus(true);
						}
					}
				}
				if(checkHitCardStatusErr(closeCode)){
					hci.setHitCardStatusErr(true);
				}
			}
		}
		/*log.error("已持卡信息:");
		log.error("$卡片状态是否异常:"+hci.isCardStatus()+"$账户状态是否异常:"+hci.isAcctStatus()
		+"$标准卡H1卡片状态的值设定:"+hci.isHitCardStatusErr()+"$易达金H1-9:"+hci.isHitCardStatusErrH1_9()
		+"$易达金H-11:"+hci.isHitCardStatusErrH_11()+"$易达金同种卡校验:"+hci.isSameCard()
		+"$易达金H-11:"+hci.isHitCardStatusErrH_11()+"$易达金同种卡校验:"+hci.isSameCard()
		+"$:卡片状态为空,A(卡片未激活)"+hci.isNormalAStatu()+"$卡片状态为空,A(卡片未激活),U,6月:"+hci.isNormalAUAnd6mth()
		+"$:卡片状态为空,A(卡片未激活),6月"+hci.isNormalAAnd6mth()+"$卡片状态为空,6月:"+hci.isNormalAnd6mth()
		+"$是否有迅卡以外的其他卡,true有其他卡"+hci.isOtherCardHave()+"$已持迅卡异常,true则异常:"+hci.isFastCardStatusErr()
		);*/
		return hci;
	}

	//add by yuanquan  卡片,账户状态判断
	private boolean checkCardStatus(String  cardStatus){
		boolean flag = false;
		flag = CacheConsts.abcodeBzkList.contains(cardStatus);
		return flag;
	}
	// //add by yuanquan  已持卡账户是否命中H1设定值
	private boolean checkHitCardStatusErr(String closeCode) {
		boolean flag = false;
		flag = CacheConsts.abcodeBzkH1List.contains(closeCode);
		return flag;
	}

	/**
	 * 组装人行征信信息模型
	 */
	@Override
	public RepInfor creatRepInfor(Opasbizinpapplc1 on1,String appIdThd) throws CoreException {
		RepInfor ri = new RepInfor();
		ri.setCheckNum(0);
		ri.setCheckAllNum(0);
		ri.setCredit("M0");
		ri.setLoan("M0");
		ri.setAssureHint(null);
		ri.setAssureHintNormal(true);//20190918-wenyh
		ri.setCreditScore1000(0f);
		ri.setMonthNum(0);
		ri.setCreditScore(0f);
		ri.setPoorPerformance(false);
		ri.setHaveLoanSum(false);
		ri.setHave6MonCard(false);
		ri.setAllCardAcctStatus(false);
		ri.setHitPbocBadness(false);
		ri.setHaveLoanHistory(false);
		ri.setHaveLoanCard(false);
		ri.setLateCardMonth(9999);
		ri.setLateLoanMon(9999);
		//20181022 from wjf 
		ri.setCivjudge(false);
		ri.setForceexe(false);
		ri.setPbocMsg(false);
		ri.setEninsurdep(false);
		ri.setAccfund(false); //易达金用人行账户公积金信息有无异常,默认无异常
		ri.setFirstLoanMon(-1);
		ri.setLoanMsgCheck(false);
		ri.setPubMsgCheck(false);
		ri.setOverdueStatu(false);
		ri.setAmtRate(-1);
		ri.setHaveDaikuanHistory(false);
		ri.setZfPayMon(999);
		ri.setLateOverdueCount(0);
		// 默认缴交记录为缴交
		ri.setZfPayStatus(true); //标准卡用公积金缴交是否缴交,默认缴交记录为缴交
		// 征信分数
		FicoYdjBlaze fybd = opasFicoYdjBlazeDaox.selectByPrimaryKey(on1.getAppId());
		if(fybd!=null&&"1".equals(on1.getYdjFlag())){
			ri.setCreditScore(fybd.getCdtScore() == null? -1 : fybd.getCdtScore().floatValue());
		}else{
			ri.setCreditScore(0f);
		}
		
		//blaze是否命中人行不良
		List<String> pbocBadnessList = opasFicoMsgDaox.queryPbocBadness(on1.getAppId());
		if(pbocBadnessList != null && pbocBadnessList.size() > 0 && pbocBadnessList.get(0) != null && !"".equals(pbocBadnessList.get(0).trim())){
			ri.setHitPbocBadness(true);
		}
		
		// 2017/09/22 from wjf 改套卡查询条件
		on1.setAppId(appIdThd);
	
		//  人行一个月内查询次数(二代人行含贷款和贷记卡),CheckAllNum被废用
		int checkNum = opasErDaiRepInforDaox.selectCheckNum(on1.getAppId());
		if(checkNum > 0){
			//人行 近一个月查询次数-wenyh
			ri.setCheckNum(checkNum);
			ri.setCheckAllNum(checkNum);
		}
		
		Boolean loanAndCardAcctAbnormal = false;//人行信用报告中是否有任一贷款或贷记卡账户状态存在异常
		Boolean allCardAcctStatus = false;//所有贷记卡状态是否存在一张发卡6个月以上
		List<Map<String,String>> creditAndLoanList = opasErDaiRepInforDaox.selectCreditAndLoan(on1.getAppId());
		if(creditAndLoanList != null && creditAndLoanList.size() > 0){
			for(Map<String,String> creditAndLoanMap : creditAndLoanList){
				//贷款、贷记卡历史逾期-wenyh
				String repayStatus = creditAndLoanMap.get("repayStatus");
				if("4".equals(repayStatus) || "5".equals(repayStatus) || "6".equals(repayStatus) || "7".equals(repayStatus)){
					ri.setCredit("M4");
					ri.setLoan("M4");
				}
				String accountType = creditAndLoanMap.get("accountType");
				if(accountType != null){
					//人行信用报告是否有一贷记卡账户状态异常-wenyh
					String newStatus = creditAndLoanMap.get("newStatus");
					String monStatus = creditAndLoanMap.get("monStatus");
					/**
					 * 贷记卡R2,R3: 1-正常 2-冻结 3-止付 31-银行止付 4-销户 5-呆账 6-未激活 8-司法追偿   (1,4,6正常)
					 * 非循环贷D1: 1-正常 2-逾期 3-结清 4-呆账 5-转出 6-担保物不足 7-强制平仓 8-司法追偿 (1,3,5正常)
					 * 循环贷R1: 1-正常 2-逾期 3-结清 4-呆账 5-银行止付 6-担保物不足 8-司法追偿(1,3正常)
					 * 循环额度分账户R4 :1-正常 2-逾期 3-结清 4-呆账 6-担保物不足 8-司法追偿(1,3正常)
					 * 催收账户C1: 1-催收 2-结束 (都不正常)
					 */ 
					if("R2".equals(accountType) || "R3".equals(accountType)){//贷记卡
						// nullOrSpace() false是空,true是有值
						if((nullOrSpace(newStatus) && !"1".equals(newStatus) && !"4".equals(newStatus) && !"6".equals(newStatus))
								|| (nullOrSpace(monStatus) && !"1".equals(monStatus) && !"4".equals(monStatus) && !"6".equals(monStatus))){
							allCardAcctStatus=true;//申请人所持全部贷记卡账户状态存在异常
							loanAndCardAcctAbnormal = true;//贷款或贷记卡账户状态存在异常
							break;
						}
					}else if("D1".equals(accountType)){//非循环贷款
						 if((nullOrSpace(newStatus) && !"1".equals(newStatus) && !"3".equals(newStatus) && !"5".equals(newStatus))
								|| (nullOrSpace(monStatus) && !"1".equals(monStatus) && !"3".equals(monStatus) && !"5".equals(monStatus))){
							loanAndCardAcctAbnormal = true;//贷款或贷记卡账户状态存在异常
						 }
					}else if("R1".equals(accountType)  || "R4".equals(accountType)){
						if((nullOrSpace(newStatus) && !"1".equals(newStatus) &&  !"3".equals(newStatus))
								|| (nullOrSpace(monStatus) && !"1".equals(monStatus) &&  !"3".equals(monStatus))){
							loanAndCardAcctAbnormal = true;//贷款或贷记卡账户状态存在异常
						}
					}else if("C1".equals(accountType)){
						loanAndCardAcctAbnormal = true;//贷款或贷记卡账户状态存在异常
					}
				}
			}
		}
		ri.setAllCardAcctStatus(allCardAcctStatus);
		ri.setLoanAndCardAcctAbnormal(loanAndCardAcctAbnormal);
		ri.setPoorPerformance(loanAndCardAcctAbnormal);
		
		//对外担保提示是否正常-wenyh
		List<Map<String,String>> assureHintList = opasErDaiRepInforDaox.selectAssureHint(on1.getAppId());
		if(assureHintList != null && assureHintList.size() > 0){
			for(Map<String,String> assureHintMap : assureHintList){
				if(assureHintMap != null && !"1".equals(assureHintMap.get("fiveClassify"))){
					ri.setAssureHintNormal(false);
				}
			}
		}
		
		// 中征信评分
		List<OpasPbocCreditCue> opccs = opasPbocCreditCueDaox.selectByExample(on1.getAppId());
		OpasPbocCreditCue opcc = null;
		if (opccs != null && opccs.size() > 0) {
			opcc = opccs.get(0);
			ri.setCreditScore1000(Float.parseFloat(opcc.getNumberRead() == null || "--".equals(opcc.getNumberRead()) 
								  || "".equals(opcc.getNumberRead()) ? "0" : opcc.getNumberRead()));
		}
		
		// 初始化首次贷款发放记录月份
		int maxLoanMonthNum = -1;
		List<Map<String,String>> monthNumList = opasErDaiRepInforDaox.selectMonthNum(on1.getAppId());
		for (Map<String, String> monthNumMap : monthNumList) {
			String serviceDL = monthNumMap.get("serviceDL");
			String firstYWGrantMonth = monthNumMap.get("firstYWGrantMonth");
			String reportTime = monthNumMap.get("reportTime");
			//首张贷记卡发卡距今月份-wenyh
			if(serviceDL != null && "1".equals(serviceDL)){//贷款
				if(nullOrSpace(firstYWGrantMonth) && nullOrSpace(reportTime)){//nullOrSpace方法,有值是true,无值是false
					String reportDate = reportTime.substring(0,7);
					int monthNum = (Integer.parseInt(reportDate.substring(0,4))-Integer.parseInt(firstYWGrantMonth.substring(0,4))
							)*12+(Integer.parseInt(reportDate.substring(5,7))-Integer.parseInt(firstYWGrantMonth.substring(5,7)));
					ri.setMonthNum(monthNum);
				}
			}
			//首次发放贷款帐龄-wenyh
			if(serviceDL != null && "2".equals(serviceDL)){//贷记卡
				if(nullOrSpace(firstYWGrantMonth) && nullOrSpace(reportTime)){//nullOrSpace方法,有值是true,无值是false
					String reportDate = reportTime.substring(0,7);
					int loanMonthNum = (Integer.parseInt(reportDate.substring(0,4))-Integer.parseInt(firstYWGrantMonth.substring(0,4))
							)*12+(Integer.parseInt(reportDate.substring(5,7))-Integer.parseInt(firstYWGrantMonth.substring(5,7)));
					if(loanMonthNum > maxLoanMonthNum){//若当前贷款账龄较大
						maxLoanMonthNum = loanMonthNum;
					}
				}
			}
		}
		ri.setFirstLoanMon(maxLoanMonthNum);
		
		// 初始化最新贷记卡卡龄月份
		int minMonthNum = 9999;
		// 初始化最新贷款发放记录月份
		int minLoanMonthNum = 9999;
		Boolean have6MonCard = false;//所有贷记卡状态是否存在一张发卡6个月以上
		List<Map<String,String>> have6MonCardList = opasErDaiRepInforDaox.selectHave6MonCard(on1.getAppId());
		for(Map<String,String> have6MonCardMap : have6MonCardList){
			String accountType = have6MonCardMap.get("accountType");
			String klDate = have6MonCardMap.get("klDate");
			String reportTime = have6MonCardMap.get("reportTime");
			/*log.error("人行征信信息:$开卡月份"+klDate+"$人行查询月份"+reportTime);*/
			if(accountType != null && "R2".equals(accountType)){//贷款
				if(nullOrSpace(klDate) && nullOrSpace(reportTime)){//nullOrSpace方法,有值是true,无值是false
					String reportDate = reportTime.substring(0,7);
					int monthNum = (Integer.parseInt(reportDate.substring(0,4))-Integer.parseInt(klDate.substring(0,4))
							)*12+(Integer.parseInt(reportDate.substring(5,7))-Integer.parseInt(klDate.substring(5,7)));
					if(monthNum>6){
						//所有贷记卡状态是否有一张6个月以上-wenyh
						have6MonCard = true;
					}
					if(monthNum < minMonthNum){//若当前卡卡龄较小
						//最新发卡贷记卡卡龄-wenyh
						minMonthNum = monthNum;
					}
				}
			}
			if(accountType != null && 
					("D1".equals(accountType) || 
				     "R1".equals(accountType) || 
				     "R4".equals(accountType))){//贷记卡
				if(nullOrSpace(klDate) && nullOrSpace(reportTime)){//nullOrSpace方法,有值是true,无值是false
					String reportDate = reportTime.substring(0,7);
					int loanMonthNum = (Integer.parseInt(reportDate.substring(0,4))-Integer.parseInt(klDate.substring(0,4))
							)*12+(Integer.parseInt(reportDate.substring(5,7))-Integer.parseInt(klDate.substring(5,7)));
					if(loanMonthNum < minLoanMonthNum){//若当前贷款账龄较小
						//最新发放贷款帐龄-wenyh
						minLoanMonthNum = loanMonthNum;
					}
				}
			}
		}
		ri.setHave6MonCard(have6MonCard);
		ri.setLateCardMonth(minMonthNum==9999?0:minMonthNum);
		ri.setLateLoanMon(minLoanMonthNum==9999?0:minLoanMonthNum);
		
		List<Map<String,Object>> loanSumList = opasErDaiRepInforDaox.selectHaveLoanSum(on1.getAppId());
		try {
			if(loanSumList.size()>0){
				for (Map loanSumMap : loanSumList) {
					String curOverdueTotal = loanSumMap.get("curOverdueTotal").toString();
					if(Integer.valueOf(curOverdueTotal) > 0){
						ri.setHaveLoanSum(true);
						break;
					}
				}
			}
		} catch (NumberFormatException e1) {
			log.error("贷记卡已用额度数字类型转换错误",e1);
		}
		
		List<Map<String,String>> isLoanCardList = opasErDaiRepInforDaox.selectIsLoanCard(on1.getAppId());
		for (Map<String, String> isLoanCardMap : isLoanCardList) {
			if(isLoanCardMap.get("accountType")!=null){
				//人行是否存在信贷记录-wenyh
				ri.setHaveLoanHistory(true);
				//人行账户有无贷款、贷记卡、准贷记卡信息-wenyh
				ri.setLoanMsgCheck(true);//有信息
				if("R2".equals(isLoanCardMap.get("accountType"))){
					//人行有无贷记卡-wenyh
					ri.setHaveLoanCard(true);
				}
				if("D2".equals(isLoanCardMap.get("accountType")) || 
				   "R1".equals(isLoanCardMap.get("accountType")) || 
				   "R4".equals(isLoanCardMap.get("accountType"))){
					//人行有无贷款记录-wenyh
					ri.setHaveDaikuanHistory(true);
				}
			}
		}
		
		//20181022 from wjf 
		// 人行有民事判决记录
		int civjudgeCount = opasPbocCreditCueDaox.selectCivjudgeCount(on1.getAppId());
		ri.setCivjudge(civjudgeCount > 0);//大于零为有记录,命中
		// 人行有强制执行记录
		int forceexeCount = opasPbocCreditCueDaox.selectForceexeCount(on1.getAppId());
		ri.setForceexe(forceexeCount > 0);
		// 人行有无记录(有无个人信息)
		List<String> pbocMsgList = opasPbocCreditCueDaox.selectPbocMsg(on1.getAppId());
		if(pbocMsgList != null && pbocMsgList.size() > 0 && nullOrSpace(pbocMsgList.get(0))){//nullOrSpace方法,有值是true,无值是false
			String[] pbocMsgStr = pbocMsgList.get(0).split("\\|");
			ri.setPbocMsg("1".equals(pbocMsgStr[0]));//1为人行有记录
		}
		// 人行账户社保信息有无异常
		List<String> eninsurdepList = opasPbocCreditCueDaox.selectEninsurdepMsg(on1.getAppId());
		if(eninsurdepList != null && eninsurdepList.size() > 0){//nullOrSpace方法,有值是true,无值是false
			if(nullOrSpace(eninsurdepList.get(0))){
				String[] eninsurdepStr = eninsurdepList.get(0).split("\\|");
				ri.setEninsurdep(!"1".equals(eninsurdepStr[0]));//除1皆为人行账户社保信息有异常
			}else{
				ri.setEninsurdep(true);
			}
		}
		// 人行账户公积金信息有无异常
		Map<String, String> accfundMap = opasPbocCreditCueDaox.selectAccfundMsg(on1.getAppId());
		if(accfundMap != null){
			if(nullOrSpace(accfundMap.get("payStatus"))){
//				String[] accfundStr = accfundMap.get("payStatus").split("\\|");
//				boolean payStatus = "001".equals(accfundStr[0]);
//				ri.setAccfund(!payStatus);//除001皆为人行账户公积金信息有异常
				boolean payStatus = "1".equals(accfundMap.get("payStatus"));
				ri.setAccfund(!payStatus);
				ri.setZfPayStatus(payStatus);
			}else{
				ri.setAccfund(true);
				ri.setZfPayStatus(false);
			}

			if(nullOrSpace(accfundMap.get("payYm"))){
				try {
					String payYmMonStr = accfundMap.get("payYm");
					//  只支持到2099年
					String appIdMonStr = "20"+on1.getAppId().substring(0, 4);
					int ZfPayMon = (Integer.parseInt(appIdMonStr.substring(0,4))-Integer.parseInt(payYmMonStr.substring(0,4))
							)*12+(Integer.parseInt(appIdMonStr.substring(4))-Integer.parseInt(payYmMonStr.substring(5)));
					ri.setZfPayMon(ZfPayMon);
				} catch (Exception e) {
					log.info("最新交费日期非日期格式: "+ e);
				}
			}
		}
		

		// 人行账户有无公共信息明细
		int eninsurdepMsgCount = opasPbocCreditCueDaox.selectEninsurdepMsgCount(on1.getAppId());//社保
		int accfundMsgCount = opasPbocCreditCueDaox.selectAccfundMsgCount(on1.getAppId());//公积金
		if(civjudgeCount > 0 || forceexeCount > 0 || eninsurdepMsgCount > 0 || accfundMsgCount > 0){
			ri.setPubMsgCheck(true);//有信息
		}

		List<Map<String, BigDecimal>> selectDCNMList = opasErDaiRepInforDaox.selectDCNM(on1.getAppId());
		if(selectDCNMList.size()>0 && selectDCNMList.get(0) != null){
			Map<String, BigDecimal> dcnmMap = selectDCNMList.get(0);
//			String curOverdueTotalStr = selectDCNMMap.get("curOverdueTotal") == null?"0":selectDCNMMap.get("curOverdueTotal");
//				String amtRateStr = selectDCNMMap.get("amtRate") == null?"0":selectDCNMMap.get("amtRate");
			//最大的贷记卡逾期金额-wenyh
			ri.setLateOverdueCount(dcnmMap.get("curOverdueTotal") == null?0d:dcnmMap.get("curOverdueTotal").doubleValue());
			//最大的额度使用率-wenyh
			ri.setAmtRate(dcnmMap.get("amtRate") == null?0f:dcnmMap.get("amtRate").floatValue());
		}
		// 人行当前所有信贷是否有金额逾期-wenyh
		Double curOverdueTotalSumD = new BigDecimal(opasErDaiRepInforDaox.selectOverdueStatu(on1.getAppId())).doubleValue();
		if(curOverdueTotalSumD > 0){
			ri.setOverdueStatu(true);
		}
		/*log.error("人行征信信息:");
		log.error("$人行有无贷记卡"+ri.isHaveLoanCard()+"$人行有无贷款记录:"+ri.isHaveDaikuanHistory()+"$近一个月贷款+贷记卡查询次数:"+ri.getCheckAllNum()
		+"$信用卡逾期月份:"+ri.getCredit()
		+"$贷款逾期月份:"+ri.getLoan()+"$对外担保提示是否正常:"+ri.isAssureHintNormal()+"$中征信信用1000分:"+ri.getCreditScore1000()
		+"$征信评分:"+ri.getCreditScore()+"$是否有不良表现:"+ri.isPoorPerformance()
		+"$贷款逾期金额是否大于0:"+ri.isHaveLoanSum()+"$申请人所持全部贷记卡账户状态是否存在异常:"+ri.isAllCardAcctStatus()
		+"$所有贷记卡状态是否存在一张发卡6个月以上:"+ri.isHave6MonCard()+"$blaze是否命中人行不良:"+ri.isHitPbocBadness()
		+"$最新一张贷记卡额度使用率是否低于阈值:"+ri.getAmtRate()+"$人行有无民事判决记录:"+ri.isCivjudge()+"$人行有无强制执行记录:"+ri.isForceexe()
		+"$人行有无记录(有无个人信息):"+ri.isPbocMsg()+"$人行账户有无贷款、贷记卡、准贷记卡信息:"+ri.isLoanMsgCheck()
		+"$人行账户有无公共信息明细:"+ri.isPubMsgCheck()+"$初始化首次贷款发放记录月份:"+ri.getFirstLoanMon()
		+"$人行账户社保信息有无异常:"+ri.isEninsurdep()+"$人行账户公积金信息有无异常:"+ri.isAccfund()
		+"$人行当前所有信贷是否有金额逾期:"+ri.isOverdueStatu());
		log.error("人行征信信息:");
		log.error("$人行最新贷记卡的最大逾期金额: "+ri.getLateOverdueCount()+"$额度使用率为:"+ri.getAmtRate()
		+"$最新贷记卡卡龄月份:"+ri.getLateCardMonth()+"$最新贷款账龄月份:"+ri.getLateLoanMon()+"$初始化首次贷款发放记录月份:"+ri.getFirstLoanMon()
		+"$近一个月贷款查询次数:"+ri.getCheckNum()+"$人行信用报告中是否有任一贷款或贷记卡账户状态存在异常:"+ri.isLoanAndCardAcctAbnormal()
		+"$人行是否存在信贷记录:"+ri.isHaveLoanHistory()+"$初始化首次贷款发放记录月份:"+ri.getFirstLoanMon()+"$首张贷款发卡距今月份:"+ri.getMonthNum()
		);*/
		return ri;
	}
	
	/**
	 * 组装人行信息检测模型
	 */
	@Override
	public RepInforCheck creatRepInforCheck(Opasbizinpapplc1 on1,String appIdThr) throws CoreException {
		RepInforCheck ric = new RepInforCheck();
		ric.setCellPhoneNumFit(false);
		ric.setCompanyAddr(false);
		ric.setCompanyName(false);
		ric.setHomeAddr(false);
		ric.setCompanyTellAll(false);
		ric.setCellPhoneNumFitAll(false);
		ric.setCompanyAddrAll(false);
		ric.setCompanyNameAll(false);
		ric.setHomeAddrAll(false);
		ric.setCellPhoneCheckNull(true);
		ric.setBillCheckAddr(false);
		ric.setBillingAddressWaibushuju(false);//add by yuan 账单地址与外部数据匹配一致
		ric.setQuyushujuCompanyName(false);
		ric.setUniCoAddrRange(999);
		ric.setUniHmAddrRange(999);
		ric.setCellPhoneNumAnyFit(false);//手机与人行任一手机匹配一致
		//  联通地址类信息逻辑 
		List<Map<String, Object>> uniAddrList = opasUniAddrDaox.queryUniAddrInforByAppId(appIdThr);
		try {
			for (Map<String, Object> uniAddrMap : uniAddrList) {
				if("360015".equals((String) uniAddrMap.get("apiKey")) && "00".equalsIgnoreCase(((String) uniAddrMap.get("responseCode")).trim())){
					String responseResult = (String) uniAddrMap.get("responseResult");
					if("CU".equalsIgnoreCase(responseResult.substring(0,2))){
						ric.setUniCoAddrRange(Double.valueOf(responseResult.substring(2))/2);
					} else if("CT".equalsIgnoreCase(responseResult.substring(0,2))){
						ric.setUniCoAddrRange(Double.valueOf(responseResult.substring(2)));
					}
				}
				if("360016".equals((String) uniAddrMap.get("apiKey")) && "00".equalsIgnoreCase(((String) uniAddrMap.get("responseCode")).trim())){
					String responseResult = (String) uniAddrMap.get("responseResult");
					if("CU".equalsIgnoreCase(responseResult.substring(0,2))){
						ric.setUniHmAddrRange(Double.valueOf(responseResult.substring(2))/2);
					} else if("CT".equalsIgnoreCase(responseResult.substring(0,2))){
						ric.setUniHmAddrRange(Double.valueOf(responseResult.substring(2)));
					}
				}
			}
		} catch (Exception e) {
			log.info("联通地址公里数返回格式错误",e);
			ric.setUniCoAddrRange(999);
			ric.setUniHmAddrRange(999);
		}
		
		Opasrevieentrycompareinfo orci = opasrevieentrycompareinfoDaox.selectByPrimaryKey(appIdThr);
		Map<String, String> jsonMap = null;
		try {
			jsonMap = queryTeam(appIdThr);
		} catch (Exception e) {
			log.info(e.getMessage());
		}
		if(jsonMap==null){
			jsonMap = new HashMap<String, String>();
		}
		List<String> queryPbocPhone = opasPbocCreditCueDaox.queryPbocPhone(appIdThr);
		if(queryPbocPhone!=null && queryPbocPhone.size()>0 && queryPbocPhone.get(0) != null && !"".equals(queryPbocPhone.get(0))){
			ric.setCellPhoneCheckNull(false);
		}
		// 2017/10/27 申请表申请人手机与人行手机匹配
		// 2018/03/26 修改为只取人工录入页面结果
		//String cellPhone = jsonMap.get("OPAS_PBOC_PERSONAL_INFO-CELL_PHONE");
		if (orci!=null&&"1".equals(orci.getPeobankPhone())) {
			ric.setCellPhoneNumFit(true);
			ric.setCellPhoneNumFitAll(true);
		} else if(orci!=null&&"1".equals(orci.getThirdPhone())){
			ric.setCellPhoneNumFitAll(true);
		} else {
			ric.setCellPhoneNumFitAll(false);
			ric.setCellPhoneNumFit(false);
		}
		/*else if (!"".equals(cellPhone) && cellPhone != null) {
			ric.setCellPhoneNumFit(true);
			ric.setCellPhoneNumFitAll(true);
		}*/
		
		// 2017/10/27 申请表单位地址与人行单位地址匹配
		// 2018/03/26 修改为只取人工录入页面结果
		//String companyAddr = jsonMap.get("OPAS_PBOC_PROFESSION_INFO-COMP_ADDR");
		// 人行单位地址,114单名加单址,官网单名加单址,非官网单名加单址,鹏元单名加单址
		if (orci!=null&&"1".equals(orci.getPeobankCompanyNameAddr())) {// 人行地址
			ric.setCompanyAddr(true);
			ric.setCompanyAddrAll(true);
		}else if(orci!=null&&(("1".equals(orci.getCompany114NameAddr()) || "1".equals(orci.getOfficwebNameAddr())
				|| "1".equals(orci.getNoOfficwebNameAddr()) ||"1".equals(orci.getPyuanCompanyNameAddr()))
				|| "1".equals(orci.getThirddataAddr()))){//add by yuanquan 添加第三方信息单址
			ric.setCompanyAddrAll(true);
		}else {
			ric.setCompanyAddrAll(false);
			ric.setCompanyAddr(false);
		}
		/*else if (companyAddr != null && !"".equals(companyAddr) ) {
			ric.setCompanyAddrAll(true);
			ric.setCompanyAddr(true);
		}*/
		// 2017/10/27 申请表单位电话与人行单位电话匹配
		// 2018/03/26 修改为只取人工录入页面结果
		//String companyPhone = jsonMap.get("OPAS_PBOC_PERSONAL_INFO-C_COMP_PHONE");
		// 人行单位电话,114单名加单电,官网单名加单电,非官网单名加单电,鹏元单名加单电
		if (orci!=null&&"1".equals(orci.getPeobankCompanyNameTel())) {// 人行单电
			ric.setCompanyTell(true);
			ric.setCompanyTellAll(true);
		} else if (orci!=null&&("1".equals(orci.getCompany114NameTel()) ||
				"1".equals(orci.getOfficwebNameTel()) || "1".equals(orci.getNoOfficwebNameTel()) || "1".equals(orci.getPyuanCompanyNameTel()))) {
			ric.setCompanyTellAll(true);
		} else {
			ric.setCompanyTellAll(false);
			ric.setCompanyTell(false);
		}
		 /*else if (!"".equals(companyPhone) && companyPhone != null) {
				ric.setCompanyTellAll(true);
		}*/
		// 2017/10/27 申请表单位信息与人行单位信息匹配从反欺诈取
		// 2018/03/26 修改为只取人工录入页面结果
		//String companyName = jsonMap.get("OPAS_PBOC_PROFESSION_INFO-COMPANY");
		if(orci!=null&&"1".equals(orci.getPeobankCompanyName())){
			ric.setCompanyName(true);
			ric.setCompanyNameAll(true);
			//add by yuanquan 2018.11.14 单位名称与外部数据匹配一致
		} else if(orci!=null&&("1".equals(orci.getCompany114NameAddr()) || //114单名+单址
				"1".equals(orci.getCompany114NameTel()) ||    //114单名+单电
				"1".equals(orci.getPyuanCompanyNameAddr()) ||  //鹏元单名+单址
				"1".equals(orci.getPyuanCompanyNameTel()) ||   //鹏元单名+单电
				"1".equals(orci.getOfficwebNameAddr()) ||      //官网单名+单址
				"1".equals(orci.getOfficwebNameTel()) ||       //官网单名+单电
				"1".equals(orci.getNoOfficwebNameAddr()) ||    //非官网单名+单址
				"1".equals(orci.getNoOfficwebNameTel()) ||    //非官网单名+单电
				"1".equals(orci.getRegionalDataListName()) )){  //区域数据单名一致(20200611新增)
			 
			ric.setCompanyNameAll(true);
		}else {
			ric.setCompanyNameAll(false);
			ric.setCompanyName(false);
		}
		 /*else if (companyName != null && !"".equals(companyName)) {
				ric.setCompanyNameAll(true);
				ric.setCompanyName(true);
		}*/
		//2017/10/27 申请表家庭地址与人行家庭地址匹配取
		String homeAddr = jsonMap.get("OPAS_PBOC_RESIDENCE_INFO-RESIDENT_ADDR");
		if (homeAddr != null && !"".equals(homeAddr)) {
			ric.setHomeAddr(true);
			ric.setHomeAddrAll(true);
		} else {
			ric.setHomeAddr(false);
			ric.setHomeAddrAll(false);
		}
		// 2017/08/18 from wjf
		String billCheckAddr = jsonMap.get("OPAS_INTERFACE_3014-BILLING_ADDRESS");
		if (!"".equals(billCheckAddr) && billCheckAddr != null) {
			ric.setBillCheckAddr(true);
		} else {
			ric.setBillCheckAddr(false);
		}
		String cellPhoneSendCard = jsonMap.get("OPAS_INTERFACE_3002-MOBILEPHONE");
		if (!"".equals(cellPhoneSendCard) && cellPhoneSendCard != null) {
			ric.setCellPhoneSendCard(true);
		} else {
			ric.setCellPhoneSendCard(false);
		}
		if (orci!=null&&"1".equals(orci.getOrderAddress())) {// 人行账单地址
			ric.setPbocBillCheckAddr(true);
		}
		// 20200819 手机与人行任一手机匹配一致
		String anyCellPhoneSame = jsonMap.get("PBOC_PHONE_NUMBER_DETAIL-MOBILE");
		if (!"".equals(anyCellPhoneSame) && anyCellPhoneSame != null) {
			ric.setCellPhoneNumAnyFit(true);
		} else {
			ric.setCellPhoneNumAnyFit(false);
		}
		/*
		 * 
		 * 账单地址与外部数据匹配一致
		 * 人行与申请表"账单地址"信息匹配结果为一致;
		 * 以及,符合单位地址与外部数据匹配一致规则且账单邮寄地址为单位的
		 * add by yuanquan 2018.10.25
		
		 * 
		 */
		if(ric.isPbocBillCheckAddr()){
			ric.setBillingAddressWaibushuju(true);
		}
		if(ric.isCompanyAddrAll() && "B".equals(on1.getC4Cycadd1())){
			ric.setBillingAddressWaibushuju(true);
		}
		if(ric.isCompanyAddr() && "B".equals(on1.getC4Cycadd1())){
			ric.setPbocBillCheckAddr(true);
		}
		
		
		/**
		 * 添加字段
		 * 单位名称单位地址与企业网匹配一致
		 * 申请人名称与企业网法人法人姓名匹配一致
		 * 
		 * add yuanquan 2018.10.25
		 */
		
		if(orci!=null && ("1".equals(orci.getOfficwebNameAddr()))){
			ric.setEnCompanyNameAddress(true);
		}
		if(orci!=null && ("1".equals(orci.getBusinetworkLegal()))){
			ric.setEnCompanylegal(true);
		}
		
		// 区域数据单位名称匹配一致
		if(orci!=null&&"1".equals(orci.getRegionalDataListName())){
			ric.setQuyushujuCompanyName(true);
		}
		
		/*log.error("人行征信数据校验:");
		log.error("$手机号码是否与人行征信数据一致:"+ric.isCellPhoneNumFit()+"$单位地址是否与人行征信数据一致:"+ric.isCompanyAddr()
		+"$住宅地址是否与人行征信数据一致:"+ric.isHomeAddr()+"$单位名称是否与人行征信数据一致:"+ric.isCompanyName()
		+"$单位地址是否与外部数据一致:"+ric.isCompanyAddrAll()+"$手机号码是否与外部数据一致:"+ric.isCellPhoneNumFitAll()
		+"$单位电话是否与外部数据一致:"+ric.isCompanyTellAll()+"$单位名称是否与外部数据一致:"+ric.isCompanyNameAll()
		+"$住宅地址是否与外部数据一致:"+ric.isHomeAddrAll()+"$手机是否与发卡数据一致"+ric.isCellPhoneSendCard()
		+"$人行手机是否为空:"+ric.isCellPhoneCheckNull()+"$账单地址是否与人行数据一致"+ric.isPbocBillCheckAddr()
		+"$账单地址是否与发卡数据一致"+ric.isBillCheckAddr()+"$单位电话是否与人行数据一致:"+ric.isCompanyTell()
		+"$账单地址是否与外部数据一致"+ric.isBillingAddressWaibushuju()
		+"$区域数据单位名称是否匹配一致"+ric.isQuyushujuCompanyName()
		+"$账单邮寄地址 : "+("B".equals(on1.getC4Cycadd1())?"单位":"住宅")
		+"$联通单位地址距离"+ric.getUniCoAddrRange()+"$联通住宅地址距离"+ric.getUniHmAddrRange()
		);
		log.error("$账单邮寄地址 : "+("B".equals(on1.getC4Cycadd1())?"单位":"住宅")
		+"$联通单位地址距离"+ric.getUniCoAddrRange()+"$联通住宅地址距离"+ric.getUniHmAddrRange()
		+"$单位名称单位地址与企业网匹配一致:"+ric.isEnCompanyNameAddress()+"$申请人名称与企业网法人法人姓名匹配一致:"+ric.isEnCompanylegal()
		);*/
		return ric;
	}

	/**
	 * 组装统计信息模型
	 */
	@Override
	public StatisInfo creatStatisInfo(Opasbizinpapplc1 on1) throws CoreException {
		StatisInfo statisInfo = new StatisInfo();
		statisInfo.setAppNum(false);
		statisInfo.setFullInfo("F");
		statisInfo.setApplyTime(-99);
//		statisInfo.setNumIsOne(false);
		statisInfo.setNumLongestTime(-1);
		statisInfo.setNumShortestTime(-1);
		statisInfo.setStrLongestTime(-1);
		statisInfo.setStrShortestTime(-1);
//		statisInfo.setStrIsOne(false);
		statisInfo.setNumMoreLongestTime(-1);
		statisInfo.setNumMoreShortestTime(-1);
		statisInfo.setStrMoreLongestTime(-1);
		statisInfo.setStrMoreShortestTime(-1);
		
		if("2".equals(on1.getC1c2Flag())){
			return statisInfo;
		}
		statisInfo.setAppNum(statisInfoServicex.getInsideAppNum(on1));
		statisInfo.setFullInfo(statisInfoServicex.getFullInfo(on1));
		FicoSdBlaze ficoSdBlaze = opasFicoSdBlazeDaox.selectByAppId(on1.getAppId());
		if(ficoSdBlaze!=null&&ficoSdBlaze.getPbocRstFinDesp()!=null&&!"".equals(ficoSdBlaze.getPbocRstFinDesp())){
			statisInfo.setPbocRstFinDesp(true);
		}else{
			statisInfo.setPbocRstFinDesp(false);
		}
		List<Map<String, Object>> customerBehaviorInfoList = opasWebMessageDaox.queryWebCustomerBehaviorInfoByAppId(on1.getAppId());
		double beginTime = 0; // 申请开始时间(单位秒)
		double endTime = 0; // 申请结束时间(单位秒)
		int idTimes = 0;//身份证号填写次数
		int phoneTimes = 0; //手机号填写次数
		int coPhoneTimes = 0; //单位电话填写次数
		int coNameTimes = 0; //单位名称填写次数
		int coAddrTimes = 0; //单位地址填写次数
		int hmAddrTimes = 0; //住宅地址填写次数
		int idTimeSpent = 0; //身份证号填写消耗时间(单位秒)
		int phoneTimeSpent = 0; //手机号填写消耗时间(单位秒)
		int coPhoneTimeSpent = 0; //单位电话填写消耗时间(单位秒)
		int coNameTimeSpent = 0; //单位名称填写消耗时间(单位秒)
		int coAddrTimeSpent = 0; //单位地址填写消耗时间(单位秒)
		int hmAddrTimeSpent = 0; //住宅地址填写消耗时间(单位秒)
		
		for (Map<String, Object> customerBehaviorInfoMap : customerBehaviorInfoList) {
			String eventName = (String) customerBehaviorInfoMap.get("eventName");
			int collectTimes = Integer.valueOf((String) customerBehaviorInfoMap.get("collectTimes"));
			double startTime = Long.valueOf((String) customerBehaviorInfoMap.get("startTime"))/1000;
			int duration = Integer.valueOf((String) customerBehaviorInfoMap.get("duration"));
			if(eventName != null && !"".equals(eventName.trim())){
				switch (eventName) {
				case "申请开始时间":
					if(beginTime < startTime)
						beginTime = startTime;
					break;
				case "申请提交时间":
					if(endTime < startTime)
						endTime = startTime;
					break;
				case "身份证号输入":
					idTimes += collectTimes;
					idTimeSpent += duration;
					break;
				case "手机号输入":
					phoneTimes += collectTimes;
					phoneTimeSpent += duration;
					break;
				case "单位电话输入":
					coPhoneTimes += collectTimes;
					coPhoneTimeSpent += duration;
					break;
				case "单位名称输入":
					coNameTimes += collectTimes;
					coNameTimeSpent += duration;
					break;
				case "单位地址输入":
					coAddrTimes += collectTimes;
					coAddrTimeSpent += duration;
					break;
				case "住宅地址输入":
					hmAddrTimes += collectTimes;
					hmAddrTimeSpent += duration;
					break;
				default:break;
				}
			}
		}
		// 申请所用时长
		statisInfo.setApplyTime(endTime - beginTime);
		// 数字类填写次数是否等于1 && 文字类填写次数是否等于1
		// 数字类填写1次填写时间最长用时,数字类填写1次填写时间最短用时,数字类填写n次填写时间最长用时,数字类填写n次填写时间最短用时
		int numLongestTime = -1;
		int numShortestTime= -1;
		int numMoreLongestTime = -1;
		int numMoreShortestTime= -1;
		// 身份证号
		if(idTimes == 1){
			numLongestTime = idTimeSpent;
			numShortestTime = idTimeSpent;
		}else{
			numMoreLongestTime = idTimeSpent;
			numMoreShortestTime = idTimeSpent;
		}
		// 手机号
		if(phoneTimes == 1 && phoneTimeSpent > numLongestTime){
			numLongestTime = phoneTimeSpent;
		}else if(phoneTimes == 1 && phoneTimeSpent < numShortestTime){
			numShortestTime = phoneTimeSpent;
		}else if(phoneTimes > 1 && phoneTimeSpent > numLongestTime){
			numMoreLongestTime = phoneTimeSpent;
		}else if(phoneTimes > 1 && phoneTimeSpent < numShortestTime){
			numMoreShortestTime = phoneTimeSpent;
		}
		// 单位电话
		if(coPhoneTimes == 1 && coPhoneTimeSpent > numLongestTime){
			numLongestTime = coPhoneTimeSpent;
		}else if(coPhoneTimes == 1 && coPhoneTimeSpent < numShortestTime){
			numShortestTime = coPhoneTimeSpent;
		}else if(coPhoneTimes > 1 && coPhoneTimeSpent > numLongestTime){
			numMoreLongestTime = coPhoneTimeSpent;
		}else if(coPhoneTimes > 1 && coPhoneTimeSpent < numShortestTime){
			numMoreShortestTime = coPhoneTimeSpent;
		}
		
		statisInfo.setNumLongestTime(numLongestTime);
		statisInfo.setNumShortestTime(numShortestTime);
		statisInfo.setNumMoreLongestTime(numMoreLongestTime);
		statisInfo.setNumMoreShortestTime(numMoreShortestTime);
		// 文字类填写时间最长用时,文字类填写时间最短用时,文字类填写n次填写时间最长用时,文字类填写n次填写时间最短用时
		int strLongestTime = -1;
		int strShortestTime= -1;
		int strMoreLongestTime = -1;
		int strMoreShortestTime= -1;
		// 单位电话
		if(coNameTimes == 1){
			strLongestTime = coNameTimeSpent;
			strShortestTime = coNameTimeSpent;
		}else{
			strMoreLongestTime = coNameTimeSpent;
			strMoreShortestTime = coNameTimeSpent;
		}
		// 单位地址
		if(coAddrTimes == 1 && coAddrTimeSpent > strLongestTime){
			strLongestTime = coAddrTimeSpent;
		}else if(coAddrTimes == 1 && coAddrTimeSpent < strShortestTime){
			strShortestTime = coAddrTimeSpent;
		}else if(coAddrTimes > 1 && coAddrTimeSpent > strLongestTime){
			strMoreLongestTime = coAddrTimeSpent;
		}else if(coAddrTimes > 1 && coAddrTimeSpent < strShortestTime){
			strMoreShortestTime = coAddrTimeSpent;
		}
		// 家庭住址
		if(hmAddrTimes == 1 && hmAddrTimeSpent > strLongestTime){
			strLongestTime = hmAddrTimeSpent;
		}else if(hmAddrTimes == 1 && hmAddrTimeSpent < strShortestTime){
			strShortestTime = hmAddrTimeSpent;
		}else if(hmAddrTimes > 1 && hmAddrTimeSpent > strLongestTime){
			strMoreLongestTime = hmAddrTimeSpent;
		}else if(hmAddrTimes > 1 && hmAddrTimeSpent < strShortestTime){
			strMoreShortestTime = hmAddrTimeSpent;
		}
		statisInfo.setStrLongestTime(strLongestTime);
		statisInfo.setStrShortestTime(strShortestTime);
		statisInfo.setStrMoreLongestTime(strMoreLongestTime);
		statisInfo.setStrMoreShortestTime(strMoreShortestTime);
		
		/*log.error("统计信息:");
		log.error("$是否为团办抽检件:"+statisInfo.isAppNum()+"$完整性:"+statisInfo.getFullInfo()+"$fico是否有不良表现"+statisInfo.isPbocRstFinDesp()+
		"$网申客户行为分析申请时长:"+statisInfo.getApplyTime()+"$客户行为数字类填写次数为1:"+statisInfo.isNumIsOne()+"$客户行为文字类填写次数为1"+statisInfo.isStrIsOne()+
		"$客户行为数字类填写1次最长用时:"+statisInfo.getNumLongestTime()+"$客户行为数字类填写1次最短用时:"+statisInfo.getNumShortestTime()+
		"$客户行为数字类填写n次最长用时:"+statisInfo.getNumLongestTime()+"$客户行为数字类填写n次最短用时:"+statisInfo.getNumShortestTime()+
		"$客户行为文字类填写1次最长用时:"+statisInfo.getStrLongestTime()+"$客户行为文字类填写1次最短用时:"+statisInfo.getStrShortestTime()+
		"$客户行为文字类填写n次最长用时:"+statisInfo.getStrLongestTime()+"$客户行为文字类填写n次最短用时:"+statisInfo.getStrShortestTime()
				);*/
		return statisInfo;
	}

	/**
	 * 组装网络三方数据校验
	 */
	@Override
	public WebInforCheck creatWebInforCheck(Opasbizinpapplc1 on1,String appIdThd) throws CoreException {
		WebInforCheck wic = new WebInforCheck();
		wic.setUnSafeOrga(false);
		wic.setHitAntData(false);
		wic.setHitBaiRong(false);
		wic.setSTAR_LEVLE(-1);
		wic.setReApplyFlag(false);
		wic.setRefuseWithCode(false);
		wic.setPoliceMatch(false);
		wic.setProposedLimit(0);
		wic.setRiskMatch(false);
		wic.setDecisionLevel("B");
		wic.setSameIndustryNull(true);
		wic.setRefuseWithCodeYdjH1(false);
		wic.setIdDataMatch(false);
		wic.setPhoneDataMatch(false);
		wic.setCredNo(false);
		wic.setReleMsgCheck(false);
		wic.setCustRisk(false);
		wic.setPhoneMsgExist(false);
		wic.setPhoneNameMatch(false);
		wic.setInNetMsgExist(false);
		wic.setInNetTimeType("6");
		wic.setFwsModelScore(999);
		wic.setWsModelScore(999);
		wic.setZfPayConame(false);
		wic.setFicoLaoHui(false);
		wic.setPoliceMatchFlag("0");  // 简项公安标示:0未查询/查询失败,-1不一致,1一致    
		wic.setKexinCheckFlag("0");   // 可信身份认证标示:0未查询/查询失败,-1不一致,1一致  
		
		// 2018/10/22 from wjf 新增手机实名认证 非网申件从ding去取
		List<String> phoneNameMatchList = opassameindustryrisklistDaox.selectPhoneNameMatch(appIdThd);
		if(phoneNameMatchList.size() > 0){
			wic.setPhoneMsgExist(true);
			// 20190522 随手机实名制接口变动 00代表联通一致,20代表移动一致,变动前0代表一致
//			wic.setPhoneNameMatch("0".equals(phoneNameMatchList.get(0)) || "00".equals(phoneNameMatchList.get(0)) || "20".equals(phoneNameMatchList.get(0)));
			// 201909 手机实名制结果字段变化,1代表一致
			wic.setPhoneNameMatch("1".equals(phoneNameMatchList.get(0)));
		}
		// 2019/1/14 from wjf 手机实名认证 网申件从su去取,两张表京东和非京东网申件
		if("B".equals(on1.getInputChannel())){//B为网申件
			List<String> wsPhoneNameMatchList = opassameindustryrisklistDaox.selectJdPhoneNameMatch(on1.getAppId());
			if(wsPhoneNameMatchList.size() > 0){
				wic.setPhoneMsgExist(true);
				wsPhoneNameMatchList.remove(null);
				wic.setPhoneNameMatch("1".equals(wsPhoneNameMatchList.get(0).charAt(2)+""));
			}
		}
		
		List<Opasbizspeciallistcelldata> obsls = opasbizspeciallistcelldataDaox.selectByAppid(appIdThd);
		List<Opasbizspeciallistiddata> obsds = opasbizspeciallistiddataDaox.selectByAppid(appIdThd);
		Opasbizspeciallistcelldata obsl = null;//为null
		Opasbizspeciallistiddata obsd = null;//为null
		try {
			wic.setHitBaiRong(false);
			if (obsls != null && obsls.size() > 0) {
				obsl = obsls.get(0);
				if (hitBairong(obsl)) {
					wic.setHitBaiRong(true);
				}
			}
			if (obsds != null && obsds.size() > 0) {// 需要验证
				obsd = obsds.get(0);
				if (hitBairong(obsd)) {
					wic.setHitBaiRong(true);
				}
			} 
		} catch (Exception e) {
			log.info("百融信息验证出错",e);
		}
		List<Opassameindustryrisklist> osirs = opassameindustryrisklistDaox.selectByExample(on1);
		Opassameindustryrisklist osir = null;//CERTIFI_TYPE:'01',CERTIFI_NO:'360426198109260011',osir:null
		if (osirs != null && osirs.size() > 0) {
			osir = osirs.get(0);
			//星级可能有字母,目前只会有N(NULL)，所以比3小，匹配规则时赋-1
			//wic.setSTAR_LEVLE(Integer.parseInt("N".equals(osir.getStarLevle().toUpperCase())?"-1":osir.getStarLevle()));
			// 2017/06/17 from weijinfeng
			if(null==(osir.getStarLevle())){
				wic.setSTAR_LEVLE(-1);
			}else if("null".equals(osir.getStarLevle().toUpperCase())||"N".equals(osir.getStarLevle().toUpperCase())){
				wic.setSTAR_LEVLE(0);
			}else{
				wic.setSTAR_LEVLE(Integer.parseInt(osir.getStarLevle()));
			}
			//wic.setSTAR_LEVLE(Integer.parseInt((null==(osir.getStarLevle().toUpperCase())||"N".equals(osir.getStarLevle().toUpperCase()))?"-1":osir.getStarLevle()));//-1
		}else{
			wic.setSTAR_LEVLE(-1);
		}

		// 公安匹配一致
		// 20200617 新增可信身份认证与公安一起匹配逻辑
		String c1c2Flag = on1.getC1c2Flag();
		if("01".equals(on1.getC1Idtype()) || "02".equals(on1.getC1Idtype())){
			List<Map<String,String>> opasBizPubsecGathers = opasBizPubsecGatherDaox.selectByAppidThd(appIdThd);//null
			if (opasBizPubsecGathers != null && opasBizPubsecGathers.size() > 0) {
				for (Map<String, String> opasBizPubsecGather : opasBizPubsecGathers) {
					String cardFlag = opasBizPubsecGather.get("cardFlag");
					if("1".equals(cardFlag) && "2".equals(c1c2Flag)){ //单办附卡
						// 身份证和姓名匹配一致
						if (("1".equals(opasBizPubsecGather.get("idCheckRst"))|| "一致".equals(opasBizPubsecGather.get("idCheckRst"))) && ("1".equals(opasBizPubsecGather.get("nameCheckRst")) || "一致".equals(opasBizPubsecGather.get("nameCheckRst")))) {
							wic.setPoliceMatchFlag("1"); 
						} else {
							wic.setPoliceMatchFlag("-1"); 
						}
						break;
					} else if("0".equals(cardFlag) && !"2".equals(c1c2Flag)){//主附同审或单办主卡
						// 身份证和姓名匹配一致
						if (("1".equals(opasBizPubsecGather.get("idCheckRst")) || "一致".equals(opasBizPubsecGather.get("idCheckRst"))) && ("1".equals(opasBizPubsecGather.get("nameCheckRst")) || "一致".equals(opasBizPubsecGather.get("nameCheckRst")))) {
							wic.setPoliceMatchFlag("1"); 
						} else {
							wic.setPoliceMatchFlag("-1"); 
							break;
						}
					}
				}
			}
		} else {
			List<Map<String, String>> foreignCheckList  = opasBizPubsecGatherDaox.queryForeignCheckByAppId(appIdThd);
			if (foreignCheckList != null && foreignCheckList.size() > 0) {
				for (Map<String, String> foreignCheckMap : foreignCheckList) {
					String cardFlag = foreignCheckMap.get("cardFlag");
					if("1".equals(cardFlag) && "2".equals(c1c2Flag)){ //单办附卡
						// 身份证和姓名匹配一致
						if ("01".equals(foreignCheckMap.get("idCheckRst"))) {
							wic.setPoliceMatchFlag("1"); 
						} else {
							wic.setPoliceMatchFlag("-1"); 
						}
						break;
					} else if("0".equals(cardFlag) && !"2".equals(c1c2Flag)){//主附同审或单办主卡
						// 身份证和姓名匹配一致
						if ("01".equals(foreignCheckMap.get("idCheckRst"))) {
							wic.setPoliceMatchFlag("1"); 
						} else {
							wic.setPoliceMatchFlag("-1"); 
							break;
						}
					}
				}
			}
		}
		// 20200617 可信身份认证
		List<Map<String,String>> kexinCheckMsgList = opasBizPubsecGatherDaox.selectKexinMsgByAppidThd(appIdThd);
		if(kexinCheckMsgList != null && kexinCheckMsgList.size() > 0){
			for (Map<String, String> kexinCheckMsgMap : kexinCheckMsgList) {
				String cardFlag = kexinCheckMsgMap.get("cardFlag");
				String  kexinCheckMsgStr = kexinCheckMsgMap.get("authResult") != null ? kexinCheckMsgMap.get("authResult"):"";
				if("1".equals(cardFlag) && "2".equals(c1c2Flag)){ //单办附卡
					// 身份证和姓名匹配一致
					if ("0".equals(kexinCheckMsgStr.substring(0, 1))) {
						wic.setKexinCheckFlag("1"); 
					} else {
						wic.setKexinCheckFlag("-1"); 
					}
					break;
				} else if("0".equals(cardFlag) && !"2".equals(c1c2Flag) && kexinCheckMsgStr.length() > 1){//主附同审或单办主卡
					// 身份证和姓名匹配一致
					if ("0".equals(kexinCheckMsgStr.substring(0, 1))) {
						wic.setKexinCheckFlag("1"); 
					} else {
						wic.setKexinCheckFlag("-1"); 
						break;
					}
				}
			}
			/*Map<String, String> kexinCheckMsgMap = kexinCheckMsgList.get(0);
			String  kexinCheckMsgStr= kexinCheckMsgMap.get("authResult");
			if(kexinCheckMsgStr != null){
				wic.setKexinCheckFlag("0".equals(kexinCheckMsgStr.substring(0, 1)) ? "1" : "-1");
			} */
		}
		
		//一次决策授信额度  一个条码决策一次,不能只查主卡
		List<Map<String, Object>> ficoMsgList = opasFicoSdBlazeDaox.selectFicoMsgByAppId(on1.getAppId());//null
		if(ficoMsgList.size() > 0){
			BigDecimal proposedLmt = (BigDecimal) ficoMsgList.get(0).get("proposedLmt");
			wic.setProposedLimit(proposedLmt != null?proposedLmt.doubleValue():-1);

			// 20190807pad版本fico捞回
			String decisionDesp = ficoMsgList.get(0).get("decisionDesp")!=null?ficoMsgList.get(0).get("decisionDesp").toString():"";
			if(decisionDesp.contains("评分标准（FICO），人工审查") || (decisionDesp.contains("评分标准(FICO),人工审查"))){
				wic.setFicoLaoHui(true);
			}
		}
		
		// 风险名单是否命中
		List<Map<String, String>> fqzRiskGategory = opasFqzResultDaox.selectFqzResultByAppId(on1.getAppId());
		for (Map<String, String> map : fqzRiskGategory) {
			if(map==null || map.get("fqzRiskGategory")==null || "".equals(map.get("fqzRiskGategory"))){
				continue;
			}else if(CacheConsts.fqzRiskGategoryList.contains(map.get("fqzRiskGategory"))){
				wic.setRiskMatch(true);
				if("Z01_6".equals(map.get("fqzRiskGategory"))){
					wic.setUnSafeOrga(true);//命中推广类风险名单
				}else if("Z01_1".equals(map.get("fqzRiskGategory"))){
					wic.setIdDataMatch(true);//命中身份类风险名单
				}else if("Z01_2".equals(map.get("fqzRiskGategory"))){
					wic.setPhoneDataMatch(true);//命中电话类风险名单
				}
			}
			if("Z01_5".equals(map.get("fqzRiskGategory"))){
				wic.setSameIndustryNull(false);//同业黑名单不为空
			}
		}
		// 从内部数据监测查询判断是否洗钱风险命中
		Map<String, String> jsonMap = null;
		try {
			jsonMap = queryTeam(appIdThd);
		} catch (Exception e) {
			log.info(e.getMessage());
		}
		if(jsonMap==null){
			jsonMap = new HashMap<String, String>();
		}

		// 是否重复申请以相关代码拒绝 20191114
		String[] hisAppIdArray = {on1.getAppId()};
		String appIdsStr = jsonMap.get("OPAS_BIZ_INP_APPL_C1-C1_IDNBR");
		if(appIdsStr != null && !"".equals(appIdsStr)){
			hisAppIdArray = (on1.getAppId() + "|" +appIdsStr).split("\\|");
		}
		Map hisAppIdArrayParams = new HashMap();
		hisAppIdArrayParams.put("hisAppIdArray", hisAppIdArray);
		List<Map<String,String>> refuseCodeMsgList = opasBizApprovalDaox.selectByHisAppIds(hisAppIdArrayParams);
		
		if (refuseCodeMsgList != null && refuseCodeMsgList.size() > 0) {
			//初始化存放拒绝码的list
			List<String> refuseCodeList = new ArrayList<String>();
			wic.setReApplyFlag(true);
			for (Map<String,String> map : refuseCodeMsgList) {
				if(map.get("refuseCode1")!=null){
					refuseCodeList.add(map.get("refuseCode1"));
				}
				if(map.get("refuseCode2")!=null){
					refuseCodeList.add(map.get("refuseCode2"));
				}
				if(map.get("refuseCode3")!=null){
					refuseCodeList.add(map.get("refuseCode3"));
				}
			}
			if(refuseCodeList!=null){
				if (judgeRefuseStatus(refuseCodeList)){
					wic.setRefuseWithCode(true);
				}
				// 20181025新增易达金高风险H1命中相关拒绝码拒绝
				if(judgeRefuseStatusYdjH1(refuseCodeList)){
					wic.setRefuseWithCodeYdjH1(true);
				}
			}
		} else {
			wic.setReApplyFlag(false);
			wic.setRefuseWithCode(false);
		}
		
		String fqzxqfxmz = jsonMap.get("FOREIGN_INDEX-AML");
		if (fqzxqfxmz != null && !"".equals(fqzxqfxmz) ) {
			wic.setRiskMatch(true);
		}
		// 20181026 from wjf 新增易达金高风险H1灰名单命中
		String graylist = jsonMap.get("OPAS_FILE_APPLICATION_DETAIL-CRED_NO");
		if (graylist != null && "1".equals(graylist)) {
			wic.setCredNo(true);
		}
		//手机,直系亲属手机
		// 关联性检查-手机 1为有异常
		String relatedcheckTel = jsonMap.get("OPAS_BIZ_INP_APPL_C1-C1_MOBILE");
		// 关联性检查-直系亲属手机号码 1为有异常
		String relatedcheckNearlyTelno = jsonMap.get("OPAS_BIZ_INP_APPL_C1-XMOBIL");
		if((relatedcheckTel != null && "1".equals(relatedcheckTel)) || (relatedcheckNearlyTelno != null && "1".equals(relatedcheckNearlyTelno))){
			wic.setReleMsgCheck(true);
		}
		
		// ID有效期状态
//		List<String> idStatusList = opasFqzResultDaox.selectFqzIdStatusByAppId(on1.getAppId());
//		wic.setIdTimeLimitType(idStatusList.size()==0?"3":idStatusList.get(0));
		wic.setIdTimeLimitType((jsonMap.get("OPAS_BIZ_INP_APPL_C1-C5_IDTE1")==null||"".equals(jsonMap.get("OPAS_BIZ_INP_APPL_C1-C5_IDTE1").trim()))?"5":jsonMap.get("OPAS_BIZ_INP_APPL_C1-C5_IDTE1").trim());
		//是否命中内部数据检查
		String innerRiskCN = jsonMap.get("OPAS_INNER_RISKINFO_LIST-COMPANY_NAME");
		String innerRiskCT = jsonMap.get("OPAS_INNER_RISKINFO_LIST-COMPANY_TEL");
		String innerRiskSP = jsonMap.get("OPAS_INNER_RISKINFO_LIST-SPREAD_PER");
		String innerRiskSN = jsonMap.get("OPAS_INNER_RISKINFO_LIST-SPREAD_NO");
		String innerRiskSO = jsonMap.get("OPAS_INNER_RISKINFO_LIST-SPREAD_ORG");
		String innerRiskCNo = jsonMap.get("OPAS_INNER_RISKINFO_LIST-CERTIFI_NO");
		if (!"".equals(innerRiskCN) && innerRiskCN != null||!"".equals(innerRiskCT) && innerRiskCT != null||!"".equals(innerRiskSP) && innerRiskSP != null||
				!"".equals(innerRiskSN) && innerRiskSN != null||!"".equals(innerRiskSO) && innerRiskSO != null||!"".equals(innerRiskCNo) && innerRiskCNo != null) {
			wic.setInternalDataMatch(true);
		}

		// 住房公积金缴费单位模糊匹配结果
		String zfConame = jsonMap.get("OPAS_PBOC_PUBLIC_ACCFUND-COMPANY");
		if(zfConame!=null && !"".equals(zfConame.trim())){
			wic.setZfPayConame(true);
		}
		
		//反欺诈决策等级
		List<String> decisionLvList = opasFqzResultDaox.selectFqzDecisionLvByAppId(on1.getAppId());
		wic.setDecisionLevel(decisionLvList.size()==0?"B":decisionLvList.get(0));
		// 网申欺诈模型评分
		List<String> wsScoreList = opasFqzResultDaox.selectFqzWsScoreByAppId(on1.getAppId());
		String wsScoreStr = wsScoreList.size()>0? wsScoreList.get(0):"";
		// 非网申欺诈模型评分
		List<String> fwsScoreList = opasFqzResultDaox.selectFqzFwsScoreByAppId(on1.getAppId());
		String fwsScoreStr = fwsScoreList.size()>0? fwsScoreList.get(0):"";
		double wsScore;
		double fwsScore;
		try {
			wsScore = Double.valueOf(wsScoreStr!=null && !"".equals(wsScoreStr) ? wsScoreStr:"999");
		} catch (NumberFormatException e) {
			wsScore=999d;
			log.info("网申模型评分数字类型解析出错",e);
		}
		wic.setWsModelScore(wsScore);
		try {
			fwsScore = Double.valueOf(fwsScoreStr!=null && !"".equals(fwsScoreStr) ? fwsScoreStr:"999");
		} catch (NumberFormatException e) {
			fwsScore=999d;
			log.info("非网申模型评分数字类型解析出错",e);
		}
		wic.setFwsModelScore(fwsScore);
		//客户洗钱风险等级是否命中 20190108
		if(nullOrSpace(on1.getC1Idnbr()) && on1.getC1Idtype() != null && "01".equals(on1.getC1Idtype())){
			List<String> custRiskMsgList = opasFqzResultDaox.selectCustRiskMsg(on1.getC1Idnbr());
			if(custRiskMsgList.size()>0 && nullOrSpace(custRiskMsgList.get(0))){
				// 等于3和4 为是//ETC修改2也命中洗钱风险等级
				wic.setCustRisk("2".equals(custRiskMsgList.get(0).trim()) || "3".equals(custRiskMsgList.get(0).trim()) || "4".equals(custRiskMsgList.get(0).trim()));
			}
		}
		//在网时长状态
		List<String> inNetTypeList = opasFqzResultDaox.selectInNetTimeTypeByAppId(appIdThd);
		wic.setInNetMsgExist(inNetTypeList.size()>0);
		wic.setInNetTimeType(inNetTypeList.size()==0?"6":inNetTypeList.get(0));
		
		/*log.error("网络第三方数据:");
		log.error("网申模型评分"+wic.getFwsModelScore()+"网申模型评分"+wic.getWsModelScore());
		log.error("$是否为不安全推广员:"+wic.isUnSafeOrga()+"$蚂蚁是否命中:"+wic.isHitAntData()
		+"$百融数据是否命中:"+wic.isHitBaiRong()+"$同业黑名单星级:"+wic.getSTAR_LEVLE()+"$同业黑名单是否为空:"+wic.isSameIndustryNull()
		+"$是否为重复申请:"+wic.isReApplyFlag()+"$是否以相关代码拒绝:"+wic.isRefuseWithCode()
		+"$公安匹配一致:"+wic.getPoliceMatchFlag()+"$一次决策授信额度:"+wic.getProposedLimit()
		+"$ID有效期状态:"+wic.getIdTimeLimitType()+"$风险信息检查名单库是否命中:"+wic.isRiskMatch()
		+"$内部数据检查风险信息是否命中:"+wic.isInternalDataMatch()
		+"$易达金高风险H1命中相关拒绝码拒绝:"+wic.isRefuseWithCodeYdjH1()+"$命中身份类风险名单:"+wic.isIdDataMatch()
		+"$命中电话类风险名单:"+wic.isPhoneDataMatch()+"$易达金高风险H1灰名单命中:"+wic.isCredNo()
		+"$反欺诈决策等级:"+wic.getDecisionLevel()+"$易达金手机,直系亲属手机命中:"+wic.isReleMsgCheck()
		+"$手机实名认证是否存在:"+wic.isPhoneMsgExist()+"$是否手机实名认证:"+wic.isPhoneNameMatch()
		+"$在网时长状态是否存在"+wic.isInNetMsgExist()+"$在网时长状态"+wic.getInNetTimeType()
		+"$洗钱风险是否命中状态"+wic.isCustRisk()+"住房公积金缴费单位模糊匹配结果"+wic.isZfPayConame()
		);*/
		return wic;
	}

	/**
	 * 组装天御分模型
	 * 
	 * yuanquan 2018.10.25
	 * 向天御分模型中添加两个字段
	 * 不触发天宇分反欺诈拒绝规则但属于高危分段(非网)\不触发天宇分反欺诈拒绝规则但属于高危分段(非网申)
	 */
	public TYDataAnalyze createTYDataAnalyze(Opasbizinpapplc1 on1,String appIdThd) throws CoreException {
		TYDataAnalyze tydata = new TYDataAnalyze();
		tydata.setTyScore(-1);
		tydata.setHitTyRisk(false);
		tydata.setQuerySuccessScoreNotFound(true);
		tydata.setHitTyRiskIdCheck(false);
		List<Map<String,String>> tyScoreCodeFoundList = opasTyMsgDaox.queryTyScoreCodeFound(appIdThd);
		if(null!=tyScoreCodeFoundList && tyScoreCodeFoundList.size()>0){
			
			Map<String,String> tyScoreCodeFoundMap = tyScoreCodeFoundList.get(0);
			if(null!=tyScoreCodeFoundMap && null!=tyScoreCodeFoundMap.get("riskscore")){
				tydata.setTyScore(Double.parseDouble(tyScoreCodeFoundMap.get("riskscore")));
			}
			if(null!=tyScoreCodeFoundMap && null!=tyScoreCodeFoundMap.get("code") && "0".equals((String)tyScoreCodeFoundMap.get("code")) && null!=tyScoreCodeFoundMap.get("found") && (Integer.parseInt(tyScoreCodeFoundMap.get("found"))==-1) ){
				tydata.setQuerySuccessScoreNotFound(true);
			}else{
				tydata.setQuerySuccessScoreNotFound(false);
			}
			
		}
		
		
		List<Map<String,String>> tyRiskLvs = opasTyMsgDaox.queryTyRiskLv(appIdThd);
		for (Map<String, String> map : tyRiskLvs) {
			if(map.get("riskcode") != null && !"".equals(map.get("riskcode").trim())){
				tydata.setHitTyRisk(true);
				// 天域风险码
				if("5".equals(map.get("riskcode").trim())){
					tydata.setHitTyRiskIdCheck(true);
				}
			}
		}
		/*log.error("天御分校验结果:");
		log.error("$天御得分: "+tydata.getTyScore()+"天御是否命中预设风险等级"+tydata.isHitTyRisk()
		+"天御查询结果返回查询成功且天御分为未查得"+tydata.isQuerySuccessScoreNotFound());*/
		return tydata;
	}
	
	/**
	 * 组装企业网信息只包含企业网经营状态字段
	 * add by yuanquan 2018.10.25
	 */
	public EnterpriseNetwork createEnterpriseNetwork(Opasbizinpapplc1 on1,String appIdThd)throws CoreException{
		EnterpriseNetwork epn = new EnterpriseNetwork();
		List<Map<String,String>>  entStatusMapList  = opasEnterpriseNetworkDaox.queryEnterpriseNetworkStatus(appIdThd);
		epn.setEnOperationStatusImproper(false);//默认不触碰规??
		
		if(null!=entStatusMapList && entStatusMapList.size()>0){
			Map<String,String> entStatusMap  = entStatusMapList.get(0);
			if(null!=entStatusMap){
				
				String entStatus = null==entStatusMap.get("entstatus")?"":entStatusMap.get("entstatus");
				
				if (!"在营（开业）".equals(entStatus.trim()) && !"开业".equals(entStatus.trim()) && !"存续".equals(entStatus.trim())
						&& !"正常".equals(entStatus.trim()) && !"正常执业".equals(entStatus.trim()) && !"正常经营".equals(entStatus.trim())){
					
					epn.setEnOperationStatusImproper(true);
				}else{
					epn.setEnOperationStatusImproper(false);
				}	
				
			}
		}
		/*log.error("企业网信息:");
		log.error("$企业网经营状态非正常: "+epn.isEnOperationStatusImproper());*/
		return epn;
	}
	
	/**
	 * 组装预审模型
	 * add by wjf 2019.04.17
	 */
	@Override
	public YuShenInfo createYuShenModel(Opasbizinpapplc1 on1, String appIdThd) throws CoreException {
		YuShenInfo yuShenInfo = new YuShenInfo();
		List<Map<String,String>>  yuShenInfoList  = opasYuShenDaox.queryYuShenInfoByAppId(on1.getAppId());
		if(yuShenInfoList.size()>0){
			Map<String,String> approveCodeMap = yuShenInfoList.get(0);
			if(approveCodeMap != null && approveCodeMap.get("approveCode") != null){
				yuShenInfo.setRefuseCode(approveCodeMap.get("approveCode"));
			}
		}else{
			yuShenInfo.setRefuseCode("");
		}
		/*log.error("预审信息:");
		log.error("$预审拒绝代码: "+yuShenInfo.getRefuseCode());*/
		return yuShenInfo;
	}
	

	/**
	 * 组装Pad件信息模型
	 * add by wjf 2019.09.23
	 */
	@Override
	public PadInfor creatPadInforModel(Opasbizinpapplc1 on1) throws CoreException {
		// 设备指纹开发任务
		PadInfor padInfor = new PadInfor();
		padInfor.setOutCodeIdCount4Week(0);
		padInfor.setOutCodeIdCount4Month(0);
		padInfor.setHitPadRisk(false);
		padInfor.setBodyCheck(true);
		padInfor.setPhotoCheck(true);
		padInfor.setPhotoCheckNew("0");
		List<Map<String, String>> padInforList = opasPadDaox.queryPadInforByAppId(on1.getAppId());
		if(padInforList.size() > 0){
			Map<String, String> padInforMap = padInforList.get(0);
			//设备指纹不同申请人证件号码次数
			Map<String,String> paramMap = new HashMap<String,String>();
			paramMap.put("deviceId", padInforMap.get("deviceId"));
			paramMap.put("c1Idnbr", on1.getC1Idnbr());
			//查询当前设备Id下决策时间7天内所有申请件的不同申请人证件号数量
			int idNumberCount4week = opasPadDaox.queryIdNumberCountByDeviceId4Week(paramMap);
			padInfor.setOutCodeIdCount4Week(idNumberCount4week);
			//查询当前设备Id下决策时间30天内所有申请件的不同申请人证件号数量
			int idNumberCount4Month = opasPadDaox.queryIdNumberCountByDeviceId4Month(paramMap);
			padInfor.setOutCodeIdCount4Month(idNumberCount4Month);
			//设备高风险命中情况
			String platform = padInforMap.get("platform");
			Map<String,String> riskParamMap = new HashMap<String,String>();
			riskParamMap.put("platform", platform);
			riskParamMap.put("appId", on1.getAppId());
			List<Map<String,String>> padRiskInfoList = null ;
			if(platform != null && !"".equals(platform.trim())){
				switch (platform) {
				case "AND":
					padRiskInfoList = opasPadDaox.queryPadANDRiskInfoByAppId(on1.getAppId());
					break;
				case "IOS":
					padRiskInfoList = opasPadDaox.queryPadIOSRiskInfoByAppId(on1.getAppId());
					break;
				case "WAP":
					padRiskInfoList = opasPadDaox.queryPadWAPRiskInfoByAppId(on1.getAppId());
					break;
				case "WEB":
					padRiskInfoList = opasPadDaox.queryPadWEBRiskInfoByAppId(on1.getAppId());
					break;
				}
				if(padRiskInfoList.size()>0){
					Map<String,String> padRiskInfoMap = padRiskInfoList.get(0);
					for(String padRiskInfoValue : padRiskInfoMap.values()){
						if(nullOrSpace(padRiskInfoValue) && "1".equals(padRiskInfoValue)){
							padInfor.setHitPadRisk(true);
							break;
						}
					}
				}
			}
		}
		List<String> bodyCheckList = opasPadDaox.queryBodyCheckByAppId(on1.getAppId());
		if(bodyCheckList.size() > 0 && bodyCheckList.get(0) != null && "0".equals(bodyCheckList.get(0).trim())){
			padInfor.setBodyCheck(false);
		}
		List<String> photoCheckList = opasPadDaox.queryPhotoCheckByAppId(on1.getAppId());
		if(photoCheckList.size() > 0 && photoCheckList.get(0) != null){
			if("系统判断为不同人".equals(photoCheckList.get(0).trim())){
				padInfor.setPhotoCheckNew("-1");
			}else if("系统判断为同一人".equals(photoCheckList.get(0).trim())){
				padInfor.setPhotoCheckNew("1");
			}
		}
		/*log.error("PAD信息:");
		log.error("$Pad人像比对是否为活体"+padInfor.isBodyCheck()+"$审批系统人像比对是否为本人"+padInfor.isPhotoCheck()+"$7天不同申请人证件号数量: "+padInfor.getOutCodeIdCount4Week()+"$30天不同申请人证件号数量"+padInfor.getOutCodeIdCount4Month()
		+"$是否命中设备高风险"+padInfor.isHitPadRisk());*/
		return padInfor;
	}
	/**
	 * 组装区域数据模型
	 * add by wjf 2019.04.17
	 */
	@Override
	public QuyushujuInfo createQysjInfoModel(Opasbizinpapplc1 on1, String appIdThd) throws CoreException {
		QuyushujuInfo qysjInfo = new QuyushujuInfo();
//		qysjInfo.setAreaCode("无");
		qysjInfo.setGaoXinScore(999d);
		qysjInfo.setCanbaoStatus(false);//参保状态异常
		qysjInfo.setJobStatus(false);//在职状态异常
		qysjInfo.setPayMon(99);// 流水号日期-最近缴费日期的月数
		qysjInfo.setAreaCode(on1.getAppId().substring(8, 10));
		qysjInfo.setCheckSuccess(false);
		qysjInfo.setXmSbcheck(false);//厦门社保数据是否查得不为空
		qysjInfo.setXmGjjcheck(false);//厦门公积金数据是否查得不为空
		qysjInfo.setGjjCanbStatus(false);//厦门公积金参保状态是否异常
		String appIdMonStr = "20"+on1.getAppId().substring(0, 4);
		if("32".equals(qysjInfo.getAreaCode())){ //深圳
			List<Map<String,String>>  szScoreInfoList = opasQysjDaox.querySzScoreInfoByAppId(on1.getAppId());
			if(szScoreInfoList.size()>0){
				qysjInfo.setCheckSuccess(true);
				Map<String, String> szScoreInfoMap = szScoreInfoList.get(0);
				// 高信分
				if(szScoreInfoMap.get("score") != null && !"".equals(szScoreInfoMap.get("score").trim())){
					qysjInfo.setGaoXinScore(Double.valueOf(szScoreInfoMap.get("score")));
				}else{
					qysjInfo.setGaoXinScore(999d);
				}
			}
			List<Map<String,String>>  szReportInfoList = opasQysjDaox.querySzReportInfoByAppId(on1.getAppId());
			if(szReportInfoList.size()>0){
				qysjInfo.setCheckSuccess(true);
				Map<String, String> szReportInfoMap = szReportInfoList.get(0);
				// 参保状态
				String currentStatus = szReportInfoMap.get("currentStatus") != null ? szReportInfoMap.get("currentStatus") : "";
				if("正常参保".equals(currentStatus)){
					qysjInfo.setCanbaoStatus(true);
				}
				// 流水号日期-最近缴费日期
				try {
					String infoDateMon = szReportInfoMap.get("infoDate").substring(0, 6);
					int payMon = (Integer.parseInt(appIdMonStr.substring(0,4))-Integer.parseInt(infoDateMon.substring(0,4))
							)*12+(Integer.parseInt(appIdMonStr.substring(4))-Integer.parseInt(infoDateMon.substring(4)));
					qysjInfo.setPayMon(payMon);
				} catch (Exception e) {
					log.info("最新交费日期非日期格式: "+ e);
					qysjInfo.setPayMon(99);
				}
			}
		}else if("36".equals(qysjInfo.getAreaCode())){ //厦门
			List<Map<String,String>>  xmGjjInfoList  = opasQysjDaox.queryXmGjjInfoByAppId(on1.getAppId());
			// 公积金信息
			if(xmGjjInfoList.size()>0){
				qysjInfo.setCheckSuccess(true);
				qysjInfo.setXmGjjcheck(true);
				Map<String, String> xmgjjInfoMap = xmGjjInfoList.get(0);
				// 公积金参保状态
				String gjjSatus = xmgjjInfoMap.get("grzhzt") != null ? xmgjjInfoMap.get("grzhzt") : "";
				if("01".equals(gjjSatus)){
					qysjInfo.setGjjCanbStatus(true);
				}
				try {
					String infoDateMon = xmgjjInfoMap.get("zjjjrq").substring(0, 6);
					int payMon = (Integer.parseInt(appIdMonStr.substring(0,4))-Integer.parseInt(infoDateMon.substring(0,4))
							)*12+(Integer.parseInt(appIdMonStr.substring(4))-Integer.parseInt(infoDateMon.substring(4)));
					qysjInfo.setPayMon(payMon);
				} catch (Exception e) {
					log.info("最新交费日期非日期格式: "+ e);
					qysjInfo.setPayMon(99);
				}
			}
			// 社保信息
			List<Map<String,String>>  xmInfoList  = opasQysjDaox.queryXmInfoByAppId(on1.getAppId());
			if(xmInfoList.size()>0){
				qysjInfo.setCheckSuccess(true);
				qysjInfo.setXmSbcheck(true);
				Map<String, String> xmInfoMap = xmInfoList.get(0);
				// 社保在职状态
				String isOnJob = xmInfoMap.get("isOnJob") != null ? xmInfoMap.get("isOnJob") : "";
				if("是".equals(isOnJob)){
					qysjInfo.setJobStatus(true);
				}
				// 社保参保状态
				String insuredSatus = xmInfoMap.get("insuredSatus") != null ? xmInfoMap.get("insuredSatus") : "";
				if("参保缴费".equals(insuredSatus)){
					qysjInfo.setCanbaoStatus(true);
				}
				// 社保流水号日期-最近缴费日期
				// 20200608优先取公积金缴费日期
				if(xmGjjInfoList.size() == 0){
					try {
						String infoDateMon = xmInfoMap.get("latestPaymentDate").substring(0, 6);
						int payMon = (Integer.parseInt(appIdMonStr.substring(0,4))-Integer.parseInt(infoDateMon.substring(0,4))
								)*12+(Integer.parseInt(appIdMonStr.substring(4))-Integer.parseInt(infoDateMon.substring(4)));
						qysjInfo.setPayMon(payMon);
					} catch (Exception e) {
						log.info("最新交费日期非日期格式: "+ e);
						qysjInfo.setPayMon(99);
					}
				}
			}
			
		} else if("22".equals(qysjInfo.getAreaCode())){ //杭州
			List<Map<String,String>>  hzDateInfoList = opasQysjDaox.queryHzInfoByAppId(on1.getAppId());
			if(hzDateInfoList.size()>0){
				qysjInfo.setCheckSuccess(true);
				Map<String, String> hzInfoMap = hzDateInfoList.get(0);
				// 参保状态（公积金缴存状态）
				String status = hzInfoMap.get("status") != null ? hzInfoMap.get("status") : "";
				if("正常".equals(status)){
					qysjInfo.setCanbaoStatus(true);
				}
				// 流水号日期-最近缴存年月
				try {
					String infoDateMon = hzInfoMap.get("payYM").substring(0, 6);
					int payMon = (Integer.parseInt(appIdMonStr.substring(0,4))-Integer.parseInt(infoDateMon.substring(0,4))
							)*12+(Integer.parseInt(appIdMonStr.substring(4))-Integer.parseInt(infoDateMon.substring(4)));
					qysjInfo.setPayMon(payMon);
				} catch (Exception e) {
					log.info("最新交费日期非日期格式: "+ e);
					qysjInfo.setPayMon(99);
				}
			}
		} else if("26".equals(qysjInfo.getAreaCode())){ //温州
			List<Map<String,String>>  wzDateInfoList = opasQysjDaox.queryWzInfoByAppId(on1.getAppId());
			if(wzDateInfoList.size()>0){
				qysjInfo.setCheckSuccess(true);
				Map<String, String> wzInfoMap = wzDateInfoList.get(0);
				// 参保状态（公积金缴存状态）
				String status = wzInfoMap.get("status") != null ? wzInfoMap.get("status") : "";
				if("正常".equals(status)){
					qysjInfo.setCanbaoStatus(true);
				}
				// 流水号日期-最近缴存年月
				try {
					String infoDateMon = wzInfoMap.get("payYM").substring(0, 6);
					int payMon = (Integer.parseInt(appIdMonStr.substring(0,4))-Integer.parseInt(infoDateMon.substring(0,4))
							)*12+(Integer.parseInt(appIdMonStr.substring(4))-Integer.parseInt(infoDateMon.substring(4)));
					qysjInfo.setPayMon(payMon);
				} catch (Exception e) {
					log.info("最新交费日期非日期格式: "+ e);
					qysjInfo.setPayMon(99);
				}
			}
		} else if("63".equals(qysjInfo.getAreaCode())){ //银川
			List<Map<String,String>>  ycDateInfoList = opasQysjDaox.queryYcInfoByAppId(on1.getAppId());
			if(ycDateInfoList.size()>0){
				qysjInfo.setCheckSuccess(true);
				Map<String, String> ycInfoMap = ycDateInfoList.get(0);
				// 参保状态（公积金缴存状态）
				String status = ycInfoMap.get("status") != null ? ycInfoMap.get("status") : "";
				if("11".equals(status)){
					qysjInfo.setCanbaoStatus(true);
				}
				// 流水号日期-最近缴存年月
				try {
					String infoDateMon = ycInfoMap.get("payYM").substring(0, 6);
					int payMon = (Integer.parseInt(appIdMonStr.substring(0,4))-Integer.parseInt(infoDateMon.substring(0,4))
							)*12+(Integer.parseInt(appIdMonStr.substring(4))-Integer.parseInt(infoDateMon.substring(4)));
					qysjInfo.setPayMon(payMon);
				} catch (Exception e) {
					log.info("最新交费日期非日期格式: "+ e);
					qysjInfo.setPayMon(99);
				}
			}
		} else if("25".equals(qysjInfo.getAreaCode())){ //宁波
			List<Map<String,String>>  nbDateInfoList = opasQysjDaox.queryNbInfoByAppId(on1.getAppId());
			if(nbDateInfoList.size()>0){
				qysjInfo.setCheckSuccess(true);
				Map<String, String> nbInfoMap = nbDateInfoList.get(0);
				// 参保状态（公积金缴存状态）
				String ybzt = nbInfoMap.get("ybzt") != null ? nbInfoMap.get("ybzt") : "否";
				String ylzt = nbInfoMap.get("ylzt") != null ? nbInfoMap.get("ylzt") : "否";
				if("参保缴费".equals(ybzt) || "参保缴费".equals(ylzt)){
					qysjInfo.setCanbaoStatus(true);
				}
				// 流水号日期-最近缴存年月
				try {
					String infoDateMon = nbInfoMap.get("zhycjjsj").substring(0, 6);
					int payMon = (Integer.parseInt(appIdMonStr.substring(0,4))-Integer.parseInt(infoDateMon.substring(0,4))
							)*12+(Integer.parseInt(appIdMonStr.substring(4))-Integer.parseInt(infoDateMon.substring(4)));
					qysjInfo.setPayMon(payMon);
				} catch (Exception e) {
					log.info("最新交费日期非日期格式: "+ e);
					qysjInfo.setPayMon(99);
				}
			}
		}
		/*log.error("区域数据信息:");
		log.error("$区域编码: "+qysjInfo.getAreaCode() + "$深圳高信分: "+qysjInfo.getGaoXinScore()
		 + "$参保状态: "+ qysjInfo.isCanbaoStatus() + "$厦门在职状态: "+ qysjInfo.isJobStatus()
		 + "$交费距今月份: "+ qysjInfo.getPayMon()
		);*/
		return qysjInfo;
	}

	public boolean nullOrSpace(String field) {
		if (field == null || field.trim().length() == 0) {
			return false;
		} else {
			return true;
		}
	}

	// 分别根据15和18位身份证向下取整得年龄
	public int getAge(String idno, String idtype) {
		String birthday = null;
		int birthy = 0;
		if ("01".equals(idtype.trim())) {
			birthday = idno.substring(6, 14);
			birthy = Integer.parseInt(birthday.substring(0, 4));
		} else if ("02".equals(idtype.trim())) {
			birthday = idno.substring(6, 12);
			birthy = Integer.parseInt(birthday.substring(0, 2)) + 1900;
		}
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) + 1;
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		int birthm = Integer.parseInt(birthday.substring(4, 6));
		int birthd = Integer.parseInt(birthday.substring(6, 8));
		if (month > birthm || month == birthm && day > birthd) {
			return year - birthy;
		} else {
			return year - birthy - 1;
		}
	}
	
	public boolean hitBairong(Object model) throws SecurityException, NoSuchMethodException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		boolean flag = false;
		Field[] field = model.getClass().getDeclaredFields();
		for (int i = 0; i < field.length; i++) {
			String name = field[i].getName();
			if (name.startsWith("sl")) {
				name = name.substring(0, 1).toUpperCase().concat(name.substring(1));
				Method m = model.getClass().getMethod("get" + name);
				String value = (String) m.invoke(model);
				if (nullOrSpace(value)) {
					flag = true;
					break;
				} else {
					continue;
				}
			}
		}
		return flag;
	}
	public boolean judgeStatus(List<String> list){
		boolean flag = false;
		for(String string : list){
			if(CacheConsts.abcodeList.contains(string)){
				flag = true;
				break;
			}
		}
		return flag;
	}
	// 易达金和标准卡通用的重复申请判断
	public boolean judgeRefuseStatus(List<String> list){
		boolean flag = false;
		for(String string : list){
//			System.out.print("test2:"+string);
//			System.out.print("test3:"+CacheConsts.refuseCodeList);
			if(CacheConsts.refuseCodeList.contains(string)){
				flag = true;
				break;
			}
		}
		return flag;
	}
	// 20181025 from wjf 易达金H1重复申请异常判断
	public boolean judgeRefuseStatusYdjH1(List<String> list){
		boolean flag = false;
		for(String string : list){
//			System.out.print("test2:"+string);
//			System.out.print("test3:"+CacheConsts.refuseCodeListYdjH1);
			if(CacheConsts.refuseCodeListYdjH1.contains(string)){
				flag = true;
				break;
			}
		}
		return flag;
	}
	private boolean judgeBzkStatus(List<String> list) {
		boolean flag = false;
		for(String string : list){
			if(CacheConsts.abcodeBzkList.contains(string)){
				flag = true;
				break;
			}
		}
		return flag;
	}
	private boolean judgeBzkH1Status(List<String> list) {
		boolean flag = false;
		for(String string : list){
			if(CacheConsts.abcodeBzkH1List.contains(string)){
				flag = true;
				break;
			}
		}
		return flag;
	}

	private boolean judgeStatusH_11(String closeCode) {
		return CacheConsts.abcodeYdjH11List.contains(closeCode);
	}

	private boolean judgeStatusH1_9(String closeCode) {
		return CacheConsts.abcodeYdjH19List.contains(closeCode);
	}
	
	public static String Date2String(Date date, String parse) throws ParseException{
		SimpleDateFormat df = new SimpleDateFormat(parse);
		return df.format(date);
	}

	/**
	 * 策略层级获取losemsg字段的值
	 * @param on1
	 * @return
	 * @throws CoreException
	 */
	public boolean getLosemsgValue(Opasbizinpapplc1 on1) throws CoreException {
		String ydjFlag = on1.getYdjFlag();
		String matchingCardFlag = on1.getMatchingCardFlag();// ydjFlag=1,matchingCardFlag=1==== 配发卡
		String c1c2Flag = on1.getC1c2Flag();
		String isWebBolt = on1.getInputChannel();
				//appId.substring(6,7); //20181022 from wjf
		String myFlag = "";
		boolean isYDJ = "1".equals(ydjFlag);
		if (isYDJ) {// 易达金卡---9项 
			//20181022 from wjf 非网申9项,网申8项
			if("B".equals(isWebBolt)){// 网申进件--8项
				myFlag = "8"; 
			}else{
				myFlag = "9";
			}
		} else {// 标准卡及易达金配发卡
			if ("3".equals(c1c2Flag)) {// 单独申请主卡
				if ("B".equals(isWebBolt)) {// 网申进件--8项
					myFlag = "8"; 
				} else {// 非网申进件  ---11项
					myFlag = "11";
				}
			} else if ("2".equals(c1c2Flag)) {// 单独申请附属卡
				myFlag = "3";
			} else {// 主附同申--11项（同单独申请主卡非网申进件）
				myFlag = "11";
			}
		}
		return getApplyInfo(on1,myFlag,isYDJ);
	}
	
	public boolean getApplyInfo(Opasbizinpapplc1 on1, String myFlag,boolean isYDJ) throws CoreException {
		String c1Cname = on1.getC1Cname();// 主卡中文姓名
		String c1Ename = on1.getC1Ename();// 主卡英文姓名
		String c1Idnbr = on1.getC1Idnbr();// 证件号码
		String c1Mobile = on1.getC1Mobile();// 手机号码
		String c1Coname = on1.getC1Coname();// 现单位全称
		String c1Cotel = on1.getC1Cotel();// 单位电话
		String c1Rename = on1.getC1Rename();// 配偶或直系亲属姓名
		String c1Retel = on1.getC1Retel();// 配偶或直系亲属联系电话
		String c1Remobil = on1.getC1Remobil();// 配偶或直系亲属联系手机
		String c1RetelOrPho = null;// 配偶或直系亲属姓名 + 配偶或直系亲属联系电话
		if ((c1Remobil != null && !"".equals(c1Remobil)) || (c1Retel != null && !"".equals(c1Retel))) {
			c1RetelOrPho = "1";
		}
		String c4Abtype = "1".equals(on1.getC4Abtype()) ? "1" : null;// 亲见签名
		RevCompInfo revCompInfo = revCompInfoService.selectByPrimaryKey(on1.getAppId());
		String applyInputfull = null;
		String signFull = null;
		if(revCompInfo!=null){
			applyInputfull = revCompInfo.getApplyInputfull();// 申请人抄录文字
			signFull = revCompInfo.getSignFull();// 主卡申请人签名
		}

		if ("11".equals(myFlag)) {// 标卡及配发卡---主卡---非网申
			int m = 0;
			String[] string = new String[] { c1Cname, c1Ename, c1Idnbr, c1Mobile, c1Coname, c1Cotel, c1RetelOrPho,
					c1Rename, c4Abtype, applyInputfull, signFull };
			for (String str : string) {
				if (requiredFieldsCheck(str,"?",isYDJ)) {
					m++;
				}
			}
			if (m < 11) {
				return false;
			}
		} else if ("8".equals(myFlag)) {// 标卡及配发卡---主卡---网申
			int n = 0;
			String[] string1 = new String[] { c1Cname, c1Ename, c1Idnbr, c1Mobile, c1Coname, c1Cotel, c1RetelOrPho,
					c1Rename };
			for (String str1 : string1) {
				if (requiredFieldsCheck(str1,"?",isYDJ)) {
					n++;
				}
			}
			if (n < 8) {
				return false;
			}
		} else if ("3".equals(myFlag)) {// 标卡及配发卡---单独申请附卡
			int s = 0;
			String[] string2 = new String[] { c4Abtype, applyInputfull, signFull };
			for (String str2 : string2) {
				if (requiredFieldsCheck(str2,"?",isYDJ)) {
					s++;
				}
			}
			if (s < 3) {
				return false;
			}
		} else {// myFlag==9 ========>易达金卡
			int i = 0;
			String[] string3 = new String[] { c1Cname, c1Ename, c1Idnbr, c1Mobile, c1Coname, c1Cotel, c1RetelOrPho,
					c1Rename, c4Abtype };
			for (String str3 : string3) {
				if (requiredFieldsCheck(str3,"?",isYDJ)) {
					i++;
				}
			}
			if (i < 9) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 
	 * @param field
	 * @param containsZifu
	 * @param isYDJ
	 * @return
	 * 标准卡异常高风险H2 主卡必填项校验添加了"?"
	 */
	private boolean requiredFieldsCheck(String field,String containsZifu,boolean isYDJ){
		if(isYDJ){
			return null!=field && !"".equals(field);
		}else{//标准卡
			return null!=field && !"".equals(field) && !field.trim().contains(containsZifu);
		}
	}
	
	/**
	 * 策略层级获取vipCard字段的值
	 * @param on1
	 * @return
	 */
	private Boolean getVipCardValue(Opasbizinpapplc1 on1) {
		String appId = on1.getAppId();
		String str =appId.substring(10,11);
		String str1 = appId.substring(7,11);
		if("V".equals(str)){
			return true;
		}else if("S".equals(str)){
			return true;
		}else if("811P".equals(str1)){
			return true;
		}else{
			return false;
		}
	}
	/**
	 * 策略层级获取hyCard字段的值
	 * @param on1
	 * @return
	 */
	private Boolean getHyCardValue(Opasbizinpapplc1 on1) {
		String appId = on1.getAppId();
		return "S".equals(appId.substring(10,11));
	}
	/**
	 * 策略层级获取TjCard字段的值
	 * @param on1
	 * @return
	 */
	private Boolean getTjCardValue(Opasbizinpapplc1 on1) {
		String appId = on1.getAppId();
		return "V".equals(appId.substring(10,11));
	}
	
	//如果为true,则异常持卡
	private boolean checkResult(Map<String, String> mainCardMsg, Map<String, String> c1c2Map) {
		boolean flag = false;
		//匹配是哪张主卡匹配附属卡成功,得到匹配结果
		if(mainCardMsg.get("cardnbr1")!=null && mainCardMsg.get("cardnbr1").equals(c1c2Map.get("c1Cardnbr"))){
			if((!"".equals(mainCardMsg.get("cardstat1")) && mainCardMsg.get("cardstat1")!=null && !"正常".equals(mainCardMsg.get("cardstat1")))
					|| (!"".equals(mainCardMsg.get("closeCode1")) && mainCardMsg.get("closeCode1")!=null && !"正常".equals(mainCardMsg.get("closeCode1"))))
			flag = true;
//				}
		}else if(mainCardMsg.get("cardnbr2")!=null && mainCardMsg.get("cardnbr2").equals(c1c2Map.get("c1Cardnbr"))){
			log.info("卡片状态2:"+mainCardMsg.get("cardstat2")+"账户状态2:"+mainCardMsg.get("closeCode2"));
			log.info("判断2-1:" + (!"".equals(mainCardMsg.get("cardstat2")) && mainCardMsg.get("cardstat2")!=null && !"正常".equals(mainCardMsg.get("cardstat2"))));
			log.info("判断2-2:" + (!"".equals(mainCardMsg.get("closeCode2")) && mainCardMsg.get("closeCode2")!=null && !"正常".equals(mainCardMsg.get("closeCode2"))));
			if((!"".equals(mainCardMsg.get("cardstat2")) && mainCardMsg.get("cardstat2")!=null && !"正常".equals(mainCardMsg.get("cardstat2")))
					|| (!"".equals(mainCardMsg.get("closeCode2")) && mainCardMsg.get("closeCode2")!=null && !"正常".equals(mainCardMsg.get("closeCode2"))))
			flag = true;
//				}
		}else if(mainCardMsg.get("cardnbr3")!=null && mainCardMsg.get("cardnbr3").equals(c1c2Map.get("c1Cardnbr"))){
			if((!"".equals(mainCardMsg.get("cardstat3")) && mainCardMsg.get("cardstat3")!=null && !"正常".equals(mainCardMsg.get("cardstat3")))
					|| (!"".equals(mainCardMsg.get("closeCode3")) && mainCardMsg.get("closeCode3")!=null && !"正常".equals(mainCardMsg.get("closeCode3"))))
			flag = true;
//				}
		}else if(mainCardMsg.get("cardnbr4")!=null && mainCardMsg.get("cardnbr4").equals(c1c2Map.get("c1Cardnbr"))){
			if((!"".equals(mainCardMsg.get("cardstat4")) && mainCardMsg.get("cardstat4")!=null && !"正常".equals(mainCardMsg.get("cardstat4")))
					|| (!"".equals(mainCardMsg.get("closeCode4")) && mainCardMsg.get("closeCode4")!=null && !"正常".equals(mainCardMsg.get("closeCode4"))))
			flag = true;
//				}
		}else if(mainCardMsg.get("cardnbr5")!=null && mainCardMsg.get("cardnbr5").equals(c1c2Map.get("c1Cardnbr"))){
			if((!"".equals(mainCardMsg.get("cardstat5")) && mainCardMsg.get("cardstat5")!=null && !"正常".equals(mainCardMsg.get("cardstat5")))
					|| (!"".equals(mainCardMsg.get("closeCode5")) && mainCardMsg.get("closeCode5")!=null && !"正常".equals(mainCardMsg.get("closeCode5"))))
			flag = true;
//				}
		}else{
			flag = true;
		}
		return flag;
	}
	
	/**
	 * 人行模糊匹配结果获取方法
	 * @param on1
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private Map<String, String> queryTeam(String appId) {
	    Map<String, String> map = new HashMap<String, String>();
		map.put("appId", appId);
		// map.put("marchNode", marchNode);
		// 根据appId获得数据源匹配信息
		KeyfiledMarchinfo info = opasKeyfiledMarchinfoDaox.selectByAppId(map);// 名单库匹配
		if (info == null) {
			log.info("没有匹配的数据信息");
			return null;
		}
		// 获取Json
		String marchResultJson = info.getMarchResult();
//		log.info("Json=" + marchResultJson);
		// 解析Json串
		JSONArray jarray = JSONArray.fromObject(marchResultJson);
		List<RiskCheck> listriskcheck = (List<RiskCheck>) JSONArray
				.toCollection(jarray, RiskCheck.class);
		Map<String, String> jsonMap = new HashMap<String, String>();
		for (RiskCheck riskCheck : listriskcheck) {
			if ("1".equals(riskCheck.getRiskResult())) {
				// 拼接map中的key规则为表明加字段名
				String key = riskCheck.getTableName() + "-"
						+ riskCheck.getFieldKey();
				jsonMap.put(key, riskCheck.getPriKeyValue());
			}
		}
		log.info("Json解析完成");
		return jsonMap;
	}

}

