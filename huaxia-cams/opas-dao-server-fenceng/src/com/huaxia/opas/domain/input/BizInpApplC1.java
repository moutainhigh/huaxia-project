package com.huaxia.opas.domain.input;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 
 * @description: 进件信息主卡字段
 * @author 张志宽
 * @since 2017年2月25日
 * @version 1.0
 * @lastUpdateDate
 * @lastUpdateUser
 */

public class BizInpApplC1 implements Serializable {
	private static final long serialVersionUID = -3312127482904806565L;
	private String c1HmaddTotal;
	private String c1CoaddTotal;
	
	private String insideAppNo;

	private String appId;
	
	private String flag;
	
	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	private Integer appBank;

	private String appProd;

	private Integer appFlag;

	private Integer appFlowst;

	private Long appCode;

	private String appAcctyp;

	private Integer c1Brnch;

	private String c1Cardnbr;

	private String c1Idtype;

	private String c1Idnbr;

	private String c1Secnbr;

	private String c1Cname;

	private String c1Ename;

	private String c1Gender; //性别
	@JSONField(format="yyyy-MM-dd")
	private Date c1Birth;

	private String c1Nation;

	private String c1Marst;

	private String c1Educls;

	private String c1Title;

	private String c1Telvl;

	private String c1Hmare;

	private String c1Hmtel; //家庭电话

	private String c1Mobile;

	private String c1Email;

	private String c1Fax;

	private String c1Class;

	private String c1Hmadd1;

	private String c1Hmadd2;

	private String c1Hmadd3;

	private String c1Hmadd4;

	private String c1Hmpost;

	private String c1Hmst;

	private Integer c1Hmyr;

	private Long c1Hloan;

	private String c1Idadd1;

	private String c1Idadd2;

	private String c1Idadd3;

	private String c1Idadd4;

	private String c1Idpost;

	private String c1Estadd1;

	private String c1Estadd2;

	private String c1Estadd3;

	private String c1Estadd4;

	private String c1Estpost;

	private String c1Carbrnd;
	@JSONField(format="yyyy-MM-dd")
	private Date c1Cardate;

	private String c1Carnbr;

	private String c1Carst;

	private String c1Coadd1;//公司地址

	private String c1Coadd2;

	private String c1Coadd3;

	private String c1Coadd4;

	private String c1Copost;

	private String c1Coname; //单位名称

	private String c1Codept;

	private String c1Coduty;

	private String c1Cocode;

	private String c1Cobuscd;

	private String c1Cokind;

	private String c1Colevl;

	private String c1Cotel; //单位电话

	private String c1Coext;

	private Integer c1Coyr;

	private BigDecimal c1Earn;

	private String c1Fconame;

	private String c1Fcodept;

	private String c1Fcotitl;

	private String c1Fcotel;

	private Integer c1Fcoyr;

	private BigDecimal c1Foreann;

	private Integer c1Depend;

	private String c1Busiser;

	private String c1Promqu;

	private String c1Proman;

	private String c1Reid;

	private String c1Rename;

	private String c1Retelar;

	private String c1Retel;

	private String c1Reship;

	private String c1Remobil;

	private String c1Xid1;

	private String c1Xname1; //联系人姓名

	private String c1Xtelar1;

	private String c1Xtel1; //联系人 固定电话

	private String c1Xship1;

	private String c1Xcomp1;

	private String c1Xmobil1;//联系人 手机

	private String c1Xid2;

	private String c1Xname2;

	private String c1Xtelar2;

	private String c1Xtel2;

	private String c1Xship2;

	private String c1Xcomp2;

	private String c1Xmobil2;

	private String c1Spidtyp;

	private String c1Spidnbr;

	private String c1Spname;

	private String c1Spmobil;

	private String c1Spcname;

	private String c1Spcdept;

	private String c1Spcduty;

	private String c1Spctel;

	private String c1Spcext;

	private BigDecimal c1Spearn;

	private Long c2Obnkno;

	private String c2Relcomp;

	private String c2Signck;
	@JSONField(format="yyyy-MM-dd")
	private Date c2Iddt1;

	private String c2Isdp1;

	private String c2Natncd1;
	@JSONField(format="yyyy-MM-dd")
	private Date c2Iddt3;

	private String c2Isdp3;

	private String c2Natncd3;

	private String c2Revs2;

	private String c3Idtype;

	private String c3Idnbr;

	private String c3Cname;

	private String c3Ename;

	private String c3Gender;
	@JSONField(format="yyyy-MM-dd")
	private Date c3Birth;

	private String c3Nation;

	private String c3Marst;

	private String c3Educls;

	private String c3Hmare;

	private String c3Hmtel;

	private String c3Mobile;

	private String c3Resvd1;

	private String c3Resvd2;

	private String c3Resvd3;

	private String c3Resvd4;

	private String c3Resvd5;

	private String c3Resvd6;

	private String c3Email;

	private String c3Coname;

	private String c3Codept;

	private String c3Coduty;

	private String c3Cocode;

	private String c3Title;

	private String c3Cokind;

	private String c3Cotel;

	private String c3Coext;

	private Integer c3Coyr;

	private BigDecimal c3Earn;

	private String c3Acctnbr;

	private Long c3Guamt;

	private String c3Type;

	private String c3Gref;

	private String c3Grel;

	private String c4Caccnbr;

	private String c4Cpaymod;

	private String c4Faccnbr;

	private String c4Fpaymod;

	private String c4Othcard;

	private String c4Othtype;

	private Long c4Othcred;

	private Long c4Paycred;

	private Integer c4Ccredit;

	private Integer c4Fcredit;

	private String c4Source;

	private String c4Apbatch;

	private String c4Abacces;

	private String c4Abbrnch; //推广机构代码

	private String c4Abuser; //推广员编号

	private String c4Abname;

	private String c4Recname;

	private String c4Reccard;

	private String c4Recemp;

	private String c4Relship;

	private String c4Relmod;
	@JSONField(format="yyyy-MM-dd")
	private Integer c4Cycdate;

	private String c4Cycadd1;

	private String c4Cycadd2;

	private String c4Bname;

	private Integer c4Pinreq;

	private Integer c4Messreq;

	private Long c4Smsamt;

	private String c4SignFlag;

	private String c4Revs2;

	private String c4Stmcode;

	private Integer c4Gtoc;

	private String c4Downpd;

	private Integer c4Rushflg;

	private Integer c4Rushfee;

	private String c4Vcode;

	private Integer c4Vrcode;

	private Integer c4Vccred;

	private Integer c4Vfcred;

	private Integer c4Vpacred;

	private String c4Cdespmd;

	private Integer c4Cardcst;

	private Integer c4Cdesplc;

	private String c4Vercode;

	private String c4Lysrv;

	private String c4Lyfee;

	private String c4Exflag;

	private String c4Excode;

	private Integer c4Exrtdt;

	private String c4Exacct;

	private String c4Chflag;

	private String c4Buemb;

	private String c4Empno;

	private String c4Riskcd;

	private String c4Pin1;

	private String c4Srvcd1;

	private String c4Giftno;

	private String c4Psnid1;

	private String c4Sysflg;

	private String c4Giftn2;

	private String c4Othcd4;

	private String c4Othtyp4;

	private Integer c4Othexp4;

	private String c4Obnknm;

	private String c4Ra;

	private String c4Sc;

	private Integer c4Sn;

	private String c4Clyn1;

	private Long c4Othcrq;

	private Integer c4Mumpfl;

	private String c4Fconm2;

	private Integer c4Fcoyr2;

	private BigDecimal c4Fcoer2;

	private String c4Usprod;

	private String c4Usmeb;

	private String c4Usrel;

	private String c4Reladd;

	private String c4Relpost;

	private String c4Apbtid;

	private BigDecimal c4Hmamt;

	private Integer c4Hloanyr;

	private BigDecimal c4Caramt;

	private Long c4Carloan;

	private String c4Abphon;

	private String c4Abtype;

	private String c4Apsour;

	private Integer c4Caryr;

	private String c4Assid;

	private String c4Assidty;

	private String c4Assname;
	@JSONField(format="yyyy-MM-dd")
	private Date c4Assbirt;

	private String c4Assgend;

	private String c4Assmars;

	private Integer c4Asshare;

	private String c4Asshtel;

	private String c4Assbtel;

	private String c4Assbext;

	private String c4Asscell;

	private String c4Asscomp;

	private String c4Assdept;

	private String c4Cdsel;

	private String c4Cdselnt;

	private Integer c4Decemp;

	private Long c4Xcrlmt;

	private Integer c4Childyn;
	@JSONField(format="yyyy-MM-dd")
	private Date c4Fcodat;

	private String c5Sercd11;

	private String c5Sercd12;

	private String c5Sercd13;

	private String c5Sercd14;

	private String c5Sercd15;

	private String c5Sercd16;

	private String c5Sercd17;

	private String c5Sercd18;

	private String c5Sercd19;

	private BigDecimal c5Capcnt;

	private Integer c5Ptplan;

	private String c5Cucode1;

	private String c5Cucode2;

	private String c5Accode1;

	private String c5Accode2;

	private String c5Ovrmtyn;

	private String c5Colevl;

	private String c5Hmst;

	private Integer c5Hmyr;

	private String c5Schlnmc;

	private String c5Schlnme;

	private String c5Moarea;

	private String c5Emailin;

	private String c5Oversea;

	private String c5Adtype1;

	private String c5Adtype2;

	private String c5Adtype3;

	private String c5Adtype4;

	private String c5Bmwyn;

	private String c5Msgfree;

	private Integer c5Cptno;

	private Integer c5Intcode;

	private String c5Mngrno;
	@JSONField(format="yyyy-MM-dd")
	private Date c5Idte1;
	@JSONField(format="yyyy-MM-dd")
	private Date c5Idte3;

	private Integer c5Abteam; //团办编号

	private Integer c5Abarea;

	private String c5Carlics;

	private String c5Atm1;

	private String c5Tele1;

	private String c5Net1;

	private String c5Phone1;

	private Integer c5Cpno1;

	private Integer c5Nbrmth1;

	private String c5Comcust;

	private String c5Comrccd;

	private String c5Comname;

	private Long c5Mpllmt;

	private String c5Jctype;

	private String c5Mailco1;

	private String c5Guarnm2;

	private String c5Guar2;

	private String c5Empno;

	private String c5Pathway;

	private String c5Guidtp;

	private Long c5Cucred;

	private String c5Ncred;

	private Long c5Credlmt;

	private Integer c5Expydt;

	private Long c5Pinlim1;

	private String c5Usrel;

	private String c5Abtype;

	private Integer c5Othbnk;

	private String c5Sendnbr;

	private Long c5Templmt;
	@JSONField(format="yyyy-MM-dd")
	private Date c5Tlmtbeg;
	@JSONField(format="yyyy-MM-dd")
	private Date c5Tlmtend;

	private String c5PoiCod;

	private String c5WorkTy;

	private String c5ProCod;

	private String c5SinDua;

	private String c5AprCom;

	private String c5RelPar;

	private String c5OtherI;

	private String c5BusOwn;
	@JSONField(format="yyyy-MM-dd")
	private Date c5BusEst;

	private String c5BusReg;

	private String c5Cuseg1;

	private Integer c5Intbnk;

	private String c5TranRte;

	private String c5CosuRte;

	private String c5DepoRte;
	@JSONField(format="yyyy-MM-dd")
	private Date c5Sgndte1;
	@JSONField(format="yyyy-MM-dd")
	private Date c5Regdte1;
	@JSONField(format="yyyy-MM-dd")
	private Date c5Sgndte3;
	@JSONField(format="yyyy-MM-dd")
	private Date c5Regdte3;

	private String c5Curef1;

	private String c5Mastno1;

	private String c5Ntmsg1;

	private String c5Ntmsg2;

	private String c5Cardto1;

	private String c5Abcode;

	private String c5Pbcbrnc;

	private String c5Comnm2p;

	private String c5Comnm2v;

	private Integer c5Noreiss;

	private String c5Msmpnbr;

	private Long c5Msmpbal;

	private String c5Cdsel1;

	private String c5HdwrSn;

	private String c5Aid;

	private Integer c5Intflcd;

	private Long c5Deposit;

	private Integer c5Dailytp;

