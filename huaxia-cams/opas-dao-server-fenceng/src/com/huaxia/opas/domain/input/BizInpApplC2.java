package com.huaxia.opas.domain.input;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 
 * @description: 进件信息附卡字段
 * @author 张志宽
 * @since 2017年2月25日
 * @version 1.0
 * @lastUpdateDate
 * @lastUpdateUser
 */
public class BizInpApplC2 implements Serializable{
	
	private static final long serialVersionUID = -4231907283453527770L;
	
	private String c2BirthS;
	
	private String insideAppNo;

	private Integer c1Credpct;

    private Long c1Credamt;

    private String c2Idtype;

    private String c2Idnbr;

    private String c2Secnbr;

    private String c2Cname;

    private String c2Ename;

    private String c2Gender;
    @JSONField(format="yyyy-MM-dd")
    private Date c2Birth;

    private String c2Nation;

    private String c2Marst;

    private String c2Educls;

    private String c2Hmare;

    private String c2Hmtel;

    private String c2Mobile;

    private String c2Relship;

    private String c2Email;

    private String c2Coname;

    private String c2Codept;

    private String c2Coduty;

    private String c2Cocode;

    private String c2Title;

    private String c2Cokind;

    private String c2Cotel;

    private String c2Coext;

    private Integer c2Coyr;

    private BigDecimal c2Earn;

    private String c2Busiser;

    private String c2Promqu;

    private String c2Proman;

    private String c2Iddt2;

    private String c2Isdp2;

    private String c2Natncd2;//附卡国籍代码 

    private String c4Sname;

    private Integer c4Pinreq2;

    private String c4Vercod2;

    private String c4Pin2;

    private String c4Srvcd2;

    private String c4Psnid2;

    private String c4Clyn2;

    private String c4Buemb2;

    private String c5Sercd21;

    private String c5Sercd22;

    private String c5Sercd23;

    private String c5Sercd24;

    private String c5Sercd25;

    private String c5Sercd26;

    private String c5Sercd27;

    private String c5Sercd28;

    private String c5Sercd29;

    private String c5Prod;

    private String c5Acctype;
    
    private String c5Idte2;

    private String c5Atm2;

    private String c5Tele2;

    private String c5Net2;

    private String c5Phone2;

    private Integer c5Cpno2;

    private Integer c5Nbrmth2;

    private String c5Mailco2;

    private String c5Vprod;

    private String c5Vfeecd;

    private String c5Vembnm;

    private Long c5Pinlim2;

    private String c5Cuseg2;
    @JSONField(format="yyyy-MM-dd")
    private Date c5Sgndte2;
    @JSONField(format="yyyy-MM-dd")
    private Date c5Regdte2;

    private String c5Curef2;

    private String c5Mastno2;

    private String c5Cardto2;

    private String c5Comnm2s;

    private String c5Cdsel2;

    private Integer c5Feegrp2;

    private Integer c5CcYn2;

    private Integer c5EcYn2;

    private String c5PoiCod2;

    private String c5WorkTy2;

    private String c5ProCod2;

    private String c5LiveCy2;

    private String c5QqNbr2;

    private String c5WeixinNbr2;

    private String c5Isdpif2;

    private String thirdPartyYn2;

    private Integer c5Vexpydt;

    private Integer c5RmbBill2;

    private String seriesId2;

    private Integer c6RelFlag2;

    private String c6ClassNbr2;

    private String c6WhiteRsn2;

    private String c6Ethnic2;

    private String c6Occupation2;

    private Integer c6Unqpass2;

    private BigDecimal c6MaxMpamt2;

    private BigDecimal c6MinMpamt2;
    
    private String c6Gatnbr2;//附卡持卡人港澳台通行证
    
    
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
    
    private String appId;
    
	public String getInsideAppNo() {
		return insideAppNo;
	}

	public void setInsideAppNo(String insideAppNo) {
		this.insideAppNo = insideAppNo;
	}

	public Integer getC1Credpct() {
		return c1Credpct;
	}

	public void setC1Credpct(Integer c1Credpct) {
		this.c1Credpct = c1Credpct;
	}

	public Long getC1Credamt() {
		return c1Credamt;
	}

	public void setC1Credamt(Long c1Credamt) {
		this.c1Credamt = c1Credamt;
	}

	public String getC2Idtype() {
		return c2Idtype;
	}

