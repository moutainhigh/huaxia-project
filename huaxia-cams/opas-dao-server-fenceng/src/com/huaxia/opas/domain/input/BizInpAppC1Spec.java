package com.huaxia.opas.domain.input;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class BizInpAppC1Spec implements Serializable {
	private static final long serialVersionUID = 9037663115749718366L;
	private String appId;
	private String c1c2Flag;
	private String appProd;
	private String c4Vercode;
	private Integer c4Ccredit;
	private Integer c4Gtoc;
	private String c1Cname;
	private String c1Ename;
	private String c1Gender;
//	@JSONField(format = "yyyy-MM-dd")
//	private Date c1Birth;
	private String c1Birth;
	private String c1Nation;
	private String c2Natncd1;
	private String c1Idtype;
	private String c1Idnbr;
//	@JSONField(format = "yyyy-MM-dd")
//	private Date c2Iddt1;
	private String c2Iddt1;
//	@JSONField(format = "yyyy-MM-dd")
//	private Date c5Idte1;
	private String c5Idte1;
	private String c1Marst;
	private Integer c1Depend;
	private String c1Educls;
	private String c1Hmadd1;
	private String c1Hmadd2;
	private String c1Hmadd3;
	private String c1Hmadd4;
	private String c1Hmpost;
	private String c1Idadd1;
	private String c1Idadd2;
	private String c1Idadd3;
	private String c1Idadd4;
	private String c1Idpost;
	private String c1Hmare;
	private String c1Hmtel;
	private String c1Mobile;
	private String c1Email;
	private String c1Hmst;
	private BigDecimal c4Hmamt;
	private Long c1Hloan;
	private Integer c4Hloanyr;
	private String c1Carst;
	private String c1Carnbr;
	private String c1Carbrnd;
	//@JSONField(format = "yyyy-MM-dd")
	//private Date c1Cardate;
	private BigDecimal c4Caramt;
	private Long c4Carloan;
	private String c1Coname;
	private String c1Codept;
	private String c1Cobuscd;
	private String c1Coadd1;
	private String c1Coadd2;
	private String c1Coadd3;
	private String c1Coadd4;
	private String c1Copost;
	private String c1Cotel;
	private String c1Coext;
	private String c1Cocode;
	private String c1Cokind;
	private String c1Coduty;
	private Integer c1Coyr;
	private BigDecimal c1Earn;
	private String c1Fconame;
	private Integer c1Fcoyr;
	private String c1Fcotel;
	private BigDecimal c1Foreann;
	private String c4Cycadd1;
	private Integer c4Pinreq;
	private String c4Usprod;
	private String c4Usmeb;
	private String c4Othtype;
	private String c1Rename;
	private String c1Reship;
	private String c1Retelar;
	private String c1Retel;
	private String c1Remobil;
	private String c1Xname1;
	private String c1Xship1;
	private String c1Xtelar1;
	private String c1Xtel1;
	private String c1Xmobil1;
	//private String c4Excode;
	//private String c5Msgfree;
	private Integer c5Ptplan;
	private Integer c4Messreq;
	private Integer c4Rushflg;
//	private String c5Sercd11;
//	private String c5Sercd12;
//	private String c5Sercd13;
	private String c4Recname;
	private String c4Reccard;
	private Integer c4Cycdate;
	private String c4Stmcode;
	private String c2Signck;
//	@JSONField(format = "yyyy-MM-dd")
//	private Date c5Sgndte1;
	private String c5Sgndte1;
	private String c4Giftn2;
	private String c4Giftno;
	private String c4Apbtid;
	private String c4Othcard;
	private Long c4Othcrq;
	private Long c4Othcred;
	private Integer c4Vccred;
	private String c4Vcode;
	private String c5Abcode;
	private String c4Abuser;
	private String c4Abname;
	private String c4Abphon;
	private String c4Apbatch;
	private String c4Clyn1;
	private String c4Abtype;
	private String c4Apsour;
	//private String c4Relship;
	private Integer c4Decemp;
	private Integer c1Hmyr;
	private String c4Cdsel;
	private String ydjFlag;
	private String appAcctyp;
	private String c1Title;
	private String c1Cardnbr;
	private String c4Caccnbr; //授权还款华夏卡（借记卡）卡号
	private String c4Cpaymod; //还款方式
	private String c1Busiser; //华夏银行信用卡中心将免费为您提供各项促销活动的短信通知，请勾选您是否愿意接受此类信息
	private String c1Class; //申请人客户类别
	private String c4Bname;// 凸字姓名
	private String c5Jctype;// 项目号
	private String c5Pbcbrnc;// 客户类型
	private String c1Telvl;// 职称 
	private String c4Fconm2;// 单位名称2
	private String c5Isdpif1;// 证件签发机构
	private Integer c5Abarea;// 车贷年限
	private String c6Absouarea;// 推广区域代码
	private String c6Ethnic1;// 推广区域代码
	private String c5Empno;// 初审人员代码
	private String c6Gatnbr1; //主卡持卡人港澳台通行证
	private String c6OccuDes1;//主卡申请人职业描述     职称
	private String c6ResiTaxid1;//主卡申请人居民税务标识
	private String c4Recemp;//推荐人工号(反欺诈高风险新增 20201019)
	

		
	public String getC6ResiTaxid1() {
		return c6ResiTaxid1;
	}
	public void setC6ResiTaxid1(String c6ResiTaxid1) {
		this.c6ResiTaxid1 = c6ResiTaxid1;
	}
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
	public String getC5Empno() {
		return c5Empno;
	}
	public void setC5Empno(String c5Empno) {
		this.c5Empno = c5Empno;
	}
	public String getC6Ethnic1() {
		return c6Ethnic1;
	}
	public void setC6Ethnic1(String c6Ethnic1) {
		this.c6Ethnic1 = c6Ethnic1;
	}
	public String getC6Absouarea() {
		return c6Absouarea;
	}
	public void setC6Absouarea(String c6Absouarea) {
		this.c6Absouarea = c6Absouarea;
	}
	public Integer getC5Abarea() {
		return c5Abarea;
	}
	public void setC5Abarea(Integer c5Abarea) {
		this.c5Abarea = c5Abarea;
	}
	public String getC5Isdpif1() {
		return c5Isdpif1;
	}
	public void setC5Isdpif1(String c5Isdpif1) {
		this.c5Isdpif1 = c5Isdpif1;
	}
	public String getC4Fconm2() {
		return c4Fconm2;
	}
	public void setC4Fconm2(String c4Fconm2) {
		this.c4Fconm2 = c4Fconm2;
	}
	//是否收取快速发卡费用
	private String c4Rushfee;
	
	//卡片递送方式
	private String c4Cdespmd;
	
	
	
	public String getC1Telvl() {
		return c1Telvl;
	}
	public void setC1Telvl(String c1Telvl) {
		this.c1Telvl = c1Telvl;
	}
	public String getC5Jctype() {
		return c5Jctype;
	}
	public void setC5Jctype(String c5Jctype) {
		this.c5Jctype = c5Jctype;
	}
	public String getC5Pbcbrnc() {
		return c5Pbcbrnc;
	}
	public void setC5Pbcbrnc(String c5Pbcbrnc) {
		this.c5Pbcbrnc = c5Pbcbrnc;
	}
	public String getC4Cdespmd() {
		return c4Cdespmd;
	}
	public void setC4Cdespmd(String c4Cdespmd) {
		this.c4Cdespmd = c4Cdespmd;
	}
	public String getC4Rushfee() {
		return c4Rushfee;
	}
	public void setC4Rushfee(String c4Rushfee) {
		this.c4Rushfee = c4Rushfee;
	}
	public String getC1Birth() {
		return c1Birth;
	}
	public String getC2Iddt1() {
		return c2Iddt1;
	}
	public String getC5Idte1() {
		return c5Idte1;
	}
	public String getC5Sgndte1() {
		return c5Sgndte1;
	}
	public void setC1Birth(String c1Birth) {
		this.c1Birth = c1Birth;
	}
	public void setC2Iddt1(String c2Iddt1) {
		this.c2Iddt1 = c2Iddt1;
	}
	public void setC5Idte1(String c5Idte1) {
		this.c5Idte1 = c5Idte1;
	}
	public void setC5Sgndte1(String c5Sgndte1) {
		this.c5Sgndte1 = c5Sgndte1;
	}
	public String getC4Bname() {
		return c4Bname;
	}
	public void setC4Bname(String c4Bname) {
		this.c4Bname = c4Bname;
	}
	public String getC1Class() {
		return c1Class;
	}
	public void setC1Class(String c1Class) {
		this.c1Class = c1Class;
	}
	public String getC1Busiser() {
		return c1Busiser;
	}
	public void setC1Busiser(String c1Busiser) {
		this.c1Busiser = c1Busiser;
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
	public String getC1Cardnbr() {
		return c1Cardnbr;
	}
	public void setC1Cardnbr(String c1Cardnbr) {
		this.c1Cardnbr = c1Cardnbr;
	}
	public String getAppAcctyp() {
		return appAcctyp;
	}
	public void setAppAcctyp(String appAcctyp) {
		this.appAcctyp = appAcctyp;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getC1c2Flag() {
		return c1c2Flag;
	}
	public void setC1c2Flag(String c1c2Flag) {
		this.c1c2Flag = c1c2Flag;
	}
	public String getAppProd() {
		return appProd;
	}
	public void setAppProd(String appProd) {
		this.appProd = appProd;
	}
	public String getC4Vercode() {
		return c4Vercode;
	}
	public void setC4Vercode(String c4Vercode) {
		this.c4Vercode = c4Vercode;
	}
	public Integer getC4Ccredit() {
		return c4Ccredit;
	}
	public void setC4Ccredit(Integer c4Ccredit) {
		this.c4Ccredit = c4Ccredit;
	}
	public Integer getC4Gtoc() {
		return c4Gtoc;
	}
	public void setC4Gtoc(Integer c4Gtoc) {
		this.c4Gtoc = c4Gtoc;
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

	public String getC1Nation() {
		return c1Nation;
	}
	public void setC1Nation(String c1Nation) {
		this.c1Nation = c1Nation;
	}
	public String getC2Natncd1() {
		return c2Natncd1;
	}
	public void setC2Natncd1(String c2Natncd1) {
		this.c2Natncd1 = c2Natncd1;
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

	public String getC1Marst() {
		return c1Marst;
	}
	public void setC1Marst(String c1Marst) {
		this.c1Marst = c1Marst;
	}
	public Integer getC1Depend() {
		return c1Depend;
	}
	public void setC1Depend(Integer c1Depend) {
		this.c1Depend = c1Depend;
	}
	public String getC1Educls() {
		return c1Educls;
	}
	public void setC1Educls(String c1Educls) {
		this.c1Educls = c1Educls;
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
	public String getC1Hmst() {
		return c1Hmst;
	}
	public void setC1Hmst(String c1Hmst) {
		this.c1Hmst = c1Hmst;
	}
	public BigDecimal getC4Hmamt() {
		return c4Hmamt;
	}
	public void setC4Hmamt(BigDecimal c4Hmamt) {
		this.c4Hmamt = c4Hmamt;
	}
	public Long getC1Hloan() {
		return c1Hloan;
	}
	public void setC1Hloan(Long c1Hloan) {
		this.c1Hloan = c1Hloan;
	}
	public Integer getC4Hloanyr() {
		return c4Hloanyr;
	}
	public void setC4Hloanyr(Integer c4Hloanyr) {
		this.c4Hloanyr = c4Hloanyr;
	}
	public String getC1Carst() {
		return c1Carst;
	}
	public void setC1Carst(String c1Carst) {
		this.c1Carst = c1Carst;
	}
	public String getC1Carnbr() {
		return c1Carnbr;
	}
	public void setC1Carnbr(String c1Carnbr) {
		this.c1Carnbr = c1Carnbr;
	}
	public String getC1Carbrnd() {
		return c1Carbrnd;
	}
	public void setC1Carbrnd(String c1Carbrnd) {
		this.c1Carbrnd = c1Carbrnd;
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
	public String getC1Cobuscd() {
		return c1Cobuscd;
	}
	public void setC1Cobuscd(String c1Cobuscd) {
		this.c1Cobuscd = c1Cobuscd;
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
	public String getC1Cocode() {
		return c1Cocode;
	}
	public void setC1Cocode(String c1Cocode) {
		this.c1Cocode = c1Cocode;
	}
	public String getC1Cokind() {
		return c1Cokind;
	}
	public void setC1Cokind(String c1Cokind) {
		this.c1Cokind = c1Cokind;
	}
	public String getC1Coduty() {
		return c1Coduty;
	}
	public void setC1Coduty(String c1Coduty) {
		this.c1Coduty = c1Coduty;
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
	public Integer getC1Fcoyr() {
		return c1Fcoyr;
	}
	public void setC1Fcoyr(Integer c1Fcoyr) {
		this.c1Fcoyr = c1Fcoyr;
	}
	public String getC1Fcotel() {
		return c1Fcotel;
	}
	public void setC1Fcotel(String c1Fcotel) {
		this.c1Fcotel = c1Fcotel;
	}
	public BigDecimal getC1Foreann() {
		return c1Foreann;
	}
	public void setC1Foreann(BigDecimal c1Foreann) {
		this.c1Foreann = c1Foreann;
	}
	public String getC4Cycadd1() {
		return c4Cycadd1;
	}
	public void setC4Cycadd1(String c4Cycadd1) {
		this.c4Cycadd1 = c4Cycadd1;
	}
	public Integer getC4Pinreq() {
		return c4Pinreq;
	}
	public void setC4Pinreq(Integer c4Pinreq) {
		this.c4Pinreq = c4Pinreq;
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
	public String getC4Othtype() {
		return c4Othtype;
	}
	public void setC4Othtype(String c4Othtype) {
		this.c4Othtype = c4Othtype;
	}
	public String getC1Rename() {
		return c1Rename;
	}
	public void setC1Rename(String c1Rename) {
		this.c1Rename = c1Rename;
	}
	public String getC1Reship() {
		return c1Reship;
	}
	public void setC1Reship(String c1Reship) {
		this.c1Reship = c1Reship;
	}
	public String getC1Retel() {
		return c1Retel;
	}
	public void setC1Retel(String c1Retel) {
		this.c1Retel = c1Retel;
	}
	public String getC1Remobil() {
		return c1Remobil;
	}
	public void setC1Remobil(String c1Remobil) {
		this.c1Remobil = c1Remobil;
	}
	public String getC1Xname1() {
		return c1Xname1;
	}
	public void setC1Xname1(String c1Xname1) {
		this.c1Xname1 = c1Xname1;
	}
	public String getC1Xship1() {
		return c1Xship1;
	}
	public void setC1Xship1(String c1Xship1) {
		this.c1Xship1 = c1Xship1;
	}
	public String getC1Xtel1() {
		return c1Xtel1;
	}
	public void setC1Xtel1(String c1Xtel1) {
		this.c1Xtel1 = c1Xtel1;
	}
	public String getC1Xmobil1() {
		return c1Xmobil1;
	}
	public void setC1Xmobil1(String c1Xmobil1) {
		this.c1Xmobil1 = c1Xmobil1;
	}
	public Integer getC5Ptplan() {
		return c5Ptplan;
	}
	public void setC5Ptplan(Integer c5Ptplan) {
		this.c5Ptplan = c5Ptplan;
	}
	public Integer getC4Messreq() {
		return c4Messreq;
	}
	public void setC4Messreq(Integer c4Messreq) {
		this.c4Messreq = c4Messreq;
	}
	public Integer getC4Rushflg() {
		return c4Rushflg;
	}
	public void setC4Rushflg(Integer c4Rushflg) {
		this.c4Rushflg = c4Rushflg;
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
	public Integer getC4Cycdate() {
		return c4Cycdate;
	}
	public void setC4Cycdate(Integer c4Cycdate) {
		this.c4Cycdate = c4Cycdate;
	}
	public String getC4Stmcode() {
		return c4Stmcode;
	}
	public void setC4Stmcode(String c4Stmcode) {
		this.c4Stmcode = c4Stmcode;
	}
	public String getC2Signck() {
		return c2Signck;
	}
	public void setC2Signck(String c2Signck) {
		this.c2Signck = c2Signck;
	}
	public String getC4Giftn2() {
		return c4Giftn2;
	}
	public void setC4Giftn2(String c4Giftn2) {
		this.c4Giftn2 = c4Giftn2;
	}
	public String getC4Giftno() {
		return c4Giftno;
	}
	public void setC4Giftno(String c4Giftno) {
		this.c4Giftno = c4Giftno;
	}
	public String getC4Apbtid() {
		return c4Apbtid;
	}
	public void setC4Apbtid(String c4Apbtid) {
		this.c4Apbtid = c4Apbtid;
	}
	public String getC4Othcard() {
		return c4Othcard;
	}
	public void setC4Othcard(String c4Othcard) {
		this.c4Othcard = c4Othcard;
	}
	public Long getC4Othcrq() {
		return c4Othcrq;
	}
	public void setC4Othcrq(Long c4Othcrq) {
		this.c4Othcrq = c4Othcrq;
	}
	public Long getC4Othcred() {
		return c4Othcred;
	}
	public void setC4Othcred(Long c4Othcred) {
		this.c4Othcred = c4Othcred;
	}
	public Integer getC4Vccred() {
		return c4Vccred;
	}
	public void setC4Vccred(Integer c4Vccred) {
		this.c4Vccred = c4Vccred;
	}
	public String getC4Vcode() {
		return c4Vcode;
	}
	public void setC4Vcode(String c4Vcode) {
		this.c4Vcode = c4Vcode;
	}
	public String getC5Abcode() {
		return c5Abcode;
	}
	public void setC5Abcode(String c5Abcode) {
		this.c5Abcode = c5Abcode;
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
	public String getC4Abphon() {
		return c4Abphon;
	}
	public void setC4Abphon(String c4Abphon) {
		this.c4Abphon = c4Abphon;
	}
	public String getC4Apbatch() {
		return c4Apbatch;
	}
	public void setC4Apbatch(String c4Apbatch) {
		this.c4Apbatch = c4Apbatch;
	}
	public String getC4Clyn1() {
		return c4Clyn1;
	}
	public void setC4Clyn1(String c4Clyn1) {
		this.c4Clyn1 = c4Clyn1;
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
	/*public String getC4Relship() {
		return c4Relship;
	}
	public void setC4Relship(String c4Relship) {
		this.c4Relship = c4Relship;
	}*/
	public Integer getC4Decemp() {
		return c4Decemp;
	}
	public void setC4Decemp(Integer c4Decemp) {
		this.c4Decemp = c4Decemp;
	}
	public Integer getC1Hmyr() {
		return c1Hmyr;
	}
	public void setC1Hmyr(Integer c1Hmyr) {
		this.c1Hmyr = c1Hmyr;
	}
	public String getC4Cdsel() {
		return c4Cdsel;
	}
	public void setC4Cdsel(String c4Cdsel) {
		this.c4Cdsel = c4Cdsel;
	}
	public String getYdjFlag() {
		return ydjFlag;
	}
	public void setYdjFlag(String ydjFlag) {
		this.ydjFlag = ydjFlag;
	}
	public String getC1Hmpost() {
		return c1Hmpost;
	}
	public void setC1Hmpost(String c1Hmpost) {
		this.c1Hmpost = c1Hmpost;
	}
	public String getC1Idpost() {
		return c1Idpost;
	}
	public void setC1Idpost(String c1Idpost) {
		this.c1Idpost = c1Idpost;
	}
	public String getC1Hmare() {
		return c1Hmare;
	}
	public void setC1Hmare(String c1Hmare) {
		this.c1Hmare = c1Hmare;
	}
	public String getC1Copost() {
		return c1Copost;
	}
	public void setC1Copost(String c1Copost) {
		this.c1Copost = c1Copost;
	}
	public String getC1Retelar() {
		return c1Retelar;
	}
	public void setC1Retelar(String c1Retelar) {
		this.c1Retelar = c1Retelar;
	}
	public String getC1Xtelar1() {
		return c1Xtelar1;
	}
	public void setC1Xtelar1(String c1Xtelar1) {
		this.c1Xtelar1 = c1Xtelar1;
	}
	public String getC1Title() {
		return c1Title;
	}
	public void setC1Title(String c1Title) {
		this.c1Title = c1Title;
	}
	public String getC4Recemp() {
		return c4Recemp;
	}
	public void setC4Recemp(String c4Recemp) {
		this.c4Recemp = c4Recemp;
	}
	
}