	private String c5OpeMod;

	private String c5OpeTpe;

	private String c5Cashrte;

	private Integer c5Beatype;

	private String c5Inland;

	private Integer c5Feegrp;

	private Integer c5CcYn1;

	private Integer c5EcYn1;

	private Long c5Frelim;

	private String c5LiveCy1;

	private Integer c5IntflCode;

	private String c5Detcard;

	private String c5QqNbr1;

	private String c5WeixinNbr1;

	private String c5IssMod;

	private String c5Cardcst2;

	private String c5Isdpif1;
	
	private String c5Isdpif2;

	private String c5Isdpif3;

	private String c5Cucode3;

	private Integer c6OutMth;

	private String thirdPartyYn1;

	private Integer paymtYn;

	private String c5Cucode4;
	@JSONField(format="yyyy-MM-dd")
	private Date c5LoanDte;

	private BigDecimal c5IntrRatew;

	private Integer c5RmbBill1;

	private String seriesId1;

	private Integer c5MplmtFlag;

	private String c5HdwrSn2;

	private String c5Aid2;

	private Integer c6RelFlag1;

	private Integer c6RelFlag3;

	private String c6ClassNbr1;

	private String c6ClassNbr3;

	private String c6WhiteRsn1;

	private String c6WhiteRsn3;
	@JSONField(format="yyyy-MM-dd")
	private Date c6MpExpdte;

	private String c6Ethnic1;
	
	private String c6Ethnic2;

	private String c6Occupation1;

	private Integer c6EnrollDate;

	private Integer c6GraduDate;

	private Integer c6Unqpass1;

	private BigDecimal c6MaxMpamt1;

	private BigDecimal c6MinMpamt1;

	private String c6Abreason;

	private String c6Absouarea;

	private String c6UnionCd;

	private String c6FinanceCd;

	private String c6LmtType1;

	private Integer c6SublmtNbr1;

	private Long c6Limit1;

	private Integer c6Attrbte1;

	private Integer c6AvailYn1;
	@JSONField(format="yyyy-MM-dd")
	private Date c6BeginDate1;
	@JSONField(format="yyyy-MM-dd")
	private Date c6EndDate1;

	private String c6LmtType2;

	private Integer c6SublmtNbr2;

	private Long c6Limit2;

	private Integer c6Attrbte2;

	private Integer c6AvailYn2;
	@JSONField(format="yyyy-MM-dd")
	private Date c6BeginDate2;
	@JSONField(format="yyyy-MM-dd")
	private Date c6EndDate2;

	private Integer c6VcnYn;

	private Integer c6VcnChl;
	@JSONField(format="yyyy-MM-dd")
	private Date c6ItcdEnddt;

	private String c5Reserv2;
	@JSONField(format="yyyy-MM-dd")
	private Date crtTime;
	@JSONField(format="yyyy-MM-dd")
	private Date crtDate;

	private String crtUser;
	@JSONField(format="yyyy-MM-dd")
	private Date lstUpdTime;
	@JSONField(format="yyyy-MM-dd")
	private Date lstUpdDate;

	private String lstUpdUser;

	private String batchOperateFlag;

	private String c1c2Flag;
	
	private String ydjFlag;

	private String inputChannel;
	
	private String epayMatch;
	
	private String czfqMatch;
	/**
	 * 增添附卡进件信息字段
	 */
	private String c2Cname; //附卡申请人
	
	private String c2Idnbr; //附卡申请人证件号码
	/**
	 * 增添补件提交信息表（标准卡）字段
	 */
	private String patchCode; //补件码
	/**
	 * 申请件生命周期表 字段
	 * @return
	 */
	private String operateResult; //状态
	/**
	 * 审批复核信息表 字段
	 * @return
	 */
	private String approveResult; //审批结果
	
	private String existCardFlag;//是否是二卡标识
	
	private String matchingCardFlag;//套卡标识
	
	private String creditRefuseReason;
	private String approveCreditLimit;
	private String approverName;
	private String prodName;
	private String c6Gatnbr1;
	private String quickCardFlag;
	private String c6OccuDes1;//主卡申请人职业描述     职业类别


	public String getC6OccuDes1() {
		return c6OccuDes1;
	}

	public void setC6OccuDes1(String c6OccuDes1) {
		this.c6OccuDes1 = c6OccuDes1;
	}

	public String getC6Gatnbr1() {
		return c6Gatnbr1;
	}

	public void setC6Gatnbr1(String c6Gatnbr1) {
		this.c6Gatnbr1 = c6Gatnbr1;
	}
	
	public String getQuickCardFlag() {
		return quickCardFlag;
	}

	public void setQuickCardFlag(String quickCardFlag) {
		this.quickCardFlag = quickCardFlag;
	}

	public String getProdName() {
		return prodName;
	}

	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

	public String getCreditRefuseReason() {
		return creditRefuseReason;
	}

	public void setCreditRefuseReason(String creditRefuseReason) {
		this.creditRefuseReason = creditRefuseReason;
	}

	public String getApproveCreditLimit() {
		return approveCreditLimit;
	}

	public void setApproveCreditLimit(String approveCreditLimit) {
		this.approveCreditLimit = approveCreditLimit;
	}

	public String getApproverName() {
		return approverName;
	}

	public void setApproverName(String approverName) {
		this.approverName = approverName;
	}

	public String getMatchingCardFlag() {
		return matchingCardFlag;
	}

	public void setMatchingCardFlag(String matchingCardFlag) {
		this.matchingCardFlag = matchingCardFlag;
	}

	public String getApproveResult() {
		return approveResult;
	}

	public void setApproveResult(String approveResult) {
		this.approveResult = approveResult;
	}

	public String getOperateResult() {
		return operateResult;
	}

	public void setOperateResult(String operateResult) {
		this.operateResult = operateResult;
	}

	public String getPatchCode() {
		return patchCode;
	}

	public void setPatchCode(String patchCode) {
		this.patchCode = patchCode;
	}

	public String getC2Cname() {
		return c2Cname;
	}
	
	public String getC2Idnbr() {
		return c2Idnbr;
	}

	public void setC2Idnbr(String c2Idnbr) {
		this.c2Idnbr = c2Idnbr;
	}

	public void setC2Cname(String c2Cname) {
		this.c2Cname = c2Cname;
	}
	
	
	
	
	
	
	
	
	
	public String getInsideAppNo() {
		return insideAppNo;
	}


