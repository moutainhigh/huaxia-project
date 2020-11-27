package com.huaxia.opas.service.decision.impl;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javax.annotation.Resource;

import org.slf4j.LoggerFactory;

import com.huaxia.opas.dao.allot.AllotApplyAllotDao;
import com.huaxia.opas.dao.allot.AllotApplyAllotHisDao;
import com.huaxia.opas.dao.baseinfo.BaseCustInfoDao;
import com.huaxia.opas.dao.decision.OpasConfQuestionDao;
import com.huaxia.opas.dao.decision.SysDecisionDao;
import com.huaxia.opas.dao.decision.YdjSysresultInfoDao;
import com.huaxia.opas.dao.sysparm.AddrRiskListDao;
import com.huaxia.opas.dao.sysparm.CompanyRiskDao;
import com.huaxia.opas.dao.sysparm.IdentityRiskDao;
import com.huaxia.opas.dao.sysparm.InnerRiskInfoDao;
import com.huaxia.opas.dao.sysparm.PromoterRiskDao;
import com.huaxia.opas.dao.sysparm.SameIndustryRiskDao;
import com.huaxia.opas.dao.sysparm.TelRiskListDao;
import com.huaxia.opas.dao.thirdparty.EducationDao;
import com.huaxia.opas.dao.thirdparty.PBOCDao;
import com.huaxia.opas.dao.thirdparty.PoliceDao;
import com.huaxia.opas.domain.allot.AllotApplyAllot;
import com.huaxia.opas.domain.allot.AllotApplyAllotHis;
import com.huaxia.opas.domain.decision.YdjSysresultInfo;
import com.huaxia.opas.domain.sysparm.InnerRiskInfo;
import com.huaxia.opas.domain.thirdparty.OpasConfQuestion;
import com.huaxia.opas.service.AbstractService;
import com.huaxia.opas.service.decision.SysDecisionCommonService;

/**
 * @author xuzhiguo
 * update by liuzhihui
 */
