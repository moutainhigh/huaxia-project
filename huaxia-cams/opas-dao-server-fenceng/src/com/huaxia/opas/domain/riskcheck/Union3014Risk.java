package com.huaxia.opas.domain.riskcheck;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * @description: 联机接口3014响应报文对应实体类
 * @author 张志宽
 * @since 2017年4月26日
 * @version 1.0
 * @lastUpdateDate
 * @lastUpdateUser
 */
public class Union3014Risk implements Serializable{

	private static final long serialVersionUID = -8380184726788903759L;

	private String id;

    private Integer trxtype;

    private String retcode;

    private Integer bnknbr;

    private String source;

    private Integer brnNo;

    private Integer opeNo;

    private Integer seqno;

    private String cardnbr;

    private Integer pinchk;

    private Integer smsyn;

    private Integer mailyn;

    private Integer lowyn;

    private Integer withdrw;

    private Integer lowamt1;

    private Integer lowamt2;

    private Integer cynbr;

    private BigDecimal credlim;

    private Integer lmtOpt;

    private Integer crdpcent;

    private BigDecimal crdlimit;

    private String name;

    private String stmCode;

    private String addType;

    private String email;

    private String add1;

    private String add2;

    private String add3;

    private String add4;

    private String add5;

    private Integer postCd;

    private Integer lySet;

    private String lysrvlv;

    private String lyfeelv;

    private Integer appdt;

    private String nextst;

    private String reiflg;

    private Integer acptno;

    private Long pinlim;

    private String forin;

    private Date chgdy;

    private String xfrchnl;

    private Integer cpno;

    private Integer nbrmth;

    private String smsfreeyn;

    private String track2N;

    private String purchYn;

    private BigDecimal minAmt;

    private BigDecimal maxAmt;

    private BigDecimal minAutamt;

    private Integer fbPos;

    private String fbposSt;

    private String fbposEd;

    private Integer fbAtm;

    private String fbatmSt;

    private String fbatmEd;

    private Integer fbNocard;

    private String nocardSt;

    private String nocardEd;

    private String forinSt;

    private String forinEd;

    private String bak;

    private Date crtTime;

    private Date crtDate;

    private String crtUser;

    private Date lstUpdTime;

    private Date lstUpdDate;

    private String lstUpdUser;

    private String appId;
    
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getTrxtype() {
		return trxtype;
	}

	public void setTrxtype(Integer trxtype) {
		this.trxtype = trxtype;
	}

	public String getRetcode() {
		return retcode;
	}

	public void setRetcode(String retcode) {
		this.retcode = retcode;
	}

	public Integer getBnknbr() {
		return bnknbr;
	}

