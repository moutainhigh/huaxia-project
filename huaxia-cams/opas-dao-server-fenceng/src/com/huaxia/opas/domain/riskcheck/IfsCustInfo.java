package com.huaxia.opas.domain.riskcheck;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class IfsCustInfo implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 6827480801473447636L;

	private String insideAppNo;

    private String appId;

    private String custid;

    private String custcode;

    private String mgcustcode;

    private String custname;

    private String sex;

    private Short age;

    private String certtype;

    private String certno;

    private String certnonew;

    private Date validat;

    private String mobilephone;

    private String phone;

    private String homephone;

    private String busphone;

    private Date opendate;

    private String marriedstatus;

    private String educationstatus;

    private String homeaddr;

    private String homepostcode;

    private String addr;

    private String opostcode;

    private String busaddr;

    private String buspostcode;

    private String fax;

    private String emailaddr;

    private String preinvst;

    private String bloodtype;

    private String relbelief;

    private String conttime;

    private String borndate;

    private Date lunarcal;

    private String nation;

    private String country;

    private String originplace;

    private String branch;

    private String subbranch;

    private String isemp;

    private String satis;

    private String company;

    private String workpost;

    private String profession;

    private String dutie;

    private String compnat;

    private String indtype;

    private BigDecimal yearincome;

    private Date workage;

    private String custman;

    private String status;

    private String isDisturb;

    private String dDate;

    private String isCreditCard;

    public String getInsideAppNo() {
        return insideAppNo;
    }

    public void setInsideAppNo(String insideAppNo) {
        this.insideAppNo = insideAppNo == null ? null : insideAppNo.trim();
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId == null ? null : appId.trim();
    }

    public String getCustid() {
        return custid;
    }

    public void setCustid(String custid) {
        this.custid = custid == null ? null : custid.trim();
    }

    public String getCustcode() {
        return custcode;
    }

    public void setCustcode(String custcode) {
        this.custcode = custcode == null ? null : custcode.trim();
    }

    public String getMgcustcode() {
        return mgcustcode;
    }

    public void setMgcustcode(String mgcustcode) {
        this.mgcustcode = mgcustcode == null ? null : mgcustcode.trim();
    }

    public String getCustname() {
        return custname;
    }

    public void setCustname(String custname) {
        this.custname = custname == null ? null : custname.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public Short getAge() {
        return age;
    }

    public void setAge(Short age) {
        this.age = age;
    }

    public String getCerttype() {
        return certtype;
    }

    public void setCerttype(String certtype) {
        this.certtype = certtype == null ? null : certtype.trim();
    }

    public String getCertno() {
        return certno;
    }

    public void setCertno(String certno) {
        this.certno = certno == null ? null : certno.trim();
    }

    public String getCertnonew() {
        return certnonew;
    }

    public void setCertnonew(String certnonew) {
        this.certnonew = certnonew == null ? null : certnonew.trim();
    }

    public Date getValidat() {
        return validat;
    }

    public void setValidat(Date validat) {
        this.validat = validat;
    }

    public String getMobilephone() {
        return mobilephone;
    }

    public void setMobilephone(String mobilephone) {
        this.mobilephone = mobilephone == null ? null : mobilephone.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getHomephone() {
        return homephone;
    }

    public void setHomephone(String homephone) {
        this.homephone = homephone == null ? null : homephone.trim();
    }

    public String getBusphone() {
        return busphone;
    }

    public void setBusphone(String busphone) {
        this.busphone = busphone == null ? null : busphone.trim();
    }

    public Date getOpendate() {
        return opendate;
    }

    public void setOpendate(Date opendate) {
        this.opendate = opendate;
    }

    public String getMarriedstatus() {
        return marriedstatus;
    }

    public void setMarriedstatus(String marriedstatus) {
        this.marriedstatus = marriedstatus == null ? null : marriedstatus.trim();
    }

    public String getEducationstatus() {
        return educationstatus;
    }

    public void setEducationstatus(String educationstatus) {
        this.educationstatus = educationstatus == null ? null : educationstatus.trim();
    }

    public String getHomeaddr() {
        return homeaddr;
    }

    public void setHomeaddr(String homeaddr) {
        this.homeaddr = homeaddr == null ? null : homeaddr.trim();
    }

    public String getHomepostcode() {
        return homepostcode;
    }

    public void setHomepostcode(String homepostcode) {
        this.homepostcode = homepostcode == null ? null : homepostcode.trim();
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr == null ? null : addr.trim();
    }

    public String getOpostcode() {
        return opostcode;
    }

    public void setOpostcode(String opostcode) {
        this.opostcode = opostcode == null ? null : opostcode.trim();
    }

    public String getBusaddr() {
        return busaddr;
    }

    public void setBusaddr(String busaddr) {
        this.busaddr = busaddr == null ? null : busaddr.trim();
    }

    public String getBuspostcode() {
        return buspostcode;
    }

    public void setBuspostcode(String buspostcode) {
        this.buspostcode = buspostcode == null ? null : buspostcode.trim();
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax == null ? null : fax.trim();
    }

    public String getEmailaddr() {
        return emailaddr;
    }

    public void setEmailaddr(String emailaddr) {
        this.emailaddr = emailaddr == null ? null : emailaddr.trim();
    }

    public String getPreinvst() {
        return preinvst;
    }

    public void setPreinvst(String preinvst) {
        this.preinvst = preinvst == null ? null : preinvst.trim();
    }

    public String getBloodtype() {
        return bloodtype;
    }

    public void setBloodtype(String bloodtype) {
        this.bloodtype = bloodtype == null ? null : bloodtype.trim();
    }

    public String getRelbelief() {
        return relbelief;
    }

    public void setRelbelief(String relbelief) {
        this.relbelief = relbelief == null ? null : relbelief.trim();
    }

    public String getConttime() {
        return conttime;
    }

    public void setConttime(String conttime) {
        this.conttime = conttime == null ? null : conttime.trim();
    }

    public String getBorndate() {
        return borndate;
    }

    public void setBorndate(String borndate) {
        this.borndate = borndate == null ? null : borndate.trim();
    }

    public Date getLunarcal() {
        return lunarcal;
    }

    public void setLunarcal(Date lunarcal) {
        this.lunarcal = lunarcal;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation == null ? null : nation.trim();
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country == null ? null : country.trim();
    }

    public String getOriginplace() {
        return originplace;
    }

    public void setOriginplace(String originplace) {
        this.originplace = originplace == null ? null : originplace.trim();
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch == null ? null : branch.trim();
    }

    public String getSubbranch() {
        return subbranch;
    }

    public void setSubbranch(String subbranch) {
        this.subbranch = subbranch == null ? null : subbranch.trim();
    }

    public String getIsemp() {
        return isemp;
    }

    public void setIsemp(String isemp) {
        this.isemp = isemp == null ? null : isemp.trim();
    }

    public String getSatis() {
        return satis;
    }

    public void setSatis(String satis) {
        this.satis = satis == null ? null : satis.trim();
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company == null ? null : company.trim();
    }

    public String getWorkpost() {
        return workpost;
    }

    public void setWorkpost(String workpost) {
        this.workpost = workpost == null ? null : workpost.trim();
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession == null ? null : profession.trim();
    }

    public String getDutie() {
        return dutie;
    }

    public void setDutie(String dutie) {
        this.dutie = dutie == null ? null : dutie.trim();
    }

    public String getCompnat() {
        return compnat;
    }

    public void setCompnat(String compnat) {
        this.compnat = compnat == null ? null : compnat.trim();
    }

    public String getIndtype() {
        return indtype;
    }

    public void setIndtype(String indtype) {
        this.indtype = indtype == null ? null : indtype.trim();
    }

    public BigDecimal getYearincome() {
        return yearincome;
    }

    public void setYearincome(BigDecimal yearincome) {
        this.yearincome = yearincome;
    }

    public Date getWorkage() {
        return workage;
    }

    public void setWorkage(Date workage) {
        this.workage = workage;
    }

    public String getCustman() {
        return custman;
    }

    public void setCustman(String custman) {
        this.custman = custman == null ? null : custman.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getIsDisturb() {
        return isDisturb;
    }

    public void setIsDisturb(String isDisturb) {
        this.isDisturb = isDisturb == null ? null : isDisturb.trim();
    }

    public String getdDate() {
        return dDate;
    }

    public void setdDate(String dDate) {
        this.dDate = dDate == null ? null : dDate.trim();
    }

    public String getIsCreditCard() {
        return isCreditCard;
    }

    public void setIsCreditCard(String isCreditCard) {
        this.isCreditCard = isCreditCard == null ? null : isCreditCard.trim();
    }
}