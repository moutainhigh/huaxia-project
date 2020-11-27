package com.huaxia.opas.action.common;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.huateng.neofp.core.Action;
import com.huateng.neofp.core.Context;
import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.allot.AllotApplyAllot;
import com.huaxia.opas.domain.compare.RevCompInfo;
import com.huaxia.opas.domain.decision.OpasCustBaseInfo;
import com.huaxia.opas.domain.dict.ApDictDetailSmall;
import com.huaxia.opas.domain.input.BizInpApplC1;
import com.huaxia.opas.domain.input.BizInpApplC2;
import com.huaxia.opas.domain.input.FicoSdBlaze;
import com.huaxia.opas.domain.input.FicoYdjBlaze;
import com.huaxia.opas.domain.input.TelcheckResult;
import com.huaxia.opas.domain.rule.OpasPbocCreditCue;
import com.huaxia.opas.domain.sysparm.ApproveReasonCode;
import com.huaxia.opas.domain.sysparm.ApproveZipcode;
import com.huaxia.opas.domain.sysparm.CardDegradeFace;
import com.huaxia.opas.domain.sysparm.CardProduct;
import com.huaxia.opas.domain.sysparm.Policy;
import com.huaxia.opas.domain.system.ApUser;
import com.huaxia.opas.domain.thirdparty.BizApproval;
import com.huaxia.opas.domain.thirdparty.BizApprovalHis;
import com.huaxia.opas.service.apply.ApplyInfoService;
import com.huaxia.opas.service.compare.RevCompInfoService;
import com.huaxia.opas.service.decision.BizApprovalService;
import com.huaxia.opas.service.decision.SysApprovalCommonService;
import com.huaxia.opas.service.decision.SysDecisionService;
import com.huaxia.opas.service.decision.SysDecisionYdjService;
import com.huaxia.opas.service.dict.ApDictDetailService;
import com.huaxia.opas.service.fico.FicoService;
import com.huaxia.opas.service.sysparm.ApproveReasonCodeService;
import com.huaxia.opas.service.sysparm.CardDegradeService;
import com.huaxia.opas.service.sysparm.CardLayoutService;
import com.huaxia.opas.service.sysparm.CardProductService;
import com.huaxia.opas.service.sysparm.PolicyService;
import com.huaxia.opas.service.system.ApUserService;
import com.huaxia.opas.service.thirdparty.AntService;
import com.huaxia.opas.service.thirdparty.PBOCService;
import com.huaxia.opas.util.CommonUtil;
import com.huaxia.opas.util.StringUtil;

/**
 * 
 * @author xuzhiguo 授权审批tab页的展示 2017-3-7 14:59:27
 */
public class SystemApprovalCommonAction implements Action {

	private static Logger logger = Logger.getLogger(SystemApprovalCommonAction.class);
	
	@Resource(name = "sysApprovalCommonService")
	private SysApprovalCommonService sysApprovalCommonService;
	
	@Resource(name = "sysDecisionYdjService")
	private SysDecisionYdjService sysDecisionYdjService;
	
	@Resource(name = "applyInfoService")
	private ApplyInfoService applyService;
	@Resource(name = "ficoService")
	private FicoService ficoService;
	@Resource(name = "cardDegradeService")
	private CardDegradeService cardDegradeService;
	@Resource(name = "cardProductService")
	private CardProductService cardProductService;
	@Resource(name = "policyService")
	private PolicyService policyService;
	@Resource(name = "approveReasonCodeService")
	private ApproveReasonCodeService approveReasonCodeService;
	@Resource(name = "revCompInfoService")
	private RevCompInfoService revCompInfoService;
	@Resource(name = "pbocService")
	private PBOCService pbocService;
	@Resource(name = "antService")
	private AntService antService;
	@Resource(name = "apUserService")
	private ApUserService apUserService;
	@Resource(name = "cardLayoutService")
	private CardLayoutService cardLayoutService;
	@Resource(name = "sysDecisionService")
	private SysDecisionService sysDecisionService;
	@Resource(name = "apDictDetailService")
	private ApDictDetailService apDictDetailService;
	@Resource(name = "bizApprovalService")
	private BizApprovalService bizApprovalService;//
	/**
	 * 1.授权审批页面初始化查询 2.授权审批页面审批结果保存 3.标准卡与易达金通用处理逻辑
	 * 
	 * @author xuzhiguo
	 * @param ctx
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void querySystemApprovalResult(Context ctx) {
		String zcredit = "", gjm = "", isWuWangDian = "", jcShouxinProduct = "",jcShouxin="",wsFlag="",rzLableCode="";
		try {
			// step1获得appId
			String appId = StringUtils.trimToEmpty((String) ctx.getDataMap().get("appId"));
			String ydjFlag = StringUtils.trimToEmpty((String) ctx.getDataMap().get("flag"));
			// step2根据appId获得客户信息、系统决策结果
			OpasCustBaseInfo custInfo = sysDecisionYdjService.selectCustAndPreSellimitByAppId(appId);
			Object ficoBlazeMsg = sysApprovalCommonService.querySystemApprovalResult(appId, ydjFlag);
			FicoYdjBlaze ficoYdjBlaze = null;
			FicoSdBlaze ficoSdBlaze = null;
			//查询欺诈结果信息表，判断是否进行过欺诈调查
			String isSpecialCust = "";
			int diaochaRes = sysApprovalCommonService.queryApprovalQzdcByAppId(appId);
			if(diaochaRes > 0){
				isSpecialCust = "Y";
			}
			
			if ("1".equals(ydjFlag) && ficoBlazeMsg != null) {// 易达金
				ficoYdjBlaze = (FicoYdjBlaze) ficoBlazeMsg;
				custInfo.setPreSellimit(ficoYdjBlaze.getProposedLmt()+"");//易达金的系统建议额度
				//isSpecialCust = ydj==null?"":ydj.getIsSpecialCust();
				// 反显中征信“信用1000”评分OPAS_PBOC_CREDIT_CUE
				List<OpasPbocCreditCue> cuelist = sysApprovalCommonService.selectOpasPbocCreditCueByAppId(appId);
				if (cuelist != null && cuelist.size() > 0) {
					OpasPbocCreditCue cue = cuelist.get(0);
					if (cue.getNumberRead() != null && !"".equals(cue.getNumberRead())) {
						zcredit = cue.getNumberRead();
					}
				}
			} else if ("2".equals(ydjFlag) && ficoBlazeMsg != null) {// 标准卡
				ficoSdBlaze = (FicoSdBlaze) ficoBlazeMsg;
				//isSpecialCust = bzk==null?"":bzk.getIsSpecialCust();
				// 系统授信产品
				jcShouxinProduct = ficoSdBlaze.getProdName();
				jcShouxin = ficoSdBlaze.getCpRst();
				rzLableCode=ficoSdBlaze.getRzLableCode();//新客群标签  授信审批初始政策码1
			}
			// 查询主卡进件信息
			BizInpApplC1 appl = sysDecisionYdjService.selectBizInpApplC1ByAppId(appId);
			appl = appl == null ? new BizInpApplC1() : appl;// 避免空指针异常
			//获得申请卡种
			if(custInfo==null){
				logger.error("客户基本信息表中没有数据，请检测数据appId="+appId);
				custInfo = new OpasCustBaseInfo();
				custInfo.setAppId(appId);
				custInfo.setApplyCard(appl.getAppProd()==null?"":appl.getAppProd());
				custInfo.setCustName(appl.getC1Cname()==null?"":appl.getC1Cname());
			}
			// step3warning list展示
			// 不安全推广机构、不安全推广员匹配结果
			Map<String, String> resMap = sysDecisionYdjService.queryTeam(appId);
			StringBuilder sb = new StringBuilder();
			// step3.1不安全推广机构（算法：推广单位代码在推广单位黑名单里）2019.11.11CRS改造项目 新增“国家/证件信息与税收居民逻辑检查”结果
			String org = null, per = null,C6RESITAXID=null;
			if (resMap != null) {
				org = resMap.get("OPAS_COMPANY_RISKLIST-APPLY-COMPANY_NAME");
				per = resMap.get("OPAS_PROMOTER_RISKLIST-APPLY-CERTIFI_NO")
						==null?resMap.get("OPAS_PROMOTER_RISKLIST-PROMOTER_NO"):resMap.get("OPAS_PROMOTER_RISKLIST-APPLY-CERTIFI_NO");
				if("2".equals(appl.getC1c2Flag())){//单申附卡
					C6RESITAXID=resMap.get("OPAS_BIZ_INP_APPL_C2-C6_RESI_TAXID1");
				}else{//单申主卡和主附同申
					C6RESITAXID=resMap.get("OPAS_BIZ_INP_APPL_C1-C6_RESI_TAXID1");
				}
			}
			if (org != null)
				sb.append("◎不安全推广机构\n");
			// step3.2不安全推广员（算法：推广员工号在推广员黑名单里）
			if (per != null)
				sb.append("◎不安全推广员\n");
			// step3.3公安身份是否异常（算法：证件号码存在但与姓名不匹配）
//			if ("不匹配".equals(custInfo.getPoliceStatus())) {/* 0:完全匹配1:库中无此号2:身份证号匹配3:姓名匹配 */
//				sb.append("◎公安身份异常\n");
//			}
			//20200709 修改
			//公安状态判断  20200807 马超群
			String policeStatus=custInfo.getPoliceStatus()==null?"":custInfo.getPoliceStatus();
			String wgrStatus=custInfo.getWgrStatus()==null?"":custInfo.getWgrStatus();
			String policeStatusFsk=custInfo.getPoliceStatusFsk()==null?"":custInfo.getPoliceStatusFsk();
			String wgrStatusFsk=custInfo.getWgrStatusFsk()==null?"":custInfo.getWgrStatusFsk();
			String certifiType = custInfo.getCertifiType()==null?"":custInfo.getCertifiType();
			String certifiType2 = custInfo.getCertifiType2()==null?"":custInfo.getCertifiType2();
			if(custInfo.getMatchingCardFlag().equals("1")&&custInfo.getYdjFlag().equals("1")){
				//易达金配发的标准卡简项公安不做校验  王伟涛 20200827
				if(custInfo.getC1c2Flag().equals("1")){
					//2.主附同申  主卡中国人 (证件类型01 02) 附卡外国人(证件类型04 07) 
					if((certifiType.equals("01")||certifiType.equals("02"))&&(certifiType2.equals("04")||certifiType2.equals("07"))){
						if(!"01".equals(wgrStatusFsk)){
							sb.append("◎公安身份异常\n");
						}
					}
					//3.主附同申  主卡外国人(证件类型04 07)   附卡外国人(证件类型04 07)
					if((certifiType.equals("04")||certifiType.equals("07"))&&(certifiType2.equals("04")||certifiType2.equals("07"))){
						if((!"01".equals(wgrStatus))||(!"01".equals(wgrStatusFsk))){
							sb.append("◎公安身份异常\n");
						}
					}
					//4.主附同申  主卡外国人 (证件类型04 07) 附卡中国人(证件类型01 02)
					if((certifiType.equals("04")||certifiType.equals("07"))&&(certifiType2.equals("01")||certifiType2.equals("02"))){
						if(!"01".equals(wgrStatus)){
							sb.append("◎公安身份异常\n");
						}
					}
				}
				if(custInfo.getC1c2Flag().equals("3")){
					//6.单办主卡 外国人
					if(!"01".equals(wgrStatus)&&(certifiType.equals("04")||certifiType.equals("07"))){
						sb.append("◎公安身份异常\n");
					}
				}	
				if(custInfo.getC1c2Flag().equals("2")){
					//8.单办附卡 外国人
					if(!"01".equals(wgrStatusFsk)&&(certifiType2.equals("04")||certifiType2.equals("07"))){
						sb.append("◎公安身份异常\n");
					}
				}
			
			}else{
				if(custInfo.getC1c2Flag().equals("1")){
					//1.主附同申  主卡中国人(证件类型01 02)  附卡中国人(证件类型01 02) 
					if((certifiType.equals("01")||certifiType.equals("02"))&&(certifiType2.equals("01")||certifiType2.equals("02"))){
						if(!"完全匹配".equals(policeStatus)||!"完全匹配".equals(policeStatusFsk)){
							sb.append("◎公安身份异常\n");
						}
					}
					//2.主附同申  主卡中国人 (证件类型01 02) 附卡外国人(证件类型04 07) 
					if((certifiType.equals("01")||certifiType.equals("02"))&&(certifiType2.equals("04")||certifiType2.equals("07"))){
						if(!"完全匹配".equals(policeStatus)||!"01".equals(wgrStatusFsk)){
							sb.append("◎公安身份异常\n");
						}
					}
					//3.主附同申  主卡外国人(证件类型04 07)   附卡外国人(证件类型04 07)
					if((certifiType.equals("04")||certifiType.equals("07"))&&(certifiType2.equals("04")||certifiType2.equals("07"))){
						if(!"01".equals(wgrStatus)||!"01".equals(wgrStatusFsk)){
							sb.append("◎公安身份异常\n");
						}
					}
					//4.主附同申  主卡外国人 (证件类型04 07) 附卡中国人(证件类型01 02)
					if((certifiType.equals("04")||certifiType.equals("07"))&&(certifiType2.equals("01")||certifiType2.equals("02"))){
						if(!"01".equals(wgrStatus)||!"完全匹配".equals(policeStatusFsk)){
							sb.append("◎公安身份异常\n");
						}
					}
				}
				if(custInfo.getC1c2Flag().equals("3")){
					//5.单办主卡 中国人
					if(!"完全匹配".equals(policeStatus)&&(certifiType.equals("01")||certifiType.equals("02"))){
						sb.append("◎公安身份异常\n");
					}
					//6.单办主卡 外国人
					if(!"01".equals(wgrStatus)&&(certifiType.equals("04")||certifiType.equals("07"))){
						sb.append("◎公安身份异常\n");
					}
				}
				if(custInfo.getC1c2Flag().equals("2")){
					//7.单办附卡 中国人
					if(!"完全匹配".equals(policeStatusFsk)&&(certifiType2.equals("01")||certifiType2.equals("02"))){
						sb.append("◎公安身份异常\n");
					}
					//8.单办附卡 外国人
					if(!"01".equals(wgrStatusFsk)&&(certifiType2.equals("04")||certifiType2.equals("07"))){
						sb.append("◎公安身份异常\n");
					}
				}
			}
			
			if(sb.toString().indexOf("◎公安身份异常")==-1){
				if(custInfo.getC1c2Flag().equals("3")&&custInfo.getYdjFlag().equals("2")){
					//标卡 单办主卡校验可信
					String authResult = custInfo.getAuthResult()==null?"":custInfo.getAuthResult();
					if(custInfo.getC1c2Flag().equals("3")&&(certifiType.equals("01")||certifiType.equals("02"))){
						if(!("完全匹配".equals(policeStatus) && !"".equals(authResult) && "0".equals(authResult.substring(0,1)))
								&&!("".equals(policeStatus) && !"".equals(authResult) && "0".equals(authResult.substring(0,1)))
								&&!("完全匹配".equals(policeStatus) && !"".equals(authResult) &&!"0".equals(authResult.substring(0,1))&&!"5".equals(authResult.substring(0,1)))
								&&!("完全匹配".equals(policeStatus)&&"".equals(authResult))
								){
							sb.append("◎公安身份异常\n");
						}
					}
				}
				
			}
			// step3.4非身份证申请警示（算法：证件类型不是身份证）
			if (!"01".equals(certifiType)&&!"02".equals(certifiType)) {
				sb.append("◎非身份证申请警示\n");
			}
			// step3.5证件类型如果是这3种，进行提示（算证件类型是外国人永久居留身份证，港澳居民居住证，台湾居民居住证）
			if ("91".equals(certifiType)|| "90".equals(certifiType) ||"07".equals(certifiType)) {
				sb.append("◎需使用“通行证/台胞证/护照号码”再次查询发卡系统\n");
			}
			// step3.5无网点（算法：电话区号不在区域表中）OPAS_APPROVE_ZIPCODE
			String C1_COTEL = appl.getC1Cotel();// 电话区号
			if("1".equals(ydjFlag)){//若当前件为易达金，则判断电话区号和公司邮编是否同时在配置的电话区号表中，若不在，则无网点
				String company_cotel = appl.getC1Copost();//单位邮政编码（获取其前3位）
				if(C1_COTEL == null || "".equals(C1_COTEL.trim()) || company_cotel == null || company_cotel.trim().length()<3){
					sb.append("◎无网点\n");
					isWuWangDian = "1";
				}else{
					Map telMap = new HashMap();
					telMap.put("acctType", "2");// 账户类型 1:标准卡;2:易达金
					telMap.put("zipCode", company_cotel.substring(0, 3));//取公司邮编的前三位
					if(C1_COTEL.length()>=3 && (C1_COTEL.indexOf("01") == 0 || C1_COTEL.indexOf("02") == 0)){
						telMap.put("telNo",C1_COTEL.substring(0, 3));//01、02开头是前3位，
					}else if(C1_COTEL.length()>=4){
						telMap.put("telNo",C1_COTEL.substring(0, 4));
					}else{//输入有误   电话区号只有三位 非直辖市
						telMap.put("telNo",C1_COTEL.trim());
					}
					List<ApproveZipcode> zipcodeList = sysApprovalCommonService.queryApproveZipcodeByTelno(telMap);
					if (zipcodeList == null || zipcodeList.size() == 0) {
						sb.append("◎无网点\n");
						isWuWangDian = "1";
					}
				}
			}else if("2".equals(ydjFlag)){//若当前件为标准卡，则判断电话区号是否在配置的电话区号表中，若不在，则无网点
				if (C1_COTEL != null && !"".equals(C1_COTEL.trim())) {
					Map telMap = new HashMap();
					telMap.put("acctType", "1");// 账户类型 1:标准卡;2:易达金
					if(C1_COTEL.length() < 3){
						sb.append("◎无网点\n");
						isWuWangDian = "1";
					}else{
						if(C1_COTEL.indexOf("01") == 0 || C1_COTEL.indexOf("02") == 0){
							telMap.put("telNo",C1_COTEL.substring(0, 3));//01、02开头是前3位，
						}else if(C1_COTEL.length()>=4){
							telMap.put("telNo",C1_COTEL.substring(0, 4));
						}else{//输入有误   电话区号只有三位 非直辖市
							telMap.put("telNo",C1_COTEL.trim());
						}
						List<ApproveZipcode> zipcodeList = sysApprovalCommonService.queryApproveZipcodeByTelno(telMap);
						if (zipcodeList == null || zipcodeList.size() == 0) {
							sb.append("◎无网点\n");
							isWuWangDian = "1";
						}
					}
				}else{
					sb.append("◎无网点\n");
					isWuWangDian = "1";
				}
			}
			// step3.6欺诈调查 算法：曾被欺诈调查过，且调查结果为“确认欺诈”
			if (!"".equals(isSpecialCust)&&isSpecialCust!=null) {
				if ("Y".equals(isSpecialCust.toUpperCase()))// 是否特殊客户（反欺诈）Y是N否
					sb.append("◎欺诈调查\n");
			}
			// step3.7加急办卡（算法：申请条形码第9-10位=93）
			if (appId.indexOf("93") == 8)
				sb.append("◎加急办卡\n");
			// step3.8宁德地区进件 
			// 算法：申请人身份证号3522或3509开头；或单位地址/住宅地址含“宁德”；或单电/宅电区号为0593/593
			