public class SysDecisionCommonServiceImpl extends AbstractService implements
		SysDecisionCommonService,Serializable  {

	private static final long serialVersionUID = -8008508483239828116L;

	private static org.slf4j.Logger logger = LoggerFactory
			.getLogger(SysDecisionCommonServiceImpl.class);

	@Resource(name = "addrRiskListDao")
	private AddrRiskListDao addrRiskListDao;

	@Resource(name = "opasConfQuestionDao")
	private OpasConfQuestionDao opasConfQuestionDao;
	
	@Resource(name = "ydjSysresultInfoDao")
	private YdjSysresultInfoDao ydjSysresultInfoDao;
	
	@Resource(name = "innerRiskInfoDao")
	private InnerRiskInfoDao innerRiskInfoDao;

	@Resource(name = "allotApplyAllotDao")
	private AllotApplyAllotDao allotApplyAllotDao;
	
	@Resource(name = "allotApplyAllotHisDao")
	private AllotApplyAllotHisDao allotApplyAllotHisDao;
	/*以下几个dao可能注入不进来   updat  by  刘志辉  2017-4-6 19:47:27*/
	@Resource(name = "identityRiskDao")
	private IdentityRiskDao identityRiskDao;
	@Resource(name = "companyRiskDao")
	private CompanyRiskDao companyRiskDao;
	@Resource(name = "telRiskListDao")
	private TelRiskListDao telRiskListDao;
	@Resource(name = "promoterRiskDao")
	private PromoterRiskDao promoterRiskDao;
	@Resource(name = "sameIndustryRiskDao")
	private SameIndustryRiskDao sameIndustryRiskDao;
	/*以下几个dao是问题库用来查找问题答案的 主要是查三方  by  安东  2017-5-9 */
	@Resource(name = "pbocDao")
	private PBOCDao pbocDao;
	@Resource(name = "policeDao")
	private PoliceDao policeDao;
	@Resource(name = "baseCustInfoDao")
	private BaseCustInfoDao baseCustInfoDao;
	@Resource(name = "sysDecisionDao")
	private SysDecisionDao sysDecisionDao;
	@Resource(name = "educationDao")
	private EducationDao educationDao;

	public PBOCDao getPbocDao() {
		return pbocDao;
	}

	public void setPbocDao(PBOCDao pbocDao) {
		this.pbocDao = pbocDao;
	}

	@Override
	public Object queryRisklist(String autoId, String flag) throws Exception {
		Map map = new HashMap();
		Object object = null;
		if ("identityRisklistWin".equals(flag)) {
			 object = identityRiskDao.queryIdentityRisk(autoId);
		} else if ("companyRisklistWin".equals(flag)) {
			 object = companyRiskDao.selectByPrimaryKey(autoId);
		} else if ("telRisklistWin".equals(flag)) {
			 object = telRiskListDao.selectByPrimaryKey(autoId);
		} else if ("addrRisklistWin".equals(flag)) {
			 object = addrRiskListDao.selectByPrimaryKey(autoId);
		} else if ("nosafePromoterWin".equals(flag)||"promoterRisklistWin".equals(flag)) {
			 object = promoterRiskDao.selectByPrimaryKey(autoId);
		} else if ("sameindustryRisklistWin".equals(flag)) {
			 object = sameIndustryRiskDao.selectByPrimaryKey(autoId);
		} else if("antRisklistWin".equals(flag)){//蚂蚁风险名单Opas_Biz_Zm_Risklist_Data；
			
		} else if("bairongRisklistWin".equals(flag)){//百融风险名单OPAS_BIZ_SPECIALLIST_LM_DATA OPAS_BIZ_SPECIALLIST_ID_DATA
			
		} else if("washmoneyRisklistWin".equals(flag)){//洗钱风险名单passports_info  cert_detail
			
		}
		return object;
	}

	@Override
	public int queryConfQuestionCount() throws Exception {
		return opasConfQuestionDao.queryConfQuestionCount();
	}

	@Override
	public List<OpasConfQuestion> queryConfQuestion(List<String> list) throws Exception {
		return opasConfQuestionDao.queryConfQuestion(list);
	}

	@Override
	public YdjSysresultInfo querySysResultfoByAppId(String appId)
			throws Exception {
		return ydjSysresultInfoDao.selectSysResultfoByAppId(appId);
	}

	@Override
	public InnerRiskInfo queryInnerRiskInfo(String autoId) throws Exception {
		return innerRiskInfoDao.queryInnerRiskInfo(autoId);
	}
	@Override
	public List<AllotApplyAllot> queryByUserId(String currOptUser) throws Exception{
		return allotApplyAllotDao.selectByUserId(currOptUser);
	}
	@Override
	public AllotApplyAllot queryOneByUserId(Map<String,String> map) throws Exception{
		return allotApplyAllotDao.selectOneByUserId(map);
	}
	@Override
	public AllotApplyAllot queryByPrimaryKey(String appId) throws Exception{
		return allotApplyAllotDao.selectByPrimaryKey(appId);
	}
	@Override
	public void updateAllotApplyAllot(AllotApplyAllot allotApplyAllot,AllotApplyAllotHis record) throws Exception{
		//修改申请件状态
		allotApplyAllotDao.updateByPrimaryKeySelective(allotApplyAllot);
		//增加流水记录
		allotApplyAllotHisDao.insertSelective(record);
	}
	@Override
	public String queryConfQuestionCount(List<String>  questionLevel) {
		return opasConfQuestionDao.queryConfQuestionCount(questionLevel);
	}
	@Override
	public String selectReultByAppId(String appId, String status) {
		System.out.println(" 卡状态为-----------"+status);
		if("1".equals(status)){
			return opasConfQuestionDao.selectReultByAppId(appId);
		}else{
			return opasConfQuestionDao.selectReultByAppIdYDJ(appId);
		}
		
	}

	/**
	 * 问题库   by ad
	 * 2017-5-9
	 * */
	@Override
	public List<OpasConfQuestion> questionLibrary(Map<Object,String> map) {
		List<OpasConfQuestion> list = new ArrayList<OpasConfQuestion>();
		//征信策略输出结果
		String  result = null;
		String ydjFlag = map.get("ydjFlag");
		//1是标准卡，2是易达金卡
		if ("1".equals(ydjFlag)) {
			ydjFlag = "2";
		} else if ("2".equals(ydjFlag)) {
			ydjFlag = "1";
		}
		String appId = map.get("appId");
		String c1Idnbr = map.get("c1Idnbr");
		if("1".equals(ydjFlag)){
			result = opasConfQuestionDao.selectReultByAppId(appId);
		}else{
			result = opasConfQuestionDao.selectReultByAppIdYDJ(appId);
		}
		List<String> questionLevel = new ArrayList<String>();
		if (result == null){
			questionLevel.add("1");
			questionLevel.add("2");
			questionLevel.add("3");
		} else {
			String level = result.trim();
			if (level.contains("H")) {
				questionLevel.add("2");
				questionLevel.add("3");
			} else if (level.contains("M")) {
				questionLevel.add("1");
				questionLevel.add("2");
			} else if (level.contains("L")) {
				questionLevel.add("1");
			} else if (level.contains("S")) {
			}
		}
		Map<String, Object> maps = new HashMap<String, Object>();
		maps.put("questionLevel", questionLevel);
		maps.put("userFlag", ydjFlag);
		//判断是否是二次申请卡  如果是第一次申请则不显示发卡问题，如果申请过就可以显示所有问题
		String existCardFlag = map.get("existCardFlag");
		if(existCardFlag!=null&&"0".equals(existCardFlag)){
			maps.put("existCardFlag", existCardFlag);
		}
		//获得符合条件的问题的编号
		String questionNos = opasConfQuestionDao.queryQuestionCount(maps);
		//将问题编号放入数组中 取下标随机
		String num[] =null;
		if(null!=questionNos&&!"".equals(questionNos)){
			num=questionNos.split(",");
		}
		
		if (num!=null){
			//选取三个不同的随机值
			Set<String> set = new HashSet<String>();
			Random ran = new Random();
			int ranNum=0;
			if(num.length<=3){//问题数小于三个问题
				int i1=num.length-1;
				for(int i=i1;i>=0;i--){
					set.add(num[i]);
				}
			}else{//问题数大于三个问题
				boolean[] bool=new boolean[num.length];
				for(int i = 0; i <3; i++){
					do{
					//如果产生的随机数相同 继续循环
					ranNum=ran.nextInt(num.length);
					}while(bool[ranNum]);
					bool[ranNum]=true;
					set.add(num[ranNum]);
				}	
			}
			List<String> questionNo=new ArrayList<String>();
			for(String nos:set){
				questionNo.add(nos);
			}
			list = opasConfQuestionDao.queryConfQuestion(questionNo);
			String no = null;
			for (int i = 0; i < list.size(); i++){
				no = list.get(i).getQuestionNo();
				if ("001".equals(no)||"019".equals(no)) {
					if ("".equals(c1Idnbr)) {
						list.get(i).setFromTableNo("没有查到该客户的生日信息，所以无法计算出生肖");
					} else {
						if (!"".equals(c1Idnbr)&&c1Idnbr!=null) {
			 				String shenxiao = getYearSX(c1Idnbr);
							list.get(i).setFromTableNo(shenxiao);
						} else {
							list.get(i).setFromTableNo("没有查到该客户的生日信息，所以无法计算出生肖");
						}
					}
				} else if ("002".equals(no)||"020".equals(no)) {// 籍贯JGSSXHOME_PROV_CITY jgssxhomeProvCity
					Map<String, String> params = baseCustInfoDao.queryBizInpApplC1(appId);
					Map<String, String> policeInfo = policeDao.queryPoliceInfo(params);
					if (policeInfo!=null||"".equals(policeInfo)) {
						if ((policeInfo.get("jgssxhomeProvCity") == null) || "".equals(policeInfo.get("jgssxhomeProvCity"))) {
							list.get(i).setFromTableNo("没查到该客户的籍贯信息");
						} else {
							list.get(i).setFromTableNo(policeInfo.get("jgssxhomeProvCity"));
						}
					}else{
						list.get(i).setFromTableNo("没查到该客户的籍贯信息");
					}
				} else if ("003".equals(no)||"021".equals(no)) {//贷款情况 OPAS_PBOC_LOAN_CARD_INFO
					Map<Object, Object>  loan= pbocDao.queryLoanNoInfo(appId);
					if(loan!=null){
						if (loan.get("COUNT")==null||"".equals(loan.get("COUNT"))) {
							list.get(i).setFromTableNo("没查到该客户的贷款情况");
						} else {
							Object o = loan.get("COUNT");
							int count = Integer.parseInt(o.toString());
							if (count > 0) {
								list.get(i).setFromTableNo("该客户有贷款或者他行信用卡");		
							} else {
								list.get(i).setFromTableNo("没查到该客户的贷款情况");
							}
						}
					}else{
						list.get(i).setFromTableNo("没查到该客户的贷款情况");
					}
				} else if ("004".equals(no)||"022".equals(no)) {//mobilephone 发卡手机号
					Map<String, String> haveCardInfo = sysDecisionDao.selectHaveCardInfoByAppId(appId);
					if(haveCardInfo!=null||"".equals(haveCardInfo)) {
						if ((haveCardInfo.get("mobilephone") == null) || "".equals(haveCardInfo.get("mobilephone"))) {
							list.get(i).setFromTableNo("没查到该客户的发卡手机号");
						} else {
							list.get(i).setFromTableNo(haveCardInfo.get("mobilephone"));
						}
					} else {
						list.get(i).setFromTableNo("没查到该客户的发卡手机号");
					}
				} else if ("005".equals(no)||"023".equals(no)) {//ORI_COMP_NAME 曾就业单位
					List<Map<String, String>> proessionInfo = getPbocDao().selectProessionInfo(appId);
					if (!proessionInfo.isEmpty()) {
						int size=proessionInfo.size();
						if(size>=2){
							if (proessionInfo.get(1)!=null&&(proessionInfo.get(1).get("company") != null)&& !"".equals(proessionInfo.get(1).get("company"))) {
								list.get(i).setFromTableNo(proessionInfo.get(1).get("company"));
							} else {
								if (proessionInfo.get(0)!=null&&(proessionInfo.get(0).get("company")!= null) && !"".equals(proessionInfo.get(0).get("company"))) {
									list.get(i).setFromTableNo(proessionInfo.get(0).get("company"));
								} else {
									list.get(i).setFromTableNo("没查到该客户的 曾就业单位"); 
								}
							}	
						}else{
							if (proessionInfo.get(0)!=null&&(proessionInfo.get(0).get("company")!= null) && !"".equals(proessionInfo.get(0).get("company"))) {
								list.get(i).setFromTableNo(proessionInfo.get(0).get("company"));
							} else {
								list.get(i).setFromTableNo("没查到该客户的 曾就业单位"); 
							}
						}
						
					} else {
						list.get(i).setFromTableNo("没查到该客户的 曾就业单位");
					}
				} else if ("006".equals(no)||"024".equals(no)) {//贷款种类+时间或贷款金额
					//LOAN_LOAN_TYPE_SEG,LOAN_OR_CREDIT_ISSU_DATE,LOAN_OR_CREDIT_CONT_AMT 
					List<Map<String, String>> loadCardInfoList = pbocDao.quertPbocLoadCardInfo(appId);
					if (!loadCardInfoList.isEmpty()) {
						String str ="";
						for(int j =0; j<loadCardInfoList.size();j++){
							String loanTypeSeg = loadCardInfoList.get(j).get("loanLoanTypeSeg");
							String loanDate = loadCardInfoList.get(j).get("loanOrCreditIssuDate");
							String loanAmt = loadCardInfoList.get(j).get("loanOrCreditContAmt");
							if (loanAmt != null && !"".equals(loanAmt)){
								str = str+ "种类"+loanTypeSeg+",时间:"+loanDate+",金额:"+loanAmt +"&&&";
								list.get(i).setFromTableNo(str);
							}
						}
						if ("".equals(str)){
							list.get(i).setFromTableNo("没有查到该客户贷款种类、时间、金额");
						}
					} else {
						list.get(i).setFromTableNo("没有查到该客户贷款种类、时间、金额");
					}
				} else if ("007".equals(no)||"025".equals(no)) {//c 贷记卡最高额度最高额度NO_PD_CARD_BANK_MAX_CREDIT_LIM
					Map<String, String> creditPbocInfo = pbocDao.queryMonthAvePayById(appId);
					if (creditPbocInfo!=null||"".equals(creditPbocInfo)) {
						if ((creditPbocInfo.get("noPdCardBankMaxCreditLim") == null) || "".equals(creditPbocInfo.get("noPdCardBankMaxCreditLim"))) {
							list.get(i).setFromTableNo("没查到该客户的贷记卡最高额度");
						} else {
							list.get(i).setFromTableNo(creditPbocInfo.get("noPdCardBankMaxCreditLim"));
						}
					} else {
						list.get(i).setFromTableNo("没查到该客户的贷记卡最高额度");
					}
				} else if ("008".equals(no)||"026".equals(no)) {//FRS_CRED_CRD_ISSUE_MON 首张发卡日期
					List<Map<String, String>> loanCardInfoList = pbocDao.queryloanTypeById(appId);
					if (!loanCardInfoList.isEmpty()) {
						if ((loanCardInfoList.get(0).get("frsCredCrdIssueMon") == null) || "".equals(loanCardInfoList.get(0).get("frsCredCrdIssueMon"))) {
							list.get(i).setFromTableNo("没查到该客户的发卡日期");
						} else {
							list.get(i).setFromTableNo(loanCardInfoList.get(0).get("frsCredCrdIssueMon"));
						}
					} else {
						list.get(i).setFromTableNo("没查到该客户的发卡日期");
					}
				} else if ("009".equals(no)||"027".equals(no)) {//RESI_TEL  住宅电话
					Map<String, String> baseInfo = pbocDao.selectBaseInfo(appId);
					if (baseInfo!=null||"".equals(baseInfo)) {
						if ((baseInfo.get("resiTel") == null) || "".equals(baseInfo.get("resiTel"))) {
							list.get(i).setFromTableNo("没查到该客户的住宅电话");
						} else {
							list.get(i).setFromTableNo(baseInfo.get("resiTel"));
						}
					} else {
						list.get(i).setFromTableNo("没查到该客户的住宅电话");
					}
				} else if ("010".equals(no)||"028".equals(no)) {//CELL_PHONE  手机号码
					Map<String, String> baseInfo = pbocDao.selectBaseInfo(appId);
					if (baseInfo!=null||"".equals(baseInfo)){
						if ((baseInfo.get("cellPhone") == null) || "".equals(baseInfo.get("cellPhone"))) {
							list.get(i).setFromTableNo("没查到该客户的手机号码");
						} else {
							list.get(i).setFromTableNo(baseInfo.get("cellPhone"));
						}
					} else {
						list.get(i).setFromTableNo("没查到该客户的手机号码");
					}
				} else if ("011".equals(no)||"029".equals(no)) {//已持卡额度CREDIT_LIMIT账户级额度
					List<Map<String, Object>> cardInfo = sysDecisionDao.selectCreditLimitByAppId(appId);
					if (!cardInfo.isEmpty()){
						String str = "";
						String str1 = "";
						for (int j = 0; j < cardInfo.size(); j++){
							
							double cate=Double.parseDouble(cardInfo.get(j).get("cate").toString());  //cardInfo.get(j).get("cate")
							double creditLimit=Double.parseDouble(cardInfo.get(j).get("creditLimit").toString());  //cardInfo.get(j).get("cate")
							if (cate==5 || cate == 10 ){
								if (cate==10){
									str1 = "标准卡：";
								} else {
									str1 = "易达金卡：";
								}
								String limit = String.valueOf(creditLimit);
								String creditMonney = "";
								String[] split = limit.split("\\.");
								for(int z = 0; z < split.length; z++) {
									creditMonney = split[0];
								}
								str = str + str1 + creditMonney +";";
								list.get(i).setFromTableNo(str);
							}
						}
						if ("".equals(str)){
							list.get(i).setFromTableNo("没查到该客户的已持卡额度");
						}
					} else {
						list.get(i).setFromTableNo("没查到该客户的已持卡额度");
					}
				} else if ("012".equals(no)||"030".equals(no)) {//PRODUCT1 已持卡卡种
					Map<String, String> cardInfo = sysDecisionDao.selectCardInfoByAppId(appId);
					if (cardInfo!=null||"".equals(cardInfo)){
						if ((cardInfo.get("product1") == null) || "".equals(cardInfo.get("product1"))
							&&(cardInfo.get("product2") == null) || "".equals(cardInfo.get("product2"))
							&&(cardInfo.get("product3") == null) || "".equals(cardInfo.get("product3"))
							&&(cardInfo.get("product4") == null) || "".equals(cardInfo.get("product4"))
							&&(cardInfo.get("product5") == null) || "".equals(cardInfo.get("product5"))) {
							list.get(i).setFromTableNo("没查到该客户的 已持卡卡种");
						} else {
							String product1 = cardInfo.get("product1");
							if ("Q".equals(cardInfo.get("closeCode1"))){
								product1="";
							} else if (cardInfo.get("product1")!=null) {
								product1 = cardInfo.get("product1");
							}
							String product2 = "";
							if ("Q".equals(cardInfo.get("closeCode2"))){
								product2="";
							}  else if (cardInfo.get("product2")!=null) {
								product2 = cardInfo.get("product2");
							}
							String product3 = "";
							if ("Q".equals(cardInfo.get("closeCode3"))){
								product3="";
							} else if (cardInfo.get("product3")!=null) {
								product3 = cardInfo.get("product3");
							}
							String product4 = "";
							if ("Q".equals(cardInfo.get("closeCode4"))){
								product4="";
							} else if (cardInfo.get("product4")!=null){
								product4 = cardInfo.get("product4");
							}
							String product5 = "";
							if ("Q".equals(cardInfo.get("closeCode5"))){
								product5="";
							}else if(cardInfo.get("product5")!=null){
								 product5 = cardInfo.get("product5");
							}
							list.get(i).setFromTableNo(product1+" "+product2+" "+product3+" "+product4+" "+product5);
						}
					} else {
						list.get(i).setFromTableNo("没查到该客户的 已持卡卡种");
					}
				} else if ("013".equals(no)||"031".equals(no)) {//OLD_NAME曾用名
					Map<String, String> params = baseCustInfoDao.queryBizInpApplC1(appId);
					Map<String, String> policeInfo = policeDao.queryPoliceInfo(params);
					if (policeInfo!=null||"".equals(policeInfo)) {
						if ((policeInfo.get("oldName") == null) || "".equals(policeInfo.get("oldName"))) {
							list.get(i).setFromTableNo("没查到该客户的 曾用名");
						} else {
							list.get(i).setFromTableNo(policeInfo.get("oldName"));
						}
					} else {
						list.get(i).setFromTableNo("没查到该客户的 曾用名");
					}
				} else if ("014".equals(no)||"032".equals(no)) {//SRVS_LOCATION服务处所
					Map<String, String> params = baseCustInfoDao.queryBizInpApplC1(appId);
					Map<String, String> policeInfo = policeDao.queryPoliceInfo(params);
					if (policeInfo!=null||"".equals(policeInfo)) {
						if ((policeInfo.get("srvsLocation") == null) || "".equals(policeInfo.get("srvsLocation"))) {
							list.get(i).setFromTableNo("没查到该客户的 服务处所");
						} else {
							list.get(i).setFromTableNo(policeInfo.get("srvsLocation"));
						}
					} else {
						list.get(i).setFromTableNo("没查到该客户的 服务处所");
					}
				} else if ("015".equals(no)||"033".equals(no)) {//OUTSTAND_LOANS_6MONTH_AVE_PAY 贷款月还款金额
					List<Map<String, String>> loadCardInfoList = pbocDao.quertPbocLoadCardInfo(appId);
					if (!loadCardInfoList.isEmpty()) {
						String str ="";
						for(int j =0; j<loadCardInfoList.size();j++){
							String loanTypeSeg = loadCardInfoList.get(j).get("loanLoanTypeSeg");
							String loanDate = loadCardInfoList.get(j).get("loanOrCreditIssuDate");
							String accCurMduePayBal = loadCardInfoList.get(j).get("accCurMduePayBal");
							String loanAmt = loadCardInfoList.get(j).get("loanOrCreditContAmt");
							if (accCurMduePayBal != null && !"".equals(accCurMduePayBal)){
								str = str+ "种类："+loanTypeSeg+",时间:"+loanDate+",金额"+loanAmt+",月还款:"+accCurMduePayBal+"&&&";
								list.get(i).setFromTableNo(str);
							}
						}
						if ("".equals(str)){
							list.get(i).setFromTableNo("没有查到该客户贷款的月还款额");
						}
					} else {
						list.get(i).setFromTableNo("没有查到该客户贷款的月还款额");
					}
				} else if ("016".equals(no)||"034".equals(no)) {// GRADUATE毕业院校
					Map<String, String> educationDetailInfo = educationDao.selectSummaryInfo(appId);
					if (educationDetailInfo!=null||"".equals(educationDetailInfo)) {
						if ((educationDetailInfo.get("graduate") == null) || "".equals(educationDetailInfo.get("graduate"))) {
							list.get(i).setFromTableNo("没查到该客户的 毕业院校");
						} else {
							list.get(i).setFromTableNo(educationDetailInfo.get("graduate"));
						}
					} else {
						list.get(i).setFromTableNo("没查到该客户的 毕业院校");
					}
				} else if ("017".equals(no)||"035".equals(no)) {//SPECIALITYNAME 专业
					Map<String, String> educationDetailInfo = educationDao.selectSummaryInfo(appId);
					if (educationDetailInfo!=null||"".equals(educationDetailInfo)) {
						if ((educationDetailInfo.get("specialityName") == null) || "".equals(educationDetailInfo.get("specialityName"))) {
							list.get(i).setFromTableNo("没查到该客户的 专业");
						} else {
							list.get(i).setFromTableNo(educationDetailInfo.get("specialityName"));
						}
					} else {
						list.get(i).setFromTableNo("没查到该客户的 专业");
					}
				} else if ("018".equals(no)||"036".equals(no)) {//BUSINAME 发卡单位
					Map<String, String> selectHaveCardInfoByAppId = sysDecisionDao.selectHaveCardInfoByAppId(appId);
					if (selectHaveCardInfoByAppId!=null||"".equals(selectHaveCardInfoByAppId)) {
						if ((selectHaveCardInfoByAppId.get("businame") == null) || "".equals(selectHaveCardInfoByAppId.get("businame"))) {
							list.get(i).setFromTableNo("没查到该客户的发卡单位");
						} else {
							list.get(i).setFromTableNo(selectHaveCardInfoByAppId.get("businame"));
						}
					} else {
						list.get(i).setFromTableNo("没查到该客户的 发卡单位");
					}
				}
			}
		}
		
		return list;
	}
	
	//计算生肖
	public static String getYearSX (String c1Idnbr) {
		int year = Integer.parseInt(c1Idnbr.substring(6, 10));
		int month = Integer.parseInt(c1Idnbr.substring(10, 12));
		int day = Integer.parseInt(c1Idnbr.substring(12, 14));
		
		String[] zodiac = new String[]{"鼠","牛","虎","兔","龙","蛇","马","羊","猴","鸡","狗","猪"};
		int myPos = (year - 1900)%12;
		String myZodiac = zodiac[myPos];
		int Y = year%100;
		double D =  0.2422;
		double C ;
		int _year = (int) (Math.floor(year/100)+1);
		switch(_year){
		case 20:
			C = 4.6295;
			break;
		case 21:
			C = 3.87;
			break;
		case 22:
			C = 4.15;
			break;
		default:
			C = 3.87;
		}
		int L = (Y-1)/4;
		double springDay = 0;
		springDay= Math.floor(Y*D+C)-Math.floor((Y-1)/4);
		
		switch(month){
		case 1:
			int _myPos = myPos-1;
			if(_myPos<0){
				_myPos = 11;
			}
			myZodiac = zodiac[_myPos];
			break;
		case 2:
			if(day<springDay){
				int myPoss = myPos-1;
				if(myPoss<0){
					myPoss = 11;
				}
				myZodiac = zodiac[myPoss];
			}
			break;
		}
		
		return myZodiac;
	}
}