	public void setInsideAppNo(String insideAppNo) {
		this.insideAppNo = insideAppNo;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public Integer getAppBank() {
		return appBank;
	}

	public void setAppBank(Integer appBank) {
		this.appBank = appBank;
	}

	

	public String getAppProd() {
		return appProd;
	}

	public void setAppProd(String appProd) {
		this.appProd = appProd;
	}

	public Integer getAppFlag() {
		return appFlag;
	}

	public void setAppFlag(Integer appFlag) {
		this.appFlag = appFlag;
	}

	public Integer getAppFlowst() {
		return appFlowst;
	}

	public void setAppFlowst(Integer appFlowst) {
		this.appFlowst = appFlowst;
	}

	public Long getAppCode() {
		return appCode;
	}

	public void setAppCode(Long appCode) {
		this.appCode = appCode;
	}

	public String getAppAcctyp() {
		return appAcctyp;
	}

	public void setAppAcctyp(String appAcctyp) {
		this.appAcctyp = appAcctyp;
	}

	public Integer getC1Brnch() {
		return c1Brnch;
	}

	public void setC1Brnch(Integer c1Brnch) {
		this.c1Brnch = c1Brnch;
	}

	public String getC1Cardnbr() {
		return c1Cardnbr;
	}

	public void setC1Cardnbr(String c1Cardnbr) {
		this.c1Cardnbr = c1Cardnbr;
	}

	public String getC1Idtype() {
		return c1Idtype;
	}

	public void setC1Idtype(String c1Idtype) {
		this.c1Idtype = c1Idtype;
	}

	public String getC1Idnbr() {
		return c1Idnbr;
	}

	public void setC1Idnbr(String c1Idnbr) {
		this.c1Idnbr = c1Idnbr;
	}

	public String getC1Secnbr() {
		return c1Secnbr;
	}

	public void setC1Secnbr(String c1Secnbr) {
		this.c1Secnbr = c1Secnbr;
	}

	public String getC1Cname() {
		return c1Cname;
	}

	public void setC1Cname(String c1Cname) {
		this.c1Cname = c1Cname;
	}

	public String getC1Ename() {
		return c1Ename;
	}

	public void setC1Ename(String c1Ename) {
		this.c1Ename = c1Ename;
	}

	public String getC1Gender() {
		return c1Gender;
	}

	public void setC1Gender(String c1Gender) {
		this.c1Gender = c1Gender;
	}

	public Date getC1Birth() {
		return c1Birth;
	}

	public void setC1Birth(Date c1Birth) {
		this.c1Birth = c1Birth;
	}

	public String getC1Nation() {
		return c1Nation;
	}

	public void setC1Nation(String c1Nation) {
		this.c1Nation = c1Nation;
	}

	public String getC1Marst() {
		return c1Marst;
	}

	public void setC1Marst(String c1Marst) {
		this.c1Marst = c1Marst;
	}

	public String getC1Educls() {
		return c1Educls;
	}

	public void setC1Educls(String c1Educls) {
		this.c1Educls = c1Educls;
	}

	public String getC1Title() {
		return c1Title;
	}

	public void setC1Title(String c1Title) {
		this.c1Title = c1Title;
	}

	public String getC1Telvl() {
		return c1Telvl;
	}

	public void setC1Telvl(String c1Telvl) {
		this.c1Telvl = c1Telvl;
	}

	public String getC1Hmare() {
		return c1Hmare;
	}

	public void setC1Hmare(String c1Hmare) {
		this.c1Hmare = c1Hmare;
	}

	public String getC1Hmtel() {
		return c1Hmtel;
	}

	public void setC1Hmtel(String c1Hmtel) {
		this.c1Hmtel = c1Hmtel;
	}

	public String getC1Mobile() {
		return c1Mobile;
	}

	public void setC1Mobile(String c1Mobile) {
		this.c1Mobile = c1Mobile;
	}

	public String getC1Email() {
		return c1Email;
	}

	public void setC1Email(String c1Email) {
		this.c1Email = c1Email;
	}

	public String getC1Fax() {
		return c1Fax;
	}

	public void setC1Fax(String c1Fax) {
		this.c1Fax = c1Fax;
	}

	public String getC1Class() {
		return c1Class;
	}

	public void setC1Class(String c1Class) {
		this.c1Class = c1Class;
	}

	public String getC1Hmadd1() {
		return c1Hmadd1;
	}

	public void setC1Hmadd1(String c1Hmadd1) {
		this.c1Hmadd1 = c1Hmadd1;
	}

	public String getC1Hmadd2() {
		return c1Hmadd2;
	}

	public void setC1Hmadd2(String c1Hmadd2) {
		this.c1Hmadd2 = c1Hmadd2;
	}

	public String getC1Hmadd3() {
		return c1Hmadd3;
	}

	public void setC1Hmadd3(String c1Hmadd3) {
		this.c1Hmadd3 = c1Hmadd3;
	}

	public String getC1Hmadd4() {
		return c1Hmadd4;
	}

	public void setC1Hmadd4(String c1Hmadd4) {
		this.c1Hmadd4 = c1Hmadd4;
	}

	public String getC1Hmpost() {
		return c1Hmpost;
	}

	public void setC1Hmpost(String c1Hmpost) {
		this.c1Hmpost = c1Hmpost;
	}

	public String getC1Hmst() {
		return c1Hmst;
	}

	public void setC1Hmst(String c1Hmst) {
		this.c1Hmst = c1Hmst;
	}

	public Integer getC1Hmyr() {
		return c1Hmyr;
	}

	public void setC1Hmyr(Integer c1Hmyr) {
		this.c1Hmyr = c1Hmyr;
	}

	public Long getC1Hloan() {
		return c1Hloan;
	}

	public void setC1Hloan(Long c1Hloan) {
		this.c1Hloan = c1Hloan;
	}

	public String getC1Idadd1() {
		return c1Idadd1;
	}

	public void setC1Idadd1(String c1Idadd1) {
		this.c1Idadd1 = c1Idadd1;
	}

	public String getC1Idadd2() {
		return c1Idadd2;
	}

	public void setC1Idadd2(String c1Idadd2) {
		this.c1Idadd2 = c1Idadd2;
	}

	public String getC1Idadd3() {
		return c1Idadd3;
	}

	public void setC1Idadd3(String c1Idadd3) {
		this.c1Idadd3 = c1Idadd3;
	}

	public String getC1Idadd4() {
		return c1Idadd4;
	}

	public void setC1Idadd4(String c1Idadd4) {
		this.c1Idadd4 = c1Idadd4;
	}

	public String getC1Idpost() {
		return c1Idpost;
	}

	public void setC1Idpost(String c1Idpost) {
		this.c1Idpost = c1Idpost;
	}

	public String getC1Estadd1() {
		return c1Estadd1;
	}

	public void setC1Estadd1(String c1Estadd1) {
		this.c1Estadd1 = c1Estadd1;
	}

	public String getC1Estadd2() {
		return c1Estadd2;
	}

	public void setC1Estadd2(String c1Estadd2) {
		this.c1Estadd2 = c1Estadd2;
	}

	public String getC1Estadd3() {
		return c1Estadd3;
	}

	public void setC1Estadd3(String c1Estadd3) {
		this.c1Estadd3 = c1Estadd3;
	}

	public String getC1Estadd4() {
		return c1Estadd4;
	}

	public void setC1Estadd4(String c1Estadd4) {
		this.c1Estadd4 = c1Estadd4;
	}

	public String getC1Estpost() {
		return c1Estpost;
	}

	public void setC1Estpost(String c1Estpost) {
		this.c1Estpost = c1Estpost;
	}

	public String getC1Carbrnd() {
		return c1Carbrnd;
	}

	public void setC1Carbrnd(String c1Carbrnd) {
		this.c1Carbrnd = c1Carbrnd;
	}

	public Date getC1Cardate() {
		return c1Cardate;
	}

	public void setC1Cardate(Date c1Cardate) {
		this.c1Cardate = c1Cardate;
	}

	public String getC1Carnbr() {
		return c1Carnbr;
	}

	public void setC1Carnbr(String c1Carnbr) {
		this.c1Carnbr = c1Carnbr;
	}

	public String getC1Carst() {
		return c1Carst;
	}

	public void setC1Carst(String c1Carst) {
		this.c1Carst = c1Carst;
	}

	public String getC1Coadd1() {
		return c1Coadd1;
	}

	public void setC1Coadd1(String c1Coadd1) {
		this.c1Coadd1 = c1Coadd1;
	}

	public String getC1Coadd2() {
		return c1Coadd2;
	}

	public void setC1Coadd2(String c1Coadd2) {
		this.c1Coadd2 = c1Coadd2;
	}

	public String getC1Coadd3() {
		return c1Coadd3;
	}

	public void setC1Coadd3(String c1Coadd3) {
		this.c1Coadd3 = c1Coadd3;
	}

	public String getC1Coadd4() {
		return c1Coadd4;
	}

	public void setC1Coadd4(String c1Coadd4) {
		this.c1Coadd4 = c1Coadd4;
	}

	public String getC1Copost() {
		return c1Copost;
	}

	public void setC1Copost(String c1Copost) {
		this.c1Copost = c1Copost;
	}

	public String getC1Coname() {
		return c1Coname;
	}

	public void setC1Coname(String c1Coname) {
		this.c1Coname = c1Coname;
	}

	public String getC1Codept() {
		return c1Codept;
	}

	public void setC1Codept(String c1Codept) {
		this.c1Codept = c1Codept;
	}

	public String getC1Coduty() {
		return c1Coduty;
	}

	public void setC1Coduty(String c1Coduty) {
		this.c1Coduty = c1Coduty;
	}

	public String getC1Cocode() {
		return c1Cocode;
	}

	public void setC1Cocode(String c1Cocode) {
		this.c1Cocode = c1Cocode;
	}

	public String getC1Cobuscd() {
		return c1Cobuscd;
	}

	public void setC1Cobuscd(String c1Cobuscd) {
		this.c1Cobuscd = c1Cobuscd;
	}

	public String getC1Cokind() {
		return c1Cokind;
	}

	public void setC1Cokind(String c1Cokind) {
		this.c1Cokind = c1Cokind;
	}

	public String getC1Colevl() {
		return c1Colevl;
	}

	public void setC1Colevl(String c1Colevl) {
		this.c1Colevl = c1Colevl;
	}

	public String getC1Cotel() {
		return c1Cotel;
	}

	public void setC1Cotel(String c1Cotel) {
		this.c1Cotel = c1Cotel;
	}

	public String getC1Coext() {
		return c1Coext;
	}

	public void setC1Coext(String c1Coext) {
		this.c1Coext = c1Coext;
	}

	public Integer getC1Coyr() {
		return c1Coyr;
	}

	public void setC1Coyr(Integer c1Coyr) {
		this.c1Coyr = c1Coyr;
	}

	public BigDecimal getC1Earn() {
		return c1Earn;
	}

	public void setC1Earn(BigDecimal c1Earn) {
		this.c1Earn = c1Earn;
	}

	public String getC1Fconame() {
		return c1Fconame;
	}

	public void setC1Fconame(String c1Fconame) {
		this.c1Fconame = c1Fconame;
	}

	public String getC1Fcodept() {
		return c1Fcodept;
	}

	public void setC1Fcodept(String c1Fcodept) {
		this.c1Fcodept = c1Fcodept;
	}

	public String getC1Fcotitl() {
		return c1Fcotitl;
	}

	public void setC1Fcotitl(String c1Fcotitl) {
		this.c1Fcotitl = c1Fcotitl;
	}

	public String getC1Fcotel() {
		return c1Fcotel;
	}

	public void setC1Fcotel(String c1Fcotel) {
		this.c1Fcotel = c1Fcotel;
	}

	public Integer getC1Fcoyr() {
		return c1Fcoyr;
	}

	public void setC1Fcoyr(Integer c1Fcoyr) {
		this.c1Fcoyr = c1Fcoyr;
	}

	public BigDecimal getC1Foreann() {
		return c1Foreann;
	}

	public void setC1Foreann(BigDecimal c1Foreann) {
		this.c1Foreann = c1Foreann;
	}

	public Integer getC1Depend() {
		return c1Depend;
	}

	public void setC1Depend(Integer c1Depend) {
		this.c1Depend = c1Depend;
	}

	public String getC1Busiser() {
		return c1Busiser;
	}

	public void setC1Busiser(String c1Busiser) {
		this.c1Busiser = c1Busiser;
	}

	public String getC1Promqu() {
		return c1Promqu;
	}

	public void setC1Promqu(String c1Promqu) {
		this.c1Promqu = c1Promqu;
	}

	public String getC1Proman() {
		return c1Proman;
	}

	public void setC1Proman(String c1Proman) {
		this.c1Proman = c1Proman;
	}

	public String getC1Reid() {
		return c1Reid;
	}

	public void setC1Reid(String c1Reid) {
		this.c1Reid = c1Reid;
	}

	public String getC1Rename() {
		return c1Rename;
	}

	public void setC1Rename(String c1Rename) {
		this.c1Rename = c1Rename;
	}

	public String getC1Retelar() {
		return c1Retelar;
	}

	public void setC1Retelar(String c1Retelar) {
		this.c1Retelar = c1Retelar;
	}

	public String getC1Retel() {
		return c1Retel;
	}

	public void setC1Retel(String c1Retel) {
		this.c1Retel = c1Retel;
	}

	public String getC1Reship() {
		return c1Reship;
	}

	public void setC1Reship(String c1Reship) {
		this.c1Reship = c1Reship;
	}

	public String getC1Remobil() {
		return c1Remobil;
	}

	public void setC1Remobil(String c1Remobil) {
		this.c1Remobil = c1Remobil;
	}

	public String getC1Xid1() {
		return c1Xid1;
	}

	public void setC1Xid1(String c1Xid1) {
		this.c1Xid1 = c1Xid1;
	}

	public String getC1Xname1() {
		return c1Xname1;
	}

	public void setC1Xname1(String c1Xname1) {
		this.c1Xname1 = c1Xname1;
	}

	public String getC1Xtelar1() {
		return c1Xtelar1;
	}

	public void setC1Xtelar1(String c1Xtelar1) {
		this.c1Xtelar1 = c1Xtelar1;
	}

	public String getC1Xtel1() {
		return c1Xtel1;
	}

	public void setC1Xtel1(String c1Xtel1) {
		this.c1Xtel1 = c1Xtel1;
	}

	public String getC1Xship1() {
		return c1Xship1;
	}

	public void setC1Xship1(String c1Xship1) {
		this.c1Xship1 = c1Xship1;
	}

	public String getC1Xcomp1() {
		return c1Xcomp1;
	}

	public void setC1Xcomp1(String c1Xcomp1) {
		this.c1Xcomp1 = c1Xcomp1;
	}

	public String getC1Xmobil1() {
		return c1Xmobil1;
	}

	public void setC1Xmobil1(String c1Xmobil1) {
		this.c1Xmobil1 = c1Xmobil1;
	}

	public String getC1Xid2() {
		return c1Xid2;
	}

	public void setC1Xid2(String c1Xid2) {
		this.c1Xid2 = c1Xid2;
	}

	public String getC1Xname2() {
		return c1Xname2;
	}

	public void setC1Xname2(String c1Xname2) {
		this.c1Xname2 = c1Xname2;
	}

	public String getC1Xtelar2() {
		return c1Xtelar2;
	}

	public void setC1Xtelar2(String c1Xtelar2) {
		this.c1Xtelar2 = c1Xtelar2;
	}

	public String getC1Xtel2() {
		return c1Xtel2;
	}

	public void setC1Xtel2(String c1Xtel2) {
		this.c1Xtel2 = c1Xtel2;
	}

	public String getC1Xship2() {
		return c1Xship2;
	}

	public void setC1Xship2(String c1Xship2) {
		this.c1Xship2 = c1Xship2;
	}

	public String getC1Xcomp2() {
		return c1Xcomp2;
	}

	public void setC1Xcomp2(String c1Xcomp2) {
		this.c1Xcomp2 = c1Xcomp2;
	}

	public String getC1Xmobil2() {
		return c1Xmobil2;
	}

	public void setC1Xmobil2(String c1Xmobil2) {
		this.c1Xmobil2 = c1Xmobil2;
	}

	public String getC1Spidtyp() {
		return c1Spidtyp;
	}

	public void setC1Spidtyp(String c1Spidtyp) {
		this.c1Spidtyp = c1Spidtyp;
	}

	public String getC1Spidnbr() {
		return c1Spidnbr;
	}

	public void setC1Spidnbr(String c1Spidnbr) {
		this.c1Spidnbr = c1Spidnbr;
	}

	public String getC1Spname() {
		return c1Spname;
	}

	public void setC1Spname(String c1Spname) {
		this.c1Spname = c1Spname;
	}

	public String getC1Spmobil() {
		return c1Spmobil;
	}

	public void setC1Spmobil(String c1Spmobil) {
		this.c1Spmobil = c1Spmobil;
	}

	public String getC1Spcname() {
		return c1Spcname;
	}

	public void setC1Spcname(String c1Spcname) {
		this.c1Spcname = c1Spcname;
	}

	public String getC1Spcdept() {
		return c1Spcdept;
	}

	public void setC1Spcdept(String c1Spcdept) {
		this.c1Spcdept = c1Spcdept;
	}

	public String getC1Spcduty() {
		return c1Spcduty;
	}

	public void setC1Spcduty(String c1Spcduty) {
		this.c1Spcduty = c1Spcduty;
	}

	public String getC1Spctel() {
		return c1Spctel;
	}

	public void setC1Spctel(String c1Spctel) {
		this.c1Spctel = c1Spctel;
	}

	public String getC1Spcext() {
		return c1Spcext;
	}

	public void setC1Spcext(String c1Spcext) {
		this.c1Spcext = c1Spcext;
	}

	public BigDecimal getC1Spearn() {
		return c1Spearn;
	}

	public void setC1Spearn(BigDecimal c1Spearn) {
		this.c1Spearn = c1Spearn;
	}

	public Long getC2Obnkno() {
		return c2Obnkno;
	}

	public void setC2Obnkno(Long c2Obnkno) {
		this.c2Obnkno = c2Obnkno;
	}

	public String getC2Relcomp() {
		return c2Relcomp;
	}

	public void setC2Relcomp(String c2Relcomp) {
		this.c2Relcomp = c2Relcomp;
	}

	public String getC2Signck() {
		return c2Signck;
	}

	public void setC2Signck(String c2Signck) {
		this.c2Signck = c2Signck;
	}

	public Date getC2Iddt1() {
		return c2Iddt1;
	}

	public void setC2Iddt1(Date c2Iddt1) {
		this.c2Iddt1 = c2Iddt1;
	}

	public String getC2Isdp1() {
		return c2Isdp1;
	}

	public void setC2Isdp1(String c2Isdp1) {
		this.c2Isdp1 = c2Isdp1;
	}

	public String getC2Natncd1() {
		return c2Natncd1;
	}

	public void setC2Natncd1(String c2Natncd1) {
		this.c2Natncd1 = c2Natncd1;
	}

	public Date getC2Iddt3() {
		return c2Iddt3;
	}

	public void setC2Iddt3(Date c2Iddt3) {
		this.c2Iddt3 = c2Iddt3;
	}

	public String getC2Isdp3() {
		return c2Isdp3;
	}

	public void setC2Isdp3(String c2Isdp3) {
		this.c2Isdp3 = c2Isdp3;
	}

	public String getC2Natncd3() {
		return c2Natncd3;
	}

	public void setC2Natncd3(String c2Natncd3) {
		this.c2Natncd3 = c2Natncd3;
	}

	public String getC2Revs2() {
		return c2Revs2;
	}

	public void setC2Revs2(String c2Revs2) {
		this.c2Revs2 = c2Revs2;
	}

	public String getC3Idtype() {
		return c3Idtype;
	}

	public void setC3Idtype(String c3Idtype) {
		this.c3Idtype = c3Idtype;
	}

	public String getC3Idnbr() {
		return c3Idnbr;
	}

	public void setC3Idnbr(String c3Idnbr) {
		this.c3Idnbr = c3Idnbr;
	}

	public String getC3Cname() {
		return c3Cname;
	}

	public void setC3Cname(String c3Cname) {
		this.c3Cname = c3Cname;
	}

	public String getC3Ename() {
		return c3Ename;
	}

	public void setC3Ename(String c3Ename) {
		this.c3Ename = c3Ename;
	}

	public String getC3Gender() {
		return c3Gender;
	}

	public void setC3Gender(String c3Gender) {
		this.c3Gender = c3Gender;
	}

	public Date getC3Birth() {
		return c3Birth;
	}

	public void setC3Birth(Date c3Birth) {
		this.c3Birth = c3Birth;
	}

	public String getC3Nation() {
		return c3Nation;
	}

	public void setC3Nation(String c3Nation) {
		this.c3Nation = c3Nation;
	}

	public String getC3Marst() {
		return c3Marst;
	}

	public void setC3Marst(String c3Marst) {
		this.c3Marst = c3Marst;
	}

	public String getC3Educls() {
		return c3Educls;
	}

	public void setC3Educls(String c3Educls) {
		this.c3Educls = c3Educls;
	}

	public String getC3Hmare() {
		return c3Hmare;
	}

	public void setC3Hmare(String c3Hmare) {
		this.c3Hmare = c3Hmare;
	}

	public String getC3Hmtel() {
		return c3Hmtel;
	}

	public void setC3Hmtel(String c3Hmtel) {
		this.c3Hmtel = c3Hmtel;
	}

	public String getC3Mobile() {
		return c3Mobile;
	}

	public void setC3Mobile(String c3Mobile) {
		this.c3Mobile = c3Mobile;
	}

	public String getC3Resvd1() {
		return c3Resvd1;
	}

	public void setC3Resvd1(String c3Resvd1) {
		this.c3Resvd1 = c3Resvd1;
	}

	public String getC3Resvd2() {
		return c3Resvd2;
	}

	public void setC3Resvd2(String c3Resvd2) {
		this.c3Resvd2 = c3Resvd2;
	}

	public String getC3Resvd3() {
		return c3Resvd3;
	}

	public void setC3Resvd3(String c3Resvd3) {
		this.c3Resvd3 = c3Resvd3;
	}

	public String getC3Resvd4() {
		return c3Resvd4;
	}

	public void setC3Resvd4(String c3Resvd4) {
		this.c3Resvd4 = c3Resvd4;
	}

	public String getC3Resvd5() {
		return c3Resvd5;
	}

	public void setC3Resvd5(String c3Resvd5) {
		this.c3Resvd5 = c3Resvd5;
	}

	public String getC3Resvd6() {
		return c3Resvd6;
	}

	public void setC3Resvd6(String c3Resvd6) {
		this.c3Resvd6 = c3Resvd6;
	}

	public String getC3Email() {
		return c3Email;
	}

	public void setC3Email(String c3Email) {
		this.c3Email = c3Email;
	}

	public String getC3Coname() {
		return c3Coname;
	}

	public void setC3Coname(String c3Coname) {
		this.c3Coname = c3Coname;
	}

	public String getC3Codept() {
		return c3Codept;
	}

	public void setC3Codept(String c3Codept) {
		this.c3Codept = c3Codept;
	}

	public String getC3Coduty() {
		return c3Coduty;
	}

	public void setC3Coduty(String c3Coduty) {
		this.c3Coduty = c3Coduty;
	}

	public String getC3Cocode() {
		return c3Cocode;
	}

	public void setC3Cocode(String c3Cocode) {
		this.c3Cocode = c3Cocode;
	}

	public String getC3Title() {
		return c3Title;
	}

	public void setC3Title(String c3Title) {
		this.c3Title = c3Title;
	}

	public String getC3Cokind() {
		return c3Cokind;
	}

	public void setC3Cokind(String c3Cokind) {
		this.c3Cokind = c3Cokind;
	}

	public String getC3Cotel() {
		return c3Cotel;
	}

	public void setC3Cotel(String c3Cotel) {
		this.c3Cotel = c3Cotel;
	}

	public String getC3Coext() {
		return c3Coext;
	}

	public void setC3Coext(String c3Coext) {
		this.c3Coext = c3Coext;
	}

	public Integer getC3Coyr() {
		return c3Coyr;
	}

	public void setC3Coyr(Integer c3Coyr) {
		this.c3Coyr = c3Coyr;
	}

	public BigDecimal getC3Earn() {
		return c3Earn;
	}

	public void setC3Earn(BigDecimal c3Earn) {
		this.c3Earn = c3Earn;
	}

	public String getC3Acctnbr() {
		return c3Acctnbr;
	}

	public void setC3Acctnbr(String c3Acctnbr) {
		this.c3Acctnbr = c3Acctnbr;
	}

	public Long getC3Guamt() {
		return c3Guamt;
	}

	public void setC3Guamt(Long c3Guamt) {
		this.c3Guamt = c3Guamt;
	}

	public String getC3Type() {
		return c3Type;
	}

	public void setC3Type(String c3Type) {
		this.c3Type = c3Type;
	}

	public String getC3Gref() {
		return c3Gref;
	}

	public void setC3Gref(String c3Gref) {
		this.c3Gref = c3Gref;
	}

	public String getC3Grel() {
		return c3Grel;
	}

	public void setC3Grel(String c3Grel) {
		this.c3Grel = c3Grel;
	}

	public String getC4Caccnbr() {
		return c4Caccnbr;
	}

	public void setC4Caccnbr(String c4Caccnbr) {
		this.c4Caccnbr = c4Caccnbr;
	}

	public String getC4Cpaymod() {
		return c4Cpaymod;
	}

	public void setC4Cpaymod(String c4Cpaymod) {
		this.c4Cpaymod = c4Cpaymod;
	}

	public String getC4Faccnbr() {
		return c4Faccnbr;
	}

	public void setC4Faccnbr(String c4Faccnbr) {
		this.c4Faccnbr = c4Faccnbr;
	}

	public String getC4Fpaymod() {
		return c4Fpaymod;
	}

	public void setC4Fpaymod(String c4Fpaymod) {
		this.c4Fpaymod = c4Fpaymod;
	}

	public String getC4Othcard() {
		return c4Othcard;
	}

	public void setC4Othcard(String c4Othcard) {
		this.c4Othcard = c4Othcard;
	}

	public String getC4Othtype() {
		return c4Othtype;
	}

	public void setC4Othtype(String c4Othtype) {
		this.c4Othtype = c4Othtype;
	}

	public Long getC4Othcred() {
		return c4Othcred;
	}

	public void setC4Othcred(Long c4Othcred) {
		this.c4Othcred = c4Othcred;
	}

	public Long getC4Paycred() {
		return c4Paycred;
	}

	public void setC4Paycred(Long c4Paycred) {
		this.c4Paycred = c4Paycred;
	}

	public Integer getC4Ccredit() {
		return c4Ccredit;
	}

	public void setC4Ccredit(Integer c4Ccredit) {
		this.c4Ccredit = c4Ccredit;
	}

	public Integer getC4Fcredit() {
		return c4Fcredit;
	}

	public void setC4Fcredit(Integer c4Fcredit) {
		this.c4Fcredit = c4Fcredit;
	}

	public String getC4Source() {
		return c4Source;
	}

	public void setC4Source(String c4Source) {
		this.c4Source = c4Source;
	}

	public String getC4Apbatch() {
		return c4Apbatch;
	}

	public void setC4Apbatch(String c4Apbatch) {
		this.c4Apbatch = c4Apbatch;
	}

	public String getC4Abacces() {
		return c4Abacces;
	}

	public void setC4Abacces(String c4Abacces) {
		this.c4Abacces = c4Abacces;
	}

	public String getC4Abbrnch() {
		return c4Abbrnch;
	}

	public void setC4Abbrnch(String c4Abbrnch) {
		this.c4Abbrnch = c4Abbrnch;
	}

	public String getC4Abuser() {
		return c4Abuser;
	}

	public void setC4Abuser(String c4Abuser) {
		this.c4Abuser = c4Abuser;
	}

	public String getC4Abname() {
		return c4Abname;
	}

	public void setC4Abname(String c4Abname) {
		this.c4Abname = c4Abname;
	}

	public String getC4Recname() {
		return c4Recname;
	}

	public void setC4Recname(String c4Recname) {
		this.c4Recname = c4Recname;
	}

	public String getC4Reccard() {
		return c4Reccard;
	}

	public void setC4Reccard(String c4Reccard) {
		this.c4Reccard = c4Reccard;
	}

	public String getC4Recemp() {
		return c4Recemp;
	}

	public void setC4Recemp(String c4Recemp) {
		this.c4Recemp = c4Recemp;
	}

	public String getC4Relship() {
		return c4Relship;
	}

	public void setC4Relship(String c4Relship) {
		this.c4Relship = c4Relship;
	}

	public String getC4Relmod() {
		return c4Relmod;
	}

	public void setC4Relmod(String c4Relmod) {
		this.c4Relmod = c4Relmod;
	}

	public Integer getC4Cycdate() {
		return c4Cycdate;
	}

	public void setC4Cycdate(Integer c4Cycdate) {
		this.c4Cycdate = c4Cycdate;
	}

	public String getC4Cycadd1() {
		return c4Cycadd1;
	}

	public void setC4Cycadd1(String c4Cycadd1) {
		this.c4Cycadd1 = c4Cycadd1;
	}

	public String getC4Cycadd2() {
		return c4Cycadd2;
	}

	public void setC4Cycadd2(String c4Cycadd2) {
		this.c4Cycadd2 = c4Cycadd2;
	}

	public String getC4Bname() {
		return c4Bname;
	}

	public void setC4Bname(String c4Bname) {
		this.c4Bname = c4Bname;
	}

	public Integer getC4Pinreq() {
		return c4Pinreq;
	}

	public void setC4Pinreq(Integer c4Pinreq) {
		this.c4Pinreq = c4Pinreq;
	}

	public Integer getC4Messreq() {
		return c4Messreq;
	}

	public void setC4Messreq(Integer c4Messreq) {
		this.c4Messreq = c4Messreq;
	}

	public Long getC4Smsamt() {
		return c4Smsamt;
	}

	public void setC4Smsamt(Long c4Smsamt) {
		this.c4Smsamt = c4Smsamt;
	}

	public String getC4SignFlag() {
		return c4SignFlag;
	}

	public void setC4SignFlag(String c4SignFlag) {
		this.c4SignFlag = c4SignFlag;
	}

	public String getC4Revs2() {
		return c4Revs2;
	}

	public void setC4Revs2(String c4Revs2) {
		this.c4Revs2 = c4Revs2;
	}

	public String getC4Stmcode() {
		return c4Stmcode;
	}

	public void setC4Stmcode(String c4Stmcode) {
		this.c4Stmcode = c4Stmcode;
	}

	public Integer getC4Gtoc() {
		return c4Gtoc;
	}

	public void setC4Gtoc(Integer c4Gtoc) {
		this.c4Gtoc = c4Gtoc;
	}

	public String getC4Downpd() {
		return c4Downpd;
	}

	public void setC4Downpd(String c4Downpd) {
		this.c4Downpd = c4Downpd;
	}

	public Integer getC4Rushflg() {
		return c4Rushflg;
	}

	public void setC4Rushflg(Integer c4Rushflg) {
		this.c4Rushflg = c4Rushflg;
	}

	public Integer getC4Rushfee() {
		return c4Rushfee;
	}

	public void setC4Rushfee(Integer c4Rushfee) {
		this.c4Rushfee = c4Rushfee;
	}

	public String getC4Vcode() {
		return c4Vcode;
	}

	public void setC4Vcode(String c4Vcode) {
		this.c4Vcode = c4Vcode;
	}

	public Integer getC4Vrcode() {
		return c4Vrcode;
	}

	public void setC4Vrcode(Integer c4Vrcode) {
		this.c4Vrcode = c4Vrcode;
	}

	public Integer getC4Vccred() {
		return c4Vccred;
	}

	public void setC4Vccred(Integer c4Vccred) {
		this.c4Vccred = c4Vccred;
	}

	public Integer getC4Vfcred() {
		return c4Vfcred;
	}

	public void setC4Vfcred(Integer c4Vfcred) {
		this.c4Vfcred = c4Vfcred;
	}

	public Integer getC4Vpacred() {
		return c4Vpacred;
	}

	public void setC4Vpacred(Integer c4Vpacred) {
		this.c4Vpacred = c4Vpacred;
	}

	public String getC4Cdespmd() {
		return c4Cdespmd;
	}

	public void setC4Cdespmd(String c4Cdespmd) {
		this.c4Cdespmd = c4Cdespmd;
	}

	public Integer getC4Cardcst() {
		return c4Cardcst;
	}

	public void setC4Cardcst(Integer c4Cardcst) {
		this.c4Cardcst = c4Cardcst;
	}

	public Integer getC4Cdesplc() {
		return c4Cdesplc;
	}

	public void setC4Cdesplc(Integer c4Cdesplc) {
		this.c4Cdesplc = c4Cdesplc;
	}

	public String getC4Vercode() {
		return c4Vercode;
	}

	public void setC4Vercode(String c4Vercode) {
		this.c4Vercode = c4Vercode;
	}

	public String getC4Lysrv() {
		return c4Lysrv;
	}

	public void setC4Lysrv(String c4Lysrv) {
		this.c4Lysrv = c4Lysrv;
	}

	public String getC4Lyfee() {
		return c4Lyfee;
	}

	public void setC4Lyfee(String c4Lyfee) {
		this.c4Lyfee = c4Lyfee;
	}

	public String getC4Exflag() {
		return c4Exflag;
	}

	public void setC4Exflag(String c4Exflag) {
		this.c4Exflag = c4Exflag;
	}

	public String getC4Excode() {
		return c4Excode;
	}

	public void setC4Excode(String c4Excode) {
		this.c4Excode = c4Excode;
	}

	public Integer getC4Exrtdt() {
		return c4Exrtdt;
	}

	public void setC4Exrtdt(Integer c4Exrtdt) {
		this.c4Exrtdt = c4Exrtdt;
	}

	public String getC4Exacct() {
		return c4Exacct;
	}

	public void setC4Exacct(String c4Exacct) {
		this.c4Exacct = c4Exacct;
	}

	public String getC4Chflag() {
		return c4Chflag;
	}

	public void setC4Chflag(String c4Chflag) {
		this.c4Chflag = c4Chflag;
	}

	public String getC4Buemb() {
		return c4Buemb;
	}

	public void setC4Buemb(String c4Buemb) {
		this.c4Buemb = c4Buemb;
	}

	public String getC4Empno() {
		return c4Empno;
	}

	public void setC4Empno(String c4Empno) {
		this.c4Empno = c4Empno;
	}

	public String getC4Riskcd() {
		return c4Riskcd;
	}

	public void setC4Riskcd(String c4Riskcd) {
		this.c4Riskcd = c4Riskcd;
	}

	public String getC4Pin1() {
		return c4Pin1;
	}

	public void setC4Pin1(String c4Pin1) {
		this.c4Pin1 = c4Pin1;
	}

	public String getC4Srvcd1() {
		return c4Srvcd1;
	}

	public void setC4Srvcd1(String c4Srvcd1) {
		this.c4Srvcd1 = c4Srvcd1;
	}

	public String getC4Giftno() {
		return c4Giftno;
	}

	public void setC4Giftno(String c4Giftno) {
		this.c4Giftno = c4Giftno;
	}

	public String getC4Psnid1() {
		return c4Psnid1;
	}

	public void setC4Psnid1(String c4Psnid1) {
		this.c4Psnid1 = c4Psnid1;
	}

	public String getC4Sysflg() {
		return c4Sysflg;
	}

	public void setC4Sysflg(String c4Sysflg) {
		this.c4Sysflg = c4Sysflg;
	}

	public String getC4Giftn2() {
		return c4Giftn2;
	}

	public void setC4Giftn2(String c4Giftn2) {
		this.c4Giftn2 = c4Giftn2;
	}

	public String getC4Othcd4() {
		return c4Othcd4;
	}

	public void setC4Othcd4(String c4Othcd4) {
		this.c4Othcd4 = c4Othcd4;
	}

	public String getC4Othtyp4() {
		return c4Othtyp4;
	}

	public void setC4Othtyp4(String c4Othtyp4) {
		this.c4Othtyp4 = c4Othtyp4;
	}

	public Integer getC4Othexp4() {
		return c4Othexp4;
	}

	public void setC4Othexp4(Integer c4Othexp4) {
		this.c4Othexp4 = c4Othexp4;
	}

	public String getC4Obnknm() {
		return c4Obnknm;
	}

	public void setC4Obnknm(String c4Obnknm) {
		this.c4Obnknm = c4Obnknm;
	}

	public String getC4Ra() {
		return c4Ra;
	}

	public void setC4Ra(String c4Ra) {
		this.c4Ra = c4Ra;
	}

	public String getC4Sc() {
		return c4Sc;
	}

	public void setC4Sc(String c4Sc) {
		this.c4Sc = c4Sc;
	}

	public Integer getC4Sn() {
		return c4Sn;
	}

	public void setC4Sn(Integer c4Sn) {
		this.c4Sn = c4Sn;
	}

	public String getC4Clyn1() {
		return c4Clyn1;
	}

	public void setC4Clyn1(String c4Clyn1) {
		this.c4Clyn1 = c4Clyn1;
	}

	public Long getC4Othcrq() {
		return c4Othcrq;
	}

	public void setC4Othcrq(Long c4Othcrq) {
		this.c4Othcrq = c4Othcrq;
	}

	public Integer getC4Mumpfl() {
		return c4Mumpfl;
	}

	public void setC4Mumpfl(Integer c4Mumpfl) {
		this.c4Mumpfl = c4Mumpfl;
	}

	public String getC4Fconm2() {
		return c4Fconm2;
	}

	public void setC4Fconm2(String c4Fconm2) {
		this.c4Fconm2 = c4Fconm2;
	}

	public Integer getC4Fcoyr2() {
		return c4Fcoyr2;
	}

	public void setC4Fcoyr2(Integer c4Fcoyr2) {
		this.c4Fcoyr2 = c4Fcoyr2;
	}

	public BigDecimal getC4Fcoer2() {
		return c4Fcoer2;
	}

	public void setC4Fcoer2(BigDecimal c4Fcoer2) {
		this.c4Fcoer2 = c4Fcoer2;
	}

	public String getC4Usprod() {
		return c4Usprod;
	}

	public void setC4Usprod(String c4Usprod) {
		this.c4Usprod = c4Usprod;
	}

	public String getC4Usmeb() {
		return c4Usmeb;
	}

	public void setC4Usmeb(String c4Usmeb) {
		this.c4Usmeb = c4Usmeb;
	}

	public String getC4Usrel() {
		return c4Usrel;
	}

	public void setC4Usrel(String c4Usrel) {
		this.c4Usrel = c4Usrel;
	}

	public String getC4Reladd() {
		return c4Reladd;
	}

	public void setC4Reladd(String c4Reladd) {
		this.c4Reladd = c4Reladd;
	}

	public String getC4Relpost() {
		return c4Relpost;
	}

	public void setC4Relpost(String c4Relpost) {
		this.c4Relpost = c4Relpost;
	}

	public String getC4Apbtid() {
		return c4Apbtid;
	}

	public void setC4Apbtid(String c4Apbtid) {
		this.c4Apbtid = c4Apbtid;
	}

	public BigDecimal getC4Hmamt() {
		return c4Hmamt;
	}

	public void setC4Hmamt(BigDecimal c4Hmamt) {
		this.c4Hmamt = c4Hmamt;
	}

	public Integer getC4Hloanyr() {
		return c4Hloanyr;
	}

	public void setC4Hloanyr(Integer c4Hloanyr) {
		this.c4Hloanyr = c4Hloanyr;
	}

	public BigDecimal getC4Caramt() {
		return c4Caramt;
	}

	public void setC4Caramt(BigDecimal c4Caramt) {
		this.c4Caramt = c4Caramt;
	}

	public Long getC4Carloan() {
		return c4Carloan;
	}

	public void setC4Carloan(Long c4Carloan) {
		this.c4Carloan = c4Carloan;
	}

	public String getC4Abphon() {
		return c4Abphon;
	}

	public void setC4Abphon(String c4Abphon) {
		this.c4Abphon = c4Abphon;
	}

	public String getC4Abtype() {
		return c4Abtype;
	}

	public void setC4Abtype(String c4Abtype) {
		this.c4Abtype = c4Abtype;
	}

	public String getC4Apsour() {
		return c4Apsour;
	}

	public void setC4Apsour(String c4Apsour) {
		this.c4Apsour = c4Apsour;
	}

	public Integer getC4Caryr() {
		return c4Caryr;
	}

	public void setC4Caryr(Integer c4Caryr) {
		this.c4Caryr = c4Caryr;
	}

	public String getC4Assid() {
		return c4Assid;
	}

	public void setC4Assid(String c4Assid) {
		this.c4Assid = c4Assid;
	}

	public String getC4Assidty() {
		return c4Assidty;
	}

	public void setC4Assidty(String c4Assidty) {
		this.c4Assidty = c4Assidty;
	}

	public String getC4Assname() {
		return c4Assname;
	}

	public void setC4Assname(String c4Assname) {
		this.c4Assname = c4Assname;
	}

	public Date getC4Assbirt() {
		return c4Assbirt;
	}

	public void setC4Assbirt(Date c4Assbirt) {
		this.c4Assbirt = c4Assbirt;
	}

	public String getC4Assgend() {
		return c4Assgend;
	}

	public void setC4Assgend(String c4Assgend) {
		this.c4Assgend = c4Assgend;
	}

	public String getC4Assmars() {
		return c4Assmars;
	}

	public void setC4Assmars(String c4Assmars) {
		this.c4Assmars = c4Assmars;
	}

	public Integer getC4Asshare() {
		return c4Asshare;
	}

	public void setC4Asshare(Integer c4Asshare) {
		this.c4Asshare = c4Asshare;
	}

	public String getC4Asshtel() {
		return c4Asshtel;
	}

	public void setC4Asshtel(String c4Asshtel) {
		this.c4Asshtel = c4Asshtel;
	}

	public String getC4Assbtel() {
		return c4Assbtel;
	}

	public void setC4Assbtel(String c4Assbtel) {
		this.c4Assbtel = c4Assbtel;
	}

	public String getC4Assbext() {
		return c4Assbext;
	}

	public void setC4Assbext(String c4Assbext) {
		this.c4Assbext = c4Assbext;
	}

	public String getC4Asscell() {
		return c4Asscell;
	}

	public void setC4Asscell(String c4Asscell) {
		this.c4Asscell = c4Asscell;
	}

	public String getC4Asscomp() {
		return c4Asscomp;
	}

	public void setC4Asscomp(String c4Asscomp) {
		this.c4Asscomp = c4Asscomp;
	}

	public String getC4Assdept() {
		return c4Assdept;
	}

	public void setC4Assdept(String c4Assdept) {
		this.c4Assdept = c4Assdept;
	}

	public String getC4Cdsel() {
		return c4Cdsel;
	}

	public void setC4Cdsel(String c4Cdsel) {
		this.c4Cdsel = c4Cdsel;
	}

	public String getC4Cdselnt() {
		return c4Cdselnt;
	}

	public void setC4Cdselnt(String c4Cdselnt) {
		this.c4Cdselnt = c4Cdselnt;
	}

	public Integer getC4Decemp() {
		return c4Decemp;
	}

	public void setC4Decemp(Integer c4Decemp) {
		this.c4Decemp = c4Decemp;
	}

	public Long getC4Xcrlmt() {
		return c4Xcrlmt;
	}

	public void setC4Xcrlmt(Long c4Xcrlmt) {
		this.c4Xcrlmt = c4Xcrlmt;
	}

	public Integer getC4Childyn() {
		return c4Childyn;
	}

	public void setC4Childyn(Integer c4Childyn) {
		this.c4Childyn = c4Childyn;
	}

	public Date getC4Fcodat() {
		return c4Fcodat;
	}

	public void setC4Fcodat(Date c4Fcodat) {
		this.c4Fcodat = c4Fcodat;
	}

	public String getC5Sercd11() {
		return c5Sercd11;
	}

	public void setC5Sercd11(String c5Sercd11) {
		this.c5Sercd11 = c5Sercd11;
	}

	public String getC5Sercd12() {
		return c5Sercd12;
	}

	public void setC5Sercd12(String c5Sercd12) {
		this.c5Sercd12 = c5Sercd12;
	}

	public String getC5Sercd13() {
		return c5Sercd13;
	}

	public void setC5Sercd13(String c5Sercd13) {
		this.c5Sercd13 = c5Sercd13;
	}

	public String getC5Sercd14() {
		return c5Sercd14;
	}

	public void setC5Sercd14(String c5Sercd14) {
		this.c5Sercd14 = c5Sercd14;
	}

	public String getC5Sercd15() {
		return c5Sercd15;
	}

	public void setC5Sercd15(String c5Sercd15) {
		this.c5Sercd15 = c5Sercd15;
	}

	public String getC5Sercd16() {
		return c5Sercd16;
	}

	public void setC5Sercd16(String c5Sercd16) {
		this.c5Sercd16 = c5Sercd16;
	}

	public String getC5Sercd17() {
		return c5Sercd17;
	}

	public void setC5Sercd17(String c5Sercd17) {
		this.c5Sercd17 = c5Sercd17;
	}

	public String getC5Sercd18() {
		return c5Sercd18;
	}

	public void setC5Sercd18(String c5Sercd18) {
		this.c5Sercd18 = c5Sercd18;
	}

	public String getC5Sercd19() {
		return c5Sercd19;
	}

	public void setC5Sercd19(String c5Sercd19) {
		this.c5Sercd19 = c5Sercd19;
	}

	public BigDecimal getC5Capcnt() {
		return c5Capcnt;
	}

	public void setC5Capcnt(BigDecimal c5Capcnt) {
		this.c5Capcnt = c5Capcnt;
	}

	public Integer getC5Ptplan() {
		return c5Ptplan;
	}

	public void setC5Ptplan(Integer c5Ptplan) {
		this.c5Ptplan = c5Ptplan;
	}

	public String getC5Cucode1() {
		return c5Cucode1;
	}

	public void setC5Cucode1(String c5Cucode1) {
		this.c5Cucode1 = c5Cucode1;
	}

	public String getC5Cucode2() {
		return c5Cucode2;
	}

	public void setC5Cucode2(String c5Cucode2) {
		this.c5Cucode2 = c5Cucode2;
	}

	public String getC5Accode1() {
		return c5Accode1;
	}

	public void setC5Accode1(String c5Accode1) {
		this.c5Accode1 = c5Accode1;
	}

	public String getC5Accode2() {
		return c5Accode2;
	}

	public void setC5Accode2(String c5Accode2) {
		this.c5Accode2 = c5Accode2;
	}

	public String getC5Ovrmtyn() {
		return c5Ovrmtyn;
	}

	public void setC5Ovrmtyn(String c5Ovrmtyn) {
		this.c5Ovrmtyn = c5Ovrmtyn;
	}

	public String getC5Colevl() {
		return c5Colevl;
	}

	public void setC5Colevl(String c5Colevl) {
		this.c5Colevl = c5Colevl;
	}

	public String getC5Hmst() {
		return c5Hmst;
	}

	public void setC5Hmst(String c5Hmst) {
		this.c5Hmst = c5Hmst;
	}

	public Integer getC5Hmyr() {
		return c5Hmyr;
	}

	public void setC5Hmyr(Integer c5Hmyr) {
		this.c5Hmyr = c5Hmyr;
	}

	public String getC5Schlnmc() {
		return c5Schlnmc;
	}

	public void setC5Schlnmc(String c5Schlnmc) {
		this.c5Schlnmc = c5Schlnmc;
	}

	public String getC5Schlnme() {
		return c5Schlnme;
	}

	public void setC5Schlnme(String c5Schlnme) {
		this.c5Schlnme = c5Schlnme;
	}

	public String getC5Moarea() {
		return c5Moarea;
	}

	public void setC5Moarea(String c5Moarea) {
		this.c5Moarea = c5Moarea;
	}

	public String getC5Emailin() {
		return c5Emailin;
	}

	public void setC5Emailin(String c5Emailin) {
		this.c5Emailin = c5Emailin;
	}

	public String getC5Oversea() {
		return c5Oversea;
	}

	public void setC5Oversea(String c5Oversea) {
		this.c5Oversea = c5Oversea;
	}

	public String getC5Adtype1() {
		return c5Adtype1;
	}

	public void setC5Adtype1(String c5Adtype1) {
		this.c5Adtype1 = c5Adtype1;
	}

	public String getC5Adtype2() {
		return c5Adtype2;
	}

	public void setC5Adtype2(String c5Adtype2) {
		this.c5Adtype2 = c5Adtype2;
	}

	public String getC5Adtype3() {
		return c5Adtype3;
	}

	public void setC5Adtype3(String c5Adtype3) {
		this.c5Adtype3 = c5Adtype3;
	}

	public String getC5Adtype4() {
		return c5Adtype4;
	}

	public void setC5Adtype4(String c5Adtype4) {
		this.c5Adtype4 = c5Adtype4;
	}

	public String getC5Bmwyn() {
		return c5Bmwyn;
	}

	public void setC5Bmwyn(String c5Bmwyn) {
		this.c5Bmwyn = c5Bmwyn;
	}

	public String getC5Msgfree() {
		return c5Msgfree;
	}

	public void setC5Msgfree(String c5Msgfree) {
		this.c5Msgfree = c5Msgfree;
	}

	public Integer getC5Cptno() {
		return c5Cptno;
	}

	public void setC5Cptno(Integer c5Cptno) {
		this.c5Cptno = c5Cptno;
	}

	public Integer getC5Intcode() {
		return c5Intcode;
	}

	public void setC5Intcode(Integer c5Intcode) {
		this.c5Intcode = c5Intcode;
	}

	public String getC5Mngrno() {
		return c5Mngrno;
	}

	public void setC5Mngrno(String c5Mngrno) {
		this.c5Mngrno = c5Mngrno;
	}

	public Date getC5Idte1() {
		return c5Idte1;
	}

	public void setC5Idte1(Date c5Idte1) {
		this.c5Idte1 = c5Idte1;
	}

	public Date getC5Idte3() {
		return c5Idte3;
	}

	public void setC5Idte3(Date c5Idte3) {
		this.c5Idte3 = c5Idte3;
	}

	public Integer getC5Abteam() {
		return c5Abteam;
	}

	public void setC5Abteam(Integer c5Abteam) {
		this.c5Abteam = c5Abteam;
	}

	public Integer getC5Abarea() {
		return c5Abarea;
	}

	public void setC5Abarea(Integer c5Abarea) {
		this.c5Abarea = c5Abarea;
	}

	public String getC5Carlics() {
		return c5Carlics;
	}

	public void setC5Carlics(String c5Carlics) {
		this.c5Carlics = c5Carlics;
	}

	public String getC5Atm1() {
		return c5Atm1;
	}

	public void setC5Atm1(String c5Atm1) {
		this.c5Atm1 = c5Atm1;
	}

	public String getC5Tele1() {
		return c5Tele1;
	}

	public void setC5Tele1(String c5Tele1) {
		this.c5Tele1 = c5Tele1;
	}

	public String getC5Net1() {
		return c5Net1;
	}

	public void setC5Net1(String c5Net1) {
		this.c5Net1 = c5Net1;
	}

	public String getC5Phone1() {
		return c5Phone1;
	}

	public void setC5Phone1(String c5Phone1) {
		this.c5Phone1 = c5Phone1;
	}

	public Integer getC5Cpno1() {
		return c5Cpno1;
	}

	public void setC5Cpno1(Integer c5Cpno1) {
		this.c5Cpno1 = c5Cpno1;
	}

	public Integer getC5Nbrmth1() {
		return c5Nbrmth1;
	}

	public void setC5Nbrmth1(Integer c5Nbrmth1) {
		this.c5Nbrmth1 = c5Nbrmth1;
	}

	public String getC5Comcust() {
		return c5Comcust;
	}

	public void setC5Comcust(String c5Comcust) {
		this.c5Comcust = c5Comcust;
	}

	public String getC5Comrccd() {
		return c5Comrccd;
	}

	public void setC5Comrccd(String c5Comrccd) {
		this.c5Comrccd = c5Comrccd;
	}

	public String getC5Comname() {
		return c5Comname;
	}

	public void setC5Comname(String c5Comname) {
		this.c5Comname = c5Comname;
	}

	public Long getC5Mpllmt() {
		return c5Mpllmt;
	}

	public void setC5Mpllmt(Long c5Mpllmt) {
		this.c5Mpllmt = c5Mpllmt;
	}

	public String getC5Jctype() {
		return c5Jctype;
	}

	public void setC5Jctype(String c5Jctype) {
		this.c5Jctype = c5Jctype;
	}

	public String getC5Mailco1() {
		return c5Mailco1;
	}

	public void setC5Mailco1(String c5Mailco1) {
		this.c5Mailco1 = c5Mailco1;
	}

	public String getC5Guarnm2() {
		return c5Guarnm2;
	}

	public void setC5Guarnm2(String c5Guarnm2) {
		this.c5Guarnm2 = c5Guarnm2;
	}

	public String getC5Guar2() {
		return c5Guar2;
	}

	public void setC5Guar2(String c5Guar2) {
		this.c5Guar2 = c5Guar2;
	}

	public String getC5Empno() {
		return c5Empno;
	}

	public void setC5Empno(String c5Empno) {
		this.c5Empno = c5Empno;
	}

	public String getC5Pathway() {
		return c5Pathway;
	}

	public void setC5Pathway(String c5Pathway) {
		this.c5Pathway = c5Pathway;
	}

	public String getC5Guidtp() {
		return c5Guidtp;
	}

	public void setC5Guidtp(String c5Guidtp) {
		this.c5Guidtp = c5Guidtp;
	}

	public Long getC5Cucred() {
		return c5Cucred;
	}

	public void setC5Cucred(Long c5Cucred) {
		this.c5Cucred = c5Cucred;
	}

	public String getC5Ncred() {
		return c5Ncred;
	}

	public void setC5Ncred(String c5Ncred) {
		this.c5Ncred = c5Ncred;
	}

	public Long getC5Credlmt() {
		return c5Credlmt;
	}

	public void setC5Credlmt(Long c5Credlmt) {
		this.c5Credlmt = c5Credlmt;
	}

	public Integer getC5Expydt() {
		return c5Expydt;
	}

	public void setC5Expydt(Integer c5Expydt) {
		this.c5Expydt = c5Expydt;
	}

	public Long getC5Pinlim1() {
		return c5Pinlim1;
	}

	public void setC5Pinlim1(Long c5Pinlim1) {
		this.c5Pinlim1 = c5Pinlim1;
	}

	public String getC5Usrel() {
		return c5Usrel;
	}

	public void setC5Usrel(String c5Usrel) {
		this.c5Usrel = c5Usrel;
	}

	public String getC5Abtype() {
		return c5Abtype;
	}

	public void setC5Abtype(String c5Abtype) {
		this.c5Abtype = c5Abtype;
	}

	public Integer getC5Othbnk() {
		return c5Othbnk;
	}

	public void setC5Othbnk(Integer c5Othbnk) {
		this.c5Othbnk = c5Othbnk;
	}

	public String getC5Sendnbr() {
		return c5Sendnbr;
	}

	public void setC5Sendnbr(String c5Sendnbr) {
		this.c5Sendnbr = c5Sendnbr;
	}

	public Long getC5Templmt() {
		return c5Templmt;
	}

	public void setC5Templmt(Long c5Templmt) {
		this.c5Templmt = c5Templmt;
	}

	public Date getC5Tlmtbeg() {
		return c5Tlmtbeg;
	}

	public void setC5Tlmtbeg(Date c5Tlmtbeg) {
		this.c5Tlmtbeg = c5Tlmtbeg;
	}

	public Date getC5Tlmtend() {
		return c5Tlmtend;
	}

	public void setC5Tlmtend(Date c5Tlmtend) {
		this.c5Tlmtend = c5Tlmtend;
	}

	public String getC5PoiCod() {
		return c5PoiCod;
	}

	public void setC5PoiCod(String c5PoiCod) {
		this.c5PoiCod = c5PoiCod;
	}

	public String getC5WorkTy() {
		return c5WorkTy;
	}

	public void setC5WorkTy(String c5WorkTy) {
		this.c5WorkTy = c5WorkTy;
	}

	public String getC5ProCod() {
		return c5ProCod;
	}

	public void setC5ProCod(String c5ProCod) {
		this.c5ProCod = c5ProCod;
	}

	public String getC5SinDua() {
		return c5SinDua;
	}

	public void setC5SinDua(String c5SinDua) {
		this.c5SinDua = c5SinDua;
	}

	public String getC5AprCom() {
		return c5AprCom;
	}

	public void setC5AprCom(String c5AprCom) {
		this.c5AprCom = c5AprCom;
	}

	public String getC5RelPar() {
		return c5RelPar;
	}

	public void setC5RelPar(String c5RelPar) {
		this.c5RelPar = c5RelPar;
	}

	public String getC5OtherI() {
		return c5OtherI;
	}

	public void setC5OtherI(String c5OtherI) {
		this.c5OtherI = c5OtherI;
	}

	public String getC5BusOwn() {
		return c5BusOwn;
	}

	public void setC5BusOwn(String c5BusOwn) {
		this.c5BusOwn = c5BusOwn;
	}

	public Date getC5BusEst() {
		return c5BusEst;
	}

	public void setC5BusEst(Date c5BusEst) {
		this.c5BusEst = c5BusEst;
	}

	public String getC5BusReg() {
		return c5BusReg;
	}

	public void setC5BusReg(String c5BusReg) {
		this.c5BusReg = c5BusReg;
	}

	public String getC5Cuseg1() {
		return c5Cuseg1;
	}

	public void setC5Cuseg1(String c5Cuseg1) {
		this.c5Cuseg1 = c5Cuseg1;
	}

	public Integer getC5Intbnk() {
		return c5Intbnk;
	}

	public void setC5Intbnk(Integer c5Intbnk) {
		this.c5Intbnk = c5Intbnk;
	}

	public String getC5TranRte() {
		return c5TranRte;
	}

	public void setC5TranRte(String c5TranRte) {
		this.c5TranRte = c5TranRte;
	}

	public String getC5CosuRte() {
		return c5CosuRte;
	}

	public void setC5CosuRte(String c5CosuRte) {
		this.c5CosuRte = c5CosuRte;
	}

	public String getC5DepoRte() {
		return c5DepoRte;
	}

	public void setC5DepoRte(String c5DepoRte) {
		this.c5DepoRte = c5DepoRte;
	}

	public Date getC5Sgndte1() {
		return c5Sgndte1;
	}

	public void setC5Sgndte1(Date c5Sgndte1) {
		this.c5Sgndte1 = c5Sgndte1;
	}

	public Date getC5Regdte1() {
		return c5Regdte1;
	}

	public void setC5Regdte1(Date c5Regdte1) {
		this.c5Regdte1 = c5Regdte1;
	}

	public Date getC5Sgndte3() {
		return c5Sgndte3;
	}

	public void setC5Sgndte3(Date c5Sgndte3) {
		this.c5Sgndte3 = c5Sgndte3;
	}

	public Date getC5Regdte3() {
		return c5Regdte3;
	}

	public void setC5Regdte3(Date c5Regdte3) {
		this.c5Regdte3 = c5Regdte3;
	}

	public String getC5Curef1() {
		return c5Curef1;
	}

	public void setC5Curef1(String c5Curef1) {
		this.c5Curef1 = c5Curef1;
	}

	public String getC5Mastno1() {
		return c5Mastno1;
	}

	public void setC5Mastno1(String c5Mastno1) {
		this.c5Mastno1 = c5Mastno1;
	}

	public String getC5Ntmsg1() {
		return c5Ntmsg1;
	}

	public void setC5Ntmsg1(String c5Ntmsg1) {
		this.c5Ntmsg1 = c5Ntmsg1;
	}

	public String getC5Ntmsg2() {
		return c5Ntmsg2;
	}

	public void setC5Ntmsg2(String c5Ntmsg2) {
		this.c5Ntmsg2 = c5Ntmsg2;
	}

	public String getC5Cardto1() {
		return c5Cardto1;
	}

	public void setC5Cardto1(String c5Cardto1) {
		this.c5Cardto1 = c5Cardto1;
	}

	public String getC5Abcode() {
		return c5Abcode;
	}

	public void setC5Abcode(String c5Abcode) {
		this.c5Abcode = c5Abcode;
	}

	public String getC5Pbcbrnc() {
		return c5Pbcbrnc;
	}

	public void setC5Pbcbrnc(String c5Pbcbrnc) {
		this.c5Pbcbrnc = c5Pbcbrnc;
	}

	public String getC5Comnm2p() {
		return c5Comnm2p;
	}

	public void setC5Comnm2p(String c5Comnm2p) {
		this.c5Comnm2p = c5Comnm2p;
	}

	public String getC5Comnm2v() {
		return c5Comnm2v;
	}

	public void setC5Comnm2v(String c5Comnm2v) {
		this.c5Comnm2v = c5Comnm2v;
	}

	public Integer getC5Noreiss() {
		return c5Noreiss;
	}

	public void setC5Noreiss(Integer c5Noreiss) {
		this.c5Noreiss = c5Noreiss;
	}

	public String getC5Msmpnbr() {
		return c5Msmpnbr;
	}

	public void setC5Msmpnbr(String c5Msmpnbr) {
		this.c5Msmpnbr = c5Msmpnbr;
	}

	public Long getC5Msmpbal() {
		return c5Msmpbal;
	}

	public void setC5Msmpbal(Long c5Msmpbal) {
		this.c5Msmpbal = c5Msmpbal;
	}

	public String getC5Cdsel1() {
		return c5Cdsel1;
	}

	public void setC5Cdsel1(String c5Cdsel1) {
		this.c5Cdsel1 = c5Cdsel1;
	}

	public String getC5HdwrSn() {
		return c5HdwrSn;
	}

	public void setC5HdwrSn(String c5HdwrSn) {
		this.c5HdwrSn = c5HdwrSn;
	}

	public String getC5Aid() {
		return c5Aid;
	}

	public void setC5Aid(String c5Aid) {
		this.c5Aid = c5Aid;
	}

	public Integer getC5Intflcd() {
		return c5Intflcd;
	}

	public void setC5Intflcd(Integer c5Intflcd) {
		this.c5Intflcd = c5Intflcd;
	}

	public Long getC5Deposit() {
		return c5Deposit;
	}

	public void setC5Deposit(Long c5Deposit) {
		this.c5Deposit = c5Deposit;
	}

	public Integer getC5Dailytp() {
		return c5Dailytp;
	}

	public void setC5Dailytp(Integer c5Dailytp) {
		this.c5Dailytp = c5Dailytp;
	}

	public String getC5OpeMod() {
		return c5OpeMod;
	}

	public void setC5OpeMod(String c5OpeMod) {
		this.c5OpeMod = c5OpeMod;
	}

	public String getC5OpeTpe() {
		return c5OpeTpe;
	}

	public void setC5OpeTpe(String c5OpeTpe) {
		this.c5OpeTpe = c5OpeTpe;
	}

	public String getC5Cashrte() {
		return c5Cashrte;
	}

	public void setC5Cashrte(String c5Cashrte) {
		this.c5Cashrte = c5Cashrte;
	}

	public Integer getC5Beatype() {
		return c5Beatype;
	}

	public void setC5Beatype(Integer c5Beatype) {
		this.c5Beatype = c5Beatype;
	}

	public String getC5Inland() {
		return c5Inland;
	}

	public void setC5Inland(String c5Inland) {
		this.c5Inland = c5Inland;
	}

	public Integer getC5Feegrp() {
		return c5Feegrp;
	}

	public void setC5Feegrp(Integer c5Feegrp) {
		this.c5Feegrp = c5Feegrp;
	}

	public Integer getC5CcYn1() {
		return c5CcYn1;
	}

	public void setC5CcYn1(Integer c5CcYn1) {
		this.c5CcYn1 = c5CcYn1;
	}

	public Integer getC5EcYn1() {
		return c5EcYn1;
	}

	public void setC5EcYn1(Integer c5EcYn1) {
		this.c5EcYn1 = c5EcYn1;
	}

	public Long getC5Frelim() {
		return c5Frelim;
	}

	public void setC5Frelim(Long c5Frelim) {
		this.c5Frelim = c5Frelim;
	}

	public String getC5LiveCy1() {
		return c5LiveCy1;
	}

	public void setC5LiveCy1(String c5LiveCy1) {
		this.c5LiveCy1 = c5LiveCy1;
	}

	public Integer getC5IntflCode() {
		return c5IntflCode;
	}

	public void setC5IntflCode(Integer c5IntflCode) {
		this.c5IntflCode = c5IntflCode;
	}

	public String getC5Detcard() {
		return c5Detcard;
	}

	public void setC5Detcard(String c5Detcard) {
		this.c5Detcard = c5Detcard;
	}

	public String getC5QqNbr1() {
		return c5QqNbr1;
	}

	public void setC5QqNbr1(String c5QqNbr1) {
		this.c5QqNbr1 = c5QqNbr1;
	}

	public String getC5WeixinNbr1() {
		return c5WeixinNbr1;
	}

	public void setC5WeixinNbr1(String c5WeixinNbr1) {
		this.c5WeixinNbr1 = c5WeixinNbr1;
	}

	public String getC5IssMod() {
		return c5IssMod;
	}

	public void setC5IssMod(String c5IssMod) {
		this.c5IssMod = c5IssMod;
	}

	public String getC5Cardcst2() {
		return c5Cardcst2;
	}

	public void setC5Cardcst2(String c5Cardcst2) {
		this.c5Cardcst2 = c5Cardcst2;
	}

	public String getC5Isdpif1() {
		return c5Isdpif1;
	}

	public void setC5Isdpif1(String c5Isdpif1) {
		this.c5Isdpif1 = c5Isdpif1;
	}

	public String getC5Isdpif3() {
		return c5Isdpif3;
	}

	public void setC5Isdpif3(String c5Isdpif3) {
		this.c5Isdpif3 = c5Isdpif3;
	}

	public String getC5Cucode3() {
		return c5Cucode3;
	}

	public void setC5Cucode3(String c5Cucode3) {
		this.c5Cucode3 = c5Cucode3;
	}

	public Integer getC6OutMth() {
		return c6OutMth;
	}

	public void setC6OutMth(Integer c6OutMth) {
		this.c6OutMth = c6OutMth;
	}

	public String getThirdPartyYn1() {
		return thirdPartyYn1;
	}

	public void setThirdPartyYn1(String thirdPartyYn1) {
		this.thirdPartyYn1 = thirdPartyYn1;
	}

	public Integer getPaymtYn() {
		return paymtYn;
	}

	public void setPaymtYn(Integer paymtYn) {
		this.paymtYn = paymtYn;
	}

	public String getC5Cucode4() {
		return c5Cucode4;
	}

	public void setC5Cucode4(String c5Cucode4) {
		this.c5Cucode4 = c5Cucode4;
	}

	public Date getC5LoanDte() {
		return c5LoanDte;
	}

	public void setC5LoanDte(Date c5LoanDte) {
		this.c5LoanDte = c5LoanDte;
	}

	public BigDecimal getC5IntrRatew() {
		return c5IntrRatew;
	}

	public void setC5IntrRatew(BigDecimal c5IntrRatew) {
		this.c5IntrRatew = c5IntrRatew;
	}

	public Integer getC5RmbBill1() {
		return c5RmbBill1;
	}

	public void setC5RmbBill1(Integer c5RmbBill1) {
		this.c5RmbBill1 = c5RmbBill1;
	}

	public String getSeriesId1() {
		return seriesId1;
	}

	public void setSeriesId1(String seriesId1) {
		this.seriesId1 = seriesId1;
	}

	public Integer getC5MplmtFlag() {
		return c5MplmtFlag;
	}

	public void setC5MplmtFlag(Integer c5MplmtFlag) {
		this.c5MplmtFlag = c5MplmtFlag;
	}

	public String getC5HdwrSn2() {
		return c5HdwrSn2;
	}

	public void setC5HdwrSn2(String c5HdwrSn2) {
		this.c5HdwrSn2 = c5HdwrSn2;
	}

	public String getC5Aid2() {
		return c5Aid2;
	}

	public void setC5Aid2(String c5Aid2) {
		this.c5Aid2 = c5Aid2;
	}

	public Integer getC6RelFlag1() {
		return c6RelFlag1;
	}

	public void setC6RelFlag1(Integer c6RelFlag1) {
		this.c6RelFlag1 = c6RelFlag1;
	}

	public Integer getC6RelFlag3() {
		return c6RelFlag3;
	}

	public void setC6RelFlag3(Integer c6RelFlag3) {
		this.c6RelFlag3 = c6RelFlag3;
	}

	public String getC6ClassNbr1() {
		return c6ClassNbr1;
	}

	public void setC6ClassNbr1(String c6ClassNbr1) {
		this.c6ClassNbr1 = c6ClassNbr1;
	}

	public String getC6ClassNbr3() {
		return c6ClassNbr3;
	}

	public void setC6ClassNbr3(String c6ClassNbr3) {
		this.c6ClassNbr3 = c6ClassNbr3;
	}

	public String getC6WhiteRsn1() {
		return c6WhiteRsn1;
	}

	public void setC6WhiteRsn1(String c6WhiteRsn1) {
		this.c6WhiteRsn1 = c6WhiteRsn1;
	}

	public String getC6WhiteRsn3() {
		return c6WhiteRsn3;
	}

	public void setC6WhiteRsn3(String c6WhiteRsn3) {
		this.c6WhiteRsn3 = c6WhiteRsn3;
	}

	public Date getC6MpExpdte() {
		return c6MpExpdte;
	}

	public void setC6MpExpdte(Date c6MpExpdte) {
		this.c6MpExpdte = c6MpExpdte;
	}

	public String getC6Ethnic1() {
		return c6Ethnic1;
	}

	public void setC6Ethnic1(String c6Ethnic1) {
		this.c6Ethnic1 = c6Ethnic1;
	}

	public String getC6Occupation1() {
		return c6Occupation1;
	}

	public void setC6Occupation1(String c6Occupation1) {
		this.c6Occupation1 = c6Occupation1;
	}

	public Integer getC6EnrollDate() {
		return c6EnrollDate;
	}

	public void setC6EnrollDate(Integer c6EnrollDate) {
		this.c6EnrollDate = c6EnrollDate;
	}

	public Integer getC6GraduDate() {
		return c6GraduDate;
	}

	public void setC6GraduDate(Integer c6GraduDate) {
		this.c6GraduDate = c6GraduDate;
	}

	public Integer getC6Unqpass1() {
		return c6Unqpass1;
	}

	public void setC6Unqpass1(Integer c6Unqpass1) {
		this.c6Unqpass1 = c6Unqpass1;
	}

	public BigDecimal getC6MaxMpamt1() {
		return c6MaxMpamt1;
	}

	public void setC6MaxMpamt1(BigDecimal c6MaxMpamt1) {
		this.c6MaxMpamt1 = c6MaxMpamt1;
	}

	public BigDecimal getC6MinMpamt1() {
		return c6MinMpamt1;
	}

	public void setC6MinMpamt1(BigDecimal c6MinMpamt1) {
		this.c6MinMpamt1 = c6MinMpamt1;
	}

	public String getC6Abreason() {
		return c6Abreason;
	}

	public void setC6Abreason(String c6Abreason) {
		this.c6Abreason = c6Abreason;
	}

	public String getC6Absouarea() {
		return c6Absouarea;
	}

	public void setC6Absouarea(String c6Absouarea) {
		this.c6Absouarea = c6Absouarea;
	}

	public String getC6UnionCd() {
		return c6UnionCd;
	}

	public void setC6UnionCd(String c6UnionCd) {
		this.c6UnionCd = c6UnionCd;
	}

	public String getC6FinanceCd() {
		return c6FinanceCd;
	}

	public void setC6FinanceCd(String c6FinanceCd) {
		this.c6FinanceCd = c6FinanceCd;
	}

	public String getC6LmtType1() {
		return c6LmtType1;
	}

	public void setC6LmtType1(String c6LmtType1) {
		this.c6LmtType1 = c6LmtType1;
	}

	public Integer getC6SublmtNbr1() {
		return c6SublmtNbr1;
	}

	public void setC6SublmtNbr1(Integer c6SublmtNbr1) {
		this.c6SublmtNbr1 = c6SublmtNbr1;
	}

	public Long getC6Limit1() {
		return c6Limit1;
	}

	public void setC6Limit1(Long c6Limit1) {
		this.c6Limit1 = c6Limit1;
	}

	public Integer getC6Attrbte1() {
		return c6Attrbte1;
	}

	public void setC6Attrbte1(Integer c6Attrbte1) {
		this.c6Attrbte1 = c6Attrbte1;
	}

	public Integer getC6AvailYn1() {
		return c6AvailYn1;
	}

	public void setC6AvailYn1(Integer c6AvailYn1) {
		this.c6AvailYn1 = c6AvailYn1;
	}

	public Date getC6BeginDate1() {
		return c6BeginDate1;
	}

	public void setC6BeginDate1(Date c6BeginDate1) {
		this.c6BeginDate1 = c6BeginDate1;
	}

	public Date getC6EndDate1() {
		return c6EndDate1;
	}

	public void setC6EndDate1(Date c6EndDate1) {
		this.c6EndDate1 = c6EndDate1;
	}

	public String getC6LmtType2() {
		return c6LmtType2;
	}

	public void setC6LmtType2(String c6LmtType2) {
		this.c6LmtType2 = c6LmtType2;
	}

	public Integer getC6SublmtNbr2() {
		return c6SublmtNbr2;
	}

	public void setC6SublmtNbr2(Integer c6SublmtNbr2) {
		this.c6SublmtNbr2 = c6SublmtNbr2;
	}

	public Long getC6Limit2() {
		return c6Limit2;
	}

	public void setC6Limit2(Long c6Limit2) {
		this.c6Limit2 = c6Limit2;
	}

	public Integer getC6Attrbte2() {
		return c6Attrbte2;
	}

	public void setC6Attrbte2(Integer c6Attrbte2) {
		this.c6Attrbte2 = c6Attrbte2;
	}

	public Integer getC6AvailYn2() {
		return c6AvailYn2;
	}

	public void setC6AvailYn2(Integer c6AvailYn2) {
		this.c6AvailYn2 = c6AvailYn2;
	}

	public Date getC6BeginDate2() {
		return c6BeginDate2;
	}

	public void setC6BeginDate2(Date c6BeginDate2) {
		this.c6BeginDate2 = c6BeginDate2;
	}

	public Date getC6EndDate2() {
		return c6EndDate2;
	}

	public void setC6EndDate2(Date c6EndDate2) {
		this.c6EndDate2 = c6EndDate2;
	}

	public Integer getC6VcnYn() {
		return c6VcnYn;
	}

	public void setC6VcnYn(Integer c6VcnYn) {
		this.c6VcnYn = c6VcnYn;
	}

	public Integer getC6VcnChl() {
		return c6VcnChl;
	}

	public void setC6VcnChl(Integer c6VcnChl) {
		this.c6VcnChl = c6VcnChl;
	}

	public Date getC6ItcdEnddt() {
		return c6ItcdEnddt;
	}

	public void setC6ItcdEnddt(Date c6ItcdEnddt) {
		this.c6ItcdEnddt = c6ItcdEnddt;
	}

	public String getC5Reserv2() {
		return c5Reserv2;
	}

	public void setC5Reserv2(String c5Reserv2) {
		this.c5Reserv2 = c5Reserv2;
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
		this.crtUser = crtUser;
	}

	public Date getLstUpdTime() {
		return lstUpdTime;
	}

	public void setLstUpdTime(Date lstUpdTime) {
		this.lstUpdTime = lstUpdTime;
	}

	public Date getLstUpdDate() {
		return lstUpdDate;
	}

	public void setLstUpdDate(Date lstUpdDate) {
		this.lstUpdDate = lstUpdDate;
	}

	public String getLstUpdUser() {
		return lstUpdUser;
	}

	public void setLstUpdUser(String lstUpdUser) {
		this.lstUpdUser = lstUpdUser;
	}

	public String getBatchOperateFlag() {
		return batchOperateFlag;
	}

	public void setBatchOperateFlag(String batchOperateFlag) {
		this.batchOperateFlag = batchOperateFlag;
	}

	public String getC1c2Flag() {
		return c1c2Flag;
	}

	public void setC1c2Flag(String c1c2Flag) {
		this.c1c2Flag = c1c2Flag;
	}	
	public String getYdjFlag() {
		return ydjFlag;
	}

	public void setYdjFlag(String ydjFlag) {
		this.ydjFlag = ydjFlag;
	}
	
	public String getInputChannel() {
		return inputChannel;
	}

	public void setInputChannel(String inputChannel) {
		this.inputChannel = inputChannel;
	}

	public String getEpayMatch() {
		return epayMatch;
	}

	public void setEpayMatch(String epayMatch) {
		this.epayMatch = epayMatch;
	}

	public String getCzfqMatch() {
		return czfqMatch;
	}

	public void setCzfqMatch(String czfqMatch) {
		this.czfqMatch = czfqMatch;
	}

	public String getC1HmaddTotal() {
		return c1HmaddTotal;
	}

	public void setC1HmaddTotal(String c1HmaddTotal) {
		this.c1HmaddTotal = c1HmaddTotal;
	}

	public String getC1CoaddTotal() {
		return c1CoaddTotal;
	}

	public void setC1CoaddTotal(String c1CoaddTotal) {
		this.c1CoaddTotal = c1CoaddTotal;
	}

	public String getExistCardFlag() {
		return existCardFlag;
	}

	public void setExistCardFlag(String existCardFlag) {
		this.existCardFlag = existCardFlag;
	}

	public String getC5Isdpif2() {
		return c5Isdpif2;
	}

	public void setC5Isdpif2(String c5Isdpif2) {
		this.c5Isdpif2 = c5Isdpif2;
	}

	public String getC6Ethnic2() {
		return c6Ethnic2;
	}

	public void setC6Ethnic2(String c6Ethnic2) {
		this.c6Ethnic2 = c6Ethnic2;
	}
	
}