				/*company = custInfo.getWorkCompany().indexOf("宁德");
				a = custInfo.getWorkCompany().indexOf("安徽金实");
				b = custInfo.getWorkCompany().indexOf("申彤大大");
				c = custInfo.getWorkCompany().indexOf("金易融");
				d = custInfo.getWorkCompany().indexOf("e租宝");
			}
			// step3.9安徽金实 申彤大大 e租宝
			if (a > -1)
				sb.append("◎安徽金实\n");
			if (b > -1)
				sb.append("◎申彤大大\n");
			if (c > -1)
				sb.append("◎e租宝\n");
			if (d > -1)
				sb.append("◎e租宝\n");*/
			
			// 20200925 from wjf 审批分层项目
			List<ApDictDetailSmall> dictDetailList = apDictDetailService.queryDictDetailByCode("WARNINGLIST_CONAME");
			if(null != custInfo.getC1Coname()){
				for (ApDictDetailSmall apDictDetailSmall : dictDetailList) {
					if(custInfo.getC1Coname().contains(apDictDetailSmall.getDictDetailName())){
						sb.append("◎"+apDictDetailSmall.getDictDetailName()+"\n");
					}
				}
			}
			if(null != custInfo.getC4Fconm2()){
				for (ApDictDetailSmall apDictDetailSmall : dictDetailList) {
					if(custInfo.getC4Fconm2().contains(apDictDetailSmall.getDictDetailName())){
						sb.append("◎"+apDictDetailSmall.getDictDetailName()+"\n");
					}
				}
			}
			//20200925 审批分层注释
			int company = -1, address = -1;
			int a = -1, b = -1, c = -1, d = -1;
			if (custInfo.getWorkCompany() != null) 
				company = custInfo.getWorkCompany().indexOf("宁德");
//			int company = -1,address = -1;
			if (appl.getC1HmaddTotal() != null) {
				address = appl.getC1HmaddTotal().indexOf("宁德");
			}
			String cretifiNo = custInfo.getCertifiNo();
			if(cretifiNo!=null){
				if((cretifiNo!=null && (cretifiNo.indexOf("3522")==0||cretifiNo.indexOf("3509")==0||
						company > -1|| address > -1))
					||(appl.getC1Cotel()!=null && (appl.getC1Cotel().indexOf("0593")==0||appl.getC1Cotel().indexOf("593")==0))
					||(appl.getC1Hmare()!=null && (appl.getC1Hmare().indexOf("0593")==0||appl.getC1Hmare().indexOf("593")==0))){
					sb.append("◎宁德地区进件\n");
				}
			}
			
			
			
			
			//step3.10 未匹配到网申合作商户
			if("B".equals(appId.substring(6, 7))&&"0".equals(custInfo.getWsFlag())){
				sb.append("◎未匹配到网申合作商户\n");
				isWuWangDian = "1";
			};
			// 20200925 from wjf 审批分层项目
			int maxOverDue = pbocService.queryCurOverdueMax(appId);
//			int org1mQuerySum3 = pbocService.queryMonthApplyCount(appId);
//			if(org1mQuerySum3>=5){//&&"2".equals(ydjFlag)
			// 征信评分
			double creditScore = -1d;
			if("1".equals(custInfo.getC1c2Flag()) || "3".equals(custInfo.getC1c2Flag())){
				if("2".equals(ydjFlag)){
					creditScore = ficoSdBlaze.getTriggerType();
					if(creditScore == 2.14d){
						sb.append("◎请关注人行一个月内申请查询大于等于5次\n");
					}
				}
				if(maxOverDue>=500){
					//msg = msg+"【历史或者当前逾期超过500元】";
					sb.append("◎请关注贷款/贷记卡历史或当期逾期\n");
				}
				//进件来源取自前端页面
				String c4Apsour=custInfo.getC4Apsour()==null?"":custInfo.getC4Apsour();
				if("7".equals(c4Apsour)){//&&"2".equals(ydjFlag)
					sb.append("◎请关注自进件\n");
				}
			}
			//是否有副卡
			AllotApplyAllot allot = sysApprovalCommonService.queryAllotApplyAllot(appId);
			if(allot==null){
				allot = new AllotApplyAllot();
			}
			ctx.setData("matchingCardFlag", allot.getMatchingCardFlag());
			//主卡审批操作反显===========================================
			/*AllotApplyAllotHis allothis = sysApprovalCommonService.selectLastOneByAppId(appId);
			if(allothis==null){
				allothis = new AllotApplyAllotHis();
			}*/
			BizApproval  bizApproval =  sysApprovalCommonService.selectByAppId(appId);
			BizApproval  bizApproval_fk = null;//标准卡套卡审批结论
			if(bizApproval == null || ("0".equals(bizApproval.getInitSaveFlag())&&ficoBlazeMsg != null)){
				bizApproval = new BizApproval();
				BigDecimal lmt = new BigDecimal(0);
				String policyCode1="",policyCode2="",policyCode3="";
				String violateCode1 = "";
				String refuseCode1 = "";
				String approveResult = "1";
				if("1".equals(ydjFlag)&&ficoYdjBlaze!=null){
					lmt = ficoYdjBlaze.getProposedLmt()==null?new BigDecimal(0):ficoYdjBlaze.getProposedLmt();
					policyCode1 = ficoYdjBlaze.getPolicyCd()==null?"":ficoYdjBlaze.getPolicyCd();
					violateCode1 = ficoYdjBlaze.getViolationCd()==null?"":ficoYdjBlaze.getViolationCd();
					refuseCode1 = ficoYdjBlaze.getRejectCd()==null?"":ficoYdjBlaze.getRejectCd();
					if(ficoYdjBlaze.getDecisionResult()!=null&&ficoYdjBlaze.getDecisionResult().contains("拒绝")){
						approveResult = "0";
					}
					bizApproval.setPolicyCode1(policyCode1);
				}else if("2".equals(ydjFlag)&&ficoSdBlaze!=null){
					lmt = ficoSdBlaze.getProposedLmt()==null?new BigDecimal(0):ficoSdBlaze.getProposedLmt();
					policyCode1 = ficoSdBlaze.getPolicyCode1() == null ? "":ficoSdBlaze.getPolicyCode1();
					policyCode2 = ficoSdBlaze.getGjmCd()==null?"":ficoSdBlaze.getGjmCd();
					policyCode3 = ficoSdBlaze.getPolicyCode3() == null ? "":ficoSdBlaze.getPolicyCode3();
					refuseCode1 = ficoSdBlaze.getJujCd()==null?"":ficoSdBlaze.getJujCd();
					if(ficoSdBlaze.getDecisionResult()!=null&&ficoSdBlaze.getDecisionResult().contains("拒绝")){
						approveResult = "0";
					}
					bizApproval.setPolicyCode2(policyCode2);
					bizApproval.setPolicyCode3(policyCode3);
					//wdb  初始加载标准卡政策码1优先反显新客群结果 易达金保持原样
					if(!"".equals(rzLableCode)&&rzLableCode!=null&&"2".equals(allot.getYdjFlag())
							&&rzLableCode.indexOf("ZX")>-1){//含专项类 优先专项类后基础客群
						String[] lableStr=rzLableCode.replace(" ", "").split(",");
						for(int i=0;i<lableStr.length;i++){
							if(lableStr[i].indexOf("ZX")>-1){
								bizApproval.setPolicyCode1(lableStr[i]);
							}
						}
					}else if(!"".equals(rzLableCode)&&rzLableCode!=null&&"2".equals(allot.getYdjFlag())
							&&rzLableCode.indexOf("ZX")==-1){//只含基础客群
						bizApproval.setPolicyCode1(rzLableCode.replace(" ", "").split(",")[0]);
					}else{
						bizApproval.setPolicyCode1(policyCode1);
					}
				}
				bizApproval.setAppId(appId);
				bizApproval.setApproveCreditLimit(lmt.longValue());
				bizApproval.setApproveResult(approveResult);
				bizApproval.setViolateCode1(violateCode1);
				bizApproval.setRefuseCode1(refuseCode1);
			}
			//主卡授信产品
			List<CardProduct> cardlist = new ArrayList<CardProduct>();
			if("1".equals(ydjFlag)&&custInfo.getApplyCard()!=null){
				CardProduct product = cardProductService.queryCardProductByCardCode(custInfo.getApplyCard());
				cardlist.add(product);
			}else if("2".equals(ydjFlag)&&custInfo.getApplyCard()!=null){
				if("0".equals(appl.getC4Gtoc()+"")){//是否愿意降级1-愿意,0-不愿
					CardProduct product = cardProductService.queryCardProductByCardCode(custInfo.getApplyCard());
					cardlist.add(product);
				}else{//因为只有标准卡有降级产品
					//根据申请卡 查询 产品降级信息
					cardlist = cardProductService.queryCardAgreeByCardCode(custInfo.getApplyCard());
					if(cardlist==null||cardlist.size()==0){
						CardProduct product = cardProductService.queryCardProductByCardCode(custInfo.getApplyCard());
						cardlist.add(product);
					}
				}
			}
			//配置默认版面关系(德斌专用)  标准卡主卡卡产品降级卡版面默认的逻辑
			//同时满足四个条件 ：标准卡   申请卡产品不为空  愿意降级  申请默认卡版面不为空
			//申请默认卡版面
			String appCardDefaultCardFace = appl.getC4Vercode();
			Map faceMap=new HashMap();
			if("2".equals(ydjFlag) && custInfo.getApplyCard()!=null && "1".equals(appl.getC4Gtoc()+"")&&appCardDefaultCardFace!=null){
				for (CardProduct cardProduct : cardlist) {
					//申请卡产品
					String startCard = custInfo.getApplyCard();
					//授信降级卡产品
					String endCard = cardProduct.getProductCode();
					//查询卡版面对应关系(status 1启用 0 停用)
					faceMap.put("startCard", startCard);
					faceMap.put("endCard", endCard);
					faceMap.put("status", "1");
					CardDegradeFace cardDegradeFace=cardLayoutService.queryCardLayout(faceMap);
					//split关系
					if(cardDegradeFace!=null&&cardDegradeFace.getFaceReation()!=null&&!"".equals(cardDegradeFace.getFaceReation())){
						List<String> faceList=Arrays.asList(cardDegradeFace.getFaceReation().split(";"));
						for(int i=0;i<faceList.size();i++){
							List<String> cardfaceList=Arrays.asList(faceList.get(i).split(":"));
							//申请卡版面在卡版面维护中有值  取其授信卡默认版面
							if(appCardDefaultCardFace.equals(cardfaceList.get(0))){
								//将其默认卡版面赋值
								String defaultFace =cardfaceList.get(1);
								cardProduct.setProductFaceDefault(defaultFace);
								break;
							}
						}
					}else{
						continue;
					}
				}
			}else {//不自愿降级
				if(cardlist!=null && custInfo.getApplyCard()!=null){//默认申请卡版面
					CardProduct cardProduct = null;
					for (int i = 0; i < cardlist.size(); i++) {
						cardProduct = cardlist.get(i);
						if(custInfo.getApplyCard().trim().equals(cardProduct.getProductCode().trim()))
							cardlist.get(i).setProductFaceDefault(appCardDefaultCardFace);
					}
				}
			}
			//标准卡---副卡数据反显
			if("2".equals(ydjFlag)&&"2".equals(allot.getMatchingCardFlag())&&(allot.getLaojianflag()==null||"00".equals(allot.getLaojianflag()))){
				String appId_fk = appId.substring(0, appId.length()-1)+"2";//标卡套卡最后一位为2
				BizApproval  bizApproval_bool =  sysApprovalCommonService.selectByAppId(appId_fk);
				BizInpApplC1 appl_fk = sysDecisionYdjService.selectBizInpApplC1ByAppId(appId_fk);
				if(bizApproval_bool!=null){
					bizApproval_fk = bizApproval_bool;
				}else{
					bizApproval_fk = bizApproval;
					bizApproval_fk.setCardFaceCode(appl_fk.getC4Vercode());
					bizApproval_fk.setYearFeeDerateType(appl_fk.getAppAcctyp());
				}
				ctx.setData("bizApproval_fk", bizApproval_fk);
				//副卡授信产品下拉框
				List<CardProduct> cardlist_fk = new ArrayList<CardProduct>();
				if(appl_fk!=null){
					cardlist_fk.add(cardProductService.queryCardProductByCardCode(appl_fk.getAppProd()));
				}else{
					logger.error("套卡appId = "+appId_fk+"的主进卡信息为空");
				}
				ctx.setData("cardlist_fk", cardlist_fk);
			}
			// 根据appId获得征信电核结果信息，获得过件码
			TelcheckResult result = sysApprovalCommonService.selectTelcheckResultByAppId(appId);
			if (result != null) {
				gjm = result.getBlockCode();
			}
			List<Policy> policyList = queryPolicyCode(ydjFlag,custInfo.getApplyCard());
			List<Policy> policyList1 = new ArrayList<Policy>();
			List<Policy> policyList2 = new ArrayList<Policy>();
			List<Policy> policyList3 = new ArrayList<Policy>();
			policyList1 = policyList;
			policyList2 = policyList;
			policyList3 = policyList;
			/*if("1".equals(flag)){
				policyList1 = policyList;
				policyList2 = policyList;
				policyList3 = policyList;
			}else{
				for (Policy policy : policyList) {
					int num = 0;
					try {
						num = Integer.parseInt(policy.getPolicyCode().substring(0, 1));
					} catch (Exception e) {
						e.printStackTrace();
					}
					//标准卡政策码2及易达金套卡修改为启用状态全部显示  易达金不变
					if("2".equals(allot.getYdjFlag())||("1".equals(allot.getYdjFlag())&&"1".equals(allot.getMatchingCardFlag()))){
						policyList2.add(policy);
					}
					if(num == 1){
						policyList1.add(policy);
					}else if(num>1 && num <6){
						if("1".equals(allot.getYdjFlag())&&"2".equals(allot.getMatchingCardFlag())){
							policyList2.add(policy);
						}
						policyList3.add(policy);
					}else if(num > 1){
						policyList3.add(policy);
					}
					if("6-YDJ".equals(policy.getPolicyCode())||"6-YG".equals(policy.getPolicyCode())){
						policyList1.add(policy);
					}
				}
			}*/
			List<ApproveReasonCode> resfueCode = queryWlmAndRefusedCode(ydjFlag,custInfo.getApplyCard(),"D");
			List<ApproveReasonCode> wlmCode = queryWlmAndRefusedCode(ydjFlag,custInfo.getApplyCard(),"A");
			// 重复申请拒绝码
			String appIdThd = new String();
			if("1".equals(allot.getMatchingCardFlag())){
				if("1".equals(appId.substring(15))){
					appIdThd = appId.substring(0,15)+"2";
				}else{
					appIdThd = appId.substring(0,15)+"1";
				}
			}else{
				appIdThd = appId;
			}
			// 从内部数据监测查询判断是否洗钱风险命中
			Map<String, String> jsonMap = null;
			try {
				jsonMap = sysDecisionYdjService.queryTeam(appIdThd);
			} catch (Exception e) {
				logger.info(e.getMessage());
			}
			if(jsonMap==null){
				jsonMap = new HashMap<String, String>();
			}
			String[] hisAppIdArray = {appId};
			String appIdsStr = jsonMap.get("OPAS_BIZ_INP_APPL_C1-C1_IDNBR");
			if(appIdsStr != null && !"".equals(appIdsStr)){
				hisAppIdArray = (appId + "|" +appIdsStr).split("\\|");
			}
			Map hisAppIdArrayParams = new HashMap();
			hisAppIdArrayParams.put("hisAppIdArray", hisAppIdArray);
			List<Map<String,String>> refuseCodeMsgList = bizApprovalService.selectByHisAppIds(hisAppIdArrayParams);
			