	public void setBnknbr(Integer bnknbr) {
		this.bnknbr = bnknbr;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public Integer getBrnNo() {
		return brnNo;
	}

	public void setBrnNo(Integer brnNo) {
		this.brnNo = brnNo;
	}

	public Integer getOpeNo() {
		return opeNo;
	}

	public void setOpeNo(Integer opeNo) {
		this.opeNo = opeNo;
	}

	public Integer getSeqno() {
		return seqno;
	}

	public void setSeqno(Integer seqno) {
		this.seqno = seqno;
	}

	public String getCardnbr() {
		return cardnbr;
	}

	public void setCardnbr(String cardnbr) {
		this.cardnbr = cardnbr;
	}

	public Integer getPinchk() {
		return pinchk;
	}

	public void setPinchk(Integer pinchk) {
		this.pinchk = pinchk;
	}

	public Integer getSmsyn() {
		return smsyn;
	}

	public void setSmsyn(Integer smsyn) {
		this.smsyn = smsyn;
	}

	public Integer getMailyn() {
		return mailyn;
	}

	public void setMailyn(Integer mailyn) {
		this.mailyn = mailyn;
	}

	public Integer getLowyn() {
		return lowyn;
	}

	public void setLowyn(Integer lowyn) {
		this.lowyn = lowyn;
	}

	public Integer getWithdrw() {
		return withdrw;
	}

	public void setWithdrw(Integer withdrw) {
		this.withdrw = withdrw;
	}

	public Integer getLowamt1() {
		return lowamt1;
	}

	public void setLowamt1(Integer lowamt1) {
		this.lowamt1 = lowamt1;
	}

	public Integer getLowamt2() {
		return lowamt2;
	}

	public void setLowamt2(Integer lowamt2) {
		this.lowamt2 = lowamt2;
	}

	public Integer getCynbr() {
		return cynbr;
	}

	public void setCynbr(Integer cynbr) {
		this.cynbr = cynbr;
	}

	public BigDecimal getCredlim() {
		return credlim;
	}

	public void setCredlim(BigDecimal credlim) {
		this.credlim = credlim;
	}

	public Integer getLmtOpt() {
		return lmtOpt;
	}

	public void setLmtOpt(Integer lmtOpt) {
		this.lmtOpt = lmtOpt;
	}

	public Integer getCrdpcent() {
		return crdpcent;
	}

	public void setCrdpcent(Integer crdpcent) {
		this.crdpcent = crdpcent;
	}

	public BigDecimal getCrdlimit() {
		return crdlimit;
	}

	public void setCrdlimit(BigDecimal crdlimit) {
		this.crdlimit = crdlimit;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStmCode() {
		return stmCode;
	}

	public void setStmCode(String stmCode) {
		this.stmCode = stmCode;
	}

	public String getAddType() {
		return addType;
	}

	public void setAddType(String addType) {
		this.addType = addType;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAdd1() {
		return add1;
	}

	public void setAdd1(String add1) {
		this.add1 = add1;
	}

	public String getAdd2() {
		return add2;
	}

	public void setAdd2(String add2) {
		this.add2 = add2;
	}

	public String getAdd3() {
		return add3;
	}

	public void setAdd3(String add3) {
		this.add3 = add3;
	}

	public String getAdd4() {
		return add4;
	}

	public void setAdd4(String add4) {
		this.add4 = add4;
	}

	public String getAdd5() {
		return add5;
	}

	public void setAdd5(String add5) {
		this.add5 = add5;
	}

	public Integer getPostCd() {
		return postCd;
	}

	public void setPostCd(Integer postCd) {
		this.postCd = postCd;
	}

	public Integer getLySet() {
		return lySet;
	}

	public void setLySet(Integer lySet) {
		this.lySet = lySet;
	}

	public String getLysrvlv() {
		return lysrvlv;
	}

	public void setLysrvlv(String lysrvlv) {
		this.lysrvlv = lysrvlv;
	}

	public String getLyfeelv() {
		return lyfeelv;
	}

	public void setLyfeelv(String lyfeelv) {
		this.lyfeelv = lyfeelv;
	}

	public Integer getAppdt() {
		return appdt;
	}

	public void setAppdt(Integer appdt) {
		this.appdt = appdt;
	}

	public String getNextst() {
		return nextst;
	}

	public void setNextst(String nextst) {
		this.nextst = nextst;
	}

	public String getReiflg() {
		return reiflg;
	}

	public void setReiflg(String reiflg) {
		this.reiflg = reiflg;
	}

	public Integer getAcptno() {
		return acptno;
	}

	public void setAcptno(Integer acptno) {
		this.acptno = acptno;
	}

	public Long getPinlim() {
		return pinlim;
	}

	public void setPinlim(Long pinlim) {
		this.pinlim = pinlim;
	}

	public String getForin() {
		return forin;
	}

	public void setForin(String forin) {
		this.forin = forin;
	}

	public Date getChgdy() {
		return chgdy;
	}

	public void setChgdy(Date chgdy) {
		this.chgdy = chgdy;
	}

	public String getXfrchnl() {
		return xfrchnl;
	}

	public void setXfrchnl(String xfrchnl) {
		this.xfrchnl = xfrchnl;
	}

	public Integer getCpno() {
		return cpno;
	}

	public void setCpno(Integer cpno) {
		this.cpno = cpno;
	}

	public Integer getNbrmth() {
		return nbrmth;
	}

	public void setNbrmth(Integer nbrmth) {
		this.nbrmth = nbrmth;
	}

	public String getSmsfreeyn() {
		return smsfreeyn;
	}

	public void setSmsfreeyn(String smsfreeyn) {
		this.smsfreeyn = smsfreeyn;
	}

	public String getTrack2N() {
		return track2N;
	}

	public void setTrack2N(String track2n) {
		track2N = track2n;
	}

	public String getPurchYn() {
		return purchYn;
	}

	public void setPurchYn(String purchYn) {
		this.purchYn = purchYn;
	}

	public BigDecimal getMinAmt() {
		return minAmt;
	}

	public void setMinAmt(BigDecimal minAmt) {
		this.minAmt = minAmt;
	}

	public BigDecimal getMaxAmt() {
		return maxAmt;
	}

	public void setMaxAmt(BigDecimal maxAmt) {
		this.maxAmt = maxAmt;
	}

	public BigDecimal getMinAutamt() {
		return minAutamt;
	}

	public void setMinAutamt(BigDecimal minAutamt) {
		this.minAutamt = minAutamt;
	}

	public Integer getFbPos() {
		return fbPos;
	}

	public void setFbPos(Integer fbPos) {
		this.fbPos = fbPos;
	}

	public String getFbposSt() {
		return fbposSt;
	}

	public void setFbposSt(String fbposSt) {
		this.fbposSt = fbposSt;
	}

	public String getFbposEd() {
		return fbposEd;
	}

	public void setFbposEd(String fbposEd) {
		this.fbposEd = fbposEd;
	}

	public Integer getFbAtm() {
		return fbAtm;
	}

	public void setFbAtm(Integer fbAtm) {
		this.fbAtm = fbAtm;
	}

	public String getFbatmSt() {
		return fbatmSt;
	}

	public void setFbatmSt(String fbatmSt) {
		this.fbatmSt = fbatmSt;
	}

	public String getFbatmEd() {
		return fbatmEd;
	}

	public void setFbatmEd(String fbatmEd) {
		this.fbatmEd = fbatmEd;
	}

	public Integer getFbNocard() {
		return fbNocard;
	}

	public void setFbNocard(Integer fbNocard) {
		this.fbNocard = fbNocard;
	}

	public String getNocardSt() {
		return nocardSt;
	}

	public void setNocardSt(String nocardSt) {
		this.nocardSt = nocardSt;
	}

	public String getNocardEd() {
		return nocardEd;
	}

	public void setNocardEd(String nocardEd) {
		this.nocardEd = nocardEd;
	}

	public String getForinSt() {
		return forinSt;
	}

	public void setForinSt(String forinSt) {
		this.forinSt = forinSt;
	}

	public String getForinEd() {
		return forinEd;
	}

	public void setForinEd(String forinEd) {
		this.forinEd = forinEd;
	}

	public String getBak() {
		return bak;
	}

	public void setBak(String bak) {
		this.bak = bak;
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
    

}