	public void setC2Idtype(String c2Idtype) {
		this.c2Idtype = c2Idtype;
	}

	public String getC2Idnbr() {
		return c2Idnbr;
	}

	public void setC2Idnbr(String c2Idnbr) {
		this.c2Idnbr = c2Idnbr;
	}

	public String getC2Secnbr() {
		return c2Secnbr;
	}

	public void setC2Secnbr(String c2Secnbr) {
		this.c2Secnbr = c2Secnbr;
	}

	public String getC2Cname() {
		return c2Cname;
	}

	public void setC2Cname(String c2Cname) {
		this.c2Cname = c2Cname;
	}

	public String getC2Ename() {
		return c2Ename;
	}

	public void setC2Ename(String c2Ename) {
		this.c2Ename = c2Ename;
	}

	public String getC2Gender() {
		return c2Gender;
	}

	public void setC2Gender(String c2Gender) {
		this.c2Gender = c2Gender;
	}

	public Date getC2Birth() {
		return c2Birth;
	}

	public void setC2Birth(Date c2Birth) {
		this.c2Birth = c2Birth;
	}

	public String getC2Nation() {
		return c2Nation;
	}

	public void setC2Nation(String c2Nation) {
		this.c2Nation = c2Nation;
	}

	public String getC2Marst() {
		return c2Marst;
	}

	public void setC2Marst(String c2Marst) {
		this.c2Marst = c2Marst;
	}

	public String getC2Educls() {
		return c2Educls;
	}

	public void setC2Educls(String c2Educls) {
		this.c2Educls = c2Educls;
	}

	public String getC2Hmare() {
		return c2Hmare;
	}

	public void setC2Hmare(String c2Hmare) {
		this.c2Hmare = c2Hmare;
	}

	public String getC2Hmtel() {
		return c2Hmtel;
	}

	public void setC2Hmtel(String c2Hmtel) {
		this.c2Hmtel = c2Hmtel;
	}

	public String getC2Mobile() {
		return c2Mobile;
	}

	public void setC2Mobile(String c2Mobile) {
		this.c2Mobile = c2Mobile;
	}

	public String getC2Relship() {
		return c2Relship;
	}

	public void setC2Relship(String c2Relship) {
		this.c2Relship = c2Relship;
	}

	public String getC2Email() {
		return c2Email;
	}

	public void setC2Email(String c2Email) {
		this.c2Email = c2Email;
	}

	public String getC2Coname() {
		return c2Coname;
	}

	public void setC2Coname(String c2Coname) {
		this.c2Coname = c2Coname;
	}

	public String getC2Codept() {
		return c2Codept;
	}

	public void setC2Codept(String c2Codept) {
		this.c2Codept = c2Codept;
	}

	public String getC2Coduty() {
		return c2Coduty;
	}

	public void setC2Coduty(String c2Coduty) {
		this.c2Coduty = c2Coduty;
	}

	public String getC2Cocode() {
		return c2Cocode;
	}

	public void setC2Cocode(String c2Cocode) {
		this.c2Cocode = c2Cocode;
	}

	public String getC2Title() {
		return c2Title;
	}

	public void setC2Title(String c2Title) {
		this.c2Title = c2Title;
	}

	public String getC2Cokind() {
		return c2Cokind;
	}

	public void setC2Cokind(String c2Cokind) {
		this.c2Cokind = c2Cokind;
	}

	public String getC2Cotel() {
		return c2Cotel;
	}

	public void setC2Cotel(String c2Cotel) {
		this.c2Cotel = c2Cotel;
	}

	public String getC2Coext() {
		return c2Coext;
	}

	public void setC2Coext(String c2Coext) {
		this.c2Coext = c2Coext;
	}

	public Integer getC2Coyr() {
		return c2Coyr;
	}

	public void setC2Coyr(Integer c2Coyr) {
		this.c2Coyr = c2Coyr;
	}

	public BigDecimal getC2Earn() {
		return c2Earn;
	}

	public void setC2Earn(BigDecimal c2Earn) {
		this.c2Earn = c2Earn;
	}

	public String getC2Busiser() {
		return c2Busiser;
	}

	public void setC2Busiser(String c2Busiser) {
		this.c2Busiser = c2Busiser;
	}

	public String getC2Promqu() {
		return c2Promqu;
	}

	public void setC2Promqu(String c2Promqu) {
		this.c2Promqu = c2Promqu;
	}