			if (refuseCodeMsgList != null && refuseCodeMsgList.size() > 0) {
				//初始化存放拒绝码的list
				List<String> refuseCodeList = new ArrayList<String>();
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
				String hitRefuseCodeListStr = "D107,D108,D109,D160,"
						+ "D511,D512,D513,D521,D522,D531,D532,D541,D551,D552,D553,D561,D572,D573,D574,D575"
						+ "YD119,YD103,YD104,YD511,YD512,YD513,YD521,YD522,"
						+ "YD531,YD532,YD533,YD541,YD551,YD552,YD553,YD561,YD571,YD572,YD573,YD581,YD582,YD583,YD584,YD585";
				List<String> hitRefuseCodeList = Arrays.asList(hitRefuseCodeListStr.split(","));
				if(refuseCodeList!=null){
					for (String refuseCodeStr : refuseCodeList) {
						if(hitRefuseCodeList.contains(refuseCodeStr)){
							sb.append("◎请关注重复申请检查高风险类拒绝");
							break;
						}
					}
				}
			} 
			//20200925 from wjf 征信分层项目  20201010 新增违例码 王伟涛
			/*if(resfueCode.contains("D107") || resfueCode.contains("D108") || resfueCode.contains("D109") || resfueCode.contains("D160") || resfueCode.contains("D5")
					|| resfueCode.contains("YD119") || resfueCode.contains("YD103") || resfueCode.contains("YD104") 
					|| resfueCode.contains("YD511") || resfueCode.contains("YD512") || resfueCode.contains("YD513") 
					|| resfueCode.contains("YD521") || resfueCode.contains("YD522") 
					|| resfueCode.contains("YD531") || resfueCode.contains("YD532") || resfueCode.contains("YD533")
					|| resfueCode.contains("YD541")
					|| resfueCode.contains("YD551") || resfueCode.contains("YD552") || resfueCode.contains("YD553")
					|| resfueCode.contains("YD561")
					|| resfueCode.contains("YD571") || resfueCode.contains("YD572") || resfueCode.contains("YD573")
					|| resfueCode.contains("YD581") || resfueCode.contains("YD582") || resfueCode.contains("YD583") || resfueCode.contains("YD584") || resfueCode.contains("YD585")
					){
				sb.append("◎请关注重复申请检查高风险类拒绝");
			}*/
			//特殊字段的特殊处理
			bizApproval.setCreditResult(gjm);
			bizApproval.setWarninglistResult(sb.toString());
			bizApproval.setIsAgreeDegrade(appl.getC4Gtoc()==null?"":appl.getC4Gtoc().toString());
			bizApproval.setSysCreditProduce(jcShouxinProduct);
			if(ficoBlazeMsg==null&&"1".equals(ydjFlag)){//易达金
				ficoBlazeMsg=new FicoYdjBlaze();
			}else if(ficoBlazeMsg==null&&"2".equals(ydjFlag)){//标准卡及易达金配发卡
				ficoBlazeMsg=new FicoSdBlaze();
			}
			ctx.setData("custInfo", custInfo);
			ctx.setData("object", ficoBlazeMsg);
			ctx.setData("zcredit", zcredit);
			ctx.setData("isWuWangDian", isWuWangDian);
			ctx.setData("jcShouxin", jcShouxin);
			ctx.setData("bizApproval", bizApproval);
			ctx.setData("cardlist", cardlist);
			//===================================
			ctx.setData("policyList1", policyList1);
			ctx.setData("policyList2", policyList2);
			ctx.setData("policyList3", policyList3);
			ctx.setData("resfueCode", resfueCode);
			ctx.setData("wlmCode", wlmCode);
			ctx.setData("laojianflag", allot.getLaojianflag()==null?"":allot.getLaojianflag());
			ctx.setData("rzLableCode", rzLableCode);
			ctx.setData("c5Abcode", appl.getC5Abcode()==null?"":appl.getC5Abcode());
			ctx.setData("C6RESITAXID", C6RESITAXID);//2019.11.11CRS改造项目 新增“国家/证件信息与税收居民逻辑检查”结果
		} catch (Exception e) {
//			logger.info("querySystemApprovalResult()发生系统错误，请技术人员检查");
//			e.printStackTrace();
			logger.error(StringUtils.trimToEmpty((String) ctx.getDataMap().get("appId")), e);
		}
	}
	/**
	 * 政策码
	 */
	public List<Policy> queryPolicyCode(String ydjFlag,String applyCard) throws Exception{
		Map<String,String> paramMap = new HashMap<String,String>();
		paramMap.put("acctType", "1".equals(ydjFlag)?"2":"1");
		List<Policy> list = policyService.queryPolicyCondition(paramMap);
		return list;
	}
	/**
	 * 违例码与拒绝码
	 */
	public List<ApproveReasonCode> queryWlmAndRefusedCode(String ydjFlag,String applyCard,String reasonCode) throws Exception{
		Map<String,String> paramMap = new HashMap<String,String>();
		paramMap.put("acctType", "1".equals(ydjFlag)?"2":"1");
		paramMap.put("reasonCode", reasonCode);
		List<ApproveReasonCode> list = approveReasonCodeService.queryAllApproveReason(paramMap);
		return list;
	}
	/**
	 * 数据保存
	 * 
	 * @param appId
	 * @param flag
	 * @return
	 * @throws Exception
	 */
	public void saveOrUpdateApprovalResult(Context ctx) throws Exception {
		// 获得appId
		String appId = StringUtils.trimToEmpty((String) ctx.getDataMap().get("appId"));
		int result=0;
		try {
			String userId = StringUtils.trimToEmpty((String) ctx.getDataMap().get("userIdParam"));
			String flag = StringUtils.trimToEmpty((String) ctx.getDataMap().get("flag"));
			Map<String, String> reqMap = (Map<String, String>) ctx.getDataMap().get("reqestData");
			Map<String, String> reqMap_fk = (Map<String, String>) ctx.getDataMap().get("reqestData_fk");
			OpasCustBaseInfo custInfo = sysDecisionYdjService.selectCustInfoByAppId(appId);
			if(custInfo == null){
				custInfo = new OpasCustBaseInfo();
			}
			ApUser apUser = apUserService.queryApUserByUserCode(userId);
			// 标准卡特殊处理部分
			String REFUSE_CHILDCARD = "";// 拒绝附属卡
			String SUPPLEMENT_RST = "";// 附属卡决策结果
			String SUPPLEMENT_RST_DSCP = "";// 附属卡审批结果描述
			if ("2".equals(flag)) {
				REFUSE_CHILDCARD = reqMap.get("refuseChildcard") == "" ? "0" : reqMap.get("refuseChildcard");
				SUPPLEMENT_RST = reqMap.get("supplementRst");
				SUPPLEMENT_RST_DSCP = reqMap.get("supplementRstDscp");
			}
			Date sysDeciTime = new Date();
			//利率分层  最低还款额  免息还款期 、利率代码失效日期、决策利率代码
			String rateValue="",repayRationValue="",repayFreeValue="";
			if("2".equals(flag)){
				FicoSdBlaze sysResultInfo = (FicoSdBlaze)sysDecisionYdjService.selectSystemDecisionByAppId(appId, "2");
				sysDeciTime = sysResultInfo==null?sysDeciTime:sysResultInfo.getCrtTime();
				rateValue=sysResultInfo.getRateName()==null?"":sysResultInfo.getRateName();
				repayRationValue=sysResultInfo.getRepayRationValue()==null?"":sysResultInfo.getRepayRationValue();
				repayFreeValue=sysResultInfo.getRepayFreeValue()==null?"":sysResultInfo.getRepayFreeValue();
			}else{
				FicoYdjBlaze sysResultInfo = (FicoYdjBlaze)sysDecisionYdjService.selectSystemDecisionByAppId(appId, "1");
				sysDeciTime = sysResultInfo==null?sysDeciTime:sysResultInfo.getCrtTime();
				rateValue=sysResultInfo.getRateName()==null?"":sysResultInfo.getRateName();
				repayRationValue=sysResultInfo.getRepayRationValue()==null?"":sysResultInfo.getRepayRationValue();
				repayFreeValue=sysResultInfo.getRepayFreeValue()==null?"":sysResultInfo.getRepayFreeValue();
			}
			// 赋值
			String memo = "";
			if("1".equals(reqMap.get("approveResult"))){
				memo = reqMap.get("pizhun_memo");
			}else if("0".equals(reqMap.get("approveResult"))){
				memo = reqMap.get("jujue_memo");
			}
			AllotApplyAllot allot = sysApprovalCommonService.queryAllotApplyAllot(appId);
			//判断当前申请件是否在审批环节
			if("03".equals(allot.getCurrNode())){
				double linelimit = Double.parseDouble(CommonUtil.getNoNullResult(reqMap.get("approveCreditLimit"), true))*1000;
				long linelimetLong = (long)linelimit;
				BizApproval bizApproval = new BizApproval();
				bizApproval.setAutoId(StringUtil.randomUUIDPlainString());
				bizApproval.setAppId(appId);
				bizApproval.setRefuseChildcard(REFUSE_CHILDCARD);// 拒绝附属卡
				bizApproval.setPreApprovelimit(Long.parseLong(CommonUtil.getNoNullResult(reqMap.get("preSellimit"), true)));// 预审批额度
				bizApproval.setApplyerName(custInfo.getCustName());
				bizApproval.setApplyCard(custInfo.getApplyCard());
				bizApproval.setIsAgreeDegrade(CommonUtil.getNoNullResult(reqMap.get("isAgreeDegrade"), false));
				bizApproval.setSysDecisionTime(sysDeciTime);// 系统决策时间
				bizApproval.setMastercardDecresult(CommonUtil.getNoNullResult(reqMap.get("decisionResult"), false));
				bizApproval.setMastercardApprovereuslt(CommonUtil.getNoNullResult(reqMap.get("decisionDesp"), false));
				bizApproval.setSysCreditProduce(CommonUtil.getNoNullResult(reqMap.get("sysCreditProduce"), false));
				bizApproval.setChildcardDecresult(SUPPLEMENT_RST);// 附属卡决策结果  未送--
				bizApproval.setChildcardApprovereuslt(SUPPLEMENT_RST_DSCP);// 附属卡审批结果描述  未送--
				bizApproval.setCreditResult(reqMap.get("creditResult"));// 征信结果[过件码]
				bizApproval.setCreditRefuseReason(reqMap.get("backFlag"));//存放backFlag
				bizApproval.setWarninglistResult(reqMap.get("warningList"));// WARNINGLIST结果
				bizApproval.setApproveResult(CommonUtil.getNoNullResult(reqMap.get("approveResult"), false));// 审批结论
				bizApproval.setApproveCreditLimit(linelimetLong);
				bizApproval.setApproveCreditProduct(CommonUtil.getNoNullResult(reqMap.get("approveCreditProduct"), false));//最终授信卡产品
				bizApproval.setManualLimit(Long.parseLong(CommonUtil.getNoNullResult(reqMap.get("manualLimit"), true)));// 调整额度   未送--
				bizApproval.setPolicyCode1(reqMap.get("policyCode1"));
				bizApproval.setPolicyCode2(reqMap.get("policyCode2"));
				bizApproval.setPolicyCode3(reqMap.get("policyCode3"));
				bizApproval.setViolateCode1(reqMap.get("violateCode1"));
				bizApproval.setViolateCode2(reqMap.get("violateCode2"));
				bizApproval.setViolateCode3(reqMap.get("violateCode3"));
				bizApproval.setMemo(memo);
				bizApproval.setRefuseCode1(reqMap.get("refuseCode1"));
				bizApproval.setRefuseCode2(reqMap.get("refuseCode2"));
				bizApproval.setRefuseCode3(reqMap.get("refuseCode3"));
				bizApproval.setApprover(userId);
				bizApproval.setApproverName(apUser.getUserName());
				//bizApproval.setHighlevleApprover(userId);
				//bizApproval.setHighlevleApprovetime(highlevleApprovetime);
				bizApproval.setSubmitHighlevleTime(new Date());
				bizApproval.setApproveTime(new Date());
				bizApproval.setCurrOptGroup(allot.getCurrOptGroup()!=null?allot.getCurrOptGroup():"");//当前操作组
				bizApproval.setIdType(custInfo.getCertifiType());
				bizApproval.setIdNo(custInfo.getCertifiNo());
				bizApproval.setYdjFlag(allot.getYdjFlag());/* 1为易达金 2标准卡 */
				//针对前台传错年费类型进行校验  F传为14 wdb 
				String approveCreditProduct=reqMap.get("approveCreditProduct")!=null?reqMap.get("approveCreditProduct"):"";
				String yearFee=CommonUtil.getNoNullResult(reqMap.get("yearFeeDerateType"), false);
				CardProduct cardProduct;
				cardProduct=cardProductService.queryCardProduct(approveCreditProduct);
				if(cardProduct!=null&&cardProduct.getYearFeeDerateType()!=null&&(!"".equals(cardProduct.getYearFeeDerateType()))){
					List<String> yearFeeList=Arrays.asList(cardProduct.getYearFeeDerateType().split(","));
					if(yearFeeList.contains(yearFee)){//包含该年费类型  可以下  
						bizApproval.setYearFeeDerateType(yearFee);
					}else{//不包含  下该产品已有的年费类型
						bizApproval.setYearFeeDerateType(yearFeeList.get(0));
					}
				}else {//这种卡产品产品表未维护  不校验
					bizApproval.setYearFeeDerateType(yearFee);
				}
				//针对卡版面校验  wdb
				String cardFace=CommonUtil.getNoNullResult(reqMap.get("cardFaceCode"), false);
				if(cardProduct!=null&&cardProduct.getProductFace()!=null&&(!"".equals(cardProduct.getProductFace()))){
					List<String> produceFaceList=Arrays.asList(cardProduct.getProductFace().split(","));
					if(produceFaceList.contains(cardFace)){//包含该卡版面  可以下  
						bizApproval.setCardFaceCode(cardFace);
					}else{//不包含  下该产品已有的卡版面
						bizApproval.setCardFaceCode(produceFaceList.get(0));
					}
				}else {//这种卡产品产品表未维护  不校验
					bizApproval.setCardFaceCode(cardFace);
				}
				//取实时系统决策
				bizApproval.setRateValue(rateValue);//利率
				bizApproval.setRepayRationValue(repayRationValue);//最低还款额
				bizApproval.setRepayFreeValue(repayFreeValue);//免息还款期
				bizApproval.setSpRefUnPlgeDti(reqMap.get("spRefUnPlgeDti")==null||reqMap.get("spRefUnPlgeDti")==""?null:new BigDecimal(reqMap.get("spRefUnPlgeDti")));//未送
				bizApproval.setSpRefCpDti(reqMap.get("spRefCpDti")==null||reqMap.get("spRefCpDti")==""?null:new BigDecimal(reqMap.get("spRefCpDti")));
				bizApproval.setSpRefMue(reqMap.get("spRefMue")==null||reqMap.get("spRefMue")==""?null:new BigDecimal(reqMap.get("spRefMue")));
				bizApproval.setSpDebtAppAuth(reqMap.get("spDebtAppAuth"));
				bizApproval.setInitSaveFlag("1");
				//历史记录
				//若是保存按钮，则不记录历史
				boolean isSaveHis = true;
				BizApprovalHis bizApprovalHis = null;
				if("0".equals(reqMap.get("backFlag"))){
					isSaveHis = false;
				}else{
					bizApprovalHis = copyApprovalRes(bizApproval);
				}
				//打印reqMap中的元素 wdb
				logger.info(appId+"该申请件前台传值reqMap="+reqMap);
				result=sysApprovalCommonService.saveOrUpdateApprovalResult(bizApproval, bizApprovalHis,isSaveHis);
				if(reqMap_fk!=null&&reqMap_fk.keySet()!=null&&reqMap_fk.keySet().size()>0){
					BizApproval bizApproval_fk = bizApproval;
					String appId_fk = appId.substring(0, appId.length()-1)+"2";//标卡的套卡最后一位为2
					String memo_fk = "";
					if("1".equals(reqMap_fk.get("approveResult"))){
						memo_fk = reqMap_fk.get("pizhun_memo");
					}else if("0".equals(reqMap_fk.get("approveResult"))){
						memo_fk = reqMap_fk.get("jujue_memo");
					}
					String RATE_NAME = "",REPAY_RATIO_VALUE ="",REPAY_FREE_VALUE ="",LOSE_DATE_RULE="";
					Date sysDeciTime_fk = new Date();
					Object object = sysDecisionYdjService.selectSystemDecisionByAppId(appId_fk, "2");
					FicoSdBlaze fico_bzk = (FicoSdBlaze)object;
					if(fico_bzk!=null){
						sysDeciTime_fk = fico_bzk.getCrtTime();
						RATE_NAME = fico_bzk.getRateName();
						REPAY_RATIO_VALUE = fico_bzk.getRepayRationValue();
						REPAY_FREE_VALUE = fico_bzk.getRepayFreeValue();
					}
					// 查询主卡进件信息
					BizInpApplC1 appl = sysDecisionYdjService.selectBizInpApplC1ByAppId(appId_fk);
					
					double linelimit_fk = Double.parseDouble(CommonUtil.getNoNullResult(reqMap_fk.get("approveCreditLimit"), true))*1000;
					long linelimetLong_fk = (long)linelimit_fk;
					bizApproval_fk.setAutoId(StringUtil.randomUUIDPlainString());
					bizApproval_fk.setAppId(appId_fk);
					bizApproval_fk.setApproveCreditProduct(reqMap_fk.get("approveCreditProduct"));
					bizApproval_fk.setApproveCreditLimit(linelimetLong_fk);
					bizApproval_fk.setApproveResult(reqMap_fk.get("approveResult"));
					bizApproval_fk.setApplyCard(appl!=null?appl.getAppProd():"");
					bizApproval_fk.setSysDecisionTime(sysDeciTime_fk);// 系统决策时间
					
					bizApproval_fk.setPolicyCode1(reqMap_fk.get("policyCode1"));
					bizApproval_fk.setPolicyCode2(reqMap_fk.get("policyCode2"));
					bizApproval_fk.setPolicyCode3(reqMap_fk.get("policyCode3"));
					bizApproval_fk.setViolateCode1(reqMap_fk.get("violateCode1"));
					bizApproval_fk.setViolateCode2(reqMap_fk.get("violateCode2"));
					bizApproval_fk.setViolateCode3(reqMap_fk.get("violateCode3"));
					bizApproval_fk.setRefuseCode1(reqMap_fk.get("refuseCode1"));
					bizApproval_fk.setRefuseCode2(reqMap_fk.get("refuseCode2"));
					bizApproval_fk.setRefuseCode3(reqMap_fk.get("refuseCode3"));
					//针对前台传错年费类型进行校验  F传为14 wdb 
					String approveCreditProduct_fk=reqMap_fk.get("approveCreditProduct")!=null?reqMap_fk.get("approveCreditProduct"):"";
					String yearFee_fk=reqMap_fk.get("yearFeeDerateType");
					CardProduct cardProduct_fk;
					cardProduct_fk=cardProductService.queryCardProduct(approveCreditProduct_fk);
					if(cardProduct_fk!=null&&cardProduct_fk.getYearFeeDerateType()!=null&&(!"".equals(cardProduct_fk.getYearFeeDerateType()))){
						List<String> yearFeeList_fk=Arrays.asList(cardProduct_fk.getYearFeeDerateType().split(","));
						if(yearFeeList_fk.contains(yearFee_fk)){//包含该年费类型  可以下  
							bizApproval_fk.setYearFeeDerateType(yearFee_fk);
						}else{//不包含  下该产品已有的年费类型
							bizApproval_fk.setYearFeeDerateType(yearFeeList_fk.get(0));
						}
					}else {//这种卡产品产品表未维护  不校验
						bizApproval_fk.setYearFeeDerateType(yearFee_fk);
					}
					//针对卡版面校验 wdb
					String cardFace_fk=reqMap_fk.get("cardFaceCode")!=null?reqMap_fk.get("cardFaceCode"):"";
					if(cardProduct_fk!=null&&cardProduct_fk.getProductFace()!=null&&(!"".equals(cardProduct_fk.getProductFace()))){
						List<String> produceFaceList_fk=Arrays.asList(cardProduct_fk.getProductFace().split(","));
						if(produceFaceList_fk.contains(cardFace_fk)){//包含该卡版面  可以下  
							bizApproval_fk.setCardFaceCode(cardFace_fk);
						}else{//不包含  下该产品已有的卡版面
							bizApproval_fk.setCardFaceCode(produceFaceList_fk.get(0));
						}
					}else {//这种卡产品产品表未维护  不校验
						bizApproval_fk.setCardFaceCode(cardFace_fk);
					}
					bizApproval_fk.setMemo(memo_fk);
					bizApproval_fk.setCreditRefuseReason(bizApproval.getCreditRefuseReason());//存放backFlag
					bizApproval_fk.setRateValue(RATE_NAME);//利率
					bizApproval_fk.setRepayRationValue(REPAY_RATIO_VALUE);//最低还款额
					bizApproval_fk.setRepayFreeValue(REPAY_FREE_VALUE);//免息还款期
					bizApproval_fk.setInitSaveFlag("1");
					//历史记录
					BizApprovalHis bizApprovalHis_fk = copyApprovalRes(bizApproval_fk);
					sysApprovalCommonService.saveOrUpdateApprovalResult(bizApproval_fk, bizApprovalHis_fk,isSaveHis);
				}
			}
			if(result==0){
				ctx.setData("isSuccess", false);
			}else{
				ctx.setData("isSuccess", true);
			}
		} catch (Exception e) {
			if(StringUtils.isNotBlank(e.getMessage()) && "save sucess and unique error".equals(e.getMessage())){
				logger.error("appId="+appId+"违反唯一约束");
				ctx.setData("isSuccess", false);
				ctx.setData("sqlUnique", false);
				return;
			}
//			logger.info("saveOrUpdateApprovalResult()发生错误"+appId+"-"+e.getMessage());
//			e.printStackTrace();
			logger.error(appId, e);
			ctx.setData("isSuccess", false);
			ctx.setData("sqlUnique", true);
		}
	}
	public BizApprovalHis copyApprovalRes(BizApproval bizApproval){
		BizApprovalHis bizApprovalHis = new BizApprovalHis();
		
		bizApprovalHis.setAutoId(StringUtil.randomUUIDPlainString());
		bizApprovalHis.setAppId(bizApproval.getAppId());
		bizApprovalHis.setRefuseChildcard(bizApproval.getRefuseChildcard());// 拒绝附属卡
		bizApprovalHis.setPreApprovelimit(bizApproval.getPreApprovelimit());// 预审批额度
		bizApprovalHis.setApplyerName(bizApproval.getApplyerName());
		bizApprovalHis.setApplyCard(bizApproval.getApplyCard());
		bizApprovalHis.setIsAgreeDegrade(bizApproval.getIsAgreeDegrade());
		bizApprovalHis.setSysDecisionTime(bizApproval.getSysDecisionTime());// 系统决策时间
		bizApprovalHis.setMastercardDecresult(bizApproval.getMastercardDecresult());
		bizApprovalHis.setMastercardApprovereuslt(bizApproval.getMastercardApprovereuslt());
		bizApprovalHis.setSysCreditProduce(bizApproval.getSysCreditProduce());
		bizApprovalHis.setChildcardDecresult(bizApproval.getChildcardDecresult());// 附属卡决策结果
		bizApprovalHis.setChildcardApprovereuslt(bizApproval.getChildcardApprovereuslt());// 附属卡审批结果描述
		bizApprovalHis.setCreditResult(bizApproval.getCreditResult());// 征信结果[过件码]
		bizApprovalHis.setCreditRefuseReason(bizApproval.getCreditRefuseReason());//存放backFlag
		bizApprovalHis.setWarninglistResult(bizApproval.getWarninglistResult());// WARNINGLIST结果
		bizApprovalHis.setApproveResult(bizApproval.getApproveResult());// 审批结论
		bizApprovalHis.setApproveCreditLimit(bizApproval.getApproveCreditLimit());
		bizApprovalHis.setApproveCreditProduct(bizApproval.getApproveCreditProduct());
		bizApprovalHis.setManualLimit(bizApproval.getManualLimit());// 调整额度
		bizApprovalHis.setPolicyCode1(bizApproval.getPolicyCode1());
		bizApprovalHis.setPolicyCode2(bizApproval.getPolicyCode2());
		bizApprovalHis.setPolicyCode3(bizApproval.getPolicyCode3());
		bizApprovalHis.setViolateCode1(bizApproval.getViolateCode1());
		bizApprovalHis.setViolateCode2(bizApproval.getViolateCode2());
		bizApprovalHis.setViolateCode3(bizApproval.getViolateCode3());
		bizApprovalHis.setMemo(bizApproval.getMemo());
		bizApprovalHis.setRefuseCode1(bizApproval.getRefuseCode1());
		bizApprovalHis.setRefuseCode2(bizApproval.getRefuseCode2());
		bizApprovalHis.setRefuseCode3(bizApproval.getRefuseCode3());
		bizApprovalHis.setApprover(bizApproval.getApprover());
		bizApprovalHis.setApproverName(bizApproval.getApproverName());
		//bizApprovalHis.setHighlevleApprover(bizApproval.getHighlevleApprover());
		//bizApprovalHis.setHighlevleApprovetime(bizApproval.getHighlevleApprovetime());
		bizApprovalHis.setSubmitHighlevleTime(bizApproval.getSubmitHighlevleTime());
		bizApprovalHis.setApproveTime(bizApproval.getApproveTime());
		bizApprovalHis.setCurrOptGroup(bizApproval.getCurrOptGroup());//当前操作组
		bizApprovalHis.setIdType(bizApproval.getIdType());
		bizApprovalHis.setIdNo(bizApproval.getIdNo());
		bizApprovalHis.setYdjFlag(bizApproval.getYdjFlag());/* 1为易达金 2标准卡 */
		bizApprovalHis.setYearFeeDerateType(bizApproval.getYearFeeDerateType());
		bizApprovalHis.setCardFaceCode(bizApproval.getCardFaceCode());
		return bizApprovalHis;
	}
	/**
	 * 保存前纠错功能
	 * 
	 * @param ctx
	 */
	public void queryGlResult(Context ctx) {
		Map map = new HashMap<String, String>();// 提示语
		try {
			// 获得appId
			String appId = StringUtils.trimToEmpty((String) ctx.getDataMap().get("appId"));
			String userId = StringUtils.trimToEmpty((String) ctx.getDataMap().get("userIdParam"));
			String flag = StringUtils.trimToEmpty((String) ctx.getDataMap().get("flag"));
			Map<String, String> reqMap = (Map<String, String>) ctx.getDataMap().get("reqestData");
			reqMap.put("userId", userId);
			//wdb 校验该申请件是否在当前人名下(并且不是关键信息修改,杜绝同一申请件打开两次进行操作)
			AllotApplyAllot apply=sysApprovalCommonService.queryAllotApplyAllot(appId);
			reqMap.put("ydjFlag",apply.getYdjFlag());
			reqMap.put("matchingCardFlag", apply.getMatchingCardFlag());
			List<String> applyList=new ArrayList<String>(Arrays.asList("0","1","2","3"));
			if(apply==null||(!userId.equals(apply.getCurrOptUser()))||(!applyList.contains(apply.getDelStatus()))){//件已不在当前人名下
				ctx.setData("isSuccess", true);
				ctx.setData("msg", "当前申请件已不在当前队列中,请刷新页面");
				ctx.setData("yes", "0");
			}else{
				// 根据appId获得征信电核结果信息，获得过件码
				TelcheckResult result = sysApprovalCommonService.selectTelcheckResultByAppId(appId);
				String gjm = "";
				if (result != null) {
					gjm = result.getBlockCode();
				}
				Map<String,String> paramMap = new HashMap<String,String>();
				int count = 0;
				String approvalCard = reqMap.get("approveCreditProduct");
				paramMap.put("userCode", userId);
				paramMap.put("cardCode", approvalCard);
				count = sysApprovalCommonService.selectApprovalCard(paramMap);
				if(count == 0){
					map.put("msg", "当前用户没有审批该卡种【"+approvalCard+"】的权限");
					map.put("yes", "0");
				}else{
					// 根据appId填写人工干预后的结果并且插入审批操作结果
					if ("1".equals(flag)&&"1".equals(reqMap.get("approveResult"))) {// 易达金审批通过
						map = ydjSpResultPd(appId, flag, gjm, reqMap);
					} else if ("2".equals(flag)&&"1".equals(reqMap.get("approveResult"))) {// 标准卡审批通过
						Map<String, String> reqMap_fk = (Map<String, String>) ctx.getDataMap().get("reqestData_fk");
						if(reqMap_fk==null||"0".equals(reqMap_fk.get("approveResult"))){//非套卡
							//判断当前卡是否是单办附属卡
							if("2".equals(reqMap.get("fsk"))){//单办附卡
								map = fskSpResultPd(appId,flag,gjm,reqMap);
							}else{//主附同申 单办主卡 (普通标卡及易达金配发的标准卡)
								map = bzkSpResultPd(appId, flag, gjm, reqMap);
							}
						}else{//套卡
							//判断当前卡是否是单办附属卡
							if("2".equals(reqMap.get("fsk"))){//单办附卡批准
								map = fskSpResultPd(appId,flag,gjm,reqMap);
							}else{//主附同申 单办主卡批准
								//套卡校验
								paramMap.put("cardCode", reqMap_fk.get("approveCreditProduct"));
								count = sysApprovalCommonService.selectApprovalCard(paramMap);
								if(count == 0){
									map.put("msg", "当前用户没有审批该卡种【"+approvalCard+"】的权限");
									map.put("yes", "0");
								}else {
									map = bzkSpResultPdAndFk(appId, flag, gjm, reqMap,reqMap_fk);
								}
							}
						}
					}else if("0".equals(reqMap.get("approveResult"))){
						//排查0115、0116两种卡种情况  ami拒绝提示保留
						if("D405".equals(reqMap.get("refuseCode1"))&&("0115".equals(reqMap.get("approveCreditProduct"))
								||"0116".equals(reqMap.get("approveCreditProduct")))){
							map.put("msg", "该卡种可重复申请，是否拒绝");
							map.put("yes", "1");
						}else{
							map.put("msg", "保存成功");
							map.put("yes", "2");
						}
					}
				}
				//返回前台
				if (map!=null&&map.keySet()!=null&&map.keySet().size() > 0) {
					ctx.setData("isSuccess", true);
					ctx.setData("msg", map.get("msg"));
					ctx.setData("yes", map.get("yes"));
				} else {
					ctx.setData("isSuccess", false);
				}
			}
		} catch (Exception e) {
//			logger.info("queryGlResult发生系统错误，请技术人员检查");
//			e.printStackTrace();
			logger.error(StringUtils.trimToEmpty((String) ctx.getDataMap().get("appId")), e);
			ctx.setData("error", "发生系统错误，请技术人员检查");
		}
	}
	public Map<String,String> fskSpResultPd(String appId,String flag,String gjm,Map<String, String> reqMap) throws Exception{
		Map<String, String> map = new HashMap<String, String>();
		String msg = "";
		String approveCreditProduct = reqMap.get("approveCreditProduct");
		String wlm = reqMap.get("wlm")==null?"":reqMap.get("wlm");// 违例码
		String wlm2 = reqMap.get("wlm2")==null?"":reqMap.get("wlm2");// 违例码
		String wlm3 = reqMap.get("wlm3")==null?"":reqMap.get("wlm3");// 违例码
		FicoSdBlaze bzk = (FicoSdBlaze) sysApprovalCommonService.querySystemApprovalResult(appId, flag);
		if (bzk == null) {
			bzk = new FicoSdBlaze();
		}
		CardProduct card = null;
		if (approveCreditProduct != null) {
			card = sysApprovalCommonService.queryCardByCardCode(approveCreditProduct);// 审批卡
		}
		// 2对征信判定拒绝审批核发通过的申请件弹框禁止通过
		if (gjm!=null && !"".equals(gjm) && (gjm.indexOf("900")>-1 || gjm.indexOf("901")>-1)) {
			msg = "【征信结论为拒绝】";
			map.put("msg", msg);
			map.put("yes", "0");
			return map;
		}
		// 4策规定的需要下违例码但未下的弹窗提示未下违例码
		if(("建议拒绝".equals(bzk.getDecisionResult())||"直接拒绝".equals(bzk.getDecisionResult()))&& "".equals(wlm)){
			msg = "【系统决策结果为拒绝，未下违例码限制通过】";
			map.put("msg", msg);
			map.put("yes", "0");
			return map;
		}
		//新增校验 卡版面和年费类型是否正确  wdb	
		String yearFee= reqMap.get("yearFeeDerateType")==null?"":reqMap.get("yearFeeDerateType");
		if(card!=null&&card.getYearFeeDerateType()!=null&&(!"".equals(card.getYearFeeDerateType()))){
			List<String> yearFeeList=Arrays.asList(card.getYearFeeDerateType().split(","));
			if(!yearFeeList.contains(yearFee)){//不包含该年费类型 提示不能提交
				msg = msg+"【附属卡年费类型与授信卡产品不匹配,限制通过】";
				map.put("msg", msg);
				map.put("yes", "0");
				return map;
			}	
		}
		String cardFace=reqMap.get("cardFaceCode")==null?"":reqMap.get("cardFaceCode");
		if(card!=null&&card.getProductFace()!=null&&(!"".equals(card.getProductFace()))){
			List<String> produceFaceList=Arrays.asList(card.getProductFace().split(","));
			if(!produceFaceList.contains(cardFace)){//不包含该卡版面  提示不能提交  
				msg = msg+"【附属卡卡版面与授信卡产品不匹配,限制通过】";
				map.put("msg", msg);
				map.put("yes", "0");
				return map;
			}
		}
		//wdb 客户已持卡（3118表不包含T销卡卡片）大于等于12张 限制通过
		Map<String, Object> paramMap = new HashMap<String,Object>();
		paramMap.put("appId", appId);
		int num3=sysApprovalCommonService.selectFkCardLimit(paramMap);
		if(num3>11){
			msg = msg+"【超申请卡片申请数量限制】";
			map.put("msg", msg);
			map.put("yes", "0");
			return map;
		}
		//查询附属卡信息表
		BizInpApplC2 app = sysApprovalCommonService.findBiz2info(appId);
		//若下违例码则，不再提示下边消息
		//去掉违例码校验20200807 南云迪
//		if(reqMap.get("wlm")!=null&&!"".equals(reqMap.get("wlm"))){
//			return map;
//		}
		//获得附属卡年龄
		//int age = CommonUtil.getAge(app.getC2Birth());
		int age = sysApprovalCommonService.getPersonAge(appId);
		//no1:关系---与主卡持有/申请人关系
		if("Z".equals(app.getC2Relship())){
			msg = msg + "【附属卡申请人与主卡持有关系是‘其他’关系】";
		}
		//no3:签名、抄录文字
		RevCompInfo info = revCompInfoService.selectByPrimaryKey(appId);
		if(info==null || !"1".equals(info.getApplyInputfull()) || !"1".equals(info.getSignFull())){
			msg = msg + "【签名或抄录文字为空】";
		}
		//no4:未亲见签名(修改直接从数据库获取,不从页面获取)wdb 
		String c4Abtype=bzk.getC4Abtype()==null?"":bzk.getC4Abtype();
		if(!"1".equals(c4Abtype)){
			msg = msg + "【未亲见签名】";
		}
		/**
		 * 20200328  yuanquan  新增单办附属卡公安状态
		 */
		OpasCustBaseInfo custInfo = sysDecisionYdjService.selectCustAndPreSellimitByAppId(appId);
		String policeStatusFsk=custInfo.getPoliceStatusFsk()==null?"":custInfo.getPoliceStatusFsk();
		//新增外国人附卡公安判断  并与简项公安整合 20200807
		String wgrStatusFsk=custInfo.getWgrStatusFsk()==null?"":custInfo.getWgrStatusFsk();
		if((!"完全匹配".equals(policeStatusFsk)&&("01".equals(custInfo.getCertifiType2())||("02".equals(custInfo.getCertifiType2())))&&(!wlm.equals("A106")&&!wlm2.equals("A106")&&!wlm3.equals("A106")))//单办附卡中国人
				||(!"01".equals(wgrStatusFsk)&&("04".equals(custInfo.getCertifiType2())||"07".equals(custInfo.getCertifiType2()))&&(!wlm.equals("A106")&&!wlm2.equals("A106")&&!wlm3.equals("A106"))))//单办附卡外国人
		{
			msg = msg+"【附属卡公安异常,需下违例码后可通过】";
			map.put("msg", msg);
			map.put("yes", "0");
			return map;
		}
		//no2:年龄--->=71 or <14 周岁
		if(age < 14 || age >= 71){
			msg = msg + "【年龄不符政策标准,未下违例码】";
			map.put("msg", msg);
			map.put("yes", "0");
			return map;
		}
		if("".equals(msg.trim())){
			return map;
		}else{
			map.put("msg", msg);
			map.put("yes", "1");
		}
		return map;
	}
	public Map<String, String> ydjSpResultPd(String appId, String flag, String gjm, Map<String, String> reqMap)
			throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		String msg = "";
		String applyCard = reqMap.get("applyCard");
		String isWuWangDian = reqMap.get("isWuWangDian")==null?"":reqMap.get("isWuWangDian");// 无网点
		String wlm = reqMap.get("wlm")==null?"":reqMap.get("wlm");// 违例码
		String wlm2 = reqMap.get("wlm2")==null?"":reqMap.get("wlm2");// 违例码
		String wlm3 = reqMap.get("wlm3")==null?"":reqMap.get("wlm3");// 违例码
		BigDecimal approveCreditLimit = new BigDecimal(
				reqMap.get("approveCreditLimit") == "" ? "0" : reqMap.get("approveCreditLimit"))
						.multiply(new BigDecimal(1000));// 授信额度
		String matchingCardFlag= reqMap.get("matchingCardFlag");//易达金套卡标识  0 无套卡 1易达金套卡标准卡 2 易达金申请卡 
		//String bcFlag=reqMap.get("bcFlag")==null?"":reqMap.get("bcFlag");// 征信批量标识
		FicoYdjBlaze ydj = (FicoYdjBlaze) sysApprovalCommonService.querySystemApprovalResult(appId, flag);
		if (ydj == null) {
			ydj = new FicoYdjBlaze();
		}
		// 1最终授信产品低于申请产品级别，且最终授信额度高于申请产品下限时，弹框提示内容
		// 获得授信产品的产品详情
		CardProduct applyProduct = sysApprovalCommonService.queryCardByCardCode(applyCard);// 申请卡
		String applyJb = null;
		if(applyProduct!=null){
			applyJb = applyProduct.getCardType();// 申请卡级别
		}
		String approveCreditProduct = reqMap.get("approveCreditProduct");
		CardProduct card = null;
		if (approveCreditProduct != null) {
			card = sysApprovalCommonService.queryCardByCardCode(approveCreditProduct);// 审批卡
		}
		// 2对征信判定拒绝审批核发通过的申请件弹框禁止通过
		if (gjm!=null && !"".equals(gjm) && gjm.indexOf("900")>-1) {
			msg = "【征信结论为拒绝】";
			map.put("msg", msg);
			map.put("yes", "0");
			return map;
		}
		// 3授信额度低于或高于产品上下限弹框禁止通过OPAS_PARAM_CARD_PRODUCT
		if ((card != null && approveCreditLimit.compareTo(new BigDecimal(card.getLineLow())) == -1)||
				(card != null&& approveCreditLimit.compareTo(new BigDecimal(card.getLineHight())) == 1)) {
			msg = "【授信额度高于或低于产品上下限】";
			map.put("msg", msg);
			map.put("yes", "0");
			return map;
		}
		// 4策规定的需要下违例码但未下的弹窗提示未下违例码
		//4.1系统建议拒绝/系统直接拒绝时，且审批人工通过，未下违例码时
		//1-直接批准2-建议批准3-直接拒绝4-建议拒绝5-人工审核
		//备注：因为系统决策结果在表里直接存的汉字，故而如此
		if(("建议拒绝".equals(ydj.getDecisionResult())||"直接拒绝".equals(ydj.getDecisionResult()))&& "".equals(wlm)){
			msg = "【系统决策结果为拒绝，未下违例码限制通过】";
			map.put("msg", msg);
			map.put("yes", "0");
			return map;
		}
		//4.2触碰WARNINGLIST里的无网点提示项，且审批人工通过，未下违例码时
		if ("1".equals(isWuWangDian) && "".equals(wlm)) {
			msg = "【无网点或未匹配到网申合作商户,未下违例码】";
			map.put("msg", msg);
			map.put("yes", "0");
			return map;
		}
		//4.5  wdb 已持卡账户状态异常的定义：账户状态除U、Q以外，非空状态的
		OpasCustBaseInfo custInfo = sysDecisionYdjService.selectCustInfoByAppId(appId);
		int closeCode1=custInfo.getCloseCode1();
		if(closeCode1>0&&"".equals(wlm)){
			msg = "【已持卡账户状态异常，未下违例码】";
			map.put("msg", msg);
			map.put("yes", "0");
			return map;
		}
		//新增校验 卡版面和年费类型是否正确  wdb		
		String yearFee= reqMap.get("yearFeeDerateType")==null?"":reqMap.get("yearFeeDerateType");
		if(card!=null&&card.getYearFeeDerateType()!=null&&(!"".equals(card.getYearFeeDerateType()))){
			List<String> yearFeeList=Arrays.asList(card.getYearFeeDerateType().split(","));
			if(!yearFeeList.contains(yearFee)){//不包含该年费类型 提示不能提交
				msg = msg+"【年费类型与授信卡产品不匹配,限制通过】";
				map.put("msg", msg);
				map.put("yes", "0");
				return map;
			}	
		}
		String cardFace=reqMap.get("cardFaceCode")==null?"":reqMap.get("cardFaceCode");
		if(card!=null&&card.getProductFace()!=null&&(!"".equals(card.getProductFace()))){
			List<String> produceFaceList=Arrays.asList(card.getProductFace().split(","));
			if(!produceFaceList.contains(cardFace)){//不包含该卡版面  提示不能提交  
				msg = msg+"【卡版面与授信卡产品不匹配,限制通过】";
				map.put("msg", msg);
				map.put("yes", "0");
				return map;
			}
		}
		// 5挂起申请件不得提交通过
		/*AllotApplyAllot allot = sysApprovalCommonService.queryAllotApplyAllot(appId);
		if (allot != null && ("2".equals(allot.getCurrStatus()) || "4".equals(allot.getCurrStatus()))) {
			msg = "挂起申请件不可进行提交";
			map.put("yes", "0");
			map.put("msg", msg);
			return map;
		}*/
		// String spJb = card.getCardType();//申请卡级别
		if (applyJb!=null&&card != null && Integer.parseInt(card.getCardType()) < Integer.parseInt(applyJb)) {
			if (approveCreditLimit.compareTo(new BigDecimal(applyProduct.getLineLow())) > -1) {
				msg = msg+"【异常降级，是否提交】";
				/*map.put("msg", msg);
				map.put("yes", "1");// 点击“是”则强制通过
				return map;*/
			}
		}
		//人行查询次数
		/*int org1mSum3 = pbocService.queryMonthApplyCount(appId);
		if(org1mSum3>=5){
			msg = msg+"【请关注人行一个月内申请查询大于等于5次,是否提交】";
		}*/
		//逾期超过500元
		//0-无套卡标识  1-主卡  2-套卡
		String appID;
		char[] arr = appId.toCharArray();
		if("1".equals(matchingCardFlag)&&arr.length==16&&arr[15]=='1'){
			arr[15]='2';
			appID = new String(arr);
		}else if("1".equals(matchingCardFlag)&&arr.length==16&&arr[15]=='2'){
			arr[15]='1';
			appID = new String(arr);
		}else{
			appID = appId;
		}
		/*char[] arr = appId.toCharArray();
		if(arr.length==16&&arr[15]=='1'){
			arr[15]='2';
			appID = new String(arr);
		}else{
			appID = appId;
		}*/
