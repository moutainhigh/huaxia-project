package com.huaxia.opas.domain.decision;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class OpasCustBaseInfo implements Serializable {

	private static final long serialVersionUID = -1402274489664982848L;

	private String companyMindan;
	private String importSchoolMindan;
	private String goodCompanyMindan;
	private String businessTuanbanMindan;
	private String tsProjectMindan;
	private String stockcustTelsale;// 存量客户电销名单
	private String prodName;
	private String preSellimit;
	private String c1c2Flag;
	private String matchingCardFlag;//套卡标识 20200827 yuanquan
	private String c4Apbatch;
	private String appAcctyp;
	private String c4VerCode;
	private String c5Jctype;
	private String wgrStatus;//外国人核查结果
	private String wgrStatusFsk;//外国人核查结果
	private String policeCode;
	private String cardStat_ydj;//易达金账户状态
	private String cardStat_bzk;//标准卡账户状态
	private String repetitionVerdict;//卡片是否重复申请，0:否   1:是   9:接口异常
	private String creditLimit;//标卡账户额度
	private String c4Relmod;//推荐方式(反欺诈高风险新增 20201019)
	
	
	public String getPoliceCode() {
		return policeCode;
	}

	public void setPoliceCode(String policeCode) {
		this.policeCode = policeCode;
	}

	public String getC5Jctype() {
		return c5Jctype;
	}

	public void setC5Jctype(String c5Jctype) {
		this.c5Jctype = c5Jctype;
	}

	// 20191112 授信审批页面反显预审流水号用
	private String epayMatch; // 配发卡标示
	private String mainCardAppId; //ETC迅卡配发卡对应主卡流水号
	private String isMatchQrcode;//网申二维码标识
	private String authResult;//可信公安主卡结果 0一致 5 不一致   数据为空三种结果
	
	// 20201015 单位名称和补充
	private String c1Coname;//单名
	private String c4Fconm2;//补充
	
	public String getC1Coname() {
		return c1Coname;
	}

	public void setC1Coname(String c1Coname) {
		this.c1Coname = c1Coname;
	}

	public String getC4Fconm2() {
		return c4Fconm2;
	}

	public void setC4Fconm2(String c4Fconm2) {
		this.c4Fconm2 = c4Fconm2;
	}

	public String getAuthResult() {
		return authResult;
	}

	public void setAuthResult(String authResult) {
		this.authResult = authResult;
	}

	public String getIsMatchQrcode() {
		return isMatchQrcode;
	}

	public void setIsMatchQrcode(String isMatchQrcode) {
		this.isMatchQrcode = isMatchQrcode;
	}

	public String getMainCardAppId() {
		return mainCardAppId;
	}

	public void setMainCardAppId(String mainCardAppId) {
		this.mainCardAppId = mainCardAppId;
	}

	public String getEpayMatch() {
		return epayMatch;
	}

	public void setEpayMatch(String epayMatch) {
		this.epayMatch = epayMatch;
	}

	public String getAppAcctyp() {
		return appAcctyp;
	}

	public void setAppAcctyp(String appAcctyp) {
		this.appAcctyp = appAcctyp;
	}

	public String getC4VerCode() {
		return c4VerCode;
	}

	public void setC4VerCode(String c4VerCode) {
		this.c4VerCode = c4VerCode;
	}

	public String getC4Apbatch() {
		return c4Apbatch;
	}

	public void setC4Apbatch(String c4Apbatch) {
		this.c4Apbatch = c4Apbatch;
	}

	public String getC1c2Flag() {
		return c1c2Flag;
	}

	public void setC1c2Flag(String c1c2Flag) {
		this.c1c2Flag = c1c2Flag;
	}
	
	public String getMatchingCardFlag() {
		return matchingCardFlag;
	}

	public void setMatchingCardFlag(String matchingCardFlag) {
		this.matchingCardFlag = matchingCardFlag;
	}

	public String getPreSellimit() {
		return preSellimit;
	}

	public void setPreSellimit(String preSellimit) {
		this.preSellimit = preSellimit;
	}

	public String getProdName() {
		return prodName;
	}

	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

	public String getStockcustTelsale() {
		return stockcustTelsale;
	}

	public void setStockcustTelsale(String stockcustTelsale) {
		this.stockcustTelsale = stockcustTelsale;
	}

	public String getCompanyMindan() {
		return companyMindan;
	}

	public void setCompanyMindan(String companyMindan) {
		this.companyMindan = companyMindan;
	}

	public String getImportSchoolMindan() {
		return importSchoolMindan;
	}

	public void setImportSchoolMindan(String importSchoolMindan) {
		this.importSchoolMindan = importSchoolMindan;
	}

	public String getGoodCompanyMindan() {
		return goodCompanyMindan;
	}

	public void setGoodCompanyMindan(String goodCompanyMindan) {
		this.goodCompanyMindan = goodCompanyMindan;
	}

	public String getBusinessTuanbanMindan() {
		return businessTuanbanMindan;
	}

	public void setBusinessTuanbanMindan(String businessTuanbanMindan) {
		this.businessTuanbanMindan = businessTuanbanMindan;
	}

	public String getTsProjectMindan() {
		return tsProjectMindan;
	}

	public void setTsProjectMindan(String tsProjectMindan) {
		this.tsProjectMindan = tsProjectMindan;
	}

	private String autoId;

	private String appId;

	private String custName;

	private String sex;

	private String nationaity;

	private Short age;
	/**
	 * 证件类型
	 */
	private String certifiType;
	//新增附卡证件类型 20200807
	private String certifiType2;
	
	/**
	 * 证件号码
	 */
	private String certifiNo;

	private String workCompany;

	private String officeTel;

	private String mobileNo;

	private String applyCard;
	/**
	 * 公安状态 0:完全匹配 1:库中无此号 2:身份证号匹配 3:姓名不匹配
	 */
	private String policeStatus;
	/**
	 * 20200328  yuanquan  新增单办附属卡公安状态
	 * @return
	 */
	private String policeStatusFsk;
	/**
	 * 团办号是否匹配 0:否1:是
	 */
	private String teamnoIsmatch;
	/**
	 * 是否我行客户 0:否1:是
	 */
	private String isOneselfCust;
	/**
	 * 是否已持卡客户 0:否1:是
	 */
	private String isHavecardCust;
	/**
	 * 是否符合我行入组标准 0:否1:是
	 */
	private String isHavecardStandard;
	/**
	 * 是否符合预筛选客户入组标准 0:否1:是
	 */
	private String isPrefiltercustStandard;
	@JSONField(format = "yyyy/MM/dd HH:mm:ss")
	private Date crtTime;
	@JSONField(format = "yyyy/MM/dd HH:mm:ss")
	private Date crtDate;
	@JSONField(format = "yyyy/MM/dd HH:mm:ss")
	private Date crtTimeC1;
	private String crtUser;
	/**
	 * C4_GTOC 主卡进件信息表（OPAS_BIZ_INP_APPL_C1） 是否同意降级
	 * 
	 * @return
	 */
	private String C4_gtoc;
	/**
	 * C4_RUSHFLG 主卡进件信息表（OPAS_BIZ_INP_APPL_C1） 快速发卡标示
	 * 
	 * @return
	 */
	private String C4_rushflg;
	/**
	 * RESULT_CODE 手机实名认证结果信息表（TRD_HAR_LD00001）结果
	 * 
	 * @return
	 */
	private String phoneCertification;
	/**
	 * wsFlag 网申商户匹配（ccard_app_ditch_info,OPAS_NET_COOPERATIVE）结果
	 */
	private String wsFlag;
	/**
	 * C4_APSOUR 主卡进件信息表（OPAS_BIZ_INP_APPL_C1）推广进件来源
	 * 自进件
	 * @return
	 */
	private String c4Apsour;
	/**
	 * CLOSE_CODE1 银联3118表（opas_interface_3118）账号状态
	 */
	private int closeCode1;
	private String c1Nation;
	private BigDecimal c1Earn;
	private String cardType;
	private String ydjFlag;
	private int ysComments;//预审情况
	private String c1Title;//2019.8.5 职务
	
	public String getAutoId() {
		return autoId;
	}

	public void setAutoId(String autoId) {
		this.autoId = autoId == null ? null : autoId.trim();
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId == null ? null : appId.trim();
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName == null ? null : custName.trim();
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex == null ? null : sex.trim();
	}

	public String getNationaity() {
		return nationaity;
	}

	public void setNationaity(String nationaity) {
		this.nationaity = nationaity == null ? null : nationaity.trim();
	}

	public Short getAge() {
		return age;
	}

	public void setAge(Short age) {
		this.age = age;
	}

	public String getCertifiType() {
		return certifiType;
	}

	public void setCertifiType(String certifiType) {
		this.certifiType = certifiType == null ? null : certifiType.trim();
	}

	public String getCertifiType2() {
		return certifiType2;
	}

	public void setCertifiType2(String certifiType2) {
		this.certifiType2 = certifiType2;
	}

	public String getCertifiNo() {
		return certifiNo;
	}

	public void setCertifiNo(String certifiNo) {
		this.certifiNo = certifiNo == null ? null : certifiNo.trim();
	}

	public String getWorkCompany() {
		return workCompany;
	}

	public void setWorkCompany(String workCompany) {
		this.workCompany = workCompany == null ? null : workCompany.trim();
	}

	public String getOfficeTel() {
		return officeTel;
	}

	public void setOfficeTel(String officeTel) {
		this.officeTel = officeTel == null ? null : officeTel.trim();
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo == null ? null : mobileNo.trim();
	}

	public String getApplyCard() {
		return applyCard;
	}

	public void setApplyCard(String applyCard) {
		this.applyCard = applyCard == null ? null : applyCard.trim();
	}

	public String getPoliceStatus() {
		return policeStatus;
	}

	public void setPoliceStatus(String policeStatus) {
		this.policeStatus = policeStatus == null ? null : policeStatus.trim();
	}
	
	

	public String getPoliceStatusFsk() {
		return policeStatusFsk;
	}

	public void setPoliceStatusFsk(String policeStatusFsk) {
		this.policeStatusFsk = policeStatusFsk;
	}

	public String getTeamnoIsmatch() {
		return teamnoIsmatch;
	}

	public void setTeamnoIsmatch(String teamnoIsmatch) {
		this.teamnoIsmatch = teamnoIsmatch == null ? null : teamnoIsmatch.trim();
	}

	public String getIsOneselfCust() {
		return isOneselfCust;
	}

	public void setIsOneselfCust(String isOneselfCust) {
		this.isOneselfCust = isOneselfCust == null ? null : isOneselfCust.trim();
	}

	public String getIsHavecardCust() {
		return isHavecardCust;
	}

	public void setIsHavecardCust(String isHavecardCust) {
		this.isHavecardCust = isHavecardCust == null ? null : isHavecardCust.trim();
	}

	public String getIsHavecardStandard() {
		return isHavecardStandard;
	}

	public void setIsHavecardStandard(String isHavecardStandard) {
		this.isHavecardStandard = isHavecardStandard == null ? null : isHavecardStandard.trim();
	}

	public String getIsPrefiltercustStandard() {
		return isPrefiltercustStandard;
	}

	public void setIsPrefiltercustStandard(String isPrefiltercustStandard) {
		this.isPrefiltercustStandard = isPrefiltercustStandard == null ? null : isPrefiltercustStandard.trim();
	}

	public Date getCrtTime() {
		return crtTime;
	}

	public void setCrtTime(Date crtTime) {
		this.crtTime = crtTime;
	}

	public Date getCrtDate() {
		return crtDate;
	}

	public void setCrtDate(Date crtDate) {
		this.crtDate = crtDate;
	}

	public String getCrtUser() {
		return crtUser;
	}

	public void setCrtUser(String crtUser) {
		this.crtUser = crtUser == null ? null : crtUser.trim();
	}

	public String getC4_gtoc() {
		return C4_gtoc;
	}

	public void setC4_gtoc(String c4_gtoc) {
		C4_gtoc = c4_gtoc;
	}

	public String getC4_rushflg() {
		return C4_rushflg;
	}

	public void setC4_rushflg(String c4_rushflg) {
		C4_rushflg = c4_rushflg;
	}

	public String getPhoneCertification() {
		return phoneCertification;
	}

	public void setPhoneCertification(String phoneCertification) {
		this.phoneCertification = phoneCertification;
	}

	public String getWsFlag() {
		return wsFlag;
	}

	public void setWsFlag(String wsFlag) {
		this.wsFlag = wsFlag;
	}
	
	
	public String getC4Apsour() {
		return c4Apsour;
	}

	public int getCloseCode1() {
		return closeCode1;
	}

	public void setCloseCode1(int closeCode1) {
		this.closeCode1 = closeCode1;
	}

	public void setC4Apsour(String c4Apsour) {
		this.c4Apsour = c4Apsour;
	}

	public String getC1Nation() {
		return c1Nation;
	}

	public void setC1Nation(String c1Nation) {
		this.c1Nation = c1Nation;
	}

	public BigDecimal getC1Earn() {
		return c1Earn;
	}

	public void setC1Earn(BigDecimal c1Earn) {
		this.c1Earn = c1Earn;
	}

	public String getCardType() {
		return cardType;
	}

	public String getYdjFlag() {
		return ydjFlag;
	}

	public void setYdjFlag(String ydjFlag) {
		this.ydjFlag = ydjFlag;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public int getYsComments() {
		return ysComments;
	}

	public void setYsComments(int ysComments) {
		this.ysComments = ysComments;
	}

	public Date getCrtTimeC1() {
		return crtTimeC1;
	}

	public void setCrtTimeC1(Date crtTimeC1) {
		this.crtTimeC1 = crtTimeC1;
	}
	
	public String getC1Title() {
		return c1Title;
	}

	public void setC1Title(String c1Title) {
		this.c1Title = c1Title;
	}

	public String getWgrStatus() {
		return wgrStatus;
	}

	public void setWgrStatus(String wgrStatus) {
		this.wgrStatus = wgrStatus;
	}

	public String getWgrStatusFsk() {
		return wgrStatusFsk;
	}

	public void setWgrStatusFsk(String wgrStatusFsk) {
		this.wgrStatusFsk = wgrStatusFsk;
	}

	public String getCardStat_ydj() {
		return cardStat_ydj;
	}

	public void setCardStat_ydj(String cardStat_ydj) {
		this.cardStat_ydj = cardStat_ydj;
	}

	public String getCardStat_bzk() {
		return cardStat_bzk;
	}

	public void setCardStat_bzk(String cardStat_bzk) {
		this.cardStat_bzk = cardStat_bzk;
	}

	public String getRepetitionVerdict() {
		return repetitionVerdict;
	}

	public void setRepetitionVerdict(String repetitionVerdict) {
		this.repetitionVerdict = repetitionVerdict;
	}

	public String getCreditLimit() {
		return creditLimit;
	}

	public void setCreditLimit(String creditLimit) {
		this.creditLimit = creditLimit;
	}

	public String getC4Relmod() {
		return c4Relmod;
	}

	public void setC4Relmod(String c4Relmod) {
		this.c4Relmod = c4Relmod;
	}
	
}