	public String getC2Proman() {
		return c2Proman;
	}

	public void setC2Proman(String c2Proman) {
		this.c2Proman = c2Proman;
	}


	public String getC2Isdp2() {
		return c2Isdp2;
	}

	public void setC2Isdp2(String c2Isdp2) {
		this.c2Isdp2 = c2Isdp2;
	}

	public String getC2Natncd2() {
		return c2Natncd2;
	}

	public void setC2Natncd2(String c2Natncd2) {
		this.c2Natncd2 = c2Natncd2;
	}

	public String getC4Sname() {
		return c4Sname;
	}

	public void setC4Sname(String c4Sname) {
		this.c4Sname = c4Sname;
	}

	public Integer getC4Pinreq2() {
		return c4Pinreq2;
	}

	public void setC4Pinreq2(Integer c4Pinreq2) {
		this.c4Pinreq2 = c4Pinreq2;
	}

	public String getC4Vercod2() {
		return c4Vercod2;
	}

	public void setC4Vercod2(String c4Vercod2) {
		this.c4Vercod2 = c4Vercod2;
	}

	public String getC4Pin2() {
		return c4Pin2;
	}

	public void setC4Pin2(String c4Pin2) {
		this.c4Pin2 = c4Pin2;
	}

	public String getC4Srvcd2() {
		return c4Srvcd2;
	}

	public void setC4Srvcd2(String c4Srvcd2) {
		this.c4Srvcd2 = c4Srvcd2;
	}

	public String getC4Psnid2() {
		return c4Psnid2;
	}

	public void setC4Psnid2(String c4Psnid2) {
		this.c4Psnid2 = c4Psnid2;
	}

	public String getC4Clyn2() {
		return c4Clyn2;
	}

	public void setC4Clyn2(String c4Clyn2) {
		this.c4Clyn2 = c4Clyn2;
	}

	public String getC4Buemb2() {
		return c4Buemb2;
	}

	public void setC4Buemb2(String c4Buemb2) {
		this.c4Buemb2 = c4Buemb2;
	}

	public String getC5Sercd21() {
		return c5Sercd21;
	}

	public void setC5Sercd21(String c5Sercd21) {
		this.c5Sercd21 = c5Sercd21;
	}

	public String getC5Sercd22() {
		return c5Sercd22;
	}

	public void setC5Sercd22(String c5Sercd22) {
		this.c5Sercd22 = c5Sercd22;
	}

	public String getC5Sercd23() {
		return c5Sercd23;
	}

	public void setC5Sercd23(String c5Sercd23) {
		this.c5Sercd23 = c5Sercd23;
	}

	public String getC5Sercd24() {
		return c5Sercd24;
	}

	public void setC5Sercd24(String c5Sercd24) {
		this.c5Sercd24 = c5Sercd24;
	}

	public String getC5Sercd25() {
		return c5Sercd25;
	}

	public void setC5Sercd25(String c5Sercd25) {
		this.c5Sercd25 = c5Sercd25;
	}

	public String getC5Sercd26() {
		return c5Sercd26;
	}

	public void setC5Sercd26(String c5Sercd26) {
		this.c5Sercd26 = c5Sercd26;
	}

	public String getC5Sercd27() {
		return c5Sercd27;
	}

	public void setC5Sercd27(String c5Sercd27) {
		this.c5Sercd27 = c5Sercd27;
	}

	public String getC5Sercd28() {
		return c5Sercd28;
	}

	public void setC5Sercd28(String c5Sercd28) {
		this.c5Sercd28 = c5Sercd28;
	}

	public String getC5Sercd29() {
		return c5Sercd29;
	}

	public void setC5Sercd29(String c5Sercd29) {
		this.c5Sercd29 = c5Sercd29;
	}

	public String getC5Prod() {
		return c5Prod;
	}

	public void setC5Prod(String c5Prod) {
		this.c5Prod = c5Prod;
	}

	public String getC5Acctype() {
		return c5Acctype;
	}

	public void setC5Acctype(String c5Acctype) {
		this.c5Acctype = c5Acctype;
	}


	public String getC2Iddt2() {
		return c2Iddt2;
	}

	public void setC2Iddt2(String c2Iddt2) {
		this.c2Iddt2 = c2Iddt2;
	}

	public String getC5Idte2() {
		return c5Idte2;
	}