//		int maxDue = pbocService.queryNowOrOverdueCount(appID);
		//20200925 审批分层项目注释
		/*int maxDue = pbocService.queryCurOverdueMax(appID);
		if(maxDue>=500){
			//msg = msg+"【历史或者当前逾期超过500元】";
			msg = msg+"【请关注贷款/贷记卡历史或当期逾期,是否提交】";
		}*/
		//申请人是否多次申请相同卡种
	//	String approval = sysApprovalCommonService.selectAppByAppId(appID);
		/*String apply = sysApprovalCommonService.selectApplyByAppId(appId);
		if(apply != null&& !"".equals(apply)){
			msg = msg+"【近期已批准相同卡产品，是否强制提交】";
		}*/
		List<String> applyList=sysApprovalCommonService.selectApplyCardByAppId(appId);
		if(applyList != null&& applyList.size()>0){
			if(applyList.size()>1){
				msg = msg+"【近期已批准相同卡产品，请确认该卡种是否可重复申请】";
				logger.info(appId+"近期已批准相同卡产品的申请件编号"+applyList.toString()+"集合长度"+applyList.size());
			}else if(applyList.size()==1){
				if(!applyList.contains(appId)&&!applyList.contains(null)){//目前只有该申请件  不报错误信息同时排除list里的值为空
					msg = msg+"【近期已批准相同卡产品，请确认该卡种是否可重复申请】";
					logger.info(appId+"近期已批准相同卡产品的申请件编号"+applyList.get(0));
				}
			}
		}
		//如果申请人已经持有卡且卡片状态正常,需要提示
		Map<String, Object> paramMap = new HashMap<String,Object>();
		paramMap.put("appId", appId);
		paramMap.put("product", reqMap.get("approveCreditProduct"));
		int num = sysApprovalCommonService.findHaveCard(paramMap);
		if(num > 0){
			if(msg.indexOf("近期已批准相同卡产品，请确认该卡种是否可重复申请")==-1){
				msg = msg+"【近期已批准相同卡产品，请确认该卡种是否可重复申请】";
			}
		}
		//芝麻信用积分
		/*String ivs = antService.selectIvsScoreByAppId(appId);
		int ivsScore = Integer.parseInt(ivs);
		if(ivsScore<40){
			msg = msg+"【IVS分低于40分】";
		}*/
		/*
		 *当最终审批卡种与申请授信产品不一致，提交界面时需提示“授信产品不一致”。
		 */
		if (applyCard != null && !applyCard.equals(reqMap.get("approveCreditProduct"))) {
			msg = msg+"【授信产品不一致】";
		}
		
		//6 ID有效期 弃用  20200113