	public void setC5Idte2(String c5Idte2) {
		this.c5Idte2 = c5Idte2;
	}

	public String getC5Atm2() {
		return c5Atm2;
	}

	public void setC5Atm2(String c5Atm2) {
		this.c5Atm2 = c5Atm2;
	}

	public String getC5Tele2() {
		return c5Tele2;
	}

	public void setC5Tele2(String c5Tele2) {
		this.c5Tele2 = c5Tele2;
	}

	public String getC5Net2() {
		return c5Net2;
	}

	public void setC5Net2(String c5Net2) {
		this.c5Net2 = c5Net2;
	}

	public String getC5Phone2() {
		return c5Phone2;
	}

	public void setC5Phone2(String c5Phone2) {
		this.c5Phone2 = c5Phone2;
	}

	public Integer getC5Cpno2() {
		return c5Cpno2;
	}

	public void setC5Cpno2(Integer c5Cpno2) {
		this.c5Cpno2 = c5Cpno2;
	}

	public Integer getC5Nbrmth2() {
		return c5Nbrmth2;
	}

	public void setC5Nbrmth2(Integer c5Nbrmth2) {
		this.c5Nbrmth2 = c5Nbrmth2;
	}

	public String getC5Mailco2() {
		return c5Mailco2;
	}

	public void setC5Mailco2(String c5Mailco2) {
		this.c5Mailco2 = c5Mailco2;
	}

	public String getC5Vprod() {
		return c5Vprod;
	}

	public void setC5Vprod(String c5Vprod) {
		this.c5Vprod = c5Vprod;
	}

	public String getC5Vfeecd() {
		return c5Vfeecd;
	}

	public void setC5Vfeecd(String c5Vfeecd) {
		this.c5Vfeecd = c5Vfeecd;
	}

	public String getC5Vembnm() {
		return c5Vembnm;
	}

	public void setC5Vembnm(String c5Vembnm) {
		this.c5Vembnm = c5Vembnm;
	}

	public Long getC5Pinlim2() {
		return c5Pinlim2;
	}

	public void setC5Pinlim2(Long c5Pinlim2) {
		this.c5Pinlim2 = c5Pinlim2;
	}

	public String getC5Cuseg2() {
		return c5Cuseg2;
	}

	public void setC5Cuseg2(String c5Cuseg2) {
		this.c5Cuseg2 = c5Cuseg2;
	}

	public Date getC5Sgndte2() {
		return c5Sgndte2;
	}

	public void setC5Sgndte2(Date c5Sgndte2) {
		this.c5Sgndte2 = c5Sgndte2;
	}

	public Date getC5Regdte2() {
		return c5Regdte2;
	}

	public void setC5Regdte2(Date c5Regdte2) {
		this.c5Regdte2 = c5Regdte2;
	}

	public String getC5Curef2() {
		return c5Curef2;
	}

	public void setC5Curef2(String c5Curef2) {
		this.c5Curef2 = c5Curef2;
	}

	public String getC5Mastno2() {
		return c5Mastno2;
	}

	public void setC5Mastno2(String c5Mastno2) {
		this.c5Mastno2 = c5Mastno2;
	}

	public String getC5Cardto2() {
		return c5Cardto2;
	}

	public void setC5Cardto2(String c5Cardto2) {
		this.c5Cardto2 = c5Cardto2;
	}

	public String getC5Comnm2s() {
		return c5Comnm2s;
	}

	public void setC5Comnm2s(String c5Comnm2s) {
		this.c5Comnm2s = c5Comnm2s;
	}

	public String getC5Cdsel2() {
		return c5Cdsel2;
	}

	public void setC5Cdsel2(String c5Cdsel2) {
		this.c5Cdsel2 = c5Cdsel2;
	}

	public Integer getC5Feegrp2() {
		return c5Feegrp2;
	}

	public void setC5Feegrp2(Integer c5Feegrp2) {
		this.c5Feegrp2 = c5Feegrp2;
	}

	public Integer getC5CcYn2() {
		return c5CcYn2;
	}

	public void setC5CcYn2(Integer c5CcYn2) {
		this.c5CcYn2 = c5CcYn2;
	}

	public Integer getC5EcYn2() {
		return c5EcYn2;
	}

	public void setC5EcYn2(Integer c5EcYn2) {
		this.c5EcYn2 = c5EcYn2;
	}

	public String getC5PoiCod2() {
		return c5PoiCod2;
	}

	public void setC5PoiCod2(String c5PoiCod2) {
		this.c5PoiCod2 = c5PoiCod2;
	}

	public String getC5WorkTy2() {
		return c5WorkTy2;
	}

	public void setC5WorkTy2(String c5WorkTy2) {
		this.c5WorkTy2 = c5WorkTy2;
	}

	public String getC5ProCod2() {
		return c5ProCod2;
	}

	public void setC5ProCod2(String c5ProCod2) {
		this.c5ProCod2 = c5ProCod2;
	}

	public String getC5LiveCy2() {
		return c5LiveCy2;
	}

	public void setC5LiveCy2(String c5LiveCy2) {
		this.c5LiveCy2 = c5LiveCy2;
	}

	public String getC5QqNbr2() {
		return c5QqNbr2;
	}

	public void setC5QqNbr2(String c5QqNbr2) {
		this.c5QqNbr2 = c5QqNbr2;
	}

	public String getC5WeixinNbr2() {
		return c5WeixinNbr2;
	}

	public void setC5WeixinNbr2(String c5WeixinNbr2) {
		this.c5WeixinNbr2 = c5WeixinNbr2;
	}

	public String getC5Isdpif2() {
		return c5Isdpif2;
	}

	public void setC5Isdpif2(String c5Isdpif2) {
		this.c5Isdpif2 = c5Isdpif2;
	}

	public String getThirdPartyYn2() {
		return thirdPartyYn2;
	}

	public void setThirdPartyYn2(String thirdPartyYn2) {
		this.thirdPartyYn2 = thirdPartyYn2;
	}

	public Integer getC5Vexpydt() {
		return c5Vexpydt;
	}

	public void setC5Vexpydt(Integer c5Vexpydt) {
		this.c5Vexpydt = c5Vexpydt;
	}

	public Integer getC5RmbBill2() {
		return c5RmbBill2;
	}

	public void setC5RmbBill2(Integer c5RmbBill2) {
		this.c5RmbBill2 = c5RmbBill2;
	}

	public String getSeriesId2() {
		return seriesId2;
	}

	public void setSeriesId2(String seriesId2) {
		this.seriesId2 = seriesId2;
	}

	public Integer getC6RelFlag2() {
		return c6RelFlag2;
	}

	public void setC6RelFlag2(Integer c6RelFlag2) {
		this.c6RelFlag2 = c6RelFlag2;
	}

	public String getC6ClassNbr2() {
		return c6ClassNbr2;
	}

	public void setC6ClassNbr2(String c6ClassNbr2) {
		this.c6ClassNbr2 = c6ClassNbr2;
	}

	public String getC6WhiteRsn2() {
		return c6WhiteRsn2;
	}

	public void setC6WhiteRsn2(String c6WhiteRsn2) {
		this.c6WhiteRsn2 = c6WhiteRsn2;
	}

	public String getC6Ethnic2() {
		return c6Ethnic2;
	}

	public void setC6Ethnic2(String c6Ethnic2) {
		this.c6Ethnic2 = c6Ethnic2;
	}

	public String getC6Occupation2() {
		return c6Occupation2;
	}

	public void setC6Occupation2(String c6Occupation2) {
		this.c6Occupation2 = c6Occupation2;
	}

	public Integer getC6Unqpass2() {
		return c6Unqpass2;
	}

	public void setC6Unqpass2(Integer c6Unqpass2) {
		this.c6Unqpass2 = c6Unqpass2;
	}

	public BigDecimal getC6MaxMpamt2() {
		return c6MaxMpamt2;
	}

	public void setC6MaxMpamt2(BigDecimal c6MaxMpamt2) {
		this.c6MaxMpamt2 = c6MaxMpamt2;
	}

	public BigDecimal getC6MinMpamt2() {
		return c6MinMpamt2;
	}

	public void setC6MinMpamt2(BigDecimal c6MinMpamt2) {
		this.c6MinMpamt2 = c6MinMpamt2;
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
    public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getC2BirthS() {
		return c2BirthS;
	}

	public void setC2BirthS(String c2BirthS) {
		this.c2BirthS = c2BirthS;
	}
	
	public String getC6Gatnbr2() {
		return c6Gatnbr2;
	}

	public void setC6Gatnbr2(String c6Gatnbr2) {
		this.c6Gatnbr2 = c6Gatnbr2;
	}

}