//		String yxq = sysApprovalCommonService.findYxqPd(appId);
//		if("00".equals(yxq)){
//			msg = msg+"【ID有效期异常】【ID已过有效期】";
//		}else if("01".equals(yxq)){
//			msg = msg+"【ID有效期异常】";
//		}else if("10".equals(yxq)){
//			msg = msg+"【ID已过有效期】";
//		}
		
		
		//7 系统根据申请人身份证件号码判断申请人实际年龄，年龄判断标准需具体到日，
		// 对于年龄低于23、女性年龄高于54、男性年龄高于59的年龄
		map = isNianlinZhenCe(appId,msg,custInfo);
		int age = custInfo.getAge()==null?0:custInfo.getAge();
		if((map!=null&&map.keySet()!=null&&map.keySet().size()>0)){
			msg = msg+map.get("msg");
		}
		//wdb 客户已持卡（3118表不包含T销卡卡片）大于等于12张 限制通过 2019.10.24新增Q卡也不包含
		String warnStr="0";
		int num3=sysApprovalCommonService.selectCardLimit(paramMap);
		if(num3>11){
			msg = msg+"【超申请卡片申请数量限制】";
			warnStr="1";
		}
		//易达金审批ID有效期校验 更改为 以系统决策页面为准 20200113
		Map<String,String> Idmap = isIDYouXiao(reqMap,"");
		if((Idmap!=null&&Idmap.keySet()!=null&&Idmap.keySet().size()>0)){
			if("0".equals(Idmap.get("yes"))){
				msg = msg+map.get("msg");
				warnStr="1";
			}else{
				msg = msg+map.get("msg");
			}
		}
		//2019.8.5 职务为空校验
		/*String c1Title=custInfo.getC1Title()==null?"":custInfo.getC1Title();
		if("1".equals(bcFlag)&&"".equals(c1Title)){
			msg = msg+"【职务不能为空】";
			warnStr="1";
		}*/
		String policeStatus=custInfo.getPoliceStatus()==null?"":custInfo.getPoliceStatus();
//		if(!"完全匹配".equals(policeStatus)&&!"库中无此号".equals(policeStatus)&&"".equals(wlm)){
//			msg = msg+"【公安异常,需下违例码后可通过】";
//			warnStr="1";
//		}
		//20200709 新增违例码
		if(!"完全匹配".equals(policeStatus)&&("01".equals(custInfo.getCertifiType())||"02".equals(custInfo.getCertifiType()))&&(!wlm.equals("A106")&&!wlm2.equals("A106")&&!wlm3.equals("A106"))){
			msg = msg+"【主卡公安异常,需下违例码后可通过】";
			warnStr="1";
		}
		
		//外国人核查状态  单办主卡/主附同申取主卡   易达金不校验外国人公安 20200804 南云迪
//		String wgrStatus = custInfo.getWgrStatus()==null?"":custInfo.getWgrStatus();
//		if(("02".equals(wgrStatus)||"03".equals(wgrStatus)||"04".equals(wgrStatus)||"11".equals(wgrStatus))&&!wlm.equals("A106")){
//			msg = msg+"【公安异常,需下违例码后可通过】";
//			warnStr="1";
//		}
		
		
		BigDecimal dlLmt=ydj.getDlAmt()==null?new BigDecimal(0):ydj.getDlAmt();
		if(dlLmt.compareTo(approveCreditLimit)==-1){
			msg = msg+"【授信额度高于DL额度】";
			warnStr="1";
		}
		//易达金个人税前年收入检验 
		//北京地区:"611","811","911" ,上海地区:"921",广州地区:"931",深圳地区:"932",南京地区:"923",杭州："922"
		/** 
		如果数组中包含被截取的appid的 8到10三位字符串,则当前税前年收入应该大于等于6万才算符合政策标准，
		如果数组中不包含被截取的appid的 8到10三位字符串,则当前税前年收入应该大于等于3.6万才算符合政策标准。
		*/
		BigDecimal c1Earn=custInfo.getC1Earn()==null?new BigDecimal(0):custInfo.getC1Earn();
		List<String> c1EarnList = Arrays.asList("611","811","911","921","931","932","923","922");
		String appSub = appId.substring(7, 10);
		int c1Earnto6 = new BigDecimal("6").compareTo(c1Earn);
		int c1Earnto3 = new BigDecimal("3.6").compareTo(c1Earn);
		
		if(c1EarnList.contains(appSub) && c1Earnto6 ==1 ){
			msg = msg+"【收入不符政策标准!】";
		}
		
		if(!c1EarnList.contains(appSub) && c1Earnto3 == 1 ){
			msg = msg+"【收入不符政策标准!】";
		}
		
		/**
		 * 20200401
		 * PAD人像比对 审批提交校验
		 * 授信审批环节（及易达金初审），当人像比对结果为“不一致”且审批以批准提交时，弹框提示：人像比对结果异常，是否提交通过。点击“确认”申请件正常流转，点击“取消”留在当前页。
		 */
		
		String padRxbd = sysDecisionYdjService.selectPoliceXPInfo(appId);
		if("完全不一致".equals(padRxbd)){
			msg = msg+"【人像比对结果异常,是否提交】";
		}
		if("1".equals(warnStr)){
			map.put("yes", "0");
			map.put("msg", msg);
			return map;
		}
		if(!"".equals(msg)){
			map.put("yes", "1");
			map.put("msg", msg);
			return map;
		}
		return map;
	}

	public Map<String, String> bzkSpResultPd(String appId, String flag, String gjm, Map<String, String> reqMap)
			throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		String msg = "";
		String applyCard = reqMap.get("applyCard");
		String approveCreditProduct = reqMap.get("approveCreditProduct");
		String isWuWangDian = reqMap.get("isWuWangDian")==null?"":reqMap.get("isWuWangDian");// 无网点
		String wlm = reqMap.get("wlm")==null?"":reqMap.get("wlm");// 违例码
		String wlm2 = reqMap.get("wlm2")==null?"":reqMap.get("wlm2");// 违例码
		String wlm3 = reqMap.get("wlm3")==null?"":reqMap.get("wlm3");// 违例码
		//String bcFlag=reqMap.get("bcFlag")==null?"":reqMap.get("bcFlag");// 征信批量标识
		//String ydjFlag =  sysApprovalCommonService.selectYdjFlagByAppId(appId);
		BigDecimal approveCreditLimit = new BigDecimal(
				reqMap.get("approveCreditLimit") == "" ? "0" : reqMap.get("approveCreditLimit"))
						.multiply(new BigDecimal(1000));// 授信额度
		String matchingCardFlag= reqMap.get("matchingCardFlag");//易达金套卡标识  0 无套卡 1易达金套卡标准卡 2 易达金申请卡 
		FicoSdBlaze bzk = (FicoSdBlaze) sysApprovalCommonService.querySystemApprovalResult(appId, flag);
		if (bzk == null) {
			bzk = new FicoSdBlaze();
		}
		/*
		 *当最终审批卡种与决策授信产品不一致，提交界面时需提示“授信产品不一致”。
		 */
		// 获得决策授信产品CP_RST
		/*if (applyCard != null && approveCreditProduct!=null && !approveCreditProduct.equals(applyCard)) {
			msg = "授信产品不一致";
			map.put("msg", msg);
			map.put("yes", "1");
			return map;
		}*/
		/*
		 * 默认反写blaze输出额度，审批人员可根据实际审批情况修改授信额度；
		 * 当最终审批额度与系统建议额度差异大于X%（X参数可配）150%，提交界面时需提示“授信额度不一致，请提交违例码”。
		 */
		/*BigDecimal PROPOSED_LMT = bzk.getProposedLmt();// 系统建议额度
		if (approveCreditLimit.compareTo(PROPOSED_LMT.multiply(new BigDecimal(1.5))) == 1 && "".equals(wlm)) {
			msg = "授信额度不一致，请提交违例码";
			map.put("msg", msg);
			map.put("yes", "0");
			return map;
		}*/
		// 1最终授信产品低于申请产品级别，且最终授信额度高于申请产品下限时，弹框提示内容
		// 获得授信产品的产品详情
		CardProduct applyProduct = sysApprovalCommonService.queryCardByCardCode(applyCard);// 申请卡
		String applyJb = null;
		if(applyProduct!=null){
			applyJb = applyProduct.getCardType();// 申请卡级别
		}
		CardProduct card = null;
		if (approveCreditProduct != null) {
			card = sysApprovalCommonService.queryCardByCardCode(approveCreditProduct);// 审批卡
		}
		// 2对征信判定拒绝审批核发通过的申请件弹框禁止通过
		if (gjm!=null && !"".equals(gjm) && (gjm.indexOf("900")>-1 || gjm.indexOf("901")>-1)) {
			msg = "【征信结论为拒绝】";
			map.put("msg", msg);
			map.put("yes", "0");
			return map;
		}
		// 3授信额度低于或高于产品上下限弹框禁止通过OPAS_PARAM_CARD_PRODUCT
		if ((card != null && approveCreditLimit.compareTo(new BigDecimal(card.getLineLow())) == -1)||
				(card != null && approveCreditLimit.compareTo(new BigDecimal(card.getLineHight())) == 1)) {
			msg = "【授信额度高于或低于产品上下限】";
			map.put("msg", msg);
			map.put("yes", "0");
			return map;
		}
		// 4策规定的需要下违例码但未下的弹窗提示未下违例码---WARNINGLIST里的无网点
		if ("1".equals(isWuWangDian) && "".equals(wlm)) {
			msg = "【无网点或未匹配到网申合作商户,未下违例码】";
			map.put("msg", msg);
			map.put("yes", "0");
			return map;
		}
		if(("建议拒绝".equals(bzk.getDecisionResult())||"直接拒绝".equals(bzk.getDecisionResult()))&& "".equals(wlm)){
			msg = "【系统决策结果为拒绝，未下违例码限制通过】";
			map.put("msg", msg);
			map.put("yes", "0");
			return map;
		}
		//4.2主卡申请人年龄>=66周岁或<18周岁，且审批人工通过，未下违例码时
		OpasCustBaseInfo custInfo = sysDecisionYdjService.selectCustAndPreSellimitByAppId(appId);
		String ydjFlag=custInfo.getYdjFlag();
		int age = custInfo.getAge()==null?0:custInfo.getAge();
		if((age>=66||age<18)&&"".equals(wlm)){
			//map.put("msg", "【主卡申请人年龄"+age+",其年龄不在18<=age<66周岁之间,未下违例码限制其通过】");
			map.put("msg", "【年龄不符政策标准,未下违例码】");
			map.put("yes", "0");//“0”限制通过
			return map;
		}else if(age==0){
			logger.error("该主卡申请人客户年龄信息缺失，请检查数据信息");
		}
		//4.3附属卡申请人年龄>=71周岁或<14周岁，且审批人工通过，未下违例码时
		//当前卡为主副同审并且没有拒绝附属卡
		if("1".equals(reqMap.get("fsk"))&&!"1".equals(reqMap.get("refuseChildcard"))){
			BizInpApplC2 bizInpApplC2 = applyService.findBiz2info(appId);
			if(bizInpApplC2!=null){
				Date birth = bizInpApplC2.getC2Birth();
				if(birth!=null){
					int fsAge = (new Date()).getYear() - birth.getYear();
					if((fsAge>=71||fsAge<14)&&"".equals(wlm)){
						msg = "【附属卡年龄不符政策标准,未下违例码】";
						map.put("msg", msg);
						map.put("yes", "0");
						return map;
					}
				}
			}
		}
		//4.5  wdb 已持卡账户状态异常的定义：账户状态除U、Q以外，非空状态的
		int closeCode1=custInfo.getCloseCode1();
		if(closeCode1>0&&"".equals(wlm)){
			msg = "【已持卡账户状态异常，未下违例码】";
			map.put("msg", msg);
			map.put("yes", "0");
			return map;
		}
		//新增校验 卡版面和年费类型是否正确  wdb		
		String yearFee= reqMap.get("yearFeeDerateType")==null?"":reqMap.get("yearFeeDerateType");
		if(card!=null&&card.getYearFeeDerateType()!=null&&(!"".equals(card.getYearFeeDerateType()))){
			List<String> yearFeeList=Arrays.asList(card.getYearFeeDerateType().split(","));
			if(!yearFeeList.contains(yearFee)){//不包含该年费类型 提示不能提交
				msg = msg+"【年费类型与授信卡产品不匹配,限制通过】";
				map.put("msg", msg);
				map.put("yes", "0");
				return map;
			}	
		}
		String cardFace=reqMap.get("cardFaceCode")==null?"":reqMap.get("cardFaceCode");
		if(card!=null&&card.getProductFace()!=null&&(!"".equals(card.getProductFace()))){
			List<String> produceFaceList=Arrays.asList(card.getProductFace().split(","));
			if(!produceFaceList.contains(cardFace)){//不包含该卡版面  提示不能提交  
				msg = msg+"【卡版面与授信卡产品不匹配,限制通过】";
				map.put("msg", msg);
				map.put("yes", "0");
				return map;
			}
		}
		// 5挂起申请件不得提交通过
		/*AllotApplyAllot allot = sysApprovalCommonService.queryAllotApplyAllot(appId);
		if (allot != null && ("2".equals(allot.getCurrStatus()) || "4".equals(allot.getCurrStatus()))) {
			msg = "挂起申请件不可进行提交";
			map.put("msg", msg);
			map.put("yes", "0");
			return map;
		}*/
		//6 ID有效期
		map = isIDYouXiao(reqMap,msg);
		if(map!=null&&map.keySet()!=null&&map.keySet().size()>0){
			if("0".equals(map.get("yes"))){
				return map;
			}else{
				msg = msg+map.get("msg");
			}
		}
		//4.4授信额度低于系统建议额度50%的，且审批人工通过，未下违例码时
		/*if(bzk!=null&&bzk.getProposedLmt()!=null
				&&approveCreditLimit.compareTo(bzk.getProposedLmt().multiply(new BigDecimal(0.5)))==-1&&"".equals(wlm)){
			msg =  msg+ "【授信额度低于系统建议额度50%,未下违例码,是否提交】";
		}*/
		if(bzk!=null&&bzk.getProposedLmt()!=null
				&&approveCreditLimit.compareTo(bzk.getProposedLmt().multiply(new BigDecimal(0.5)))==-1
				&&!("A207".equals(wlm)||"A207".equals(wlm2)||"A207".equals(wlm3))){
			msg =  msg+ "【未下A207限制通过】";
		}
		// String spJb = card.getCardType();//申请卡级别 新客群二期去掉该校验
	/*	if (applyJb!=null&&card != null && Integer.parseInt(card.getCardType()) < Integer.parseInt(applyJb)) {
			if (approveCreditLimit.compareTo(new BigDecimal(applyProduct.getLineLow())) > -1) {
				msg = msg+"【异常降级,是否提交】";
			}
		}*/
		/*if("1".equals(ydjFlag)){
			appId = appId.substring(0, 15) + "2";		
		}*/
		String appID;
		char[] arr=appId.toCharArray();
		if("1".equals(ydjFlag)&&"1".equals(matchingCardFlag)&&arr[15]=='1'){
			arr[15]='2';
			appID=new String(arr);
		}else if("1".equals(ydjFlag)&&"1".equals(matchingCardFlag)&&arr[15]=='2'){
			arr[15]='1';
			appID=new String(arr);
		}else{
			appID=appId;
		}
		//人行查询次数
		//20200925 审批分层项目注释
		/*int org1mSum3 = pbocService.queryMonthApplyCount(appID);
		if(org1mSum3>=5&&"2".equals(ydjFlag)){
			msg = msg+"【请关注人行一个月内申请查询大于等于5次,是否提交】";
		}*/
		//逾期超过500元
//		int maxDue = pbocService.queryNowOrOverdueCount(appID);
		//20200925 审批分层项目注释
		/*int maxDue = pbocService.queryCurOverdueMax(appID);
		if(maxDue>=500){
			msg = msg+"【请关注贷款/贷记卡历史或当期逾期,是否提交】";
		}*/
		//申请人是否多次申请同意卡种 wdb:排除0115、0116两种卡种的重复申请
		//String approval = sysApprovalCommonService.selectAppByAppId(appId);
		/*String apply = sysApprovalCommonService.selectApplyByAppId(appId);
		if(apply != null&& !"".equals(apply)){
			msg = msg+"【近期已批准相同卡产品，是否强制提交】";
		}*/
		Map<String, Object> paramMap = new HashMap<String,Object>();
		paramMap.put("appId", appId);
		paramMap.put("product", reqMap.get("approveCreditProduct"));
		//主附同申卡片数量限制 20200827
		if(custInfo.getC1c2Flag().equals("1")){
			if(!"0115".equals(approveCreditProduct)&&!"0116".equals(approveCreditProduct)){
				List<String> applyList=sysApprovalCommonService.selectApplyCardByAppId(appId);
				if(applyList != null&& applyList.size()>0){
					if(applyList.size()>1){
						msg = msg+"【近期已批准相同卡产品，请确认该卡种是否可重复申请】";
						logger.info(appId+"近期已批准相同卡产品的申请件编号"+applyList.toString()+"集合长度"+applyList.size());
					}else if(applyList.size()==1){
						if(!applyList.contains(appId)&&!applyList.contains(null)){//目前只有该申请件  不报错误信息 同时排除list里的值为空
							msg = msg+"【近期已批准相同卡产品，请确认该卡种是否可重复申请】";
							logger.info(appId+"近期已批准相同卡产品的申请件编号"+applyList.get(0));
						}
					}
				}
				//如果申请人已经持有卡且卡片状态正常,需要提示
				int num = sysApprovalCommonService.findHaveCard(paramMap);
				if(num > 0){
					if(msg.indexOf("近期已批准相同卡产品，请确认该卡种是否可重复申请")==-1){
						msg = msg+"【近期已批准相同卡产品，请确认该卡种是否可重复申请】";
					}
				}
			}
			
		}
		//芝麻信用积分
		/*String ivs = antService.selectIvsScoreByAppId(appId);
		int ivsScore = Integer.parseInt(ivs);
		if(ivsScore<40){
			msg = msg+"【IVS分低于40分】";
		}	*/
		//wdb 新增校验  刚性扣减额度与审批额度校验   2019.8.5新增违例码校验2019.9.6 去掉违例码校验，将上海地区修改为全国提示2019.9.9对易达金配发卡不校验2019.10.28新增排除已持卡
		BigDecimal dlLmt=bzk.getDlLmt()==null?new BigDecimal(0):bzk.getDlLmt();
		if("2".equals(ydjFlag)&&dlLmt.compareTo(approveCreditLimit)==-1&&"0".equals(custInfo.getIsHavecardCust())){
			msg = msg+"【授信额度高于标准DL】";
			map.put("msg", msg);
			map.put("yes", "0");
			return map;
		}
		//wdb 自进件提示  
		// 20200925 审批分层注释
		/*String c4Apsour=custInfo.getC4Apsour()==null?"":custInfo.getC4Apsour();
		if("7".equals(c4Apsour)&&"2".equals(ydjFlag)){
			msg = msg+"【请关注自进件，是否提交】";
		}*/
		//wdb 持非境内居民身份证申请的客户
		String c1Idtype=custInfo.getCertifiType()==null?"":custInfo.getCertifiType();
		if(!"01".equals(c1Idtype)){
			msg = msg+"【请关注外籍客户，是否审核通过】";
		}
		//wdb 0072、0071 主卡年龄不达23周岁
		if(("0071".equals(approveCreditProduct)||"0072".equals(approveCreditProduct))&&age<23){
			msg = msg+"【年龄不达23周岁,是否确认提交】";
		}
		if(("4".equals(card.getCardType())||"5".equals(card.getCardType()))&&age<23&&"".equals(wlm)){//强制拦截
			msg = msg+"【年龄不符卡种政策标准，未下违例码】";
			map.put("msg", msg);
			map.put("yes", "0");
			return map;
		}
		//wdb 0115、0116两种卡判定审批系统中该卡产品编号下已批准的卡片数量
		//主附同申卡片数量限制 20200827
		if(custInfo.getC1c2Flag().equals("1")){
			if("0115".equals(approveCreditProduct)||"0116".equals(approveCreditProduct)){
				int num2=sysApprovalCommonService.selectCountHaveCard(paramMap);
				if(num2>8){
					msg = msg+"【已有"+num2+"张卡，限制审批通过】";
					map.put("msg", msg);
					map.put("yes", "0");
					return map;
				}else if(num2>0&&num2<9){
					msg = msg+"【申请人已持有该卡"+num2+"张，是否继续核批】";
				}
			}
			
		}
		//wdb 境内居民、非境内居民税前收入校验
		String c1Nation=custInfo.getC1Nation()==null?"":custInfo.getC1Nation();
		BigDecimal c1Earn=custInfo.getC1Earn()==null?new BigDecimal(0):custInfo.getC1Earn();
		if(("5".equals(c1Nation) || "1".equals(c1Nation))&&"2".equals(ydjFlag)&&c1Earn.compareTo(BigDecimal.valueOf(3.6))==-1){
			msg = msg+"【收入不符政策标准，是否提交】";
		}else if(!("5".equals(c1Nation) || "1".equals(c1Nation))&&"2".equals(ydjFlag)&&c1Earn.compareTo(BigDecimal.valueOf(6))==-1){
			msg = msg+"【收入不符政策标准，是否提交】";
		}
		//wdb 客群级别校验（最终授信卡产品级别）
		if("2".equals(ydjFlag)&&("4".equals(card.getCardType())||"5".equals(card.getCardType()))&&
				(reqMap.get("policyCode1").indexOf("B")>-1||reqMap.get("policyCode1").indexOf("C")>-1)){
			msg = msg+"【所属客群分类与申请卡产品对应关系异常，是否确认提交】";
		}else if("2".equals(ydjFlag)&&"3".equals(card.getCardType())&&reqMap.get("policyCode1").indexOf("C")>-1){
			msg = msg+"【所属客群分类与申请卡产品对应关系异常，是否确认提交】";
		}
		
		/**
		 * 新增存量客户校验 胡宝川   马超群 20200825
		 * 适用范围  只校验标准卡   单办主卡 主附同申
		 * 校验逻辑如下 (只删除标卡主卡)
		 * 删除系统现针对12张卡上限提交校验
		 * 删除系统同卡产品重复申请提交校验
		 * 删除系统ami卡9张上限提交校验
		 * ami审批拒绝提交张数限制保留
		 * 校验1
		 * 流转至人工环节的存量客户（按现有审核系统是否为存量客户判断,存量卡标识 0:否 1：是 2：异常），
		 * 将“人工最终授信额度”与“标卡账户额度、刚性扣减额度”进行比对，
		 * 需结合特定违例码（AXXX）可提交通过
		 * 存量客户 是 A207+A207 人工最终授信额度大于标卡账户额度且大于刚扣额度时
		 * 存量客户 排查  A901+A207  人工最终授信额度大于刚扣额度时
		 * 存量客户 否  同现有逻辑
		 * 提示信息    额度不符要求，请审慎评估
		 * 校验2
		 * 卡片重复申请为“是”时
		 * 且未下任何违例码时(新加) 20200916  马超群 
		 * 提示信息  重复申请
		 * 
		 */
		String warnStr="0";
		//易达金配发的标准卡存量不做校验  王伟涛 20200907
		if(!(custInfo.getMatchingCardFlag().equals("1")&&custInfo.getYdjFlag().equals("1"))){
			//存量客户标识
			String  isHavecardCust = custInfo.getIsHavecardCust();
			//标卡账户额度
			String creditLimit = custInfo.getCreditLimit()==null?"0":custInfo.getCreditLimit();
			//刚扣额度
			FicoSdBlaze sysResultInfo = (FicoSdBlaze)sysDecisionYdjService.selectSystemDecisionByAppId(appId, "2");
			BigDecimal DlLmt = sysResultInfo.getDlLmt()==null?new BigDecimal(0):sysResultInfo.getDlLmt();
			if(isHavecardCust.equals("1")&&approveCreditLimit.compareTo(new BigDecimal(creditLimit))==1
					&&approveCreditLimit.compareTo(DlLmt)==1&&(!wlm.equals("A207")||!wlm2.equals("A207"))){
				msg = msg+"【额度不符要求，请审慎评估】";
				warnStr="1";
			}
			if(isHavecardCust.equals("2")&&approveCreditLimit.compareTo(DlLmt)==1&&(!wlm.equals("A901")||!wlm2.equals("A207"))){
				msg = msg+"【额度不符要求，请审慎评估】";
				warnStr="1";
			}
		}
		
		if("3".equals(custInfo.getC1c2Flag())&&null!=custInfo.getRepetitionVerdict()&&"1".equals(custInfo.getRepetitionVerdict())&&wlm.equals("")&&wlm2.equals("")&&wlm3.equals("")){//标卡 单办主卡
			msg = msg+"【重复申请】";
			warnStr="1";
		}
		
		//wdb 客户已持卡（3118表不包含T销卡卡片）大于等于12张 限制通过 2019.10.24新增Q卡也不包含
		int num3=sysApprovalCommonService.selectCardLimit(paramMap);
		if(num3>11){
			msg = msg+"【超申请卡片申请数量限制】";
			warnStr="1";
		}
		//2019.8.5 职务为空校验
		/*String c1Title=custInfo.getC1Title()==null?"":custInfo.getC1Title();
		if("1".equals(bcFlag)&&"".equals(c1Title)){
			msg = msg+"【职务不能为空】";
			warnStr="1";
		}*/
//		if(!"完全匹配".equals(policeStatus)&&!"库中无此号".equals(policeStatus)&&"".equals(wlm)){
//			msg = msg+"【公安异常,需下违例码后可通过】";
//			warnStr="1";
//		}
		//20200709 新增违例码
		//新增外国人主附同申与单办主卡公安判断  并与简项公安整合 20200807
		String policeStatus=custInfo.getPoliceStatus()==null?"":custInfo.getPoliceStatus();
		String wgrStatus=custInfo.getWgrStatus()==null?"":custInfo.getWgrStatus();
		String policeStatusFsk=custInfo.getPoliceStatusFsk()==null?"":custInfo.getPoliceStatusFsk();
		String wgrStatusFsk=custInfo.getWgrStatusFsk()==null?"":custInfo.getWgrStatusFsk();
		String certifiType = custInfo.getCertifiType()==null?"":custInfo.getCertifiType();
		String certifiType2 = custInfo.getCertifiType2()==null?"":custInfo.getCertifiType2();
		
		//易达金配发的标准卡简项公安不做校验  王伟涛 20200827
		if(custInfo.getMatchingCardFlag().equals("1")&&custInfo.getYdjFlag().equals("1")){	
			if(custInfo.getC1c2Flag().equals("1")){
				//2.主附同申  主卡中国人 (证件类型01 02) 附卡外国人(证件类型04 07) 
				if((certifiType.equals("01")||certifiType.equals("02"))&&(certifiType2.equals("04")||certifiType2.equals("07"))){
					if(!"01".equals(wgrStatusFsk)&&(!wlm.equals("A106")&&!wlm2.equals("A106")&&!wlm3.equals("A106"))){
						msg = msg+"【主卡或附卡公安异常,需下违例码后可通过】";
						warnStr="1";
					}
				}
				//3.主附同申  主卡外国人(证件类型04 07)   附卡外国人(证件类型04 07)
				if((certifiType.equals("04")||certifiType.equals("07"))&&(certifiType2.equals("04")||certifiType2.equals("07"))){
					if((!"01".equals(wgrStatus)||!"01".equals(wgrStatusFsk))&&(!wlm.equals("A106")&&!wlm2.equals("A106")&&!wlm3.equals("A106"))){
						msg = msg+"【主卡或附卡公安异常,需下违例码后可通过】";
						warnStr="1";
					}
				}
				//4.主附同申  主卡外国人 (证件类型04 07) 附卡中国人(证件类型01 02)
				if((certifiType.equals("04")||certifiType.equals("07"))&&(certifiType2.equals("01")||certifiType2.equals("02"))){
					if(!"01".equals(wgrStatus)&&(!wlm.equals("A106")&&!wlm2.equals("A106")&&!wlm3.equals("A106"))){
						msg = msg+"【主卡或附卡公安异常,需下违例码后可通过】";
						warnStr="1";
					}
				}
			}
			if(custInfo.getC1c2Flag().equals("3")){
				//6.单办主卡 外国人
				if(!"01".equals(wgrStatus)&&(certifiType.equals("04")||certifiType.equals("07"))&&(!wlm.equals("A106")&&!wlm2.equals("A106")&&!wlm3.equals("A106"))){
					msg = msg+"【主卡公安异常,需下违例码后可通过】";
					warnStr="1";
				}
			}		
		}else{
			
			if(custInfo.getC1c2Flag().equals("1")){
				//1.主附同申  主卡中国人(证件类型01 02)  附卡中国人(证件类型01 02) 
				if((certifiType.equals("01")||certifiType.equals("02"))&&(certifiType2.equals("01")||certifiType2.equals("02"))){
					if((!"完全匹配".equals(policeStatus)&&(!wlm.equals("A106")&&!wlm2.equals("A106")&&!wlm3.equals("A106")))||(!"完全匹配".equals(policeStatusFsk)&&(!wlm.equals("A106")&&!wlm2.equals("A106")&&!wlm3.equals("A106")))){
						msg = msg+"【主卡或附卡公安异常,需下违例码后可通过】";
						warnStr="1";
					}
				}
				//2.主附同申  主卡中国人 (证件类型01 02) 附卡外国人(证件类型04 07) 
				if((certifiType.equals("01")||certifiType.equals("02"))&&(certifiType2.equals("04")||certifiType2.equals("07"))){
					if((!"完全匹配".equals(policeStatus)&&(!wlm.equals("A106")&&!wlm2.equals("A106")&&!wlm3.equals("A106")))||(!"01".equals(wgrStatusFsk)&&(!wlm.equals("A106")&&!wlm2.equals("A106")&&!wlm3.equals("A106")))){
						msg = msg+"【主卡或附卡公安异常,需下违例码后可通过】";
						warnStr="1";
					}
				}
				//3.主附同申  主卡外国人(证件类型04 07)   附卡外国人(证件类型04 07)
				if((certifiType.equals("04")||certifiType.equals("07"))&&(certifiType2.equals("04")||certifiType2.equals("07"))){
					if((!"01".equals(wgrStatus)&&(!wlm.equals("A106")&&!wlm2.equals("A106")&&!wlm3.equals("A106")))||(!"01".equals(wgrStatusFsk)&&(!wlm.equals("A106")&&!wlm2.equals("A106")&&!wlm3.equals("A106")))){
						msg = msg+"【主卡或附卡公安异常,需下违例码后可通过】";
						warnStr="1";
					}
				}
				//4.主附同申  主卡外国人 (证件类型04 07) 附卡中国人(证件类型01 02)
				if((certifiType.equals("04")||certifiType.equals("07"))&&(certifiType2.equals("01")||certifiType2.equals("02"))){
					if((!"01".equals(wgrStatus)&&(!wlm.equals("A106")&&!wlm2.equals("A106")&&!wlm3.equals("A106")))||(!"完全匹配".equals(policeStatusFsk)&&(!wlm.equals("A106")&&!wlm2.equals("A106")&&!wlm3.equals("A106")))){
						msg = msg+"【主卡或附卡公安异常,需下违例码后可通过】";
						warnStr="1";
					}
				}
			}
			if(custInfo.getC1c2Flag().equals("3")){
				//5.单办主卡 中国人
				if(!"完全匹配".equals(policeStatus)&&(certifiType.equals("01")||certifiType.equals("02"))&&(!wlm.equals("A106")&&!wlm2.equals("A106")&&!wlm3.equals("A106"))){
					msg = msg+"【主卡公安异常,需下违例码后可通过】";
					warnStr="1";
				}
				//6.单办主卡 外国人
				if(!"01".equals(wgrStatus)&&(certifiType.equals("04")||certifiType.equals("07"))&&(!wlm.equals("A106")&&!wlm2.equals("A106")&&!wlm3.equals("A106"))){
					msg = msg+"【主卡公安异常,需下违例码后可通过】";
					warnStr="1";
				}
			}		
		}
		
		/**
		 * 新增可信校验 马超群  20200825
		 * 适用范围  只校验标准卡主卡
		 * 校验逻辑   
		 * 简项公安 完全匹配 可信 一致  为一致
		 * 简项公安 空            可信 一致   为一致
		 * 简项公安 完全匹配 可信 查询未成功(非0非5) 为一致
		 * 简项公安 完全匹配 可信 未发起查询  为一致
		 * 提示信息   主卡公安异常,需下违例码后可通过 
		 * 
		 */
		if(msg.indexOf("公安异常")==-1){
			if(!(custInfo.getMatchingCardFlag().equals("1")&&custInfo.getYdjFlag().equals("1"))){	
				String authResult = custInfo.getAuthResult()==null?"":custInfo.getAuthResult();
				if(custInfo.getC1c2Flag().equals("3")&&(certifiType.equals("01")||certifiType.equals("02"))){
					if(!("完全匹配".equals(policeStatus) && !"".equals(authResult)  && "0".equals(authResult.substring(0,1)))
							&&!("".equals(policeStatus) && !"".equals(authResult) &&"0".equals(authResult.substring(0,1)))
							&&!("完全匹配".equals(policeStatus) && !"".equals(authResult) &&!"0".equals(authResult.substring(0,1))&&!"5".equals(authResult.substring(0,1)))
							&&!("完全匹配".equals(policeStatus)&&"".equals(authResult))
							&&(!wlm.equals("A106")&&!wlm2.equals("A106")&&!wlm3.equals("A106"))){
						
						msg = msg+"【主卡公安异常,需下违例码后可通过】";
						warnStr="1";
					}
					
				}	
			}
			
		}
		/**
		 * 20200401
		 * PAD人像比对 审批提交校验
		 * 授信审批环节（及易达金初审），当人像比对结果为“不一致”且审批以批准提交时，弹框提示：人像比对结果异常，是否提交通过。点击“确认”申请件正常流转，点击“取消”留在当前页。
		 */
		
		String padRxbd = sysDecisionYdjService.selectPoliceXPInfo(appId);
		if("完全不一致".equals(padRxbd)){
			msg = msg+"【人像比对结果异常,是否提交】";
		}
		
		if("1".equals(warnStr)){
			map.put("yes", "0");
			map.put("msg", msg);
			return map;
		}
		if(!"".equals(msg)){
			map.put("yes", "1");
			map.put("msg", msg);
			return map;
		}
		//7 系统根据申请人身份证件号码判断申请人实际年龄，年龄判断标准需具体到日，
		// 对于年龄低于23、女性年龄高于54、男性年龄高于59的年龄
		//map = isNianlinZhenCe(appId,msg);
		return map;
	}
	public Map<String, String> bzkSpResultPdAndFk(String appId, String flag, String gjm, Map<String, String> reqMap,Map<String, String> reqMap_fk)
			throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		String msg = "";
		String applyCard = reqMap.get("applyCard");
		String approveCreditProduct = reqMap.get("approveCreditProduct");
		String isWuWangDian = reqMap.get("isWuWangDian")==null?"":reqMap.get("isWuWangDian");// 无网点
		String wlm = reqMap.get("wlm")==null?"":reqMap.get("wlm");// 违例码
		String wlm2 = reqMap.get("wlm2")==null?"":reqMap.get("wlm2");// 违例码
		String wlm3 = reqMap.get("wlm3")==null?"":reqMap.get("wlm3");// 违例码
		//String bcFlag=reqMap.get("bcFlag")==null?"":reqMap.get("bcFlag");// 征信批量标识
		//String ydjFlag =  sysApprovalCommonService.selectYdjFlagByAppId(appId);
		BigDecimal approveCreditLimit = new BigDecimal(
				reqMap.get("approveCreditLimit") == "" ? "0" : reqMap.get("approveCreditLimit"))
						.multiply(new BigDecimal(1000));// 授信额度
		FicoSdBlaze bzk = (FicoSdBlaze) sysApprovalCommonService.querySystemApprovalResult(appId, flag);
		if (bzk == null) {
			bzk = new FicoSdBlaze();
		}
		/*
		 *当最终审批卡种与申请授信产品不一致，提交界面时需提示“授信产品不一致”。
		 */
		// 获得决策授信产品CP_RST
		/*if (applyCard != null && approveCreditProduct!=null && !approveCreditProduct.equals(applyCard)) {
			msg = "授信产品不一致";
			map.put("msg", msg);
			map.put("yes", "1");
			return map;
		}*/
		/*
		 * 默认反写blaze输出额度，审批人员可根据实际审批情况修改授信额度；
		 * 当最终审批额度与系统建议额度差异大于X%（X参数可配）150%，提交界面时需提示“授信额度不一致，请提交违例码”。
		 */
		/*BigDecimal PROPOSED_LMT = bzk.getProposedLmt();// 系统建议额度
		if (approveCreditLimit.compareTo(PROPOSED_LMT.multiply(new BigDecimal(1.5))) == 1 && "".equals(wlm)) {
			msg = "授信额度不一致，请提交违例码";
			map.put("msg", msg);
			map.put("yes", "0");
			return map;
		}*/
		// 1最终授信产品低于申请产品级别，且最终授信额度高于申请产品下限时，弹框提示内容
		// 获得授信产品的产品详情
		CardProduct applyProduct = sysApprovalCommonService.queryCardByCardCode(applyCard);// 申请卡
		String applyJb = null;
		if(applyProduct!=null){
			applyJb = applyProduct.getCardType();// 申请卡级别
		}
		CardProduct card = null;
		if (approveCreditProduct != null) {
			card = sysApprovalCommonService.queryCardByCardCode(approveCreditProduct);// 审批卡
		}
		
		// 2对征信判定拒绝审批核发通过的申请件弹框禁止通过
		if (gjm!=null && !"".equals(gjm) && (gjm.indexOf("900")>-1 || gjm.indexOf("901")>-1)) {
			msg = "【征信结论为拒绝】";
			map.put("msg", msg);
			map.put("yes", "0");
			return map;
		}
		// 3授信额度低于或高于产品上下限弹框禁止通过OPAS_PARAM_CARD_PRODUCT
		if ((card != null && approveCreditLimit.compareTo(new BigDecimal(card.getLineLow())) == -1)||
				(card != null && approveCreditLimit.compareTo(new BigDecimal(card.getLineHight())) == 1)) {
			msg = "【授信额度高于或低于产品上下限】";
			map.put("msg", msg);
			map.put("yes", "0");
			return map;
		}
		// 3.1副卡--授信额度低于或高于产品上下限弹框禁止通过OPAS_PARAM_CARD_PRODUCT
		CardProduct card_fk = null;
		String applyJb_fk=null;
		String approveCreditLimit_fk = reqMap_fk.get("approveCreditLimit");
		if (reqMap_fk.get("approveCreditProduct") != null &&  approveCreditLimit_fk!= null) {
			card_fk = sysApprovalCommonService.queryCardByCardCode(reqMap_fk.get("approveCreditProduct"));// 审批卡
			applyJb_fk=card_fk.getCardType();
		}
		if ((card_fk != null && new BigDecimal(approveCreditLimit_fk).multiply(new BigDecimal(1000)).compareTo(new BigDecimal(card_fk.getLineLow())) == -1)||
				(card_fk != null && new BigDecimal(approveCreditLimit_fk).multiply(new BigDecimal(1000)).compareTo(new BigDecimal(card_fk.getLineHight())) == 1)) {
			msg = "【套卡--授信额度高于或低于产品上下限】";
			map.put("msg", msg);
			map.put("yes", "0");
			return map;
		}
		// 4策规定的需要下违例码但未下的弹窗提示未下违例码---WARNINGLIST里的无网点
		if ("1".equals(isWuWangDian) && ("".equals(wlm)||"".equals(reqMap_fk.get("wlm")))) {
			msg = "【无网点或未匹配到网申合作商户,未下违例码】";
			map.put("msg", msg);
			map.put("yes", "0");
			return map;
		}
		if(("建议拒绝".equals(bzk.getDecisionResult())||"直接拒绝".equals(bzk.getDecisionResult()))&& "".equals(wlm)){
			msg = "【系统决策结果为拒绝，未下违例码限制通过】";
			map.put("msg", msg);
			map.put("yes", "0");
			return map;
		}
		//4.2主卡申请人年龄>=66周岁或<18周岁，且审批人工通过，未下违例码时
		OpasCustBaseInfo custInfo = sysDecisionYdjService.selectCustAndPreSellimitByAppId(appId);
		int age = custInfo.getAge()==null?0:custInfo.getAge();
		if((age>=66||age<18)&&"".equals(wlm)){
			//map.put("msg", "【主卡申请人年龄"+age+",其年龄不在18<=age<66周岁之间,未下违例码限制其通过】");
			map.put("msg", "【年龄不符政策标准,未下违例码】");
			map.put("yes", "0");//“0”限制通过
			return map;
		}else if(age==0){
			logger.error("该主卡申请人客户年龄信息缺失，请检查数据信息");
		}
		//4.3附属卡申请人年龄>=71周岁或<14周岁，且审批人工通过，未下违例码时 
		//当前卡为主副同审并且没有拒绝附属卡
		if("1".equals(reqMap.get("fsk"))&&!"1".equals(reqMap.get("refuseChildcard"))){
			BizInpApplC2 bizInpApplC2 = applyService.findBiz2info(appId);
			if(bizInpApplC2!=null){
				Date birth = bizInpApplC2.getC2Birth();
				if(birth!=null){
					int fsAge = (new Date()).getYear() - birth.getYear();
					if((fsAge>=71||fsAge<14)&&"".equals(wlm)){
						msg = "【附属卡年龄不符政策标准,未下违例码】";
						map.put("msg", msg);
						map.put("yes", "0");
						return map;
					}
				}
			}
		}
		//4.5   wdb 已持卡账户状态异常的定义：账户状态除U、Q以外，非空状态的
		int closeCode1=custInfo.getCloseCode1();
		if(closeCode1>0&& ("".equals(wlm)||"".equals(reqMap_fk.get("wlm")))){
			msg = "【已持卡账户状态异常，未下违例码】";
			map.put("msg", msg);
			map.put("yes", "0");
			return map;
		}
		//新增校验 卡版面和年费类型是否正确  wdb		
		String yearFee= reqMap.get("yearFeeDerateType")==null?"":reqMap.get("yearFeeDerateType");
		if(card!=null&&card.getYearFeeDerateType()!=null&&(!"".equals(card.getYearFeeDerateType()))){
			List<String> yearFeeList=Arrays.asList(card.getYearFeeDerateType().split(","));
			if(!yearFeeList.contains(yearFee)){//不包含该年费类型 提示不能提交
				msg = msg+"【主卡年费类型与授信卡产品不匹配,限制通过】";
				map.put("msg", msg);
				map.put("yes", "0");
				return map;
			}	
		}
		String cardFace=reqMap.get("cardFaceCode")==null?"":reqMap.get("cardFaceCode");
		if(card!=null&&card.getProductFace()!=null&&(!"".equals(card.getProductFace()))){
			List<String> produceFaceList=Arrays.asList(card.getProductFace().split(","));
			if(!produceFaceList.contains(cardFace)){//不包含该卡版面  提示不能提交  
				msg = msg+"【主卡卡版面与授信卡产品不匹配,限制通过】";
				map.put("msg", msg);
				map.put("yes", "0");
				return map;
			}
		}
		String yearFee_fk= reqMap_fk.get("yearFeeDerateType")==null?"":reqMap_fk.get("yearFeeDerateType");
		if(card_fk!=null&&card_fk.getYearFeeDerateType()!=null&&(!"".equals(card_fk.getYearFeeDerateType()))){
			List<String> yearFeeList_fk=Arrays.asList(card_fk.getYearFeeDerateType().split(","));
			if(!yearFeeList_fk.contains(yearFee_fk)){//不包含该年费类型 提示不能提交
				msg = msg+"【附属卡年费类型与授信卡产品不匹配,限制通过】";
				map.put("msg", msg);
				map.put("yes", "0");
				return map;
			}	
		}
		String cardFace_fk=reqMap_fk.get("cardFaceCode")==null?"":reqMap_fk.get("cardFaceCode");
		if(card_fk!=null&&card_fk.getProductFace()!=null&&(!"".equals(card_fk.getProductFace()))){
			List<String> produceFaceList_fk=Arrays.asList(card_fk.getProductFace().split(","));
			if(!produceFaceList_fk.contains(cardFace_fk)){//不包含该卡版面  提示不能提交  
				msg = msg+"【附属卡卡版面与授信卡产品不匹配,限制通过】";
				map.put("msg", msg);
				map.put("yes", "0");
				return map;
			}
		}
		//}
		//6 ID有效期
		map = isIDYouXiao(reqMap,msg);
		if((map!=null&&map.keySet()!=null&&map.keySet().size()>0)){
			if("0".equals(map.get("yes"))){
				return map;
			}else{
				msg = msg+map.get("msg");
			}
		}
		//4.4授信额度低于系统建议额度50%的，且审批人工通过，未下违例码时
		/*if(bzk!=null&&bzk.getProposedLmt()!=null
				&&approveCreditLimit.compareTo(bzk.getProposedLmt().multiply(new BigDecimal(0.5)))==-1&&"".equals(wlm)){
			msg =  msg+ "【未下违例码限制通过】";
		}*/
		if(bzk!=null&&bzk.getProposedLmt()!=null
				&&approveCreditLimit.compareTo(bzk.getProposedLmt().multiply(new BigDecimal(0.5)))==-1
				&&!("A207".equals(wlm)||"A207".equals(wlm2)||"A207".equals(wlm3))){
			msg =  msg+ "【未下A207限制通过】";
		}
		String wlm_fk = reqMap_fk.get("wlm")==null?"":reqMap_fk.get("wlm");// 违例码
		String wlm2_fk = reqMap_fk.get("wlm2")==null?"":reqMap_fk.get("wlm2");// 违例码
		String wlm3_fk = reqMap_fk.get("wlm3")==null?"":reqMap_fk.get("wlm3");// 违例码
		if(bzk!=null&&bzk.getProposedLmt()!=null
				&&new BigDecimal(approveCreditLimit_fk).multiply(new BigDecimal(1000)).compareTo(bzk.getProposedLmt().multiply(new BigDecimal(0.5)))==-1
				&&!("A207".equals(wlm_fk)||"A207".equals(wlm2_fk)||"A207".equals(wlm3_fk))){
			msg =  msg+ "【套卡--未下A207限制通过】";
		}
		// 5挂起申请件不得提交通过
		//if(ydjFlag=="2"||"2".equals(ydjFlag)){
		//人行查询次数
		//20200925 审批分层项目注释
		/*int org1mSum3 = pbocService.queryMonthApplyCount(appId);
		if(org1mSum3>=5){
			msg = msg+"【请关注人行一个月内申请查询大于等于5次,是否提交】";
		}*/
		//逾期超过500元
//		int maxDue = pbocService.queryNowOrOverdueCount(appId);
		//202000925 审批分层项目注释
		/*int maxDue = pbocService.queryCurOverdueMax(appId);
		if(maxDue>=500){
			msg = msg+"【请关注贷款/贷记卡历史或当期逾期,是否提交】";
		}*/
		//申请人是否多次申请同意卡种 wdb:排除0115、0116两种卡种的重复申请
		//String approval = sysApprovalCommonService.selectAppByAppId(appId);
	/*	String apply = sysApprovalCommonService.selectApplyByAppId(appId);
		if(apply != null&& !"".equals(apply)){
			msg = msg+"【近期已批准相同卡产品，是否强制提交】";
		}*/
		Map<String, Object> paramMap = new HashMap<String,Object>();
		paramMap.put("appId", appId);
		paramMap.put("product", reqMap.get("approveCreditProduct"));
		//主附同申卡片数量限制 20200827
		if(custInfo.getC1c2Flag().equals("1")){
			if(!"0115".equals(approveCreditProduct)&&!"0116".equals(approveCreditProduct)){
				List<String> applyList=sysApprovalCommonService.selectApplyCardByAppId(appId);
				if(applyList != null&& applyList.size()>0){
					if(applyList.size()>1){
						msg = msg+"【近期已批准相同卡产品，请确认该卡种是否可重复申请】";
						logger.info(appId+"近期已批准相同卡产品的申请件编号"+applyList.toString()+"集合长度"+applyList.size());
					}else if(applyList.size()==1){
						if(!applyList.contains(appId)&&!applyList.contains(null)){//目前只有该申请件  不报错误信息同时排除list里的值为空
							msg = msg+"【近期已批准相同卡产品，请确认该卡种是否可重复申请】";
							logger.info(appId+"近期已批准相同卡产品的申请件编号"+applyList.get(0));
						}
					}
				}
				//如果申请人已经持有卡且卡片状态正常,需要提示
				int num = sysApprovalCommonService.findHaveCard(paramMap);
				if(num > 0){
					if(msg.indexOf("近期已批准相同卡产品，请确认该卡种是否可重复申请")==-1){
						msg = msg+"【近期已批准相同卡产品，请确认该卡种是否可重复申请】";
					}
				}
			}
			
		}
		//芝麻信用积分
		/*String ivs = antService.selectIvsScoreByAppId(appId);
		int ivsScore = Integer.parseInt(ivs);
		if(ivsScore<40){
			msg = msg+"【IVS分低于40分】";
		}*/
		
		// String spJb = card.getCardType();//申请卡级别
		/*if (applyJb!=null&&card != null && Integer.parseInt(card.getCardType()) < Integer.parseInt(applyJb)) {
			if (approveCreditLimit.compareTo(new BigDecimal(applyProduct.getLineLow())) > -1) {
				msg = msg+ "【异常降级,是否提交】";
			}
		}*/
		//wdb 新增校验  刚性扣减额度与审批额度校验   2019.8.5新增违例码校验2019.9.6 去掉违例码校验，将上海地区修改为全国提示2019.9.9对易达金配发卡不校验2019.10.28新增排除已持卡
		BigDecimal dlLmt=bzk.getDlLmt()==null?new BigDecimal(0):bzk.getDlLmt();
		if("2".equals(custInfo.getYdjFlag())&&dlLmt.compareTo(approveCreditLimit)==-1&&"0".equals(custInfo.getIsHavecardCust())){
			msg = msg+"【授信额度高于标准DL】";
			map.put("msg", msg);
			map.put("yes", "0");
			return map;
		}
		
		/**
		 * 新增存量客户校验 胡宝川   马超群 20200825
		 * 适用范围  只校验标准卡   单办主卡 主附同申
		 * 校验逻辑如下 (只删除标卡主卡)
		 * 删除系统现针对12张卡上限提交校验
		 * 删除系统同卡产品重复申请提交校验
		 * 删除系统ami卡9张上限提交校验
		 * ami审批拒绝提交张数限制保留
		 * 校验1
		 * 流转至人工环节的存量客户（按现有审核系统是否为存量客户判断,存量卡标识 0:否 1：是 2：异常），
		 * 将“人工最终授信额度”与“标卡账户额度、刚性扣减额度”进行比对，
		 * 需结合特定违例码（AXXX）可提交通过
		 * 存量客户 是 A207+A207 人工最终授信额度大于标卡账户额度且大于刚扣额度时
		 * 存量客户 排查  A901+A207  人工最终授信额度大于刚扣额度时
		 * 存量客户 否  同现有逻辑
		 * 提示信息    额度不符要求，请审慎评估
		 * 校验2
		 * 卡片重复申请为“是”时 
		 * 且未下任何违例码时(新加) 20200916  马超群 
		 * 提示信息  重复申请
		 * 
		 */
		String warnStr="0";
		//易达金配发的标准卡存量不做校验  王伟涛 20200907
		if(!(custInfo.getMatchingCardFlag().equals("1")&&custInfo.getYdjFlag().equals("1"))){
			//存量客户标识
			String  isHavecardCust = custInfo.getIsHavecardCust();
			//标卡账户额度
			String creditLimit = custInfo.getCreditLimit()==null?"0":custInfo.getCreditLimit();
			//刚扣额度
			FicoSdBlaze sysResultInfo = (FicoSdBlaze)sysDecisionYdjService.selectSystemDecisionByAppId(appId, "2");
			BigDecimal DlLmt = sysResultInfo.getDlLmt()==null?new BigDecimal(0):sysResultInfo.getDlLmt();
			if(isHavecardCust.equals("1")&&approveCreditLimit.compareTo(new BigDecimal(creditLimit))==1
					&&approveCreditLimit.compareTo(DlLmt)==1&&(!wlm.equals("A207")||!wlm2.equals("A207"))){
				msg = msg+"【额度不符要求，请审慎评估】";
				warnStr="1";
			}
			if(isHavecardCust.equals("2")&&approveCreditLimit.compareTo(DlLmt)==1&&(!wlm.equals("A901")||!wlm2.equals("A207"))){
				msg = msg+"【额度不符要求，请审慎评估】";
				warnStr="1";
			}
		}
		
		if("3".equals(custInfo.getC1c2Flag())&&null!=custInfo.getRepetitionVerdict()&&"1".equals(custInfo.getRepetitionVerdict())&&wlm.equals("")&&wlm2.equals("")&&wlm3.equals("")){//标卡 单办主卡
			msg = msg+"【重复申请】";
			warnStr="1";
		}
		//wdb 自进件提示  
		// 20200925 审批分层注释
		/*String c4Apsour=custInfo.getC4Apsour()==null?"":custInfo.getC4Apsour();
		if("7".equals(c4Apsour)){
			msg = msg+"【请关注自进件，是否提交】";
		}*/
		//wdb 持非境内居民身份证申请的客户 不是居民身份证
		String c1Idtype=custInfo.getCertifiType()==null?"":custInfo.getCertifiType();
		if(!"01".equals(c1Idtype)){
			msg = msg+"【请关注外籍客户，是否审核通过】";
		}
		//wdb 0072、0071 主卡年龄不达23周岁
		if(("0071".equals(approveCreditProduct)||"0072".equals(approveCreditProduct))&&age<23){
			msg = msg+"【年龄不达23周岁,是否确认提交】";
		}
		if(("4".equals(card.getCardType())||"5".equals(card.getCardType()))&&age<23&&"".equals(wlm)){
			msg = msg+"【年龄不符卡种政策标准，未下违例码】";
			map.put("msg", msg);
			map.put("yes", "0");
			return map;
		}
		if(("4".equals(applyJb_fk)||"5".equals(applyJb_fk))&&age<23&&"".equals(reqMap_fk.get("wlm"))){
			msg = msg+"【套卡年龄不符卡种政策标准，未下违例码】";
			map.put("msg", msg);
			map.put("yes", "0");
			return map;
		}
		//wdb 0115、0116两种卡判定审批系统中该卡产品编号下已批准的卡片数量
		//主附同申卡片数量限制 20200827
		if(custInfo.getC1c2Flag().equals("1")){
			if("0115".equals(approveCreditProduct)||"0116".equals(approveCreditProduct)){
				int num2=sysApprovalCommonService.selectCountHaveCard(paramMap);
				if(num2>8){
					msg = msg+"【已有"+num2+"张卡，限制审批通过】";
					map.put("msg", msg);
					map.put("yes", "0");
					return map;
				}else if(num2>0&&num2<9){
					msg = msg+"【申请人已持有该卡"+num2+"张，是否继续核批】";
				}
			}
		}
		//wdb 境内居民、非境内居民税前收入校验
		String ydjFlag=custInfo.getYdjFlag()==null?"":custInfo.getYdjFlag();
		String c1Nation=custInfo.getC1Nation()==null?"":custInfo.getC1Nation();
		BigDecimal c1Earn=custInfo.getC1Earn()==null?new BigDecimal(0):custInfo.getC1Earn();
		if(("5".equals(c1Nation) || "1".equals(c1Nation))&&"2".equals(ydjFlag)&&c1Earn.compareTo(BigDecimal.valueOf(3.6))==-1){
			msg = msg+"【收入不符政策标准，是否提交】";
		}else if(!("5".equals(c1Nation) || "1".equals(c1Nation))&&"2".equals(ydjFlag)&&c1Earn.compareTo(BigDecimal.valueOf(6))==-1){
			msg = msg+"【收入不符政策标准，是否提交】";
		}
		//wdb 客群级别校验
		if("2".equals(ydjFlag)&&("4".equals(card.getCardType())||"5".equals(card.getCardType()))&&
				(reqMap.get("policyCode1").indexOf("B")>-1||reqMap.get("policyCode1").indexOf("C")>-1)){
			msg = msg+"【所属客群分类与申请卡产品对应关系异常，是否确认提交】";
		}else if("2".equals(ydjFlag)&&"3".equals(card.getCardType())&&reqMap.get("policyCode1").indexOf("C")>-1){
			msg = msg+"【所属客群分类与申请卡产品对应关系异常，是否确认提交】";
		}
		if("2".equals(ydjFlag)&&("4".equals(applyJb_fk)||"5".equals(applyJb_fk))&&
				(reqMap_fk.get("policyCode1").indexOf("B")>-1||reqMap_fk.get("policyCode1").indexOf("C")>-1)){
			msg = msg+"【所属客群分类与申请卡产品对应关系异常，是否确认提交】";
		}else if("2".equals(ydjFlag)&&"3".equals(applyJb_fk)&&reqMap_fk.get("policyCode1").indexOf("C")>-1){
			msg = msg+"【所属客群分类与申请卡产品对应关系异常，是否确认提交】";
		}
		//wdb 客户已持卡（3118表不包含T销卡卡片）大于等于12张 限制通过 2019.10.24新增Q卡也不包含
		int num3=sysApprovalCommonService.selectCardLimit(paramMap);
		if(num3>11){
			msg = msg+"【超申请卡片申请数量限制】";
			warnStr="1";
		}
		//2019.8.5 职务为空校验
	/*	String c1Title=custInfo.getC1Title()==null?"":custInfo.getC1Title();
		if("1".equals(bcFlag)&&"".equals(c1Title)){
			msg = msg+"【职务不能为空】";
			warnStr="1";
		}*/
//		if(!"完全匹配".equals(policeStatus)&&!"库中无此号".equals(policeStatus)&&"".equals(wlm)){
//			msg = msg+"【公安异常,需下违例码后可通过】";
//			warnStr="1";
//		}
		//20200709 新增违例码
				
		//外国人核查状态  
		//新增外国人主附同申与单办主卡公安判断  并与简项公安整合 20200807
		String policeStatus=custInfo.getPoliceStatus()==null?"":custInfo.getPoliceStatus();
		String wgrStatus=custInfo.getWgrStatus()==null?"":custInfo.getWgrStatus();
		String policeStatusFsk=custInfo.getPoliceStatusFsk()==null?"":custInfo.getPoliceStatusFsk();
		String wgrStatusFsk=custInfo.getWgrStatusFsk()==null?"":custInfo.getWgrStatusFsk();
		String certifiType = custInfo.getCertifiType()==null?"":custInfo.getCertifiType();
		String certifiType2 = custInfo.getCertifiType2()==null?"":custInfo.getCertifiType2();
		
		if(custInfo.getC1c2Flag().equals("1")){
			//1.主附同申  主卡中国人(证件类型01 02)  附卡中国人(证件类型01 02) 
			if((certifiType.equals("01")||certifiType.equals("02"))&&(certifiType2.equals("01")||certifiType2.equals("02"))){
				if((!"完全匹配".equals(policeStatus)&&(!wlm.equals("A106")&&!wlm2.equals("A106")&&!wlm3.equals("A106")))||(!"完全匹配".equals(policeStatusFsk)&&(!wlm.equals("A106")&&!wlm2.equals("A106")&&!wlm3.equals("A106")))){
					msg = msg+"【主卡或附卡公安异常,需下违例码后可通过】";
					warnStr="1";
				}
			}
			//2.主附同申  主卡中国人 (证件类型01 02) 附卡外国人(证件类型04 07) 
			if((certifiType.equals("01")||certifiType.equals("02"))&&(certifiType2.equals("04")||certifiType2.equals("07"))){
				if((!"完全匹配".equals(policeStatus)&&(!wlm.equals("A106")&&!wlm2.equals("A106")&&!wlm3.equals("A106")))||(!"01".equals(wgrStatusFsk)&&(!wlm.equals("A106")&&!wlm2.equals("A106")&&!wlm3.equals("A106")))){
					msg = msg+"【主卡或附卡公安异常,需下违例码后可通过】";
					warnStr="1";
				}
			}
			//3.主附同申  主卡外国人(证件类型04 07)   附卡外国人(证件类型04 07)
			if((certifiType.equals("04")||certifiType.equals("07"))&&(certifiType2.equals("04")||certifiType2.equals("07"))){
				if((!"01".equals(wgrStatus)&&(!wlm.equals("A106")&&!wlm2.equals("A106")&&!wlm3.equals("A106")))||(!"01".equals(wgrStatusFsk)&&(!wlm.equals("A106")&&!wlm2.equals("A106")&&!wlm3.equals("A106")))){
					msg = msg+"【主卡或附卡公安异常,需下违例码后可通过】";
					warnStr="1";
				}
			}
			//4.主附同申  主卡外国人 (证件类型04 07) 附卡中国人(证件类型01 02)
			if((certifiType.equals("04")||certifiType.equals("07"))&&(certifiType2.equals("01")||certifiType2.equals("02"))){
				if((!"01".equals(wgrStatus)&&(!wlm.equals("A106")&&!wlm2.equals("A106")&&!wlm3.equals("A106")))||(!"完全匹配".equals(policeStatusFsk)&&(!wlm.equals("A106")&&!wlm2.equals("A106")&&!wlm3.equals("A106")))){
					msg = msg+"【主卡或附卡公安异常,需下违例码后可通过】";
					warnStr="1";
				}
			}
		}
		if(custInfo.getC1c2Flag().equals("3")){
			//5.单办主卡 中国人
			if(!"完全匹配".equals(policeStatus)&&(certifiType.equals("01")||certifiType.equals("02"))&&(!wlm.equals("A106")&&!wlm2.equals("A106")&&!wlm3.equals("A106"))){
				msg = msg+"【主卡公安异常,需下违例码后可通过】";
				warnStr="1";
			}
			//6.单办主卡 外国人
			if(!"01".equals(wgrStatus)&&(certifiType.equals("04")||certifiType.equals("07"))&&(!wlm.equals("A106")&&!wlm2.equals("A106")&&!wlm3.equals("A106"))){
				msg = msg+"【主卡公安异常,需下违例码后可通过】";
				warnStr="1";
			}
		}
		
		/**
		 * 新增可信校验 马超群  20200825
		 * 适用范围  只校验标准卡主卡
		 * 校验逻辑   
		 * 简项公安 完全匹配 可信 一致  为一致
		 * 简项公安 空            可信 一致   为一致
		 * 简项公安 完全匹配 可信 查询未成功(非0非5) 为一致
		 * 简项公安 完全匹配 可信 未发起查询  为一致
		 * 提示信息   主卡公安异常,需下违例码后可通过 
		 * 
		 */
		if(msg.indexOf("公安异常")==-1){
			String authResult = custInfo.getAuthResult()==null?"":custInfo.getAuthResult();
			if(custInfo.getC1c2Flag().equals("3")&&(certifiType.equals("01")||certifiType.equals("02"))){
				if(!("完全匹配".equals(policeStatus) && !"".equals(authResult)  && "0".equals(authResult.substring(0,1)))
						&&!("".equals(policeStatus) && !"".equals(authResult) &&"0".equals(authResult.substring(0,1)))
						&&!("完全匹配".equals(policeStatus) && !"".equals(authResult) &&!"0".equals(authResult.substring(0,1))&&!"5".equals(authResult.substring(0,1)))
						&&!("完全匹配".equals(policeStatus)&&("".equals(authResult)))
						&&(!wlm.equals("A106")&&!wlm2.equals("A106")&&!wlm3.equals("A106"))){
					
					msg = msg+"【主卡公安异常,需下违例码后可通过】";
					warnStr="1";
				}
				
			}
		}
		/**
		 * 20200401
		 * PAD人像比对 审批提交校验
		 * 授信审批环节（及易达金初审），当人像比对结果为“不一致”且审批以批准提交时，弹框提示：人像比对结果异常，是否提交通过。点击“确认”申请件正常流转，点击“取消”留在当前页。
		 */
		
		String padRxbd = sysDecisionYdjService.selectPoliceXPInfo(appId);
		if("完全不一致".equals(padRxbd)){
			msg = msg+"【人像比对结果异常,是否提交】";
		}
		
		if("1".equals(warnStr)){
			map.put("yes", "0");
			map.put("msg", msg);
			return map;
		}
		if(!"".equals(msg)){
			map.put("yes", "1");
			map.put("msg", msg);
			return map;
		}
		//7 系统根据申请人身份证件号码判断申请人实际年龄，年龄判断标准需具体到日，
		// 对于年龄低于23、女性年龄高于54、男性年龄高于59的年龄
		//map = isNianlinZhenCe(appId,msg);
		return map;
	}
	/**
	 * 系统根据申请人身份证件号码判断申请人实际年龄，年龄判断标准需具体到日，
	 * 对于年龄低于23、女性年龄高于54、男性年龄高于59的年龄
	 * @throws ParseException 
	 */
	public Map<String,String> isNianlinZhenCe(String appId,String msg,OpasCustBaseInfo custInfo) throws ParseException{
		Map<String,String> map = new HashMap();
		
		if(custInfo!=null){
			int age = custInfo.getAge();
			if(age<23){//低于23
				msg = msg+"【申请表信息的年龄不符政策标准】";
				map.put("msg", msg);
				map.put("yes", "1");
				return map;
			}else if("F".equals(custInfo.getSex())&&age>54){//女性年龄高于54
				msg = msg+"【申请表信息的年龄不符政策标准】";
				map.put("msg", msg);
				map.put("yes", "1");
				return map;
			}else if("M".equals(custInfo.getSex())&&age>59){//男性年龄高于59的年龄
				msg = msg+"【申请表信息的年龄不符政策标准】";
				map.put("msg", msg);
				map.put("yes", "1");
				return map;
			}
		}
		return map;
	}
	/**
	 * ID有效期
	 * @param reqMap
	 * @param msg
	 * @throws CoreException
	 */
	public Map<String,String> isIDYouXiao(Map<String,String> reqMap,String msg) throws CoreException{
		Map<String,String> map = new HashMap();
		//若是转上级或退回审批，则不提示该校验
		if("2".equals(reqMap.get("caozuoFlag"))){
			return null;
		}
		//ApRole role = sysApprovalCommonService.queryRoleCodeByUserId(reqMap.get("userId"));
		//若审批结论为拒绝，不弹框提示关于ID有效期的问题
		if("1".equals(reqMap.get("approveResult"))){
			if(reqMap.get("certifinoVailud")==null||"".equals(reqMap.get("certifinoVailud"))
					||"空".equals(reqMap.get("certifinoVailud")) || "已过期".equals(reqMap.get("certifinoVailud"))){
				msg = "【ID有效期系统检查结果异常，审核不可通过】";
				map.put("msg", msg);
				map.put("yes", "0");
				return map;
			}else if("关注".equals(reqMap.get("certifinoVailud"))){
				msg ="【ID有效期系统检查结果为“关注”,是否提交】";
				map.put("msg", msg);
				map.put("yes", "1");//点击“是”可以强制通过
				return map;
			}
		}
		return map;
	}
	
	/**
	 * xuzhiguo
	 * @param ctx
	 * @throws Exception
	 * 易达金点击调整额度按钮
	 */
	public void queryManualLimitAfterResult(Context ctx) throws Exception {
		try {
			String requestType = "YDJ1006";// 易达金执行策略类型
			String appId = StringUtils.trimToEmpty((String) ctx.getDataMap().get("appId"));
			Map<String, String> reqMap = (Map<String, String>) ctx.getDataMap().get("reqMap");
			// 调用接口，获取返回值
			logger.info("=======调用ficoJsonRequest接口开始=======");
			Map<String, String> resMap = ficoService.ficoMapRequest(reqMap, requestType, appId);
			String stauts = "";
			if (resMap == null) {
				ctx.setData("isSusscess", "false");
				throw new Exception("queryManualLimitAfterResult()调用接口返回值为空");
			} else {
				stauts = resMap.get("statusCode");
				if ("2".equals(stauts)) {// 1成功，2失败
					ctx.setData("isSusscess", "false");
					throw new Exception("queryManualLimitAfterResult()调用接口失败，请检测详情");
				} else {
					// 返回前台结果
					ctx.setData("isSusscess", "true");
					ctx.setData("REF_UN_PLGE_DTI", resMap.get("REF_UN_PLGE_DTI"));
					ctx.setData("REF_CP_DTI", resMap.get("REF_CP_DTI"));
					ctx.setData("REF_MUE", resMap.get("REF_MUE"));
					ctx.setData("DEBT_APP_AUTH", resMap.get("DEBT_APP_AUTH"));
					logger.info("==========调用接口成功==========");
				}
			}
		} catch (Exception e) {
//			logger.info("queryManualLimitAfterResult()发生系统错误，请检测");
//			e.printStackTrace();
			logger.error(StringUtils.trimToEmpty((String) ctx.getDataMap().get("appId")), e);
		}
	}
}
